package App_Barber.com.br.AppBarber.v1.user;

import App_Barber.com.br.AppBarber.v1.user.Service.AuthBarberService;
import App_Barber.com.br.AppBarber.v1.user.Service.AuthClientService;
import App_Barber.com.br.AppBarber.v1.user.domain.User;
import App_Barber.com.br.AppBarber.v1.user.dto.LoginResponseDTO;
import App_Barber.com.br.AppBarber.v1.user.dto.RegisterDTO;
import App_Barber.com.br.AppBarber.v1.user.dto.UserAuthenticationDTO;
import App_Barber.com.br.AppBarber.v2.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2")
@RequiredArgsConstructor
public class userController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final AuthBarberService authBarberService;
    private final AuthClientService authClientService;

    @PostMapping("/login")
    @Operation(
            summary = "Realiza o login do usuario",
            description = "Metodo para o usuario realizar o login"
    )
    @ApiResponse(responseCode = "201", description = "Usuario logado com sucesso")
    @ApiResponse(responseCode = "401")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid UserAuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok( new LoginResponseDTO(token));
    }

    @PostMapping("/user/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Endpoint responsavel por registrar um usuario",
    description = "Esse usuario é responsavel por cadastrar / registrar um usuario dentro da api")
    public ResponseEntity<?> registerClient (@RequestBody @Valid RegisterDTO registerDTO){
        try {
            authClientService.authClient(registerDTO);
            return ResponseEntity.ok("Usuario registrado com sucesso");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/barber/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Endpoint responsavel por registrar um usuario",
            description = "Esse usuario é responsavel por cadastrar / registrar um usuario dentro da api")
    public ResponseEntity<?> registerBarber (@RequestBody @Valid RegisterDTO registerDTO){
        try{
            authBarberService.authBarber(registerDTO);
            return ResponseEntity.ok("Usuario cadastrado com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
