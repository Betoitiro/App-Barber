package App_Barber.com.br.AppBarber.v1.barber.service;

import App_Barber.com.br.AppBarber.v1.barber.domain.model.Barber;
import App_Barber.com.br.AppBarber.v1.barber.dto.CreateBarberRequestDTO;
import App_Barber.com.br.AppBarber.v1.barber.repository.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateBarberServiceImpl {


    @Autowired
    private BarberRepository barberRepository;

    public void createBarberService (CreateBarberRequestDTO createBarberRequestDTO){
        var createBarber = Barber.builder()
                .name(createBarberRequestDTO.getName())
                .email(createBarberRequestDTO.getEmail())
                .phone(createBarberRequestDTO.getPhone())
                .cpf(createBarberRequestDTO.getCpf())
                .createAt(LocalDateTime.now())
                .build();

        barberRepository.save(createBarber);
    }

}
