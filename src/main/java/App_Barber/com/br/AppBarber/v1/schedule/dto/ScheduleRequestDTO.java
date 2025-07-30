package App_Barber.com.br.AppBarber.v1.schedule.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleRequestDTO {
    private LocalDate date;
    private LocalTime time;
}
