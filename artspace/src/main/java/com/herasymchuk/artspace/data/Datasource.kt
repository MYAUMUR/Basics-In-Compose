package com.herasymchuk.artspace.data

import com.herasymchuk.artspace.R
import com.herasymchuk.artspace.model.Photo

class Datasource {
    val allPhotos = listOf(
        Photo(
            imageId = R.drawable.photo1,
            title = "Biljash",
            description = "The cat Biljash with its mouth open"
        ),
        Photo(
            imageId = R.drawable.photo2,
            title = "Biljash",
            description = "The cat Biljash with its mouth shut"
        ),
        Photo(
            imageId = R.drawable.photo3,
            title = "Rainbow",
            description = "Beautiful rainbow"
        ),
        Photo(
            imageId = R.drawable.photo4,
            title = "Moon",
            description = "Beautiful moon"
        ),
        Photo(
            imageId = R.drawable.photo5,
            title = "Blue tractor",
            description = "We use a tractor to sow soybeans in the field"
        ),
        Photo(
            imageId = R.drawable.photo6,
            title = "Rose",
            description = "Beautiful rose"
        )
    )
}