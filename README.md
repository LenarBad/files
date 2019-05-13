[![Maven Central](https://img.shields.io/maven-central/v/io.lenar/files.svg)](https://maven-badges.herokuapp.com/maven-central/io.lenar/files)

# Easy Files

* [Maven dependency](#maven-dependency)
* [Resources folder files - ResourceFile, JsonResourceFile](#resources-folder-files---resourcefile-jsonresourcefile)
  * [Examples](#resource-file-examples)
* [Regular files in File System - UserFiles, UserHomeFile](#regular-files-in-file-system---userfiles-userhomefile)
  * [Examples](#regular-file-examples)
  
## Maven dependency

```xml
    <dependency>
        <groupId>io.lenar</groupId>
        <artifactId>files</artifactId>
        <version>1.2.4</version>
    </dependency>
```

## Resources folder files - ResourceFile, JsonResourceFile

Reading files in the ```resources``` folder

No matter if the resource files are in the File System or in the Jar.

### Resource file examples

```java
String content = new ResourceFile("my-file.json").content();

List<String> lines = new ResourceFile("my-file.txt").lines();

Book book = new JsonResourceFile("my-book.json").fromJson(Book.class);

List<Book> books = new JsonResourceFile("books.json").fromJsonAsList(Book[].class);
```

## Regular files in File System - UserFiles, UserHomeFile

### Regular file examples

```java
String myFileContent = new UserFile("c:/myfiles/myfile.txt").content();
String myUserHomeFileContent = new UserHomeFile("my-user-home-test.txt").content();

List<String> myFileLines = new UserFile("my-file.txt").lines();
List<String> myUserHomeFileLines = new UserHomeFile("my-user-home-test.txt").lines();
```
