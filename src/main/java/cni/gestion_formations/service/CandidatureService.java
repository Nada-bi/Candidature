package cni.gestion_formations.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface CandidatureService {

    ResponseEntity<Boolean> sendCandidature(Long formationId , MultipartFile cv , Authentication authentication  );
}
