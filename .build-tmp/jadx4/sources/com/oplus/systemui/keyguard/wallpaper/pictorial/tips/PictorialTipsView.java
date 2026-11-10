package com.oplus.systemui.keyguard.wallpaper.pictorial.tips;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMessageViewInfo;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class PictorialTipsView extends AppCompatImageView {
    public boolean mAllowState;
    public boolean mCreate;
    public OnTipsListener mOnTipsListener;
    public boolean mShow;
    public PictorialMessageViewInfo mViewInfo;

    public PictorialTipsView(Context context) {
        this(context, null);
    }

    public void setAllowStates(boolean z) {
        this.mAllowState = z;
    }

    public void setOnTipsListener(OnTipsListener onTipsListener) {
        this.mOnTipsListener = onTipsListener;
    }

    public PictorialTipsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public PictorialTipsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
