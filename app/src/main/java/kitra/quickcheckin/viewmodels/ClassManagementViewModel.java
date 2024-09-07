package kitra.quickcheckin.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import kitra.quickcheckin.data.local.AppDatabase;
import kitra.quickcheckin.data.local.LocalDataRepository;
import kitra.quickcheckin.data.local.dao.TeachingClassDao;
import kitra.quickcheckin.data.local.datamodel.TeachingClass;

public class ClassManagementViewModel extends ViewModel {
    private final AppDatabase database;
    private final TeachingClassDao teachingClassDao;
    private final LiveData<List<TeachingClass>> classList;
    private final LocalDataRepository localDataRepository;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private int idx = 0;

    public ClassManagementViewModel(AppDatabase database) {
        this.database = database;
        this.teachingClassDao = database.teachingClassDao();
        this.localDataRepository = new LocalDataRepository(database, disposable);
        //this.update();
        classList = localDataRepository.getAllClasses();
    }

    public LiveData<List<TeachingClass>> getAllClasses() {
        return classList;
    }

    public void update() {
        //this.classList =  teachingClassDao.getAll();
    }

    public void addTest(String name) {
        localDataRepository.addClass(new TeachingClass(0, 1, "course" + idx++));
    }

    public void delete(int uniqueId, Action onComplete, Consumer<? super Throwable> onError) {
        localDataRepository.deleteClass(onComplete, onError, new TeachingClass(uniqueId));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
