package com.warhammermatchup.campaignback.root.controller;

import com.warhammermatchup.campaignback.root.service.PlayerDataService;
import entity.PlayerDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PlayerDataController {

    @Autowired
    PlayerDataService playerDataService;

    @GetMapping(value = "/getData")
    public ResponseEntity<Object> getPlayerData(@RequestParam String player){
        PlayerDataEntity result;
        List<PlayerDataEntity> resultAll;
        if(player.equals("full")){
            resultAll = playerDataService.getPlayerDataAll();
            return ResponseEntity.ok(resultAll);
        }
        else {
            result = playerDataService.getPlayerData(player);
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping(value = "/setData")
    public ResponseEntity<PlayerDataEntity> setPlayerData(@RequestParam String player, @RequestBody PlayerDataEntity playerDataEntityList){
        PlayerDataEntity result = playerDataService.setPlayerData(player, playerDataEntityList);
        return ResponseEntity.ok(result);
    }
}
