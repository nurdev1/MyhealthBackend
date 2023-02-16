package Myhealth.myhealth.services;

import Myhealth.myhealth.modeles.DatabaseFile;
import org.springframework.web.multipart.MultipartFile;

public interface DatabaseFileService {

    public DatabaseFile storeFile(MultipartFile file,Long idUser,Long idDossier);
    public DatabaseFile saveFile(MultipartFile file,Long idUser);
    public DatabaseFile getFile(String fileId);
}
