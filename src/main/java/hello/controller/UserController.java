package hello.controller;

import hello.dao.UserDOMapper;
import hello.dataObject.UserDO;
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
public class UserController {
    @Autowired
    private UserServiceImp userServiceImp;

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUserById(@RequestParam("id") Integer id){
        UserModel userModel = userServiceImp.getUserModelById(id);
        return CommonReturnType.create(userModel);
    }

    @RequestMapping("/get2")
    @ResponseBody
    public CommonReturnType getUserById2(@RequestParam("id") Integer id){
        UserModelVO userModelVO = userServiceImp.getUserModelVOById(id);
        return CommonReturnType.create(userModelVO);
    }
}
