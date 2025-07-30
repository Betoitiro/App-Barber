package App_Barber.com.br.AppBarber.v2.user.dto;

import App_Barber.com.br.AppBarber.v2.user.jpa.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO{
    private String login;
    private String password;
    private UserRole userRole;
}
