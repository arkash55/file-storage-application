package com.example.FileStorageAppBackend.Controller;

import Responses.AuthenticationResponse;
import com.example.FileStorageAppBackend.Requests.AuthenticationRequest;
import com.example.FileStorageAppBackend.Requests.RegisterRequest;
import com.example.FileStorageAppBackend.Service.AuthenticationService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest request) {
        //check if exists here

        AuthenticationResponse res = authenticationService.register(request);

        return res == null ?  new ResponseEntity<>(null, HttpStatusCode.valueOf(409)) : ResponseEntity.ok(res);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }



}
