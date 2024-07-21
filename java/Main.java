import java.util.Scanner;

public class Main {
    int fib(int n) {
        if (n == 0)
            return 1;
        else if (n == 1)
            return 1;
        else
            return fib(n - 1) + fib(n - 2);
    }

    static void dowhile(){
        int n = 0;
        do {
            System.out.println(n);
            --n;
        }
        while(n > 0);
    }
    static public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入分数: ");
        int score = Integer.parseInt(scanner.nextLine());
        if (score == 100) {
            System.out.println("去游乐场");
        }
        else if (score >= 80 && score < 90){
            // && 表示和运算 && : boolean -> boolean
            System.out.println("妈妈给我买棒棒糖");
        }
        else if(score >= 90 && score < 100){
            //
            System.out.println("妈妈带我吃麦当劳");
        }
        else{
            //
            System.out.println("妈妈打我");
        }

        System.out.println("晚上接着写作业");
        scanner.close();
    }
}