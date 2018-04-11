import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> fullJustify = new ArrayList<>();
        List<String> wordsInLine = new ArrayList<>();
        if (maxWidth <= 1 || words.length <= 1) {
            for (String word : words) {
                fullJustify.add(buildLine(Collections.singletonList(word), word.length(), maxWidth, false));
            }
            return fullJustify;
        }

        int index = 0;
        int currentLineLength = 0;
        while (index < words.length) {
            if (currentLineLength + wordsInLine.size() + words[index].length() <= maxWidth) {
                wordsInLine.add(words[index]);
                currentLineLength += words[index].length();
                index++;
            } else {
                fullJustify.add(buildLine(wordsInLine, currentLineLength, maxWidth, false));
                wordsInLine = new ArrayList<>();
                currentLineLength = 0;
            }
        }
        fullJustify.add(buildLine(wordsInLine, currentLineLength, maxWidth, true));

        return fullJustify;
    }

    private String buildLine(List<String> wordsInLine, int currentLineLength, int maxWidth, boolean lastLine) {
        StringBuilder line = new StringBuilder();
        if (lastLine) {
            for (String aWordsInLine : wordsInLine) {
                line.append(aWordsInLine);
                if (line.length() < maxWidth) {
                    line.append(" ");
                }
            }
            for (int i = line.length(); i < maxWidth; i++) {
                line.append(" ");
            }
        } else {
            int numberOfWords = wordsInLine.size() > 1 ? wordsInLine.size() - 1 : 1;
            int numberOfSpaces = maxWidth - currentLineLength;
            int extraSpace = numberOfSpaces % numberOfWords;
            int spacesBetweenEachWord = (numberOfSpaces - extraSpace) / numberOfWords;
            for (String aWordsInLine : wordsInLine) {
                line.append(aWordsInLine);
                if (line.length() < maxWidth) {
                    if (extraSpace != 0) {
                        line.append(" ");
                        extraSpace--;
                    }
                    for (int i = 0; i < spacesBetweenEachWord; i++) {
                        line.append(" ");
                    }
                }
            }
        }

        return line.toString();
    }

    public static void main(String[] args) {
        String[] words = {"Here","is","an","example","of","text","justification."};
        Solution solution = new Solution();
        System.out.println(solution.fullJustify(words, 20));
    }
}