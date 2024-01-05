package com.example.database


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log

class WritingViewModel(
    firstArrayCloneForWritting: Array<String>,
    secondArrayCloneForWritting: Array<String>,
    unitName: String
) : ViewModel() {
    val unitName = unitName
    var firstArrayCloneForWritting = mutableListOf<String>()
    var secondArrayCloneForWritting = mutableListOf<String>()
    var firstArrayCloneForWrittingService = firstArrayCloneForWritting
    var secondArrayCloneForWrittingService = secondArrayCloneForWritting

    private val _russianWordOfSecret = MutableLiveData<String>()
    val russianWordOfSecret: LiveData<String>
        get() = _russianWordOfSecret

    private val _englishWordOfSecretHint = MutableLiveData<String>("Click to get a hint")
    val englishWordOfSecretHint: LiveData<String>
        get() = _englishWordOfSecretHint

    private val _ifUserWrongAnswer = MutableLiveData<String>("")
    val ifUserWrongAnswer: LiveData<String>
        get() = _ifUserWrongAnswer

    private val _isUserWrong = MutableLiveData<Boolean>(false)
    val isUserWrong: LiveData<Boolean>
        get() = _isUserWrong


    var randomNumber: Int = 0
    var englishWordOfSecret: String = ""

        var fromEditText: String = ""
    var rightOrWrong: String = ""
    var count = MutableLiveData(0)

    init {
        firstStart()
        writing()
    }

    fun firstStart() {
        for ((index, item) in firstArrayCloneForWrittingService.withIndex()) {
            firstArrayCloneForWritting.add(firstArrayCloneForWrittingService[index])
        }
        for ((index, item) in secondArrayCloneForWrittingService.withIndex()) {
            secondArrayCloneForWritting.add(secondArrayCloneForWrittingService[index])
        }
        count.value = firstArrayCloneForWritting.size

    }

    fun ifUserWrongAnswer() {
        _ifUserWrongAnswer.value = " "
    }

    fun writing() {
        randomNumber = (1..firstArrayCloneForWritting.size).random()
        englishWordOfSecret = firstArrayCloneForWritting.get(randomNumber - 1)
        _russianWordOfSecret.value = secondArrayCloneForWritting.get(randomNumber - 1)
    }

    fun hintUsers() {
        _englishWordOfSecretHint.value = englishWordOfSecret
    }

    fun checkAnswerUserWriting() {
        if (englishWordOfSecret.uppercase().filterNot { it.isWhitespace() }
                .equals(
                    fromEditText.uppercase()
                        .filterNot { it.isWhitespace() }) || wordContainBracket().uppercase()
                .filterNot { it.isWhitespace() }
                .equals(fromEditText.uppercase().filterNot { it.isWhitespace() })
        ) {
            count.value = count.value?.minus(1)
            firstArrayCloneForWritting.removeAt(randomNumber - 1)
            secondArrayCloneForWritting.removeAt(randomNumber - 1)

            _isUserWrong.value = false

            if (count.value != 0) writing()

            _englishWordOfSecretHint.value = "Click to get a hint"
            rightOrWrong = "Right"
        } else {
            _englishWordOfSecretHint.value = "Click to get a hint"

            firstArrayCloneForWritting.add(englishWordOfSecret)
            secondArrayCloneForWritting.add(_russianWordOfSecret.value.toString())
            firstArrayCloneForWritting.add(englishWordOfSecret)
            secondArrayCloneForWritting.add(_russianWordOfSecret.value.toString())


//            _ifUserWrongAnswer.value =
//                "${_russianWordOfSecret.value}\nВы ввели: $fromEditText\nПравильный ответ: $englishWordOfSecret\nНажмите чтобы скрыть это сообщение"
            rightOrWrong = "Wrong"
            _isUserWrong.value = true
        }
    }

    //    fun wordContainBracket(): String {
//        var result = ""
//        var englishWordForCheck = englishWordOfSecret
//        Log.i(englishWordForCheck, "I'm log 100 and - $englishWordForCheck")
//        var startBracketList = mutableListOf<Int>()
//        var endBracketList = mutableListOf<Int>()
//        englishWordForCheck.forEachIndexed { index, char ->
//            if (char.equals('(')) startBracketList.add(index)
//            if (!startBracketList.isEmpty() && char.equals(')')) endBracketList.add(index)
//        }
//        Log.i(startBracketList.toString(), "I'm log 107 and - ${startBracketList.toString()}")
//        Log.i(endBracketList.toString(), "I'm log 108 and - ${endBracketList.toString()}")
//        startBracketList.reverse()
//        endBracketList.reverse()
//        if (!startBracketList.isEmpty() && !endBracketList.isEmpty()) {
//            if (startBracketList.size == endBracketList.size) {
//                for (index in startBracketList.indices) {
//                    Log.i(
//                        startBracketList.size.toString(),
//                        "I'm log 114 and ${startBracketList.get(index).toString()}"
//                    )
//
//                    result = englishWordForCheck.removeRange(
//                        startBracketList.get(index)..endBracketList.get(
//                            index
//                        )
//                    )
//                }
//            }
//        }
//        Log.i(englishWordForCheck, "I'm log 119 and - $result")
//        return result
//    }
    fun wordContainBracket(): String {
        var counter = 0
        var result = ""
        englishWordOfSecret.forEach { char ->
            if (char.equals('(')) counter++
            else if (counter != 0 && char.equals(')')) counter--
            else if (counter == 0) {
                result += char.toString()
            }
        }
        return result
    }
}

//class WritingViewModel(
//    firstArrayCloneForWritting: Array<String>,
//    secondArrayCloneForWritting: Array<String>,
//    unitName: String
//) : ViewModel() {
//    val unitName = unitName
//    var firstArrayCloneForWritting = mutableListOf<String>()
//    var secondArrayCloneForWritting = mutableListOf<String>()
//    var firstArrayCloneForWrittingService = firstArrayCloneForWritting
//    var secondArrayCloneForWrittingService = secondArrayCloneForWritting
//
//    private val _russianWordOfSecret = MutableLiveData<String>()
//    val russianWordOfSecret: LiveData<String>
//        get() = _russianWordOfSecret
//
//    private val _englishWordOfSecretHint = MutableLiveData<String>("Click to get a hint")
//    val englishWordOfSecretHint: LiveData<String>
//        get() = _englishWordOfSecretHint
//
//    private val _ifUserWrongAnswer = MutableLiveData<String>("")
//    val ifUserWrongAnswer: LiveData<String>
//        get() = _ifUserWrongAnswer
//
//
//    var randomNumber: Int = 0
//    var englishWordOfSecret: String = ""
//
//    var fromEditText: String = ""
//    var rightOrWrong: String = ""
//    var count = MutableLiveData(0)
//
//    fun firstStart() {
//        for ((index, item) in firstArrayCloneForWrittingService.withIndex()) {
//            firstArrayCloneForWritting.add(firstArrayCloneForWrittingService[index])
//        }
//        for ((index, item) in secondArrayCloneForWrittingService.withIndex()) {
//            secondArrayCloneForWritting.add(secondArrayCloneForWrittingService[index])
//        }
//        count.value = firstArrayCloneForWritting.size
//
//    }
//
//    fun ifUserWrongAnswer() {
//        _ifUserWrongAnswer.value = " "
//    }
//
//    fun writing() {
//        randomNumber = (1..firstArrayCloneForWritting.size).random()
//        englishWordOfSecret = firstArrayCloneForWritting.get(randomNumber - 1)
//        _russianWordOfSecret.value = secondArrayCloneForWritting.get(randomNumber - 1)
//    }
//
//    fun hintUsers() {
//        _englishWordOfSecretHint.value = englishWordOfSecret
//    }
//
//    fun checkAnswerUserWriting() {
//        if (englishWordOfSecret.uppercase().filterNot { it.isWhitespace() }
//                .equals(fromEditText.uppercase().filterNot { it.isWhitespace() })) {
//            count.value = count.value?.minus(1)
//            firstArrayCloneForWritting.removeAt(randomNumber - 1)
//            secondArrayCloneForWritting.removeAt(randomNumber - 1)
//
//            _englishWordOfSecretHint.value = "Click to get a hint"
//            rightOrWrong = "Right"
//        } else {
//            _englishWordOfSecretHint.value = "Click to get a hint"
//            _ifUserWrongAnswer.value =
//                "${_russianWordOfSecret.value}\nВы ввели: $fromEditText\nПравильный ответ: $englishWordOfSecret\nНажмите чтобы скрыть это сообщение"
//            rightOrWrong = "Wrong"
//
//        }
//    }
//}