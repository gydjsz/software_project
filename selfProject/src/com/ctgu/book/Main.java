package com.ctgu.book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private Map<String, String> operator;


    public void getOperation(String[] args){
        operator = new HashMap<>();
        String regex = "^-.";
        String num = "^[0-9]+$";
        for(int i = 0; i < args.length; ++i){
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
    }

    public void achieveOperations() throws IOException {
        GetOperationContent content = new GetOperationContent();
        OutputResult outputResult = new OutputResult();
        List<Map.Entry<String, Integer>> letters = new ArrayList<>();
        List<Map.Entry<String, Integer>> words = new ArrayList<>();
        List<String> allSentence = new ArrayList<>();
        List<String> stopWords = new ArrayList<>();
        List<Map.Entry<String, Integer>> allPhrase = new ArrayList<>();
        Map<String, String> normal = new HashMap<>();
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
                case "-b":
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
                case "-v":
                    normal = content.normalWords(value);
                    type = 4;
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
            List<String> phrase = content.allFileSentence("/home/dal/Documents/computer/test/testData/phrase");
            phrase = content.getSomePhrase(phrase, phraseNum);
            allPhrase = content.phraseAmount(allSentence, phrase);
            outputResult.phraseAmount(allPhrase);
        }
        else if(type == 4){
            List<String> phrase = content.allFileSentence("/home/dal/Documents/computer/test/testData/phrase");
            phrase = content.getSomePhrase(phrase, phraseNum);
            allPhrase = content.normalPhraseAmount(allSentence, phrase, normal);
            outputResult.phraseAmount(allPhrase);
        }
        else{
            outputResult.errorWarn();
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.getOperation(args);
        main.achieveOperations();
    }
}
