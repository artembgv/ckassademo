package ru.artembgv.ckassademo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "persons", uniqueConstraints = @UniqueConstraint(columnNames = { "inn" }))
public class Person {
	/**
	 * id пользователя
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_person")
	private Integer id;
	/**
	 * ФИО пользователя
	 */
	@Column(length = 50, nullable = false)
	@Size(min = 2, max = 50, message = "ФИО должно быть от 2 до 50 символов")
	@NotBlank(message = "ФИО должно быть задано")
	private String fio;
	/**
	 * Дата и время регистрации
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "register_time", nullable = false)
	private Date registerTime = new Date();
	/**
	 * Дата рождения
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	private Date birthDate;

	/**
	 * Баланс
	 */
	@Column(nullable = false)
	private double balance;

	/**
	 * ИНН
	 */
	private Long inn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Long getInn() {
		return inn;
	}

	public void setInn(Long inn) {
		this.inn = inn;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", fio=" + fio + ", registerTime=" + registerTime + ", birthDate=" + birthDate
				+ ", balance=" + balance + ", inn=" + inn + "]";
	}
}