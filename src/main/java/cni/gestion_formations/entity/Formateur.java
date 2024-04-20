package cni.gestion_formations.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Formateur")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Formateur extends  User{
    private String firstname ;
    private String lastname ;
    private String telephone ;


}
