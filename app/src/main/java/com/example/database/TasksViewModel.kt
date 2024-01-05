package com.example.database

import android.util.Log
import androidx.core.util.rangeTo
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class TasksViewModel(val dao: TaskDao) : ViewModel() {

    var nameUnitToQuizFragment = ""

    var taskForDelArrayForRecycleView = listOf<Task>()
    val tasks = dao.getAll()
    val unitNameFromDatabase = dao.getUnitName()

    fun firstStartApp() {
        if (taskForDelArrayForRecycleView.isEmpty()) {
            defaultModule()
            defaultModuleUnitOne()
            defaultModuleUnitTwo()
            defaultModuleUnitThree()
            defaultModuleUnitFour()
            defaultModuleUnitFive()
            harryPotterOneChapterOne()
        }
    }

    fun getUnitList(unitName: String): LiveData<List<Task>> {
        nameUnitToQuizFragment = unitName
        return dao.get(unitName)
    }

    fun update() {
        viewModelScope.launch {
            taskForDelArrayForRecycleView.forEach {
                val task = Task()
                task.taskUnit = it.taskUnit
                task.taskName = it.taskName
                task.taskTranslation = it.taskTranslation
                task.taskTranscription = it.taskTranscription
                task.taskId = it.taskId
                task.taskDone = false
                dao.update(task)
            }

        }
    }

    //    fun updateModuleTaskDone(){
//        taskForDelArrayForRecycleView.forEach {
//            defaultAdd(it.taskName, it.taskTranscription, false, it.taskTranslation)
//            idTaskForDel = it.taskId
//            delTaskForModule()
//        }
//    }
    fun checkStatedListModule(): Boolean {
        var arrayService = mutableListOf<Boolean>()
        taskForDelArrayForRecycleView.forEach {
            if (it.taskDone == false) {
                arrayService.add(it.taskDone)
            }
        }
        if (arrayService.size == 0) return false
        else return true
    }

    fun defaultModule() {
        var firstArray =
            mutableListOf(
                "I (am(are)) u","I (am(are)) u","I (am(are)) u","I (am(are)) u","I (am(are)) u","I (am(are)) u","I (am(are)) u"
//                "First",
//                "Second",
//                "Third",
//                "Fourth",
//                "Fifth",
//                "Sixth",
//                "Seventh",
//                "Eighth",
//                "Ninth",
//                "Tenth"
            )
        var secondArray = mutableListOf(
            "I (am(are)) u","I (am(are)) u","I (am(are)) u","I (am(are)) u","I (am(are)) u","I (am(are)) u","I (am(are)) u"

//            "Первый",
//            "Второй",
//            "Третий",
//            "Четвертый",
//            "Пятый",
//            "Шестой",
//            "Седьмой",
//            "Восьмой",
//            "Девятый",
//            "Десятый"
        )
        var thirdArray = mutableListOf(
            "I (am(are)) u","I (am(are)) u","I (am(are)) u","I (am(are)) u","I (am(are)) u","I (am(are)) u","I (am(are)) u"
//            " [fɜːst]", "[ˈsekənd]", "[θɜːd]", "[fɔːθ]", "[fɪfθ]", "[sɪksθ]",
//            " [sevnθ]", "[eɪtθ]", "[naɪnθ]", "[tenθ]"
        )
        for (index in firstArray.indices) {
            defaultAdd(
                "Test",
                firstArray.get(index),
                thirdArray.get(index),
                false,
                secondArray.get(index)
            )
        }
    }
    fun defaultModuleUnitOne() {
        var firstArray =
            mutableListOf(
                "spend time with relatives", "do housework", "do homework", "do some exercise", "do the shopping", "do some work", "stay in (for the evening)", "make a to-do list", "make future plans", "eat healthy food",
"have a good time", "have fun", "have a family meal", "have a lie-in", "have an early night", "go on a trip", "go to bed late", "chat with friends online", "to describe", "an event",
"an exhibition", "a museum", "a theatre", "a concert", "a band", "a stage", "a reason for doing smth", "tiny", "a view", "poor (quality, health)",
                "terrible", "close to", "next to", "in front of", "within", "to move on", "to record / a record", "to film / a film", "to post / a post", "to experience / an experience",
                "enjoy doing smth", "avoid doing smth", "worry about smth", "psychology / psychologist", "miss smth", "feel good / bad", "save (time/money)", "immediate", "pleasure", "successful",
"research (v, n)", "equal (balance)", "relationship", "appointment", "nearly always", "most days", "sometimes", "occasionally", "hardly ever",
                "rarely", "warm", "uncomfortable", "cold", "chilly", "cool", "frosty", "dry","wet","humid",
                "damp", "foggy", "cloudy", "stormy", "hot","pleasant", "a bit (cold)","too cold","short periods of rain", "heavy rain",
"a shower", "a thunder", "a thunderstorm", "a lightning", "outside / outdoors", "indoors", "I don't mind doing smth", "I can't stand (do)ing...","helicopter","crowd",
"facilities", "slope", "peak", "to climb a mountain", "to cover", "be popular with", "hike","incredible", "resort","destination",
              "narrow","landscape","scenery","peaceful","quiet","to depend on"
                )
        var secondArray = mutableListOf(
            "проводите время с родственниками", "делать работу по дому", "делать домашнее задание", "делать упражнения", "делать покупки", "делать работу", "оставаться (вечером дома)" ,"составьте список дел", "стройте планы на будущее", "есть здоровую пищу",
    "хорошо проводить время", "развлекаться", "have a family meal", "валяться в постели", "рано ложиться спать", "отправиться в поездку", "поздно ложиться спать", "общаться с друзьями онлайн","описывать" , "событие",
"выставка", "музей", "театр", "концерт", "музыкальная группа", "сцена", "причина для того, чтобы что-то делать", "крошечный", "вид, пейзаж", "плохой (о качестве, здоровье)",
            "ужасный", "близко/рядом с", "следующий, потом, рядом", "перед", "внутри, в пределах", "идти дальше; продолжать движение", "записывать, запись", "снимать фильм, фильм", "постить, пост", "спытывать, опыт",
            "нравиться делать что-то", "избегать делать что-то", "беспокоиться о чем-то", "психология / психолог", "скучать, пропускать (уроки)", "чувствовать себя хорошо/плохо", "экономить (время, деньги)","немедленный", "удовольствие","удачный, успешный",
"исследовать, исследование", "равный (баланс)","отношения", "встреча", "почти всегда", "в большинство дней", "иногда", "изредка", "почти никогда",
            "редко", "теплый", "неудобный, некомфортный", "холодный", "прохладный, зябкий", "прохладный", "морозный", "сухой", "мокрый","влажный, сырой",
            "влажный, сырой (мокрый асфальт)", "туманный", "облачный", "бурный, штормовой", "жаркий", "приятный, радостный","немного (холодно)" ,"слишком холодно","кратковременный дождь","сильный дождь",
"ливень", "гром", "гроза", "молния", "на улице", "в помещении", "я не возражаю", "терпеть не могу делать", "вертолет","толпа, народ",
"удобства, возможности, оборудование", "склон", "вершина, пик", "взбираться на гору", "покрывать", "быть популярным среди", "ходить в поход", "невероятный", "курорт","пункт назначения",
"узкий","пейзаж","декорации, пейзаж, вид","мирный","тихий","зависеть от"
        )
        var thirdArray = mutableListOf(
            "[ˈrelətɪvz]", "[duː ˈhaʊswɜːk]", " [duː ˈhəʊmwɜːk]", "[duː sʌm ˈeksəsaɪz]", "[duː ðiː ˈʃɒpɪŋ]", "[duː sʌm wɜːk]", "[steɪ ɪn]","[meɪk ə təˈduː lɪst]", "[meɪk ˈfjuːʧə plænz]", "[iːt ˈhelθɪ fuːd]",
"[hæv ə gʊd taɪm]", "[hæv fʌn]", "[hæv ə ˈfæm(ə)lɪ miːl]", "[hæv ə laɪˈɪn]", "[hæv ən ˈɜːlɪ naɪt]", "[gəʊ ɒn ə trɪp]", "[gəʊ tuː bed leɪt]","[ʧæt wɪð frend ˈɒnlaɪn]", "[tuː dɪsˈkraɪb]", "[ən ɪˈvent]",
"[ən eksɪˈbɪʃn]", "[ə mjuːˈzɪəm]", "[ə ˈθɪətə]" ,"[ə ˈkɒnsət]", "[ə bænd]" ,"[ə steɪʤ]", "[ə riːzn fɔː ˈduɪŋ ˈsʌmθɪŋ]", "[ˈtaɪnɪ]", "[ə vjuː]", "[pʊə (ˈkwɒlɪtɪ, helθ)]",
            "[ˈterəbl]","[kləʊs tuː]", "[nekst tuː]", "[ɪn frʌnt ɒv]", "[wɪˈðɪn]", "[tuː muːv ɒn]", "[tuː ˈrekɔːd / ə ˈrekɔːd]", "[tuː fɪlm / ə fɪlm]", "[tuː pəʊst / ə pəʊst]", "[tuː ɪksˈpɪərɪəns / ə ɪksˈpɪərɪəns]",
            "[ɪnˈʤɔɪ ˈduɪŋ ˈsʌmθɪŋ]", "[əˈvɔɪd ˈduɪŋ ˈsʌmθɪŋ]", "[ˈwʌrɪ əˈbaʊt ˈsʌmθɪŋ]", "[saɪˈkɒləʤɪ / saɪˈkɒləʤɪst]", "[mɪs ˈsʌmθɪŋ]", "[fiːl gʊd / bæd]", "[seɪv (taɪm / ˈmʌnɪ)]", "[ɪˈmiːdɪət]", "[ˈpleʒə]", "[səkˈsesf(ə)l]",
"[rɪˈsɜːʧ]","[ˈiːkwəl ˈbæləns]", "[rɪˈleɪʃnʃɪp]", "[əˈpɔɪntmənt]", "[ˈnɪəlɪ ˈɔːlw(e)ɪz]", "[məʊst deɪz]", "[ˈsʌmtaɪmz]", "[əˈkeɪʒnəlɪ]", "[ˈhɑːdlɪ ˈevə]",
            "[ˈreəlɪ]", "[wɔːm]", "[ʌnˈkʌmf(ə)təb(ə)l]", "[kəʊld]", "[ˈʧɪlɪ]", "[kuːl]", "[ˈfrɒstɪ]", "[draɪ]", "[wet]","[ˈhjuːmɪd]",
            "[dæmp]", "[ˈfɒgɪ]", "[ˈklaʊdɪ]", "[ˈstɔːmɪ]" , "[hɒt]", "[pleznt]","[ə bɪt (kəʊld)]", "[tuː kəʊld]","[ʃɔːt ˈpɪərɪəds ɒv reɪn]","[ˈhevɪ reɪn]]",
            "[ə ˈʃaʊə]", "[ə ˈθʌndə]", "[ə ˈθʌndəstɔːm]", "[ə ˈlaɪtnɪŋ]", "[ˈaʊtsaɪd / aʊtˈdɔːz]","[ɪnˈdɔːz]", "[maɪnd]", "[stænd]", "[ˈhelɪkɒptə]","[kraʊd]",
"[fəˈsɪlɪtɪz]", "[sləʊp]","[piːk]","[klaɪm ɑ ˈmaʊntɪn]", "[ˈkʌvə]", "[biː ˈpɒpjʊlə wɪð]" , "[haɪk]","[ɪnˈkredəbl]","[rɪˈzɔːt]", "[destɪˈneɪʃn]",
"[ˈnærəʊ]", "[ˈlændskeɪp]","[ˈsiːnərɪ]","[ˈpiːsf(ə)l]","[ˈkwaɪət]", "[dɪˈpend ɒn]"
        )
        for (index in firstArray.indices) {
            defaultAdd(
                "Unit 1",
                firstArray.get(index),
                thirdArray.get(index),
                false,
                secondArray.get(index)
            )
        }
    }

    fun defaultModuleUnitTwo() {
        var firstArray =
            mutableListOf(
                "duvet",
                "fast lane",
                "fine",
                "flower stall",
                "house swap",
                "household",
                "possessions",
                "house-sitting",
                "industry",
                "lively",
                "location",
                "market place",
                "maze",
                "microwave oven",
                "mirror",
                "narrow",
                "navigation",
                "on business",
                "on holiday",
                "on public transport",
                "on the Internet",
                "on the way",
                "on time",
                "on TV",
                "pan",
                "parking space",
                "pavement artist",
                "pedestrian area",
                "play area",
                "publisher",
                "rage",
                "recycle",
                "rubbish",
                "rug",
                "carpet",
                "safe",
                "satellite dish",
                "sheet",
                "shop display",
                "pillow",
                "shopper",
                "solution",
                "souvenir seller",
                "stall",
                "strange",
                "statue",
                "street cleaner",
                "street performer",
                "student accommodation",
                "tap",
                "towel",
                "tower block",
                "truck",
                "wardrobe",
                "wash basin",
                "happen",
                "serve",
                "neighbourhood",
                "plenty",
                "take place",
                "pray",
                "miss",
                "appointment",
                "walk along",
                "feel",
                "recent",
                "city council",
                "honest",
                "rude",
                "buzz",
                "keep going",
                "straight",
                "crossroad",
                "skyscraper",
                "attraction",
                "building site",
                "property",
                "developer",
                "expand",
                "suddenly",
                "demand",
                "argue",
                "through",
                "waste products",
                "pollution",
                "conclusion",
                "embarrassed",
                "architect",
                "block",
                "candle",
                "chest of drawers",
                "cloth",
                "collect",
                "crowded",
                "destroy",
                "dull",
                "dustpan and brush"
            )
        var secondArray = mutableListOf(
            "одеяло",
            "скоростная полоса",
            "штрафовать",
            "цветочный киоск",
            "обмен домами",
            "домашнее хозяйство",
            "имущество",
            "присматривать за домом",
            "промышленность",
            "оживленный",
            "местоположение",
            "рыночная площадь",
            "лабиринт",
            "микроволновая печь",
            "зеркало",
            "узкий",
            "навигация",
            "по делу",
            "в отпуске",
            "на общественном транспорте",
            "в интернете",
            "по/на пути",
            "вовремя",
            "по телевизору",
            "сковорода",
            "парковочное место",
            "уличный художник",
            "пешеходная зона",
            "игровая площадка",
            "издатель",
            "ярость/гнев",
            "перерабатывать",
            "мусор",
            "коврик (прикроватный)",
            "ковер",
            "безопасный",
            "спутниковая тарелка",
            "простыня",
            "витрина магазина",
            "подушка",
            "покупатель",
            "решение",
            "продавец сувениров",
            "киоск",
            "странный",
            "статуя",
            "дворник",
            "уличный артист",
            "общежитие",
            "кран",
            "полотенце",
            "высотка",
            "грузовик",
            "гардероб",
            "умывальник",
            "происходить/случаться",
            "обслуживать",
            "соседство",
            "множество",
            "происходить",
            "молиться",
            "скучать",
            "назначенная встреча",
            "идти вдоль",
            "чувствовать",
            "недавний",
            "городской совет",
            "честный",
            "грубый",
            "жужжание/жужжать",
            "продолжайте идти",
            "прямой",
            "перекресток",
            "небоскреб",
            "привлечение",
            "строительная площадка",
            "собственность/свойство",
            "разработчик",
            "расширять",
            "внезапно",
            "спрос/требование",
            "спорить",
            "через",
            "отходы",
            "загрязнение окружающей среды",
            "вывод",
            "смущенный",
            "архитектор",
            "блокировать",
            "свеча",
            "комод",
            "тряпка",
            "собирать",
            "многолюдный",
            "разрушать",
            "скучный",
            "совок и щетка"
        )
        var thirdArray = mutableListOf(
            "[ˈduveɪ]",
            "[fɑːst leɪn]",
            "[faɪn]",
            "[ˈflaʊə stɔːl]",
            "[haʊs swɒp]",
            "[ˈhaʊshəʊld]",
            "[pəˈzeʃnz]",
            "[haʊs ˈsɪtɪŋ]",
            "[ˈɪndəstrɪ]",
            "[ˈlaɪvlɪ]",
            "[ləʊˈkeɪʃn]",
            "[ˈmɑːkɪt pleɪs]",
            "[meɪz]",
            "[ˈmaɪkrəweɪv ʌvn]",
            "[ˈmɪrə]",
            "[ˈnærəʊ]",
            "[nævɪˈgeɪʃn]",
            "[ɒn ˈbɪznɪs]",
            "[ɒn ˈhɒlɪdɪ]",
            "[ɒn ˈpʌblɪk ˈtrænspɔːt]",
            "[ɒn ðiː ˈɪntənɛt]",
            "[ɒn ðiː weɪ]",
            "[ɒn taɪm]",
            "[ɒn tiːˈviː]",
            "[pæn]",
            "[ˈpɑːkɪŋ speɪs]",
            "[ˈpeɪvmənt ˈɑːtɪst]",
            "[pɪˈdestrɪən ˈe(ə)rɪə]",
            "[pleɪ ˈe(ə)rɪə]",
            "[ˈpʌblɪʃə]",
            "[reɪʤ]",
            "[riːˈsaɪkl]",
            "[ˈrʌbɪʃ]",
            "[rʌg]",
            "[ˈkɑːpɪt]",
            "[seɪf]",
            "[ˈsæt(ɪ)laɪt dɪʃ]",
            "[ʃiːt]",
            "[ʃɒp dɪsˈpleɪ]",
            "[ˈpɪləʊ]",
            "[ˈʃɒpə]",
            "[səˈluːʃn]",
            "[ˈsuːvənɪə ˈselə]",
            "[stɔːl]",
            "[streɪnʤ]",
            "[ˈstæʧuː]",
            "[striːt ˈkliːnə]",
            "[striːt pəˈfɔːmə]",
            "[ˈstjuːdənt əkɒməˈdeɪʃn]",
            "[tæp]",
            "[ˈtaʊəl]",
            "[ˈtaʊə blɒk]",
            "[trʌk]",
            "[ˈwɔːdrəʊb]",
            "[wɒʃ beɪsn]",
            "[ˈhæpən]",
            "[sɜːv]",
            "[ˈneɪbəhʊd]",
            "[ˈplentɪ]",
            "[teɪk pleɪs]",
            "[preɪ]",
            "[mɪs]",
            "[əˈpɔɪntmənt]",
            "[wɔːk əˈlɒŋ]",
            "[fiːl]",
            "[riːsnt]",
            "[ˈsɪtɪ kaʊnsl]",
            "[ˈɒnɪst]",
            "[ruːd]",
            "[bʌz]",
            "[kiːp ˈgəʊɪŋ]",
            "[streɪt]",
            "[ˈkrɒsrəʊd]",
            "[ˈskaɪskreɪpə]",
            "[əˈtrækʃn]",
            "[ˈbɪldɪŋ saɪt]",
            "[ˈprɒpətɪ]",
            "[dɪˈveləpə]",
            "[ɪksˈpænd]",
            "[ˈsʌdnlɪ]",
            "[dɪˈmɑːnd]",
            "[ˈɑːgjuː]",
            "[θruː]",
            "[weɪst ˈprɒdəkts]",
            "[pəˈluːʃn]",
            "[kənˈkluːʒən]",
            "[ɪmˈbærəst]",
            "[ˈɑːkɪtekt]",
            "[blɒk]",
            "[kændl]",
            "[ʧest ɒv drɔːz]",
            "[klɒθ]",
            "[kəˈlekt]",
            "[ˈkraʊdɪd]",
            "[dɪsˈtrɔɪ]",
            "[dʌl]",
            " [ˈdʌstpæn ænd brʌʃ]"

        )
        for (index in firstArray.indices) {
            defaultAdd(
                "Unit 2",
                firstArray.get(index),
                thirdArray.get(index),
                false,
                secondArray.get(index)
            )
        }
    }
    fun defaultModuleUnitThree() {
        var firstArray =
            mutableListOf(
                "achievement", "angrily", "angry", "anxious", "avoid","backwards","balloon","behavior","bother","calmly",
                "capsule","climb","confused", "cook", "dive", "drop", "easily", "eater", "embarrassed", "excited",
                "exhausted","eye contact", "fall", "fluently", "forwards", "frightened","guilty","headphones","in a good mood", "intercom",
                "into","jump", "land","lift","loudly","nervous","nicely","nightmare","out of","over","parachute",
"pleased","politely", "pull out","quickly","reader","rescue team","round and round","runner","skydiving", "smartly",
                "snowboarding","stranger","straw","stressed","take off","through","towards","traffic lights","traveller","typist",
"walker", "lonely","stuck","break","empty","hit","crash","explore"
            )
        var secondArray = mutableListOf(
"достижение", "сердито", "сердитый", "тревожный", "избегать","назад","воздушный шар", "поведение","беспокоить","спокойно",
            "капсула", "взбираться", "озадаченный", "повар", "нырять", "ронять", "легко", "едок", "смущенный", "в восторге",
            "изнуренный", "зрительный контакт", "падать", "бегло","вперед", "испуганный", "виновный", "наушники","в хорошем настроении","внутренняя связь",
      "внутрь","прыгать","приземляться","поднимать","громко","нервный","приятно","ночной кошмар","из","над","спускаться на парашюте",
"довольный","вежливо","вытаскивать","быстро","читатель","спасательная команда","кругом","бегун","прыжок с парашютом","элегантно",
       "катание на сноуборде","незнакомец","соломинка","напряженный","взлетать","через","по направлению к","светофор","путешественник","машинистка",
"ходок","одинокий","застрять","перерыв","пустой", "ударить","авария","исследовать"
            )
        var thirdArray = mutableListOf(
"[əˈʧiːvmənt]","[ˈæŋgrɪlɪ]", "[ˈæŋgrɪ]", "[ˈæŋkʃəs]", "[əˈvɔɪd]","[ˈbækwədz]", "[bəˈluːn]", "[bɪˈheɪvjə]", "[ˈbɒðə]","[ˈkɑː(r)mli]",
            "[ˈkæpsjuːl]","[klaɪm]", "[kənˈfjuːzd]", "[kʊk]", "[daɪv]", "[drɒp]", "[ˈiːzɪlɪ]", "[ˈiːtə]", "[ɪmˈbærəst]", "[ɪkˈsaɪtɪd]",
            "[ɪgˈzɔːstɪd]", "[aɪ ˈkɒntækt]", "[fɔːl]", "[ˈfluːəntlɪ]","[ˈfɔːwədz]", "[fraɪtnd]","[ˈgɪltɪ]","[ˈhedfəʊns]","[in ə gʊd muːd]","[ˈɪntəkɒm]",
       "[ˈɪntuː]", "[ʤʌmp]","[lænd]","[lɪft]","[ˈlaʊdlɪ]","[ˈnɜːvəs]","[ˈnaɪslɪ]","[ˈnaɪtmeə]","[aʊt ɒv]","[ˈəʊvə]","[ˈpærəʃuːt]",
"[pliːzd]","[pəˈlaɪtlɪ]","[pʊl aʊt]","[ˈkwɪklɪ]","[ˈriːdə]","[ˈreskjuː tiːm]","[raʊnd ænd raʊnd]","[ˈrʌnə]","[ˈskaɪdaɪvɪŋ]","[ˈsmɑːtlɪ]",
            "[ˈsnəʊbədɪŋ]","[ˈstreɪnʤə]","[strɔː]","[strest]","[teɪk ɒf]","[θruː]","[təˈwɔːdz]","[ˈtræfɪk laɪts]","[ˈtrævlə]","[ˈtaɪpɪst]",
"[ˈwɔːkə]","[ˈləʊnlɪ]","[stʌk]","[breɪk]","[ˈemptɪ]","[hɪt]","[kræʃ]","[ɪksˈplɔː]"
        )
        for (index in firstArray.indices) {
            defaultAdd(
                "Unit 3",
                firstArray.get(index),
                thirdArray.get(index),
                false,
                secondArray.get(index)
            )
        }
    }

    fun defaultModuleUnitFour() {
        var firstArray =
            mutableListOf(
"about thirty-five", "adult", "agree to do something","allow to do sth","ask to do something","avoid doing something","brain injury","can't stand doing",
"change career", "child", "choose to do something", "choose your career", "decide to do something","(don't) mind doing", "early twenties",
"elderly", "enjoy doing something", "eventually", "finish doing something", "forget to do something", "get a job","get married","give up doing something",
"go to university","have a baby", "hope to do something", "however","in your sixties","keep doing sth","late twenties","learn to do something",
"learn to swim","leave home","leave school","live with a partner","look forward to doing something","middle-aged","mid twenties","mind doing something",
"move house", "movement", "offer to do something","pain","pass your exams","plan to do something","practise doing something","repetitive strain injury",
"retire", "soccer","spend time abroad","start your own business","stop doing something","suffer","take up a hobby","teenager",
"toddler","try to do something","want to do something","would like to do something"
            )
        var secondArray = mutableListOf(
"около 35", "взрослый, совершеннолетний", "согласиться сделать что-то","разрешить что-то сделать","Попросить сделать что-либо","избегать делать что-то","травма головы","терпеть не могу",
"сменить род деятельности", "ребенок", "выбрать сделать что-либо", "выбрать свою карьеру", "решить сделать что-то", "не против делать что-то","21-23",
"пожилой", "наслаждаться чем-то", "в конечном итоге", "заканчивать делать что-то", "забыть сделать что-то", "устроиться на работу","жениться, выйти замуж","бросать что-то делать",
"поступать в университет", "иметь ребенка","надеяться что-то сделать","однако","в ваших 60","продолжать (делать что-либо)","27-29","учиться делать что-то",
"учиться плавать","покидать дом", "заканчивать школу","жить с партнером","с нетерпением ждать чего-то","средних лет","24-26", "возражать против того, чтобы делать что-то",
"переезжать","движение, подвижность","предлагать сделать что-то","боль","сдать экзамены","планировать сделать что-то","практиковаться делать что-то","травма от повторяющихся нагрузок",
"уходить на пенсию","футбол","проводить время заграницей","открыть свое дело","перестань что-то делать","страдать","начать заниматься хобби","подросток",
"малыш", "стараться что-то делать","хотеть что-то сделать","хотел бы что-то сделать"

        )
        var thirdArray = mutableListOf(
"","[adult]","","[əˈlaʊ]","","","[breɪn ˈɪnʤərɪ]","",
" [ʧeɪnʤ kəˈrɪə]", "", "[ʧuːz]", "[ʧuːz] [kəˈrɪə]","","","[ˈtwentɪz]",
            "[ˈeldəlɪ]","", "[ɪˈvenʧʊ(ə)lɪ]", "","","","[ˈmærɪd]","",
"","","","[haʊˈevə]","[ˈsɪkstɪz]","","[ˈtwentɪz]","",
"","","","","[ˈfɔːwəd]","[eɪʤd]","[ˈtwentɪz]","",
"", "[ˈmuːvmənt]","[ˈɒfə]","","[ɪgˈzæm]","[plæn]","[ˈpræktɪs]","[rɪˈpetɪtɪv streɪn ˈɪnʤərɪ]",
"[rɪˈtaɪə]", "[ˈsɒkə]","[əˈbrɔːd]","[əʊn ˈbɪznɪs]","","[ˈsʌfə]","","",
"[ˈtɒdlə]","","",""

        )
        for (index in firstArray.indices) {
            defaultAdd(
                "Unit 4",
                firstArray.get(index),
                thirdArray.get(index),
                false,
                secondArray.get(index)
            )
        }
    }
    fun defaultModuleUnitFive() {
        var firstArray =
            mutableListOf(
               "advice", "afford", "antique", "balance", "bank account","benefit","bill","borrow", "brand new","bright", "buyer",
"change","coin","comfort","comfortable","compute","condition","crime","dark","debt","decide",
"developing world","digit","digital","disappointment","economy","enjoyable","equip","essential", "fashionable","fee","heavy",
                "leather","lend","minimalist","mosquito","note","owe","pale","pay back","possess","purse","refund","rent","repellent",
                "revolution","save up","scooter","shiny","statistic","stress","stressful","suitable","thin", "time capsule","transfer",
"useful","valuable","wallet","ordinary","value","weight","shape","contain","suggest","suggestion","deal with","decrease","robbery","follow","significance","enough",
"disaster","stuff","security","drawer","realize","get rid of","completely","explain","wealthy","notice"
            )
        var secondArray = mutableListOf(
"совет", "позволить себе", "античный", "баланс, уравновешивать","банковский счет","выгода","счет","брать в займы, одалживать","совершенно новый","яркий","покупатель",
"сдача","монета","утешать","удобный","вычислять","условие; состояние; обусловливать, определять","преступление","темный","долг (в значении \"денежный долг\")","решать",
"развивающиеся страны","цифра","цифровой, дискретный, числовой, численный, пальцевой","разочарование","экономика","приятный","снаряжать","необходимый","модный","плата","тяжелый",
"кожа", "давать в долг","минималист","комар","банкнота","быть должным","бледный","вернуть деньги","обладать","кошелек","возврат денег (за покупку)","аренда","средство, отпугивающее насекомых",
"революция","копить","скутер","блестящий","статистика","делать ударение на чем-либо","напряженный","подходящий","тонкий","капсула времени","передавать, переводить (напр. деньги)",
"полезный","ценный","бумажник","обычный","ценность","вес","форма","содержать (в себе)","предлагать","предложение","иметь дело с","уменьшение","ограбление","следовать","значимость","достаточно",
"катастрофа","хлам (барахло)","безопасность","выдвижной ящик","осознавать","избавиться от","полностью","объяснять","богатый, состоятельный","замечать, уведомление"
        )
        var thirdArray = mutableListOf(
"[ədˈvaɪs]", "[əˈfɔːd]", "[ænˈtiːk]","[ˈbæləns]", "[bæŋk əˈkaʊnt]","[ˈbenɪfɪt]", "[bɪl]", "[ˈbɒrəʊ]","[brænd njuː]","[braɪt]","[ˈbaɪə]",
"[ʧeɪnʤ]","[kɔɪn]","[ˈkʌmfət]","[ˈkʌmf(ə)təb(ə)l]","[kəmˈpjuːt]","[kənˈdɪʃn]","[kraɪm]","[dɑːk]","[det]","[dɪˈsaɪd]",
"[dɪˈveləpɪŋ wɜːld]", "[ˈdɪʤɪt]","[ˈdɪʤɪtl]","[dɪsəˈpɔɪntmənt]","[ɪˈkɒnəmɪ]","[ɪnˈʤɔɪəbl]","[ɪˈkwɪp]","[ɪˈsenʃəl]","[ˈfæʃnəbl]","[fiː]","[ˈhevɪ]",
"[ˈleðə]","[lend]","[ˈmɪnɪməlɪst]","[məsˈkiːtəʊ]","[nəʊt]","[əʊ]","[peɪl]","[peɪ bæk]","[pəˈzes]","[pɜːs]","[ˈriːfʌnd]","[rent]","[rɪˈpelənt]",
"[revəˈluːʃn]","[seɪv ʌp]","[ˈskuːtə]","[ˈʃaɪnɪ]","[stəˈtɪstɪk]","[stres]","[ˈstresfəl]","[ˈs(j)uːtəb(ə)l]","[θɪn]","[taɪm ˈkæpsjuːl]","[ˈtrænsfɜː]",
"[ˈjuːsf(ə)l]","[ˈvæljʊ(ə)b(ə)l]","[ˈwɒlɪt]","[ˈɔːdnrɪ]","[ˈvæljuː]","[weɪt]","[ʃeɪp]","[kənˈteɪn]","[səˈʤest]","[səˈʤesʧən]","[diːl wɪð]","[ˈdiːkriːs]","[ˈrɒbərɪ]","[ˈfɒləʊ]","[sɪgˈnɪfɪkəns]","[ɪˈnʌf]",
"[dɪˈzɑːstə]","[stʌf]","[sɪˈkjʊərɪtɪ]","[ˈdrɔːə]","[ˈrɪəlaɪz]","","[kəmˈpliːtlɪ]","[ɪksˈpleɪn]","[ˈwelθɪ]","[ˈnəʊtɪs]"
        )
        for (index in firstArray.indices) {
            defaultAdd(
                "Unit 5",
                firstArray.get(index),
                thirdArray.get(index),
                false,
                secondArray.get(index)
            )
        }
    }

    fun harryPotterOneChapterOne() {
        var firstArray =
            mutableListOf(
                "pretend", "shudder", "hum", "pick out", "gossip", "peck", "peculiar", "tabby", "tawny", "stare at", "drive (someone) (or an animal) out", "whisper", "emerald", "struck",
                "stunt", "swooping", "gaze", "yell", "bun", "clutch", "doughnut", "flood", "dash", "snap", "seize", "stroke", "grunt", "stumble", "realize", "on the contrary", "wide",
                "squeaky", "passer-by", "stand-stood-stood", "rattle", "set off (for something)", "stern", "determine", "mention", "allow", "grin", "oddly", "throat", "mumble", "snap",
                "folk", "sharply", "sip", "purse", "stiffly", "sink", "creep-crept-crept", "peer", "fall asleep", "involve", "yawn", "quiver", "slam", "nor",
                "pop out (of something) (of the ground)", "twitch", "tuck",  "belt", "sweep-swept-swept", "high-heel", "rummage", "sight", "amuse", "chuckle", "mutter", "know-knew-known",
                "flick", "hold-held-held", "pop", "pinprick", "beady", "slip", "rather", "severe", "draw-drew-drawn", "was drawn into", "tight", "bun", "distinctly", "ruffled", "brick")
        var secondArray = mutableListOf(
            "притворяться", "содрогаться", "напевать", "выбирать", "сплетня/сплетничать", "клевать", "своеобразный", "полосатый", "коричневатый/рыжеватый", "уставиться на", "прогнать",
            "шепот/шептать", "изумруд/изумрудный", "пораженный", "трюк", "парящий", "пристальный взгляд/уставиться", "кричать", "булочка", "сжимать", "пончик", "затопить", "мчаться",
            "рявкнуть/наорать", "схватить", "поглаживать", "хрюкнул/хмыкнул", "спотыкаться", "осознавать", "как раз наоборот", "широкий", "писклявый", "прохожий", "стоять", "грохот/стук",
            "отправляться", "суровый", "определенно/полон решимости", "упоминать","разрешил/позволил", "ухмыляться", "странно", "горло", "промямлил", "огрызнутсья", "народ", "резко",
            "прихлебывать", "поджать", "чопорно/натянуто", "погружать/тонуть", "подкрался", "посмотреть", "заснуть", "вовлекать", "зевать", "дрожать", "хлопать", "ни",
            "Выпрыгнуть из чего-то, вырваться из чего-то", "подергивание", "засовывать", "пояс", "подметать", "на высоком каблуке", "рыться", "взгляд", "забавлять", "хмыкать",
            "бормотать", "знать", "щелкать", "держать", "хлопок", "булавочный укол", "бусинки", "сунуть", "вернее", "суровый", "рисовать", "был втянут в", "тугой", "пучок", "отчетливо",
            "взъерошенный",  "кирпич")
        var thirdArray = mutableListOf(
            "[prɪˈtend]", "[ˈʃʌdə]", "[hʌm]", "", "[ˈgɒsɪp]", "[pek]", "[pɪˈkjuːlɪə]", "[ˈtæbɪ]", "[ˈtɔːnɪ]", "[steə]", "", "[ˈwɪspə]", "[ˈemərəld]", "[strʌk]", "[stʌnt]", "[swuːp]",
            "[geɪz]", "[jel]", "[bʌn]", "", "[ˈdəʊnʌt]", "[flʌd]", "[dæʃ]", "[snæp]", "[siːz]", "[strəʊk]", "[grʌnt]", "[stʌmbl]", "[ˈrɪəlaɪz]", "[ˈkɒntrərɪ]", "[waɪd]", "[ˈskwiːkɪ]",
            "[pɑːsəˈbaɪ]", "", "[rætl] He was rattled", "[set ɔf]", "[stɜːn]", "[dɪˈtɜːmɪn]", "[menʃn]", "[əˈlaʊ]", "[grɪn]", "[ˈɒdlɪ]", "[θrəʊt]", "[mʌmbl]", "[snæp]", "[fəʊk]",
            "[ˈʃɑːplɪ]", "[sɪp]", "[pɜːs]", "[ˈstɪflɪ]", "[sɪŋk]", "[krept]", "[pɪə]", "[fɔːl əˈsliːp]", "[ɪnˈvɒlv]", "[jɔːn]", "[ˈkwɪvə]", "[slæm]", "[nɔː]", "", "[twɪʧ]", "[tʌk]",
            "[belt]", "[swiːp]-[swept]", "[haɪˈhiːl]", "[ˈrʌmɪʤ]", "[saɪt]", "[əˈmjuːz]", "[ʧʌkl]", "[ˈmʌtə]", "[nəʊ — njuː — nəʊn]", "[flɪk]", "", "[pɒp]", "[ˈpɪnprɪk]",
            "[ˈbiːdɪ] beady-eyed", "[slɪp]", "[ˈrɑːðə]",  "[sɪˈvɪə]", "[drɔː - druː - drɔːn]", "", "[taɪt]", "[bʌn]", "[dɪsˈtɪŋktlɪ]", "[rʌfld]", "[brɪk]")
        for (index in firstArray.indices) {
            defaultAdd(
                "Harry Potter One Chapter One",
                firstArray.get(index),
                thirdArray.get(index),
                false,
                secondArray.get(index)
            )
        }
    }

    fun defaultAdd(
        unit: String,
        name: String,
        transcription: String,
        isTask: Boolean,
        translation: String
    ) {
        viewModelScope.launch {
            val task = Task()
            task.taskUnit = unit
            task.taskName = name
            task.taskTranscription = transcription
            task.taskDone = isTask
            task.taskTranslation = translation
            dao.insert(task)
        }
    }

}
