package com.ctgu.book;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GetBook {

    /**
     * 获得当前目录下的文件名
     * @param fileName 文件或目录名
     * @param flag 是否遍历子目录
     * @return 文件名列表
     */
    public List<String> getAllFile(String fileName, boolean flag){
        File file = new File(fileName);
        List<String> list = new ArrayList<>();
        if(!file.isDirectory()){
            list.add(file.getPath());
            return list;
        }
        for(File f : file.listFiles()){
            if(f.isDirectory()) {
                //是否递归遍历子目录
                if(flag)
                    list.addAll(getAllFile(f.getPath(), flag));
                continue;
            }
            list.add(f.getPath());
        }
        return list;
    }

    /**
     * 获得书的句子
     * @param allFile
     * @return 所有句子
     * @throws IOException
     */
    public List<String> getBookSentence(List<String> allFile) throws IOException {
        List<String> bookSentence = new ArrayList<>();
        for(String file : allFile){
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String s;
            while((s = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(s, ",.\n");
                while(st.hasMoreElements()){
                    String sc = st.nextToken().toLowerCase();
                    bookSentence.add(sc);
                }
            }
            br.close();
            fr.close();
        }
        return bookSentence;
    }

    /**
     * 获得单词
     * @param book
     * @return 单词列表
     */
    public List<String> getWords(List<String> book) {
        List<String> words = new ArrayList<>();
        for(String s : book){
            StringTokenizer st = new StringTokenizer(s, " ");
            while (st.hasMoreElements()){
                String sc = st.nextToken();
                words.add(sc);
            }
        }
        return words;
    }

    /**
     * 获得字母
     * @param book
     * @return
     */
    public Map<String, Integer> getLetters(List<String> book) {
        Map<String, Integer> letters = new HashMap<>();
        for(String s : book){
            for(int i = 0; i < s.length(); ++i){
                String le = String.valueOf(s.charAt(i));
                if(le.matches("[a-z]"))
                    letters.put(le, letters.getOrDefault(le, 0) + 1);
            }
        }
        return letters;
    }

    /**
     * 获得统一化的单词
     * @param words
     * @return
     */
    public Map<String, String> getNormalization(List<String> words){
        Map<String, String> normalize = new HashMap<>();
        for(String s : words){
            StringTokenizer st = new StringTokenizer(s, " ");
            String sn = st.nextToken();
            while(st.hasMoreElements()){
                String sc = st.nextToken();
                normalize.put(sc, sn);
            }
        }
        return normalize;
    }

    public Map<String, Integer> getWordsAmount(List<String> words, List<String> stopWords){
        Map<String, Integer> wordsAmount = new HashMap<>();
        for (String s : words){
            wordsAmount.put(s, wordsAmount.getOrDefault(s, 0) + 1);
        }
        for(String s : stopWords){
            wordsAmount.remove(s);
        }
        return wordsAmount;
    }

    public List<Map.Entry<String, Integer>> toSort(Map<String, Integer> book){
        List<Map.Entry<String, Integer>> list = new ArrayList<>(book.entrySet());
        Collections.sort(list, (p1, p2) -> {
            if(p1.getValue() == p2.getValue()){
                return p1.getKey().compareTo(p2.getKey());
            }
            return p2.getValue().compareTo(p1.getValue());
        });
        return list;
    }

    public Map<String, Integer> getPhrase(List<String> book, List<String> allPhrase){
        Map<String, Integer> phrase = new HashMap<>();
        List<String> wordList, phraseList;
        Map<String, Integer>[] map;
        for(String s : book){
            List<String> list = new ArrayList<>();
            list.add(s);
            wordList = getWords(list);
            map = new Map[200];
            for(int i = wordList.size() - 1; i >= 0; --i){
                map[i] = new HashMap<>();
                if(map[i + 1] != null){
                    map[i].putAll(map[i + 1]);
                }
                map[i].put(wordList.get(i), i + 1);
            }
            for(String p : allPhrase){
                List<String> li = new ArrayList<>();
                li.add(p);
                phraseList = getWords(li);
                int i = 0, j = 0;
                while(i < wordList.size() && j < phraseList.size() && map[i].get(phraseList.get(j)) != null){
                    i = map[i].get(phraseList.get(j));
                    j++;
                }
                if(j == phraseList.size()){
                    phrase.put(p, phrase.getOrDefault(p, 0) + 1);
                }
            }
        }
        return phrase;
    }

    public List<String> getNormalSentence(List<String> sentence, Map<String, String> normal){
        List<String> list =  new ArrayList<>();
        for(String s : sentence){
            String ss = "";
            StringTokenizer st = new StringTokenizer(s, " ");
            while(st.hasMoreElements()){
                String sc = st.nextToken();
                ss += normal.getOrDefault(sc, sc) + " ";
            }
            list.add(ss);
        }
        return list;
    }

    public List<String> getSomePhrase(List<String> phrase, int wordNum){
        if(wordNum == 0)
            return phrase;
        List<String> list = new ArrayList<>();
        for(String s : phrase){
            StringTokenizer st = new StringTokenizer(s, " ");
            int n = 0;
            while(st.hasMoreElements()){
                st.nextToken();
                n++;
            }
            if(n == wordNum)
                list.add(s);
        }
       return list;
    }

}
