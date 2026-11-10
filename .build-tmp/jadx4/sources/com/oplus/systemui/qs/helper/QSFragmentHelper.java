package com.oplus.systemui.qs.helper;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.OplusBaseConfigurationAddon;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.ui.spatial.RectList$;
import androidx.core.view.ViewCompat;
import androidx.slice.widget.RowView$;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.deprecated.BaseQuickStatusBarHeader;
import com.android.systemui.qs.deprecated.QSImpl;
import com.android.systemui.qs.deprecated.QSPanel;
import com.android.systemui.qs.deprecated.QuickQSPanel;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.oplus.systemui.qs.OplusQSBottomImpl;
import com.oplus.systemui.qs.OplusQSContainerImpl;
import com.oplus.systemui.qs.OplusQSFooterImpl;
import com.oplus.systemui.qs.OplusQSFooterViewController;
import com.oplus.systemui.qs.OplusQSImpl;
import com.oplus.systemui.qs.OplusQSPanelContainer;
import com.oplus.systemui.qs.OplusQSTileMediaContainer;
import com.oplus.systemui.qs.OplusQuickStatusBarHeader;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.customize.OplusQSCustomizer;
import com.oplus.systemui.qs.mydevice.MyDevicePanel;
import com.oplus.systemui.qs.utils.SpringOverScroller;
import com.oplus.systemui.qs.widget.MoreButtonPopupWindow;
import com.oplusos.systemui.common.feature.QSFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.util.OpUtils;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSFragmentHelper {
    public static volatile QSFragmentHelper sInstance;
    public View mBackground;
    public View mBrightness;
    public OplusQSContainerImpl mContainer;
    public float mDeltaY;
    public OplusQSFooterImpl mFooter;
    public boolean mHasReset;
    public OplusQuickStatusBarHeader mHeader;
    public QuickQSPanel mHeaderPanel;
    public View mHlTileOneHolder;
    public View mHlTileTwoHolder;
    public float mInitialMotionY;
    public boolean mIsBeingDragged;
    public boolean mIsExpanded;
    public boolean mLastAccentColorChanged;
    public boolean mLastAccentColorConfigChanged;
    public float mLastMotionX;
    public float mLastMotionY;
    public int mMaximumVelocity;
    public View mMoreButton;
    public MyDevicePanel mMyDevicePanel;
    public View mOplusDate;
    public int mOverscrollDistance;
    public OplusQSBottomImpl mQSBottom;
    public OplusQSCustomizer mQSCustomizer;
    public QSPanel mQSPanel;
    public OplusQSPanelContainer mQSPanelScrollView;
    public OplusQSImpl mQs;
    public View mQsFooterCarrier;
    public View mQsFooterClock;
    public View mQsMediaContainer;
    public NotificationStackScrollLayout mScrollLayout;
    public View mSeekbarContainer;
    public View mSettingButton;
    public OplusQSTileMediaContainer mTileMediaContainer;
    public int mTouchSlop;
    public VelocityTracker mVelocityTracker;
    public final CopyOnWriteArrayList mCallbacks = new CopyOnWriteArrayList();
    public float mLastQSExpansion = -1.0f;
    public int mLastActiveColor = -1;
    public int mLastActiveColorDark = -1;
    public int mScrollMode = -1;
    public int mActivePointerId = -1;
    public int mLastFlipFont = 0;
    public int mLastFontStatus = -1;
    public int mLastFontVariation = 0;
    public float mLastTurnMotionY = 0.0f;
    public final Interpolator mQsCoveredInterpolator = new PathInterpolator(0.1f, 0.2f, 1.0f, 0.1f);
    public final Interpolator mAlphaAnimInterpolator = new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f);
    public float mConverFraction = 0.0f;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface BeginClosingCallback {
        void onClosingBegin();

        void onClosingEnd();
    }

    public static QSFragmentHelper getInstance() {
        if (sInstance == null) {
            synchronized (QSFragmentHelper.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new QSFragmentHelper();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public static boolean isInOverScroll(View view) {
        return view.getScrollY() < 0;
    }

    public final void abortAnimation() {
        SpringOverScroller springOverScroller = this.mHeader.getSpringOverScroller();
        if (springOverScroller != null) {
            springOverScroller.abortAnimation();
        }
        this.mFooter.getSpringOverScroller().abortAnimation();
        this.mTileMediaContainer.getSpringOverScroller().abortAnimation();
        this.mQSPanelScrollView.getSpringOverScroller().abortAnimation();
        this.mQSBottom.getSpringOverScroller().abortAnimation();
        MyDevicePanel myDevicePanel = this.mMyDevicePanel;
        if (myDevicePanel != null) {
            myDevicePanel.getSpringOverScroller().abortAnimation();
        }
    }

    public final void applyAccentColor(Context context) {
        int themeAccentColor = OpUtils.getThemeAccentColor(context, true);
        int i = this.mLastActiveColor;
        this.mLastAccentColorChanged = i != themeAccentColor;
        if (i != themeAccentColor) {
            EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(this.mLastActiveColor, LogUtil.TAG_QS, "QSFragmentHelper", ListPopupWindow$.ExternalSyntheticOutline0.m(themeAccentColor, "init activeColor=", " mLastActiveColor="));
            this.mLastActiveColor = themeAccentColor;
            int darkThemeAccentColor = OpUtils.getDarkThemeAccentColor(context);
            this.mLastActiveColorDark = darkThemeAccentColor;
            BaseQSResPool.updateDarkThemeAccentColor(darkThemeAccentColor);
            this.mLastAccentColorConfigChanged = this.mLastAccentColorChanged;
        }
    }

    public final boolean applyDynamicFontVariationConfig(Configuration configuration) {
        int fontVariation = OplusBaseConfigurationAddon.getFontVariation(configuration);
        int fontVariationStatus = OplusBaseConfigurationAddon.getFontVariationStatus(configuration);
        if (fontVariation == this.mLastFontVariation && this.mLastFontStatus == fontVariationStatus) {
            return false;
        }
        StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(fontVariation, "applyDynamicFontVariationConfig variation=", " mLastFontVariation=");
        RectList$.ExternalSyntheticOutline0.m(this.mLastFontVariation, fontVariationStatus, " newStatus=", " mLastFontStatus=", sbM);
        ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mLastFontStatus, LogUtil.TAG_QS, "QSFragmentHelper", sbM);
        this.mLastFontVariation = fontVariation;
        this.mLastFontStatus = fontVariationStatus;
        return true;
    }

    public final boolean applyFlipFontConfig(Configuration configuration) {
        int flipFont = OplusBaseConfigurationAddon.getFlipFont(configuration);
        if (flipFont == this.mLastFlipFont) {
            return false;
        }
        ClipboardOverlayView$.ExternalSyntheticOutline0.m(this.mLastFlipFont, LogUtil.TAG_QS, "QSFragmentHelper", ListPopupWindow$.ExternalSyntheticOutline0.m(flipFont, "applyFlipFontConfig newFlipFont=", " mLastFlipFont="));
        this.mLastFlipFont = flipFont;
        return true;
    }

    public final void computeScroll(View view, SpringOverScroller springOverScroller) {
        if (springOverScroller.computeScrollOffset()) {
            int cOUICurrY = springOverScroller.getCOUICurrY();
            if (this.mScrollMode == 1 && view.getScrollY() != cOUICurrY) {
                view.scrollTo(0, cOUICurrY);
            }
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            view.postInvalidateOnAnimation();
        }
    }

    public final boolean forbidQsExpansion() {
        boolean z = isOverTouchSlop() || this.mDeltaY != 0.0f;
        if (z) {
            StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("forbidQsExpansion=", " mDeltaY=", z);
            sbM.append(this.mDeltaY);
            LogUtil.d(LogUtil.TAG_QS, "QSFragmentHelper", sbM.toString());
        }
        return z;
    }

    public final int getActiveColorWithDarkMode(Context context) {
        if (this.mLastActiveColorDark == -1) {
            int darkThemeAccentColor = OpUtils.getDarkThemeAccentColor(context);
            this.mLastActiveColorDark = darkThemeAccentColor;
            BaseQSResPool.updateDarkThemeAccentColor(darkThemeAccentColor);
        }
        return this.mLastActiveColorDark;
    }

    public float getConverFraction() {
        return this.mConverFraction;
    }

    public View getHeader() {
        return this.mHeader;
    }

    public View getMyDevicePanel() {
        return this.mMyDevicePanel;
    }

    public NotificationStackScrollLayout getNotificationScrollLayout() {
        return this.mScrollLayout;
    }

    public OplusQSBottomImpl getQSBottom() {
        return this.mQSBottom;
    }

    public OplusQSCustomizer getQSCustomizer() {
        return this.mQSCustomizer;
    }

    public QSPanel getQSPanel() {
        return this.mQSPanel;
    }

    public OplusQSContainerImpl getQsContainer() {
        return this.mContainer;
    }

    public float getQsExpansionFraction() {
        return 0.0f;
    }

    public OplusQSFooterImpl getQsFooter() {
        return this.mFooter;
    }

    public QS getQsFragment() {
        return this.mQs;
    }

    public View getQsPanelBackground() {
        return this.mBackground;
    }

    public OplusQSTileMediaContainer getQsTileMediaContainer() {
        return this.mTileMediaContainer;
    }

    public QuickQSPanel getQuickQSPanel() {
        return this.mHeaderPanel;
    }

    public final void hideCouiPopup() {
        MoreButtonPopupWindow moreButtonPopupWindow;
        MoreButtonPopupWindow moreButtonPopupWindow2;
        OplusQSImpl oplusQSImpl = this.mQs;
        if (oplusQSImpl != null) {
            OplusQSFooterViewController oplusQSFooterViewController = ((QSImpl) oplusQSImpl).mFooter;
            if (oplusQSFooterViewController == null) {
                oplusQSFooterViewController = null;
            }
            if (oplusQSFooterViewController == null || (moreButtonPopupWindow = oplusQSFooterViewController.mCouiPopup) == null || !moreButtonPopupWindow.isShowing()) {
                return;
            }
            OplusQSFooterViewController oplusQSFooterViewController2 = ((QSImpl) this.mQs).mFooter;
            OplusQSFooterViewController oplusQSFooterViewController3 = oplusQSFooterViewController2 != null ? oplusQSFooterViewController2 : null;
            if (oplusQSFooterViewController3 == null || (moreButtonPopupWindow2 = oplusQSFooterViewController3.mCouiPopup) == null || !moreButtonPopupWindow2.isShowing()) {
                return;
            }
            oplusQSFooterViewController3.mCouiPopup.dismiss();
        }
    }

    public final boolean isCoveringOrCoveredQs() {
        float f = this.mConverFraction;
        return f > 0.0f && f <= 1.0f;
    }

    public final boolean isFullExpanded() {
        return this.mLastQSExpansion == 1.0f;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0029  */
    public final boolean isOverTouchSlop() {
        boolean z;
        int iAbs;
        boolean zIsCOUIFinished;
        OplusQSPanelContainer oplusQSPanelContainer = this.mQSPanelScrollView;
        if (oplusQSPanelContainer == null || this.mQSPanel == null) {
            z = false;
        } else {
            SpringOverScroller springOverScroller = oplusQSPanelContainer.getSpringOverScroller();
            boolean zIsCOUIFinished2 = springOverScroller.isCOUIFinished();
            int iAbs2 = Math.abs(springOverScroller.getCOUIFinalY() - springOverScroller.getCOUICurrY());
            if (zIsCOUIFinished2 || iAbs2 <= this.mTouchSlop / 3) {
                z = false;
            } else {
                z = true;
            }
        }
        BaseQuickStatusBarHeader baseQuickStatusBarHeader = this.mHeader;
        if (baseQuickStatusBarHeader != null && baseQuickStatusBarHeader.getVisibility() == 0) {
            SpringOverScroller springOverScroller2 = this.mHeader.getSpringOverScroller();
            if (springOverScroller2 != null) {
                zIsCOUIFinished = springOverScroller2.isCOUIFinished();
                iAbs = Math.abs(springOverScroller2.getCOUIFinalY() - springOverScroller2.getCOUICurrY());
            } else {
                iAbs = 0;
                zIsCOUIFinished = false;
            }
            z |= !zIsCOUIFinished && iAbs > this.mTouchSlop / 3;
        }
        OplusQSFooterImpl oplusQSFooterImpl = this.mFooter;
        if (oplusQSFooterImpl != null) {
            SpringOverScroller springOverScroller3 = oplusQSFooterImpl.getSpringOverScroller();
            z |= !springOverScroller3.isCOUIFinished() && Math.abs(springOverScroller3.getCOUIFinalY() - springOverScroller3.getCOUICurrY()) > this.mTouchSlop / 3;
        }
        OplusQSTileMediaContainer oplusQSTileMediaContainer = this.mTileMediaContainer;
        if (oplusQSTileMediaContainer != null) {
            SpringOverScroller springOverScroller4 = oplusQSTileMediaContainer.getSpringOverScroller();
            z |= !springOverScroller4.isCOUIFinished() && Math.abs(springOverScroller4.getCOUIFinalY() - springOverScroller4.getCOUICurrY()) > this.mTouchSlop / 3;
        }
        OplusQSBottomImpl oplusQSBottomImpl = this.mQSBottom;
        if (oplusQSBottomImpl != null) {
            SpringOverScroller springOverScroller5 = oplusQSBottomImpl.getSpringOverScroller();
            z |= !springOverScroller5.isCOUIFinished() && Math.abs(springOverScroller5.getCOUIFinalY() - springOverScroller5.getCOUICurrY()) > this.mTouchSlop / 3;
        }
        MyDevicePanel myDevicePanel = this.mMyDevicePanel;
        if (myDevicePanel == null || myDevicePanel.getVisibility() != 0) {
            return z;
        }
        SpringOverScroller springOverScroller6 = this.mMyDevicePanel.getSpringOverScroller();
        return z | (!springOverScroller6.isCOUIFinished() && Math.abs(springOverScroller6.getCOUIFinalY() - springOverScroller6.getCOUICurrY()) > this.mTouchSlop / 3);
    }

    public final void onFractionUpdated(float f, boolean z) {
        if (!z) {
            this.mHasReset = false;
        }
        if (this.mBrightness == null || this.mHeaderPanel == null || this.mHlTileOneHolder == null || this.mHlTileTwoHolder == null || this.mQsMediaContainer == null || this.mMoreButton == null || this.mQsFooterClock == null || this.mQsFooterCarrier == null || this.mSettingButton == null || this.mOplusDate == null) {
            return;
        }
        QSFeatureOption.INSTANCE.getClass();
        if (QSFeatureOption.isSupportVolumeSeekBar() && this.mSeekbarContainer == null) {
            return;
        }
        float interpolation = ((PathInterpolator) this.mQsCoveredInterpolator).getInterpolation(f);
        float f2 = interpolation * 833.0f;
        float interpolation2 = ((PathInterpolator) this.mAlphaAnimInterpolator).getInterpolation(Math.min(f2 / 200.0f, 1.0f));
        float interpolation3 = ((PathInterpolator) this.mAlphaAnimInterpolator).getInterpolation(Math.min(f2 / 130.0f, 1.0f));
        float interpolation4 = ((PathInterpolator) this.mAlphaAnimInterpolator).getInterpolation(Math.min(f2 / 300.0f, 1.0f));
        float interpolation5 = ((PathInterpolator) this.mAlphaAnimInterpolator).getInterpolation(Math.min(f2 / 500.0f, 1.0f));
        this.mBrightness.setAlpha(1.0f - interpolation3);
        this.mHeaderPanel.setAlpha(1.0f - interpolation2);
        float f3 = 1.0f - interpolation4;
        this.mHlTileOneHolder.setAlpha(f3);
        this.mHlTileTwoHolder.setAlpha(f3);
        this.mQsMediaContainer.setAlpha(f3);
        if (QSFeatureOption.isSupportVolumeSeekBar()) {
            this.mSeekbarContainer.setAlpha(f3);
        }
        float f4 = 1.0f - interpolation5;
        this.mQsFooterClock.setAlpha(f4);
        this.mSettingButton.setAlpha(f4);
        this.mOplusDate.setAlpha(f4);
    }

    public final void onQsClosingBegin() {
        for (WeakReference weakReference : this.mCallbacks) {
            if (weakReference != null && weakReference.get() != null) {
                ((BeginClosingCallback) weakReference.get()).onClosingBegin();
            }
        }
    }

    public final void onQsClosingEnd() {
        for (WeakReference weakReference : this.mCallbacks) {
            if (weakReference != null && weakReference.get() != null) {
                ((BeginClosingCallback) weakReference.get()).onClosingEnd();
            }
        }
    }

    public final void resetSpringBack() {
        this.mHeader.scrollTo(0, 0);
        this.mFooter.scrollTo(0, 0);
        this.mTileMediaContainer.scrollTo(0, 0);
        this.mQSPanelScrollView.scrollTo(0, 0);
        this.mQSBottom.scrollTo(0, 0);
        MyDevicePanel myDevicePanel = this.mMyDevicePanel;
        if (myDevicePanel != null) {
            myDevicePanel.scrollTo(0, 0);
        }
        this.mLastTurnMotionY = 0.0f;
    }

    public final void resetTouch() {
        this.mActivePointerId = -1;
        this.mIsBeingDragged = false;
        this.mDeltaY = 0.0f;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void setHost(QSHost qSHost) {
        this.mQSBottom.setHost(qSHost);
    }

    public void setKeyguardShowing(boolean z) {
        OplusQSImpl oplusQSImpl;
        this.mLastQSExpansion = -1.0f;
        this.mDeltaY = 0.0f;
        OplusQSCustomizer oplusQSCustomizer = this.mQSCustomizer;
        if (oplusQSCustomizer != null) {
            oplusQSCustomizer.setKeyguardShowing(z);
        }
        if (z && (oplusQSImpl = this.mQs) != null && oplusQSImpl.isShowingDetail()) {
            this.mQs.closeDetail();
        }
    }

    public void setLastQSExpansion(float f) {
        this.mLastQSExpansion = f;
    }

    public void setListening(boolean z) {
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m("setListening ", LogUtil.TAG_QS, "QSFragmentHelper", z);
        if (z) {
            return;
        }
        resetSpringBack();
    }

    public void setNotificationScrollLayout(ViewGroup viewGroup) {
        if (viewGroup instanceof NotificationStackScrollLayout) {
            this.mScrollLayout = (NotificationStackScrollLayout) viewGroup;
        }
    }

    public void setmMyDevicePanel(MyDevicePanel myDevicePanel) {
        this.mMyDevicePanel = myDevicePanel;
        OplusQSCustomizer oplusQSCustomizer = this.mQSCustomizer;
        if (oplusQSCustomizer != null) {
            oplusQSCustomizer.setMyDevicePanel(myDevicePanel);
        }
    }

    public final void springBack(View view, SpringOverScroller springOverScroller, int i) {
        if (isInOverScroll(view)) {
            int scrollY = view.getScrollY();
            this.mScrollMode = 1;
            springOverScroller.setRefreshRate(Math.max(30.0f, view.getDisplay().getRefreshRate()));
            springOverScroller.setFlingFriction(0.32f);
            if (view instanceof OplusQuickStatusBarHeader) {
                springOverScroller.setSpringBackFriction(12.18f);
            }
            if (view instanceof OplusQSFooterImpl) {
                springOverScroller.setSpringBackFriction(13.68f);
            }
            if (view instanceof OplusQSTileMediaContainer) {
                springOverScroller.setSpringBackFriction(15.18f);
            }
            if (view instanceof OplusQSPanelContainer) {
                springOverScroller.setSpringBackFriction(16.68f);
            }
            if (view instanceof OplusQSBottomImpl) {
                springOverScroller.setSpringBackFriction(18.18f);
            }
            if (view == this.mMyDevicePanel) {
                springOverScroller.setSpringBackFriction(19.68f);
            }
            springOverScroller.setCurrVelocityY(0.0f);
            LogUtil.d(LogUtil.TAG_QS, "QSFragmentHelper", "springBack = " + scrollY + ",initVelocity = " + i + " view=" + view.getClass());
            springOverScroller.springBack(view.getScrollX(), scrollY, 0, 0, 0, 0);
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            view.postInvalidateOnAnimation();
        }
    }
}
