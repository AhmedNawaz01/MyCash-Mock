package com.practice.initialarchitecture.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccountProperties(
    val pk: Int? = null,
    val email: String? = null
) : Parcelable
