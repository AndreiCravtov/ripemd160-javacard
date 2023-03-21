# RIPEMD160 implementation for Java Card.
This implementation doesn't have any static array initialisation, hence can be used inside library packages.
I made use of the `m4` preprocessor in order to write this implementation.

To use this in your project, copy `Ripemd160.java` into your codebase.

To understand the implementation more clearly, read `Ripemd160.m4`.

The file `Ripemd160.java` can be obtained by running `m4 Ripemd160.m4 > Ripemd160.java` and then removing redunancies as suggested by the IntelliJ IDEA editor.

# Licence
This project was inspired by [Project Nayuki's RIPEMD160 implementation](https://github.com/nayuki/Bitcoin-Cryptography-Library/blob/master/java/io/nayuki/bitcoin/crypto/Ripemd160.java).

```
MIT License

Copyright © 2022 Andrei Cravtov
Copyright © 2019 Project Nayuki

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

The Software is provided "as is", without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose and noninfringement. In no event shall the authors or copyright holders be liable for any claim, damages or other liability, whether in an action of contract, tort or otherwise, arising from, out of or in connection with the Software or the use or other dealings in the Software.
```
