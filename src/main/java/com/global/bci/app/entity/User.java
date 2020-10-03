package com.global.bci.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "users")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	@ApiModelProperty(notes = "Id Unico", dataType = "Long")
	private Long idUser;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "user_create_at")
	@ApiModelProperty(notes = "Fecha Creacion", dataType = "Date Temporal.TIMESTAMP")
	private Date createAt;
	
	@Column(name = "user_update_at")
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(notes = "Fecha Actualizacion", dataType = "Date Temporal.TIMESTAMP")
	private Date updateAt;
	
	@Column(name = "user_last_login_at")
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(notes = "Fecha ultimo login", dataType = "Date Temporal.TIMESTAMP")
	private Date lastLoginAt;
	
	@Column(name = "user_token")
	@ApiModelProperty(notes = "JWT TOKEN", dataType = "String")
	private String token;
	
	@NotNull(message = "No puede ser nulo")
	@Column(name = "user_name")
	@ApiModelProperty(notes = "Nombre", dataType = "String")
	private String name;
	
//	@Email(message = "Formato de correo incorrecto, debe seguir el siguiente: correo@ejemplo.cl")
	@Column(name = "user_email", unique = true )
	@ApiModelProperty(notes = "Email", dataType = "String")
	private String email;
	
	@NotNull(message = "No puede ser nulo")
	@Column(name = "user_password")
	@ApiModelProperty(notes = "Password", dataType = "String")
	private String password;
	
	@Column(name = "user_is_active")
	@ApiModelProperty(notes = "Usuario Activo", dataType = "Boolean")
	private Boolean isActive;
	
	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	@ApiModelProperty(notes = "Lista de telefonos registrados", dataType = "List<Phone>")
	private List<Phone> phones;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Date getLastLoginAt() {
		return lastLoginAt;
	}

	public void setLastLoginAt(Date lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createAt == null) ? 0 : createAt.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((lastLoginAt == null) ? 0 : lastLoginAt.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phones == null) ? 0 : phones.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((updateAt == null) ? 0 : updateAt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (createAt == null) {
			if (other.createAt != null)
				return false;
		} else if (!createAt.equals(other.createAt))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (lastLoginAt == null) {
			if (other.lastLoginAt != null)
				return false;
		} else if (!lastLoginAt.equals(other.lastLoginAt))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phones == null) {
			if (other.phones != null)
				return false;
		} else if (!phones.equals(other.phones))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (updateAt == null) {
			if (other.updateAt != null)
				return false;
		} else if (!updateAt.equals(other.updateAt))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;
}
