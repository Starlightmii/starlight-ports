package com.oplus.systemui.qs.base.res.model;

import android.content.res.ColorStateList;
import android.util.StateSet;
import androidx.compose.animation.core.MonoSpline$;
import androidx.exifinterface.media.ExifInterface$;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileIconColorState {
    public final int activeColor;
    private final Lazy colorStateList$delegate;
    public final int inactiveColor;
    private final int[][] states = {TileViewFlag.Active.getFlag(), TileViewFlag.Inoperable.getFlag(), TileViewFlag.Inactive.getFlag(), TileViewFlag.Unavailable.getFlag(), StateSet.WILD_CARD};
    public final boolean supportMask;
    public final int unavailableColor;

    public static ColorStateList $r8$lambda$imrZAMdW_vht5ciAFYP_KYScloI(TileIconColorState tileIconColorState) {
        int i = tileIconColorState.activeColor;
        int colorAlpha = QSConstant.setColorAlpha(i);
        int i2 = tileIconColorState.inactiveColor;
        return new ColorStateList(tileIconColorState.states, new int[]{i, colorAlpha, i2, tileIconColorState.unavailableColor, i2});
    }

    public TileIconColorState(int i, int i2, int i3, boolean z) {
        this.activeColor = i;
        this.inactiveColor = i2;
        this.unavailableColor = i3;
        this.supportMask = z;
        TileIconColorState$$ExternalSyntheticLambda0 tileIconColorState$$ExternalSyntheticLambda0 = new TileIconColorState$$ExternalSyntheticLambda0();
        tileIconColorState$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.colorStateList$delegate = LazyKt__LazyJVMKt.lazy(tileIconColorState$$ExternalSyntheticLambda0);
    }

    public final TileIconColorState copy(int i, int i2, int i3, boolean z) {
        return new TileIconColorState(i, i2, i3, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TileIconColorState)) {
            return false;
        }
        TileIconColorState tileIconColorState = (TileIconColorState) obj;
        return this.activeColor == tileIconColorState.activeColor && this.inactiveColor == tileIconColorState.inactiveColor && this.unavailableColor == tileIconColorState.unavailableColor && this.supportMask == tileIconColorState.supportMask;
    }

    public final int getActiveColor() {
        return this.activeColor;
    }

    public final ColorStateList getColorStateList(Integer num) {
        if (!this.supportMask || num == null) {
            return getColorStateList();
        }
        int colorAlpha = QSConstant.setColorAlpha(num.intValue());
        int iIntValue = num.intValue();
        int i = this.inactiveColor;
        return new ColorStateList(this.states, new int[]{iIntValue, colorAlpha, i, this.unavailableColor, i});
    }

    public final int getInactiveColor() {
        return this.inactiveColor;
    }

    public final boolean getSupportMask() {
        return this.supportMask;
    }

    public final int getUnavailableColor() {
        return this.unavailableColor;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.supportMask) + MonoSpline$.ExternalSyntheticOutline0.m(this.unavailableColor, MonoSpline$.ExternalSyntheticOutline0.m(this.inactiveColor, Integer.hashCode(this.activeColor) * 31, 31), 31);
    }

    public String toString() {
        String hexString = Integer.toHexString(this.activeColor);
        String hexString2 = Integer.toHexString(this.inactiveColor);
        String hexString3 = Integer.toHexString(this.unavailableColor);
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("TileIconColorState(activeColor=", hexString, ", inactiveColor=", hexString2, ", unavailableColor=");
        sbM.append(hexString3);
        sbM.append(", supportMask=");
        sbM.append(this.supportMask);
        sbM.append(")");
        return sbM.toString();
    }

    private final ColorStateList getColorStateList() {
        return (ColorStateList) this.colorStateList$delegate.getValue();
    }
}
