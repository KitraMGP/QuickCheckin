package kitra.quickcheckin.viewmodels.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

import kitra.quickcheckin.data.local.AppDatabase;

public class HomeScreenViewModelFactory implements ViewModelProvider.Factory {

    private final AppDatabase database;

    public HomeScreenViewModelFactory(AppDatabase database) {
        this.database = database;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(AppDatabase.class).newInstance(database);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
