package fi.haagahelia.jobTrackingDatabase.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import org.springframework.boot.ApplicationArguments;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Vacancy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	public String title, location, company, postedDate, lastDate, duration;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "departmentid")
	private Department department;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "decisionid")
	private Applicant applicant;

	public Vacancy() {

	}

	public Vacancy(String title, String location, String company, String postedDate, String lastDate, String duration,
			Department department, Applicant applicant) {
		super();
		this.title = title;
		this.location = location;
		this.company = company;
		this.postedDate = postedDate;
		this.lastDate = lastDate;
		this.duration = duration;
		this.department = department;
		this.applicant = applicant;

	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

}
