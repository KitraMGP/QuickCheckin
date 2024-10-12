package kitra.quickcheckin.viewmodels

import android.util.Log
import android.util.SparseArray
import androidx.camera.core.ImageProxy
import androidx.core.util.forEach
import androidx.lifecycle.ViewModel
import com.huawei.hms.mlsdk.common.MLFrame
import com.huawei.hms.mlsdk.faceverify.MLFaceVerificationAnalyzer
import com.huawei.hms.mlsdk.faceverify.MLFaceVerificationAnalyzerFactory
import com.huawei.hms.mlsdk.faceverify.MLFaceVerificationAnalyzerSetting
import com.huawei.hms.mlsdk.faceverify.MLFaceVerificationResult

class FaceRecognitionDemoScreenViewModel: ViewModel() {
    private lateinit var faceVerificationAnalyzer: MLFaceVerificationAnalyzer
    private lateinit var templateFace: MLFrame
    private var templateSet = false

    init {
        initialize()
    }

    private fun initialize(): Unit {
        faceVerificationAnalyzer = MLFaceVerificationAnalyzerFactory.getInstance().getFaceVerificationAnalyzer(
            MLFaceVerificationAnalyzerSetting.Factory().setMaxFaceDetected(1).create())
    }

    fun setTemplateFace(template: MLFrame): Boolean {
        templateFace = template
        val num = faceVerificationAnalyzer.setTemplateFace(templateFace).size
        templateSet = num > 0
        return templateSet
    }

    fun analyze(image: ImageProxy): SparseArray<MLFaceVerificationResult> {
        if(!templateSet) return SparseArray<MLFaceVerificationResult>()
        val frame = MLFrame.fromBitmap(MLFrame.rotate(image.toBitmap(), image.imageInfo.rotationDegrees / 90))
        val results = faceVerificationAnalyzer.analyseFrame(frame)
        results.forEach { _, value ->  Log.i("FaceVerification", "Face: ${value.templateId}, Similarity: ${value.similarity}")}
        return results
    }

    override fun onCleared() {
        super.onCleared()
        faceVerificationAnalyzer.stop()
    }
}