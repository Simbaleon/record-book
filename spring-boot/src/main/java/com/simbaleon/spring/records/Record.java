package com.simbaleon.spring.records;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "records", schema = "public")
public class Record extends RepresentationModel<Record> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn
    private Session session;
    @Column
    @ManyToOne
    private Subject subject;
    @Column
    @Min(value = 2, message = "The grade cannot be less than 2")
    @Max(value = 5, message = "The grade cannot be more than 2")
    private GradeType grade;
    @Column
    private String professorLastName;

    @Getter
    public enum GradeType {
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
