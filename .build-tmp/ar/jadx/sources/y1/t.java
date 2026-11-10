package y1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final t f6820b = new t(false);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f6821a;

    public t() {
        this.f6821a = false;
    }

    public t(boolean z2) {
        this.f6821a = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof t) {
            return this.f6821a == ((t) obj).f6821a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(0) + (Boolean.hashCode(this.f6821a) * 31);
    }

    public final String toString() {
        return "PlatformParagraphStyle(includeFontPadding=" + this.f6821a + ", emojiSupportMatch=EmojiSupportMatch.Default)";
    }
}
