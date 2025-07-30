package App_Barber.com.br.AppBarber.v2.user.jpa;

import App_Barber.com.br.AppBarber.v2.user.dto.RegisterDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "t_users")
@Entity(name = "t_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Ou AUTO
    private Long id;

    @Column(unique = true)
    @NotNull(message = "O login não pode ser nulo")
    @Size(max = 100, message = "O login não pode exceder 100 caracteres")
    private String login;

    @NotNull(message = "A senha não pode ser nula")
    @Size(min = 8, max = 64, message = "A senha deve ter entre 8 e 64 caracteres")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;


    public User(String login, String password, UserRole user) {
        this.login = login;
        this.password = password;
        this.role = user;
    }

    public User(RegisterDTO data) {
        this.login = data.getLogin();
        this.password = data.getPassword();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_SELLER"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_SELLER")
                    );
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
