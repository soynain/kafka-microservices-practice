package com.kafkapractice.proy.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafkapractice.proy.Entities.MicroOneEntity;
import com.kafkapractice.proy.Services.MicroOneService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MainController {
    
    @Autowired
    private MicroOneService microOneService;

    @GetMapping("/pruebita")
    public String getMethodName() {
        return new String("AQUI ANDO PACHI");
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MicroOneEntity>> returnRegistries(){
        /**Interceptor seria la práctica... libreria */
        return ResponseEntity.ok(microOneService.getAllResults());
    }
    
}
