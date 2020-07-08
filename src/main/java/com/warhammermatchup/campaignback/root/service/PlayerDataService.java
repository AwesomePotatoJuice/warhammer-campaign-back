package com.warhammermatchup.campaignback.root.service;

import entity.PlayerDataEntity;
import lombok.SneakyThrows;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.stereotype.Service;

import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.filechooser.FileSystemView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerDataService {

    private static String filePath = "/root/campaign-back/data/data.json";
    private static final String filePathLinux = "/root/campaign-back/data/data.json";
//    private static final String localFilePath = "C:/Users/Surin/Desktop/TestCampaign/data.json"; //Deprecated

    public PlayerDataService() {
        if(SystemUtils.IS_OS_LINUX){
            filePath = filePathLinux;
        }
        if(SystemUtils.IS_OS_WINDOWS){


            FileSystemView filesys = FileSystemView.getFileSystemView();

            filePath = filesys.getHomeDirectory().getAbsolutePath() + "\\TestCampaign\\data.json";
        }
    }

    @SneakyThrows
    public PlayerDataEntity getPlayerData(String player){
        InputStream inputStream = this.readFromDataSource();

        ObjectMapper mapper = new ObjectMapper();

        List<PlayerDataEntity> playerDataEntities = Arrays.asList(mapper.readValue(inputStream, PlayerDataEntity[].class));
        List<PlayerDataEntity> collect = playerDataEntities.stream().filter((i) -> i.playerName.equals(player)).collect(Collectors.toList());

        return collect.size() == 1 ? collect.get(0) : null;
    }

    private InputStream readFromDataSource() {
        InputStream targetStream = null;
        try
        {
            File initialFile = new File(PlayerDataService.filePath);
            targetStream = new FileInputStream(initialFile);
        }
        catch (IOException e)
        {
            System.out.println("No data.json file found! Have to create new one.");
        }
        return targetStream;
    }

    @SneakyThrows
    public PlayerDataEntity setPlayerData(String player, PlayerDataEntity newEntity) { //todo при ошибке парсинга бэкапить файл и создавать новый
        InputStream inputStream = this.readFromDataSource();

        ObjectMapper mapper = new ObjectMapper();
        List<PlayerDataEntity> playerDataEntities;
        int index = 0;

        if(inputStream == null){
            playerDataEntities = new ArrayList<>();
            playerDataEntities.add(newEntity);
        }else {
            playerDataEntities = new ArrayList<>(List.of(mapper.readValue(inputStream, PlayerDataEntity[].class)));
            List<PlayerDataEntity> playerDataEntitiesNew = new ArrayList<>(playerDataEntities);
            for (PlayerDataEntity entity : playerDataEntities
            ) {
                if (entity.playerName.equals(player)) {
                    playerDataEntitiesNew.set(index, newEntity);
                    break;
                }
                index++;
                if (index + 1 > playerDataEntities.size()) {
                    playerDataEntitiesNew.add(newEntity);
                }
            }
            playerDataEntities = new ArrayList<>(playerDataEntitiesNew);
        }

        String jsonString = mapper.writeValueAsString(playerDataEntities);

        this.writeToDataSource(jsonString);

        return playerDataEntities.get(index);
    }

    @SneakyThrows
    private void writeToDataSource(String jsonString) {
        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            System.out.println("writing data to data/data.json");
            fileWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public List<PlayerDataEntity> getPlayerDataAll() {
        InputStream inputStream = this.readFromDataSource();

        ObjectMapper mapper = new ObjectMapper();

        List<PlayerDataEntity> playerDataEntities = Arrays.asList(mapper.readValue(inputStream, PlayerDataEntity[].class));

        return playerDataEntities;
    }
}
