package com.brayden.video.mapper;

import com.brayden.video.entity.Account;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface AccountMapper {

    @InsertProvider(type = SqlProvider.class, method = "addAccount")
    @Options(useGeneratedKeys = true)
    public int addAccount(Account account);

    @SelectProvider(type = SqlProvider.class, method = "getAccountByName")
    public Account getAccountByName(String name);


    public class SqlProvider {
        public String addAccount() {
            SQL sql = new SQL();
            sql.INSERT_INTO("account");
            sql.VALUES("user_id", "#{userId}");
            sql.VALUES("name", "#{name}");
            sql.VALUES("credentials", "#{credentials}");
            sql.VALUES("created_time", "#{createdTime}");
            return sql.toString();
        }

        public String getAccountByName(String name) {
            SQL sql = new SQL();
            sql.SELECT("id");
            sql.SELECT("user_id");
            sql.SELECT("credentials");
            sql.SELECT("name");
            sql.SELECT("created_time");
            sql.SELECT("modified_time");
            sql.SELECT("flag");
            sql.FROM("account");
            sql.WHERE("name = #{arg0}");
            return sql.toString();
        }
    }
}
