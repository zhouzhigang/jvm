# Memebers

Reflection defines an interface `java.lang.reflect.Member` which is implemented by `java.lang.reflect.Field`, `java.lang.reflect.Method`, `java.lang.reflect.Constructor`.

## Fields

A `field` is a class, interface, or enum iwth an associated value.

### Obtaining Field Types

    Class<?> c = Class.forName("className");
    Field f = c.getField("fieldName");
    f.getType();
    f.getGenericType();

e.g. [FieldSpy.java](FieldSpy/java)

### Retrieving and Parsing Field Modifiers

Field modifiers:

* Access modifiers: `public`, `protected`, `private`
* Field-specific modifiers governing runtime behavior:`transient`, `volatile`
* Modifier restricting to one instance: `static`
* Modifier prohibiting value modification: `final`
* Annotations

    Class<?> c = Class.forNmae("className");
    Field[] flds = c.getDeclaredFields();
    for (Field f : flds) {
        int mod = f.getModifiers();
        //...
    }

e.g. [FieldModifierSpy.java](FieldModifierSpy.java)


### Getting and Setting Field Values

### Troubleshooting

## Methods

## Constructors


