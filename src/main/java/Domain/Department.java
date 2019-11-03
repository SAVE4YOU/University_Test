package Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "head_id")
    private Lector head;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Lector> lectors;

    public Department(String name) {
        this.name = name;
    }

    public Department() {
    }
}
