package cni.gestion_formations.service.impl;

import cni.gestion_formations.config.JwtService;
import cni.gestion_formations.dto.RegisterRequest;
import cni.gestion_formations.dto.auth.AuthenticationRequest;
import cni.gestion_formations.dto.auth.AuthenticationResponse;
import cni.gestion_formations.entity.Formateur;
import cni.gestion_formations.repository.FormateurRepository;
import cni.gestion_formations.repository.UserRepository;
import cni.gestion_formations.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService ;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder ;
    private final FormateurRepository formateurRepository ;


    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail() ,
                        authenticationRequest.getPassword()
                )
        );

        if(authentication.isAuthenticated())
        {
            var user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();

            var token =  jwtService.generateToken(user);
            return ResponseEntity.ok( AuthenticationResponse.builder()
                    .role(user.getRole())
                    .accessToken(token)
                    .build()
            );
        }
        else {
            throw new BadCredentialsException("Invalid credentials  " + 400);
        }


    }

    @Override
    public ResponseEntity<AuthenticationResponse> register(RegisterRequest registerRequest) {
        Formateur formateur = RegisterRequest.toEntity(registerRequest);
        formateur.setPassword(passwordEncoder.encode(formateur.getPassword()));
        Formateur formateurSaved = formateurRepository.save(formateur);
        String token = jwtService.generateToken(formateur);
        return ResponseEntity.ok( AuthenticationResponse.builder()
                .role(formateurSaved.getRole())
                .accessToken(token)
                .build()
        );


    }


}

