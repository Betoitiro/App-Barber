package App_Barber.com.br.AppBarber.v2.user.service;


import App_Barber.com.br.AppBarber.v2.user.dto.RegisterDTO;
import App_Barber.com.br.AppBarber.v2.user.jpa.User;
import App_Barber.com.br.AppBarber.v2.user.jpa.UserRole;
import App_Barber.com.br.AppBarber.v2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void authUser(RegisterDTO registerDTO) {
        if (userRepository.findByLogin(registerDTO.getLogin()) != null) {
            throw new IllegalArgumentException("Login já está em uso.");
        }
        var newUser = new User(
                registerDTO.getLogin(),
                passwordEncoder.encode(registerDTO.getPassword()),
                UserRole.USER
        );
        userRepository.save(newUser);
    }


}
