package com.oplus.systemui.notification.lockscreen.capsule.animtor;

import com.oplus.systemui.notification.lockscreen.capsule.CapsuleEarController;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleViewState;
import com.oplus.systemui.notification.lockscreen.capsule.metaball.CapsuleMetaBallParams;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface CapsuleScrollCalculator {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ScrollCalculatorDelegate {
        private CapsuleScrollCalculator value;

        public final CapsuleScrollCalculator getValue(Object obj, KProperty kProperty) {
            CapsuleScrollCalculator capsuleScrollCalculator = this.value;
            if (capsuleScrollCalculator != null) {
                return capsuleScrollCalculator;
            }
            CapsuleScrollCalculator calculator = CapsuleScrollCalculatorFactory.INSTANCE.getCalculator(null);
            this.value = calculator;
            return calculator;
        }

        public final void setValue(Object obj, KProperty kProperty, CapsuleScrollCalculator capsuleScrollCalculator) {
            this.value = capsuleScrollCalculator;
        }
    }

    int calculateBaseCardVerticalClip(int i, float f, int i2, CapsuleItemView capsuleItemView, CapsuleViewState capsuleViewState, CapsuleViewState.ScrollClipInfo scrollClipInfo);

    int calculateNextCardVerticalClip(int i);

    int getBaseCardMinWidth();

    float getBaseCardTransX(float f);

    int getCapsuleBetweenMargin(int i, int i2);

    int getCapsuleHeight();

    int getCapsuleWidth();

    int getEarMargin();

    int getEarToCapsuleDistance();

    int getEarWidth();

    default int getMaxOverScrollWidth() {
        return (int) (CapsuleMetaBallParams.SwipeParam.INSTANCE.getCapsuleOverscrollDistance().get().floatValue() * getCapsuleWidth());
    }

    int getNextCardMinWidth();

    default void initCapsuleParams(int i, int i2, int i3, int i4) {
        setCapsuleWidth(i);
        setCapsuleHeight(i2);
        setEarWidth(i3);
        setEarMargin(i4);
        setEarToCapsuleDistance(i3 + i4);
    }

    void setCapsuleHeight(int i);

    void setCapsuleWidth(int i);

    void setEarMargin(int i);

    void setEarToCapsuleDistance(int i);

    void setEarWidth(int i);

    default void updateBaseCardScale(CapsuleItemView capsuleItemView, CapsuleViewState capsuleViewState, float f) {
    }

    void updateEarState(CapsuleEarController capsuleEarController, int i, boolean z);

    void updateNextCardOverScrollState(CapsuleItemView capsuleItemView, CapsuleViewState capsuleViewState, float f, float f2);

    default void updateNextCardScrollState(CapsuleItemView capsuleItemView, CapsuleViewState capsuleViewState, float f, int i) {
    }
}
