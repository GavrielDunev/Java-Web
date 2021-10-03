package bg.softuni.MobiLeLeLe.service;

import bg.softuni.MobiLeLeLe.model.service.UserLoginServiceModel;

public interface UserService {

    boolean login(UserLoginServiceModel loginServiceModel);

    void initializeUsersAndRoles();

    void logout();
}
