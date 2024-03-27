package by.radiance.space.pictures.presenter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.radiance.space.pictures.domain.entity.settings.ApplicationSettings
import by.radiance.space.pictures.domain.entity.settings.ApplicationTheme
import by.radiance.space.pictures.domain.entity.settings.CornersSize
import by.radiance.space.pictures.domain.entity.settings.ListArrangement
import by.radiance.space.pictures.domain.entity.settings.SafeArea
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.navigation.ScreenType
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.theme.CardGray
import by.radiance.space.pictures.presenter.ui.theme.safeArea
import by.radiance.space.pictures.presenter.ui.utils.AnimatedVisibilityBottomNavigation
import by.radiance.space.pictures.presenter.ui.utils.BottomNavigationScreenItem
import by.radiance.space.pictures.presenter.ui.utils.ScaffoldWithConstraints
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.ui.utils.heightWindowSize

@Composable
fun Root(
    darkTheme: Boolean,
    cornersSize: CornersSize,
    safeArea: SafeArea,
    listArrangement: ListArrangement,
    router: Router,
) {
    AstronomyPicturesTheme(
        darkTheme = darkTheme,
        cornersSize = cornersSize.size,
        safeArea = safeArea.size.dp,
        listArrangement = listArrangement.size.dp,
    ) {
        var bottomBarState by rememberSaveable { (mutableStateOf(true)) }

        ScaffoldWithConstraints(
            bottomBar = {
                BottomBar(
                    bottomBarState = bottomBarState,
                    router = router,
                )
            },
            content = { innerPadding ->
                Content(
                    bottomBarState = bottomBarState,
                    bottomMenu = router.bottomMenu,
                    router = router,
                    innerPadding = innerPadding,
                    heightWindowSize = heightWindowSize,
                    updateBottomBarVisibility = { isVisible ->
                        bottomBarState = isVisible
                    }
                )
            }
        )
    }
}

@Composable
private fun Content(
    bottomBarState: Boolean,
    bottomMenu: List<ScreenType>,
    router: Router,
    innerPadding: PaddingValues,
    heightWindowSize: WindowSize,
    updateBottomBarVisibility: (Boolean) -> Unit,
) {
    Row {
        if (bottomBarState && heightWindowSize == WindowSize.Compact) {
            Column(
                modifier = Modifier
                    .background(CardGray),
            ) {
                bottomMenu.forEach { screen ->
                    BottomNavigationScreenItem(
                        screenType = screen,
                        router = router,
                    )
                }
            }
        }
        router.NavigationHost(
            modifier = Modifier
                .padding(innerPadding),
            updateBottomBarVisibility = updateBottomBarVisibility,
            heightWindowSize = heightWindowSize,
        )
    }
}

@Composable
private fun BoxWithConstraintsScope.BottomBar(
    bottomBarState: Boolean,
    router: Router,
) {
    AnimatedVisibilityBottomNavigation(
        visible = bottomBarState && heightWindowSize != WindowSize.Compact,
        modifier = Modifier,
        backgroundColor = CardGray,
    ) {
        router.bottomMenu.forEach { screen ->
            BottomNavigationScreenItem(
                screen,
                router,
            )
        }
    }
}
