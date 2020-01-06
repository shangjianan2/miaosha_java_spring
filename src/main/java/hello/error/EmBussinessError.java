package hello.error;

public enum EmBussinessError implements CommonError{
    SUCCESS(10000, "SUCCESS"),
    UNKNOWN_ERROR(10002, "unknow error"),
    USER_NOT_EXIST(10001, "can not find user"),
    OPT_UNVALID(10003, "the opt is unvalid"),
    MYSQL_ERROR(10004, "mysql error"),
    USERNAME_OR_PASSWORD_WRONG(10005, "name or password is wrong");

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
