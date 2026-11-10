package a2;

import java.text.BreakIterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e extends a.a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final BreakIterator f42l;

    public e(CharSequence charSequence) {
        BreakIterator characterInstance = BreakIterator.getCharacterInstance();
        characterInstance.setText(charSequence.toString());
        this.f42l = characterInstance;
    }

    @Override // a.a
    public final int F(int i5) {
        return this.f42l.following(i5);
    }

    @Override // a.a
    public final int H(int i5) {
        return this.f42l.preceding(i5);
    }
}
