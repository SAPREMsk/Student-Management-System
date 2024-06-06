package StudentManagementSysterm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import StudentManagementSysterm.entiry.Student;
import StudentManagementSysterm.service.StudentService;

@Controller
public class StudentController {
	
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@GetMapping("/students")
	public String listStudents(Model model) {
		
		model.addAttribute("students", studentService.getAllStudents());
		
		return "students";
		
	}
	
	@GetMapping("/students/new")
	public String CreateStudentForm(Model model) {
		
		Student student =new Student();
		model.addAttribute("student", student);
		
		return "createStudent";
	}
	
	@PostMapping("/students/new")
	public String saveStudent(@ModelAttribute("student") Student student) {
		
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit{id}")
	public String editStudent(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "editStudent";
	}

	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id ,@ModelAttribute ("student") Student student) {
		
		Student exixstingstud= studentService.getStudentById(id);
		
		exixstingstud.setId(id);
		exixstingstud.setFirstName(student.getFirstName());
		exixstingstud.setLastName(student.getLastName());
		exixstingstud.setEmail(student.getEmail());
		exixstingstud.setPhoneNo(student.getPhoneNo());
		
		studentService.updateStudent(student);
		
		return "redirect:/students";	
				
	}
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	
	}
	
}