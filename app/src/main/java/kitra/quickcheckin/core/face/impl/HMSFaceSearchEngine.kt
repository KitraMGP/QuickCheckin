package kitra.quickcheckin.core.face.impl

import android.graphics.Bitmap
import android.util.Log
import androidx.core.graphics.scale
import androidx.core.util.isEmpty
import com.huawei.hms.mlsdk.common.MLFrame
import com.huawei.hms.mlsdk.faceverify.MLFaceVerificationAnalyzer
import com.huawei.hms.mlsdk.faceverify.MLFaceVerificationAnalyzerFactory
import com.huawei.hms.mlsdk.faceverify.MLFaceVerificationAnalyzerSetting
import kitra.quickcheckin.core.face.FaceSearchEngine
import kitra.quickcheckin.core.face.FaceSearchResult
import java.util.Collections
import java.util.PriorityQueue

class HMSFaceSearchEngine : FaceSearchEngine<Bitmap, Bitmap>() {
    private lateinit var faceVerificationAnalyzer: MLFaceVerificationAnalyzer
    private val faceTemplates = Collections.synchronizedMap(mutableMapOf<Int, Bitmap>())
    private val leastSimilarity = 0.85f

    override fun init() {
        faceVerificationAnalyzer =
            MLFaceVerificationAnalyzerFactory.getInstance().getFaceVerificationAnalyzer(MLFaceVerificationAnalyzerSetting.Factory().setMaxFaceDetected(1).create())
        return
    }

    override fun destroy() {
        faceVerificationAnalyzer.stop()
    }

    override fun hasFaceTemplate(faceId: Int): Boolean {
        return faceTemplates.containsKey(faceId)
    }

    override fun getFaceTemplate(faceId: Int): Bitmap? {
        return faceTemplates[faceId]
    }

    override fun removeFaceTemplate(faceId: Int): Boolean {
        if (faceTemplates.containsKey(faceId)) {
            faceTemplates.remove(faceId)
            return true
        } else return false
    }

    override fun analyze(frame: Bitmap): FaceSearchResult {
        faceVerificationAnalyzer.stop()
        init()
        if (faceTemplates.isEmpty()) return FaceSearchResult(
            false,
            0
        )   // 初始大小为0时，创建PriorityQueue会报错
        val results = PriorityQueue(
            faceTemplates.size,
            Comparator.comparing(Pair<Int, Float>::second).reversed()
        )
        val factor = if(frame.width > frame.height) 600f / frame.width
        else 600f / frame.height
        val scaledFrame = frame.scale((frame.width * factor).toInt(), (frame.height * factor).toInt())
        synchronized(faceTemplates) {
            faceTemplates.forEach { (faceId, face) ->
                faceVerificationAnalyzer.setTemplateFace(MLFrame.fromBitmap(face))
                val mlResult = faceVerificationAnalyzer.analyseFrame(MLFrame.fromBitmap(scaledFrame))
                if (!mlResult.isEmpty()) results.add(Pair(faceId, mlResult[0].similarity))
            }
        }
        if (results.isEmpty()) return FaceSearchResult(false, 0)
        val last = results.peek()
        Log.d("FaceVerification|Engine", "LastFace: ${last!!.first}, Similarity: ${last.second}")
        return if (last.second >= leastSimilarity) FaceSearchResult(true, last.first)
        else FaceSearchResult(false, 0)
    }

    override fun updateFaceTemplate(faceId: Int, face: Bitmap): Boolean {
        return addFaceTemplate(faceId, face)
    }

    override fun addFaceTemplate(faceId: Int, face: Bitmap): Boolean {
        val factor = if(face.width > face.height) 600f / face.width
        else 600f / face.height
        val scaledBitmap = face.scale((face.width * factor).toInt(), (face.height * factor).toInt())
        faceTemplates[faceId] = scaledBitmap
        return true
    }
}