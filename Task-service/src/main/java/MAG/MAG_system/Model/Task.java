package MAG.MAG_system.Model;

import java.time.LocalDate;

import io.micrometer.common.lang.Nullable;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static  MAG.MAG_system.Constant.TableConstant.TASK_TABLE;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = TASK_TABLE)
public class Task {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Nonnull 
    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 200, message = "{validation.name.size.too_long}")
    private String name;

    @Nonnull
    private String semester;

    @Nonnull
    @Size(min = 5, message = "{validation.name.size.too_short}")
    @Size(max = 200, message = "{validation.name.size.too_long}")
    private String description;
    
    @Nonnull
    private TaskType type;

    @Nonnull
    private LocalDate created_date;

    @Nonnull
    private LocalDate expired_date;

    @Nullable
    private Integer score;

    
    @ManyToOne
    @JoinColumn(name = "idSubject", nullable = false)
    private Subject subject;
}
