package com.simbaleon.spring.models.subjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simbaleon.spring.models.Identifiable;
import com.simbaleon.spring.models.sessions.Session;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "subjects", schema = "public")
public class Subject extends RepresentationModel<Subject> implements Identifiable<Long> {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String disciplineName;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "subjects")
    private List<Session> session;
    private int totalHours;
    private short semester;
    private String speciality;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Subject subject = (Subject) o;
        return getTotalHours() == subject.getTotalHours()
                && getSemester() == subject.getSemester()
                && Objects.equals(getDisciplineName(), subject.getDisciplineName())
                && Objects.equals(getSpeciality(), subject.getSpeciality());
    }
}
