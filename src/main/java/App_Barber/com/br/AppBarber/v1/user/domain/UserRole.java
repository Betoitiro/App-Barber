package App_Barber.com.br.AppBarber.v1.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN("admin"),

    USER("user"),

    BARBER("barber");

    private String role;
}
