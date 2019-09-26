package com.ctgu.software;

import org.omg.CORBA.INTERNAL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BookOperation<E> {
    int letterSum;

    public BufferedReader getReader(String fileName) throws IOException {
        File file = new File(fileName);
        if(!file.exists()){
            System.out.println("文件不存在, 请重新输入!");
            System.exit(-1);
        }
        FileReader fileReader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fileReader);
        return br;
    }

    public List<Map.Entry<String, Integer>> letterFrequency(String fileName) throws IOException {
        BufferedReader br = getReader(fileName);
        Map<String, Integer> letter = new HashMap<>();
        String s;
        while((s = br.readLine()) != null){
            String sc = s.toLowerCase();
            for(int i = 0; i < sc.length(); ++i){
                String c = sc.charAt(i) + "";
                if(sc.charAt(i) >= 'a' && sc.charAt(i) <= 'z'){
                    letterSum++;
                    letter.put(c, letter.getOrDefault(c, 0) + 1);
                }
            }
        }
        br.close();
        return toSort(letter);
    }

    public List<Map.Entry<String, Integer>> toSort(Map<String, Integer> words){
        List<Map.Entry<String, Integer>> list = new ArrayList<>(words.entrySet());
        Collections.sort(list, (p1, p2) -> {
            if(p1.getValue() == p2.getValue())
                return p1.getKey().compareTo(p2.getKey());
            return p2.getValue().compareTo(p1.getValue());
        });
        return list;
    }

    public Map<String, Integer> wordsCount(String fileName) throws IOException {
        BufferedReader br = getReader(fileName);
        Map<String, Integer> words = new HashMap<>();
        String s;
        while((s = br.readLine()) != null){
            StringTokenizer st = new StringTokenizer(s, " ,?.!:\"\"“”\n#;1234567890()");
            while (st.hasMoreElements()) {
                String sc = st.nextToken().toLowerCase();
                words.put(sc, words.getOrDefault(sc, 0) + 1);
            }
        }
        return words;
    }

    public void output(List<Map.Entry<String, Integer>> words){
        System.out.println("单词的个数为:");
        for(Map.Entry<String, Integer> word : words){
            System.out.println(word.getKey() + ":" + word.getValue());
        }
    }


    public void outputLetterFrequency(String fileName) throws IOException {
        List<Map.Entry<String, Integer>> list = letterFrequency(fileName);
        System.out.println("字母的频率为:");
        for(Map.Entry<String, Integer> letter : list){
            System.out.println(letter.getKey() + ": " +String.format("%.2f", letter.getValue() * 100.0 / letterSum) + "%");
        }
    }

    public List<String> getAllDirFiles(String dirName) throws IOException {
        List<String> list = new ArrayList<>();
        File file = new File(dirName);
        if(!file.isDirectory()){
            list.add(file.getPath());
            return list;
        }
        File[] allFile = file.listFiles();
        String s = null;
        for(File fileName : allFile){
            if(fileName.isDirectory()){
                list.addAll(getAllDirFiles(fileName.getPath()));
                continue;
            }
            list.add(fileName.getPath());
        }
        return list;
    }

    public List<String> getDirFiles(String dirName){
        List<String> list = new ArrayList<>();
        File file = new File(dirName);
        File[] allFile = file.listFiles();
        for(File fileName : allFile){
            if(fileName.isDirectory()){
                continue;
            }
            list.add(fileName.getPath());
        }
        return list;
    }

    public List<Map.Entry<String, Integer>> getFilesWords(List<String> list) throws IOException {
        Map<String, Integer> getWord = new HashMap<>();
        for(String fileName : list){
            getWord.putAll(wordsCount(fileName));
        }
        return toSort(getWord);
    }

    public void outputWordsCount(String fileName) throws IOException {
        output(toSort(wordsCount(fileName)));
    }

    public void outputDirFilesWords(String dirName) throws IOException {
        output(getFilesWords(getDirFiles(dirName)));
    }

    public void outputAllDirFilesWords(String dirName) throws IOException {
        output(getFilesWords(getAllDirFiles(dirName)));
    }

}
