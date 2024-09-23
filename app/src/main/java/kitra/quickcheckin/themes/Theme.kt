package kitra.quickcheckin.themes

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

//暗色主题色彩定义
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

//亮色主题色彩定义
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    //检测系统开的是不是深色模式
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    //定义常量 色彩组合
    //dynamicColor：bool型。为 true 表示应用支持动态颜色（在Android 12中才引入）
    //检测 Android 版本是否大于等于 12
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current  //模板部分，访问应用资源和类
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            //根据系统是否开启深色模式 设置颜色主题，亮 -》 darkTheme==0 -》 LightColor
        }
        //如果darkTheme为True那么colorScheme=DarkColorScheme
        darkTheme -> DarkColorScheme
        //否则选亮色模式
        else -> LightColorScheme
    }

    MaterialTheme(  //库方法，材质主题
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}