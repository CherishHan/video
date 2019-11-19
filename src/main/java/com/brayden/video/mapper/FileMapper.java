package com.brayden.video.mapper;

import com.brayden.video.entity.FileInfo;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface FileMapper {

    @InsertProvider(type = SqlProvider.class, method = "addFile")
    public void addFile(FileInfo fileInfo);

    public class SqlProvider{

        public String addFile(){
            SQL sql = new SQL();
            sql.INSERT_INTO("file");
            sql.VALUES("user_id", "#{userId}");
            sql.VALUES("path", "#{path}");
            sql.VALUES("created_time", "#{createdTime}");
            return sql.toString();
        }
    }
}
