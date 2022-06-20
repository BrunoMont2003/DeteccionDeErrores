package algorithms;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;

public class VerificacionDeRedundanciaCiclica {
    public int decimal(BitSet bits){
        //Método para convertir los datos del mensaje en binario a un número en base 10
        int decimal = 0;
        for (int i = 0; i < bits.length(); i++) {
            if (bits.get(i)) {
                decimal |= (1 << i);
            }
        }
        return decimal;
    }

    public int residuo (BitSet bits){
        int P = decimal(bits); // Mensaje en decimal para realizar la operación de cálculo del residuo
        int G = 8; // Divisor de 4 bits 1001
        int residuo;
        residuo = P%G;
        return residuo;
    }

    public int residuoEnviado (String mensajeEnviado){
        byte [] datos = mensajeEnviado.getBytes(StandardCharsets.UTF_8);
        BitSet bitSet = BitSet.valueOf(datos); //Mensaje en binario
        int ax = decimal(bitSet);
        System.out.println("Bits enviados: "+Integer.toBinaryString(ax));
        int residuo;
        residuo = residuo(bitSet);
        System.out.println("Residuo de la division "+Integer.toBinaryString(ax)+"/"+"1000: "+residuo);
        return residuo;
    }

    public int residuoRecibido (String mensajeRecibido){
        byte [] datos = mensajeRecibido.getBytes(StandardCharsets.UTF_8);
        BitSet bitSet = BitSet.valueOf(datos); //Mensaje en binario
        int ax = decimal(bitSet);
        System.out.println("Bits recibidos: "+Integer.toBinaryString(ax));
        int residuo;
        residuo = residuo(bitSet);
        System.out.println("Residuo de la division "+Integer.toBinaryString(ax)+"/"+"1000: "+residuo);
        System.out.println("-----------------------------------------------------");
        return residuo;
    }

    public boolean receivedMessage(int r1, int r2){
        return (r1 == r2) ? true : false;
    }
}
