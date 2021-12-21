package com.simbaleon.spring.models.subjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simbaleon.spring.models.Identifiable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

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
    @Column
    private String disciplineName;
    @Column
    private int totalHours;
    @Column
    private short semester;
    @Column
    private String speciality;
}
