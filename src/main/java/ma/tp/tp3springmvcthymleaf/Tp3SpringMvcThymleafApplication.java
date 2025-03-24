package ma.tp.tp3springmvcthymleaf;

import ma.tp.tp3springmvcthymleaf.entities.Patient;
import ma.tp.tp3springmvcthymleaf.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class Tp3SpringMvcThymleafApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(Tp3SpringMvcThymleafApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Patient patient = new Patient().builder().nom("yasine").dateNaissance(new Date()).malade(true).score(110).build();
        Patient patient1 = new Patient().builder().nom("ahmed").dateNaissance(new Date()).malade(false).score(120).build();
        Patient patient2 = new Patient().builder().nom("hanane").dateNaissance(new Date()).malade(true).score(130).build();
        patientRepository.save(patient);
        patientRepository.save(patient1);
        patientRepository.save(patient2);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
