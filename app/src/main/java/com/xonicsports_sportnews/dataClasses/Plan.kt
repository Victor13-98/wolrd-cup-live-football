package com.xonicsports_sportnews.dataClasses

import android.os.Parcel
import android.os.Parcelable

data class Plan(
    val accessDays: String,
    val amount: String,
    val name: String,
    val odds: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(accessDays)
        parcel.writeString(amount)
        parcel.writeString(name)
        parcel.writeString(odds)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Plan> {
        override fun createFromParcel(parcel: Parcel): Plan {
            return Plan(parcel)
        }

        override fun newArray(size: Int): Array<Plan?> {
            return arrayOfNulls(size)
        }
    }
}