package com.oplus.systemui.privacy;

import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.ui.spatial.RectList$;
import com.oplus.transition.OplusAnimationController;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PrivacyDotLayoutState {
    public static final Companion Companion = new Companion();
    private static final PrivacyDotLayoutState EMPTY = new PrivacyDotLayoutState(0, 0, 0, 0, 0, 0, 0, 0, 0, 0.0f);
    public final int bottomInset;
    public final float burnInTranslationX;
    public final int leftInset;
    public final int rightInset;
    public final int statusBarContentsHeight;
    public final int statusBarPaddingBottom;
    public final int statusBarPaddingLeft;
    public final int statusBarPaddingRight;
    public final int statusBarPaddingTop;
    public final int topInset;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final PrivacyDotLayoutState getEMPTY() {
            return PrivacyDotLayoutState.EMPTY;
        }
    }

    public PrivacyDotLayoutState(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, float f) {
        this.leftInset = i;
        this.topInset = i2;
        this.rightInset = i3;
        this.bottomInset = i4;
        this.statusBarPaddingLeft = i5;
        this.statusBarPaddingRight = i6;
        this.statusBarPaddingTop = i7;
        this.statusBarPaddingBottom = i8;
        this.statusBarContentsHeight = i9;
        this.burnInTranslationX = f;
    }

    public static /* synthetic */ PrivacyDotLayoutState copy$default(PrivacyDotLayoutState privacyDotLayoutState, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, float f, int i9) {
        if ((i9 & 1) != 0) {
            i = privacyDotLayoutState.leftInset;
        }
        int i10 = i;
        if ((i9 & 2) != 0) {
            i2 = privacyDotLayoutState.topInset;
        }
        int i11 = i2;
        if ((i9 & 4) != 0) {
            i3 = privacyDotLayoutState.rightInset;
        }
        int i12 = i3;
        int i13 = (i9 & 8) != 0 ? privacyDotLayoutState.bottomInset : 0;
        if ((i9 & 16) != 0) {
            i4 = privacyDotLayoutState.statusBarPaddingLeft;
        }
        return privacyDotLayoutState.copy(i10, i11, i12, i13, i4, (i9 & 32) != 0 ? privacyDotLayoutState.statusBarPaddingRight : i5, (i9 & 64) != 0 ? privacyDotLayoutState.statusBarPaddingTop : i6, (i9 & 128) != 0 ? privacyDotLayoutState.statusBarPaddingBottom : i7, (i9 & 256) != 0 ? privacyDotLayoutState.statusBarContentsHeight : i8, (i9 & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) != 0 ? privacyDotLayoutState.burnInTranslationX : f);
    }

    public final PrivacyDotLayoutState copy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, float f) {
        return new PrivacyDotLayoutState(i, i2, i3, i4, i5, i6, i7, i8, i9, f);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PrivacyDotLayoutState)) {
            return false;
        }
        PrivacyDotLayoutState privacyDotLayoutState = (PrivacyDotLayoutState) obj;
        return this.leftInset == privacyDotLayoutState.leftInset && this.topInset == privacyDotLayoutState.topInset && this.rightInset == privacyDotLayoutState.rightInset && this.bottomInset == privacyDotLayoutState.bottomInset && this.statusBarPaddingLeft == privacyDotLayoutState.statusBarPaddingLeft && this.statusBarPaddingRight == privacyDotLayoutState.statusBarPaddingRight && this.statusBarPaddingTop == privacyDotLayoutState.statusBarPaddingTop && this.statusBarPaddingBottom == privacyDotLayoutState.statusBarPaddingBottom && this.statusBarContentsHeight == privacyDotLayoutState.statusBarContentsHeight && Float.compare(this.burnInTranslationX, privacyDotLayoutState.burnInTranslationX) == 0;
    }

    public final int getBottomInset() {
        return this.bottomInset;
    }

    public final float getBurnInTranslationX() {
        return this.burnInTranslationX;
    }

    public final int getLeftInset() {
        return this.leftInset;
    }

    public final int getRightInset() {
        return this.rightInset;
    }

    public final int getStatusBarContentsHeight() {
        return this.statusBarContentsHeight;
    }

    public final int getStatusBarPaddingBottom() {
        return this.statusBarPaddingBottom;
    }

    public final int getStatusBarPaddingLeft() {
        return this.statusBarPaddingLeft;
    }

    public final int getStatusBarPaddingRight() {
        return this.statusBarPaddingRight;
    }

    public final int getStatusBarPaddingTop() {
        return this.statusBarPaddingTop;
    }

    public final int getTopInset() {
        return this.topInset;
    }

    public final int hashCode() {
        return Float.hashCode(this.burnInTranslationX) + MonoSpline$.ExternalSyntheticOutline0.m(this.statusBarContentsHeight, MonoSpline$.ExternalSyntheticOutline0.m(this.statusBarPaddingBottom, MonoSpline$.ExternalSyntheticOutline0.m(this.statusBarPaddingTop, MonoSpline$.ExternalSyntheticOutline0.m(this.statusBarPaddingRight, MonoSpline$.ExternalSyntheticOutline0.m(this.statusBarPaddingLeft, MonoSpline$.ExternalSyntheticOutline0.m(this.bottomInset, MonoSpline$.ExternalSyntheticOutline0.m(this.rightInset, MonoSpline$.ExternalSyntheticOutline0.m(this.topInset, Integer.hashCode(this.leftInset) * 31, 31), 31), 31), 31), 31), 31), 31), 31);
    }

    public String toString() {
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("PrivacyDotLayoutState(leftInset=", this.leftInset, this.topInset, ", topInset=", ", rightInset=");
        RectList$.ExternalSyntheticOutline0.m(this.rightInset, this.bottomInset, ", bottomInset=", ", statusBarPaddingLeft=", sbM);
        RectList$.ExternalSyntheticOutline0.m(this.statusBarPaddingLeft, this.statusBarPaddingRight, ", statusBarPaddingRight=", ", statusBarPaddingTop=", sbM);
        RectList$.ExternalSyntheticOutline0.m(this.statusBarPaddingTop, this.statusBarPaddingBottom, ", statusBarPaddingBottom=", ", statusBarContentsHeight=", sbM);
        sbM.append(this.statusBarContentsHeight);
        sbM.append(", burnInTranslationX=");
        sbM.append(this.burnInTranslationX);
        sbM.append(")");
        return sbM.toString();
    }
}
