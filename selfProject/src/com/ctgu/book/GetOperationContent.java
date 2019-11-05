package com.ctgu.book;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GetOperationContent extends GetBook{

    public List<String> allFileSentence(String fileName) throws IOException {
        return getBookSentence(getAllFile(fileName, false));
    }

    public List<String> allDirFileSentence(String fileName) throws IOException {
        return getBookSentence(getAllFile(fileName, true));
    }

    public List<Map.Entry<String, Integer>> allLetters(String fileName) throws IOException {
        return toSort(getLetters(allFileSentence(fileName)));
    }

    public List<String> allWords(List<String> sentence){
        return getWords(sentence);
    }

    public List<String> stopWords(String fileName) throws IOException {
        return getWords(allFileSentence(fileName));
    }

    public Map<String, String> normalWords(String fileName) throws IOException {
        return getNormalization(allFileSentence(fileName));
    }

    public List<Map.Entry<String, Integer>> afterStopWords(List<String> sentence, List<String> stopWords){
        return toSort(getWordsAmount(getWords(sentence), stopWords));
    }

    public List<Map.Entry<String, Integer>> wordsAmount(List<String> words){
        return toSort(getWordsAmount(words, null));
    }

    public List<Map.Entry<String, Integer>> phraseAmount(List<String> sentence, int num){
        return getSomePhrase(getSomePhrase(toSort(getPhrase(sentence, num))));
    }

//    public List<Map.Entry<String, Integer>> normalPhraseAmount(List<String> sentence, List<String> phrase, Map<String, String> normal){
//    }

}
