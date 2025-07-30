package App_Barber.com.br.AppBarber.v1.schedule.repository;

import App_Barber.com.br.AppBarber.v1.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByBarberIdAndDate(Long barberId, LocalDate date);
    boolean existsByBarberIdAndDateAndTime(Long barberId, LocalDate date, LocalTime time);
}
