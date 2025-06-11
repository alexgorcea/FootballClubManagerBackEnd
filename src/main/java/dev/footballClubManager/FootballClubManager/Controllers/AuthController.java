package dev.footballClubManager.FootballClubManager.Controllers;

import dev.footballClubManager.FootballClubManager.Models.Role;
import dev.footballClubManager.FootballClubManager.Models.User;
import dev.footballClubManager.FootballClubManager.Models.Ticket;
import dev.footballClubManager.FootballClubManager.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        return ResponseEntity.ok(authService.findCurrentUser(principal));
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> payload) {
        try {
            Map<String, String> result = authService.doRegister(payload);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
