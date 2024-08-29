package kitra.quickcheckin.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import kitra.quickcheckin.data.local.dao.CourseDao;
import kitra.quickcheckin.data.local.dao.CourseStudentDao;
import kitra.quickcheckin.data.local.dao.StudentDao;
import kitra.quickcheckin.data.local.datamodel.Course;
import kitra.quickcheckin.data.local.datamodel.CourseStudent;
import kitra.quickcheckin.data.local.datamodel.Student;

@Database(entities = {Student.class, Course.class, CourseStudent.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();

    public abstract CourseDao courseDao();

    public abstract CourseStudentDao courseStudentDao();
}
