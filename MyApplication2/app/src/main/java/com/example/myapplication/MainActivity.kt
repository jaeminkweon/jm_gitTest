package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup


class MainActivity : AppCompatActivity() {
    /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        textView2.text = "Second에서 돌아옴" //인자 requestCode는 어떤 액티비티가 갔다왔는지 알수있다
        /* 무슨액티비티로부터 돌아왔냐에 따라 분기, resultCode는 해당 액티비티에서 setResult(Activity.RESULT_OK. CANCLED, 등등)로 설정해놓고 여기서 또 분기칠 수 있다
        when(requestCode){
            1 -> {
                textView2.text = "Second에서 돌아옴"
            }
            2 -> {
                textView2.text = "Third에서 돌아옴"
            }
        }
        */
    }
    */



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        /*
        button.setOnClickListener{
            var intent = Intent(this, SecondActivity::class.java)
            //startActivity(intent)
            startActivityForResult(intent, 100) // 다른 activity로 이동하고 종료되서 다시 돌아올때 처리가 필요한경우 사용
        }
        button16.setOnClickListener{
            finish()
        }
        */
        /* 액티비티 종료버튼
        button16.setOnClickListener{
            finish()
        }
        */
        /* 다른 Activity 로 이동 버튼
        button.setOnClickListener{
            var intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("data1", 100)
            intent.putExtra("data2", 11.11)
            intent.putExtra("data3", false)
            intent.putExtra("data4", "hello")
            startActivity(intent)
            //다른 액티비티로 가서 데이터 보고싶을때는 intent.getIntExtra("data1",0) //데이터이름, 디폴트값
            //액티비티 2->1로 데이터 주고싶을때 2에서 intent2 선언후 setResult(RESULT_OK,intent2)
        }
        */
        /* 버튼클릭시 반응
        button.setOnClickListener{
            var now = System.currentTimeMillis()
            textView.text = "버튼클릭:${now}"
        }
        */
        /* 1.스레드와 핸들러를 이용한 화면처리
        handler = DisplayHandler()
        */

        /* main thread를 무한루프로 잡아먹으면 화면처리 불가
        while(true){
            var now = System.currentTimeMillis()
            textView2.text = "무한루프:${now}"
        }
        */

        /* 사용자 스레드 시작
        var thread = ThreadClass1()
        thread.start()
        */

        //checkPermission() 사용자 권한 요청

        /* 1.스레드와 핸들러를 이용한 화면처리
        isRunning = true
        var thread = ThreadClass()
        thread.start()
        */
    }



/* 1.스레드와 핸들러를 이용한 화면처리
override fun onDestroy() {
super.onDestroy()
isRunning = false
}
*/
/* 1.스레드와 핸들러를 이용한 화면처리
inner class ThreadClass : Thread(){
override fun run() {
while(isRunning){
    SystemClock.sleep(100)
    var time = System.currentTimeMillis()
    Log.d("test1", "Thread : ${time}")
    //textView2.text = "Thread : ${time}"
    var msg = Message()
    msg.what = 0 // 이 메세지의 처리작업 종류 설정
    msg.obj = time // 객체로 넘길수있다는건 값을 무한정 넘길수 있다는 의미
    handler?.sendMessage(msg)
}
}
}
*/
/* 1.스레드와 핸들러를 이용한 화면처리
inner class DisplayHandler : Handler(){
override fun handleMessage(msg: Message?) {
super.handleMessage(msg)
textView2.text = "Thread : ${msg?.obj}"
}
}
*/
/*
사용자 스레드 예시
inner class ThreadClass1 : Thread(){
override fun run(){
while(true){
    SystemClock.sleep(100)
    var now = System.currentTimeMillis()
    Log.d("test1", "쓰레드 : ${now}")
}
}
//사용자 thread에서 화면처리 안드로이드 8.0부터 가능
}
*/
/*
//권한 체크 함수
fun checkPermission(){
//마시멜로 이상버전에서는 사용자가 직접 권한 허용 해줘야함.
if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
return;
}

//권한이 비활성화 되어있으면 권한허용 요청
for(permission : String in permission_list){
var check = checkCallingOrSelfPermission(permission)

if(check == PackageManager.PERMISSION_DENIED){
    requestPermissions(permission_list, 0);
    break;
}
}
}
*/
/* permission 체크후 불리는 함수
override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
super.onRequestPermissionsResult(requestCode, permissions, grantResults)

textView.text = ""

for(idx in grantResults.indices){
if(grantResults[idx] == PackageManager.PERMISSION_GRANTED){
    textView.append("${permission_list[idx]} : 허용함\n");
}
else{
    textView.append("${permission_list[idx]} : 허용하지않음\n");
}
}
}
*/
}
