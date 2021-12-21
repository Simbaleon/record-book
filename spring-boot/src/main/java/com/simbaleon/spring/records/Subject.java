package com.simbaleon.spring.records;

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
public class Subject extends RepresentationModel<Subject> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String disciplineName;
    @Column
    private int totalHours;
    @Column
    private short course;
    @Column
    private String speciality;
}
