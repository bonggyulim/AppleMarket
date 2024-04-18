package com.example.applemarket.data

import com.example.applemarket.R

fun dataList() : MutableList<DummyData> {
    return mutableListOf(
        DummyData(
            num = 1,
            image = R.drawable.sample1,
            product = R.string.product1,
            introduction = R.string.introduction1,
            sellerId = "대현동",
            price = 1000,
            address = "서울 서대문구 창천동",
            like = 13,
            chat = 25,
            check = false
        ),
        DummyData(
            num = 2,
            image = R.drawable.sample2,
            product = R.string.product2,
            introduction = R.string.introduction2,
            sellerId = "안마담",
            price = 20000,
            address = "인천 계양구 귤현동",
            like = 8,
            chat = 28,
            check = false
        ),
        DummyData(
            num = 3,
            image = R.drawable.sample3,
            product = R.string.product3,
            introduction = R.string.introduction3,
            sellerId = "코코유",
            price = 10000,
            address = "수성구 범어동",
            like = 23,
            chat = 5,
            check = false
        ),
        DummyData(
            num = 4,
            image = R.drawable.sample4,
            product = R.string.product4,
            introduction = R.string.introduction4,
            sellerId = "Nicole",
            price = 10000,
            address = "해운대구 우제2동",
            like = 14,
            chat = 17,
            check = false
        ),
        DummyData(
            num = 5,
            image = R.drawable.sample5,
            product = R.string.product5,
            introduction = R.string.introduction5,
            sellerId = "절명",
            price = 150000,
            address = "연제구 연산제8동",
            like = 22,
            chat = 9,
            check = false
        ),
        DummyData(
            num = 6,
            image = R.drawable.sample6,
            product = R.string.product6,
            introduction = R.string.introduction6,
            sellerId = "미니멀하게",
            price = 50000,
            address = "수원시 영통구 원천동",
            like = 25,
            chat = 16,
            check = false
        ),
        DummyData(
            num = 7,
            image = R.drawable.sample7,
            product = R.string.product7,
            introduction = R.string.introduction7,
            sellerId = "굿리치",
            price = 150000,
            address = "남구 옥동",
            like = 142,
            chat = 54,
            check = false
        ),
        DummyData(
            num = 8,
            image = R.drawable.sample8,
            product = R.string.product8,
            introduction = R.string.introduction8,
            sellerId = "난쉽",
            price = 180000,
            address = "동래구 온천제2동",
            like = 31,
            chat = 7,
            check = false
        ),
        DummyData(
            num = 9,
            image = R.drawable.sample9,
            product = R.string.product9,
            introduction = R.string.introduction9,
            sellerId = "알뜰한",
            price = 30000,
            address = "원주시 명륜2동",
            like = 7,
            chat = 28,
            check = false
        ),
        DummyData(
            num = 10,
            image = R.drawable.sample10,
            product = R.string.product10,
            introduction = R.string.introduction10,
            sellerId = "똑태현",
            price = 190000,
            address = "중구 동화동",
            like = 40,
            chat = 6,
            check = false
        )



    )

}