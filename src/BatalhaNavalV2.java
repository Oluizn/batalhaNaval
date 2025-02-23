import java.util.InputMismatchException;
import java.util.Scanner;

class match{
    static int height, width, ship, maxShip, board[][];

    public void boardSet (){
        Scanner scanner = new Scanner(System.in);
        boolean allSet = false;
        while(!allSet){
            try{
                System.out.println("Digite a altura do tabuleiro (min 3)");
                height = scanner.nextInt();
                System.out.println("Digite a largura do tabuleiro (min 3)");
                width = scanner.nextInt();
                if (height >= 3 && width >= 3)
                    allSet = true;
                else
                    System.out.println("\nInforme uma altura e largura mínima de 3 casas!!\n");
                
            } catch(InputMismatchException erro){
                System.out.println("\n\nDigite um valor numérico\n\n");
            }
        }
        scanner.close();
    }
    
    public void boardSize (){
        board = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void maxShipOnBoard(){
        maxShip = (height * width)/3;
    }

    public void numberOfShips(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nInforme a quantidade de navios de 1 a " + maxShip);
        ship = scanner.nextInt();
        if (ship > maxShip)
            ship = maxShip;
        scanner.close();
    }
}

class player{

}

public class BatalhaNavalV2 {
    public static void main (String[] args){

    }
}
