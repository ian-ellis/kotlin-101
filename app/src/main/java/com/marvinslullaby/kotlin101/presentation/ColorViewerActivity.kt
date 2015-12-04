package com.marvinslullaby.kotlin101.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.marvinslullaby.kotlin101.R
import com.marvinslullaby.kotlin101.data.vo.Color


class ColorViewerActivity : Activity() {
  companion object {
    private val COLOR: String = "color"
    @JvmStatic public fun getIntent(context: Context, color:Color): Intent {
      val intent = Intent(context, ColorViewerActivity::class.java)
      intent.putExtra(ColorViewerActivity.COLOR, color)
      return intent
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.color_viewer)

    val color = intent.extras.getSerializable(COLOR)

    if (color is Color) {
      val root = findViewById(R.id.color_viewer_wrapper)
      val name = findViewById(R.id.color_viewer_color_name) as TextView

      name.text = color.name
      name.setTextColor(color.complimentaryColor)
      root.setBackgroundColor(color.color)
    }
  }
}