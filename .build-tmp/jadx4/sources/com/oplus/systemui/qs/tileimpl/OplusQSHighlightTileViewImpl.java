package com.oplus.systemui.qs.tileimpl;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.widget.LinearLayout;
import com.android.systemui.plugins.qs.QSIconView;
import com.oplus.systemui.qs.OplusQSSmallSpaceController;
import com.oplus.systemui.qs.base.tile.OplusQSHighlightTileView;
import com.oplus.systemui.qs.helper.OplusQSTileSizeHelper;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.utils.OplusQSDisplayModeUtils;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSHighlightTileViewImpl extends OplusQSHighlightTileView {
    public OplusQSHighlightTileViewImpl(Context context, QSIconView qSIconView) {
        this(context, qSIconView, false);
    }

    @Override // com.oplus.systemui.qs.base.tile.OplusQSHighlightTileView
    public /* bridge */ /* synthetic */ Rect getPaddingForLaunchAnimation() {
        return super.getPaddingForLaunchAnimation();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.qs.base.tile.OplusQSHighlightTileView
    public int getTileSize() {
        return OplusQSTileSizeHelper.getQsTileSize(((LinearLayout) this).mContext, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.qs.base.tile.OplusQSHighlightTileView
    public int getTitleMarginStart() {
        return getContext().getResources().getDimensionPixelSize(2131171884);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.qs.base.tile.OplusQSHighlightTileView
    public final void setBgSize() {
        int dimensionPixelSize;
        boolean z = false;
        boolean z2 = OplusQSSmallSpaceController.Companion.isSmallSpace(((LinearLayout) this).mContext, false) && OplusQSDisplayModeUtils.isBigDisplayMode(((LinearLayout) this).mContext);
        setMBgSize(OplusQSTileSizeHelper.getQsTileSize(((LinearLayout) this).mContext, 1));
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isSupportVolumeSeekBar()) {
            int i = 2131171881;
            if (QSFragmentHelper.getInstance().getQsTileMediaContainer().getMediaMode()) {
                FeatureOption.INSTANCE.getClass();
                if (FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled() && DeviceStateController.getInstance().isOpenOrHalfOpen()) {
                    z = true;
                }
                Resources resources = ((LinearLayout) this).mContext.getResources();
                if (z || FeatureOption.isTablet()) {
                    i = 2131171882;
                } else if (z2) {
                    i = 2131171880;
                }
                dimensionPixelSize = resources.getDimensionPixelSize(i);
            } else {
                dimensionPixelSize = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(z2 ? 2131171881 : 2131171883);
            }
        } else {
            dimensionPixelSize = ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(z2 ? 2131171879 : 2131171878);
        }
        setMBgHeight(dimensionPixelSize);
        setMIconSize(getMBgSize());
    }

    public OplusQSHighlightTileViewImpl(Context context, QSIconView qSIconView, boolean z) {
        super(context, qSIconView, z);
    }
}
