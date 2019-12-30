package hello.error;

public interface CommonError {
    Integer getErrCode();
    String getErrMsg();
    CommonError setErrMsg(String errMsg);
}
