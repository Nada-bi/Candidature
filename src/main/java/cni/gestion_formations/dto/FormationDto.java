package cni.gestion_formations.dto;

import cni.gestion_formations.entity.Formation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FormationDto {
    private Long id;
    private String title ;
    private String description;
    private Date deadline;

    public static Formation toEntity(FormationDto dto)
    {
        if(dto== null)
        {
            return null ;
        }

        Formation formation = new Formation();
        formation.setId(dto.getId() != null ? dto.getId() : null);
        formation.setTitle(dto.getTitle());
        formation.setDescription(dto.getDescription());
        formation.setDeadline(dto.getDeadline());

        return new Formation();


    }

    public void setName(Object name) {
    }
}
