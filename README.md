# QA Java Project

-----

## Оглавление
1. [Окружение](#окружение)
2. [Что использовалось в проекте](#что-использовалось-в-проекте)
3. [Тестируемые ресурсы](#тестируемые-ресурсы)
4. [Запуск тестов](#запуск-тестов)
5. [Тестирование локальной базы данных](#тестирование-локальной-базы-данных)
6. [Построение отчета](#построение-отчета)
7. [Время запуска всех тестов](#время-запуска-всех-тестов)

## Окружение

* Java 13
* PostgreSQL 15

## Что использовалось в проекте

Для написания тестов и их запуска используется [TestNG](https://testng.org/doc/)

Для тестирования API используется [RestAssured](https://rest-assured.io)

Для тестирования Web используется [Selenide](https://ru.selenide.org). Тесты запускаются в Google Chrome.

Для отправления запросов к базе данных используется
[JDBC](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/) вместе с 
[PostgreSQL](https://www.postgresql.org)

Для построения отчетов по запущенным тестам используется
[Allure Framework](https://docs.qameta.io/allure/)

Для генерации тестовых данных (имена, фамилии, работа, email и т. д.) использовался класс
[Faker](https://javadoc.io/doc/com.github.javafaker/javafaker/latest/com/github/javafaker/Faker.html)

## Тестируемые ресурсы

[Ссылка на API](https://reqres.in)

[Ссылка на веб-сайт](https://opensource-demo.orangehrmlive.com/web/index.php/auth/login)

[Ссылка на описание базы данных](https://www.postgresqltutorial.com/postgresql-getting-started/postgresql-sample-database/)

[Ссылка на скачивание базы данных](https://www.postgresqltutorial.com/wp-content/uploads/2019/05/dvdrental.zip)

[Ссылка на ERD базы данных](https://www.postgresqltutorial.com/wp-content/uploads/2018/03/printable-postgresql-sample-database-diagram.pdf)

## Запуск тестов

### Тестовые сьюты

В проекте есть 4 тестовых сьюта:

* BackendSuite.xml. В нем представлены все тесты, которые относятся к
тестированию API.
* FrontendSuite.xml. В нем представлены все тесты, которые относятся к 
тестированию UI в Web.
* DataBaseSuite.xml. В нем представлены все тесты, которые относятся к
тестированию локально поднятой базы данных.
* TestNG.xml. В нем представлены все тесты проекта.

### Как запустить тесты

Варианты:

* Запустить через консоль, сделав mvn clean test. Прогонятся все тесты под default suite.
* Нажать правой кнопкой мыши на выбранный сьют в формате .xml в среде разработки и выбрать "Run".

## Тестирование локальной базы данных

* По приведенной выше ссылке загрузите базу данных dvdrental в 
PostgreSQL согласно инструкции [Load PostgreSQL Sample Database](https://www.postgresqltutorial.com/postgresql-getting-started/load-postgresql-sample-database/)
* В файле ./src/test/resources/database.properties укажите данные установленной локально
базы данных (локальный адрес базы данных, имя пользователя и пароль для доступа к базе)

## Построение отчета

Примечание: при запуске отдельных тестов в проекте или запуске тестовых сьютов Allure Framework
будет генерировать данные в .json формате о запущенных тестах по пути ./target/allure-results.
Если запускать подряд несколько раз одни и те же тест сьюты в итоговом отчете одни и те же тесты
будут повторяться по несколько раз. Чтобы сделать "чистый прогон" и получить "чистый отчет", перед
тем как запускать тесты необходимо сделать mvn clean, который очистит данные о предыдущих запусках.

1. Запустить какой-то из тестовых сьютов
2. mvn allure:report
3. Открыть в Firefox target/site/index.html
4. mvn clean для очистки данных собранных Allure Framework для текущих тестов

## Время запуска всех тестов
Запуск всех тестов занимает около 3 минут на локальной машине.