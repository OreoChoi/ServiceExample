package com.example.serviceexample

import android.app.IntentService
import android.content.Intent
import android.util.Log

/**
 * IntentService
 *
 * 1. Service의 하위 클래스이다.
 *
 * 2. 별도의 스레딩을 하지 않아도 IntentService만 실행하면
 *    메인 스레드가 아닌 작업 스레드에서 작업을 실행합니다.
 *
 * 3. Service를 순차적으로 실행합니다.
 *    해당 IntentService를 여러번 호출한다고 해도
 *    작업 Queue에 보관한다음 순차적으로 꺼내 작업을 실행합니다.
 *
 * 4. 작업이 끝나면 IntentService가 자체적으로 서비스를 중단하므로 개발자가 처리할 것은 없습니다.
 *
 * */
class MyIntentService: IntentService("MyIntentService"){

    override fun onHandleIntent(intent: Intent?) {
        Log.d("tag","MSG : ${intent?.getStringExtra("MSG")}")
        for(i in 1..10){
            Thread.sleep(1000)
            Log.d("tag","onHandle : $i")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("tag","Job execution finished")
    }
}