import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentFlatFileDemo {

    public static class Student {
        private String name;
        private String id;
        private String course;

        public Student(String name, String id, String course) {
            this.name = name;
            this.id = id;
            this.course = course;
        }

        public String toFixedFormatString() {
            return String.format("%-10s%-10s%-20s", name, id, course);
        }

        public static Student fromFixedFormatString(String line) {
            String name = line.substring(0, 10).trim();
            String id = line.substring(10, 20).trim();
            String course = line.substring(20, 40).trim();
            return new Student(name, id, course);
        }
    }

    public static void main(String[] args) {
        // Example data
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", "1001", "Math"));
        students.add(new Student("Bob", "1002", "History"));

        // Write students to a flat file
        try (PrintWriter writer = new PrintWriter(new FileWriter("students.txt"))) {
            for (Student student : students) {
                writer.println(student.toFixedFormatString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read students from the flat file
        List<Student> loadedStudents = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                loadedStudents.add(Student.fromFixedFormatString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Display loaded students
        for (Student student : loadedStudents) {
            System.out.println("Name: " + student.name + ", ID: " + student.id + ", Course: " + student.course);
        }
    }
}
