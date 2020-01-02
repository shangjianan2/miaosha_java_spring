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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller("wjl")
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    HttpServletRequest httpServletRequest;

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

    @RequestMapping(path = "/getopt", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORM})
    @ResponseBody
    @CrossOrigin
    public CommonReturnType getOpt(@RequestParam("telephone") String telephone){
        Random random = new Random();
        Integer randomNum = 1000 + random.nextInt(9000);

        httpServletRequest.getSession().setAttribute(telephone, randomNum);

        System.out.printf("%d\r\n", randomNum);
        return CommonReturnType.create(randomNum, "success");
    }
}
