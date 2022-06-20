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
