package cni.gestion_formations.repository;

import cni.gestion_formations.dto.FormationDto;
import cni.gestion_formations.entity.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepository extends JpaRepository<Formation,Long> {


    static FormationDto save(FormationDto dto) {
        return dto;
    }
}
