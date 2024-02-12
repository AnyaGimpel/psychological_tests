package com.example.myquiz

class Questions {
    val MakingDecisions = listOf(
        Question("Вопрос #1: Что, по Вашему, движет человеком в жизни прежде всего?", listOf("любопытство", "желания", "необходимость")),
        Question("Вопрос #2: Как Вы думаете, почему люди переходят с одной работы на другую?", listOf("их увольняют", "уходят из-за большей зарплаты", "другая работа им больше по душе")),
        Question("Вопрос #3: Когда у Вас происходят неприятности:", listOf("Вы откладываете их решение до последнего", "у Вас есть потребность проанализировать, насколько виноваты Вы сами", "Вы не хотите даже и думать о том, что случилось")),
        Question("Вопрос #4: Вы не успели вовремя сделать какую-то работу и:", listOf("с боязнью ждете, когда Вас спросят о результатах", "основательно подготавливаетесь к объяснению","заявляете о своей неудаче еще до того, как это станет известно")),
        Question("Вопрос #5: Когда Вы достигаете какой-то поставленной цели, то встречаете известие об этом:", listOf( "по-разному в зависимости от цели, но не так бурно", "с бурными положительными эмоциями", "с чувством облегчения")),
        Question("Вопрос #6: Что бы Вы рекомендовали очень стеснительному человеку:", listOf("избегать ситуаций, требующих риска", "познакомиться с людьми другого склада, не страдающими застенчивостью", "избавиться от этого, обратившись к помощи психолога")),
        Question("Вопрос #7: Как Вы поступите в конфликтной ситуации:", listOf("напишу ему письмо", "поговорю с тем, с кем вступил в конфликт", "попробую разрешить конфликт через посредника")),
        Question("Вопрос #8: Какого рода страх возникает у Вас, когда Вы ошибаетесь:", listOf("боязнь наказания", "страх того, что ошибка может изменить тот порядок, к которому вы привыкли", "боязнь потерять престиж")),
        Question("Вопрос #9: Когда Вы с кем-то разговариваете, то:", listOf("время от времени отводите взгляд", "смотрите прямо в глаза собеседнику", "отводите взгляд, даже когда к Вам обращаются")),
        Question("Вопрос #10: Когда Вы ведете важный разговор, то: ", listOf("Вы повторяетесь, волнуетесь, голос начинает Вас подводить", "Вы то и дело вставляете ничего не значащие слова", "тон разговора обычно остается спокойным")
        ))
    val listOfLists = listOf(
        MakingDecisions
    )

    val dataTest = listOf(
        Test("Тест \"Принятие решений\"","Оценкой правильности принятого руководителем решения являются не только хозяйственные показатели, но и поведение работников при достижении ими производственных целей, мера их активности, инициативы, коллективизма\n" +
                "Данный тест поможет оценить, насколько Вы решительный человек и какого типа должны быть люди в Вашей команде.", MakingDecisions)
    )
}

data class Question(val question: String, val answers: List<String>) // Класс для представления вопроса
data class Test(val name: String, val description: String, val question: List<Question>)
