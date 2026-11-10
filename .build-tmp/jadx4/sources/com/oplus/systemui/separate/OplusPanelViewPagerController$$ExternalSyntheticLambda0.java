package com.oplus.systemui.separate;

import android.view.View;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusPanelViewPagerController$$ExternalSyntheticLambda0 implements Function2 {
    public final Object invoke(Object obj, Object obj2) {
        ExpandableView expandableView = (View) obj;
        float fFloatValue = ((Float) obj2).floatValue();
        if ((expandableView instanceof ExpandableNotificationRow) || (expandableView instanceof OplusCustomRow)) {
            expandableView.getViewState().setXTranslation(fFloatValue);
        }
        return Unit.INSTANCE;
    }
}
