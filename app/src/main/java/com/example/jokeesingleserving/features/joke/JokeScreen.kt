package com.example.jokeesingleserving.features.joke

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jokeesingleserving.R
import com.example.jokeesingleserving.core.model.Joke
import com.example.jokeesingleserving.features.joke.components.JokeButton
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
        JokeContent(uiState?.content ?: stringResource(id = R.string.out_of_joke))
        Spacer(modifier = Modifier.weight(1f))
        LikeButtons(feedbackJoke = feedbackJoke)
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        DisclaimerText(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxHeight(0.05f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_zens),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxHeight()
        )
        Spacer(modifier = Modifier.weight(1f))
        UserCard(name = "Nguyen Duc Hung")
    }
}

@Composable
fun IntroductionBanner(modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(vertical = 32.dp, horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.introduction_title),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.introduction_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun JokeContent(content: String) {
    Text(
        text = content,
        modifier = Modifier.padding(horizontal = 32.dp),
        style = MaterialTheme.typography.bodyLarge
    )
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
        JokeButton(textId = R.string.dislike_description) {
            feedbackJoke(false)
        }
        JokeButton(
            textId = R.string.like_description,
            containerColor = MaterialTheme.colorScheme.secondary
        ) {
            feedbackJoke(true)
        }
    }
}

@Composable
fun DisclaimerText(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.disclaimer),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.copyright),
            style = MaterialTheme.typography.titleSmall
        )
    }
}
