package cni.gestion_formations.dto.auth;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationResponse
{
    private String accessToken ;
    private String role ;

    public AuthenticationResponse(String jwtToken) {

    }
}

