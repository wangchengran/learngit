package com.aran.utils;

import java.util.List;

import com.aran.entity.Page;
import com.aran.entity.Phone;

public class PageUtils {
	

	public Page<Phone> getPage(List<Phone> list,int size,int total,int pageCount){
		if(list.isEmpty()||size<=0||total<=0||pageCount<=0){
			return null;
		}
		
		Page<Phone> page=new Page<Phone>(pageCount, total, size, list);
		
		return null;
	}
}
