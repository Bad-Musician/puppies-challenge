/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.PUPPY_KEY
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.loadPicture
import com.example.androiddevchallenge.model.DogModel

@Composable
fun DetailedDogsScreen(navController: NavController) {
    @DrawableRes val defaultPicture: Int = R.drawable.ic_default
    val puppy = navController.previousBackStackEntry?.arguments?.get(PUPPY_KEY) as DogModel

    Column {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
        val bitmap = loadPicture(
            url = puppy.photo,
            defaultPicture = defaultPicture
        ).value
        bitmap?.let {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = puppy.name,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .padding(bottom = 16.dp),
            elevation = 4.dp
        ) {
            Column {
                Text(
                    text = "Name: ${puppy.name}",
                    modifier = Modifier.padding(start = 16.dp)
                )
                Divider()
                Text(
                    text = "Breed: ${puppy.breed}",
                    modifier = Modifier.padding(start = 16.dp)
                )
                Divider()
                Text(
                    text = "Location: ${puppy.city}",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}
