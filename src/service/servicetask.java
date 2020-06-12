package service;

import shuju.shuju;

public class servicetask {
	
	public String dosomething(String need,int id) {
		return this.dostring(need,id);
	}
	
	String dostring(String need,int id) {
		String res = "";
		if(need.contains("quit")) {
			shuju.list.get(shuju.Myserver.findid(id)).run=false;
			res="quit success";
		}
		else if(need.equals("connect")) {
			res="connecting";
		}
		else if(need.equals("create table")) {
			if(shuju.Mytable.createtable(id)) {
				res="create table success";
			}
			else {
				res="create table lose";
			}
		}
		else if(need.equals("out table")) {
			if(this.rear(id)) {
				res="out table success";
			}
			else {
				res="out table lose";
			}
		}
		return res;
	}
	
	boolean join() {
		return true;
	}
	
	boolean rear(int id) {
		int pid=shuju.Myserver.findid(id);
		int tid=shuju.list.get(pid).table;
		if(tid==0) {
			return false;
		}
		shuju.list.get(pid).table=0;
		shuju.Mytable.findtable(tid).rear(id);
		return true;
	}
}
