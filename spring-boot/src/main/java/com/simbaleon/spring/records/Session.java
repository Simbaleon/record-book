package com.simbaleon.spring.records;

import com.simbaleon.spring.books.Book;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "sessions", schema = "public")
public class Session extends RepresentationModel<Session> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "session")
    private List<Record> records;
    private String bookNum;

    public boolean isPassed() {
        return records.stream().noneMatch(Record::isFail);
    }
}
