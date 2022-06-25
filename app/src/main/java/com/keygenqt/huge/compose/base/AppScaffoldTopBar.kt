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
package com.keygenqt.huge.compose.base

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.keygenqt.routing.NavigationDispatcher
import com.keygenqt.huge.compose.texts.TextH6
import com.keygenqt.huge.compose.texts.TextOverline
import kotlinx.coroutines.launch

@Composable
fun AppScaffoldTopBar(
    navigationDispatcher: NavigationDispatcher?,
    topBarTitle: String? = null,
    topBarSubtitle: String? = null,
    topBarLoading: Boolean = false,
    topBarActions: @Composable ((RowScope) -> Unit)? = null,
) {

    val scope = rememberCoroutineScope()

    // remember has callbacks
    val hasEnabledCallbacks: Boolean by remember {
        mutableStateOf(navigationDispatcher?.hasEnabledCallbacks() == true)
    }

    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 0.dp,
        title = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (topBarTitle != null) {
                    TextH6(
                        maxLines = 1,
                        color = MaterialTheme.colors.onPrimary,
                        text = topBarTitle
                    )
                }
                if (topBarSubtitle != null) {
                    TextOverline(
                        maxLines = 1,
                        color = MaterialTheme.colors.onPrimary,
                        text = topBarSubtitle
                    )
                }
            }
        },
        navigationIcon = if (hasEnabledCallbacks) {
            {
                IconButton(onClick = {
                    scope.launch {
                        navigationDispatcher?.onBackPressed()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        } else null,
        actions = {
            if (topBarLoading) {
                Box(
                    modifier = Modifier.size(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        color = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
            } else {
                topBarActions?.invoke(this)
            }
        }
    )
}
