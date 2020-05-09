package lifee.majiang.community.community.model;

import lombok.Data;

@Data
public class Reply {
    private Integer id;
    private Integer questionId;
    private Integer replyerId;
    private String replyText;
}
