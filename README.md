[![Maven Central](https://img.shields.io/maven-central/v/io.lenar/files.svg)](https://maven-badges.herokuapp.com/maven-central/io.lenar/files)

# files


Read files in the ```resources``` folder just like this

```java
String content = new ResourceFile("my-file.json").string();
```

or

```java
List<String> lines = new ResourceFile("my-file.txt").lines();
```

No matter it's in the File System or in the Jar