package App_Barber.com.br.AppBarber.v1.barber.service;

import App_Barber.com.br.AppBarber.v1.barber.domain.model.Barber;
import App_Barber.com.br.AppBarber.v1.barber.dto.CreateBarberRequestDTO;
import App_Barber.com.br.AppBarber.v1.barber.repository.BarberRepository;
import App_Barber.com.br.AppBarber.v1.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateBarberServiceImpl {

    private final UserRepository userRepository;
    private final  BarberRepository barberRepository;

    public CreateBarberServiceImpl (BarberRepository barberRepository, UserRepository userRepository){
        this.userRepository = userRepository;
        this.barberRepository = barberRepository;
    }


    public void createBarberService (CreateBarberRequestDTO createBarberRequestDTO){
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login;

        if (principal instanceof UserDetails){
            login = ((UserDetails) principal).getUsername();
        }else {
            login = principal.toString();
        }

        var user = userRepository.findUserByLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        var createBarber = Barber.builder()
                .name(createBarberRequestDTO.getName())
                .email(createBarberRequestDTO.getEmail())
                .phone(createBarberRequestDTO.getPhone())
                .cpf(createBarberRequestDTO.getCpf())
                .createAt(LocalDateTime.now())
                .build();

        createBarber.setUser(user);
       // user.setBarber(createBarber);
        barberRepository.save(createBarber);
    }

}
