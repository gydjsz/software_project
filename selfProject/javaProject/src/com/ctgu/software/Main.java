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
                    bookOperation.outputAllDirFilesWords(args[2]);
                }
                else
                    bookOperation.outputDirFilesWords(args[1]);
                break;
            default:
                System.out.println("参数错误,请重新输入!");
        }
    }
}
