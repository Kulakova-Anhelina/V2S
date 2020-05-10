package fi.haagahelia.jobTrackingDatabase.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.jobTrackingDatabase.domain.Applicant;
import fi.haagahelia.jobTrackingDatabase.domain.ApplicantRepository;
import fi.haagahelia.jobTrackingDatabase.domain.Department;
import fi.haagahelia.jobTrackingDatabase.domain.DepartmentRepository;
import fi.haagahelia.jobTrackingDatabase.domain.Vacancy;
import fi.haagahelia.jobTrackingDatabase.domain.VacancyRepository;

@Controller
public class VacancyContoller {

	@Autowired
	private VacancyRepository repository;
	@Autowired
	private DepartmentRepository drepository;

	@Autowired
	private ApplicantRepository arepository;

	/**
	 * Login page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	/**
	 * This is a home page after successfully log in
	 * 
	 * @param model
	 * @return the page list of all vacancies 
	 */

	@RequestMapping("/vacancylist")
	public String vacancyList(Model model) {
		model.addAttribute("vacancies", repository.findAll());
		return "vacancylist";

	}

	/**
	 * This end point: /vacancy
	 * 
	 * @return a Rest API JSON file with all the vacancies in database
	 */

	// RESTful service to get all vacancies
	@RequestMapping(value = "/vacancies", method = RequestMethod.GET)
	public @ResponseBody List<Vacancy> vacancyListRest() {
		return (List<Vacancy>) repository.findAll();
	}

	/**
	 * This end point: /vacancy
	 * 
	 * @return a Rest API JSON file with a specific id
	 */

	// RESTful service to get vacancy by id
	@RequestMapping(value = "/vacancy/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Vacancy> findStudentRest(@PathVariable("id") Long vacancyId) {
		return repository.findById(vacancyId);
	}

	/**
	 * This end point: /departments
	 * 
	 * @return a Rest API JSON file with all departments
	 */

	@RequestMapping(value = "/departments", method = RequestMethod.GET)
	public @ResponseBody List<Department> departmentListRest() {
		return (List<Department>) drepository.findAll();
	}

	/**
	 * This end point: /decisions
	 * 
	 * @return a Rest API JSON file with all decisions
	 */

	@RequestMapping(value = "/decisions", method = RequestMethod.GET)
	public @ResponseBody List<Applicant> applicantListRest() {
		return (List<Applicant>) arepository.findAll();
	}


	
	/**
	 * Return page when add a vacancy to the list 
	 * @param  model
	 * @return a form add
	 */
	
	
	@RequestMapping(value = "/add")
	public String addVacancy(Model model) {
		model.addAttribute("vacancy", new Vacancy());
		model.addAttribute("departments", drepository.findAll());
		model.addAttribute("applicants", arepository.findAll());
		return "addVacancy";
	}


	
	/**
	 * edit html page for a specific vacancy Id
	 * only for Admin
	 * @param vacancyId
	 * @param model
	 * @return a form to edit
	 */
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editStudent(@PathVariable("id") Long vacancyId, Model model) {
		model.addAttribute("vacancy", repository.findById(vacancyId));
		model.addAttribute("departments", drepository.findAll());
		model.addAttribute("applicants", arepository.findAll());
		return "editVacancy";
	}


	
	/**
	 * Return page when submit vacancy
	 * @param vacancy
	 * @return
	 */
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Vacancy vacancy) {
		repository.save(vacancy);
		return "redirect:/vacancylist";
	}


	
	/**
	 * delete by specific vacancy Id
	 * only for Admin
	 * @param vacancyId
	 * @param model
	 * @return 
	 */
	
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteVacancy(@PathVariable("id") Long vacancyId, Model model) {
		repository.deleteById(vacancyId);
		return "redirect:/vacancylist";
	}

}
