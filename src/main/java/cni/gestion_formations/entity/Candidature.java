package cni.gestion_formations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "candidature")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidature {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long Id;

    @ManyToOne
    private Formation formation ;

    @OneToOne
    private Formateur formateur ;

    private String cvUrl;


    private String status ;

    private LocalDateTime date ;
}
