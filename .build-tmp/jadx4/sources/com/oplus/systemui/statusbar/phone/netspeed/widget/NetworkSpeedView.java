package com.oplus.systemui.statusbar.phone.netspeed.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.LocaleList;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.Dumpable;
import com.android.systemui.FontSizeUtils;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.StatusIconDisplayable;
import com.android.systemui.statusbar.phone.OplusSysIconVisStateApplier;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.phone.VisStateIcon;
import com.oplus.systemui.aod.aodclock.util.CommonUtils$;
import com.oplus.systemui.qs.widget.OplusQSClock$$ExternalSyntheticLambda0;
import com.oplus.systemui.statusbar.phone.netspeed.NetworkSpeedIconState;
import com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon;
import com.oplus.systemui.statusbar.util.StatusBarViewUtil;
import com.oplus.util.OplusUnitConversionUtils;
import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class NetworkSpeedView extends FrameLayout implements StatusIconDisplayable, DarkIconDispatcher.DarkReceiver, Dumpable, CoordAnimIcon, SystemTypefaceHelper.SystemUIFontCallback, VisStateIcon {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean mBlocked;
    public final Context mContext;
    public String mCurrentLanguage;
    public float mDarkIntensity;
    public float mIconAppearAmount;
    public int mIconTint;
    public OplusSysIconVisStateApplier mIconVisApplier;
    public boolean mIsStaticDrawableColor;
    public String mLanguageTag;
    public int mLayoutLeft;
    public String mSlot;
    public long mSpeed;
    public TextView mSpeedNumber;
    public TextView mSpeedUnit;
    public NetworkSpeedIconState mState;
    public final ArrayList mTintAreas;
    public final OplusUnitConversionUtils mUtils;
    public int mVisibleState;
    public float transXForCoord;

    public NetworkSpeedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTintAreas = new ArrayList();
        this.mVisibleState = -1;
        this.mLayoutLeft = -1;
        this.mIconTint = -1;
        this.transXForCoord = 0.0f;
        this.mIconVisApplier = null;
        this.mIconAppearAmount = 1.0f;
        this.mContext = context;
        this.mUtils = new OplusUnitConversionUtils(context);
        LocaleList locales = getResources().getConfiguration().getLocales();
        Locale locale = locales.get(0);
        this.mLanguageTag = locales.toLanguageTags();
        this.mCurrentLanguage = locale != null ? locale.getLanguage() : null;
    }

    private OplusSysIconVisStateApplier getIconVisStateApplier() {
        if (this.mIconVisApplier == null) {
            this.mIconVisApplier = new OplusSysIconVisStateApplier(this);
        }
        return this.mIconVisApplier;
    }

    public final void applyNetworkState(NetworkSpeedIconState networkSpeedIconState) {
        double d;
        String[] strArr = null;
        int i = 8;
        if (networkSpeedIconState == null) {
            setVisibility(8);
            this.mState = null;
            return;
        }
        this.mState = networkSpeedIconState.copy();
        boolean visible = networkSpeedIconState.getVisible();
        if (visible && !this.mBlocked) {
            i = 0;
        }
        if (i != getVisibility()) {
            setVisibility(i);
        }
        if (visible) {
            long speedText = networkSpeedIconState.getSpeedText();
            if (speedText < 0 || speedText > Math.pow(1024.0d, 5.0d) * 1000.0d) {
                LogUtil.d(LogUtil.TAG_STATUSBAR, "NetworkSpeedView", "speedChanged speed=" + speedText);
                return;
            }
            this.mSpeed = speedText;
            String speedValue = this.mUtils.getSpeedValue(speedText);
            String str = "KB/s";
            String[] strArrSplit = speedValue.split(" ");
            if (strArrSplit == null || strArrSplit.length != 2) {
                LogUtil.d(LogUtil.TAG_STATUSBAR, "NetworkSpeedView", "updateNetworkSpeed error");
            } else {
                String str2 = strArrSplit[1];
                try {
                    if (str2.equals("B/s")) {
                        d = Double.parseDouble(strArrSplit[0]) / 1024.0d;
                    } else {
                        d = Double.parseDouble(strArrSplit[0]);
                        str = str2;
                    }
                    DecimalFormat decimalFormat = new DecimalFormat("##0.00", new DecimalFormatSymbols(Locale.CHINA));
                    decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                    String str3 = decimalFormat.format(Double.valueOf(d));
                    if (d >= 100.0d) {
                        strArrSplit[0] = str3.substring(0, 3);
                    } else {
                        strArrSplit[0] = str3.substring(0, 4);
                    }
                    strArrSplit[1] = str;
                } catch (Exception e) {
                    CommonUtils$.ExternalSyntheticOutline0.m(e, new StringBuilder("NetworkSpeed display exception "), LogUtil.TAG_STATUSBAR, "NetworkSpeedView");
                    if (this.mSpeed < 1024) {
                        strArrSplit[0] = new DecimalFormat("0", new DecimalFormatSymbols(this.mContext.getResources().getConfiguration().locale)).format(this.mSpeed / 1024);
                        strArrSplit[1] = "KB/s";
                    }
                }
                strArr = strArrSplit;
            }
            if (strArr == null || strArr.length < 2) {
                return;
            }
            TextView textView = this.mSpeedNumber;
            if (textView != null) {
                textView.setText(strArr[0]);
            }
            TextView textView2 = this.mSpeedUnit;
            if (textView2 != null) {
                textView2.setText(strArr[1].toUpperCase());
            }
            StatusBarViewUtil.setContentDescription(this, this.mContext.getString(2131951883), this.mContext.getString(2131951990), speedValue);
            invalidate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        if (this.mIconAppearAmount <= 0.0f) {
            return;
        }
        canvas.save();
        float width = getWidth() / 2.0f;
        float height = getHeight() / 2.0f;
        float f = this.mIconAppearAmount;
        canvas.scale(f, f, width, height);
        canvas.translate(this.transXForCoord, 0.0f);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("NetworkSpeedViewstate:");
        printWriter.println(" view: " + this);
        printWriter.println(" transXForCoord: " + this.transXForCoord);
        Object tag = getTag(2131365374);
        if (tag instanceof StatusIconContainer.StatusIconState) {
            ((StatusIconContainer.StatusIconState) tag).dump(printWriter, strArr);
        }
    }

    public float getAppearAmount() {
        return this.mIconAppearAmount;
    }

    public String getSlot() {
        return this.mSlot;
    }

    public /* bridge */ /* synthetic */ int getSlotTag() {
        return super.getSlotTag();
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public float getTransXForCoord() {
        return this.transXForCoord;
    }

    public int getVisibleState() {
        return this.mVisibleState;
    }

    public final boolean isIconBlocked() {
        return this.mBlocked;
    }

    public final boolean isIconVisible() {
        NetworkSpeedIconState networkSpeedIconState = this.mState;
        if (networkSpeedIconState != null) {
            return networkSpeedIconState.getVisible();
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        SystemTypefaceHelper.Companion companion = SystemTypefaceHelper.Companion;
        SystemTypefaceHelper companion2 = companion.getInstance(this.mContext);
        SystemTypefaceHelper companion3 = companion.getInstance(this.mContext);
        Objects.requireNonNull(companion3);
        OplusQSClock$$ExternalSyntheticLambda0 oplusQSClock$$ExternalSyntheticLambda0 = new OplusQSClock$$ExternalSyntheticLambda0();
        oplusQSClock$$ExternalSyntheticLambda0.f$0 = companion3;
        VarHandle.storeStoreFence();
        companion2.addCallback(this, oplusQSClock$$ExternalSyntheticLambda0);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LocaleList locales = configuration.getLocales();
        Locale locale = locales.get(0);
        String languageTags = locales.toLanguageTags();
        String language = locale != null ? locale.getLanguage() : null;
        if (this.mLanguageTag == languageTags && this.mCurrentLanguage == language) {
            return;
        }
        this.mLanguageTag = languageTags;
        this.mCurrentLanguage = language;
        Resources resources = getContext().getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(2131173072);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        layoutParams.width = dimensionPixelSize;
        setLayoutParams(layoutParams);
        if (this.mSpeedNumber == null || this.mSpeedUnit == null) {
            return;
        }
        int dimensionPixelSize2 = resources.getDimensionPixelSize(2131170548);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.mSpeedNumber.getLayoutParams();
        layoutParams2.bottomMargin = dimensionPixelSize2;
        this.mSpeedNumber.setLayoutParams(layoutParams2);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(2131170550);
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) this.mSpeedUnit.getLayoutParams();
        layoutParams3.topMargin = dimensionPixelSize3;
        this.mSpeedUnit.setLayoutParams(layoutParams3);
        FontSizeUtils.updateFontSize(this.mSpeedNumber, 2131170549);
        FontSizeUtils.updateFontSize(this.mSpeedUnit, 2131170551);
    }

    public final void onDarkChanged(ArrayList arrayList, float f, int i) {
        if (this.mIsStaticDrawableColor) {
            return;
        }
        this.mTintAreas.clear();
        this.mTintAreas.addAll(arrayList);
        this.mIconTint = i;
        this.mDarkIntensity = f;
        setIconTint(DarkIconDispatcher.getTint(arrayList, this, i));
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SystemTypefaceHelper.Companion.getInstance(this.mContext).removeCallback(this);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mSpeedNumber = (TextView) findViewById(2131364216);
        this.mSpeedUnit = (TextView) findViewById(2131365899);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int[] locationOnScreen = getLocationOnScreen();
        int i5 = this.mLayoutLeft;
        int i6 = locationOnScreen[0];
        if (i5 != i6) {
            this.mLayoutLeft = i6;
            onDarkChanged(new ArrayList(this.mTintAreas), this.mDarkIntensity, this.mIconTint);
        }
    }

    public void setAppearAmount(float f) {
        if (this.mIconAppearAmount != f) {
            this.mIconAppearAmount = f;
            invalidate();
        }
    }

    public void setBlocked(boolean z) {
        this.mBlocked = z;
    }

    public void setDecorColor(int i) {
    }

    @Override // com.oplusos.systemui.common.typeface.SystemTypefaceHelper.SystemUIFontCallback
    public final void setFontTypeface(Typeface typeface, int i) {
        this.mSpeedNumber.setTypeface(typeface);
        this.mSpeedUnit.setTypeface(typeface);
    }

    public void setIconTint(int i) {
        this.mSpeedNumber.setTextColor(i);
        this.mSpeedUnit.setTextColor(i);
    }

    public void setSlot(String str) {
        this.mSlot = str;
    }

    public void setStaticDrawableColor(int i) {
        this.mIsStaticDrawableColor = true;
        setIconTint(i);
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public void setTransXForCoord(float f) {
        if (this.transXForCoord != f) {
            this.transXForCoord = f;
            invalidate();
        }
    }

    public final void setVisibleState(int i, boolean z) {
        if (i == this.mVisibleState) {
            return;
        }
        this.mVisibleState = i;
        OplusSysIconVisStateApplier iconVisStateApplier = getIconVisStateApplier();
        boolean z2 = false;
        boolean z3 = (getParent() instanceof View) && ((View) getParent()).isShown();
        NetworkSpeedView$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new NetworkSpeedView$.ExternalSyntheticLambda0(0);
        externalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        NetworkSpeedView$.ExternalSyntheticLambda0 externalSyntheticLambda1 = new NetworkSpeedView$.ExternalSyntheticLambda0(1);
        externalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        boolean z4 = i == 0;
        if (z && z3) {
            z2 = true;
        }
        iconVisStateApplier.setVisibleState(z4, z2, externalSyntheticLambda0, externalSyntheticLambda1);
    }

    @Override // android.view.View
    public final String toString() {
        return "NetworkSpeedView(slot=" + this.mSlot + " mIcon=" + this.mState + " visibility: this=" + getVisibility() + " speedNumber=" + this.mSpeedNumber.getVisibility() + " speedUnit=" + this.mSpeedUnit.getVisibility() + ")";
    }

    public /* bridge */ /* synthetic */ void setVisibleState(int i) {
        super.setVisibleState(i);
    }
}
