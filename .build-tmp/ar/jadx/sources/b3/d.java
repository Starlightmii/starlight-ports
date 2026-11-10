package b3;

import android.text.TextPaint;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d implements g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ThreadLocal f605b = new ThreadLocal();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextPaint f606a;

    public d() {
        TextPaint textPaint = new TextPaint();
        this.f606a = textPaint;
        textPaint.setTextSize(10.0f);
    }
}
