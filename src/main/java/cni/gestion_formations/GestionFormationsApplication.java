package cni.gestion_formations;

import cni.gestion_formations.config.FileStorageProperties;
import cni.gestion_formations.entity.User;
import cni.gestion_formations.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
@RequiredArgsConstructor
public class GestionFormationsApplication implements CommandLineRunner {
	private final UserRepository userRepository ;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(GestionFormationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if(Boolean.FALSE.equals(userRepository.existsByRole("Admin")))
		{
			User admin = new User();
			admin.setEmail("admin@elearningpfe.com");
			admin.setPassword(passwordEncoder.encode("admin@E+2024"));
			admin.setUsername("admin");
			admin.setRole("Admin");
			userRepository.save(admin);

		}

	}
}
