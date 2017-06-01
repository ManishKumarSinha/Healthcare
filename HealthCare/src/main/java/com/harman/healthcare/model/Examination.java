package com.harman.healthcare.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="EXAMINATION")
public class Examination implements Serializable{

	@Id
	@Column(name="EXAM_ID")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(name="DATE", nullable=false)
	private Date examDate;
	
	@NotEmpty
	@Column(name="NAME", nullable=false)
	private String name;

	@NotEmpty
	@Column(name="DESCR", nullable=false)
	private String desc;
	
	@NotEmpty
	@Column(name="WEIGHT")
	private Double weight;
	
	@NotEmpty
	@Column(name="HEIGHT")
	private Double height;

	public Examination(){

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Examination(Long id, Date examDate, String name, String desc, Double weight, Double height) {
		super();
		this.id = id;
		this.examDate = examDate;
		this.name = name;
		this.desc = desc;
		this.weight = weight;
		this.height = height;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((examDate == null) ? 0 : examDate.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		Examination other = (Examination) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (examDate == null) {
			if (other.examDate != null)
				return false;
		} else if (!examDate.equals(other.examDate))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	

}
