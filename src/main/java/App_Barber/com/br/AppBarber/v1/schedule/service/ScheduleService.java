package App_Barber.com.br.AppBarber.v1.schedule.service;

import App_Barber.com.br.AppBarber.v1.barber.domain.model.Barber;
import App_Barber.com.br.AppBarber.v1.barber.repository.BarberRepository;
import App_Barber.com.br.AppBarber.v1.schedule.domain.Schedule;
import App_Barber.com.br.AppBarber.v1.schedule.dto.ScheduleRequestDTO;
import App_Barber.com.br.AppBarber.v1.schedule.repository.ScheduleRepository;
import App_Barber.com.br.AppBarber.v2.user.service.AuthenticatedUserService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final BarberRepository barberRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public ScheduleService(ScheduleRepository scheduleRepository,
                           BarberRepository barberRepository,
                           AuthenticatedUserService authenticatedUserService) {
        this.scheduleRepository = scheduleRepository;
        this.barberRepository = barberRepository;
        this.authenticatedUserService = authenticatedUserService;
    }

    public List<LocalTime> getAvailableTimes(Long barberId, LocalDate date) {
        List<LocalTime> allSlots = generateTimeSlots(LocalTime.of(9, 0), LocalTime.of(18, 0), Duration.ofMinutes(20));
        List<LocalTime> booked = scheduleRepository.findByBarberIdAndDate(barberId, date)
                .stream().map(Schedule::getTime).toList();
        allSlots.removeAll(booked);
        return allSlots;
    }

    public void createSchedule(Long barberId, ScheduleRequestDTO dto) {
        boolean exists = scheduleRepository.existsByBarberIdAndDateAndTime(barberId, dto.getDate(), dto.getTime());
        if (exists) throw new IllegalStateException("Horário já agendado");

        Barber barber = barberRepository.findById(barberId)
                .orElseThrow(() -> new IllegalArgumentException("Barbeiro não encontrado"));

        Schedule schedule = new Schedule();
        schedule.setBarber(barber);
        schedule.setUser(authenticatedUserService.getAuthenticatedUserEntity());
        schedule.setDate(dto.getDate());
        schedule.setTime(dto.getTime());
        schedule.setStatus("AGENDADO");

        scheduleRepository.save(schedule);
    }

    private List<LocalTime> generateTimeSlots(LocalTime start, LocalTime end, Duration interval) {
        List<LocalTime> slots = new ArrayList<>();
        LocalTime current = start;

        while (!current.isAfter(end.minus(interval))) {
            slots.add(current);
            current = current.plus(interval);
        }

        return slots;
    }
}
