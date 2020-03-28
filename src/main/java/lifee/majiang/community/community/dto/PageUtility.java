package lifee.majiang.community.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageUtility {
    private final Integer pagerHelperSizes = 5;
    private List<Integer> totalPageCount;
    private Integer lastPage;
    private Integer currentPageCount;


    public void setToltalPageCount(Integer pageCount){
        List<Integer> list = new ArrayList<Integer>();
        if(currentPageCount==null){
            for(int i=1;i<=pagerHelperSizes;i++){
                list.add(i);
            }
        }else{
            if(pageCount<pagerHelperSizes){
                for(int i=1;i<=pageCount;i++){
                    list.add(i);
                }
            }
            else if(currentPageCount<=3){
                for(int i=1;i<=pagerHelperSizes;i++){
                    list.add(i);
                }
            }else if(currentPageCount>=pageCount-2){
                for(int i=(int)pageCount - 4;i<=pageCount;i++){
                    list.add(i);
                }
            }else{
                for(int i =currentPageCount-2;i<=currentPageCount+2;i++){
                    list.add(i);
                }
            }
        }
        totalPageCount = list;
        lastPage = pageCount;
    }
}
