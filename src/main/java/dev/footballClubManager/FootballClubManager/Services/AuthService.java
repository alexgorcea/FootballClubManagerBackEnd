package dev.footballClubManager.FootballClubManager.Services;

import dev.footballClubManager.FootballClubManager.Models.Role;
import dev.footballClubManager.FootballClubManager.Models.User;
import dev.footballClubManager.FootballClubManager.Models.Ticket;
import dev.footballClubManager.FootballClubManager.Repositories.TicketRepository;
import dev.footballClubManager.FootballClubManager.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Map<String,Object> findCurrentUser(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Ticket> tickets = ticketRepository.findByUserId(user.getId());

        return Map.of(
                "username", user.getUsername(),
                "roles", user.getRoles().stream().map(Role::name).toList(),
                "tickets", tickets
        );
    }

    public Map<String,String> doRegister(Map<String, Object> payload){
        String username = (String) payload.get("username");
        String email = (String) payload.get("email");
        String password = (String) payload.get("password");
        boolean isAdmin = payload.get("isAdmin") != null && (Boolean) payload.get("isAdmin");

        if (userRepository.existsByUsername(username) || userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Username or email already in use");
        }

        Role role = isAdmin ? Role.ROLE_ADMIN : Role.ROLE_CLIENT;
        User user = new User(null, username, email, passwordEncoder.encode(password), Collections.singleton(role));
        userRepository.save(user);

        return Map.of("message", "User registered successfully");
    }
}
