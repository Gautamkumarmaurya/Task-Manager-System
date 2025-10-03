package Task.Manger.com.example.Service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public UserDetails loadUserByUsername(String username);

    public User save(User user);

    public User findByUsername(String username);
}
