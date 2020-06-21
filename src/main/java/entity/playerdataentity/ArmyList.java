package entity.playerdataentity;

import entity.unitentity.BaseUnitEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ArmyList {

    public BaseUnitEntity[] hq;
    public BaseUnitEntity[] troops;
    public BaseUnitEntity[] elites;
    public BaseUnitEntity[] heavySupport;
    public BaseUnitEntity[] fastAttack;
    public BaseUnitEntity[] dedicatedTransport;
}
