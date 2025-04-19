package markup;

import java.util.List;

public class Emphasis extends AbstractMarkup {

    public Emphasis(List<Markup> text) {
        super(text, "*", "#emph[", "]");
    }

}
