package lifee.majiang.community.community.mapper;

import lifee.majiang.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USER(name,account_id,token,gmt_create,gmt_modified,avatar_url) VALUES(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avartarUrl})")
    void insert(User user);

    @Select("SELECT * FROM USER WHERE TOKEN = #{token}")
    User findByToken(@Param("token") String token);
}


