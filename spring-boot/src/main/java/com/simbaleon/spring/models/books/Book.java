package com.simbaleon.spring.models.books;

import com.simbaleon.spring.models.Identifiable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "books", schema = "public")
public class Book extends RepresentationModel<Book> implements Identifiable<String> {
    @Id
    @GenericGenerator(
            name = "assigned-sequence",
            strategy = "com.simbaleon.spring.models.books.BookNumberGenerator",
            parameters = @org.hibernate.annotations.Parameter(name = "sequence_name", value = "hibernate_sequence")
    )
    @GeneratedValue(generator = "assigned-sequence", strategy = GenerationType.SEQUENCE)
    private String id;
    @Column
    @NotEmpty
    private String fullName;
    @Column
    @NotEmpty
    private String faculty;
    @Column
    private String specialty;
    @CreatedDate
    private LocalDate admissionDate;
    @Column
    @Enumerated(EnumType.STRING)
    private StudyForm studyForm;

    public enum StudyForm {
        FULL_TIME, PART_TIME, EXTRAMURAL
    }
}
