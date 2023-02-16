package Myhealth.myhealth.mailNotification;

import Myhealth.myhealth.modeles.Medecin;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
@AllArgsConstructor
public class EmailMedecinConstructor {

    private Environment env;


    private TemplateEngine templateEngine;

    public MimeMessagePreparator constructNewMedecinEmail(Medecin medecin, String motdepasse) {
        Context context = new Context();
        context.setVariable("medecin", medecin);
        context.setVariable("motdepasse", motdepasse);
        String text = templateEngine.process("newMedecinEmailTemplate", context);
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(medecin.getEmail());
                email.setSubject("Bienvenue sur Masante");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }

    public MimeMessagePreparator constructResetPasswordEmailMedecin(Medecin medecin, String motdepasse) {
        Context context = new Context();
        context.setVariable("patient", medecin);
        context.setVariable("motdepasse", motdepasse);
        String text = templateEngine.process("resetPasswordEmailTemplate", context);
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(medecin.getEmail());
                email.setSubject("New Motdepasse - Orchard");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }

    public MimeMessagePreparator constructUpdateMedecinProfileEmail(Medecin medecin) {
        Context context = new Context();
        context.setVariable("patient", medecin);
        String text = templateEngine.process("updateUserProfileEmailTemplate", context);
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(medecin.getEmail());
                email.setSubject("Profile Update - Orchard");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }

}




