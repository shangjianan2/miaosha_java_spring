package hello.validate;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {
    private boolean isError = false;
    private Map<String, String> errorMsgMap = new HashMap<>();

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    public String getErrorMsg(){
        return String.join(",", errorMsgMap.values());
    }

}
