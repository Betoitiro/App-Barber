package App_Barber.com.br.AppBarber.v2.user.jpa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN("admin"),

    USER("user"),

    SELLER("seller");

    private String role;
 /*
    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

  */
}
