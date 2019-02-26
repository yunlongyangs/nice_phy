package cn.nice.nice_phy.result;

import cn.nice.nice_phy.codesenum.NiceCode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NiceResult {
    private Integer code;
    private String message;
    private Long timestmp;

    public NiceResult(NiceCode niceCode) {
        this.code = niceCode.getCode();
        this.message = niceCode.getMessage();
        this.timestmp = System.currentTimeMillis();
    }
}
