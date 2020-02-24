package leet;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Solution535 {
    public static void main(String[] args) {

    }


}

class Codec {
    private Map<String, String> map = new HashMap<>();
    private final String all_chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String tinyurl = "http://tinyurl.com";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        while (true) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                int rand_index = new Random().nextInt(all_chars.length());
                char c = all_chars.charAt(rand_index);
                str.append(c);
            }
            String final_url = tinyurl + str.toString();
            if (!map.containsKey(final_url)) {
                map.put(final_url, longUrl);
                return final_url;
            }
        }
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }
}
