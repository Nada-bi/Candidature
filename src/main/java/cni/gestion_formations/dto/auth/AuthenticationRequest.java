package cni.gestion_formations.dto.auth;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    private String email ;
    private String password ;



}
