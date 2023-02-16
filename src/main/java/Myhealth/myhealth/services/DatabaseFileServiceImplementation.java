package Myhealth.myhealth.services;


import Myhealth.myhealth.exception.FileNotFoundException;
import Myhealth.myhealth.exception.FileStorageException;
import Myhealth.myhealth.modeles.DatabaseFile;
import Myhealth.myhealth.repository.DatabaseFileRepository;
import Myhealth.myhealth.repository.DossierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DatabaseFileServiceImplementation implements DatabaseFileService {

    @Autowired
    private DatabaseFileRepository dbFileRepository;

    @Autowired
    private DossierRepository dossierRepository;
    @Override
    public DatabaseFile storeFile(MultipartFile file,Long idUser,Long idDossier) {
        // Normaliser le nom du fichier
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Vérifiez si le nom du fichier contient des caractères invalides
            if(fileName.contains("..")) {
                throw new FileStorageException("Pardon! Le nom de fichier contient une séquence de chemin non valide " + fileName);
            }

            DatabaseFile dbFile = new DatabaseFile(fileName, file.getContentType(), file.getBytes());
           // dbFile.setUtilisateus(utilisateusRepository.findById(idDossier).get());
            dbFile.setDossier(dossierRepository.findByIddossier(idDossier));
            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Impossible de stocker le fichier" + fileName + ". Veuillez réessayer!", ex);
        }
    }
    @Override
    public DatabaseFile saveFile(MultipartFile file,Long idUser) {
        // Normaliser le nom du fichier
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Vérifiez si le nom du fichier contient des caractères invalides
            if(fileName.contains("..")) {
                throw new FileStorageException("Pardon! Le nom de fichier contient une séquence de chemin non valide " + fileName);
            }

            DatabaseFile dbFile = new DatabaseFile(fileName, file.getContentType(), file.getBytes());
           // dbFile.setUtilisateus(utilisateusRepository.findById(idDossier).get());
            dbFile.setDossier(dossierRepository.findByIddossier(idUser));
            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Impossible de stocker le fichier" + fileName + ". Veuillez réessayer!", ex);
        }
    }

    @Override
    public DatabaseFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("Fichier introuvable avec l'identifiant" + fileId));
    }
}
