package Myhealth.myhealth.services;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Medecin;
import Myhealth.myhealth.repository.MedecinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class MedecinServiceImplemetation implements MedecinService {


    private MedecinRepository medecinRepository;
    @Override
    public ReponseMessage creerMedecin(Medecin medecin) {
        if (medecinRepository.findByEmail(medecin.getEmail()) == null){
            medecinRepository.save(medecin);
            ReponseMessage message = new ReponseMessage("médecin ajouté avec succes", true);
            return  message;
        }else {
            ReponseMessage message = new ReponseMessage("Cet médecin existe déjà ", false);

            return message;
        }
    }


    @Override
    public ReponseMessage modifierMedecin(Medecin medecin) {
        if (medecinRepository.findByIdmedecin(medecin.getIdmedecin()) !=null) {
            return medecinRepository.findById(medecin.getIdmedecin())
                    .map(medecin1->{
                        medecin1.setNom(medecin.getNom());
                        medecin1.setPrenom(medecin.getPrenom());
                        medecin1.setAdresse(medecin.getAdresse());
                        medecin1.setVille(medecin.getVille());
                        medecin1.setPhoto(medecin.getPhoto());
                        medecinRepository.save(medecin1);
                        ReponseMessage message = new ReponseMessage("médecin modifié avec succes", true);
                        return  message;
                    }).orElseThrow(() -> new RuntimeException("Désole, médecin non trouvée"));
        }else {
            ReponseMessage message = new ReponseMessage("Désole, médecin non trouvée", false);

            return message;
        }
    }

    @Override
    public List<Medecin> afficherToutLesMedecin() {
        return medecinRepository.findAll();
    }

/*
    @Override
    public ReponseMessage SupprimerMedecin(Long idmedecin) {
        if (medecinRepository.findByIdmedecin(idmedecin) != null) {
            medecinRepository.deleteById(idmedecin);
            ReponseMessage message = new ReponseMessage("Médecin supprimée avec succes", true);
            return message;
        } else {
            ReponseMessage message = new ReponseMessage("Médecin non trouvée", false);
            return message;
        }
    }
*/

    @Override
    public List<Object> nombreMedecinparHopital(String nom) {
        return medecinRepository.nombreMedecinparHopital(nom);
    }

    @Override
    public List<Object> nombreMedecinHopital() {
        return medecinRepository.nombreMedecinHopital();
    }

    @Override
    public List<Object> NombreMedecinSpecialite() {
        return medecinRepository.NombreMedecinSpecialite();
    }

    @Override
    public List<Object> NombreMedecinParSpecialite(String specialite) {
        return medecinRepository.NombreMedecinParSpecialite(specialite);
    }

    @Override
    public int NombreMedecin() {
        return medecinRepository.NombreMedecin();
    }

    @Override
    public List<Object> HopitalListeMedecin() {
        return medecinRepository.HopitalListeMedecin();
    }

    @Override
    public ReponseMessage SupprimerMedecin(Long idmedecin) {
        final  Medecin medecin = null;
        if (medecinRepository.findByIdmedecin(idmedecin) != null) {
            medecin.setEtat(false);
            ReponseMessage message = new ReponseMessage("Médecin supprimée avec succes", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage("Médecin non trouvée", false);
            return message;
        }
    }

    public ReponseMessage activer(Long idmedecin) {
        final  Medecin medecin = null;
        if (medecinRepository.findByIdmedecin(idmedecin) != null) {
            medecin.setEtat(false);
            ReponseMessage message = new ReponseMessage("Médecin supprimée avec succes", true);
            return message;
        }
        else {
            ReponseMessage message = new ReponseMessage("Médecin non trouvée", false);
            return message;
        }
    }


    public void ajouterMedecin(Medecin medecin, MultipartFile diplomeFile) throws IOException {
        // Vérifier que le fichier est non vide
        if (!diplomeFile.isEmpty()) {
            // Lire le contenu du fichier dans un tableau de bytes
            byte[] diplomeContent = diplomeFile.getBytes();
            // Stocker le contenu du fichier dans le champ "diplome" de l'objet "Medecin"
            medecin.setDiplome(String.valueOf(diplomeContent));
        }
        // Enregistrer le médecin dans la base de données
        medecinRepository.save(medecin);
    }

/*public class MedecinService {

    // Inject the JavaMailSender instance
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MedecinRepository medecinRepository;

    public void addMedecin(Medecin medecin) {
        // Save the new medecin in the database
        Medecin newMedecin = medecinRepository.save(medecin);
        // Get the email address of the new medecin
        String email = newMedecin.getEmail();

        try {
            // Create a new MimeMessage instance
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            // Set the email subject
            helper.setSubject("Activation de votre compte");
            // Set the email recipient
            helper.setTo(email);
            // Set the email body
            String activationLink = "http://example.com/activate?id=" + newMedecin.getIdmedecin();
            String text = "Bonjour " + newMedecin.getPrenom() + ",\n\n" +
                    "Votre compte a été créé avec succès. Veuillez cliquer sur le lien ci-dessous pour activer votre compte : \n\n" +
                    activationLink + "\n\n" +
                    "Cordialement,\n" +
                    "L'équipe de notre site";
            helper.setText(text, true);
            // Send the email
            javaMailSender.send(message);

            // Update the medecin's state to "true"
            newMedecin.setEtat(true);
            medecinRepository.save(newMedecin);
        } catch (MessagingException e) {
            // Handle the exception
        }
    }
}
*/

    /*@Service
public class MedecinService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MedecinRepository medecinRepository;

    public void addMedecin(Medecin medecin) {
        Medecin newMedecin = medecinRepository.save(medecin);
        String email = newMedecin.getEmail();

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject("Activation de votre compte");
            helper.setTo(email);
            String activationLink = "http://example.com/activate?id=" + newMedecin.getIdmedecin();
            String text = "Bonjour " + newMedecin.getPrenom() + ",\n\n" +
                    "Votre compte a été créé avec succès. Veuillez cliquer sur le lien ci-dessous pour activer votre compte : \n\n" +
                    activationLink + "\n\n" +
                    "Cordialement,\n" +
                    "L'équipe de notre site";
            helper.setText(text, true);
            javaMailSender.send(message);

            newMedecin.setEtat(true);
            medecinRepository.save(newMedecin);
        } catch (MessagingException e) {
            // Handle the exception
        }
    }
}
*/



}


