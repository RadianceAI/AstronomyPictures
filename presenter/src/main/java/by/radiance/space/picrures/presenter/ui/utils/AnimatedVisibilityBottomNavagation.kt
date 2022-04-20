package by.radiance.space.picrures.presenter.ui.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import by.radiance.space.picrures.presenter.ui.screen.Screen

@Composable
fun AnimatedVisibilityBottomNavigation(
    visible: Boolean,
    modifier: Modifier,
    backgroundColor: androidx.compose.ui.graphics.Color,
    content: @Composable RowScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        content = {
            BottomNavigation(
                modifier = modifier,
                backgroundColor = backgroundColor,
                content = content
            )
        }
    )
}

@Composable
fun RowScope.BottomNavigationScreenItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        icon = {
            Icon(
                screen.icon,
                stringResource(screen.title)
            )
        },
        label = { Text(stringResource(screen.title)) },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}


@Composable
fun ColumnScope.ColumnBottomNavigationItem(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selectedContentColor: androidx.compose.ui.graphics.Color = LocalContentColor.current,
    unselectedContentColor: androidx.compose.ui.graphics.Color = selectedContentColor.copy(alpha = ContentAlpha.medium)
) {
    val ripple = rememberRipple(bounded = false, color = selectedContentColor)

    val selected = true
    val label: @Composable (() -> Unit) = {  }
    val styledLabel: @Composable (() -> Unit)? = label.let {
        @Composable {
            val style = MaterialTheme.typography.caption.copy(textAlign = TextAlign.Center)
            ProvideTextStyle(style, content = label)
        }
    }
    val iconCompose: @Composable () -> Unit = {
        Icon(
            icon,
            null,
            modifier = Modifier.align(CenterHorizontally),
        )
    }

    Box(
        modifier
            .selectable(
                selected = selected,
                onClick = {
                    onClick()
                },
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = ripple
            )
            .weight(1f)
            .align(Alignment.CenterHorizontally),
        contentAlignment = Alignment.Center
    ) {
        BottomNavigationTransition(
            selectedContentColor,
            unselectedContentColor,
            selected
        ) { progress ->
            val animationProgress = if (alwaysShowLabel) 1f else progress

            BottomNavigationItemBaselineLayout(
                icon = iconCompose,
                label = styledLabel,
                iconPositionAnimationProgress = animationProgress
            )
        }
    }
}

@Composable
fun ColumnScope.BottomNavigationScreenItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selectedContentColor: androidx.compose.ui.graphics.Color = LocalContentColor.current,
    unselectedContentColor: androidx.compose.ui.graphics.Color = selectedContentColor.copy(alpha = ContentAlpha.medium)
) {
    val ripple = rememberRipple(bounded = false, color = selectedContentColor)

    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    val label: @Composable (() -> Unit) = {  }
    val styledLabel: @Composable (() -> Unit)? = label.let {
        @Composable {
            val style = MaterialTheme.typography.caption.copy(textAlign = TextAlign.Center)
            ProvideTextStyle(style, content = label)
        }
    }
    val icon: @Composable () -> Unit = {
        Icon(
            screen.icon,
            stringResource(screen.title),
            modifier = Modifier.align(CenterHorizontally),
        )
    }

    Box(
        modifier
            .selectable(
                selected = selected,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = ripple
            )
            .weight(1f)
            .align(Alignment.CenterHorizontally),
        contentAlignment = Alignment.Center
    ) {
        BottomNavigationTransition(
            selectedContentColor,
            unselectedContentColor,
            selected
        ) { progress ->
            val animationProgress = if (alwaysShowLabel) 1f else progress

            BottomNavigationItemBaselineLayout(
                icon = icon,
                label = styledLabel,
                iconPositionAnimationProgress = animationProgress
            )
        }
    }
 }

private val BottomNavigationAnimationSpec = TweenSpec<Float>(
    durationMillis = 300,
    easing = FastOutSlowInEasing
)
private val BottomNavigationItemHorizontalPadding = 12.dp

@Composable
private fun BottomNavigationTransition(
    activeColor: androidx.compose.ui.graphics.Color,
    inactiveColor: androidx.compose.ui.graphics.Color,
    selected: Boolean,
    content: @Composable (animationProgress: Float) -> Unit
) {
    val animationProgress by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = BottomNavigationAnimationSpec
    )

    val color = lerp(inactiveColor, activeColor, animationProgress)

    CompositionLocalProvider(
        LocalContentColor provides color.copy(alpha = 1f),
        LocalContentAlpha provides color.alpha,
    ) {
        content(animationProgress)
    }
}

@Composable
private fun BottomNavigationItemBaselineLayout(
    icon: @Composable () -> Unit,
    label: @Composable (() -> Unit)?,
    iconPositionAnimationProgress: Float
) {
    Box(Modifier.layoutId("icon").padding(16.dp)) { icon() }
    if (label != null) {
        Box(
            Modifier
                .layoutId("label")
                .alpha(iconPositionAnimationProgress)
                .padding(horizontal = BottomNavigationItemHorizontalPadding)
        ) { label() }
    }
}