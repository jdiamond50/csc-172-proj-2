# CSC 172 Project 2 - Huffman Compression
Julien Diamond
## Summary
This lab was an implementation of the huffman compression algorithm. The program has three primary components:
1) Creation of a frequency file during encoding that contains the frequency of each byte in the uncompressed data
2) Compression of the data using a huffman tree created from the frequency file
3) Recovery of the original data by recreating the same huffman tree from the frequency file
## Running the Program
First, compile everything with `javac *.java`. Then `java HuffmanSubmit` will run the three test cases `test.txt`, `alice30.txt`, and `ur.jpg`. Three corresponding `.enc` compressed files will be created, along with three `_dec` decrypted files. Each can be compared with the original using `diff:` 
- `diff test.txt test_dec.txt`
- `diff alice30.txt alice30_dec.txt`
- `diff ur.jpg ur_dec.jpg`
## Files in Submission
- `alice30.txt`: test case 2 for the compression algorithm
- `BinaryIn.java`: given source code for reading from an input file
- `BinaryOut.java`: given source code for writing to an output file
- `Huffman.java`: given interface defining methods to be implemented by HuffmanSubmit
- `HuffmanNode.java`: class defining a node in a huffman tree
- `HuffmanSubmit.java`: class containing the `encode()` and `decode()` methods as well as the `main()` method with test cases
- `JBitSet.java`: class for easy manipulation of sequences of bits
- `makefile`: personal use for ease of testing
- `README.md`: this file
- `test.txt`: test case 1 for the compression algorithm
- `UR_Heap.class`: class file for `URHeap.java`
- `ur.jpg`: test case 3 for the compression algorithm
- `URHeap.java`: implementation of a min heap for use in creating a huffman tree
