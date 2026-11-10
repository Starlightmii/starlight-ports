package com.oplus.systemui.notification.interruption;

import com.android.systemui.CoreStartable;
import com.android.systemui.InitController;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.oplus.systemui.notification.interruption.gamespace.PeekGameSpaceFilter;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusInterruptionDecisionProvider implements CoreStartable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusInterruption";
    private final InitController initController;
    public PeekEnvelope peekEnvelope;
    public PeekFluidCondition peekFluidCondition;
    public PeekGameSpaceFilter peekGameSpaceFilter;
    public PeekInCallUIFloating peekInCallUIFloating;
    public PeekOplusFilter peekOplusFilter;
    public PeekPanelStateCondition peekPanelStateCondition;
    public PluseOplusFilter pluseOplusFilter;
    private final VisualInterruptionDecisionProvider visualInterruptionDecisionProvider;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.interruption.OplusInterruptionDecisionProvider$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ OplusInterruptionDecisionProvider this$0;

        @Override // java.lang.Runnable
        public final void run() {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, OplusInterruptionDecisionProvider.TAG, "start", null, 8);
            this.this$0.visualInterruptionDecisionProvider.addFilter(this.this$0.getPeekGameSpaceFilter());
            this.this$0.visualInterruptionDecisionProvider.addFilter(this.this$0.getPeekOplusFilter());
            this.this$0.visualInterruptionDecisionProvider.addFilter(this.this$0.getPeekEnvelope());
            this.this$0.visualInterruptionDecisionProvider.addFilter(this.this$0.getPluseOplusFilter());
            this.this$0.visualInterruptionDecisionProvider.addCondition(this.this$0.getPeekPanelStateCondition());
            this.this$0.visualInterruptionDecisionProvider.addCondition(this.this$0.getPeekInCallUIFloating());
            this.this$0.visualInterruptionDecisionProvider.addConditionForFluid(this.this$0.getPeekFluidCondition());
        }
    }

    public OplusInterruptionDecisionProvider(InitController initController, VisualInterruptionDecisionProvider visualInterruptionDecisionProvider) {
        this.initController = initController;
        this.visualInterruptionDecisionProvider = visualInterruptionDecisionProvider;
    }

    public final PeekEnvelope getPeekEnvelope() {
        PeekEnvelope peekEnvelope = this.peekEnvelope;
        if (peekEnvelope != null) {
            return peekEnvelope;
        }
        return null;
    }

    public final PeekFluidCondition getPeekFluidCondition() {
        PeekFluidCondition peekFluidCondition = this.peekFluidCondition;
        if (peekFluidCondition != null) {
            return peekFluidCondition;
        }
        return null;
    }

    public final PeekGameSpaceFilter getPeekGameSpaceFilter() {
        PeekGameSpaceFilter peekGameSpaceFilter = this.peekGameSpaceFilter;
        if (peekGameSpaceFilter != null) {
            return peekGameSpaceFilter;
        }
        return null;
    }

    public final PeekInCallUIFloating getPeekInCallUIFloating() {
        PeekInCallUIFloating peekInCallUIFloating = this.peekInCallUIFloating;
        if (peekInCallUIFloating != null) {
            return peekInCallUIFloating;
        }
        return null;
    }

    public final PeekOplusFilter getPeekOplusFilter() {
        PeekOplusFilter peekOplusFilter = this.peekOplusFilter;
        if (peekOplusFilter != null) {
            return peekOplusFilter;
        }
        return null;
    }

    public final PeekPanelStateCondition getPeekPanelStateCondition() {
        PeekPanelStateCondition peekPanelStateCondition = this.peekPanelStateCondition;
        if (peekPanelStateCondition != null) {
            return peekPanelStateCondition;
        }
        return null;
    }

    public final PluseOplusFilter getPluseOplusFilter() {
        PluseOplusFilter pluseOplusFilter = this.pluseOplusFilter;
        if (pluseOplusFilter != null) {
            return pluseOplusFilter;
        }
        return null;
    }

    public final void setPeekEnvelope(PeekEnvelope peekEnvelope) {
        this.peekEnvelope = peekEnvelope;
    }

    public final void setPeekFluidCondition(PeekFluidCondition peekFluidCondition) {
        this.peekFluidCondition = peekFluidCondition;
    }

    public final void setPeekGameSpaceFilter(PeekGameSpaceFilter peekGameSpaceFilter) {
        this.peekGameSpaceFilter = peekGameSpaceFilter;
    }

    public final void setPeekInCallUIFloating(PeekInCallUIFloating peekInCallUIFloating) {
        this.peekInCallUIFloating = peekInCallUIFloating;
    }

    public final void setPeekOplusFilter(PeekOplusFilter peekOplusFilter) {
        this.peekOplusFilter = peekOplusFilter;
    }

    public final void setPeekPanelStateCondition(PeekPanelStateCondition peekPanelStateCondition) {
        this.peekPanelStateCondition = peekPanelStateCondition;
    }

    public final void setPluseOplusFilter(PluseOplusFilter pluseOplusFilter) {
        this.pluseOplusFilter = pluseOplusFilter;
    }

    public final void start() {
        InitController initController = this.initController;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        initController.addPostInitTask(anonymousClass1);
    }
}
