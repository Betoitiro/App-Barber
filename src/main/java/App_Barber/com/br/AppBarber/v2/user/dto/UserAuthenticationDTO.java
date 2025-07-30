package App_Barber.com.br.AppBarber.v2.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticationDTO {
    private String login;
    private String password;
}
