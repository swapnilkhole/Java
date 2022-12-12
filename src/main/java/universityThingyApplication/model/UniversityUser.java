package universityThingyApplication.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "University_Accreditation", name = "UNIVERSITY_USER")
public class UniversityUser {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String userId;

	public enum Type {
		STUDENT, TEACHER, ADMIN;
	}

	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "USER_PASSWORD")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "USER_TYPE")
	private Type type;

	public UniversityUser(String userName, String userId, Type type) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.type = type;
	}

	public UniversityUser() {

	}

	public UniversityUser(String userName, String userId) {
		super();
		this.userName = userName;
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, userId, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UniversityUser other = (UniversityUser) obj;
		return Objects.equals(type, other.type) && Objects.equals(userId, other.userId)
				&& Objects.equals(userName, other.userName);
	}

}
