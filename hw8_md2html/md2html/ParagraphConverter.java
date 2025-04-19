package md2html;

import java.util.Map;

public class ParagraphConverter {
    private static final Map<Character, String> specialSymbols = Map.of('<', "&lt;",
            '>', "&gt;",
            '&', "&amp;");
    private final String paragraph;

    public ParagraphConverter(String paragraph) {
        this.paragraph = paragraph;
    }

    private Integer checkHeader(String paragraph) {
        int level = 0;
        while (level < paragraph.length() && paragraph.charAt(level) == '#') {
            level++;
        }
        if (Character.isWhitespace(paragraph.charAt(level))) {
            return level;
        } else {
            return 0;
        }
    }

    private Integer checkEmphasisMark (String paragraph, StringBuilder text, int i, int len) {
        char currChar = paragraph.charAt(i);
        if (i != 0 && paragraph.charAt(i - 1) == '\\') {
            text.deleteCharAt(text.length() - 1);
            text.append(currChar);
            i++;
        } else if (((i + 1) < len) && (i != 0) && Character.isWhitespace(paragraph.charAt(i - 1)) &&
                Character.isWhitespace(paragraph.charAt(i + 1))) {
            text.append(currChar);
            i++;
        } else if (i == 0 && i + 1 < len && Character.isWhitespace(paragraph.charAt(i + 1))) {
            text.append(currChar);
            i++;
        } else if (i + 1 == len && i != 0 && Character.isWhitespace(paragraph.charAt(i - 1))) {
            text.append(currChar);
            i++;
        } else if (i + 1 == len && i == 0) {
            text.append(currChar);
            i++;
        }
        return i;
    }

    private String checkSpecSymbol(char i) {
        return specialSymbols.getOrDefault(i, "");
    }

    private String convertText(String paragraph) {
        String[] markdownSymbols = {"*", "**", "_", "__", "--", "`", "~"};
        String[] htmlSymbols = {"em>", "strong>", "em>", "strong>", "s>", "code>", "mark>"};
        MdStack stack = new MdStack();
        StringBuilder text = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        int len = paragraph.length();
        int i = 0;
        while (i < len) {
            char current = paragraph.charAt(i);
            if (current == '*' || current == '_' || current == '~') {
                i = checkEmphasisMark(paragraph, temp, i, len);
                if (i >= len) {
                    break;
                }
            }
            boolean found = true;
            for (int md = 0; md < markdownSymbols.length; md++) {
                int lnSym = markdownSymbols[md].length();
                if (lnSym == 2 && i == len - 1) {
                    continue;
                }
                found = true;
                for (int j = 0; j < lnSym; j++) {
                    if (i + j <= paragraph.length() - 1 && paragraph.charAt(i + j) != markdownSymbols[md].charAt(j)) {
                        found = false;
                        break;
                    }
                    if (lnSym == 1 && i + 1 < len && paragraph.charAt(i + 1) == markdownSymbols[md].charAt(0)) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    i += lnSym;
                    if (stack.getSize() > 0) {
                        if (stack.getLastMd().equals(markdownSymbols[md])) {
                            if (stack.getFirstMd().equals(markdownSymbols[md])) {
                                text.setLength(text.length() - markdownSymbols[md].length());
                            }
                            temp = new StringBuilder("<" + htmlSymbols[md] + convertText(paragraph.substring(stack.getLastInx(), i
                                    - lnSym)) + "</" + htmlSymbols[md]);
                            stack.pop();
                        } else {
                            temp.append(markdownSymbols[md]);
                            stack.insert(i, markdownSymbols[md]);
                        }
                    } else {
                        stack.insert(i, markdownSymbols[md]);
                        text.append(temp).append(markdownSymbols[md]);
                        temp.setLength(0);
                    }
                    break;
                }
            } 
            if (!found) {
                if (specialSymbols.containsKey(paragraph.charAt(i))) {
                    temp.append(checkSpecSymbol(paragraph.charAt(i)));
                } else {
                    temp.append((paragraph.charAt(i)));
                }
                i++;
            }
        }
        if (!temp.isEmpty()) {
            text.append(temp);

        }
        return text.toString();
    }

    private void collectText(StringBuilder strToHtml) {
        strToHtml.append("<p>");
        strToHtml.append(convertText(paragraph));
        strToHtml.append("</p>");
    }

    public String convertToHtml() {
        StringBuilder strToHtml = new StringBuilder();
        if (paragraph.startsWith("#")) {
            int level = checkHeader(paragraph);
            if (level != 0) {
                strToHtml.append(String.format("<h%d>", level));
                strToHtml.append(convertText(paragraph.substring(level + 1)));
                strToHtml.append(String.format("</h%d>", level));
            } else {
                collectText(strToHtml);
            }
        } else {
            collectText(strToHtml);
        }
        return strToHtml.toString();
    }
}
