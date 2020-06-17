package versionmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import service.service;


/*
 * 管理版本
 * 当所需版本不在内存时读取
 * 一个版本文件只保存1分钟，一分钟不被第二个玩家读取则释放
 */
public class VersionManager implements Runnable {
	HashMap<Integer, byte[]> version=new HashMap<Integer, byte[]>();
	HashMap<Integer, Integer> time=new HashMap<Integer, Integer>();
	

	public void Version(int version,OutputStream out,service service) throws IOException {
		byte[] res;
		if(this.version.containsKey(version)) {
			res=this.version.get(version);
			this.time.replace(version, 0);
		}
		else {
			res=doing(version);
			this.version.put(version, res);
			this.time.put(version, 0);
		}
		this.doout(out,service,res);
	}
	
	
	byte[] doing(int version) throws IOException {
		File file=new File("D://"+version+".bytes");
		InputStream in=new FileInputStream(file);
		byte[] bytes=new byte[4096];
		int len=0;
		ArrayList<Byte> need=new ArrayList<Byte>();
		while ((len=in.read(bytes))!=-1) {
			//System.out.println(len);
			for(int i=0;i<len;i++) {
				need.add(bytes[i]);
			}
		}
		byte[] res=new byte[need.size()];
		for(int i=0;i<need.size();i++) {
			res[i]=need.get(i);
		}
		in.close();
		return res;
	}
	
	void doout(OutputStream out,service service,byte[] need) throws IOException {
		out.write((""+need.length).getBytes());
		out.flush();
		if(service.get().equals("update begin")) {
			out.write(need,0,need.length);
			out.flush();
		}
	}


	@Override
	public void run() {
		while (true) {
			try {
				for(int key:this.time.keySet()) {
					int time=this.time.get(key);
					if(time>=50) {
						//System.out.println("remove");
						this.time.remove(key);
					}
					else {
						this.time.replace(key, time+10);
					}
				}
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
