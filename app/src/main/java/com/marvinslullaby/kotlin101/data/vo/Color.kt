package com.marvinslullaby.kotlin101.data.vo

import android.graphics.Color
import java.io.Serializable


public data class Color(val name:String, val color:Int):Serializable{

  companion object{
    @JvmStatic public val serialVersionUID = 0L;
  }

  public val complimentaryColor:Int

  init {
    complimentaryColor = Color.rgb(
      255 - Color.red(color),
      255 - Color.green(color),
      255 - Color.blue(color)
    )
  }


}