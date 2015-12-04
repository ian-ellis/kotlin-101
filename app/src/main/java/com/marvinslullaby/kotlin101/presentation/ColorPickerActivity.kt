package com.marvinslullaby.kotlin101.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.marvinslullaby.kotlin101.R
import com.marvinslullaby.kotlin101.data.ColorsModel


class ColorPickerActivity : Activity() {

  protected val recycler: RecyclerView by lazy { findViewById(R.id.recycler) as RecyclerView }
  protected val adapter = ColorAdapter()
  protected val model = ColorsModel(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.color_picker)

    recycler.layoutManager = LinearLayoutManager(this)
    recycler.adapter = adapter
    adapter.selected = {
      val intent = ColorViewerActivity.getIntent(this, it)
      startActivity(intent)
    }
    model.getColors {
      adapter.colors = it
    }
  }
}