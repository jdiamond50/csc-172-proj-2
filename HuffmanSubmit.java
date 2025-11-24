// Import any package as required
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class HuffmanSubmit implements Huffman {

	// Feel free to add more methods and variables as required.

	public void encode(String inputFile, String outputFile, String freqFile){
		try {
			createFreqTableFile(inputFile, freqFile);
			int[] freqs = createFreqArrayFromFile(freqFile);
			HuffmanNode root = createHuffmanTree(freqs);
			JBitSet[] dictionary = new JBitSet[256];
			createByteDictionary(dictionary, root, new JBitSet());
			// printByteDictionary(dictionary);
			writeToCompressedFile(inputFile, outputFile, dictionary);
		} catch (Exception e) { e.printStackTrace(); }
  }

  public void decode(String inputFile, String outputFile, String freqFile){
		try {
			int[] freqs = createFreqArrayFromFile(freqFile);
			HuffmanNode root = createHuffmanTree(freqs);
			writeToUncompressedFile(inputFile, outputFile, root, freqs[256]);
		} catch (Exception e) { e.printStackTrace(); }
  }

	private void createFreqTableFile(String inputFilename, String outputFilename) {
		int numBytes = 0;
    byte[] freqArr = new byte[256];
		BinaryIn input = new BinaryIn(inputFilename);
		int[] freqs = new int[256];
		while (! input.isEmpty()) {
			freqs[(int) input.readByte() + 128]++;
			numBytes++;
		}
		BinaryOut output = new BinaryOut(outputFilename);
		for (int i = 0; i < freqs.length; i++) {
			String entry = String.format("%" + 8 + "s", Integer.toBinaryString(i)).replace(" ", "0") + ":" + freqs[i] + "\n";
			output.write(entry);
		}
		output.write(Integer.toString(numBytes));
		output.flush();
  }

	private int[] createFreqArrayFromFile(String filename) throws IOException {
		File freqFile = new File(filename);
		Scanner sc = new Scanner(freqFile);
		int index = 0;
		int[] freqs = new int[257];
		while (sc.hasNextLine()) {
			String[] entry = sc.nextLine().split(":");
			if (index < 256) {
				freqs[index++] = Integer.parseInt(entry[1]);
			} else {
				freqs[index] = Integer.parseInt(entry[0]);
			}
		}
		return freqs;
	}

	private HuffmanNode createHuffmanTree(int[] freqs) {
		URHeap<HuffmanNode> heap = new URHeap<HuffmanNode>();
		for (int i = 0; i < freqs.length - 1; i++) {
			if (freqs[i] != 0) heap.insert(new HuffmanNode((byte) (i - 128), freqs[i]));
		}
		while (heap.size() > 1) {
			heap.insert(joinNodes(heap.deleteMin(), heap.deleteMin()));
		}
		return heap.deleteMin();
	}

	private HuffmanNode joinNodes(HuffmanNode node1, HuffmanNode node2) {
		HuffmanNode parent = new HuffmanNode(null, node1.getFreq() + node2.getFreq());
		parent.setLeft(node1);
		parent.setRight(node2);
		return parent;
	}

	private void createByteDictionary(JBitSet[] dictionary, HuffmanNode currNode, JBitSet currBitSet) {
		if (currNode.getVal() != null) {
			dictionary[(int) currNode.getVal() + 128] = currBitSet;
			return;
		}
		JBitSet modBitSet1 = new JBitSet(currBitSet.getData());
		JBitSet modBitSet2 = new JBitSet(currBitSet.getData());
		modBitSet1.append(false);
		modBitSet2.append(true);
		createByteDictionary(dictionary, currNode.left, modBitSet1);
		createByteDictionary(dictionary, currNode.right, modBitSet2);
	}

	private void printByteDictionary(JBitSet[] dictionary) {
		for (int i = 0; i < dictionary.length; i++) if (dictionary[i] != null) System.out.println(i + ": " + dictionary[i].toString());
	}

	// input file is uncompressed data; output file is compressed data
	private void writeToCompressedFile(String inputFilename, String outputFilename, JBitSet[] dictionary) {
		BinaryIn input = new BinaryIn(inputFilename);
		BinaryOut output = new BinaryOut(outputFilename);
		while (! input.isEmpty()) {
			ArrayList<Boolean> data = dictionary[(int) input.readByte() + 128].getData();
			for (Boolean bool : data) output.write(bool);
		}
		output.flush();
	}

	// input file is compressed data; output file is uncompressed data
	public void writeToUncompressedFile(String inputFilename, String outputFilename, HuffmanNode root, int numBytes) {
		BinaryIn input = new BinaryIn(inputFilename);
		BinaryOut output = new BinaryOut(outputFilename);
		HuffmanNode currNode = root;
		int bytesWritten = 0;;
		while (! input.isEmpty() && bytesWritten < numBytes) {
			boolean nextBit = input.readBoolean();
			if (nextBit) {
				currNode = currNode.getRight();
			} else {
				currNode = currNode.getLeft();
			}
			if (currNode.getVal() != null) {
				output.write(currNode.getVal());
				bytesWritten++;
				currNode = root;
			}
		}
		output.flush();
	}

	public static void main(String[] args) throws IOException {
		HuffmanSubmit huffman = new HuffmanSubmit();
		// test.txt
		huffman.encode("test.txt", "test.enc", "freqTest.txt");
		huffman.decode("test.enc", "test_dec.txt", "freqTest.txt");

		// alice.txt
		huffman.encode("alice30.txt", "alice30.enc", "freqAlice.txt");
		huffman.decode("alice30.enc", "alice30_dec.txt", "freqAlice.txt");

		// ur.jpg
		huffman.encode("ur.jpg", "ur.enc", "freqUR.txt");
		huffman.decode("ur.enc", "ur_dec.jpg", "freqUR.txt");

  	// After decoding, both ur.jpg and ur_dec.jpg should be the same.
  	// On linux and mac, you can use `diff' command to check if they are the same.
  }

  // --- helper classes ---



}
