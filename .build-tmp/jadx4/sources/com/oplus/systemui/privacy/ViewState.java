package com.oplus.systemui.privacy;

import android.view.View;
import androidx.appcompat.app.AppCompatDelegateImpl$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.ui.spatial.RectList$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.bluetooth.devicesettings.DeviceSettingItem$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ViewState {
    public final boolean chipAnimationStart;
    private final String contentDescription;
    public final int cornerIndex;
    private final View designatedCorner;
    public final int height;
    public final boolean needShow;
    public final int persistentDotFillColorRes;
    public final boolean preDraw;
    public final int rotation;
    public final boolean rtl;
    public final boolean shadeExpanded;
    public final boolean systemPrivacyEventIsActive;
    public final boolean wakeAllowsPrivacyDot;
    public final int x;
    public final int y;

    public ViewState(boolean z, int i, boolean z2, boolean z3, boolean z4, boolean z5, int i2, int i3, int i4, int i5, int i6, View view, String str, boolean z6, boolean z7) {
        this.systemPrivacyEventIsActive = z;
        this.persistentDotFillColorRes = i;
        this.shadeExpanded = z2;
        this.needShow = z3;
        this.wakeAllowsPrivacyDot = z4;
        this.rtl = z5;
        this.rotation = i2;
        this.height = i3;
        this.x = i4;
        this.y = i5;
        this.cornerIndex = i6;
        this.designatedCorner = view;
        this.contentDescription = str;
        this.chipAnimationStart = z6;
        this.preDraw = z7;
    }

    public final View component12() {
        return this.designatedCorner;
    }

    public final String component13() {
        return this.contentDescription;
    }

    public final ViewState copy(boolean z, int i, boolean z2, boolean z3, boolean z4, boolean z5, int i2, int i3, int i4, int i5, int i6, View view, String str, boolean z6, boolean z7) {
        return new ViewState(z, i, z2, z3, z4, z5, i2, i3, i4, i5, i6, view, str, z6, z7);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ViewState)) {
            return false;
        }
        ViewState viewState = (ViewState) obj;
        return this.systemPrivacyEventIsActive == viewState.systemPrivacyEventIsActive && this.persistentDotFillColorRes == viewState.persistentDotFillColorRes && this.shadeExpanded == viewState.shadeExpanded && this.needShow == viewState.needShow && this.wakeAllowsPrivacyDot == viewState.wakeAllowsPrivacyDot && this.rtl == viewState.rtl && this.rotation == viewState.rotation && this.height == viewState.height && this.x == viewState.x && this.y == viewState.y && this.cornerIndex == viewState.cornerIndex && Intrinsics.areEqual(this.designatedCorner, viewState.designatedCorner) && Intrinsics.areEqual(this.contentDescription, viewState.contentDescription) && this.chipAnimationStart == viewState.chipAnimationStart && this.preDraw == viewState.preDraw;
    }

    public final boolean getChipAnimationStart() {
        return this.chipAnimationStart;
    }

    public final String getContentDescription() {
        return this.contentDescription;
    }

    public final int getCornerIndex() {
        return this.cornerIndex;
    }

    public final View getDesignatedCorner() {
        return this.designatedCorner;
    }

    public final int getHeight() {
        return this.height;
    }

    public final boolean getNeedShow() {
        return this.needShow;
    }

    public final int getPersistentDotFillColorRes() {
        return this.persistentDotFillColorRes;
    }

    public final boolean getPreDraw() {
        return this.preDraw;
    }

    public final int getRotation() {
        return this.rotation;
    }

    public final boolean getRtl() {
        return this.rtl;
    }

    public final boolean getShadeExpanded() {
        return this.shadeExpanded;
    }

    public final boolean getSystemPrivacyEventIsActive() {
        return this.systemPrivacyEventIsActive;
    }

    public final boolean getWakeAllowsPrivacyDot() {
        return this.wakeAllowsPrivacyDot;
    }

    public final int getX() {
        return this.x;
    }

    public final int getY() {
        return this.y;
    }

    public final int hashCode() {
        int iM = MonoSpline$.ExternalSyntheticOutline0.m(this.cornerIndex, MonoSpline$.ExternalSyntheticOutline0.m(this.y, MonoSpline$.ExternalSyntheticOutline0.m(this.x, MonoSpline$.ExternalSyntheticOutline0.m(this.height, MonoSpline$.ExternalSyntheticOutline0.m(this.rotation, ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.persistentDotFillColorRes, Boolean.hashCode(this.systemPrivacyEventIsActive) * 31, 31), 31, this.shadeExpanded), 31, this.needShow), 31, this.wakeAllowsPrivacyDot), 31, this.rtl), 31), 31), 31), 31), 31);
        View view = this.designatedCorner;
        int iHashCode = (iM + (view == null ? 0 : view.hashCode())) * 31;
        String str = this.contentDescription;
        return Boolean.hashCode(this.preDraw) + ChangeSize$.ExternalSyntheticOutline0.m((iHashCode + (str != null ? str.hashCode() : 0)) * 31, 31, this.chipAnimationStart);
    }

    public final boolean needsLayout(ViewState viewState) {
        return (this.rotation == viewState.rotation && this.x == viewState.x && this.y == viewState.y && this.height == viewState.height && this.preDraw == viewState.preDraw && this.chipAnimationStart == viewState.chipAnimationStart) ? false : true;
    }

    public final boolean shouldShowDot() {
        return this.systemPrivacyEventIsActive && !this.shadeExpanded && this.needShow && !this.chipAnimationStart && !this.preDraw && this.wakeAllowsPrivacyDot;
    }

    public String toString() {
        View view = this.designatedCorner;
        String str = this.contentDescription;
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("ViewState(systemPrivacyEventIsActive=", this.persistentDotFillColorRes, ", persistentDotFillColorRes=", ", shadeExpanded=", this.systemPrivacyEventIsActive);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", needShow=", ", wakeAllowsPrivacyDot=", sbM, this.shadeExpanded, this.needShow);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", rtl=", ", rotation=", sbM, this.wakeAllowsPrivacyDot, this.rtl);
        RectList$.ExternalSyntheticOutline0.m(this.rotation, this.height, ", height=", ", x=", sbM);
        RectList$.ExternalSyntheticOutline0.m(this.x, this.y, ", y=", ", cornerIndex=", sbM);
        sbM.append(this.cornerIndex);
        sbM.append(", designatedCorner=");
        sbM.append(view);
        sbM.append(", contentDescription=");
        DeviceSettingItem$.ExternalSyntheticOutline0.m(str, ", chipAnimationStart=", ", preDraw=", sbM, this.chipAnimationStart);
        return AppCompatDelegateImpl$.ExternalSyntheticOutline0.m(")", sbM, this.preDraw);
    }
}
