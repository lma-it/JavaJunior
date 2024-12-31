package databases.seminar.task1.interfaces;

import java.util.Collection;

public interface Repository<T, TId> {
    void add(T item);
    void update(T item);
    void delete(T idem);
    T getBy(TId id);
    Collection<T> getAll();
}
