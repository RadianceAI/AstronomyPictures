package by.radiance.space.pictures.presenter.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import by.radiance.space.pictures.presenter.navigation.Router
import by.radiance.space.pictures.presenter.navigation.ScreenType
import by.radiance.space.pictures.presenter.navigation.screen.TodayScreen
import by.radiance.space.pictures.presenter.navigation.screen.base.Screen
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import by.radiance.space.pictures.presenter.ui.theme.CardGray
import by.radiance.space.pictures.presenter.ui.utils.AnimatedVisibilityBottomNavigation
import by.radiance.space.pictures.presenter.ui.utils.BottomNavigationScreenItem
import by.radiance.space.pictures.presenter.ui.utils.Rectangle
import by.radiance.space.pictures.presenter.ui.utils.ScaffoldWithConstraints
import by.radiance.space.pictures.presenter.ui.utils.WindowSize
import by.radiance.space.pictures.presenter.ui.utils.heightWindowSize
import by.radiance.space.pictures.presenter.utils.composableFromType
import by.radiance.space.pictures.presenter.viewModel.TodayViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.compat.ViewModelCompat.viewModel
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.ext.android.viewModel

@Composable
fun Root(
    router: Router,
) {
    AstronomyPicturesTheme(
        darkTheme = true,
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
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp))
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
            modifier = Modifier.padding(innerPadding),
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
        modifier = Modifier
            .padding(5.dp)
            .clip(Rectangle),
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
