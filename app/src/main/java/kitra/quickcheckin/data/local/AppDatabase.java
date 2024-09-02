package kitra.quickcheckin.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import kitra.quickcheckin.data.local.dao.CourseDao;
import kitra.quickcheckin.data.local.dao.StudentClassRelationDao;
import kitra.quickcheckin.data.local.dao.StudentDao;
import kitra.quickcheckin.data.local.dao.TeachingClassDao;
import kitra.quickcheckin.data.local.datamodel.Course;
import kitra.quickcheckin.data.local.datamodel.Student;
import kitra.quickcheckin.data.local.datamodel.StudentClassRelation;
import kitra.quickcheckin.data.local.datamodel.TeachingClass;

@Database(entities = {Course.class, Student.class, StudentClassRelation.class, TeachingClass.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CourseDao courseDao();
    public abstract StudentDao studentDao();
    public abstract StudentClassRelationDao studentClassRelationDao();
    public abstract TeachingClassDao teachingClassDao();
}
