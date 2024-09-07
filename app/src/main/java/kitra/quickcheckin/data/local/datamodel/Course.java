package kitra.quickcheckin.data.local.datamodel;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Course")
public class Course {
    @PrimaryKey(autoGenerate = true)
    private int uniqueId;

    @NonNull
    private String name;

    public Course(int uniqueId, @NonNull String name) {
        this.uniqueId = uniqueId;
        this.name = name;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return "Course{" +
                "uniqueId=" + uniqueId +
                ", name='" + name + '\'' +
                '}';
    }
}
