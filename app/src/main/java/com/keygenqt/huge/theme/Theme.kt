/*
 * Copyright 2022 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.keygenqt.huge.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = dark_primary,
    primaryVariant = dark_primaryVariant,
    secondary = dark_secondary,
    secondaryVariant = dark_secondaryVariant,
    background = dark_background,
    surface = dark_surface,
    error = dark_error,
    onPrimary = dark_onPrimary,
    onSecondary = dark_onSecondary,
    onBackground = dark_onBackground,
    onSurface = dark_onSurface,
    onError = dark_onError,
)

private val LightColorPalette = lightColors(
    primary = light_primary,
    primaryVariant = light_primaryVariant,
    secondary = light_secondary,
    secondaryVariant = light_secondaryVariant,
    background = light_background,
    surface = light_surface,
    error = light_error,
    onPrimary = light_onPrimary,
    onSecondary = light_onSecondary,
    onBackground = light_onBackground,
    onSurface = light_onSurface,
    onError = light_onError,
)

@Composable
fun SoloTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
