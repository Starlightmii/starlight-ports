package com.oplus.systemui.notification.stackednotification;

import android.view.View;
import com.android.systemui.statusbar.notification.animation.RowAnimationStateEx;
import com.android.systemui.statusbar.notification.animation.ScaleState;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.oplus.systemui.statusbar.notification.animation.RowAnimationStateExImpl;
import java.util.List;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface NotificationStackedRuler {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final String KEY_STACKED_NOTIFICATION = "key_stacked_notification";

        public final void requestStackedScale(ExpandableViewState expandableViewState, float f) {
            RowAnimationStateEx rowAnimationStateEx;
            OplusExpandableViewStateEx oplusExpandableViewStateEx = expandableViewState.mExpandableViewStateEx;
            if (oplusExpandableViewStateEx == null || (rowAnimationStateEx = oplusExpandableViewStateEx.getRowAnimationStateEx()) == null) {
                return;
            }
            ((RowAnimationStateExImpl) rowAnimationStateEx).requestRowScale(KEY_STACKED_NOTIFICATION, ScaleState.Companion.invoke(f));
        }
    }

    boolean getCurrentIsStackedNotification();

    StateFlow isStackedNotification();

    default void prepareAddOrRemoveAnimationForOverlay(View view, View view2, boolean z) {
    }

    float shelfPosition(AmbientState ambientState);

    void updateStackedState(List<? extends ExpandableView> list, AmbientState ambientState, float f);
}
