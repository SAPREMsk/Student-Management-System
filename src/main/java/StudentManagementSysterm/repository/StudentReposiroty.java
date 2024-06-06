package StudentManagementSysterm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import StudentManagementSysterm.entiry.Student;

public interface StudentReposiroty extends JpaRepository<Student, Long>{
	

}
