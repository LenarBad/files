[![Maven Central](https://img.shields.io/maven-central/v/io.lenar/files.svg)](https://maven-badges.herokuapp.com/maven-central/io.lenar/files)

# Easy Files


Read files in the ```resources``` folder just like this

```java
String content = new ResourceFile("my-file.json").string();
```

or like this

```java
List<String> lines = new ResourceFile("my-file.txt").lines();
```

or like this

```java
Book book = new JsonResourceFile("my-book.json").fromJson(Book.class);
```

or even like this

```java
List<Book> books = new JsonResourceFile("books.json").fromJsonAsList(Book[].class);
```

No matter it's in the File System or in the Jar.

All you need is to add the Maven dependency

```xml
    <dependency>
        <groupId>io.lenar</groupId>
        <artifactId>files</artifactId>
        <version>1.2.2</version>
    </dependency>
```
