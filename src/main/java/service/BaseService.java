package service;


import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> getAll();
    Optional<T> getById(long id);
    T add(T data);
    void delete(T data);
    T update (T data);

}
