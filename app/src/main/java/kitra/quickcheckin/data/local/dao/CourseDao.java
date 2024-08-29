package kitra.quickcheckin.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import kitra.quickcheckin.data.local.datamodel.Course;
import kitra.quickcheckin.data.local.datamodel.Student;

@Dao
public interface CourseDao {
    @Insert
    public void addCourse(Course course);

    @Insert
    public void addCourses(List<Course> courseList);

    /**
     * 删除一门课程
     *
     * @param course 要删除的课程（匹配id）
     * @return 成功删除的课程数量。1为成功，0为失败
     */
    @Delete
    public int deleteCourse(Course course);

    /**
     * 删除多门课程
     *
     * @param courseList 要删除的课程列表（匹配id）
     * @return 成功删除的课程数量
     */
    @Delete
    public int deleteCourses(List<Course> courseList);

    @Query("SELECT id, name FROM Courses")
    public List<Course> getAllCourses();

    @Query("SELECT id, name FROM Courses WHERE id == :id")
    public Student getStudent(String id);

    /**
     * 更新一条课程信息
     *
     * @param course 要更新的课程信息（匹配id）
     */
    @Update
    public void updateCourse(Course course);
}
