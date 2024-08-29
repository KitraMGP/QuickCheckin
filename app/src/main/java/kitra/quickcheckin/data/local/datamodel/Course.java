package kitra.quickcheckin.data.local.datamodel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Courses")
public class Course {
    //private final Set<Student> studentSet;
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NonNull
    private String id;

    @ColumnInfo(name = "name")
    private String name;

    public Course(String name, @NonNull String id) {
        this.name = name;
        this.id = id;
        //this.studentSet = new HashSet<>();
    }

//    public Course(String name, String id, Set<Student> studentSet) {
//        this.name = name;
//        this.id = id;
//        this.studentSet = studentSet;
//    }

//    public int getStudentCount() {
//        return studentSet.size();
//    }
//
//    public boolean hasStudent(String id) {
//        return studentSet.stream().anyMatch(student -> student.getId().equals(id));
//    }
//
//    public boolean addStudent(Student s) {
//        return studentSet.add(s);
//    }
//
//    public boolean removeStudent(String id) {
//        return studentSet.removeIf(student -> student.getId().equals(id));
//    }
//
//    public List<Student> getStudents() {
//        return new ArrayList<>(this.studentSet);
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }
}
