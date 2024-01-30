import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.app.HelloSpringBootApplication;
import com.itheima.app.pojo.User;
import com.itheima.app.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试mybatis plus乐观锁机制
 */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@SpringBootTest(classes = HelloSpringBootApplication.class)
@RunWith(SpringRunner.class)
public class OptimisticLockApplication {
    @Autowired
    private IUserService userService;

    /**
     * 测试乐观锁支持updateById()方法
     */
    @Test
    public void OptimisticLockUpdateById(){
        User result = userService.getBaseMapper().selectById(43l);
        User user = new User(43l,"rose02",29,"2441844365@qq.com",0,result.getVersion());
        boolean update = userService.updateById(user);
        System.out.println("修改操作受影响的行数："+update);
    }

    /**
     * 模拟另一个线程携带版本号去操作相同的数据 UPDATE user SET name=?, age=?, email=?, version=? WHERE id=? AND version=? AND is_deleted=0
     */
    @Test
    public void OptimisticLockUpdateById01(){
        User user = new User(43l,"rose03",30,"2441844365@qq.com",0,2);
        boolean update = userService.updateById(user);
        System.out.println("修改操作受影响的行数："+update);
    }
    /**
     * 测试乐观锁支持update()方法 UPDATE user SET name=?, age=?, email=?, version=? WHERE is_deleted=0 AND (id = ? AND version = ?)
     */
    @Test
    public void OptimisticLockUpdate(){
        User result = userService.getById(43l);
        User user = new User(43l,"rose02",29,"2441844365@qq.com",0,result.getVersion());
        //通过乐观锁自旋的方式进行更新
        boolean update = userService.update(user, new QueryWrapper<User>().eq("id", 43l));
        System.out.println("修改操作受影响的行数："+update);
    }

    /**
     * 测试mybatis原生的乐观锁方法
     */
    @Test
    public void updateOptional(){
        User result = userService.getById(43l);
        System.out.println(result.getVersion());
        User user = new User(43l,"lisi",26,"18395476031@weibo.com",result.getVersion());
        userService.selectOrUpdateById(user);
    }

    /**
     * 测试不加条件的update导致的全表更新操作
     */
    @Test
    public void AllTableUpdate(){
        User result = userService.getById(43l);
        User user = new User(42l,"wangmazi",29,"17777777777@weibo.com",result.getVersion());
        //这里属于不加where条件的更新数据 如果不开启防止全表更新的拦截，将导致全表的更新 如果开启避免全表更新将产生  Cause: com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: Prohibition of table update operation
        userService.saveOrUpdate(user,null);
    }
}
