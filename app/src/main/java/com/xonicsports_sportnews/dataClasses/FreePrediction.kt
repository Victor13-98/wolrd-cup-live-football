package com.xonicsports_sportnews.dataClasses

import android.os.Parcel
import android.os.Parcelable

data class FreePrediction(
    val date: String,
    val fixture: String,
    val prediction: String
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(fixture)
        parcel.writeString(prediction)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FreePrediction> {
        override fun createFromParcel(parcel: Parcel): FreePrediction {
            return FreePrediction(parcel)
        }

        override fun newArray(size: Int): Array<FreePrediction?> {
            return arrayOfNulls(size)
        }
    }

}