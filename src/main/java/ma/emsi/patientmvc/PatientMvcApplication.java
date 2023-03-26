package ma.emsi.patientmvc;

import ma.emsi.patientmvc.entities.Patient;
import ma.emsi.patientmvc.repositories.PatientRepository;
import ma.emsi.patientmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(
                    new Patient(null,"imad",new Date(),false,122));
            patientRepository.save(
                    new Patient(null,"mohammed",new Date(),true,321));
            patientRepository.save(
                    new Patient(null,"yassine",new Date(),false,165));
            patientRepository.save(
                    new Patient(null,"ayman",new Date(),false,132));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };

    }
   // @Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("mohammed","1234","1234");
            securityService.saveNewUser("imad","1234","1234");
            securityService.saveNewUser("hassan","1234","1234");


            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoletoUser("mohammed","ADMIN");
            securityService.addRoletoUser("mohammed","USER");
            securityService.addRoletoUser("imad","USER");
            securityService.addRoletoUser("hassan","USER");






        };
    }

}
