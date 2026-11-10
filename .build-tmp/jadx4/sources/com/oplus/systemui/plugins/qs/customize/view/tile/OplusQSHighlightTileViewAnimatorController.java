package com.oplus.systemui.plugins.qs.customize.view.tile;

import com.android.systemui.plugins.qs.QSTile;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSHighlightTileViewAnimatorController {
    public static final OplusQSHighlightTileViewAnimatorController INSTANCE = new OplusQSHighlightTileViewAnimatorController();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Callback {
        void doAnimation(QSTile.State state, boolean z);

        void refreshTextDesc(QSTile.State state, boolean z);
    }

    public final void playAnimation(QSTile.State state, QSTile.State state2, Callback callback) {
        Integer numValueOf;
        int i = state.state;
        boolean z = i == 2;
        if (i == 0) {
            numValueOf = state2 != null ? Integer.valueOf(state2.state) : null;
            if (numValueOf != null && numValueOf.intValue() == 2) {
                callback.doAnimation(state, z);
                return;
            }
            if (numValueOf != null && numValueOf.intValue() == 1) {
                callback.refreshTextDesc(state, z);
                return;
            }
            if (numValueOf != null && numValueOf.intValue() == 0) {
                callback.refreshTextDesc(state, z);
                return;
            } else {
                if (numValueOf != null && numValueOf.intValue() == 101) {
                    callback.doAnimation(state, z);
                    return;
                }
                return;
            }
        }
        if (i == 1) {
            numValueOf = state2 != null ? Integer.valueOf(state2.state) : null;
            if (numValueOf != null && numValueOf.intValue() == 2) {
                callback.doAnimation(state, z);
                return;
            }
            if (numValueOf != null && numValueOf.intValue() == 1) {
                callback.refreshTextDesc(state, z);
                return;
            }
            if (numValueOf != null && numValueOf.intValue() == 0) {
                callback.refreshTextDesc(state, z);
                return;
            } else {
                if (numValueOf != null && numValueOf.intValue() == 101) {
                    callback.doAnimation(state, z);
                    return;
                }
                return;
            }
        }
        if (i == 2) {
            numValueOf = state2 != null ? Integer.valueOf(state2.state) : null;
            if (numValueOf != null && numValueOf.intValue() == 2) {
                callback.refreshTextDesc(state, z);
                return;
            }
            if (numValueOf != null && numValueOf.intValue() == 1) {
                callback.doAnimation(state, z);
                return;
            }
            if (numValueOf != null && numValueOf.intValue() == 0) {
                callback.doAnimation(state, z);
                return;
            } else {
                if (numValueOf != null && numValueOf.intValue() == 101) {
                    callback.doAnimation(state, z);
                    return;
                }
                return;
            }
        }
        if (i != 101) {
            return;
        }
        numValueOf = state2 != null ? Integer.valueOf(state2.state) : null;
        if (numValueOf != null && numValueOf.intValue() == 2) {
            callback.doAnimation(state, z);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 1) {
            callback.doAnimation(state, z);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 0) {
            callback.doAnimation(state, z);
        } else if (numValueOf != null && numValueOf.intValue() == 101) {
            callback.refreshTextDesc(state, z);
        }
    }
}
