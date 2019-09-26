import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BookOperation bookOperation = new BookOperation();
        switch (args[0]){
            case "-c":
                bookOperation.outputLetterFrequency(args[1]);
                break;
            default:
                System.out.println("参数错误,请重新输入!");
        }
    }
}
