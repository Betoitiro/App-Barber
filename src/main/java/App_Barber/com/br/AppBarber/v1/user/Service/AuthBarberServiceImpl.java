package App_Barber.com.br.AppBarber.v1.user.Service;

import App_Barber.com.br.AppBarber.v1.user.domain.User;
import App_Barber.com.br.AppBarber.v1.user.domain.UserRole;
import App_Barber.com.br.AppBarber.v1.user.dto.RegisterDTO;
import App_Barber.com.br.AppBarber.v1.user.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthBarberServiceImpl implements AuthBarberService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthBarberServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void authBarber(RegisterDTO registerDTO) {
        try {
            var newBarber = new User(
                    registerDTO.getLogin(),
                    passwordEncoder.encode(registerDTO.getPassword()),
                    UserRole.BARBER
            );
            userRepository.save(newBarber);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("O login já está em uso");
        }
    }
}
