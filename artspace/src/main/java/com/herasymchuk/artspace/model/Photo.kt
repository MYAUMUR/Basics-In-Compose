package com.herasymchuk.artspace.model

import androidx.annotation.DrawableRes

data class Photo(@DrawableRes val imageId: Int, val title: String, val description: String)
