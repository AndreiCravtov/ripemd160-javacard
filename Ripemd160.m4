// MIT License

// Copyright (c) 2022 Andrei Cravtov, Project Nayuki

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

divert(-1)dnl // BEGIN MACRO DEFINITIONS
define(`_forloop',`$3`$1'$4`'ifelse(`$1', `$2', `',`$0(incr(`$1'), `$2', `$3', `$4')')')
define(`forloop',`ifelse(eval(`($1) <= ($2)'), `1',`_forloop(`$1', eval(`$2'), `$3(', `)')')')
define(`_join',`ifelse(`$#$2', `2', `',`ifelse(`$2', `', `', ``$1$2'')$0(`$1', shift(shift($@)))')')
define(`join',`ifelse(`$#', `2', ``$2'',`ifelse(`$2', `', `', ``$2'_')$0(`$1', shift(shift($@)))')')

// VARIABLE OPERATIONS
define(`DECLARE_VARIABLE',`ifelse($2,`',`ifelse($1,`',`',`$1A = 0, $1B = 0')',`$1A = 0, $1B = 0, $0(shift($@))')')
define(`SET',`ifelse(`$#',`3',`$1A = (short) $2; $1B = (short) $3;',`errprint(`Macro SET(name, a, b) was provided $# arguments.
')')')
define(`COPY',`ifelse(`$#',`2',`$1A = $2A; $1B = $2B;',`errprint(`Macro COPY(target, source) was provided $# arguments.
')')')
define(`COPY_TO_ARRAY',`ifelse(`$#',`3',`
$1[(short) ($2)] = (byte) ($3B);
$1[(short) ($2 + 1)] = (byte) ($3B >>> 8);
$1[(short) ($2 + 2)] = (byte) ($3A);
$1[(short) ($2 + 3)] = (byte) ($3A >>> 8);
',`errprint(`Macro COPY_TO_ARRAY(array, arrayOffset, source) was provided $# arguments.
')')')

// LOGICAL OPERATIONS
define(`AND',`ifelse(`$#',`3',`$1A = (short) ($2A & $3A); $1B = (short) ($2B & $3B);',`errprint(`Macro AND(target, x, y) was provided $# arguments.
')')')
define(`OR',`ifelse(`$#',`3',`$1A = (short) ($2A | $3A); $1B = (short) ($2B | $3B);',`errprint(`Macro OR(target, x, y) was provided $# arguments.
')')')
define(`XOR',`ifelse(`$#',`3',`$1A = (short) ($2A ^ $3A); $1B = (short) ($2B ^ $3B);',`errprint(`Macro XOR(target, x, y) was provided $# arguments.
')')')
define(`NOT',`ifelse(`$#',`2',`$1A = (short) (~$2A); $1B = (short) (~$2B);',`errprint(`Macro NOT(target, source) was provided $# arguments.
')')')
define(`ROTATE_LEFT',`ifelse(`$#',`3',`
GET_ROTATE_LEFT_MASK(rotateLeftMask, $3)
rotateLeftCarryA = (short) (($2A >>> (16 - $3)) & rotateLeftMask);
rotateLeftCarryB = (short) (($2B >>> (16 - $3)) & rotateLeftMask);
$1A = (short) (($2A << $3) | rotateLeftCarryB);
$1B = (short) (($2B << $3) | rotateLeftCarryA);
',`errprint(`Macro ROTATE_LEFT(target, source, distance) was provided $# arguments.
')')')

// ARITHMETIC OPERATIONS
define(`ADD_VALUES',`ifelse(`$#',`5',`
$1A = (short) ((short) $2 + (short) $4 + (short) ((short) $3 < 0 && (short) $5 < 0 || (short) $3 < 0 && (short) $5 >= (short) (-$3) || (short) $5 < 0 && (short) $3 >= (short) (-$5) ? 1 : 0));
$1B = (short) ((short) $3 + (short) $5);
',`errprint(`Macro ADD_VALUES(target, xA, xB, yA, yB) was provided $# arguments.
')')')
define(`ADD',`ifelse(`$#',`3',`ADD_VALUES($1, $2A, $2B, $3A, $3B)',`errprint(`Macro ADD(target, x, y) was provided $# arguments.
')')')

// CONSTANTS
define(`GET_LEFT_MESSAGE_SCHEDULE',`ifelse(`$#',`2',`switch ($2) {
case 16: $1 = 7; break;
case 17: $1 = 4; break;
case 18: $1 = 13; break;
case 19: $1 = 1; break;
case 20: $1 = 10; break;
case 21: $1 = 6; break;
case 22: $1 = 15; break;
case 23: $1 = 3; break;
case 24: $1 = 12; break;
case 25: $1 = 0; break;
case 26: $1 = 9; break;
case 27: $1 = 5; break;
case 28: $1 = 2; break;
case 29: $1 = 14; break;
case 30: $1 = 11; break;
case 31: $1 = 8; break;
case 32: $1 = 3; break;
case 33: $1 = 10; break;
case 34: $1 = 14; break;
case 35: $1 = 4; break;
case 36: $1 = 9; break;
case 37: $1 = 15; break;
case 38: $1 = 8; break;
case 39: $1 = 1; break;
case 40: $1 = 2; break;
case 41: $1 = 7; break;
case 42: $1 = 0; break;
case 43: $1 = 6; break;
case 44: $1 = 13; break;
case 45: $1 = 11; break;
case 46: $1 = 5; break;
case 47: $1 = 12; break;
case 48: $1 = 1; break;
case 49: $1 = 9; break;
case 50: $1 = 11; break;
case 51: $1 = 10; break;
case 52: $1 = 0; break;
case 53: $1 = 8; break;
case 54: $1 = 12; break;
case 55: $1 = 4; break;
case 56: $1 = 13; break;
case 57: $1 = 3; break;
case 58: $1 = 7; break;
case 59: $1 = 15; break;
case 60: $1 = 14; break;
case 61: $1 = 5; break;
case 62: $1 = 6; break;
case 63: $1 = 2; break;
case 64: $1 = 4; break;
case 65: $1 = 0; break;
case 66: $1 = 5; break;
case 67: $1 = 9; break;
case 68: $1 = 7; break;
case 69: $1 = 12; break;
case 70: $1 = 2; break;
case 71: $1 = 10; break;
case 72: $1 = 14; break;
case 73: $1 = 1; break;
case 74: $1 = 3; break;
case 75: $1 = 8; break;
case 76: $1 = 11; break;
case 77: $1 = 6; break;
case 78: $1 = 15; break;
case 79: $1 = 13; break;
}',`errprint(`Macro GET_LEFT_MESSAGE_SCHEDULE(target, index) was provided $# arguments.
')')')
define(`GET_RIGHT_MESSAGE_SCHEDULE',`ifelse(`$#',`2',`switch ($2) {
case 0: $1 = 5; break;
case 1: $1 = 14; break;
case 2: $1 = 7; break;
case 3: $1 = 0; break;
case 4: $1 = 9; break;
case 5: $1 = 2; break;
case 6: $1 = 11; break;
case 7: $1 = 4; break;
case 8: $1 = 13; break;
case 9: $1 = 6; break;
case 10: $1 = 15; break;
case 11: $1 = 8; break;
case 12: $1 = 1; break;
case 13: $1 = 10; break;
case 14: $1 = 3; break;
case 15: $1 = 12; break;
case 16: $1 = 6; break;
case 17: $1 = 11; break;
case 18: $1 = 3; break;
case 19: $1 = 7; break;
case 20: $1 = 0; break;
case 21: $1 = 13; break;
case 22: $1 = 5; break;
case 23: $1 = 10; break;
case 24: $1 = 14; break;
case 25: $1 = 15; break;
case 26: $1 = 8; break;
case 27: $1 = 12; break;
case 28: $1 = 4; break;
case 29: $1 = 9; break;
case 30: $1 = 1; break;
case 31: $1 = 2; break;
case 32: $1 = 15; break;
case 33: $1 = 5; break;
case 34: $1 = 1; break;
case 35: $1 = 3; break;
case 36: $1 = 7; break;
case 37: $1 = 14; break;
case 38: $1 = 6; break;
case 39: $1 = 9; break;
case 40: $1 = 11; break;
case 41: $1 = 8; break;
case 42: $1 = 12; break;
case 43: $1 = 2; break;
case 44: $1 = 10; break;
case 45: $1 = 0; break;
case 46: $1 = 4; break;
case 47: $1 = 13; break;
case 48: $1 = 8; break;
case 49: $1 = 6; break;
case 50: $1 = 4; break;
case 51: $1 = 1; break;
case 52: $1 = 3; break;
case 53: $1 = 11; break;
case 54: $1 = 15; break;
case 55: $1 = 0; break;
case 56: $1 = 5; break;
case 57: $1 = 12; break;
case 58: $1 = 2; break;
case 59: $1 = 13; break;
case 60: $1 = 9; break;
case 61: $1 = 7; break;
case 62: $1 = 10; break;
case 63: $1 = 14; break;
case 64: $1 = 12; break;
case 65: $1 = 15; break;
case 66: $1 = 10; break;
case 67: $1 = 4; break;
case 68: $1 = 1; break;
case 69: $1 = 5; break;
case 70: $1 = 8; break;
case 71: $1 = 7; break;
case 72: $1 = 6; break;
case 73: $1 = 2; break;
case 74: $1 = 13; break;
case 75: $1 = 14; break;
case 76: $1 = 0; break;
case 77: $1 = 3; break;
case 78: $1 = 9; break;
case 79: $1 = 11; break;
}',`errprint(`Macro GET_RIGHT_MESSAGE_SCHEDULE(target, index) was provided $# arguments.
')')')
define(`GET_LEFT_ROTATION_LEFT_LINE',`ifelse(`$#',`2',`switch ($2) {
case 0: $1 = 11; break;
case 1: $1 = 14; break;
case 2: $1 = 15; break;
case 3: $1 = 12; break;
case 4: $1 = 5; break;
case 5: $1 = 8; break;
case 6: $1 = 7; break;
case 7: $1 = 9; break;
case 8: $1 = 11; break;
case 9: $1 = 13; break;
case 10: $1 = 14; break;
case 11: $1 = 15; break;
case 12: $1 = 6; break;
case 13: $1 = 7; break;
case 14: $1 = 9; break;
case 15: $1 = 8; break;
case 16: $1 = 7; break;
case 17: $1 = 6; break;
case 18: $1 = 8; break;
case 19: $1 = 13; break;
case 20: $1 = 11; break;
case 21: $1 = 9; break;
case 22: $1 = 7; break;
case 23: $1 = 15; break;
case 24: $1 = 7; break;
case 25: $1 = 12; break;
case 26: $1 = 15; break;
case 27: $1 = 9; break;
case 28: $1 = 11; break;
case 29: $1 = 7; break;
case 30: $1 = 13; break;
case 31: $1 = 12; break;
case 32: $1 = 11; break;
case 33: $1 = 13; break;
case 34: $1 = 6; break;
case 35: $1 = 7; break;
case 36: $1 = 14; break;
case 37: $1 = 9; break;
case 38: $1 = 13; break;
case 39: $1 = 15; break;
case 40: $1 = 14; break;
case 41: $1 = 8; break;
case 42: $1 = 13; break;
case 43: $1 = 6; break;
case 44: $1 = 5; break;
case 45: $1 = 12; break;
case 46: $1 = 7; break;
case 47: $1 = 5; break;
case 48: $1 = 11; break;
case 49: $1 = 12; break;
case 50: $1 = 14; break;
case 51: $1 = 15; break;
case 52: $1 = 14; break;
case 53: $1 = 15; break;
case 54: $1 = 9; break;
case 55: $1 = 8; break;
case 56: $1 = 9; break;
case 57: $1 = 14; break;
case 58: $1 = 5; break;
case 59: $1 = 6; break;
case 60: $1 = 8; break;
case 61: $1 = 6; break;
case 62: $1 = 5; break;
case 63: $1 = 12; break;
case 64: $1 = 9; break;
case 65: $1 = 15; break;
case 66: $1 = 5; break;
case 67: $1 = 11; break;
case 68: $1 = 6; break;
case 69: $1 = 8; break;
case 70: $1 = 13; break;
case 71: $1 = 12; break;
case 72: $1 = 5; break;
case 73: $1 = 12; break;
case 74: $1 = 13; break;
case 75: $1 = 14; break;
case 76: $1 = 11; break;
case 77: $1 = 8; break;
case 78: $1 = 5; break;
case 79: $1 = 6; break;
}',`errprint(`Macro GET_LEFT_ROTATION_LEFT_LINE(target, index) was provided $# arguments.
')')')
define(`GET_LEFT_ROTATION_RIGHT_LINE',`ifelse(`$#',`2',`switch ($2) {
case 0: $1 = 8; break;
case 1: $1 = 9; break;
case 2: $1 = 9; break;
case 3: $1 = 11; break;
case 4: $1 = 13; break;
case 5: $1 = 15; break;
case 6: $1 = 15; break;
case 7: $1 = 5; break;
case 8: $1 = 7; break;
case 9: $1 = 7; break;
case 10: $1 = 8; break;
case 11: $1 = 11; break;
case 12: $1 = 14; break;
case 13: $1 = 14; break;
case 14: $1 = 12; break;
case 15: $1 = 6; break;
case 16: $1 = 9; break;
case 17: $1 = 13; break;
case 18: $1 = 15; break;
case 19: $1 = 7; break;
case 20: $1 = 12; break;
case 21: $1 = 8; break;
case 22: $1 = 9; break;
case 23: $1 = 11; break;
case 24: $1 = 7; break;
case 25: $1 = 7; break;
case 26: $1 = 12; break;
case 27: $1 = 7; break;
case 28: $1 = 6; break;
case 29: $1 = 15; break;
case 30: $1 = 13; break;
case 31: $1 = 11; break;
case 32: $1 = 9; break;
case 33: $1 = 7; break;
case 34: $1 = 15; break;
case 35: $1 = 11; break;
case 36: $1 = 8; break;
case 37: $1 = 6; break;
case 38: $1 = 6; break;
case 39: $1 = 14; break;
case 40: $1 = 12; break;
case 41: $1 = 13; break;
case 42: $1 = 5; break;
case 43: $1 = 14; break;
case 44: $1 = 13; break;
case 45: $1 = 13; break;
case 46: $1 = 7; break;
case 47: $1 = 5; break;
case 48: $1 = 15; break;
case 49: $1 = 5; break;
case 50: $1 = 8; break;
case 51: $1 = 11; break;
case 52: $1 = 14; break;
case 53: $1 = 14; break;
case 54: $1 = 6; break;
case 55: $1 = 14; break;
case 56: $1 = 6; break;
case 57: $1 = 9; break;
case 58: $1 = 12; break;
case 59: $1 = 9; break;
case 60: $1 = 12; break;
case 61: $1 = 5; break;
case 62: $1 = 15; break;
case 63: $1 = 8; break;
case 64: $1 = 8; break;
case 65: $1 = 5; break;
case 66: $1 = 12; break;
case 67: $1 = 9; break;
case 68: $1 = 12; break;
case 69: $1 = 5; break;
case 70: $1 = 14; break;
case 71: $1 = 6; break;
case 72: $1 = 8; break;
case 73: $1 = 13; break;
case 74: $1 = 6; break;
case 75: $1 = 5; break;
case 76: $1 = 15; break;
case 77: $1 = 13; break;
case 78: $1 = 11; break;
case 79: $1 = 11; break;
}',`errprint(`Macro GET_LEFT_ROTATION_RIGHT_LINE(target, index) was provided $# arguments.
')')')
define(`GET_ROTATE_LEFT_MASK',`ifelse(`$#',`2',`switch ($2) {
case 0: $1 =          0b0000000000000000; break;
case 1: $1 = (short)  0b0000000000000001; break;
case 2: $1 = (short)  0b0000000000000011; break;
case 3: $1 = (short)  0b0000000000000111; break;
case 4: $1 = (short)  0b0000000000001111; break;
case 5: $1 = (short)  0b0000000000011111; break;
case 6: $1 = (short)  0b0000000000111111; break;
case 7: $1 = (short)  0b0000000001111111; break;
case 8: $1 = (short)  0b0000000011111111; break;
case 9: $1 = (short)  0b0000000111111111; break;
case 10: $1 = (short) 0b0000001111111111; break;
case 11: $1 = (short) 0b0000011111111111; break;
case 12: $1 = (short) 0b0000111111111111; break;
case 13: $1 = (short) 0b0001111111111111; break;
case 14: $1 = (short) 0b0011111111111111; break;
case 15: $1 = (short) 0b0111111111111111; break;
}',`errprint(`Macro GET_ROTATE_LEFT_MASK(target, index) was provided $# arguments.
')')')
define(`GET_SCHEDULE',`ifelse(`$#',`2',`switch ($2) {
case 0: COPY($1,schedule_0) break;
case 1: COPY($1,schedule_1) break;
case 2: COPY($1,schedule_2) break;
case 3: COPY($1,schedule_3) break;
case 4: COPY($1,schedule_4) break;
case 5: COPY($1,schedule_5) break;
case 6: COPY($1,schedule_6) break;
case 7: COPY($1,schedule_7) break;
case 8: COPY($1,schedule_8) break;
case 9: COPY($1,schedule_9) break;
case 10: COPY($1,schedule_10) break;
case 11: COPY($1,schedule_11) break;
case 12: COPY($1,schedule_12) break;
case 13: COPY($1,schedule_13) break;
case 14: COPY($1,schedule_14) break;
case 15: COPY($1,schedule_15) break;
}',`errprint(`Macro GET_SCHEDULE(target, index) was provided $# arguments.
')')')

divert(0)dnl // END MACRO DEFINITIONS
package your.javacard.package;

/**
 * A RIPEMD160 implementation for Java Card. <br>
 * This implementation doesn't have any static array initialisation, hence can be used inside library packages.
 */
public class Ripemd160 {
    /**
     * The size of the input of this implementation of RIPEMD160.
     */
    public static short MESSAGE_SIZE = 32;
    /**
     * The size of the output of RIPEMD160.
     */
    public static short DIGEST_SIZE = 20;

    /**
     * The RIPEMD160 digest method that takes in a message of {@link #MESSAGE_SIZE} bytes. <br>
     * The message digest size is {@link #DIGEST_SIZE} bytes.
     *
     * @param buffer the message buffer
     * @param bufferOffset offset of the message buffer
     * @param target the target buffer
     * @param targetOffset offset of the target buffer
     */
    public static void digest(byte[] buffer, short bufferOffset, byte[] target, short targetOffset) {
        short rotateLeftMask = 0,
        DECLARE_VARIABLE(rotateLeftCarry),
        DECLARE_VARIABLE(state_0, state_1, state_2, state_3, state_4),
        DECLARE_VARIABLE(schedule_0,  schedule_1,  schedule_2,  schedule_3,
                         schedule_4,  schedule_5,  schedule_6,  schedule_7,
                         schedule_8,  schedule_9,  schedule_10, schedule_11,
                         schedule_12, schedule_13, schedule_14, schedule_15),
        DECLARE_VARIABLE(aLeft,  bLeft,  cLeft,  dLeft,  eLeft,
                         aRight, bRight, cRight, dRight, eRight,
                         temp_0, temp_1), temp = 0;

        // Set initial state
        SET(state_0, 0x6745, 0x2301)
        SET(state_1, 0xEFCD, 0xAB89)
        SET(state_2, 0x98BA, 0xDCFE)
        SET(state_3, 0x1032, 0x5476)
        SET(state_4, 0xC3D2, 0xE1F0)

        // Create message schedule from message data
        define(`ITERATION',`
        // Read little-endian number from array into message schedule element
        SET(schedule_$1,
            (((buffer[(short) (bufferOffset+3+eval(($1)*4))] & 0xFF) << 8) | (buffer[(short) (bufferOffset+2+eval(($1)*4))] & 0xFF)),
            (((buffer[(short) (bufferOffset+1+eval(($1)*4))] & 0xFF) << 8) | (buffer[(short) (bufferOffset+eval(($1)*4))] & 0xFF)))
        ') dnl
        forloop(0, 7, `ITERATION') dnl
        undefine(`ITERATION')
        // Set schedule constants
        SET(schedule_8, 0, 128)
        SET(schedule_9, 0, 0)
        SET(schedule_10, 0, 0)
        SET(schedule_11, 0, 0)
        SET(schedule_12, 0, 0)
        SET(schedule_13, 0, 0)
        SET(schedule_14, 0, 256)
        SET(schedule_15, 0, 0)

        // Prepare round variables
        COPY(aLeft, state_0) COPY(aRight, state_0)
        COPY(bLeft, state_1) COPY(bRight, state_1)
        COPY(cLeft, state_2) COPY(cRight, state_2)
        COPY(dLeft, state_3) COPY(dRight, state_3)
        COPY(eLeft, state_4) COPY(eRight, state_4)

        // Run 80 hashing iterations
        for (byte i=0; i<80; i++) {
            // Left line
            switch (i/16) {
                case 0:
                    // temp_0 = bLeft ^ cLeft ^ dLeft
                    XOR(temp_0, bLeft, cLeft)
                    XOR(temp_0, temp_0, dLeft)

                    // In the range of i from 0 to 15: i = LEFT_MESSAGE_SCHEDULE[i] and ROUND_CONSTANT = 0x00000000
                    // temp_0 += ROUND_CONSTANT + schedule[LEFT_MESSAGE_SCHEDULE[i]]
                    GET_SCHEDULE(temp_1, i)
                    ADD(temp_0, temp_0, temp_1)
                    break;
                case 1:
                    // temp_0 = ((bLeft & cLeft) | (~bLeft & dLeft))
                    NOT(temp_1, bLeft)
                    AND(temp_1, temp_1, dLeft)
                    AND(temp_0, bLeft, cLeft)
                    OR(temp_0, temp_0, temp_1)

                    // In the range of i from 16 to 31: ROUND_CONSTANT = 0x5A827999
                    // temp_0 += ROUND_CONSTANT + schedule[LEFT_MESSAGE_SCHEDULE[i]]
                    ADD_VALUES(temp_0, temp_0A, temp_0B, 0x5A82, 0x7999)
                    GET_LEFT_MESSAGE_SCHEDULE(temp, i)
                    GET_SCHEDULE(temp_1, temp)
                    ADD(temp_0, temp_0, temp_1)
                    break;
                case 2:
                    // temp_0 = ((bLeft | ~cLeft) ^ dLeft)
                    NOT(temp_0, cLeft)
                    OR(temp_0, temp_0, bLeft)
                    XOR(temp_0, temp_0, dLeft)

                    // In the range of i from 32 to 47: ROUND_CONSTANT = 0x6ED9EBA1
                    // temp_0 += ROUND_CONSTANT + schedule[LEFT_MESSAGE_SCHEDULE[i]]
                    ADD_VALUES(temp_0, temp_0A, temp_0B, 0x6ED9, 0xEBA1)
                    GET_LEFT_MESSAGE_SCHEDULE(temp, i)
                    GET_SCHEDULE(temp_1, temp)
                    ADD(temp_0, temp_0, temp_1)
                    break;
                case 3:
                    // temp_0 = ((bLeft & dLeft) | (cLeft & ~dLeft))
                    NOT(temp_1, dLeft)
                    AND(temp_1, temp_1, cLeft)
                    AND(temp_0, bLeft, dLeft)
                    OR(temp_0, temp_0, temp_1)

                    // In the range of i from 48 to 63: ROUND_CONSTANT = 0x8F1BBCDC
                    // temp_0 += ROUND_CONSTANT + schedule[LEFT_MESSAGE_SCHEDULE[i]]
                    ADD_VALUES(temp_0, temp_0A, temp_0B, 0x8F1B, 0xBCDC)
                    GET_LEFT_MESSAGE_SCHEDULE(temp, i)
                    GET_SCHEDULE(temp_1, temp)
                    ADD(temp_0, temp_0, temp_1)
                    break;
                case 4:
                    // temp_0 = (bLeft ^ (cLeft | ~dLeft))
                    NOT(temp_0, dLeft)
                    OR(temp_0, temp_0, cLeft)
                    XOR(temp_0, temp_0, bLeft)

                    // In the range of i from 64 to 79: ROUND_CONSTANT = 0xA953FD4E
                    // temp_0 += ROUND_CONSTANT + schedule[LEFT_MESSAGE_SCHEDULE[i]]
                    ADD_VALUES(temp_0, temp_0A, temp_0B, 0xA953, 0xFD4E)
                    GET_LEFT_MESSAGE_SCHEDULE(temp, i)
                    GET_SCHEDULE(temp_1, temp)
                    ADD(temp_0, temp_0, temp_1)
                    break;
            }
            // temp_0 += aLeft
            ADD(temp_0, temp_0, aLeft)

            // temp_0 = leftRotate(temp_0, i)
            GET_LEFT_ROTATION_LEFT_LINE(temp, i)
            ROTATE_LEFT(temp_0, temp_0, temp)

            // temp_0 += eLeft
            ADD(temp_0, temp_0, eLeft)

            // aLeft = eLeft
            COPY(aLeft, eLeft)

            // eLeft = dLeft
            COPY(eLeft, dLeft)

            // dLeft = leftRotate(cLeft, 10)
            ROTATE_LEFT(dLeft, cLeft, 10)

            // cLeft = bLeft
            COPY(cLeft, bLeft)

            // bLeft = temp_0
            COPY(bLeft, temp_0)

            // Right line
            switch (i/16) {
                case 0:
                    // temp_0 = bRight ^ (cRight | ~dRight)
                    NOT(temp_0, dRight)
                    OR(temp_0, temp_0, cRight)
                    XOR(temp_0, temp_0, bRight)

                    // In the range of i from 0 to 15: ROUND_CONSTANT = 0x50A28BE6
                    // temp_0 += ROUND_CONSTANT
                    ADD_VALUES(temp_0, temp_0A, temp_0B, 0x50A2, 0x8BE6)
                    break;
                case 1:
                    // temp_0 = (bRight & dRight) | (cRight & ~dRight)
                    NOT(temp_1, dRight)
                    AND(temp_1, temp_1, cRight)
                    AND(temp_0, bRight, dRight)
                    OR(temp_0, temp_0, temp_1)

                    // In the range of i from 16 to 31: ROUND_CONSTANT = 0x5C4DD124
                    // temp_0 += ROUND_CONSTANT
                    ADD_VALUES(temp_0, temp_0A, temp_0B, 0x5C4D, 0xD124)
                    break;
                case 2:
                    // temp_0 = (bRight | ~cRight) ^ dRight
                    NOT(temp_0, cRight)
                    OR(temp_0, temp_0, bRight)
                    XOR(temp_0, temp_0, dRight)

                    // In the range of i from 32 to 47: ROUND_CONSTANT = 0x6D703EF3
                    // temp_0 += ROUND_CONSTANT
                    ADD_VALUES(temp_0, temp_0A, temp_0B, 0x6D70, 0x3EF3)
                    break;
                case 3:
                    // temp_0 = (bRight & cRight) | (~bRight & dRight)
                    NOT(temp_1, bRight)
                    AND(temp_1, temp_1, dRight)
                    AND(temp_0, bRight, cRight)
                    OR(temp_0, temp_0, temp_1)

                    // In the range of i from 48 to 63: ROUND_CONSTANT = 0x7A6D76E9
                    // temp_0 += ROUND_CONSTANT
                    ADD_VALUES(temp_0, temp_0A, temp_0B, 0x7A6D, 0x76E9)
                    break;
                case 4:
                    // temp_0 =  bRight ^ cRight ^ dRight
                    XOR(temp_0, bRight, cRight)
                    XOR(temp_0, temp_0, dRight)

                    // In the range of i from 64 to 79: ROUND_CONSTANT = 0x00000000
                    // temp_0 += ROUND_CONSTANT
                    break;
            }
            // temp_0 += schedule[RIGHT_MESSAGE_SCHEDULE[i]]
            GET_RIGHT_MESSAGE_SCHEDULE(temp, i)
            GET_SCHEDULE(temp_1, temp)
            ADD(temp_0, temp_0, temp_1)

            // temp_0 += aRight
            ADD(temp_0, temp_0, aRight)

            // temp_0 += leftRotate(temp_0, i)
            GET_LEFT_ROTATION_RIGHT_LINE(temp, i)
            ROTATE_LEFT(temp_0, temp_0, temp)

            // temp_0 += eRight
            ADD(temp_0, temp_0, eRight)

            // aRight += eRight
            COPY(aRight, eRight)

            // eRight = dRight
            COPY(eRight, dRight)

            // dRight = leftRotate(cRight, 10)
            ROTATE_LEFT(dRight, cRight, 10)

            // cRight = bRight
            COPY(cRight, bRight)

            // bRight = temp_0
            COPY(bRight, temp_0)
        }

        // Mix together round data and schedule data
        ADD(temp_0, state_1, cLeft) ADD(temp_0, temp_0, dRight)
        ADD(state_1, state_2, dLeft) ADD(state_1, state_1, eRight)
        ADD(state_2, state_3, eLeft) ADD(state_2, state_2, aRight)
        ADD(state_3, state_4, aLeft) ADD(state_3, state_3, bRight)
        ADD(state_4, state_0, bLeft) ADD(state_4, state_4, cRight)
        COPY(state_0, temp_0)

        // Write contents of state into target buffer with little-endian encoding
        COPY_TO_ARRAY(target, targetOffset, state_0)
        COPY_TO_ARRAY(target, targetOffset + 4, state_1)
        COPY_TO_ARRAY(target, targetOffset + 8, state_2)
        COPY_TO_ARRAY(target, targetOffset + 12, state_3)
        COPY_TO_ARRAY(target, targetOffset + 16, state_4)
    }
}
