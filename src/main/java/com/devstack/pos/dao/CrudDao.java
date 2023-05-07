package com.devstack.pos.dao;

import java.util.List;

public interface CrudDao<T, ID> extends SuperDao{
    public void save(T t);
    public T find(ID id);
    public void update(T t) throws ClassNotFoundException;
    public void delete(ID id);
    public List findAll();
}
