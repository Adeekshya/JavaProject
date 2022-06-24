public class Appointment{

	public String weekday;
	public String month;
	public int date;
	public int year;
	public int patient;
	public boolean isTaken;

	public String printAppointment(){
		String text = (this.getWeekday() + ", " + this.getMonth() + ' ' + this.getDate() + ", " + this.getYear());
		return text;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPatient() {
		return patient;
	}

	public void setPatient(int patient) {
		this.patient = patient;
	}

	public boolean getIsTaken() {
		return isTaken;
	}

	public void setIsTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}
	
}