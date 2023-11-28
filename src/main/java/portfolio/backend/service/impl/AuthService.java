package portfolio.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import portfolio.backend.ExceptionHandler.exception.NotFoundException;
import portfolio.backend.config.jwt.JwtUtilities;
import portfolio.backend.dto.request.LoginRequestDTO;
import portfolio.backend.dto.response.UserResponseDTO;
import portfolio.backend.model.User;
import portfolio.backend.repository.IUserRepository;
import portfolio.backend.service.IAuthService;

import java.util.stream.Collectors;

@Service
public class AuthService implements IAuthService {

    @Autowired
    AuthenticationManager authenticationManager ;
    @Autowired
    JwtUtilities jwtUtilities ;
    @Autowired
    IUserRepository iUserRepository;

    @Override
    public UserResponseDTO login(LoginRequestDTO userRequestDTO) {
        try{
            Authentication authentication= authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequestDTO.getUsername(),
                            userRequestDTO.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String role = authentication.getAuthorities().stream().map(auth -> auth.getAuthority()).collect(Collectors.joining(","));
            String token = jwtUtilities.generateToken(userRequestDTO.getUsername(),role);

            User user = new User()
                    .setUsername(authentication.getName())
                    .setAccessToken(token);

            return new UserResponseDTO(user);
        } catch (AuthenticationException authenticationException){
            throw new NotFoundException("Incorrect username or password");
        }


    }
}
