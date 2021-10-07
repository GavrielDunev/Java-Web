package bg.softuni.MobiLeLeLe.service;

import bg.softuni.MobiLeLeLe.model.service.UserLoginServiceModel;
import bg.softuni.MobiLeLeLe.model.service.UserRegistrationServiceModel;

public interface UserService {

    boolean login(UserLoginServiceModel loginServiceModel);

    void initializeUsersAndRoles();

    void logout();

    void registerAndLoginUser(UserRegistrationServiceModel userRegisterServiceModel);

    boolean isUsernameFree(String username);
}
