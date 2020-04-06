package lifee.majiang.community.community.controller;

import lifee.majiang.community.community.dto.QuestionDTO;
import lifee.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/question/{action}")
    public String question(@PathVariable("action")Integer id,
                           Model model) {
        QuestionDTO questionDTO = questionService.getQuestionById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
