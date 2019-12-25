package hello;

import hello.dao.UserDOMapper;
import hello.dataObject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"hello"})
@MapperScan("hello.dao")
@RestController
public class Applicationg{

    @Autowired
    private UserDOMapper userDOMapper;

    @RequestMapping("/wjl")
    public String wjl(){
        UserDO userDO = userDOMapper.selectByPrimaryKey(1);
        if(userDO == null){
            return "not exist";
        }else{
            return userDO.getTelephone();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Applicationg.class, args);
    }
}