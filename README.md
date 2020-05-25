# PhoneBook

1.	Есть база данных :
- таблица person: id, name
- таблица contacts: id, phonenumber, id_person
mysql 
/sql - директория бд
 
2.
 - запроса данных из таблицы person, по id-записи и получения связанных записей из таблицы contacts
 - запроса данных из таблицы person, по полю name - записи и получения связанных записей из таблицы contacts
 - запроса всех данных из таблицы person, и получения связанных записей из таблицы contacts
 - создание записи в таблице person, и создания связанных записей в таблице contacts
 - обновление записи в таблице person, и обновление связанных записей в таблице contacts
 - удаление записи в таблице person, и удаление связанных записей в таблице contacts
 
 3. проверка rest api напрямую
curl --request GET http://localhost:8080/all

curl --request GET  http://localhost:8080/personByName?name=TestName

curl --request POST --header "Content-Type: application/json" --data "{\"name\":\"TestName\"}" http://localhost:8080/create-person

curl --request DELETE  http://localhost:8080/delete/
 
Использовалось: Spring Boot + Hibernate + MapStruct + Maven
