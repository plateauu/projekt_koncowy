package pl.szymonstankowski.floraManager.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final  UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    };

    public void saveUser(User user){
        userRepository.save(user);
    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    public User editUser(Long id){
       return userRepository.findById(id).orElse(null);
    }
    public List<User> showUsers(){
        return userRepository.findAll();
    }
    public User showUser(Long id){
        return userRepository.findById(id).orElse(null);
    }
}
