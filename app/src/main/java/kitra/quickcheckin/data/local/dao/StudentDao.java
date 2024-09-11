package kitra.quickcheckin.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import kitra.quickcheckin.data.local.datamodel.Student;

@Dao
public interface StudentDao {
    @Insert
    Completable insert(Student... students);

    /**
     * 删除一个或多个学生条目
     * @param students 要删除的学生（只匹配uniqueId）
     * @return 成功删除的条目数量
     */
    @Delete
    Completable delete(Student... students);

    /**
     * 更新一个或多个学生条目
     * @param students 要更新的学生条目（不能更新uniqueId）
     * @return 成功更新的条目数量
     */
    @Update
    Completable update(Student... students);

    @Query("SELECT * FROM Student")
    LiveData<List<Student>> getAll();

    @Query("SELECT COUNT(*) FROM Student")
    LiveData<Integer> getStudentCount();
}
