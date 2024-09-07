package kitra.quickcheckin.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import kitra.quickcheckin.data.local.datamodel.TeachingClass;

@Dao
public interface TeachingClassDao {
    @Insert
    Completable insert(TeachingClass... teachingClass);

    /**
     * 删除一个或多个上课班级条目
     * @param teachingClasses 要删除的上课班级条目
     * @return 成功删除的条目个数
     */
    @Delete
    Completable delete(TeachingClass... teachingClasses);

    /**
     * 更新一个或多个上课班级条目
     * @param teachingClasses 要更新的上课班级条目
     * @return 成功更新的条目个数
     */
    @Update
    int update(TeachingClass... teachingClasses);

    @Query("SELECT * FROM TeachingClass")
    LiveData<List<TeachingClass>> getAll();

    @Query("SELECT * FROM TeachingClass WHERE uniqueId == :uniqueId")
    LiveData<TeachingClass> getClassByUniqueId(int uniqueId);
}
