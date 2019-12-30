package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sec.project.domain.Signup;
import java.util.List;

public interface SignupRepository extends JpaRepository<Signup, Long> {

    @Query("SELECT f FROM Signup as f WHERE f.name like :name%")
    //@Query("SELECT f FROM Signup as f WHERE f.name like " + (@Param("name") long name))
    // Tee BrokenQuery joka ohittaa sql-sanitizer!!
    List<Signup> findByNameContaining(@Param("name") long name);

}
