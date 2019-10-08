package hulkdx.com.features.explore.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */

data class Cloth (
        val imageUrl: String,
        val userUrl: String?,
        val userName: String,
        val price: String
): Parcelable {

    // region Parcelable ---------------------------------------------------------------------------

    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString(),
            parcel.readString()!!,
            parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(imageUrl)
        dest.writeString(userUrl)
        dest.writeString(userName)
        dest.writeString(price)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Cloth> {
        override fun createFromParcel(parcel: Parcel): Cloth {
            return Cloth(parcel)
        }

        override fun newArray(size: Int): Array<Cloth?> {
            return arrayOfNulls(size)
        }
    }

    // endregion Parcelable ------------------------------------------------------------------------

}
