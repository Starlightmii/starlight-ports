package com.oplus.systemui.media.model;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.DependencyEx;
import com.android.systemui.media.controls.models.OplusMediaDataModel;
import com.android.systemui.media.controls.pipeline.OplusMediaDataManagerEx;
import com.oplus.media.OplusMediaControlManager;
import com.oplus.media.OplusMediaEvent;
import com.oplus.media.OplusMediaEventFilter;
import com.oplus.media.OplusMediaPlayerInfo;
import com.oplus.media.OplusPlaybackInfoRequestData;
import com.oplus.systemui.media.seedling.MediaSeedlingBgThread;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaDataModelImpl implements OplusMediaDataModel {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MediaDataModelImpl";
    private final Context context;
    private final Lazy eventObserver$delegate;
    private final OplusMediaDataModelImpl$innerOplusMediaDataCallBack$1 innerOplusMediaDataCallBack;
    private final List<OplusMediaDataManagerEx.OplusMediaDataCallBack> mediaCallbackList;
    private final OplusMediaControlManager mediaControlService;
    private OplusMediaDataManagerEx mediaDataManagerEx;
    private final Handler mediaThreadHandler;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.media.model.OplusMediaDataModelImpl$sendEvent$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Object $event;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusMediaDataModelImpl this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    try {
                        this.this$0.mediaControlService.sendEvent((OplusMediaEvent) this.$event);
                    } catch (Exception e) {
                        Log.e(OplusMediaDataModelImpl.TAG, "sendEvent exception: " + e.getMessage(), e);
                        return;
                    }
                    break;
                case 1:
                    try {
                        this.this$0.mediaControlService.unregisterEventObserver(this.this$0.getEventObserver());
                        this.this$0.mediaControlService.registerEventObserver(this.this$0.getEventObserver(), (OplusMediaEventFilter) this.$event);
                    } catch (Exception e2) {
                        Log.e(OplusMediaDataModelImpl.TAG, "registerEventObserver Exception.", e2);
                    }
                    break;
                default:
                    try {
                        this.this$0.mediaControlService.setMediaControlDenyList((List) this.$event);
                    } catch (Exception e3) {
                        Log.e(OplusMediaDataModelImpl.TAG, "setMediaControlBlackList exception: " + e3.getMessage(), e3);
                        return;
                    }
                    break;
            }
        }
    }

    public static MediaEventObserverImpl $r8$lambda$NoczzexnDq0RUhiYVO8EknNAffM(OplusMediaDataModelImpl oplusMediaDataModelImpl) {
        return new MediaEventObserverImpl(oplusMediaDataModelImpl.innerOplusMediaDataCallBack);
    }

    public OplusMediaDataModelImpl(Context context) {
        this.context = context;
        OplusMediaDataModelImpl$$ExternalSyntheticLambda0 oplusMediaDataModelImpl$$ExternalSyntheticLambda0 = new OplusMediaDataModelImpl$$ExternalSyntheticLambda0();
        oplusMediaDataModelImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.eventObserver$delegate = LazyKt__LazyJVMKt.lazy(oplusMediaDataModelImpl$$ExternalSyntheticLambda0);
        OplusMediaDataModelImpl$innerOplusMediaDataCallBack$1 oplusMediaDataModelImpl$innerOplusMediaDataCallBack$1 = new OplusMediaDataModelImpl$innerOplusMediaDataCallBack$1();
        oplusMediaDataModelImpl$innerOplusMediaDataCallBack$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.innerOplusMediaDataCallBack = oplusMediaDataModelImpl$innerOplusMediaDataCallBack$1;
        this.mediaControlService = OplusMediaControlManager.getInstance();
        this.mediaCallbackList = new ArrayList();
        this.mediaThreadHandler = new Handler(MediaSeedlingBgThread.INSTANCE.getThreadLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaEventObserverImpl getEventObserver() {
        return (MediaEventObserverImpl) this.eventObserver$delegate.getValue();
    }

    private final OplusMediaDataManagerEx getMediaDataManagerEx() {
        if (this.mediaDataManagerEx == null) {
            this.mediaDataManagerEx = (OplusMediaDataManagerEx) DependencyEx.sDependency.getDependency(OplusMediaDataManagerEx.class);
        }
        return this.mediaDataManagerEx;
    }

    public synchronized void addOplusMediaDataCallback(OplusMediaDataManagerEx.OplusMediaDataCallBack oplusMediaDataCallBack) {
        if (!this.mediaCallbackList.contains(oplusMediaDataCallBack)) {
            this.mediaCallbackList.add(oplusMediaDataCallBack);
        }
    }

    public final Context getContext() {
        return this.context;
    }

    public List<OplusMediaPlayerInfo> getCurrentPlayerInfoList() {
        List<OplusMediaPlayerInfo> currentPlayerInfoList = this.mediaControlService.getCurrentPlayerInfoList();
        return currentPlayerInfoList == null ? CollectionsKt__CollectionsKt.emptyList() : currentPlayerInfoList;
    }

    public List<OplusMediaPlayerInfo> getHistoryMediaPlayerInfoList() {
        OplusMediaDataManagerEx mediaDataManagerEx = getMediaDataManagerEx();
        if (mediaDataManagerEx == null || !mediaDataManagerEx.isOplusMediaDataSource()) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        List<OplusMediaPlayerInfo> historyMediaPlayerInfoList = this.mediaControlService.getHistoryMediaPlayerInfoList();
        return historyMediaPlayerInfoList == null ? CollectionsKt__CollectionsKt.emptyList() : historyMediaPlayerInfoList;
    }

    public synchronized void removeOplusMediaDataCallback(OplusMediaDataManagerEx.OplusMediaDataCallBack oplusMediaDataCallBack) {
        this.mediaCallbackList.remove(oplusMediaDataCallBack);
    }

    public void requestFavoriteState(String str) {
        OplusNotificationLogger.logMediaPanelNormal(TAG, "requestFavoriteState, playerId :" + str);
        sentEventInner(3, str);
    }

    public void requestMediaMetadata(String str) {
        OplusNotificationLogger.logMediaPanelNormal(TAG, "requestMediaMetadata, playerId :" + str);
        sentEventInner(1, str);
        sentEventInner(2, str);
    }

    public void requestPlaybackState(String str) {
        OplusNotificationLogger.logMediaPanelNormal(TAG, "requestPlaybackState, playerId :" + str);
        sentEventInner(2, str);
    }

    public final void runOnMediaThread(Runnable runnable) {
        if (Intrinsics.areEqual(Looper.myLooper(), MediaSeedlingBgThread.INSTANCE.getThreadLooper())) {
            runnable.run();
        } else {
            this.mediaThreadHandler.post(runnable);
        }
    }

    public void sendEvent(OplusMediaEvent oplusMediaEvent) {
        if (oplusMediaEvent == null) {
            Log.e(TAG, "event shouldn't be null");
            return;
        }
        OplusNotificationLogger.logMediaPanelNormal(TAG, "sendEvent：" + oplusMediaEvent);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        anonymousClass1.$event = oplusMediaEvent;
        VarHandle.storeStoreFence();
        runOnMediaThread(anonymousClass1);
    }

    public final void sentEventInner(int i, String str) {
        try {
            OplusMediaEvent.Builder data = new OplusMediaEvent.Builder(2).setData(new OplusPlaybackInfoRequestData(i));
            if (!TextUtils.isEmpty(str)) {
                data.setPlayerId(str);
            }
            sendEvent(data.build());
        } catch (Exception e) {
            Log.e(TAG, "mediaControlCenter sendEvent exception=" + e.getMessage(), e);
        }
    }

    public void setMediaControlBlackList(List<String> list) {
        Log.i(TAG, "setMediaControlBlackList");
        if (list.isEmpty()) {
            Log.e(TAG, "packageNameList shouldn't be empty");
            return;
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(2);
        anonymousClass1.this$0 = this;
        anonymousClass1.$event = list;
        VarHandle.storeStoreFence();
        runOnMediaThread(anonymousClass1);
    }

    public final void startObserveEvent() {
        OplusNotificationLogger.logMediaPanelNormal(TAG, "registerEventObserver startObserveEvent!!");
        OplusMediaEventFilter oplusMediaEventFilter = new OplusMediaEventFilter();
        oplusMediaEventFilter.addEventType(6);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
        anonymousClass1.this$0 = this;
        anonymousClass1.$event = oplusMediaEventFilter;
        VarHandle.storeStoreFence();
        runOnMediaThread(anonymousClass1);
    }
}
