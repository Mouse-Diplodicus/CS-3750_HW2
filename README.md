# CS-3750_HW2
by Matthew Williams

## Description

The programs in this directory use the TEA (Tiny Encryption Algorithm)
to perform encryption and decryption on a plaintext provided by the user in
hexadecimal as two 32-bit blocks. To do this the program uses a key provided
by the user in hexadecimal as four 32-bit blocks to perform two rounds of TEA
encryption or decryption on the given text.

## Running the Program

To start the programs use the following commands:

> java Encrypt.java  
> java Decrypt.java  

Once started the program will ask for the inputs in hexadecimal. The keys 
and text should be provided as 8 hexadecimal characters representing 32-bits
(e.g. 1234ABCD or FFFF0000).

Additionally, if you want to save the programs outputs to a file the following
commands can be used:

> java Encrypt.java | tee tst_Encryption.txt  
> java Decrypt.java | tee tst_Decryption.txt  

## Compiling the Program

If the program needs to be compiled use the following commands:

> javac Encrypt.java  
> javac Decrypt.java  