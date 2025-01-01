package cn.travellerr.qqzone.tokenAlgorithm;

public class TokenGenerator {

    // TODO

    public static int ptQrToken(String t) {
        int e = 0;
        for (int n = 0; n < t.length(); n++) {
            e = (e << 5) + t.charAt(n);
        }
        return e & 2147483647;
    }

    public static int getTk(String skey) {
        int hashValue = 5381;
        for (char c : skey.toCharArray()) {
            hashValue += (hashValue << 5) + (int) c;
        }
        return hashValue & 2147483647;
    }
}
