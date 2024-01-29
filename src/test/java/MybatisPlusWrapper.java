import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.app.HelloSpringBootApplication;
import com.itheima.app.pojo.User;
import com.itheima.app.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest(classes = HelloSpringBootApplication.class)
@RunWith(SpringRunner.class)
public class MybatisPlusWrapper {
    @Autowired
    private IUserService userService;

    /**
     * select * from user where name like %?% and age>23 or email is not null;
     */
    @Test
    public void queryUserByCondition(){
        List<User> users = userService.getBaseMapper().selectList(new QueryWrapper<User>().like("name", 'o')
                .gt("age", 20).or().isNull("email"));
        users.forEach(System.out::println);
    }

    /**
     * select * from user where (name like %?% and age>23) or email is not null;
     */
    @Test
    public void queryUserByLambda(){
        BaseMapper<User> baseMapper = userService.getBaseMapper();
        List<User> users = baseMapper.selectList(new QueryWrapper<User>()
                .and(item -> {
                    item.like("name", 'o').gt("age", 20);
                }).or(item -> {
                    item.isNull("email");
                }));
        users.forEach(System.out::println);
    }

    /**
     * select * from user ORDER BY age asc,id DESC;
     */
    @Test
    public void sort(){
        List<User> users = userService.getBaseMapper().selectList(new QueryWrapper<User>()
                .orderByAsc("age").orderByDesc("id"));
        users.forEach(System.out::println);
    }
    /**
     * select id,name,age from user where (name like %?% and age>23) or email is not null;
     */
    @Test
    public void queryUserByLambdaWithSelect(){
        BaseMapper<User> baseMapper = userService.getBaseMapper();
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .and(item -> {
                    item.like("name", 'o').gt("age", 20);
                }).or(item -> {
                    item.isNull("email");
                }).select("id", "name", "age");
        List<Map<String, Object>> maps = baseMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }
    /**
     * select * from user where id in（select id from user where id<6）子查询
     */
    @Test
    public void queryUser(){
        BaseMapper<User> baseMapper = userService.getBaseMapper();
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        QueryWrapper<User> wrapper = queryWrapper.inSql("id", "select id from user where id<6").select("name","age","email");
        List<Map<String, Object>> maps = baseMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }
    /**
     * 动态sql查询 条件拼装 SELECT id,name,age,email,is_deleted FROM user WHERE is_deleted=0 AND (name = ? AND age = ? AND email = ?);
     */
    @Test
    public void queryByDynamicSql(){
        String name = "Tom";
        Integer age = 23;
        String email = "2060953137@qq,com";
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(!StringUtils.isBlank(name)){
            wrapper.eq("name",name);
        }
        if(age!=null && age>0){
            wrapper.eq("age",age);
        }
        if(!StringUtils.isBlank(email)){
            wrapper.eq("email",email);
        }
        List<User> users = userService.getBaseMapper().selectList(wrapper);
        users.forEach(System.out::println);
    }


    @Test
    public void queryDynamicSqlByWrapper(){
        String name = "Tom";
        Integer age = 28;
        String email = "test3@baomidou.com";
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(!StringUtils.isBlank(name),"name",name).eq((age!=null && age>0),"age",age)
                .eq((!StringUtils.isBlank(email)),"email",email);
        List<User> users = userService.getBaseMapper().selectList(wrapper);
        users.forEach(System.out::println);
    }
    /**
     * 分页测试 mysql的页默认是从第一页开始的 SELECT id,name,age,email,is_deleted FROM user WHERE is_deleted=0 LIMIT 5
     */
    @Test
    public void queryPage(){
        Page<User> userPage = new Page<>(1,5);
        Page<User> page = userService.getBaseMapper().selectPage(userPage,new QueryWrapper<>());
        List<User> records = page.getRecords();
        records.forEach(System.out::println);
        System.out.println("page.getPages() = " + page.getPages()); //总页数
        System.out.println("page.getCurrent() = " + page.getCurrent());
        System.out.println("page.getRecords() = " + page.getRecords());
        System.out.println("page.getTotal() = " + page.getTotal());
        System.out.println("page.getSize() = " + page.getSize());
        System.out.println("page.hasPrevious() = " + page.hasPrevious()); //是否有前一页
        System.out.println("page.hasNext() = " + page.hasNext()); //是否有后一页
    }

}
