![GitHub](https://img.shields.io/github/license/lenarbad/files)
[![Maven Central](https://img.shields.io/maven-central/v/io.lenar/files.svg)](https://maven-badges.herokuapp.com/maven-central/io.lenar/files)

![Build Status](https://travis-ci.com/LenarBad/files.svg?branch=master)
[![Hits of Code](https://hitsofcode.com/github/lenarbad/files)](https://hitsofcode.com/view/github/lenarbad/files)
[![Lines of Code](https://tokei.rs/b1/github/lenarbad/files)](https://github.com/lenarbad/files)



# Easy Files

* [Maven dependency](#maven-dependency)
* [Resources folder files - ResourceFile](#resources-folder-files---resourcefile)
* [Regular files in File System - UserFiles, UserHomeFile](#regular-files-in-file-system---userfiles-userhomefile)
* [Network Resources](#network-resources)
* [Read file examples](#read-file-examples)
  
## Maven dependency

```xml
    <dependency>
        <groupId>io.lenar</groupId>
        <artifactId>files</artifactId>
        <version>1.6.1</version>
    </dependency>
```

## Resources folder files - ResourceFile

Reading files in the ```resources``` folder

No matter if the resource files are in the File System or in the Jar.

```java
ResourceFile file = new ResourceFile("my-file.json");
```

## Regular files in File System - UserFiles, UserHomeFile

```java
UserFile file = new UserFile("c:/myfiles/myfile.txt");
```

```java
UserHomeFile file = new UserHomeFile("my-user-home-test.txt");
```

## Network sources

It can be a file somewhere in the Internet or other resources accessible through urls.

```java
NetworkSource file = new NetworkSource("https://github.com/LenarBad/files")
```

## Read file examples

```java
String content = file.content();
List<String> lines = file.lines();

Book book = file.fromJson(Book.class);
List<Book> books = file.fromJsonAsList(Book[].class);

Book book = file.fromYaml(Book.class);
List<Book> books = file.fromYamlAsList(Book.class);

Properties properties = file.properties();
```
