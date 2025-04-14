package ma.tp.tp3springmvcthymleaf;

import ma.tp.tp3springmvcthymleaf.entities.Patient;
import ma.tp.tp3springmvcthymleaf.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class Tp3SpringMvcThymleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tp3SpringMvcThymleafApplication.class, args);
    }

    @Bean
    CommandLineRunner usersInitializer(JdbcUserDetailsManager jdbcUserDetailsManager, PasswordEncoder encoder) {
        return args -> {
            if (!jdbcUserDetailsManager.userExists("user11")) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user11")
                                .password(encoder.encode("1234"))
                                .roles("USER")
                                .build()
                );
            }

            if (!jdbcUserDetailsManager.userExists("user21")) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user21")
                                .password(encoder.encode("1234"))
                                .roles("USER")
                                .build()
                );
            }

            if (!jdbcUserDetailsManager.userExists("admin2")) {
                jdbcUserDetailsManager.createUser(
                        User.withUsername("admin2")
                                .password(encoder.encode("1234"))
                                .roles("USER", "ADMIN")
                                .build()
                );
            }
        };
    }

    @Bean
    CommandLineRunner patientInitializer(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(new Patient(null, "Yasine", new Date(), true, 110));
            patientRepository.save(new Patient(null, "Ahmed", new Date(), false, 120));
            patientRepository.save(new Patient(null, "Hanane", new Date(), true, 130));
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
