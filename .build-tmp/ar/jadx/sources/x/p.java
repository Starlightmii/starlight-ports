package x;

import android.view.View;
import android.view.contentcapture.ContentCaptureSession;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f6476a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f6477b;

    public p(View view) {
        this.f6477b = view;
        this.f6476a = h1.c.D(new b0.a(27, this));
    }

    public p(ContentCaptureSession contentCaptureSession, View view) {
        this.f6476a = contentCaptureSession;
        this.f6477b = view;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, p3.d] */
    public InputMethodManager a() {
        return (InputMethodManager) this.f6476a.getValue();
    }
}
