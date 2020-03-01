package com.example.intershalacourseapp

import android.icu.text.StringPrepParseException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class Main2Activity : AppCompatActivity() {
    var mytag: String = "mytask"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        button.setOnClickListener {
            CoroutineScope(IO).launch {
                var result: String = longTask1()
                Log.d(mytag, result)
                setTextToTextview(result)
                var result2: String = longTask2()
                setTextToTextview(result2)

                var part1: Deferred<String> = async { longTask1() }
                var part2: Deferred<String> = async { longTask2() }
                setTextToTextview(part1.await() + part2.await())
            }
        }

    }

    suspend fun setTextToTextview(message: String) {
        withContext(Main) {
            textView.append(message)
        }
    }

    suspend fun longTask1(): String {
        delay(2000)
        return "Hello\n"
    }

    suspend fun longTask2(): String {
        delay(2000)
        return "Hi\n"
    }
}
