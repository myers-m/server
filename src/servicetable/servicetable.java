package servicetable;

import java.util.ArrayList;

import shuju.shuju;

public class servicetable {
	public ArrayList<Integer> Idlist;
	public int id=0;
	
	
	public servicetable(int id) {
		this.Idlist=new ArrayList<Integer>();
		this.Idlist.add(id);
		this.id=shuju.tableid;
		shuju.list.get(shuju.Myserver.findid(id)).table=this.id;
		shuju.tableid+=1;
	}
	
	public boolean join(int id,int tid) {
		if(shuju.list.get(id).table!=0||shuju.Mytable.findtable(tid)==null) {
			return false;
		}
		else if(shuju.Mytable.findtable(tid).Idlist.size()==10) {
			return false;
		}
		this.Idlist.add(id);
		return true;
	}
	
	public void rear(int id) {
		if(this.Idlist.contains(id)) {
			this.Idlist.remove((Object)id);
			this.change();
			if(this.Idlist.size()==0) {
				this.close();
			}
		}
	}
	
	public void change() {
		for(int i=0;i<this.Idlist.size();i++) {
			shuju.list.get(shuju.Myserver.findid(this.Idlist.get(i))).change=true;
		}
	}
	
	public void close() {
		shuju.tablelist.remove(this);
	}
}