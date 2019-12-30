package hello.controller;

import hello.error.BussinessException;
import hello.error.EmBussinessError;
import hello.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

public class BaseController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonReturnType BussinessExceptionHandler(Exception e){
        CommonReturnType commonReturnType = new CommonReturnType();
        HashMap<String, String> map = new HashMap<>();
        if(e instanceof BussinessException){
            commonReturnType.setStatus("error");
            map.put("errCode", ((BussinessException) e).getErrCode().toString());
            map.put("errMsg", ((BussinessException) e).getErrMsg());
            commonReturnType.setData(map);
        }else{
            commonReturnType.setStatus("error");
            map.put("errCode", EmBussinessError.UNKNOWN_ERROR.getErrCode().toString());
            map.put("errMsg", EmBussinessError.UNKNOWN_ERROR.getErrMsg());
            commonReturnType.setData(map);
        }
        return commonReturnType;
    }
}
