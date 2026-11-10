package com.oplus.systemui.media.seedling.client.controller;

import android.content.Context;
import android.os.Handler;
import com.android.systemui.media.dialog.MediaOutputDialogManager;
import com.android.systemui.plugins.ActivityStarter;
import com.oplus.systemui.media.seedling.OplusSeedlingMediaDataCombineLatest;
import com.oplus.systemui.media.seedling.client.AbsMediaClient;
import com.oplus.systemui.media.seedling.client.loader.album.SingleAlbumLoader;
import com.oplus.systemui.media.seedling.client.loader.album.artwrok.UriArtworkLoader;
import com.oplus.systemui.media.seedling.client.loader.album.background.OplusArtworkBgColorLoader;
import com.oplus.systemui.media.seedling.client.loader.appicon.DefaultAppIconLoader;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaControllerClient extends AbsMediaClient {
    private final ActivityStarter activityStarter;
    private final Handler bgHandler;
    private final Context context;
    private final Handler mainHandler;
    private SeedlingMediaData mediaData;
    private final OplusSeedlingMediaDataCombineLatest mediaDataCombineLatest;
    private final MediaOutputDialogManager outputDialogFactory;
    private final CoroutineDispatcher seedlingBgDispatcher;
    private final CoroutineScope seedlingBgScope;

    public MediaControllerClient(Context context, SeedlingMediaData seedlingMediaData, ActivityStarter activityStarter, MediaOutputDialogManager mediaOutputDialogManager, OplusSeedlingMediaDataCombineLatest oplusSeedlingMediaDataCombineLatest, Handler handler, Handler handler2, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope) {
        super(context, seedlingMediaData, activityStarter, mediaOutputDialogManager, oplusSeedlingMediaDataCombineLatest, new DefaultAppIconLoader(context), new SingleAlbumLoader(new UriArtworkLoader(context, 0), new OplusArtworkBgColorLoader()), handler, handler2, coroutineDispatcher, coroutineScope);
        this.context = context;
        this.mediaData = seedlingMediaData;
        this.activityStarter = activityStarter;
        this.outputDialogFactory = mediaOutputDialogManager;
        this.mediaDataCombineLatest = oplusSeedlingMediaDataCombineLatest;
        this.mainHandler = handler;
        this.bgHandler = handler2;
        this.seedlingBgDispatcher = coroutineDispatcher;
        this.seedlingBgScope = coroutineScope;
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public ActivityStarter getActivityStarter() {
        return this.activityStarter;
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

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public final void onCreate() {
    }

    @Override // com.oplus.systemui.media.seedling.client.AbsMediaClient
    public void setMediaData(SeedlingMediaData seedlingMediaData) {
        this.mediaData = seedlingMediaData;
    }
}
