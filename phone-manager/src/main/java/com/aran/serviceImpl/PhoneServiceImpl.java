package com.aran.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aran.dao.PhoneDAO;
import com.aran.entity.Page;
import com.aran.entity.Phone;
import com.aran.service.PhoneService;

@Service
public class PhoneServiceImpl implements PhoneService {

	@Autowired
	private PhoneDAO phoneDAO;
	
	@Override
	public List<Phone> getAll() {
		return phoneDAO.getALL();
	}

	@Override
	public int addPhone(Phone phone) {
		return phoneDAO.add(phone);
	}

	@Override
	public Page<Phone> getAllByPage(int num, int size,int count ,int maxNum) {
		if(size<=0)size=5;
		if(size>20)size=20;
		if(num>maxNum)num=maxNum;
		if(num<=0)num=1;
		Page<Phone> page=new Page<Phone>();
		page.setFrom(0);
		page.setTo(maxNum);
		page.setPageCount(num);
		page.setPhone(phoneDAO.getAllByPage((num-1)*size, size));
		page.setTotal(count);
		page.setSize(size);
		return page;
	}

	@Override
	public int count() {
		return phoneDAO.count();
	}

	@Override
	public List<Phone> getLikePhone(String str) {
		return phoneDAO.getLikePhone(str);
	}

	@Override
	public void deletePhoneByIds(String ids) {
		String idss[]=ids.split(",");
		int id [] =new int[idss.length];
		for(int i=0;i<idss.length;i++){
			id[i]=Integer.parseInt(idss[i]);
		}
		phoneDAO.deleteByIds(id);
	}

}
