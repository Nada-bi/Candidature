package cni.gestion_formations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "formation Details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    private String title ;
    private String description;
    private Date deadline;


    public Object getName() {
        return null;
    }
}
