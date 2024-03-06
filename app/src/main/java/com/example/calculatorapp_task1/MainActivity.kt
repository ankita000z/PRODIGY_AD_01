package com.example.calculatorapp_task1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorapp_task1.ui.theme.CalculatorApp_Task1Theme
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorApp_Task1Theme {

                CalcScreen()

            }
        }
    }
}

@Composable
fun CalcScreen(modifier: Modifier = Modifier){
    var expression by remember{
        mutableStateOf("")
    }
    var result by remember{
        mutableStateOf("")
    }
    Column(modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        Column(modifier = modifier.weight(1f).padding(16.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = expression,
                style =  TextStyle(fontSize = 40.sp, color = MaterialTheme.colorScheme.error, textAlign = TextAlign.End),
                maxLines = 3
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = result,
                style =  TextStyle(fontSize = 48.sp, color = MaterialTheme.colorScheme.error, textAlign = TextAlign.End))
        }
        Column() {
            Row(modifier = modifier.fillMaxWidth()){
                CalcButton(isFunction = true, text = "AC", modifier = modifier.weight(2f), onClick = {
                    expression = ""
                    result = ""

                })
                CalcButton(isFunction = true, text = "⌫", modifier = modifier.weight(1f), onClick = {
                    expression = delCharacter(expression)

                })
                CalcButton(isFunction = true, text = "/", modifier = modifier.weight(1f), onClick = {
                    expression+=it
                })
            }
            Row(modifier = modifier.fillMaxWidth()){
                CalcButton( text = "7", modifier = modifier.weight(1f), onClick = {
                    expression+=it
                })
                CalcButton(text = "8", modifier = modifier.weight(1f), onClick = {
                    expression+=it

                })
                CalcButton(text = "9", modifier = modifier.weight(1f), onClick = {
                    expression+=it
                })
                CalcButton( isFunction = true, text = "*", modifier = modifier.weight(1f), onClick = {
                    expression+=it
                })
            }
            Row(modifier = modifier.fillMaxWidth()){
                CalcButton( text = "4", modifier = modifier.weight(1f), onClick = {
                    expression+=it
                })
                CalcButton(text = "5", modifier = modifier.weight(1f), onClick = {
                    expression+=it
                })
                CalcButton(text = "6", modifier = modifier.weight(1f), onClick = {
                    expression+=it
                })
                CalcButton( isFunction = true, text = "+", modifier = modifier.weight(1f), onClick = {
                    expression+=it
                })
            }
            Row(modifier = modifier.fillMaxWidth()){
                CalcButton( text = "1", modifier = modifier.weight(1f), onClick = {
                    expression+=it
                })
                CalcButton(text = "2", modifier = modifier.weight(1f), onClick = {
                    expression+=it
                })
                CalcButton(text = "3", modifier = modifier.weight(1f), onClick = {
                    expression+=it
                })
                CalcButton( isFunction = true, text = "-", modifier = modifier.weight(1f), onClick = {
                    expression+=it
                })
            }
            Row(modifier = modifier.fillMaxWidth()){
                CalcButton( text = "0", modifier = modifier.weight(1f), onClick = {
                    expression+=it
                })
                CalcButton(text = ".", modifier = modifier.weight(1f), onClick = {
                    expression+=it
                })
                CalcButton( isFunction = true, text = "=", modifier = modifier.weight(1f), onClick = {
                    if(expression.isEmpty()) return@CalcButton
                    result = solveXpression(expression)

                })
            }
        }

    }

}

fun solveXpression(expression: String): String {
    var answer = ""

    try {
        answer = Expression(expression).calculate().toString()
    }catch (e:Exception){
        e.printStackTrace()
        return "Invalid expression"
    }

    return answer.replace(".0","")

}

fun delCharacter(expression: String): String {
    return if (expression.isNotEmpty()) {
        expression.substring(0, expression.length - 1)
    } else {
        expression
    }
}

@Composable
fun CalcButton(modifier: Modifier = Modifier, text: String = "0", isFunction: Boolean = false, onClick: (String) -> Unit = {}){
    ElevatedButton(modifier = modifier
        .size(70.dp)
        .clip(CircleShape)
        .padding(3.dp),onClick = {onClick(text)}, colors = ButtonDefaults.buttonColors(
            containerColor = if(isFunction && text == "=" || text == "AC"){
                MaterialTheme.colorScheme.tertiary
            }else if(isFunction){
                MaterialTheme.colorScheme.secondary
            }else{
                MaterialTheme.colorScheme.primary
            }
        )) {
        Text(text = text, style = TextStyle(fontSize = 25.sp,
            color = if(isFunction && text == "=" || text == "AC"){
                MaterialTheme.colorScheme.error
            }else if(isFunction){
                MaterialTheme.colorScheme.onTertiary
            }else{
                MaterialTheme.colorScheme.onTertiary
            }))
    }

}



@Preview(showBackground = true)
@Composable
fun GreetingrPreview() {
    CalcButton()

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalcScreen()

}