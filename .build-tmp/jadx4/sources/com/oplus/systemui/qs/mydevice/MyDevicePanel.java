package com.oplus.systemui.qs.mydevice;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.systemui.qs.TouchAnimator;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.utils.SpringOverScroller;
import com.oplus.systemui.volume.OplusVolumeTipsControler;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MyDevicePanel extends FrameLayout {
    private static final String ACTION_MYDEVICE_HOME_PAGE = "com.oplus.mydevices.ACTION_DEVICE_CARD_HOME_ACTIVITY";
    public static final Companion Companion = new Companion();
    private static final String TAG = "MyDevicePanel";
    private ViewController.OnConfigurationChangedListener mConfigListener;
    public int mExpandGap;
    private SpringOverScroller mSpringOverScroller;
    private TouchAnimator mTouchAnimator;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public MyDevicePanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void computeScroll() {
        QSFragmentHelper.getInstance().computeScroll(this, this.mSpringOverScroller);
    }

    public final SpringOverScroller getSpringOverScroller() {
        return this.mSpringOverScroller;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        updateResources();
        ViewController.OnConfigurationChangedListener onConfigurationChangedListener = this.mConfigListener;
        if (onConfigurationChangedListener != null) {
            onConfigurationChangedListener.onConfigurationChange(configuration);
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        OplusNotificationLogger.logDevicePanel(TAG, "onFinishInflate");
        super.onFinishInflate();
        this.mSpringOverScroller = new SpringOverScroller(((FrameLayout) this).mContext);
        TouchAnimator.Builder builder = new TouchAnimator.Builder();
        builder.addFloat(this, OplusVolumeTipsControler.PROPERTY_NAME_ALPHA, new float[]{0.0f, 1.0f});
        this.mTouchAnimator = builder.setStartDelay(0.65f).build();
        updateResources();
    }

    public void setExpansion(float f) {
        TouchAnimator touchAnimator = this.mTouchAnimator;
        if (touchAnimator != null) {
            touchAnimator.setPosition(f);
        }
    }

    public final void setOnConfigurationChangeListener(ViewController.OnConfigurationChangedListener onConfigurationChangedListener) {
        this.mConfigListener = onConfigurationChangedListener;
    }

    public final void updateResources() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131172012);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(2131172011);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 == null || dimensionPixelSize != layoutParams2.getMarginStart() || layoutParams2 == null || dimensionPixelSize2 != layoutParams2.getMarginEnd()) {
            if (layoutParams2 != null) {
                layoutParams2.setMarginsRelative(dimensionPixelSize, 0, dimensionPixelSize2, 0);
            }
            if (layoutParams2 != null) {
                setLayoutParams(layoutParams2);
            }
        }
        this.mExpandGap = getResources().getDimensionPixelSize(2131172009);
    }
}
