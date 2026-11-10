package com.oplus.systemui.plugins.qs.customize.viewmodel;

import androidx.car.app.model.constraints.ActionsConstraints$;
import androidx.car.app.serialization.Bundler$;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class TileEditEffect {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AToBMaxTileCountEffect extends TileEditEffect {
        public final int maxCount;

        public AToBMaxTileCountEffect(int i) {
            this.maxCount = i;
        }

        public final AToBMaxTileCountEffect copy(int i) {
            return new AToBMaxTileCountEffect(i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof AToBMaxTileCountEffect) && this.maxCount == ((AToBMaxTileCountEffect) obj).maxCount;
        }

        public final int getMaxCount() {
            return this.maxCount;
        }

        public final int hashCode() {
            return Integer.hashCode(this.maxCount);
        }

        public String toString() {
            return ActionsConstraints$.ExternalSyntheticOutline0.m(this.maxCount, "AToBMaxTileCountEffect(maxCount=", ")");
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MaxTileCountEffect extends TileEditEffect {
        public final int maxCount;

        public MaxTileCountEffect(int i) {
            this.maxCount = i;
        }

        public final MaxTileCountEffect copy(int i) {
            return new MaxTileCountEffect(i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof MaxTileCountEffect) && this.maxCount == ((MaxTileCountEffect) obj).maxCount;
        }

        public final int getMaxCount() {
            return this.maxCount;
        }

        public final int hashCode() {
            return Integer.hashCode(this.maxCount);
        }

        public String toString() {
            return ActionsConstraints$.ExternalSyntheticOutline0.m(this.maxCount, "MaxTileCountEffect(maxCount=", ")");
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TileAddFailedDueToNoEnoughSpace extends TileEditEffect {
        private final String tileLabel;

        public TileAddFailedDueToNoEnoughSpace(String str) {
            this.tileLabel = str;
        }

        public final String component1() {
            return this.tileLabel;
        }

        public final TileAddFailedDueToNoEnoughSpace copy(String str) {
            return new TileAddFailedDueToNoEnoughSpace(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof TileAddFailedDueToNoEnoughSpace) && Intrinsics.areEqual(this.tileLabel, ((TileAddFailedDueToNoEnoughSpace) obj).tileLabel);
        }

        public final String getTileLabel() {
            return this.tileLabel;
        }

        public final int hashCode() {
            return this.tileLabel.hashCode();
        }

        public String toString() {
            return Bundler$.ExternalSyntheticOutline0.m("TileAddFailedDueToNoEnoughSpace(tileLabel=", this.tileLabel, ")");
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TileAlreadyAddedEffect extends TileEditEffect {
        private final CharSequence tileLabel;

        public TileAlreadyAddedEffect(CharSequence charSequence) {
            this.tileLabel = charSequence;
        }

        public final CharSequence component1() {
            return this.tileLabel;
        }

        public final TileAlreadyAddedEffect copy(CharSequence charSequence) {
            return new TileAlreadyAddedEffect(charSequence);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof TileAlreadyAddedEffect) && Intrinsics.areEqual(this.tileLabel, ((TileAlreadyAddedEffect) obj).tileLabel);
        }

        public final CharSequence getTileLabel() {
            return this.tileLabel;
        }

        public final int hashCode() {
            return this.tileLabel.hashCode();
        }

        public String toString() {
            return "TileAlreadyAddedEffect(tileLabel=" + ((Object) this.tileLabel) + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TransformToSpecEffect extends TileEditEffect {
        public final boolean highlight;
        public final long targetUniqueId;

        public TransformToSpecEffect(long j, boolean z) {
            this.targetUniqueId = j;
            this.highlight = z;
        }

        public final TransformToSpecEffect copy(long j, boolean z) {
            return new TransformToSpecEffect(j, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TransformToSpecEffect)) {
                return false;
            }
            TransformToSpecEffect transformToSpecEffect = (TransformToSpecEffect) obj;
            return this.targetUniqueId == transformToSpecEffect.targetUniqueId && this.highlight == transformToSpecEffect.highlight;
        }

        public final boolean getHighlight() {
            return this.highlight;
        }

        public final long getTargetUniqueId() {
            return this.targetUniqueId;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.highlight) + (Long.hashCode(this.targetUniqueId) * 31);
        }

        public String toString() {
            return "TransformToSpecEffect(targetUniqueId=" + this.targetUniqueId + ", highlight=" + this.highlight + ")";
        }
    }
}
