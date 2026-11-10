package com.oplus.systemui.media.controls.pipeline;

import android.content.Context;
import android.os.SystemProperties;
import android.os.UserHandle;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.settings.UserTracker;
import com.oplus.media.OplusMediaPlayerInfo;
import com.oplus.systemui.media.model.OplusMediaDataModelImpl;
import com.oplusos.systemui.common.feature.FeatureOption;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaPlayerManager {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OplusMediaPlayerManager";
    private final Context context;
    private final Executor executor;
    private final List<MediaPlayerDataInfoCallback> mediaPlayerDataInfoCallbackList;
    private final OplusMediaPlayerManager$oplusMediaDataCallback$1 oplusMediaDataCallback;
    private final Lazy oplusMediaDataModelImpl$delegate;
    private final List<OplusMediaPlayerInfo> oplusMediaPlayerDataInfoList;
    private final UserTracker userTracker;
    private final OplusMediaPlayerManager$userTrackerCallback$1 userTrackerCallback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface MediaPlayerDataInfoCallback {
        void onMediaPlayerDataInfoChanged(int i, List<OplusMediaPlayerInfo> list);
    }

    public static OplusMediaDataModelImpl $r8$lambda$YlmfZ1NTMoHKcxzva3t59Myyv8o(OplusMediaPlayerManager oplusMediaPlayerManager) {
        return new OplusMediaDataModelImpl(oplusMediaPlayerManager.context);
    }

    public OplusMediaPlayerManager(Context context, UserTracker userTracker, Executor executor) {
        this.context = context;
        this.userTracker = userTracker;
        this.executor = executor;
        OplusMediaPlayerManager$$ExternalSyntheticLambda0 oplusMediaPlayerManager$$ExternalSyntheticLambda0 = new OplusMediaPlayerManager$$ExternalSyntheticLambda0();
        oplusMediaPlayerManager$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusMediaDataModelImpl$delegate = LazyKt__LazyJVMKt.lazy(oplusMediaPlayerManager$$ExternalSyntheticLambda0);
        this.oplusMediaPlayerDataInfoList = new ArrayList();
        this.mediaPlayerDataInfoCallbackList = new ArrayList();
        OplusMediaPlayerManager$userTrackerCallback$1 oplusMediaPlayerManager$userTrackerCallback$1 = new OplusMediaPlayerManager$userTrackerCallback$1();
        oplusMediaPlayerManager$userTrackerCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.userTrackerCallback = oplusMediaPlayerManager$userTrackerCallback$1;
        OplusMediaPlayerManager$oplusMediaDataCallback$1 oplusMediaPlayerManager$oplusMediaDataCallback$1 = new OplusMediaPlayerManager$oplusMediaDataCallback$1();
        oplusMediaPlayerManager$oplusMediaDataCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.oplusMediaDataCallback = oplusMediaPlayerManager$oplusMediaDataCallback$1;
        getOplusMediaDataModelImpl().addOplusMediaDataCallback(oplusMediaPlayerManager$oplusMediaDataCallback$1);
        getOplusMediaDataModelImpl().startObserveEvent();
        FeatureOption featureOption = FeatureOption.INSTANCE;
        if ("1".equals(SystemProperties.get("sys.boot_completed")) && UserHandle.myUserId() == 0) {
            initPlayerInfoList(OsBinderCacheUtils.getCurrentUserId());
        }
        userTracker.addCallback(oplusMediaPlayerManager$userTrackerCallback$1, executor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OplusMediaDataModelImpl getOplusMediaDataModelImpl() {
        return (OplusMediaDataModelImpl) this.oplusMediaDataModelImpl$delegate.getValue();
    }

    public final void addMediaPlayerDataInfoCallback(MediaPlayerDataInfoCallback mediaPlayerDataInfoCallback) {
        this.mediaPlayerDataInfoCallbackList.add(mediaPlayerDataInfoCallback);
    }

    public final List<OplusMediaPlayerInfo> getOplusMediaPlayerDataInfoList() {
        return this.oplusMediaPlayerDataInfoList;
    }

    public final void initPlayerInfoList(int i) {
        List<OplusMediaPlayerInfo> currentPlayerInfoList = getOplusMediaDataModelImpl().getCurrentPlayerInfoList();
        if (currentPlayerInfoList.isEmpty()) {
            return;
        }
        List<OplusMediaPlayerInfo> list = currentPlayerInfoList;
        Iterator it = CollectionsKt___CollectionsKt.reversed(list).iterator();
        while (it.hasNext()) {
            getOplusMediaDataModelImpl().requestMediaMetadata(((OplusMediaPlayerInfo) it.next()).getPlayerId());
        }
        notifyMediaPlayerDataInfoChanged(i, CollectionsKt___CollectionsKt.toMutableList(CollectionsKt___CollectionsKt.reversed(list)));
        getOplusMediaDataModelImpl().startObserveEvent();
    }

    public final void notifyMediaPlayerDataInfoChanged(int i, List list) {
        Iterator<T> it = this.mediaPlayerDataInfoCallbackList.iterator();
        while (it.hasNext()) {
            ((OplusMediaDataManagerExImpl$oplusMediaPlayerInfoCallback$1) ((MediaPlayerDataInfoCallback) it.next())).onMediaPlayerDataInfoChanged(i, list);
        }
    }

    public final void removeMediaPlayerDataInfoCallback(MediaPlayerDataInfoCallback mediaPlayerDataInfoCallback) {
        this.mediaPlayerDataInfoCallbackList.remove(mediaPlayerDataInfoCallback);
    }
}
