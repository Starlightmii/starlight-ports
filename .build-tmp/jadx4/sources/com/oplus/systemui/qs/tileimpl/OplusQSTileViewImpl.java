package com.oplus.systemui.qs.tileimpl;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.OplusBaseConfigurationAddon;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.plugins.qs.QSIconView;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.qs.OplusQSTileMediaContainer;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.util.Objects;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSTileViewImpl extends OplusQSTileBaseViewImpl {
    public ColorStateList mColorLabelDefault;
    public ColorStateList mColorLabelUnavailable;
    public ColorStateList mColorSecondLabelDefault;
    public ColorStateList mColorSecondLabelUnavailable;
    public int mCurrentOrientation;
    public ImageView mExpandIndicator;
    public TextView mLabel;
    public ViewGroup mLabelContainer;
    public LinearLayout mLabelGroup;
    public final int[] mLabelGroupPostion;
    public final int[] mLabelPostion;
    public int mLastFlipFont;
    public boolean mLastSingleLineStatus;
    public ImageView mPadLock;
    public TextView mSecondLine;
    public int mState;
    public int mTileLabelMargin;

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQSTileViewImpl(Context context, QSIconView qSIconView, boolean z) {
        super(context, qSIconView, z);
        this.mLastFlipFont = 0;
        this.mLastSingleLineStatus = false;
        setClipChildren(false);
        setClipToPadding(false);
        setClickable(true);
        setId(LinearLayout.generateViewId());
        createLabel();
        setOrientation(1);
        setGravity(49);
        this.mColorLabelDefault = getContext().getColorStateList(2131103752);
        this.mColorLabelUnavailable = getContext().getColorStateList(2131103754);
        this.mColorSecondLabelDefault = getContext().getColorStateList(2131103756);
        this.mColorSecondLabelUnavailable = getContext().getColorStateList(2131103757);
        setPadding(0, ((LinearLayout) this).mContext.getResources().getDimensionPixelSize(2131172178), 0, 0);
        Configuration configuration = getResources().getConfiguration();
        if (configuration != null) {
            this.mLastFlipFont = OplusBaseConfigurationAddon.getFlipFont(configuration);
            this.mCurrentOrientation = configuration.orientation;
        }
        addPadLockView(context);
        this.mLabelPostion = new int[2];
        this.mLabelGroupPostion = new int[2];
        this.mTileLabelMargin = context.getResources().getDimensionPixelOffset(2131172184);
        updateResource$1();
    }

    public void addPadLockView(Context context) {
        if (getIconWithBackground() == null) {
            return;
        }
        ImageView imageView = new ImageView(context);
        this.mPadLock = imageView;
        imageView.setVisibility(8);
        this.mPadLock.setImageResource(2131234068);
        this.mPadLock.setImageTintList(context.getColorStateList(2131103759));
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(2131172022);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize);
        layoutParams.gravity = 8388661;
        int i = dimensionPixelSize / 2;
        layoutParams.setMarginsRelative(0, i, i, 0);
        getIconWithBackground().addView(this.mPadLock, layoutParams);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void createLabel() {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(2131559380, (ViewGroup) this, false);
        this.mLabelContainer = viewGroup;
        viewGroup.setClipChildren(false);
        this.mLabelContainer.setClipToPadding(false);
        this.mLabel = (TextView) this.mLabelContainer.findViewById(2131365664);
        this.mPadLock = (ImageView) this.mLabelContainer.findViewById(2131364898);
        this.mExpandIndicator = (ImageView) this.mLabelContainer.findViewById(2131363082);
        this.mSecondLine = (TextView) this.mLabelContainer.findViewById(2131362126);
        this.mLabelGroup = (LinearLayout) this.mLabelContainer.findViewById(2131363624);
        addView(this.mLabelContainer);
    }

    public int getCurrentOrientation() {
        return this.mCurrentOrientation;
    }

    public View getLabel() {
        return this.mLabel;
    }

    @Override // com.oplus.systemui.qs.base.tile.OplusQSTileBaseView
    public View getSecondaryClickHostView() {
        return this.mLabelContainer;
    }

    @Override // com.oplus.systemui.qs.base.tile.OplusQSTileBaseView
    public final void handleQsColorStateChanged$1(QSTile.State state) {
        super.handleQsColorStateChanged$1(state);
        updateTextColor(QsColorUtil.getQsColorState());
        TextView textView = this.mLabel;
        if (textView != null) {
            int i = state.state;
            textView.setTextColor((i == 0 || i == 101) ? this.mColorLabelUnavailable : this.mColorLabelDefault);
        }
        TextView textView2 = this.mSecondLine;
        if (textView2 != null) {
            int i2 = state.state;
            textView2.setTextColor((i2 == 0 || i2 == 101) ? this.mColorSecondLabelUnavailable : this.mColorSecondLabelDefault);
        }
        ImageView imageView = this.mExpandIndicator;
        if (imageView != null) {
            int i3 = state.state;
            imageView.setImageTintList((i3 == 0 || i3 == 101) ? this.mColorLabelUnavailable : this.mColorLabelDefault);
        }
    }

    @Override // com.oplus.systemui.qs.base.tile.OplusQSTileBaseView
    public void handleStateChanged(QSTile.State state) {
        super.handleStateChanged(state);
        updateTextColor(QsColorUtil.getQsColorState());
        if (!Objects.equals(this.mLabel.getText(), state.label) || this.mState != state.state) {
            TextView textView = this.mLabel;
            int i = state.state;
            textView.setTextColor((i == 0 || i == 101) ? this.mColorLabelUnavailable : this.mColorLabelDefault);
            this.mLabel.setText(state.label);
        }
        TextView textView2 = this.mSecondLine;
        if (textView2 != null && (!Objects.equals(textView2.getText(), state.secondaryLabel) || this.mState != state.state)) {
            this.mSecondLine.setText(state.secondaryLabel);
            boolean zBooleanValue = isSingleLine().booleanValue();
            TextView textView3 = this.mSecondLine;
            if (zBooleanValue) {
                textView3.setVisibility(8);
            } else {
                textView3.setVisibility(TextUtils.isEmpty(state.secondaryLabel) ? 8 : 0);
            }
            TextView textView4 = this.mSecondLine;
            int i2 = state.state;
            textView4.setTextColor((i2 == 0 || i2 == 101) ? this.mColorSecondLabelUnavailable : this.mColorSecondLabelDefault);
        }
        ImageView imageView = this.mExpandIndicator;
        if (imageView != null) {
            int i3 = state.state;
            imageView.setImageTintList((i3 == 0 || i3 == 101) ? this.mColorLabelUnavailable : this.mColorLabelDefault);
        }
        this.mState = state.state;
        boolean z = state.dualTarget;
        ImageView imageView2 = this.mExpandIndicator;
        if (imageView2 != null) {
            imageView2.setVisibility(z ? 0 : 8);
            updateLabelPadding();
        }
        this.mLabelContainer.setContentDescription(z ? state.dualLabelContentDescription : null);
        if (z != this.mLabelContainer.isClickable()) {
            this.mLabelContainer.setClickable(z);
            this.mLabelContainer.setLongClickable(z);
            this.mLabel.setBackground(null);
        }
        this.mLabel.setEnabled(!state.disabledByPolicy);
        ImageView imageView3 = this.mPadLock;
        if (imageView3 != null) {
            imageView3.setVisibility(state.disabledByPolicy ? 0 : 8);
        }
        updateLabelTextLine();
    }

    @Override // com.oplus.systemui.qs.base.tile.OplusQSTileBaseView
    public final void init(View.OnClickListener onClickListener, View.OnClickListener onClickListener2, View.OnLongClickListener onLongClickListener) {
        super.init(onClickListener, onClickListener2, onLongClickListener);
        this.mLabelContainer.setOnClickListener(onClickListener2);
        this.mLabelContainer.setOnLongClickListener(onLongClickListener);
        this.mLabelContainer.setClickable(false);
        this.mLabelContainer.setLongClickable(false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Boolean isSingleLine() {
        boolean z = ((LinearLayout) this).mContext.getResources().getConfiguration().orientation == 2;
        OplusQSTileMediaContainer qsTileMediaContainer = QSFragmentHelper.getInstance().getQsTileMediaContainer();
        if (qsTileMediaContainer == null) {
            LogUtil.e(LogUtil.TAG_QS, "OplusQSTileViewImpl", "qsTileMediaContainer is null");
            return Boolean.FALSE;
        }
        QSFeatureOption.INSTANCE.getClass();
        return (QSFeatureOption.isSupportVolumeSeekBar() && z && qsTileMediaContainer.getMediaMode()) ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override // com.oplus.systemui.qs.base.tile.OplusQSTileBaseView
    public final void onConfigurationChanged(Configuration configuration) {
        if (configuration != null) {
            super.onConfigurationChanged(configuration);
            int flipFont = OplusBaseConfigurationAddon.getFlipFont(configuration);
            if (flipFont != this.mLastFlipFont) {
                this.mLastFlipFont = flipFont;
            }
            this.mCurrentOrientation = configuration.orientation;
        }
        updateResource$1();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.oplus.systemui.qs.base.tile.OplusQSTileBaseView
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        ViewGroup viewGroup = this.mLabelContainer;
        if (viewGroup == null || this.mLabelGroup == null) {
            return;
        }
        viewGroup.getLocationOnScreen(this.mLabelPostion);
        this.mLabelGroup.getLocationOnScreen(this.mLabelGroupPostion);
        if (this.mLabelGroupPostion[1] == this.mLabelPostion[1] || !DeviceUtil.isLandscape(((LinearLayout) this).mContext) || this.mLabelGroupPostion[1] - this.mLabelPostion[1] <= 10) {
            return;
        }
        this.mLabelContainer.setPadding(0, getResources().getDimensionPixelSize(2131172218), 0, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onMeasure(int i, int i2) {
        super/*android.widget.LinearLayout*/.onMeasure(i, i2);
        boolean zBooleanValue = isSingleLine().booleanValue();
        if (zBooleanValue != this.mLastSingleLineStatus) {
            this.mLastSingleLineStatus = zBooleanValue;
            updateLabelTextLine();
            super/*android.widget.LinearLayout*/.onMeasure(i, i2);
        }
        updateLabelPadding();
    }

    @Override // com.oplus.systemui.qs.base.tile.OplusQSTileBaseView
    public void setPosition(int i) {
    }

    public final void updateLabelPadding() {
        if (this.mLabelGroup != null) {
            ImageView imageView = this.mExpandIndicator;
            if (imageView == null || imageView.getVisibility() != 0) {
                if (this.mLabelGroup.getPaddingLeft() != 0) {
                    this.mLabelGroup.setPadding(0, 0, 0, 0);
                }
            } else {
                LinearLayout linearLayout = this.mLabelGroup;
                int i = this.mTileLabelMargin;
                linearLayout.setPadding(i, 0, i, 0);
            }
        }
    }

    public final void updateLabelTextLine() {
        if (this.mIsHighLightForCustomizer) {
            this.mLabel.setSelected(true);
            this.mLabel.setSingleLine();
            this.mLabel.setMaxLines(1);
        } else {
            if (this.mIsCustomizer) {
                this.mLabel.setMaxLines(2);
                return;
            }
            if (this.mLastSingleLineStatus) {
                this.mLabel.setMaxLines(1);
                return;
            }
            TextView textView = this.mSecondLine;
            if (textView == null || TextUtils.isEmpty(textView.getText())) {
                this.mLabel.setMaxLines(2);
            } else {
                this.mLabel.setMaxLines(1);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void updateResource$1() {
        Resources resources = getResources();
        setPadding(0, resources.getDimensionPixelSize(2131172178), 0, 0);
        ViewGroup viewGroup = this.mLabelContainer;
        if (viewGroup != null) {
            viewGroup.setPadding(0, resources.getDimensionPixelSize(2131172218), 0, 0);
        }
        TextView textView = this.mSecondLine;
        if (textView != null) {
            textView.setVisibility(isSingleLine().booleanValue() ? 8 : 0);
            this.mSecondLine.setPadding(0, resources.getDimensionPixelSize(2131172167), 0, 0);
        }
        this.mTileLabelMargin = ((LinearLayout) this).mContext.getResources().getDimensionPixelOffset(2131172184);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateTextColor(int i) {
        if (!QsColorUtil.isTextNeedUseLightColorWhenLight(((LinearLayout) this).mContext, i)) {
            this.mColorLabelDefault = getContext().getColorStateList(2131103752);
            this.mColorLabelUnavailable = getContext().getColorStateList(2131103754);
            this.mColorSecondLabelDefault = getContext().getColorStateList(2131103756);
            this.mColorSecondLabelUnavailable = getContext().getColorStateList(2131103757);
            return;
        }
        this.mColorLabelDefault = getContext().getColorStateList(R.color.white);
        int i2 = QsColorUtil.QS_TILE_LABEL_UNAVAILABLE_COLOR;
        this.mColorLabelUnavailable = ColorStateList.valueOf(i2);
        this.mColorSecondLabelDefault = getContext().getColorStateList(R.color.white);
        this.mColorSecondLabelUnavailable = ColorStateList.valueOf(i2);
    }
}
