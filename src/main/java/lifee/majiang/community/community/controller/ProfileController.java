package lifee.majiang.community.community.controller;

import lifee.majiang.community.community.dto.PageUtility;
import lifee.majiang.community.community.dto.QuestionDTO;
import lifee.majiang.community.community.model.User;
import lifee.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {
    private final Integer PAGE_SIZES = 5;

    @Autowired
    QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable("action") String action, Model model,
                          @RequestParam(name="pageCount",required=false,defaultValue="1")Integer pageCount){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null)return "redirect:/";

        if(pageCount<1) pageCount=1;
        PageUtility pageUtility = new PageUtility();
        pageUtility.setCurrentPageCount(pageCount);
        pageUtility.setToltalPageCount((int)Math.ceil(questionService.getPageCountByUserId(user.getId())*1.0f/PAGE_SIZES));
        model.addAttribute("pagecount", pageUtility);
        List<QuestionDTO> questionDTOList = questionService.ListByUserId(user.getId(),pageCount,PAGE_SIZES);
        model.addAttribute("questions",questionDTOList);

        if("questions".equals(action)){//当url为/profile/questions
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        return "profile";
    }
}
