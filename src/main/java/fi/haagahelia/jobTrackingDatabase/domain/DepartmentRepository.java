package fi.haagahelia.jobTrackingDatabase.domain;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



public interface DepartmentRepository extends CrudRepository<Department, Long> {

    List<Department> findByName(String name);
    
    
}