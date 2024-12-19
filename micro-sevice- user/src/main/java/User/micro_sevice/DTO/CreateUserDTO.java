package User.micro_sevice.DTO;

import User.micro_sevice.Model.RolEnum;
import lombok.Builder;

@Builder
public record CreateUserDTO(

    String name,
    String email,
    String username,
    String password,
    RolEnum rol


) {
} 
