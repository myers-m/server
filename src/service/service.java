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
	
	public boolean run=true;
	
	public String tablestr1="";
	public String color="";
	
	public ArrayList<Integer> team=new ArrayList<Integer>();
	
	public service(Socket socket) throws SocketException {
		this.socket=socket;
		this.socket.setSoTimeout(5000);
		this.id=shuju.id;
		shuju.id+=1;
	}

	@Override
	public void run() {
		try {
			OutputStream out = this.socket.getOutputStream();
			InputStream in = this.socket.getInputStream();
			if(this.check(in,out)) {
			while(this.run) {
				this.set(out,shuju.Mytask.dosomething(this.get(in),this.id));
			}
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
	
	boolean check(InputStream in,OutputStream out) throws IOException{
		boolean res=shuju.Mycheck.runcheck(this.get(in));
		if(res) {
			this.set(out, "connect success");
		}
		else {
			this.set(out, "get out now");
		}
		return res;
	}
	
	String get(InputStream in) throws IOException {
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
	
	void set(OutputStream out,String need) throws IOException {
		//System.out.println(need);
	    String message = need;
		out.write(message.getBytes());
		out.flush();
	}
}
