package entity.unitentity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BaseUnitEntity {

    public String label;
    public String points;
    public String modelsCount;
}
