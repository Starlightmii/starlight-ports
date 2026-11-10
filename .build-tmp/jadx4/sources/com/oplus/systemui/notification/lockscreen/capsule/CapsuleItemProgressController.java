package com.oplus.systemui.notification.lockscreen.capsule;

import android.util.Log;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.oplus.keyguard.folder.OplusBouncerLocationController$clickListener$1$;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleItemProgressController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleItemProgressCtrl";
    private ProgressState currentState = new ProgressState(1.0f, false, 1.0f, CapsuleTotalToAnimationSegment.Companion.getEND());
    private final CapsuleTotalToAnimationManager totalToAnimationManager = new CapsuleTotalToAnimationManager();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleTotalToAnimationManager {
        private Float lastSplitRatio;
        private List<CapsuleTotalToAnimationSegment> segmentMappings = new ArrayList();
        private List<CapsuleTotalToAnimationSegment> specialMap;

        public final ProgressState handle(float f) {
            float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
            List<CapsuleTotalToAnimationSegment> list = this.specialMap;
            if (list != null) {
                for (CapsuleTotalToAnimationSegment capsuleTotalToAnimationSegment : list) {
                    if (fCoerceIn > capsuleTotalToAnimationSegment.totalFrom && fCoerceIn <= capsuleTotalToAnimationSegment.totalTo) {
                        return capsuleTotalToAnimationSegment.getState(fCoerceIn);
                    }
                }
            }
            for (CapsuleTotalToAnimationSegment capsuleTotalToAnimationSegment2 : this.segmentMappings) {
                if (fCoerceIn > capsuleTotalToAnimationSegment2.totalFrom && fCoerceIn <= capsuleTotalToAnimationSegment2.totalTo) {
                    return capsuleTotalToAnimationSegment2.getState(fCoerceIn);
                }
            }
            throw new IllegalArgumentException("totalProgress:" + f + ",map=" + this.segmentMappings);
        }

        public final void setSpecialMap(List<CapsuleTotalToAnimationSegment> list) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("setSpecialMap: ", list == null ? "null(clear)" : list, "CapsuleItemProgressCtrl-->", "SystemUi--Notification");
            }
            this.specialMap = list;
        }

        public final boolean useDefaultMap(float f) {
            if (Intrinsics.areEqual(f, this.lastSplitRatio)) {
                return false;
            }
            this.lastSplitRatio = Float.valueOf(f);
            CapsuleTotalToAnimationSegment.Companion companion = CapsuleTotalToAnimationSegment.Companion;
            this.segmentMappings = CollectionsKt__CollectionsKt.listOf(new CapsuleTotalToAnimationSegment[]{CapsuleTotalToAnimationSegment.copy$default(companion.getSTART(), 0.0f, f, 0.0f, 0.0f, 61), CapsuleTotalToAnimationSegment.copy$default(companion.getEND(), f, 0.0f, 0.0f, 0.0f, 62)});
            return true;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ProgressState {
        private final CapsuleTotalToAnimationSegment currentSegment;
        public final boolean isStep1;
        public final float stepProgress;
        public final float totalProgress;

        public ProgressState(float f, boolean z, float f2, CapsuleTotalToAnimationSegment capsuleTotalToAnimationSegment) {
            this.totalProgress = f;
            this.isStep1 = z;
            this.stepProgress = f2;
            this.currentSegment = capsuleTotalToAnimationSegment;
        }

        public final CapsuleTotalToAnimationSegment component4() {
            return this.currentSegment;
        }

        public final ProgressState copy(float f, boolean z, float f2, CapsuleTotalToAnimationSegment capsuleTotalToAnimationSegment) {
            return new ProgressState(f, z, f2, capsuleTotalToAnimationSegment);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ProgressState)) {
                return false;
            }
            ProgressState progressState = (ProgressState) obj;
            return Float.compare(this.totalProgress, progressState.totalProgress) == 0 && this.isStep1 == progressState.isStep1 && Float.compare(this.stepProgress, progressState.stepProgress) == 0 && Intrinsics.areEqual(this.currentSegment, progressState.currentSegment);
        }

        public final CapsuleTotalToAnimationSegment getCurrentSegment() {
            return this.currentSegment;
        }

        public final float getStepProgress() {
            return this.stepProgress;
        }

        public final float getTotalProgress() {
            return this.totalProgress;
        }

        public final int hashCode() {
            return this.currentSegment.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(this.stepProgress, ChangeSize$.ExternalSyntheticOutline0.m(Float.hashCode(this.totalProgress) * 31, 31, this.isStep1), 31);
        }

        public String toString() {
            return "ProgressState(totalProgress=" + this.totalProgress + ", isStep1=" + this.isStep1 + ", stepProgress=" + this.stepProgress + ", currentSegment=" + this.currentSegment + ")";
        }
    }

    /* JADX WARN: Code duplicated, block: B:44:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:46:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:48:0x00da  */
    /* JADX WARN: Code duplicated, block: B:64:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:65:? A[RETURN, SYNTHETIC] */
    public final void setTransition(float f, boolean z) {
        ProgressState progressState;
        CapsuleTotalToAnimationSegment capsuleTotalToAnimationSegmentCopy$default;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            ProgressState progressState2 = this.currentState;
            StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("setTransition: isShowing=", z, " splitRatio=", f, " current=");
            sbM.append(progressState2);
            Log.d("SystemUi--Notification", "CapsuleItemProgressCtrl-->".concat(sbM.toString()));
        }
        if ((this.currentState.getTotalProgress() < f) != this.currentState.isStep1) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleItemProgressCtrl-->setTransition skipped");
                return;
            }
            return;
        }
        boolean zUseDefaultMap = this.totalToAnimationManager.useDefaultMap(f);
        if (!zUseDefaultMap || this.currentState.getTotalProgress() == 0.0f || this.currentState.getTotalProgress() == 1.0f) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleItemProgressCtrl-->".concat("setTransition: changed=" + zUseDefaultMap + " progress=" + this.currentState.getTotalProgress()));
                return;
            }
            return;
        }
        if ((z && !this.currentState.isStep1) || (!z && this.currentState.isStep1)) {
            CapsuleTotalToAnimationSegment capsuleTotalToAnimationSegmentCopy$default2 = CapsuleTotalToAnimationSegment.copy$default(this.currentState.getCurrentSegment(), 0.0f, 0.0f, 0.0f, 0.0f, 31);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleItemProgressCtrl-->handlerLastStep apply specialMap segment");
            }
            this.totalToAnimationManager.setSpecialMap(CollectionsKt__CollectionsJVMKt.listOf(capsuleTotalToAnimationSegmentCopy$default2));
            return;
        }
        if (z) {
            ProgressState progressState3 = this.currentState;
            if (progressState3.isStep1) {
                capsuleTotalToAnimationSegmentCopy$default = CapsuleTotalToAnimationSegment.copy$default(progressState3.getCurrentSegment(), this.currentState.getTotalProgress(), f, this.currentState.getStepProgress(), 0.0f, 20);
            } else {
                if (!z) {
                    return;
                }
                progressState = this.currentState;
                if (!progressState.isStep1) {
                    return;
                } else {
                    capsuleTotalToAnimationSegmentCopy$default = CapsuleTotalToAnimationSegment.copy$default(progressState.getCurrentSegment(), f, this.currentState.getTotalProgress(), 0.0f, this.currentState.getStepProgress(), 12);
                }
            }
        } else {
            if (!z) {
                return;
            }
            progressState = this.currentState;
            if (!progressState.isStep1) {
                return;
            } else {
                capsuleTotalToAnimationSegmentCopy$default = CapsuleTotalToAnimationSegment.copy$default(progressState.getCurrentSegment(), f, this.currentState.getTotalProgress(), 0.0f, this.currentState.getStepProgress(), 12);
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleItemProgressCtrl-->handlerSameStep apply specialMap");
        }
        this.totalToAnimationManager.setSpecialMap(CollectionsKt__CollectionsJVMKt.listOf(capsuleTotalToAnimationSegmentCopy$default));
    }

    public final ProgressState updateProgress(float f) {
        ProgressState progressState;
        if (f == 0.0f) {
            progressState = new ProgressState(0.0f, true, 0.0f, CapsuleTotalToAnimationSegment.Companion.getSTART());
        } else {
            progressState = f == 1.0f ? new ProgressState(1.0f, false, 1.0f, CapsuleTotalToAnimationSegment.Companion.getEND()) : this.totalToAnimationManager.handle(f);
        }
        boolean z = this.currentState.getCurrentSegment().isDefault;
        this.currentState = progressState;
        if (!z && progressState.getCurrentSegment().isDefault) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleItemProgressCtrl-->updateProgress clear SpecialMap");
            }
            this.totalToAnimationManager.setSpecialMap(null);
        }
        return progressState;
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleTotalToAnimationSegment {
        public static final Companion Companion = new Companion();
        private static final CapsuleTotalToAnimationSegment END;
        private static final CapsuleTotalToAnimationSegment START;
        public final float animaFrom;
        public final float animaTo;
        public final boolean isDefault;
        public final boolean isStep1;
        public final float totalFrom;
        public final float totalTo;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
            public final CapsuleTotalToAnimationSegment getEND() {
                return CapsuleTotalToAnimationSegment.END;
            }

            public final CapsuleTotalToAnimationSegment getSTART() {
                return CapsuleTotalToAnimationSegment.START;
            }
        }

        static {
            float f = 0.4f;
            START = new CapsuleTotalToAnimationSegment(0.0f, f, true);
            END = new CapsuleTotalToAnimationSegment(f, 1.0f, false);
        }

        public CapsuleTotalToAnimationSegment(float f, float f2, boolean z, float f3, float f4, boolean z2) {
            this.totalFrom = f;
            this.totalTo = f2;
            this.isStep1 = z;
            this.animaFrom = f3;
            this.animaTo = f4;
            this.isDefault = z2;
        }

        public static /* synthetic */ CapsuleTotalToAnimationSegment copy$default(CapsuleTotalToAnimationSegment capsuleTotalToAnimationSegment, float f, float f2, float f3, float f4, int i) {
            if ((i & 1) != 0) {
                f = capsuleTotalToAnimationSegment.totalFrom;
            }
            float f5 = f;
            if ((i & 2) != 0) {
                f2 = capsuleTotalToAnimationSegment.totalTo;
            }
            float f6 = f2;
            boolean z = capsuleTotalToAnimationSegment.isStep1;
            if ((i & 8) != 0) {
                f3 = capsuleTotalToAnimationSegment.animaFrom;
            }
            float f7 = f3;
            if ((i & 16) != 0) {
                f4 = capsuleTotalToAnimationSegment.animaTo;
            }
            return capsuleTotalToAnimationSegment.copy(f5, f6, z, f7, f4, (i & 32) != 0 ? capsuleTotalToAnimationSegment.isDefault : false);
        }

        public final CapsuleTotalToAnimationSegment copy(float f, float f2, boolean z, float f3, float f4, boolean z2) {
            return new CapsuleTotalToAnimationSegment(f, f2, z, f3, f4, z2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CapsuleTotalToAnimationSegment)) {
                return false;
            }
            CapsuleTotalToAnimationSegment capsuleTotalToAnimationSegment = (CapsuleTotalToAnimationSegment) obj;
            return Float.compare(this.totalFrom, capsuleTotalToAnimationSegment.totalFrom) == 0 && Float.compare(this.totalTo, capsuleTotalToAnimationSegment.totalTo) == 0 && this.isStep1 == capsuleTotalToAnimationSegment.isStep1 && Float.compare(this.animaFrom, capsuleTotalToAnimationSegment.animaFrom) == 0 && Float.compare(this.animaTo, capsuleTotalToAnimationSegment.animaTo) == 0 && this.isDefault == capsuleTotalToAnimationSegment.isDefault;
        }

        public final float getAnimaFrom() {
            return this.animaFrom;
        }

        public final float getAnimaTo() {
            return this.animaTo;
        }

        public final ProgressState getState(float f) {
            float f2 = this.totalTo;
            float f3 = this.totalFrom;
            float f4 = f2 - f3;
            if (f4 < 1.0E-6f) {
                f4 = 1.0E-6f;
            }
            float f5 = (f - f3) / f4;
            float f6 = this.animaTo;
            float f7 = this.animaFrom;
            return new ProgressState(f, this.isStep1, DrawerArrowDrawable$.ExternalSyntheticOutline0.m(f6, f7, f5, f7), this);
        }

        public final float getTotalFrom() {
            return this.totalFrom;
        }

        public final float getTotalTo() {
            return this.totalTo;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isDefault) + ChangeSize$.ExternalSyntheticOutline0.m(this.animaTo, ChangeSize$.ExternalSyntheticOutline0.m(this.animaFrom, ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(this.totalTo, Float.hashCode(this.totalFrom) * 31, 31), 31, this.isStep1), 31), 31);
        }

        public String toString() {
            StringBuilder sbM = MonoSpline$.ExternalSyntheticOutline0.m("CapsuleTotalToAnimationSegment(totalFrom=", this.totalFrom, ", totalTo=", this.totalTo, ", isStep1=");
            sbM.append(this.isStep1);
            sbM.append(", animaFrom=");
            sbM.append(this.animaFrom);
            sbM.append(", animaTo=");
            sbM.append(this.animaTo);
            sbM.append(", isDefault=");
            sbM.append(this.isDefault);
            sbM.append(")");
            return sbM.toString();
        }

        public /* synthetic */ CapsuleTotalToAnimationSegment(float f, float f2, boolean z) {
            this(f, f2, z, 0.0f, 1.0f, true);
        }
    }
}
