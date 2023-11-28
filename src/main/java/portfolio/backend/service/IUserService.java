package portfolio.backend.service;

import portfolio.backend.dto.request.UserRequestDTO;
import portfolio.backend.dto.response.UserResponseDTO;

import java.util.List;

public interface IUserService {
    public UserResponseDTO getUserById(int id) throws Exception;
    public List<UserResponseDTO> getAllUser();
    public UserResponseDTO createUser(UserRequestDTO user);
    public UserResponseDTO updateUser(int id, UserRequestDTO user);
    public void deleteUser(int id);
}
