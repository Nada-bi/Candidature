package cni.gestion_formations.controller;

import cni.gestion_formations.service.CandidatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static cni.gestion_formations.utils.Constants.CANDIDATURE_ENDPOINT;

@RestController
@RequestMapping(CANDIDATURE_ENDPOINT)
@RequiredArgsConstructor
public class CandidatureController {

    private final CandidatureService candidatureService ;

    @PostMapping("/sendCandidature")
    public ResponseEntity<Boolean> sendCandidature( @RequestParam(name = "file") MultipartFile file ,Authentication authentication)
    {
        return candidatureService.sendCandidature( file ,authentication  ) ;
    }

}


