package portfolio.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import portfolio.backend.ExceptionHandler.exception.NotFoundException;
import portfolio.backend.ExceptionHandler.exception.UniqueEntityException;
import portfolio.backend.dto.request.UserRequestDTO;
import portfolio.backend.dto.response.UserResponseDTO;
import portfolio.backend.model.User;
import portfolio.backend.repository.IUserRepository;
import portfolio.backend.service.IUserService;

import java.util.*;
import java.util.logging.Logger;

@Service
public class UserService implements IUserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserResponseDTO getUserById(int id) {
        logger.info("getUserById with userid: " + id);
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            String message = "User not found with userid: " + id;
            throw new NotFoundException(logger, message);
        }
        return new UserResponseDTO(user.get());
    }


    @Override
    public List<UserResponseDTO> getAllUser() {
        logger.info("getAllUser");
        List<User> userList = userRepository.findAll();
        if (userList == null) {
            return Collections.emptyList();
        }
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        for (User user : userList) {
            userResponseDTOList.add(new UserResponseDTO(user));
        }
        return userResponseDTOList;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO user) {
        logger.info("createUser" + user.getUsername());
        checkExistingUserByUsername(user.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        User newUser = new User()
                .setUsername(user.getUsername())
                .setPassword(hashedPassword)
                .setEmail(user.getEmail())
                .setRole(user.getRole());
        User createdUser = userRepository.save(newUser);
        return new UserResponseDTO(createdUser);
    }

    @Override
    public UserResponseDTO updateUser(int userId, UserRequestDTO user) {
        logger.info("updateUser for userId: " + userId);
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isEmpty()) {
            throw new NotFoundException("User not found with id: " + userId);
        }
        if (!existingUser.get().getUsername().equals(user.getUsername())){
            checkExistingUserByUsername(user.getUsername());
        }
        User updatedUser = existingUser.get()
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setRole(user.getRole())
                .setUpdatedAt(new Date());

        User savedUser = userRepository.save(updatedUser);
        return new UserResponseDTO(savedUser);
    }

    @Override
    public void deleteUser(int userId) {
        logger.info("deleteUser for userId: " + userId);
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isEmpty()) {
            throw new NotFoundException("User not found with id: " + userId);
        }
        userRepository.delete(existingUser.get());
    }

    private void checkExistingUserByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isEmpty()){
            throw new UniqueEntityException("Username already exist !!!");
        }
    }

}
