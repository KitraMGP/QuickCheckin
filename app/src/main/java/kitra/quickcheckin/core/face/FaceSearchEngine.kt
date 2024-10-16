package kitra.quickcheckin.core.face

abstract class FaceSearchEngine<T, U> {
    abstract fun init()
    abstract fun destroy()
    abstract fun addFaceTemplate(faceId: Int, face: T): Boolean
    abstract fun hasFaceTemplate(faceId: Int): Boolean
    abstract fun getFaceTemplate(faceId: Int): T?
    abstract fun removeFaceTemplate(faceId: Int): Boolean
    abstract fun updateFaceTemplate(faceId: Int, face: T): Boolean
    abstract fun analyze(frame: U): FaceSearchResult
}