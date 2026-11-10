package com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.car.app.hardware.common.CarZone$;
import androidx.car.app.serialization.Bundler$;
import androidx.compose.animation.ChangeSize$;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.coroutines.TracingKt;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.notification.lockscreen.capsule.CapsuleItemView;
import com.oplus.systemui.notification.lockscreen.capsule.modle.CapsuleItemData;
import com.oplus.systemui.notification.lockscreen.capsule.utils.CapsuleViewExpandKtKt;
import com.oplus.systemui.notification.lockscreen.config.CapsuleConfig;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyKt__LazyJVMKt$;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleDataPinHandler extends CapsuleDataBaseHandler {
    private static final String TAG = "CapsuleDataPinHandler";
    private static final Lazy mainScope$delegate;
    private static CapsulePinInfo pinInfo;
    private static Job remindTimerSetupJob;
    private static final Lazy timeOutHandler$delegate;
    public static final CapsuleDataPinHandler INSTANCE = new CapsuleDataPinHandler();
    private static final AtomicBoolean capsuleScreenOnHolder = new AtomicBoolean(false);
    private static final AtomicBoolean keyguardShown = new AtomicBoolean(false);
    private static final AtomicBoolean needKeepAodDisplayDoze = new AtomicBoolean(false);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsulePinInfo {
        public int enterAodCount;
        public boolean isExtension;
        private final Lazy key$delegate;
        private final CapsuleItemView pinCard;
        public boolean pinFrmScreenON;

        public CapsulePinInfo(CapsuleItemView capsuleItemView, boolean z, boolean z2, int i) {
            this.pinCard = capsuleItemView;
            this.pinFrmScreenON = z;
            this.isExtension = z2;
            this.enterAodCount = i;
            CapsuleDataPinHandler$CapsulePinInfo$$ExternalSyntheticLambda0 capsuleDataPinHandler$CapsulePinInfo$$ExternalSyntheticLambda0 = new CapsuleDataPinHandler$CapsulePinInfo$$ExternalSyntheticLambda0();
            capsuleDataPinHandler$CapsulePinInfo$$ExternalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            this.key$delegate = LazyKt__LazyJVMKt.lazy(capsuleDataPinHandler$CapsulePinInfo$$ExternalSyntheticLambda0);
        }

        public final CapsuleItemView component1() {
            return this.pinCard;
        }

        public final CapsulePinInfo copy(CapsuleItemView capsuleItemView, boolean z, boolean z2, int i) {
            return new CapsulePinInfo(capsuleItemView, z, z2, i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CapsulePinInfo)) {
                return false;
            }
            CapsulePinInfo capsulePinInfo = (CapsulePinInfo) obj;
            return Intrinsics.areEqual(this.pinCard, capsulePinInfo.pinCard) && this.pinFrmScreenON == capsulePinInfo.pinFrmScreenON && this.isExtension == capsulePinInfo.isExtension && this.enterAodCount == capsulePinInfo.enterAodCount;
        }

        public final int getEnterAodCount() {
            return this.enterAodCount;
        }

        public final String getKey() {
            return (String) this.key$delegate.getValue();
        }

        public final CapsuleItemView getPinCard() {
            return this.pinCard;
        }

        public final long getPinDuration() {
            boolean z = this.isExtension;
            CapsuleItemData entry = this.pinCard.getEntry();
            return z ? entry.getPinExtensionDuration() : entry.getPinDuration();
        }

        public final boolean getPinFrmScreenON() {
            return this.pinFrmScreenON;
        }

        public final int hashCode() {
            return Integer.hashCode(this.enterAodCount) + ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(this.pinCard.hashCode() * 31, 31, this.pinFrmScreenON), 31, this.isExtension);
        }

        public final void setEnterAodCount(int i) {
            this.enterAodCount = i;
        }

        public final void setExtension(boolean z) {
            this.isExtension = z;
        }

        public final void setPinFrmScreenON(boolean z) {
            this.pinFrmScreenON = z;
        }

        public String toString() {
            return "CapsulePinInfo(pinCard=" + this.pinCard + ", pinFrmScreenON=" + this.pinFrmScreenON + ", isExtension=" + this.isExtension + ", enterAodCount=" + this.enterAodCount + ")";
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleTimeoutHandler extends Handler {
        public CapsuleTimeoutHandler(Looper looper) {
            super(looper);
        }

        public final void cancelTimeout(CapsuleTimer capsuleTimer, CapsulePinInfo capsulePinInfo, String str) {
            if (hasMessages(capsuleTimer.ordinal())) {
                capsuleTimer.traceTimerState(capsulePinInfo, "CANCEL", str);
                removeMessages(capsuleTimer.ordinal());
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            CapsuleTimer capsuleTimer;
            CapsuleTimer[] capsuleTimerArrValues = CapsuleTimer.values();
            int length = capsuleTimerArrValues.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    capsuleTimer = null;
                    break;
                }
                capsuleTimer = capsuleTimerArrValues[i];
                if (capsuleTimer.ordinal() == message.what) {
                    break;
                } else {
                    i++;
                }
            }
            if (capsuleTimer == null) {
                return;
            }
            Object obj = message.obj;
            capsuleTimer.traceTimerState(obj instanceof CapsulePinInfo ? (CapsulePinInfo) obj : null, "FINISHED", null);
            Object obj2 = message.obj;
            capsuleTimer.onTimeout(obj2 instanceof CapsulePinInfo ? (CapsulePinInfo) obj2 : null);
        }

        public final void setupTimeout(CapsuleTimer capsuleTimer, CapsulePinInfo capsulePinInfo, String str) {
            long pinDuration = capsulePinInfo != null ? capsulePinInfo.getPinDuration() : 3000L;
            capsuleTimer.traceTimerState(capsulePinInfo, "START", str);
            removeMessages(capsuleTimer.ordinal());
            sendMessageDelayed(obtainMessage(capsuleTimer.ordinal(), capsulePinInfo), pinDuration);
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataPinHandler$CapsuleTimer[], still in use, count: 1, list:
      (r0v1 com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataPinHandler$CapsuleTimer[]) from 0x002a: INVOKE (r0v1 com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataPinHandler$CapsuleTimer[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[WRAPPED]
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
    	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class CapsuleTimer {
        AGGREGATE("CapsuleTimer::Aggregate"),
        PIN("CapsuleTimer::Pin"),
        REMIND("CapsuleTimer::Remind");

        public static final /* synthetic */ EnumEntries $ENTRIES;
        private final String timerName;

        static {
            $ENTRIES = EnumEntriesKt.enumEntries(capsuleTimerArr);
        }

        public CapsuleTimer(String str) {
            super(str, i);
            this.timerName = str;
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static CapsuleTimer valueOf(String str) {
            return (CapsuleTimer) Enum.valueOf(CapsuleTimer.class, str);
        }

        public static CapsuleTimer[] values() {
            return (CapsuleTimer[]) $VALUES.clone();
        }

        public final void cancelTimeout(String str) {
            CapsuleDataPinHandler.INSTANCE.getTimeOutHandler().cancelTimeout(this, null, str);
        }

        public final String getTimerName() {
            return this.timerName;
        }

        public final void onTimeout(CapsulePinInfo capsulePinInfo) {
            int iOrdinal = ordinal();
            if (iOrdinal != 0) {
                if (iOrdinal == 1) {
                    if (capsulePinInfo != null) {
                        CapsuleDataPinHandler.access$onPinTimeout(CapsuleDataPinHandler.INSTANCE, capsulePinInfo);
                        return;
                    }
                    return;
                } else if (iOrdinal == 2) {
                    CapsuleDataPinHandler.INSTANCE.switchToRemindCard("RemindTimeout");
                    return;
                } else {
                    LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                    return;
                }
            }
            if (capsulePinInfo != null) {
                CapsuleDataPinHandler capsuleDataPinHandler = CapsuleDataPinHandler.INSTANCE;
                CapsuleItemView baseCard = capsuleDataPinHandler.getAmbientState().getBaseCard();
                CapsuleItemView pinCard = capsulePinInfo.getPinCard();
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("onPinAggregateTimeout.", pinCard.getKey(), ", baseCard:", baseCard != null ? baseCard.getKey() : null, "CapsuleDataPinHandler-->"));
                }
                if (CapsuleViewExpandKtKt.isNotifCard(baseCard)) {
                    capsuleDataPinHandler.startNotifAggregate(true, "onPinAggregateTimeout::BaseCard");
                } else if (CapsuleViewExpandKtKt.findNotifCard(capsuleDataPinHandler.getCapsuleContainer()) != null) {
                    capsuleDataPinHandler.startNotifAggregate(false, "onPinAggregateTimeout::NotBaseCard");
                }
            }
        }

        public final void setupTimeout(CapsulePinInfo capsulePinInfo, String str) {
            if (this == REMIND || capsulePinInfo != null) {
                CapsuleDataPinHandler.INSTANCE.getTimeOutHandler().setupTimeout(this, capsulePinInfo, str);
            }
        }

        public final void traceTimerState(CapsulePinInfo capsulePinInfo, String str, String str2) {
            String str3 = CapsuleDataPinHandler.capsuleScreenOnHolder.get() ? "SCREEN_ON" : "SCREEN_OFF";
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                String str4 = this.timerName;
                String str5 = "";
                String strM = str2 == null ? "" : Bundler$.ExternalSyntheticOutline0.m("[reason:", str2, "]");
                if (capsulePinInfo != null) {
                    str5 = "[" + capsulePinInfo + "]";
                }
                Log.d("SystemUi--Notification", "CapsuleDataPinHandler-->".concat(str4 + "[" + str + "][" + str3 + "]" + strM + str5));
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataPinHandler$autoScrollToNext$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public /* synthetic */ CapsuleDataOrderHandler.CapsuleNextModel $nextModel;

        @Override // java.lang.Runnable
        public final void run() {
            CapsuleDataPinHandler.INSTANCE.getCapsuleContainer().getScroller().horizontalScrollTo(this.$nextModel, CapsuleConfig.INSTANCE.getItemWidthMultiCard());
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataPinHandler$onScrollFinished$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ boolean $fromUser;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$fromUser = z;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$fromUser, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DelayKt.delay(10L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            CapsuleDataPinHandler capsuleDataPinHandler = CapsuleDataPinHandler.INSTANCE;
            CapsuleItemView baseCard = capsuleDataPinHandler.getAmbientState().getBaseCard();
            boolean z = this.$fromUser;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleDataPinHandler-->".concat("onScrollFinished[fromUser=" + z + "], " + capsuleDataPinHandler.getCurBaseCard()));
            }
            CapsuleDataOrderHandler.CapsuleNextCardInfo remindCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic = CapsuleDataOrderHandler.INSTANCE.getRemindCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic();
            if (!remindCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic.valid() || Intrinsics.areEqual(remindCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic.getNextCard(), baseCard)) {
                CapsuleTimer.REMIND.cancelTimeout("onScrollFinishedFromUser");
            } else {
                CapsuleTimer.REMIND.setupTimeout(null, "onScrollFinishedFromUser");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataPinHandler$startNotifAggregate$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC01872 implements Runnable {
        public /* synthetic */ boolean $needAnim;

        @Override // java.lang.Runnable
        public final void run() {
            CapsuleDataPinHandler.INSTANCE.getNotifDataController().showAggregate(this.$needAnim);
        }
    }

    static {
        final int i = 0;
        timeOutHandler$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataPinHandler$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        return new CapsuleDataPinHandler.CapsuleTimeoutHandler(Looper.getMainLooper());
                    default:
                        return CoroutineScopeKt.CoroutineScope(Dispatchers.getMain().plus(TracingKt.newTracingContext("CapsuleDataPinHandler")));
                }
            }
        });
        final int i2 = 1;
        mainScope$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.notification.lockscreen.capsule.modle.datahandler.CapsuleDataPinHandler$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        return new CapsuleDataPinHandler.CapsuleTimeoutHandler(Looper.getMainLooper());
                    default:
                        return CoroutineScopeKt.CoroutineScope(Dispatchers.getMain().plus(TracingKt.newTracingContext("CapsuleDataPinHandler")));
                }
            }
        });
    }

    public static final void access$onPinTimeout(CapsuleDataPinHandler capsuleDataPinHandler, CapsulePinInfo capsulePinInfo) {
        capsuleDataPinHandler.getClass();
        CapsulePinInfo capsulePinInfo2 = pinInfo;
        if (capsulePinInfo2 != null) {
            if (!capsulePinInfo2.equals(capsulePinInfo)) {
                capsulePinInfo2 = null;
            }
            if (capsulePinInfo2 == null) {
                return;
            }
            CapsuleItemView baseCard = capsuleDataPinHandler.getAmbientState().getBaseCard();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleDataPinHandler-->".concat("onPinTimeout, timeoutPinInfo:" + capsulePinInfo + ", baseCard:" + baseCard + ", curPinInfo:" + capsulePinInfo2));
            }
            CapsuleTimer.AGGREGATE.cancelTimeout("onPinTimeout");
            if (capsulePinInfo.getPinFrmScreenON() || !keyguardShown.get() || capsuleScreenOnHolder.get() || capsulePinInfo2.isExtension) {
                cancelPinCardInfo("onPinTimeout");
            } else {
                capsulePinInfo2.setExtension(true);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "CapsuleDataPinHandler-->onPinTimeout.-->ScreenOff and not clear PinInfo!!!");
                }
            }
            if (Intrinsics.areEqual(capsulePinInfo.getPinCard(), baseCard)) {
                CapsuleDataOrderHandler.CapsuleNextCardInfo headupBackCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic = CapsuleDataOrderHandler.INSTANCE.getHeadupBackCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(baseCard);
                if (headupBackCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic.valid() && !Intrinsics.areEqual(headupBackCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic.getNextCard(), baseCard)) {
                    CapsuleTimer.REMIND.cancelTimeout("onPinTimeout->pinBackToNext:" + headupBackCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic);
                    autoScrollToNext(CapsuleDataOrderHandler.CapsuleNextModel.PIN_BACK);
                } else if (CapsuleViewExpandKtKt.isNotifCard(baseCard)) {
                    capsuleDataPinHandler.startNotifAggregate(true, "onPinTimeoutNoNextCard");
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "CapsuleDataPinHandler-->".concat("onPinTimeout. nextCardInfo:" + headupBackCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic));
                }
            }
        }
    }

    public static void autoScrollToNext(CapsuleDataOrderHandler.CapsuleNextModel capsuleNextModel) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleDataPinHandler-->".concat("autoScrollToNext: " + capsuleNextModel));
        }
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.$nextModel = capsuleNextModel;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(anonymousClass2);
    }

    public static void cancelPinCardInfo(String str) {
        if (pinInfo != null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("cancelPinCardInfo[", str, "]", "CapsuleDataPinHandler-->", "SystemUi--Notification");
            }
            pinInfo = null;
        }
    }

    public static void cancelRemindTimerSetupJob() {
        Unit unit;
        try {
            Job job = remindTimerSetupJob;
            if (job != null) {
                Job.Key key = Job.Key;
                job.cancel((CancellationException) null);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Result.constructor-impl(unit);
        } catch (Throwable th) {
            Result.constructor-impl(ResultKt.createFailure(th));
        }
        remindTimerSetupJob = null;
    }

    private final CoroutineScope getMainScope() {
        return (CoroutineScope) mainScope$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CapsuleTimeoutHandler getTimeOutHandler() {
        return (CapsuleTimeoutHandler) timeOutHandler$delegate.getValue();
    }

    public static void onScrollStartByUser() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            CapsulePinInfo capsulePinInfo = pinInfo;
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onScrollStart[fromUser=true]. pinCard=", capsulePinInfo != null ? capsulePinInfo.getKey() : null, "CapsuleDataPinHandler-->", "SystemUi--Notification");
        }
        CapsuleTimer.PIN.cancelTimeout("onScrollStartByUser");
        CapsuleTimer.REMIND.cancelTimeout("onScrollStartByUser");
        cancelRemindTimerSetupJob();
    }

    public final void cancelAllTimer(String str) {
        cancelRemindTimerSetupJob();
        for (CapsuleTimer capsuleTimer : CapsuleTimer.values()) {
            INSTANCE.getTimeOutHandler().cancelTimeout(capsuleTimer, null, str);
        }
    }

    public final void checkIfRemovePinCard(CapsuleItemView capsuleItemView) {
        if (capsuleItemView == null) {
            return;
        }
        CapsuleItemView baseCard = getAmbientState().getBaseCard();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleDataPinHandler-->".concat("checkIfRemovePinCard, removeCard:" + capsuleItemView + ", pinCard:" + pinInfo + ", baseCard:" + baseCard));
        }
        CapsulePinInfo capsulePinInfo = pinInfo;
        if (Intrinsics.areEqual(capsulePinInfo != null ? capsulePinInfo.getPinCard() : null, capsuleItemView)) {
            cancelPinCardInfo("PinCardRemoved");
            cancelAllTimer("PinCardRemoved");
        }
    }

    public final AtomicBoolean getNeedKeepAodDisplayDoze() {
        return needKeepAodDisplayDoze;
    }

    public final void initCapsuleScreenState(Boolean bool, boolean z) {
        keyguardShown.set(z);
        capsuleScreenOnHolder.set(Intrinsics.areEqual(bool, Boolean.TRUE));
    }

    public final void onBaseCardChange() {
        CapsuleItemView baseCard = getAmbientState().getBaseCard();
        if (baseCard == null) {
            return;
        }
        CapsuleItemView capsuleItemViewFindNotifCard = CapsuleViewExpandKtKt.findNotifCard(getCapsuleContainer());
        if (CapsuleViewExpandKtKt.isSeedlingCard(baseCard) && CapsuleViewExpandKtKt.isNotifCard(capsuleItemViewFindNotifCard)) {
            BuildersKt.launch$default(getMainScope(), (CoroutineContext) null, (CoroutineStart) null, new CapsuleDataPinHandler$checkIfNeedAggregateNotif$1(capsuleItemViewFindNotifCard, null), 3);
        }
    }

    public final void onCapsuleScreenChanged(Boolean bool, boolean z) {
        keyguardShown.set(z);
        if (bool != null) {
            if (!bool.booleanValue() || !capsuleScreenOnHolder.compareAndSet(false, true)) {
                if (bool.booleanValue() || !capsuleScreenOnHolder.compareAndSet(true, false)) {
                    return;
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    CapsulePinInfo capsulePinInfo = pinInfo;
                    OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("onCapsuleScreenOff. pinCard:", capsulePinInfo != null ? capsulePinInfo.getKey() : null, "  doNothing...", "CapsuleDataPinHandler-->", "SystemUi--Notification");
                }
                CapsulePinInfo capsulePinInfo2 = pinInfo;
                if (capsulePinInfo2 != null) {
                    capsulePinInfo2.setEnterAodCount(capsulePinInfo2.getEnterAodCount() + 1);
                    return;
                }
                return;
            }
            CapsulePinInfo capsulePinInfo3 = pinInfo;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleDataPinHandler-->".concat("onCapsuleScreenOn. pinInfo:" + capsulePinInfo3));
            }
            CapsuleTimer.REMIND.cancelTimeout("onCapsuleScreenOn");
            if (capsulePinInfo3 == null || !getAlgorithmState().getVisibleChildren().contains(capsulePinInfo3.getPinCard())) {
                cancelPinCardInfo("onCapsuleScreenOnNoPinCard");
                CapsuleTimer.AGGREGATE.cancelTimeout("onCapsuleScreenOn");
                CapsuleTimer.PIN.cancelTimeout("onCapsuleScreenOn");
                return;
            }
            if (capsulePinInfo3.getPinFrmScreenON() || capsulePinInfo3.getEnterAodCount() > 1) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    CapsulePinInfo capsulePinInfo4 = pinInfo;
                    OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("onCapsuleScreenON. pinCard:", capsulePinInfo4 != null ? capsulePinInfo4.getKey() : null, "  doNothing...", "CapsuleDataPinHandler-->", "SystemUi--Notification");
                    return;
                }
                return;
            }
            if (!capsulePinInfo3.isExtension) {
                CapsuleTimer.PIN.setupTimeout(capsulePinInfo3, "ExtensionPin");
                CapsuleTimer.AGGREGATE.setupTimeout(capsulePinInfo3, "ExtensionPin");
            } else {
                if (Intrinsics.areEqual(capsulePinInfo3.getPinCard(), getCurBaseCard())) {
                    return;
                }
                cancelAllTimer("ExtensionPinWhenScreenOn");
                getAnimHelper().onViewAddedInternal(capsulePinInfo3.getPinCard(), getCapsuleContainer());
            }
        }
    }

    public final void onScrollFinished(boolean z) {
        if (z) {
            cancelRemindTimerSetupJob();
            remindTimerSetupJob = BuildersKt.launch$default(getMainScope(), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(z, null), 3);
        }
    }

    public final void setupPinCard(CapsuleItemView capsuleItemView, String str) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleDataPinHandler-->".concat(CarZone$.ExternalSyntheticOutline0.m("setupPinCard[", str, "]:", capsuleItemView.getKey(), ".")));
        }
        CapsulePinInfo capsulePinInfo = pinInfo;
        if (Intrinsics.areEqual(capsulePinInfo != null ? capsulePinInfo.getPinCard() : null, capsuleItemView)) {
            return;
        }
        pinInfo = new CapsulePinInfo(capsuleItemView, capsuleScreenOnHolder.get(), false, 0);
    }

    public final void startNotifAggregate(boolean z, String str) {
        CapsuleTimer.AGGREGATE.cancelTimeout(str);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", KeyguardSettingProvider$.ExternalSyntheticOutline0.m("startNotifAggregate[", str, "], needAnim=", z, "CapsuleDataPinHandler-->"));
        }
        RunnableC01872 runnableC01872 = new RunnableC01872();
        runnableC01872.$needAnim = z;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(runnableC01872);
    }

    public final void startPinTimeout() {
        CapsulePinInfo capsulePinInfo = pinInfo;
        cancelAllTimer("setupPinTimer—" + (capsulePinInfo != null ? capsulePinInfo.getKey() : null));
        CapsulePinInfo capsulePinInfo2 = pinInfo;
        if (capsulePinInfo2 == null) {
            return;
        }
        CapsuleTimer.PIN.setupTimeout(capsulePinInfo2, "NewCard");
        if (CapsuleViewExpandKtKt.isNotifCard(capsulePinInfo2.getPinCard())) {
            CapsuleTimer.AGGREGATE.setupTimeout(capsulePinInfo2, "NewCard");
        }
    }

    public final void switchToRemindCard(String str) {
        CapsuleItemView baseCard = getAmbientState().getBaseCard();
        if (baseCard == null) {
            return;
        }
        CapsuleDataOrderHandler.CapsuleNextCardInfo remindCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic = CapsuleDataOrderHandler.INSTANCE.getRemindCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic();
        if (!remindCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic.valid() || Intrinsics.areEqual(remindCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic.getNextCard(), baseCard)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleDataPinHandler-->".concat("switchToRemindCard[reason:" + str + "]. NotChange!!! nextCard:" + remindCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic));
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleDataPinHandler-->".concat("switchToRemindCard[reason:" + str + "]. baseCard:" + baseCard + " --> nextCard:" + remindCardInfo$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic.getNextCard()));
        }
        autoScrollToNext(CapsuleDataOrderHandler.CapsuleNextModel.REMIND);
    }
}
