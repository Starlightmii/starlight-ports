package com.oplus.systemui.qs.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BrightnessHighPowerConsumptionTextView extends TextView {
    public static final Companion Companion = new Companion();
    private static final String DRAINING_TEXT_CONTENT = "draining";
    private static final String ENGLISH_TEXT_CONTENT = "Battery\ndraining";
    private static final String TAG = "BrightnessHighPowerConsumptionTextView";
    private String lastLocale;
    public int orientation;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public BrightnessHighPowerConsumptionTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Configuration configuration = ((TextView) this).mContext.getApplicationContext().getResources().getConfiguration();
        configuration.fontScale = 1.0f;
        Context contextCreateConfigurationContext = ((TextView) this).mContext.createConfigurationContext(configuration);
        ((TextView) this).mContext = contextCreateConfigurationContext;
        this.lastLocale = contextCreateConfigurationContext.getApplicationContext().getResources().getConfiguration().getLocales().toLanguageTags();
    }

    public static boolean isEnglish() {
        return Intrinsics.areEqual(Locale.getDefault().getLanguage(), Locale.ENGLISH.getLanguage());
    }

    public final String getLastLocale() {
        return this.lastLocale;
    }

    public final int getOrientation() {
        return this.orientation;
    }

    public final boolean getShouldShow() {
        return isEnglish() || Intrinsics.areEqual(Locale.getDefault().getLanguage(), Locale.CHINESE.getLanguage());
    }

    @Override // android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        boolean z;
        super.onConfigurationChanged(configuration);
        String languageTags = configuration.getLocales().toLanguageTags();
        int i = configuration.orientation;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", NotificationPanelViewController$.ExternalSyntheticOutline0.m(this.orientation, i, ",newOrientation=", "BrightnessHighPowerConsumptionTextView-->", ExifInterface$.ExternalSyntheticOutline1.m("onConfigurationChanged: newLocale=", languageTags, ",lastLocale=", this.lastLocale, ",orientation=")));
        }
        boolean z2 = true;
        if (Intrinsics.areEqual(languageTags, this.lastLocale)) {
            z = false;
        } else {
            this.lastLocale = languageTags;
            configuration.fontScale = 1.0f;
            z = true;
        }
        if (this.orientation != i) {
            this.orientation = i;
            configuration.fontScale = 1.0f;
        } else {
            z2 = z;
        }
        if (z2) {
            ((TextView) this).mContext = ((TextView) this).mContext.createConfigurationContext(configuration);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onMeasure(int i, int i2) {
        if (isEnglish()) {
            i = View.MeasureSpec.makeMeasureSpec(((int) getPaint().measureText(DRAINING_TEXT_CONTENT)) + 1, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    public final void setLastLocale(String str) {
        this.lastLocale = str;
    }

    public final void setOrientation(int i) {
        this.orientation = i;
    }

    public final void updateText(boolean z) {
        float f;
        if (!getShouldShow()) {
            setVisibility(8);
            return;
        }
        if (!z) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        getPaint().setFakeBoldText(true);
        if (Intrinsics.areEqual(Locale.getDefault().getLanguage(), Locale.CHINESE.getLanguage())) {
            if (getMaxEms() != 1) {
                setMaxEms(1);
            }
            if (!TextUtils.equals(getText(), ((TextView) this).mContext.getResources().getString(2131952478))) {
                setText(2131952478);
            }
        } else if (isEnglish()) {
            if (getMaxEms() != Integer.MAX_VALUE) {
                setMaxEms(Integer.MAX_VALUE);
            }
            if (!TextUtils.equals(ENGLISH_TEXT_CONTENT, getText())) {
                setText(ENGLISH_TEXT_CONTENT);
            }
        }
        if (StringsKt__StringsJVMKt.startsWith(((TextView) this).mContext.getResources().getConfiguration().locale.toString(), "zh_CN", false)) {
            f = 14.0f;
        } else {
            String string = ((TextView) this).mContext.getResources().getConfiguration().locale.toString();
            f = (StringsKt__StringsJVMKt.startsWith(string, "zh_TW", false) || StringsKt__StringsJVMKt.startsWith(string, "zh_HK", false)) ? 12.0f : 11.0f;
        }
        setTextSize(f);
        setMaxLines(isEnglish() ? 2 : 4);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x003f  */
    public final void updateTextViewMargin(boolean z) {
        if (getShouldShow()) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
            if (layoutParams2 == null) {
                return;
            }
            int i = layoutParams2.topMargin;
            int dimensionPixelSize = ((TextView) this).mContext.getResources().getDimensionPixelSize(2131172227);
            int dimensionPixelSize2 = ((TextView) this).mContext.getResources().getDimensionPixelSize(2131172226);
            boolean z2 = true;
            if (z) {
                if (i != dimensionPixelSize2) {
                    layoutParams2.topMargin = dimensionPixelSize2;
                } else {
                    z2 = false;
                }
            } else if (i != dimensionPixelSize) {
                layoutParams2.topMargin = dimensionPixelSize;
            } else {
                z2 = false;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("updateTextViewMargin: changed=", "BrightnessHighPowerConsumptionTextView-->", "SystemUi--QuickSettings", z2);
            }
            if (z2) {
                setLayoutParams(layoutParams2);
            }
        }
    }

    public BrightnessHighPowerConsumptionTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ BrightnessHighPowerConsumptionTextView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public BrightnessHighPowerConsumptionTextView(Context context) {
        this(context, null, 0, 6, null);
    }
}
