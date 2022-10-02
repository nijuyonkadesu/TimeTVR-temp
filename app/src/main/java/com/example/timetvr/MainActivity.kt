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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.timetvr.ui.theme.TimeTVRTheme
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
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
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("main_screen") {
            Main_Menu(navController)
        }
        composable("classes_screen") {
            Classes_Menu()
        }
        composable("info_screen") {
            Info_Menu(secretCode = logic()[2].toInt())
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
            targetValue = 0.9f,
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
            painter = painterResource(id = R.drawable.tt),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}


@Composable
fun Main_Menu(navController: NavController) {
    var classOrNot = logic()[0]
    var whatClass: String = logic()[1]
    var classCode = logic()[2].toInt()

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
            if(logic()[2].toInt() != 0){
                Button(onClick = {
                    navController.navigate("info_screen")
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

@Composable
fun Classes_Menu() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Card(
            elevation = 8.dp,
            modifier = Modifier.padding(15.dp)
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
            backgroundColor = Color.White
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
            modifier = Modifier.padding(15.dp)
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
            backgroundColor = Color.White
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

    }
}

@Composable
fun Info_Menu(secretCode: Int) {
    var objectives = ""
    var credits = 0
    var outcomes = ""
    var moreInfo = mapOf<Int, List<String>>(
        1 to listOf<String>("The students are made to\n" +
                "\uF0B7 Understand the basis of linear and softmax regression\n" +
                "\uF0B7 Learn the notions of over fitting, under fitting and regularization techniques.\n" +
                "\uF0B7 Design convolutional neural network\n" +
                "\uF0B7 Handle sequential information\n" +
                "\uF0B7 Study the transformation of an idea to concrete math model and make it work", "3", "Upon completion of the course, students will be able to\n" +
                "\uF0B7 Train models for multi-category classification.\n" +
                "\uF0B7 Train deep learning models and ensure the gradients are well controlled\n" +
                "\uF0B7 Construct a complex CNN and tune various hyper parameters\n" +
                "\uF0B7 Construct a sequential model which can capture the dependencies for time series data\n" +
                "\uF0B7 Familiar with the encoder-decoder architecture\n"),
        2 to listOf<String>("The students will be made to :\n" +
                "\uF06C Understand the basics of signals and systems\n" +
                "\uF06C Gain an understanding of discrete time signals and systems\n" +
                "\uF06C Understand the various probability distributions\n" +
                "\uF06C Study how to build machine learning models using audio signals\n" +
                "\uF06C Study deep learning model and architectures for computer vision", "4", "At the end of the course, learners will be able to:\n" +
                "\uF06C Use signals for modelling\n" +
                "\uF06C Know various signal transformations\n" +
                "\uF06C Appreciate necessity of various probability distributions\n" +
                "\uF06C Design models that can process audio signals\n" +
                "\uF06C Use existing architectures and create their own architectures for computer vision"),
        3 to listOf<String>("To understand data warehouse concepts, architecture, business analysis and tools\n" +
                "\uF0B7 To understand data pre-processing and data visualization techniques\n" +
                "\uF0B7 To study algorithms for finding hidden and interesting patterns in data\n" +
                "\uF0B7 To understand and apply various classification and clustering techniques using tools.\n" +
                "\uF0B7 Master data mining techniques in various applications like social, scientific and environmental\n" +
                "context.", "4", "Students will be able to understand data warehouse concepts, architecture, business analysis\n" +
                "and tools.\n" +
                "\uF0B7 Students will be able to understand data pre- processing and data visualization techniques\n" +
                "\uF0B7 Students will be able to study algorithms for finding hidden and interesting patterns in data\n" +
                "using association algorithms\n" +
                "\uF0B7 Students will be able to apply various classification and clustering techniques using tools\n" +
                "\uF0B7 Students will be mastering the data mining techniques in various applications like social,\n" +
                "scientific and environmental context"),
        4 to listOf<String>("To introduce the terminology, technology and its applications\n" +
                "\uF06C To introduce the concept of M2M (machine to machine) with necessary protocols\n" +
                "\uF06C To introduce the Python Scripting Language which is used in many IoT devices\n" +
                "\uF06C To introduce the Raspberry PI platform, that is widely used in IoT applications.\n" +
                "\uF06C To apply the concept of Internet of Things in the real world scenario\n", "3", "Analyze various protocols for IoT.\n" +
                "\uF0B7 Develop IoT application using scripting languages\n" +
                "\uF0B7 Design a portable IoT using Raspberry Pi\n" +
                "\uF0B7 Develop web services to access/control IoT devices.\n" +
                "\uF0B7 Analyze applications of IoT in real time scenario" +
                "scientific and environmental context"),
        5 to listOf<String>("This course will let the students understand the importance of English towards Technology.", "3", "Students, by the end of this course will be ABLE to use their technical knowledge and learn corporate-English skills."),
        6 to listOf<String>("These Labs will help the students get ready with learning AI&DS and the processing of Data, and management of the same. The students are requested to follow the lab procedures and protocol.", "2", "The labs will train the average student into corporate-AI ready fellows."),
        7 to listOf<String>("This lab will let the students understand the importance of English towards Technology.", "2", "Students, by the end of this course will be ABLE to use their technical knowledge and learn corporate-English skills."),
        8 to listOf<String>("This lab will let the students understand the importance of English towards Technology.", "2", "Students, by the end of this course will be ABLE to use their technical knowledge and learn corporate-English skills."),
        9 to listOf<String>("This lab will let the students understand the importance of English towards Technology.", "2", "Students, by the end of this course will be ABLE to use their technical knowledge and learn corporate-English skills."),
        10 to listOf<String>("This lab will let the students understand the importance of English towards Technology.", "2", "Students, by the end of this course will be ABLE to use their technical knowledge and learn corporate-English skills."),
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = moreInfo[secretCode]!![0],
            style = MaterialTheme.typography.h3
        )
        Text(
            text = "This is a "+moreInfo[secretCode]!![1]+" Credit Subject.",
            style = MaterialTheme.typography.h2
        )
        Text(
            text = moreInfo[secretCode]!![2],
            style = MaterialTheme.typography.h3
        )
    }
}

fun logic(): List<String> {
    val hours = SimpleDateFormat("HH", Locale.US)
    val hour: Int = hours.format(Date()).toInt()
    val minutes = SimpleDateFormat("mm", Locale.US)
    val minute: Int = minutes.format(Date()).toInt()
    val convertDateTo = SimpleDateFormat("EEEE", Locale.US)
    val theDay: String = convertDateTo.format(Date())
    val hourMinute: String = hours.format(Date())+":"+minutes.format(Date())
    var hasClass = true
    if (theDay == "Sunday" || theDay == "Saturday") {
        hasClass = false
        return listOf("", "Relax, You have no other classes Today!", "0")
    }
    else if (hour >= 14 || hour < 7){
        hasClass = false
        return listOf("", "Relax, You have no other classes Today!", "0")
    }
    else{
        when (theDay) {
            "Monday" -> {
                if (hour == 7) {
                    return listOf("OH18007", "English for Technologists", "5")
                }
                else if (hour == 8) {
                    return listOf("AD18511/12", "DL Laboratory/ IoT Laboratory", "6")
                }
                else if (hour == 9) {
                    return listOf("AD18511/12", "DL Laboratory/ IoT Laboratory", "6")
                }
                else if (hour == 10 ) {
                    return listOf("AD18511/12", "DL Laboratory/ IoT Laboratory", "6")
                }
                else if (hour == 11) {
                    return listOf("NoCode", "SEMINAR - I", "9")
                }
                else if (hour == 12) {
                    return listOf("AD18503", "Internet Of Things towards Data Science", "4")
                }
                else if (hour == 13) {
                    return listOf("AD18502", "Digital Signal Processing for Data Science", "2")
                }
            }
            "Tuesday" -> {
                if (hour == 7) {
                    return listOf("OH18007", "English for Technologists", "5")
                }
                else if (hour == 8) {
                    return listOf("AD18502", "Digital Signal Processing for Data Science", "2")
                    //return listOf("AD18511/12", "DL Laboratory/ IoT Laboratory", 1)
                }
                else if (hour == 9) {
                    return listOf("CS18502", "Data Mining and Data Warehousing", "3")
                }
                else if (hour == 10 ) {
                    //return listOf("AD18511/12", "DL Laboratory/ IoT Laboratory", 1)
                    return listOf("AD18501", "Deep Learning Algorithms and Architectures", "1")
                }
                else if (hour == 11) {
                    //return listOf("", "SEMINAR - I", 1)
                    return listOf("AD18503", "Internet Of Things towards Data Science", "4")
                }
                else if (hour == 12) {
                    //return listOf("AD18503", "Internet Of Things towards Data Science", 1)
                    return listOf("CS18502", "Data Mining and Data Warehousing", "3")
                }
                else if (hour == 13) {
                    return listOf("MC18001", "Indian Constitution and Society", "8")
                }
            }
            "Wednesday" -> {
                if (hour == 7) {
                    return listOf("OH18007", "English for Technologists", "5")
                }
                else if (hour == 8) {
                    return listOf("HS18561", "Interview and Career Skills Laboratory", "7")
                }
                else if (hour == 9) {
                    return listOf("HS18561", "Interview and Career Skills Laboratory", "7")
                }
                else if (hour == 10 ) {
                    return listOf("HS18561", "Interview and Career Skills Laboratory", "7")
                }
                else if (hour == 11) {
                    return listOf("AD18502", "Digital Signal Processing for Data Science", "2")
                }
                else if (hour == 12) {
                    return listOf("MC18001", "Indian Constitution and Society", "8")
                }
                else if (hour == 13) {
                    return listOf("CS18502", "Data Mining and Data Warehousing", "3")
                }
            }
            "Thursday" -> {
                if (hour == 7) {
                    return listOf("AD18502", "Digital Signal Processing for Data Science", "2")
                }
                else if (hour == 8) {
                    return listOf("AD18511/12", "DL Laboratory/ IoT Laboratory","6")
                }
                else if (hour == 9) {
                    return listOf("AD18511/12", "DL Laboratory/ IoT Laboratory", "6")
                }
                else if (hour == 10 ) {
                    return listOf("AD18511/12", "DL Laboratory/ IoT Laboratory", "6")
                }
                else if (hour == 11) {
                    return listOf("MC18001", "Indian Constitution and Society", "8")
                }
                else if (hour == 12) {
                    return listOf("CS18502", "Data Mining and Data Warehousing", "3")
                }
                else if (hour == 13) {
                    return listOf("AD18501", "Deep Learning Algorithms and Architectures", "1")
                }
            }
            "Friday" -> {
                if (hour == 7) {
                    return listOf("AD18501", "Deep Learning Algorithms and Architectures", "1")
                }
                else if (hour == 8) {
                    return listOf("AD18503", "Internet Of Things towards Data Science", "4")
                }
                else if (hour == 9) {
                    return listOf("CS18502", "Data Mining and Data Warehousing", "3")
                }
                else if (hour == 10 ) {
                    return listOf("AD18503", "Internet Of Things towards Data Science", "4")
                }
                else if (hour == 11) {
                    return listOf("AD18502", "Digital Signal Processing for Data Science", "2")
                }
                else if (hour == 12) {
                    return listOf("AD18501", "Deep Learning Algorithms and Architectures", "1")
                }
                else if (hour == 13) {
                    return listOf("NoCode", "SEMINAR - II", "10")
                }
            }
        }
    }
    return listOf("", "", "1")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TimeTVRTheme {
    }
}