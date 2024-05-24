package itbrains.az.blogpage2.services.impl;

import itbrains.az.blogpage2.dtos.authdtos.RegisterDto;
import itbrains.az.blogpage2.models.User;
import itbrains.az.blogpage2.repositories.UserRepository;
import itbrains.az.blogpage2.services.EmailService;
import itbrains.az.blogpage2.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public boolean register(RegisterDto register) {

        User user = userRepository.findByEmail(register.getEmail());

        if(user != null) {
            return false;
        }

        String hashPassword = bCryptPasswordEncoder.encode(register.getPassword());
        String token = bCryptPasswordEncoder.encode(register.getEmail());
        User newUser = modelMapper.map(register,User.class);
        newUser.setConfirmationToken(token);
        newUser.setEmailConfirmed(false);
        newUser.setPassword(hashPassword);
        userRepository.save(newUser);
        emailService.sendConfirmationEmail(register.getEmail(),token);
        return true;
    }

    @Override
    public boolean confirmEmail(String email, String token) {
        User findUser = userRepository.findByEmail(email);
        if (findUser.getConfirmationToken().equals(token) && findUser != null) {
            findUser.setEmailConfirmed(true);
            userRepository.saveAndFlush(findUser);
            return true;
        }
        return false;
    }
}
