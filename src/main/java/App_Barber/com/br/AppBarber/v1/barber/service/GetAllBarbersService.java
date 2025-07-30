package App_Barber.com.br.AppBarber.v1.barber.service;

import App_Barber.com.br.AppBarber.v1.barber.dto.GetAllBarberResponseDTO;

import java.util.List;

public interface GetAllBarbersService {
    List<GetAllBarberResponseDTO> findAllBarbers();
}
