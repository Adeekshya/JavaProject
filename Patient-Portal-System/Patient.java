import java.util.*;

public class Patient extends User{

	public int age;
	public int phoneNumber;
	public int lastFourSSN;
	public char gender;
	public ArrayList<String> prescriptions;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getLastFourSSN() {
		return lastFourSSN;
	}

	public void setLastFourSSN(int lastFourSSN) {
		this.lastFourSSN = lastFourSSN;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public ArrayList<String> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(ArrayList<String> prescriptions) {
		this.prescriptions = prescriptions;
	}

}