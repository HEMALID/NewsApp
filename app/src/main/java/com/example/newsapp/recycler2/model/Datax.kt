package com.example.newsapp.recycler2.model

import android.os.Parcel
import android.os.Parcelable

data class DataX(
    val author: String,
    val content: String,
    val date: String,
    val id: String,
    val imageUrl: String,
    val readMoreUrl: String,
    val time: String,
    val title: String,
    val url: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(content)
        parcel.writeString(date)
        parcel.writeString(id)
        parcel.writeString(imageUrl)
        parcel.writeString(readMoreUrl)
        parcel.writeString(time)
        parcel.writeString(title)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataX> {
        override fun createFromParcel(parcel: Parcel): DataX {
            return DataX(parcel)
        }

        override fun newArray(size: Int): Array<DataX?> {
            return arrayOfNulls(size)
        }
    }
}