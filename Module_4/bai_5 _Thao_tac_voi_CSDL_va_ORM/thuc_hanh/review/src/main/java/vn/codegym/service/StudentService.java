package vn.codegym.service;

import vn.codegym.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student finById(int id);

    void save(Student student);

    void update(Student student);

    void remove(Student student);

    List<Student> searchByName(String kq);

}
