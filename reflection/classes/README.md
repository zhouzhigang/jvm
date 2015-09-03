# Classes

## Retrieving Class Objects

* `Object.getClass()`
* The `.class` Syntax
* `Class.forName()`
* Type Field for Primitive Type Wrappers
* Methods that return Classes
    - class.getSuperclass()
    - Class.getClasses()
    - class.getDeclaredClasses()

## Examining Class modifiers and Types

Class Modifiers:
* Access modifiers: `public`, `protected`, `private`
* Modifier requiring override: `abstract`
* Modifier restricting to one instance: `static`
* Modifier prohibiting value modification: `final`
* Modifier forcing strict floating point behavior: `strictfp`
* Annotations

    Class c = Class.forName("xxx");
    c.getModifiers();
    TypeVariable<?>[] tv = c.getTypeParameters();
    Class<?> ancestor = c.getSuperclass();
    Type[] intfs = c.getGenericInterfaces();
    Annotation[] ann = c.getAnnotations();

## Discovering Class Memebers

Class Methods for Locating __Fields__

|class API|List of Members|Inherited members|Private members|
+---------+---------------+-----------------+---------------+
|`getDelcaredField()`|no|no|yes|
|`getField()`|no|yes|no|
|`getDeclaredFields()`|yes|no|yes|
|`getFields()`|yes|yes|no|

Class Methods for Locating __Methods__

|class API|List of Members|Inherited members|Private members|
+---------+---------------+-----------------+---------------+
|`getDelcaredMethod()`|no|no|yes|
|`getMethod()`|no|yes|no|
|`getDeclaredMethods()`|yes|no|yes|
|`getMethods()`|yes|yes|no|

Class Methods for Locating __Constructors__

|class API|List of Members|Inherited members|Private members|
+---------+---------------+-----------------+---------------+
|`getDelcaredConstructor()`|no|N/A|yes|
|`getConstructor()`|no|N/A|no|
|`getDeclaredConstructors()`|yes|N/A|yes|
|`getConstructors()`|yes|N/A|no|

## Troubleshooting


