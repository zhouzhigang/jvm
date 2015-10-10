# Java Memory Model

## Java Runtime Aera

### Program Counter Register

The JVM can support many threads of execution at once.
Each JVM thread has its own pc (program counter) register. 

pointer to `null` when execute native method


### Java Virtual machine Stasks

Each Java Virtual Machine thread has a private Java Virtual Machine stack, created at the same time as the thread.

Stack Frame: Local Variable Table(primiary, reference), Operand Stack, Dynamic Link, returnAddress

StackOverflow
OutOfMemory

### Native Method Stack

Similar with JVM Stack, but for Native method

Sun HotSpot combine JVM stack and Native Method Stack in one stack.

StackOverflow
OutOfMemory

### Java Heap

The largest one, shared by all threads(excepted for TLAB), created on JVM start-up.

Sotre all class instance(new) and arrarys.
Optimize: allocate on stack when no escape.

The main area of garbage collection.
New(Eden, From Survivor, To Survivor), Old

OutOfMemory(-Xms, -Xmx)

### Method Area

Shared by all threads.

Sotre class information, constant, static variables etc.

HotSpot: Permanent Generation( -> Metadata Area(Native/Direct Memory)


OutOfMemeory

### Run-Time Constant Pool

A run-time constant pool is a per-class or per-interface run-time representation of the `constant_pool` table in a `class` file.
Part of Method Area.

Dynamic example: `String.intern()`

OutOfMemory

### Direct Memeory

Not part of JVM run-time area.

Java 1.4 NIO, `Channel`, `Buffer` use native libarary to allocate direct memory, and use a `DirectByteBuffer` in Java Heap to reference it.
Improve performance by avoiding copy data between Java Heap and Native Heap.

OutOfMemory(Toatl memory of the Machine)

## New Object

`new` instruction

+ Check if there are symbol reference of the class in the constant pool, and if the class have been loaded, parsed, initialized or not.
+ After it has loaded, the JVM will allocate memory for the object.
    * Where to allocate?
        - Stack if no Escape
        - TLAB(+/-UseTLAB)
        - HEAP
    * How to allocate? depends on GC?
        - Just move the pointer(`Bump the Pointer`) - Serial, ParNew: Compact
        - Maintain a `Free List` - CMS: Mark-Sweep
+ Initialization
+ Necessary seeting by JVM
    * Object Header(Class reference, Metadata, object hashcode, gc age, lock)
+ invokespecial: `<init>`

## Object Memory Model

+ Header
    * Mark Word: save runtime data, 32 or 64 bits
        - hashcode, gc age; lock
        - Class Type Reference
+ Instance Data
+ Padding

## Locate an object

+ Handle Pool(Stable address)
    * pointer to object
    * pointer to class(object type)
+ Direct Pointer(JVM use, fast)
    * there is a pointer to class(type) in the object

## Reference

* [JVM Specification - 2.5. Run-Time Data Areas](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-2.html#jvms-2.5)
