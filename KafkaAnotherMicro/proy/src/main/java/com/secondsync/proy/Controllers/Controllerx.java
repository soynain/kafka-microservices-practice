package com.secondsync.proy.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secondsync.proy.Services.TestService;

@RestController
public class Controllerx {
    @Autowired
    private TestService testService;


    @GetMapping("/start")
    public ResponseEntity<String> startExample(){
        return ResponseEntity.ok(testService.triggerSavingDBLikeSycronization("TRUE"));
    }

    @GetMapping("/pojo")
    public ResponseEntity<String> startPojo(){
        return ResponseEntity.ok(testService.triggerPojoSend());
    }

    @GetMapping("/testSend")
    public ResponseEntity<String> testSendListener(){
        return ResponseEntity.ok(testService.sendToListenerTest());
    }
}
