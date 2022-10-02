package com.example.timetvr

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.timetvr.data.Subject
import com.example.timetvr.data.SubjectDetails
import com.example.timetvr.model.TimeTableViewModel
import com.example.timetvr.ui.theme.TimeTVRTheme
import kotlinx.coroutines.delay
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeTVRTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation(
    viewModel: TimeTableViewModel = viewModel()
) {
    
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("main_screen") {
            Main_Menu(navController, viewModel)
        }
        composable("classes_screen") {
            Classes_Menu(viewModel, navController)
        }
        composable(
            "info_screen/{infocode}",
            arguments = listOf(
                navArgument("infocode"){
                    type = NavType.IntType
                    defaultValue = viewModel.logic().infoCode
                }
            )
        ) { entry ->
            entry.arguments?.let { Info_Menu(secretCode = it.getInt("infocode")) }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1.3f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(5000L)
        navController.navigate("main_screen") {
            popUpTo(0)
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ns),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}


@Composable
fun Main_Menu(
    navController: NavController,
    viewModel: TimeTableViewModel
) {
    var classOrNot = viewModel.logic().subjectCode
    var whatClass: String = viewModel.logic().title
    var classCode = viewModel.logic().infoCode

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        horizontalAlignment = Alignment.Start
    ) {
        var havingNextClass = if (classOrNot == "") "" else "Your Next Academic Class is,"
        var whatNextClassCode: String = if (classOrNot == "") "Relax, You have no other classes Today!" else classOrNot
        var whatNextClass: String = if (classOrNot == "") "" else whatClass
        Text(
            text = havingNextClass,
            style = MaterialTheme.typography.h2,
            modifier = Modifier.weight(1.5f)
        )
        Column(
            modifier = Modifier.weight(3f)
        ) {
            Text(
                text = whatNextClassCode,
                style = MaterialTheme.typography.h1
            )
            Text(
                text = whatNextClass,
                style = MaterialTheme.typography.h1
            )
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            if(classCode != 0){
                Button(onClick = {
                    navController.navigate("info_screen/${viewModel.logic().infoCode}")
                }) {
                    Text(text = "More Info", style = MaterialTheme.typography.h3)
                }
            }

            Button(onClick = {
                navController.navigate("classes_screen")
            }) {
                Text(text = "Classes", style = MaterialTheme.typography.h3)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Classes_Menu(
    viewModel: TimeTableViewModel,
    navController: NavController
) {
// TODO: Make this list generated automatically using LazyColum (items) using 'subjects' from viewModel
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Card(
            elevation = 8.dp,
            modifier = Modifier.padding(15.dp),
            backgroundColor = Color.White,
            onClick = {
                navController.navigate("info_screen/${1}"){
                }

            }
        ) {
            Column(
                modifier = Modifier.padding(1.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.deeplearning1),
                    contentDescription = "",
                    modifier = Modifier.height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(6f)

                    ) {
                        Text(
                            text = "AD18501",
                            style = MaterialTheme.typography.h5
                        )
                        Text(
                            text = "Deep Learning Algorithms and Architectures",
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.h6
                        )
                    }

                }

            }

        }
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            elevation = 8.dp,
            modifier = Modifier.padding(15.dp),
            backgroundColor = Color.White,
            onClick = {
                navController.navigate("info_screen/${2}"){
                }

            }
        ) {
            Column(
                modifier = Modifier.padding(1.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dsp),
                    contentDescription = "",
                    modifier = Modifier.height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(6f)

                    ) {
                        Text(
                            text = "AD18502",
                            style = MaterialTheme.typography.h5
                        )
                        Text(
                            text = "Digital Signal Processing for Data Science",
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.h6
                        )
                    }

                }

            }

        }
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            elevation = 8.dp,
            backgroundColor = Color.White,
            modifier = Modifier.padding(15.dp),
            onClick = {
                navController.navigate("info_screen/${3}"){
                }

            }
        ) {
            Column(
                modifier = Modifier.padding(1.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.datamining1),
                    contentDescription = "",
                    modifier = Modifier.height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(6f)

                    ) {
                        Text(
                            text = "CS18502",
                            style = MaterialTheme.typography.h5
                        )
                        Text(
                            text = "Datamining and Data warehousing",
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.h6
                        )
                    }

                }

            }

        }
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            elevation = 8.dp,
            modifier = Modifier.padding(15.dp),
            backgroundColor = Color.White,
            onClick = {
                navController.navigate("info_screen/${4}"){
                }

            }
        ) {
            Column(
                modifier = Modifier.padding(1.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.iot1),
                    contentDescription = "",
                    modifier = Modifier.height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(6f)

                    ) {
                        Text(
                            text = "AD18503",
                            style = MaterialTheme.typography.h5
                        )
                        Text(
                            text = "Internet Of Things towards Data Science ",
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.h6
                        )
                    }

                }

            }

        }
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            elevation = 8.dp,
            modifier = Modifier.padding(15.dp),
            backgroundColor = Color.White,
            onClick = {
                navController.navigate("info_screen/${5}"){
                }

            }
        ) {
            Column(
                modifier = Modifier.padding(1.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.english),
                    contentDescription = "",
                    modifier = Modifier.height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(6f)

                    ) {
                        Text(
                            text = "OH18007",
                            style = MaterialTheme.typography.h5
                        )
                        Text(
                            text = "English for Technologists",
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.h6
                        )
                    }

                }

            }

        }
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            elevation = 8.dp,
            modifier = Modifier.padding(15.dp),
            backgroundColor = Color.White,
            onClick = {
                navController.navigate("info_screen/${6}"){
                }

            }
        ) {
            Column(
                modifier = Modifier.padding(1.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.deeplearning2),
                    contentDescription = "",
                    modifier = Modifier.height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(6f)

                    ) {
                        Text(
                            text = "AD18511/AD18512",
                            style = MaterialTheme.typography.h5
                        )
                        Text(
                            text = "Deep Learning/IoT Lab",
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.h6
                        )
                    }

                }

            }

        }
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            elevation = 8.dp,
            modifier = Modifier.padding(15.dp),
            backgroundColor = Color.White,
            onClick = {
                navController.navigate("info_screen/${7}"){
                }

            }
        ) {
            Column(
                modifier = Modifier.padding(1.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.eng2),
                    contentDescription = "",
                    modifier = Modifier.height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(6f)

                    ) {
                        Text(
                            text = "HS18561",
                            style = MaterialTheme.typography.h5
                        )
                        Text(
                            text = "Interview and Career Skills Laboratory",
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.h6
                        )
                    }

                }

            }

        }
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            elevation = 8.dp,
            modifier = Modifier.padding(15.dp),
            backgroundColor = Color.White,
            onClick = {
                navController.navigate("info_screen/${8}"){
                }

            }
        ) {
            Column(
                modifier = Modifier.padding(1.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ind),
                    contentDescription = "",
                    modifier = Modifier.height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(6f)

                    ) {
                        Text(
                            text = "MC18001",
                            style = MaterialTheme.typography.h5
                        )
                        Text(
                            text = "Indian Constitution and Society",
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.h6
                        )
                    }

                }

            }

        }
        Spacer(modifier = Modifier.height(30.dp))

    }
}

@Composable
fun Info_Menu(secretCode: Int) {
    var objectives = ""
    var credits = 0
    var outcomes = ""
    var moreInfo = SubjectDetails().moreInfo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = moreInfo[secretCode]!![0],
            style = MaterialTheme.typography.h3,
            modifier = Modifier.weight(3f),
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "Credits:",
            textDecoration = TextDecoration.Underline,
            style = MaterialTheme.typography.h1,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "This is a "+moreInfo[secretCode]!![1]+" Credit Subject.",
            style = MaterialTheme.typography.h2,
            modifier = Modifier.weight(1.5f)
        )
        Text(
            text = moreInfo[secretCode]!![2],
            style = MaterialTheme.typography.h3,
            modifier = Modifier.weight(2f),
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TimeTVRTheme {
    }
}