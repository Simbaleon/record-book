package com.simbaleon.spring.models.books;

import com.simbaleon.spring.models.Identifiable;
import com.simbaleon.spring.models.sessions.Session;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

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
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String faculty;
    @NotEmpty
    private String groupId;
    @NotEmpty
    private String specialty;
    @CreatedDate
    private LocalDate admissionDate;
    @Enumerated(EnumType.STRING)
    private StudyForm studyForm;
    @Transient
    private List<Session> sessionList;

    public enum StudyForm {
        FULL_TIME, PART_TIME, EXTRAMURAL
    }
}
