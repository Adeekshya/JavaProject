public class Staff extends User{
	//mention that staff has additional functionalities

  public Staff(User u) {
    this.userID = u.userID;
    this.name = u.name;
    this.username = u.username;
    this.password = u.password;
    this.email = u.email;
  }
}