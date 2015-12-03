package com.marvinslullaby.kotlin101.data

import android.content.Context
import android.os.AsyncTask
import com.marvinslullaby.kotlin101.R
import com.marvinslullaby.kotlin101.data.vo.Color

import org.json.JSONArray
import org.json.JSONObject


class ColorsModel(val context: Context) {

  protected var colors: MutableList<Color>? = null
  protected val queuedCallbacks: MutableList<((List<Color>) -> Unit)> = arrayListOf()

  public fun getColors(callback: (List<Color>) -> Unit) {
    colors?.let {
      callback.invoke(it)
      return
    }

    queuedCallbacks.add(callback)
    if (queuedCallbacks.size == 1) {
      LoadColors().execute()
    }
  }

  private inner class LoadColors : AsyncTask<Void, Void, MutableList<Color>>() {

    override fun doInBackground(vararg params: Void?): MutableList<Color>? {
      val colors: MutableList<Color> = arrayListOf()
      val inputStream = context.resources.openRawResource(R.raw.colors)
      val bytes = ByteArray(inputStream.available())

      inputStream.read(bytes)
      inputStream.close()

      val json = String(bytes)
      val jsonColors = JSONArray(json)
      for (i in 0..jsonColors.length() - 1) {
        val jsonColor: JSONObject = jsonColors.getJSONObject(i)
        val colorInt: Int = android.graphics.Color.parseColor(jsonColor.getString("hex"))
        colors.add(Color(jsonColor.getString("name"), colorInt))
      }
      return colors
    }

    override fun onPostExecute(result: MutableList<Color>?) {
      result?.let {
        colors = result
        queuedCallbacks.forEach {
          it.invoke(result.toList())
        }
        queuedCallbacks.clear()
      }
    }

  }

}