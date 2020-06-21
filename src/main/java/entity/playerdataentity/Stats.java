package entity.playerdataentity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Stats {

    public String limit;
    public String maxLimit;
    public String fcp;
}
