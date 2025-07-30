package App_Barber.com.br.AppBarber.v2.user.service;

import App_Barber.com.br.AppBarber.v2.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticatedUserService.class);

    private final UserRepository userRepository;

    public AuthenticatedUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long getAuthenticatedUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();

            var user = userRepository.findUserByLogin(username)
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

            Long userId = user.getId();
            logger.info("Usuário autenticado com ID: {}", userId); // Adiciona o log aqui

            return userId;
        }

        throw new IllegalArgumentException("Usuário não autenticado ou não encontrado.");
    }
}
