import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.itheima.app.HelloSpringBootApplication;
import com.itheima.app.mapper.UserMapper;
import com.itheima.app.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mybatis plus的基本增删改查操作
 */
@SpringBootTest(classes = HelloSpringBootApplication.class)
@RunWith(SpringRunner.class)
public class MybatisPlusApplication {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void queryAllUser(){
        List<User> allUser = userMapper.selectList(null);
        allUser.forEach(System.out::println);
    }
    @Test
    public void queryUserById(){
        User user = userMapper.selectById(1l);
        System.out.println("user = " + user);
    }
    @Test
    public void queryUserByBatchId(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1l, 2l, 3l));
        users.forEach(System.out::println);
    }
    /**
     * select* from user where name=?
     */
    @Test
    public void queryUserByMap(){
        Map hashMap = new HashMap();
        hashMap.put("name", "Tom");
        List<User> users = userMapper.selectByMap(hashMap);
        users.forEach(System.out::println);
    }
    /**
     * select count(*) from user;
     */
    @Test
    public void queryUserByCount(){
        Map hashMap = new HashMap();
        hashMap.put("name", "Tom");
        Integer i = userMapper.selectCount(null);
        System.out.println("user表的总记录数为"+i);
    }
    /**
     * select count(*) from user where name=? and age>？;
     */
    @Test
    public void queryUserByCondition(){
        Map hashMap = new HashMap();
        hashMap.put("name", "Tom");
        Integer i = userMapper.selectCount(new QueryWrapper<User>().eq("name","Tom").gt("age",23));
        System.out.println("user表的总记录数为"+i);
    }
    @Test
    public void insertUser(){
        User user = new User(6l,"bob",25,"2060853137@qq.com");
        int insert = userMapper.insert(user);
        System.out.println("增加受影响的行数"+insert);

    }
    /**
     * 不指定Id插入数据mybatis plus将根据雪花算法生成一个id，可以指定为auto等其它方式
     */
    @Test
    public void insertUserWithOutId(){
        User user = new User("rose",27,"2060853137@weibo.com",0);
        int insert = userMapper.insert(user);
        System.out.println("增加受影响的行数"+insert);
    }
    @Test
    public void deleteUserById(){
        int i = userMapper.deleteById(41l);
        System.out.println("删除受影响的行数"+i);
    }
    /**
     * delete form user where name=?
     */
    @Test
    public void deleteUserByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","rose");
        int delete = userMapper.deleteByMap(map);
        System.out.println("删除受影响的行数"+delete);
    }
    /**
     * 批量删除 delete from user where id in(1l,2l,3l)
     */
    @Test
    public void deleteUserByListIds(){
        int delete = userMapper.deleteBatchIds(Arrays.asList(1l,2l,3l));
        System.out.println("删除受影响的行数"+delete);
    }
    @Test
    public void deleteUserByCondition(){
        int deleted = userMapper.delete(new UpdateWrapper<User>().eq("id", 4l));
        System.out.println("删除受影响的行数"+deleted);
    }
    @Test
    public void updateUser(){
        User user = new User(6l,"bob01",27,"2060853137@qq.com");
        int i = userMapper.updateById(user);
        System.out.println("修改受影响的行数"+i);
    }
    /**
     * update set name=? from user where id =?
     */
    @Test
    public void updateUserByCondition(){
        User user = new User(6l,"bob01",27,"2060853137@qq.com");
        int update = userMapper.update(user, new UpdateWrapper<User>().set("name", "bob04").eq("id",6l));
        System.out.println("修改受影响的行数"+update);
    }
}
