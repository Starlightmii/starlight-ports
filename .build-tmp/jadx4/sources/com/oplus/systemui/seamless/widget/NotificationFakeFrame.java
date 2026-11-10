package com.oplus.systemui.seamless.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.FrameLayout;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.systemui.seamless.FakeType;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationFakeFrame extends StartSideFakeFrame {
    public static final Companion Companion = new Companion();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationFakeFrame(Context context) {
        super(context);
    }

    @Override // com.oplus.systemui.seamless.widget.StatusBarFakeFrame, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        View mView = getMView();
        if (mView != null) {
            float width = isLayoutRtl() ? (getWidth() - mView.getWidth()) - getOffsetX() : getOffsetX();
            int iSave = canvas.save();
            canvas.translate(width, 0.0f);
            try {
                mView.draw(canvas);
            } finally {
                canvas.restoreToCount(iSave);
            }
        }
    }

    public int getOffsetX() {
        return 30;
    }

    @Override // com.oplus.systemui.seamless.widget.StatusBarFakeFrame
    public float getRealStatusElementLocX() {
        if (getScaleX() == 1.0f) {
            return super.getRealStatusElementLocX();
        }
        return getAbsoluteTranslationX(getMView()) + getAbsoluteLeft(getMView());
    }

    @Override // com.oplus.systemui.seamless.widget.StatusBarFakeFrame
    public final void resetLayoutParams() {
        View mView = getMView();
        if (mView != null) {
            if (mView.getHeight() != getHeight()) {
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, mView.getHeight());
                layoutParams.gravity = 16;
                setLayoutParams(layoutParams);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    FakeType type = getType();
                    int width = getWidth();
                    int height = getHeight();
                    StringBuilder sb = new StringBuilder("resetLayoutParams type:");
                    sb.append(type);
                    sb.append(" width:");
                    sb.append(width);
                    sb.append(" height:");
                    MobileInputLogger$.ExternalSyntheticOutline0.m(height, "FakeFrame-->", "SystemUi--Common", sb);
                }
            }
            invalidate();
        }
    }
}
