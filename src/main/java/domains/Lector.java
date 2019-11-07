package domains;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "lectors")
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @ElementCollection(targetClass = Degree.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "degree", joinColumns = @JoinColumn(name = "lector_id"))
    @Enumerated(EnumType.STRING)
    private Set<Degree> degree;
    private int salary;


    @ManyToMany(fetch = FetchType.EAGER)
    @ElementCollection(targetClass = Department.class)
    @CollectionTable(name = "departments", joinColumns = @JoinColumn(name = "lector_id"))
    private List<Department> departments;

    public Lector(String name, String lastName, Set<Degree> degree, int salary) {
        this.name = name;
        this.lastName = lastName;
        this.degree = degree;
        this.salary = salary;
    }

    public Lector() {
    }

    @Override
    public String toString() {
        return "Lector{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", degree=" + degree +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lector lector = (Lector) o;
        return id.equals(lector.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

