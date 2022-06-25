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
package com.keygenqt.huge.features.ui.screens.welcome

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Label
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.keygenqt.routing.NavigationDispatcher
import com.keygenqt.huge.BuildConfig
import com.keygenqt.huge.R
import com.keygenqt.huge.compose.base.AppScaffold
import com.keygenqt.huge.compose.extension.graphicsCollapse
import com.keygenqt.huge.compose.texts.TextBody1
import com.keygenqt.huge.compose.texts.TextCaption
import com.keygenqt.huge.compose.texts.TextH6
import com.keygenqt.huge.features.ui.actions.WelcomeActions
import com.keygenqt.huge.utils.Components
import timber.log.Timber

/**
 * Body for [WelcomeScreen]
 */
@Composable
fun WelcomeBody(
    lazyListState: LazyListState = rememberLazyListState(),
    navDispatcher: NavigationDispatcher? = null,
    onActions: (WelcomeActions) -> Unit = {},
) {
    AppScaffold(
        background = MaterialTheme.colors.secondary,
        navigationDispatcher = navDispatcher,
        topBarTitle = stringResource(id = R.string.app_name),
        topBarSubtitle = "v${BuildConfig.VERSION_NAME}"
    ) {
        LazyColumn(
            modifier = Modifier,
            state = lazyListState
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.background)
                        .graphicsCollapse(lazyListState)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 30.dp, horizontal = 20.dp)
                                .align(Alignment.BottomStart),
                            contentScale = ContentScale.FillWidth,
                            painter = painterResource(R.drawable.welcome),
                            contentDescription = null,
                        )
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.background)
                ) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                            .background(MaterialTheme.colors.secondary)
                            .height(28.dp)
                            .fillMaxWidth()
                    ) {}
                }
            }
            Components.values().forEach { item ->
                item {
                    ItemFeature(item = item, onClick = {
                        Timber.e(it.toString())
                    })
                }
            }
            item {
                Spacer(modifier = Modifier.size(12.dp))
            }
        }
    }
}

@Composable
private fun ItemFeature(
    item: Components,
    onClick: (Components) -> Unit
) {
    val context = LocalContext.current
    val message = stringResource(id = R.string.welcome_coming_soon)

    Box(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .background(MaterialTheme.colors.secondary)
            .padding(horizontal = 16.dp)
    ) {
        Card(
            shape = MaterialTheme.shapes.medium,
            elevation = 12.dp,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(top = 20.dp, bottom = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .clickable(onClick = {
                        if (item.version == null) {
                            Toast
                                .makeText(context, message, Toast.LENGTH_LONG)
                                .show()
                        } else {
                            onClick(item)
                        }
                    })
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 24.dp, bottom = 16.dp, end = 16.dp, start = 16.dp)
                ) {
                    TextH6(
                        modifier = Modifier,
                        text = stringResource(id = item.title)
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    TextBody1(
                        modifier = Modifier,
                        text = stringResource(id = item.description)
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        item.version?.apply {
                            Box(
                                modifier = Modifier.size(18.dp),
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .padding(bottom = 2.dp)
                                        .size(14.dp)
                                        .align(Alignment.Center),
                                    imageVector = Icons.Filled.Label,
                                    contentDescription = null,
                                    tint = MaterialTheme.colors.onBackground
                                )
                            }

                            Spacer(modifier = Modifier.size(4.dp))

                            TextCaption(
                                modifier = Modifier,
                                text = this
                            )
                        } ?: run {
                            TextCaption(
                                modifier = Modifier,
                                text = stringResource(id = R.string.welcome_coming_soon)
                            )
                        }
                    }
                }
            }
        }

        Card(
            modifier = Modifier
                .size(40.dp)
                .offset(24.dp)
                .align(Alignment.TopStart),
            shape = CircleShape,
            backgroundColor = MaterialTheme.colors.background,
            elevation = 12.dp,
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(36.dp)
                    .background(MaterialTheme.colors.primary)
            ) {
                Image(
                    modifier = Modifier
                        .size(22.dp)
                        .align(Alignment.Center),
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary),
                    painter = painterResource(item.icon),
                    contentDescription = null,
                )
            }
        }
    }


//    Box(
//        modifier = Modifier
//            .background(MaterialTheme.colors.secondary)
//            .padding(horizontal = 16.dp),
//    ) {
//        Card(
//            elevation = 3.dp,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp)
//        ) {
//            Column(
//                modifier = Modifier
//                    .clickable {
//                        onClick(item)
//                    }
//                    .background(MaterialTheme.colors.background)
//                    .padding(
//                        vertical = 30.dp,
//                        horizontal = 16.dp
//                    )
//            ) {
//                Text(
//                    text = stringResource(id = item.title)
//                )
//            }
//        }
//    }
}
