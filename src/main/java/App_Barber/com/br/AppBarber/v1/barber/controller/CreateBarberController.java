package App_Barber.com.br.AppBarber.v1.barber.controller;

import App_Barber.com.br.AppBarber.v1.barber.dto.CreateBarberRequestDTO;
import App_Barber.com.br.AppBarber.v1.barber.service.CreateBarberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/barbers")
public class CreateBarberController {
    @Autowired
    private CreateBarberServiceImpl createBarberService;

    @PostMapping
    public ResponseEntity<Void> createBarber(@RequestBody CreateBarberRequestDTO createBarberRequestDTO){
        createBarberService.createBarberService(createBarberRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
