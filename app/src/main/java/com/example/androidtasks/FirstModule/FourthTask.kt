package com.example.androidtasks.FirstModule

fun shakeSort(list: List<Int?>) : List<Int?> {
    val mList = list.toMutableList()
    var left = 0
    var right = mList.size - 1
    var flag = true
    while (left < right && flag) {
        flag = false
        for (i in left until right) {
            val first = mList[i]
            val second = mList[i+1]
            if (first == null || (second != null && first > second)) {
                mList[i] = mList[i+1].also{mList[i+1] = mList[i]}
                flag = true
            }
        }
        right--
        for (i in right downTo left + 1) {
            val first = mList[i]
            val second = mList[i-1]
            if (first == null || (second != null && first > second)) {
                mList[i] = mList[i-1].also{mList[i-1] = mList[i]}
                flag = true
            }
        }
        left++
        if (!flag) break
    }
    return mList.toList()
}
