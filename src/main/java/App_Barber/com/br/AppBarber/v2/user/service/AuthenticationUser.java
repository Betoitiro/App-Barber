package App_Barber.com.br.AppBarber.v2.user.service;

import App_Barber.com.br.AppBarber.v2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationUser {

    private final UserRepository userRepository;

    public UserDetails getAuthenticatedUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
    public UserDetails getUserData() {
        var authenticatedUser = getAuthenticatedUser();
        if (authenticatedUser != null) {
            return userRepository.findByLogin(authenticatedUser.getUsername());
        }
        return null;
    }
}
