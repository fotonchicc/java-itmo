package markup;

import java.util.List;

public class Paragraph implements Markup {
    private final List<Markup> list;

    public Paragraph(List<Markup> list) {
        this.list = list;
    }

    @Override
    public void toMarkdown(StringBuilder textToMarkdown) {
        for (Markup item : list) {
            item.toMarkdown(textToMarkdown);
        }
    }

    @Override
    public void toTypst(StringBuilder textToTypst) {
        for (Markup item : list) {
            item.toTypst(textToTypst);
        }
    }
}
