package kitra.quickcheckin.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RewriteQueriesToDropUnusedColumns;
import androidx.room.Update;

import java.util.List;

import kitra.quickcheckin.data.local.datamodel.Student;
import kitra.quickcheckin.data.local.datamodel.StudentClassRelation;

@Dao
public interface StudentClassRelationDao {
    @Insert
    void insert(StudentClassRelation[] studentClassRelations);

    /**
     * 删除一个或多个学生-班级关联条目
     * @param studentClassRelations 要删除的条目
     * @return 删除成功的条目个数
     */
    @Delete
    int delete(StudentClassRelation[] studentClassRelations);

    /**
     * 更新一个或多个学生-班级关联条目
     * @param studentClassRelations 要更新的条目
     * @return 成功更新的条目个数
     */
    @Update
    int update(StudentClassRelation[] studentClassRelations);

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM Student JOIN StudentClassRelation "
            + "ON Student.uniqueId == StudentClassRelation.studentId "
            + "WHERE StudentClassRelation.classId == :classId")
    List<Student> getStudentsFromClass(int classId);
}
