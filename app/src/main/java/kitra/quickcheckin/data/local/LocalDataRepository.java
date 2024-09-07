package kitra.quickcheckin.data.local;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kitra.quickcheckin.data.local.dao.CourseDao;
import kitra.quickcheckin.data.local.dao.StudentClassRelationDao;
import kitra.quickcheckin.data.local.dao.StudentDao;
import kitra.quickcheckin.data.local.dao.TeachingClassDao;
import kitra.quickcheckin.data.local.datamodel.TeachingClass;

public class LocalDataRepository {
    private final TeachingClassDao teachingClassDao;
    private final StudentDao studentDao;
    private final StudentClassRelationDao studentClassRelationDao;
    private final CourseDao courseDao;

    private final CompositeDisposable disposable;

    public LocalDataRepository(AppDatabase database, CompositeDisposable disposable) {
        this.teachingClassDao = database.teachingClassDao();
        this.studentDao = database.studentDao();
        this.studentClassRelationDao = database.studentClassRelationDao();
        this.courseDao = database.courseDao();
        this.disposable = disposable;
    }

    public LiveData<List<TeachingClass>> getAllClasses() {
        return teachingClassDao.getAll();
    }

    public void addClass(TeachingClass teachingClass) {
        disposable.add(
                teachingClassDao.insert(teachingClass)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
        );
    }

    public void addClass(@NonNull Action onComplete, @NonNull Consumer<? super Throwable> onError, TeachingClass... teachingClass) {
        disposable.add(
                teachingClassDao.insert(teachingClass)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onComplete, onError)
        );
    }

    public void deleteClass(TeachingClass... teachingClasses) {
        disposable.add(
                teachingClassDao.delete(teachingClasses)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
        );
    }

    public void deleteClass(@NonNull Action onComplete, @NonNull Consumer<? super Throwable> onError, TeachingClass... teachingClasses) {
        disposable.add(
                teachingClassDao.delete(teachingClasses)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onComplete, onError)
        );
    }

    public LiveData<TeachingClass> getClassByUniqueId(int uniqueId) {
        return teachingClassDao.getClassByUniqueId(uniqueId);
    }

}
