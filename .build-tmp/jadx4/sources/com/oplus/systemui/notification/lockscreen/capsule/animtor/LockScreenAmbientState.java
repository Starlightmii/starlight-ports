package com.oplus.systemui.notification.lockscreen.capsule.animtor;

import android.util.Log;
import androidx.compose.ui.spatial.RectList$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.media.DynamicRouteAttributes$;
import com.android.systemui.Dumpable;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleEarController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataPinHandler;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenAmbientState implements Dumpable {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public static final Companion Companion;
    private static final String TAG = "LockScreenAmbientState";
    public boolean aodState;
    private CapsuleItemView baseCard;
    private final CapsuleEarController earController;
    private CapsuleItemView immersiveCard;
    public boolean isHorizontalBackFling;
    public boolean isHorizontalFling;
    public boolean isLightWallpaper;
    public boolean isStackState;
    private CapsuleItemView lastBaseCard;
    public int marginBottom;
    private CapsuleItemView nextCard;
    public float paddingBetweenElements;
    private ExpandableView referStackView;
    private ExpandableView referStackViewForImmersiveShowItem;
    public int scrollX;
    public float stackY;
    public float touchProgress;
    public boolean isShowingCapsuleOnlyOne = true;
    private WeakReference<CapsuleItemView> touchView = new WeakReference<>(null);
    private final CapsuleScrollCalculator.ScrollCalculatorDelegate scrollCalculator$delegate = new CapsuleScrollCalculator.ScrollCalculatorDelegate();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(LockScreenAmbientState.class, "scrollCalculator", "getScrollCalculator()Lcom/oplus/systemui/notification/lockscreen/capsule/animtor/CapsuleScrollCalculator;", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{mutablePropertyReference1Impl};
        Companion = new Companion();
    }

    public LockScreenAmbientState(CapsuleEarController capsuleEarController) {
        this.earController = capsuleEarController;
    }

    public final CapsuleEarController component1() {
        return this.earController;
    }

    public final LockScreenAmbientState copy(CapsuleEarController capsuleEarController) {
        return new LockScreenAmbientState(capsuleEarController);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println(toString());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LockScreenAmbientState) && Intrinsics.areEqual(this.earController, ((LockScreenAmbientState) obj).earController);
    }

    public final boolean getAodState() {
        return this.aodState;
    }

    public final CapsuleItemView getBaseCard() {
        return this.baseCard;
    }

    public final CapsuleEarController getEarController() {
        return this.earController;
    }

    public final CapsuleItemView getImmersiveCard() {
        return this.immersiveCard;
    }

    public final int getItemHeight() {
        return CapsuleConfig.INSTANCE.getItemHeight();
    }

    public final int getItemWidth() {
        return CapsuleConfig.INSTANCE.getItemWidth();
    }

    public final CapsuleItemView getLastBaseCard() {
        return this.lastBaseCard;
    }

    public final int getMarginBottom() {
        return this.marginBottom;
    }

    public final CapsuleItemView getNextCard() {
        return this.nextCard;
    }

    public final float getPaddingBetweenElements() {
        return this.paddingBetweenElements;
    }

    public final ExpandableView getReferStackView() {
        return this.referStackView;
    }

    public final ExpandableView getReferStackViewForImmersiveShowItem() {
        return this.referStackViewForImmersiveShowItem;
    }

    public final CapsuleScrollCalculator getScrollCalculator() {
        return this.scrollCalculator$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public final int getScrollX() {
        return this.scrollX;
    }

    public final float getStackY() {
        return this.stackY;
    }

    public final float getTouchProgress() {
        return this.touchProgress;
    }

    public final WeakReference<CapsuleItemView> getTouchView() {
        return this.touchView;
    }

    public final int hashCode() {
        return this.earController.hashCode();
    }

    public final void reset() {
        setBaseCard(null);
        setLastBaseCard(null);
        this.nextCard = null;
        this.referStackViewForImmersiveShowItem = null;
        this.stackY = 0.0f;
        this.scrollX = 0;
    }

    public final void setAodState(boolean z) {
        this.aodState = z;
    }

    public final void setBaseCard(CapsuleItemView capsuleItemView) {
        if (Intrinsics.areEqual(this.baseCard, capsuleItemView)) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "LockScreenAmbientState-->".concat("onBaseCardChange. " + this.baseCard + LogUtil.SEPARATOR + capsuleItemView));
        }
        this.baseCard = capsuleItemView;
        CapsuleDataPinHandler.INSTANCE.onBaseCardChange();
    }

    public final void setHorizontalBackFling(boolean z) {
        this.isHorizontalBackFling = z;
    }

    public final void setHorizontalFling(boolean z) {
        this.isHorizontalFling = z;
    }

    public final void setImmersiveCard(CapsuleItemView capsuleItemView) {
        this.immersiveCard = capsuleItemView;
    }

    public final void setLastBaseCard(CapsuleItemView capsuleItemView) {
        if (Intrinsics.areEqual(this.lastBaseCard, capsuleItemView)) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "LockScreenAmbientState-->".concat("onLastBaseCardChange. " + this.lastBaseCard + LogUtil.SEPARATOR + capsuleItemView));
        }
        this.lastBaseCard = capsuleItemView;
    }

    public final void setLightWallpaper(boolean z) {
        this.isLightWallpaper = z;
    }

    public final void setMarginBottom(int i) {
        this.marginBottom = i;
    }

    public final void setNextCard(CapsuleItemView capsuleItemView) {
        this.nextCard = capsuleItemView;
    }

    public final void setPaddingBetweenElements(float f) {
        this.paddingBetweenElements = f;
    }

    public final void setReferStackView(ExpandableView expandableView) {
        this.referStackView = expandableView;
    }

    public final void setReferStackViewForImmersiveShowItem(ExpandableView expandableView) {
        this.referStackViewForImmersiveShowItem = expandableView;
    }

    public final void setScrollCalculator(CapsuleScrollCalculator capsuleScrollCalculator) {
        this.scrollCalculator$delegate.setValue(this, $$delegatedProperties[0], capsuleScrollCalculator);
    }

    public final void setScrollX(int i) {
        this.scrollX = i;
    }

    public final void setShowingCapsuleOnlyOne(boolean z) {
        if (z != this.isShowingCapsuleOnlyOne) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("isShowingCapsuleOnlyOne. ", LogUtil.SEPARATOR, "LockScreenAmbientState-->", this.isShowingCapsuleOnlyOne, z));
            }
            this.isShowingCapsuleOnlyOne = z;
        }
    }

    public final void setStackState(boolean z) {
        this.isStackState = z;
    }

    public final void setStackY(float f) {
        this.stackY = f;
    }

    public final void setTouchProgress(float f) {
        if (f == this.touchProgress) {
            return;
        }
        if ((f == 0.0f || f == 1.0f) && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("setTouchProgress. ", this.touchProgress, LogUtil.SEPARATOR, f, "LockScreenAmbientState-->"));
        }
        this.touchProgress = f;
    }

    public final void setTouchView(WeakReference<CapsuleItemView> weakReference) {
        this.touchView = weakReference;
    }

    public String toString() {
        boolean z = this.isStackState;
        float f = this.stackY;
        int i = this.marginBottom;
        int i2 = this.scrollX;
        boolean z2 = this.isHorizontalFling;
        boolean z3 = this.isHorizontalBackFling;
        boolean z4 = this.isShowingCapsuleOnlyOne;
        CapsuleItemView capsuleItemView = this.baseCard;
        CapsuleItemView capsuleItemView2 = this.lastBaseCard;
        CapsuleItemView capsuleItemView3 = this.nextCard;
        CapsuleItemView capsuleItemView4 = this.immersiveCard;
        ExpandableView expandableView = this.referStackView;
        ExpandableView expandableView2 = this.referStackViewForImmersiveShowItem;
        int itemHeight = getItemHeight();
        boolean z5 = this.aodState;
        boolean z6 = this.isLightWallpaper;
        StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("LockScreenAmbientState {isStackState:", z, ", stackY:", f, ", marginBottom:");
        RectList$.ExternalSyntheticOutline0.m(i, i2, ", scrollX:", ", isHorizontalFling:", sbM);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", isHorizontalBackFling:", ", isShowingCapsuleOnlyOne:", sbM, z2, z3);
        sbM.append(z4);
        sbM.append(", baseCard:");
        sbM.append(capsuleItemView);
        sbM.append(", lastBaseCard:");
        sbM.append(capsuleItemView2);
        sbM.append(", nextCard:");
        sbM.append(capsuleItemView3);
        sbM.append(", immersiveCard:");
        sbM.append(capsuleItemView4);
        sbM.append(", referStackView:");
        sbM.append(expandableView);
        sbM.append(", referStackViewForImmersiveShowItem:");
        sbM.append(expandableView2);
        sbM.append(", itemHeight: ");
        sbM.append(itemHeight);
        sbM.append(", aodState:");
        return DynamicRouteAttributes$.ExternalSyntheticOutline0.m(", isLightWallpaper:", " }", sbM, z5, z6);
    }
}
