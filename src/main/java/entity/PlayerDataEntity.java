package entity;

import entity.playerdataentity.ArmyList;
import entity.playerdataentity.Stats;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PlayerDataEntity {

    public String playerName;
    public Stats stats;
    public ArmyList armyList;
}
