package z1;

import android.text.StaticLayout;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class s {
    public static final boolean a(StaticLayout staticLayout) {
        return staticLayout.isFallbackLineSpacingEnabled();
    }

    public static final void b(StaticLayout.Builder builder, int i5, int i6) {
        builder.setLineBreakConfig(x.f.a().setLineBreakStyle(i5).setLineBreakWordStyle(i6).build());
    }
}
