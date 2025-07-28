package App_Barber.com.br.AppBarber.v1.user.repository;

import App_Barber.com.br.AppBarber.v1.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    //UserDetails vai ser usado pelo SpringSecurity depois
    UserDetails findByLogin(String login);
    Optional<User> findUserByLogin(String login);

}