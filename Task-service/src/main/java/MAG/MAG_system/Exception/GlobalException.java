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

    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<?> handleSubjectNotFoundException(SubjectNotFoundException exception) {
        return ResponseEntity.status(404).body("Subject not found");
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<?> handleTaskNotFoundException(TaskNotFoundException exception) {
        return ResponseEntity.status(404).body("Task not found");
    }

    @ExceptionHandler(UnathorizedException.class)
    public ResponseEntity<?> handleUnathorizedException(UnathorizedException exception) {
        return ResponseEntity.status(401).body("Unauthorized access");
    }

    
}
