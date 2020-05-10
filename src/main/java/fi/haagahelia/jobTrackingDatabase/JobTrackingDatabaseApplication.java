package fi.haagahelia.jobTrackingDatabase;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.jobTrackingDatabase.domain.Applicant;
import fi.haagahelia.jobTrackingDatabase.domain.ApplicantRepository;
import fi.haagahelia.jobTrackingDatabase.domain.Department;
import fi.haagahelia.jobTrackingDatabase.domain.DepartmentRepository;
import fi.haagahelia.jobTrackingDatabase.domain.User;
import fi.haagahelia.jobTrackingDatabase.domain.UserRepository;
import fi.haagahelia.jobTrackingDatabase.domain.Vacancy;
import fi.haagahelia.jobTrackingDatabase.domain.VacancyRepository;

@SpringBootApplication
public class JobTrackingDatabaseApplication {

	private static final Logger log = LoggerFactory.getLogger(JobTrackingDatabaseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JobTrackingDatabaseApplication.class, args);

	}

	@Bean
	public CommandLineRunner demo(VacancyRepository repository, DepartmentRepository drepository,
			ApplicantRepository arepository, UserRepository urepository) {
		return (arg) -> {
			drepository.save(new Department("IT"));
			drepository.save(new Department("Marketing"));
			drepository.save(new Department("Human Resourses"));

			arepository.save(new Applicant("reject"));
			arepository.save(new Applicant("interview"));
			arepository.save(new Applicant("reserve list"));
			arepository.save(new Applicant("no answer"));

			Vacancy v1 = new Vacancy("Front-End intern", "Denmark, Billund", "LEGO", "20.04.2020", "12.06.2020",
					"6 months", drepository.findByName("IT").get(0), arepository.findByDecision("interview").get(0));
			Vacancy v2 = new Vacancy("Full-stack ", "Denmark, Viby", "Visiolink", "12.05.2020", "12.06.2020", "3 month",
					drepository.findByName("IT").get(0), arepository.findByDecision("no answer").get(0));
			Vacancy v3 = new Vacancy("React-Native Frontend intern", "Denmark, Aarhus", "WasteHero", "20.04.2020",
					"12.06.2020", "6 months", drepository.findByName("IT").get(0),
					arepository.findByDecision("reject").get(0));

			repository.save(v1);
			repository.save(v2);
			repository.save(v3);

			// Create users with BCrypt encoded password (user/user, admin/admin)
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
		};
	}

}
