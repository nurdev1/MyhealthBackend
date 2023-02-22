package Myhealth.myhealth.mailNotification;

import Myhealth.myhealth.modeles.Patient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class EmailConstructor {

    private Environment env;


    private TemplateEngine templateEngine;

    public MimeMessagePreparator constructNewUserEmail(Patient patient, String codePatient) {
        Context context = new Context();
        context.setVariable("patient", patient);
        context.setVariable("codePatient", codePatient);
        String text = templateEngine.process("newUserEmailTemplate", context);
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(patient.getEmail());
                email.setSubject("Bienvenue sur Masante");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }

    public MimeMessagePreparator constructResetPasswordEmail(Patient patient, String motdepasse) {
        Context context = new Context();
        context.setVariable("patient", patient);
        context.setVariable("motdepasse", motdepasse);
        String text = templateEngine.process("resetPasswordEmailTemplate", context);
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(patient.getEmail());
                email.setSubject("New Motdepasse - Orchard");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }

    public MimeMessagePreparator constructUpdateUserProfileEmail(Patient patient) {
        Context context = new Context();
        context.setVariable("patient", patient);
        String text = templateEngine.process("updateUserProfileEmailTemplate", context);
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
                email.setPriority(1);
                email.setTo(patient.getEmail());
                email.setSubject("Profile Update - Orchard");
                email.setText(text, true);
                email.setFrom(new InternetAddress(env.getProperty("support.email")));
            }
        };
        return messagePreparator;
    }

}




