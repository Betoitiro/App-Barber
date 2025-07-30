package App_Barber.com.br.AppBarber.v1.barber.service;

import App_Barber.com.br.AppBarber.v1.barber.domain.model.Barber;
import App_Barber.com.br.AppBarber.v1.barber.dto.CreateBarberRequestDTO;
import App_Barber.com.br.AppBarber.v1.barber.repository.BarberRepository;
import App_Barber.com.br.AppBarber.v2.user.repository.UserRepository;
import App_Barber.com.br.AppBarber.v2.user.service.AuthenticatedUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateBarberServiceImpl {

    private final UserRepository userRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final  BarberRepository barberRepository;

    public CreateBarberServiceImpl (BarberRepository barberRepository, AuthenticatedUserService authenticatedUserService, UserRepository userRepository){
        this.userRepository = userRepository;
        this.authenticatedUserService = authenticatedUserService;
        this.barberRepository = barberRepository;
    }
    public void createBarberService (CreateBarberRequestDTO createBarberRequestDTO){
        var userId = authenticatedUserService.getAuthenticatedUser();
        var user = userRepository.findById(String.valueOf(userId))
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));

        var createBarber = Barber.builder()
                .name(createBarberRequestDTO.getName())
                .email(createBarberRequestDTO.getEmail())
                .phone(createBarberRequestDTO.getPhone())
                .cpf(createBarberRequestDTO.getCpf())
                .createAt(LocalDateTime.now())
                .build();
        createBarber.setUser(user);
        barberRepository.save(createBarber);
    }
}
