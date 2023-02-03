package Myhealth.myhealth.controller;

import Myhealth.myhealth.modeles.DatabaseFile;
import Myhealth.myhealth.payloadFile.Response;
import Myhealth.myhealth.services.DatabaseFileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/import")
@CrossOrigin(origins = "http://localhost:8100")
public class FileUploadController {

    @Autowired
    private DatabaseFileService fileStorageService;

    @PostMapping("/uploadFile/{idDossier}/{idUser}")
    public Response uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long idUser, @PathVariable Long idDossier) {
    	DatabaseFile fileName = fileStorageService.storeFile(file,idDossier,idUser);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getFileName())
                .toUriString();

        return new Response(fileName.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @PathVariable Long idUser,@PathVariable Long idDossier) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file,idUser,idDossier))
                .collect(Collectors.toList());
    }
}
