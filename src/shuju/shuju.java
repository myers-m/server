package shuju;

import java.util.ArrayList;

import server.server;
import servercheck.check;
import service.service;
import service.servicetask;
import servicetable.servicetable;
import servicetable.table;

public class shuju {
	public static ArrayList<service> list=new ArrayList<service>();
	public static ArrayList<servicetable> tablelist=new ArrayList<servicetable>();
	
	public static server Myserver;
	public static check Mycheck;
	public static table Mytable;
	public static servicetask Mytask;
	
	public static int tableid=0;
	public static int id=0;
}
