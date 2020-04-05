package lifee.majiang.community.community.controller;

import lifee.majiang.community.community.dto.PageUtility;
import lifee.majiang.community.community.dto.QuestionDTO;
import lifee.majiang.community.community.mapper.UserMapper;
import lifee.majiang.community.community.model.User;
import lifee.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;
import java.util.List;

@Controller
public class IndexController {

    private final Integer PAGE_SIZES = 5;

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name="pageCount",required=false,defaultValue="1")Integer pageCount){
        if(pageCount<1) pageCount=1;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length!=0)
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        PageUtility pageUtility = new PageUtility();
        pageUtility.setCurrentPageCount(pageCount);
        pageUtility.setToltalPageCount((int)Math.ceil(questionService.getPageCount()*1.0f/PAGE_SIZES));
        model.addAttribute("pagecount", pageUtility);
        List<QuestionDTO> questionDTOList = questionService.List(pageCount,PAGE_SIZES);
        model.addAttribute("questions",questionDTOList);
        return "index";
    }
}
