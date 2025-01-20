package models;

import java.sql.*;

public class User {
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private City city;
    private String contact;
    private Date dob;
    private Character gender;
    private String address;
    private String logo;
    private String activationCode;
    private Status status;
    private UserType userType;

    public User() {

    }

    public User(String name, String email, String password, City city, String contact, UserType userType, String activationCode) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
        this.contact = contact;
        this.userType = userType;
        this.activationCode = activationCode;
    }

    public boolean saveUser() {
        boolean flag = false;

        try { Class.forName("com.mysql.cj.jdbc.Driver"); } catch(ClassNotFoundException e) { e.printStackTrace(); }
        
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cimsdb?user=root&password=1234");
        
            String query = "insert into users (name, email, password, contact, city_id, user_type_id, activation_code) value (?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, contact);
            ps.setInt(5, city.getCityId());
            ps.setInt(6, userType.getUserTypeId());
            ps.setString(7, activationCode);

            int val = ps.executeUpdate();

            if(val == 1)
                flag = true;
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch(SQLException e) {
                e.printStackTrace();
            } finally {

            }
        }

        return flag;
    }


    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public Character getGender() {
        return gender;
    }
    public void setGender(Character gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public String getActivationCode() {
        return activationCode;
    }
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    
}