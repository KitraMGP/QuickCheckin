package kitra.quickcheckin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import kitra.quickcheckin.navigation.NavGraph
import kitra.quickcheckin.themes.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  //界面初始化模板
        setContent {    //界面内容
            MyApplicationTheme {    //方法
                NavGraph()
            }
        }
    }
}
