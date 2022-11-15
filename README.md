# RIPEMD160 implementation for Java Card.
This implementation doesn't have any static array initialisation, hence can be used inside library packages.
I made use of the `m4` preprocessor in order to write this implementation.

To use this in your project, copy `Ripemd160.java` into your codebase.

To understand the implementation more clearly, read `Ripemd160.m4`.

The file `Ripemd160.java` can be obtained my running `m4 Ripemd160.m4 > Ripemd160.java` and then removing redunancies as suggested by the IntelliJ IDEA editor.

# Licence
to be done
