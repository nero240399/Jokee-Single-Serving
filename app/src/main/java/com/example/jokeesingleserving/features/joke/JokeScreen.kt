package com.example.jokeesingleserving.features.joke

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jokeesingleserving.R
import com.example.jokeesingleserving.core.model.Joke
import com.example.jokeesingleserving.features.joke.components.JokeButton
import com.example.jokeesingleserving.features.joke.components.UserCard

@Composable
fun JokeRoute(
    windowSizeClass: WindowSizeClass,
    viewModel: JokeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold { paddingValues ->
        JokeScreen(
            windowSizeClass = windowSizeClass,
            uiState = uiState,
            feedbackJoke = viewModel::feedbackJoke,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun JokeScreen(
    windowSizeClass: WindowSizeClass,
    uiState: Joke?,
    feedbackJoke: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val isCompact by remember {
        mutableStateOf(
            windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        TopBar(
            isCompact = isCompact,
            modifier = Modifier
                .padding(bottom = (if (isCompact) 8 else 4).dp)
                .padding(horizontal = 16.dp)
        )
        IntroductionBanner(isCompact)
        Spacer(modifier = Modifier.height((if (isCompact) 64 else 8).dp))
        JokeContent(
            content = uiState?.content ?: stringResource(id = R.string.out_of_joke),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.height((if (isCompact) 16 else 8).dp))
        LikeButtons(
            isCompact = isCompact,
            feedbackJoke = feedbackJoke
        )
        Spacer(modifier = Modifier.height((if (isCompact) 44 else 0).dp))
        DisclaimerContent()
    }
}

@Composable
fun TopBar(isCompact: Boolean, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxHeight(if (isCompact) 0.06f else 0.1f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxHeight()
        )
        Spacer(modifier = Modifier.weight(1f))
        UserCard(name = "Jim HLS")
    }
}

@Composable
fun IntroductionBanner(isCompact: Boolean, modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(52.dp))
            Text(
                text = stringResource(id = R.string.introduction_title),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height((if (isCompact) 24 else 0).dp))
            Text(
                text = stringResource(id = R.string.introduction_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height((if (isCompact) 36 else 0).dp))
        }
    }
}

@Composable
fun JokeContent(content: String, modifier: Modifier = Modifier) {
    val state = rememberScrollState()
    Column(
        modifier = modifier
            .padding(horizontal = 32.dp)
            .verticalScroll(state)
    ) {
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Left
        )
    }
}

@Composable
fun LikeButtons(
    isCompact: Boolean,
    feedbackJoke: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Spacer(modifier = Modifier.weight(if (isCompact) 0.18f else 1f))
        JokeButton(
            textId = R.string.like_description,
            containerColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.weight(1f)
        ) {
            feedbackJoke(true)
        }
        Spacer(modifier = Modifier.weight(if (isCompact) 0.12f else 1f))
        JokeButton(
            textId = R.string.dislike_description,
            modifier = Modifier.weight(1f)
        ) {
            feedbackJoke(false)
        }
        Spacer(modifier = Modifier.weight(if (isCompact) 0.18f else 1f))
    }
}

@Composable
fun DisclaimerContent(modifier: Modifier = Modifier) {
    Divider(
        thickness = 0.5.dp,
        color = Color.LightGray
    )
    Spacer(modifier = Modifier.height(4.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .animateContentSize()
    ) {
        Text(
            text = stringResource(id = R.string.disclaimer),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.copyright),
            style = MaterialTheme.typography.labelMedium
        )
    }
}
