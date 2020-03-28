package lifee.majiang.community.community.mapper;

import lifee.majiang.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    public void create(Question question);

    @Select("SELECT * FROM QUESTION LIMIT #{offerset},#{sizes}")
    public List<Question> List(@Param("offerset") int offset,@Param("sizes") Integer PAGE_SIZES);

    @Select("SELECT COUNT(*) FROM QUESTION")
    Integer getPageCount();
}
