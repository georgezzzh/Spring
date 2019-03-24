package spittle;

public class Spitter {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    public void setUsername(String username){this.username=username;}
    public void setPassword(String password){this.password=password;}
    public void setFirstName(String firstName){this.firstName=firstName;}
    public void setLastName(String lastName){this.lastName=lastName;}
    public String getUsername(){return username;}
    public String getPassword(){return password;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String toString(){
        return "username="+username
                +";password="+password
                +";firstName="+firstName
                +";lastName="+lastName;
    }
}
