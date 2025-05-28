package com.example.NewsApp.news.presentation.home_screen.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeLazyColumn(){
    val pullRefreshState = rememberPullToRefreshState()
    var isRefresh  by remember {  mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val imageList = remember { mutableStateListOf<String>(
        "https://images.pexels.com/photos/11822719/pexels-photo-11822719.jpeg?auto=compress&cs=tinysrgb&w=600.png",
        "https://images.pexels.com/photos/24838990/pexels-photo-24838990/free-photo-of-audi-rs-7-sportback.jpeg?auto=compress&cs=tinysrgb&w=600.png",
        "https://images.pexels.com/photos/11822719/pexels-photo-11822719.jpeg?auto=compress&cs=tinysrgb&w=600.png",
        "https://images.pexels.com/photos/24838990/pexels-photo-24838990/free-photo-of-audi-rs-7-sportback.jpeg?auto=compress&cs=tinysrgb&w=600.png",
        "https://images.pexels.com/photos/11822719/pexels-photo-11822719.jpeg?auto=compress&cs=tinysrgb&w=600.png",
        "https://images.pexels.com/photos/24838990/pexels-photo-24838990/free-photo-of-audi-rs-7-sportback.jpeg?auto=compress&cs=tinysrgb&w=600.png",
        "https://images.pexels.com/photos/11822719/pexels-photo-11822719.jpeg?auto=compress&cs=tinysrgb&w=600.png",
        "https://images.pexels.com/photos/24838990/pexels-photo-24838990/free-photo-of-audi-rs-7-sportback.jpeg?auto=compress&cs=tinysrgb&w=600.png",
        "https://images.pexels.com/photos/11822719/pexels-photo-11822719.jpeg?auto=compress&cs=tinysrgb&w=600.png",
        "https://images.pexels.com/photos/24838990/pexels-photo-24838990/free-photo-of-audi-rs-7-sportback.jpeg?auto=compress&cs=tinysrgb&w=600.png",
        "https://images.pexels.com/photos/11822719/pexels-photo-11822719.jpeg?auto=compress&cs=tinysrgb&w=600.png",
        "https://images.pexels.com/photos/24838990/pexels-photo-24838990/free-photo-of-audi-rs-7-sportback.jpeg?auto=compress&cs=tinysrgb&w=600.png"
    ) }

    PullToRefreshBox(
        isRefreshing = isRefresh,
        onRefresh = {
            coroutineScope.launch{
                isRefresh=true
                delay(10000L)
                isRefresh = false
            }

        },
        state=pullRefreshState,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefresh,
                state = pullRefreshState,
                containerColor = Color.Black,
                color = Color.White,
                threshold = 64.dp
            )
        }

    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(imageList, key = {index, item ->
                index.toString() + item}) { index, item ->
                key(index,item) {
                    SwipeToDeleteContainer(onSwipeToDelete = { imageList.removeAt(index) }) {
                        ImageList(item)
                    }
                }


            }
        }
    }
}

    @Composable
fun ImageList(image: String){
    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(model =image,
            contentDescription = null ,
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.Center,
            contentScale = (ContentScale.Crop))
        Spacer(Modifier.height(8.dp))
        Text(text = "Item ", modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
        HorizontalDivider()

    }
}
@Composable
fun SwipeToDeleteContainer(
    onSwipeToDelete:()->Unit,
    actualContent:@Composable ()->Unit
){
   var isRemoved  by remember {  mutableStateOf(false) }

    val swipeToDeleteState= rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart){
                isRemoved = true
                true  //assign
            }
            else  false
        }
    )
    //-----------------------------Animation Part-------------------------------------
    LaunchedEffect(key1 = isRemoved) {
        if (isRemoved){
            delay(500L)
            onSwipeToDelete()
        }
    }
    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(animationSpec = tween(500), shrinkTowards = Alignment.Top)+ fadeOut()
    ){
        SwipeToDismissBox(
            state =swipeToDeleteState,
            backgroundContent = {SwipeToDeleteBackground(swipeToDeleteState)},
            content = {actualContent()},
            enableDismissFromStartToEnd = false
        )
    }
}
@Composable
fun SwipeToDeleteBackground(swipeToDeleteState: SwipeToDismissBoxState){
    Box(modifier = Modifier.fillMaxSize()
        .background(
            if (swipeToDeleteState.dismissDirection == SwipeToDismissBoxValue.EndToStart) Color.Gray
            else Color.Transparent
        ).padding(16.dp)
        ,contentAlignment = Alignment.CenterEnd
    ){
        Icon(imageVector = Icons.Default.Delete,contentDescription = null,tint=Color.White)
    }
}
