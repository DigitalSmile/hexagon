# Hexagon Java library
![Java+](https://img.shields.io/badge/Java-17%2B-green)
[![](https://jitpack.io/v/DigitalSmile/hexagon.svg)](https://jitpack.io/#DigitalSmile/hexagon)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/DigitalSmile/hexagon/Java%20CI%20with%20Gradle)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=DigitalSmile_hexagon&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=DigitalSmile_hexagon)
[![codecov](https://codecov.io/gh/DigitalSmile/hexagon/branch/main/graph/badge.svg?token=YH8VTC3F99)](https://codecov.io/gh/DigitalSmile/hexagon)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=DigitalSmile_hexagon&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=DigitalSmile_hexagon)
![GitHub](https://img.shields.io/github/license/DigitalSmile/hexagon)

Small and pure Java library to deal with hexagons math and operations with zero dependencies. The library provides an abstract level of hexagon manipulation and is render engine agnostic, so it can be used with any type of visual libraries (AWT, JavaFX, libGDX, etc.)

The code and the library itself are highly inspired by blog posts by @redblobgames (https://www.redblobgames.com/grids/hexagons/)

## Features
- Pure Java 17+ library
- Zero Dependencies
- Supports regular hexagons with flat or pointy orientation 
- Supports custom object to be tied to hexagon
- Supports grid creation via simple builder
- Supports grid with rectangle and hexagonal shape

## Usage
Add dependency to your project.

Gradle:

```
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }

    dependencies {
        implementation 'com.github.DigitalSmile:hexagon:0.1.2'
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
            <version>0.1.2</version>
        </dependency>
```
Use a `HexagonGridBuilder` to create a grid or use `Hexagon` and `Operations` classes directly:
```
        var hexagonGrid = new HexagonGrid.HexagonGridBuilder()
                .hexagonShape(new HexagonalBounds(5))
                .orientation(Orientation.FLAT)
                .hexagonWidth(150)
                .build();
        hexagonGrid.generateHexagons();
        var hexagonList = hexagonGrid.getHexagons();
```

## Next milestone - [Mercury](https://github.com/DigitalSmile/hexagon/milestone/1) ![GitHub milestone](https://img.shields.io/github/milestones/progress-percent/DigitalSmile/hexagon/1)