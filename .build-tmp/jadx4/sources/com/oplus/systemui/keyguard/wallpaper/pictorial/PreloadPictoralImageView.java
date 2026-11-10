package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.keyguard.base.OplusKeyguardCtrl$;
import com.android.systemui.DependencyEx;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.oplusos.systemui.common.feature.FeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class PreloadPictoralImageView extends ImageView implements ConfigurationController.ConfigurationListener {
    public int mScreenHeight;
    public int mScreenWidth;

    public PreloadPictoralImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        getRealScreenSize();
        StringBuilder sb = new StringBuilder("init mScreenWidth = ");
        sb.append(this.mScreenWidth);
        sb.append("; mScreenHeight: ");
        OplusKeyguardCtrl$.ExternalSyntheticOutline0.m(this.mScreenHeight, "PreloadPictoralImageView", sb);
    }

    private void getRealScreenSize() {
        int i = ((ImageView) this).mContext.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        int i2 = ((ImageView) this).mContext.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isLargeScreenFolding()) {
            this.mScreenWidth = Math.min(i, i2);
            this.mScreenHeight = Math.max(i, i2);
            return;
        }
        if (FeatureOption.isWhiteSwanFoldDevice()) {
            this.mScreenWidth = i;
            this.mScreenHeight = i2;
            return;
        }
        int i3 = ((ImageView) this).mContext.getResources().getConfiguration().orientation;
        if (i3 == 1) {
            if (i > i2) {
                OplusKeyguardCtrl$.ExternalSyntheticOutline0.m("getRealScreenSize: portrait swap to logical ", i2, i, "x", "PreloadPictoralImageView");
                i2 = i;
                i = i2;
            }
        } else if (i3 == 2 && i < i2) {
            OplusKeyguardCtrl$.ExternalSyntheticOutline0.m("getRealScreenSize: landscape swap to logical ", i2, i, "x", "PreloadPictoralImageView");
            i2 = i;
            i = i2;
        }
        this.mScreenWidth = i;
        this.mScreenHeight = i2;
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getConfigurationController().addCallback(this);
    }

    public final void onConfigChanged(Configuration configuration) {
        getRealScreenSize();
        StringBuilder sb = new StringBuilder("onConfigChanged  mScreenWidth = ");
        sb.append(this.mScreenWidth);
        sb.append("; mScreenHeight: ");
        OplusKeyguardCtrl$.ExternalSyntheticOutline0.m(this.mScreenHeight, "PreloadPictoralImageView", sb);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getConfigurationController().removeCallback(this);
    }

    @Override // android.widget.ImageView
    public final boolean setFrame(int i, int i2, int i3, int i4) {
        int i5;
        FeatureOption.INSTANCE.getClass();
        boolean zIsLargeScreenFolding = FeatureOption.isLargeScreenFolding();
        if (zIsLargeScreenFolding) {
            return (!zIsLargeScreenFolding || (i3 == (i5 = this.mScreenWidth) && i4 == this.mScreenHeight)) ? super.setFrame(i, i2, i3, i4) : super.setFrame(i, i2, i5, this.mScreenHeight);
        }
        return super.setFrame(i, i2, Math.min(i3, i4), Math.max(i3, i4));
    }

    public PreloadPictoralImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PreloadPictoralImageView(Context context) {
        this(context, null);
    }
}
