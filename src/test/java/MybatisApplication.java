import com.itheima.app.HelloSpringBootApplication;
import com.itheima.app.mapper.UserMapper;
import com.itheima.app.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = HelloSpringBootApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MybatisApplication {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void queryAllUser(){
        List<User> allUser = userMapper.getAllUser();
        allUser.forEach(System.out::println);
    }
}
