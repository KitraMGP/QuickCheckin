package kitra.quickcheckin.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import kitra.quickcheckin.data.local.AppDatabase;
import kitra.quickcheckin.data.local.LocalDataRepository;
import kitra.quickcheckin.data.local.datamodel.Student;

public class StudentManagementViewModel extends ViewModel {
    private final LocalDataRepository localDataRepository;
    private final CompositeDisposable disposable;

    public StudentManagementViewModel(AppDatabase database) {
        disposable = new CompositeDisposable();
        localDataRepository = new LocalDataRepository(database, disposable);
    }

    public void addStudent(Action onComplete, Consumer<? super Throwable> onError, Student... students) {
        localDataRepository.addStudents(onComplete, onError, students);
    }

    public void deleteStudent(Action onComplete, Consumer<? super Throwable> onError, Student... students) {
        localDataRepository.deleteStudents(onComplete, onError, students);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    public LiveData<List<Student>> getAllStudents() {
        return localDataRepository.getAllStudents();
    }
}
