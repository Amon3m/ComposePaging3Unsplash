package com.mon3m.paging3.data.screens.common

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import androidx.wear.compose.material.ContentAlpha
import coil.compose.rememberImagePainter
import com.mon3m.paging3.R
import com.mon3m.paging3.model.UnsplashImage
import com.mon3m.paging3.model.Urls
import com.mon3m.paging3.model.User
import com.mon3m.paging3.model.UserLinks

@Composable
fun ListContent (items: LazyPagingItems<UnsplashImage>,contentPadding: PaddingValues){

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { unsplashImage ->
            unsplashImage?.let {
                UnsplashItem(unsplashImage = it) }
        }
    }
}
    @Composable
    fun LikeCounter(
        modifier: Modifier,
        painter: Painter,
        likes: String
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                painter = painter,
                contentDescription = "Heart Icon",
                tint = Color(0xFFD32F2F),
            )
            Divider(modifier = Modifier.width(6.dp))
            Text(
                text = likes,
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

    @Composable
    fun UnsplashItem(unsplashImage: UnsplashImage) {
        val painter = rememberImagePainter(data = unsplashImage.urls.regular) {
            crossfade(durationMillis = 1000)
            error(R.drawable.ic_placeholder)
            placeholder(R.drawable.ic_placeholder)
        }
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .clickable {
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://unsplash.com/@${unsplashImage.user?.username}?utm_source=DemoApp&utm_medium=referral")
                    )
                    startActivity(context, browserIntent, null)
                }
                .height(300.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = "Unsplash Image",
                contentScale = ContentScale.Crop
            )
            Surface(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .alpha(ContentAlpha.medium),
                color = Color.Black
            ) {}
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Photo by ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                            append(unsplashImage.user?.username)
                        }
                        append(" on Unsplash")
                    },
                    color = Color.White,
                    fontSize = 10.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                LikeCounter(
                    modifier = Modifier.weight(3f),
                    painter = painterResource(id = R.drawable.heart),
                    likes = "${unsplashImage.likes}"
                )
            }
        }
    }
@Composable
@Preview
fun UnsplashImagePreview() {
    UnsplashItem(
        unsplashImage = UnsplashImage(
            id = "1",
            urls = Urls(regular = ""),
            likes = 100,
            user = User(username = "monem", userLinks = UserLinks(html = ""))
        )
    )
}

