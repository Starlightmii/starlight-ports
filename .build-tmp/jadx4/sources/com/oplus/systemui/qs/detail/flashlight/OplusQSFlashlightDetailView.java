package com.oplus.systemui.qs.detail.flashlight;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.compose.runtime.collection.MutableVectorKt$;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.ColorUtils;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.animation.view.LaunchableFrameLayout;
import com.android.systemui.shared.utils.DeviceUtils;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.utils.QsUtil;
import com.oplusos.systemui.common.flashlight.OplusFlashLightLevelHelper;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.DeviceUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSFlashlightDetailView extends LaunchableFrameLayout {
    private static final String TAG = "OplusQSFlashlightDetailView";
    private Context cachedNightContext;
    private FlashlightEffectHost effectHost;
    public boolean effectsAttached;
    private final Lazy fullEffectHost$delegate;
    private final Lazy isLowGaussian$delegate;
    private final Lazy liteEffectHost$delegate;
    private View mFlashlightButtonIcon;
    private final OplusFlashlightButtonIconAnimator mFlashlightButtonIconAnimator;
    private View mFlashlightButtonIconInner;
    private View mFlashlightCard;
    private OplusFlashlightDashedArcView mFlashlightDashedArc;
    private ImageView mFlashlightIconShell;
    private ImageView mFlashlightOnEffect;
    private TextView mFlashlightText;
    private TextView mFlashlightTorchText;
    public boolean mIsFlashLightOn;
    public boolean mIsLandscape;
    private Function0 onSecondaryStructureRebuilt;
    public static final Companion Companion = new Companion();
    private static volatile Function1 factory = new OplusQSFlashlightDetailView$.ExternalSyntheticLambda3();

    /* JADX WARN: Multi-variable type inference failed */
    public OplusQSFlashlightDetailView(Context context) {
        super(context);
        this.mFlashlightButtonIconAnimator = new OplusFlashlightButtonIconAnimator(context);
        this.liteEffectHost$delegate = LazyKt__LazyJVMKt.lazy(new OplusQSFlashlightDetailView$.ExternalSyntheticLambda0(0));
        this.fullEffectHost$delegate = LazyKt__LazyJVMKt.lazy(new OplusQSFlashlightDetailView$.ExternalSyntheticLambda0(1));
        OplusQSFlashlightDetailView$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new OplusQSFlashlightDetailView$.ExternalSyntheticLambda2();
        externalSyntheticLambda2.f$0 = context;
        VarHandle.storeStoreFence();
        this.isLowGaussian$delegate = LazyKt__LazyJVMKt.lazy(externalSyntheticLambda2);
        getResources().getBoolean(2131034332);
        QsUtil.isPowerSavingMode();
        this.mIsLandscape = DeviceUtil.isLandscape(context);
        inflateSecondaryLayout();
        onThreeFlashConfigure();
    }

    private final FullFlashlightEffectHost getFullEffectHost() {
        return (FullFlashlightEffectHost) this.fullEffectHost$delegate.getValue();
    }

    private final LiteFlashlightEffectHost getLiteEffectHost() {
        return (LiteFlashlightEffectHost) this.liteEffectHost$delegate.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Context getNightDrawableContext() {
        Context context = this.cachedNightContext;
        if (context != null) {
            return context;
        }
        Context context2 = getContext();
        Configuration configuration = new Configuration(getResources().getConfiguration());
        configuration.uiMode = (configuration.uiMode & (-49)) | 32;
        Context contextCreateConfigurationContext = context2.createConfigurationContext(configuration);
        this.cachedNightContext = contextCreateConfigurationContext;
        return contextCreateConfigurationContext;
    }

    public static void setSpacerHeight(int i, View view) {
        ConstraintLayout.LayoutParams layoutParams = view.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams2 = layoutParams instanceof ConstraintLayout.LayoutParams ? layoutParams : null;
        if (layoutParams2 == null) {
            return;
        }
        if (((ViewGroup.MarginLayoutParams) layoutParams2).height == i && layoutParams2.matchConstraintPercentHeight == 0.0f && layoutParams2.verticalWeight == 0.0f) {
            return;
        }
        ((ViewGroup.MarginLayoutParams) layoutParams2).height = i;
        layoutParams2.matchConstraintPercentHeight = 0.0f;
        layoutParams2.verticalWeight = 0.0f;
    }

    public final void applyProgressToUi(float f, float f2, ProgressRenderMode progressRenderMode) {
        Pair<Float, Float> pairMapDragProgressToEffectHost = OplusFlashLightLevelHelper.mapDragProgressToEffectHost(f, f2);
        float fFloatValue = ((Number) pairMapDragProgressToEffectHost.component1()).floatValue();
        float fFloatValue2 = ((Number) pairMapDragProgressToEffectHost.component2()).floatValue();
        BaseFlashlightEffectHost baseFlashlightEffectHost = this.effectHost;
        if (baseFlashlightEffectHost != null) {
            baseFlashlightEffectHost.updateProgress(fFloatValue, fFloatValue2, progressRenderMode);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void attachFlashlightEffectsInternal() {
        LiteFlashlightEffectHost liteEffectHost = ((Boolean) this.isLowGaussian$delegate.getValue()).booleanValue() ? getLiteEffectHost() : getFullEffectHost();
        LiteFlashlightEffectHost liteFlashlightEffectHost = this.effectHost;
        if (liteFlashlightEffectHost != liteEffectHost) {
            if (liteFlashlightEffectHost != null) {
                ((BaseFlashlightEffectHost) liteFlashlightEffectHost).release();
            }
            this.effectHost = liteEffectHost;
        }
        liteEffectHost.release();
        liteEffectHost.attach(this);
        this.effectsAttached = true;
    }

    public final void bindFlashlightShellButtonArc(Context context) {
        ImageView imageView = this.mFlashlightIconShell;
        if (imageView != null) {
            imageView.setImageDrawable(context.getDrawable(2131236017));
        }
        View view = this.mFlashlightButtonIcon;
        ImageView imageView2 = view instanceof ImageView ? (ImageView) view : null;
        if (imageView2 != null) {
            imageView2.setImageDrawable(context.getDrawable(2131236015));
        }
        OplusFlashlightDashedArcView oplusFlashlightDashedArcView = this.mFlashlightDashedArc;
        if (oplusFlashlightDashedArcView != null) {
            oplusFlashlightDashedArcView.setArcColor(context.getColor(2131103240));
        }
    }

    public final void destroyDialog() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSFlashlightDetailView-->destroyDialog()");
        }
        this.onSecondaryStructureRebuilt = null;
        onThreeFlashSecondaryViewDismissed();
        onThreeFlashReset();
        BaseFlashlightEffectHost baseFlashlightEffectHost = this.effectHost;
        if (baseFlashlightEffectHost != null) {
            baseFlashlightEffectHost.release();
        }
        this.effectHost = null;
        this.mFlashlightButtonIconAnimator.release();
        this.mFlashlightButtonIcon = null;
        this.mFlashlightButtonIconInner = null;
        this.mFlashlightCard = null;
        this.mFlashlightDashedArc = null;
        this.mFlashlightIconShell = null;
        this.mFlashlightOnEffect = null;
        this.mFlashlightText = null;
        this.mFlashlightTorchText = null;
        this.cachedNightContext = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        List<CharSequence> text;
        if (accessibilityEvent != null && (text = accessibilityEvent.getText()) != null) {
            text.add("");
        }
        return super/*android.widget.FrameLayout*/.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    public final boolean getEffectsAttached() {
        return this.effectsAttached;
    }

    public final Function0 getOnSecondaryStructureRebuilt() {
        return this.onSecondaryStructureRebuilt;
    }

    public /* bridge */ /* synthetic */ Rect getPaddingForLaunchAnimation() {
        return super/*com.android.systemui.animation.LaunchableView*/.getPaddingForLaunchAnimation();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void inflateSecondaryLayout() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("inflateInitialLayout() isLandscape = ", "OplusQSFlashlightDetailView-->", "SystemUi--QuickSettings", this.mIsLandscape);
        }
        LayoutInflater.from(getContext()).inflate(2131559345, (ViewGroup) this, true);
        View viewFindViewById = findViewById(2131363152);
        if (viewFindViewById != null) {
            viewFindViewById.setBackground(FlashlightCardBgDrawableUtils.INSTANCE.create(viewFindViewById));
        } else {
            viewFindViewById = null;
        }
        this.mFlashlightCard = viewFindViewById;
        this.mFlashlightText = (TextView) findViewById(2131363159);
        this.mFlashlightTorchText = (TextView) findViewById(2131363160);
        this.mFlashlightButtonIcon = findViewById(2131363155);
        this.mFlashlightButtonIconInner = findViewById(2131363156);
        this.mFlashlightIconShell = (ImageView) findViewById(2131363157);
        this.mFlashlightOnEffect = (ImageView) findViewById(2131363158);
        this.mFlashlightDashedArc = findViewById(2131364760);
        this.mFlashlightButtonIconAnimator.bind(CollectionsKt__CollectionsKt.listOfNotNull(new View[]{this.mFlashlightButtonIcon, this.mFlashlightButtonIconInner}), this.mFlashlightOnEffect, this.mIsFlashLightOn);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onAttachedToWindow() {
        super/*android.widget.FrameLayout*/.onAttachedToWindow();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onConfigurationChanged(Configuration configuration) {
        super/*android.widget.FrameLayout*/.onConfigurationChanged(configuration);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSFlashlightDetailView-->".concat("onConfigurationChanged() " + (configuration != null ? Integer.valueOf(configuration.orientation) : null)));
        }
        onThreeFlashConfigure();
        boolean z = false;
        if (configuration != null && configuration.orientation == 2) {
            z = true;
        }
        if (this.mIsLandscape != z) {
            this.mIsLandscape = z;
            rebuildStructureForOrientation(true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onDetachedFromWindow() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusQSFlashlightDetailView-->onDetachedFromWindow()");
        }
        destroyDialog();
        super/*android.widget.FrameLayout*/.onDetachedFromWindow();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onMeasure(int i, int i2) {
        int size;
        ConstraintLayout constraintLayoutFindViewById;
        View viewFindViewById;
        View viewFindViewById2;
        View viewFindViewById3;
        DeviceUtils.INSTANCE.getClass();
        if ((DeviceUtils.isTablet() || DeviceUtil.shouldUseLargeFoldConfig(getResources())) && View.MeasureSpec.getMode(i2) == 1073741824 && (size = View.MeasureSpec.getSize(i2)) > 0 && (constraintLayoutFindViewById = findViewById(2131362629)) != null && (viewFindViewById = constraintLayoutFindViewById.findViewById(2131365734)) != null && (viewFindViewById2 = constraintLayoutFindViewById.findViewById(2131363958)) != null && (viewFindViewById3 = constraintLayoutFindViewById.findViewById(2131362322)) != null) {
            int paddingTop = (((size - constraintLayoutFindViewById.getPaddingTop()) - constraintLayoutFindViewById.getPaddingBottom()) - getResources().getDimensionPixelSize(2131171831)) - getResources().getDimensionPixelSize(2131171812);
            if (paddingTop <= 0) {
                LogUtil.w$default(8, LogUtil.TAG_QS, TAG, MutableVectorKt$.ExternalSyntheticOutline0.m("applyLargeScreenSpacerHeights: pool=", paddingTop, size, ", container=", ", skip"));
            } else {
                int i3 = (paddingTop * 3) / 8;
                int dimensionPixelSize = getResources().getDimensionPixelSize(2131171843);
                if (i3 > dimensionPixelSize) {
                    i3 = dimensionPixelSize;
                }
                int i4 = paddingTop - i3;
                int i5 = (i4 * 3) / 5;
                setSpacerHeight(i5, viewFindViewById);
                setSpacerHeight(i3, viewFindViewById2);
                setSpacerHeight(i4 - i5, viewFindViewById3);
            }
        }
        super/*android.widget.FrameLayout*/.onMeasure(i, i2);
    }

    public void onThreeFlashConfigure() {
    }

    public void onThreeFlashReset() {
    }

    public void onThreeFlashSecondaryViewDismissed() {
    }

    public void onThreeFlashSecondaryViewShowed() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void rebuildStructureForOrientation(boolean z) {
        removeAllViews();
        inflateSecondaryLayout();
        if (isShown()) {
            View viewFindViewById = findViewById(2131363153);
            if (viewFindViewById != null) {
                viewFindViewById.setAlpha(1.0f);
            }
            View viewFindViewById2 = findViewById(2131363154);
            if (viewFindViewById2 != null) {
                viewFindViewById2.setAlpha(1.0f);
            }
        }
        if (z) {
            refreshAppearance();
            onThreeFlashConfigure();
            onThreeFlashSecondaryViewShowed();
        }
        boolean z2 = this.effectsAttached;
        if (z2) {
            BaseFlashlightEffectHost baseFlashlightEffectHost = this.effectHost;
            if (baseFlashlightEffectHost != null) {
                baseFlashlightEffectHost.release();
            }
            this.effectHost = null;
            this.effectsAttached = false;
        }
        if (z2) {
            attachFlashlightEffectsInternal();
        }
        Function0 function0 = this.onSecondaryStructureRebuilt;
        if (function0 != null) {
            function0.invoke();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void refreshAppearance() {
        if (QsColorUtil.isGlobalThemeApplied(getContext())) {
            int color = getContext().getColor(2131103752);
            TextView textView = this.mFlashlightText;
            if (textView != null) {
                textView.setTextColor(color);
            }
            TextView textView2 = this.mFlashlightTorchText;
            if (textView2 != null) {
                textView2.setTextColor(color);
            }
            ColorStateList colorStateListValueOf = ColorStateList.valueOf(color);
            ImageView imageView = this.mFlashlightIconShell;
            if (imageView != null) {
                imageView.setImageTintList(colorStateListValueOf);
            }
            View view = this.mFlashlightButtonIcon;
            ImageView imageView2 = view instanceof ImageView ? (ImageView) view : null;
            if (imageView2 != null) {
                imageView2.setImageTintList(colorStateListValueOf);
            }
            OplusFlashlightDashedArcView oplusFlashlightDashedArcView = this.mFlashlightDashedArc;
            if (oplusFlashlightDashedArcView != null) {
                oplusFlashlightDashedArcView.setArcColor(ColorUtils.setAlphaComponent(color, 66));
            }
        } else if (QsColorUtil.isNeedDarkThemeColor(getContext(), false)) {
            TextView textView3 = this.mFlashlightText;
            if (textView3 != null) {
                textView3.setTextColor(getContext().getColor(2131103235));
            }
            TextView textView4 = this.mFlashlightTorchText;
            if (textView4 != null) {
                textView4.setTextColor(getContext().getColor(2131100939));
            }
            bindFlashlightShellButtonArc(getNightDrawableContext());
        } else if (QsColorUtil.isTextNeedUseLightColorWhenLight(getContext(), QsColorUtil.getQsColorState())) {
            int color2 = getContext().getColor(2131103235);
            TextView textView5 = this.mFlashlightText;
            if (textView5 != null) {
                textView5.setTextColor(color2);
            }
            TextView textView6 = this.mFlashlightTorchText;
            if (textView6 != null) {
                textView6.setTextColor(color2);
            }
            bindFlashlightShellButtonArc(getNightDrawableContext());
        } else {
            int color3 = getContext().getColor(2131103706);
            TextView textView7 = this.mFlashlightText;
            if (textView7 != null) {
                textView7.setTextColor(color3);
            }
            TextView textView8 = this.mFlashlightTorchText;
            if (textView8 != null) {
                textView8.setTextColor(color3);
            }
            bindFlashlightShellButtonArc(getContext());
        }
        View viewFindViewById = findViewById(2131363153);
        if (viewFindViewById != null) {
            viewFindViewById.setBackgroundColor(getContext().getColor(((Boolean) this.isLowGaussian$delegate.getValue()).booleanValue() ? 2131103220 : 2131103219));
        }
    }

    public final void renderRestrictSecondaryText(Integer num) {
        TextView textView = this.mFlashlightTorchText;
        if (textView != null) {
            if (num == null) {
                textView.setVisibility(8);
            } else {
                textView.setText(num.intValue());
                textView.setVisibility(0);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void renderSwitchState(boolean z) {
        View view;
        boolean z2 = this.mIsFlashLightOn;
        this.mIsFlashLightOn = z;
        int i = z ? 2131955655 : 2131955654;
        TextView textView = this.mFlashlightText;
        if (textView != null) {
            textView.setText(i);
        }
        String string = getContext().getString(z ? 2131954868 : 2131954866);
        View view2 = this.mFlashlightCard;
        if (view2 != null) {
            view2.setContentDescription(string);
        }
        if (z2 != z && isShown() && (view = this.mFlashlightCard) != null) {
            view.announceForAccessibility(string);
        }
        this.mFlashlightButtonIconAnimator.setFlashlightStatus(z);
    }

    public final void setEffectsAttached(boolean z) {
        this.effectsAttached = z;
    }

    public final void setOnSecondaryStructureRebuilt(Function0 function0) {
        this.onSecondaryStructureRebuilt = function0;
    }

    public void updateLiteEffectColorTemperature(int i) {
    }
}
