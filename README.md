# Recaf Utility Classes

These copy-pasted here from [the main repo](https://github.com/Col-E/Recaf/tree/master/src/main/java/me/coley/recaf/util) for the following use cases:

1. Wanting to use the Recaf utilities without importing the whole Recaf dependency tree
2. Quick reference/skidding since these don't depend on internal Recaf components.

## Usage

### Add dependency

Add Jitpack to your repositories
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add the dependency _(where `VERSION` is the latest version, IE `1.0.0`)_
```xml
<dependency>
    <groupId>com.github.Col-E</groupId>
    <artifactId>rcutils</artifactId>
    <version>VERSION</version>
</dependency>
```