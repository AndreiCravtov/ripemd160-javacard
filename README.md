# RIPEMD160 implementation for Java Card.
This implementation doesn't have any static array initialisation, hence can be used inside library packages.
I made use of the `m4` preprocessor in order to write this implementation.

To use this in your project, copy `Ripemd160.java` into your codebase.

To understand the implementation more clearly, read `Ripemd160.m4`.

The file `Ripemd160.java` can be obtained my running `m4 Ripemd160.m4 > Ripemd160.java` and then removing redunancies as suggested by the IntelliJ IDEA editor.

# Licence
This project was inspired by [Project Nayuki's RIPEMD160 implementation](https://github.com/nayuki/Bitcoin-Cryptography-Library/blob/master/java/io/nayuki/bitcoin/crypto/Ripemd160.java).

```
MIT License

Copyright (c) 2022 Andrei Cravtov, Project Nayuki

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
