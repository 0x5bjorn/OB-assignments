# OB-assignments
Optima bank assignments

Versions: Java(11), JDK(11), Spring(2.7.3), Maven(3.8.1)

## РУС

### Пункты которые были пропущены и не реализованы:
- Логирование
- Обработка exception-ов

### ПРИМЕЧАНИЕ (задание 1):
Создание и запуск контейнера RabbitMQ: ``` docker-compose up -d ```

Отключение и удаление контейнера RabbitMQ: ``` docker-compose down ``` 

Чтобы заменить базу данных на коллекцию объектов(и наоборот), в проекте dml в файле ``` application.properties ``` ([ссылка](https://github.com/umarbaev-S/OB-assignments/blob/main/interproc-communication/interproc-communication-dml/src/main/resources/application.properties)) перепишите значение ``` ipc.dml.storage ``` на ``` coll/db ``` соответственно

## ENG

### Points that were skipped:
- Logging
- Exception handling

### NOTE (assignment 1):
RabbitMQ container creation and launching: ``` docker-compose up -d ```

RabbitMQ container stopping and deletion: ``` docker-compose down ``` 
