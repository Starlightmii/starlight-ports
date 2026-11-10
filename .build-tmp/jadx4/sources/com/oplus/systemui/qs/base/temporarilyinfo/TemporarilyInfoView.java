package com.oplus.systemui.qs.base.temporarilyinfo;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.compose.ui.spatial.RectList$;
import com.android.internal.policy.SystemBarUtils;
import com.android.systemui.keyboard.backlight.ui.view.KeyboardBacklightDialog$RootProperties$;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.base.util.SpecialLanguageTextScaleHelper;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.view.ViewExtKt;
import java.lang.invoke.VarHandle;
import kotlin.Triple;
import kotlin.jvm.functions.Function0;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TemporarilyInfoView extends LinearLayout {
    public static final Companion Companion = new Companion();
    private static final String TAG = "TemporarilyInfoView";
    private Float autoMainTextSize;
    private Float autoSubTextSize;
    private final Function0 getLandSplitMode;
    public boolean isLandSplitLayout;
    public int lastOrientation;
    private final TextView mainLabelView;
    private ViewGroup parent;
    private ViewGroup qsQuickEntranceContainerView;
    public int separateExtraSpace;
    private ViewGroup statusBarContainerView;
    private TemporarilyInfoManager.InfoShowStyle style;
    private final TextView subLabelView;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public TemporarilyInfoView(Context context, ViewGroup viewGroup, ViewGroup viewGroup2, ViewGroup viewGroup3, TemporarilyInfoManager.InfoShowStyle infoShowStyle, Function0 function0) {
        super(context);
        this.parent = viewGroup;
        this.statusBarContainerView = viewGroup2;
        this.qsQuickEntranceContainerView = viewGroup3;
        this.style = infoShowStyle;
        this.getLandSplitMode = function0;
        this.lastOrientation = getResources().getConfiguration().orientation;
        Triple layoutParams = getLayoutParams(this, this.parent, this.statusBarContainerView, this.qsQuickEntranceContainerView, null);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams.component1();
        float fFloatValue = ((Number) layoutParams.component2()).floatValue();
        float fFloatValue2 = ((Number) layoutParams.component3()).floatValue();
        setOrientation(1);
        setGravity(17);
        setId(2131365612);
        ViewGroup.LayoutParams layoutParams2 = new ViewGroup.LayoutParams(-1, -2);
        TextView textViewCreateTextView = createTextView(layoutParams2);
        updateMaxLines(textViewCreateTextView);
        setAutoSizeMainText(textViewCreateTextView, this.style);
        this.mainLabelView = textViewCreateTextView;
        TextView textViewCreateTextView2 = createTextView(layoutParams2);
        textViewCreateTextView2.setMaxLines(2);
        setAutoSizeSubText(textViewCreateTextView2, this.style);
        textViewCreateTextView2.setVisibility(8);
        this.subLabelView = textViewCreateTextView2;
        addView(textViewCreateTextView);
        addView(textViewCreateTextView2);
        textViewCreateTextView2.setTextColor(this.style.getTextColor());
        textViewCreateTextView.setTextColor(this.style.getTextColor());
        updateMaxLines(textViewCreateTextView);
        setAutoSizeMainText(textViewCreateTextView, this.style);
        setAutoSizeSubText(textViewCreateTextView2, this.style);
        setAlpha(0.0f);
        this.parent.addView(this, marginLayoutParams);
        setTranslationY(fFloatValue);
        setTranslationX(fFloatValue2);
        if (this.style.getAlongWithReplacedView()) {
            ViewExtKt.startAlongWithPosition(this, getAlongWithView());
        }
    }

    public static void updateMaxLines(TextView textView) {
        QsColorUtil.INSTANCE.getClass();
        if (QsColorUtil.isSuperPowerSaveState) {
            textView.setMaxLines(1);
            return;
        }
        int i = textView.getResources().getConfiguration().orientation;
        FeatureOption featureOption = FeatureOption.INSTANCE;
        featureOption.getClass();
        if (!FeatureOption.isTablet()) {
            Resources resources = textView.getResources();
            featureOption.getClass();
            if ((!FeatureOption.isFoldDevice() || !DeviceUtil.shouldUseLargeFoldConfig(resources)) && i == 2) {
                textView.setMaxLines(1);
                return;
            }
        }
        textView.setMaxLines(2);
    }

    public final TextView createTextView(ViewGroup.LayoutParams layoutParams) {
        TextView textView = new TextView(getContext());
        textView.setTextColor(this.style.getTextColor());
        textView.setGravity(17);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    public final View getAlongWithView() {
        return (((Boolean) this.getLandSplitMode.invoke()).booleanValue() || (this.parent instanceof FrameLayout)) ? this.statusBarContainerView : this.qsQuickEntranceContainerView;
    }

    public final Function0 getGetLandSplitMode() {
        return this.getLandSplitMode;
    }

    /* JADX WARN: Code duplicated, block: B:44:0x012a  */
    public final Triple getLayoutParams(TemporarilyInfoView temporarilyInfoView, ViewGroup viewGroup, ViewGroup viewGroup2, ViewGroup viewGroup3, ViewGroup.MarginLayoutParams marginLayoutParams) {
        int dimensionPixelSize;
        if (marginLayoutParams == null) {
            marginLayoutParams = new ViewGroup.MarginLayoutParams(0, 0);
        }
        boolean z = viewGroup.getContext().getResources().getConfiguration().orientation == 2;
        boolean z2 = viewGroup instanceof FrameLayout;
        int height = viewGroup3.getVisibility() == 8 ? 0 : viewGroup3.getHeight();
        int dimensionPixelSize2 = viewGroup.getContext().getResources().getDimensionPixelSize(2131172165);
        if (!z) {
            marginLayoutParams.width = viewGroup3.getWidth();
            if (z2) {
                FrameLayout frameLayout = (FrameLayout) viewGroup;
                int statusBarHeight = SystemBarUtils.getStatusBarHeight(frameLayout.getContext());
                marginLayoutParams.height = ((viewGroup2.getHeight() + height) - statusBarHeight) - frameLayout.getContext().getResources().getDimensionPixelSize(2131172163);
                marginLayoutParams.topMargin = statusBarHeight;
            } else {
                QsColorUtil.INSTANCE.getClass();
                if (QsColorUtil.isSuperPowerSaveState) {
                    dimensionPixelSize = 0;
                } else {
                    dimensionPixelSize = this.separateExtraSpace;
                    if (dimensionPixelSize == 0) {
                        dimensionPixelSize = viewGroup.getContext().getResources().getDimensionPixelSize(2131172157);
                    }
                }
                this.separateExtraSpace = dimensionPixelSize;
                marginLayoutParams.height = Math.max(height, dimensionPixelSize2) + viewGroup2.getHeight() + this.separateExtraSpace;
            }
        } else if (z2) {
            marginLayoutParams.topMargin = 0;
            Resources resources = ((FrameLayout) viewGroup).getResources();
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isFoldDevice() && DeviceUtil.shouldUseLargeFoldConfig(resources)) {
                marginLayoutParams.width = viewGroup3.getWidth();
                marginLayoutParams.height = viewGroup3.getHeight() + viewGroup2.getHeight();
            } else {
                marginLayoutParams.width = Math.max(viewGroup3.getWidth(), viewGroup2.getWidth());
                marginLayoutParams.height = Math.max(viewGroup2.getHeight(), viewGroup3.getHeight());
            }
        } else if (this.isLandSplitLayout) {
            marginLayoutParams.width = viewGroup2.getWidth();
            QsColorUtil.INSTANCE.getClass();
            if (QsColorUtil.isSuperPowerSaveState) {
                marginLayoutParams.height = viewGroup2.getHeight() + dimensionPixelSize2;
            } else {
                marginLayoutParams.height = viewGroup2.getHeight();
            }
        } else {
            marginLayoutParams.width = viewGroup3.getWidth();
            marginLayoutParams.height = Math.max(height, dimensionPixelSize2) + viewGroup2.getHeight();
        }
        if (this.isLandSplitLayout || z2) {
            viewGroup3 = viewGroup2;
        } else {
            QsColorUtil.INSTANCE.getClass();
            if (QsColorUtil.isSuperPowerSaveState) {
                viewGroup3 = viewGroup2;
            }
        }
        float translationY = viewGroup3.getTranslationY() + viewGroup3.getTop();
        int left = viewGroup3.getLeft();
        if (z2) {
            int dimensionPixelSize3 = ((FrameLayout) viewGroup).getContext().getResources().getDimensionPixelSize(2131172164);
            temporarilyInfoView.setPadding(dimensionPixelSize3, 0, dimensionPixelSize3, 0);
        } else {
            temporarilyInfoView.setPadding(viewGroup2.getPaddingLeft(), 0, viewGroup2.getPaddingRight(), 0);
        }
        int i = marginLayoutParams.width;
        int i2 = marginLayoutParams.height;
        StringBuilder sbM = KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(translationY, left, "[getLayoutParams] translationY=", ", translationX=", ", width=");
        RectList$.ExternalSyntheticOutline0.m(i, i2, ", height=", ", isLandscape=", sbM);
        sbM.append(z);
        sbM.append(", parent=");
        sbM.append(viewGroup);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        return new Triple(marginLayoutParams, Float.valueOf(translationY), Float.valueOf(left));
    }

    public final CharSequence getMainViewText() {
        return this.mainLabelView.getText();
    }

    public final int getSeparateExtraSpace() {
        return this.separateExtraSpace;
    }

    public final TemporarilyInfoManager.InfoShowStyle getStyle() {
        return this.style;
    }

    public final CharSequence getSubViewText() {
        return this.subLabelView.getText();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.lastOrientation = getResources().getConfiguration().orientation;
        this.mainLabelView.setAutoSizeTextTypeWithDefaults(0);
        this.mainLabelView.setTypeface(null);
        this.subLabelView.setTypeface(null);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration == null || configuration.orientation == this.lastOrientation) {
            return;
        }
        if (this.style.getAlongWithReplacedView()) {
            ViewExtKt.stopAlongWithPosition(this, this.statusBarContainerView);
            ViewExtKt.stopAlongWithPosition(this, this.qsQuickEntranceContainerView);
            ViewExtKt.startAlongWithPosition(this, getAlongWithView());
        }
        onConfigurationChanged.1 r0 = new onConfigurationChanged.1();
        r0.this$0 = this;
        r0.$newConfig = configuration;
        VarHandle.storeStoreFence();
        post(r0);
        this.lastOrientation = configuration.orientation;
    }

    public final void setAutoSizeMainText(TextView textView, TemporarilyInfoManager.InfoShowStyle infoShowStyle) {
        QsColorUtil qsColorUtil = QsColorUtil.INSTANCE;
        qsColorUtil.getClass();
        if (QsColorUtil.isSuperPowerSaveState) {
            textView.setAutoSizeTextTypeWithDefaults(0);
            textView.setTextSize(infoShowStyle.getSizeUnit(), infoShowStyle.getTextSize());
            return;
        }
        if (qsColorUtil.getEnableSeparateNotificationAndQS() && this.autoMainTextSize != null) {
            textView.setAutoSizeTextTypeWithDefaults(0);
            Float f = this.autoMainTextSize;
            if (f != null) {
                textView.setTextSize(0, f.floatValue());
                return;
            }
            return;
        }
        if (textView.getMaxLines() > 1) {
            textView.setAutoSizeTextTypeWithDefaults(1);
            textView.setAutoSizeTextTypeUniformWithConfiguration(4, 14, 1, 2);
        } else {
            textView.setAutoSizeTextTypeWithDefaults(0);
            textView.setTextSize(infoShowStyle.getSizeUnit(), infoShowStyle.getTextSize());
        }
    }

    public final void setAutoSizeSubText(TextView textView, TemporarilyInfoManager.InfoShowStyle infoShowStyle) {
        if (!QsColorUtil.INSTANCE.getEnableSeparateNotificationAndQS() || this.autoSubTextSize == null) {
            textView.setAutoSizeTextTypeWithDefaults(0);
            textView.setTextSize(infoShowStyle.getSizeUnit(), 12.0f);
            return;
        }
        textView.setAutoSizeTextTypeWithDefaults(0);
        Float f = this.autoSubTextSize;
        if (f != null) {
            textView.setTextSize(0, f.floatValue());
        }
    }

    public final void setLandSplitLayout(boolean z) {
        this.isLandSplitLayout = z;
    }

    public final void setSeparateExtraSpace(int i) {
        this.separateExtraSpace = i;
    }

    public final void setStyle(TemporarilyInfoManager.InfoShowStyle infoShowStyle) {
        this.style = infoShowStyle;
    }

    public final void setTemporarilyInfoViewContainer(ViewGroup viewGroup, ViewGroup viewGroup2, ViewGroup viewGroup3) {
        if (viewGroup != null) {
            this.parent = viewGroup;
        }
        if (viewGroup2 != null) {
            this.statusBarContainerView = viewGroup2;
        }
        if (viewGroup3 != null) {
            this.qsQuickEntranceContainerView = viewGroup3;
        }
    }

    public final void setTemporarilyInfoViewStyle(TemporarilyInfoManager.InfoShowStyle infoShowStyle) {
        this.style = infoShowStyle;
    }

    public final void setText(CharSequence charSequence, CharSequence charSequence2) {
        this.mainLabelView.setText(charSequence);
        this.subLabelView.setText(charSequence2);
        this.subLabelView.setTextColor(this.style.getTextColor());
        this.mainLabelView.setTextColor(this.style.getTextColor());
        updateLayoutForOrientation(this.lastOrientation == 2);
        if (this.style.getAlongWithReplacedView()) {
            ViewExtKt.stopAlongWithPosition(this, this.statusBarContainerView);
            ViewExtKt.stopAlongWithPosition(this, this.qsQuickEntranceContainerView);
            ViewExtKt.startAlongWithPosition(this, getAlongWithView());
        }
    }

    public final void updateLayoutForOrientation(boolean z) {
        Triple layoutParams = getLayoutParams(this, this.parent, this.statusBarContainerView, this.qsQuickEntranceContainerView, (ViewGroup.MarginLayoutParams) getLayoutParams());
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams.component1();
        float fFloatValue = ((Number) layoutParams.component2()).floatValue();
        float fFloatValue2 = ((Number) layoutParams.component3()).floatValue();
        setTranslationY(fFloatValue);
        setTranslationX(fFloatValue2);
        setLayoutParams(marginLayoutParams);
        String string = this.mainLabelView.getText().toString();
        String string2 = this.subLabelView.getText().toString();
        if (string2 == null || string2.length() == 0) {
            this.subLabelView.setVisibility(8);
        } else if (z) {
            boolean zContains = StringsKt__StringsKt.contains(string, string2, false);
            TextView textView = this.mainLabelView;
            if (zContains) {
                textView.setText(String.valueOf(string));
            } else {
                textView.setText(string + " " + string2);
            }
            this.subLabelView.setVisibility(8);
        } else {
            if (StringsKt__StringsKt.contains(string, string2, false)) {
                this.mainLabelView.setText(StringsKt__StringsKt.trim(StringsKt__StringsJVMKt.replace(string, string2.toString(), "", true)).toString());
            }
            this.subLabelView.setVisibility(0);
        }
        updateMaxLines(this.mainLabelView);
        setAutoSizeMainText(this.mainLabelView, this.style);
        setAutoSizeSubText(this.subLabelView, this.style);
    }

    public final void updateTemporarilyViewTextSize(float f, SpecialLanguageTextScaleHelper specialLanguageTextScaleHelper, float f2) {
        int i;
        specialLanguageTextScaleHelper.getClass();
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, SpecialLanguageTextScaleHelper.getMinScale(f2 / 160.0f), 1.15f);
        if (specialLanguageTextScaleHelper.isSpecialHeightLanguage()) {
            i = 2131172159;
        } else {
            i = specialLanguageTextScaleHelper.isSecondSpecialHeightLanguage() ? 2131172162 : 2131172161;
        }
        Context context = getContext();
        this.autoMainTextSize = Float.valueOf(((int) ((context.getResources().getDimensionPixelSize(i) * f2) / context.getResources().getDisplayMetrics().density)) * fCoerceIn);
        Context context2 = getContext();
        this.autoSubTextSize = Float.valueOf(((int) ((context2.getResources().getDimensionPixelSize(2131172160) * f2) / context2.getResources().getDisplayMetrics().density)) * fCoerceIn);
    }
}
