import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static int height, width;
    static int boardPlayer1[][], boardPlayer2[][];
    static int ship, maxShip;

    public static void boardSet (){
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
    }

    public static void boardSize (){
        boardPlayer1 = new int[height][width];
        boardPlayer2 = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boardPlayer1[i][j] = 0;
                boardPlayer2[i][j] = 0;
            }
        }
    }

    public static void maxShipOnBoard(){
        maxShip = (height * width)/3;
    }

    public static void numberOfShips(){
        System.out.println("\nInforme a quantidade de navios de 1 a " + maxShip);
        ship = scanner.nextInt();
        if (ship > maxShip)
            ship = maxShip;
    }
    
    public static void setShipsOnBoard(int[][] player){
        Random rand = new Random();
        int cont=0;
        while (cont < maxShip){
            int posX = rand.nextInt(height);
            int posY = rand.nextInt(width);
            if (player[posX][posY] != 1){
                player[posX][posY] = 1;
                cont++;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        boardSet();
        boardSize();
        maxShipOnBoard();
        numberOfShips();
        setShipsOnBoard(boardPlayer1);
        setShipsOnBoard(boardPlayer2);
        System.out.println("\n\n Player 1\n\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.printf("%d", boardPlayer1[i][j]);
            }
            System.out.println("\n");
        }
        System.out.println("\n\nPlayer 2 \n\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.printf("%d", boardPlayer2[i][j]);
            }
            System.out.println("\n");
        }
        scanner.close();
    }
}
