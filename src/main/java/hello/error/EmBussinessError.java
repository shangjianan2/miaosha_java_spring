package hello.error;

public enum EmBussinessError implements CommonError{
    SUCCESS(10000, "SUCCESS"),
    UNKNOWN_ERROR(10002, "unknow error"),
    USER_NOT_EXIST(10001, "can not find user");

    private Integer errCode;
    private String errMsg;

    EmBussinessError(Integer errCode, String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }


    @Override
    public Integer getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}