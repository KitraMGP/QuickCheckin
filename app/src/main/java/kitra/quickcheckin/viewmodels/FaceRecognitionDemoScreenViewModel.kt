package kitra.quickcheckin.viewmodels

import android.graphics.Bitmap
import androidx.camera.core.ImageProxy
import androidx.lifecycle.ViewModel
import com.huawei.hms.mlsdk.common.MLFrame
import kitra.quickcheckin.core.face.impl.HMSFaceSearchEngine

class FaceRecognitionDemoScreenViewModel: ViewModel() {
    private lateinit var faceSearchEngine: HMSFaceSearchEngine
    private var newFaceId = 0

    init {
        initialize()
    }

    private fun initialize() {
        faceSearchEngine = HMSFaceSearchEngine()
        faceSearchEngine.init()
    }

    fun addTemplateFace(template: Bitmap): Int {
        val success = faceSearchEngine.addFaceTemplate(newFaceId, template)
        if(success) {
            newFaceId++
            return newFaceId - 1
        } else {
            return -1
        }
    }

    fun analyze(image: ImageProxy): Int {
        val frame = MLFrame.rotate(image.toBitmap(), image.imageInfo.rotationDegrees / 90)
        val result = faceSearchEngine.analyze(frame)
        return if(result.found) result.faceId else -1
    }

    override fun onCleared() {
        super.onCleared()
        faceSearchEngine.destroy()
    }
}