package App_Barber.com.br.AppBarber.v1.barber.domain.model;

import App_Barber.com.br.AppBarber.v2.user.jpa.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_barbearia")
@Builder
public class Barber implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Email(message = "O campo email não pode ser vazio")
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 digitos")
    @Column(length = 11, unique = true)
    private String cpf;

    @Column
    private String phone;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @Column(name = "created_at", updatable = false)
    private LocalDateTime createAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "barber", cascade = CascadeType.ALL, orphanRemoval = true)
    private AdressBarber address;

    public String getUserLogin() {
        return this.user != null ? this.user.getLogin() : null;
    }

}
