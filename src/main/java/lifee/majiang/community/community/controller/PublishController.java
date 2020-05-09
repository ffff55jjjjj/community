package lifee.majiang.community.community.controller;

import lifee.majiang.community.community.dto.QuestionDTO;
import lifee.majiang.community.community.mapper.QuestionMapper;
import lifee.majiang.community.community.model.Question;
import lifee.majiang.community.community.model.User;
import lifee.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
   public String publish(HttpServletRequest request, Model model){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        model.addAttribute("edit","发布");
        model.addAttribute("buttonName","发布");
        return "publish";
   }

   @GetMapping("/publish/{questionId}")
   public String edit(HttpServletRequest request,
                      @PathVariable("questionId") Integer questionId, Model model){
        QuestionDTO questionDTO = questionService.getQuestionById(questionId);
        model.addAttribute("title",questionDTO.getTitle());
        model.addAttribute("description",questionDTO.getDescription());
        model.addAttribute("tag",questionDTO.getTag());
        model.addAttribute("edit","编辑");
        model.addAttribute("buttonName","确认");
        model.addAttribute("id",questionDTO.getId());
        return "publish";
   }

   @PostMapping("/publish")
   public String doPublish(
           @RequestParam("title") String title,
           @RequestParam("description") String description,
           @RequestParam("tag") String tag,
           @RequestParam("id") Integer id,
           HttpServletRequest request,
           Model model){
        try{
           model.addAttribute("title",title);
           model.addAttribute("description",description);
           model.addAttribute("tag",tag);
           model.addAttribute("id",-1);
            if(title == null || title == "" ){
                model.addAttribute("error","标题不能为空！");
                return "publish";
            }
           if(description == null || description == "" ){
               model.addAttribute("error","问题补充不能为空！");
               return "publish";
           }
           if(tag == null || tag == "" ){
               model.addAttribute("error","标签不能为空！");
               return "publish";
           }

           User user = (User)request.getSession().getAttribute("user");
           if(user == null){
               model.addAttribute("error","请先登录，再发布！");
               return "publish";
           }
           Question question = new Question();
           question.setTitle(title);
           question.setDescription(description);
           question.setTag(tag);
           question.setCreator(user.getId());
           question.setGmtCreate(System.currentTimeMillis());
           question.setGmtModified(question.getGmtCreate());
           if(id != null){
               question.setId(id);
               questionMapper.update(question);
           }else{
               questionMapper.create(question);
           }

           return "redirect:/";
        }catch (Exception e){
            return "404";
        }
   }
}
