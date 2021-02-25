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
package com.example.androiddevchallenge

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.androiddevchallenge.model.DogModel
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                PuppiesChallengeApp()
            }
        }
    }
}

@Composable
fun DogsListScreen(navController: NavController) {

    @DrawableRes val defaultPicture: Int = R.drawable.ic_default

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(items = puppiesList) { dogModel ->
            val bitmap = loadPicture(
                url = dogModel.photo,
                defaultPicture = defaultPicture
            ).value
            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .clickable { },
                elevation = 4.dp
            ) {
                Column {
                    bitmap?.let {
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = dogModel.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = null,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun loadPicture(url: String, @DrawableRes defaultPicture: Int): MutableState<Bitmap?> {
    val bitmapState = mutableStateOf<Bitmap?>(null)
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(defaultPicture)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    return bitmapState
}

private val puppiesList = listOf(
    DogModel(
        name = "Ember Sue",
        breed = "Skye Terrier",
        isAdopted = false,
        city = "Kazan",
        photo = "https://random.dog/bc1d0b93-34bb-4ce7-8b15-5adad0359213.jpg"
    ),
    DogModel(
        name = "Bailey",
        breed = "Golden Retriever",
        isAdopted = true,
        city = "Winnipeg",
        photo = "https://random.dog/8811-17451-16018.jpg"
    ),
    DogModel(
        name = "Pinky, Daisy and Mr.Pickles",
        breed = "Border Collie",
        isAdopted = false,
        city = "Boston",
        photo = "https://random.dog/945e599c-3a1e-44d5-a23e-d6acc2ca47a5.jpg"
    ),
)

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        PuppiesChallengeApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        PuppiesChallengeApp()
    }
}
