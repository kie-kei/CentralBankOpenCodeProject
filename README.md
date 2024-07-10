Проект разделен на два модуля
- central-bank-rest-api - модуль содержащий все что связано с API (dto, swagger, exceptions)
- central-bank-rest-src - само spring boot приложение

Для удобства загрузили образ на docker hub

`docker pull kieq/central-bank-rest-src`

`docker run -p 8080:8080 kieq/central-bank-rest-src`

Swagger доступен по эндпоинту `/swagger-ui/index.html`

Spring Security реализован с jwt токеном.
По умолчанию создается пользователь admin и 2 пользователя user
Данные для аутентификации:
```
{
  "username": "admin",
  "password": "admin"
}
```

```
{
  "username": "user1",
  "password": "user
}
```

```
{
  "username": "user2",
  "password": "user
}
```

---

База данных - `h2`
Доступ к `h2` консоли досутпен по эндпоинту /h2-console

login: sa

password: password

JDBC URL: jdbc:h2:file:/data/centralbankdb

---

также прикручен `liquibase`

для компактного маппинга использовали `mapstruct`

для работы с xml использовали `org.glassfish.jaxb`

`lombok` в деле (учтены все рекомендации :) ~~наверное~~ ) 

---

пара слов на оправдание этого творения.

много `dto` временно наверное.
пока нереализованы тесты, а может и не ~~пока~~. тестили все ручками, но **вроде** все должно работать корректно.
увидели на скриншоте кнопку "актуализирвоать данные" и сделали ручку `/api/v1/file/cbr` , котоаря обновляет данные в с сайта cbr
