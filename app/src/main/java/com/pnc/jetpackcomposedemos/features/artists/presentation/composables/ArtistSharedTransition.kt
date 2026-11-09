package com.pnc.jetpackcomposedemos.features.artists.presentation.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier

// The two scopes a shared element needs
// Artist directory -> artist list -> Artist card
// would change every signature, so we pass them through
// CompositionLocal instead

// for moving and morphing it from one position and size to another
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }
// animating whether or not its visible
val LocalPaneAnimatedScope = compositionLocalOf<AnimatedVisibilityScope?> { null }


// Marks an image as the same element on the list card and on the details page
// so it animates between them
// outside a sharedtransitionlayout (previews, ui tests) it does nothing
@Composable
fun Modifier.artistImageSharedElement(artistId: Int): Modifier {
    val sharedScope = LocalSharedTransitionScope.current ?: return this
    val animatedScope = LocalPaneAnimatedScope.current ?: return this

    return with (sharedScope) {
        // @ sign here refers to the function we are inside of
        // cannot use a bare "return" inside a trailing lambda
        this@artistImageSharedElement.sharedElement(
            sharedContentState = rememberSharedContentState(key = "artist-image-$artistId"),
            animatedVisibilityScope = animatedScope
        )
    }
}