package Myhealth.myhealth.services.implementation;

import Myhealth.myhealth.Message.ReponseMessage;
import Myhealth.myhealth.modeles.Medecin;
import Myhealth.myhealth.repository.MedecinRepository;
import Myhealth.myhealth.services.MedecinService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@AllArgsConstructor
public class MedecinServiceImplemetation implements MedecinService {


    private MedecinRepository medecinRepository;
    @Autowired
    private JavaMailSender javaMailSender;
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
        if (medecinRepository.findById(medecin.getId()) !=null) {
            return medecinRepository.findById(medecin.getId())
                    .map(medecin1->{
                        medecin1.setNom(medecin.getNom());
                        medecin1.setPrenom(medecin.getPrenom());
                        medecin1.setAdresse(medecin.getAdresse());
                        medecin1.setVille(medecin.getVille());
                        medecin1.setPhoto(medecin.getPhoto());
                        medecin1.setTelephone(medecin.getTelephone());
                        medecin1.setSpecialite(medecin.getSpecialite());
                      //  medecin1.setHopital(medecin.getHopital());
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
        Medecin medecin;


        return medecinRepository.findAll();
    }


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
        if (medecinRepository.findById(idmedecin) != null) {
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
        if (medecinRepository.findById(idmedecin) != null) {
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


    @Override
    public void acivateEmailMedecin() {
        //Medecin newMedecin = medecinRepository.save(medecin);
        Medecin newMedecin = null;
        String email = newMedecin.getEmail();

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject("Activation de votre compte");
            helper.setTo(email);
            String activationLink = "http://example.com/activate?id=" + newMedecin.getId();
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
    @Override
    public void activerMedecin(Long id) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Médecin introuvable"));
        medecin.setEtat(true);
        medecinRepository.save(medecin);
    }

    @Override
    public List<Medecin> dernier() {
        return medecinRepository.dernier();
    }


      /*  public void registerPatient(SignupPatientRequest signupPatientRequest) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
            if (patientRepository.existsByUsername(signupPatientRequest.getUsername())) {
                throw new UsernameAlreadyExistsException();
            }

            if (patientRepository.existsByEmail(signupPatientRequest.getEmail())) {
                throw new EmailAlreadyExistsException();
            }

            Patient patient = new Patient();
            patient.setUsername(signupPatientRequest.getUsername());
            patient.setEmail(signupPatientRequest.getEmail());
            patient.setPassword(passwordEncoder.encode(signupPatientRequest.getPassword()));
            patient.setNom(signupPatientRequest.getNom());
            patient.setPrenom(signupPatientRequest.getPrenom());
            patient.setPhoto(signupPatientRequest.getPhoto());
            patient.setTelephone(signupPatientRequest.getTelephone());
            patient.setAdresse(signupPatientRequest.getAdresse());
            patient.setRole(Role.PATIENT);
            patientRepository.save(patient);
        }
    */


}


