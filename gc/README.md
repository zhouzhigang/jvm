# Garbage Collection

## Overview
What memory need garbage collection?
When need garbage collection?
How to garbage collection?
Why I need learn the priciple of garbage collection?

### Reference Counting

### Reachability Analysis
Reachability Analysis: GC Roots

### Reference
package java.lang.ref;

* Reference<T>

Abstract base class for reference objects.
This class defines the operations common to all reference objects.  Because reference objects are implemented in close cooperation with the garbage collector, this class may not be subclassed directly.

* Strong Reference

Reference like "Object obj = new Object();" , if it exist, GC never

* SoftReference<T>

which are cleared at the discretion of the garbage collector in response to memory demand.  Soft references are most often used to implement memory-sensitive caches.

* WeakReference<T>

which do not prevent their referents from being made finalizable, finalized, and then reclaimed.

PhantomReference:


## Garbage Collection Algorithm

### Mark-Sweep

### Copying

### Mark-Compact

### Generational Collection


## HotSpot GC Algorithm Implementation


## Garbage Collector

### Serial Collector
Single thread: stop the whole world
Default gc in jvm client mode.
Easy and effective.

### Parallel Scavenge

### ParNew


### Serial MSC

### Parallel MSC

### CMS
