package kitra.quickcheckin.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import kitra.quickcheckin.data.local.AppDatabase;
import kitra.quickcheckin.data.local.LocalDataRepository;

public class HomeScreenViewModel extends ViewModel {

    private final LocalDataRepository localDataRepository;
    private final CompositeDisposable disposable;

    public HomeScreenViewModel(AppDatabase database) {
        disposable = new CompositeDisposable();
        localDataRepository = new LocalDataRepository(database, disposable);
    }

    public LiveData<Integer> getStudentCount() {
        return localDataRepository.getStudentCount();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
