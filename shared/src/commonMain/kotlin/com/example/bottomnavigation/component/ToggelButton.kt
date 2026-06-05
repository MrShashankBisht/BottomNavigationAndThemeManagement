package com.example.bottomnavigation.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import bottomnavigation.shared.generated.resources.Res
import bottomnavigation.shared.generated.resources.icon_moon
import bottomnavigation.shared.generated.resources.icon_sun
import org.jetbrains.compose.resources.painterResource

@Composable
fun CuteThemeToggle(
    isDark: Boolean,
    onThemeToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val switchWidth = 72.dp
    val switchHeight = 40.dp
    val knobSize = 32.dp
    val internalPadding = 4.dp

    val backgroundColor by animateColorAsState(
        targetValue = if (isDark) Color(0xFF2C3E50) else Color(0xFF87CEEB),
        animationSpec = tween(400)
    )
    val knobColor by animateColorAsState(
        targetValue = if (isDark) Color(0xFFF1C40F) else Color(0xFFF39C12),
        animationSpec = tween(400)
    )
    val iconColor by animateColorAsState(
        targetValue = if (isDark) Color(0xFF2C3E50) else Color(0xFFFFFFFF),
        animationSpec = tween(400)
    )

    val maxOffset = switchWidth - knobSize - (internalPadding * 2)
    val knobOffset by animateDpAsState(
        targetValue = if (isDark) maxOffset else 0.dp,
        animationSpec = tween(400)
    )
    val rotationDegrees by animateDpAsState(
        targetValue = if (isDark) 180.dp else 0.dp,
        animationSpec = tween(400)
    )

    Box(
        modifier = modifier
            .size(width = switchWidth, height = switchHeight)
            .clip(RoundedCornerShape(24.dp))
            .background(backgroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onThemeToggle(!isDark) }
            .padding(internalPadding)
    ) {
        Box(
            modifier = Modifier
                .offset(x = knobOffset)
                .size(knobSize)
                .rotate(rotationDegrees.value)
                .clip(CircleShape)
                .background(knobColor),
            contentAlignment = Alignment.Center
        ) {
            // 2. Use the modern Lucide icons here
            Image(
                painter = if (isDark) painterResource(Res.drawable.icon_moon) else painterResource(Res.drawable.icon_sun),
                contentDescription = if (isDark) "Moon Icon" else "Sun Icon",
                modifier = Modifier.size(18.dp),
            )
        }
    }
}
