package com.aran.entity;

import java.util.List;

public class Page<T> {

	/**当前页码*/
	private int pageCount;
	/**总计*/
	private int total;
	/**每页大小*/
	private int size;
	/**首页*/
	private int from;
	/**末页*/
	private int to;
	/**电话本数据*/
	private List<T> phone;
	
	
	
	public Page(int pageCount, int total, int size, List<T> phone) {
		super();
		this.pageCount = pageCount;
		this.total = total;
		this.size = size;
		this.from = 0;
		this.to = total/size+1;
		this.phone = phone;
	}

	
	public Page() {
		super();
	}


	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<T> getPhone() {
		return phone;
	}

	public void setPhone(List<T> phone) {
		this.phone = phone;
	}


	@Override
	public String toString() {
		return "Page [pageCount=" + pageCount + ", total=" + total + ", size=" + size + ", from=" + from + ", to=" + to
				+ ", phone=" + phone + "]";
	}

}
