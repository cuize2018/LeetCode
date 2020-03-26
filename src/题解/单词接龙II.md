# BFS

思路说明：在到达最短路径所在的层时，记录并输出所有符合条件的路径。

1. 在单词接龙的基础上，需要将找到的最短路径存储下来；
2. 之前的队列只用来存储每层的元素，那么现在就得存储每层添加元素之后的结果："ab","if",{"cd","af","ib","if"}；
   - 第一层：{"ab"}
   - 第二层：{"ab","af"}、{"ab","ib"}
   - 第三层：{"ab","af","if"}、{"ab","ib","if"}
3. 如果该层添加的某一个单词符合目标单词，则该路径为最短路径，该层为最短路径所在的层，但此时不能直接返回结果，必须将该层遍历完，将该层所有符合的结果都添加进结果集;
4. 每层添加单词的时候，不能直接添加到总的已访问单词集合中，需要每层有一个单独的该层访问的单词集，该层结束之后，再会合到总的已访问单词集合中，原因就是因为3.

```java
public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    List<List<String>> out = new ArrayList<>();
    Set<String> dict = new HashSet<>(wordList);
    // 累积每一层的结果队列
    Queue<List<String>> queue = new ArrayDeque<>();
    // 已经访问过的单词集合：只找最短路径，所以之前出现过的单词不用出现在下一层
    Set<String> visited = new HashSet<>();
    // 字典中不包含目标单词
    if (!dict.contains(endWord))return out;

    List<String> list = new ArrayList<>(Arrays.asList(beginWord));
    queue.add(list);
    visited.add(beginWord);

    // 是否到达符合条件的层：如果该层添加的某一单词符合目标单词，则说明截止该层的所有解为最短路径，停止循环
    boolean flag = false;
    while (!queue.isEmpty() && !flag) {
        // 上一层的结果队列
        int size = queue.size();

        // 该层添加的所有元素：每层必须在所有结果都添加完新的单词之后，再将这些单词统一添加到已使用单词集合
        // 如果直接添加到 visited 中，会导致该层本次结果添加之后的相同添加行为失败
        // 如：该层遇到目标单词，有两条路径都可以遇到，但是先到达的将该单词添加进 visited 中，会导致第二条路径无法添加
        Set<String> subVisited = new HashSet<>();

        for (int i = 0; i < size; i++) {
            List<String> path = queue.remove();
            // 获取该路径上一层的单词
            String lastWord = path.get(path.size() - 1);

            // 寻找该单词的所有下一个符合条件的单词
            List<String> neighbors = getNeighbors(lastWord, dict);
            for (String str : neighbors) {
                if (dict.contains(str) && !visited.contains(str)) {
                    // 生成新的路径
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(str);
                    // 如果该单词是目标单词：将该路径添加到结果集中，查询截止到该层
                    if (str.equals(endWord)) {
                        flag = true;
                        out.add(newPath);
                    }
                    // 将该路径添加到该层队列中
                    queue.add(newPath);
                    // 将该单词添加到该层已访问的单词集合中
                    subVisited.add(str);
                }
            }
        }
        // 将该层所有访问的单词添加到总的已访问集合中
        visited.addAll(subVisited);
    }
    return out;
}

private static ArrayList<String> getNeighbors(String node, Set<String> dict) {
    ArrayList<String> res = new ArrayList<>();
    char chs[] = node.toCharArray();
    for (char ch = 'a'; ch <= 'z'; ch++) {
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == ch)
                continue;
            char old_ch = chs[i];
            chs[i] = ch;
            if (dict.contains(String.valueOf(chs))) {
                res.add(String.valueOf(chs));
            }
            chs[i] = old_ch;
        }
    }
    return res;
}
```