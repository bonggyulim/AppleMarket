package com.example.applemarket.common

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.applemarket.R
import com.example.applemarket.data.DummyData
import com.example.applemarket.data.dataList
import com.example.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val position = intent.getIntExtra("position", 0)
        val bundle = intent.getBundleExtra("bundle")
        val data = bundle?.getParcelable<DummyData>("parcelable")
        var isLiked = data?.check

        binding.profile.clipToOutline = true
        binding.manner.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        data?.let {
            binding.image.setImageResource(it.image)
            binding.product.setText(it.product)
            binding.sellerId.text = it.sellerId
            binding.address.text = it.address
            binding.introduction.setText(it.introduction)
            binding.price.text = DecimalFormat.decimalFormat(it.price)
            binding.heartIcon.setImageResource(
                if (isLiked == true) {R.drawable.ic_favorite}
                else {R.drawable.ic_favorite_border}
            )

        }
        binding.heartIcon.setOnClickListener {
            if (isLiked == true) {
                binding.heartIcon.setImageResource(R.drawable.ic_favorite_border)
            } else {
                binding.heartIcon.setImageResource(R.drawable.ic_favorite)
                Snackbar.make(binding.constLayout, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
            }
            isLiked = isLiked?.not()

        }

        binding.button2.setOnClickListener {
            intent.putExtra("isLiked", isLiked)
            intent.putExtra("position", position)
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}