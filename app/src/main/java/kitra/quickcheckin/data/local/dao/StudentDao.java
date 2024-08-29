package kitra.quickcheckin.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import kitra.quickcheckin.data.local.datamodel.Student;

@Dao
public interface StudentDao {

    @Insert
    public void addStudent(Student student);

    @Insert
    public void addStudents(List<Student> studentList);

    /**
     * 从数据库表中删除学生信息
     *
     * @param student 删除的学生信息（匹配学号）
     * @return 成功删除的条目个数，为1则删除成功，否则失败
     */
    @Delete
    public int deleteStudent(Student student);

    /**
     * 从数据库表中删除多个学生信息
     *
     * @param studentList 删除的学生信息列表（匹配学号）
     * @return 成功删除的条目个数
     */
    @Delete
    public int deleteStudents(List<Student> studentList);

    @Query("SELECT id, name FROM Students")
    public List<Student> getAllStudents();

    @Query("SELECT id, name FROM Students WHERE id == :id")
    public Student getStudent(String id);

    /**
     * 更新一条学生信息
     *
     * @param student 要更新的学生信息（匹配学号）
     */
    @Update
    public void updateStudent(Student student);
}
