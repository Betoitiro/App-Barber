package App_Barber.com.br.AppBarber.v1.barber.repository;

import App_Barber.com.br.AppBarber.v1.barber.domain.model.AdressBarber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBarberRepository extends JpaRepository<AdressBarber, Long> {
}
