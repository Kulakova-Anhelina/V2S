package fi.haagahelia.jobTrackingDatabase.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long departmentid;
	private String name;
	
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	private List<Vacancy> vacancies;
	
	public Department() {}
	
	public Department(String name) {
		super();
		this.name = name;
	}
	
	public Long getDepartmentid() {
		return departmentid;
	}
	
	public void setDepartmentid(Long departmentid) {
		this.departmentid = departmentid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Vacancy> geVacancies() {
		return vacancies;
	}

	public void setStudents(List<Vacancy> vacancies) {
		this.vacancies = vacancies;
	}

	@Override
	public String toString() {
		return "Department [departmentid=" + departmentid + ", name=" + name + "]";
	}
}