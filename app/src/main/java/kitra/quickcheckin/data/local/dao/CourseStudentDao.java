package kitra.quickcheckin.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import kitra.quickcheckin.data.local.datamodel.Course;
import kitra.quickcheckin.data.local.datamodel.CourseStudent;
import kitra.quickcheckin.data.local.datamodel.Student;

@Dao
public interface CourseStudentDao {
    @Insert
    public void add(CourseStudent courseStudent);

    @Insert
    public void add(List<CourseStudent> courseStudentList);

    @Delete
    public int remove(CourseStudent courseStudent);

    @Delete
    public int remove(List<CourseStudent> courseStudent);

    @Query("SELECT id, name FROM Students "
            + "JOIN CourseStudents ON CourseStudents.studentId == Students.id "
            + "WHERE CourseStudents.courseId == :courseId")
    public List<Student> getStudentsByCourse(String courseId);


    @Query("SELECT id, name FROM Courses "
            + "JOIN CourseStudents ON CourseStudents.courseId == Courses.id "
            + "WHERE CourseStudents.studentId== :studentId")
    public List<Course> getCoursesByStudent(String studentId);
}
