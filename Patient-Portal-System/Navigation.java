import java.util.*;
import java.io.*;

public class Navigation{

	public int pageID;
	public String pageName;
	public String pageText;
	public ArrayList<Integer> options;
	public boolean isForStaff;
	public boolean isForPatients;

	public Navigation(){
		pageID = 0;
		pageName = "";
		pageText = "";
		options = new ArrayList<Integer>();
		isForStaff = false;
		isForPatients = false;
	}

	public Navigation(int pageID, String pageName, String pageText, ArrayList<Integer> options, boolean isForStaff, boolean isForPatients){
		this.pageID = pageID;
		this.pageName = pageName;
		this.pageText = pageText;
		this.options = options;
		this.isForStaff = isForStaff;
		this.isForPatients = isForPatients;
	}

	public void openPage(){
		Scanner input = new Scanner(System.in);

		System.out.print(this.getPageName() + "\n\n" + this.getPageText() + "\n\n");
	}

	public int displayOptions(ArrayList<Navigation> pages){
    Scanner input = new Scanner(System.in);

		for(int i = 0; i < this.options.size(); i++){
			int page = this.getOptions().get(i);
			System.out.println("[" + (i + 1) + "] " + pages.get(page - 1).getPageName());
		}

		System.out.println("\n[0] Quit");

		System.out.print("\nChoose selection: ");

		int selection = input.nextInt();

		if(selection == 0){
			//clears the console
			System.out.print("\033\143");
			return -1;
		}

		else{
			selection = (this.getOptions().get(selection - 1));

			//clears the console
			System.out.print("\033\143");

			return selection;
		}
	}

	public int getPageID() {
		return pageID;
	}

	public void setPageID(int pageID) {
		this.pageID = pageID;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageText() {
		return pageText;
	}

	public void setPageText(String pageText) {
		this.pageText = pageText;
	}

	public ArrayList<Integer> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<Integer> options) {
		this.options = options;
	}

	public boolean getIsForStaff() {
		return isForStaff;
	}

	public void setIsForStaff(boolean isForStaff) {
		this.isForStaff = isForStaff;
	}

	public boolean getIsForPatients() {
		return isForPatients;
	}

	public void setIsForPatients(boolean isForPatients) {
		this.isForPatients = isForPatients;
	}
	
}