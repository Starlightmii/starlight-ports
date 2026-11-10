package b1;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import q1.q2;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n extends ViewOutlineProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f560a;

    public /* synthetic */ n(int i5) {
        this.f560a = i5;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        Outline outline2;
        switch (this.f560a) {
            case 0:
                if ((view instanceof o) && (outline2 = ((o) view).f566h) != null) {
                    outline.set(outline2);
                    break;
                }
                break;
            case 1:
                outline.setRect(0, 0, view.getWidth(), view.getHeight());
                outline.setAlpha(0.0f);
                break;
            default:
                b4.l.c("null cannot be cast to non-null type androidx.compose.ui.platform.ViewLayer", view);
                Outline outlineB = ((q2) view).f5319h.b();
                b4.l.b(outlineB);
                outline.set(outlineB);
                break;
        }
    }
}
