package com.oplus.systemui.notification.blur;

import android.content.Context;
import android.graphics.RenderEffect;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oplus.graphics.OplusRenderEffect;
import com.oplus.view.OplusViewBackgroundRenderEffect;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationTiltShiftBlurContainer extends FrameLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusNotificationTiltShiftBlurContainer";
    private RenderEffect blurRenderEffect;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public /* synthetic */ OplusNotificationTiltShiftBlurContainer(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    public final void resetMaterialBlur() {
        Object obj;
        try {
            OplusViewBackgroundRenderEffect.setBackgroundRenderEffect((RenderEffect) null, this);
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "resetMaterialBlur", null, 8);
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, "resetMaterialBlur fail=" + th2);
        }
    }

    public final void setMaterialBlur() {
        Object obj;
        try {
            if (this.blurRenderEffect == null) {
                this.blurRenderEffect = OplusRenderEffect.createGradientBlurEffect(50.0f, 0.0f, true, 4.0f, 1, 0, 0);
            }
            OplusViewBackgroundRenderEffect.setBackgroundRenderEffect(this.blurRenderEffect, this);
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "setMaterialBlur " + this.blurRenderEffect + " " + this, null, 8);
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, "setMaterialBlur fail=" + th2);
        }
    }

    public final void updateTiltShiftBlurtSize(int i, int i2) {
        Object obj;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams2 = layoutParams instanceof ConstraintLayout.LayoutParams ? (ConstraintLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 == null) {
            return;
        }
        try {
            ((ViewGroup.MarginLayoutParams) layoutParams2).width = i;
            ((ViewGroup.MarginLayoutParams) layoutParams2).height = i2;
            layoutParams2.startToStart = 0;
            layoutParams2.endToEnd = 0;
            setLayoutParams(layoutParams2);
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.e$default(8, LogUtil.TAG_NOTIFICATION, TAG, "updateTiltShiftBlurtSize fail=" + th2);
        }
    }

    public OplusNotificationTiltShiftBlurContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, null);
    }

    public OplusNotificationTiltShiftBlurContainer(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, null);
    }

    public OplusNotificationTiltShiftBlurContainer(Context context) {
        this(context, null, 0, 0, 14, null);
    }

    public OplusNotificationTiltShiftBlurContainer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
