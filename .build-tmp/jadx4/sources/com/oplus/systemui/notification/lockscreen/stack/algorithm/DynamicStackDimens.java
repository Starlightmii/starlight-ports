package com.oplus.systemui.notification.lockscreen.stack.algorithm;

import androidx.collection.FloatList$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.foundation.shape.DpCornerSize$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DynamicStackDimens {
    public int largeNotificationHeight;
    public float notificationChildrenContainerDivider;
    public float paddingBetweenElements;
    public float scaleDampingBaseHeight;
    public int stackedLargeNotificationLimitedHeight;
    public float stackedNotificationFadeOutRange;
    public float translationDampingBaseHeight;

    public DynamicStackDimens(int i, int i2, float f, float f2, float f3, float f4, float f5) {
        this.largeNotificationHeight = i;
        this.stackedLargeNotificationLimitedHeight = i2;
        this.notificationChildrenContainerDivider = f;
        this.translationDampingBaseHeight = f2;
        this.scaleDampingBaseHeight = f3;
        this.stackedNotificationFadeOutRange = f4;
        this.paddingBetweenElements = f5;
    }

    public final DynamicStackDimens copy(int i, int i2, float f, float f2, float f3, float f4, float f5) {
        return new DynamicStackDimens(i, i2, f, f2, f3, f4, f5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DynamicStackDimens)) {
            return false;
        }
        DynamicStackDimens dynamicStackDimens = (DynamicStackDimens) obj;
        return this.largeNotificationHeight == dynamicStackDimens.largeNotificationHeight && this.stackedLargeNotificationLimitedHeight == dynamicStackDimens.stackedLargeNotificationLimitedHeight && Float.compare(this.notificationChildrenContainerDivider, dynamicStackDimens.notificationChildrenContainerDivider) == 0 && Float.compare(this.translationDampingBaseHeight, dynamicStackDimens.translationDampingBaseHeight) == 0 && Float.compare(this.scaleDampingBaseHeight, dynamicStackDimens.scaleDampingBaseHeight) == 0 && Float.compare(this.stackedNotificationFadeOutRange, dynamicStackDimens.stackedNotificationFadeOutRange) == 0 && Float.compare(this.paddingBetweenElements, dynamicStackDimens.paddingBetweenElements) == 0;
    }

    public final int getLargeNotificationHeight() {
        return this.largeNotificationHeight;
    }

    public final float getNotificationChildrenContainerDivider() {
        return this.notificationChildrenContainerDivider;
    }

    public final float getPaddingBetweenElements() {
        return this.paddingBetweenElements;
    }

    public final float getScaleDampingBaseHeight() {
        return this.scaleDampingBaseHeight;
    }

    public final int getStackedLargeNotificationLimitedHeight() {
        return this.stackedLargeNotificationLimitedHeight;
    }

    public final float getStackedNotificationFadeOutRange() {
        return this.stackedNotificationFadeOutRange;
    }

    public final float getTranslationDampingBaseHeight() {
        return this.translationDampingBaseHeight;
    }

    public final int hashCode() {
        return Float.hashCode(this.paddingBetweenElements) + ChangeSize$.ExternalSyntheticOutline0.m(this.stackedNotificationFadeOutRange, ChangeSize$.ExternalSyntheticOutline0.m(this.scaleDampingBaseHeight, ChangeSize$.ExternalSyntheticOutline0.m(this.translationDampingBaseHeight, ChangeSize$.ExternalSyntheticOutline0.m(this.notificationChildrenContainerDivider, MonoSpline$.ExternalSyntheticOutline0.m(this.stackedLargeNotificationLimitedHeight, Integer.hashCode(this.largeNotificationHeight) * 31, 31), 31), 31), 31), 31);
    }

    public final void setLargeNotificationHeight(int i) {
        this.largeNotificationHeight = i;
    }

    public final void setNotificationChildrenContainerDivider(float f) {
        this.notificationChildrenContainerDivider = f;
    }

    public final void setPaddingBetweenElements(float f) {
        this.paddingBetweenElements = f;
    }

    public final void setScaleDampingBaseHeight(float f) {
        this.scaleDampingBaseHeight = f;
    }

    public final void setStackedLargeNotificationLimitedHeight(int i) {
        this.stackedLargeNotificationLimitedHeight = i;
    }

    public final void setStackedNotificationFadeOutRange(float f) {
        this.stackedNotificationFadeOutRange = f;
    }

    public final void setTranslationDampingBaseHeight(float f) {
        this.translationDampingBaseHeight = f;
    }

    public String toString() {
        int i = this.largeNotificationHeight;
        int i2 = this.stackedLargeNotificationLimitedHeight;
        float f = this.notificationChildrenContainerDivider;
        float f2 = this.translationDampingBaseHeight;
        float f3 = this.scaleDampingBaseHeight;
        float f4 = this.stackedNotificationFadeOutRange;
        float f5 = this.paddingBetweenElements;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("DynamicStackDimens(largeNotificationHeight=", i, i2, ", stackedLargeNotificationLimitedHeight=", ", notificationChildrenContainerDivider=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f, ", translationDampingBaseHeight=", f2, ", scaleDampingBaseHeight=");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, f3, ", stackedNotificationFadeOutRange=", f4, ", paddingBetweenElements=");
        return DpCornerSize$.ExternalSyntheticOutline0.m(f5, ")", sbM);
    }
}
