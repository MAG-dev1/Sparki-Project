package MAG.MAG_system.Model;

import java.time.LocalDate;
import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import static  MAG.MAG_system.Constant.TableConstant.SUBJECT_TABLE;

@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = SUBJECT_TABLE, uniqueConstraints = @UniqueConstraint(columnNames = { "idUser", "username", "name"}))
public class Subject {

    @Id
    @GeneratedValue
    private Long idSubject;

    @Nonnull
    private Long idUser;

    @Nonnull
    private String username;
    
    @Nonnull 
    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 200, message = "{validation.name.size.too_long}")
    private String name;

    @Nonnull
    @Pattern(regexp = "^[1-2]-\\d{4}$", message = "Invalid semester format. Expected format: 1-YYYY or 2-YYYY")
    private String semester; // 1-YYYY or 2-YYYY

    @Nonnull
    @Size(min = 5, message = "{validation.name.size.too_short}")
    @Size(max = 200, message = "{validation.name.size.too_long}")
    private String description;
    
    @Nonnull
    private SubjectStatus status;

    @Nonnull
    private LocalDate created_date;

    @Nullable
    private Integer grade;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    @AssertTrue(message = "Grade must be non-null if status is APPROVED")
    private boolean isGradeValid() {
        return status != SubjectStatus.APPROVED || grade != null;
    }
}
