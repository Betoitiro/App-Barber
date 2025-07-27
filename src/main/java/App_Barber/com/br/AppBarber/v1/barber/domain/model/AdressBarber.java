package App_Barber.com.br.AppBarber.v1.barber.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "address_barber")
public class AdressBarber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String stree;

    @NotBlank
    @Size(max = 50)
    private String neighborhood;

    @NotBlank
    @Size(max = 10)
    private String number;

    @NotBlank
    @Size(max = 50)
    private String city;

    @NotBlank
    @Size(max = 2)
    private String state;

    @NotBlank
    @Size(max = 10)
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "barber_id", referencedColumnName = "id")
    private Barber barber;
}
