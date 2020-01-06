package hello.service;

import hello.error.BussinessException;
import hello.service.model.UserModel;
import hello.service.model.UserModelVO;

public interface UserService {

    UserModel getUserModelById(Integer id);
    UserModelVO getUserModelVOById(Integer id);
    void setMapByUserModel(UserModel userModel);
    UserModel verifyPassword(String telephone, String password_web) throws BussinessException;
}
