package cni.gestion_formations.service.impl;

import cni.gestion_formations.config.FileStorageProperties;
import cni.gestion_formations.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {

    private final Path fileLocation;

    @Autowired
    public FileServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileLocation= Paths.get(fileStorageProperties.getUploadCvUsersDir()).toAbsolutePath().normalize();

    }
    @Override
    public void init() {
        try {
            Files.createDirectories(fileLocation);
        }catch (IOException e){
            throw new RuntimeException("Could not initialize storage");
        }
    }

    @Override
    public String store(MultipartFile file) {
        String fileName= StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if (fileName.contains("..")){
               // throw new FileStorageException("File name contains invalid path sequence "+fileName);
            }
            Files.copy(file.getInputStream(),this.fileLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            throw new RuntimeException("Fail",e);
        }
        return file.getOriginalFilename();
    }

    @Override
    public Resource loadResource(String filename) {
        try {
            Path path = fileLocation.resolve(filename);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            }else {
                throw new RuntimeException("Fail");
            }
        }catch (MalformedURLException e){
            throw new RuntimeException("Fail");
        }
    }

    @Override
    public ResponseEntity<Resource> downloadUserCv(String imageName, HttpServletRequest request) {
        Resource resource = this.loadResource(imageName);
        String contentType = null;
        try {
            if (resource!=null){
                contentType=request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        assert contentType != null;
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"")
                .body(resource);
    }
}
