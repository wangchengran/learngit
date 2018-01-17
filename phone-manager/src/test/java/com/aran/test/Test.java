package com.aran.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class Test {

	public static void main(String[] args) {
		List<String> list1=new ArrayList<String>();
//		for(int i=0;i<=5;i++){
//			list1.add("name"+i);
//		}
		List<String> list2=new ArrayList<String>();
		for(int i=0;i<=5;i++){
			list2.add("what"+i);
		}
		List<String> list3=new ArrayList<String>();
		for(int i=0;i<=5;i++){
			list3.add("name"+i);
		}
		List<String> list4=new ArrayList<String>();
		for(int i=0;i<=5;i++){
			list4.add("name"+i);
		}
		System.out.println(check(list1, list2, list3, list4));
		
//		System.out.println("".length());
	}
	
	public static String check(List<String> list1,List<String> list2,List<String> list3,List<String> list4){
		List<String> listttt=list2;
		
		for(String name:list1){
			System.out.println("1111111111111"+name);
		}
		for(String name:list2){
			System.out.println("2222222222222"+name);
		}
		Map<String, String> map= new HashMap<String, String>();
		String temp="";
		String result="";
		String tmp="";
		if(!CollectionUtils.isEmpty(list1)){
			for(String name:list1){
				map.put(name, "1");
			}
		}
		System.out.println("===============s");
		
		if(!CollectionUtils.isEmpty(list2)){
			for(String name:list2){
				if(map.get(name)==null){
					map.put(name,"2");
					System.out.println("1111111");
				}
				
				else {
					System.out.println("2222222");
					temp=map.put(name,"2");
					result+=name;
					tmp=map.get(name);
				}
			}
		}
		
		if(!StringUtils.isEmpty(result)){
			return temp+","+tmp+result;
		}
		
		if(!CollectionUtils.isEmpty(list3)){
			for(String name:list3){
				if(map.get(name)==null){
					map.put(name,"3");
				}else {
					temp=map.put(name,"3");
					result+=name;
					tmp=map.get(name);
				}
			}
		}
		
		if(!StringUtils.isEmpty(result)){
			return temp+","+tmp+result;
		}
		if(!CollectionUtils.isEmpty(list4)){
			for(String name:list4){
				if(map.get(name)==null){
					map.put(name,"4");
				}else {
					temp=map.put(name,"4");
					result+=name;
					tmp=map.get(name);
				}
			}
		}
		if(!StringUtils.isEmpty(result))
			return temp+","+tmp+result;
		
		return null;
	}
}
