package com.oplus.systemui.qs.media;

import android.content.Context;
import android.graphics.RenderEffect;
import android.util.AttributeSet;
import android.view.View;
import com.oplus.graphics.OplusRenderEffect;
import com.oplus.view.OplusViewBackgroundRenderEffect;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ProgressiveBlurOverlay extends View {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ProgressiveBlurOverlay";
    private Float pendingBlurProgress;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public /* synthetic */ ProgressiveBlurOverlay(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final void applyGradientBlurWithProgress(float f) {
        Object obj;
        if (!isAttachedToWindow() || getWidth() <= 0 || getHeight() <= 0) {
            OplusNotificationLogger.logMediaPanel(TAG, "applyGradientBlurWithProgress: not ready");
            return;
        }
        try {
            OplusViewBackgroundRenderEffect.setBackgroundRenderEffect(OplusRenderEffect.createGradientBlurEffect(0.0f, f * 30.0f, true, f * 1.0f, 0, 0, 0), this);
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            OplusNotificationLogger.logMediaPanel(TAG, "applyGradientBlurWithProgress fail=" + th2);
        }
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        applyGradientBlurWithProgress(1.0f);
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        Object obj;
        super.onDetachedFromWindow();
        try {
            OplusViewBackgroundRenderEffect.setBackgroundRenderEffect((RenderEffect) null, this);
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            OplusNotificationLogger.logMediaPanel(TAG, "resetSetMaterialBlur fail=" + th2);
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Float f = this.pendingBlurProgress;
        if (f == null) {
            applyGradientBlurWithProgress(1.0f);
            return;
        }
        float fFloatValue = f.floatValue();
        OplusNotificationLogger.logMediaPanel(TAG, "onSizeChanged: apply pendingBlurProgress=" + fFloatValue);
        applyGradientBlurWithProgress(fFloatValue);
        this.pendingBlurProgress = null;
    }

    public final void setBlurProgress(float f) {
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        if (getWidth() > 0 && getHeight() > 0) {
            applyGradientBlurWithProgress(f);
        } else {
            OplusNotificationLogger.logMediaPanel(TAG, "setBlurProgress: view not ready, cache progress");
            this.pendingBlurProgress = Float.valueOf(fCoerceIn);
        }
    }

    public ProgressiveBlurOverlay(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public ProgressiveBlurOverlay(Context context) {
        this(context, null, 0, 6, null);
    }

    public ProgressiveBlurOverlay(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setBackgroundColor(0);
    }
}
