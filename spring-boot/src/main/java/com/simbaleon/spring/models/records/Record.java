package com.simbaleon.spring.models.records;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simbaleon.spring.models.Identifiable;
import com.simbaleon.spring.models.subjects.Subject;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "records", schema = "public")
public class Record extends RepresentationModel<Record> implements Identifiable<Long> {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookNum;
    @ManyToOne
    private Subject subject;
    @Column
    @Enumerated(EnumType.STRING)
    private GradeType grade;
    @Column
    private String professorLastName;

    @Getter
    public enum GradeType {
        BLANK(0),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        PASS(0),
        FAIL(-1);

        private final int value;

        GradeType(int value) {
            this.value = value;
        }
    }

    public boolean isFail() {
        return grade == GradeType.FAIL || grade == GradeType.TWO;
    }
}
