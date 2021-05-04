package infy.parksha.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_AUTH_TBL")
public class User {

	@Id
	@GeneratedValue
	private int id;
	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	private String gender;
	private boolean active;
	private String roles;
}
