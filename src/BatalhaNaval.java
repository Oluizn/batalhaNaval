import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class BatalhaNaval {
    static Scanner scanner = new Scanner(System.in);
    static int height, width;
    static int boardPlayer1[][], boardPlayer2[][];
    static int ship, maxShip;


    //usuario define o tamanho do tabuleiro
    public void boardSet (){
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

    // metodo para criar matriz para tabuleiro
    public void boardSize (){
        boardPlayer1 = new int[height][width];
        boardPlayer2 = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boardPlayer1[i][j] = 0;
                boardPlayer2[i][j] = 0;
            }
        }
    }

    // metodo para definir quantidade máxima de navios de acordo com tamanho do tabuleiro
    public void maxShipOnBoard(){
        maxShip = (height * width)/3;
    }

    // metodo para definir quantidade de navios da partida
    public void numberOfShips(){
        System.out.println("\nInforme a quantidade de navios de 1 a " + maxShip);
        ship = scanner.nextInt();
        if (ship > maxShip)
            ship = maxShip;
    }
    
    // metodo para posicionar navios aleatoriamente no tabuleiro
    public void setShipsOnBoard(int[][] player){
        Random rand = new Random();
        int cont=0;
        while (cont < ship){
            int posX = rand.nextInt(height);
            int posY = rand.nextInt(width);
            if (player[posX][posY] != 1){
                player[posX][posY] = 1;
                cont++;
            }
        }
    }

    // metodo para capturar posição do tiro do player:
    public static String inputPlayerShot(){
        System.out.println("Digite a posição do seu tiro");
        return scanner.next();
    }

    // metodoo para validar se o tiro do jogador está dentro dos caracteres aceitos
    public static boolean verify(String shot){
        int heightQuantity = (height > 10) ? 2 : 1;
        String verifyingExpression = "^[A-Za-z]{1}[0-9]{" + heightQuantity + "}$";
        return shot.matches(verifyingExpression);
    }

    // metodo para transformar o tiro do jogador em dois números inteiros
    public static int[] getPosition(String shot){
        char tiro = shot.toUpperCase().charAt(0);
        int position[] = new int[2];
        position[0] = Integer.parseInt(shot.substring(1)) - 1;
        position[1] = (int)tiro - 65;
        return position;
    }

    // metodo para realizar o tiro do player
    public void playerShot(int[][] Player){
        String shot = inputPlayerShot();
        int[] position = getPosition(shot);
        if(verify(shot)){
            if(position[0] < width && position[1] < height){
                if(Player[position[0]][position[1]] == 1){
                    Player[position[0]][position[1]] = 2;
                }if(Player[position[0]][position[1]] == 0){
                    Player[position[0]][position[1]] = 3;
                }
            } else{
                System.out.println("Tiro fora do tabuleiro!!");
            }
        } else{
            System.out.println("ERRO");
        }
    }

    // metodo para imprimir o tabuleiro do player
    public void showPlayerBoard (int[][] Player, String playerName){
        System.out.println(playerName);
        System.out.printf("    ");
        for (int i = 65; i < width + 65; i++) {
            System.out.printf("%c   ", (char)i);
        }
        System.out.println("\n");
        for (int i = 0; i < height; i++) {
            if(i<9)
                System.out.printf("%d ", i+1);
            else
                System.out.printf("%d", i+1);
            for (int j = 0; j < width; j++) {
                if(Player[i][j] == 3)
                    System.out.printf("| X ");
                if(Player[i][j] == 2)
                    System.out.printf("| D ");
                if(Player[i][j] == 1)
                    System.out.printf("| N ");
                if(Player[i][j] == 0)
                    System.out.printf("|   ");
            }
            System.out.println("|");
            System.out.println("\n");
        }
    }

    // metodo para imprimir o tabuleiro do inimigo
    public void showEnemyBoard (int[][] Player){
        System.out.println("\nTabuleiro inimigo\n");
        System.out.printf("    ");
        for (int i = 65; i < width + 65; i++) {
            System.out.printf("%c   ", (char)i);
        }
        System.out.println("\n");
        for (int i = 0; i < height; i++) {
            System.out.printf("%d ", i+1);
            for (int j = 0; j < width; j++) {
                if(Player[i][j] == 3)
                    System.out.printf("| X ");
                if(Player[i][j] == 2)
                    System.out.printf("| D ");
                if(Player[i][j] == 1)
                    System.out.printf("|   ");
                if(Player[i][j] == 0)
                    System.out.printf("|   ");
            }
            System.out.println("|");
            System.out.println("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        BatalhaNaval partida = new BatalhaNaval();
        partida.boardSet();
        partida.boardSize();
        partida.maxShipOnBoard();
        partida.numberOfShips();
        partida.setShipsOnBoard(boardPlayer1);
        partida.setShipsOnBoard(boardPlayer2);
        partida.showPlayerBoard(boardPlayer1, "Antonio");
        partida.showEnemyBoard(boardPlayer2);
        partida.playerShot(boardPlayer1);
        partida.showPlayerBoard(boardPlayer1, "Antonio");
        partida.showEnemyBoard(boardPlayer2);

        System.out.println("Digite a posição do seu tiro");
        scanner.close();
    }
}
