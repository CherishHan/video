package com.brayden.video.mapper;

import com.brayden.video.entity.User;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface UserMapper {

    @InsertProvider(type = SqlProvider.class, method = "addUser")
    @Options(useGeneratedKeys = true)
    public void addUser(User user);


    public class SqlProvider {

        public String addUser(){
            SQL sql = new SQL();
            sql.INSERT_INTO("user");
            sql.VALUES("name", "#{name}");
            sql.VALUES("password", "#{password}");
            sql.VALUES("email", "#{email}");
            return sql.toString();
        }
    }
}
