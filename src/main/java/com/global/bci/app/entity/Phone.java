package com.global.bci.app.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "phones")
public class Phone implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPhone;
	
	@NotNull(message = "No puede ser nulo")
	@Column(name = "phone_number")
	private String number;
	
	@NotNull(message = "No puede ser nulo")
	@Column(name = "phone_city_code")
	private String cityCode;
	
	@NotNull(message = "No puede ser nulo")
	@Column(name = "phone_country_code")
	private String countryCode;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name = "user_id",nullable = false, foreignKey = @ForeignKey(name = "fk_phone_user"))
	public User user;

	public Long getIdPhone() {
		return idPhone;
	}

	public void setIdPhone(Long idPhone) {
		this.idPhone = idPhone;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cityCode == null) ? 0 : cityCode.hashCode());
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((idPhone == null) ? 0 : idPhone.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Phone other = (Phone) obj;
		if (cityCode == null) {
			if (other.cityCode != null)
				return false;
		} else if (!cityCode.equals(other.cityCode))
			return false;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (idPhone == null) {
			if (other.idPhone != null)
				return false;
		} else if (!idPhone.equals(other.idPhone))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	private static final long serialVersionUID = 1L;

}
