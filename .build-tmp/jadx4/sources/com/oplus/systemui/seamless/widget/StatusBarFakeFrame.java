package com.oplus.systemui.seamless.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.android.systemui.plugins.qs.QSFakeStatusElement;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.systemui.seamless.FakeType;
import com.oplusos.systemui.common.rendercache.RenderBooster;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class StatusBarFakeFrame extends FrameLayout implements QSFakeStatusElement {
    public static final Companion Companion = new Companion();
    public static final String TAG = "FakeFrame";
    public boolean hasBind;
    private final ViewTreeObserver.OnDrawListener mOnDrawListener;
    private View mView;
    private final View.OnLayoutChangeListener onLayoutChangeListener;
    private FakeType type;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StatusBarFakeFrame(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        context.getClass();
        this.type = FakeType.FAKE_CARRIER;
        StatusBarFakeFrame$mOnDrawListener$1 statusBarFakeFrame$mOnDrawListener$1 = new StatusBarFakeFrame$mOnDrawListener$1();
        statusBarFakeFrame$mOnDrawListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mOnDrawListener = statusBarFakeFrame$mOnDrawListener$1;
        StatusBarFakeFrame$onLayoutChangeListener$1 statusBarFakeFrame$onLayoutChangeListener$1 = new StatusBarFakeFrame$onLayoutChangeListener$1();
        statusBarFakeFrame$onLayoutChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.onLayoutChangeListener = statusBarFakeFrame$onLayoutChangeListener$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0019, code lost:
    
        r3 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static float getViewCoordinate(android.view.View r3, kotlin.jvm.functions.Function1 r4) {
        /*
            if (r3 == 0) goto L33
            java.lang.Object r0 = r4.invoke(r3)
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            android.view.ViewParent r3 = r3.getParent()
            boolean r1 = r3 instanceof android.view.View
            r2 = 0
            if (r1 == 0) goto L18
            android.view.View r3 = (android.view.View) r3
            goto L19
        L18:
            r3 = r2
        L19:
            if (r3 == 0) goto L31
            java.lang.Object r1 = r4.invoke(r3)
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            int r0 = r0 + r1
            android.view.ViewParent r3 = r3.getParent()
            boolean r1 = r3 instanceof android.view.View
            if (r1 == 0) goto L18
            android.view.View r3 = (android.view.View) r3
            goto L19
        L31:
            float r3 = (float) r0
            return r3
        L33:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.seamless.widget.StatusBarFakeFrame.getViewCoordinate(android.view.View, kotlin.jvm.functions.Function1):float");
    }

    public void bindRealView() {
        View view;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "FakeFrame-->".concat("bindRealView realView:" + this.mView));
        }
        this.hasBind = true;
        unregisterRealViewListener();
        if (isAttachedToWindow() && (view = this.mView) != null) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver != null) {
                viewTreeObserver.addOnDrawListener(this.mOnDrawListener);
            }
            View view2 = this.mView;
            if (view2 != null) {
                view2.addOnLayoutChangeListener(this.onLayoutChangeListener);
            }
        }
        resetLayoutParams();
    }

    public final void clearFakeView() {
        unregisterRealViewListener();
        this.mView = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        View view = this.mView;
        if (view != null) {
            view.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public final float getAbsoluteLeft(View view) {
        return getViewCoordinate(view, new StatusBarFakeFrame$$ExternalSyntheticLambda0(2));
    }

    public final float getAbsoluteTranslationX(View view) {
        return getViewCoordinate(view, new StatusBarFakeFrame$$ExternalSyntheticLambda0(1));
    }

    public final boolean getHasBind() {
        return this.hasBind;
    }

    public final View getMView() {
        return this.mView;
    }

    public float getRealStatusElementLocX() {
        int[] locationOnScreen;
        View view = this.mView;
        if (view == null || (locationOnScreen = view.getLocationOnScreen()) == null) {
            return 0.0f;
        }
        return locationOnScreen[0];
    }

    public float getRealStatusElementLocY() {
        return getViewCoordinate(this.mView, new StatusBarFakeFrame$$ExternalSyntheticLambda0(0));
    }

    public int getRealStatusElementPaddingStart() {
        View view = this.mView;
        if (view != null) {
            return view.getPaddingStart();
        }
        return 0;
    }

    public Pair<Integer, Integer> getRealStatusElementParams() {
        View view = this.mView;
        Integer numValueOf = Integer.valueOf(view != null ? view.getWidth() : 0);
        View view2 = this.mView;
        return new Pair<>(numValueOf, Integer.valueOf(view2 != null ? view2.getHeight() : 0));
    }

    public final FakeType getType() {
        return this.type;
    }

    public void resetLayoutParams() {
        View view = this.mView;
        if (view != null) {
            if (view.getWidth() != getWidth() || view.getHeight() != getHeight()) {
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(view.getWidth(), view.getHeight());
                layoutParams.gravity = 16;
                setLayoutParams(layoutParams);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    FakeType fakeType = this.type;
                    int width = getWidth();
                    int height = getHeight();
                    StringBuilder sb = new StringBuilder("resetLayoutParams type:");
                    sb.append(fakeType);
                    sb.append(" width:");
                    sb.append(width);
                    sb.append(" height:");
                    MobileInputLogger$.ExternalSyntheticOutline0.m(height, "FakeFrame-->", "SystemUi--Common", sb);
                }
            }
            invalidate();
        }
    }

    public final void setFakeView(View view, FakeType fakeType) {
        RenderBooster renderBoosterEnsureRenderBooster$default;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "FakeFrame-->".concat("setFakeView realView:" + view));
        }
        this.mView = view;
        if (view != null && (renderBoosterEnsureRenderBooster$default = RenderBooster.Companion.ensureRenderBooster$default(RenderBooster.Companion, view, null, 3)) != null) {
            RenderBooster.requestBooster$default(renderBoosterEnsureRenderBooster$default, 10, false, 6);
        }
        this.type = fakeType;
        setClipChildren(false);
        setClipToPadding(false);
    }

    public final void setMView(View view) {
        this.mView = view;
    }

    public final void unbindRealView() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "FakeFrame-->unbindRealView");
        }
        this.hasBind = false;
        unregisterRealViewListener();
    }

    public final void unregisterRealViewListener() {
        ViewTreeObserver viewTreeObserver;
        View view = this.mView;
        if (view != null && (viewTreeObserver = view.getViewTreeObserver()) != null) {
            viewTreeObserver.removeOnDrawListener(this.mOnDrawListener);
        }
        View view2 = this.mView;
        if (view2 != null) {
            view2.removeOnLayoutChangeListener(this.onLayoutChangeListener);
        }
    }

    public StatusBarFakeFrame getElementView() {
        return this;
    }

    public StatusBarFakeFrame(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 4, 0);
    }

    public /* synthetic */ StatusBarFakeFrame(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, (i & 2) != 0 ? null : attributeSet, 0);
    }

    public StatusBarFakeFrame(Context context) {
        this(context, null, 6, 0);
    }
}
