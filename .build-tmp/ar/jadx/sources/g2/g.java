package g2;

import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class g extends ClickableSpan {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final y1.l f2539d;

    public g(y1.l lVar) {
        this.f2539d = lVar;
    }

    @Override // android.text.style.ClickableSpan
    public final void onClick(View view) {
        this.f2539d.getClass();
    }
}
