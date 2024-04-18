package com.example.applemarket.common

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.provider.Settings
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.R
import com.example.applemarket.data.dataList
import com.example.applemarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 회색선
        val decoration = DividerItemDecoration(applicationContext, LinearLayoutManager(this).orientation)
        binding.recyclerview.addItemDecoration(decoration)

        // 싱글톤 인스턴스 가져오기
        val datalist = dataList()
        val detailIntent = Intent(this, DetailActivity::class.java)

        // 어댑터 연결
        val adapter = MyAdapter(datalist)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)


        // 결과반환 인텐트 설정
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == RESULT_OK) {
                val position = it.data?.getIntExtra("position", 0) as Int
                val isLiked = it.data?.getBooleanExtra("isLiked", false) as Boolean

                if (isLiked != datalist[position].check) {
                    datalist[position].check = !datalist[position].check

                    if(isLiked) {
                        datalist[position].like++
                    } else {
                        datalist[position].like--
                    }
                }
                adapter.notifyItemChanged(position)
            }
        }


        // 디테일페이지에 bundle 넘기기
        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(viwe: View, position: Int) {
                val bundle = Bundle()
                bundle.putParcelable("parcelable", datalist[position] as Parcelable)
                detailIntent.putExtra("bundle", bundle)
                detailIntent.putExtra("position", position)
                resultLauncher.launch(detailIntent)
            }
        }


        // 롱클릭 리스너 설정
        adapter.longClick = object : MyAdapter.LongClick {
            override fun longClick(position: Int) {
                var builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("상품 삭제")
                builder.setMessage("해당 상품을 삭제합니다.")
                builder.setIcon(R.drawable.chat)

                val listener = DialogInterface.OnClickListener { p0, p1 ->
                    // 다이얼로그 인터페이스 생성, 버튼 클릭시 처리 이벤트
                    if (p1 == DialogInterface.BUTTON_POSITIVE ) {
                        adapter.removeItem(position)
                        Toast.makeText(this@MainActivity, "상품 삭제 완료", Toast.LENGTH_SHORT).show()
                    }
                }
                builder.setPositiveButton("확인", listener)
                builder.setNegativeButton("취소", null)
                builder.show()
            }
        }


        // 알림 권한 설정
        binding.bellbutton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (!NotificationManagerCompat.from(this).areNotificationsEnabled()) {
                    val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                        putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                    }
                    startActivity(intent)
                }
            }
            notification()
        }

        //플로팅 버튼
        val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 500 }
        val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 500 }
        var isTop = true
        binding.recyclerview.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.recyclerview.canScrollVertically(-1)
                    && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.floatBtn01.startAnimation(fadeOut)
                    binding.floatBtn01.visibility = View.GONE
                    isTop = true
                } else {
                    if(isTop) {
                        binding.floatBtn01.startAnimation(fadeIn)
                        binding.floatBtn01.visibility = View.VISIBLE
                        isTop = false
                    }
                }
            }
        })

        binding.floatBtn01.setOnClickListener{
            binding.recyclerview.smoothScrollToPosition(0)
        }
    }

    // 백버튼 다이얼로그
    override fun onBackPressed() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("종료")
        builder.setMessage("정말 종료하시겠습니까?")
        builder.setIcon(R.drawable.chat)

        val listener = DialogInterface.OnClickListener { p0, p1 ->
            // 다이얼로그 인터페이스 생성, 버튼 클릭시 처리 이벤트
            if (p1 == DialogInterface.BUTTON_POSITIVE ) super.onBackPressed()
        }
        builder.setPositiveButton("확인", listener)
        builder.setNegativeButton("취소", null)
        builder.show()

    }


    // 알림 채널 생성 및 알림 생성 함수
    fun notification(){
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        val channelId="one-channel"
        val channelName="My Channel One"
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
            description = "My Channel One Description"
            setShowBadge(true)
            enableVibration(true)
        }

        manager.createNotificationChannel(channel)
        builder = NotificationCompat.Builder(this, channelId)

        builder.run {
            setSmallIcon(R.mipmap.ic_launcher)
            setWhen(System.currentTimeMillis())
            setContentTitle("키워드 알림")
            setContentText("설정한 키워드에 대한 알림이 도착 했습니다!!")
        }

        manager.notify(11, builder.build())
    }
}