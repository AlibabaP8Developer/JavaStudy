package com.ruoyi.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuiguException extends RuntimeException{

    private Integer code;
    private String msg;

}
