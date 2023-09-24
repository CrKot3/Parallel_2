# Лабораторная работа №2. Формулы Ньютона-Котса.
## Постановка задачи
Метод приближенного счёта определенного интеграла формулами Ньютона-Котса предполагает разбиение отрезка интегрирования на много равных мелких отрезков, аппроксимации подинтегральной функции на выбранных промежутках многочленами и нахождении суммарной площади полученных криволинейных трапеций. Есть четыре способа аппроксимации функции. Первые три - построение прямоугольников по ЛЕВОЙ, ПРАВОЙ границе или СРЕДНЕМУ значению интервала разбиения. Еще один способ - счёт площади трапеций с левой и правой границами интервалов.

![image](https://github.com/CrKot3/Parallel_2/assets/103645968/164f2f79-e811-42d4-891a-5579e0448f76)

## Описание алгоритма

## Пример работы
Пример работы функции с полиномом x^3 на отрезке [0,10] с 95955959 отрезками разбиения и разными методами счёта:
![image](https://github.com/CrKot3/Parallel_2/assets/103645968/697eea0f-dd81-4120-acfc-f2b15e7be85d)

## Тесты
Для тестирования был использован Junit и графический калькулятор Desmos. Написаны 4 группы тестов. Первая проверяет простые интегралы некоторых функций. Вторая группа проверяет, что все методы работают одинкого близко к эталонному результату. Третья группа проверяет аксиому об аддитивности определенного интеграла. Четвёртая проверяет интересные интегралы вроде интегралов с совпадающими границами и интегралов с перевенутыми границами. Все тесты прошли с погрешностью delta = 0.00001

![image](https://github.com/CrKot3/Parallel_2/assets/103645968/0e99d868-3d63-41e3-a461-b6372f3b0442)

## Сравнение с непараллельным алгоритмом
Для сравнения был создан класс NonParallelIntegral, который считает то же самое, но последовательно. В Main есть функция timeCompare, которая сравнивает время выполнения на параллельном и непараллельном методах. Для 8 логических процессоров были проведены 4 теста: с 500, 50000, 5000000 и 500000000 отрезками разбиения. В первых двух выиграл непаралельный метод, а в двух других - параллельный. Так как задача предполагает стремление числа отрезков разбиения к бесконечности, параллельный метод можно назвать победителем.

![image](https://github.com/CrKot3/Parallel_2/assets/103645968/9797e205-b9df-44d9-968a-52b364d220a8)
