package com.fiap.roupapp.roupapp.controller;

import com.fiap.roupapp.roupapp.database.CarregarDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/database")
public class DatabaseController {


    private CarregarDatabase carregarDatabase;

    @Autowired
    public DatabaseController(CarregarDatabase carregarDatabase) {
        this.carregarDatabase = carregarDatabase;
    }


    @PostMapping("/create")
    public ResponseEntity carregar(){

        try{
            carregarDatabase.carregarBase();
            return ResponseEntity.ok().body("Banco de dados carregado com sucesso !");
        }catch (Exception e){

            return ResponseEntity.status(500).body("Ocorreu um erro " + e);
        }

    }
}
