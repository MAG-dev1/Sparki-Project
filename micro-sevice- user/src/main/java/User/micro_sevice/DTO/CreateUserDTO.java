package User.micro_sevice.DTO;

import User.micro_sevice.Model.Rol;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Builder.Default;

@Builder
public record CreateUserDTO(

    String name,
    String email,
    String username,
    String password,
    String rol


) {
} 
