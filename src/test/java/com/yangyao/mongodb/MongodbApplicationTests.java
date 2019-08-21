package com.yangyao.mongodb;

import com.mongodb.client.result.DeleteResult;
import com.yangyao.mongodb.model.Department;
import com.yangyao.mongodb.model.User;
import com.yangyao.mongodb.service.DeptService;
import com.yangyao.mongodb.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    DeptService deptService;


    @Test
    public void contextLoads() {
        User user=new User();
        user.setId(1111);
        user.setName("lzkj");
        user.setNumber(110);
        userService.insert(user);
    }
    @Test
    public void find(){
        User user = userService.findBy((long) 2222);
        System.out.println(user.toString());
    }
    @Test
    public void update(){
        User user=new User();
        user.setId(1111);
        user.setNumber(911);
        user.setName("修改");
        userService.update(user);
    }
    @Test
    public void delete(){
        DeleteResult deleteResult = userService.delete(1111);
        if (deleteResult.getDeletedCount()==1){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }





    @Test
    public void ins(){
        Department department = new Department();
        department.setId(1);
        department.setDeptName("lzkj");
        //yyyy-MM-dd HH:mm:ss
        Date date = new Date();
        department.setCreateTime(date.getTime());
        deptService.insert(department);
    }
    @Test
    public void fin(){
        Department department = deptService.findById(1);
        long createTime = department.getCreateTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = simpleDateFormat.format(createTime);
        System.out.println(s);
    }
    @Test
    public void mod(){
        Department department = new Department();
        department.setId(1);
        department.setDeptName("联智科技");
        //yyyy-MM-dd HH:mm:ss
        Date date = new Date();
        department.setCreateTime(date.getTime());
        deptService.modify(department);
    }

    @Test
    public void test(){
        Class<User> userClass = User.class;
        System.out.println(userClass.toString());
    }
}
