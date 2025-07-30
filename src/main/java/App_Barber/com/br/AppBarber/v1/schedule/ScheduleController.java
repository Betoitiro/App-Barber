package App_Barber.com.br.AppBarber.v1.schedule;

import App_Barber.com.br.AppBarber.v1.schedule.dto.ScheduleRequestDTO;
import App_Barber.com.br.AppBarber.v1.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/barbers/{barberId}/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/available")
    public List<LocalTime> getAvailable(@PathVariable Long barberId, @RequestParam("date") LocalDate date) {

        return scheduleService.getAvailableTimes(barberId, date);
    }

    @PostMapping
    public ResponseEntity<String> createSchedule(@PathVariable Long barberId, @RequestBody ScheduleRequestDTO dto) {
        scheduleService.createSchedule(barberId, dto);
        return ResponseEntity.ok("Horário agendado com sucesso");
    }
}
