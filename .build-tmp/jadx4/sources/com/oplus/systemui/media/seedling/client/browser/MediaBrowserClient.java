package com.oplus.systemui.media.seedling.client.browser;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.media.browse.MediaBrowser;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import com.android.systemui.media.dialog.MediaOutputDialogManager;
import com.android.systemui.plugins.ActivityStarter;
import com.oplus.systemui.media.seedling.OplusSeedlingMediaDataCombineLatest;
import com.oplus.systemui.media.seedling.client.AbsMediaClient;
import com.oplus.systemui.media.seedling.client.loader.album.IAlbumLoader;
import com.oplus.systemui.media.seedling.client.loader.appicon.IAppIconLoader;
import com.oplus.systemui.media.seedling.utils.HandlerExtKt;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class MediaBrowserClient extends AbsMediaClient {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MediaBrowserClient";
    private final ActivityStarter activityStarter;
    private final IAlbumLoader albumLoader;
    private final List<Runnable> allTasks;
    private final IAppIconLoader appIconLoader;
    private final Handler bgHandler;
    private ComponentName componentName;
    private final Runnable connectErrorTask;
    private final Runnable connectedTask;
    private final connectionCallback.1 connectionCallback;
    private final Context context;
    private String currentSubscribeMediaId;
    private final CopyOnWriteArrayList<MediaBrowser.MediaItem> currentSubscriptionChildren;
    private final Handler mainHandler;
    private volatile MediaBrowser mediaBrowser;
    private SeedlingMediaData mediaData;
    private final OplusSeedlingMediaDataCombineLatest mediaDataCombineLatest;
    private final MediaOutputDialogManager outputDialogFactory;
    public boolean requestedConnect;
    private final CoroutineDispatcher seedlingBgDispatcher;
    private final CoroutineScope seedlingBgScope;
    private final subscriptionCallback.1 subscriptionCallback;
    private final Runnable subscriptionChildrenLoadedTask;

    public MediaBrowserClient(Context context, SeedlingMediaData seedlingMediaData, ActivityStarter activityStarter, MediaOutputDialogManager mediaOutputDialogManager, OplusSeedlingMediaDataCombineLatest oplusSeedlingMediaDataCombineLatest, IAppIconLoader iAppIconLoader, IAlbumLoader iAlbumLoader, Handler handler, Handler handler2, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope) {
        super(context, seedlingMediaData, activityStarter, mediaOutputDialogManager, oplusSeedlingMediaDataCombineLatest, iAppIconLoader, iAlbumLoader, handler, handler2, coroutineDispatcher, coroutineScope);
        this.context = context;
        this.mediaData = seedlingMediaData;
        this.activityStarter = activityStarter;
        this.outputDialogFactory = mediaOutputDialogManager;
        this.mediaDataCombineLatest = oplusSeedlingMediaDataCombineLatest;
        this.appIconLoader = iAppIconLoader;
        this.albumLoader = iAlbumLoader;
        this.mainHandler = handler;
        this.bgHandler = handler2;
        this.seedlingBgDispatcher = coroutineDispatcher;
        this.seedlingBgScope = coroutineScope;
        this.currentSubscribeMediaId = "";
        this.currentSubscriptionChildren = new CopyOnWriteArrayList<>();
        this.connectionCallback = new connectionCallback.1(this);
        this.subscriptionCallback = new subscriptionCallback.1(this);
        Runnable runnable = new connectedTask.1(0);
        ((connectedTask.1) runnable).this$0 = this;
        VarHandle.storeStoreFence();
        this.connectedTask = runnable;
        Runnable runnable2 = new connectedTask.1(1);
        ((connectedTask.1) runnable2).this$0 = this;
        VarHandle.storeStoreFence();
        this.connectErrorTask = runnable2;
        Runnable runnable3 = new connectedTask.1(3);
        ((connectedTask.1) runnable3).this$0 = this;
        VarHandle.storeStoreFence();
        this.subscriptionChildrenLoadedTask = runnable3;
        this.allTasks = CollectionsKt__CollectionsKt.listOf(new Runnable[]{runnable, runnable2, runnable3});
    }

    public static final void access$handleMediaBrowserConnected(MediaBrowserClient mediaBrowserClient) {
        OplusNotificationLogger.logMediaSeedling(TAG, "handleMediaBrowserConnected");
        mediaBrowserClient.requestedConnect = false;
        mediaBrowserClient.clearLocalRecommendations();
        MediaController mediaController = mediaBrowserClient.getMediaController();
        MediaSession.Token sessionToken = mediaController != null ? mediaController.getSessionToken() : null;
        MediaSession.Token mediaBrowserToken = mediaBrowserClient.getMediaBrowserToken();
        if (Intrinsics.areEqual(sessionToken, mediaBrowserToken) || mediaBrowserToken == null) {
            mediaBrowserClient.setMediaController(null);
        } else {
            mediaBrowserClient.setMediaController(new MediaController(mediaBrowserClient.getContext(), mediaBrowserToken));
            MediaController mediaController2 = mediaBrowserClient.getMediaController();
            PlaybackState playbackState = mediaController2 != null ? mediaController2.getPlaybackState() : null;
            Handler bgHandler = mediaBrowserClient.getBgHandler();
            updateMediaController.1 r4 = new updateMediaController.1(0);
            r4.this$0 = mediaBrowserClient;
            r4.$playbackState = playbackState;
            VarHandle.storeStoreFence();
            bgHandler.post(r4);
        }
        String mediaBrowserRoot = mediaBrowserClient.getMediaBrowserRoot();
        if (mediaBrowserRoot != null && !StringsKt__StringsKt.isBlank(mediaBrowserRoot)) {
            mediaBrowserClient.subscribe(mediaBrowserRoot);
            return;
        }
        OplusNotificationLogger.logMediaSeedling(TAG, "MediaBrowser root is invalid for " + mediaBrowserClient.componentName);
        mediaBrowserClient.requestDisconnectMediaBrowserService();
    }

    public static final void access$runTargetTaskAndRemoveAllOtherTasks(MediaBrowserClient mediaBrowserClient, Runnable runnable) {
        for (Runnable runnable2 : mediaBrowserClient.allTasks) {
            if (!Intrinsics.areEqual(runnable2, runnable)) {
                HandlerExtKt.removeTaskIfExist(mediaBrowserClient.getMainHandler(), runnable2);
            }
        }
        HandlerExtKt.runTask(mediaBrowserClient.getMainHandler(), runnable);
    }

    private final ComponentName getMediaBrowserComponentAsUser() {
        Object next;
        Iterator<T> it = getContext().getPackageManager().queryIntentServices(new Intent("android.media.browse.MediaBrowserService"), 64).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(getMediaData().getPackageName(), ((ResolveInfo) next).serviceInfo.packageName));
        ResolveInfo resolveInfo = (ResolveInfo) next;
        if (resolveInfo == null) {
            return null;
        }
        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
        return new ComponentName(serviceInfo.packageName, serviceInfo.name);
    }

    private final String getMediaBrowserRoot() {
        MediaBrowser mediaBrowser;
        MediaBrowser mediaBrowser2 = this.mediaBrowser;
        if (mediaBrowser2 == null || !mediaBrowser2.isConnected() || (mediaBrowser = this.mediaBrowser) == null) {
            return null;
        }
        return mediaBrowser.getRoot();
    }

    private final MediaSession.Token getMediaBrowserToken() {
        MediaBrowser mediaBrowser;
        MediaBrowser mediaBrowser2 = this.mediaBrowser;
        if (mediaBrowser2 == null || !mediaBrowser2.isConnected() || (mediaBrowser = this.mediaBrowser) == null) {
            return null;
        }
        return mediaBrowser.getSessionToken();
    }

    public final void clearLocalRecommendations() {
        this.currentSubscriptionChildren.clear();
        getMediaData().getRecommendations().clear();
    }

    public abstract void fillRootHints(Bundle bundle);

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public ActivityStarter getActivityStarter() {
        return this.activityStarter;
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public IAlbumLoader getAlbumLoader() {
        return this.albumLoader;
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public IAppIconLoader getAppIconLoader() {
        return this.appIconLoader;
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public Handler getBgHandler() {
        return this.bgHandler;
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public Context getContext() {
        return this.context;
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public Handler getMainHandler() {
        return this.mainHandler;
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public SeedlingMediaData getMediaData() {
        return this.mediaData;
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public OplusSeedlingMediaDataCombineLatest getMediaDataCombineLatest() {
        return this.mediaDataCombineLatest;
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public MediaOutputDialogManager getOutputDialogFactory() {
        return this.outputDialogFactory;
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public CoroutineDispatcher getSeedlingBgDispatcher() {
        return this.seedlingBgDispatcher;
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public CoroutineScope getSeedlingBgScope() {
        return this.seedlingBgScope;
    }

    public final Runnable getSubscriptionChildrenLoadedTask() {
        return this.subscriptionChildrenLoadedTask;
    }

    public abstract void handleOnChildrenLoaded(String str, List<? extends MediaBrowser.MediaItem> list);

    public final void initMediaBrowserIfNeed() {
        if (this.mediaBrowser == null) {
            ComponentName mediaBrowserComponentAsUser = getMediaBrowserComponentAsUser();
            this.componentName = mediaBrowserComponentAsUser;
            OplusNotificationLogger.logMediaSeedling(TAG, "initMediaBrowserIfNeed componentName = " + mediaBrowserComponentAsUser);
            if (this.componentName != null) {
                Bundle bundle = new Bundle();
                fillRootHints(bundle);
                this.mediaBrowser = new MediaBrowser(getContext(), this.componentName, this.connectionCallback, bundle);
            }
        }
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public final void onCreate() {
        synchronized (this) {
            if (this.requestedConnect) {
                OplusNotificationLogger.logMediaSeedling(TAG, "connect() called return, was requested connect");
                return;
            }
            MediaBrowser mediaBrowser = this.mediaBrowser;
            if (mediaBrowser != null && mediaBrowser.isConnected()) {
                OplusNotificationLogger.logMediaSeedling(TAG, "connect() called return, mediaBrowser is connected");
                return;
            }
            if (getMainHandler().hasCallbacks(this.connectedTask)) {
                OplusNotificationLogger.logMediaSeedling(TAG, "connect() called return, mainHandler already has connectedTask");
                return;
            }
            initMediaBrowserIfNeed();
            if (this.mediaBrowser == null) {
                OplusNotificationLogger.logMediaSeedling(TAG, "connect() called return, mediaBrowser is null");
                return;
            }
            this.requestedConnect = true;
            MediaBrowser mediaBrowser2 = this.mediaBrowser;
            if (mediaBrowser2 != null) {
                mediaBrowser2.connect();
            }
        }
    }

    public final synchronized void requestDisconnectMediaBrowserService() {
        try {
            OplusNotificationLogger.logMediaSeedling(TAG, "requestDisconnectMediaBrowserService");
            Iterator<T> it = this.allTasks.iterator();
            while (it.hasNext()) {
                HandlerExtKt.removeTaskIfExist(getMainHandler(), (Runnable) it.next());
            }
            if (!StringsKt__StringsKt.isBlank(this.currentSubscribeMediaId)) {
                MediaBrowser mediaBrowser = this.mediaBrowser;
                if (mediaBrowser != null) {
                    mediaBrowser.unsubscribe(this.currentSubscribeMediaId);
                }
                this.currentSubscribeMediaId = "";
            }
            Handler bgHandler = getBgHandler();
            connectedTask.1 r1 = new connectedTask.1(2);
            r1.this$0 = this;
            VarHandle.storeStoreFence();
            bgHandler.post(r1);
            this.currentSubscriptionChildren.clear();
            setMediaController(null);
            MediaBrowser mediaBrowser2 = this.mediaBrowser;
            if (mediaBrowser2 != null) {
                mediaBrowser2.disconnect();
            }
            this.requestedConnect = false;
            this.mediaBrowser = null;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public void setMediaData(SeedlingMediaData seedlingMediaData) {
        this.mediaData = seedlingMediaData;
    }

    public final void subscribe(String str) {
        if (!StringsKt__StringsKt.isBlank(this.currentSubscribeMediaId)) {
            MediaBrowser mediaBrowser = this.mediaBrowser;
            if (mediaBrowser != null) {
                mediaBrowser.unsubscribe(this.currentSubscribeMediaId);
            }
            this.currentSubscribeMediaId = "";
        }
        if (str == null || StringsKt__StringsKt.isBlank(str)) {
            return;
        }
        this.currentSubscribeMediaId = str;
        MediaBrowser mediaBrowser2 = this.mediaBrowser;
        if (mediaBrowser2 != null) {
            mediaBrowser2.subscribe(str, this.subscriptionCallback);
        }
    }
}
