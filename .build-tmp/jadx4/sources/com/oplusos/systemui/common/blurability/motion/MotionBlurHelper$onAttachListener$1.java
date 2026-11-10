package com.oplusos.systemui.common.blurability.motion;

import android.util.Log;
import android.view.View;
import androidx.exifinterface.media.ExifInterface$;
import com.android.internal.graphics.drawable.BackgroundBlurDrawable;
import com.oplusos.systemui.common.blurability.DebugToolKt;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MotionBlurHelper$onAttachListener$1 implements View.OnAttachStateChangeListener {
    public /* synthetic */ MotionBlurHelper this$0;

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        MotionBlurHelper motionBlurHelper = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "MotionBlurHelper-->".concat("[onViewAttachedToWindow] " + DebugToolKt.getName(motionBlurHelper.hostView, false) + " blurEnabled=" + motionBlurHelper.getBlurEnabled()));
        }
        this.this$0.ensureBlurDrawable();
        BackgroundBlurDrawable blurDrawable = this.this$0.getBlurDrawable();
        if (blurDrawable != null) {
            MotionBlurHelper motionBlurHelper2 = this.this$0;
            blurDrawable.setVisible(motionBlurHelper2.getBlurEnabled() && motionBlurHelper2.hostView.getWindowVisibility() == 0 && motionBlurHelper2.hostView.isShown(), false);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                StringBuilder sb = new StringBuilder("MotionBlurHelper-->");
                StringBuilder sb2 = new StringBuilder("[onViewAttachedToWindow] ");
                sb2.append(DebugToolKt.getName(motionBlurHelper2.hostView, false));
                sb2.append(" set visible ");
                sb2.append(motionBlurHelper2.getBlurEnabled() && motionBlurHelper2.hostView.getWindowVisibility() == 0 && motionBlurHelper2.hostView.isShown());
                sb2.append(",hostView.windowVisible=");
                sb2.append(motionBlurHelper2.hostView.getWindowVisibility() == 0);
                sb2.append(",hostView.isShown=");
                sb2.append(motionBlurHelper2.hostView.isShown());
                sb2.append(", blurEnabled=");
                sb2.append(motionBlurHelper2.getBlurEnabled());
                ExifInterface$.ExternalSyntheticOutline1.m(sb, sb2.toString(), "SystemUi--Common");
            }
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.this$0.release("onViewDetachedFromWindow");
    }
}
