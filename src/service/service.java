package service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import shuju.shuju;

public class service implements Runnable{
	public Socket socket;
	
	public boolean needtable=false;
	public int table=0;
	public int id=0;
	
	public boolean intable=false;
	
	public boolean change=false;
	
	public boolean run=false;
	
	public String tablestr1="";
	public String color="";
	
	public ArrayList<Integer> team=new ArrayList<Integer>();
	
	String getstr="";
	String setstr="";
	
	InputStream in;
	OutputStream out;
	
	public service(Socket socket) throws SocketException {
		this.socket=socket;
		this.socket.setSoTimeout(5000);
		this.id=shuju.id;
		shuju.id+=1;
	}

	@Override
	public void run() {
		try {
			this.out = this.socket.getOutputStream();
			this.in = this.socket.getInputStream();
			if(this.check()) {
				//if(this.checkversion()) {
					while(this.run) {
						this.getstr=this.get();
						this.setstr=shuju.Mytask.dosomething(this.getstr,this.id);
						this.set(this.setstr);
					}
				//}
			}
			in.close();
			out.close();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			this.run=false;
		}
		if(this.table!=0&&shuju.Mytable.findtable(this.table)!=null) {
			shuju.Mytable.findtable(this.table).rear(this.id);
		}
		shuju.list.remove(this);
	}
	
	boolean check() throws IOException{
		boolean res=true;
		this.get();
		res=shuju.Mycheck.runcheck(this.get());
		if(res) {
			this.set("connect success");
		}
		else {
			this.set("get out now");
		}
		return res;
	}
	
	boolean checkversion() throws IOException {
		boolean res=true;
		String need=shuju.Mycheck.runcheckversion(this.get());
		System.out.print(need);
		if(need.equals("true")) {
			res=true;
		}
		else if(need.equals("error")) {
			res=false;
		}
		else {
			int vs=Integer.valueOf(need.replace("false", ""));
			this.set( ""+(shuju.version-vs));
			System.out.print("this");
			while(true) {
				if(vs==shuju.version) {
					break;
				}
				vs+=1;
				if(this.get().equals("update ready")) {
				shuju.vm.Version(vs,out,this);
				}
				else {
					this.run=false;
					break;
				}
			}
			res=true;
		}
		return res;
	}
	
	public String get() throws IOException {
		byte[] need = new byte[1024];
		String res = "";
		
		int i=0;
		boolean run=true;
		while(run) {
			i = in.read(need);
			res=i!=-1?res+new String(need,0,i):res;
			if(res.contains("-0")) {
				res=res.replace("-0", "");
				run=false;
			}
		}
		return res;
	}
	
	void set(String need) throws IOException {
		System.out.println(need);
	    String message = need;
		out.write(message.getBytes());
		out.flush();
	}
}
