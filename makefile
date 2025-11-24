run: compile
	@java HuffmanSubmit
compile: HuffmanSubmit.class Huffman.class HuffmanNode.class JBitSet.class BinaryIn.class BinaryOut.class URHeap.class
HuffmanSubmit.class: HuffmanSubmit.java Huffman.class
	@javac HuffmanSubmit.java
Huffman.class: Huffman.java
	@javac Huffman.java
HuffmanNode.class: HuffmanNode.java
	@javac HuffmanNode.java
JBitSet.class: JBitSet.java
	@javac JBitSet.java
URHeap.class: URHeap.java
	@javac URHeap.java
BinaryIn.class: BinaryIn.java
	@javac BinaryIn.java
BinaryOut.class: BinaryOut.java
	@javac BinaryOut.java
clean:
	@rm -rf HuffmanSubmit.class
	@rm -rf Huffman.class
	@rm -rf BinaryIn.class
	@rm -rf BinaryOut.class
	@rm -rf HuffmanNode.class
	@rm -rf URHeap.class
	@rm -rf JBitSet.class
	@rm -rf freq.*
	@rm -rf *.enc
	@rm -rf *_dec*
