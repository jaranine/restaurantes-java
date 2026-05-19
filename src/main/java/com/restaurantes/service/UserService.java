package com.restaurantes.service;

import com.restaurantes.model.User;
import com.restaurantes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    // metodo para buscar el usuario en base de datos por username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        }  else {
            throw new UsernameNotFoundException("Usuario no encontrado con username: " + username);
        }

//        return userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Usuario: " + username + " no encontrado"));
    }
}
