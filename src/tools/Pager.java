package tools;

import java.util.List;

public class Pager<E> {
	
	private List<E> content;
	
	private Integer pageNo;
	
	private Integer pageCount;
	
	//totalCount总记录数
	public void setPageCountByTotalCountAndPageSize(int totalCount,int pageSize){
		
		//获取总页数
		//计算总页数
		pageCount = totalCount;
		
//		//说明整除
//		if(totalCount%pageSize!=0){
//			pageCount++;
//		}
	}
	
	public List<E> getContent() {
		return content;
	}

	public void setContent(List<E> content) {
		this.content = content;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

}
