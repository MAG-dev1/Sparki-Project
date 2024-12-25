package User.micro_sevice.Mapper;


import User.micro_sevice.DTO.GetUserDTO;
import User.micro_sevice.Model.User;

public class UserMapper {
    
    public GetUserDTO toDTO(User user){
        return GetUserDTO
        .builder()
        .name(user.getName())
        .email(user.getEmail())
        .username(user.getUsername())
        .password(user.getPassword())
        .build();
    }
}
