package com.harman.healthcare.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="INSTITUTION")
public class Institution extends AbstractPersistable<Long>{

	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="INSTITUTION_ID")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="NAME", nullable=false)
	private String name;

	@Column(name="DESCR", nullable=false)
	private String desc;
	
	@OneToMany(targetEntity=Patient.class, mappedBy = "institution", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    //@JsonIgnore
    private Set<Patient> patients;
	
	public Institution(){
		
	}

	public Institution(Long id, String name, String desc, Set<Patient> patients) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.patients = patients;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		result = prime * result + ((patients == null) ? 0 : patients.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Institution other = (Institution) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
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
		if (patients == null) {
			if (other.patients != null)
				return false;
		} else if (!patients.equals(other.patients))
			return false;
		return true;
	}

	
	
	

	
}
