package com.oplus.systemui.notification.lockscreen.capsule;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleScrimView extends View {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleAodScrimView:LEO";
    private Drawable drawable;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public CapsuleScrimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.drawable = ((View) this).mContext.getDrawable(2131232787);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Drawable drawable = this.drawable;
            Log.d("SystemUi--Notification", "CapsuleAodScrimView:LEO-->".concat("onDraw capsule scrim, alpha: " + (drawable != null ? Integer.valueOf(drawable.getAlpha()) : null)));
        }
        int iSave = canvas.save();
        try {
            Drawable drawable2 = this.drawable;
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            }
            Drawable drawable3 = this.drawable;
            if (drawable3 != null) {
                drawable3.draw(canvas);
            }
        } finally {
            canvas.restoreToCount(iSave);
        }
    }

    public final void setScrimAlpha(float f) {
        int i = (int) (f * 40.0f);
        Drawable drawable = this.drawable;
        if (drawable == null || i != drawable.getAlpha()) {
            boolean z = i > 0;
            if ((getVisibility() == 0) != z) {
                setVisibility(z ? 0 : 8);
            }
            Drawable drawable2 = this.drawable;
            if (drawable2 != null) {
                drawable2.setAlpha(i);
            }
            invalidate();
        }
    }

    public CapsuleScrimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ CapsuleScrimView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public CapsuleScrimView(Context context) {
        this(context, null, 0, 6, null);
    }
}
