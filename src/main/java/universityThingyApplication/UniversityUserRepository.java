package universityThingyApplication;

import org.springframework.data.jpa.repository.JpaRepository;

import universityThingyApplication.model.UniversityUser;

public interface UniversityUserRepository extends JpaRepository<UniversityUser, String> {

}
