package algorithms;

public class VerificacionDeParidad {

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

    public String verPar (String codigoBinario){
        int contadorPar=0;
        for (int i=0;i<codigoBinario.length();i++){
            if (codigoBinario.charAt(i)=='1')
                contadorPar++;
        }
        if (contadorPar%2!=0){
            return "1";
        }
        else {
            return "0";
        }
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
    public String tTBPar (String text){
        String binary = "";
        char[] chars = text.toCharArray();
        // convert each character to ascci code
        for (int i : chars) {
            String expression = numberToBinary(i);
            binary += verPar(expression);
        }
        return binary;
    }

    public String sended(String text) {
        String binaryChain = textToBinary(text);
        System.out.println("Datos enviados     : " + binaryChain);
        String c2 = tTBPar(text);
        System.out.println("Datos enviados VP  : "+c2);
        return c2;
    }

    public String messageToSend(String text) {
        String binaryChain = textToBinary(text);
        System.out.println("Datos Recibidos    : " + binaryChain);
        String c2 = tTBPar(text);
        System.out.println("Datos Recibidos VP : "+c2);
        return c2;
    }


    public boolean receivedMessage(String cadena1,String cadena2) {
        return tTBPar(cadena1).equals(tTBPar(cadena2));
    }


}
