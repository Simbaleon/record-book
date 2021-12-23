package com.simbaleon.spring.models.professors;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simbaleon.spring.models.Identifiable;
import com.simbaleon.spring.models.subjects.Subject;
import com.simbaleon.spring.models.users.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "professor_subject", schema = "public")
public class ProfessorSubject implements Identifiable<Long> {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User professor;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Subject subject;
    @Column
    private String groupId;
}
