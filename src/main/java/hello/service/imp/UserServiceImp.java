package hello.service.imp;

import hello.dao.UserDOMapper;
import hello.dao.UserPasswordDOMapper;
import hello.dataObject.UserDO;
import hello.dataObject.UserPasswordDO;
import hello.service.UserService;
import hello.service.model.UserModel;
import hello.service.model.UserModelVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserDOMapper userDOMapper;
    @Autowired
    UserPasswordDOMapper userPasswordDOMapper;

    @Override
    public UserModel getUserModelById(Integer id) {
        UserModel userModel = new UserModel();
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(id);
        BeanUtils.copyProperties(userDO, userModel);
        userModel.setPassword(userPasswordDO.getPassword());
        return userModel;
    }

    @Override
    public UserModelVO getUserModelVOById(Integer id) {
        UserModelVO userModelVO = new UserModelVO();
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(userDO, userModelVO);
        return userModelVO;
    }
}
