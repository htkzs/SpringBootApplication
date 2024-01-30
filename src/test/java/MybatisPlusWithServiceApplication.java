import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.itheima.app.HelloSpringBootApplication;
import com.itheima.app.pojo.User;
import com.itheima.app.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.*;

@SpringBootTest(classes = HelloSpringBootApplication.class)
@RunWith(SpringRunner.class)
public class MybatisPlusWithServiceApplication {
    @Autowired
    private IUserService userService;
    @Test
    public void selectById(){
        User user = userService.getById(1l);
        System.out.println("user = " + user);
    }
    @Test
    public void queryUserByBatchIds(){
        List<User> users = userService.list();
        users.forEach(System.out::println);
    }
    @Test
    public void queryUserByBatchCondition(){
        List<User> users = userService.list(new QueryWrapper<User>().eq("age",27));
        users.forEach(System.out::println);
    }
    @Test
    public void InsertUser(){
        boolean result = userService.save(new User(6l, "alone", 27, "2060953137@qq,com"));
        System.out.println("保存的结果为："+result);
    }
    @Test
    public void InsertUserForBatch(){
        ArrayList<User> users = new ArrayList<>();
        for (long i = 7; i < 50; i++) {
            User user = new User(i, "alone"+i, 27, "2060953137@qq,com");
            users.add(user);
        }
        boolean result = userService.saveBatch(users,20);
        System.out.println("保存的结果为："+result);
    }
    /**
     * 插入的数据时先根据id查，如果存在就更改，不存在就新增
     */
    @Test
    public void insertOrUpdate(){
        User user = new User(5l, "alone", 27, "2060953137@qq,com");
        boolean result = userService.saveOrUpdate(user);
        System.out.println("保存的结果为："+result);
    }
    @Test
    public void insertOrUpdateBatch(){
        ArrayList<User> users = new ArrayList<>();
        User user = new User(5l, "alone", 27, "2060953137@qq,com");
        users.add(user);
        boolean result = userService.saveOrUpdateBatch(users,20);
        System.out.println("保存的结果为："+result);
    }

    /**
     * update set name=? and set=? and set age=? where id =?
     */
    @Test
    public void updateOptional(){
        boolean update = userService.update(new UpdateWrapper<User>().eq("id", 1l).set("name", "Tony").set("age", 19));
        System.out.println("数据更新的结果为："+update);
    }
    @Test
    public void updateByCondition(){
        //需要修改的user对象
        User user = new User(2l, "sunday", 22, "2060953137@qq,com");
        boolean update = userService.update(user,new UpdateWrapper<User>().eq("id",2l));
        System.out.println("数据更新的结果为："+update);
    }
    @Test
    public void deleteById(){
        int delete = userService.getBaseMapper().deleteById(49l);
        System.out.println("删除操作影响的行数"+delete);
    }

    /**
     * delete from where age=? and name=?
     */
    @Test
    public void deleteByCondition(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","alone48");
        map.put("age",27);
        int delete = userService.getBaseMapper().deleteByMap(map);
        System.out.println("删除操作影响的行数"+delete);
    }
    @Test
    public void deleteByIds(){
        int delete = userService.getBaseMapper().deleteBatchIds(Arrays.asList(42l,43l,44l));
        System.out.println("删除操作影响的行数"+delete);
    }
}
