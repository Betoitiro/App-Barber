package App_Barber.com.br.AppBarber.v1.user.Service;

import App_Barber.com.br.AppBarber.v1.user.domain.User;
import App_Barber.com.br.AppBarber.v1.user.domain.UserRole;
import App_Barber.com.br.AppBarber.v1.user.dto.RegisterDTO;
import App_Barber.com.br.AppBarber.v1.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthClientServiceImpl implements AuthClientService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void authClient(RegisterDTO registerDTO) {
        try {
            var authClient = new User(
                    registerDTO.getLogin(),
                    passwordEncoder.encode(registerDTO.getPassword()),
                    UserRole.USER);
            userRepository.save(authClient);
        }catch (DataIntegrityViolationException e ){
            throw new IllegalArgumentException("Login ja esta em uso");
        }
    }
}
