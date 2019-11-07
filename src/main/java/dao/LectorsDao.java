package dao;

import domains.Lector;

import java.util.List;

public interface LectorsDao {

    void save(Lector lector);

    void update(Lector lector);

    Lector findById(Long id);

    List<Lector> findAll();

    List<Lector> globalSearch(String template);

    void delete(Lector lector);
}
