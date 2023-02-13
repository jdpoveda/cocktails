package com.juanpoveda.cocktails.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UiIngredient (
    val ingredientName: String?,
    val measure: String?
) : Parcelable
