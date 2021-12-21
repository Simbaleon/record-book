package com.simbaleon.spring.models.sessions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simbaleon.spring.models.Identifiable;
import com.simbaleon.spring.models.records.Record;
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
public class SessionResult extends RepresentationModel<SessionResult> implements Identifiable<Long> {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "session")
    private List<Record> records;
    private String bookNum;
    private short semester;

    public boolean isPassed() {
        return records.stream().noneMatch(Record::isFail);
    }
}
