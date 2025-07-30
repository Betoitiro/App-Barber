package App_Barber.com.br.AppBarber.v2.user;


import App_Barber.com.br.AppBarber.v2.infra.security.TokenService;
import App_Barber.com.br.AppBarber.v2.user.dto.LoginResponseDTO;
import App_Barber.com.br.AppBarber.v2.user.dto.RegisterDTO;
import App_Barber.com.br.AppBarber.v2.user.dto.UserAuthenticationDTO;
import App_Barber.com.br.AppBarber.v2.user.jpa.User;
import App_Barber.com.br.AppBarber.v2.user.service.AuthUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/v3")
@RequiredArgsConstructor
@Tag(name = "Controlador de autenticação")
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final AuthUserService userService;

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
    @PostMapping("/authuser/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Endponint responsavel por registar um usuario",
            description = "Endpoint respnsavel por registrar um usuario dentro da api")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO registerDTO) {
        try {
            userService.authUser(registerDTO);
            return ResponseEntity.ok("Usuário registrado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
