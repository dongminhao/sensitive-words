package com.dmh.words.sensitive;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class SensitiveWordsUtils {

    private TreeNode rootNode = new TreeNode();

    /**
     * 初始化敏感词树
     * @param sensitiveWords 敏感词集合
     */
    public void initTree(List<String> sensitiveWords){
        for (String sensitiveWord : sensitiveWords) {
            addSensitiveWord(sensitiveWord);
        }
    }


    /**
     * 添加单个敏感词
     * @param sensitiveWord 敏感词
     */
    public void addSensitiveWord(String sensitiveWord) {
        TreeNode currentNodes = rootNode;
        for (int i = 0; i < sensitiveWord.length(); i++) {
            char c = sensitiveWord.charAt(i);
            if (currentNodes.getNode(c) != null) {
                currentNodes = currentNodes.getNode(c);
            } else {
                TreeNode node = new TreeNode();
                if (i == sensitiveWord.length() - 1) {
                    node.setWordsEnd(true);
                }
                currentNodes.addNode(c, node);
                currentNodes = node;
            }
        }
    }


    /**
     * 过滤敏感词
     * @param text 原字符
     * @param replace 替换标识
     * @return 返回过滤后的字符
     */
    public String filter(String text, String replace) {
        if (StringUtils.isEmpty(text.trim())) {
            return text;
        }
        StringBuilder result = new StringBuilder();

        TreeNode tempNode = rootNode;
        int begin = 0, position = 0;
        while (position < text.length()) {
            char c = text.charAt(position);
            tempNode = tempNode.getNode(c);
            if (tempNode == null) {
                result.append(text.charAt(begin));
                begin++;
                position = begin;
                tempNode = rootNode;
            } else if (tempNode.isEnd()) {
                position++;
                result.append(String.valueOf(replace).repeat(Math.max(0, position - begin)));
                begin = position;
                tempNode = rootNode;
            } else {
                position++;
            }
        }
        result.append(text.substring(begin));
        return result.toString();
    }

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("words.txt")));
        try{
            for(String line = reader.readLine(); line != null; line = reader.readLine()){
                words.add(line);
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        SensitiveWordsUtils wordsUtils = new SensitiveWordsUtils();
        wordsUtils.initTree(words);
        System.out.println(wordsUtils.filter("责责责责责责有攸归责责责", "*"));
        wordsUtils.addSensitiveWord("厉害啊");
        System.out.println(wordsUtils.filter("责厉害啊责责责责责有攸归责责责", "*"));
    }


}
