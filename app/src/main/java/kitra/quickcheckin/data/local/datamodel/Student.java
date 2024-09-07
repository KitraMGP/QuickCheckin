package kitra.quickcheckin.data.local.datamodel;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Student")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int uniqueId;

    @NonNull
    private String number;

    @NonNull
    private String name;

    public Student(int uniqueId, @NonNull String number, @NonNull String name) {
        this.uniqueId = uniqueId;
        this.number = number;
        this.name = name;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    @NonNull
    public String getNumber() {
        return number;
    }

    public void setNumber(@NonNull String number) {
        this.number = number;
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
        return "Student{" +
                "uniqueId=" + uniqueId +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
