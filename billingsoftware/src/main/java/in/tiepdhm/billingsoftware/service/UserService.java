package in.tiepdhm.billingsoftware.service;

import in.tiepdhm.billingsoftware.io.UserRequest;
import in.tiepdhm.billingsoftware.io.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    String getUserRole(String email);

    List<UserResponse> readUsers();

    void deleteUser(String id);
}
