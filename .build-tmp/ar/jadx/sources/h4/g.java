package h4;

import b4.l;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class g extends f {
    public static boolean Q(CharSequence charSequence, char c5) {
        l.e("<this>", charSequence);
        return S(charSequence, c5, 0, 2) >= 0;
    }

    public static final int R(String str, String str2, int i5, boolean z2) {
        String str3;
        String str4;
        boolean z3;
        boolean zRegionMatches;
        l.e("string", str2);
        if (!z2) {
            return str.indexOf(str2, i5);
        }
        int length = str.length();
        if (i5 < 0) {
            i5 = 0;
        }
        int length2 = str.length();
        if (length > length2) {
            length = length2;
        }
        int i6 = new e4.d(i5, length, 1).f2075e;
        if (i5 > i6) {
            return -1;
        }
        int i7 = i5;
        while (true) {
            int length3 = str2.length();
            if (z2) {
                str3 = str;
                str4 = str2;
                z3 = z2;
                zRegionMatches = str4.regionMatches(z3, 0, str3, i7, length3);
            } else {
                zRegionMatches = str2.regionMatches(0, str, i7, length3);
                str3 = str;
                str4 = str2;
                z3 = z2;
            }
            if (zRegionMatches) {
                return i7;
            }
            if (i7 == i6) {
                return -1;
            }
            i7++;
            str2 = str4;
            z2 = z3;
            str = str3;
        }
    }

    public static int S(CharSequence charSequence, char c5, int i5, int i6) {
        if ((i6 & 2) != 0) {
            i5 = 0;
        }
        l.e("<this>", charSequence);
        if (charSequence instanceof String) {
            return ((String) charSequence).indexOf(c5, i5);
        }
        char[] cArr = {c5};
        if (charSequence instanceof String) {
            return ((String) charSequence).indexOf(cArr[0], i5);
        }
        if (i5 < 0) {
            i5 = 0;
        }
        int length = charSequence.length() - 1;
        if (i5 > length) {
            return -1;
        }
        while (cArr[0] != charSequence.charAt(i5)) {
            if (i5 == length) {
                return -1;
            }
            i5++;
        }
        return i5;
    }

    public static boolean T(String str) {
        l.e("<this>", str);
        for (int i5 = 0; i5 < str.length(); i5++) {
            if (!h1.c.B(str.charAt(i5))) {
                return false;
            }
        }
        return true;
    }

    public static String U() {
        char cCharAt = "H".charAt(0);
        char[] cArr = new char[10];
        for (int i5 = 0; i5 < 10; i5++) {
            cArr[i5] = cCharAt;
        }
        return new String(cArr);
    }

    public static String V(String str, String str2) {
        l.e("delimiter", str2);
        int iR = R(str, str2, 0, false);
        if (iR == -1) {
            return str;
        }
        String strSubstring = str.substring(str2.length() + iR, str.length());
        l.d("substring(...)", strSubstring);
        return strSubstring;
    }

    public static CharSequence W(String str) {
        l.e("<this>", str);
        int length = str.length() - 1;
        int i5 = 0;
        boolean z2 = false;
        while (i5 <= length) {
            boolean zB = h1.c.B(str.charAt(!z2 ? i5 : length));
            if (z2) {
                if (!zB) {
                    break;
                }
                length--;
            } else if (zB) {
                i5++;
            } else {
                z2 = true;
            }
        }
        return str.subSequence(i5, length + 1);
    }
}
