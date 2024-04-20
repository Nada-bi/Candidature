package cni.gestion_formations.service.impl;

import cni.gestion_formations.entity.Formation;
import cni.gestion_formations.repository.FormationRepository;
import cni.gestion_formations.service.CandidatureService;
import cni.gestion_formations.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CandidatureServiceImpl implements CandidatureService {
    private final FileService fileService ;
    private final FormationRepository formationRepository ;
    @Override
    public ResponseEntity<Boolean> sendCandidature(Long formationId , MultipartFile cv , Authentication authentication) {
        System.out.println("authenticated user " + authentication.getName());

        Formation formation = formationRepository.findById(formationId).get();


        fileService.store(cv);
        //System.out.println("file  "  +  cv.getSize());
        return ResponseEntity.ok(true);
    }
}
