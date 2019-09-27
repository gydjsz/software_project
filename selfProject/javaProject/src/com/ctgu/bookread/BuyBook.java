package com.ctgu.bookread;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuyBook {

    public List<String> getDirFile(String fileName){
        File file = new File(fileName);
        List<String> pathList = new ArrayList<>();
        if(!file.isDirectory()){
            pathList.add(file.getPath());
            return pathList;
        }
        for(File f : file.listFiles()){
            if(f.isDirectory())
                continue;
            pathList.add(f.getPath());
        }
        return pathList;
    }

    public List<String> getAllDirFile(String fileName){
        File file = new File(fileName);
        List<String> fileList = new ArrayList<>();
        if(!file.isDirectory()){
            fileList.add(file.getPath());
            return fileList;
        }
        for(File f : file.listFiles()){
            if(f.isDirectory()){
                fileList.addAll(getAllDirFile(f.getPath()));
                continue;
            }
            fileList.add(f.getPath());
        }
       return fileList;
    }

    public List<String> getBook(List<String> file) throws IOException {
        List<String> book = new ArrayList<>();
        for(String f : file){
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String s;
            while((s = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(s, ",.\n");
                while(st.hasMoreElements()){
                    String sc = st.nextToken().toLowerCase();
                    book.add(sc);
                }
            }
        }
        return book;
    }

}
