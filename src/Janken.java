
import java.util.Random;// Scannerクラスをインポート
import java.util.Scanner;// Randomクラスをインポート

// じゃんけんゲーム（三回戦）を実装したJankenクラス
public class Janken {

    public static void main(String[] args) {

        // じゃんけんゲームの準備(ユーザー入力の取り込みと乱数生成のオブジェクト作成)
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // 手の名前を格納する配列
        String[] hands = {"グー", "チョキ", "パー"};

        // じゃんけん勝敗カウント用の変数を初期化
        int userWinCount = 0;
        int computerWinCount = 0;
        int drawCount = 0;

        // ゲームの説明・開始メッセージ
        System.out.println("じゃんけんをしましょう。勝負は三回戦です。");
        System.out.println("|0:グー 1:チョキ 2:パー|の中から数字で入力できます。");

        // 三回戦ループ
        for (int round = 1; round <= 3; round++) {
            System.out.println("＜第" + round + "回戦＞");

            // ユーザーの手の入力を受け取る（正しくない入力には再入力を促す）
            int userHand = -1;

            while (userHand < 0 || userHand > 2) {
                System.out.print("あなたの手を数字で入力してください＞ ");

                // 「次の入力が整数かどうか」をチェック
                if (scanner.hasNextInt()) {
                    int input = scanner.nextInt();

                    // 入力された整数が0〜2の範囲内かをチェック
                    if (input >= 0 && input <= 2) {
                        userHand = input;  // 正しい値なのでユーザーの手として変数に代入
                    } else {
                        System.out.println("0～2の数字を入力してください。");
                    }
                } else {
                    // 整数でない場合(文字列など)の処理
                    String bad = scanner.next(); // 不正な入力を読み捨てる
                    System.out.println("無効な入力です。0～2の数字で再入力してください。");
                }
            }

            // コンピュータの手をランダムに生成
            int computerHand = random.nextInt(3);

            // ユーザーとコンピュータの手を表示
            // 0:あいこ, 1:ユーザーの負け, 2:ユーザーの勝ち
            System.out.println("あなたの手：" + hands[userHand]
                    + "、コンピュータの手：" + hands[computerHand]);

            // 勝敗判定（mod を使ったロジック）
            int result = judge(userHand, computerHand);

            if (result == 0) {
                drawCount++;
            } else if (result == 1) {
                computerWinCount++;
            } else { // result == 2
                userWinCount++;
            }

            System.out.println(); // 空白行で見やすく

        }

        // 三回戦終了後の最終結果表示
        System.out.println("最終結果：あなた " + userWinCount + "勝 / コンピュータ "
                + computerWinCount + "勝 / あいこ " + drawCount + "回");

        if (userWinCount > computerWinCount) {
            System.out.println("あなたの勝ちです！おめでとう！");
        } else if (userWinCount < computerWinCount) {
            System.out.println("コンピュータの勝ちです…もう一度挑戦しましょう!");
        } else {
            System.out.println("最終的には引き分けでした。惜しい戦いでしたね！");
        }

        // Scannerをto閉じる
        scanner.close();
    }

//仕様:5 // 勝敗判定を行うjudgeメソッドの実装
    public static int judge(int player, int cpu) {
        int result = (player - cpu + 3) % 3;

        if (result == 0) {
            System.out.println("→あいこです");
        } else if (result == 1) {
            System.out.println("→ あなたの負けです");
        } else { // result == 2
            System.out.println("→ あなたの勝ちです！");
        }

        return result;
    }
}
