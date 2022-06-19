package algorithms;

public class SumaDeVerificacion {
    private int nBits;

    public void setnBits(String message) {
        this.nBits = message.length() == 1 ? 2 : message.length() < 3 ? 8 : 16;
    }

    public String numberToBinary(int number) {
        int[] initialArray = new int[100];
        String binary = "";
        int index = 0;
        while (number != 1) {
            int residue = number % 2;
            initialArray[index] = residue;
            number = number / 2;
            index++;
            if (number == 1) {
                initialArray[index] = number;
            }
        }
        for (int i = index; i >= 0; i--) {
            binary += String.valueOf(initialArray[i]);
        }
        return binary;
    }

    public String textToBinary(String text) {
        String binary = "";
        char[] chars = text.toCharArray();
        // convert each character to ascci code
        for (int i : chars) {
            String expression = numberToBinary(i);
            binary += expression;
        }
        return binary;
    }

    public String getOnesComplement(String chain) {
        String complement = "";
        char[] chars = chain.toCharArray();
        for (char c : chars) {
            int item = (int) c - 48;
            int comp = item == 0 ? 1 : 0;
            complement += String.valueOf(comp);
        }
        return complement;
    }

    public String[] getKBlocksOfNBits(String chain, int nBits) {
        String[] array = new String[1000];
        int c = 0;
        for (int i = 0; i < chain.length(); i += nBits, c++) {
            String expression = i + nBits <= chain.length() ? chain.substring(i, i + nBits)
                    : chain.substring(i);
            array[c] = expression;
        }
        return array;
    }

    public int getLength(String[] chain) {
        int counter = 0;
        for (String element : chain) {
            counter = element != null ? counter + 1 : counter;
        }
        return counter;
    }

    public String completeWithZeros(String element, int sizeRequired) {
        String zeros = "";
        for (int i = 0; i < sizeRequired - element.length(); i++) {
            zeros += "0";
        }
        return zeros + element;
    }

    public String binarySum(String[] array) {
        int result = 0;
        for (int i = 0; i < getLength(array); i++) {
            try {
                int element = Integer.parseInt(array[i], 2);
                result += element;

            } catch (Exception e) {
                System.out.println("here the element is void: " + array[i]);
                System.out.println("ERROR: " + e.getMessage());
            }
        }
        return Integer.toString(result, 2);
    }

    public String addCarryToSum(String sum) {
        try {

            if (sum.length() < nBits) {
                return sum;
            }
            String carry = sum.substring(0, sum.length() - nBits);
            // System.out.println("The carry is" + carry);
            if (carry.equals(""))
                return sum;

            String sumWithOutCarry = sum.substring(sum.length() - nBits);
            String[] array = {carry, sumWithOutCarry};
            String newSum = binarySum(array);
            return completeWithZeros(newSum, nBits);
        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
            return "";
        }
    }

    public String checksum(String text) {
        String binaryChain = textToBinary(text);
        System.out.println(binaryChain);
        // k blocks of n bits
        String[] blocks = getKBlocksOfNBits(binaryChain, nBits);
        // get the binary sum
        String binarySum = binarySum(blocks);
        System.out.println("binarySum " + binarySum);
        // add the carry to the sum
        String sumPlusCarry = addCarryToSum(binarySum);
        System.out.println("sumPlusCarry " + sumPlusCarry);
        // 1's complement
        return getOnesComplement(sumPlusCarry);
    }

    public String messageToSend(String message, String checksum) {
        String binary = textToBinary(message);
        return checksum + binary;
    }

    public boolean areAllOnes(String binaryChain) {
        return !binaryChain.matches("(.*)0(.*)");
    }

    public boolean receivedMessage(String binaryWithCheksum) {
        String[] blocks = getKBlocksOfNBits(binaryWithCheksum, nBits);
        String binarySum = binarySum(blocks);
        String sumPlusCarry = addCarryToSum(binarySum);
        System.out.println("-------------------------");
        System.out.println("final sum " + sumPlusCarry);
        return areAllOnes(sumPlusCarry);
    }
}
