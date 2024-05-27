package itbrains.az.blogpage2.config.auth;

import groovy.transform.AutoFinal;
import itbrains.az.blogpage2.models.UserEntity;
import itbrains.az.blogpage2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(username);
        if (user != null) {
            User loginUser = new User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getEmailConfirmed(),
                    true,
                    true,
                    true,
                    user.getRoles().stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
            );
            return loginUser;
        }
        else
        {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
