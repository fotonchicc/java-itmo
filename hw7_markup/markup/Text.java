package markup;

public class Text implements Markup {

    private final String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder textToMarkdown) {
        textToMarkdown.append(text);
    }

    @Override
    public void toTypst(StringBuilder textToTypst) {
        textToTypst.append(text);
    }
}
