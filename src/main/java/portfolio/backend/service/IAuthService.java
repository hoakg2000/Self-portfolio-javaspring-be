package portfolio.backend.service;

import portfolio.backend.dto.request.LoginRequestDTO;
import portfolio.backend.dto.response.UserResponseDTO;

public interface IAuthService {
    UserResponseDTO login(LoginRequestDTO userRequestDTO);
}
