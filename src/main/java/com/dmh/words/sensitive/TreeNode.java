package com.dmh.words.sensitive;

import lombok.ToString;

import java.util.HashMap;
import java.util.Map;


@ToString
public class TreeNode {

    private boolean isEnd = false;

    private Map<Character, TreeNode> children = new HashMap<>();

    public void addNode(Character key, TreeNode node) {
        setWordsEnd(false);
        children.put(key, node);
    }

    public TreeNode getNode(Character key) {
        return children.get(key);
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void  setWordsEnd(Boolean isEnd) {
        this.isEnd = isEnd;
    }
}
