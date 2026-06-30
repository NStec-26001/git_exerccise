import java.util.Random;
import java.util.Scanner;

public class Game {
    Random r = new Random();
    Scanner s = new Scanner(System.in);
    boolean hl = true;
    boolean sh = true;
    private int setNumber;
    private int bite;
    private int eat;
    // ゲームのマックス回数
    private final int MAX_GAME = 10;
    private int setCorrectNumber[] = new int[3];

    public Game() {
        // ゲームの準備とスタート
        System.out.println("ゲームスタート");
        setGame();
        startGame();
    }

    public void setGame() {
        while (true) {
            // 999~100の間でランダムな数字を生成する
            this.setNumber = (r.nextInt(900) + 100);
            System.out.println(setNumber);
            // 各桁を分割する
            this.setCorrectNumber[0] = setNumber % 10;
            this.setCorrectNumber[1] = (setNumber / 10) % 10;
            this.setCorrectNumber[2] = setNumber / 100;

            // デバッグ用
            // System.out.print("3:" + setNumber3);
            // System.out.print(",2:" + setNumber2);
            // System.out.println(",1:" + setNumber1);

            //各桁にかぶりがなければループを抜ける
            if (setCorrectNumber[0] != setCorrectNumber[1] && setCorrectNumber[0] != setCorrectNumber[2]
                    && setCorrectNumber[1] != setCorrectNumber[2]) {
                break;
            }
        }
    }

    public void startGame() {
        System.out.println("Game Start!!");
        int turn = MAX_GAME;
        //ターンが０になるか、gが押されるまでループ
        while (true) {
            System.out.println("アクションか数字を入力");
            System.out.println("残りターン数：" + turn);
            System.out.println("g: Give up, s:Slash, h: High&Low");
            String command = s.nextLine();
            //tryで数字部分を処理、文字が入力された場合はcatchで処理をする
            try {
                int value = Integer.parseInt(command);
                if (value == this.setNumber) {
                    System.out.println("正解！");
                    break;
                } else if(100 < value  && value < 999){
                    checkNumber(value);
                    turn--;
                }else{
                    System.out.println("3桁の数字を入力してください");
                }

            } catch (NumberFormatException e) {
                if (command.equals("g")) {
                    System.out.println("Game Over...");
                    break;
                } else if (command.equals("s")) {
                    System.out.println("最大値と最小値の差を表示");
                    slash();
                } else if (command.equals("h") && this.hl == true) {
                    System.out.println("5以上HIGH,4以下LOW");
                    highAndLow();
                } else {
                    System.out.println("不正な入力です");
                }
            }
            if (turn <= 0) {
                System.out.println("残りターンが0になってしまいました");
                System.out.println("Game Over...");
                break;
            }

        }

    }

    public void checkNumber(int number) {
        int setNumber[] = new int[3];
        setNumber[0] = number % 10;
        setNumber[1] = (number / 10) % 10;
        setNumber[2] = number / 100;
        bite = 0;
        eat = 0;

        for (int i = 0; i < setCorrectNumber.length; i++) {
            for (int j = 0; j < setCorrectNumber.length; j++) {
                if (setCorrectNumber[i] == setNumber[j]) {
                    if (i == j) {
                        bite++;
                    } else {
                        eat++;
                    }
                }
            }
        }
        System.out.printf("%d BITE : %d EAT", bite, eat);

    }

    public void highAndLow() {
        System.out.println();
        for (int i = setCorrectNumber.length - 1; i >= 0; i--) {
            if (setCorrectNumber[i] > 4) {
                System.out.print("HIGH ");
            } else {
                System.out.print("LOW ");
            }
        }
        this.hl = false;
        System.out.println();
    }

    public void slash() {
        System.out.println();
        int max, min;
        if (this.setCorrectNumber[0] > this.setCorrectNumber[1]) {
            if (this.setCorrectNumber[0] > this.setCorrectNumber[2]) {
                max = this.setCorrectNumber[0];
                if (this.setCorrectNumber[1] > this.setCorrectNumber[2]) {
                    min = this.setCorrectNumber[2];
                } else {
                    min = this.setCorrectNumber[1];
                }
            } else {
                max = this.setCorrectNumber[2];
                min = this.setCorrectNumber[1];
            }
        } else {
            if (this.setCorrectNumber[1] > this.setCorrectNumber[2]) {
                max = this.setCorrectNumber[1];
                if (this.setCorrectNumber[0] > this.setCorrectNumber[2]) {
                    min = this.setCorrectNumber[2];
                } else {
                    min = this.setCorrectNumber[0];
                }
            } else {
                max = this.setCorrectNumber[2];
                min = this.setCorrectNumber[0];
            }
        }
        System.out.println("SLASH:" + (max - min));
        this.sh = false;
    }

}
