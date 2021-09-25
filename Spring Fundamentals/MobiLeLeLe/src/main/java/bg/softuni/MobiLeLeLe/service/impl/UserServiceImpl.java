package bg.softuni.MobiLeLeLe.service.impl;

import bg.softuni.MobiLeLeLe.model.entity.UserEntity;
import bg.softuni.MobiLeLeLe.model.service.UserLoginServiceModel;
import bg.softuni.MobiLeLeLe.repository.UserRepository;
import bg.softuni.MobiLeLeLe.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(UserLoginServiceModel loginServiceModel) {
        Optional<UserEntity> user = this.userRepository.findByUsername(loginServiceModel.getUsername());

        if (user.isEmpty()) {
            return false;
        } else {
            return passwordEncoder.matches(loginServiceModel.getRawPassword(), user.get().getPassword());
        }
    }
}
