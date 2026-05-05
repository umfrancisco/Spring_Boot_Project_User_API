package com.umfrancisco.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(nullable = false, length = 50)
    private String username;
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Column(nullable = false, length = 50)
    private String password;
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Column(nullable = false, length = 100)
    private String email;
    @NotBlank(message = "City is required")
    @Column(nullable = false, length = 100)
    private String city;
    @NotBlank(message = "Country is required")
    @Column(nullable = false, length = 100)
    private String country;
    
    public User() {
    	
    }
    
	public User(Long id,
			@NotBlank(message = "Username is required") @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String username,
			@NotBlank(message = "Password is required") @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters") String password,
			@NotBlank(message = "Email is required") @Email(message = "Email must be valid") String email,
			@NotBlank(message = "City is required") String city,
			@NotBlank(message = "Country is required") String country) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.city = city;
		this.country = country;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", city="
				+ city + ", country=" + country + "]";
	}
}
