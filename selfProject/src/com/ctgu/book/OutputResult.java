package com.ctgu.book;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputResult {
    private int wordNum;
    private String res;
    private String errorString = "-c <file_name> 输出字母频率\n" +
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
            "\n";

    public OutputResult() {
        res = "";
    }

    public void setWordNum(int wordNum) {
        this.wordNum = wordNum;
    }

    public void letterFrequency(List<Map.Entry<String, Integer>> letters){
        res += "字母的频率为: \n";
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
            res += letter.getKey() + ": " + String.format("%.2f", 100.0 * letter.getValue() / sum) + "%\n";
        }
        boolean flag = false;
        for(String key : let.keySet()){
            if(let.get(key) == 0) {
                res += key + "、";
                flag = true;
            }
        }
        if(flag){
            res += ": 0.00%\n";
        }
    }

    public void wordsAmount(List<Map.Entry<String, Integer>> words){
        res += "单词的个数为: \n";
        int count = 0;
        for(Map.Entry<String, Integer> word : words){
            res += word.getKey() + ": " + word.getValue() + "\n";
            if(wordNum == 0)
                continue;
            count++;
            if(count >= wordNum){
                break;
            }
        }
    }

    public void phraseAmount(List<Map.Entry<String, Integer>> phrases){
        res += "短语的个数为: \n";
        int count = 0;
        for(Map.Entry<String, Integer> phrase : phrases){
            res += phrase.getKey() + ": " + phrase.getValue() + "\n";
            if(wordNum == 0)
                continue;
            count++;
            if(count >= wordNum){
                break;
            }
        }
    }

    public void errorWarn(){
        System.out.println("参数错误!");
        System.out.println(errorString);
    }

    public void outputFile(String fileName) throws IOException {
        System.out.println(res);
        if(fileName.equals(""))
            return;
        File f = new File(fileName);
        if(!f.exists()){
            f.createNewFile();
        }
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(res);
        bw.close();
        fw.close();
    }
}
