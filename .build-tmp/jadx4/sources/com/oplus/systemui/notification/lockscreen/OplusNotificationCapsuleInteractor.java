package com.oplus.systemui.notification.lockscreen;

import androidx.car.app.SurfaceContainer$;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface OplusNotificationCapsuleInteractor {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsulePosition {
        public final int initY;
        public final int maxTranslationY;
        public final int translationY;

        public CapsulePosition(int i, int i2, int i3) {
            this.initY = i;
            this.maxTranslationY = i2;
            this.translationY = i3;
        }

        public static /* synthetic */ CapsulePosition copy$default(CapsulePosition capsulePosition, int i, int i2, int i3, int i4) {
            if ((i4 & 1) != 0) {
                i = capsulePosition.initY;
            }
            if ((i4 & 4) != 0) {
                i3 = capsulePosition.translationY;
            }
            return capsulePosition.copy(i, i2, i3);
        }

        public final CapsulePosition copy(int i, int i2, int i3) {
            return new CapsulePosition(i, i2, i3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CapsulePosition)) {
                return false;
            }
            CapsulePosition capsulePosition = (CapsulePosition) obj;
            return this.initY == capsulePosition.initY && this.maxTranslationY == capsulePosition.maxTranslationY && this.translationY == capsulePosition.translationY;
        }

        public final int getInitY() {
            return this.initY;
        }

        public final int getMaxTranslationY() {
            return this.maxTranslationY;
        }

        public final int getTranslationY() {
            return this.translationY;
        }

        public final int hashCode() {
            return Integer.hashCode(this.translationY) + MonoSpline$.ExternalSyntheticOutline0.m(this.maxTranslationY, Integer.hashCode(this.initY) * 31, 31);
        }

        public String toString() {
            return SurfaceContainer$.ExternalSyntheticOutline0.m(this.translationY, ")", FloatList$.ExternalSyntheticOutline0.m("CapsulePosition(initY=", this.initY, this.maxTranslationY, ", maxTranslationY=", ", translationY="));
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface UpEventListener {
        void onUpEvent(String str);
    }

    StateFlow getCapsulePositionFlow();

    void setForceHideNotificationCapsule(boolean z);

    void setUpEventListener(UpEventListener upEventListener);
}
