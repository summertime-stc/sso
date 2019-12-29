package com.example.sso.login.Dao;

import com.example.sso.login.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * user
 * @author 大狼狗
 * @date 2019/12/28
 */
@Mapper
@Repository
public interface UserDao{

    /**
     * [新增]
     * @author 大狼狗
     * @date 2019/12/28
     **/
    int insert(User user);

    /**
     * [刪除]
     * @author 大狼狗
     * @date 2019/12/28
     **/
    int delete(int id);

    /**
     * [更新]
     * @author 大狼狗
     * @date 2019/12/28
     **/
    int update(User user);

    /**
     * [查询] 根据主键 id 查询
     * @author 大狼狗
     * @date 2019/12/28
     **/
    User load(int id);

    /**
     * [查询] 分页查询
     * @author 大狼狗
     * @date 2019/12/28
     **/
    List<User> pageList(int offset,int pagesize);

    /**
     * [查询] 分页查询 count
     * @author 大狼狗
     * @date 2019/12/28
     **/
    int pageListCount(int offset,int pagesize);

    User existuser(String username,String password);

}
