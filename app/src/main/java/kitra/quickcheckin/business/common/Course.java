package kitra.quickcheckin.business.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Course {
    private String name;
    private String id;
    private Set<Student> studentList;

    public Course(String name, String id) {
        this.name = name;
        this.id = id;
        this.studentList = new HashSet<>();
    }

    public Course(String name, String id, Set<Student> studentList) {
        this.name = name;
        this.id = id;
        this.studentList = studentList;
    }

    public int getStudentCount() {
        return studentList.size();
    }

    public boolean hasStudent(String id) {
        return studentList.stream().anyMatch(student -> student.getId().equals(id));
    }

    public boolean addStudent(Student s) {
        return studentList.add(s);
    }

    public boolean removeStudent(String id) {
        return studentList.removeIf(student -> student.getId().equals(id));
    }

    public List<Student> getStudents() {
        return new ArrayList<>(this.studentList);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
