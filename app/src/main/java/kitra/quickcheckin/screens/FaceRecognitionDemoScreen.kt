package kitra.quickcheckin.screens

import android.Manifest
import android.content.Context
import android.net.Uri
import android.util.Log
import android.util.SparseArray
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import com.huawei.hms.mlsdk.faceverify.MLFaceVerificationResult
import kitra.quickcheckin.components.DefaultTopAppBar
import kitra.quickcheckin.themes.MyApplicationTheme
import kitra.quickcheckin.viewmodels.FaceRecognitionDemoScreenViewModel
import java.util.concurrent.Executor
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

    /* 人脸识别 */
    var templateSet by remember { mutableStateOf(false) }
    // 存储识别结果
    var faceVerificationResultList by remember { mutableStateOf(SparseArray<MLFaceVerificationResult>()) }

    /* CameraX 分析接口 */
    cameraController?.setImageAnalysisAnalyzer(executor) { image ->
        if (templateSet) {
            val results = viewModel.analyze(image)
            for (i in 0 until results.size()) {
                Log.d(
                    "FaceVerification",
                    "Id: ${results[i].templateId}, Similarity: ${results[i].similarity}"
                )
            }
            faceVerificationResultList = results
        }
        Thread.sleep(100)
        image.close()
    }

    /* 人脸模板照片选择器 */
    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                Log.d("PhotoPicker", "photo:$uri")
                templateSet = viewModel.setTemplateFace(MLFrame.fromFilePath(context, uri))
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
            if (!isInPreview && cameraController != null) {
                // 权限授予后的行为
                if (permissionState!!.status.isGranted) {
                    CameraView(
                        cameraController,
                        previewView,
                        executor,
                        context,
                        imagePickerLauncher,
                        faceVerificationResultList
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
    cameraController: LifecycleCameraController,
    previewView: PreviewView,
    executor: Executor,
    context: Context,
    imagePickerLauncher: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    faceVerificationResultList: SparseArray<MLFaceVerificationResult>
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

        Text(
            "结果数量：${faceVerificationResultList.size()}",
            color = MaterialTheme.colorScheme.onBackground
        )

        LazyColumn {
            items(faceVerificationResultList.size()) {
                run {
                    Text(
                        text = "人脸：${faceVerificationResultList[it].templateId}，相似度：${faceVerificationResultList[it].similarity}",
                        color = MaterialTheme.colorScheme.onBackground
                    )
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
