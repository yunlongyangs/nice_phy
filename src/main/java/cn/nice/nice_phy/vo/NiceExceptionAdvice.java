package cn.nice.nice_phy.vo;

import cn.nice.nice_phy.exception.NiceException;
import cn.nice.nice_phy.result.NiceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NiceExceptionAdvice {
    @ExceptionHandler(value = NiceException.class)
    public ResponseEntity<NiceResult> error(NiceException e){
        return ResponseEntity.status(e.getNiceCode().getCode()).body(new NiceResult(e.getNiceCode()));
    }
}
