# Spring Boot Pagination and Sorting

## Gradle based spring boot application which provide APIs with pagination and sorting using test driven development.

## APIs of the Application
    - Read Avengers
    - Read Avengers with Pagination
    - Read Avengers with Sorting
    - Read Avengers with Pagination and Sorting

I have clearly explained about these topics on Medium. 
* [Part - 1](https://medium.com/@rasheed99/pagination-and-sorting-using-spring-boot-with-tdd-part-i-c6533aa57a0c)
* [Part - 2](https://medium.com/@rasheed99/pagination-and-sorting-using-spring-boot-with-tdd-part-ii-281958b2d6fb)

## Get Avengers

* Request
```
GET /avengers
Host: localhost:3000
```
* Response
```
Status code: 200 OK
Body:
[
    {
        "id": 1,
        "name": "Iron Man",
        "introducedInMovie": "Iron Man",
        "introducedInYear": 2008
    },
    {
        "id": 2,
        "name": "Thanos",
        "introducedInMovie": "The Avengers",
        "introducedInYear": 2012
    },
    {
        "id": 3,
        "name": "Thor",
        "introducedInMovie": "Thor",
        "introducedInYear": 2011
    }
]
```

## Get Avengers with Pagination

* Request
```
GET /avengers-with-pagination?pageNumber=1&pageSize=3
Host: localhost:3000
```
* Response
```
Status code: 200 OK
Body:
[
    {
        "id": 4,
        "name": "Thor",
        "introducedInMovie": "Thor",
        "introducedInYear": 2011
    },
    {
        "id": 5,
        "name": "Loki",
        "introducedInMovie": "Thor",
        "introducedInYear": 2011
    },
    {
        "id": 6,
        "name": "Hulk",
        "introducedInMovie": "The Incredible Hulk",
        "introducedInYear": 2008
    }
]
```

## Get Avengers with Sorting

* Request
```
GET /avengers-with-sorting?sort=introducedInYear,desc
Host: localhost:3000
```
* Response
```
Status code: 200 OK
Body:
[
    {
        "id": 7,
        "name": "Captain Marvel",
        "introducedInMovie": "Captain Marvel",
        "introducedInYear": 2019
    },
    {
        "id": 12,
        "name": "Doctor Strange",
        "introducedInMovie": "Doctor Strange",
        "introducedInYear": 2016
    },
    {
        "id": 8,
        "name": "Spider Man",
        "introducedInMovie": "Captain America: Civil War",
        "introducedInYear": 2016
    }
]
```

## Get Avengers with Pagination ans Sorting

* Request
```
GET /avengers-with-pagination-and-sorting?pageNumber=0&pageSize=3&sort=id,desc
Host: localhost:3000
```
* Response
```
Status code: 200 OK
Body:
[
    {
        "id": 21,
        "name": "Red Skull",
        "introducedInMovie": "Captain America: The First Avenger",
        "introducedInYear": 2011
    },
    {
        "id": 20,
        "name": "Ultron",
        "introducedInMovie": "Avengers: Age of Ultron",
        "introducedInYear": 2015
    },
    {
        "id": 19,
        "name": "Winter Soldier",
        "introducedInMovie": "Captain America: The Winter Soldier",
        "introducedInYear": 2014
    }
]
```