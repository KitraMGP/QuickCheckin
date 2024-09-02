package kitra.quickcheckin.data.local.datamodel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        tableName = "StudentClassRelation",
        primaryKeys = {"studentId", "classId"},
        foreignKeys = {
                @ForeignKey(entity = Student.class, parentColumns = "uniqueId", childColumns = "studentId", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = TeachingClass.class, parentColumns = "uniqueId", childColumns = "classId", onDelete = ForeignKey.CASCADE)
        }
)
public class StudentClassRelation {
    @ColumnInfo(index = true)
    private int studentId;

    @ColumnInfo(index = true)
    private int classId;

    public StudentClassRelation(int studentId, int classId) {
        this.studentId = studentId;
        this.classId = classId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @NonNull
    @Override
    public String toString() {
        return "StudentClassRelation{" +
                "studentId=" + studentId +
                ", classId=" + classId +
                '}';
    }
}
