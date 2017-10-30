package web.model;

import java.sql.Date;

public class Employee {
	
	private int id;
	private String pass;
	private String firstName;
	private String lastName;
	private int age;
	private String email;
	private int salary;
	private Date oldEmployee;
	
	public Employee(int id, String pass, String firstName, String lastName, int age, String email, int salary,
			Date oldEmployee) {
		super();
		this.id = id;
		this.pass = pass;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.salary = salary;
		this.oldEmployee = oldEmployee;
	}

	public Employee(String pass, String firstName, String lastName, int age, String email, int salary,
			Date oldEmployee) {
		super();
		this.pass = pass;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.salary = salary;
		this.oldEmployee = oldEmployee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Date getOldEmployee() {
		return oldEmployee;
	}

	public void setOldEmployee(Date oldEmployee) {
		this.oldEmployee = oldEmployee;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", pass=" + pass + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", age=" + age + ", email=" + email + ", salary=" + salary + ", oldEmployee=" + oldEmployee + "]";
	}
	
	
	
	

}
