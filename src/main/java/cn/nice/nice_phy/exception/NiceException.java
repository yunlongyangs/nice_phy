package cn.nice.nice_phy.exception;

import cn.nice.nice_phy.codesenum.NiceCode;
import lombok.Getter;


@Getter
public class NiceException extends RuntimeException{
    private NiceCode niceCode;

    public NiceException(NiceCode niceCode) {
        this.niceCode = niceCode;
    }
}
