# Library

### How to run
Setup a local mongo server:
```shell script
mongodb://localhost:27017/my-books
```
Test and run the application:
```shell script
sbt test
sbt run
```
How to test in Postman:
1. add 20 documents to mongo:
```shell script
{"title": "The Great Gatsby", "author": "F. Scott Fitzgerald"}
{"title": "To Kill a Mockingbird", "author": "Harper Lee"}
{"title": "1984", "author": "George Orwell"}
{"title": "Pride and Prejudice", "author": "Jane Austen"}
{"title": "The Catcher in the Rye", "author": "J.D. Salinger"}
{"title": "Animal Farm", "author": "George Orwell"}
{"title": "Brave New World", "author": "Aldous Huxley"}
{"title": "One Hundred Years of Solitude", "author": "Gabriel Garcia Marquez"}
{"title": "Crime and Punishment", "author": "Fyodor Dostoevsky"}
{"title": "The Picture of Dorian Gray", "author": "Oscar Wilde"}
{"title": "Wuthering Heights", "author": "Emily Bronte"}
{"title": "The Adventures of Huckleberry Finn", "author": "Mark Twain"}
{"title": "The Sound and the Fury", "author": "William Faulkner"}
{"title": "The Brothers Karamazov", "author": "Fyodor Dostoevsky"}
{"title": "The Sun Also Rises", "author": "Ernest Hemingway"}
{"title": "The Grapes of Wrath", "author": "John Steinbeck"}
{"title": "Invisible Man", "author": "Ralph Ellison"}
{"title": "Lord of the Flies", "author": "William Golding"}
{"title": "A Clockwork Orange", "author": "Anthony Burgess"}
{"title": "Beloved", "author": "Toni Morrison"}
```
2) Look file routes in conf package to test api