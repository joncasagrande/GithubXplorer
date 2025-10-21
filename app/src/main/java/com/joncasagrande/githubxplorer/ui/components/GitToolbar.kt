package com.joncasagrande.githubxplorer.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.getString
import com.joncasagrande.githubxplorer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitToolbar(
    title: String,
    query: String,
    onActionClick: () -> Unit,
    onQueryChange: (String) -> Unit,
    isSearching: Boolean,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    TopAppBar(
        modifier = Modifier.wrapContentSize(),
        title = {
            if (isSearching) {
                TextField(
                    singleLine = true,
                    value = query,
                    onValueChange = { term -> onQueryChange(term) },
                    placeholder = {
                        Text(
                            "Search Lang",
                        )
                    },
                    modifier = Modifier,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        cursorColor = Color.Blue
                    ),
                    textStyle = TextStyle(
                        fontWeight = FontWeight.Bold,
                    )
                )
            } else {
                Text(title)
            }

        },
        actions = {
            IconButton(onClick = onActionClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = LocalContext.current.getString(R.string.search),
                    modifier = Modifier
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InnerToolbar(
    title: String,
    onBackClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    MediumTopAppBar(
        modifier = Modifier.wrapContentSize(),
        title = { Text(title, overflow = TextOverflow.Ellipsis) },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = getString(LocalContext.current, R.string.back)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun InnerToolbarWithMenuDarkPreview() {
    Surface {
        GitToolbar("Allow DropOff", "", {}, {}, true, scrollBehavior = null)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun InnerToolbarWithMenuPreview() {
    Surface {
        InnerToolbar("Allow DropOff", {})
    }
}
