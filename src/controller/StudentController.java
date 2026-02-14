package controller;

import exception.StudentException;
import model.dto.StudentRequestDto;
import model.dto.StudentResponseDto;
import model.service.StudentService;
import view.StudentView;

public class StudentController {

    private final StudentView view;
    private final StudentService service;

    public StudentController (StudentView view, StudentService service) {
        this.view = view;
        this.service = service;
    }

    public void create() {
//        StudentResponseDto response = null;
        try {
            StudentRequestDto request = view.displayStudentCreateDto();
            StudentResponseDto response = service.createStudent(request);
            view.displaySingleStudent(response);
        } catch (StudentException e) {
            System.out.println(e.getMessage());
        }

//        assert response != null;

    }
    public void showAll() {
        view.displayStudentList(service.getAllStudents(3, 2));
    }

    public void delete() {
        Long id = view.showIdInput();
        if (service.deleteById(id)) {
            System.out.println("Removed successfully");
        } else {
            System.out.println("Failed to remove");
        }
    }

    public void run() {

    }

    private void update() {
    }


}
