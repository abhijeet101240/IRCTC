package IRCTC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class IrctcApplication {

	public static void main(String[] args) {
		SpringApplication.run(IrctcApplication.class, args);

		/*BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		String rawPass = "123";

		String encode = bCryptPasswordEncoder.encode(rawPass);

		System.out.println("|||||||||||||||||||||||||||||||||||||||||||| Encoded Password = " + encode );

*/
	}

}
