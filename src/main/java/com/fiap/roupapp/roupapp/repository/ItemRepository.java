package com.fiap.roupapp.roupapp.repository;

import com.fiap.roupapp.roupapp.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ItemRepository extends JpaRepository<Item,String> {
}
