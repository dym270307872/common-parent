package cn.dyaoming.plugs;


import java.io.Serializable;


/**
 * <p>
 * 分页信息
 * </p>
 * 
 * @author DYAOMING
 * @since 2020-05-28
 * @version 0.0.4
 */
public class PageInfo implements Serializable {

    /**
     * 总条目数
     */
    private int total;
    private int pages; // 总页数
    private int pageNum; // 当前的页码
    private int[] pageSizes = new int[] { 5, 10, 15, 20, 30, 40, 50 }; // select选项设置：条/页
    private int pageSize; // 每页显示条目个数
    private int navigateFirstPage; // 上一页
    private int navigateLastPage; // 下一页
    private boolean lastPage; // 是最后一页？
    private boolean firstPage; // 是第一页？
    private boolean hasNextPage; // 有下一页？
    private boolean hasPreviousPage; // 有上一页？

    private Integer startRowNum;
    private Integer endRowNum;



    /**
     * <p>
     * 计算分页参数
     * </p>
     */
    public void math() {
        if (pageNum <= 1) {
            pageNum = 1;
            firstPage = true;
        } else {
            hasPreviousPage = true;
        }
        if (pageSize < 0) {
            pageSize = 10;
        }
        pages = total / pageSize + 1;

        if (pageNum >= pages) {
            pageNum = pages;
            lastPage = true;
        } else {
            hasNextPage = true;
        }

        if (hasPreviousPage) {
            navigateFirstPage = pageNum - 1;
        }
        if (hasNextPage) {
            navigateLastPage = pageNum + 1;
        }

        startRowNum = (pageNum - 1) * pageSize;
        endRowNum = pageNum * pageSize - 1;
        if (endRowNum > total) {
            endRowNum = total;
        }
    }



    public int getTotal() {
        return total;
    }



    public void setTotal(int total) {
        this.total = total;
    }



    public int getPages() {
        return pages;
    }



    public void setPages(int pages) {
        this.pages = pages;
    }



    public int getPageNum() {
        return pageNum;
    }



    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }



    public int[] getPageSizes() {
        return pageSizes;
    }



    public void setPageSizes(int[] pageSizes) {
        this.pageSizes = pageSizes;
    }



    public int getPageSize() {
        return pageSize;
    }



    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }



    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }



    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }



    public int getNavigateLastPage() {
        return navigateLastPage;
    }



    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }



    public boolean isLastPage() {
        return lastPage;
    }



    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }



    public boolean isFirstPage() {
        return firstPage;
    }



    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }



    public boolean isHasNextPage() {
        return hasNextPage;
    }



    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }



    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }



    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }



    public Integer getStartRowNum() {
        return startRowNum;
    }



    public void setStartRowNum(Integer startRowNum) {
        this.startRowNum = startRowNum;
    }



    public Integer getEndRowNum() {
        return endRowNum;
    }



    public void setEndRowNum(Integer endRowNum) {
        this.endRowNum = endRowNum;
    }

}
