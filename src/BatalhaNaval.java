import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class BatalhaNaval {
    static Scanner scanner = new Scanner(System.in);
    static int height, width, hitCount = 0, hitTakenCount = 0;;
    static int boardPlayer1[][], boardPlayer2[][];
    static int ship, maxShip;
    static String playerName;


    //usuario define o tamanho do tabuleiro
    public void boardSet (){
        System.out.println("Informe o nome do jogador");
        playerName = scanner.nextLine();
        try{
            System.out.println("Digite a altura do tabuleiro (min 3)");
            height = scanner.nextInt();
            System.out.println("Digite a largura do tabuleiro (min 3)");
            width = scanner.nextInt();
            if (height < 3 || width < 3){
                System.out.println("\nInforme uma altura e largura mínima de 3 casas!!\n");
                boardSet();
            }                
        } catch(InputMismatchException erro){
            scanner.nextLine();
            System.out.println("\n\nDigite um valor numérico\n\n");
            boardSet();
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
        try {
            System.out.println("\nInforme a quantidade de navios de 1 a " + maxShip);
            ship = scanner.nextInt();
            if(ship < 1){
                System.out.println("Informe um número maior que 0!!");
                numberOfShips();
            }
            if(ship > maxShip)
                ship = maxShip;
        } catch (InputMismatchException erro) {
            scanner.nextLine();
            System.out.println("\n\nDigite um valor numérico\n\n");
            numberOfShips();
        }
        
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


    // metodo para transformar o tiro do jogador em dois números inteiros
    public static int[] getPosition(String shot){
        char tiro = shot.toUpperCase().charAt(0);
        int position[] = new int[2];
        position[0] = Integer.parseInt(shot.substring(1)) - 1;
        position[1] = (int)tiro - 65;
        return position;
    }

    // metodoo para validar se o tiro do jogador está dentro dos caracteres aceitos
    public static boolean verify(String shot){
        int[] position = getPosition(shot);
        int operator;
        if(position[0] >= 9)
            operator = 2;
        else
            operator = 1;
        String verifyingExpression = "^[A-Za-z]{1}[0-9]{" + operator + "}$";
        return shot.matches(verifyingExpression);
    }

    // metodo para realizar o tiro do player
    public void playerShot(int[][] Player){
        String shot = inputPlayerShot();
        int[] position = getPosition(shot);
        if(verify(shot)){
            if(position[0] < width && position[1] < height){
                if(Player[position[0]][position[1]] == 1){
                    Player[position[0]][position[1]] = 2;
                    System.out.println("\n\n\n\n\nNavio destruido!!!");
                    hitCount++;

                }if(Player[position[0]][position[1]] == 0){
                    Player[position[0]][position[1]] = 3;
                    System.out.println("\n\n\n\n\nErrrooouuuuu :(");
                }
            } else{
                System.out.println("Tiro fora do tabuleiro!!");
            }
        } else{
            System.out.println("ERRO");
        }
    }

    public void enemyShot(int player[][]){
        Random rand = new Random();
        int posX = rand.nextInt(height);
        int posY = rand.nextInt(width);
        if(player[posX][posY] == 2)
            enemyShot(player);
        if(player[posX][posY] == 3)
            enemyShot(player);
        if(player[posX][posY] == 0)
            player[posX][posY] = 3;
        if(player[posX][posY] == 1){
            player[posX][posY] = 2;
            hitTakenCount++;
        }
        
    }

    public boolean endGame(int[][] player){
        boolean game = true;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(player[j][i] == 1){
                    game = false;
                    break;
                }
            }
        }
        return game;
    }

    public void loop(){
        showBothBoard();
        playerShot(boardPlayer2);
        enemyShot(boardPlayer1);
        if(!endGame(boardPlayer1) && !endGame(boardPlayer2))
            loop();
        if(endGame(boardPlayer1) || endGame(boardPlayer2)){
            showBothBoard();
            System.out.println("Fim do jogo!!!");
            if(endGame(boardPlayer1)){
                System.out.println("\n");
                String[] perdeu = {
                    " ****   *****  ****   ****   ****  *****  ***  ",
                    " *   *  *      *   *  *   * *    *   *   *   * ",
                    " *   *  ****   ****   ****  *    *   *   ***** ",
                    " *   *  *      * *    * *   *    *   *   *   * ",
                    " ****   *****  *  *   *  *   ****    *   *   * "
                };
        
                for (String linha : perdeu) {
                    System.out.println(linha);
                }
            }
            if(endGame(boardPlayer2)){
                System.out.println("\n");
                String[] perdeu = {
                    " *   * ***** *****  ****  ****  *****  ***  ",
                    " *   *   *     *   *    * *   *   *   *   * ",
                    " *   *   *     *   *    * ****    *   ***** ",
                    "  * *    *     *   *    * * *     *   *   * ",
                    "   *   *****   *    ****  *  *  ***** *   * "
                };
        
                for (String linha : perdeu) {
                    System.out.println(linha);
                }
            }
        }
    }
    
    // metodo para imprimir o tabuleiro do player
    public void showPlayerBoard (int[][] Player, String playerName){
        System.out.println("\nLegenda:");
        System.out.println("- D -> Navio destruido");
        System.out.println("- X -> Tiro errado");
        System.out.println("\n" + playerName + "\n");
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
        System.out.println("Total de navios destruidos: " + hitTakenCount + "\n");
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
                    System.out.printf("|   ");
                if(Player[i][j] == 0)
                    System.out.printf("|   ");
            }
            System.out.println("|");
            System.out.println("\n");
        }
        System.out.println("Total de navios destruido: " + hitCount + "\n");
    }

    public void showBothBoard(){
        showPlayerBoard(boardPlayer1, playerName);
        showEnemyBoard(boardPlayer2);
    }

    public void menu(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n********************************************************************");
        System.out.println("*                    BEM-VINDO AO BATALHA NAVAL                    *");
        System.out.println("********************************************************************");
        System.out.println();
        System.out.println("           |      |                          |      |    ");
        System.out.println("          )_)    )_)                        )_)    )_)   ");
        System.out.println("         )___)  )___)                      )___)  )___)  ");
        System.out.println("        )____) )_____)                    )____) )_____) ");
        System.out.println("-----\\_________________/---------------\\_________________/----------");
        System.out.println("~~~~~~\\_______________/~~~~~~~~~~~~~~~~~\\_______________/~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("\nPrepare-se para uma emocionante batalha no mar!");
        System.out.println("Seus navios e o do oponente serão posicionados de forma aleatória no tabuleiro");
        System.out.println("e você deverá tentar afundar os navios inimigos antes que os seus sejam destruídos.");
        System.out.println("Você deverá escolher a quantidade de navios no tabuleiro, e essa quantidade de navios será sempre 1 terço do tamanho total do tabuleiro");
        System.out.println("e essa quantidade de navios será sempre 1 terço do tamanho total do tabuleiro\n");
        System.out.println("Regras básicas:");
        System.out.println("- Os jogadores possuem a mesma quantidade de navios");
        System.out.println("- Os navios são posicionados de forma aleatória pelo sistema.");
        System.out.println("- O tabuleiro deverá ter uma tamanho mínimo de 3x3");
        System.out.println("- A quantidade máxima de navios deverá ser menor ou igual a 1 terço da quantidade de casas no tabuleiro");
        System.out.println("- Os jogadores se revezam tentando acertar as coordenadas dos navios inimigos.");
        System.out.println("- O primeiro a afundar todos os navios do adversário vence!");
        System.out.println();
        System.out.println("Boa sorte, comandante! Que vença o melhor estrategista!");
    }
    
    public static void main(String[] args) throws Exception {
        BatalhaNaval partida = new BatalhaNaval();
        partida.menu();
        partida.boardSet();
        partida.maxShipOnBoard();
        partida.boardSize();
        partida.numberOfShips();
        partida.setShipsOnBoard(boardPlayer1);
        partida.setShipsOnBoard(boardPlayer2);
        partida.loop();
        scanner.close();
    }
}
