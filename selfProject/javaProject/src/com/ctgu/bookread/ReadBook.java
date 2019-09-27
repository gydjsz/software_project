package com.ctgu.bookread;

import java.util.*;

public class ReadBook extends BuyBook{
    private int letterSum;
    private int amount;

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<Map.Entry<String, Integer>> calLetterFrequency(List<String> book){
        Map<String, Integer> letter = new HashMap<>();
        String regex = "[a-z]";
        for(String s : book){
            for(int i = 0; i < s.length(); ++i){
                String c = s.charAt(i) + "";
                if(c.matches(regex)){
                    letterSum++;
                    letter.put(c, letter.getOrDefault(c, 0) + 1);
                }
            }
        }
        return toSort(letter);
    }

    public List<Map.Entry<String, Integer>> toSort(Map<String, Integer> book){
        List<Map.Entry<String, Integer>> list = new ArrayList<>(book.entrySet());
        Collections.sort(list, (p1, p2) -> {
            if(p1.getValue() == p2.getValue())
                return p1.getKey().compareTo(p2.getKey());
            return p2.getValue().compareTo(p1.getValue());
        });
        return list;
    }

    public List<Map.Entry<String, Integer>> calWordsAmount(List<String> book){
        Map<String, Integer> words = new HashMap<>();
        for(String s : book){
            StringTokenizer st = new StringTokenizer(s, " ");
            while(st.hasMoreElements()){
                String sc = st.nextToken();
                words.put(sc, words.getOrDefault(sc, 0) + 1);
            }
        }
        return toSort(words);
    }

    public void output(List<Map.Entry<String, Integer>> result){
        int count = 0;
        for(Map.Entry<String, Integer> res : result){
            count++;
            if(letterSum != 0)
                System.out.println(res.getKey() + ": " + String.format("%.2f", res.getValue() * 100.0 / letterSum) + "%");
            else
                System.out.println(res.getKey() + ": " + res.getValue());
            if(amount != 0 && count >= amount)
                break;
        }
    }

    public List<Map.Entry<String, Integer>> getNumPhrase(int n, List<Map.Entry<String, Integer>> phrase){
        List<String> list;
        Map<String, Integer> newPhrase = new HashMap<>();
        for(Map.Entry<String, Integer> p : phrase){
            StringTokenizer st = new StringTokenizer(p.getKey(), " ");
            int amount = 0;
            while(st.hasMoreElements()){
                st.nextToken();
                amount++;
            }
            if(amount == n){
                newPhrase.put(p.getKey(), p.getValue());
            }
        }
        return toSort(newPhrase);
    }

    public List<String> getSentenceToWords(String sentence){
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(sentence, " ");
        while(st.hasMoreElements()){
            String s = st.nextToken();
            list.add(s);
        }
        return list;
    }

    public List<Map.Entry<String, Integer>> wordsFilter(List<String> filter, List<Map.Entry<String, Integer>> book){
        List<Map.Entry<String, Integer>> list = calWordsAmount(filter);
        Map<String, Integer> b = new HashMap<>();
        for(Map.Entry<String, Integer> s : book){
            b.put(s.getKey(), s.getValue());
        }
        for(Map.Entry<String, Integer> s : list){
            b.remove(s.getKey());
        }
        return toSort(b);
    }

    public Map<String, String> getNormalizeBook(List<String> book){
        Map<String, String> normalize = new HashMap<>();
        List<String> list;
        for(String s : book){
            list = getSentenceToWords(s);
            for(String li : list){
                normalize.put(li, list.get(0));
            }
        }
        return normalize;
    }

    public List<String> toNormalizeSentence(List<String> book, Map<String, String> normalizeBook){
        List<String> newBook = new ArrayList<>();
        List<String> list;
        for(String b : book){
            String sentence = "";
            list = getSentenceToWords(b);
            for(String s : list){
                sentence += normalizeBook.getOrDefault(s, s) + " ";
            }
            newBook.add(sentence);
        }
       return newBook;
    }

    public List<Map.Entry<String, Integer>> phraseCount(List<String> book, List<String> allPhrase){
       Map<String, Integer> phrase = new HashMap<>();
       List<String> wordList, phraseList;
       Map<String, Integer>[] map;
       for(String s : book){
           wordList = getSentenceToWords(s);
           map = new Map[200];
           for(int i = wordList.size() - 1; i >= 0; --i){
               map[i] = new HashMap<>();
               if(map[i + 1] != null){
                   map[i].putAll(map[i + 1]);
               }
               map[i].put(wordList.get(i), i + 1);
           }
           for(String p : allPhrase){
               phraseList = getSentenceToWords(p);
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
       return toSort(phrase);
    }
}
