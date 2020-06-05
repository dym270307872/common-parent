package cn.dyaoming.plugs;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



/**
 * <p>分页对象</p>
 * 
 * @author DYAOMING
 * @since 2020-05-28
 * @version 0.0.4
 */
public class Page<T> implements Serializable {

    private PageInfo pageInfo;

    private List<T> rows;



    /**
     * 静态分页方法
     * @param rows 待分页数据
     * @param pageSize 页面数量
     * @param pageNum 页码
     * @return 分页对象
     */
    public static <T> Page<T> math(List<T> rows, Integer pageSize, Integer pageNum) {
        Page<T> page = new Page<>();

        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(rows.size());
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);

        pageInfo.math();

        page.setPageInfo(pageInfo);
        page.setRows(new ArrayList<>(rows.subList(pageInfo.getStartRowNum(), pageInfo.getEndRowNum() - pageInfo.getStartRowNum())));
        return page;
    }



    public PageInfo getPageInfo() {
        return pageInfo;
    }



    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }



    public List<T> getRows() {
        return rows;
    }



    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}
