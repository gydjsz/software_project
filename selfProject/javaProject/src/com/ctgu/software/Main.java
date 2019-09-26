package com.ctgu.software;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BookOperation bookOperation = new BookOperation();
        switch (args[0]){
            case "-c":
                bookOperation.outputLetterFrequency(args[1]);
                break;
            case "-f":
                bookOperation.outputWordsCount(args[1]);
                break;
            case "-d":
                if(args[1].equals("-s")) {
                    if(args.length == 5 && args[2].equals("-n")){
                        bookOperation.outputTopWords(args[4], Integer.valueOf(args[3]), 1);
                    }
                    else if(args.length == 4 && args[2].equals("-n")){
                        bookOperation.outputTopWords(args[3], 0, 1);
                    }
                    else if(args.length == 3){
                        bookOperation.outputAllDirFilesWords(args[2]);
                    }
                }
                else {
                    if(args.length == 4 && args[1].equals("-n"))
                        bookOperation.outputTopWords(args[3], Integer.valueOf(args[2]), 0);
                    else if(args.length == 3 && args[1].equals("-n"))
                        bookOperation.outputTopWords(args[2], 0, 0);
                    else if(args.length == 2)
                        bookOperation.outputDirFilesWords(args[1]);
                }
                break;

            default:
                System.out.println("参数错误,请重新输入!");
        }
    }
}
