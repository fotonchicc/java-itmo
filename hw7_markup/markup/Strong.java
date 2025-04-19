package markup;

import java.util.List;

public class Strong extends AbstractMarkup {

    public Strong(List<Markup> text) {
        super(text, "__", "#strong[", "]");
    }
}
