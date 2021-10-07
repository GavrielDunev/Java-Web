package bg.softuni.MobiLeLeLe.service.impl;

import bg.softuni.MobiLeLeLe.model.entity.UserEntity;
import bg.softuni.MobiLeLeLe.model.entity.UserRoleEntity;
import bg.softuni.MobiLeLeLe.model.entity.enums.UserRoleEnum;
import bg.softuni.MobiLeLeLe.model.service.UserLoginServiceModel;
import bg.softuni.MobiLeLeLe.model.service.UserRegistrationServiceModel;
import bg.softuni.MobiLeLeLe.repository.UserRepository;
import bg.softuni.MobiLeLeLe.repository.UserRoleRepository;
import bg.softuni.MobiLeLeLe.service.UserService;
import bg.softuni.MobiLeLeLe.user.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final CurrentUser currentUser;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository, CurrentUser currentUser) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.currentUser = currentUser;
    }

    @Override
    public boolean isUsernameFree(String username) {
        return this.userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    @Override
    public boolean login(UserLoginServiceModel loginServiceModel) {
        Optional<UserEntity> user = this.userRepository.findByUsername(loginServiceModel.getUsername());

        if (user.isEmpty()) {
            logout();
            return false;
        } else {
            boolean success = passwordEncoder.matches(loginServiceModel.getRawPassword(),
                    user.get().getPassword());

            if (success) {
                UserEntity loggedInUser = user.get();
                this.currentUser.setLoggedIn(true).setUsername(loggedInUser.getUsername())
                        .setFirstName(loggedInUser.getFirstName())
                        .setLastName(loggedInUser.getLastName());

                loggedInUser.getRoles()
                        .forEach(r -> this.currentUser.addRole(r.getRole()));
            }

            return success;
        }
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    @Override
    public void logout() {
        this.currentUser.clear();
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegisterServiceModel) {

        UserRoleEntity userRole = this.userRoleRepository.findByRole(UserRoleEnum.USER);

        UserEntity newUser = new UserEntity()
                .setFirstName(userRegisterServiceModel.getFirstName())
                .setLastName(userRegisterServiceModel.getLastName())
                .setUsername(userRegisterServiceModel.getUsername())
                .setPassword(this.passwordEncoder.encode(userRegisterServiceModel.getPassword()))
                .setActive(true)
                .setRoles(Set.of(userRole));

        newUser = this.userRepository.save(newUser);

        login(newUser);
    }

    private void login(UserEntity userEntity) {
        this.currentUser.setUsername(userEntity.getUsername())
                .setFirstName(userEntity.getFirstName())
                .setLastName(userEntity.getLastName())
                .setLoggedIn(true);

        userEntity.getRoles().forEach(r -> this.currentUser.addRole(r.getRole()));
    }

    private void initializeRoles() {
        if (this.userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.USER);

            this.userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    private void initializeUsers() {
        if (this.userRepository.count() == 0) {
            UserRoleEntity adminRole = this.userRoleRepository.findByRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = this.userRoleRepository.findByRole(UserRoleEnum.USER);

            UserEntity admin = new UserEntity();
            admin.setUsername("admin")
                    .setPassword(passwordEncoder.encode("12345"))
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setActive(true);

            admin.setRoles(Set.of(adminRole, userRole));
            this.userRepository.save(admin);

            UserEntity ivan = new UserEntity();
            ivan.setUsername("ivan")
                    .setPassword(passwordEncoder.encode("12345"))
                    .setFirstName("Ivan")
                    .setLastName("Ivanov")
                    .setActive(true);

            ivan.setRoles(Set.of(userRole));
            this.userRepository.save(ivan);

        }
    }

}
