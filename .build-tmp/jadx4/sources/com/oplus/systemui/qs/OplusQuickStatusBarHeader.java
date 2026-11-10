package com.oplus.systemui.qs;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.DisplayCutout;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.Space;
import androidx.car.app.model.TimerSpan$;
import androidx.collection.FloatList$;
import androidx.compose.foundation.text.input.TextFieldBuffer$;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.insets.Protection$;
import androidx.fragment.app.Fragment$5$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.Utils;
import com.android.systemui.privacy.OngoingPrivacyChip;
import com.android.systemui.qs.TouchAnimator;
import com.android.systemui.qs.deprecated.BaseQuickStatusBarHeader;
import com.android.systemui.statusbar.layout.StatusBarContentInsetsProvider;
import com.android.systemui.statusbar.layout.StatusBarContentInsetsProviderImpl;
import com.android.systemui.statusbar.phone.PhoneStatusBarViewEx;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.phone.ui.TintedIconManager;
import com.android.systemui.util.LargeScreenUtils;
import com.android.systemui.util.ViewController;
import com.android.systemui.utils.windowmanager.WindowManagerUtils;
import com.oplus.systemui.notification.utlils.NotificationColorUtil;
import com.oplus.systemui.plugins.qs.customize.view.animation.drag.animator.MultiAnimViewDelegate;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.utils.QsUtil;
import com.oplus.systemui.qs.utils.SpringOverScroller;
import com.oplus.systemui.qs.widget.OplusSecondCarrierText;
import com.oplus.systemui.statusbar.phone.StatusBarPaddingProvider;
import com.oplus.systemui.statusbar.pipeline.battery.ui.view.StatBatteryMeterView;
import com.oplus.systemui.statusbar.util.OplusStatusBarUtils;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQuickStatusBarHeader extends BaseQuickStatusBarHeader {
    public static final Companion Companion = new Companion();
    private static final Interpolator ICON_INTERPOLATOR = new PathInterpolator(0.33f, 0.0f, 0.67f, 0.0f);
    private static final String TAG = "OplusQuickStatusBarHeader";
    private StatBatteryMeterView mBatteryView;
    private ConstraintLayout.LayoutParams mCenterHoleSpaceLp;
    private Space mCenterHoleView;
    private ViewController.OnConfigurationChangedListener mConfigurationListener;
    public boolean mCustomizing;
    public int mCutOutPaddingLeft;
    public int mCutOutPaddingRight;
    public int mDpPix18;
    public int mDpPix8;
    private StatusIconContainer mIconContainer;
    public boolean mIgnoreCenterHole;
    private StatusBarContentInsetsProvider mInsetsProvider;
    public boolean mIsCenterCutOut;
    public boolean mIsCenterHole;
    public boolean mIsDualSim;
    public float mKeyguardExpansionFraction;
    public int mLastLayoutDirection;
    private OngoingPrivacyChip mPrivacyContainer;
    private View mQSCarriers;
    private ViewGroup mQsClockContainer;
    public int mRoundedCornerPadding;
    public int mStatusBarPaddingTop;
    private ConstraintLayout mStatusIconsView;
    public int mStatusIconsViewPaddingLeft;
    private TintedIconManager mTintedIconManager;
    private TouchAnimator mTranslationAnimator;
    public int mWaterfallTopInset;
    public int mZoneMinWidth;
    private Scroller scroller;
    private SpringOverScroller springOverScroller;
    private final WindowManager windowManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusQuickStatusBarHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLastLayoutDirection = -1;
        this.windowManager = WindowManagerUtils.getWindowManager(context);
        this.springOverScroller = new SpringOverScroller(context);
        this.scroller = new Scroller(context);
    }

    private final int getMinLeftWidth() {
        ViewGroup viewGroup = this.mQsClockContainer;
        int i = 0;
        if (viewGroup != null && viewGroup.getChildCount() > 0) {
            int childCount = viewGroup.getChildCount();
            int measuredWidth = 0;
            while (i < childCount) {
                View childAt = viewGroup.getChildAt(i);
                if (!(childAt instanceof OplusSecondCarrierText)) {
                    measuredWidth = childAt.getMeasuredWidth() + measuredWidth;
                }
                i++;
            }
            i = measuredWidth;
        }
        int i2 = this.mZoneMinWidth;
        int qsCarrierTextStartOffsetPx = !QsColorUtil.getSeparateNotificationAndQSState() ? getQsCarrierTextStartOffsetPx() + i2 : i2;
        int i3 = i + qsCarrierTextStartOffsetPx;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, TimerSpan$.ExternalSyntheticOutline0.m(qsCarrierTextStartOffsetPx, i2, " minCarrierWidth=", " ", FloatList$.ExternalSyntheticOutline0.m("getMinLeftWidth minWidth=", i3, i, " childWidth=", " carrierWidth=")), null, 8);
        return i3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int getQsCarrierTextStartOffsetPx() {
        return getResources().getDimensionPixelSize(2131171925);
    }

    private final int getRightWidth() {
        int viewWidthWithMargin = getViewWidthWithMargin(0, this.mCenterHoleView);
        int viewWidthWithMargin2 = getViewWidthWithMargin(0, this.mPrivacyContainer);
        StatusIconContainer statusIconContainer = this.mIconContainer;
        int viewWidthWithMargin3 = getViewWidthWithMargin(statusIconContainer != null ? statusIconContainer.getRealNeedWidth() : 0, statusIconContainer);
        int viewWidthWithMargin4 = getViewWidthWithMargin(0, this.mBatteryView);
        int i = viewWidthWithMargin + viewWidthWithMargin2 + viewWidthWithMargin3 + viewWidthWithMargin4;
        StatusIconContainer statusIconContainer2 = this.mIconContainer;
        Integer numValueOf = statusIconContainer2 != null ? Integer.valueOf(statusIconContainer2.getMeasuredWidth()) : null;
        StatusIconContainer statusIconContainer3 = this.mIconContainer;
        Integer numValueOf2 = statusIconContainer3 != null ? Integer.valueOf(statusIconContainer3.getRealNeedWidth()) : null;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("getRightWidth holeViewWidth=", viewWidthWithMargin, viewWidthWithMargin2, " privacyWidth=", " iconContainerWidth=");
        sbM.append(numValueOf);
        sbM.append(" iconContainerNeedWidth=");
        sbM.append(numValueOf2);
        sbM.append(" batteryWidth=");
        LogUtil.i$default(LogUtil.TAG_QS, TAG, Fragment$5$.ExternalSyntheticOutline0.m(sbM, viewWidthWithMargin4, i, " rightWidth="), null, 8);
        return i;
    }

    public static int getViewWidthWithMargin(int i, View view) {
        if (view.getVisibility() != 0) {
            return 0;
        }
        if (i == 0) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            int measuredWidth = view.getMeasuredWidth() + (layoutParams instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams).getMarginStart() : 0);
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            return measuredWidth + (layoutParams2 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams2).getMarginEnd() : 0);
        }
        ViewGroup.LayoutParams layoutParams3 = view.getLayoutParams();
        int marginStart = (layoutParams3 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams3).getMarginStart() : 0) + i;
        ViewGroup.LayoutParams layoutParams4 = view.getLayoutParams();
        return marginStart + (layoutParams4 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams4).getMarginEnd() : 0);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0012  */
    public static void measureChild(ViewGroup viewGroup, int i, int i2) {
        int marginStart;
        int marginEnd = 0;
        if (viewGroup != null) {
            ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                marginStart = ((ViewGroup.MarginLayoutParams) layoutParams).getMarginStart();
            } else {
                marginStart = 0;
            }
        } else {
            marginStart = 0;
        }
        int i3 = i - marginStart;
        if (viewGroup != null) {
            ViewGroup.LayoutParams layoutParams2 = viewGroup.getLayoutParams();
            if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
                marginEnd = ((ViewGroup.MarginLayoutParams) layoutParams2).getMarginEnd();
            }
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i3 - marginEnd, 1073741824);
        if (viewGroup != null) {
            viewGroup.measure(iMakeMeasureSpec, i2);
        }
    }

    public static void setMarginEnd(int i, ViewGroup viewGroup) {
        Integer numValueOf;
        if (viewGroup != null) {
            ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
            numValueOf = Integer.valueOf(layoutParams instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams).getMarginEnd() : 0);
        } else {
            numValueOf = null;
        }
        if (numValueOf != null && numValueOf.intValue() == i) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "setMarginEnd margin=" + i + " oldMargin=" + numValueOf, null, 8);
        ViewGroup.LayoutParams layoutParams2 = viewGroup != null ? viewGroup.getLayoutParams() : null;
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : null;
        if (marginLayoutParams != null) {
            marginLayoutParams.setMarginEnd(i);
        }
        if (viewGroup != null) {
            viewGroup.setLayoutParams(marginLayoutParams);
        }
    }

    public static void setMarginStart(int i, ViewGroup viewGroup) {
        Integer numValueOf;
        if (viewGroup != null) {
            ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
            numValueOf = Integer.valueOf(layoutParams instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams).getMarginStart() : 0);
        } else {
            numValueOf = null;
        }
        if (numValueOf != null && numValueOf.intValue() == i) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "setMarginStart margin=" + i + " oldMargin=" + numValueOf, null, 8);
        ViewGroup.LayoutParams layoutParams2 = viewGroup != null ? viewGroup.getLayoutParams() : null;
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : null;
        if (marginLayoutParams != null) {
            marginLayoutParams.setMarginStart(i);
        }
        if (viewGroup != null) {
            viewGroup.setLayoutParams(marginLayoutParams);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void applyIconDarkness(TintedIconManager tintedIconManager) {
        int qSDateTimeColor = NotificationColorUtil.getQSDateTimeColor(((LinearLayout) this).mContext);
        float f = Color.luminance(qSDateTimeColor) >= 0.4f ? 1.0f : 0.0f;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, Protection$.ExternalSyntheticOutline0.m(f, "applyIconDarkness intensity="), null, 8);
        if (tintedIconManager != null) {
            tintedIconManager.setTint(qSDateTimeColor, Utils.getColorAttrDefaultColor(R.attr.textColorPrimaryInverse, 0, getContext()));
            ArrayList<Rect> arrayList = new ArrayList<>();
            StatBatteryMeterView statBatteryMeterView = this.mBatteryView;
            if (statBatteryMeterView != null) {
                statBatteryMeterView.onDarkChanged(arrayList, f, qSDateTimeColor);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void computeScroll() {
        QSFragmentHelper.getInstance().computeScroll(this, this.springOverScroller);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x004c  */
    /* JADX WARN: Code duplicated, block: B:37:0x0084  */
    /* JADX WARN: Multi-variable type inference failed */
    public final void doMeasureChild(int i, int i2, int i3, int i4, boolean z) {
        int marginEnd;
        int marginStart;
        int marginEnd2 = 0;
        setMarginEnd(0, this.mQsClockContainer);
        setMarginStart(0, this.mQsClockContainer);
        if (i < 0) {
            i = 0;
        }
        int i5 = !z ? 1073741824 : Integer.MIN_VALUE;
        int size = View.MeasureSpec.getSize(i4);
        ViewGroup viewGroup = this.mQsClockContainer;
        if (viewGroup != null) {
            viewGroup.measure(View.MeasureSpec.makeMeasureSpec(i, i5), View.MeasureSpec.makeMeasureSpec(size, i5));
        }
        int i6 = this.mDpPix8;
        if (QsColorUtil.getSeparateNotificationAndQSState() || (z && !QsColorUtil.getSeparateNotificationAndQSState())) {
            i6 = 0;
        }
        ConstraintLayout constraintLayout = this.mStatusIconsView;
        if (constraintLayout != null) {
            ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                marginEnd = ((ViewGroup.MarginLayoutParams) layoutParams).getMarginEnd();
            } else {
                marginEnd = 0;
            }
        } else {
            marginEnd = 0;
        }
        setMarginStart(i6, this.mStatusIconsView);
        if (z && !QsColorUtil.getSeparateNotificationAndQSState()) {
            measureChild(this.mStatusIconsView, i2, i4);
            return;
        }
        int size2 = View.MeasureSpec.getSize(i3) - (getPaddingRight() + getPaddingLeft());
        if (size2 < 0) {
            size2 = 0;
        }
        ViewGroup viewGroup2 = this.mQsClockContainer;
        if (viewGroup2 != null) {
            ViewGroup.LayoutParams layoutParams2 = viewGroup2.getLayoutParams();
            if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
                marginStart = ((ViewGroup.MarginLayoutParams) layoutParams2).getMarginStart();
            } else {
                marginStart = 0;
            }
        } else {
            marginStart = 0;
        }
        int measuredWidth = viewGroup2 != null ? viewGroup2.getMeasuredWidth() : 0;
        if (measuredWidth == 0) {
            measuredWidth = viewGroup2 != null ? viewGroup2.getWidth() : 0;
        }
        if (viewGroup2 != null) {
            ViewGroup.LayoutParams layoutParams3 = viewGroup2.getLayoutParams();
            if (layoutParams3 instanceof ViewGroup.MarginLayoutParams) {
                marginEnd2 = ((ViewGroup.MarginLayoutParams) layoutParams3).getMarginEnd();
            }
        }
        int i7 = size2 - ((marginStart + measuredWidth) + marginEnd2);
        int i8 = i6 + marginEnd;
        if (i7 < i8) {
            i7 = i8;
        }
        measureChild(this.mStatusIconsView, Math.max(i7, i2), i4);
    }

    public void dump(PrintWriter printWriter) {
        StatBatteryMeterView statBatteryMeterView = this.mBatteryView;
        if (statBatteryMeterView != null) {
            statBatteryMeterView.dump(printWriter);
        }
    }

    public final SpringOverScroller getSpringOverScroller() {
        return this.springOverScroller;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void initZoneMinWidth() {
        int dimensionPixelSize = !this.mIsDualSim ? getResources().getDimensionPixelSize(2131171586) : getResources().getDimensionPixelSize(2131171585);
        this.mZoneMinWidth = dimensionPixelSize;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("initZoneMinWidth mIsDualSim=", this.mIsDualSim, " mZoneMinWidth=", dimensionPixelSize), null, 8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isCenterCutOutDisplay() {
        StatusBarContentInsetsProviderImpl statusBarContentInsetsProviderImpl;
        WindowInsets rootWindowInsets = getRootWindowInsets();
        DisplayCutout displayCutout = rootWindowInsets != null ? rootWindowInsets.getDisplayCutout() : null;
        return (displayCutout == null || this.mInsetsProvider == null || displayCutout.getBoundingRectTop().isEmpty() || ((statusBarContentInsetsProviderImpl = this.mInsetsProvider) != null && statusBarContentInsetsProviderImpl.currentRotationHasCornerCutout())) ? false : true;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0022  */
    public final void measureExpand(int i, int i2) {
        int marginEnd;
        int rightWidth = getRightWidth();
        int i3 = this.mDpPix8;
        if (QsColorUtil.getSeparateNotificationAndQSState()) {
            i3 = 0;
        }
        ConstraintLayout constraintLayout = this.mStatusIconsView;
        if (constraintLayout != null) {
            ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                marginEnd = ((ViewGroup.MarginLayoutParams) layoutParams).getMarginEnd();
            } else {
                marginEnd = 0;
            }
        } else {
            marginEnd = 0;
        }
        int i4 = rightWidth + i3 + marginEnd;
        int i5 = i4 < 0 ? 0 : i4;
        int size = View.MeasureSpec.getSize(i);
        int i6 = size - i5;
        int minLeftWidth = getMinLeftWidth();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, Fragment$5$.ExternalSyntheticOutline0.m(FloatList$.ExternalSyntheticOutline0.m("measureExpand rightWidth(outer)=", i5, size, " totalWidth=", " remainWidth="), i6, minLeftWidth, " minLeftWidth="), null, 8);
        ViewGroup viewGroup = this.mQsClockContainer;
        if (i6 >= minLeftWidth) {
            if (viewGroup != null) {
                viewGroup.setMinimumWidth(0);
            }
            doMeasureChild(i6, i5, i, i2, false);
        } else {
            int i7 = size - minLeftWidth;
            if (viewGroup != null) {
                viewGroup.setMinimumWidth(minLeftWidth);
            }
            doMeasureChild(minLeftWidth, i7, i, i2, true);
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0083  */
    /* JADX WARN: Multi-variable type inference failed */
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        boolean z;
        Insets waterfallInsets;
        int i = 0;
        DisplayCutout displayCutout = windowInsets != null ? windowInsets.getDisplayCutout() : null;
        Rect bounds = this.windowManager.getCurrentWindowMetrics().getBounds();
        Pair<Integer, Integer> pairCornerCutoutMargins = getDisplay() != null ? PhoneStatusBarViewEx.Companion.cornerCutoutMargins(bounds, displayCutout, getDisplay()) : null;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "onApplyWindowInsets mIgnoreCenterHole: " + this.mIgnoreCenterHole + " mCenterHoleSpaceLp: " + this.mCenterHoleSpaceLp + " bounds: " + bounds, null, 8);
        Space space = this.mCenterHoleView;
        this.mCenterHoleSpaceLp = (ConstraintLayout.LayoutParams) (space != null ? space.getLayoutParams() : null);
        if (this.mIgnoreCenterHole) {
            updateCenterHoleView(8, -1, 0);
        }
        Pair<Integer, Integer> pairPaddingNeededForCutoutAndRoundedCorner = StatusBarPaddingProvider.Companion.paddingNeededForCutoutAndRoundedCorner(displayCutout, pairCornerCutoutMargins, -1, getContext());
        StatusBarContentInsetsProviderImpl statusBarContentInsetsProviderImpl = this.mInsetsProvider;
        if (statusBarContentInsetsProviderImpl != null) {
            z = statusBarContentInsetsProviderImpl.currentRotationHasCornerCutout();
        }
        ConstraintLayout constraintLayout = this.mStatusIconsView;
        if (constraintLayout != null) {
            constraintLayout.setPadding(((Number) pairPaddingNeededForCutoutAndRoundedCorner.first).intValue(), 0, ((Number) pairPaddingNeededForCutoutAndRoundedCorner.second).intValue(), 0);
        }
        Space space2 = this.mCenterHoleView;
        this.mCenterHoleSpaceLp = (ConstraintLayout.LayoutParams) (space2 != null ? space2.getLayoutParams() : null);
        if (displayCutout != null) {
            Rect boundingRectTop = displayCutout.getBoundingRectTop();
            if (boundingRectTop.isEmpty() || z || this.mIgnoreCenterHole) {
                updateCenterHoleView(8, -1, 0);
            } else {
                updateCenterHoleView(0, 0, Integer.valueOf(boundingRectTop.width()));
            }
            if (this.mKeyguardExpansionFraction == 1.0f) {
                updateCenterHoleView(8, -1, null);
            }
        }
        this.mCutOutPaddingLeft = ((Number) pairPaddingNeededForCutoutAndRoundedCorner.first).intValue();
        int iIntValue = ((Number) pairPaddingNeededForCutoutAndRoundedCorner.second).intValue();
        this.mCutOutPaddingRight = iIntValue;
        QsUtil.setQsStatusBarCutOutPadding(this.mCutOutPaddingLeft, iIntValue);
        if (displayCutout != null && (waterfallInsets = displayCutout.getWaterfallInsets()) != null) {
            i = waterfallInsets.top;
        }
        this.mWaterfallTopInset = i;
        updateHeadersPadding();
        this.mIsCenterCutOut = isCenterCutOutDisplay();
        return super/*android.widget.LinearLayout*/.onApplyWindowInsets(windowInsets);
    }

    public void onAttach(TintedIconManager tintedIconManager, List<String> list, StatusBarContentInsetsProvider statusBarContentInsetsProvider, boolean z, boolean z2) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("onAttach mIgnoreCenterHole: ", z2), null, 8);
        this.mTintedIconManager = tintedIconManager;
        this.mInsetsProvider = statusBarContentInsetsProvider;
        this.mIgnoreCenterHole = z2;
        updateStatusIconExpandAnimator();
        applyIconDarkness(tintedIconManager);
        Space space = this.mCenterHoleView;
        this.mCenterHoleSpaceLp = (ConstraintLayout.LayoutParams) (space != null ? space.getLayoutParams() : null);
        if (this.mIgnoreCenterHole) {
            updateCenterHoleView(8, -1, 0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        Trace.traceBegin(8L, "OplusQuickStatusBarHeader-onConfigurationChanged");
        super/*android.widget.LinearLayout*/.onConfigurationChanged(configuration);
        this.mLastLayoutDirection = -1;
        updateResources();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[onConfigurationChanged] updateResources end, start call listener", null, 8);
        ViewController.OnConfigurationChangedListener onConfigurationChangedListener = this.mConfigurationListener;
        if (onConfigurationChangedListener != null) {
            onConfigurationChangedListener.onConfigurationChange(configuration);
        }
        Trace.traceEnd(8L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDetachedFromWindow() {
        super/*android.widget.LinearLayout*/.onDetachedFromWindow();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onFinishInflate() {
        this.mIsCenterHole = OplusStatusBarUtils.isCenterHole();
        this.mStatusIconsView = findViewById(2131364818);
        this.mQSCarriers = findViewById(2131362491);
        this.mIconContainer = findViewById(2131365358);
        this.mCenterHoleView = (Space) findViewById(2131362777);
        this.mBatteryView = (StatBatteryMeterView) findViewById(2131362234);
        this.mQsClockContainer = (ViewGroup) findViewById(2131364721);
        this.mPrivacyContainer = findViewById(2131364644);
        updateResources();
        initZoneMinWidth();
        this.mDpPix8 = getResources().getDimensionPixelSize(2131171993);
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131172240);
        this.mDpPix18 = dimensionPixelSize;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, TextFieldBuffer$.ExternalSyntheticOutline0.m(this.mDpPix8, dimensionPixelSize, "initMarginDpPix mDpPix8=", " mDpPix18="), null, 8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super/*android.widget.LinearLayout*/.onLayout(z, i, i2, i3, i4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onMeasure(int i, int i2) {
        Space space;
        super/*android.widget.LinearLayout*/.onMeasure(i, i2);
        if (DeviceUtil.isLandscape(((LinearLayout) this).mContext)) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "measureChild isLandscape", null, 8);
            measureExpand(i, i2);
        } else if (QsColorUtil.getSeparateNotificationAndQSState() || (space = this.mCenterHoleView) == null || space.getVisibility() != 0) {
            measureExpand(i, i2);
        } else {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, "measureChild first expand", null, 8);
            int size = View.MeasureSpec.getSize(i);
            int i3 = this.mDpPix18;
            setMarginEnd(i3, this.mQsClockContainer);
            int i4 = size / 2;
            measureChild(this.mQsClockContainer, i4, i2);
            setMarginStart(i3, this.mStatusIconsView);
            measureChild(this.mStatusIconsView, i4, i2);
        }
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }

    public final void onRtlPropertiesChanged(int i) {
        if (this.mLastLayoutDirection != i) {
            this.mLastLayoutDirection = i;
            updateResources();
        }
    }

    public final void setConfigurationChangeListener(ViewController.OnConfigurationChangedListener onConfigurationChangedListener) {
        this.mConfigurationListener = onConfigurationChangedListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setCustomizing(boolean z) {
        this.mCustomizing = z;
        updateVisibility(getResources());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setDualSim(boolean z) {
        if (this.mIsDualSim == z) {
            return;
        }
        this.mIsDualSim = z;
        int i = this.mZoneMinWidth;
        initZoneMinWidth();
        if (i == this.mZoneMinWidth || !isAttachedToWindow()) {
            return;
        }
        requestLayout();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setExpansion(float f) {
        ViewPropertyAnimator viewPropertyAnimatorAnimate;
        ViewPropertyAnimator interpolator;
        ViewPropertyAnimator duration;
        ViewPropertyAnimator viewPropertyAnimatorAlpha;
        ViewPropertyAnimator viewPropertyAnimatorAnimate2;
        Rect boundingRectTop;
        OngoingPrivacyChip ongoingPrivacyChip;
        ViewPropertyAnimator viewPropertyAnimatorAnimate3;
        ViewPropertyAnimator interpolator2;
        ViewPropertyAnimator duration2;
        ViewPropertyAnimator viewPropertyAnimatorAlpha2;
        ViewPropertyAnimator viewPropertyAnimatorAnimate4;
        Integer numValueOf = null;
        if (this.mCenterHoleSpaceLp == null) {
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "mCenterHoldeSpaceLp=null", null, 8);
            Space space = this.mCenterHoleView;
            if (space != null) {
                this.mCenterHoleSpaceLp = space.getLayoutParams();
            }
        }
        this.mIsCenterCutOut = isCenterCutOutDisplay();
        FeatureOption.INSTANCE.getClass();
        boolean z = FeatureOption.isTablet() && !this.mIsCenterCutOut;
        if (this.mCenterHoleSpaceLp != null && this.mIsCenterCutOut && !DeviceUtil.isLandscape(((LinearLayout) this).mContext) && !this.mIgnoreCenterHole) {
            OngoingPrivacyChip ongoingPrivacyChip2 = this.mPrivacyContainer;
            if (f < 1.0f) {
                if (ongoingPrivacyChip2 != null && ongoingPrivacyChip2.getVisibility() == 0) {
                    OngoingPrivacyChip ongoingPrivacyChip3 = this.mPrivacyContainer;
                    if (f == 0.0f) {
                        if (ongoingPrivacyChip3 != null && (viewPropertyAnimatorAnimate4 = ongoingPrivacyChip3.animate()) != null) {
                            viewPropertyAnimatorAnimate4.cancel();
                        }
                        OngoingPrivacyChip ongoingPrivacyChip4 = this.mPrivacyContainer;
                        if (ongoingPrivacyChip4 != null && (viewPropertyAnimatorAnimate3 = ongoingPrivacyChip4.animate()) != null && (interpolator2 = viewPropertyAnimatorAnimate3.setInterpolator(ICON_INTERPOLATOR)) != null && (duration2 = interpolator2.setDuration(200L)) != null && (viewPropertyAnimatorAlpha2 = duration2.alpha(1.0f)) != null) {
                            viewPropertyAnimatorAlpha2.start();
                        }
                    } else {
                        if (!Intrinsics.areEqual(ongoingPrivacyChip3 != null ? Float.valueOf(ongoingPrivacyChip3.getAlpha()) : null, 0.0f) && (ongoingPrivacyChip = this.mPrivacyContainer) != null) {
                            ongoingPrivacyChip.setAlpha(0.0f);
                        }
                    }
                }
                DisplayCutout displayCutout = getRootWindowInsets().getDisplayCutout();
                if (displayCutout != null && (boundingRectTop = displayCutout.getBoundingRectTop()) != null) {
                    numValueOf = Integer.valueOf(boundingRectTop.width());
                }
                updateCenterHoleView(0, 0, numValueOf);
            } else {
                if (ongoingPrivacyChip2 != null && ongoingPrivacyChip2.getVisibility() == 0) {
                    OngoingPrivacyChip ongoingPrivacyChip5 = this.mPrivacyContainer;
                    if (ongoingPrivacyChip5 != null && (viewPropertyAnimatorAnimate2 = ongoingPrivacyChip5.animate()) != null) {
                        viewPropertyAnimatorAnimate2.cancel();
                    }
                    OngoingPrivacyChip ongoingPrivacyChip6 = this.mPrivacyContainer;
                    if (ongoingPrivacyChip6 != null && (viewPropertyAnimatorAnimate = ongoingPrivacyChip6.animate()) != null && (interpolator = viewPropertyAnimatorAnimate.setInterpolator(ICON_INTERPOLATOR)) != null && (duration = interpolator.setDuration(200L)) != null && (viewPropertyAnimatorAlpha = duration.alpha(1.0f)) != null) {
                        viewPropertyAnimatorAlpha.start();
                    }
                }
                updateCenterHoleView(8, -1, null);
            }
        } else if (z && f == 1.0f && this.mCenterHoleSpaceLp != null && !this.mIgnoreCenterHole) {
            updateCenterHoleView(8, -1, null);
        }
        TouchAnimator touchAnimator = this.mTranslationAnimator;
        if (touchAnimator != null && (!DeviceUtil.isLandscape(((LinearLayout) this).mContext) || DeviceUtil.shouldUseLargeFoldConfig(getResources()))) {
            touchAnimator.setPosition(f);
        }
        this.mKeyguardExpansionFraction = f;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001f  */
    public final void updateCenterHoleView(int i, int i2, Integer num) {
        boolean z;
        Space space;
        Space space2;
        boolean z2 = true;
        if (num != null) {
            int iIntValue = num.intValue();
            ConstraintLayout.LayoutParams layoutParams = this.mCenterHoleSpaceLp;
            if (layoutParams != null && ((ViewGroup.MarginLayoutParams) layoutParams).width == iIntValue) {
                num = null;
            }
            if (num != null) {
                int iIntValue2 = num.intValue();
                ConstraintLayout.LayoutParams layoutParams2 = this.mCenterHoleSpaceLp;
                if (layoutParams2 != null) {
                    ((ViewGroup.MarginLayoutParams) layoutParams2).width = iIntValue2;
                }
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        ViewGroup.LayoutParams layoutParams3 = this.mCenterHoleSpaceLp;
        if (layoutParams3 != null && ((ConstraintLayout.LayoutParams) layoutParams3).endToEnd == i2) {
            z2 = z;
        } else if (layoutParams3 != null) {
            ((ConstraintLayout.LayoutParams) layoutParams3).endToEnd = i2;
        }
        if (z2 && (space2 = this.mCenterHoleView) != null) {
            space2.setLayoutParams(layoutParams3);
        }
        Space space3 = this.mCenterHoleView;
        if ((space3 == null || space3.getVisibility() != i) && (space = this.mCenterHoleView) != null) {
            space.setVisibility(i);
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("updateCenterHoleView visibility: ", i, " isLpChanged: ", z2), null, 8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateHeadersPadding() {
        Pair<Integer, Integer> qsStatusBarCutoutPadding;
        int i;
        if ((this.mCutOutPaddingLeft == 0 || this.mCutOutPaddingRight == 0) && (qsStatusBarCutoutPadding = QsUtil.getQsStatusBarCutoutPadding()) != null) {
            this.mCutOutPaddingLeft = ((Number) qsStatusBarCutoutPadding.first).intValue();
            this.mCutOutPaddingRight = ((Number) qsStatusBarCutoutPadding.second).intValue();
        }
        if (this.mCutOutPaddingLeft > 0) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isEarScreen() && isLayoutRtl() && !this.mIsCenterHole) {
                int i2 = this.mCutOutPaddingLeft;
                int i3 = this.mRoundedCornerPadding;
                if (i2 < i3) {
                    i2 = i3;
                }
                ConstraintLayout constraintLayout = this.mStatusIconsView;
                ViewGroup.LayoutParams layoutParams = constraintLayout != null ? constraintLayout.getLayoutParams() : null;
                LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
                i = i2 - (layoutParams2 != null ? layoutParams2.leftMargin : 0);
            } else {
                i = 0;
            }
            this.mStatusIconsViewPaddingLeft = i;
        }
        ConstraintLayout constraintLayout2 = this.mStatusIconsView;
        if (constraintLayout2 != null) {
            constraintLayout2.setPadding(this.mStatusIconsViewPaddingLeft, this.mWaterfallTopInset + this.mStatusBarPaddingTop, 0, 0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateResources() {
        Resources resources = ((LinearLayout) this).mContext.getResources();
        resources.getClass();
        updateVisibility(resources);
        this.mRoundedCornerPadding = resources.getDimensionPixelSize(2131172336);
        this.mStatusBarPaddingTop = resources.getDimensionPixelSize(2131173091);
        updateHeadersPadding();
        updateStatusIconExpandAnimator();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateStatusIconExpandAnimator() {
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isEarScreen() || !isLayoutRtl() || this.mIsCenterHole) {
            this.mTranslationAnimator = null;
            return;
        }
        TouchAnimator.Builder builder = new TouchAnimator.Builder();
        builder.addFloat(this.mStatusIconsView, MultiAnimViewDelegate.PROPERTY_TRANSLATION_X, new float[]{0.0f, -this.mStatusIconsViewPaddingLeft});
        this.mTranslationAnimator = builder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateVisibility(Resources resources) {
        boolean z = this.mCustomizing || LargeScreenUtils.shouldUseSplitNotificationShade(resources);
        if (z != (getVisibility() != 0)) {
            setVisibility(z ? 4 : 0);
        }
    }
}
