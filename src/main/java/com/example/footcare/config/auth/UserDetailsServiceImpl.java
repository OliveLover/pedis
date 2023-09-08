package com.example.footcare.config.auth;

import com.example.footcare.model.Users;
import com.example.footcare.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername 진입!");
        Users findUser = authRepository.findByUsername(username).orElse(null);

        if (findUser == null) {
            return null;
        }

        System.out.println(findUser.getUsername());
        return new UserDetailsImpl(findUser);
    }
}
