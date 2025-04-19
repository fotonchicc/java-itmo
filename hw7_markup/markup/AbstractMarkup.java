package markup;

import java.util.List;

public abstract class AbstractMarkup implements Markup {

    private final List<Markup> list;
    private final String markdownElem;
    private final String startTypst;
    private final String endTypst;

    protected AbstractMarkup(List<Markup> list, String markdownElem, String startTypst, String endTypst) {
        this.list = list;
        this.markdownElem = markdownElem;
        this.startTypst = startTypst;
        this.endTypst = endTypst;
    }

    @Override
    public void toMarkdown(StringBuilder textToMarkdown) {
        textToMarkdown.append(markdownElem);
        for (Markup item : list) {
            item.toMarkdown(textToMarkdown);
        }
        textToMarkdown.append(markdownElem);
    }

    @Override
    public void toTypst(StringBuilder textToTypst) {
        textToTypst.append(startTypst);
        for (Markup item : list) {
            item.toTypst(textToTypst);
        }
        textToTypst.append(endTypst);
    }
}

