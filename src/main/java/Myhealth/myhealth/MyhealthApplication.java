package Myhealth.myhealth;

import Myhealth.myhealth.repository.RoleRepository;
import Myhealth.myhealth.serviceMail.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication

public class MyhealthApplication {

	//private final RoleRepository roleRepository;

	@Autowired
	private EmailSenderService emailSenderService;


	public MyhealthApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(MyhealthApplication.class, args);
	}
//	@EventListener(ApplicationReadyEvent.class)
//	public void sendMail(){
//		emailSenderService.SendEmail("fatoudevweb1@gmail.com",
//				"tester",
//				"Salut Fatoumata par myhealth");
//	}

/*	public Masante( RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		if (utilisateurRepository.findAll().size()==0){
			roleRepository.createAdmin();
		}
	}*/

}
