package App_Barber.com.br.AppBarber.v1.barber.repository;

import App_Barber.com.br.AppBarber.v1.barber.domain.model.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarberRepository extends JpaRepository<Barber, Long> {
    Optional<Barber> findByUserId(Long userId);
}
