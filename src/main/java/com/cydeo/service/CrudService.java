package com.cydeo.service;

import com.cydeo.dto.RoleDTO;

import java.util.List;

public interface CrudService<T,ID>{

    //It depends on our app needs
    T save(T role);
    T findById(ID id);
    List<T> findAll();
    void deleteById(ID id);

    void update(T object);

}
