package entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class PlayerDataEntityList {

    public PlayerDataEntity[] playerDataEntities;
}
