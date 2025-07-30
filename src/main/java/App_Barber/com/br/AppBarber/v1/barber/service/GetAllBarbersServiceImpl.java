package App_Barber.com.br.AppBarber.v1.barber.service;

import App_Barber.com.br.AppBarber.v1.barber.dto.GetAllBarberResponseDTO;
import App_Barber.com.br.AppBarber.v1.barber.repository.BarberRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllBarbersServiceImpl {
    private final BarberRepository barberRepository;

    @Transactional
    public List<GetAllBarberResponseDTO> findAllBarbers(){
        return barberRepository.findAll()
                .stream()
                .map(GetAllBarberResponseDTO::from)
                .toList();
    }
}
