package lifee.majiang.community.community.service;

import lifee.majiang.community.community.dto.QuestionDTO;
import lifee.majiang.community.community.mapper.QuestionMapper;
import lifee.majiang.community.community.mapper.UserMapper;
import lifee.majiang.community.community.model.Question;
import lifee.majiang.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    public List<QuestionDTO> List() {
        List<Question> questions = questionMapper.List();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
