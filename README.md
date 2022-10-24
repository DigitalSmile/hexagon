# Hexagon Java library
![Java+](https://img.shields.io/badge/Java-17%2B-green)
[![](https://jitpack.io/v/DigitalSmile/hexagon.svg)](https://jitpack.io/#DigitalSmile/hexagon)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/DigitalSmile/hexagon/Java%20CI%20with%20Gradle)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=DigitalSmile_hexagon&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=DigitalSmile_hexagon)
[![codecov](https://codecov.io/gh/DigitalSmile/hexagon/branch/main/graph/badge.svg?token=YH8VTC3F99)](https://codecov.io/gh/DigitalSmile/hexagon)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=DigitalSmile_hexagon&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=DigitalSmile_hexagon)
![GitHub](https://img.shields.io/github/license/DigitalSmile/hexagon)

Small and pure Java library to deal with hexagons math and operations with zero dependencies. The library provides an abstract level of hexagon manipulation and is render engine agnostic, so it can be used with any type of visual libraries (AWT, JavaFX, libGDX, etc.)

Documentation can be found in [wiki](https://github.com/DigitalSmile/hexagon/wiki).

The code and the library itself are highly inspired by blog posts by @redblobgames (https://www.redblobgames.com/grids/hexagons/).

## Features
- Pure Java 17+ library
- Zero Dependencies
- Supports regular hexagons with flat or pointy orientation 
- Supports custom object to be tied to hexagon
- Supports grid creation via simple builder
- Supports grid with rectangle and hexagonal shape

## Usage
Add dependency to your project. Latest version can be found at the jitpack badge.

Gradle:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.DigitalSmile:hexagon:{version}'
}
```
Maven:
```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
...
<dependency>
    <groupId>com.github.DigitalSmile</groupId>
    <artifactId>hexagon</artifactId>
    <version>{version}</version>
</dependency>
```
Use a `HexagonGridBuilder` to create a grid or use `Hexagon` and `Operations` classes directly:
```
var hexagonGrid = new HexagonGrid.HexagonGridBuilder<>()
                        .shape(new HexagonalShape(5), Orientation.FLAT) // hexagonal shape with radius of 5 hexagons and flat orientation
                        .hexagonWidth(150)                              // width of hexagon in physical units
                        .build();
hexagonGrid.generateHexagons();
var hexagonList = hexagonGrid.getHexagons(); // returns all generated hexagons
var lineHexagonList = HexagonOperations.hexagonLinePath(new Hexagon(0,0,0), new Hexagon(0, -3, 3)); // returns hexagons, that are in line between two hexagons
```

## Next milestone - [Mercury](https://github.com/DigitalSmile/hexagon/milestone/1) ![GitHub milestone](https://img.shields.io/github/milestones/progress-percent/DigitalSmile/hexagon/1)