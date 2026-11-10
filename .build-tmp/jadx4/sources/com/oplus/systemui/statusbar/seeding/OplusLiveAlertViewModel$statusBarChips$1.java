package com.oplus.systemui.statusbar.seeding;

import java.util.List;
import java.util.Locale;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class OplusLiveAlertViewModel$statusBarChips$1 extends SuspendLambda implements Function3 {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$10;
    Object L$11;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    final /* synthetic */ OplusLiveAlertViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OplusLiveAlertViewModel$statusBarChips$1(OplusLiveAlertViewModel oplusLiveAlertViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = oplusLiveAlertViewModel;
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OplusLiveAlertViewModel$statusBarChips$1 oplusLiveAlertViewModel$statusBarChips$1 = new OplusLiveAlertViewModel$statusBarChips$1(this.this$0, (Continuation) obj3);
        oplusLiveAlertViewModel$statusBarChips$1.L$0 = (List) obj;
        oplusLiveAlertViewModel$statusBarChips$1.L$1 = (Locale) obj2;
        return oplusLiveAlertViewModel$statusBarChips$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x006d  */
    /* JADX WARN: Code duplicated, block: B:13:0x007b  */
    /* JADX WARN: Code duplicated, block: B:16:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:19:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:21:0x010d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:22:0x010e  */
    /* JADX WARN: Code duplicated, block: B:24:0x0116  */
    /* JADX WARN: Code duplicated, block: B:26:0x011f  */
    /* JADX WARN: Code duplicated, block: B:28:0x0132  */
    /* JADX WARN: Code duplicated, block: B:30:0x013c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x010e -> B:23:0x0110). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x011f -> B:27:0x0130). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:11:0x006d
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object invokeSuspend(java.lang.Object r22) {
        /*
            Method dump skipped, instruction units count: 325
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.statusbar.seeding.OplusLiveAlertViewModel$statusBarChips$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
