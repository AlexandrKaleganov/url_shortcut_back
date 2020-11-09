[![Build Status](https://travis-ci.org/AlexandrKaleganov/url_shortcut_back.svg?branch=master)](https://travis-ci.org/AlexandrKaleganov/url_shortcut_back)
[![codecov](https://codecov.io/gh/AlexandrKaleganov/url_shortcut_back/branch/master/graph/badge.svg?token=L1PSKISZXZ)](https://codecov.io/gh/AlexandrKaleganov/url_shortcut_back)
# url_shortcut
Стек технологий:
java 8,
SpringBoot, авторизация через  jwt, СУБД MySQL/PostgreSQL  - в зависимости от выбранного вами файла, liquibase,
добавлено логирование с использованием Aspect, написаны тесты junit/mockito, Angular

Для входа в приложение можно использовать **admin/admin или user/user а также зарегистрироваться указав домен**

#deploy: 
1. Создайте локально базу url_shortcut
2. В application-postgres.yml  укажите необходимый логин/пароль от бд
3. В application-postgres.yml  укажите порт на котором будет запущено приложение , на данный момент  8080
4. mvn clean install

Приложение состоит из двух частей
Backend
https://github.com/AlexandrKaleganov/url_shortcut_back
Frontend
https://github.com/AlexandrKaleganov/url_shortcut_frontend

В данном приложении реализован следующий функционал: 
 1.Пользователь может зарегистрироваться, чтобы зарегистрироваться необходимо указать домен, например: 'google.com'
без http, www  и всего лишнего.
 2.После регистрации пользователь может добавлять длинные ссылки.
 3.В форму уже подставляется домен автоматически.
 4.На вкладке "ссылки" мы можем наблюдать список ссылок и коротких ссылок к ним.
 5.Список ссылок, Список пользователей, Статистика отображается с пагинацией.
 6.Пользователей можно  фильтровать по домену, по логину, по фамилии, Ссылки можно фильтровать по логину пользователя

Ниже идут скриншёты:


#Титульный лист
![Alt text](https://github.com/AlexandrKaleganov/url_shortcut_back/blob/master/src/main/resources/img/1.png?raw=true "Optional Title")

Регистрация:
![Alt text](https://github.com/AlexandrKaleganov/url_shortcut_back/blob/master/src/main/resources/img/reg1.png?raw=true "Optional Title")
![Alt text](https://github.com/AlexandrKaleganov/url_shortcut_back/blob/master/src/main/resources/img/reg2.png?raw=true "Optional Title")

Авторизация:
![Alt text](https://github.com/AlexandrKaleganov/url_shortcut_back/blob/master/src/main/resources/img/auth.png?raw=true "Optional Title")

Меню:
![Alt text](https://github.com/AlexandrKaleganov/url_shortcut_back/blob/master/src/main/resources/img/menu.png?raw=true "Optional Title")
Меню администратора
![Alt text](https://github.com/AlexandrKaleganov/url_shortcut_back/blob/master/src/main/resources/img/menu-user-admin.png?raw=true "Optional Title")
Редактирование пользователя
![Alt text](https://github.com/AlexandrKaleganov/url_shortcut_back/blob/master/src/main/resources/img/addUSer.png?raw=true "Optional Title")
Добавление пользователя
![Alt text](https://github.com/AlexandrKaleganov/url_shortcut_back/blob/master/src/main/resources/img/editUser.png?raw=true "Optional Title")
Меню пользователя
![Alt text](https://github.com/AlexandrKaleganov/url_shortcut_back/blob/master/src/main/resources/img/menu-user-user.png?raw=true "Optional Title")
Список с добавленными ссылками
![Alt text](https://github.com/AlexandrKaleganov/url_shortcut_back/blob/master/src/main/resources/img/urladd.png?raw=true "Optional Title")
Переход по короткой ссылке(переходы доступны для не авторизованных пользователей)
![Alt text](https://github.com/AlexandrKaleganov/url_shortcut_back/blob/master/src/main/resources/img/sendshortcut.png?raw=true "Optional Title")
Отображение статистики(сколько раз переходили по ссылкам)
![Alt text](https://github.com/AlexandrKaleganov/url_shortcut_back/blob/master/src/main/resources/img/statistic.png?raw=true "Optional Title")






