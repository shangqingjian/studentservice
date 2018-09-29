package com.huawei.StudentService.beans;

import java.util.ArrayList;
import java.util.List;

public class PageList<T> {

	private int page;

	private int pageSize;

	private int total;

	private int pages;

	private boolean hasNext = false;

	private boolean hasPre = false;

	private List<T> dataInfos = new ArrayList<T>();

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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

	public void setPages() {
		if (this.pageSize != 0) {
			this.pages = (int) Math.ceil(this.total * 1.0 / this.pageSize);
		}
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext() {
		if (this.page < this.total) {
			this.hasNext = true;
		}
	}

	public boolean isHasPre() {
		return hasPre;
	}

	public void setHasPre() {
		if (this.page > 1) {
			this.hasPre = true;
		}
	}

	public List<T> getDataInfos() {
		return dataInfos;
	}

	public void setDataInfos(List<T> dataInfos) {
		this.dataInfos = dataInfos;
	}
}
