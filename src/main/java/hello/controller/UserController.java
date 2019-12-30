package hello.controller;

import hello.dao.UserDOMapper;
import hello.dataObject.UserDO;
import hello.error.BussinessException;
import hello.error.EmBussinessError;
import hello.response.CommonReturnType;
import hello.service.UserService;
import hello.service.imp.UserServiceImp;
import hello.service.model.UserModel;
import hello.service.model.UserModelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("wjl")
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserServiceImp userServiceImp;

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUserById(@RequestParam("id") Integer id) throws BussinessException {
        UserModel userModel = userServiceImp.getUserModelById(id);
        if(userModel == null){
            throw new BussinessException(EmBussinessError.USER_NOT_EXIST);
        }
        return CommonReturnType.create(userModel);
    }

    @RequestMapping("/get2")
    @ResponseBody
    public CommonReturnType getUserById2(@RequestParam("id") Integer id) throws BussinessException {
        UserModelVO userModelVO = userServiceImp.getUserModelVOById(id);
        if(userModelVO == null){
            throw new BussinessException(EmBussinessError.USER_NOT_EXIST, "can not find user(get2)");
        }
        return CommonReturnType.create(userModelVO);
    }
}
