package com.oplus.systemui.statusbar.pipeline.battery.ui.view;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.car.app.serialization.Bundler$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.DualToneHandler;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder;
import com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconColor;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconStyle;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.ChargeIcon;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.OldHorizontal;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.PercentOutIcon;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon;
import com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel;
import com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher;
import com.oplus.systemui.statusbar.seeding.ILocationCalculatorKt;
import com.oplus.systemui.statusbar.seeding.IScaledAncestorView;
import com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon;
import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Deprecated;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlinx.coroutines.flow.MutableStateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StatBatteryMeterView extends BaseBatteryMeterView implements DarkIconDispatcher.DarkReceiver, HiddenIconDispatcher.HiddenReceiver, CoordAnimIcon, SystemTypefaceHelper.SystemUIFontCallback {
    public boolean allowLayoutTransition;
    private ChargeIcon batteryCharge;
    private StatBatteryIcon batteryIcon;
    private BatteryIconStyle batteryIconStyle;
    private PercentOutIcon batteryPercentOut;
    private ChargeIcon batteryReverse;
    private BatteryContentDrawableBase batteryStyleDrawable;
    private BatteryViewBinder.Binding binding;
    public float darkIntensity;
    public boolean disable;
    public boolean hidden;
    private final ArrayList<Rect> hiddenAreas;
    private AtomicInteger lastShowStyle;
    public int layoutLeft;
    private String moduleTAG;
    private OldHorizontal oldHorizontal;
    public boolean shouldScale;
    private final ArrayList<Rect> tmpRectList;
    public float transXForCoord;

    public StatBatteryMeterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.layoutLeft = -1;
        this.tmpRectList = new ArrayList<>();
        this.hiddenAreas = new ArrayList<>();
        this.lastShowStyle = new AtomicInteger(0);
        this.moduleTAG = "StatBatteryMeterView";
    }

    @Deprecated
    public static /* synthetic */ void getOldHorizontal$annotations() {
    }

    public final void bindViewModel(LocationBasedBatteryViewModel locationBasedBatteryViewModel) {
        this.binding = BatteryViewBinder.bind(this, locationBasedBatteryViewModel);
        this.moduleTAG = Bundler$.ExternalSyntheticOutline0.m(locationBasedBatteryViewModel.getModuleTag(), "_StatBatteryMeterView");
    }

    public final void disable(boolean z) {
        int i;
        String str = this.moduleTAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append("disable: " + this.disable + " " + this.hidden);
            Log.d("SystemUi--Statusbar", sbM.toString());
        }
        this.disable = z;
        if (z) {
            i = 8;
        } else {
            i = this.hidden ? 4 : 0;
        }
        setVisibility(i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        if (getTransXForCoord() == 0.0f) {
            super.dispatchDraw(canvas);
            return;
        }
        canvas.save();
        canvas.translate(getTransXForCoord(), 0.0f);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    public final void dump(PrintWriter printWriter) {
        if (printWriter != null) {
            printWriter.println(this.moduleTAG);
        }
        if (printWriter != null) {
            printWriter.println("darkIntensity: " + this.darkIntensity + " tmpRectList:" + this.tmpRectList);
        }
        if (printWriter != null) {
            printWriter.println("batteryIconStyle: " + this.batteryIconStyle);
        }
        if (printWriter != null) {
            printWriter.println("batteryIcon: " + this.batteryIcon);
        }
        if (printWriter != null) {
            printWriter.println("batteryReverse: " + this.batteryReverse);
        }
        if (printWriter != null) {
            printWriter.println("batteryCharge: " + this.batteryCharge);
        }
        if (printWriter != null) {
            printWriter.println("batteryPercentOut: " + this.batteryPercentOut);
        }
        if (printWriter != null) {
            OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "disable: ", this.disable);
        }
        if (printWriter != null) {
            OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "hidden: ", this.hidden);
        }
        BatteryContentDrawableBase batteryContentDrawableBase = this.batteryStyleDrawable;
        if (batteryContentDrawableBase != null) {
            batteryContentDrawableBase.dump(printWriter);
        }
    }

    public final boolean getAllowLayoutTransition() {
        return this.allowLayoutTransition;
    }

    public final ChargeIcon getBatteryCharge() {
        return this.batteryCharge;
    }

    public final StatBatteryIcon getBatteryIcon() {
        return this.batteryIcon;
    }

    public final BatteryIconStyle getBatteryIconStyle() {
        return this.batteryIconStyle;
    }

    public final PercentOutIcon getBatteryPercentOut() {
        return this.batteryPercentOut;
    }

    public final ChargeIcon getBatteryReverse() {
        return this.batteryReverse;
    }

    public final BatteryContentDrawableBase getBatteryStyleDrawable() {
        return this.batteryStyleDrawable;
    }

    public final OldHorizontal getOldHorizontal() {
        return this.oldHorizontal;
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public float getTransXForCoord() {
        return this.transXForCoord;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onDarkChanged(ArrayList<Rect> arrayList, float f, int i) {
        this.darkIntensity = f;
        this.tmpRectList.clear();
        this.tmpRectList.addAll(arrayList);
        if (!DarkIconDispatcher.isInAreas(arrayList, this)) {
            f = 0.0f;
        }
        BatteryViewBinder.Binding binding = this.binding;
        if (binding != null) {
            BatteryViewBinder.AnonymousClass2 anonymousClass2 = (BatteryViewBinder.AnonymousClass2) binding;
            MutableStateFlow batteryColor = anonymousClass2.$viewModel.getBatteryColor();
            DualToneHandler dualToneHandler = anonymousClass2.$dualToneHandler;
            batteryColor.setValue(new BatteryIconColor(dualToneHandler.getSingleColor(f), dualToneHandler.getFillColor(f), dualToneHandler.getBackgroundColor(f)));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BatteryContentDrawableBase batteryContentDrawableBase = this.batteryStyleDrawable;
        if (batteryContentDrawableBase != null) {
            batteryContentDrawableBase.onDestroy();
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        ImageView imageView = (ImageView) findViewById(2131362241);
        if (imageView != null) {
            imageView.setImageDrawable(this.batteryStyleDrawable);
        }
        if (imageView != null) {
            imageView.setVisibility(0);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public void onHiddenChanged(ArrayList<Rect> arrayList, boolean z) {
        int i;
        IScaledAncestorView scaledAncestorViewOrNull = ILocationCalculatorKt.getScaledAncestorViewOrNull(this);
        HiddenIconDispatcher.Companion companion = HiddenIconDispatcher.Companion;
        boolean zIsValid = companion.isValid(arrayList);
        this.shouldScale = z;
        this.hidden = companion.isInAreas(arrayList, scaledAncestorViewOrNull != null ? ILocationCalculatorKt.getLocationByScaledAncestorView(this, scaledAncestorViewOrNull.getScaledAncestorView(), scaledAncestorViewOrNull.getScaleFraction(z), scaledAncestorViewOrNull.getPivotXDirection()) : ILocationCalculatorKt.getLocationByDefault(this));
        String str = this.moduleTAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            boolean z2 = this.hidden;
            boolean z3 = this.disable;
            AtomicInteger atomicInteger = this.lastShowStyle;
            StringBuilder sbM2 = TextFieldImplKt$.ExternalSyntheticOutline0.m("onHiddenChanged ", ", ", " disable=", z, z2);
            sbM2.append(z3);
            sbM2.append(", lastShowStyle=");
            sbM2.append(atomicInteger);
            sbM.append(sbM2.toString());
            Log.d("SystemUi--Statusbar", sbM.toString());
        }
        this.hiddenAreas.clear();
        this.hiddenAreas.addAll(arrayList);
        if (this.lastShowStyle.get() == 2) {
            BatteryViewBinder.Binding binding = this.binding;
            if (binding != null) {
                ((BatteryViewBinder.AnonymousClass2) binding).$viewModel.setForceShowStyle(2);
                return;
            }
            return;
        }
        this.lastShowStyle.set(zIsValid ? 1 : 0);
        String str2 = this.moduleTAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            StringBuilder sbM3 = Bundler$.ExternalSyntheticOutline0.m(str2, LogUtil.SEPARATOR);
            sbM3.append("onHiddenChanged showStyle:" + (zIsValid ? 1 : 0) + ".");
            Log.d("SystemUi--Statusbar", sbM3.toString());
        }
        BatteryViewBinder.Binding binding2 = this.binding;
        if (binding2 != null) {
            ((BatteryViewBinder.AnonymousClass2) binding2).$viewModel.setForceShowStyle(zIsValid ? 1 : 0);
        }
        if (this.disable) {
            i = 8;
        } else {
            i = this.hidden ? 4 : 0;
        }
        setVisibility(i);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int[] locationOnScreen = getLocationOnScreen();
        if (locationOnScreen != null) {
            if (!(locationOnScreen.length == 0)) {
                int i5 = this.layoutLeft;
                int i6 = locationOnScreen[0];
                if (i5 != i6) {
                    this.layoutLeft = i6;
                    onDarkChanged((ArrayList) CollectionsKt___CollectionsKt.toMutableList(this.tmpRectList), this.darkIntensity, 0);
                    onHiddenChanged(new ArrayList<>(this.hiddenAreas), this.shouldScale);
                }
            }
        }
    }

    public final void setAllowLayoutTransition(boolean z) {
        this.allowLayoutTransition = z;
    }

    public final void setBatteryCharge(ChargeIcon chargeIcon) {
        this.batteryCharge = chargeIcon;
    }

    public final void setBatteryIcon(StatBatteryIcon statBatteryIcon) {
        this.batteryIcon = statBatteryIcon;
    }

    public final void setBatteryIconStyle(BatteryIconStyle batteryIconStyle) {
        this.batteryIconStyle = batteryIconStyle;
    }

    public final void setBatteryPercentOut(PercentOutIcon percentOutIcon) {
        this.batteryPercentOut = percentOutIcon;
    }

    public final void setBatteryReverse(ChargeIcon chargeIcon) {
        this.batteryReverse = chargeIcon;
    }

    public final void setBatteryStyleDrawable(BatteryContentDrawableBase batteryContentDrawableBase) {
        this.batteryStyleDrawable = batteryContentDrawableBase;
    }

    @Override // com.oplusos.systemui.common.typeface.SystemTypefaceHelper.SystemUIFontCallback
    public void setFontTypeface(Typeface typeface, int i) {
        TextView textView = (TextView) findViewById(2131362245);
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.oplus.systemui.statusbar.seeding.HiddenIconDispatcher.HiddenReceiver
    public void setHiddenIconListChanged(boolean z, ArrayList<String> arrayList) {
        String string = getResources().getString(R.string.wifi_available_sign_in);
        String str = this.moduleTAG;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
            sbM.append(string + ", " + arrayList + ", " + this.hidden + ", " + this.lastShowStyle.get());
            Log.d("SystemUi--Statusbar", sbM.toString());
        }
        if (z && arrayList != null && arrayList.contains(string)) {
            this.lastShowStyle.set(2);
            BatteryViewBinder.Binding binding = this.binding;
            if (binding != null) {
                ((BatteryViewBinder.AnonymousClass2) binding).$viewModel.setForceShowStyle(2);
                return;
            }
            return;
        }
        boolean zIsValid = HiddenIconDispatcher.Companion.isValid(this.hiddenAreas);
        this.lastShowStyle.set(zIsValid ? 1 : 0);
        BatteryViewBinder.Binding binding2 = this.binding;
        if (binding2 != null) {
            ((BatteryViewBinder.AnonymousClass2) binding2).$viewModel.setForceShowStyle(zIsValid ? 1 : 0);
        }
    }

    public final void setOldHorizontal(OldHorizontal oldHorizontal) {
        this.oldHorizontal = oldHorizontal;
    }

    @Override // com.oplus.systemui.statusbar.seeding.anim.CoordAnimIcon
    public void setTransXForCoord(float f) {
        if (this.transXForCoord == f) {
            return;
        }
        this.transXForCoord = f;
        invalidate();
    }

    public StatBatteryMeterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StatBatteryMeterView(Context context) {
        this(context, null);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println(this.moduleTAG + " state: ");
        StringBuilder sb = new StringBuilder("  view: ");
        sb.append(this);
        printWriter.println(sb.toString());
        printWriter.println("  location: " + getLocationOnScreen()[0]);
        printWriter.println("  translation: " + getTranslationX() + ", " + getTranslationY());
        boolean z = this.hidden;
        StringBuilder sb2 = new StringBuilder("  hidden: ");
        sb2.append(z);
        printWriter.println(sb2.toString());
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("  transXForCoord: ", getTransXForCoord(), printWriter);
        printWriter.println("  hiddenAreas: " + this.hiddenAreas);
    }
}
