package IRCTC.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserServiceUtil {
    /*public class PasswordUtil {
        public static void main(String[] args) {
            String rawPassword = "secret123";
            String hashed = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

            System.out.println("Hashed: " + hashed);
            System.out.println("Matches: " + BCrypt.checkpw("secret123", hashed));

        }*/

    /* To use BCrypt for password hashing in a Java project, you can add
      the Spring Security Crypto dependency (commonly used in Spring Boot), or use the standalone
      jBCrypt library.*/

    public static String generateHashPassword(String rawPassword){

        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String rawPassword, String hashPassword){

        return BCrypt.checkpw(rawPassword,hashPassword);
    }
}
