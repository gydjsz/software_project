package com.ctgu.software;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.util.*;

public class BookOperation {
    int letterSum;

    public BufferedReader getReader(String fileName) throws IOException {
        File file = new File(fileName);
        if(!file.exists()){
            System.out.println("文件不存在, 请重新输入!");
            System.exit(-1);
        }
        if(file.isDirectory()){
            System.out.println("请输入文件名!");
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
            StringTokenizer st = new StringTokenizer(s, " ,?.!:\"\"“”\n#;1234567890()-");
            while (st.hasMoreElements()) {
                String sc = st.nextToken().toLowerCase();
                words.put(sc, words.getOrDefault(sc, 0) + 1);
            }
        }
        br.close();
        return words;
    }

    public List<String> getAllDirFiles(String dirName) {
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

    public List<String> getSentences(String fileName) throws IOException {
        BufferedReader br = getReader(fileName);
        List<String> list = new ArrayList<>();
        String s;
        while((s = br.readLine()) != null){
            StringTokenizer st = new StringTokenizer(s, ",.\n");
            while (st.hasMoreElements()) {
                String sc = st.nextToken().toLowerCase();
                list.add(sc);
            }
        }
        br.close();
        return list;
    }

    public Map<String, String> getNormalise(String fileName) throws IOException {
        BufferedReader br = getReader(fileName);
        Map<String, String> word = new HashMap<>();
        String s;
        while((s = br.readLine()) != null){
            StringTokenizer st = new StringTokenizer(s, " ");
            st.hasMoreElements();
            String sc = st.nextToken().toLowerCase();
            String index = sc;
            while (st.hasMoreElements()) {
                sc = st.nextToken().toLowerCase();
                word.put(sc, word.getOrDefault(sc, index));
            }
        }
        return word;
    }

    public List<String> toNormalise(String fileName, Map<String, String> normalise) throws IOException {
        BufferedReader br = getReader(fileName);
        String s;
        List<String> list = new ArrayList<>();
        while((s = br.readLine()) != null){
            String sentence = "";
            StringTokenizer st = new StringTokenizer(s, ",.\n");
            while (st.hasMoreElements()) {
                String sc = st.nextToken().toLowerCase();
                StringTokenizer w = new StringTokenizer(sc, " ");
                while(w.hasMoreElements()){
                    String sen = w.nextToken().toLowerCase();
                    sentence += normalise.getOrDefault(sen, sen) + " ";
                }
                list.add(sentence);
            }
        }
        br.close();
        return list;
    }

    public List<String> getWords(String s){
        List<String> words = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(s, " ");
        while(st.hasMoreElements()){
            String sc = st.nextToken().toLowerCase();
            words.add(sc);
        }
        return words;
    }

    public Map<String, Integer> getPhraseCount(List<String> sentence, List<String> phrase){
        Map<String, Integer> allPhrase = new HashMap<>();
        List<String> words;
        for(String sen : sentence){
            words = getWords(sen);
            //序列自动机
            Map<String, Integer>[] place = new Map[1000];
            for(int i = words.size() - 1; i >= 0; --i){
                place[i] = new HashMap<>();
                if(place[i + 1] != null)
                    place[i].putAll(place[i + 1]);
                place[i].put(words.get(i), i + 1);
            }
            for(String ph : phrase){
                List<String> p = getWords(ph);
                int i = 0, j = 0;
                while(j < p.size() && i < words.size() && place[i].get(p.get(j)) != null){
                    i = place[i].get(p.get(j));
                    j++;
                }
                if(j == p.size()){
                    allPhrase.put(ph, allPhrase.getOrDefault(ph, 0) + 1);
                }
            }
        }
        return allPhrase;
    }
}
