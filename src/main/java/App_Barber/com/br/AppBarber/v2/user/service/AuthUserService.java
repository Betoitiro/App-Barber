package App_Barber.com.br.AppBarber.v2.user.service;


import App_Barber.com.br.AppBarber.v2.user.dto.RegisterDTO;

@FunctionalInterface
public interface AuthUserService {
    void authUser(RegisterDTO registerDTO);
}
