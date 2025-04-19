public class Sum {
    public static void main(String[] args) {
        int sum = 0;
        int inx = 0;
        boolean wasSpace = false;
        for (String arg : args) {
            for (int i = 0; i < arg.length(); i ++) {
                if (!Character.isWhitespace(arg.charAt(i))) {
                    if (i != arg.length() - 1) {
                        wasSpace = false;
                    } else {
                        sum += Integer.parseInt(arg.substring(inx));
                    }
                } else if (!wasSpace) {
                    if (i == 0) {
                        inx = i + 1;
                        wasSpace = true;
                        continue;
                    }
                    sum += Integer.parseInt(arg.substring(inx, i));
                    inx = i + 1;
                    wasSpace = true;
                } else {
                    inx = i + 1;
                }
            }
        }
        System.out.println(sum);
    }
}
