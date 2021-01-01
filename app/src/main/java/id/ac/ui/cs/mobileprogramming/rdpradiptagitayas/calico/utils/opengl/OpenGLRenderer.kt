package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.opengl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.os.SystemClock
import android.util.Log
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

internal class OpenGLRenderer : GLSurfaceView.Renderer {
    private var squareLeft: Square? = null
    private var squareRight: Square? = null
    private val mViewMatrix = FloatArray(16)
    private val mMVPMatrix = FloatArray(16)
    private val mProjectionMatrix = FloatArray(16)
    private val mRotationMatrix = FloatArray(16)
    private var mTempMatrix = FloatArray(16)
    private val mModelMatrix = FloatArray(16)
    private var dx = 0f
    override fun onSurfaceCreated(
        gl10: GL10,
        eglConfig: EGLConfig
    ) {
        GLES20.glClearColor(0.03921568627f, 0.56470588235f, 0.94509803921f, 1f)
        squareLeft = Square()
        squareRight = Square()
    }

    override fun onSurfaceChanged(gl10: GL10, width: Int, height: Int) {
        // Adjust the viewport based on geometry changes,
        // such as screen rotation
        GLES20.glViewport(0, 0, width, height)
        val ratio = width.toFloat() / height
        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1f, 1f, 3f, 7f)
    }

    override fun onDrawFrame(gl10: GL10) {
        // Draw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        rotateSquare(squareLeft, 1)
        rotateSquare(squareRight, -1)
    }

    private fun rotateSquare(square: Square?, direction: Int) {
        Matrix.setIdentityM(mModelMatrix, 0) // initialize to identity matrix
        Matrix.translateM(
            mModelMatrix,
            0,
            direction * dx,
            0f,
            0f
        ) // translation to the left
        dx += 0.003f

        // Set the camera position (View matrix)
        Matrix.setLookAtM(mViewMatrix, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0)

        // Create a rotation transformation for the square
        val time = SystemClock.uptimeMillis() % 4000L.toFloat()
        val angle = direction * 0.2f * time.toInt()
        Matrix.setRotateM(mRotationMatrix, 0, angle, 0f, 0f, -1.0f)
        mTempMatrix = mModelMatrix.clone()
        Matrix.multiplyMM(mModelMatrix, 0, mTempMatrix, 0, mRotationMatrix, 0)
        mTempMatrix = mMVPMatrix.clone()
        Matrix.multiplyMM(mMVPMatrix, 0, mTempMatrix, 0, mModelMatrix, 0)
        if (square != null) {
            square.draw(mMVPMatrix)
        }
    }

    companion object {
        fun loadShader(type: Int, shaderCode: String?): Int {
            // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
            // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
            val shader = GLES20.glCreateShader(type)
            // add the source code to the shader and compile it
            GLES20.glShaderSource(shader, shaderCode)
            GLES20.glCompileShader(shader)
            return shader
        }

        fun checkGlError(glOperation: String) {
            var error: Int
            while (GLES20.glGetError().also { error = it } != GLES20.GL_NO_ERROR) {
                Log.e("OPENGL", "$glOperation: glError $error")
                throw RuntimeException("$glOperation: glError $error")
            }
        }
    }
}