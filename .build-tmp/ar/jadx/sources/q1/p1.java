package q1;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import io.github.andrea_lyz.assistrestore.R;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class p1 extends ViewGroup {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5303d;

    public p1(Context context) {
        super(context);
        setClipChildren(false);
        setTag(R.id.hide_in_inspector_tag, Boolean.TRUE);
    }

    public final void a(y0.r rVar, q2 q2Var, long j5) {
        super.drawChild(y0.e.a(rVar), q2Var, j5);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        int childCount = super.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            b4.l.c("null cannot be cast to non-null type androidx.compose.ui.platform.ViewLayer", childAt);
            if (((q2) childAt).f5322k) {
                this.f5303d = true;
                try {
                    super.dispatchDraw(canvas);
                    return;
                } finally {
                    this.f5303d = false;
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    public int getChildCount() {
        if (this.f5303d) {
            return super.getChildCount();
        }
        return 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i5, int i6, int i7, int i8) {
    }

    @Override // android.view.View
    public final void onMeasure(int i5, int i6) {
        setMeasuredDimension(0, 0);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
    }
}
