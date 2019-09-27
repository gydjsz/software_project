package com.ctgu.software;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputAll extends BookOperation{

    public void outputWordsCount(String fileName) throws IOException {
        output(toSort(wordsCount(fileName)), 0);
    }

    public void outputDirFilesWords(String dirName) throws IOException {
        output(getFilesWords(getDirFiles(dirName)), 0);
    }

    public void outputAllDirFilesWords(String dirName) throws IOException {
        output(getFilesWords(getAllDirFiles(dirName)), 0);
    }

    public void outputTopWords(String dirName, int amount, int flag) throws IOException {
        if(flag == 1){
            output(getFilesWords(getAllDirFiles(dirName)), amount);
        }
        else{
            output(getFilesWords(getDirFiles(dirName)), amount);
        }
    }

    public void outputAfterStopWords(String stopFileName, String fileName) throws IOException {
        Map<String, Integer> words = wordsCount(fileName);
        Map<String, Integer> stopWords = wordsCount(stopFileName);
        for(Map.Entry<String, Integer> stopWord : stopWords.entrySet()){
            words.remove(stopWord.getKey());
        }
        output(toSort(words), 0);
    }


    public void outputPhraseFrequency(String fileName) throws IOException {
        List<String> allPhrase = getSentences("src/allPhrase");
        List<String> allSentence = getSentences(fileName);
        Map<String, Integer> phrase = new HashMap<>();
        for(String s : allSentence){
            for(String p : allPhrase){
                if(s.contains(p)){
                    phrase.put(p, phrase.getOrDefault(p, 0) + 1);
                }
            }
        }
        output(toSort(phrase), 0);
    }

    public void output(List<Map.Entry<String, Integer>> words, int amount) {
        System.out.println("个数为:");
        int count = 0;
        for(Map.Entry<String, Integer> word : words){
            count++;
            System.out.println(word.getKey() + ": " + word.getValue());
            if(amount != 0 && count >= amount)
                break;
        }
    }

    public void outputLetterFrequency(String fileName) throws IOException {
        List<Map.Entry<String, Integer>> list = letterFrequency(fileName);
        System.out.println("字母的频率为:");
        List<String> le = new ArrayList<>();
        for(int i = 0; i < 26; ++i){
            le.add("" + (char)(i + 97));
        }
        for(Map.Entry<String, Integer> letter : list){
            le.remove(letter.getKey());
            System.out.println(letter.getKey() + ": " +String.format("%.2f", letter.getValue() * 100.0 / letterSum) + "%");
        }
        for(String s : le){
            System.out.print(s + " ");
        }
        if(le.size() > 0){
            System.out.println(": 0.00%");
        }
    }

    public void outputAfterNormalise(String normaliseFile, String fileName) throws IOException {
        List<String> normalise = toNormalise(fileName, getNormalise(normaliseFile));
        List<String> phrase = getSentences("src/allPhrase");
        List<Map.Entry<String, Integer>> allPhrase = toSort(getPhraseCount(normalise, phrase));
        for(Map.Entry<String, Integer> p : allPhrase){
            System.out.println(p.getKey() + ": " + p.getValue());
        }
    }
}
