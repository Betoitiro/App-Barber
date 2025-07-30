package App_Barber.com.br.AppBarber.v1.barber.dto;

import lombok.Data;

@Data
public class CreateAddressBarberRequestDTO {
    private String stree;
    private String neighborhood;
    private String number;
    private String city;
    private String state;
    private String zipCode;
}
