package com.example.myapplication.data.model

import android.os.Parcel
import android.os.Parcelable

data class Category(
    val createdAt: String,
    val id: Int,
    val name: String,
    val product: List<Product>,
    val updatedAt: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString(),
        TODO("product"),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(createdAt)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }
}