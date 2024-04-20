package cni.gestion_formations.repository;

import cni.gestion_formations.entity.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormateurRepository extends JpaRepository<Formateur, Long> {
}
