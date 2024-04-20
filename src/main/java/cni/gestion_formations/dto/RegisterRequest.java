package cni.gestion_formations.dto;

import cni.gestion_formations.entity.Formateur;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {


    private String username;
    private String password;
    private String email;
    private String firstname ;
    private String lastname ;
    private String telephone ;


    public static Formateur toEntity(RegisterRequest dto)
    {
        if(dto== null )
        {
            return null ;
        }

        Formateur formateur = new Formateur();
        formateur.setUsername(dto.getUsername());
        formateur.setEmail(dto.getEmail());
        formateur.setPassword(dto.getPassword());
        formateur.setRole("Formateur");
        formateur.setFirstname(dto.getFirstname());
        formateur.setLastname(dto.getLastname());
        formateur.setTelephone(dto.getTelephone());
        return formateur ;



    }
}
