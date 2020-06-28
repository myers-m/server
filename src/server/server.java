package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import servercheck.check;
import servercontroller.controller;
import service.service;
import service.servicetask;
import servicetable.table;
import shuju.shuju;
import sql.sqlmanager;

public class server {
	ServerSocket Myserver=null;
	controller Mycontroller;
	
	int port=0;
	
	public server(int port) throws IOException {
		this.port=port;
		this.Mycontroller=new controller();
		shuju.Mycheck=new check();
		shuju.Mytable=new table();
		shuju.Mytask=new servicetask();
		shuju.Mysql=new sqlmanager();
		//shuju.vm=new VersionManager();
		//new Thread(shuju.vm).start();
		new Thread(this.Mycontroller).start();
	}
	
	public void create() throws IOException{
		this.Myserver=new ServerSocket(this.port);
		//System.out.print("create success");
		while(true) {
			Socket s=this.Myserver.accept();
			service service = new service(s);
			new Thread(service).start();
			shuju.list.add(service);
		}
	}
	
	public int findid(int id) {
		int res=0;
		for(int i=0;i<shuju.list.size();i++) {
			if(id==shuju.list.get(i).id) {
				res=i;
				break;
			}
		}
		return res;
	}
}
