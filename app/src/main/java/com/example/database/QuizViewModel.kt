package com.example.database

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class QuizViewModel(val unitName: String, val dao: TaskDao) : ViewModel() {

    val unitNametoWritingFragment = unitName
    private val _englishWordOfSecret = MutableLiveData<String>("")
    val englishWordOfSecret: LiveData<String>
        get() = _englishWordOfSecret

    private val _transcriptionWordOfSecret = MutableLiveData<String>("")
    val transcriptionWordOfSecret: LiveData<String>
        get() = _transcriptionWordOfSecret

    var russianWordOfSecret: String = ""

    private val _keyWordOfSecret = MutableLiveData<Long>(0L)
    val keyWordOfSecret: LiveData<Long>
        get() = _keyWordOfSecret

    private val _firstCase = MutableLiveData<String>("")
    val firstCase: LiveData<String>
        get() = _firstCase

    private val _secondCase = MutableLiveData<String>("")
    val secondCase: LiveData<String>
        get() = _secondCase

    private val _thirdCase = MutableLiveData<String>("")
    val thirdCase: LiveData<String>
        get() = _thirdCase

    private val _fourthCase = MutableLiveData<String>("")
    val fourthCase: LiveData<String>
        get() = _fourthCase

    private val _isCanStart = MutableLiveData<Boolean>(false)
    val isCanStart: LiveData<Boolean>
        get() = _isCanStart

    var rightOrWrong: String = ""

    var userAnswer: Int = 0
    var randomNumber: Int = 0

    var firstArray = mutableListOf<String>()
    var secondArray = mutableListOf<String>()
    var thirdArray = mutableListOf<String>()
    var fourthArray = mutableListOf<Long>()
    var nameUnit = ""

    var firstArrayClone = mutableListOf<String>()
    var secondArrayClone = mutableListOf<String>()
    var thirdArrayClone = mutableListOf<String>()
    var fourthArrayClone = mutableListOf<Long>()


    var taskForDelArray = listOf<Task>()
//    val tasks = dao.getAll()
    val tasks = dao.get(unitName)

    var firstArrayCloneForWritting: MutableList<String> = mutableListOf()
    lateinit var firstArrayCloneForWrittingToFragment: Array<String>
    var secondArrayCloneForWritting: MutableList<String> = mutableListOf()
    lateinit var secondArrayCloneForWrittingToFragment: Array<String>
    var secondArrayQuiz = mutableListOf<String>()


    fun test() {

        taskForDelArray.forEach {
            secondArray.add(it.taskTranslation)
            if (!it.taskDone) {
                firstArray.add(it.taskName)
                nameUnit = it.taskUnit
                secondArrayClone.add(it.taskTranslation)

                thirdArray.add(it.taskTranscription)
                fourthArray.add(it.taskId)
            }
        }
        firstArrayClone = firstArray.toMutableList()

        thirdArrayClone = thirdArray.toMutableList()
        fourthArrayClone = fourthArray.toMutableList()

        _isCanStart.value = true
    }


    fun firstStart() {
        randomNumber = (1..firstArrayClone.size).random()
        _englishWordOfSecret.value = firstArrayClone.get(randomNumber - 1)
        russianWordOfSecret = secondArrayClone.get(randomNumber - 1)
        _transcriptionWordOfSecret.value = thirdArrayClone.get(randomNumber - 1)
        _keyWordOfSecret.value = fourthArrayClone.get(randomNumber - 1)

        var secondArrayForDel = secondArray.toMutableList()

        secondArrayForDel.remove(russianWordOfSecret)

        secondArrayQuiz.add(russianWordOfSecret)

        var loop = 0
        while (loop < 3 ) {

            val randomWordNumber = (1..secondArrayForDel.size).random()
            secondArrayQuiz.add(secondArrayForDel.get(randomWordNumber - 1))
            secondArrayForDel.removeAt(randomWordNumber - 1)
            loop += 1
        }
        secondArrayQuiz.shuffle()

        _firstCase.value = secondArrayQuiz.get(0)
        _secondCase.value = secondArrayQuiz.get(1)
        _thirdCase.value = secondArrayQuiz.get(2)
        _fourthCase.value = secondArrayQuiz.get(3)
    }

    fun checkAnswerUserQuiz() {

        if (russianWordOfSecret == secondArrayQuiz.get(userAnswer)) {

            rightOrWrong = "Right"
            secondArrayQuiz.clear()

            firstArrayCloneForWritting.add(firstArrayClone.get(randomNumber - 1))
            firstArrayClone.removeAt(randomNumber - 1)

            secondArrayCloneForWritting.add(secondArrayClone.get(randomNumber - 1))
            secondArrayClone.removeAt(randomNumber - 1)

            thirdArrayClone.removeAt(randomNumber - 1)

            fourthArrayClone.removeAt(randomNumber - 1)

            count.value = count.value?.minus(1)

            if (firstArrayClone.size != 0) firstStart()
        } else {


            rightOrWrong = "Wrong"

            _isColorBackground.value = true
            _getItMistake.value = "Get it"

        }
    }

    private val _isColorBackground = MutableLiveData<Boolean>(false)
    val isColorBackground: LiveData<Boolean>
        get() = _isColorBackground

    private val _getItMistake = MutableLiveData<String>("")
    val getItMistake: LiveData<String>
        get() = _getItMistake


    fun forChangeColourIfUserMistake(): Int {
        for ((index, item) in secondArrayQuiz.withIndex()) {
            if (item.equals(russianWordOfSecret)) return index
        }
        return -1
    }

    fun forStartAfterUsersMistake() {
        _isColorBackground.value = false
        _getItMistake.value = ""
        secondArrayQuiz.clear()
        firstStart()
    }


    var count = MutableLiveData(0)
    fun counter() {
        if (firstArrayClone.size >= 7) {
            count.value = 7
        } else count.value = firstArrayClone.size
    }

    fun updateTaskTwo() {
        viewModelScope.launch {
            firstArrayCloneForWritting.forEach { item ->
                taskForDelArray.forEach {
                    if (item.uppercase().equals(it.taskName.uppercase())) {
                        val task = Task()
                        task.taskUnit = nameUnit
                        task.taskName = it.taskName
                        task.taskTranslation = it.taskTranslation
                        task.taskTranscription = it.taskTranscription
                        task.taskId = it.taskId
                        task.taskDone = true
                        dao.update(task)
                    }
                }
            }
        }
    }

    fun arrayToWritingFragment() {
        firstArrayCloneForWrittingToFragment = emptyArray()
        firstArrayCloneForWritting.forEach { firstArrayCloneForWrittingToFragment += it }

        secondArrayCloneForWrittingToFragment = emptyArray()
        secondArrayCloneForWritting.forEach { secondArrayCloneForWrittingToFragment += it }
    }

}