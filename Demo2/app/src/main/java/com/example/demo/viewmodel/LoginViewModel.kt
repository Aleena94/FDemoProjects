package com.example.demo.viewmodel

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.demo.model.login.LoginModel
import com.example.demo.repository.LoginRepository
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class LoginViewModel : ViewModel() {

    private var liveDataLogin: LiveData<LoginModel>? = null
    private val repository: LoginRepository = LoginRepository()
    fun insertData(context: Context, username: String, password: String) {
        repository.insertData(context, username, password)
    }

    fun getLoginDetails(context: Context, username: String): LiveData<LoginModel>? {
        liveDataLogin = repository.getLoginDetails(context, username)
        return liveDataLogin
    }

    fun deleteUser(context: Context, username: String) {
        repository.deleteByUsername(context, username)
    }


    fun copyFileToInternalStorage(context: Context, bitmap: Bitmap): String? {
        //  val drawable = ContextCompat.getDrawable(context, drawableId)
        //  val bitmap = (drawable as BitmapDrawable).bitmap

        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path: String = MediaStore.Images.Media.insertImage(
            context.contentResolver,
            bitmap,
            "D_" + System.currentTimeMillis(),
            null
        )
        val uri = Uri.parse(path)

        val returnCursor: Cursor = context.contentResolver.query(
            uri, arrayOf(
                OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE
            ), null, null, null
        )!!


        /*
     * Get the column indexes of the data in the Cursor,
     *     * move to the first row in the Cursor, get the data,
     *     * and display it.
     * */
        val nameIndex: Int = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        val sizeIndex: Int = returnCursor.getColumnIndex(OpenableColumns.SIZE)
        returnCursor.moveToFirst()
        val name: String = returnCursor.getString(nameIndex)
        returnCursor.getLong(sizeIndex).toString()
        returnCursor.close()
        val dir = File(context.filesDir.toString() + "/" + "demo")
        if (!dir.exists()) {
            dir.mkdir()
        }
        val output = File(context.filesDir.toString() + "/" + "demo" + "/" + name)
        try {
            val inputStream: InputStream = context.contentResolver.openInputStream(uri)!!
            val outputStream = FileOutputStream(output)
            var read: Int
            val bufferSize = 1024
            val buffers = ByteArray(bufferSize)
            while (inputStream.read(buffers).also { read = it } != -1) {
                outputStream.write(buffers, 0, read)
            }
            inputStream.close()
            outputStream.close()
        } catch (e: Exception) {
            e.message?.let { Log.e("Exception", it) }
        }
        return output.path
    }


}