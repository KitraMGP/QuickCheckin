package kitra.quickcheckin;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.huawei.agconnect.AGConnectInstance;

import kitra.quickcheckin.data.local.AppDatabase;
//以库中的类Application为父类，创建了子类MainApplication
public class MainApplication extends Application {
//连接应用数据库
    private static AppDatabase database;
//确认数据库是否连接 若未连接 则
    public static AppDatabase getDatabase(Context context) {
        if (database == null) {
            synchronized (AppDatabase.class) {
                database = Room.databaseBuilder(context, AppDatabase.class, "app_database").fallbackToDestructiveMigration().build();
            }
        }
        return database;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化华为AGC
        if (AGConnectInstance.getInstance() == null) {
            AGConnectInstance.initialize(getApplicationContext());
        }   //摄像头代码模板
    }
}
