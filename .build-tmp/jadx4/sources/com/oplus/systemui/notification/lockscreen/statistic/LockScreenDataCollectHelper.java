package com.oplus.systemui.notification.lockscreen.statistic;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.power.shared.model.WakeSleepReason;
import com.android.systemui.power.shared.model.WakefulnessModel;
import com.oplus.systemui.aod.display.AODDisplayUtil;
import com.oplus.systemui.aod.display.BaseDisplayUtil;
import com.oplus.systemui.aod.proxy.AodSettingsValueProxy;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleViewModel;
import com.oplus.systemui.notification.lockscreen.seedling.SeedlingViewModel;
import com.oplus.systemui.statusbar.notification.customcard.OplusCustomRow;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class LockScreenDataCollectHelper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "LockScreenDataCollectHelper";
    private final CapsuleViewModel capsuleViewModel;
    private final Context context;
    private final ILockScreenExtStateController extStateManager;
    private final CoroutineScope mainScope;
    private final PowerInteractor powerInteractor;
    private final Lazy seedlingViewModel;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.statistic.LockScreenDataCollectHelper$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements BaseDisplayUtil.OplusDisplayStateListener {
        public /* synthetic */ LockScreenDataCollectHelper this$0;

        public final void onDisplayChanged(int i) {
            LockScreenDataCollectHelper.access$handleScreenOrAodStateChanged(this.this$0);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.statistic.LockScreenDataCollectHelper$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.statistic.LockScreenDataCollectHelper$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 implements FlowCollector {
            public /* synthetic */ LockScreenDataCollectHelper this$0;

            public final Object emit(Object obj, Continuation continuation) {
                WakefulnessModel wakefulnessModel = (WakefulnessModel) obj;
                LockScreenDataCollectHelper lockScreenDataCollectHelper = this.this$0;
                if (wakefulnessModel.isFinishedWakingUP() && wakefulnessModel.getLastWakeReason() != WakeSleepReason.OTHER) {
                    Context context = lockScreenDataCollectHelper.context;
                    int newState = ((ILockScreenExtStateController.ExtStateEvent) lockScreenDataCollectHelper.extStateManager.getExtStateEvent().getValue()).getNewState();
                    List list = (List) lockScreenDataCollectHelper.capsuleViewModel.getCapsuleData().getValue();
                    Bundle extra = ((ILockScreenExtStateController.ExtStateEvent) lockScreenDataCollectHelper.extStateManager.getExtStateEvent().getValue()).getExtra();
                    LockScreenStatisticUtil.reportCapsuleInfo(context, newState, list, extra != null ? ILockScreenExtStateController.ExtStateEvent.Companion.getImmersiveKey(extra) : null);
                }
                LockScreenDataCollectHelper.access$handleScreenOrAodStateChanged(lockScreenDataCollectHelper);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return LockScreenDataCollectHelper.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow detailedWakefulness = LockScreenDataCollectHelper.this.powerInteractor.getDetailedWakefulness();
                LockScreenDataCollectHelper lockScreenDataCollectHelper = LockScreenDataCollectHelper.this;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                anonymousClass1.this$0 = lockScreenDataCollectHelper;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (detailedWakefulness.collect(anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            Regex$.ExternalSyntheticBUOutline0.m$1();
            return null;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public LockScreenDataCollectHelper(CoroutineScope coroutineScope, Context context, PowerInteractor powerInteractor, CapsuleViewModel capsuleViewModel, ILockScreenExtStateController iLockScreenExtStateController, Lazy lazy) {
        this.mainScope = coroutineScope;
        this.context = context;
        this.powerInteractor = powerInteractor;
        this.capsuleViewModel = capsuleViewModel;
        this.extStateManager = iLockScreenExtStateController;
        this.seedlingViewModel = lazy;
        AODDisplayUtil aODDisplayUtil = AODDisplayUtil.getInstance(context);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        aODDisplayUtil.registerOplusDisplayStateListener(anonymousClass1);
        BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new AnonymousClass2(null), 2);
    }

    public static final void access$handleScreenOrAodStateChanged(LockScreenDataCollectHelper lockScreenDataCollectHelper) {
        lockScreenDataCollectHelper.getClass();
        if (LockScreenStatisticUtil.getRecordCardId() == null) {
            return;
        }
        boolean z = AodSettingsValueProxy.getAodSwitchEnable(lockScreenDataCollectHelper.context) == 1;
        int aodType = AodSettingsValueProxy.getAodType(lockScreenDataCollectHelper.context);
        int deviceDisplayState = AODDisplayUtil.getInstance(lockScreenDataCollectHelper.context).getDeviceDisplayState();
        if (((WakefulnessModel) lockScreenDataCollectHelper.powerInteractor.getDetailedWakefulness().getValue()).isAwake()) {
            if (z && aodType == 2 && (deviceDisplayState == 3 || deviceDisplayState == 4)) {
                int currRecordTimeType = LockScreenStatisticUtil.getCurrRecordTimeType();
                if (currRecordTimeType == 0 || currRecordTimeType == 1) {
                    stopRecordImmerseCountAndTime(true);
                    refreshTimerState(-1);
                    return;
                }
                return;
            }
            int currRecordTimeType2 = LockScreenStatisticUtil.getCurrRecordTimeType();
            if (currRecordTimeType2 != 0) {
                if (currRecordTimeType2 != 1) {
                    refreshTimerState(0);
                    return;
                } else {
                    stopRecordImmerseCountAndTime(true);
                    refreshTimerState(0);
                    return;
                }
            }
            return;
        }
        if (z && aodType == 2 && (deviceDisplayState == 3 || deviceDisplayState == 4)) {
            int currRecordTimeType3 = LockScreenStatisticUtil.getCurrRecordTimeType();
            if (currRecordTimeType3 == 0) {
                stopRecordImmerseCountAndTime(true);
                refreshTimerState(1);
                return;
            } else {
                if (currRecordTimeType3 != 1) {
                    refreshTimerState(1);
                    return;
                }
                return;
            }
        }
        int currRecordTimeType4 = LockScreenStatisticUtil.getCurrRecordTimeType();
        if (currRecordTimeType4 == 0) {
            stopRecordImmerseCountAndTime(true);
            refreshTimerState(-1);
        } else if (currRecordTimeType4 != 1) {
            refreshTimerState(-1);
        } else {
            stopRecordImmerseCountAndTime(true);
            refreshTimerState(-1);
        }
    }

    public static void refreshTimerState(int i) {
        LockScreenStatisticUtil.currRecordTimeType = i;
        LockScreenStatisticUtil.currentImmerseStartTime = SystemClock.elapsedRealtime();
    }

    public static void stopRecordImmerseCountAndTime(boolean z) {
        LockScreenStatisticUtil.recordImmerseTime(LockScreenStatisticUtil.getCurrentImmerseType(), LockScreenStatisticUtil.getCurrRecordTimeType(), LockScreenStatisticUtil.getRecordCardPkg(), LockScreenStatisticUtil.getRecordCardId(), (SystemClock.elapsedRealtime() - LockScreenStatisticUtil.getRecordImmerseStartTime()) / 1000);
        LockScreenStatisticUtil.resetCounters(z);
    }

    public final void collectExtStateAndReport(int i, int i2, int i3) {
        OplusCustomRow oplusCustomRowFindStackView;
        CustomRowEntry entry;
        if (-1 == i3) {
            return;
        }
        Context context = this.context;
        List list = (List) this.capsuleViewModel.getCapsuleData().getValue();
        Bundle extra = ((ILockScreenExtStateController.ExtStateEvent) this.extStateManager.getExtStateEvent().getValue()).getExtra();
        LockScreenStatisticUtil.reportListSystleSwitch(context, i, i2, i3, list, extra != null ? ILockScreenExtStateController.ExtStateEvent.Companion.getImmersiveKey(extra) : null);
        if (i2 != 2) {
            if (i != 2) {
                return;
            } else {
                stopRecordImmerseCountAndTime(false);
            }
        }
        Bundle extra2 = ((ILockScreenExtStateController.ExtStateEvent) this.extStateManager.getExtStateEvent().getValue()).getExtra();
        String immersiveKey = extra2 != null ? ILockScreenExtStateController.ExtStateEvent.Companion.getImmersiveKey(extra2) : null;
        if (immersiveKey != null) {
            LogUtil.e$default(12, TAG, "seedlingCard is error,so stop record", null);
            if (LockScreenStatisticUtil.getRecordCardId() != null) {
                stopRecordImmerseCountAndTime(false);
            }
            LockScreenStatisticUtil.resetCounters(false);
        } else {
            immersiveKey = null;
        }
        if (immersiveKey == null || (oplusCustomRowFindStackView = ((SeedlingViewModel) this.seedlingViewModel.get()).findStackView(immersiveKey)) == null || (entry = oplusCustomRowFindStackView.getEntry()) == null) {
            return;
        }
        if (i == 2) {
            if (immersiveKey.equals(LockScreenStatisticUtil.getRecordCardId()) || LockScreenStatisticUtil.getRecordCardId() == null) {
                immersiveKey = LockScreenStatisticUtil.getRecordCardId();
            } else {
                stopRecordImmerseCountAndTime(false);
            }
        }
        LockScreenStatisticUtil.setRecordCardId(immersiveKey);
        if (LockScreenStatisticUtil.getRecordCardId() == null) {
            LogUtil.e$default(12, TAG, " currentCardId is null,so stop record", null);
            return;
        }
        LockScreenStatisticUtil.setRecordCardPkg(LockScreenStatisticUtil.isMediaSeedlingCard(entry) ? entry.getPackageName() : null);
        if (i3 == 302 || i3 == 305) {
            LockScreenStatisticUtil.setCurrentImmerseType(3);
        } else if (i == 0) {
            LockScreenStatisticUtil.setCurrentImmerseType(1);
        } else if (i != 1) {
            LockScreenStatisticUtil.setCurrentImmerseType(0);
        } else {
            LockScreenStatisticUtil.setCurrentImmerseType(2);
        }
        LockScreenStatisticUtil.incrementCardCount(LockScreenStatisticUtil.getCurrentImmerseType(), LockScreenStatisticUtil.getRecordCardPkg(), LockScreenStatisticUtil.getRecordCardId());
        WakefulnessModel wakefulnessModel = (WakefulnessModel) this.powerInteractor.getDetailedWakefulness().getValue();
        boolean z = AodSettingsValueProxy.getAodSwitchEnable(this.context) == 1;
        int aodType = AodSettingsValueProxy.getAodType(this.context);
        if (wakefulnessModel.isAwake()) {
            if (z && (aodType == 3 || aodType == 4)) {
                refreshTimerState(-1);
                return;
            } else {
                refreshTimerState(0);
                return;
            }
        }
        if (z && (aodType == 3 || aodType == 4)) {
            refreshTimerState(1);
        } else {
            refreshTimerState(-1);
        }
    }
}
