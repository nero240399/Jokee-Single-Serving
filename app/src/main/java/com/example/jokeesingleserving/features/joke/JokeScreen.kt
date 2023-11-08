package com.example.jokeesingleserving.features.joke

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jokeesingleserving.R
import com.example.jokeesingleserving.core.model.Joke
import com.example.jokeesingleserving.features.joke.components.UserCard

@Composable
fun JokeRoute(viewModel: JokeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold { paddingValues ->
        JokeScreen(
            uiState = uiState,
            feedbackJoke = viewModel::feedbackJoke,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun JokeScreen(
    uiState: Joke?,
    feedbackJoke: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        TopBar(modifier = Modifier.padding(all = 16.dp))
        IntroductionBanner()
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = uiState?.content ?: stringResource(id = R.string.out_of_joke),
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        LikeButtons(feedbackJoke = feedbackJoke)
        Spacer(modifier = Modifier.height(32.dp))
        Divider()
        DisclaimerText(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_zens),
            contentDescription = null,
            modifier = Modifier.width(120.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        UserCard(name = "Nguyen Duc Hung")
    }
}

@Composable
fun IntroductionBanner(modifier: Modifier = Modifier) {
    Surface(
        color = Color.Green,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(vertical = 32.dp, horizontal = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.introduction_title))
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.introduction_description))
        }
    }
}

@Composable
fun LikeButtons(
    feedbackJoke: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxWidth()
    ) {
        Button(onClick = { feedbackJoke(false) }) {
            Text(text = stringResource(id = R.string.dislike_description))
        }
        Button(onClick = { feedbackJoke(true) }) {
            Text(text = stringResource(id = R.string.like_description))
        }
    }
}

@Composable
fun DisclaimerText(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.disclaimer))
        Text(text = stringResource(id = R.string.copyright))
    }
}
