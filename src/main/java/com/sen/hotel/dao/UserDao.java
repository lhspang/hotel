package com.sen.hotel.dao;

import com.sen.hotel.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("userDao")
public interface UserDao {
    @Insert("insert into tb_user(email,phone,nickName,password) values(#{email},#{phone},#{nickName},#{password})")
    boolean insert(User user);

    @Update("update tb_user set email=#{email},nickName=#{nickName}," +
            "sex=#{sex},qq=#{qq},address=#{address} where id=#{id}")
    boolean update(User user);

    @Delete("delete from tb_user where id=#{id}")
    boolean delete(int id);

    @Update("update tb_user set password=#{password} where id=#{id}")
    boolean updatePassword(User user);

    @Select({"<script>",
            "select * from tb_user ",
            "<where>",
            "<if test='phone !=null'> phone=#{phone} </if>",
            "<if test='email !=null'>and email=#{email} </if>",
            "</where>",
            "</script>"})
    User selectByPhoneAndEmail(Map<String,Object> map);

    @Select("select * from tb_user where id=#{id}")
    User selectById(int id);
}
