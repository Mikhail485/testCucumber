Feature: Поиск ЯндексМаркет
  @phone
  Scenario: поиск телефонов в ЯндексМаркете
    Given перейти на сайт 'https://yandex.ru/'
    Then перейти в сервис 'Маркет'
    Then выбрать раздел в каталоге 'Электроника'
    Then выбрать раздел 'Смартфоны'
    Then выбрать производителя 'Apple'
    Then изменить результат поиска на 12 элементов и проверить изменения
    Then проверить что в выборку попали только 'iPhone'
    Then закончить работу
