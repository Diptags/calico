package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.opengl

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet

class OpenGLSurfaceView : GLSurfaceView {
    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        init()
    }

    private fun init() {
        setEGLContextClientVersion(2)
        preserveEGLContextOnPause = true
        setRenderer(OpenGLRenderer())
    }
}