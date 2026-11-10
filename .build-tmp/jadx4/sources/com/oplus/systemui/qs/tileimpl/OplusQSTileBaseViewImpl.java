package com.oplus.systemui.qs.tileimpl;

import android.content.Context;
import android.graphics.Path;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.systemui.plugins.qs.QSIconView;
import com.oplus.systemui.qs.OplusQSSmallSpaceController;
import com.oplus.systemui.qs.base.customizer.IQSCustomizer$EditType;
import com.oplus.systemui.qs.base.res.drawable.TileDrawableWrapper;
import com.oplus.systemui.qs.base.res.model.InterestingViewFlag;
import com.oplus.systemui.qs.base.tile.OplusQSTileBaseView;
import com.oplus.systemui.qs.base.tile.PressFeedbackHelper;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.qs.helper.OplusQSTileSizeHelper;
import com.oplus.systemui.qs.utils.OplusQSDisplayModeUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSTileBaseViewImpl extends OplusQSTileBaseView {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [android.view.View, android.widget.FrameLayout, com.oplus.systemui.qs.base.tile.OplusQSTileBaseView$SpotLightIconFrame] */
    public OplusQSTileBaseViewImpl(Context context, QSIconView qSIconView, boolean z) {
        super(context);
        new OplusQSTileBaseView.H(this);
        this.mLocInScreen = new int[2];
        this.mViewFlag = new InterestingViewFlag();
        this.mIsHighLightForCustomizer = false;
        this.mIsCustomizer = false;
        this.mTileIconState = 1;
        this.mEidtType = IQSCustomizer$EditType.TOP;
        new Path();
        ?? spotLightIconFrame = new OplusQSTileBaseView.SpotLightIconFrame(this, context);
        this.mIconFrame = spotLightIconFrame;
        spotLightIconFrame.setDuplicateParentStateEnabled(true);
        this.mCollapsedView = z;
        this.mIsLandscape = DeviceUtil.isLandscape(((LinearLayout) this).mContext);
        setBgSize();
        int i = this.mBgSize;
        addView(spotLightIconFrame, new LinearLayout.LayoutParams(i, i));
        ImageView imageView = new ImageView(getContext());
        this.mBg = imageView;
        imageView.setDuplicateParentStateEnabled(true);
        imageView.setId(2131364808);
        imageView.setTag(2131364408, Boolean.FALSE);
        TileDrawableWrapper tileDrawableWrapper = new TileDrawableWrapper(null);
        this.mBgDrawable = tileDrawableWrapper;
        imageView.setBackground(tileDrawableWrapper);
        int i2 = this.mBgSize;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i2, i2, 17);
        spotLightIconFrame.addView(imageView, layoutParams);
        imageView.setLayoutParams(layoutParams);
        this.mQsTilePressFeedback = PressFeedbackHelper.attachPressFeedback((View) this, (View) spotLightIconFrame);
        this.mIcon = qSIconView;
        int qSTileSize = getQSTileSize();
        float f = qSTileSize != 0 ? (this.mBgSize * 1.0f) / qSTileSize : 1.0f;
        qSIconView.setScaleX(f);
        qSIconView.setScaleY(f);
        spotLightIconFrame.addView(qSIconView, new FrameLayout.LayoutParams(-2, -2, 17));
        spotLightIconFrame.setClipChildren(false);
        spotLightIconFrame.setClipToPadding(false);
        setPadding(0, 0, 0, 0);
        setClipChildren(false);
        setClipToPadding(false);
        setFocusable(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.qs.base.tile.OplusQSTileBaseView
    public int getQSTileSize() {
        return OplusQSTileSizeHelper.getQsTileSize(((LinearLayout) this).mContext, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.qs.base.tile.OplusQSTileBaseView
    public void setBgSize() {
        boolean z = this.mCollapsedView;
        Context context = ((LinearLayout) this).mContext;
        if (z) {
            this.mBgSize = OplusQSTileSizeHelper.getQsTileSize(context, 1);
            return;
        }
        this.mBgSize = OplusQSTileSizeHelper.getQsTileSize(context, 0);
        if (OplusQSSmallSpaceController.isSmallSpace(((LinearLayout) this).mContext) && OplusQSDisplayModeUtils.isBigDisplayMode(((LinearLayout) this).mContext)) {
            this.mBgSize = OplusQSTileSizeHelper.getQsTileSize(((LinearLayout) this).mContext, 6);
        } else {
            this.mBgSize = OplusQSTileSizeHelper.getQsTileSize(((LinearLayout) this).mContext, 0);
        }
    }
}
