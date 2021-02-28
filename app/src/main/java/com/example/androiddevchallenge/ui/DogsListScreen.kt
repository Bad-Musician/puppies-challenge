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

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.PUPPY_KEY
import com.example.androiddevchallenge.model.DogModel
import com.example.androiddevchallenge.ui.component.DogCard

@Composable
fun DogsListScreen(navController: NavController) {

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(items = puppiesList) { dogModel ->
            DogCard(
                dogModel = dogModel,
                onClick = {
                    navController.currentBackStackEntry
                        ?.arguments?.putSerializable(PUPPY_KEY, dogModel)
                    navController.navigate("puppiesDetailed")
                }
            )
        }
    }
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
