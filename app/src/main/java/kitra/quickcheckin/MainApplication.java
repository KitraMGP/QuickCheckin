package kitra.quickcheckin;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.huawei.agconnect.AGConnectInstance;

import kitra.quickcheckin.data.local.AppDatabase;

/**
 * 应用的 Application 类
 */
public class MainApplication extends Application {

    // 用于存放应用的 AppDatabase 实例
    private static AppDatabase database;

    /**
     * 获取应用的数据库实例。<br>
     * 应用的数据库实例遵循<strong>单例模式</strong>，即整个应用只有唯一的数据库实例。
     *
     * @param context 需要传入的 {@link Context} 实例，在 <code>@Composable</code> 函数中使用
     *                <code>LocalContext.current</code> 获取。
     * @return 获取到的数据库实例
     */
    public static AppDatabase getDatabase(Context context) {
        // 对 database 判空，若为空则创建 AppDatabase 实例
        if (database == null) {
            // 将数据库实例化代码置于同步块中，确保不会有两个线程同时创建数据库实例
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
        }
    }
}
