package cni.gestion_formations.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void init();
    String store(MultipartFile file);
    Resource loadResource(String filename);

    ResponseEntity<Resource> downloadUserCv(String imageName, HttpServletRequest request);
}
