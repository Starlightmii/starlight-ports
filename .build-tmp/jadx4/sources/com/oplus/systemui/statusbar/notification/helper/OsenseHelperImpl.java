package com.oplus.systemui.statusbar.notification.helper;

import android.content.Context;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import androidx.appcompat.widget.ListPopupWindow$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.statusbar.notification.helper.OsenseHelper;
import com.oplus.osense.OsenseResClient;
import com.oplus.osense.info.OsenseSaRequest;
import com.oplus.uah.UAHResClient;
import com.oplus.uah.info.UAHEventRequest;
import com.oplus.uah.info.UAHRuleCtrlRequest;
import com.oplusos.systemui.common.concurrency.OplusUiOffloadThread;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OsenseHelperImpl implements OsenseHelper {
    private static final String ACTION_BLUR = "blur";
    private static final String ACTION_FLING = "fling";
    private static final String ACTION_SHOW_QS_DETAIL_DIALOG = "show_qs_detail_dialog";
    private static final String ACTION_TOUCH = "touch";
    public static final Companion Companion = new Companion();
    private static final String OSENSE_ACTION_SYSTEM_UI_SWIPE = "OSENSE_ACTION_SYSTEM_UI_SWIPE";
    private static final String OSENSE_ACTION_UNLOCK_BOOST = "OSENSE_ACTION_UNLOCK_BOOST";
    private static final String SCENE_NAME = "systemui_ripple";
    private static final String TAG = "OsenseHelper";
    private final Context context;
    public int mAODSmoothTransitionAnimBoostHandle;
    private String mBoomAction = ACTION_BLUR;
    public int mLastEventAction = -1;
    public final int mMinGaussValve;
    private OsenseRecord mOsenseRecord;
    private OsenseResClient mOsenseResClient;
    public int mPenetrateRateLimitHandle;
    public int mQSDialogAnimBoost;
    public int mTimeOut;
    private UAHResClient mUAHResClient;
    public int qsScrollBoostHandle;
    public int unlockAnimBoostHandle;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OsenseRecord {
        public static final Companion Companion = new Companion();
        public long mLastCancelSignal;
        public long mLastRecordSignal;
        public long mSignal;
        public long mTimeStamp;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
        }

        public static String encryptionSignalForLogger(long j) {
            StringBuilder sb = new StringBuilder();
            if (j <= 0) {
                return String.valueOf(j);
            }
            char[] charArray = String.valueOf(j).toCharArray();
            int length = charArray.length;
            for (int i = 0; i < length; i++) {
                if (i % 4 == 0) {
                    sb.append(charArray[i]);
                }
            }
            return sb.toString();
        }

        public final String getLastCancelSignalEncry() {
            return encryptionSignalForLogger(this.mLastCancelSignal);
        }

        public final String getLastRecordSignalEncry() {
            return encryptionSignalForLogger(this.mLastRecordSignal);
        }

        public final long getMLastCancelSignal() {
            return this.mLastCancelSignal;
        }

        public final long getMLastRecordSignal() {
            return this.mLastRecordSignal;
        }

        public final long getMSignal() {
            return this.mSignal;
        }

        public final long getMTimeStamp() {
            return this.mTimeStamp;
        }

        public final String getSignalEncry() {
            return encryptionSignalForLogger(this.mSignal);
        }

        public final void setMLastCancelSignal(long j) {
            this.mLastCancelSignal = j;
        }

        public final void setMLastRecordSignal(long j) {
            this.mLastRecordSignal = j;
        }

        public final void setMSignal(long j) {
            this.mSignal = j;
        }

        public final void setMTimeStamp(long j) {
            this.mTimeStamp = j;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.notification.helper.OsenseHelperImpl$autoGPUBoomForQs$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OsenseHelperImpl.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            OsenseHelperImpl osenseHelperImpl = OsenseHelperImpl.this;
            osenseHelperImpl.getClass();
            OsenseHelperImpl.access$autoGPUBoom(osenseHelperImpl, OsenseHelperImpl.ACTION_FLING);
            return Unit.INSTANCE;
        }
    }

    public OsenseHelperImpl(Context context) {
        this.context = context;
        OsenseRecord osenseRecord = new OsenseRecord();
        osenseRecord.mSignal = -1L;
        osenseRecord.mLastCancelSignal = -1L;
        osenseRecord.mLastRecordSignal = -1L;
        osenseRecord.mTimeStamp = 0L;
        this.mOsenseRecord = osenseRecord;
        this.mOsenseResClient = OsenseResClient.get(OsenseHelperImpl.class);
        this.mUAHResClient = UAHResClient.get(OsenseHelperImpl.class);
        this.mMinGaussValve = context.getResources().getDimensionPixelOffset(2131170215);
        context.getResources().getDimensionPixelSize(2131170115);
    }

    public static final void access$autoGPUBoom(OsenseHelperImpl osenseHelperImpl, String str) {
        int i;
        synchronized (osenseHelperImpl.mOsenseRecord) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis > osenseHelperImpl.mOsenseRecord.getMTimeStamp() && osenseHelperImpl.mOsenseRecord.getMSignal() != -1) {
                    osenseHelperImpl.endGPUAcceleration("timeout");
                }
                if (!TextUtils.equals(str, osenseHelperImpl.mBoomAction) && osenseHelperImpl.mOsenseRecord.getMSignal() != -1) {
                    long mTimeStamp = osenseHelperImpl.mOsenseRecord.getMTimeStamp() - jCurrentTimeMillis;
                    if (str.equals(ACTION_TOUCH)) {
                        i = 500;
                    } else {
                        str.equals(ACTION_FLING);
                        i = 3000;
                    }
                    long j = mTimeStamp - ((long) i);
                    if (j <= 0 && (j >= 0 || Math.abs(j) >= 24)) {
                        osenseHelperImpl.endGPUAcceleration("force restart");
                    }
                    return;
                }
                TextUtils.equals(str, ACTION_BLUR);
                osenseHelperImpl.mBoomAction = str;
                if (!TextUtils.equals(str, ACTION_BLUR) || osenseHelperImpl.mMinGaussValve <= 0) {
                    if (TextUtils.equals(str, ACTION_BLUR)) {
                        str = "pullDown-blur";
                    }
                    if (osenseHelperImpl.mOsenseRecord.getMSignal() == -1) {
                        osenseHelperImpl.startGPUAcceleration(str);
                    }
                } else if (osenseHelperImpl.mOsenseRecord.getMSignal() != -1) {
                    osenseHelperImpl.endGPUAcceleration("gauss less than 1");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void autoGPUBoomForQs() {
        BuildersKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), (CoroutineStart) null, new AnonymousClass1(null), 2);
    }

    public final void endGPUAcceleration(String str) {
        if (this.mOsenseResClient == null || this.mOsenseRecord.getMSignal() == -1) {
            return;
        }
        Trace.traceBegin(8L, "endGPUAcceleration:".concat(str));
        OsenseResClient osenseResClient = this.mOsenseResClient;
        if (osenseResClient != null) {
            osenseResClient.osenseClrSceneAction(this.mOsenseRecord.getMSignal());
        }
        Trace.traceEnd(8L);
        OsenseRecord osenseRecord = this.mOsenseRecord;
        osenseRecord.setMLastCancelSignal(osenseRecord.getMSignal());
        this.mOsenseRecord.setMSignal(-1L);
        this.mOsenseRecord.setMTimeStamp(System.currentTimeMillis());
    }

    public void gpuBoomForTouchEvent(MotionEvent motionEvent) {
        this.mLastEventAction = motionEvent.getAction();
        if (motionEvent.getAction() == 0 || motionEvent.getAction() == 2) {
            if (this.mLastEventAction == 2) {
                return;
            }
            OplusUiOffloadThread companion = OplusUiOffloadThread.Companion.getInstance();
            OsenseHelperImpl$autoGPUBoom$2 osenseHelperImpl$autoGPUBoom$2 = new OsenseHelperImpl$autoGPUBoom$2();
            osenseHelperImpl$autoGPUBoom$2.this$0 = this;
            osenseHelperImpl$autoGPUBoom$2.$action = ACTION_TOUCH;
            VarHandle.storeStoreFence();
            companion.runOnSubThread(osenseHelperImpl$autoGPUBoom$2);
            return;
        }
        if (motionEvent.getAction() != 1 || this.mLastEventAction == 2) {
            return;
        }
        OplusUiOffloadThread companion2 = OplusUiOffloadThread.Companion.getInstance();
        OsenseHelperImpl$autoGPUBoom$2 osenseHelperImpl$autoGPUBoom$3 = new OsenseHelperImpl$autoGPUBoom$2();
        osenseHelperImpl$autoGPUBoom$3.this$0 = this;
        osenseHelperImpl$autoGPUBoom$3.$action = ACTION_FLING;
        VarHandle.storeStoreFence();
        companion2.runOnSubThread(osenseHelperImpl$autoGPUBoom$3);
    }

    public final void notifyScreenEvent(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("notifyScreenEvent, isScreenOn:", "OsenseHelper-->", "SystemUi--Keyguard", z);
        }
        UAHRuleCtrlRequest uAHRuleCtrlRequest = new UAHRuleCtrlRequest(1, z ? 1 : 0, (ArrayList) null);
        UAHResClient uAHResClient = this.mUAHResClient;
        if (uAHResClient != null) {
            uAHResClient.ctrlRule(uAHRuleCtrlRequest);
        }
    }

    public final void releaseAODSmoothTransitionAnimBoost() {
        int i = this.mAODSmoothTransitionAnimBoostHandle;
        if (i <= 0) {
            LogUtil.i$default(TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "invalid release handle: "), null, null, 12);
            return;
        }
        UAHResClient uAHResClient = this.mUAHResClient;
        if (uAHResClient != null) {
            uAHResClient.release(i);
        }
        this.mAODSmoothTransitionAnimBoostHandle = 0;
    }

    public final void releaseQsDialogAnimBoost() {
        int i = this.mQSDialogAnimBoost;
        if (i <= 0) {
            LogUtil.i$default(TAG, ListPopupWindow$.ExternalSyntheticOutline0.m(i, "invalid release handle: "), null, null, 12);
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.mQSDialogAnimBoost, "releaseQsDialogAnimBoost handle:", "OsenseHelper-->", "SystemUi--Keyguard");
        }
        UAHResClient uAHResClient = this.mUAHResClient;
        if (uAHResClient != null) {
            uAHResClient.release(this.mQSDialogAnimBoost);
        }
        this.mQSDialogAnimBoost = 0;
    }

    public final void requestAODSmoothTransitionAnimBoost() {
        UAHResClient uAHResClient = this.mUAHResClient;
        this.mAODSmoothTransitionAnimBoostHandle = uAHResClient != null ? uAHResClient.acquireEvent(new UAHEventRequest(445, SCENE_NAME, 600, (ArrayList) null)) : 0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.mAODSmoothTransitionAnimBoostHandle, "requestAODSmoothTransitionAnimBoost handle:", "OsenseHelper-->", "SystemUi--Keyguard");
        }
    }

    public final void requestClockAnimBoost() {
        UAHResClient uAHResClient = this.mUAHResClient;
        int iAcquireEvent = uAHResClient != null ? uAHResClient.acquireEvent(new UAHEventRequest(445, SCENE_NAME, 800, (ArrayList) null)) : 0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(iAcquireEvent, "requestClockAnimBoost result:", "OsenseHelper-->", "SystemUi--Keyguard");
        }
    }

    public final void requestNoneLocalHbmPanoramicAodBoost(int i) {
        if (i <= 0) {
            OplusLockIconView$.ExternalSyntheticOutline0.m(i, "invalid time: ", TAG);
            return;
        }
        requestNoneLocalHbmPanoramicAodBoost.1 r0 = new requestNoneLocalHbmPanoramicAodBoost.1();
        r0.this$0 = this;
        r0.$timeout = i;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnWorkThread(r0);
    }

    public final void requestQsDetailDialog() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "requestQsDetailDialog OSENSE_ACTION_SYSTEM_UI_SWIPE", null, 8);
        Trace.traceBegin(8L, "startGPUAcceleration:show_qs_detail_dialog");
        OsenseResClient osenseResClient = this.mOsenseResClient;
        if (osenseResClient != null) {
            osenseResClient.osenseSetSceneAction(new OsenseSaRequest("", OSENSE_ACTION_SYSTEM_UI_SWIPE, 1000));
        }
        Trace.traceEnd(8L);
    }

    public final void requestQsDialogAnimBoost() {
        UAHResClient uAHResClient = this.mUAHResClient;
        this.mQSDialogAnimBoost = uAHResClient != null ? uAHResClient.acquireEvent(new UAHEventRequest(491, SCENE_NAME, 1000, (ArrayList) null)) : 0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.mQSDialogAnimBoost, "requestQsDialogAnimBoost handle:", "OsenseHelper-->", "SystemUi--Keyguard");
        }
    }

    public final void requestRippleBoost() {
        UAHResClient uAHResClient = this.mUAHResClient;
        if ((uAHResClient != null ? uAHResClient.acquireEvent(new UAHEventRequest(225, SCENE_NAME, 800, (ArrayList) null)) : 0) > 0 && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "OsenseHelper-->requestRippleBoost success");
        }
    }

    public final void requestSystemUIEdegSwipeDetect() {
        UAHResClient uAHResClient = this.mUAHResClient;
        int iAcquireEvent = uAHResClient != null ? uAHResClient.acquireEvent(new UAHEventRequest(486, SCENE_NAME, 800, (ArrayList) null)) : 0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(iAcquireEvent, "requestSystemUIEdegSwipeDetect result:", "OsenseHelper-->", "SystemUi--NavBar");
        }
    }

    public final void requestUnlockAnimBoost(int i) {
        if (i <= 0) {
            OplusLockIconView$.ExternalSyntheticOutline0.m(i, "requestUnlockAnimBoost invalid time: ", TAG);
            return;
        }
        UAHResClient uAHResClient = this.mUAHResClient;
        this.unlockAnimBoostHandle = uAHResClient != null ? uAHResClient.acquireEvent(new UAHEventRequest(432, SCENE_NAME, i, (ArrayList) null)) : 0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(this.unlockAnimBoostHandle, "requestUnlockAnimBoost handle:", "OsenseHelper-->", "SystemUi--Keyguard");
        }
    }

    public final void requestUnlockBoost() {
        OsenseResClient osenseResClient = this.mOsenseResClient;
        if (osenseResClient != null) {
            osenseResClient.osenseSetSceneAction(new OsenseSaRequest("", OSENSE_ACTION_UNLOCK_BOOST, 1000));
        }
    }

    public final void startGPUAcceleration(String str) {
        int i;
        if (this.mOsenseResClient != null) {
            if (this.mOsenseRecord.getMSignal() != -1 || (System.currentTimeMillis() > this.mOsenseRecord.getMTimeStamp() && this.mOsenseRecord.getMTimeStamp() != 0)) {
                endGPUAcceleration("forRestart");
            }
            if (this.mOsenseRecord.getMLastRecordSignal() != this.mOsenseRecord.getMLastCancelSignal() && this.mOsenseRecord.getMLastRecordSignal() != -1 && this.mOsenseRecord.getMLastCancelSignal() != -1 && this.mOsenseResClient != null) {
                if (this.mOsenseRecord.getMSignal() != -1) {
                    OsenseResClient osenseResClient = this.mOsenseResClient;
                    if (osenseResClient != null) {
                        osenseResClient.osenseClrSceneAction(this.mOsenseRecord.getMSignal());
                    }
                    this.mOsenseRecord.setMSignal(-1L);
                }
                if (this.mOsenseRecord.getMLastRecordSignal() != -1) {
                    OsenseResClient osenseResClient2 = this.mOsenseResClient;
                    if (osenseResClient2 != null) {
                        osenseResClient2.osenseClrSceneAction(this.mOsenseRecord.getMLastRecordSignal());
                    }
                    this.mOsenseRecord.setMLastRecordSignal(-1L);
                }
                if (this.mOsenseRecord.getMLastCancelSignal() != -1) {
                    OsenseResClient osenseResClient3 = this.mOsenseResClient;
                    if (osenseResClient3 != null) {
                        osenseResClient3.osenseClrSceneAction(this.mOsenseRecord.getMLastCancelSignal());
                    }
                    this.mOsenseRecord.setMLastCancelSignal(-1L);
                }
            }
            if (str.equals(ACTION_TOUCH)) {
                i = 500;
            } else {
                str.equals(ACTION_FLING);
                i = 3000;
            }
            this.mTimeOut = i;
            Trace.traceBegin(8L, "startGPUAcceleration:".concat(str));
            OsenseSaRequest osenseSaRequest = new OsenseSaRequest("", OSENSE_ACTION_SYSTEM_UI_SWIPE, this.mTimeOut);
            OsenseResClient osenseResClient4 = this.mOsenseResClient;
            long jOsenseSetSceneAction = osenseResClient4 != null ? osenseResClient4.osenseSetSceneAction(osenseSaRequest) : -1L;
            Trace.traceEnd(8L);
            this.mOsenseRecord.setMSignal(jOsenseSetSceneAction);
            this.mOsenseRecord.setMLastRecordSignal(jOsenseSetSceneAction);
            this.mOsenseRecord.setMTimeStamp(System.currentTimeMillis() + ((long) this.mTimeOut));
            this.mOsenseRecord.getMSignal();
        }
    }
}
