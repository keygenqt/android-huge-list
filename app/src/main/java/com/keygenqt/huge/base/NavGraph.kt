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
package com.keygenqt.huge.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.keygenqt.huge.features.navigation.graph.navGraphFeatures
import com.keygenqt.huge.features.navigation.route.NavRoute

@Composable
fun NavGraph(
    controller: NavHostController
) {
    val appActions = remember(controller) {
        AppNavActions(controller)
    }

    NavHost(
        navController = controller,
        startDestination = NavRoute.welcome.default.route
    ) {
        navGraphFeatures(appActions)
    }
}
