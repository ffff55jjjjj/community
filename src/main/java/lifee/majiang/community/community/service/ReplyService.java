package lifee.majiang.community.community.service;

import lifee.majiang.community.community.mapper.ReplyMapper;
import lifee.majiang.community.community.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    @Autowired
    ReplyMapper replyMapper;

    public void reply(Reply reply){
        replyMapper.reply(reply);
    }
}
