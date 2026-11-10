package com.oplus.systemui.statusbar.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Insets;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.Dependency;
import com.android.systemui.DualToneHandler;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.util.leak.RotationUtils;
import com.oplus.systemui.keyguard.helper.ScreenSizeHelper;
import com.oplus.systemui.statusbar.phone.StatusBarPaddingProvider;
import com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableFactory;
import com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.HorizontalBatteryContentDrawable;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt___StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class FixedStatusBarView extends LinearLayout implements DarkIconDispatcher.DarkReceiver {
    public static final Companion Companion = new Companion();
    private static final String FIXED_TIME = "19:30";
    private static final String TAG = "FixedStatusBarView";
    private HorizontalBatteryContentDrawable batteryDrawable;
    private ImageView batteryView;
    private TextView clockView;
    public float darkIntensity;
    private DisplayCutout displayCutout;
    private final DualToneHandler dualToneHandler;
    private final String fixedTime;
    private final Lazy holeBottom$delegate;
    private final Lazy holeHeight$delegate;
    private final Lazy holeTop$delegate;
    public int iconTint;
    public int leftMargin;
    private ImageView mobileSignalView;
    public int physicScreenWidth;
    public int rightMargin;
    public int rotationOrientation;
    private final Lazy statusBarHeight$delegate;
    private final StatusBarPaddingProvider statusBarPaddingProvider;
    private final ArrayList<Rect> tintAreas;
    private ImageView wifiSignalView;

    public FixedStatusBarView(Context context, StatusBarPaddingProvider statusBarPaddingProvider, AttributeSet attributeSet, int i, String str) {
        Typeface typefaceBuild;
        super(context, attributeSet, i);
        this.statusBarPaddingProvider = statusBarPaddingProvider;
        this.fixedTime = str;
        this.tintAreas = new ArrayList<>();
        this.dualToneHandler = new DualToneHandler(context);
        FixedStatusBarView$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new FixedStatusBarView$.ExternalSyntheticLambda0(0);
        externalSyntheticLambda0.f$0 = context;
        VarHandle.storeStoreFence();
        this.holeHeight$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda0);
        FixedStatusBarView$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new FixedStatusBarView$.ExternalSyntheticLambda0(1);
        externalSyntheticLambda1.f$0 = context;
        VarHandle.storeStoreFence();
        this.holeTop$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda1);
        FixedStatusBarView$.ExternalSyntheticLambda0 externalSyntheticLambda2 = new FixedStatusBarView$.ExternalSyntheticLambda0(2);
        externalSyntheticLambda2.f$0 = context;
        VarHandle.storeStoreFence();
        this.holeBottom$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda2);
        FixedStatusBarView$.ExternalSyntheticLambda0 externalSyntheticLambda3 = new FixedStatusBarView$.ExternalSyntheticLambda0(3);
        externalSyntheticLambda3.f$0 = context;
        VarHandle.storeStoreFence();
        this.statusBarHeight$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda3);
        this.rotationOrientation = -1;
        this.iconTint = -1;
        setOrientation(0);
        setGravity(16);
        TextView textView = new TextView(getContext());
        this.clockView = textView;
        textView.setTextColor(getContext().getColor(2131101481));
        TextView textView2 = this.clockView;
        if (textView2 != null) {
            textView2.setTextSize(0, getContext().getResources().getDimensionPixelSize(2131172971));
        }
        TextView textView3 = this.clockView;
        if (textView3 != null) {
            textView3.setText((str == null || str.length() == 0) ? FIXED_TIME : StringsKt___StringsKt.take(str, 8));
        }
        TextView textView4 = this.clockView;
        if (textView4 != null) {
            try {
                Typeface.Builder builder = new Typeface.Builder(getContext().getAssets(), "OplusSans2.0_NoGX.ttf");
                builder.setFontVariationSettings("'wght' 700");
                typefaceBuild = builder.build();
            } catch (Exception unused) {
                typefaceBuild = Typeface.DEFAULT;
            }
            textView4.setTypeface(typefaceBuild);
        }
        ImageView imageView = new ImageView(getContext());
        this.wifiSignalView = imageView;
        imageView.setImageResource(2131236929);
        ImageView imageView2 = new ImageView(getContext());
        this.mobileSignalView = imageView2;
        imageView2.setImageResource(2131233503);
        this.batteryView = new ImageView(getContext());
        addView(this.clockView, new LinearLayout.LayoutParams(-2, -2));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getContext().getResources().getDimensionPixelSize(2131172969), getContext().getResources().getDimensionPixelSize(2131172969));
        layoutParams.weight = 1.0f;
        addView(new View(getContext()), layoutParams);
        addView(this.wifiSignalView, new LinearLayout.LayoutParams(-2, -2));
        addView(this.mobileSignalView, new LinearLayout.LayoutParams(-2, -2));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(getContext().getResources().getDimensionPixelSize(2131172998), getContext().getResources().getDimensionPixelSize(2131172989));
        layoutParams2.setMarginStart(getContext().getResources().getDimensionPixelSize(2131165612));
        HorizontalBatteryContentDrawable horizontalBatteryContentDrawable = (HorizontalBatteryContentDrawable) BatteryContentDrawableFactory.INSTANCE.createContentDrawable(getContext(), 1, 0);
        this.batteryDrawable = horizontalBatteryContentDrawable;
        if (horizontalBatteryContentDrawable != null) {
            horizontalBatteryContentDrawable.setShowPercentIn(true);
            horizontalBatteryContentDrawable.setBatteryLevel(100);
        }
        ImageView imageView3 = this.batteryView;
        if (imageView3 != null) {
            imageView3.setImageDrawable(this.batteryDrawable);
        }
        addView(this.batteryView, layoutParams2);
        this.physicScreenWidth = ScreenSizeHelper.Companion.getInstance(context.getApplicationContext()).getBaseDisplayWidthSize();
    }

    public final DisplayCutout getDisplayCutout() {
        return this.displayCutout;
    }

    public final int getHoleBottom() {
        return ((Number) this.holeBottom$delegate.getValue()).intValue();
    }

    public final int getHoleHeight() {
        return ((Number) this.holeHeight$delegate.getValue()).intValue();
    }

    public final int getHoleTop() {
        return ((Number) this.holeTop$delegate.getValue()).intValue();
    }

    public final int getLeftMargin() {
        return this.leftMargin;
    }

    public final int getPhysicScreenWidth() {
        return this.physicScreenWidth;
    }

    public final int getRightMargin() {
        return this.rightMargin;
    }

    public final int getRotationOrientation() {
        return this.rotationOrientation;
    }

    public final int getStatusBarHeight() {
        return ((Number) this.statusBarHeight$delegate.getValue()).intValue();
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        Insets insetsIgnoringVisibility = windowInsets != null ? windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars()) : null;
        int i = insetsIgnoringVisibility != null ? insetsIgnoringVisibility.left : 0;
        this.leftMargin = i;
        int i2 = insetsIgnoringVisibility != null ? insetsIgnoringVisibility.right : 0;
        this.rightMargin = i2;
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "onApplyWindowInsets, insets = " + insetsIgnoringVisibility + ".leftMargin = " + i + ", rightMargin = " + i2, null, 8);
        return super.onApplyWindowInsets(windowInsets);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        boolean z;
        super.onAttachedToWindow();
        int exactRotation = RotationUtils.getExactRotation(getContext());
        if (exactRotation != this.rotationOrientation) {
            this.rotationOrientation = exactRotation;
            z = true;
        } else {
            z = false;
        }
        if (!Intrinsics.areEqual(getRootWindowInsets().getDisplayCutout(), this.displayCutout)) {
            this.displayCutout = getRootWindowInsets().getDisplayCutout();
            z = true;
        }
        if (z) {
            updateSafeInsets();
        }
        DarkIconDispatcher darkIconDispatcher = (DarkIconDispatcher) Dependency.sDependency.getDependencyInner(DarkIconDispatcher.class);
        if (darkIconDispatcher != null) {
            darkIconDispatcher.addDarkReceiver(this);
        }
        onDarkChanged((ArrayList) CollectionsKt___CollectionsKt.toMutableList(this.tintAreas), this.darkIntensity, this.iconTint, true);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.statusBarPaddingProvider.updateKeyguardStatusBarPadding(this, new Pair<>(Integer.valueOf(this.leftMargin), Integer.valueOf(this.rightMargin)));
        requestLayout();
    }

    public final void onDarkChanged(ArrayList<Rect> arrayList, float f, int i, boolean z) {
        boolean z2 = (i == this.iconTint && f == this.darkIntensity && Intrinsics.areEqual(this.tintAreas, arrayList)) ? false : true;
        this.iconTint = i;
        this.darkIntensity = f;
        this.tintAreas.clear();
        this.tintAreas.addAll(arrayList);
        if ((z || z2) && isAttachedToWindow()) {
            TextView textView = this.clockView;
            if (textView != null) {
                textView.setTextColor(DarkIconDispatcher.getTint(this.tintAreas, textView, this.iconTint));
            }
            ImageView imageView = this.wifiSignalView;
            if (imageView != null) {
                imageView.setImageTintList(ColorStateList.valueOf(DarkIconDispatcher.getTint(this.tintAreas, imageView, this.iconTint)));
            }
            ImageView imageView2 = this.mobileSignalView;
            if (imageView2 != null) {
                imageView2.setImageTintList(ColorStateList.valueOf(DarkIconDispatcher.getTint(this.tintAreas, imageView2, this.iconTint)));
            }
            HorizontalBatteryContentDrawable horizontalBatteryContentDrawable = this.batteryDrawable;
            if (horizontalBatteryContentDrawable != null) {
                horizontalBatteryContentDrawable.setColors(this.dualToneHandler.getSingleColor(this.darkIntensity), this.dualToneHandler.getFillColor(this.darkIntensity), this.dualToneHandler.getBackgroundColor(this.darkIntensity));
            }
            HorizontalBatteryContentDrawable horizontalBatteryContentDrawable2 = this.batteryDrawable;
            if (horizontalBatteryContentDrawable2 != null) {
                horizontalBatteryContentDrawable2.invalidateSelf();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        DarkIconDispatcher darkIconDispatcher = (DarkIconDispatcher) Dependency.sDependency.getDependencyInner(DarkIconDispatcher.class);
        if (darkIconDispatcher != null) {
            darkIconDispatcher.removeDarkReceiver(this);
        }
        HorizontalBatteryContentDrawable horizontalBatteryContentDrawable = this.batteryDrawable;
        if (horizontalBatteryContentDrawable != null) {
            horizontalBatteryContentDrawable.onDestroy();
        }
    }

    public final void setDisplayCutout(DisplayCutout displayCutout) {
        this.displayCutout = displayCutout;
    }

    public final void setLeftMargin(int i) {
        this.leftMargin = i;
    }

    public final void setPhysicScreenWidth(int i) {
        this.physicScreenWidth = i;
    }

    public final void setRightMargin(int i) {
        this.rightMargin = i;
    }

    public final void setRotationOrientation(int i) {
        this.rotationOrientation = i;
    }

    public void updateSafeInsets() {
        this.statusBarPaddingProvider.updateKeyguardStatusBarPadding(this, new Pair<>(Integer.valueOf(this.leftMargin), Integer.valueOf(this.rightMargin)));
    }

    public void onDarkChanged(ArrayList<Rect> arrayList, float f, int i) {
        onDarkChanged(arrayList, f, i, false);
    }

    public FixedStatusBarView(Context context, StatusBarPaddingProvider statusBarPaddingProvider, AttributeSet attributeSet) {
        this(context, statusBarPaddingProvider, attributeSet, 0, null, 24);
    }

    public FixedStatusBarView(Context context, StatusBarPaddingProvider statusBarPaddingProvider, AttributeSet attributeSet, int i) {
        this(context, statusBarPaddingProvider, attributeSet, i, null, 16);
    }

    public /* synthetic */ FixedStatusBarView(Context context, StatusBarPaddingProvider statusBarPaddingProvider, AttributeSet attributeSet, int i, String str, int i2) {
        this(context, statusBarPaddingProvider, (i2 & 4) != 0 ? null : attributeSet, (i2 & 8) != 0 ? 0 : i, (i2 & 16) != 0 ? null : str);
    }

    public FixedStatusBarView(Context context, StatusBarPaddingProvider statusBarPaddingProvider) {
        this(context, statusBarPaddingProvider, null, 0, null, 28);
    }
}
