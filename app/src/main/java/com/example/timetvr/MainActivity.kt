package com.example.timetvr

import android.os.Build
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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


@RequiresApi(Build.VERSION_CODES.O)
fun Modifier.drawColoredShadow(
    color: Color,
    alpha: Float = 0.2f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 20.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = this.drawBehind {
    val transparentColor = android.graphics.Color.toArgb(color.copy(alpha = 0.0f).value.toLong())
    val shadowColor = android.graphics.Color.toArgb(color.copy(alpha = alpha).value.toLong())
    this.drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            borderRadius.toPx(),
            borderRadius.toPx(),
            paint
        )
    }
}
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
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
                    Text(text = "More Info", style = MaterialTheme.typography.h6)
                }
            }

            Button(onClick = {
                navController.navigate("classes_screen")
            }) {
                Text(text = "Classes", style = MaterialTheme.typography.h6)
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Classes_Menu(
    viewModel: TimeTableViewModel,
    navController: NavController
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(20.dp)
    ){
        items(viewModel.subjects){
            if(it.imgId != 0){
                ClassesItem(it, navController = navController)
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ClassesItem(
    subject: Subject,
    navController: NavController
){
    Column {
        Card(
            elevation = 8.dp,
            modifier = Modifier
                .padding(15.dp)
                .drawColoredShadow(Color.White, 0.9f, 5.dp),
            border = BorderStroke(1.dp, Color.White),
            backgroundColor = Color.White,
            contentColor = Color.Black,
            onClick = {
                navController.navigate("info_screen/${subject.infoCode}") {  }
            }
        ) {
            Column(
                modifier = Modifier.padding(1.dp)
            ) {
                Image(
                    painter = painterResource(id = subject.imgId),
                    contentDescription = "",
                    modifier = Modifier.height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Column(
                        modifier = Modifier
                            .weight(6f)

                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = subject.subjectCode,
                            style = MaterialTheme.typography.h5
                        )
                        Row(modifier = Modifier.background(Color.Black).fillMaxWidth()) {
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = subject.title,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.h3
                            )
                        }
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