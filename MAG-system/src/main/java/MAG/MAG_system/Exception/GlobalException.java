package MAG.MAG_system.Exception;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientException;

@ControllerAdvice(basePackages = {"MAG.MAG_system.Services"})
@ParameterObject
public class GlobalException {
    
    @ExceptionHandler(WebClientException.class)
    public ResponseEntity<?> handleInternalErrorResponse(WebClientException exception){
        return ResponseEntity.status(500).build();
    }
}
