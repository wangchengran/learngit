package com.aran.service;

import java.util.List;

import com.aran.entity.Page;
import com.aran.entity.Phone;

public interface PhoneService {

	List<Phone> getAll();

	int addPhone(Phone phone);
	
	Page<Phone> getAllByPage(int num,int size, int count, int maxNum);
	
	List<Phone> getLikePhone(String str);
	
	int count();

	void deletePhoneByIds(String ids);

}
