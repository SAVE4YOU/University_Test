package services;

import dao_impl.LectorsDaoImpl;
import domains.Lector;

import java.util.List;

public class LectorService {
    private LectorsDaoImpl lectorsDao = new LectorsDaoImpl();

    public void save(Lector lector) {
        lectorsDao.save(lector);
    }

    public void update(Lector lector) {
        lectorsDao.update(lector);
    }

    public Lector findById(Long id) {
        return lectorsDao.findById(id);
    }

    public List<Lector> findAll() {
        return lectorsDao.findAll();
    }

    public List<Lector> globalSearch(String template) {
        return lectorsDao.globalSearch(template);
    }

    public void delete(Lector lector){
        lectorsDao.delete(lector);
    }
}
