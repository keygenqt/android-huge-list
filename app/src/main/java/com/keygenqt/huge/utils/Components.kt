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
package com.keygenqt.huge.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.keygenqt.huge.R

enum class Components(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
    val version: String? = null
) {
    PAGER_INDICATORS(
        icon = R.drawable.ic_pager_indicator,
        title = R.string.welcome_pager_indicators,
        description = R.string.welcome_pager_indicators_desc,
        version = "0.0.1",
    ),
    CHIPS(
        icon = R.drawable.ic_chips,
        title = R.string.welcome_chips,
        description = R.string.welcome_chips_desc,
    ),
    LOADERS(
        icon = R.drawable.ic_loader,
        title = R.string.welcome_loaders,
        description = R.string.welcome_loaders_desc,
    ),
}
