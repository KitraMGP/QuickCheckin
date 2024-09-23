package kitra.quickcheckin.screens

import android.Manifest
import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kitra.quickcheckin.themes.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun ComposableFaceRecognitionDemoScreen(navController: NavController) {
    val context = LocalContext.current
    val isInPreview = LocalInspectionMode.current
    // rememberPermissionState在Preview中不能使用，所以预览状态下将permissionState设为null
    val permissionState =
        if (isInPreview) null else rememberPermissionState(permission = Manifest.permission.CAMERA)
    // 相机预览控件
    val previewView = remember { PreviewView(context) }
    // 相机需要控制器和LifecycleOwner
    val cameraController = remember { LifecycleCameraController(context) }
    val lifecycleOwner = LocalLifecycleOwner.current
    // 指定要使用的摄像头
    cameraController.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    // 将控制器绑定到生命周期上
    cameraController.bindToLifecycle(lifecycleOwner)
    // 将控制器绑定到预览控件上
    previewView.controller = cameraController


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            title = { Text("人脸识别测试") },
            navigationIcon = {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, "返回")
                }
            },
            modifier = Modifier.shadow(elevation = 4.dp)
        )

        Column(modifier = Modifier.weight(1f)) {
            // 权限功能在Preview中不可用
            if (!isInPreview) {
                // 权限授予后的行为
                if (permissionState!!.status.isGranted) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        AndroidView(
                            factory = { previewView },
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                        )
                    }
                } else {
                    Button(onClick = {
                        permissionState.launchPermissionRequest()
                    }) {
                        Text("请求权限")
                    }
                }
            }
        }

    }
}

@Preview
@Composable
private fun Preview() {
    MyApplicationTheme {
        ComposableFaceRecognitionDemoScreen(rememberNavController())
    }
}