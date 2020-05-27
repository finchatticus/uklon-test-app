package ua.vlad.uklon.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PostVO(
    val id: Int,
    val idUser: Int,
    val title: String,
    val body: String
): Parcelable