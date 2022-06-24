import java.util.*;
import java.io.*;

public class Main{

	public static void main(String[] args){
		boolean userIsStaff = true;
		int currentUserID = 0;
		ArrayList<Navigation> pages = new ArrayList();

		//loading databases
		ArrayList<User> allUsers = RetrieveUsers();
		ArrayList<Appointment> allAppointments = RetrieveAppointments();
		ArrayList<Message> allMessages = RetrieveMessages();

		//creating arraylist of pages
		Navigation welcome = new Navigation(1, "Welcome", "Please log in or register.", new ArrayList<>(List.of(2, 3, 4)), true, true);
		pages.add(welcome);

		Navigation register = new Navigation(2, "Register", "Please enter the following information.", new ArrayList<>(List.of(1)), true, true);
		pages.add(register);

		Navigation login = new Navigation(3, "Log in", "Please enter your username and password.", new ArrayList<>(List.of(4)), true, true);
		pages.add(login);

		Navigation main_menu = new Navigation(4, "Main menu", "Please choose a navigation option.", new ArrayList<>(List.of(5, 8, 11, 12)), true, true);
		pages.add(main_menu);

		Navigation appointment_schedule = new Navigation(5, "Appointment schedule", "These are the appointment times.", new ArrayList<>(List.of(6, 7, 4)), true, true);
		pages.add(appointment_schedule);

		Navigation taken_appointments = new Navigation(6, "Scheduled appointments (Staff only)", "These are upcoming appointments.", new ArrayList<>(List.of(5, 4)), true, false);
		pages.add(taken_appointments);

		Navigation your_appointments = new Navigation(7, "Your appointments", "These are your upcoming appointments.", new ArrayList<>(List.of(5, 4)), false, true);
		pages.add(your_appointments);

		Navigation messages = new Navigation(8, "Messages", "View your inbox or send a message.", new ArrayList<>(List.of(9, 10)), true, true);
		pages.add(messages);

		Navigation inbox = new Navigation(9, "Inbox", "These are all your messages.", new ArrayList<>(List.of(8, 4)), true, true);
		pages.add(inbox);

		Navigation send_message = new Navigation(10, "Send a message", "Please enter your message.", new ArrayList<>(List.of(8, 4)), true, true);
		pages.add(send_message);

		Navigation records = new Navigation(11, "All records (Staff only)", "Here are the available patient records.", new ArrayList<>(List.of(4)), true, false);
		pages.add(records);

		Navigation your_records = new Navigation(12, "Your records", "These are the records we have on file for you.", new ArrayList<>(List.of(4)), false, true);
		pages.add(your_records);

		//opening welcome page
		welcome.openPage();
		int nextPageID = welcome.displayOptions(pages);

		//looping through navigation
		while(true){
			//logging out
			if(nextPageID == -1){
				System.out.println("You have logged out. Thanks for using Diamond Eyecare!");
				System.exit(0);
			}

			//returning to welcome page
			else if(nextPageID == 1){
				pages.get(nextPageID - 1).openPage();
				nextPageID = pages.get(nextPageID - 1).displayOptions(pages);
			}

			//registering account
			else if(nextPageID == 2){
				pages.get(nextPageID - 1).openPage();
				RegisterUser();
				nextPageID = pages.get(nextPageID - 1).displayOptions(pages);
			}

			//logging in
			else if(nextPageID == 3){
				pages.get(nextPageID - 1).openPage();
				if (LoginUser()) {
          nextPageID = pages.get(nextPageID - 1).displayOptions(pages);
        }
				else
        {
          //print error message
          System.out.println("\nUsername or password incorrect!\n");
        }
			}

			//main menu and messages
			else if(nextPageID == 4 || nextPageID == 8){
				pages.get(nextPageID - 1).openPage();
				nextPageID = pages.get(nextPageID - 1).displayOptions(pages);
			}

			//appointment schedule
			else if(nextPageID == 5){
				pages.get(nextPageID - 1).openPage();
				showAppointmentSchedule(allAppointments);

				if(userIsStaff == false){
					//scheduleAppointment();
				}

				nextPageID = pages.get(nextPageID - 1).displayOptions(pages);
			}

			//viewing all scheduled appointments
			else if(nextPageID == 6){
				if(userIsStaff){
					pages.get(nextPageID - 1).openPage();
					//showTakenAppointments();
				}

				else{
					System.out.println("Error: You do not have permission to view this page.");
				}

				nextPageID = pages.get(nextPageID - 1).displayOptions(pages);
			}

			//viewing your appointments
			else if(nextPageID == 7){
				if(userIsStaff == false){
					pages.get(nextPageID - 1).openPage();
					//showMyAppointments(currentUserID);
				}

				else{
					System.out.println("Error: You do not have permission to view this page.");
				}

				nextPageID = pages.get(nextPageID - 1).displayOptions(pages);
			}

			//viewing inbox
			else if(nextPageID == 9){
				pages.get(nextPageID - 1).openPage();
				//printInbox();
				nextPageID = pages.get(nextPageID - 1).displayOptions(pages);
			}

			//sending message
			else if(nextPageID == 10){
				pages.get(nextPageID - 1).openPage();
				//sendMessage();
				nextPageID = pages.get(nextPageID - 1).displayOptions(pages);
			}

			//viewing all patient records
			else if(nextPageID == 11){
				if(userIsStaff){
					pages.get(nextPageID - 1).openPage();
					showAllRecords(allUsers);
				}

				else{
					System.out.println("Error: You do not have permission to view this page.");
				}

				nextPageID = pages.get(nextPageID - 1).displayOptions(pages);
			}

			//viewing personal records
			else if(nextPageID == 12){
				if(userIsStaff == false){
					pages.get(nextPageID - 1).openPage();
					//showMyRecords();
				}

				else{
					System.out.println("Error: You do not have permission to view this page.");
				}

				nextPageID = pages.get(nextPageID - 1).displayOptions(pages);
			}
		}

	}

	public static ArrayList<User> RetrieveUsers(){

		/*Write function that will: 
		* Open a file called registeredUsers.csv
		* Take the data from the file and populate an ArrayList<Users> called registeredUsers*/

		ArrayList<User> userList = new ArrayList<>();
		//User tempUser = new User();

		try {
			//csv file containing data
			String strFile = "registeredUsers.csv";
			
			//create BufferedReader to read csv file
			BufferedReader br = new BufferedReader( new FileReader(strFile));
			String strLine = "";
			StringTokenizer st = null;
      int lineNumber = 1;

			//read comma separated file line by line
			while( (strLine = br.readLine()) != null) {	

        if (lineNumber > 1) {
          //break comma separated line using ","
				  st = new StringTokenizer(strLine, ",");

          User tempUser = new User();
				  tempUser.setUserID(Integer.parseInt(st.nextToken()));
				  tempUser.setName(st.nextToken());
				  tempUser.setUsername(st.nextToken());
				  tempUser.setPassword(st.nextToken());
				  tempUser.setEmail(st.nextToken());

          if (st.nextToken().equals("TRUE")) {
            //creates a staff object from tempUser object using copy constructor in Staff class
            Staff tempStaff = new Staff(tempUser);
            userList.add(tempStaff);
          }
          else {
            userList.add(tempUser);
          }

          //st.nextToken() reads userID first so we cannot check is_staff before reading the columns that come before it
          /*if(st.nextToken().equals("TRUE")){
						//String[] tempArray = new String[]{st.nextToken(), st.nextToken()}
					  User tempUser = new Staff();
					  tempUser.setUserID(Integer.parseInt(st.nextToken()));
					  tempUser.setName(st.nextToken());
					  tempUser.setUsername(st.nextToken());
					  tempUser.setPassword(st.nextToken());
					  tempUser.setEmail(st.nextToken());
					  userList.add(tempUser);
				  }

				  else if(st.nextToken().equals("FALSE")){
					  User tempUser = new User();
					  tempUser.setUserID(Integer.parseInt(st.nextToken()));
					  tempUser.setName(st.nextToken());
					  tempUser.setUsername(st.nextToken());
					  tempUser.setPassword(st.nextToken());
					  tempUser.setEmail(st.nextToken());
					  userList.add(tempUser);
				  }*/
        
        }		

        lineNumber++;	
			}
		}
		catch(Exception e) {
			System.out.println("Exception while reading csv file: " + e); 
		}

		return userList;
	}

	public static ArrayList<Appointment> RetrieveAppointments(){
		ArrayList<Appointment> appointmentList = new ArrayList<>();

		try {
			//csv file containing data
			String strFile = "appointments.csv";
			
			//create BufferedReader to read csv file
			BufferedReader br = new BufferedReader( new FileReader(strFile));
			String strLine = "";
			StringTokenizer st = null;
      int lineNumber = 1;

			//read comma separated file line by line
			while( (strLine = br.readLine()) != null) {	

        if (lineNumber > 1) {
          //break comma separated line using ","
				  st = new StringTokenizer(strLine, ",");

          Appointment tempApt = new Appointment();
				  tempApt.setWeekday(st.nextToken());
				  tempApt.setMonth(st.nextToken());
				  tempApt.setDate(Integer.parseInt(st.nextToken()));
				  tempApt.setYear(Integer.parseInt(st.nextToken()));
				  tempApt.setPatient(Integer.parseInt(st.nextToken()));
					tempApt.setIsTaken(Boolean.parseBoolean(st.nextToken()));

          appointmentList.add(tempApt);
        
        }		

        lineNumber++;	
			}
		}
		catch(Exception e) {
			System.out.println("Exception while reading csv file: " + e); 
		}

		return appointmentList;
	}

	public static ArrayList<Message> RetrieveMessages(){
		ArrayList<Message> messageList = new ArrayList<>();

		try {
			//csv file containing data
			String strFile = "messages.csv";
			
			//create BufferedReader to read csv file
			BufferedReader br = new BufferedReader( new FileReader(strFile));
			String strLine = "";
			StringTokenizer st = null;
      int lineNumber = 1;

			//read comma separated file line by line
			while( (strLine = br.readLine()) != null) {	

        if (lineNumber > 1) {
          //break comma separated line using ","
				  st = new StringTokenizer(strLine, ",");

          Message tempMsg = new Message();
				  tempMsg.setRecipient(Integer.parseInt(st.nextToken()));
				  tempMsg.setSender(Integer.parseInt(st.nextToken()));
				  tempMsg.setTitle(st.nextToken());
				  tempMsg.setBody(st.nextToken());

          messageList.add(tempMsg);
        
        }		

        lineNumber++;	
			}
		}
		catch(Exception e) {
			System.out.println("Exception while reading csv file: " + e); 
		}

		return messageList;
	}

	public static void RegisterUser(){
		/*
		Write function that will: 
		* Take user input for name, username, password, email, and last four digits of SSN
		* Check if name exists in patients.csv and checks if SSN matches the record
		* If they match, write the user to registeredUsers.csv
		* (Optional) Make sure username doesn't exist already
		*/

		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter name: ");
		String name = scan.nextLine();

		System.out.println("Enter username: ");
		String userName = scan.nextLine();

		System.out.println("Enter password: ");
		String passWord = scan.nextLine();

		System.out.println("Enter emailID: ");
		String emailID = scan.nextLine();

		System.out.println("Enter last four digits of SSN: ");
		int socialSecurity = Integer.parseInt(scan.nextLine());
		//String name = ""; String userName = ""; String passWord = ""; String emailID =""; 
		/*try { 
				x=new Scanner(new File(file));
				x.useDelimeter("[,\n");

				while(x.hasNext()&& !found){
					
				}
		}*/
	}

	public static boolean LoginUser(){
	/*
		Write function that will: 
		* Take user input for username and password
		* Check if they match the registered user from the registeredUsers ArrayList obtained with the RetrieveUsers function

		(the function is called on line 79, can be uncommented when you want to use the function)
	*/
  
		Scanner scan = new Scanner(System.in);
		String uname, password;

		System.out.println("Enter username: ");
		String inUname = scan.nextLine();
    
    System.out.println("Enter password: ");
		String inPassword = scan.nextLine();

    //check uname & pw with existing list of Users
    ArrayList<User> userList = RetrieveUsers();
    boolean isMatch = false;
    int i = 0;

    while ((!isMatch) && (i < userList.size())) {
      uname = userList.get(i).getUsername();
      password = userList.get(i).getPassword();

      //check
      //System.out.println(uname);

      if ((inUname.equals(uname)) && (inPassword.equals(inPassword))) {
        isMatch = true;
      }

      i++;
    }

		//clears console
		System.out.print("\033\143");

    return isMatch;
	}

	public static void showAppointmentSchedule(ArrayList<Appointment> aptList){
		for (int i = 0; i < aptList.size(); i++){
			System.out.println(aptList.get(i).printAppointment());
		}
		System.out.println('\n');
	}

	public static void showAllRecords(ArrayList<User> userList){
		for(int i = 2; i < userList.size(); i++){
			System.out.println(userList.get(i).printRecord());
		}
		System.out.println('\n');
	}
	
	/*
	public static void scheduleAppointment(){
		//prompt if would like to make an appointment
		//if yes, show appointments that are not taken and give menu options to select one
	}

	public static void showTakenAppointments(){
		//(staff only)
		//shows appointments that have been booked by patients
	}

	public static void showMyAppointments(){
		//(patient only)
		//shows current user's appointments
	}

	public static void printInbox(){
		//shows current user's received messages from messages.csv
	}

	public static void sendMessage(){
		//prompts for recipient with menu option (which dr to send message to)
		//prompts for title
		//prompts for body of message
		//adds to messages.csv
	}


	public static void showMyRecords(){
		//(patient only)
		//shows records of current user
	}
	*/


}

