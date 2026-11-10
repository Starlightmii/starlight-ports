package com.oplus.systemui.media.controls.pipeline;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.MediaMetadata;
import android.media.Rating;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.DependencyEx;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.media.MediaCarouselControllerEx;
import com.android.systemui.media.NotificationMediaManager;
import com.android.systemui.media.controls.domain.pipeline.LegacyMediaDataManagerImplKt;
import com.android.systemui.media.controls.domain.pipeline.MediaDataLoader;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.models.player.MediaActionPriority;
import com.android.systemui.media.controls.models.player.OplusMediaActionEx;
import com.android.systemui.media.controls.models.player.OplusMediaButtonEx;
import com.android.systemui.media.controls.models.player.OplusMediaLyricData;
import com.android.systemui.media.controls.pipeline.OplusMediaDataManagerEx;
import com.android.systemui.media.controls.shared.model.MediaAction;
import com.android.systemui.media.controls.shared.model.MediaButton;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaNotificationAction;
import com.android.systemui.media.controls.ui.controller.MediaPlayerData;
import com.android.systemui.media.controls.util.MediaControllerFactory;
import com.android.systemui.media.controls.util.MediaFlags;
import com.android.systemui.media.controls.util.MediaUiEventLogger;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.statusbar.OplusNotificationMediaManagerExt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.NotificationResolverEx;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.oplus.media.OplusMediaEvent;
import com.oplus.media.OplusMediaPlayerInfo;
import com.oplus.systemui.media.MediaNotificationExtKt;
import com.oplus.systemui.media.OplusMediaDataFilterExImpl;
import com.oplus.systemui.media.model.OplusMediaDataModelImpl;
import com.oplus.systemui.media.seedling.MediaSeedlingBgThread;
import com.oplusos.systemui.aod.mediapanel.AodMediaDataListener;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.coroutines.BuildersKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusMediaDataManagerExImpl extends OplusMediaDataManagerEx {
    public static final String OPLUS_SESSION_KEY = "oplus.mediaSession";
    public static final String TAG = "OplusMediaDataManagerEx";
    public final /* synthetic */ OplusMediaDataModelImpl $$delegate_0;
    private final Context context;
    private final DelayableExecutor foregroundExecutor;
    private final Lazy isOplusMediaDataSource$delegate;
    private final dagger.Lazy logBuffer;
    private final MediaUiEventLogger logger;
    private final Lazy mediaActionPrioritySelector$delegate;
    private final Handler mediaBgHandler;
    private final MediaCarouselControllerEx mediaCarouselControllerEx;
    private final MediaControllerFactory mediaControllerFactory;
    private final Lazy mediaDataFlowLimiter$delegate;
    private final dagger.Lazy mediaDataLoaderLazy;
    private final dagger.Lazy mediaDataManagerLazy;
    private final OplusMediaPlayerManager mediaPlayerDataInfo;
    private final List<MediaSessionHolder> mediaSessionHolderList;
    private final NotificationResolverEx notifcationResolverEx;
    private final Lazy oplusMediaDataManagerStrategy$delegate;
    private final OplusMediaDataManagerExImpl$oplusMediaPlayerInfoCallback$1 oplusMediaPlayerInfoCallback;
    private final Lazy oplusNotificationMediaManagerExt$delegate;
    private final Map<String, MediaPlayerDataInfo> playerInfoMap;
    private final Map<String, StatusBarNotification> sbnMap;
    private final Lazy spotifyMediaDataManagerStrategy$delegate;
    public static final Companion Companion = new Companion();
    private static final List<String> sessionActionsMediaWhiteList = CollectionsKt__CollectionsJVMKt.listOf("com.tencent.qqmusic");

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MediaSessionHolder {
        private final String key;
        private final MediaController mediaController;
        private final MediaController.Callback mediaControllerCallback;
        private final MediaSession.Token token;

        public MediaSessionHolder(String str, MediaSession.Token token, MediaController mediaController, MediaController.Callback callback) {
            this.key = str;
            this.token = token;
            this.mediaController = mediaController;
            this.mediaControllerCallback = callback;
        }

        public static /* synthetic */ MediaSessionHolder copy$default(MediaSessionHolder mediaSessionHolder, String str) {
            return mediaSessionHolder.copy(str, mediaSessionHolder.token, mediaSessionHolder.mediaController, mediaSessionHolder.mediaControllerCallback);
        }

        public final String component1() {
            return this.key;
        }

        public final MediaSession.Token component2() {
            return this.token;
        }

        public final MediaController component3() {
            return this.mediaController;
        }

        public final MediaController.Callback component4() {
            return this.mediaControllerCallback;
        }

        public final MediaSessionHolder copy(String str, MediaSession.Token token, MediaController mediaController, MediaController.Callback callback) {
            return new MediaSessionHolder(str, token, mediaController, callback);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaSessionHolder)) {
                return false;
            }
            MediaSessionHolder mediaSessionHolder = (MediaSessionHolder) obj;
            return Intrinsics.areEqual(this.key, mediaSessionHolder.key) && Intrinsics.areEqual(this.token, mediaSessionHolder.token) && Intrinsics.areEqual(this.mediaController, mediaSessionHolder.mediaController) && Intrinsics.areEqual(this.mediaControllerCallback, mediaSessionHolder.mediaControllerCallback);
        }

        public final String getKey() {
            return this.key;
        }

        public final MediaController getMediaController() {
            return this.mediaController;
        }

        public final MediaController.Callback getMediaControllerCallback() {
            return this.mediaControllerCallback;
        }

        public final MediaSession.Token getToken() {
            return this.token;
        }

        public final int hashCode() {
            return this.mediaControllerCallback.hashCode() + ((this.mediaController.hashCode() + ((this.token.hashCode() + (this.key.hashCode() * 31)) * 31)) * 31);
        }

        public String toString() {
            return "MediaSessionHolder(key=" + this.key + ", token=" + this.token + ", mediaController=" + this.mediaController + ", mediaControllerCallback=" + this.mediaControllerCallback + ")";
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.media.controls.pipeline.OplusMediaDataManagerExImpl$getArtWorkIconByMediaMetaData$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OplusMediaDataManagerExImpl.getArtWorkIconByMediaMetaData$suspendImpl(OplusMediaDataManagerExImpl.this, null, null, null, this);
        }
    }

    public static OplusMediaDataManagerStrategy $r8$lambda$0HUdYLjmHyym7xxWGjz95MXLA0U(OplusMediaDataManagerExImpl oplusMediaDataManagerExImpl) {
        return new OplusMediaDataManagerStrategy(oplusMediaDataManagerExImpl.context);
    }

    public static SpotifyMediaDataManagerStrategy $r8$lambda$efWdZjpUDTVdjKMuYp4lNjAmIV4(OplusMediaDataManagerExImpl oplusMediaDataManagerExImpl) {
        return new SpotifyMediaDataManagerStrategy(oplusMediaDataManagerExImpl.context);
    }

    public static MediaDataFlowLimiter $r8$lambda$jh_eAvGPZFt6UUTsFxeCK3vAOrA(OplusMediaDataManagerExImpl oplusMediaDataManagerExImpl) {
        return new MediaDataFlowLimiter(oplusMediaDataManagerExImpl.getMediaDataManager(), oplusMediaDataManagerExImpl.context);
    }

    public OplusMediaDataManagerExImpl(Context context, MediaControllerFactory mediaControllerFactory, DelayableExecutor delayableExecutor, NotificationResolverEx notificationResolverEx, MediaCarouselControllerEx mediaCarouselControllerEx, dagger.Lazy lazy, MediaUiEventLogger mediaUiEventLogger, dagger.Lazy lazy2, OplusMediaPlayerManager oplusMediaPlayerManager, dagger.Lazy lazy3) {
        this.$$delegate_0 = new OplusMediaDataModelImpl(context);
        this.context = context;
        this.mediaControllerFactory = mediaControllerFactory;
        this.foregroundExecutor = delayableExecutor;
        this.notifcationResolverEx = notificationResolverEx;
        this.mediaCarouselControllerEx = mediaCarouselControllerEx;
        this.mediaDataLoaderLazy = lazy;
        this.logger = mediaUiEventLogger;
        this.mediaDataManagerLazy = lazy2;
        this.mediaPlayerDataInfo = oplusMediaPlayerManager;
        this.logBuffer = lazy3;
        final int i = 0;
        OplusMediaDataManagerExImpl$$ExternalSyntheticLambda0 oplusMediaDataManagerExImpl$$ExternalSyntheticLambda0 = new OplusMediaDataManagerExImpl$$ExternalSyntheticLambda0(i);
        oplusMediaDataManagerExImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.spotifyMediaDataManagerStrategy$delegate = LazyKt__LazyJVMKt.lazy(oplusMediaDataManagerExImpl$$ExternalSyntheticLambda0);
        final int i2 = 1;
        OplusMediaDataManagerExImpl$$ExternalSyntheticLambda0 oplusMediaDataManagerExImpl$$ExternalSyntheticLambda1 = new OplusMediaDataManagerExImpl$$ExternalSyntheticLambda0(i2);
        oplusMediaDataManagerExImpl$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusMediaDataManagerStrategy$delegate = LazyKt__LazyJVMKt.lazy(oplusMediaDataManagerExImpl$$ExternalSyntheticLambda1);
        this.oplusNotificationMediaManagerExt$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.media.controls.pipeline.OplusMediaDataManagerExImpl$$ExternalSyntheticLambda2
            public final Object invoke() {
                switch (i) {
                    case 0:
                        OplusMediaDataManagerExImpl.Companion companion = OplusMediaDataManagerExImpl.Companion;
                        return (OplusNotificationMediaManagerExt) DependencyEx.sDependency.getDependency(OplusNotificationMediaManagerExt.class);
                    case 1:
                        OplusMediaDataManagerExImpl.Companion companion2 = OplusMediaDataManagerExImpl.Companion;
                        return MediaActionPrioritySelector.Companion.getInstance();
                    default:
                        OplusMediaDataManagerExImpl.Companion companion3 = OplusMediaDataManagerExImpl.Companion;
                        return Boolean.valueOf(FeatureOption.isOplusMediaControlSupport());
                }
            }
        });
        this.mediaBgHandler = new Handler(MediaSeedlingBgThread.INSTANCE.getThreadLooper());
        final int i3 = 2;
        OplusMediaDataManagerExImpl$$ExternalSyntheticLambda0 oplusMediaDataManagerExImpl$$ExternalSyntheticLambda2 = new OplusMediaDataManagerExImpl$$ExternalSyntheticLambda0(i3);
        oplusMediaDataManagerExImpl$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.mediaDataFlowLimiter$delegate = LazyKt__LazyJVMKt.lazy(oplusMediaDataManagerExImpl$$ExternalSyntheticLambda2);
        this.mediaActionPrioritySelector$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.media.controls.pipeline.OplusMediaDataManagerExImpl$$ExternalSyntheticLambda2
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        OplusMediaDataManagerExImpl.Companion companion = OplusMediaDataManagerExImpl.Companion;
                        return (OplusNotificationMediaManagerExt) DependencyEx.sDependency.getDependency(OplusNotificationMediaManagerExt.class);
                    case 1:
                        OplusMediaDataManagerExImpl.Companion companion2 = OplusMediaDataManagerExImpl.Companion;
                        return MediaActionPrioritySelector.Companion.getInstance();
                    default:
                        OplusMediaDataManagerExImpl.Companion companion3 = OplusMediaDataManagerExImpl.Companion;
                        return Boolean.valueOf(FeatureOption.isOplusMediaControlSupport());
                }
            }
        });
        this.isOplusMediaDataSource$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.media.controls.pipeline.OplusMediaDataManagerExImpl$$ExternalSyntheticLambda2
            public final Object invoke() {
                switch (i3) {
                    case 0:
                        OplusMediaDataManagerExImpl.Companion companion = OplusMediaDataManagerExImpl.Companion;
                        return (OplusNotificationMediaManagerExt) DependencyEx.sDependency.getDependency(OplusNotificationMediaManagerExt.class);
                    case 1:
                        OplusMediaDataManagerExImpl.Companion companion2 = OplusMediaDataManagerExImpl.Companion;
                        return MediaActionPrioritySelector.Companion.getInstance();
                    default:
                        OplusMediaDataManagerExImpl.Companion companion3 = OplusMediaDataManagerExImpl.Companion;
                        return Boolean.valueOf(FeatureOption.isOplusMediaControlSupport());
                }
            }
        });
        this.sbnMap = new LinkedHashMap();
        this.playerInfoMap = new LinkedHashMap();
        this.mediaSessionHolderList = new ArrayList();
        OplusMediaDataManagerExImpl$oplusMediaPlayerInfoCallback$1 oplusMediaDataManagerExImpl$oplusMediaPlayerInfoCallback$1 = new OplusMediaDataManagerExImpl$oplusMediaPlayerInfoCallback$1();
        oplusMediaDataManagerExImpl$oplusMediaPlayerInfoCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.oplusMediaPlayerInfoCallback = oplusMediaDataManagerExImpl$oplusMediaPlayerInfoCallback$1;
    }

    public static final void access$onMediaPlayerInfoAdded(OplusMediaDataManagerExImpl oplusMediaDataManagerExImpl, MediaPlayerDataInfo mediaPlayerDataInfo) {
        OplusNotificationLogger.logMediaPanelNormal(TAG, "onMediaPlayerAdded playerInfo:" + mediaPlayerDataInfo.getOplusMediaPlayerInfo());
        oplusMediaDataManagerExImpl.playerInfoMap.remove(mediaPlayerDataInfo.getOplusMediaPlayerInfo().getPlayerId());
        oplusMediaDataManagerExImpl.playerInfoMap.put(mediaPlayerDataInfo.getOplusMediaPlayerInfo().getPlayerId(), mediaPlayerDataInfo);
        MediaSession.Token token = mediaPlayerDataInfo.getOplusMediaPlayerInfo().getToken();
        token.getClass();
        oplusMediaDataManagerExImpl.updateMediaSession(token);
        oplusMediaDataManagerExImpl.getMediaDataManager().onMediaPlayerAdded(mediaPlayerDataInfo.getOplusMediaPlayerInfo());
    }

    public static final void access$onMediaPlayerInfoRemoved(OplusMediaDataManagerExImpl oplusMediaDataManagerExImpl, MediaPlayerDataInfo mediaPlayerDataInfo) {
        OplusMediaPlayerInfo oplusMediaPlayerInfo;
        MediaSession.Token token;
        OplusNotificationLogger.logMediaPanelNormal(TAG, "onMediaPlayerInfoRemoved playerInfo:" + mediaPlayerDataInfo.getOplusMediaPlayerInfo().getPlayerId());
        MediaPlayerDataInfo mediaPlayerDataInfoRemove = oplusMediaDataManagerExImpl.playerInfoMap.remove(mediaPlayerDataInfo.getOplusMediaPlayerInfo().getPlayerId());
        if (mediaPlayerDataInfoRemove != null && (oplusMediaPlayerInfo = mediaPlayerDataInfoRemove.getOplusMediaPlayerInfo()) != null && (token = oplusMediaPlayerInfo.getToken()) != null) {
            oplusMediaDataManagerExImpl.updateMediaSession(token);
        }
        oplusMediaDataManagerExImpl.getMediaDataManager().onMediaPlayerRemove(mediaPlayerDataInfo.getOplusMediaPlayerInfo());
    }

    public static final void access$updateMediaDataFromMetaData(OplusMediaDataManagerExImpl oplusMediaDataManagerExImpl, String str, MediaMetadata mediaMetadata) {
        if (oplusMediaDataManagerExImpl.getMediaPlayerIndex(str) != -1) {
            Handler handler = oplusMediaDataManagerExImpl.mediaBgHandler;
            OplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1 oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1 = new OplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1(0);
            oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1.this$0 = oplusMediaDataManagerExImpl;
            oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1.$key = str;
            oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1.$metaData = mediaMetadata;
            VarHandle.storeStoreFence();
            handler.post(oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1);
        }
    }

    public static final void access$updateMediaDataFromPlayState(OplusMediaDataManagerExImpl oplusMediaDataManagerExImpl, String str, PlaybackState playbackState) {
        MediaPlayerDataInfo mediaPlayerDataInfo = oplusMediaDataManagerExImpl.playerInfoMap.get(str);
        if (mediaPlayerDataInfo != null) {
            oplusMediaDataManagerExImpl.getMediaDataManager().onMediaPlayerAdded(mediaPlayerDataInfo.getOplusMediaPlayerInfo());
        }
        boolean z = 3 == playbackState.getState();
        int mediaPlayerIndex = oplusMediaDataManagerExImpl.getMediaPlayerIndex(str);
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(mediaPlayerIndex, "updateMediaDataFromPlayState mediaPlayerIndex:", ", key:", str, ", playbackState:");
        sbM.append(playbackState);
        OplusNotificationLogger.logMediaPanelNormal(TAG, sbM.toString());
        if (mediaPlayerIndex != -1 || z) {
            oplusMediaDataManagerExImpl.getMediaDataManager().onStateUpdate(str, playbackState);
        }
    }

    /* JADX WARN: Code duplicated, block: B:22:0x007b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:23:0x007d  */
    /* JADX WARN: Code duplicated, block: B:24:0x0085  */
    /* JADX WARN: Code duplicated, block: B:26:0x0088 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:27:0x008a  */
    /* JADX WARN: Code duplicated, block: B:28:0x0092  */
    /* JADX WARN: Code duplicated, block: B:30:0x0095  */
    /* JADX WARN: Code duplicated, block: B:32:0x009b  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Instruction removed from duplicated block: B:32:0x009b, please report this as an issue */
    public static /* synthetic */ Object getArtWorkIconByMediaMetaData$suspendImpl(OplusMediaDataManagerExImpl oplusMediaDataManagerExImpl, MediaMetadata mediaMetadata, String str, UserHandle userHandle, Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        Bitmap bitmap;
        Icon iconCreateWithBitmap;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = oplusMediaDataManagerExImpl.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = oplusMediaDataManagerExImpl.new AnonymousClass1(continuation);
        }
        Object objLoadBitmapFromUri = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objLoadBitmapFromUri);
            if (mediaMetadata != null) {
                MediaDataLoader mediaDataLoader = (MediaDataLoader) oplusMediaDataManagerExImpl.mediaDataLoaderLazy.get();
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(oplusMediaDataManagerExImpl);
                anonymousClass1.L$1 = mediaMetadata;
                anonymousClass1.L$2 = str;
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(userHandle);
                anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(mediaMetadata);
                anonymousClass1.I$0 = 0;
                anonymousClass1.label = 1;
                objLoadBitmapFromUri = mediaDataLoader.loadBitmapFromUri(mediaMetadata, userHandle, anonymousClass1);
                if (objLoadBitmapFromUri == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                bitmap = null;
            }
            if (bitmap == null) {
                if (mediaMetadata != null) {
                    bitmap = mediaMetadata.getBitmap("android.media.metadata.ART");
                } else {
                    bitmap = null;
                }
            }
            if (bitmap == null) {
                if (mediaMetadata != null) {
                    bitmap = mediaMetadata.getBitmap("android.media.metadata.ALBUM_ART");
                } else {
                    bitmap = null;
                }
            }
            iconCreateWithBitmap = bitmap != null ? Icon.createWithBitmap(bitmap) : null;
            if (iconCreateWithBitmap == null) {
                OplusNotificationLogger.logMediaPanelNormal(TAG, "getArtWorkIconByMediaMetaData: artWorkIcon is null, packageName=" + str);
            }
            return iconCreateWithBitmap;
        }
        if (i2 != 1) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        str = (String) anonymousClass1.L$2;
        MediaMetadata mediaMetadata2 = (MediaMetadata) anonymousClass1.L$1;
        ResultKt.throwOnFailure(objLoadBitmapFromUri);
        mediaMetadata = mediaMetadata2;
        bitmap = (Bitmap) objLoadBitmapFromUri;
        if (bitmap == null) {
            if (mediaMetadata != null) {
                bitmap = mediaMetadata.getBitmap("android.media.metadata.ART");
            } else {
                bitmap = null;
            }
        }
        if (bitmap == null) {
            if (mediaMetadata != null) {
                bitmap = mediaMetadata.getBitmap("android.media.metadata.ALBUM_ART");
            } else {
                bitmap = null;
            }
        }
        if (bitmap != null) {
        }
        if (iconCreateWithBitmap == null) {
            OplusNotificationLogger.logMediaPanelNormal(TAG, "getArtWorkIconByMediaMetaData: artWorkIcon is null, packageName=" + str);
        }
        return iconCreateWithBitmap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaActionPrioritySelector getMediaActionPrioritySelector() {
        return (MediaActionPrioritySelector) this.mediaActionPrioritySelector$delegate.getValue();
    }

    private final MediaDataFlowLimiter getMediaDataFlowLimiter() {
        return (MediaDataFlowLimiter) this.mediaDataFlowLimiter$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaDataManager getMediaDataManager() {
        return (MediaDataManager) this.mediaDataManagerLazy.get();
    }

    private final OplusMediaDataManagerStrategy getOplusMediaDataManagerStrategy() {
        return (OplusMediaDataManagerStrategy) this.oplusMediaDataManagerStrategy$delegate.getValue();
    }

    private final OplusNotificationMediaManagerExt getOplusNotificationMediaManagerExt() {
        return (OplusNotificationMediaManagerExt) this.oplusNotificationMediaManagerExt$delegate.getValue();
    }

    private final SpotifyMediaDataManagerStrategy getSpotifyMediaDataManagerStrategy() {
        return (SpotifyMediaDataManagerStrategy) this.spotifyMediaDataManagerStrategy$delegate.getValue();
    }

    public void addOplusMediaDataCallback(OplusMediaDataManagerEx.OplusMediaDataCallBack oplusMediaDataCallBack) {
        this.$$delegate_0.addOplusMediaDataCallback(oplusMediaDataCallBack);
    }

    public void createActionsFromNotification(NotificationEntry notificationEntry) {
        String strFindExistingEntry;
        StatusBarNotification sbn = notificationEntry.getSbn();
        String packageName = sbn.getPackageName();
        if (packageName == null || (strFindExistingEntry = findExistingEntry(packageName)) == null) {
            return;
        }
        if (LegacyMediaDataManagerImplKt.isMediaNotification(sbn) || isMediaCustomNotification(sbn)) {
            Handler handler = this.mediaBgHandler;
            OplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1 oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1 = new OplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1(1);
            oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1.this$0 = this;
            oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1.$metaData = sbn;
            oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1.$key = strFindExistingEntry;
            VarHandle.storeStoreFence();
            handler.post(oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1);
            return;
        }
        if (getOplusNotificationMediaManagerExt().isMediaNotification(notificationEntry)) {
            Handler handler2 = this.mediaBgHandler;
            OplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1 oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$2 = new OplusMediaDataManagerExImpl$updateMediaDataFromMetaData$1(3);
            oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$2.this$0 = this;
            oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$2.$key = strFindExistingEntry;
            oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$2.$metaData = sbn;
            VarHandle.storeStoreFence();
            handler2.post(oplusMediaDataManagerExImpl$updateMediaDataFromMetaData$2);
        }
    }

    public MediaButton createActionsFromState(String str, MediaController mediaController, MediaFlags mediaFlags, UserHandle userHandle, List<Integer> list, MediaData mediaData) {
        if (str != null) {
            if (!isOplusMediaDataSource()) {
                int size = list.size();
                if (this.notifcationResolverEx.getTargetSdkByPkg(str, userHandle.getIdentifier()) <= 31 && !sessionActionsMediaWhiteList.contains(str) && !AodMediaDataListener.Companion.isAodMediaSupportWithoutFeature(str) && size != 0 && (mediaFlags == null || !mediaFlags.areMediaSessionActionsEnabled(str, userHandle))) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "OplusMediaDataManagerEx-->createActionsFromState, do nothing.");
                    }
                    return null;
                }
            }
            if (getSpotifyMediaDataManagerStrategy().onMatching(str)) {
                return getSpotifyMediaDataManagerStrategy().createActionsFromState(str, mediaData, mediaController);
            }
            if (getOplusMediaDataManagerStrategy().onMatching(str)) {
                return getOplusMediaDataManagerStrategy().createActionsFromState(str, mediaData, mediaController);
            }
        }
        return null;
    }

    public MediaNotificationAction createMediaActionForNotification(StatusBarNotification statusBarNotification, Notification.Action action, Drawable drawable) {
        Icon icon = action.getIcon();
        if (icon != null && icon.getType() == 2) {
            icon = Icon.createWithResource(statusBarNotification.getPackageName(), icon.getResId());
        }
        Icon icon2 = icon;
        boolean zIsAuthenticationRequired = action.isAuthenticationRequired();
        PendingIntent pendingIntent = action.actionIntent;
        return new MediaNotificationAction(zIsAuthenticationRequired, pendingIntent, drawable, action.title, new OplusMediaActionEx(false, icon2, (Uri) null, pendingIntent != null ? pendingIntent.isActivity() : false, (Boolean) null, false, (String) null, 117));
    }

    public void dump(PrintWriter printWriter) {
        MediaPlayerData mediaPlayerData = MediaPlayerData.INSTANCE;
        printWriter.println(StringsKt__IndentKt.trimIndent("\n                OplusMediaDataManagerExImpl:\n                    mediaData=" + mediaPlayerData.mediaData() + "\n                    playerKeys=" + mediaPlayerData.playerKeys() + "\n                    sbnMap=" + this.sbnMap + "\n                    playerInfoMap=" + this.playerInfoMap + "\n                    mediaSessionHolderList=" + this.mediaSessionHolderList + "\n            "));
    }

    public String findExistingEntry(String str) {
        Object next;
        Iterator<T> it = this.playerInfoMap.entrySet().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((MediaPlayerDataInfo) ((Map.Entry) next).getValue()).getOplusMediaPlayerInfo().getPackageName(), str));
        Map.Entry entry = (Map.Entry) next;
        String str2 = entry != null ? (String) entry.getKey() : null;
        OplusNotificationLogger.logMediaPanelNormal(TAG, "findExistingEntry, packageName=" + str + ", foundKey=" + str2);
        return str2 == null ? this.mediaCarouselControllerEx.findExistingEntry(str) : str2;
    }

    public Object getArtWorkIconByMediaMetaData(MediaMetadata mediaMetadata, String str, UserHandle userHandle, Continuation continuation) {
        return getArtWorkIconByMediaMetaData$suspendImpl(this, mediaMetadata, str, userHandle, continuation);
    }

    public List<OplusMediaPlayerInfo> getCurrentPlayerInfoList() {
        return this.$$delegate_0.getCurrentPlayerInfoList();
    }

    public List<OplusMediaPlayerInfo> getHistoryMediaPlayerInfoList() {
        return this.$$delegate_0.getHistoryMediaPlayerInfoList();
    }

    public String getLabelByPkg(String str) {
        return this.notifcationResolverEx.getLabelByPkg(str);
    }

    public int getMediaPlayerIndex(String str) {
        return OplusMediaDataFilterExImpl.MediaPlayerData.INSTANCE.getMediaPlayerIndex(str);
    }

    public String getPlayerId(String str) {
        Object next;
        Iterator<T> it = this.playerInfoMap.entrySet().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((MediaPlayerDataInfo) ((Map.Entry) next).getValue()).getOplusMediaPlayerInfo().getPackageName(), str));
        Map.Entry entry = (Map.Entry) next;
        if (entry != null) {
            return (String) entry.getKey();
        }
        return null;
    }

    public int getPreState(MediaData mediaData) {
        if (mediaData == null) {
            return 0;
        }
        PlaybackState state = mediaData.getMediaDataEx().getState();
        int state2 = state != null ? state.getState() : 0;
        return !NotificationMediaManager.CONNECTING_MEDIA_STATES.contains(Integer.valueOf(state2)) ? state2 : mediaData.getMediaDataEx().getPreState();
    }

    public MediaAction getResumeMediaAction(Runnable runnable, int i) {
        Icon tint = Icon.createWithResource(this.context, 2131234165).setTint(i);
        return new MediaAction(tint.loadDrawable(this.context), runnable, this.context.getString(2131952782), (Drawable) null, (Integer) null, new OplusMediaActionEx(false, tint, (Uri) null, false, (Boolean) null, false, (String) null, 125), 16);
    }

    public MediaSession.Token getSessionToken(StatusBarNotification statusBarNotification, boolean z) {
        return z ? (MediaSession.Token) statusBarNotification.getNotification().extras.getParcelable(OPLUS_SESSION_KEY, MediaSession.Token.class) : (MediaSession.Token) statusBarNotification.getNotification().extras.getParcelable("android.mediaSession", MediaSession.Token.class);
    }

    public int getUserIdForPlayerId(String str) {
        MediaPlayerDataInfo orDefault = str != null ? this.playerInfoMap.getOrDefault(str, null) : null;
        return orDefault != null ? orDefault.getUserId() : OsBinderCacheUtils.getCurrentUserId();
    }

    public void init(MediaDataManager mediaDataManager) {
        super.init(mediaDataManager);
        this.mediaPlayerDataInfo.addMediaPlayerDataInfoCallback(this.oplusMediaPlayerInfoCallback);
    }

    public boolean isMediaCustomNotification(StatusBarNotification statusBarNotification) {
        boolean z = statusBarNotification.getNotification().contentView != null;
        Bundle bundle = statusBarNotification.getNotification().extras;
        Parcelable parcelable = bundle != null ? bundle.getParcelable(OPLUS_SESSION_KEY) : null;
        if (parcelable == null) {
            parcelable = null;
        }
        if (z) {
            if (!(parcelable instanceof MediaSession.Token)) {
                MediaSessionCompat.Token token = parcelable instanceof MediaSessionCompat.Token ? (MediaSessionCompat.Token) parcelable : null;
                if ((token != null ? token.getToken() : null) instanceof MediaSession.Token) {
                }
            }
            return true;
        }
        return false;
    }

    public boolean isOplusCustomMediaNotification(boolean z, NotificationEntry notificationEntry) {
        if (!isOplusMediaDataSource()) {
            return isMediaCustomNotification(notificationEntry.getSbn());
        }
        if (!z && !isMediaCustomNotification(notificationEntry.getSbn())) {
            return false;
        }
        Set<Map.Entry<String, MediaPlayerDataInfo>> setEntrySet = this.playerInfoMap.entrySet();
        if ((setEntrySet instanceof Collection) && setEntrySet.isEmpty()) {
            return false;
        }
        Iterator<T> it = setEntrySet.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(notificationEntry.getSbn().getPackageName(), ((MediaPlayerDataInfo) ((Map.Entry) it.next()).getValue()).getOplusMediaPlayerInfo().getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public final boolean isOplusMediaDataSource() {
        return ((Boolean) this.isOplusMediaDataSource$delegate.getValue()).booleanValue();
    }

    public boolean isPlaybackActive(Integer num) {
        return (num == null || num.intValue() == 7) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x00f2, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r10, r11 != null ? r11.getArtist() : null) == false) goto L60;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.android.systemui.media.controls.models.player.OplusMediaLyricData loadLyricInBg(java.lang.String r6, android.media.MediaMetadata r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, com.android.systemui.media.controls.models.player.OplusMediaLyricData r11) {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.media.controls.pipeline.OplusMediaDataManagerExImpl.loadLyricInBg(java.lang.String, android.media.MediaMetadata, java.lang.String, java.lang.String, java.lang.String, com.android.systemui.media.controls.models.player.OplusMediaLyricData):com.android.systemui.media.controls.models.player.OplusMediaLyricData");
    }

    public boolean needCreateActionsFromNotification(String str, UserHandle userHandle, MediaFlags mediaFlags, boolean z) {
        return true;
    }

    public boolean needLoadMediaData(boolean z, boolean z2, StatusBarNotification statusBarNotification) {
        if (z) {
            return z2 || isMediaCustomNotification(statusBarNotification);
        }
        return false;
    }

    public void onMediaDataLoaded(String str, String str2, MediaData mediaData) {
        Pair<MediaActionPriority, String> mediaActionPriority = ((MediaActionPrioritySelectorImpl) getMediaActionPrioritySelector()).getMediaActionPriority(mediaData.getPackageName(), mediaData.getActions(), mediaData.getSemanticActions());
        MediaActionPriority mediaActionPriority2 = (MediaActionPriority) mediaActionPriority.component1();
        String str3 = (String) mediaActionPriority.component2();
        Pair<MediaActionPriority, String> mediaActionPriorityQs = ((MediaActionPrioritySelectorImpl) getMediaActionPrioritySelector()).getMediaActionPriorityQs(mediaData.getPackageName(), mediaData.getActions(), mediaData.getSemanticActions());
        MediaActionPriority mediaActionPriority3 = (MediaActionPriority) mediaActionPriorityQs.component1();
        String str4 = (String) mediaActionPriorityQs.component2();
        mediaData.getMediaDataEx().setMediaActionPriority(mediaActionPriority2);
        mediaData.getMediaDataEx().setMediaActionPriorityQs(mediaActionPriority3);
        mediaData.getMediaDataEx().setLyricSupport(((MediaActionPrioritySelectorImpl) getMediaActionPrioritySelector()).resolveLyricSupport(mediaData.getPackageName(), mediaData.getMediaDataEx().getMetadata()));
        LogBuffer logBuffer = (LogBuffer) this.logBuffer.get();
        LogLevel logLevel = LogLevel.DEBUG;
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m(" rule: ", str3, ", ruleQs: ", str4, ", OplusMediaDataLoaded: ");
        sbM.append(mediaData);
        logBuffer.log(TAG, logLevel, sbM.toString(), (Throwable) null);
        getMediaDataFlowLimiter().onMediaDataLoaded(str, str2, mediaData);
    }

    public final void onMetaDataChanged(String str, MediaMetadata mediaMetadata) {
        Triple triple;
        String str2;
        String str3;
        MediaSession.Token token;
        OplusMediaButtonEx mediaButtonEx;
        MediaAction heartAction;
        OplusMediaActionEx mediaActionEx;
        OplusMediaButtonEx mediaButtonEx2;
        MediaAction heartAction2;
        OplusMediaActionEx mediaActionEx2;
        if (mediaMetadata == null) {
            OplusNotificationLogger.logMediaPanelDebug(TAG, "onMetaDataChanged error: metaData is null");
            return;
        }
        OplusNotificationLogger.logMediaPanelDebug(TAG, "onMetaDataChanged(MediaStyle) key=" + str);
        MediaData mediaData = getMediaDataManager().getMediaData(str);
        if (mediaData != null) {
            String string = mediaMetadata.getString("android.media.metadata.ARTIST");
            String string2 = mediaMetadata.getString("android.media.metadata.TITLE");
            if (string2 == null) {
                string2 = mediaMetadata.getString("android.media.metadata.DISPLAY_TITLE");
            }
            long j = mediaMetadata.getLong("android.media.metadata.DURATION");
            String str4 = null;
            if (Intrinsics.areEqual(string, mediaData.getArtist()) && Intrinsics.areEqual(string2, mediaData.getSong()) && j == mediaData.getMediaDataEx().getDuration()) {
                triple = null;
            } else {
                StringBuilder sbM = SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(j, "onMetaDataChanged(MediaStyle): ", string2, ", ");
                sbM.append(", ");
                sbM.append(j);
                OplusNotificationLogger.logMediaPanelDebug(TAG, sbM.toString());
                triple = new Triple(string, string2, Long.valueOf(j));
            }
            MediaButton semanticActions = mediaData.getSemanticActions();
            Boolean hasHeartFromRating = (semanticActions == null || (mediaButtonEx2 = semanticActions.getMediaButtonEx()) == null || (heartAction2 = mediaButtonEx2.getHeartAction()) == null || (mediaActionEx2 = heartAction2.getMediaActionEx()) == null) ? null : mediaActionEx2.getHasHeartFromRating();
            boolean z = false;
            if (hasHeartFromRating == null) {
                MediaButton semanticActions2 = mediaData.getSemanticActions();
                OplusNotificationLogger.logMediaPanelDebug(TAG, "needUpdateFavorite: semanticActions not userRating." + (semanticActions2 != null ? semanticActions2.getCustom0() : null));
            } else {
                Rating rating = mediaMetadata.getRating("android.media.metadata.USER_RATING");
                if (rating == null) {
                    rating = mediaMetadata.getRating("android.media.metadata.RATING");
                }
                Boolean boolValueOf = rating != null ? Boolean.valueOf(rating.hasHeart()) : null;
                if (hasHeartFromRating.equals(boolValueOf)) {
                    OplusNotificationLogger.logMediaPanelDebug(TAG, "needUpdateFavorite: hasHeart = " + boolValueOf + ", oldState = " + hasHeartFromRating);
                } else {
                    z = true;
                }
            }
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            if (z && (token = mediaData.getToken()) != null) {
                OplusNotificationLogger.logMediaPanel(TAG, "onMetaDataChanged: favorite state changed");
                OplusMediaDataManagerStrategy oplusMediaDataManagerStrategy = getOplusMediaDataManagerStrategy();
                MediaController mediaControllerCreate = this.mediaControllerFactory.create(token);
                MediaButton semanticActions3 = mediaData.getSemanticActions();
                objectRef.element = oplusMediaDataManagerStrategy.getHeartAction(mediaControllerCreate, (semanticActions3 == null || (mediaButtonEx = semanticActions3.getMediaButtonEx()) == null || (heartAction = mediaButtonEx.getHeartAction()) == null || (mediaActionEx = heartAction.getMediaActionEx()) == null) ? null : mediaActionEx.getIntentUriKey());
            }
            Icon icon = (Icon) BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, new OplusMediaDataManagerExImpl$onMetaDataChanged$1$newArtworkIcon$1(this, mediaMetadata, mediaData, null));
            String packageName = mediaData.getPackageName();
            if (triple == null || (str2 = (String) triple.getSecond()) == null) {
                CharSequence song = mediaData.getSong();
                str2 = song instanceof String ? (String) song : null;
            }
            if (triple == null || (str3 = (String) triple.getFirst()) == null) {
                CharSequence artist = mediaData.getArtist();
                if (artist instanceof String) {
                    str4 = (String) artist;
                }
            } else {
                str4 = str3;
            }
            OplusMediaLyricData oplusMediaLyricDataLoadLyricInBg = loadLyricInBg(str, mediaMetadata, packageName, str2, str4, mediaData.getMediaDataEx().getLyricData());
            ExecutorImpl executorImpl = this.foregroundExecutor;
            OplusMediaDataManagerExImpl$onMetaDataChanged$1$2 oplusMediaDataManagerExImpl$onMetaDataChanged$1$2 = new OplusMediaDataManagerExImpl$onMetaDataChanged$1$2();
            oplusMediaDataManagerExImpl$onMetaDataChanged$1$2.this$0 = this;
            oplusMediaDataManagerExImpl$onMetaDataChanged$1$2.$key = str;
            oplusMediaDataManagerExImpl$onMetaDataChanged$1$2.$heartAction = objectRef;
            oplusMediaDataManagerExImpl$onMetaDataChanged$1$2.$songData = triple;
            oplusMediaDataManagerExImpl$onMetaDataChanged$1$2.$newArtworkIcon = icon;
            oplusMediaDataManagerExImpl$onMetaDataChanged$1$2.$metaData = mediaMetadata;
            oplusMediaDataManagerExImpl$onMetaDataChanged$1$2.$lyric = oplusMediaLyricDataLoadLyricInBg;
            VarHandle.storeStoreFence();
            executorImpl.execute(oplusMediaDataManagerExImpl$onMetaDataChanged$1$2);
        }
    }

    public void onNotificationAdded(String str, StatusBarNotification statusBarNotification) {
        MediaSession.Token oplusCustomMediaToken;
        Notification notification;
        Notification notification2;
        if (!LegacyMediaDataManagerImplKt.isMediaNotification(statusBarNotification) && !isMediaCustomNotification(statusBarNotification)) {
            onNotificationRemoved(str, "onNotificationAdded");
            return;
        }
        StatusBarNotification statusBarNotificationRemove = this.sbnMap.remove(str);
        this.sbnMap.put(str, statusBarNotification);
        Object obj = null;
        if (statusBarNotificationRemove == null || (notification2 = statusBarNotificationRemove.getNotification()) == null || (oplusCustomMediaToken = MediaNotificationExtKt.getMediaToken(notification2)) == null) {
            oplusCustomMediaToken = (statusBarNotificationRemove == null || (notification = statusBarNotificationRemove.getNotification()) == null) ? null : MediaNotificationExtKt.getOplusCustomMediaToken(notification);
        }
        MediaSession.Token mediaToken = MediaNotificationExtKt.getMediaToken(statusBarNotification.getNotification());
        if (mediaToken == null) {
            mediaToken = MediaNotificationExtKt.getOplusCustomMediaToken(statusBarNotification.getNotification());
        }
        if (!Intrinsics.areEqual(oplusCustomMediaToken, mediaToken) && oplusCustomMediaToken != null) {
            OplusNotificationLogger.logMediaPanelNormal(TAG, ConstraintSet$.ExternalSyntheticOutline0.m(statusBarNotification.getUser().getIdentifier(), "onNotificationAdded pkg=", statusBarNotification.getPackageName(), ",userid=", " token changed"));
            for (Object obj2 : this.mediaSessionHolderList) {
                MediaSessionHolder mediaSessionHolder = (MediaSessionHolder) obj2;
                if (Intrinsics.areEqual(mediaSessionHolder.getToken(), oplusCustomMediaToken) && Intrinsics.areEqual(str, mediaSessionHolder.getKey())) {
                    obj = obj2;
                    break;
                }
            }
            MediaSessionHolder mediaSessionHolder2 = (MediaSessionHolder) obj;
            OplusNotificationLogger.logMediaPanelNormal(TAG, "removeMediaSession key=" + str + " token=" + oplusCustomMediaToken + ", oldHolder=" + mediaSessionHolder2);
            if (mediaSessionHolder2 != null) {
                mediaSessionHolder2.getMediaController().unregisterCallback(mediaSessionHolder2.getMediaControllerCallback());
                this.mediaSessionHolderList.remove(mediaSessionHolder2);
            }
        }
        OplusNotificationLogger.logMediaPanelNormal(TAG, "onNotificationAdded media key=" + str + ",token=" + mediaToken);
        if (mediaToken != null) {
            updateMediaSession(mediaToken);
        }
    }

    public void onNotificationRemoved(String str, String str2) {
        MediaSession.Token oplusCustomMediaToken;
        StatusBarNotification statusBarNotificationRemove = this.sbnMap.remove(str);
        if (statusBarNotificationRemove != null) {
            OplusNotificationLogger.logMediaPanelNormal(TAG, "onNotificationRemoved media key=" + str + ", sbn=" + statusBarNotificationRemove + ", reason=" + str2);
        }
        if (statusBarNotificationRemove != null) {
            Notification notification = statusBarNotificationRemove.getNotification();
            if (notification == null || (oplusCustomMediaToken = MediaNotificationExtKt.getMediaToken(notification)) == null) {
                Notification notification2 = statusBarNotificationRemove.getNotification();
                oplusCustomMediaToken = notification2 != null ? MediaNotificationExtKt.getOplusCustomMediaToken(notification2) : null;
            }
            if (oplusCustomMediaToken != null) {
                updateMediaSession(oplusCustomMediaToken);
            }
        }
    }

    public void removeOplusMediaDataCallback(OplusMediaDataManagerEx.OplusMediaDataCallBack oplusMediaDataCallBack) {
        this.$$delegate_0.removeOplusMediaDataCallback(oplusMediaDataCallBack);
    }

    public void requestFavoriteState(String str) {
        this.$$delegate_0.requestFavoriteState(str);
    }

    public void requestMediaMetadata(String str) {
        this.$$delegate_0.requestMediaMetadata(str);
    }

    public void requestPlaybackState(String str) {
        this.$$delegate_0.requestPlaybackState(str);
    }

    public void sendEvent(OplusMediaEvent oplusMediaEvent) {
        this.$$delegate_0.sendEvent(oplusMediaEvent);
    }

    public void setMediaControlBlackList(List<String> list) {
        this.$$delegate_0.setMediaControlBlackList(list);
    }

    public final void tryAddMediaSession(String str, final MediaSession.Token token) {
        Object next;
        Iterator<T> it = this.mediaSessionHolderList.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((MediaSessionHolder) next).getToken(), token));
        MediaSessionHolder mediaSessionHolder = (MediaSessionHolder) next;
        OplusNotificationLogger.logMediaPanelNormal(TAG, "tryAddMediaSession media key=" + str + ", token=" + token + ", oldHolder=" + mediaSessionHolder);
        if (mediaSessionHolder != null) {
            if (Intrinsics.areEqual(mediaSessionHolder.getKey(), str)) {
                return;
            }
            MediaSessionHolder mediaSessionHolderCopy$default = MediaSessionHolder.copy$default(mediaSessionHolder, str);
            this.mediaSessionHolderList.remove(mediaSessionHolder);
            this.mediaSessionHolderList.add(mediaSessionHolderCopy$default);
            return;
        }
        MediaController mediaController = new MediaController(this.context, token);
        MediaController.Callback callback = new MediaController.Callback() { // from class: com.oplus.systemui.media.controls.pipeline.OplusMediaDataManagerExImpl$tryAddMediaSession$callback$1
            @Override // android.media.session.MediaController.Callback
            public final void onMetadataChanged(MediaMetadata mediaMetadata) {
                Object next2;
                String key;
                if (mediaMetadata != null) {
                    OplusMediaDataManagerExImpl oplusMediaDataManagerExImpl = this.this$0;
                    MediaSession.Token token2 = token;
                    Iterator it2 = oplusMediaDataManagerExImpl.mediaSessionHolderList.iterator();
                    do {
                        if (!it2.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it2.next();
                    } while (!Intrinsics.areEqual(((OplusMediaDataManagerExImpl.MediaSessionHolder) next2).getToken(), token2));
                    OplusMediaDataManagerExImpl.MediaSessionHolder mediaSessionHolder2 = (OplusMediaDataManagerExImpl.MediaSessionHolder) next2;
                    if (mediaSessionHolder2 == null || (key = mediaSessionHolder2.getKey()) == null) {
                        return;
                    }
                    OplusMediaDataManagerExImpl.access$updateMediaDataFromMetaData(oplusMediaDataManagerExImpl, key, mediaMetadata);
                }
            }

            @Override // android.media.session.MediaController.Callback
            public final void onPlaybackStateChanged(PlaybackState playbackState) {
                Object next2;
                String key;
                if (playbackState != null) {
                    OplusMediaDataManagerExImpl oplusMediaDataManagerExImpl = this.this$0;
                    MediaSession.Token token2 = token;
                    Iterator it2 = oplusMediaDataManagerExImpl.mediaSessionHolderList.iterator();
                    do {
                        if (!it2.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it2.next();
                    } while (!Intrinsics.areEqual(((OplusMediaDataManagerExImpl.MediaSessionHolder) next2).getToken(), token2));
                    OplusMediaDataManagerExImpl.MediaSessionHolder mediaSessionHolder2 = (OplusMediaDataManagerExImpl.MediaSessionHolder) next2;
                    if (mediaSessionHolder2 == null || (key = mediaSessionHolder2.getKey()) == null) {
                        return;
                    }
                    OplusMediaDataManagerExImpl.access$updateMediaDataFromPlayState(oplusMediaDataManagerExImpl, key, playbackState);
                }
            }
        };
        mediaController.registerCallback(callback);
        this.mediaSessionHolderList.add(new MediaSessionHolder(str, token, mediaController, callback));
    }

    public final void updateMediaSession(MediaSession.Token token) {
        Object obj;
        Object next;
        Object next2;
        MediaSession.Token mediaToken;
        Iterator<T> it = this.sbnMap.entrySet().iterator();
        do {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            Map.Entry entry = (Map.Entry) next;
            mediaToken = MediaNotificationExtKt.getMediaToken(((StatusBarNotification) entry.getValue()).getNotification());
            if (mediaToken == null) {
                mediaToken = MediaNotificationExtKt.getOplusCustomMediaToken(((StatusBarNotification) entry.getValue()).getNotification());
            }
        } while (!Intrinsics.areEqual(mediaToken, token));
        Map.Entry entry2 = (Map.Entry) next;
        if (entry2 != null) {
            String key = ((StatusBarNotification) entry2.getValue()).getKey();
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("update media session from sbn,key=", key, ", ", ((StatusBarNotification) entry2.getValue()).getPackageName(), " token=");
            sbM.append(token);
            OplusNotificationLogger.logMediaPanelNormal(TAG, sbM.toString());
            key.getClass();
            tryAddMediaSession(key, token);
            return;
        }
        Iterator<T> it2 = this.playerInfoMap.entrySet().iterator();
        do {
            if (!it2.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it2.next();
        } while (!Intrinsics.areEqual(((MediaPlayerDataInfo) ((Map.Entry) next2).getValue()).getOplusMediaPlayerInfo().getToken(), token));
        Map.Entry entry3 = (Map.Entry) next2;
        if (entry3 != null) {
            OplusNotificationLogger.logMediaPanelNormal(TAG, "update media session from playerId, " + ((MediaPlayerDataInfo) entry3.getValue()).getOplusMediaPlayerInfo().getPackageName() + " token=" + token);
            tryAddMediaSession(((MediaPlayerDataInfo) entry3.getValue()).getOplusMediaPlayerInfo().getPlayerId(), token);
            return;
        }
        for (Object obj2 : this.mediaSessionHolderList) {
            if (Intrinsics.areEqual(((MediaSessionHolder) obj2).getToken(), token)) {
                obj = obj2;
                break;
            }
        }
        MediaSessionHolder mediaSessionHolder = (MediaSessionHolder) obj;
        OplusNotificationLogger.logMediaPanelNormal(TAG, "removeMediaSession from token=" + token + ", oldHolder=" + mediaSessionHolder);
        if (mediaSessionHolder != null) {
            mediaSessionHolder.getMediaController().unregisterCallback(mediaSessionHolder.getMediaControllerCallback());
            this.mediaSessionHolderList.remove(mediaSessionHolder);
        }
    }
}
