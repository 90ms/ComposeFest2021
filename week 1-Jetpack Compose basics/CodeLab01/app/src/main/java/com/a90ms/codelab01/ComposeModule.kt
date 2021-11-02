package com.a90ms.codelab01

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.a90ms.codelab01.ui.theme.CodeLab01Theme
import com.a90ms.codelab01.ui.theme.Navy

@Composable
fun CodeLap01App() {
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnBoarding) OnBoardingScreen(buttonClick = { shouldShowOnBoarding = false })
    else Greetings()
}

@Composable
fun OnBoardingScreen(buttonClick: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(id = R.string.text_description))
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = buttonClick
            ) {
                Text(stringResource(id = R.string.text_join))
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = SCREEN_SIZE_WIDTH,
    heightDp = SCREEN_SIZE_HEIGHT
)
@Composable
private fun Greetings(names: List<String> = List(LIST_SIZE) { "$it" }) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        items(items = names) { name ->
            Card(
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier.padding(
                    vertical = 4.dp,
                    horizontal = 8.dp
                )
            ) {
                CardContent(name)
            }
        }
    }
}

@Composable
private fun CardContent(name: String) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(10.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
        ) {
            Text(text = stringResource(id = R.string.author))
            Text(
                text = name,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold,
                )
            )
            if (expanded) {
                CardHiddenContents(name)
            }
        }
        OutlinedButton(
            onClick = { expanded = !expanded }
        ) {
            Text(
                text = stringResource(
                    id = if (expanded) R.string.show_less
                    else R.string.show_more
                )
            )
        }
    }
}

@Composable
fun CardHiddenContents(name: String) {
    Row {
        Column(
            Modifier.weight(1f)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .border(5.dp, Navy, CircleShape)
            )
        }
        Column(
            Modifier.weight(2f)
        ) {
            Text(
                text = ("$name ${stringResource(id = R.string.app_name)}\n").repeat(10),
                overflow = TextOverflow.Ellipsis,
                maxLines = 5,
                textDecoration = TextDecoration.combine(
                    listOf(
                        TextDecoration.Underline,
                        TextDecoration.LineThrough
                    )
                )
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = SCREEN_SIZE_WIDTH,
    name = "LightMode"
)
@Preview(
    showBackground = true,
    widthDp = SCREEN_SIZE_WIDTH,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DarkMode"
)
@Composable
fun DefaultPreview() {
    CodeLab01Theme {
        Greetings()
    }
}