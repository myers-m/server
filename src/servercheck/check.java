package servercheck;

import java.util.Calendar;

import java.lang.String;

public class check {
	int time=0;
	String[] value=new String[3];
	Calendar calendar = Calendar.getInstance();
	
	public boolean runcheck(String value){
		if(!this.checktime()) {
			this.createvalue();
		}
		if (this.checkvalue(value)) {
			return true;
		}
		return false;
	}
	
	boolean checktime() {
		calendar.setTimeInMillis(System.currentTimeMillis());
		int time=calendar.get(Calendar.DAY_OF_YEAR)*1000000+calendar.get(Calendar.HOUR_OF_DAY)*10000+calendar.get(Calendar.MINUTE)*100+calendar.get(Calendar.SECOND);
		if(this.time!=time) {
			this.time=time;
			return false;
		}
		return true;
	}
	
	void createvalue() {
		this.value[0]=""+(this.time-1);
		this.value[1]=""+this.time;
		this.value[2]=""+(this.time+1);
		
	}
	
	boolean checkvalue(String value){
		System.out.println("****"+value);
		for(int i=0;i<this.value.length;i++) {
			System.out.println(this.value[i]);
			if(value.equals(this.value[i])) {
				return true;
			}
		}
		return false;
	}
}
