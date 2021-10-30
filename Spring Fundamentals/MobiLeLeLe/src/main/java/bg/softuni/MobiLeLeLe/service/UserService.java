package bg.softuni.MobiLeLeLe.service;

import bg.softuni.MobiLeLeLe.model.service.UserRegistrationServiceModel;

public interface UserService {

    void initializeUsersAndRoles();

    void registerAndLoginUser(UserRegistrationServiceModel userRegisterServiceModel);

    boolean isUsernameFree(String username);
}
