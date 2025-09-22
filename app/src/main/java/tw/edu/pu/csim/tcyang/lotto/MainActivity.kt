package tw.edu.pu.csim.tcyang.lotto

import android.os.Bundle
import android.widget.Toast // 引入 Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.detectTapGestures // 引入 detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput // 引入 pointerInput
import androidx.compose.ui.platform.LocalContext // 引入 LocalContext
import androidx.compose.ui.tooling.preview.Preview
import tw.edu.pu.csim.tcyang.lotto.ui.theme.LottoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LottoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // 取得當前的 Context
                    val context = LocalContext.current
                    Play(
                        context = this, // 傳遞 Context
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun Play(context: ComponentActivity, modifier: Modifier = Modifier) {
    var lucky by remember {
        mutableStateOf((1..100).random())
    }

    // 在 Column 的 Modifier 中新增 pointerInput 修飾符
    Column(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) { // 新增 pointerInput
                detectTapGestures(
                    onPress = {
                        lucky-- // 短按觸發時數字減1
                        Toast.makeText(context, "螢幕短按，數字減1", Toast.LENGTH_SHORT).show()
                    },
                    onLongPress = {
                        lucky++ // 長按觸發時數字加1
                        Toast.makeText(context, "螢幕長按，數字加1", Toast.LENGTH_SHORT).show()
                    }

                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "樂透數字(1-100)為 $lucky"
        )
        Text(
            text = "楊承智共同編輯"
        )
        Button(
            onClick = { lucky = (1..100).random() }
        ) {
            Text("重新產生樂透碼")
        }
    }
}
// 預覽功能中因無法取得 Context，所以移除 context 參數
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LottoTheme {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("樂透數字(1-100)為 50")
            Button(onClick = {}) {
                Text("重新產生樂透碼")
            }
        }
    }
}