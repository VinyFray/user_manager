package br.com.zup.user_manager.services;

import br.com.zup.user_manager.controllers.dtos.UserLoginDTO;
import br.com.zup.user_manager.models.User;
import br.com.zup.user_manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
        String passwordEncoder = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoder);
        return userRepository.save(user);
    }

    public Map<String, String> login(UserLoginDTO userLoginDTO){
        Optional<User> userOptional = userRepository.findByUserName(userLoginDTO.getUserName());
        if(userOptional.isPresent()){
            User user = userOptional.get();

            if(bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())){
                return Map.of("mensagem:", "usuario logado");
            }

            return Map.of("mensagem:", "senha invalida");
        }

        return Map.of("mensagem:", "userName invalido");
    }
}
