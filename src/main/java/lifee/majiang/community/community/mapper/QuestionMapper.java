package lifee.majiang.community.community.mapper;

import lifee.majiang.community.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    public void create(Question question);

    @Select("SELECT * FROM QUESTION LIMIT #{offerset},#{sizes}")
    public List<Question> List(@Param("offerset") int offset,@Param("sizes") Integer PAGE_SIZES);

    @Select("SELECT COUNT(*) FROM QUESTION")
    Integer getPageCount();

    @Select("SELECT COUNT(*) FROM QUESTION WHERE CREATOR = #{userId}")
    Integer getPageCountByUserId(@Param("userId") Integer userId);

    @Select("SELECT * FROM QUESTION WHERE CREATOR = #{userId} LIMIT #{offerset},#{sizes}")
    List<Question> ListUserId(@Param("userId") Integer userId,@Param("offerset") int offset,@Param("sizes") Integer page_sizes);

    @Update("UPDATE QUESTION SET TITLE = #{title}, DESCRIPTION = #{description}, TAG = #{tag} where ID = #{id}")
    void update(Question question);
}
