package service;

import servicetable.servicetable;
import shuju.shuju;

public class servicetask {
	
	public String dosomething(String need,int id) {
		return this.dostring(need,id);
	}
	
	String dostring(String need,int id) {
		String res = "";
		int pid=shuju.Myserver.findid(id);
		
		if(need.contains("quit")) {
			shuju.list.get(pid).run=false;
			res="quit success";
		}
		else if(need.contains("intable")&&shuju.list.get(pid).table!=0) {
			shuju.list.get(pid).intable=true;
			String[] need1=need.split(",");
			System.out.println(need1[0]);
			shuju.list.get(pid).color=need1[1];
			servicetable t=shuju.Mytable.findtable(shuju.list.get(pid).table);
			t.change();
			res=this.createstr(pid, t);
		}
		else if(shuju.list.get(pid).table!=0&&shuju.list.get(pid).intable&&need.contains("|")) {
			shuju.list.get(pid).tablestr1=id+"|"+need;
			res=this.createstr(pid, shuju.Mytable.findtable(shuju.list.get(pid).table));
		}
		else if(need.equals("connect")) {
			res="connecting";
		}
		else if(need.equals("create table")) {
			if(shuju.Mytable.createtable(id)) {
				res="create table success"+shuju.list.get(pid).table;
			}
			else {
				res="create table lose";
			}
		}
		else if(need.equals("join table")) {
			if(this.join(id,pid)) {
				res="join success"+shuju.list.get(pid).table;
			}
			else {
				res="join lose";
			}
		}
		else if(need.equals("out table")) {
			System.out.print("this123");
			if(this.rear(id,pid)) {
				res="out table success";
			}
			else {
				res="out table lose";
			}
		}
		return res;
	}
	
	boolean join(int id,int pid) {
		if(shuju.tablelist.size()>0&&shuju.list.get(pid).table==0) {
			int needid=(int)(Math.random()*shuju.tablelist.size());
			shuju.list.get(pid).table=shuju.tablelist.get(needid).id;
			shuju.tablelist.get(needid).Idlist.add(id);
			return true;
		}
		return false;
	}
	
	boolean rear(int id,int pid) {
		int tid=shuju.list.get(pid).table;
		System.out.println("***"+tid);
		if(tid==0) {
			return false;
		}
		shuju.list.get(pid).table=0;
		shuju.Mytable.findtable(tid).rear(id);
		return true;
	}
	
	String createstr(int pid,servicetable t){
		String value = "";
		String value1 = "";
		if(shuju.list.get(pid).change) {
			value1=this.createstate(pid,t);
		}
		for(int i=0;i<shuju.list.get(pid).team.size();i++) {
			//System.out.println(i);
			//System.out.println(t.Idlist.get(i));
			//System.out.println(pid);
			//System.out.println(shuju.list.size());
			int id=shuju.list.get(pid).team.get(i);
			if(value!="") {
				value+="_";
			}
			value+=shuju.list.get(shuju.Myserver.findid(id)).tablestr1;
		}
		value+="%"+value1;
		return value;
	}
	
	String createstate(int pid,servicetable t) {
		String value="";
		boolean bl=false;
		for(int i=0;i<t.Idlist.size();i++) {
			if(shuju.list.get(pid).id!=t.Idlist.get(i)&&!shuju.list.get(pid).team.contains(t.Idlist.get(i))) {
				if(shuju.list.get(shuju.Myserver.findid(t.Idlist.get(i))).intable) {
					shuju.list.get(pid).team.add(t.Idlist.get(i));
					if (!value.equals("")) {
						value+="%";
					}
					value+="in"+t.Idlist.get(i)+","+shuju.list.get(shuju.Myserver.findid(t.Idlist.get(i))).color;
				}
			}
		}
		for(int i=0;i<shuju.list.get(pid).team.size();i++) {
			if(!t.Idlist.contains(shuju.list.get(pid).team.get(i))) {
				if (!value.equals("")) {
					value+="%";
				}
				value+="out"+shuju.list.get(pid).team.get(i);
				shuju.list.get(pid).team.remove(i);
				i-=1;
			}
		}
		shuju.list.get(pid).change=bl;
		return value;
	}
}
