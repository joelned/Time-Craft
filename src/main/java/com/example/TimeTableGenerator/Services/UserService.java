package com.example.TimeTableGenerator.Services;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import com.example.TimeTableGenerator.Entities.User;
import com.example.TimeTableGenerator.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        user.setEmail(user.getEmail());
        user.setEmail(user.getEmail());
        user.setPassword(hashPassword(user.getPassword()));
        userRepository.save(user);
    }

    public static String hashPassword(String password) {
        try {
            // Generate a random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // Combine password and salt, then hash using SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());

            // Encode the salt and hashed password in Base64
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            String encodedHashedPassword = Base64.getEncoder().encodeToString(hashedPassword);

            // Concatenate salt and hashed password and return
            return encodedSalt + "$" + encodedHashedPassword;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean verifyPassword(String inputPassword, String storedPassword) {
        try {
            // Split the stored password into salt and hashed password
            String[] parts = storedPassword.split("\\$");
            String encodedSalt = parts[0];
            String encodedHashedPassword = parts[1];

            // Decode the salt and hashed password from Base64
            byte[] salt = Base64.getDecoder().decode(encodedSalt);
            byte[] storedHashedPassword = Base64.getDecoder().decode(encodedHashedPassword);

            // Combine input password and salt, then hash using SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] inputHashedPassword = md.digest(inputPassword.getBytes());

            // Compare the calculated hash with the stored hash
            return MessageDigest.isEqual(storedHashedPassword, inputHashedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }
    public String getHashedPasswordByEmail(String email){
        User user= userRepository.findUserByEmail(email);
        return user!=null ? user.getPassword(): "Error";
    }

}
