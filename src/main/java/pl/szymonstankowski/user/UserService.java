package pl.szymonstankowski.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

    public User getUserByLogin(String login){
        Optional<User> userByName = userRepository.findUserByName(login);
        return userByName.orElseThrow(()-> new UsernameNotFoundException("User not found "+ login));
    }

    @Transactional
    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public User getUserByName(String username){
        return userRepository.findUserByName(username).orElse(null);
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByName(email).orElse(null);
    }

    public User getUserById(Long id){
        return userRepository.findUserById(id);
    }

}
