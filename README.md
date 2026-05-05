# Spring Boot Project User API

## Description

** Português **

Este projeto foi inicialmente elaborado através de um prompt de IA do Claude Code. Solicitei que fosse construído um projeto de backend Java utilizando Spring Boot e deveria seguir as seguintes instruções: Utilizar JDK 17, o padrão de projeto MVC (Model-View-Controller), um objeto User com os campos ID, nome de usuário, senha, email, cidade e país, e PostgreSQL como banco de dados. Em seguida, adicionei um novo objeto, Post, com os campos ID, título, texto e usuário, objeto que faz referência ao objeto User.

** English **

This project was initially created using an AI prompt from Claude Code. I requested that a Java backend project be built using Spring Boot, following these instructions: use JDK 17, adopt the MVC (Model-View-Controller) design pattern, include a User object with the fields ID, username, password, email, city, and country, and use PostgreSQL as the database. Then, I added a new object, Post, with the fields ID, title, text, and user—an object that references the User object.

## Technologies
   - JDK 17+
   - Maven
   - PostgreSQL
   - Spring Boot

<pre>
+---.settings
+---src
¦   +---main
¦   ¦   +---java
¦   ¦   ¦   +---com
¦   ¦   ¦       +---umfrancisco
¦   ¦   ¦           +---controller
¦   ¦   ¦           +---exception
¦   ¦   ¦           +---model
¦   ¦   ¦           +---repository
¦   ¦   ¦           +---service
¦   ¦   +---resources
¦   +---test
¦       +---java
¦           +---com
¦               +---umfrancisco
+---target
    +---classes
    ¦   +---com
    ¦   ¦   +---umfrancisco
    ¦   ¦       +---controller
    ¦   ¦       +---exception
    ¦   ¦       +---model
    ¦   ¦       +---repository
    ¦   ¦       +---service
    ¦   +---META-INF
    ¦       +---maven
    ¦           +---com.example
    ¦               +---user-api
    +---test-classes
        +---com
            +---umfrancisco
</pre>