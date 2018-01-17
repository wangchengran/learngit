package com.aran.dao;

import com.aran.entity.User;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

@DAO
public interface UserDAO {

	final String TABLE = "User";
	final String ALLFIELDS = "id ,username ,password ,realname ";
	
		@ReturnGeneratedKeys
		@SQL("INSERT INTO $TABLE (id ,username ,password ,realname ) VALUES("
				+ ":1.id "
				+ ",:1.username "
				+ ",:1.password "
				+ ",:1.realname)")
	public int add(User User);
	
		@SQL("UPDATE $TABLE SET " +
	    	       "id=:1.id" +
	    	       ",username= :1.username" +
	    	       ",password= :1.password" +
	    	       ",realname= :1.realname" +
	    	       "where id=:1.id")
	public int updateById(User User );
	
	@SQL("SELECT $ALLFIELDS FROM $TABLE WHERE id = :1")
	public User getById(int id);

	@SQL("SELECT $ALLFIELDS FROM $TABLE WHERE username= :1")
	public User getUserByUsername(String username);
	
	@SQL("DELETE FROM $TABLE WHERE id= :1")
	public int deleteById(int id);
	
	@SQL("SELECT COUNT(1) FROM $TABLE ")
	public int count();
	
	
}