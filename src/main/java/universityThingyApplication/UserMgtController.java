package universityThingyApplication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import universityThingyApplication.model.UniversityUser;
import universityThingyApplication.model.UniversityUser.Type;

@RestController
public class UserMgtController {

	@Autowired
	private UniversityUserRepository universityUserRepository;

	@GetMapping("/")
	public String index() {
		return "Greetings from University Accreditation Application...built by Mantarlele!!!!";
	}

	@RequestMapping("/validateuser")
	public boolean validate(@RequestBody UniversityUser user) {
		boolean validUser = false;
		UniversityUser[] users = getUserList();
		for (UniversityUser existingUser : users) {
			if (user.getUserName().equals(existingUser.getUserName())) {
				validUser = true;
			}
		}
		return validUser;
	}

	@RequestMapping("/adduser")
	public boolean adduser(@RequestBody UniversityUser universityUser) {
		boolean addUser = true;
		UniversityUser[] users = getUserList();
		for (UniversityUser existingUser : users) {
			if (universityUser.equals(existingUser)) {
				addUser = false;// meaning this user already exists in the database.
			}
		}
		if (addUser) {
			universityUserRepository.saveAndFlush(universityUser);
		}
		return addUser;
	}

	@RequestMapping("/users")
	public UniversityUser[] getUserList() {

		/*
		 * List<UniversityUser> userList = new ArrayList<>(); userList.add(new
		 * UniversityUser("Swapnil", "1234-scdw2-2fcevw342-2323", Type.ADMIN));
		 * userList.add(new UniversityUser("Rahul", "3454-2csfdfd-sfdvsdjv-7623",
		 * Type.STUDENT));
		 */
		List<UniversityUser> universityUsers = universityUserRepository.findAll();
		return universityUsers.toArray(new UniversityUser[0]);
	}
}
