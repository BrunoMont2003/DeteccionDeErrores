package algorithms;

public class VerificacionRedundanciaLongitudinal {
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
    public String VRL (String text){
        String binaryChain = textToBinary(text);
        String vrl="";
        int v[]={0,0,0,0,0,0,0};
        int suma=0;
        int tam = binaryChain.length();
        int r=tam/7;
        int i=0;
        while (i<tam){
            v[0]+=binaryChain.charAt(i)-48;
            v[1]+=binaryChain.charAt(i+1)-48;
            v[2]+=binaryChain.charAt(i+2)-48;
            v[3]+=binaryChain.charAt(i+3)-48;
            v[4]+=binaryChain.charAt(i+4)-48;
            v[5]+=binaryChain.charAt(i+5)-48;
            v[6]+=binaryChain.charAt(i+6)-48;
            i=i+7;
        }
        for (i=0;i<7;i++){
            v[i]=v[i]%2;
            vrl+=String.valueOf(v[i]);
        }
        return vrl;
    }

    public String sended(String text) {
        String binaryChain = textToBinary(text);
        System.out.println("Datos enviados     : " + binaryChain);
        String c2 = VRL(text);
        System.out.println("Datos enviados VP  : "+c2);
        return c2;
    }

    public String messageToSend(String text) {
        String binaryChain = textToBinary(text);
        System.out.println("Datos Recibidos    : " + binaryChain);
        String c2 = VRL(text);
        System.out.println("Datos Recibidos VP : "+c2);
        return c2;
    }


    public boolean receivedMessage(String cadena1,String cadena2) {
        return VRL(cadena1).equals(VRL(cadena2));
    }


}
