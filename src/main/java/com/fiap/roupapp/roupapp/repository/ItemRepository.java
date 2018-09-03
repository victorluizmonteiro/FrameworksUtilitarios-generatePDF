package com.fiap.roupapp.roupapp.repository;

import com.fiap.roupapp.roupapp.entity.Item;
import org.springframework.data.repository.CrudRepository;


public interface ItemRepository extends CrudRepository<Item,String> {
}
