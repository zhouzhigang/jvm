# Memebers

Reflection defines an interface `java.lang.reflect.Member` which is implemented by `java.lang.reflect.Field`, `java.lang.reflect.Method`, `java.lang.reflect.Constructor`.

## Fields

A `field` is a class, interface, or enum iwth an associated value.

### Obtaining Field Types

    Class<?> c = Class.forName("className");
    Field f = c.getField("fieldName");
    f.getType();
    f.getGenericType();

e.g. [FieldSpy.java](FieldSpy.java)

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

    Field f = c.getDeclaredFiled("fielName");
    f.setLong(instance, value);
    f.getLong(instance);
    f.set(instance, value);
    f.get(instance);

Note: Setting a field's value via reflection has a certain amount of performance overhead because various operations must occur such as validating access permissions.

Use of reflection can cause some runtime optimizations to be lost. For example, `int x = 1; x = 2; x =3;` will be optimized by JVM, while using `Field.set*()` will not.

e.g [Book.java](Book.java)

### Troubleshooting

* [IllegalArgumentException due to Inconvertible Types](FieldTrouble.java)
* NoSuchFieldException for Non-Public Fields
* [IllegalAccessException when Modifying Final Fields](FieldTroubleToo.java)

## Methods

## Constructors


