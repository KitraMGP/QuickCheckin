package kitra.quickcheckin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import kitra.quickcheckin.navigation.NavGraph
import kitra.quickcheckin.themes.MyApplicationTheme

/**
 * 应用的主 Activity，这是一个支持 Compose 的 Activity
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 启用边缘到边缘模式
        // 即让状态栏颜色和界面背景颜色统一
        enableEdgeToEdge()
        // 在 setContent 中指定界面中的 Compose 组件
        setContent {
            // 对 Activity 设定全局主题
            MyApplicationTheme {
                // 指定导航图组件为界面内容
                // 让 NavHost 来控制应用启动时的界面
                NavGraph()
            }
        }
    }
}
