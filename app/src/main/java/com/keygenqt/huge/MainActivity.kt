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
package com.keygenqt.huge

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import com.keygenqt.routing.NavigationDispatcher
import com.keygenqt.huge.base.LocalNavigationDispatcher
import com.keygenqt.huge.base.MainViewModel
import com.keygenqt.huge.base.NavGraph
import com.keygenqt.huge.theme.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Main ViewModel
     */
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // get ui mode
        val uiMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        setContent {
            rememberNavController().let { controller ->
                CompositionLocalProvider(
                    LocalNavigationDispatcher provides NavigationDispatcher(
                        lifecycle = lifecycle,
                        controller = controller,
                        backPressedDispatcher = onBackPressedDispatcher
                    )
                ) {
                    SoloTheme {
                        NavGraph(controller = controller)
                    }
                }
            }
        }

        // Splash delay
        window.decorView.findViewById<View>(android.R.id.content)?.let { content ->
            content.viewTreeObserver.addOnPreDrawListener(
                object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        return if (!viewModel.isSplash.value) {
                            // remove BG splash
                            this@MainActivity.window.apply {
                                if (uiMode == Configuration.UI_MODE_NIGHT_YES) {
                                    decorView.setBackgroundColor(dark_background.toArgb())
                                    navigationBarColor = dark_surface.toArgb()
                                    statusBarColor = dark_primary.toArgb()
                                } else {
                                    decorView.setBackgroundColor(light_background.toArgb())
                                    navigationBarColor = light_surface.toArgb()
                                    statusBarColor = light_primary.toArgb()
                                }
                            }
                            // done splash remove listener
                            content.viewTreeObserver.removeOnPreDrawListener(this); true
                        } else false
                    }
                }
            )
        }
    }
}
