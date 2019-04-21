package com.hamilton.gamesskillst.data

import android.content.Context
import java.io.IOException
import java.nio.charset.Charset

class DataFilesManager(private val context: Context) {
    fun getAssetAsString(filename: String): String {
        return try {
            val inputStream = context.assets.open(filename)
            val result = inputStream.readBytes().toString(Charset.defaultCharset())
            inputStream.close()
            return result
        } catch (e: IOException) {
            ""
        }
    }
}