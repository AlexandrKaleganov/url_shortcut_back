[![Build Status](https://travis-ci.org/AlexandrKaleganov/url_shortcut_back.svg?branch=master)](https://travis-ci.org/AlexandrKaleganov/url_shortcut_back)
[![codecov](https://codecov.io/gh/AlexandrKaleganov/url_shortcut_back/branch/master/graph/badge.svg?token=L1PSKISZXZ)](https://codecov.io/gh/AlexandrKaleganov/url_shortcut_back)
# url_shortcut
Стек технологий:
java 8,
SpringBoot

Авторизация через  jwt

СУБД MySQL/PostresSQL  - в зависимости от выбанного вами файла

liquibase(для входа в приложение можно использовать admin/admin || user/user)

Добавлено логирование с ипользованием Aspect

Написаны   тесты junit/mockito

Angular

#deploy: 
1. Создайте локально базу url_shortcut
2. В application-postgres.yml  укажите необходимый логин/пароль от бд
3. В application-postgres.yml  укажите порт на котором будет запущено приложение , на данный момент  9000
4. mvn clean install

приложение состоит из двух частей
Backend
https://github.com/AlexandrKaleganov/url_shortcut_back
Frontend
https://github.com/AlexandrKaleganov/url_shortcut_frontend

В данном приложении реализован следующий функционал: 
пользовател может зарегистрироваться, чтобы зарегистрироваться необходимо указать домен, например: 'google.com'
без http, www  и всего лишнего,  после регистрации пользователь может добавлять длинные ссылки.
В форму уже подставляется домен автоматически.
На вкладке "ссылки" мы можем наблюдать список ссылок и коротких ссылок к ним.
Список ссылок, Список пользователей, Статистика отображается с пагинацией.
Пользователей можно  фильтровать по домену, по логину, по фамилии, Ссылки можно фильтровать по логину пользователя
бек java/springboot
фронт реализован на Angular

Все работы проводились в ветке dev далее были слиты в master 
ниже идут скриншёты


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






