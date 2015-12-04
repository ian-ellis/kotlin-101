package com.marvinslullaby.kotlin101.presentation

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.marvinslullaby.kotlin101.R
import com.marvinslullaby.kotlin101.data.vo.Color


class ColorAdapter : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

  private var layoutInflater: LayoutInflater? = null
  public var selected: ((Color) -> Unit)? = null

  public var colors: List<Color> = listOf()
    set(value) {
      field = value
      notifyDataSetChanged()
    }
    get() {
      return field.map { it }
    }


  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val color = colors[position]
    holder.bind(color)

  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val inflater: LayoutInflater = layoutInflater ?: {
      layoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
      layoutInflater!!
    }.invoke()

    return ViewHolder(inflater.inflate(R.layout.color_picker_list_item, parent, false))
  }

  override fun getItemCount() = colors.size

  public inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    public val title: TextView

    init {
      title = view.findViewById(R.id.txt_title) as TextView
    }

    public fun bind(color: Color) {
      title.text = color.name
      title.setTextColor(color.complimentaryColor)
      itemView.setBackgroundColor(color.color)
      itemView.setOnClickListener {
        selected?.invoke(color)
      }
    }
  }
}