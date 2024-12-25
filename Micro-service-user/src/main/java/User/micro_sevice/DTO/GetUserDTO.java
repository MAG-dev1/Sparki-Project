package User.micro_sevice.DTO;

import lombok.Builder;

@Builder
public record GetUserDTO(
    String name,
    String email,
    String username,
    String password
) {
    
}
