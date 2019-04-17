package cn.dyaoming.models;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.dyaoming.plugs.Page;


/**
 * <p>分页返回结果集</p>
 * @param <E> 实体类型
 * @author DYAOMING
 * @since 2019-04-17
 * @version V1.0
 */
public class PageResult<E> extends ApiResult {

	private static final long	serialVersionUID	= 1L;

	// 主表的当前页码
	private int					page;
	// 一页显示的条数
	private int					size;
	// 总条数，查询出来的
	private long				total;
	// 当前页的数据，当前页的数据是不确定的，因此需要使用到泛型
	private List<E>				rows				= new ArrayList<E>();



	/**
	 * 	默认构造器
	 */
	public PageResult() {
		super();
	}



	/**
	 * 	基础构造器
	 * @param cFlag boolean类型 结果标志
	 * @param cCode String类型 结果编码
	 */
	public PageResult(boolean cFlag, String cCode) {
		super(cFlag, cCode);
	}



	/**
	 * 	带结果说明的构造器
	 * @param cFlag boolean类型 结果标志
	 * @param cCode String类型 结果编码
	 * @param cMsg String类型 结果说明
	 */
	public PageResult(boolean cFlag, String cCode, String cMsg) {
		super(cFlag, cCode, cMsg);
	}



	/**
	 * 	通过构造函数将需要的数据传入，当前页，页面的大小，以及总共的条数记录，这个总的条数记录是从数据库里边
	 * @param page int类型 页码
	 * @param size int类型 每页尺寸
	 * @param total int类型 总行数
	 */
	public PageResult(int page, int size, int total) {
		this.page = page;
		this.size = size;
		this.total = total;
	}



	/**
	 * 	内容赋值构造函数
	 * @param list List<E>类型 内容
	 */
	public PageResult(List<E> list) {
		this(list, 8);
	}



	/**
	 *	内容赋值构造函数，带分页参数（实际上未使用到）
	 * @param list List<E>类型 内容
	 * @param navigatePages int类型 分页参数
	 */
	public PageResult(List<E> list, int navigatePages) {
		if (list instanceof Page) {
			Page page = (Page) list;
			// this.pageNum = page.getPageNum();
			// this.pageSize = page.getPageSize();

			this.page = page.getPageNum();
			this.rows = page;
			this.size = page.size();
			this.total = page.getTotal();
			// 由于结果是>startRow的，所以实际的需要+1
			// if (this.size == 0) {
			// this.startRow = 0;
			// this.endRow = 0;
			// } else {
			// this.startRow = page.getStartRow() + 1;
			// //计算实际的endRow（最后一页的时候特殊）
			// this.endRow = this.startRow - 1 + this.size;
			// }
		} else if (list instanceof Collection) {
			// this.pageNum = 1;
			// this.pageSize = list.size();

			this.page = 1;
			this.rows = list;
			this.size = list.size();
			this.total = list.size();
			// this.startRow = 0;
			// this.endRow = list.size() > 0 ? list.size() - 1 : 0;
		}
		/*
		 * if (list instanceof Collection) {
		 * this.navigatePages = navigatePages;
		 * //计算导航页
		 * calcNavigatepageNums();
		 * //计算前后页，第一页，最后一页
		 * calcPage();
		 * //判断页面边界
		 * judgePageBoudary();
		 * }
		 */
	}



	public int getPage() {
		return page;
	}



	public void setPage(int page) {
		this.page = page;
	}



	public int getSize() {
		return size;
	}



	public void setSize(int size) {
		this.size = size;
	}



	public long getTotal() {
		return total;
	}



	public void setTotal(long total) {
		this.total = total;
	}



	public List<E> getRows() {
		return rows;
	}



	public void setRows(List<E> rows) {
		this.rows = rows;
	}

}