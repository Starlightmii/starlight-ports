package z1;

import java.text.CharacterIterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k implements CharacterIterator {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final CharSequence f7118d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f7119e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f7120f = 0;

    public k(CharSequence charSequence, int i5) {
        this.f7118d = charSequence;
        this.f7119e = i5;
    }

    @Override // java.text.CharacterIterator
    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    @Override // java.text.CharacterIterator
    public final char current() {
        int i5 = this.f7120f;
        if (i5 == this.f7119e) {
            return (char) 65535;
        }
        return this.f7118d.charAt(i5);
    }

    @Override // java.text.CharacterIterator
    public final char first() {
        this.f7120f = 0;
        return current();
    }

    @Override // java.text.CharacterIterator
    public final int getBeginIndex() {
        return 0;
    }

    @Override // java.text.CharacterIterator
    public final int getEndIndex() {
        return this.f7119e;
    }

    @Override // java.text.CharacterIterator
    public final int getIndex() {
        return this.f7120f;
    }

    @Override // java.text.CharacterIterator
    public final char last() {
        int i5 = this.f7119e;
        if (i5 == 0) {
            this.f7120f = i5;
            return (char) 65535;
        }
        int i6 = i5 - 1;
        this.f7120f = i6;
        return this.f7118d.charAt(i6);
    }

    @Override // java.text.CharacterIterator
    public final char next() {
        int i5 = this.f7120f + 1;
        this.f7120f = i5;
        int i6 = this.f7119e;
        if (i5 < i6) {
            return this.f7118d.charAt(i5);
        }
        this.f7120f = i6;
        return (char) 65535;
    }

    @Override // java.text.CharacterIterator
    public final char previous() {
        int i5 = this.f7120f;
        if (i5 <= 0) {
            return (char) 65535;
        }
        int i6 = i5 - 1;
        this.f7120f = i6;
        return this.f7118d.charAt(i6);
    }

    @Override // java.text.CharacterIterator
    public final char setIndex(int i5) {
        if (i5 > this.f7119e || i5 < 0) {
            throw new IllegalArgumentException("invalid position");
        }
        this.f7120f = i5;
        return current();
    }
}
