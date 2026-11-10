package com.oplus.systemui.plugins.qs.guide;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import androidx.appcompat.app.AppCompatDelegateImpl$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.qs.GuideEvent;
import com.android.systemui.plugins.qs.GuideEventInfo;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QSGuide;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.Map;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsGuideSpiritualManager implements QSGuide.QsGuideCallback, OplusSeparateQSPlugin.ISysUiStateListener {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQsGuideSpiritualManager";
    private final Context context;
    private final OplusQsGuideSpiritualManager$guideAnimationCallback$1 guideAnimationCallback;
    private final OplusQsGuideSpiritualViewController guideSpiritualViewController;
    private final OplusQsGuideStateProvider guideStateProvider;
    private final Handler handler;
    public boolean isStartGuiding;
    private String mLastScenarioType;
    private QSGuide qsGuide;
    private final OplusSeparateQSPlugin.IStub separateQsPluginStub;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.guide.OplusQsGuideSpiritualManager$handleGuideEvent$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ GuideEvent $guideEvent;
        public /* synthetic */ OplusQsGuideSpiritualManager this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.handleEvent(this.$guideEvent);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.plugins.qs.guide.OplusQsGuideSpiritualManager$onSeparateModeChange$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC02421 implements Runnable {
        public /* synthetic */ boolean $separateMode;
        public /* synthetic */ OplusQsGuideSpiritualManager this$0;

        @Override // java.lang.Runnable
        public final void run() {
            boolean z = this.$separateMode;
            OplusQsGuideSpiritualManager oplusQsGuideSpiritualManager = this.this$0;
            if (z) {
                OplusQsGuideSpiritualManager.access$handleSeparateModeEnter(oplusQsGuideSpiritualManager);
            } else {
                OplusQsGuideSpiritualManager.access$handleSeparateModeExit(oplusQsGuideSpiritualManager);
            }
        }
    }

    public OplusQsGuideSpiritualManager(Context context, OplusQsGuideSpiritualViewController oplusQsGuideSpiritualViewController, OplusQsGuideStateProvider oplusQsGuideStateProvider, OplusSeparateQSPlugin.IStub iStub, Handler handler) {
        this.context = context;
        this.guideSpiritualViewController = oplusQsGuideSpiritualViewController;
        this.guideStateProvider = oplusQsGuideStateProvider;
        this.separateQsPluginStub = iStub;
        this.handler = handler;
        OplusQsGuideSpiritualManager$guideAnimationCallback$1 oplusQsGuideSpiritualManager$guideAnimationCallback$1 = new OplusQsGuideSpiritualManager$guideAnimationCallback$1();
        oplusQsGuideSpiritualManager$guideAnimationCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.guideAnimationCallback = oplusQsGuideSpiritualManager$guideAnimationCallback$1;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(" init, isNeedGuide: ", OplusQsGuideSpUtils.isNeedGuide(context)), null, 8);
    }

    public static final void access$handleSeparateModeEnter(OplusQsGuideSpiritualManager oplusQsGuideSpiritualManager) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(" handleSeparateModeEnter, isNeedGuide: ", OplusQsGuideSpUtils.isNeedGuide(oplusQsGuideSpiritualManager.context)), null, 8);
        if (OplusQsGuideSpUtils.isNeedGuide(oplusQsGuideSpiritualManager.context)) {
            oplusQsGuideSpiritualManager.startGuide();
        }
    }

    public static final void access$handleSeparateModeExit(OplusQsGuideSpiritualManager oplusQsGuideSpiritualManager) {
        boolean zIsSelectDialogNeedShow = oplusQsGuideSpiritualManager.guideStateProvider.isSelectDialogNeedShow();
        boolean zIsMainUser = oplusQsGuideSpiritualManager.guideStateProvider.isMainUser();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, AppCompatDelegateImpl$.ExternalSyntheticOutline0.m(" ", TextFieldImplKt$.ExternalSyntheticOutline0.m(" handleSeparateModeExit:", ", ", ", ", oplusQsGuideSpiritualManager.isStartGuiding, zIsMainUser), zIsSelectDialogNeedShow), null, 8);
        if (oplusQsGuideSpiritualManager.isStartGuiding && zIsMainUser && !oplusQsGuideSpiritualManager.guideStateProvider.isSelectDialogNeedShow()) {
            OplusQsGuideSpUtils.setSupportGuide(oplusQsGuideSpiritualManager.context, false);
        }
        oplusQsGuideSpiritualManager.releaseGuide();
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        Map<String, ?> all;
        printWriter.println("OplusQsGuide:dump start!!!");
        printWriter.println(this);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "isNeedGuide=", OplusQsGuideSpUtils.isNeedGuide(this.context));
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "isSupportGuide: ", OplusQsGuideSpUtils.isSupportGuide(this.context));
        printWriter.println("getTestGuideMode: " + OplusQsGuideSpUtils.getTestGuideMode(this.context));
        printWriter.println("guideSharedPreferences: ");
        SharedPreferences sharedPreferences = OplusQsGuideSpUtils.getSharedPreferences(this.context);
        if (sharedPreferences != null && (all = sharedPreferences.getAll()) != null) {
            for (Map.Entry<String, ?> entry : all.entrySet()) {
                String key = entry.getKey();
                printWriter.println(" " + ((Object) key) + " = " + entry.getValue());
            }
        }
        printWriter.println("OplusQsGuide:dump end!!!");
    }

    public final IBinder getExcludeWindowTokens() {
        IBinder windowTokens = this.guideSpiritualViewController.getWindowTokens();
        LogUtil.i$default(LogUtil.TAG_QS, TAG, " getExcludeWindowTokens: " + windowTokens, null, 8);
        return windowTokens;
    }

    public final String getMLastScenarioType() {
        return this.mLastScenarioType;
    }

    public final void handleEvent(GuideEvent guideEvent) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", KeyguardSettingProvider$.ExternalSyntheticOutline0.m(" handleEvent: ", guideEvent.name(), " , ", this.isStartGuiding, "OplusQsGuideSpiritualManager-->"));
        }
        if (this.isStartGuiding) {
            this.guideSpiritualViewController.handleEvent(guideEvent);
        }
    }

    public void handleGuideEvent(GuideEvent guideEvent, GuideEventInfo guideEventInfo) {
        Handler handler = this.handler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        anonymousClass1.$guideEvent = guideEvent;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    public final void handlePluginBind() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(" handlePluginBind isNeedGuide", OplusQsGuideSpUtils.isNeedGuide(this.context)), null, 8);
        if (OplusQsGuideSpUtils.isNeedGuide(this.context)) {
            startGuide();
        }
    }

    public final void onFullscreenStateChanged(boolean z) {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(" onFullscreenStateChanged ", z), null, 8);
        if (z) {
            handleEvent(GuideEvent.FULLSCREEN_ENTER);
        } else {
            handleEvent(GuideEvent.FULLSCREEN_EXIT);
        }
    }

    public final void onScenarioTypeChanged(String str) {
        if (str != null) {
            QSGuide qSGuide = this.qsGuide;
            if (qSGuide == null || !qSGuide.isQsVisible()) {
                QSGuide qSGuide2 = this.qsGuide;
                if (qSGuide2 == null || !qSGuide2.isNotificationVisible()) {
                    this.guideSpiritualViewController.setScenarioType(str);
                    int guideSpIntValue = OplusQsGuideSpUtils.getGuideSpIntValue(this.context, str, 0);
                    StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("scenarioType:", str, "  mLastScenarioType:", this.mLastScenarioType, " scenarioGuideSpIntValue:");
                    sbM.append(guideSpIntValue);
                    LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
                    if (str.equals(this.mLastScenarioType) || guideSpIntValue != 0) {
                        return;
                    }
                    this.mLastScenarioType = str;
                    handleGuideEvent(GuideEvent.SCENARIO_ASSISTANT, GuideEventInfo.NONE);
                }
            }
        }
    }

    public final void onSeparateModeChange(boolean z) {
        Handler handler = this.handler;
        RunnableC02421 runnableC02421 = new RunnableC02421();
        runnableC02421.$separateMode = z;
        runnableC02421.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(runnableC02421);
    }

    public final void releaseGuide() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, " releaseGuide, ".concat(!this.isStartGuiding ? "not isStartGuiding return" : ""), null, 8);
        if (this.isStartGuiding) {
            this.isStartGuiding = false;
            QSGuide qSGuide = this.qsGuide;
            if (qSGuide != null) {
                qSGuide.removeCallback(this);
            }
            this.separateQsPluginStub.removeFullScreenChangedCallback(this);
            this.guideSpiritualViewController.setAnimationCallback(null);
            OplusQsGuideSpiritualViewController oplusQsGuideSpiritualViewController = this.guideSpiritualViewController;
            oplusQsGuideSpiritualViewController.getClass();
            LogUtil.i$default(LogUtil.TAG_QS, "OplusQsGuideSpiritualViewController", " releaseGuide", null, 8);
            oplusQsGuideSpiritualViewController.resetAllState();
            oplusQsGuideSpiritualViewController.cancelAnimation(OplusQsGuideSpiritualViewController.GuideFlag.FLAG_SCENARIO_ASSISTANT);
        }
    }

    public final void setMLastScenarioType(String str) {
        this.mLastScenarioType = str;
    }

    public final void startGuide() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, " startGuide, ".concat(this.isStartGuiding ? "already isStartGuiding return" : ""), null, 8);
        if (this.isStartGuiding) {
            return;
        }
        this.isStartGuiding = true;
        QSGuide qSGuideBindGuideProvider = this.separateQsPluginStub.bindGuideProvider();
        this.qsGuide = qSGuideBindGuideProvider;
        if (qSGuideBindGuideProvider != null) {
            qSGuideBindGuideProvider.addCallBack(this);
        }
        this.separateQsPluginStub.addFullScreenChangedCallback(this);
        this.guideStateProvider.bindQsGuide(this.qsGuide);
        this.guideSpiritualViewController.setAnimationCallback(this.guideAnimationCallback);
        OplusQsGuideSpiritualViewController oplusQsGuideSpiritualViewController = this.guideSpiritualViewController;
        oplusQsGuideSpiritualViewController.getClass();
        LogUtil.i$default(LogUtil.TAG_QS, "OplusQsGuideSpiritualViewController", " startGuide", null, 8);
        oplusQsGuideSpiritualViewController.resetAllState();
        OplusQsGuideSpiritualViewController.startPlayAnimation$default(oplusQsGuideSpiritualViewController);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("OplusQsGuideSpiritualManager@" + hashCode() + "(");
        stringBuffer.append("isStartGuiding=" + this.isStartGuiding + ", ");
        stringBuffer.append("qsGuide=" + this.qsGuide + ", ");
        if (this.isStartGuiding) {
            stringBuffer.append("guideStateProvider=" + this.guideStateProvider + ", ");
            stringBuffer.append("guideSpiritualViewController=" + this.guideSpiritualViewController + ", ");
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }
}
