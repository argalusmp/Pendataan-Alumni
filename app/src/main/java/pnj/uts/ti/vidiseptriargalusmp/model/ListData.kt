package pnj.uts.ti.vidiseptriargalusmp.model

import android.os.Parcel
import android.os.Parcelable

data class ListData(
    var judul: Int,
    var deskripsi: Int,
    var ringkasan: Int,
    var image: Int
    ) : Parcelable
{

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(judul)
        parcel.writeInt(deskripsi)
        parcel.writeInt(ringkasan)
        parcel.writeInt(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListData> {
        override fun createFromParcel(parcel: Parcel): ListData {
            return ListData(parcel)
        }

        override fun newArray(size: Int): Array<ListData?> {
            return arrayOfNulls(size)
        }
    }
}