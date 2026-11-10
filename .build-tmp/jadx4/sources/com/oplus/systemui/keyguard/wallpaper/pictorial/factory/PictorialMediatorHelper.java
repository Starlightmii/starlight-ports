package com.oplus.systemui.keyguard.wallpaper.pictorial.factory;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegateImpl$;
import androidx.collection.FloatList$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.ui.spatial.RectList$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface PictorialMediatorHelper {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String TAG = "PictorialMediatorHelper";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String TAG = "PictorialMediatorHelper";
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SwitchStateParams {
        public int autoPlaySwitchKeyState;
        public boolean autoSwitchOn;
        public int enableSwitchKeyState;
        public boolean enableSwitchOn;
        public boolean isNotShowAgain;
        public boolean isPictorialEnable;
        public boolean isSupportLeftSwipe;
        public int leftSwipeSwitchKeyState;
        public int mainSwitchKeyState;
        public boolean mainSwitchOn;
        public int rightSwipeSwitchKeyState;

        public SwitchStateParams(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
            this.mainSwitchKeyState = i;
            this.autoPlaySwitchKeyState = i2;
            this.enableSwitchKeyState = i3;
            this.leftSwipeSwitchKeyState = i4;
            this.rightSwipeSwitchKeyState = i5;
            this.mainSwitchOn = z;
            this.enableSwitchOn = z2;
            this.autoSwitchOn = z3;
            this.isPictorialEnable = z4;
            this.isSupportLeftSwipe = z5;
            this.isNotShowAgain = z6;
        }

        public final SwitchStateParams copy(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
            return new SwitchStateParams(i, i2, i3, i4, i5, z, z2, z3, z4, z5, z6);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SwitchStateParams)) {
                return false;
            }
            SwitchStateParams switchStateParams = (SwitchStateParams) obj;
            return this.mainSwitchKeyState == switchStateParams.mainSwitchKeyState && this.autoPlaySwitchKeyState == switchStateParams.autoPlaySwitchKeyState && this.enableSwitchKeyState == switchStateParams.enableSwitchKeyState && this.leftSwipeSwitchKeyState == switchStateParams.leftSwipeSwitchKeyState && this.rightSwipeSwitchKeyState == switchStateParams.rightSwipeSwitchKeyState && this.mainSwitchOn == switchStateParams.mainSwitchOn && this.enableSwitchOn == switchStateParams.enableSwitchOn && this.autoSwitchOn == switchStateParams.autoSwitchOn && this.isPictorialEnable == switchStateParams.isPictorialEnable && this.isSupportLeftSwipe == switchStateParams.isSupportLeftSwipe && this.isNotShowAgain == switchStateParams.isNotShowAgain;
        }

        public final int getAutoPlaySwitchKeyState() {
            return this.autoPlaySwitchKeyState;
        }

        public final boolean getAutoSwitchOn() {
            return this.autoSwitchOn;
        }

        public final int getEnableSwitchKeyState() {
            return this.enableSwitchKeyState;
        }

        public final boolean getEnableSwitchOn() {
            return this.enableSwitchOn;
        }

        public final int getLeftSwipeSwitchKeyState() {
            return this.leftSwipeSwitchKeyState;
        }

        public final int getMainSwitchKeyState() {
            return this.mainSwitchKeyState;
        }

        public final boolean getMainSwitchOn() {
            return this.mainSwitchOn;
        }

        public final int getRightSwipeSwitchKeyState() {
            return this.rightSwipeSwitchKeyState;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isNotShowAgain) + ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(MonoSpline$.ExternalSyntheticOutline0.m(this.rightSwipeSwitchKeyState, MonoSpline$.ExternalSyntheticOutline0.m(this.leftSwipeSwitchKeyState, MonoSpline$.ExternalSyntheticOutline0.m(this.enableSwitchKeyState, MonoSpline$.ExternalSyntheticOutline0.m(this.autoPlaySwitchKeyState, Integer.hashCode(this.mainSwitchKeyState) * 31, 31), 31), 31), 31), 31, this.mainSwitchOn), 31, this.enableSwitchOn), 31, this.autoSwitchOn), 31, this.isPictorialEnable), 31, this.isSupportLeftSwipe);
        }

        public final void setAutoPlaySwitchKeyState(int i) {
            this.autoPlaySwitchKeyState = i;
        }

        public final void setAutoSwitchOn(boolean z) {
            this.autoSwitchOn = z;
        }

        public final void setEnableSwitchKeyState(int i) {
            this.enableSwitchKeyState = i;
        }

        public final void setEnableSwitchOn(boolean z) {
            this.enableSwitchOn = z;
        }

        public final void setLeftSwipeSwitchKeyState(int i) {
            this.leftSwipeSwitchKeyState = i;
        }

        public final void setMainSwitchKeyState(int i) {
            this.mainSwitchKeyState = i;
        }

        public final void setMainSwitchOn(boolean z) {
            this.mainSwitchOn = z;
        }

        public final void setNotShowAgain(boolean z) {
            this.isNotShowAgain = z;
        }

        public final void setPictorialEnable(boolean z) {
            this.isPictorialEnable = z;
        }

        public final void setRightSwipeSwitchKeyState(int i) {
            this.rightSwipeSwitchKeyState = i;
        }

        public final void setSupportLeftSwipe(boolean z) {
            this.isSupportLeftSwipe = z;
        }

        public String toString() {
            int i = this.mainSwitchKeyState;
            int i2 = this.autoPlaySwitchKeyState;
            int i3 = this.enableSwitchKeyState;
            int i4 = this.leftSwipeSwitchKeyState;
            int i5 = this.rightSwipeSwitchKeyState;
            boolean z = this.mainSwitchOn;
            boolean z2 = this.enableSwitchOn;
            boolean z3 = this.autoSwitchOn;
            boolean z4 = this.isPictorialEnable;
            boolean z5 = this.isSupportLeftSwipe;
            boolean z6 = this.isNotShowAgain;
            StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("SwitchStateParams(mainSwitchKeyState=", i, i2, ", autoPlaySwitchKeyState=", ", enableSwitchKeyState=");
            RectList$.ExternalSyntheticOutline0.m(i3, i4, ", leftSwipeSwitchKeyState=", ", rightSwipeSwitchKeyState=", sbM);
            ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(i5, ", mainSwitchOn=", ", enableSwitchOn=", sbM, z);
            KeyguardLogger$.ExternalSyntheticOutline0.m(", autoSwitchOn=", ", isPictorialEnable=", sbM, z2, z3);
            KeyguardLogger$.ExternalSyntheticOutline0.m(", isSupportLeftSwipe=", ", isNotShowAgain=", sbM, z4, z5);
            return AppCompatDelegateImpl$.ExternalSyntheticOutline0.m(")", sbM, z6);
        }
    }

    default void adjustForVerticalApp(Context context, SwitchStateParams switchStateParams) {
    }

    default boolean calculateAutoPlayState(SwitchStateParams switchStateParams) {
        return switchStateParams.getAutoPlaySwitchKeyState() == 1 && switchStateParams.getMainSwitchKeyState() == 1;
    }

    default void initStatusWhenShowLocked(Context context, SwitchStateParams switchStateParams) {
    }

    default boolean isAutoPlaySwitchOn(SwitchStateParams switchStateParams) {
        return switchStateParams.getAutoSwitchOn();
    }

    default boolean isLeftSwipeSwitchOn(SwitchStateParams switchStateParams) {
        if (!isMainSwitchOn(switchStateParams)) {
            return !SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState;
        }
        if (switchStateParams.getLeftSwipeSwitchKeyState() == -1) {
            switchStateParams.setLeftSwipeSwitchKeyState(1);
        }
        return switchStateParams.getLeftSwipeSwitchKeyState() == 1;
    }

    default boolean isMainSwitchOn(SwitchStateParams switchStateParams) {
        return switchStateParams.getMainSwitchOn();
    }

    default boolean isRightSwipeSwitchOn(SwitchStateParams switchStateParams) {
        if (switchStateParams.getRightSwipeSwitchKeyState() == -1) {
            switchStateParams.setRightSwipeSwitchKeyState(0);
        }
        return switchStateParams.getRightSwipeSwitchKeyState() == 1;
    }

    default boolean isSupportDisableAlive(Context context, Bundle bundle) {
        if (bundle != null) {
            return bundle.getBoolean("disable_pictorial_alive", false);
        }
        return false;
    }

    default void onChangeAutoSwitchState(Context context, SwitchStateParams switchStateParams) {
    }

    default void onChangeEnableSwitchState(Context context, SwitchStateParams switchStateParams) {
    }

    default void onChangeMainSwitchState(Context context, SwitchStateParams switchStateParams) {
    }

    default void onPictorialServicesConnect() {
    }

    default boolean onPictorialServicesDisconnect(Context context) {
        return false;
    }
}
