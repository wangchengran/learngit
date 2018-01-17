package com.aran.dao;

import java.util.List;

import com.aran.entity.Phone;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

@DAO
public interface PhoneDAO {

	final String TABLE = "Phone";
	final String ALLFIELDS = "id ,name ,phonenumber ,age ,company ,email ,group_id";
	
		@ReturnGeneratedKeys
		@SQL("INSERT INTO $TABLE (id ,name ,phonenumber ,age ,company ,email ,group_id) VALUES("+ 
				":1.id" +
				",:1.name" +
				",:1.phonenumber" +
				",:1.age" +
				",:1.company" +
				",:1.email" +
				",:1.groupId)")
	public int add(Phone phone);
	
		@SQL("UPDATE $TABLE SET " 
				+ ":1.id"
				+ ",:1.name"
				+ ",:1.phonenumber"
				+ ",:1.age"
				+ ",:1.company"
				+ ",:1.email"
				+ ",:1.groupId"
		+ "where id = :1.id")
	public int updateById(Phone phone );
	
	@SQL("SELECT $ALLFIELDS FROM $TABLE WHERE id = :1")
	public Phone getById(int id);

	@SQL("DELETE FROM $TABLE WHERE id= :1")
	public int deleteById(int id);
	
	@SQL("SELECT COUNT(1) FROM $TABLE ")
	public int count();

	@SQL("SELECT $ALLFIELDS FROM $TABLE")
	public List<Phone> getALL();

	@SQL("SELECT $ALLFIELDS FROM $TABLE LIMIT :1,:2")
	public List<Phone> getAllByPage(int page, int size);

	@SQL("SELECT $ALLFIELDS FROM $TABLE WHERE name like'%##(:1)%' ")
	public List<Phone> getLikePhone(String str);

	@SQL("DELETE FROM $TABLE WHERE id in(:1)")
	public void deleteByIds(int ids[]);
	
	
}
