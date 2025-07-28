package App_Barber.com.br.AppBarber.v1.user.Service;

import App_Barber.com.br.AppBarber.v1.user.dto.RegisterDTO;

@FunctionalInterface
public interface AuthBarberService {
    void authBarber(RegisterDTO registerDTO);
}
