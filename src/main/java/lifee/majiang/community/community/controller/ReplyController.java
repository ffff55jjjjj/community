package lifee.majiang.community.community.controller;

import lifee.majiang.community.community.model.Reply;
import lifee.majiang.community.community.model.User;
import lifee.majiang.community.community.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @GetMapping("/reply")
    public String reply(HttpServletRequest request,
                        @RequestParam("questionId") Integer questionId,
                        @RequestParam("description") String description){
        System.out.println(questionId);
        System.out.println(description);
        Reply reply = new Reply();
        reply.setQuestionId(questionId);
        reply.setReplyText(description);
        reply.setReplyerId(((User)request.getSession().getAttribute("user")).getId());
        System.out.println(reply.getQuestionId()+":"+reply.getReplyerId()+":"+reply.getReplyText());
        replyService.reply(reply);
        return "redirect:/";
    }
}
