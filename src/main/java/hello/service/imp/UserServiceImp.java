package hello.service.imp;

import hello.dao.UserDOMapper;
import hello.dao.UserPasswordDOMapper;
import hello.dataObject.UserDO;
import hello.dataObject.UserPasswordDO;
import hello.error.BussinessException;
import hello.error.EmBussinessError;
import hello.service.UserService;
import hello.service.model.UserModel;
import hello.service.model.UserModelVO;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if(userDO == null || userPasswordDO == null) return null;
        BeanUtils.copyProperties(userDO, userModel);
        userModel.setPassword(userPasswordDO.getPassword());
        return userModel;
    }

    @Override
    public UserModelVO getUserModelVOById(Integer id) {
        UserModelVO userModelVO = new UserModelVO();
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if(userDOMapper == null) return null;
        BeanUtils.copyProperties(userDO, userModelVO);
        return userModelVO;
    }

    @Override
    public void setMapByUserModel(UserModel userModel){
        UserDO userDO = convertUserModelTOUserDO(userModel);
        userDOMapper.insertSelective(userDO);
        userModel.setId(userDO.getId());
        UserPasswordDO  userPasswordDO = convertUserModelTOUserPasswordDO(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
    }

    @Override
    public UserModel verifyPassword(String telephone, String password_web) throws BussinessException {
        UserDO userDO = userDOMapper.selectByTelephone(telephone);
        if(userDO == null){
            throw new BussinessException(EmBussinessError.USER_NOT_EXIST);
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        if(!com.alibaba.druid.util.StringUtils.equals(userPasswordDO.getPassword(), password_web)){
            throw new BussinessException(EmBussinessError.USERNAME_OR_PASSWORD_WRONG);
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        userModel.setPassword(userPasswordDO.getPassword());
        return userModel;
    }

    public UserDO convertUserModelTOUserDO(UserModel userModel){
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
    }
    public UserPasswordDO convertUserModelTOUserPasswordDO(UserModel userModel){
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setPassword(userModel.getPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }
}
