Тестовое задание:

1. Необходимо написать парсер строк в десятичное числовое представление с учетом следующих требований
* Символы разделителей тысяч: "," "." пробел 
* Символы разделителей дробной части: "," "."
* В случае когда не понятно, это разделитель тысяч или дроби, принимаем решение, что тысяч 
* Число может быть отрицательное, в этом случае число берётся в скобки или используется знак минус (минус может быть как слева так и справа от числа, так иногда пишут финансисты; между числом и минусом может быть 1 пробел) 
* Рядом с числом может быть символ валюты €, $ (достаточно для теста ) слева или справа; между числом и символом валюты может быть 1 пробел; символ валюты и минус могут быть слева или справа друг от друга, между ними может быть 1 пробел; символ валюты может быть внутри или снаружи скобок, означающих минус.

2. Необходимо добавить возможнось передавать кастомную логику для парсинга
3. Сервис распространяется как библиотека в jar файле
