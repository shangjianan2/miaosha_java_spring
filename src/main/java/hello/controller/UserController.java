package hello.controller;

import hello.dao.UserDOMapper;
import hello.dao.UserPasswordDOMapper;
import hello.dataObject.UserDO;
import hello.dataObject.UserPasswordDO;
import hello.error.BussinessException;
import hello.error.EmBussinessError;
import hello.response.CommonReturnType;
import hello.service.UserService;
import hello.service.imp.UserServiceImp;
import hello.service.model.UserModel;
import hello.service.model.UserModelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

@Controller("wjl")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUserById(@RequestParam("id") Integer id) throws BussinessException {
        UserModel userModel = userService.getUserModelById(id);
        if(userModel == null){
            throw new BussinessException(EmBussinessError.USER_NOT_EXIST);
        }
        return CommonReturnType.create(userModel);
    }

    @RequestMapping("/get2")
    @ResponseBody
    public CommonReturnType getUserById2(@RequestParam("id") Integer id) throws BussinessException {
        UserModelVO userModelVO = userService.getUserModelVOById(id);
        if(userModelVO == null){
            throw new BussinessException(EmBussinessError.USER_NOT_EXIST, "can not find user(get2)");
        }
        return CommonReturnType.create(userModelVO);
    }

    @RequestMapping(path = "/getopt", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORM})
    @ResponseBody
    public CommonReturnType getOpt(@RequestParam("telephone") String telephone){
        Random random = new Random();
        Integer randomNum = 1000 + random.nextInt(9000);

        httpServletRequest.getSession().setAttribute(telephone, randomNum);

        System.out.printf("%d\r\n", randomNum);
        return CommonReturnType.create(randomNum, "success");
    }

    @RequestMapping(path = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORM})
    @ResponseBody
    public CommonReturnType register(@RequestParam("telephone") String telephone,
                                     @RequestParam("opt") String opt,
                                     @RequestParam("name") String name,
                                     @RequestParam("gender") String gender,
                                     @RequestParam("age") String age,
                                     @RequestParam("password") String password) throws BussinessException {
        Object optSession = httpServletRequest.getSession().getAttribute(telephone);
        if(!com.alibaba.druid.util.StringUtils.equals(opt, String.valueOf(optSession))){
            throw new BussinessException(EmBussinessError.OPT_UNVALID);
        }

        UserModel userModel = new UserModel();
        userModel.setPassword(password);
        userModel.setAge(Integer.valueOf(age));
        userModel.setGender(Integer.valueOf(gender));
        userModel.setName(name);
        userModel.setRegisterMode("telephone");
        userModel.setTelephone(telephone);
        userModel.setThirdPartyId("");
        try{
            userService.setMapByUserModel(userModel);
        }catch (Exception e){
            throw new BussinessException(EmBussinessError.MYSQL_ERROR, e.getMessage());
        }
        return CommonReturnType.create(EmBussinessError.SUCCESS, "success");
    }

    @RequestMapping(path = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORM})
    @ResponseBody
    public CommonReturnType login(@RequestParam("telephone") String telephone,
                                  @RequestParam("password") String password) throws BussinessException {
        if(StringUtils.isEmpty(telephone) || StringUtils.isEmpty(password)){
            throw new BussinessException(EmBussinessError.USERNAME_OR_PASSWORD_WRONG, "name or password is null");
        }
        UserModel userModel = userService.verifyPassword(telephone, password);
        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        httpServletRequest.getSession().setAttribute("telephone", telephone);
        return CommonReturnType.create(userModel);
    }

    @RequestMapping("/session")
    @ResponseBody
    public CommonReturnType session(){
        Enumeration<String> enumeration = httpServletRequest.getSession().getAttributeNames();
        List<String> list = new ArrayList<>();
        while(enumeration.hasMoreElements()){
            String key = enumeration.nextElement();
            String val = String.valueOf(httpServletRequest.getSession().getAttribute(key));
            list.add(key + ": " + val);
        }
        String id = httpServletRequest.getSession().getId();
        list.add("id: " + id);
        return CommonReturnType.create(list);

    }
    @RequestMapping("/cookies")
    @ResponseBody
    public CommonReturnType cookies(){
        HashMap<String, String> map = new HashMap<>();
        for(Cookie cookie : httpServletRequest.getCookies()){
            map.put(cookie.getName(), cookie.getValue());
        }
        return CommonReturnType.create(map);
    }
}
