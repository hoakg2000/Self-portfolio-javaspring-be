package portfolio.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.backend.dto.request.LoginRequestDTO;
import portfolio.backend.dto.response.UserResponseDTO;
import portfolio.backend.service.IAuthService;
import portfolio.backend.service.IUserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService iAuthService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO loginDto){
        return new ResponseEntity<>(iAuthService.login(loginDto), HttpStatus.OK);
    }
    @GetMapping("/test")
    public String test() {
        return "oke";
    }

}
