package com.huawei.StudentService.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huawei.StudentService.beans.QueryCondition;

public interface BaseMapper<T> {

	public void insert(T value);

	public void batchInsert(List<T> infos);

	public void delete(Integer id);

	public void batchDelete(List<Integer> ids);

	public void update(T value);

	public void batchUpdate(List<T> infos);

	public int getTotal();
	
	public T getInfoById(@Param("id") int id);

	public List<T> getInfosByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

	public List<T> getInfosByCondition(QueryCondition queryCondition);

}
