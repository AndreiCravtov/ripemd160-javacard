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
     * @param buffer       the message buffer
     * @param bufferOffset offset of the message buffer
     * @param target       the target buffer
     * @param targetOffset offset of the target buffer
     */
    public static void digest(byte[] buffer, short bufferOffset, byte[] target, short targetOffset) {
        short rotateLeftMask = 0,
                rotateLeftCarryA, rotateLeftCarryB,
                state_0A, state_0B, state_1A, state_1B, state_2A, state_2B, state_3A, state_3B, state_4A, state_4B,
                schedule_0A, schedule_0B, schedule_1A, schedule_1B, schedule_2A, schedule_2B, schedule_3A, schedule_3B, schedule_4A, schedule_4B, schedule_5A, schedule_5B, schedule_6A, schedule_6B, schedule_7A, schedule_7B, schedule_8A, schedule_8B, schedule_9A, schedule_9B, schedule_10A, schedule_10B, schedule_11A, schedule_11B, schedule_12A, schedule_12B, schedule_13A, schedule_13B, schedule_14A, schedule_14B, schedule_15A, schedule_15B,
                aLeftA, aLeftB, bLeftA, bLeftB, cLeftA, cLeftB, dLeftA, dLeftB, eLeftA, eLeftB, aRightA, aRightB, bRightA, bRightB, cRightA, cRightB, dRightA, dRightB, eRightA, eRightB, temp_0A = 0, temp_0B = 0, temp_1A = 0, temp_1B = 0, temp = 0;

        // Set initial state
        state_0A = (short) 0x6745;
        state_0B = (short) 0x2301;
        state_1A = (short) 0xEFCD;
        state_1B = (short) 0xAB89;
        state_2A = (short) 0x98BA;
        state_2B = (short) 0xDCFE;
        state_3A = (short) 0x1032;
        state_3B = (short) 0x5476;
        state_4A = (short) 0xC3D2;
        state_4B = (short) 0xE1F0;

        // Create message schedule from message data
        // Read in little-endian numbers from array into message schedule element
        schedule_0A = (short) (((buffer[(short) (bufferOffset + 3)] & 0xFF) << 8) | (buffer[(short) (bufferOffset + 2)] & 0xFF));
        schedule_0B = (short) (((buffer[(short) (bufferOffset + 1)] & 0xFF) << 8) | (buffer[bufferOffset] & 0xFF));
        schedule_1A = (short) (((buffer[(short) (bufferOffset + 7)] & 0xFF) << 8) | (buffer[(short) (bufferOffset + 6)] & 0xFF));
        schedule_1B = (short) (((buffer[(short) (bufferOffset + 5)] & 0xFF) << 8) | (buffer[(short) (bufferOffset + 4)] & 0xFF));
        schedule_2A = (short) (((buffer[(short) (bufferOffset + 11)] & 0xFF) << 8) | (buffer[(short) (bufferOffset + 10)] & 0xFF));
        schedule_2B = (short) (((buffer[(short) (bufferOffset + 9)] & 0xFF) << 8) | (buffer[(short) (bufferOffset + 8)] & 0xFF));
        schedule_3A = (short) (((buffer[(short) (bufferOffset + 15)] & 0xFF) << 8) | (buffer[(short) (bufferOffset + 14)] & 0xFF));
        schedule_3B = (short) (((buffer[(short) (bufferOffset + 13)] & 0xFF) << 8) | (buffer[(short) (bufferOffset + 12)] & 0xFF));
        schedule_4A = (short) (((buffer[(short) (bufferOffset + 19)] & 0xFF) << 8) | (buffer[(short) (bufferOffset + 18)] & 0xFF));
        schedule_4B = (short) (((buffer[(short) (bufferOffset + 17)] & 0xFF) << 8) | (buffer[(short) (bufferOffset + 16)] & 0xFF));
        schedule_5A = (short) (((buffer[(short) (bufferOffset + 23)] & 0xFF) << 8) | (buffer[(short) (bufferOffset + 22)] & 0xFF));
        schedule_5B = (short) (((buffer[(short) (bufferOffset + 21)] & 0xFF) << 8) | (buffer[(short) (bufferOffset + 20)] & 0xFF));
        schedule_6A = (short) (((buffer[(short) (bufferOffset + 27)] & 0xFF) << 8) | (buffer[(short) (bufferOffset + 26)] & 0xFF));
        schedule_6B = (short) (((buffer[(short) (bufferOffset + 25)] & 0xFF) << 8) | (buffer[(short) (bufferOffset + 24)] & 0xFF));
        schedule_7A = (short) (((buffer[(short) (bufferOffset + 31)] & 0xFF) << 8) | (buffer[(short) (bufferOffset + 30)] & 0xFF));
        schedule_7B = (short) (((buffer[(short) (bufferOffset + 29)] & 0xFF) << 8) | (buffer[(short) (bufferOffset + 28)] & 0xFF));

        // Set schedule constants
        schedule_8A = (short) 0;
        schedule_8B = (short) 128;
        schedule_9A = (short) 0;
        schedule_9B = (short) 0;
        schedule_10A = (short) 0;
        schedule_10B = (short) 0;
        schedule_11A = (short) 0;
        schedule_11B = (short) 0;
        schedule_12A = (short) 0;
        schedule_12B = (short) 0;
        schedule_13A = (short) 0;
        schedule_13B = (short) 0;
        schedule_14A = (short) 0;
        schedule_14B = (short) 256;
        schedule_15A = (short) 0;
        schedule_15B = (short) 0;

        // Prepare round variables
        aLeftA = state_0A;
        aLeftB = state_0B;
        aRightA = state_0A;
        aRightB = state_0B;
        bLeftA = state_1A;
        bLeftB = state_1B;
        bRightA = state_1A;
        bRightB = state_1B;
        cLeftA = state_2A;
        cLeftB = state_2B;
        cRightA = state_2A;
        cRightB = state_2B;
        dLeftA = state_3A;
        dLeftB = state_3B;
        dRightA = state_3A;
        dRightB = state_3B;
        eLeftA = state_4A;
        eLeftB = state_4B;
        eRightA = state_4A;
        eRightB = state_4B;

        // Run 80 hashing iterations
        for (byte i = 0; i < 80; i++) {
            // Left line
            switch (i / 16) {
                case 0:
                    // temp_0 = bLeft ^ cLeft ^ dLeft
                    temp_0A = (short) (bLeftA ^ cLeftA);
                    temp_0B = (short) (bLeftB ^ cLeftB);
                    temp_0A = (short) (temp_0A ^ dLeftA);
                    temp_0B = (short) (temp_0B ^ dLeftB);

                    // In the range of i from 0 to 15: i = LEFT_MESSAGE_SCHEDULE[i] and ROUND_CONSTANT = 0x00000000
                    // temp_0 += ROUND_CONSTANT + schedule[LEFT_MESSAGE_SCHEDULE[i]]
                    switch (i) {
                        case 0:
                            temp_1A = schedule_0A;
                            temp_1B = schedule_0B;
                            break;
                        case 1:
                            temp_1A = schedule_1A;
                            temp_1B = schedule_1B;
                            break;
                        case 2:
                            temp_1A = schedule_2A;
                            temp_1B = schedule_2B;
                            break;
                        case 3:
                            temp_1A = schedule_3A;
                            temp_1B = schedule_3B;
                            break;
                        case 4:
                            temp_1A = schedule_4A;
                            temp_1B = schedule_4B;
                            break;
                        case 5:
                            temp_1A = schedule_5A;
                            temp_1B = schedule_5B;
                            break;
                        case 6:
                            temp_1A = schedule_6A;
                            temp_1B = schedule_6B;
                            break;
                        case 7:
                            temp_1A = schedule_7A;
                            temp_1B = schedule_7B;
                            break;
                        case 8:
                            temp_1A = schedule_8A;
                            temp_1B = schedule_8B;
                            break;
                        case 9:
                            temp_1A = schedule_9A;
                            temp_1B = schedule_9B;
                            break;
                        case 10:
                            temp_1A = schedule_10A;
                            temp_1B = schedule_10B;
                            break;
                        case 11:
                            temp_1A = schedule_11A;
                            temp_1B = schedule_11B;
                            break;
                        case 12:
                            temp_1A = schedule_12A;
                            temp_1B = schedule_12B;
                            break;
                        case 13:
                            temp_1A = schedule_13A;
                            temp_1B = schedule_13B;
                            break;
                        case 14:
                            temp_1A = schedule_14A;
                            temp_1B = schedule_14B;
                            break;
                        case 15:
                            temp_1A = schedule_15A;
                            temp_1B = schedule_15B;
                            break;
                    }

                    temp_0A = (short) (temp_0A + temp_1A + (short) (temp_0B < 0 && temp_1B < 0 || temp_0B < 0 && temp_1B >= (short) (-temp_0B) || temp_1B < 0 && temp_0B >= (short) (-temp_1B) ? 1 : 0));
                    temp_0B = (short) (temp_0B + temp_1B);
                    break;
                case 1:
                    // temp_0 = ((bLeft & cLeft) | (~bLeft & dLeft))
                    temp_1A = (short) (~bLeftA);
                    temp_1B = (short) (~bLeftB);
                    temp_1A = (short) (temp_1A & dLeftA);
                    temp_1B = (short) (temp_1B & dLeftB);
                    temp_0A = (short) (bLeftA & cLeftA);
                    temp_0B = (short) (bLeftB & cLeftB);
                    temp_0A = (short) (temp_0A | temp_1A);
                    temp_0B = (short) (temp_0B | temp_1B);

                    // In the range of i from 16 to 31: ROUND_CONSTANT = 0x5A827999
                    // temp_0 += ROUND_CONSTANT + schedule[LEFT_MESSAGE_SCHEDULE[i]]
                    temp_0A = (short) (temp_0A + (short) 0x5A82 + (short) (temp_0B < 0 && (short) 0x7999 >= (short) (-temp_0B) ? 1 : 0));
                    temp_0B = (short) (temp_0B + (short) 0x7999);

                    switch (i) {
                        case 25:
                        case 42:
                        case 52:
                        case 65:
                            temp = 0;
                            break;
                        case 19:
                        case 39:
                        case 48:
                        case 73:
                            temp = 1;
                            break;
                        case 28:
                        case 40:
                        case 63:
                        case 70:
                            temp = 2;
                            break;
                        case 16:
                        case 58:
                        case 68:
                        case 41:
                            temp = 7;
                            break;
                        case 17:
                            temp = 4;
                            break;
                        case 18:
                            temp = 13;
                            break;
                        case 20:
                            temp = 10;
                            break;
                        case 21:
                            temp = 6;
                            break;
                        case 22:
                            temp = 15;
                            break;
                        case 23:
                            temp = 3;
                            break;
                        case 24:
                            temp = 12;
                            break;
                        case 26:
                            temp = 9;
                            break;
                        case 27:
                            temp = 5;
                            break;
                        case 29:
                            temp = 14;
                            break;
                        case 30:
                            temp = 11;
                            break;
                        case 31:
                            temp = 8;
                            break;
                        case 32:
                            temp = 3;
                            break;
                        case 33:
                            temp = 10;
                            break;
                        case 34:
                            temp = 14;
                            break;
                        case 35:
                            temp = 4;
                            break;
                        case 36:
                            temp = 9;
                            break;
                        case 37:
                            temp = 15;
                            break;
                        case 38:
                            temp = 8;
                            break;
                        case 43:
                            temp = 6;
                            break;
                        case 44:
                            temp = 13;
                            break;
                        case 45:
                            temp = 11;
                            break;
                        case 46:
                            temp = 5;
                            break;
                        case 47:
                            temp = 12;
                            break;
                        case 49:
                            temp = 9;
                            break;
                        case 50:
                            temp = 11;
                            break;
                        case 51:
                            temp = 10;
                            break;
                        case 53:
                            temp = 8;
                            break;
                        case 54:
                            temp = 12;
                            break;
                        case 55:
                            temp = 4;
                            break;
                        case 56:
                            temp = 13;
                            break;
                        case 57:
                            temp = 3;
                            break;
                        case 59:
                            temp = 15;
                            break;
                        case 60:
                            temp = 14;
                            break;
                        case 61:
                            temp = 5;
                            break;
                        case 62:
                            temp = 6;
                            break;
                        case 64:
                            temp = 4;
                            break;
                        case 66:
                            temp = 5;
                            break;
                        case 67:
                            temp = 9;
                            break;
                        case 69:
                            temp = 12;
                            break;
                        case 71:
                            temp = 10;
                            break;
                        case 72:
                            temp = 14;
                            break;
                        case 74:
                            temp = 3;
                            break;
                        case 75:
                            temp = 8;
                            break;
                        case 76:
                            temp = 11;
                            break;
                        case 77:
                            temp = 6;
                            break;
                        case 78:
                            temp = 15;
                            break;
                        case 79:
                            temp = 13;
                            break;
                    }
                    switch (temp) {
                        case 0:
                            temp_1A = schedule_0A;
                            temp_1B = schedule_0B;
                            break;
                        case 1:
                            temp_1A = schedule_1A;
                            temp_1B = schedule_1B;
                            break;
                        case 2:
                            temp_1A = schedule_2A;
                            temp_1B = schedule_2B;
                            break;
                        case 3:
                            temp_1A = schedule_3A;
                            temp_1B = schedule_3B;
                            break;
                        case 4:
                            temp_1A = schedule_4A;
                            temp_1B = schedule_4B;
                            break;
                        case 5:
                            temp_1A = schedule_5A;
                            temp_1B = schedule_5B;
                            break;
                        case 6:
                            temp_1A = schedule_6A;
                            temp_1B = schedule_6B;
                            break;
                        case 7:
                            temp_1A = schedule_7A;
                            temp_1B = schedule_7B;
                            break;
                        case 8:
                            temp_1A = schedule_8A;
                            temp_1B = schedule_8B;
                            break;
                        case 9:
                            temp_1A = schedule_9A;
                            temp_1B = schedule_9B;
                            break;
                        case 10:
                            temp_1A = schedule_10A;
                            temp_1B = schedule_10B;
                            break;
                        case 11:
                            temp_1A = schedule_11A;
                            temp_1B = schedule_11B;
                            break;
                        case 12:
                            temp_1A = schedule_12A;
                            temp_1B = schedule_12B;
                            break;
                        case 13:
                            temp_1A = schedule_13A;
                            temp_1B = schedule_13B;
                            break;
                        case 14:
                            temp_1A = schedule_14A;
                            temp_1B = schedule_14B;
                            break;
                        case 15:
                            temp_1A = schedule_15A;
                            temp_1B = schedule_15B;
                            break;
                    }

                    temp_0A = (short) (temp_0A + temp_1A + (short) (temp_0B < 0 && temp_1B < 0 || temp_0B < 0 && temp_1B >= (short) (-temp_0B) || temp_1B < 0 && temp_0B >= (short) (-temp_1B) ? 1 : 0));
                    temp_0B = (short) (temp_0B + temp_1B);
                    break;
                case 2:
                    // temp_0 = ((bLeft | ~cLeft) ^ dLeft)
                    temp_0A = (short) (~cLeftA);
                    temp_0B = (short) (~cLeftB);
                    temp_0A = (short) (temp_0A | bLeftA);
                    temp_0B = (short) (temp_0B | bLeftB);
                    temp_0A = (short) (temp_0A ^ dLeftA);
                    temp_0B = (short) (temp_0B ^ dLeftB);

                    // In the range of i from 32 to 47: ROUND_CONSTANT = 0x6ED9EBA1
                    // temp_0 += ROUND_CONSTANT + schedule[LEFT_MESSAGE_SCHEDULE[i]]
                    temp_0A = (short) (temp_0A + (short) 0x6ED9 + (short) (temp_0B < 0 || temp_0B < 0 && (short) 0xEBA1 >= (short) (-temp_0B) || temp_0B >= (short) (-0xEBA1) ? 1 : 0));
                    temp_0B = (short) (temp_0B + (short) 0xEBA1);

                    switch (i) {
                        case 25:
                        case 42:
                        case 65:
                        case 52:
                            temp = 0;
                            break;
                        case 19:
                        case 39:
                        case 48:
                        case 73:
                            temp = 1;
                            break;
                        case 28:
                        case 40:
                        case 63:
                        case 70:
                            temp = 2;
                            break;
                        case 16:
                        case 41:
                        case 58:
                        case 68:
                            temp = 7;
                            break;
                        case 17:
                            temp = 4;
                            break;
                        case 18:
                            temp = 13;
                            break;
                        case 20:
                            temp = 10;
                            break;
                        case 21:
                            temp = 6;
                            break;
                        case 22:
                            temp = 15;
                            break;
                        case 23:
                            temp = 3;
                            break;
                        case 24:
                            temp = 12;
                            break;
                        case 26:
                            temp = 9;
                            break;
                        case 27:
                            temp = 5;
                            break;
                        case 29:
                            temp = 14;
                            break;
                        case 30:
                            temp = 11;
                            break;
                        case 31:
                            temp = 8;
                            break;
                        case 32:
                            temp = 3;
                            break;
                        case 33:
                            temp = 10;
                            break;
                        case 34:
                            temp = 14;
                            break;
                        case 35:
                            temp = 4;
                            break;
                        case 36:
                            temp = 9;
                            break;
                        case 37:
                            temp = 15;
                            break;
                        case 38:
                            temp = 8;
                            break;
                        case 43:
                            temp = 6;
                            break;
                        case 44:
                            temp = 13;
                            break;
                        case 45:
                            temp = 11;
                            break;
                        case 46:
                            temp = 5;
                            break;
                        case 47:
                            temp = 12;
                            break;
                        case 49:
                            temp = 9;
                            break;
                        case 50:
                            temp = 11;
                            break;
                        case 51:
                            temp = 10;
                            break;
                        case 53:
                            temp = 8;
                            break;
                        case 54:
                            temp = 12;
                            break;
                        case 55:
                            temp = 4;
                            break;
                        case 56:
                            temp = 13;
                            break;
                        case 57:
                            temp = 3;
                            break;
                        case 59:
                            temp = 15;
                            break;
                        case 60:
                            temp = 14;
                            break;
                        case 61:
                            temp = 5;
                            break;
                        case 62:
                            temp = 6;
                            break;
                        case 64:
                            temp = 4;
                            break;
                        case 66:
                            temp = 5;
                            break;
                        case 67:
                            temp = 9;
                            break;
                        case 69:
                            temp = 12;
                            break;
                        case 71:
                            temp = 10;
                            break;
                        case 72:
                            temp = 14;
                            break;
                        case 74:
                            temp = 3;
                            break;
                        case 75:
                            temp = 8;
                            break;
                        case 76:
                            temp = 11;
                            break;
                        case 77:
                            temp = 6;
                            break;
                        case 78:
                            temp = 15;
                            break;
                        case 79:
                            temp = 13;
                            break;
                    }
                    switch (temp) {
                        case 0:
                            temp_1A = schedule_0A;
                            temp_1B = schedule_0B;
                            break;
                        case 1:
                            temp_1A = schedule_1A;
                            temp_1B = schedule_1B;
                            break;
                        case 2:
                            temp_1A = schedule_2A;
                            temp_1B = schedule_2B;
                            break;
                        case 3:
                            temp_1A = schedule_3A;
                            temp_1B = schedule_3B;
                            break;
                        case 4:
                            temp_1A = schedule_4A;
                            temp_1B = schedule_4B;
                            break;
                        case 5:
                            temp_1A = schedule_5A;
                            temp_1B = schedule_5B;
                            break;
                        case 6:
                            temp_1A = schedule_6A;
                            temp_1B = schedule_6B;
                            break;
                        case 7:
                            temp_1A = schedule_7A;
                            temp_1B = schedule_7B;
                            break;
                        case 8:
                            temp_1A = schedule_8A;
                            temp_1B = schedule_8B;
                            break;
                        case 9:
                            temp_1A = schedule_9A;
                            temp_1B = schedule_9B;
                            break;
                        case 10:
                            temp_1A = schedule_10A;
                            temp_1B = schedule_10B;
                            break;
                        case 11:
                            temp_1A = schedule_11A;
                            temp_1B = schedule_11B;
                            break;
                        case 12:
                            temp_1A = schedule_12A;
                            temp_1B = schedule_12B;
                            break;
                        case 13:
                            temp_1A = schedule_13A;
                            temp_1B = schedule_13B;
                            break;
                        case 14:
                            temp_1A = schedule_14A;
                            temp_1B = schedule_14B;
                            break;
                        case 15:
                            temp_1A = schedule_15A;
                            temp_1B = schedule_15B;
                            break;
                    }

                    temp_0A = (short) (temp_0A + temp_1A + (short) (temp_0B < 0 && temp_1B < 0 || temp_0B < 0 && temp_1B >= (short) (-temp_0B) || temp_1B < 0 && temp_0B >= (short) (-temp_1B) ? 1 : 0));
                    temp_0B = (short) (temp_0B + temp_1B);
                    break;
                case 3:
                    // temp_0 = ((bLeft & dLeft) | (cLeft & ~dLeft))
                    temp_1A = (short) (~dLeftA);
                    temp_1B = (short) (~dLeftB);
                    temp_1A = (short) (temp_1A & cLeftA);
                    temp_1B = (short) (temp_1B & cLeftB);
                    temp_0A = (short) (bLeftA & dLeftA);
                    temp_0B = (short) (bLeftB & dLeftB);
                    temp_0A = (short) (temp_0A | temp_1A);
                    temp_0B = (short) (temp_0B | temp_1B);

                    // In the range of i from 48 to 63: ROUND_CONSTANT = 0x8F1BBCDC
                    // temp_0 += ROUND_CONSTANT + schedule[LEFT_MESSAGE_SCHEDULE[i]]
                    temp_0A = (short) (temp_0A + (short) 0x8F1B + (short) (temp_0B < 0 || temp_0B < 0 && (short) 0xBCDC >= (short) (-temp_0B) || temp_0B >= (short) (-0xBCDC) ? 1 : 0));
                    temp_0B = (short) (temp_0B + (short) 0xBCDC);

                    switch (i) {
                        case 25:
                        case 42:
                        case 52:
                        case 65:
                            temp = 0;
                            break;
                        case 19:
                        case 39:
                        case 48:
                        case 73:
                            temp = 1;
                            break;
                        case 28:
                        case 40:
                        case 63:
                        case 70:
                            temp = 2;
                            break;
                        case 16:
                        case 41:
                        case 58:
                        case 68:
                            temp = 7;
                            break;
                        case 17:
                            temp = 4;
                            break;
                        case 18:
                            temp = 13;
                            break;
                        case 20:
                            temp = 10;
                            break;
                        case 21:
                            temp = 6;
                            break;
                        case 22:
                            temp = 15;
                            break;
                        case 23:
                            temp = 3;
                            break;
                        case 24:
                            temp = 12;
                            break;
                        case 26:
                            temp = 9;
                            break;
                        case 27:
                            temp = 5;
                            break;
                        case 29:
                            temp = 14;
                            break;
                        case 30:
                            temp = 11;
                            break;
                        case 31:
                            temp = 8;
                            break;
                        case 32:
                            temp = 3;
                            break;
                        case 33:
                            temp = 10;
                            break;
                        case 34:
                            temp = 14;
                            break;
                        case 35:
                            temp = 4;
                            break;
                        case 36:
                            temp = 9;
                            break;
                        case 37:
                            temp = 15;
                            break;
                        case 38:
                            temp = 8;
                            break;
                        case 43:
                            temp = 6;
                            break;
                        case 44:
                            temp = 13;
                            break;
                        case 45:
                            temp = 11;
                            break;
                        case 46:
                            temp = 5;
                            break;
                        case 47:
                            temp = 12;
                            break;
                        case 49:
                            temp = 9;
                            break;
                        case 50:
                            temp = 11;
                            break;
                        case 51:
                            temp = 10;
                            break;
                        case 53:
                            temp = 8;
                            break;
                        case 54:
                            temp = 12;
                            break;
                        case 55:
                            temp = 4;
                            break;
                        case 56:
                            temp = 13;
                            break;
                        case 57:
                            temp = 3;
                            break;
                        case 59:
                            temp = 15;
                            break;
                        case 60:
                            temp = 14;
                            break;
                        case 61:
                            temp = 5;
                            break;
                        case 62:
                            temp = 6;
                            break;
                        case 64:
                            temp = 4;
                            break;
                        case 66:
                            temp = 5;
                            break;
                        case 67:
                            temp = 9;
                            break;
                        case 69:
                            temp = 12;
                            break;
                        case 71:
                            temp = 10;
                            break;
                        case 72:
                            temp = 14;
                            break;
                        case 74:
                            temp = 3;
                            break;
                        case 75:
                            temp = 8;
                            break;
                        case 76:
                            temp = 11;
                            break;
                        case 77:
                            temp = 6;
                            break;
                        case 78:
                            temp = 15;
                            break;
                        case 79:
                            temp = 13;
                            break;
                    }
                    switch (temp) {
                        case 0:
                            temp_1A = schedule_0A;
                            temp_1B = schedule_0B;
                            break;
                        case 1:
                            temp_1A = schedule_1A;
                            temp_1B = schedule_1B;
                            break;
                        case 2:
                            temp_1A = schedule_2A;
                            temp_1B = schedule_2B;
                            break;
                        case 3:
                            temp_1A = schedule_3A;
                            temp_1B = schedule_3B;
                            break;
                        case 4:
                            temp_1A = schedule_4A;
                            temp_1B = schedule_4B;
                            break;
                        case 5:
                            temp_1A = schedule_5A;
                            temp_1B = schedule_5B;
                            break;
                        case 6:
                            temp_1A = schedule_6A;
                            temp_1B = schedule_6B;
                            break;
                        case 7:
                            temp_1A = schedule_7A;
                            temp_1B = schedule_7B;
                            break;
                        case 8:
                            temp_1A = schedule_8A;
                            temp_1B = schedule_8B;
                            break;
                        case 9:
                            temp_1A = schedule_9A;
                            temp_1B = schedule_9B;
                            break;
                        case 10:
                            temp_1A = schedule_10A;
                            temp_1B = schedule_10B;
                            break;
                        case 11:
                            temp_1A = schedule_11A;
                            temp_1B = schedule_11B;
                            break;
                        case 12:
                            temp_1A = schedule_12A;
                            temp_1B = schedule_12B;
                            break;
                        case 13:
                            temp_1A = schedule_13A;
                            temp_1B = schedule_13B;
                            break;
                        case 14:
                            temp_1A = schedule_14A;
                            temp_1B = schedule_14B;
                            break;
                        case 15:
                            temp_1A = schedule_15A;
                            temp_1B = schedule_15B;
                            break;
                    }

                    temp_0A = (short) (temp_0A + temp_1A + (short) (temp_0B < 0 && temp_1B < 0 || temp_0B < 0 && temp_1B >= (short) (-temp_0B) || temp_1B < 0 && temp_0B >= (short) (-temp_1B) ? 1 : 0));
                    temp_0B = (short) (temp_0B + temp_1B);
                    break;
                case 4:
                    // temp_0 = (bLeft ^ (cLeft | ~dLeft))
                    temp_0A = (short) (~dLeftA);
                    temp_0B = (short) (~dLeftB);
                    temp_0A = (short) (temp_0A | cLeftA);
                    temp_0B = (short) (temp_0B | cLeftB);
                    temp_0A = (short) (temp_0A ^ bLeftA);
                    temp_0B = (short) (temp_0B ^ bLeftB);

                    // In the range of i from 64 to 79: ROUND_CONSTANT = 0xA953FD4E
                    // temp_0 += ROUND_CONSTANT + schedule[LEFT_MESSAGE_SCHEDULE[i]]
                    temp_0A = (short) (temp_0A + (short) 0xA953 + (short) (temp_0B < 0 || temp_0B < 0 && (short) 0xFD4E >= (short) (-temp_0B) || temp_0B >= (short) (-0xFD4E) ? 1 : 0));
                    temp_0B = (short) (temp_0B + (short) 0xFD4E);

                    switch (i) {
                        case 25:
                        case 42:
                        case 52:
                        case 65:
                            temp = 0;
                            break;
                        case 19:
                        case 39:
                        case 48:
                        case 73:
                            temp = 1;
                            break;
                        case 28:
                        case 40:
                        case 63:
                        case 70:
                            temp = 2;
                            break;
                        case 16:
                        case 58:
                        case 41:
                        case 68:
                            temp = 7;
                            break;
                        case 17:
                            temp = 4;
                            break;
                        case 18:
                            temp = 13;
                            break;
                        case 20:
                            temp = 10;
                            break;
                        case 21:
                            temp = 6;
                            break;
                        case 22:
                            temp = 15;
                            break;
                        case 23:
                            temp = 3;
                            break;
                        case 24:
                            temp = 12;
                            break;
                        case 26:
                            temp = 9;
                            break;
                        case 27:
                            temp = 5;
                            break;
                        case 29:
                            temp = 14;
                            break;
                        case 30:
                            temp = 11;
                            break;
                        case 31:
                            temp = 8;
                            break;
                        case 32:
                            temp = 3;
                            break;
                        case 33:
                            temp = 10;
                            break;
                        case 34:
                            temp = 14;
                            break;
                        case 35:
                            temp = 4;
                            break;
                        case 36:
                            temp = 9;
                            break;
                        case 37:
                            temp = 15;
                            break;
                        case 38:
                            temp = 8;
                            break;
                        case 43:
                            temp = 6;
                            break;
                        case 44:
                            temp = 13;
                            break;
                        case 45:
                            temp = 11;
                            break;
                        case 46:
                            temp = 5;
                            break;
                        case 47:
                            temp = 12;
                            break;
                        case 49:
                            temp = 9;
                            break;
                        case 50:
                            temp = 11;
                            break;
                        case 51:
                            temp = 10;
                            break;
                        case 53:
                            temp = 8;
                            break;
                        case 54:
                            temp = 12;
                            break;
                        case 55:
                            temp = 4;
                            break;
                        case 56:
                            temp = 13;
                            break;
                        case 57:
                            temp = 3;
                            break;
                        case 59:
                            temp = 15;
                            break;
                        case 60:
                            temp = 14;
                            break;
                        case 61:
                            temp = 5;
                            break;
                        case 62:
                            temp = 6;
                            break;
                        case 64:
                            temp = 4;
                            break;
                        case 66:
                            temp = 5;
                            break;
                        case 67:
                            temp = 9;
                            break;
                        case 69:
                            temp = 12;
                            break;
                        case 71:
                            temp = 10;
                            break;
                        case 72:
                            temp = 14;
                            break;
                        case 74:
                            temp = 3;
                            break;
                        case 75:
                            temp = 8;
                            break;
                        case 76:
                            temp = 11;
                            break;
                        case 77:
                            temp = 6;
                            break;
                        case 78:
                            temp = 15;
                            break;
                        case 79:
                            temp = 13;
                            break;
                    }
                    switch (temp) {
                        case 0:
                            temp_1A = schedule_0A;
                            temp_1B = schedule_0B;
                            break;
                        case 1:
                            temp_1A = schedule_1A;
                            temp_1B = schedule_1B;
                            break;
                        case 2:
                            temp_1A = schedule_2A;
                            temp_1B = schedule_2B;
                            break;
                        case 3:
                            temp_1A = schedule_3A;
                            temp_1B = schedule_3B;
                            break;
                        case 4:
                            temp_1A = schedule_4A;
                            temp_1B = schedule_4B;
                            break;
                        case 5:
                            temp_1A = schedule_5A;
                            temp_1B = schedule_5B;
                            break;
                        case 6:
                            temp_1A = schedule_6A;
                            temp_1B = schedule_6B;
                            break;
                        case 7:
                            temp_1A = schedule_7A;
                            temp_1B = schedule_7B;
                            break;
                        case 8:
                            temp_1A = schedule_8A;
                            temp_1B = schedule_8B;
                            break;
                        case 9:
                            temp_1A = schedule_9A;
                            temp_1B = schedule_9B;
                            break;
                        case 10:
                            temp_1A = schedule_10A;
                            temp_1B = schedule_10B;
                            break;
                        case 11:
                            temp_1A = schedule_11A;
                            temp_1B = schedule_11B;
                            break;
                        case 12:
                            temp_1A = schedule_12A;
                            temp_1B = schedule_12B;
                            break;
                        case 13:
                            temp_1A = schedule_13A;
                            temp_1B = schedule_13B;
                            break;
                        case 14:
                            temp_1A = schedule_14A;
                            temp_1B = schedule_14B;
                            break;
                        case 15:
                            temp_1A = schedule_15A;
                            temp_1B = schedule_15B;
                            break;
                    }

                    temp_0A = (short) (temp_0A + temp_1A + (short) (temp_0B < 0 && temp_1B < 0 || temp_0B < 0 && temp_1B >= (short) (-temp_0B) || temp_1B < 0 && temp_0B >= (short) (-temp_1B) ? 1 : 0));
                    temp_0B = (short) (temp_0B + temp_1B);
                    break;
            }
            // temp_0 += aLeft
            temp_0A = (short) (temp_0A + aLeftA + (short) (temp_0B < 0 && aLeftB < 0 || temp_0B < 0 && aLeftB >= (short) (-temp_0B) || aLeftB < 0 && temp_0B >= (short) (-aLeftB) ? 1 : 0));
            temp_0B = (short) (temp_0B + aLeftB);


            // temp_0 = leftRotate(temp_0, i)
            switch (i) {
                case 0:
                case 8:
                case 20:
                case 28:
                case 32:
                case 48:
                case 67:
                case 76:
                    temp = 11;
                    break;
                case 1:
                case 10:
                case 36:
                case 40:
                case 50:
                case 52:
                case 57:
                case 75:
                    temp = 14;
                    break;
                case 2:
                case 11:
                case 23:
                case 26:
                case 39:
                case 51:
                case 53:
                case 65:
                    temp = 15;
                    break;
                case 3:
                case 25:
                case 31:
                case 45:
                case 49:
                case 63:
                case 71:
                case 73:
                    temp = 12;
                    break;
                case 4:
                    temp = 5;
                    break;
                case 5:
                    temp = 8;
                    break;
                case 6:
                    temp = 7;
                    break;
                case 7:
                    temp = 9;
                    break;
                case 9:
                    temp = 13;
                    break;
                case 12:
                    temp = 6;
                    break;
                case 13:
                    temp = 7;
                    break;
                case 14:
                    temp = 9;
                    break;
                case 15:
                    temp = 8;
                    break;
                case 16:
                    temp = 7;
                    break;
                case 17:
                    temp = 6;
                    break;
                case 18:
                    temp = 8;
                    break;
                case 19:
                    temp = 13;
                    break;
                case 21:
                    temp = 9;
                    break;
                case 22:
                    temp = 7;
                    break;
                case 24:
                    temp = 7;
                    break;
                case 27:
                    temp = 9;
                    break;
                case 29:
                    temp = 7;
                    break;
                case 30:
                    temp = 13;
                    break;
                case 33:
                    temp = 13;
                    break;
                case 34:
                    temp = 6;
                    break;
                case 35:
                    temp = 7;
                    break;
                case 37:
                    temp = 9;
                    break;
                case 38:
                    temp = 13;
                    break;
                case 41:
                    temp = 8;
                    break;
                case 42:
                    temp = 13;
                    break;
                case 43:
                    temp = 6;
                    break;
                case 44:
                    temp = 5;
                    break;
                case 46:
                    temp = 7;
                    break;
                case 47:
                    temp = 5;
                    break;
                case 54:
                    temp = 9;
                    break;
                case 55:
                    temp = 8;
                    break;
                case 56:
                    temp = 9;
                    break;
                case 58:
                    temp = 5;
                    break;
                case 59:
                    temp = 6;
                    break;
                case 60:
                    temp = 8;
                    break;
                case 61:
                    temp = 6;
                    break;
                case 62:
                    temp = 5;
                    break;
                case 64:
                    temp = 9;
                    break;
                case 66:
                    temp = 5;
                    break;
                case 68:
                    temp = 6;
                    break;
                case 69:
                    temp = 8;
                    break;
                case 70:
                    temp = 13;
                    break;
                case 72:
                    temp = 5;
                    break;
                case 74:
                    temp = 13;
                    break;
                case 77:
                    temp = 8;
                    break;
                case 78:
                    temp = 5;
                    break;
                case 79:
                    temp = 6;
                    break;
            }

            switch (temp) {
                case 0:
                    rotateLeftMask = 0b0000000000000000;
                    break;
                case 1:
                    rotateLeftMask = (short) 0b0000000000000001;
                    break;
                case 2:
                    rotateLeftMask = (short) 0b0000000000000011;
                    break;
                case 3:
                    rotateLeftMask = (short) 0b0000000000000111;
                    break;
                case 4:
                    rotateLeftMask = (short) 0b0000000000001111;
                    break;
                case 5:
                    rotateLeftMask = (short) 0b0000000000011111;
                    break;
                case 6:
                    rotateLeftMask = (short) 0b0000000000111111;
                    break;
                case 7:
                    rotateLeftMask = (short) 0b0000000001111111;
                    break;
                case 8:
                    rotateLeftMask = (short) 0b0000000011111111;
                    break;
                case 9:
                    rotateLeftMask = (short) 0b0000000111111111;
                    break;
                case 10:
                    rotateLeftMask = (short) 0b0000001111111111;
                    break;
                case 11:
                    rotateLeftMask = (short) 0b0000011111111111;
                    break;
                case 12:
                    rotateLeftMask = (short) 0b0000111111111111;
                    break;
                case 13:
                    rotateLeftMask = (short) 0b0001111111111111;
                    break;
                case 14:
                    rotateLeftMask = (short) 0b0011111111111111;
                    break;
                case 15:
                    rotateLeftMask = (short) 0b0111111111111111;
                    break;
            }
            rotateLeftCarryA = (short) ((temp_0A >>> (16 - temp)) & rotateLeftMask);
            rotateLeftCarryB = (short) ((temp_0B >>> (16 - temp)) & rotateLeftMask);
            temp_0A = (short) ((temp_0A << temp) | rotateLeftCarryB);
            temp_0B = (short) ((temp_0B << temp) | rotateLeftCarryA);


            // temp_0 += eLeft
            temp_0A = (short) (temp_0A + eLeftA + (short) (temp_0B < 0 && eLeftB < 0 || temp_0B < 0 && eLeftB >= (short) (-temp_0B) || eLeftB < 0 && temp_0B >= (short) (-eLeftB) ? 1 : 0));
            temp_0B = (short) (temp_0B + eLeftB);

            // aLeft = eLeft
            aLeftA = eLeftA;
            aLeftB = eLeftB;

            // eLeft = dLeft
            eLeftA = dLeftA;
            eLeftB = dLeftB;

            // dLeft = leftRotate(cLeft, 10)
            rotateLeftMask = (short) 0b0000001111111111;
            rotateLeftCarryA = (short) ((cLeftA >>> (16 - 10)) & rotateLeftMask);
            rotateLeftCarryB = (short) ((cLeftB >>> (16 - 10)) & rotateLeftMask);
            dLeftA = (short) ((cLeftA << 10) | rotateLeftCarryB);
            dLeftB = (short) ((cLeftB << 10) | rotateLeftCarryA);

            // cLeft = bLeft
            cLeftA = bLeftA;
            cLeftB = bLeftB;

            // bLeft = temp_0
            bLeftA = temp_0A;
            bLeftB = temp_0B;

            // Right line
            switch (i / 16) {
                case 0:
                    // temp_0 = bRight ^ (cRight | ~dRight)
                    temp_0A = (short) (~dRightA);
                    temp_0B = (short) (~dRightB);
                    temp_0A = (short) (temp_0A | cRightA);
                    temp_0B = (short) (temp_0B | cRightB);
                    temp_0A = (short) (temp_0A ^ bRightA);
                    temp_0B = (short) (temp_0B ^ bRightB);

                    // In the range of i from 0 to 15: ROUND_CONSTANT = 0x50A28BE6
                    // temp_0 += ROUND_CONSTANT
                    temp_0A = (short) (temp_0A + (short) 0x50A2 + (short) (temp_0B < 0 || temp_0B < 0 && (short) 0x8BE6 >= (short) (-temp_0B) || temp_0B >= (short) (-0x8BE6) ? 1 : 0));
                    temp_0B = (short) (temp_0B + (short) 0x8BE6);

                    break;
                case 1:
                    // temp_0 = (bRight & dRight) | (cRight & ~dRight)
                    temp_1A = (short) (~dRightA);
                    temp_1B = (short) (~dRightB);
                    temp_1A = (short) (temp_1A & cRightA);
                    temp_1B = (short) (temp_1B & cRightB);
                    temp_0A = (short) (bRightA & dRightA);
                    temp_0B = (short) (bRightB & dRightB);
                    temp_0A = (short) (temp_0A | temp_1A);
                    temp_0B = (short) (temp_0B | temp_1B);

                    // In the range of i from 16 to 31: ROUND_CONSTANT = 0x5C4DD124
                    // temp_0 += ROUND_CONSTANT
                    temp_0A = (short) (temp_0A + (short) 0x5C4D + (short) (temp_0B < 0 || temp_0B < 0 && (short) 0xD124 >= (short) (-temp_0B) || temp_0B >= (short) (-0xD124) ? 1 : 0));
                    temp_0B = (short) (temp_0B + (short) 0xD124);

                    break;
                case 2:
                    // temp_0 = (bRight | ~cRight) ^ dRight
                    temp_0A = (short) (~cRightA);
                    temp_0B = (short) (~cRightB);
                    temp_0A = (short) (temp_0A | bRightA);
                    temp_0B = (short) (temp_0B | bRightB);
                    temp_0A = (short) (temp_0A ^ dRightA);
                    temp_0B = (short) (temp_0B ^ dRightB);

                    // In the range of i from 32 to 47: ROUND_CONSTANT = 0x6D703EF3
                    // temp_0 += ROUND_CONSTANT
                    temp_0A = (short) (temp_0A + (short) 0x6D70 + (short) (temp_0B < 0 && (short) 0x3EF3 >= (short) (-temp_0B) ? 1 : 0));
                    temp_0B = (short) (temp_0B + (short) 0x3EF3);

                    break;
                case 3:
                    // temp_0 = (bRight & cRight) | (~bRight & dRight)
                    temp_1A = (short) (~bRightA);
                    temp_1B = (short) (~bRightB);
                    temp_1A = (short) (temp_1A & dRightA);
                    temp_1B = (short) (temp_1B & dRightB);
                    temp_0A = (short) (bRightA & cRightA);
                    temp_0B = (short) (bRightB & cRightB);
                    temp_0A = (short) (temp_0A | temp_1A);
                    temp_0B = (short) (temp_0B | temp_1B);

                    // In the range of i from 48 to 63: ROUND_CONSTANT = 0x7A6D76E9
                    // temp_0 += ROUND_CONSTANT
                    temp_0A = (short) (temp_0A + (short) 0x7A6D + (short) (temp_0B < 0 && (short) 0x76E9 >= (short) (-temp_0B) ? 1 : 0));
                    temp_0B = (short) (temp_0B + (short) 0x76E9);

                    break;
                case 4:
                    // temp_0 =  bRight ^ cRight ^ dRight
                    temp_0A = (short) (bRightA ^ cRightA);
                    temp_0B = (short) (bRightB ^ cRightB);
                    temp_0A = (short) (temp_0A ^ dRightA);
                    temp_0B = (short) (temp_0B ^ dRightB);

                    // In the range of i from 64 to 79: ROUND_CONSTANT = 0x00000000
                    // temp_0 += ROUND_CONSTANT
                    break;
            }
            // temp_0 += schedule[RIGHT_MESSAGE_SCHEDULE[i]]
            switch (i) {
                case 0:
                case 22:
                case 33:
                case 56:
                case 69:
                    temp = 5;
                    break;
                case 1:
                case 24:
                case 37:
                case 63:
                case 75:
                    temp = 14;
                    break;
                case 2:
                case 19:
                case 36:
                case 61:
                case 71:
                    temp = 7;
                    break;
                case 3:
                    temp = 0;
                    break;
                case 4:
                    temp = 9;
                    break;
                case 5:
                    temp = 2;
                    break;
                case 6:
                    temp = 11;
                    break;
                case 7:
                    temp = 4;
                    break;
                case 8:
                    temp = 13;
                    break;
                case 9:
                    temp = 6;
                    break;
                case 10:
                    temp = 15;
                    break;
                case 11:
                    temp = 8;
                    break;
                case 12:
                    temp = 1;
                    break;
                case 13:
                    temp = 10;
                    break;
                case 14:
                    temp = 3;
                    break;
                case 15:
                    temp = 12;
                    break;
                case 16:
                    temp = 6;
                    break;
                case 17:
                    temp = 11;
                    break;
                case 18:
                    temp = 3;
                    break;
                case 20:
                    temp = 0;
                    break;
                case 21:
                    temp = 13;
                    break;
                case 23:
                    temp = 10;
                    break;
                case 25:
                    temp = 15;
                    break;
                case 26:
                    temp = 8;
                    break;
                case 27:
                    temp = 12;
                    break;
                case 28:
                    temp = 4;
                    break;
                case 29:
                    temp = 9;
                    break;
                case 30:
                    temp = 1;
                    break;
                case 31:
                    temp = 2;
                    break;
                case 32:
                    temp = 15;
                    break;
                case 34:
                    temp = 1;
                    break;
                case 35:
                    temp = 3;
                    break;
                case 38:
                    temp = 6;
                    break;
                case 39:
                    temp = 9;
                    break;
                case 40:
                    temp = 11;
                    break;
                case 41:
                    temp = 8;
                    break;
                case 42:
                    temp = 12;
                    break;
                case 43:
                    temp = 2;
                    break;
                case 44:
                    temp = 10;
                    break;
                case 45:
                    temp = 0;
                    break;
                case 46:
                    temp = 4;
                    break;
                case 47:
                    temp = 13;
                    break;
                case 48:
                    temp = 8;
                    break;
                case 49:
                    temp = 6;
                    break;
                case 50:
                    temp = 4;
                    break;
                case 51:
                    temp = 1;
                    break;
                case 52:
                    temp = 3;
                    break;
                case 53:
                    temp = 11;
                    break;
                case 54:
                    temp = 15;
                    break;
                case 55:
                    temp = 0;
                    break;
                case 57:
                    temp = 12;
                    break;
                case 58:
                    temp = 2;
                    break;
                case 59:
                    temp = 13;
                    break;
                case 60:
                    temp = 9;
                    break;
                case 62:
                    temp = 10;
                    break;
                case 64:
                    temp = 12;
                    break;
                case 65:
                    temp = 15;
                    break;
                case 66:
                    temp = 10;
                    break;
                case 67:
                    temp = 4;
                    break;
                case 68:
                    temp = 1;
                    break;
                case 70:
                    temp = 8;
                    break;
                case 72:
                    temp = 6;
                    break;
                case 73:
                    temp = 2;
                    break;
                case 74:
                    temp = 13;
                    break;
                case 76:
                    temp = 0;
                    break;
                case 77:
                    temp = 3;
                    break;
                case 78:
                    temp = 9;
                    break;
                case 79:
                    temp = 11;
                    break;
            }
            switch (temp) {
                case 0:
                    temp_1A = schedule_0A;
                    temp_1B = schedule_0B;
                    break;
                case 1:
                    temp_1A = schedule_1A;
                    temp_1B = schedule_1B;
                    break;
                case 2:
                    temp_1A = schedule_2A;
                    temp_1B = schedule_2B;
                    break;
                case 3:
                    temp_1A = schedule_3A;
                    temp_1B = schedule_3B;
                    break;
                case 4:
                    temp_1A = schedule_4A;
                    temp_1B = schedule_4B;
                    break;
                case 5:
                    temp_1A = schedule_5A;
                    temp_1B = schedule_5B;
                    break;
                case 6:
                    temp_1A = schedule_6A;
                    temp_1B = schedule_6B;
                    break;
                case 7:
                    temp_1A = schedule_7A;
                    temp_1B = schedule_7B;
                    break;
                case 8:
                    temp_1A = schedule_8A;
                    temp_1B = schedule_8B;
                    break;
                case 9:
                    temp_1A = schedule_9A;
                    temp_1B = schedule_9B;
                    break;
                case 10:
                    temp_1A = schedule_10A;
                    temp_1B = schedule_10B;
                    break;
                case 11:
                    temp_1A = schedule_11A;
                    temp_1B = schedule_11B;
                    break;
                case 12:
                    temp_1A = schedule_12A;
                    temp_1B = schedule_12B;
                    break;
                case 13:
                    temp_1A = schedule_13A;
                    temp_1B = schedule_13B;
                    break;
                case 14:
                    temp_1A = schedule_14A;
                    temp_1B = schedule_14B;
                    break;
                case 15:
                    temp_1A = schedule_15A;
                    temp_1B = schedule_15B;
                    break;
            }
            temp_0A = (short) (temp_0A + temp_1A + (short) (temp_0B < 0 && temp_1B < 0 || temp_0B < 0 && temp_1B >= (short) (-temp_0B) || temp_1B < 0 && temp_0B >= (short) (-temp_1B) ? 1 : 0));
            temp_0B = (short) (temp_0B + temp_1B);


            // temp_0 += aRight
            temp_0A = (short) (temp_0A + aRightA + (short) (temp_0B < 0 && aRightB < 0 || temp_0B < 0 && aRightB >= (short) (-temp_0B) || aRightB < 0 && temp_0B >= (short) (-aRightB) ? 1 : 0));
            temp_0B = (short) (temp_0B + aRightB);

            // temp_0 += leftRotate(temp_0, i)
            switch (i) {
                case 0:
                case 10:
                case 21:
                case 36:
                case 50:
                case 63:
                case 64:
                case 72:
                    temp = 8;
                    break;
                case 1:
                case 2:
                case 22:
                case 16:
                case 32:
                case 57:
                case 59:
                case 67:
                    temp = 9;
                    break;
                case 3:
                case 11:
                case 23:
                case 31:
                case 35:
                case 51:
                case 78:
                case 79:
                    temp = 11;
                    break;
                case 4:
                case 17:
                case 30:
                case 41:
                case 44:
                case 45:
                    temp = 13;
                    break;
                case 5:
                    temp = 15;
                    break;
                case 6:
                    temp = 15;
                    break;
                case 7:
                    temp = 5;
                    break;
                case 8:
                    temp = 7;
                    break;
                case 9:
                    temp = 7;
                    break;
                case 12:
                    temp = 14;
                    break;
                case 13:
                    temp = 14;
                    break;
                case 14:
                    temp = 12;
                    break;
                case 15:
                    temp = 6;
                    break;
                case 18:
                    temp = 15;
                    break;
                case 19:
                    temp = 7;
                    break;
                case 20:
                    temp = 12;
                    break;
                case 24:
                    temp = 7;
                    break;
                case 25:
                    temp = 7;
                    break;
                case 26:
                    temp = 12;
                    break;
                case 27:
                    temp = 7;
                    break;
                case 28:
                    temp = 6;
                    break;
                case 29:
                    temp = 15;
                    break;
                case 33:
                    temp = 7;
                    break;
                case 34:
                    temp = 15;
                    break;
                case 37:
                    temp = 6;
                    break;
                case 38:
                    temp = 6;
                    break;
                case 39:
                    temp = 14;
                    break;
                case 40:
                    temp = 12;
                    break;
                case 42:
                    temp = 5;
                    break;
                case 43:
                    temp = 14;
                    break;
                case 46:
                    temp = 7;
                    break;
                case 47:
                    temp = 5;
                    break;
                case 48:
                    temp = 15;
                    break;
                case 49:
                    temp = 5;
                    break;
                case 52:
                    temp = 14;
                    break;
                case 53:
                    temp = 14;
                    break;
                case 54:
                    temp = 6;
                    break;
                case 55:
                    temp = 14;
                    break;
                case 56:
                    temp = 6;
                    break;
                case 58:
                    temp = 12;
                    break;
                case 60:
                    temp = 12;
                    break;
                case 61:
                    temp = 5;
                    break;
                case 62:
                    temp = 15;
                    break;
                case 65:
                    temp = 5;
                    break;
                case 66:
                    temp = 12;
                    break;
                case 68:
                    temp = 12;
                    break;
                case 69:
                    temp = 5;
                    break;
                case 70:
                    temp = 14;
                    break;
                case 71:
                    temp = 6;
                    break;
                case 73:
                    temp = 13;
                    break;
                case 74:
                    temp = 6;
                    break;
                case 75:
                    temp = 5;
                    break;
                case 76:
                    temp = 15;
                    break;
                case 77:
                    temp = 13;
                    break;
            }

            switch (temp) {
                case 0:
                    rotateLeftMask = 0b0000000000000000;
                    break;
                case 1:
                    rotateLeftMask = (short) 0b0000000000000001;
                    break;
                case 2:
                    rotateLeftMask = (short) 0b0000000000000011;
                    break;
                case 3:
                    rotateLeftMask = (short) 0b0000000000000111;
                    break;
                case 4:
                    rotateLeftMask = (short) 0b0000000000001111;
                    break;
                case 5:
                    rotateLeftMask = (short) 0b0000000000011111;
                    break;
                case 6:
                    rotateLeftMask = (short) 0b0000000000111111;
                    break;
                case 7:
                    rotateLeftMask = (short) 0b0000000001111111;
                    break;
                case 8:
                    rotateLeftMask = (short) 0b0000000011111111;
                    break;
                case 9:
                    rotateLeftMask = (short) 0b0000000111111111;
                    break;
                case 10:
                    rotateLeftMask = (short) 0b0000001111111111;
                    break;
                case 11:
                    rotateLeftMask = (short) 0b0000011111111111;
                    break;
                case 12:
                    rotateLeftMask = (short) 0b0000111111111111;
                    break;
                case 13:
                    rotateLeftMask = (short) 0b0001111111111111;
                    break;
                case 14:
                    rotateLeftMask = (short) 0b0011111111111111;
                    break;
                case 15:
                    rotateLeftMask = (short) 0b0111111111111111;
                    break;
            }
            rotateLeftCarryA = (short) ((temp_0A >>> (16 - temp)) & rotateLeftMask);
            rotateLeftCarryB = (short) ((temp_0B >>> (16 - temp)) & rotateLeftMask);
            temp_0A = (short) ((temp_0A << temp) | rotateLeftCarryB);
            temp_0B = (short) ((temp_0B << temp) | rotateLeftCarryA);


            // temp_0 += eRight
            temp_0A = (short) (temp_0A + eRightA + (short) (temp_0B < 0 && eRightB < 0 || temp_0B < 0 && eRightB >= (short) (-temp_0B) || eRightB < 0 && temp_0B >= (short) (-eRightB) ? 1 : 0));
            temp_0B = (short) (temp_0B + eRightB);

            // aRight += eRight
            aRightA = eRightA;
            aRightB = eRightB;

            // eRight = dRight
            eRightA = dRightA;
            eRightB = dRightB;

            // dRight = leftRotate(cRight, 10)
            rotateLeftMask = (short) 0b0000001111111111;
            rotateLeftCarryA = (short) ((cRightA >>> (16 - 10)) & rotateLeftMask);
            rotateLeftCarryB = (short) ((cRightB >>> (16 - 10)) & rotateLeftMask);
            dRightA = (short) ((cRightA << 10) | rotateLeftCarryB);
            dRightB = (short) ((cRightB << 10) | rotateLeftCarryA);

            // cRight = bRight
            cRightA = bRightA;
            cRightB = bRightB;

            // bRight = temp_0
            bRightA = temp_0A;
            bRightB = temp_0B;
        }

        // Mix together round data and schedule data
        temp_0A = (short) (state_1A + cLeftA + (short) (state_1B < 0 && cLeftB < 0 || state_1B < 0 && cLeftB >= (short) (-state_1B) || cLeftB < 0 && state_1B >= (short) (-cLeftB) ? 1 : 0));
        temp_0B = (short) (state_1B + cLeftB);
        temp_0A = (short) (temp_0A + dRightA + (short) (temp_0B < 0 && dRightB < 0 || temp_0B < 0 && dRightB >= (short) (-temp_0B) || dRightB < 0 && temp_0B >= (short) (-dRightB) ? 1 : 0));
        temp_0B = (short) (temp_0B + dRightB);
        state_1A = (short) (state_2A + dLeftA + (short) (state_2B < 0 && dLeftB < 0 || state_2B < 0 && dLeftB >= (short) (-state_2B) || dLeftB < 0 && state_2B >= (short) (-dLeftB) ? 1 : 0));
        state_1B = (short) (state_2B + dLeftB);
        state_1A = (short) (state_1A + eRightA + (short) (state_1B < 0 && eRightB < 0 || state_1B < 0 && eRightB >= (short) (-state_1B) || eRightB < 0 && state_1B >= (short) (-eRightB) ? 1 : 0));
        state_1B = (short) (state_1B + eRightB);
        state_2A = (short) (state_3A + eLeftA + (short) (state_3B < 0 && eLeftB < 0 || state_3B < 0 && eLeftB >= (short) (-state_3B) || eLeftB < 0 && state_3B >= (short) (-eLeftB) ? 1 : 0));
        state_2B = (short) (state_3B + eLeftB);
        state_2A = (short) (state_2A + aRightA + (short) (state_2B < 0 && aRightB < 0 || state_2B < 0 && aRightB >= (short) (-state_2B) || aRightB < 0 && state_2B >= (short) (-aRightB) ? 1 : 0));
        state_2B = (short) (state_2B + aRightB);
        state_3A = (short) (state_4A + aLeftA + (short) (state_4B < 0 && aLeftB < 0 || state_4B < 0 && aLeftB >= (short) (-state_4B) || aLeftB < 0 && state_4B >= (short) (-aLeftB) ? 1 : 0));
        state_3B = (short) (state_4B + aLeftB);
        state_3A = (short) (state_3A + bRightA + (short) (state_3B < 0 && bRightB < 0 || state_3B < 0 && bRightB >= (short) (-state_3B) || bRightB < 0 && state_3B >= (short) (-bRightB) ? 1 : 0));
        state_3B = (short) (state_3B + bRightB);
        state_4A = (short) (state_0A + bLeftA + (short) (state_0B < 0 && bLeftB < 0 || state_0B < 0 && bLeftB >= (short) (-state_0B) || bLeftB < 0 && state_0B >= (short) (-bLeftB) ? 1 : 0));
        state_4B = (short) (state_0B + bLeftB);
        state_4A = (short) (state_4A + cRightA + (short) (state_4B < 0 && cRightB < 0 || state_4B < 0 && cRightB >= (short) (-state_4B) || cRightB < 0 && state_4B >= (short) (-cRightB) ? 1 : 0));
        state_4B = (short) (state_4B + cRightB);
        state_0A = temp_0A;
        state_0B = temp_0B;

        // Write contents of state into target buffer with little-endian encoding
        target[targetOffset] = (byte) (state_0B);
        target[(short) (targetOffset + 1)] = (byte) (state_0B >>> 8);
        target[(short) (targetOffset + 2)] = (byte) (state_0A);
        target[(short) (targetOffset + 3)] = (byte) (state_0A >>> 8);
        target[(short) (targetOffset + 4)] = (byte) (state_1B);
        target[(short) (targetOffset + 5)] = (byte) (state_1B >>> 8);
        target[(short) (targetOffset + 6)] = (byte) (state_1A);
        target[(short) (targetOffset + 7)] = (byte) (state_1A >>> 8);
        target[(short) (targetOffset + 8)] = (byte) (state_2B);
        target[(short) (targetOffset + 9)] = (byte) (state_2B >>> 8);
        target[(short) (targetOffset + 10)] = (byte) (state_2A);
        target[(short) (targetOffset + 11)] = (byte) (state_2A >>> 8);
        target[(short) (targetOffset + 12)] = (byte) (state_3B);
        target[(short) (targetOffset + 13)] = (byte) (state_3B >>> 8);
        target[(short) (targetOffset + 14)] = (byte) (state_3A);
        target[(short) (targetOffset + 15)] = (byte) (state_3A >>> 8);
        target[(short) (targetOffset + 16)] = (byte) (state_4B);
        target[(short) (targetOffset + 17)] = (byte) (state_4B >>> 8);
        target[(short) (targetOffset + 18)] = (byte) (state_4A);
        target[(short) (targetOffset + 19)] = (byte) (state_4A >>> 8);

    }
}
