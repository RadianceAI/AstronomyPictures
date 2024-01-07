package by.radiance.space.pictures.presenter.ui.actionMenu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import by.radiance.space.pictures.presenter.ui.theme.CardGray
import by.radiance.space.pictures.presenter.ui.utils.VerticalItem

@Composable
fun ActionBar(
    modifier: Modifier = Modifier,
    orientation: ActionBarOrientation,
    actions: List<Action>
) {
    when(orientation) {
        ActionBarOrientation.Horizontal -> {
            HorizontalActionBar(
                modifier = modifier,
                actions = actions,
            )
        }
        ActionBarOrientation.Vertical -> {
            VerticalActionBar(
                modifier = modifier,
                actions = actions,
            )
        }
    }
}

data class Action(
    val icon: ImageVector,
    val contentDescription: String,
    val onClick: () -> Unit,
)

@Composable
private fun VerticalActionBar(
    modifier: Modifier,
    actions: List<Action>,
) {
    Column(
        modifier = modifier,
    ) {
        actions.forEach { action ->
            VerticalItem(
                icon = action.icon,
                onClick = action.onClick,
            )
        }
    }
}

@Composable
private fun HorizontalActionBar(
    modifier: Modifier,
    actions: List<Action>,
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = CardGray,
    ) {
        actions.forEach { action ->
            HorizontalIcon(
                icon = action.icon,
                contentDescription = action.contentDescription,
                onClick = action.onClick,
            )
        }
    }
}

sealed class ActionBarOrientation {
    data object Horizontal : ActionBarOrientation()
    data object Vertical : ActionBarOrientation()
}

@Composable
fun RowScope.HorizontalIcon(
    icon: ImageVector,
    onClick: () -> Unit,
    contentDescription: String,
) {
    BottomNavigationItem(
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
            )
        },
        onClick = {
            onClick.invoke()
        },
        selected = true,
    )
}

