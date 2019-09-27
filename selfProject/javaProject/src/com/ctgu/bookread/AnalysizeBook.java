package com.ctgu.bookread;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class AnalysizeBook extends ReadBook{

    public Map<String, String> getOperation(String[] args){
        Map<String, String>  operator = new HashMap<>();
        String regex = "^-.";
        String num = "^[0-9]+$";
        for(int i = 0; i < args.length; ++i){
            if(args[i].matches(regex)){
                operator.put(args[i], "");
                continue;
            }
            if(args[i - 1].equals("-n") && !args[i].matches(num))
                continue;
            operator.put(args[i - 1], args[i]);
        }
        return operator;
    }



    public void achieveOperation(String[] args) throws IOException {
        Map<String, String> operator = getOperation(args);
        BuyBook buyBook = new BuyBook();
        List<String> book = new ArrayList<>();
        Map<String, String> nomalizeBook = new HashMap<>();


        if(operator.get("-n") != null && !operator.get("-n").equals("")){
            setAmount(Integer.valueOf(operator.get("-n")));
        }

        if(operator.get("-c") != null){
            output(calLetterFrequency(buyBook.getBook(getDirFile(operator.get("-c")))));
            return;
        }
        if(operator.get("-d") != null){
            if(operator.get("-s") != null){
                book = buyBook.getBook(getAllDirFile(operator.get("-s")));
            }else{
                book = buyBook.getBook(getDirFile(operator.get("-d")));
            }
        }
        if(operator.get("-v") != null){
            nomalizeBook = getNormalizeBook(buyBook.getBook(getDirFile(operator.get("-v"))));
            book = toNormalizeSentence(book, nomalizeBook);
        }
        if(operator.get("-f") != null){
            book = getBook(getDirFile(operator.get("-f")));
            if(operator.get("-v") != null)
                book = toNormalizeSentence(book, nomalizeBook);
            if(operator.get("-x") != null) {
                output(wordsFilter(getBook(getDirFile(operator.get("-x"))), calWordsAmount(book)));
            }
            else
                output(calWordsAmount(book));
            return;
        }
        if(operator.get("-p") != null){
            output(getNumPhrase(Integer.valueOf(operator.get("-p")), phraseCount(book, getBook(getDirFile("src/allPhrase")))));
            return;
        }
        if(book != null)
            output(calWordsAmount(book));
        else
            System.out.println("参数输入错误!");
    }

    public static void main(String[] args) throws IOException {
        AnalysizeBook analysizeBook = new AnalysizeBook();
        analysizeBook.achieveOperation(args);
    }
}
