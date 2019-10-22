package com.ctgu.book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class OutputResult {
    private int wordNum;
    private String s = "-c <file_name> 输出字母频率\n" +
            "\n" +
            "-f <file_name> 排序输出文件单词个数\n" +
            "\n" +
            "-d <file_name> 排序输出文件夹下单词个数\n" +
            "\n" +
            "-d -s <file_name> 排序输出所有文件夹下单词个数\n" +
            "\n" +
            "-n num 指定输出前n个单词, 不加num则默认输出全部\n" +
            "\n" +
            "-x <stop_file> -f <file_name> 跳过stop_file中的内容输出单词个数\n" +
            "\n" +
            "-p num 指定num个词的短语,与-d,-d -s搭配使用\n" +
            "\n" +
            "-v <verb_file> 将不同形式的单词归一化,与-d,-d -s搭配使用";

    public void setWordNum(int wordNum) {
        this.wordNum = wordNum;
    }

    public void letterFrequency(List<Map.Entry<String, Integer>> letters){
        System.out.println("字母的频率为: ");
        int sum = 0;
        Map<String, Integer> let = new HashMap<>();
        for(int i = 0; i < 26; i++){
            char a = (char)(97 + i);
            let.put(String.valueOf(a), 0);
        }
        for(Map.Entry<String, Integer> letter : letters){
            let.put(letter.getKey(), 1);
            sum += letter.getValue();
        }
        for(Map.Entry<String, Integer> letter : letters){
            System.out.println(letter.getKey() + ": " + String.format("%.2f", 100.0 * letter.getValue() / sum) + "%");
        }
        boolean flag = false;
        for(String key : let.keySet()){
            if(let.get(key) == 0) {
                System.out.print(key + "、");
                flag = true;
            }
        }
        if(flag){
            System.out.println(": 0.00%");
        }
    }

    public void wordsAmount(List<Map.Entry<String, Integer>> words){
        System.out.println("单词的个数为: ");
        int count = 0;
        for(Map.Entry<String, Integer> word : words){
            System.out.println(word.getKey() + ": " + word.getValue());
            if(wordNum == 0)
                continue;
            count++;
            if(count >= wordNum){
                break;
            }
        }
    }

    public void phraseAmount(List<Map.Entry<String, Integer>> phrases){
        System.out.println("短语的个数为: ");
        for(Map.Entry<String, Integer> phrase : phrases){
            System.out.println(phrase.getKey() + ": " + phrase.getValue());
        }
    }

    public void errorWarn(){
        System.out.println("参数错误!");
        System.out.println(s);
    }
}
