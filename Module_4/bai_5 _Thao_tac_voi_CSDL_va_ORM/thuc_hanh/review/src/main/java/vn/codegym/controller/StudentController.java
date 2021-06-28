package vn.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.codegym.model.Student;
import vn.codegym.service.StudentService;
import vn.codegym.service.StudentServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"student",""}, name = "studentController")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping(value = "/edit/{id}")
//            (value = "/edit/{id:[0-2]}", name = "showEditPage")
    public String showEditPage(@PathVariable("id") int studentId, ModelMap modelMap) {
        modelMap.addAttribute("student", studentService.finById(studentId));
        return "edit";
    }

    @PostMapping(value = "/edit")
    public String editStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes){
        studentService.update(student);
        redirectAttributes.addFlashAttribute("successMsg", "Update Student"
                + student.getName()+ "OK");
        return  "redirect:/student/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteStudent (@PathVariable ("id") int id, RedirectAttributes redirectAttributes){
        Student student = studentService.finById(id);
        if ((student != null)) {
            redirectAttributes.addFlashAttribute("successMsg","Delete student"
            + student.getName()+"OK");
            studentService.remove(student);
        }else {
            redirectAttributes.addFlashAttribute("successMsg", "Student not found");
        }
        return "redirect:/student/list";
    }

    @GetMapping(value = "/search")
    public ModelAndView searchStudent(@RequestParam("kq") String kq){
        return new ModelAndView("list" , "studentList",
                studentService.searchByName(kq));
    }





    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showStudentList() {
        return new ModelAndView("list", "studentList",
                studentService.findAll());
    }

    @GetMapping(value = "/create")
    public String showCreatePage(Model model){
        model.addAttribute("student", new Student());

        return "create";
    }

    @PostMapping(value = "/create")
    public String createStudent(@ModelAttribute Student student, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("successMsg", "create student: "
                + student.getName() + " OK");
        System.out.println(student);
        studentService.save(student);
        return "redirect:/student/list";
//        return "list";

    }
}
