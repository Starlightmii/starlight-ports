package com.oplus.systemui.plugins.qs.customize.viewmodel;

import androidx.compose.animation.ChangeSize$;
import com.android.systemui.plugins.qs.QSTile;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TilePair {
    private final CompletableDeferred deferred;
    private final QSTile qsTile;
    public boolean ready;

    public TilePair(QSTile qSTile, boolean z, CompletableDeferred completableDeferred) {
        this.qsTile = qSTile;
        this.ready = z;
        this.deferred = completableDeferred;
    }

    public final QSTile component1() {
        return this.qsTile;
    }

    public final CompletableDeferred component3() {
        return this.deferred;
    }

    public final TilePair copy(QSTile qSTile, boolean z, CompletableDeferred completableDeferred) {
        return new TilePair(qSTile, z, completableDeferred);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TilePair)) {
            return false;
        }
        TilePair tilePair = (TilePair) obj;
        return Intrinsics.areEqual(this.qsTile, tilePair.qsTile) && this.ready == tilePair.ready && Intrinsics.areEqual(this.deferred, tilePair.deferred);
    }

    public final CompletableDeferred getDeferred() {
        return this.deferred;
    }

    public final QSTile getQsTile() {
        return this.qsTile;
    }

    public final boolean getReady() {
        return this.ready;
    }

    public final int hashCode() {
        return this.deferred.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(this.qsTile.hashCode() * 31, 31, this.ready);
    }

    public final void setReady(boolean z) {
        this.ready = z;
    }

    public String toString() {
        return "TilePair(qsTile=" + this.qsTile + ", ready=" + this.ready + ", deferred=" + this.deferred + ")";
    }
}
