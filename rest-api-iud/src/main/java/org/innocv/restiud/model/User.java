package org.innocv.restiud.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USUARIOS")
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Please provide a name")
	private String name;

	@JsonFormat(pattern = "dd-MM-YYYY")
	private Date birthdate;

	public User(String name, Date birthdate) {
		this.name = name;
		this.birthdate = birthdate;
	}

	public User(Integer id, String name, Date birthdate) {
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}

	public User() {
	}
}
