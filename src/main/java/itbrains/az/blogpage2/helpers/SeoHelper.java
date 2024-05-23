package itbrains.az.blogpage2.helpers;

import java.util.Locale;

public class SeoHelper {

    public String seoUrlHelper(String text) {
        String[] change = text.toLowerCase()
                .replaceAll("ə", "e")
                .replaceAll("ç", "c")
                .replaceAll("ö", "o")
                .replaceAll("ö", "o")
                .replaceAll("ş", "s")
                .split(" ");
        String result = String.join("-",change);

        return result.replaceAll("[^a-z0-9-]","");

    }
}    