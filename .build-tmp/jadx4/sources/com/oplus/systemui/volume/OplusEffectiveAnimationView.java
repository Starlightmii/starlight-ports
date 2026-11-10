package com.oplus.systemui.volume;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.oplus.anim.EffectiveAnimationView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusEffectiveAnimationView extends EffectiveAnimationView {
    private View.OnClickListener mOnClickListener;

    public OplusEffectiveAnimationView(Context context) {
        super(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean performClick() {
        View.OnClickListener onClickListener = this.mOnClickListener;
        if (onClickListener == null) {
            return true;
        }
        onClickListener.onClick(this);
        return true;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public OplusEffectiveAnimationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public OplusEffectiveAnimationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
