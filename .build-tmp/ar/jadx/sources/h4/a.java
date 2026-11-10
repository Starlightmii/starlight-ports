package h4;

import b4.l;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a implements Iterator, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final CharSequence f2623d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2624e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2625f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2626g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2627h;

    public a(CharSequence charSequence) {
        l.e("string", charSequence);
        this.f2623d = charSequence;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i5;
        int i6;
        int i7 = this.f2624e;
        if (i7 != 0) {
            return i7 == 1;
        }
        if (this.f2627h < 0) {
            this.f2624e = 2;
            return false;
        }
        CharSequence charSequence = this.f2623d;
        int length = charSequence.length();
        int length2 = charSequence.length();
        for (int i8 = this.f2625f; i8 < length2; i8++) {
            char cCharAt = charSequence.charAt(i8);
            if (cCharAt == '\n' || cCharAt == '\r') {
                i5 = (cCharAt == '\r' && (i6 = i8 + 1) < charSequence.length() && charSequence.charAt(i6) == '\n') ? 2 : 1;
                length = i8;
                this.f2624e = 1;
                this.f2627h = i5;
                this.f2626g = length;
                return true;
            }
        }
        i5 = -1;
        this.f2624e = 1;
        this.f2627h = i5;
        this.f2626g = length;
        return true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f2624e = 0;
        int i5 = this.f2626g;
        int i6 = this.f2625f;
        this.f2625f = this.f2627h + i5;
        return this.f2623d.subSequence(i6, i5).toString();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
