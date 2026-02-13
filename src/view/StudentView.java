package view;

import model.dto.StudentRequestDto;
import model.dto.StudentResponseDto;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class StudentView {
    private final static Scanner scanner = new Scanner(System.in);

    public StudentRequestDto displayStudentCreateDto() {
        System.out.print("[+] Enter Full Name: ");
        String fullName = scanner.nextLine();

        System.out.print("[+] Enter Gender: ");
        String gender = scanner.nextLine();

        System.out.print("[+] Enter Date of Birth(Format yyyy-MM-dd): ");
        String dob = scanner.nextLine();
        String[] parts = dob.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        LocalDate dateOfBirth = LocalDate.of(year, month, day);

        return new StudentRequestDto(
                fullName, gender, dateOfBirth
        );

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
    public void displayStudentList(List<StudentResponseDto> students){

        Table table = new Table(
                4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER
        );
        String[] columns = {"ID", "FullName", "Gender", "Date of Birth"};
        for (String column : columns) {
            table.addCell(column);
        }

        students.forEach(student -> {
            table.addCell(student.id().toString());
            table.addCell(student.fullName());
            table.addCell(student.gender());
            table.addCell(student.dateOfBirth().toString());

            System.out.println(table.render());
        });

    }

    public Long showIdInput() {
        System.out.print("Enter ID: ");
        return Long.parseLong(scanner.nextLine());
    }

    public int showMenuAndGetOption() {
        System.out.println("""
                1. Create
                2. Show All Students
                3. Find by id
                4. Search by name
                5. update student
                6. Delete student
                0. Exit
                """);
        System.out.println("Choose an option:");

        return Integer.parseInt((scanner.nextLine()));
    }
}
