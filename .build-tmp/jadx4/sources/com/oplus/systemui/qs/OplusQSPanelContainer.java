package com.oplus.systemui.qs;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.MathUtils;
import android.view.View;
import android.widget.FrameLayout;
import com.android.systemui.qs.deprecated.QSPanel;
import com.android.systemui.qs.deprecated.QuickQSPanel;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.utils.OplusQSDisplayModeUtils;
import com.oplus.systemui.qs.utils.SpringOverScroller;
import com.oplusos.systemui.common.feature.QSFeatureOption;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSPanelContainer extends FrameLayout {
    public int mCollapseTopGap;
    public int mExpandTopGap;
    private QuickQSPanel mQQSPanel;
    private QSPanel mQSPanel;
    private SpringOverScroller springOverScroller;

    public OplusQSPanelContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.springOverScroller = new SpringOverScroller(context);
    }

    @Override // android.view.View
    public final void computeScroll() {
        QSFragmentHelper.getInstance().computeScroll(this, this.springOverScroller);
    }

    public final View getAnimatorView() {
        return this.mQSPanel;
    }

    public final int getCollapseHeight() {
        QuickQSPanel quickQSPanel = this.mQQSPanel;
        if (quickQSPanel != null) {
            return quickQSPanel.getMeasuredHeight();
        }
        return 0;
    }

    public final int getExpandHeight() {
        QSPanel qSPanel = this.mQSPanel;
        return qSPanel != null ? qSPanel.getMeasuredHeight() : getCollapseHeight();
    }

    public final SpringOverScroller getSpringOverScroller() {
        return this.springOverScroller;
    }

    public final float getTopGap(float f) {
        boolean z = OplusQSSmallSpaceController.Companion.isSmallSpace(((FrameLayout) this).mContext, true) && OplusQSDisplayModeUtils.isBigDisplayMode(((FrameLayout) this).mContext);
        boolean z2 = ((FrameLayout) this).mContext.getResources().getConfiguration().orientation == 2;
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isSupportVolumeSeekBar() && z2 && QSFragmentHelper.getInstance().getQsTileMediaContainer().getMediaMode()) {
            return z ? getResources().getDimensionPixelSize(2131172245) : getResources().getDimensionPixelSize(2131172244);
        }
        return MathUtils.lerp(this.mCollapseTopGap, this.mExpandTopGap, f);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateResource(getResources());
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mQQSPanel = findViewById(2131364817);
        this.mQSPanel = findViewById(2131364821);
        updateResource(getResources());
    }

    public final void setExpansion(float f) {
    }

    public final void updateColor() {
        QSPanel.QSTileLayout qSTileLayout;
        QSPanel.QSTileLayout qSTileLayout2;
        QuickQSPanel quickQSPanel = this.mQQSPanel;
        if (quickQSPanel != null && (qSTileLayout2 = ((QSPanel) quickQSPanel).mTileLayout) != null) {
            qSTileLayout2.updateColor();
        }
        QSPanel qSPanel = this.mQSPanel;
        if (qSPanel == null || (qSTileLayout = qSPanel.mTileLayout) == null) {
            return;
        }
        qSTileLayout.updateColor();
    }

    public final void updateQSBounds(Rect rect) {
        QSPanel qSPanel = this.mQSPanel;
        if (qSPanel != null) {
            qSPanel.setClipBounds(rect);
        }
    }

    public final void updateResource(Resources resources) {
        boolean z = false;
        if (OplusQSSmallSpaceController.Companion.isSmallSpace(((FrameLayout) this).mContext, false) && OplusQSDisplayModeUtils.isBigDisplayMode(((FrameLayout) this).mContext)) {
            z = true;
        }
        this.mCollapseTopGap = z ? resources.getDimensionPixelSize(2131172243) : resources.getDimensionPixelSize(2131172242);
        this.mExpandTopGap = z ? resources.getDimensionPixelSize(2131172176) : resources.getDimensionPixelSize(2131172175);
    }

    public final void updateViewState(float f, float f2) {
        QuickQSPanel quickQSPanel = this.mQQSPanel;
        if (quickQSPanel != null) {
            quickQSPanel.setTransitionAlpha(f);
        }
        QSPanel qSPanel = this.mQSPanel;
        if (qSPanel != null) {
            qSPanel.setTransitionAlpha(f);
        }
        QuickQSPanel quickQSPanel2 = this.mQQSPanel;
        if (quickQSPanel2 != null) {
            quickQSPanel2.setScaleX(f2);
        }
        QSPanel qSPanel2 = this.mQSPanel;
        if (qSPanel2 != null) {
            qSPanel2.setScaleX(f2);
        }
        QuickQSPanel quickQSPanel3 = this.mQQSPanel;
        if (quickQSPanel3 != null) {
            quickQSPanel3.setScaleY(f2);
        }
        QSPanel qSPanel3 = this.mQSPanel;
        if (qSPanel3 != null) {
            qSPanel3.setScaleY(f2);
        }
    }
}
