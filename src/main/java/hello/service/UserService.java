package hello.service;

import hello.service.model.UserModel;
import hello.service.model.UserModelVO;

public interface UserService {

    UserModel getUserModelById(Integer id);
    UserModelVO getUserModelVOById(Integer id);
}
