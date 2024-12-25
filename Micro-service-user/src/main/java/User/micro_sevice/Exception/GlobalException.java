package User.micro_sevice.Exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import User.micro_sevice.Constant.ErrorMessage;
import User.micro_sevice.DTO.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Hidden;

@ControllerAdvice
@Hidden
public class GlobalException {
    
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException e){
        return ResponseEntity.badRequest().body(error(e));
    }

       
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e){
        return ResponseEntity.badRequest().body(error(e));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e){
        return ResponseEntity.badRequest().body(error(new Exception(ErrorMessage.INVALID_USER)));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<?> handleInvalidTokenException(InvalidTokenException e){
        return ResponseEntity.status(401).build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundExceptionException(UsernameNotFoundException e){
        return ResponseEntity.status(401).build();
    }

    private ErrorResponseDTO error(Exception e){
        return ErrorResponseDTO.builder().error(e.getMessage()).build();
    }

}
