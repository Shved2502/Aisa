# Aisa

Test task for Aisa

Эндпоинты протетстировать можно с помощью Swagger UI по адресу 

`localhost:8080/swagger-ui/index.html#/`
  
Для получения списка загруженных в память рецептов кофе использовать
  
`GET api/v1/recipes`

Для добавления нового рецепта в память в формате : 

```
* String name - название кофе
* Integer coffeeConsumption - требуемое количество кофе в граммах
* Integer milkConsumption - требуемое количество молока в миллилитрах
* Integer waterConsumption - требуемое количество воды в миллилитрах
* Integer purityConsumption - потребляемое количество "единиц чистоты"
* Integer timeConsumption - время на приготовление в секундах
```

Использовать эндпоинт

`POST api/v1/recipes/register`

Для приготовления кофе, использовать эндпоинт

`POST api/v1/make/{coffee}`
где coffee - название кофе из списка, полученного с помощью первого описанного эндпоинта

Результатом будет статус кофемашины
Получить статус можно и во время приготовления кофе с помощью эндпоинта 

`GET api/v1/status`

Примеры статусов :
* MAKING LATTE - латте готовиться
* LATTEE IS READY - латте готов
* CLEANING - кофемашина запустила процесс самоочистки
* WAITING TO FILL WATER CONTAINER - недостаточно ингридиентов в одном из контейнеров с расходниками, необходимо вручную его наполнить заново, после чего воспользоваться эндпоинтом update для обновления ресурса ингридиента
* READY - статус после окончания очистки или обновления контейнера

Эндпоинт для обновления ресурса ингридиента 

`PUT api/v1/update/{container}`
где container - название контейнера (Coffee / Water / Milk)

## Запуск

Запуск возможен из командной строки 

`java -jar Aisa-0.0.1-SNAPSHOT.jar`
 либо из среды разработки

Для запуска необходимо иметь PgAdmin поскольку с Docker я еще не работал и не смог настроить

## Логика

* liquibase 
> создает схему и таблицы
>  заполняет для старта таблицу с рецептами
* Для эмуляции работы кофемашины бал создан класс CoffeeMachine
> метод processing эмулирует выполнение оперций
> хранятся данные о максимальной вместимости емкостей
> хранятся данные о реальной вместимости емкостей
*
