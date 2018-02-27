package service;

import java.util.List;

public interface GenericDAO<T, ID> {
    public abstract boolean save(T t);
    public abstract T getById(ID id);
    public abstract List<T> getAll();
    public abstract boolean update(ID id, T t);
    public abstract boolean remove(T t);
}
