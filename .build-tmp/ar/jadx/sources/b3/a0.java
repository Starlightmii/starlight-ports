package b3;

import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import androidx.lifecycle.k0;
import java.util.stream.IntStream;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a0 implements Spannable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f603d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Spannable f604e;

    public a0(Spannable spannable) {
        this.f604e = spannable;
    }

    public final void a() {
        Spannable spannable = this.f604e;
        if (!this.f603d) {
            if ((Build.VERSION.SDK_INT < 28 ? new k0(9) : new z(9)).m(spannable)) {
                this.f604e = new SpannableString(spannable);
            }
        }
        this.f603d = true;
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i5) {
        return this.f604e.charAt(i5);
    }

    @Override // java.lang.CharSequence
    public final IntStream chars() {
        return this.f604e.chars();
    }

    @Override // java.lang.CharSequence
    public final IntStream codePoints() {
        return this.f604e.codePoints();
    }

    @Override // android.text.Spanned
    public final int getSpanEnd(Object obj) {
        return this.f604e.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public final int getSpanFlags(Object obj) {
        return this.f604e.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public final int getSpanStart(Object obj) {
        return this.f604e.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    public final Object[] getSpans(int i5, int i6, Class cls) {
        return this.f604e.getSpans(i5, i6, cls);
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.f604e.length();
    }

    @Override // android.text.Spanned
    public final int nextSpanTransition(int i5, int i6, Class cls) {
        return this.f604e.nextSpanTransition(i5, i6, cls);
    }

    @Override // android.text.Spannable
    public final void removeSpan(Object obj) {
        a();
        this.f604e.removeSpan(obj);
    }

    @Override // android.text.Spannable
    public final void setSpan(Object obj, int i5, int i6, int i7) {
        a();
        this.f604e.setSpan(obj, i5, i6, i7);
    }

    @Override // java.lang.CharSequence
    public final CharSequence subSequence(int i5, int i6) {
        return this.f604e.subSequence(i5, i6);
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        return this.f604e.toString();
    }
}
