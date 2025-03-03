package com.github.payment_manager.service;

import com.github.payment_manager.domain.User;
import com.github.payment_manager.dto.authentication.AuthenticationDTO;
import com.github.payment_manager.dto.user.CreateUserDTO;
import com.github.payment_manager.dto.user.GetUserResponseDTO;
import com.github.payment_manager.repository.UserRepository;
import com.github.payment_manager.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    public GetUserResponseDTO save(CreateUserDTO dto) {

        User user = new User(dto);

        return new GetUserResponseDTO(this.repository.save(user));
    }

    public String login(AuthenticationDTO dto) {

        User user = new User(repository.findByLoginAndPassword(dto.login(),
                new BCryptPasswordEncoder().encode(dto.password())));

        return tokenService.generateToken(user);
    }

    public GetUserResponseDTO findUserByLogin(String login) {
        try {
            return new GetUserResponseDTO(repository.getByLogin(login));
        } catch (Exception e) {
            return null;
        }
    }
}
