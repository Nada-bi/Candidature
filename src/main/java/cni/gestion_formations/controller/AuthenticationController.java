package cni.gestion_formations.controller;

import cni.gestion_formations.dto.RegisterRequest;
import cni.gestion_formations.dto.auth.AuthenticationRequest;
import cni.gestion_formations.dto.auth.AuthenticationResponse;
import cni.gestion_formations.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cni.gestion_formations.utils.Constants.AUTHENTICATION_ENDPOINT;

@RestController
@RequestMapping(AUTHENTICATION_ENDPOINT)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {
        return authenticationService.authenticate(request) ;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request)
    {
        return authenticationService.register(request) ;
    }
}
