package q1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b1 extends ViewGroup {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashMap f5124d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashMap f5125e;

    public b1(Context context) {
        super(context);
        setClipChildren(false);
        this.f5124d = new HashMap();
        this.f5125e = new HashMap();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public final HashMap<Object, p1.d0> getHolderToLayoutNode() {
        return this.f5124d;
    }

    public final HashMap<p1.d0, Object> getLayoutNodeToHolder() {
        return this.f5125e;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final /* bridge */ /* synthetic */ ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onDescendantInvalidated(View view, View view2) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i5, int i6, int i7, int i8) {
        Iterator it = this.f5124d.keySet().iterator();
        if (it.hasNext()) {
            it.next().getClass();
            throw new ClassCastException();
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i5, int i6) {
        if (View.MeasureSpec.getMode(i5) != 1073741824) {
            d4.a.K("widthMeasureSpec should be EXACTLY");
            throw null;
        }
        if (View.MeasureSpec.getMode(i6) != 1073741824) {
            d4.a.K("heightMeasureSpec should be EXACTLY");
            throw null;
        }
        setMeasuredDimension(View.MeasureSpec.getSize(i5), View.MeasureSpec.getSize(i6));
        Iterator it = this.f5124d.keySet().iterator();
        if (it.hasNext()) {
            it.next().getClass();
            throw new ClassCastException();
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        cleanupLayoutState(this);
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            p1.d0 d0Var = (p1.d0) this.f5124d.get(childAt);
            if (childAt.isLayoutRequested() && d0Var != null) {
                p1.d0.Q(d0Var, false, 7);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
