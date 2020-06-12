package servicetable;

import shuju.shuju;

public class table {
	
	public boolean createtable(int id){
		if(shuju.list.get(shuju.Myserver.findid(id)).table==0) {
			shuju.tablelist.add(new servicetable(id));
			return true;
		}
		else {
			return false;
		}
	}
	
	public servicetable findtable(int id) {
		servicetable res=null;
		for (int i = 0; i < shuju.tablelist.size(); i++) {
			if(shuju.tablelist.get(i).id==id) {
				res=shuju.tablelist.get(i);
				break;
			}
		}
		return res;
	}
}
