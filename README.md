# Проект по автоматизации API методов для сайта [DemoQA](https://demoqa.com)
<p align="center"><a href="https://demoqa.com"><img src="images/logo/Toolsqa.jpg" align="center" width="400" height="100"  alt="Java"/></a></p>  

> DemoQA — демонстрационный сайт для инженеров по контролю качества, созданный компанией Tools QA.
>
> Состоит из веб-сайта с учебными формами и примера книжного магазина с открытым API.

## :notebook: Содержание:

- [Стек технологий](#computer-стек-технологий)
- [Тестовые сценарии](#clipboard-тестовые-сценарии)
- [Сборка в Jenkins](#-сборка-в-jenkins)
- [Команда для запуска](#rocket-команда-для-запуска)
- [Allure отчет](#-allure-отчет)
- [Интеграция с Allure TestOps](#-интеграция-с-allure-testops)
- [Интеграция с Jira](#-интеграция-с-jira)
- [Уведомления в Telegram чат с ботом](#-уведомления-в-telegram-чат-с-ботом)

---

## :computer: Стек технологий
<p align="center">
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"/></a>
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Intelij_IDEA.svg" width="50" height="50"/></a>
<a href="https://www.github.com/"><img src="images/logo/Github.svg" width="50" height="50"/></a>
<a href="https://rest-assured.io/"><img src="images/logo/Rest-assured.svg" width="50" height="50"/></a>
<a href="https://www.gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"/></a>
<a href="https://www.junit.org/junit5/"><img src="images/logo/JUnit5.svg" width="50" height="50"/></a>
<a href="https://www.selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50"/></a>
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images/logo/Allure.svg" width="50" height="50"/></a>
<a href="https://www.qameta.io/"><img src="images/logo/AllureTestOps.svg" width="50" height="50"/></a>
<a href="https://www.atlassian.com/software/jira"><img src="images/logo/Jira.svg" width="50" height="50"/></a>
<a href="https://www.telegram.org/"><img src="images/logo/Telegram.svg" width="50" height="50"/></a>
</p>

---

## :clipboard: Тестовые сценарии

- :white_check_mark: Аккаунт
    - :heavy_check_mark: Проверка удаления аккаунта
- :white_check_mark: Авторизация/Регистрация
    - :heavy_check_mark: Проверка полей после авторизации
    - :heavy_check_mark: Проверка полей после успешной регистрации
    - :heavy_check_mark: Проверка сообщения после регистрации под существующим пользователем
- :white_check_mark: Книжный магазин
    - :heavy_check_mark: Проверка удаления книги из списка
    - :heavy_check_mark: Проверка полей на странице книги

---

## <img src="images/logo/Jenkins.svg" width="50" height="50"/> Сборка в [Jenkins](https://jenkins.autotests.cloud/job/demoqa_api_kpoludnitsyn/)

<p align="center">
<img src="images/screenshots/JenkinsPage.jpg" alt="Jenkins Page" width="1000" height="350">
</p>

### Параметры сборки проекта

| Параметр        | Назначение                               |
|-----------------|------------------------------------------|
| TASK            | Опция выбора запуска определённых тестов |

### Запуск тестов с параметрами в **Jenkins**

<p align="center">
<img src="images/screenshots/JenkinsLaunch.jpg" alt="Jenkins Launch" width="500" height="400">
</p>

---

## :rocket: Команда для запуска

```bash
clean
${TASK}
```

### Варианты запуска тестов

Для запуска можно выбрать один из четырёх тест-сьютов:

```mermaid
flowchart LR
    A[Test Suite] --> B[Все тесты] --> C[test]
    A --> D[Аккаунт] --> E[account_test]
    A --> F[Авторизация/Регистрация'] --> G[authorize_test]
    A --> H[Книжный магазин] --> I[bookstore_test]
```

---

## <img src="images/logo/Allure.svg" width="50" height="50"/> [Allure](https://jenkins.autotests.cloud/job/demoqa_api_kpoludnitsyn/allure/) отчет

### Главная страница отчета

<p align="center">
<img src="images/screenshots/AllureReport.jpg" alt="Allure report" width="1000" height="300">
</p>

### Тест-кейсы

<p align="center">
<img src="images/screenshots/AllureTestCases.jpg" alt="Test Case" width="1000" height="400">
</p>

#### Содержание тест-кейсов

- :heavy_check_mark: Подробное описание шагов
- :heavy_check_mark: Тег
- :heavy_check_mark: Эпик
- :heavy_check_mark: Критичность теста
- :heavy_check_mark: Автор
- :heavy_check_mark: Отдельный запрос
- :heavy_check_mark: Статус код ответа

### Графики

<p align="center">
<img src="images/screenshots/AllureGraph.jpg" alt="Allure-graph" width="1000" height="400">
</p>

---

## <img src="images/logo/AllureTestOps.svg" width="50" height="50"/> Интеграция с [Allure TestOps](https://allure.autotests.cloud/project/3754/dashboards)

### Dashboard

<p align="center">
<img src="images/screenshots/TestOps_dashboard.jpg" alt="TestOps dashboard" width="1000" height="400">
</p>

### Автоматизированные тест-кейсы

<p align="center">
<img src="images/screenshots/TestOps_testCases.jpg" alt="TestOps test cases" width="1000" height="400">
</p>

### Запуск сборки из **Allure TestOps**

<p align="center">
<img src="images/screenshots/TestOps_launches.jpg" alt="TestOps launches">
</p>

---

## <img src="images/logo/Jira.svg" width="50" height="50"/> Интеграция с [Jira](https://jira.autotests.cloud/browse/HOMEWORK-932)

### Задача в Jira

<p align="center">
<img src="images/screenshots/Jira.jpg" alt="Jira" width="1000" height="400">
</p>

#### Содержание задачи

- :heavy_check_mark: Тест-кейсы из Allure TestOps
- :heavy_check_mark: Результат прогона тестов в Allure TestOps

---

## <img src="images/logo/Telegram.svg" width="50" height="50"/> Уведомления в Telegram чат с ботом

### Уведомление через чат бот

<p align="center">
<img src="images/screenshots/Telegram.jpg" alt="Telegram" width="500" height="400">
</p>


#### Содержание уведомления в Telegram

- :heavy_check_mark: Окружение
- :heavy_check_mark: Комментарий
- :heavy_check_mark: Длительность прохождения тестов
- :heavy_check_mark: Общее количество сценариев
- :heavy_check_mark: Процент прохождения тестов
- :heavy_check_mark: Ссылка на Allure отчет