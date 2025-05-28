package com.example.nirdhant.news.presentation.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.nirdhant.news.presentation.home_screen.components.HomeLazyColumn
import com.example.nirdhant.news.presentation.home_screen.components.bottomNavigationItemList
import com.example.nirdhant.ui.theme.NirdhantTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    var selectedItemIndex by remember { mutableIntStateOf(0) }   //this is default selected index for my list of bottomNavigationItems
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {

        HomeLazyColumn()
        //-------------------------------Bottom Navigation-----------------------------------------
        NavigationBar(modifier=Modifier.align(Alignment.BottomCenter).fillMaxWidth().shadow(elevation = 50.dp, ambientColor = Color.Yellow, shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)))
        {
            bottomNavigationItemList.forEachIndexed { index, eachItem ->
                NavigationBarItem(
                    selected = selectedItemIndex == index,
                    label = { Text(text = eachItem.title) },
                    icon =
                    { BadgedBox(badge ={
                        if (eachItem.badge) Badge()
                        else if (eachItem.badgeCount!=null) Badge{ Text(text = eachItem.badgeCount.toString())}}
                    ) {
                        Icon(
                            imageVector =if (selectedItemIndex == index) eachItem.selectedIcon else eachItem.unselectedIcon,
                            contentDescription =eachItem.title
                        )
                    }
                    },
                    onClick = { selectedItemIndex = index }
                )
            }
        }

    }
}




@PreviewLightDark
@Composable
fun MainScreenPreview(){
    NirdhantTheme {
        MainScreen()
    }
}