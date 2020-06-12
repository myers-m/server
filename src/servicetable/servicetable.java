package servicetable;

import java.util.ArrayList;

import shuju.shuju;

public class servicetable {
	public ArrayList<Integer> Idlist;
	int id=0;
	
	public servicetable(int id) {
		this.Idlist=new ArrayList<Integer>();
		this.Idlist.add(id);
		this.id=shuju.tableid;
		shuju.list.get(shuju.Myserver.findid(id)).table=this.id;
		shuju.tableid+=1;
	}
	
	public boolean join(int id) {
		if(shuju.list.get(id).table!=0) {
			return false;
		}
		this.Idlist.add(id);
		return true;
	}
	
	public void rear(int id) {
		this.Idlist.remove(id);
		if(this.Idlist.size()==0) {
			this.close();
		}
	}
	
	public void close() {
		shuju.tablelist.remove(this);
	}
}