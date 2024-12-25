package User.micro_sevice.DTO;

import lombok.Builder;

@Builder
public record ErrorResponseDTO(
    String error
) {
    
}
