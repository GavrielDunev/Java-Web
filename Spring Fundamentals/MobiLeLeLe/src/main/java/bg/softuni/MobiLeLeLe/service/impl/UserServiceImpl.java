package bg.softuni.MobiLeLeLe.service.impl;

import bg.softuni.MobiLeLeLe.model.entity.UserEntity;
import bg.softuni.MobiLeLeLe.model.entity.UserRoleEntity;
import bg.softuni.MobiLeLeLe.model.entity.enums.UserRoleEnum;
import bg.softuni.MobiLeLeLe.model.service.UserRegistrationServiceModel;
import bg.softuni.MobiLeLeLe.repository.UserRepository;
import bg.softuni.MobiLeLeLe.repository.UserRoleRepository;
import bg.softuni.MobiLeLeLe.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public boolean isUsernameFree(String username) {
        return this.userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
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

        this.userRepository.save(newUser);
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
