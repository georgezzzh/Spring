package spittr;
public class Spitter {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    public void setFirstName(String firstName){this.firstName=firstName;}
    public void setLastName(String lastName){this.lastName=lastName;}
    public void setUsername(String username){this.username=username;}
    public void setPassword(String password){this.password=password;}
    public String getUsername(){return username;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getPassword(){return password;}
    public String toString(){
        return "firstName="+firstName+";"+
                "lastName="+lastName+";"+
                "username="+username+";"+
                "password="+password;
    }
}
