package App_Barber.com.br.AppBarber.v1.barber;

import App_Barber.com.br.AppBarber.v1.barber.dto.CreateAddressBarberRequestDTO;
import App_Barber.com.br.AppBarber.v1.barber.dto.CreateBarberRequestDTO;
import App_Barber.com.br.AppBarber.v1.barber.dto.GetAllBarberResponseDTO;
import App_Barber.com.br.AppBarber.v1.barber.service.CreateAddressBarberServiceImpl;
import App_Barber.com.br.AppBarber.v1.barber.service.CreateBarberServiceImpl;
import App_Barber.com.br.AppBarber.v1.barber.service.GetAllBarbersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/barbers")
public class BarberController {
    @Autowired
    private CreateBarberServiceImpl createBarberService;

    @Autowired
    private CreateAddressBarberServiceImpl createAddressBarberService;

    @Autowired
    private GetAllBarbersServiceImpl getAllBarbersService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBarber(@RequestBody CreateBarberRequestDTO createBarberRequestDTO){
        createBarberService.createBarberService(createBarberRequestDTO);
    }

    @PostMapping("/address")
    public void createAddressBarber(@RequestBody CreateAddressBarberRequestDTO createAddressBarberRequestDTO){
        createAddressBarberService.createAddressBarber(createAddressBarberRequestDTO);
    }

    @GetMapping
    public List<GetAllBarberResponseDTO> getAllBarber(){
        return getAllBarbersService.findAllBarbers();
    }
}