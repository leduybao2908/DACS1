package model;

import javax.persistence.*;

@Entity
@Table(name = "admins", uniqueConstraints = {
    @UniqueConstraint(columnNames = "Email")
})
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AdminID")
    private int adminID;

    @Column(name = "Name", length = 100)
    private String name;

    @Column(name = "Email", length = 100, unique = true)
    private String email;

    @Column(name = "Password", length = 100)
    private String password;

    // Constructors, getters, and setters



    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

   
	public Admin() {
		// TODO Auto-generated constructor stub
	}


	public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
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
    
    public Object[] toObject() {
    	return new Object[]{"" + adminID, name, email, password};
    }
}
