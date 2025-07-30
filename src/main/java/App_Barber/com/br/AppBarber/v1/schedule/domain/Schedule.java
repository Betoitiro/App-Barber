package App_Barber.com.br.AppBarber.v1.schedule.domain;

import App_Barber.com.br.AppBarber.v1.barber.domain.model.Barber;
import App_Barber.com.br.AppBarber.v2.user.jpa.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "t_schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private LocalDate date;

    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "barber_id")
    private Barber barber;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User user;

    private String status;
}
