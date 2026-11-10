package x2;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f6550d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final h f6551e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f6552f;

    public a(int i5, h hVar, int i6) {
        this.f6550d = i5;
        this.f6551e = hVar;
        this.f6552f = i6;
    }

    @Override // android.text.style.ClickableSpan
    public final void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f6550d);
        this.f6551e.f6563a.performAction(this.f6552f, bundle);
    }
}
