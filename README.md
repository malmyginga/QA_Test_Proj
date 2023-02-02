# QA Java Project

-----

## Оглавление
1. [Что использовалось в проекте](#что-использовалось-в-проекте)
2. [Тестируемые ресурсы](#тестируемые-ресурсы)
3. [Запуск тестов](#запуск-тестов)
4. [Построение отчета](#построение-отчета)


## Что использовалось в проекте

Для написания тестов и их запуска используется [TestNG](https://testng.org/doc/)

Для тестирования API используется [RestAssured](https://rest-assured.io)

Для тестирования Web используется [Selenide](https://ru.selenide.org)

Для отправления запросов к базе данных используется
[JDBC](https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/) вместе с 
[PostgreSQL](https://www.postgresql.org)

Для построения отчетов по запущенным тестам используется
[Allure Framework](https://docs.qameta.io/allure/)

## Тестируемые ресурсы

[Ссылка на API](https://reqres.in)

[Ссылка на веб сайт](https://opensource-demo.orangehrmlive.com/web/index.php/auth/login)

[Ссылка на базу данных](https://www.postgresqltutorial.com/postgresql-getting-started/postgresql-sample-database/)

[Ссылка на ERD базы данных](https://www.postgresqltutorial.com/wp-content/uploads/2018/03/printable-postgresql-sample-database-diagram.pdf)

## Запуск тестов

В проекте есть 4 тестовых сьюта:

* BackendSuite.xml. В нем представлены все тесты, которые относятся к
тестированию API.
* DataBaseSuite.xml. В нем представлены все тесты, которые относятся к 
тестированию UI в Web.
* FrontendSuite.xml. В нем представлены все тесты, которые относятся к
тестированию локально поднятой базы данных.
* TestNG.xml. В нем представлены все тесты проекта.

## Построение отчета

1. Запустить какой-то из тестовых сьютов
2. mvn allure:report
3. Открыть в Firefox target/site/index.html
4. mvn clean для очистки данных собранных Allure для текущих тестов