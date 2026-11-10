package a2;

import e2.l;
import java.text.BreakIterator;
import java.util.Locale;
import z1.k;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f43a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f44b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f45c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CharSequence f46d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f47e;

    public /* synthetic */ g() {
    }

    public g(CharSequence charSequence, int i5, Locale locale) {
        this.f46d = charSequence;
        if (charSequence.length() < 0) {
            throw new IllegalArgumentException("input start index is outside the CharSequence");
        }
        if (i5 < 0 || i5 > charSequence.length()) {
            throw new IllegalArgumentException("input end index is outside the CharSequence");
        }
        BreakIterator wordInstance = BreakIterator.getWordInstance(locale);
        this.f47e = wordInstance;
        this.f44b = Math.max(0, -50);
        this.f45c = Math.min(charSequence.length(), i5 + 50);
        wordInstance.setText(new k(charSequence, i5));
    }

    public void a(int i5) {
        int i6 = this.f44b;
        int i7 = this.f45c;
        if (i5 > i7 || i6 > i5) {
            StringBuilder sb = new StringBuilder("Invalid offset: ");
            sb.append(i5);
            sb.append(". Valid range is [");
            sb.append(i6);
            sb.append(" , ");
            throw new IllegalArgumentException(b.b.f(sb, i7, ']').toString());
        }
    }

    public int b() {
        l lVar = (l) this.f47e;
        if (lVar == null) {
            return ((String) this.f46d).length();
        }
        return (lVar.f2033b - lVar.b()) + (((String) this.f46d).length() - (this.f45c - this.f44b));
    }

    public boolean c(int i5) {
        return i5 <= this.f45c && this.f44b + 1 <= i5 && Character.isLetterOrDigit(Character.codePointBefore(this.f46d, i5));
    }

    public boolean d(int i5) {
        int i6 = this.f44b + 1;
        if (i5 > this.f45c || i6 > i5) {
            return false;
        }
        return d4.a.z(Character.codePointBefore(this.f46d, i5));
    }

    public boolean e(int i5) {
        return i5 < this.f45c && this.f44b <= i5 && Character.isLetterOrDigit(Character.codePointAt(this.f46d, i5));
    }

    public boolean f(int i5) {
        int i6 = this.f44b;
        if (i5 >= this.f45c || i6 > i5) {
            return false;
        }
        return d4.a.z(Character.codePointAt(this.f46d, i5));
    }

    public void g(int i5, int i6, String str) {
        if (i5 > i6) {
            throw new IllegalArgumentException(b.b.d("start index must be less than or equal to end index: ", i5, " > ", i6).toString());
        }
        if (i5 < 0) {
            throw new IllegalArgumentException(("start must be non-negative, but was " + i5).toString());
        }
        l lVar = (l) this.f47e;
        if (lVar == null) {
            int iMax = Math.max(255, str.length() + 128);
            char[] cArr = new char[iMax];
            int iMin = Math.min(i5, 64);
            int iMin2 = Math.min(((String) this.f46d).length() - i6, 64);
            String str2 = (String) this.f46d;
            int i7 = i5 - iMin;
            b4.l.c("null cannot be cast to non-null type java.lang.String", str2);
            str2.getChars(i7, i5, cArr, 0);
            String str3 = (String) this.f46d;
            int i8 = iMax - iMin2;
            int i9 = iMin2 + i6;
            b4.l.c("null cannot be cast to non-null type java.lang.String", str3);
            str3.getChars(i6, i9, cArr, i8);
            str.getChars(0, str.length(), cArr, iMin);
            int length = str.length() + iMin;
            l lVar2 = new l();
            lVar2.f2033b = iMax;
            lVar2.f2036e = cArr;
            lVar2.f2034c = length;
            lVar2.f2035d = i8;
            this.f47e = lVar2;
            this.f44b = i7;
            this.f45c = i9;
            return;
        }
        int i10 = this.f44b;
        int i11 = i5 - i10;
        int i12 = i6 - i10;
        if (i11 < 0 || i12 > lVar.f2033b - lVar.b()) {
            this.f46d = toString();
            this.f47e = null;
            this.f44b = -1;
            this.f45c = -1;
            g(i5, i6, str);
            return;
        }
        int length2 = str.length() - (i12 - i11);
        if (length2 > lVar.b()) {
            int iB = length2 - lVar.b();
            int i13 = lVar.f2033b;
            do {
                i13 *= 2;
            } while (i13 - lVar.f2033b < iB);
            char[] cArr2 = new char[i13];
            q3.k.x((char[]) lVar.f2036e, cArr2, 0, 0, lVar.f2034c);
            int i14 = lVar.f2033b;
            int i15 = lVar.f2035d;
            int i16 = i14 - i15;
            int i17 = i13 - i16;
            q3.k.x((char[]) lVar.f2036e, cArr2, i17, i15, i16 + i15);
            lVar.f2036e = cArr2;
            lVar.f2033b = i13;
            lVar.f2035d = i17;
        }
        int i18 = lVar.f2034c;
        if (i11 < i18 && i12 <= i18) {
            int i19 = i18 - i12;
            char[] cArr3 = (char[]) lVar.f2036e;
            q3.k.x(cArr3, cArr3, lVar.f2035d - i19, i12, i18);
            lVar.f2034c = i11;
            lVar.f2035d -= i19;
        } else if (i11 >= i18 || i12 < i18) {
            int iB2 = lVar.b() + i11;
            int iB3 = lVar.b() + i12;
            int i20 = lVar.f2035d;
            char[] cArr4 = (char[]) lVar.f2036e;
            q3.k.x(cArr4, cArr4, lVar.f2034c, i20, iB2);
            lVar.f2034c += iB2 - i20;
            lVar.f2035d = iB3;
        } else {
            lVar.f2035d = lVar.b() + i12;
            lVar.f2034c = i11;
        }
        str.getChars(0, str.length(), (char[]) lVar.f2036e, lVar.f2034c);
        lVar.f2034c = str.length() + lVar.f2034c;
    }

    public String toString() {
        switch (this.f43a) {
            case 1:
                l lVar = (l) this.f47e;
                if (lVar == null) {
                    return (String) this.f46d;
                }
                StringBuilder sb = new StringBuilder();
                sb.append(this.f46d, 0, this.f44b);
                sb.append((char[]) lVar.f2036e, 0, lVar.f2034c);
                char[] cArr = (char[]) lVar.f2036e;
                int i5 = lVar.f2035d;
                sb.append(cArr, i5, lVar.f2033b - i5);
                String str = (String) this.f46d;
                sb.append((CharSequence) str, this.f45c, str.length());
                return sb.toString();
            default:
                return super.toString();
        }
    }
}
