import java.util.Scanner;

public class Numer0n {

    public static void main(String[] args) {
        //タイトル
        System.out.println("""
                    ######################
                    #    Numer0n Game    #
                    ######################
                """);
        
        //ゲームの継続判定
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.print("コマンドを入力してください(開始:y, 終了:n):");
            String command = s.nextLine();

            //ゲームをスタートする
            if (command.equals("y")) {
                Game g = new Game();                

            //ゲームを終了してプログラムを閉じる
            } else if (command.equals("n")) {
                System.out.println("ゲームを終了します");
                break;

            //入力が正しくないため再入力
            } else {
                System.out.println("不正な入力です");
            }

        }
        System.out.println("End");

    }
}
