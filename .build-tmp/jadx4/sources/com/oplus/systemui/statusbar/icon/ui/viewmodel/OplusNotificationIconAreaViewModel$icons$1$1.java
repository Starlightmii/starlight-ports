package com.oplus.systemui.statusbar.icon.ui.viewmodel;

import com.android.systemui.statusbar.notification.icon.domain.interactor.ActiveNotificationIconModel;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconInfo;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconsViewData;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconsViewDataKt;
import java.util.ArrayList;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusNotificationIconAreaViewModel$icons$1$1 extends SuspendLambda implements Function3 {
    /* synthetic */ int I$0;
    /* synthetic */ Object L$0;
    int label;

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int iIntValue = ((Number) obj2).intValue();
        OplusNotificationIconAreaViewModel$icons$1$1 oplusNotificationIconAreaViewModel$icons$1$1 = new OplusNotificationIconAreaViewModel$icons$1$1(3, (Continuation) obj3);
        oplusNotificationIconAreaViewModel$icons$1$1.L$0 = (Set) obj;
        oplusNotificationIconAreaViewModel$icons$1$1.I$0 = iIntValue;
        return oplusNotificationIconAreaViewModel$icons$1$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Set<ActiveNotificationIconModel> set = (Set) this.L$0;
        int i = this.I$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        ArrayList arrayList = new ArrayList();
        for (ActiveNotificationIconModel activeNotificationIconModel : set) {
            NotificationIconInfo iconInfo = NotificationIconsViewDataKt.toIconInfo(activeNotificationIconModel, activeNotificationIconModel.getStatusBarIcon());
            if (iconInfo != null) {
                arrayList.add(iconInfo);
            }
        }
        return new NotificationIconsViewData(arrayList, i, 4);
    }
}
