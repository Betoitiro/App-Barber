package App_Barber.com.br.AppBarber.v1.barber.dto;

import App_Barber.com.br.AppBarber.v1.barber.domain.model.Barber;
import App_Barber.com.br.AppBarber.v2.user.jpa.User;
import lombok.Data;

@Data
public class GetAllBarberResponseDTO {

    private String name;
    private String email;
    private String phone;
    private String cpf;
    private String user;
    private String street;
    private String neighborhood;
    private String number;
    private String city;
    private String state;
    private String zipCode;

    public static GetAllBarberResponseDTO from(Barber barber) {
        GetAllBarberResponseDTO dto = new GetAllBarberResponseDTO();
        dto.name = barber.getName();
        dto.email = barber.getEmail();
        dto.phone = barber.getPhone();
        dto.cpf = barber.getCpf();
        dto.user = barber.getUser() != null ? barber.getUser().getLogin() : null;
        if (barber.getAddress() != null){
            dto.street = barber.getAddress().getStree();
            dto.neighborhood = barber.getAddress().getNeighborhood();
            dto.number = barber.getAddress().getNumber();
            dto.city = barber.getAddress().getCity();
            dto.state = barber.getAddress().getState();
            dto.zipCode = barber.getAddress().getZipCode();
        }
        return dto;
    }

}
