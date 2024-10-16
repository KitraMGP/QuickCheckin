package kitra.quickcheckin.screens

import android.Manifest
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.huawei.hms.mlsdk.common.MLFrame
import kitra.quickcheckin.components.DefaultTopAppBar
import kitra.quickcheckin.themes.MyApplicationTheme
import kitra.quickcheckin.viewmodels.FaceRecognitionDemoScreenViewModel
import java.util.concurrent.Executors

/**
 * 人脸识别测试界面
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ComposableFaceRecognitionDemoScreen(navController: NavController) {
    val context = LocalContext.current
    val isInPreview = LocalInspectionMode.current
    val viewModel = viewModel(FaceRecognitionDemoScreenViewModel::class)

    /* 权限部分 */
    // rememberPermissionState在Preview中不能使用，所以预览状态下将permissionState设为null
    val permissionState =
        if (isInPreview) null else rememberPermissionState(permission = Manifest.permission.CAMERA)

    /* 相机部分 */
    val executor = remember { Executors.newSingleThreadExecutor() }
    // 相机预览控件
    val previewView = remember { PreviewView(context) }

    // 相机需要控制器和LifecycleOwner
    val cameraController =
        if (isInPreview) null else remember { LifecycleCameraController(context) }
    val lifecycleOwner = if (isInPreview) null else LocalLifecycleOwner.current

    if (lifecycleOwner != null && cameraController != null) {
        // 指定要使用的摄像头
        cameraController.cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

        // 将控制器绑定到生命周期上
        cameraController.bindToLifecycle(lifecycleOwner)
        // 将控制器绑定到预览控件上
        previewView.controller = cameraController
    }

    /* 人脸识别结果 */
    var faceId by remember { mutableIntStateOf(-1) }

    /* CameraX 分析接口 */
    cameraController?.setImageAnalysisAnalyzer(executor) { image ->
        faceId = viewModel.analyze(image)
        image.close()
    }

    /* 人脸模板照片选择器 */
    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                Log.d("PhotoPicker", "photo:$uri")
                val r = viewModel.addTemplateFace(MLFrame.fromFilePath(context, uri).readBitmap())
                Toast.makeText(context, if(r >= 0) "成功，faceId=${r}" else "失败", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("PhotoPicker", "None")
            }
        }

    /* 界面主体布局 */
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // App顶栏
        DefaultTopAppBar(
            title = { Text("人脸识别测试") },
            navigationIcon = {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, "返回")
                }
            }
        )

        // 主体容器
        Column {
            // 摄像头预览组件
            // 权限功能在Preview中不可用
            if (!isInPreview) {
                // 权限授予后的行为
                if (permissionState!!.status.isGranted) {
                    CameraView(
                        previewView,
                        imagePickerLauncher,
                        faceId
                    )
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

@Composable
private fun CameraView(
    previewView: PreviewView,
    imagePickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    faceId: Int
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        // 相机预览
        AndroidView(
            factory = {
                previewView.clipToOutline = true
                previewView
            }, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            // 导入照片按钮
            Button(onClick = {
                imagePickerLauncher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            }) {
                Text("导入人脸照片")
            }
        }
        if(faceId >= 0) {
            Text(text = "识别到人脸，faceId=${faceId}", color = MaterialTheme.colorScheme.onBackground)
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
