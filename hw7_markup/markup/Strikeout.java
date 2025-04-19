package markup;

import java.util.List;

public class Strikeout extends AbstractMarkup {

    public Strikeout(List<Markup> text) {
        super(text, "~", "#strike[", "]");
    }
}


