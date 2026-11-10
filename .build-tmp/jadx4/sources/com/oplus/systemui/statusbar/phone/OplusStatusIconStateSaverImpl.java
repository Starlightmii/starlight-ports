package com.oplus.systemui.statusbar.phone;

import android.os.Bundle;
import android.view.View;
import com.android.systemui.statusbar.StatusIconDisplayable;
import com.android.systemui.statusbar.phone.OplusStatusIconStateSaver;
import com.android.systemui.statusbar.phone.StatusIconContainer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStatusIconStateSaverImpl extends OplusStatusIconStateSaver {
    public static final Companion Companion = new Companion();
    private static final String FIELD_DISTANCE_TO_VIEW_END = "distanceToViewEnd";
    private static final String FIELD_VISIBLE_STATE = "visibleState";
    private static final String TAG = "OplusStatusIconStateSaverImpl";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static String getSavedStateKey(int i, String str, String str2) {
        return str + ":" + i + ":" + str2;
    }

    public void onRestoreIconInstanceState(StatusIconDisplayable statusIconDisplayable, Bundle bundle) {
        View view = statusIconDisplayable instanceof View ? (View) statusIconDisplayable : null;
        Object tag = view != null ? view.getTag(2131365374) : null;
        StatusIconContainer.StatusIconState statusIconState = tag instanceof StatusIconContainer.StatusIconState ? (StatusIconContainer.StatusIconState) tag : null;
        int i = bundle.getInt(getSavedStateKey(statusIconDisplayable.getSlotTag(), statusIconDisplayable.getSlot(), FIELD_VISIBLE_STATE), -1);
        if (i == 0 || i == 2) {
            if (statusIconState != null) {
                statusIconState.justAdded = false;
            }
            statusIconDisplayable.setVisibleState(i, false);
        }
        float f = bundle.getFloat(getSavedStateKey(statusIconDisplayable.getSlotTag(), statusIconDisplayable.getSlot(), FIELD_DISTANCE_TO_VIEW_END), -1.0f);
        if (f == -1.0f || statusIconState == null) {
            return;
        }
        statusIconState.distanceToViewEnd = f;
    }

    public void onSaveIconInstanceState(StatusIconDisplayable statusIconDisplayable, Bundle bundle) {
        bundle.putInt(getSavedStateKey(statusIconDisplayable.getSlotTag(), statusIconDisplayable.getSlot(), FIELD_VISIBLE_STATE), statusIconDisplayable.getVisibleState());
        View view = statusIconDisplayable instanceof View ? (View) statusIconDisplayable : null;
        Object tag = view != null ? view.getTag(2131365374) : null;
        StatusIconContainer.StatusIconState statusIconState = tag instanceof StatusIconContainer.StatusIconState ? (StatusIconContainer.StatusIconState) tag : null;
        if (statusIconState != null) {
            bundle.putFloat(getSavedStateKey(statusIconDisplayable.getSlotTag(), statusIconDisplayable.getSlot(), FIELD_DISTANCE_TO_VIEW_END), statusIconState.distanceToViewEnd);
        }
    }
}
