package com.simbaleon.spring.models.sessions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simbaleon.spring.models.Identifiable;
import com.simbaleon.spring.models.subjects.Subject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "sessions", schema = "public")
public class Session extends RepresentationModel<Session> implements Identifiable<Long> {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "session_subjects",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjects;
    private String bookNum;
    private short semester;

    public Session(String bookNum, short semester) {
        this.bookNum = bookNum;
        this.semester = semester;
    }
}
