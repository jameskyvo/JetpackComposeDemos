package com.pnc.jetpackcomposedemos

// no state information so we can use data class
data class Artist (
    val id: Int,
    val name: String,
    val genre: String,
    val location: String,
    val imageUrl: String,
    val description: String,
    val tags: String
) {

    // we would like a static function to generate some fake artists
    // but kotlin does not have a static keyword to create class-level functions
    // Instead, we do it using a "companion object"
    companion object {
        // any methods in here become static methods
        fun generateArtists(num: Int): List<Artist> = List(num) { index ->
            Artist(
                id = index +1,
                name = "Artist ${index + 1}",
                genre = listOf("Rock", "Pop", "Jazz", "Acoustic", "Classical").random(),
                location = listOf("New York", "Los Angelas", "Miami").random(),
                imageUrl = "image/fakeimage.png",
                description = "Some fake details",
                tags = "tag,tag,tag"
            )
        }
    }

}