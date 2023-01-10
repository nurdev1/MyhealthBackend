package Myhealth.myhealth.services;

import Myhealth.myhealth.modelFile.DatabaseFile;
import org.springframework.web.multipart.MultipartFile;

public interface DatabaseFileService {

    public DatabaseFile storeFile(MultipartFile file);
    public DatabaseFile getFile(String fileId);
}
