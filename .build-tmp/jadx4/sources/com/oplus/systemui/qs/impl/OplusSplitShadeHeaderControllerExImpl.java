package com.oplus.systemui.qs.impl;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.settingslib.Utils;
import com.android.systemui.DualToneHandler;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.qs.OplusQsColorManagerEx;
import com.android.systemui.statusbar.phone.OplusSplitShadeHeaderControllerEx;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.phone.ui.TintedIconManager;
import com.android.systemui.tuner.TunerService;
import com.oplus.systemui.blur.OplusQsColorManager;
import com.oplus.systemui.notification.utlils.NotificationColorUtil;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.statusbar.phone.policy.OplusPolicyObserver;
import com.oplus.systemui.statusbar.pipeline.battery.StatBatteryMeterViewController;
import com.oplus.systemui.statusbar.pipeline.battery.ui.view.StatBatteryMeterView;
import com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSplitShadeHeaderControllerExImpl extends OplusSplitShadeHeaderControllerEx implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusSplitShadeHeaderControllerExImpl";
    private StatBatteryMeterView batteryIcon;
    public StatBatteryMeterViewController batteryMeterViewController;
    private final LocationBasedBatteryViewModel batteryViewModel;
    private View carrierView;
    private final DumpManager dumpManager;
    private TintedIconManager iconManager;
    private ViewGroup iconsStrip;
    private final OplusQsColorManagerEx.QsColorStateObserver mQsColorStateObserver;
    private final OplusPolicyObserver oplusPolicyObserver;
    private final OplusQsColorManager oplusQsColorManager;
    private ViewGroup qsStatusIcon;
    private final View.OnLayoutChangeListener splitHeaderLayoutChangeListener;
    private LinearLayout splitHeaderRow;
    public ViewGroup splitStatusbar;
    private StatusIconContainer statusIconContainer;
    private final TunerService tunerService;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusSplitShadeHeaderControllerExImpl(LocationBasedBatteryViewModel locationBasedBatteryViewModel, TunerService tunerService, OplusQsColorManager oplusQsColorManager, OplusPolicyObserver oplusPolicyObserver, DumpManager dumpManager) {
        this.batteryViewModel = locationBasedBatteryViewModel;
        this.tunerService = tunerService;
        this.oplusQsColorManager = oplusQsColorManager;
        this.oplusPolicyObserver = oplusPolicyObserver;
        this.dumpManager = dumpManager;
        OplusSplitShadeHeaderControllerExImpl$splitHeaderLayoutChangeListener$1 oplusSplitShadeHeaderControllerExImpl$splitHeaderLayoutChangeListener$1 = new OplusSplitShadeHeaderControllerExImpl$splitHeaderLayoutChangeListener$1();
        oplusSplitShadeHeaderControllerExImpl$splitHeaderLayoutChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.splitHeaderLayoutChangeListener = oplusSplitShadeHeaderControllerExImpl$splitHeaderLayoutChangeListener$1;
        OplusSplitShadeHeaderControllerExImpl$mQsColorStateObserver$1 oplusSplitShadeHeaderControllerExImpl$mQsColorStateObserver$1 = new OplusSplitShadeHeaderControllerExImpl$mQsColorStateObserver$1();
        oplusSplitShadeHeaderControllerExImpl$mQsColorStateObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mQsColorStateObserver = oplusSplitShadeHeaderControllerExImpl$mQsColorStateObserver$1;
    }

    private final int getRightContentNeed() {
        StatBatteryMeterView statBatteryMeterView = this.batteryIcon;
        StatusIconContainer statusIconContainer = this.statusIconContainer;
        ViewGroup viewGroup = this.iconsStrip;
        return getViewWidthWithMargin(0, viewGroup != null ? viewGroup.getChildAt(0) : null) + getViewWidthWithMargin(statusIconContainer != null ? statusIconContainer.getRealNeedWidth() : 0, statusIconContainer) + getViewWidthWithMargin(0, statBatteryMeterView);
    }

    public static int getViewWidthWithMargin(int i, View view) {
        if (view == null || view.getVisibility() != 0) {
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

    public void applyDarkness(TintedIconManager tintedIconManager, Context context) {
        if (this.qsStatusIcon == null) {
            return;
        }
        this.iconManager = tintedIconManager;
        ViewGroup viewGroup = this.splitStatusbar;
        if (viewGroup == null) {
            viewGroup = null;
        }
        tintedIconManager.setTint(NotificationColorUtil.getQSDateTimeColor(viewGroup.getContext()), Utils.getColorAttrDefaultColor(R.attr.textColorPrimaryInverse, 0, context));
        ViewGroup viewGroup2 = this.splitStatusbar;
        int qSDateTimeColor = NotificationColorUtil.getQSDateTimeColor((viewGroup2 != null ? viewGroup2 : null).getContext());
        ArrayList<Rect> arrayList = new ArrayList<>();
        float f = Color.luminance(qSDateTimeColor) >= 0.4f ? 1.0f : 0.0f;
        StatBatteryMeterView statBatteryMeterView = this.batteryIcon;
        if (statBatteryMeterView != null) {
            statBatteryMeterView.onDarkChanged(arrayList, f, qSDateTimeColor);
        }
    }

    public final void applySplitHeaderHorizontalWidths() {
        View view;
        LinearLayout linearLayout;
        int width;
        ViewGroup viewGroup = this.qsStatusIcon;
        if (viewGroup == null || (view = this.carrierView) == null || (linearLayout = this.splitHeaderRow) == null || (width = (linearLayout.getWidth() - linearLayout.getPaddingLeft()) - linearLayout.getPaddingRight()) <= 0) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
        int marginStart = layoutParams instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams).getMarginStart() : 0;
        ViewGroup.LayoutParams layoutParams2 = viewGroup.getLayoutParams();
        int marginEnd = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams2).getMarginEnd() : 0;
        int i = (width - marginStart) - marginEnd;
        if (i < 0) {
            i = 0;
        }
        int iCoerceIn = RangesKt___RangesKt.coerceIn(getRightContentNeed(), 0, i);
        int i2 = marginStart + iCoerceIn + marginEnd;
        ViewGroup.LayoutParams layoutParams3 = view.getLayoutParams();
        int marginStart2 = layoutParams3 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams3).getMarginStart() : 0;
        ViewGroup.LayoutParams layoutParams4 = view.getLayoutParams();
        int marginEnd2 = ((width - i2) - marginStart2) - (layoutParams4 instanceof ViewGroup.MarginLayoutParams ? ((ViewGroup.MarginLayoutParams) layoutParams4).getMarginEnd() : 0);
        int i3 = marginEnd2 >= 0 ? marginEnd2 : 0;
        ViewGroup.LayoutParams layoutParams5 = viewGroup.getLayoutParams();
        LinearLayout.LayoutParams layoutParams6 = layoutParams5 instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams5 : null;
        if (layoutParams6 == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams7 = view.getLayoutParams();
        LinearLayout.LayoutParams layoutParams8 = layoutParams7 instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams7 : null;
        if (layoutParams8 == null) {
            return;
        }
        if (layoutParams6.width == iCoerceIn && layoutParams6.weight == 0.0f && layoutParams8.width == i3 && layoutParams8.weight == 0.0f) {
            return;
        }
        layoutParams6.width = iCoerceIn;
        layoutParams6.weight = 0.0f;
        layoutParams8.width = i3;
        layoutParams8.weight = 0.0f;
        viewGroup.setLayoutParams(layoutParams6);
        view.setLayoutParams(layoutParams8);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        StatBatteryMeterView statBatteryMeterView = this.batteryIcon;
        if (statBatteryMeterView != null) {
            statBatteryMeterView.dump(printWriter);
        }
    }

    public void init(ViewGroup viewGroup) {
        LinearLayout linearLayout = this.splitHeaderRow;
        if (linearLayout != null) {
            linearLayout.removeOnLayoutChangeListener(this.splitHeaderLayoutChangeListener);
        }
        this.dumpManager.registerDumpable(this);
        new DualToneHandler(new ContextThemeWrapper(viewGroup.getContext(), 2132018165));
        this.splitStatusbar = viewGroup;
        this.qsStatusIcon = (ViewGroup) viewGroup.findViewById(2131364818);
        this.carrierView = viewGroup.findViewById(2131364716);
        ViewGroup viewGroup2 = this.qsStatusIcon;
        if (viewGroup2 != null) {
            this.statusIconContainer = viewGroup2.findViewById(2131365358);
            this.iconsStrip = (ViewGroup) viewGroup2.findViewById(2131363411);
        }
        ViewGroup viewGroup3 = this.qsStatusIcon;
        ViewParent parent = viewGroup3 != null ? viewGroup3.getParent() : null;
        LinearLayout linearLayout2 = parent instanceof LinearLayout ? (LinearLayout) parent : null;
        this.splitHeaderRow = linearLayout2;
        if (linearLayout2 != null) {
            linearLayout2.addOnLayoutChangeListener(this.splitHeaderLayoutChangeListener);
        }
        StatBatteryMeterView statBatteryMeterView = (StatBatteryMeterView) viewGroup.findViewById(2131362234);
        this.batteryIcon = statBatteryMeterView;
        StatBatteryMeterViewController statBatteryMeterViewController = new StatBatteryMeterViewController(statBatteryMeterView, this.batteryViewModel, this.tunerService);
        this.batteryMeterViewController = statBatteryMeterViewController;
        statBatteryMeterViewController.init();
        StatBatteryMeterViewController statBatteryMeterViewController2 = this.batteryMeterViewController;
        if (statBatteryMeterViewController2 == null) {
            statBatteryMeterViewController2 = null;
        }
        statBatteryMeterViewController2.ignoreTunerUpdates();
        this.oplusQsColorManager.addQsColorStateObserver(this.mQsColorStateObserver);
        View view = this.carrierView;
        TextView textView = view instanceof TextView ? (TextView) view : null;
        if (textView != null) {
            ViewGroup viewGroup4 = this.splitStatusbar;
            QsColorUtil.adaptiveColor(textView, (viewGroup4 != null ? viewGroup4 : null).getResources().getColor(2131103705));
        }
        applySplitHeaderHorizontalWidths();
    }

    public final void onDensityOrFontScaleChanged() {
        if (this.qsStatusIcon == null) {
            return;
        }
        ViewGroup viewGroup = this.splitStatusbar;
        if (viewGroup == null) {
            viewGroup = null;
        }
        Resources resources = viewGroup.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(2131172051);
        ViewGroup viewGroup2 = this.splitStatusbar;
        if (viewGroup2 == null) {
            viewGroup2 = null;
        }
        ViewGroup.LayoutParams layoutParams = viewGroup2.getLayoutParams();
        layoutParams.height = resources.getDimensionPixelSize(2131172944);
        ViewGroup viewGroup3 = this.splitStatusbar;
        ViewGroup viewGroup4 = viewGroup3 == null ? null : viewGroup3;
        if (viewGroup3 == null) {
            viewGroup3 = null;
        }
        int paddingTop = viewGroup3.getPaddingTop();
        ViewGroup viewGroup5 = this.splitStatusbar;
        if (viewGroup5 == null) {
            viewGroup5 = null;
        }
        viewGroup4.setPadding(dimensionPixelSize, paddingTop, dimensionPixelSize, viewGroup5.getPaddingBottom());
        ViewGroup viewGroup6 = this.splitStatusbar;
        if (viewGroup6 == null) {
            viewGroup6 = null;
        }
        viewGroup6.setLayoutParams(layoutParams);
        ConstraintLayout constraintLayout = this.qsStatusIcon;
        if (constraintLayout instanceof ConstraintLayout) {
            constraintLayout.setMinHeight(resources.getDimensionPixelSize(2131172241));
        }
        ViewGroup viewGroup7 = this.qsStatusIcon;
        ViewGroup.LayoutParams layoutParams2 = viewGroup7 != null ? viewGroup7.getLayoutParams() : null;
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : null;
        if (marginLayoutParams != null) {
            marginLayoutParams.setMarginEnd(resources.getDimensionPixelSize(2131172239));
            marginLayoutParams.setMarginStart(resources.getDimensionPixelSize(2131172240));
            ViewGroup viewGroup8 = this.qsStatusIcon;
            if (viewGroup8 != null) {
                viewGroup8.setLayoutParams(marginLayoutParams);
            }
        }
        applySplitHeaderHorizontalWidths();
    }

    public void setListening(boolean z) {
    }
}
