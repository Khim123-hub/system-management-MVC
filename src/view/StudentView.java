package view;

import model.dto.StudentRequestDto;
import model.dto.StudentResponseDto;
import model.dto.StudentUpdateDto;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class StudentView {

    private final static Scanner scanner = new Scanner(System.in);

    public StudentRequestDto displayStudentCreateDto() {
        System.out.print("[+] Enter Full Name: ");
        String fullname = scanner.nextLine();

        System.out.print("[+] Enter gender: ");
        String gender = scanner.nextLine();

        System.out.print("[+] Enter Date of Birth(Format yyyy-MM-dd): ");
        String dob = scanner.nextLine();
        String[] parts = dob.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        LocalDate dateOfBirth = LocalDate.of(year,month, day);

        return new StudentRequestDto(fullname, gender, dateOfBirth);

    }

    public StudentUpdateDto displayStudentUpdateDto() {

        System.out.print("[+] Enter New Full Name: ");
        String fullName = scanner.nextLine();

        System.out.print("[+] Enter Gender: ");
        String gender = scanner.nextLine();

        System.out.print("[+] Enter Date of Birth (yyyy-MM-dd): ");
        String dob = scanner.nextLine();
        String[] parts = dob.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        LocalDate dateOfBirth = LocalDate.of(year, month, day);

        return new StudentUpdateDto(fullName,gender,dateOfBirth);
    }

    public void displaySingleStudent(StudentResponseDto responseDto) {
        Table table = new Table(
                4, BorderStyle.CLASSIC
        );
        table.addCell("Student Information",
                new CellStyle(CellStyle.HorizontalAlign.center), 4);
        table.addCell("ID");
        table.addCell(responseDto.id().toString(), 3);
        table.addCell("Full Name");
        table.addCell(responseDto.fullName(), 3);
        table.addCell("Gender");
        table.addCell(responseDto.gender(), 3);
        table.addCell("Date of Birth");
        table.addCell(responseDto.dateOfBirth().toString(), 3);

        System.out.println(table.render());


    }

    public void displayStudentList(List<StudentResponseDto> students) {
        if (students.isEmpty()) {
            System.out.println("\nNo students found.");
            return;
        }

        Table table = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER);
        String[] columns = {"ID", "FullName", "Gender", "Date of Birth"};
        for (String column : columns) {
            table.addCell(column, new CellStyle(CellStyle.HorizontalAlign.center));
        }
        students.forEach(student -> {
            table.addCell(student.id().toString());
            table.addCell(student.fullName());
            table.addCell(student.gender());
            table.addCell(student.dateOfBirth().toString());
        });
        System.out.println(table.render());
        System.out.println("Total: " + students.size() + " student(s)");
    }

    public Long showIdInput() {
        System.out.print("Enter ID: ");
        return Long.parseLong(scanner.nextLine());
    }

    public int showMenuAndGetOption() {
        System.out.print("""
                1. Create
                2. Show All Students
                3. Delete
                4. Update
                0. Exit
                """);

        System.out.print("Choose an option: ");
        return Integer.parseInt(scanner.nextLine());
    }
}
