package cni.gestion_formations.service;

import cni.gestion_formations.dto.RegisterRequest;
import cni.gestion_formations.dto.auth.AuthenticationRequest;
import cni.gestion_formations.dto.auth.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) ;
    ResponseEntity<AuthenticationResponse> register(RegisterRequest registerRequest) ;

}
