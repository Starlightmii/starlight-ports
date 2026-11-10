package com.oplus.systemui.keyguard.wallpaper.pictorial.slider;

import com.android.systemui.keyboard.backlight.ui.view.KeyboardBacklightDialog$RootProperties$;
import java.lang.reflect.Method;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class KeyguardSliderLayout$$ExternalSyntheticLambda7 implements Function0 {
    public /* synthetic */ KeyguardSliderLayout f$0;
    public /* synthetic */ float f$1;
    public /* synthetic */ int f$2;

    public final Object invoke() {
        KeyguardSliderLayout keyguardSliderLayout = this.f$0;
        float f = this.f$1;
        int i = this.f$2;
        Method method = KeyguardSliderLayout.sSetMeasuredDimensionRawMethod;
        StringBuilder sbM = KeyboardBacklightDialog$RootProperties$.ExternalSyntheticOutline0.m(f, i, "transformNotificationPanel offset = ", ", positionOffsetPixels = ", ", mTouchSlop = ");
        sbM.append(keyguardSliderLayout.mTouchSlop);
        sbM.append(", mIsTouchMoving = ");
        sbM.append(keyguardSliderLayout.mIsTouchMoving);
        return sbM.toString();
    }
}
