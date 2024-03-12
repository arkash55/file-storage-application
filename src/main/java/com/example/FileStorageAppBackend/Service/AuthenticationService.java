package com.example.FileStorageAppBackend.Service;


import Responses.AuthenticationResponse;
import com.example.FileStorageAppBackend.Entity.Role;
import com.example.FileStorageAppBackend.Repository.UserRepository;
import com.example.FileStorageAppBackend.Requests.AuthenticationRequest;
import com.example.FileStorageAppBackend.Requests.RegisterRequest;
import com.example.FileStorageAppBackend.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        //check if user exists, if exists login
        boolean userExists = userRepository.findByEmail(request.getEmail()).isPresent();
//        System.out.println(userExists);
        if (userExists) return null;
        System.out.println("here");

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        }
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> {
            return new UsernameNotFoundException("Username not found");
        });

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
