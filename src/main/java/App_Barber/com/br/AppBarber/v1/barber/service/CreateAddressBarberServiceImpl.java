package App_Barber.com.br.AppBarber.v1.barber.service;

import App_Barber.com.br.AppBarber.v1.barber.domain.model.AdressBarber;
import App_Barber.com.br.AppBarber.v1.barber.dto.CreateAddressBarberRequestDTO;
import App_Barber.com.br.AppBarber.v1.barber.repository.AddressBarberRepository;
import App_Barber.com.br.AppBarber.v1.barber.repository.BarberRepository;
import App_Barber.com.br.AppBarber.v2.user.service.AuthenticatedUserService;
import org.springframework.stereotype.Service;

@Service
public class CreateAddressBarberServiceImpl implements CreateAddressBarberService{
    private final AddressBarberRepository addressBarberRepository;
    private final BarberRepository barberRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public CreateAddressBarberServiceImpl(AddressBarberRepository addressBarberRepository,
                                      BarberRepository barberRepository,
                                      AuthenticatedUserService authenticatedUserService) {
        this.addressBarberRepository = addressBarberRepository;
        this.barberRepository = barberRepository;
        this.authenticatedUserService = authenticatedUserService;
    }

    public void createAddressBarber(CreateAddressBarberRequestDTO createAddressBarberRequestDTO){
        var userId = authenticatedUserService.getAuthenticatedUser();
        var barber = barberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Barbearia não encontrada"));
        var address = new AdressBarber();
        address.setStree(createAddressBarberRequestDTO.getStree());
        address.setNeighborhood(createAddressBarberRequestDTO.getNeighborhood());
        address.setNumber(createAddressBarberRequestDTO.getNumber());
        address.setCity(createAddressBarberRequestDTO.getCity());
        address.setState(createAddressBarberRequestDTO.getState());
        address.setZipCode(createAddressBarberRequestDTO.getZipCode());
        address.setBarber(barber);
        addressBarberRepository.save(address);

    }

}
