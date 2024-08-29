package kitra.quickcheckin.data.local.datamodel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        tableName = "CourseStudents",
        foreignKeys = {
                @ForeignKey(entity = Course.class, parentColumns = "id", childColumns = "courseId", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Student.class, parentColumns = "id", childColumns = "studentId", onDelete = ForeignKey.CASCADE)
        },
        primaryKeys = {"courseId", "studentId"})
public class CourseStudent {
    @ColumnInfo(name = "courseId", index = true)
    @NonNull
    private String courseId;

    @ColumnInfo(name = "studentId", index = true)
    @NonNull
    private String studentId;

    public CourseStudent(String courseId, String studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
