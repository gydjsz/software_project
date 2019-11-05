package com.ctgu.book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private Map<String, String> operator;
    private String command;
    private static OutputResult outputResult;
    private static String outputFileName;

    public Main() {
        outputFileName = "";
    }

    public void getOperation(String[] args){
        operator = new HashMap<>();
        command = "==========================================================================\n" +
                "\n\n参数：";
        String regex = "^-.";
        String num = "^[0-9]+$";
        for(int i = 0; i < args.length; ++i){
            command += args[i] + " ";
            if(args[i].matches(regex)){
                if(args[i].equals("-n")) {
                    operator.put(args[i], "0");
                    continue;
                }
                operator.put(args[i], null);
                continue;
            }
            operator.put(args[i - 1], args[i]);
        }
        command += "\n\n==========================================================================\n" +
                "\n\n\n";
    }

    public void achieveOperations() throws IOException {
        GetOperationContent content = new GetOperationContent();
        outputResult = new OutputResult(command);
        List<Map.Entry<String, Integer>> letters = new ArrayList<>();
        List<Map.Entry<String, Integer>> words;
        List<String> allSentence = new ArrayList<>();
        List<String> stopWords = new ArrayList<>();
        List<Map.Entry<String, Integer>> allPhrase = new ArrayList<>();
//        Map<String, String> normal = new HashMap<>();

        int wordNum = 0;
        int phraseNum = 0;
        int type = 2;

        for(String key : operator.keySet()){
            String value = operator.get(key);
            if(value == null)
                continue;
            switch (key){
                case "-c":
                    letters = content.allLetters(value);
                    type = 1;
                    break;
                case "-s":
                    allSentence = content.allDirFileSentence(value);
                    break;
                case "-d":
                    allSentence = content.allFileSentence(value);
                    break;
                case "-f":
                    allSentence = content.allFileSentence(value);
                    type = 2;
                    break;
                case "-n":
                    wordNum = Integer.valueOf(value);
                    break;
                case "-x":
                    stopWords = content.stopWords(value);
                    break;
                case "-p":
                    phraseNum = Integer.valueOf(value);
                    type = 3;
                    break;
//                case "-v":
//                    normal = content.normalWords(value);
//                    type = 4;
//                    break;
                case "-o":
                    outputFileName = value;
                    break;
                default:
                    type = -1;
            }
            if(type == -1)
                break;
        }
        if(type == 1){
            outputResult.letterFrequency(letters);
        }
        else if(type == 2){
            outputResult.setWordNum(wordNum);
            words = content.afterStopWords(allSentence, stopWords);
            outputResult.wordsAmount(words);
        }
        else if(type == 3){
            allPhrase = content.phraseAmount(allSentence, phraseNum);
            outputResult.phraseAmount(allPhrase);
        }
        else if(type == 4){
            outputResult.phraseAmount(allPhrase);
        }
        else{
            outputResult.errorWarn();
        }

    }

    public static void main(String[] args) throws IOException {
        long startTime=System.currentTimeMillis();
        Main main = new Main();
        main.getOperation(args);
        main.achieveOperations();
        long endTime=System.currentTimeMillis();
        String s = outputResult.getRes() +"\n\n" +
                "==========================================================================\n" +
                "程序运行时间： "+(endTime-startTime)+"ms";
        outputResult.setRes(s);
        outputResult.outputFile(outputFileName);
    }
}
