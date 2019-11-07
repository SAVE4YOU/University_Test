package domains;

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

    @ManyToMany(mappedBy = "departments",fetch = FetchType.EAGER)
    private List<Lector> lectors;

    public Department(String name) {
        this.name = name;
    }

    public Department() {
    }
}
