package com.example.footcare.config.auth;

import com.example.footcare.model.Users;
import com.example.footcare.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> findUser = authRepository.findByUsername(username);
        if (findUser.isEmpty()) {
            throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
        }
        return new UserDetailsImpl(findUser.get());
    }
}
