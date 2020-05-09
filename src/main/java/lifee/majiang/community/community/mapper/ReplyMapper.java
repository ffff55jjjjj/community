package lifee.majiang.community.community.mapper;

import lifee.majiang.community.community.model.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {

    @Insert("INSERT INTO QUESTIONREPLY(QUESTIONID,REPLYERID,REPLYTEXT) VALUES(#{questionId},#{replyerId},#{replyText})")
    void reply(Reply reply);
}
