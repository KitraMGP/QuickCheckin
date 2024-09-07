package kitra.quickcheckin.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import kitra.quickcheckin.data.local.datamodel.Course;

@Dao
public interface CourseDao {
    @Insert
    void insert(Course... course);

    /**
     * 删除一个或多个课程条目
     * @param courses 要删除的课程，只匹配uniqueId
     * @return 成功删除的条目个数
     */
    @Delete
    int delete(Course... courses);

    /**
     * 更新一个或多个学生条目
     * @param courses 要更新的课程（不能更新uniqueId）
     * @return 成功更新的条目个数
     */
    @Update
    int update(Course... courses);

    @Query("SELECT * FROM Course")
    List<Course> getAll();
}
