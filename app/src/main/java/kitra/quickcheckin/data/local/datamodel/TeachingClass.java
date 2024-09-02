package kitra.quickcheckin.data.local.datamodel;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TeachingClass")
public class TeachingClass {
    @PrimaryKey
    private int uniqueId;
    private int courseId;

    @NonNull
    private String name;

    public TeachingClass(int uniqueId, int courseId, @NonNull String name) {
        this.uniqueId = uniqueId;
        this.courseId = courseId;
        this.name = name;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return "TeachingClass{" +
                "uniqueId=" + uniqueId +
                ", courseId=" + courseId +
                ", name='" + name + '\'' +
                '}';
    }
}
