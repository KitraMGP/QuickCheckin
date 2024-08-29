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

    public CourseStudent(@NonNull String courseId, @NonNull String studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    @NonNull
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(@NonNull String courseId) {
        this.courseId = courseId;
    }

    @NonNull
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(@NonNull String studentId) {
        this.studentId = studentId;
    }
}
