package com.oplus.systemui.media.seedling.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.media.MediaDataExtKt;
import com.android.systemui.media.controls.models.player.OplusMediaActionEx;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.controls.shared.model.MediaDeviceData;
import com.android.systemui.media.dialog.MediaOutputDialogManager;
import com.android.systemui.oplusutils.OsBinderCacheUtils;
import com.android.systemui.plugins.ActivityStarter;
import com.oplus.systemui.media.controls.ui.MediaActionBinderKt;
import com.oplus.systemui.media.controls.ui.OplusMediaActions;
import com.oplus.systemui.media.seedling.OplusSeedlingMediaDataCombineLatest;
import com.oplus.systemui.media.seedling.client.loader.album.IAlbumLoader;
import com.oplus.systemui.media.seedling.client.loader.appicon.IAppIconLoader;
import com.oplus.systemui.media.seedling.utils.FeatureOption;
import com.oplus.systemui.media.seedling.utils.FileUtil;
import com.oplus.systemui.media.seedling.utils.HandlerExtKt;
import com.oplus.systemui.media.seedling.utils.StatisticsManager;
import com.oplus.systemui.seedlingservice.R$string;
import com.oplus.systemui.seedlingservice.mediaControl.IconMapsTuple;
import com.oplus.systemui.seedlingservice.mediaControl.LyricData;
import com.oplus.systemui.seedlingservice.mediaControl.MediaAction;
import com.oplus.systemui.seedlingservice.mediaControl.MediaActionRunnable;
import com.oplus.systemui.seedlingservice.mediaControl.MediaButton;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import com.oplus.systemui.seedlingservice.utils.UriExtKt;
import com.oplus.wrapper.os.UserHandle;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.HashMap;
import java.util.Map;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class AbsMediaClient {
    public static final Companion Companion = new Companion();
    private static final String ROTATE_LEFT = "rotate-l";
    private static final String ROTATE_RIGHT = "rotate-r";
    private static final String TAG = "AbsMediaClient";
    private final ActivityStarter activityStarter;
    private final IAlbumLoader albumLoader;
    private final IAppIconLoader appIconLoader;
    private final Handler bgHandler;
    private MediaSession.Token cacheToken;
    private MediaDeviceData cachedDevice;
    private OplusMediaActions cachedMediaActions;
    private final Context context;
    public int custom0Count;
    public int custom1Count;
    public int deviceCount;
    public boolean isDestroyed;
    public boolean isFirstInit;
    private final Runnable loadArtworkAndUpdateTask;
    private final Handler mainHandler;
    private volatile MediaController mediaController;
    private final MediaController.Callback mediaControllerCallback = new MediaController.Callback() { // from class: com.oplus.systemui.media.seedling.client.AbsMediaClient$mediaControllerCallback$1
        @Override // android.media.session.MediaController.Callback
        public final void onPlaybackStateChanged(PlaybackState playbackState) {
            Long lExecutePlaybackStateChangedTaskTimeThreshold = this.this$0.executePlaybackStateChangedTaskTimeThreshold();
            if (lExecutePlaybackStateChangedTaskTimeThreshold == null || lExecutePlaybackStateChangedTaskTimeThreshold.longValue() <= 0) {
                HandlerExtKt.runTask(this.this$0.getMainHandler(), this.this$0.getPlaybackStateChangedTask());
            } else {
                HandlerExtKt.runTaskDelayed(this.this$0.getMainHandler(), this.this$0.getPlaybackStateChangedTask(), lExecutePlaybackStateChangedTaskTimeThreshold.longValue());
            }
        }
    };
    private SeedlingMediaData mediaData;
    private final OplusSeedlingMediaDataCombineLatest mediaDataCombineLatest;
    public int nextCount;
    private Icon oldArtwork;
    private final MediaOutputDialogManager outputDialogFactory;
    public int playOrPauseCount;
    private final Runnable playbackStateChangedTask;
    public int preCount;
    private final CoroutineDispatcher seedlingBgDispatcher;
    private final CoroutineScope seedlingBgScope;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.media.seedling.client.AbsMediaClient$onDestroy$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ AbsMediaClient this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // java.lang.Runnable
        public final void run() {
            Boolean bool = null;
            switch (this.$r8$classId) {
                case 0:
                    MediaController mediaController = this.this$0.getMediaController();
                    if (mediaController != null) {
                        mediaController.unregisterCallback(this.this$0.getMediaControllerCallback());
                    }
                    break;
                case 1:
                    AbsMediaClient absMediaClient = this.this$0;
                    if (!absMediaClient.isDestroyed) {
                        OplusNotificationLogger.logMediaSeedling(AbsMediaClient.TAG, "loadArtworkAndUpdate start");
                        Triple<Object, Object, Boolean> tripleLoad = absMediaClient.getAlbumLoader().load(absMediaClient.getMediaData().getPackageName(), absMediaClient.getMediaData().getPlayerId(), absMediaClient.getMediaData());
                        Object objComponent1 = tripleLoad.component1();
                        Object objComponent2 = tripleLoad.component2();
                        boolean zBooleanValue = ((Boolean) tripleLoad.component3()).booleanValue();
                        if (objComponent1 != null) {
                            bool = Boolean.TRUE;
                        } else if (zBooleanValue) {
                            bool = Boolean.FALSE;
                        }
                        if (bool != null) {
                            boolean zBooleanValue2 = bool.booleanValue();
                            Handler mainHandler = absMediaClient.getMainHandler();
                            AbsMediaClient$loadArtworkAndUpdate$1$1 absMediaClient$loadArtworkAndUpdate$1$1 = new AbsMediaClient$loadArtworkAndUpdate$1$1();
                            absMediaClient$loadArtworkAndUpdate$1$1.this$0 = absMediaClient;
                            absMediaClient$loadArtworkAndUpdate$1$1.$artworkUri = objComponent1;
                            absMediaClient$loadArtworkAndUpdate$1$1.$albumBgColor = objComponent2;
                            absMediaClient$loadArtworkAndUpdate$1$1.$it = zBooleanValue2;
                            VarHandle.storeStoreFence();
                            mainHandler.post(absMediaClient$loadArtworkAndUpdate$1$1);
                        }
                        break;
                    }
                    break;
                default:
                    AbsMediaClient absMediaClient2 = this.this$0;
                    MediaController mediaController2 = absMediaClient2.getMediaController();
                    absMediaClient2.updatePositionByPlaybackState(mediaController2 != null ? mediaController2.getPlaybackState() : null, true);
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.media.seedling.client.AbsMediaClient$onMediaDataLoaded$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C01801 extends SuspendLambda implements Function2 {
        final /* synthetic */ MediaData $data;
        final /* synthetic */ Ref.LongRef $newDuration;
        final /* synthetic */ MediaSession.Token $newToken;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01801(MediaData mediaData, MediaSession.Token token, Ref.LongRef longRef, Continuation continuation) {
            super(2, continuation);
            this.$data = mediaData;
            this.$newToken = token;
            this.$newDuration = longRef;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            C01801 c01801 = AbsMediaClient.this.new C01801(this.$data, this.$newToken, this.$newDuration, continuation);
            c01801.L$0 = obj;
            return c01801;
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object obj2;
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    AbsMediaClient absMediaClient = AbsMediaClient.this;
                    MediaData mediaData = this.$data;
                    MediaSession.Token token = this.$newToken;
                    long j = this.$newDuration.element;
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.I$0 = 0;
                    this.label = 1;
                    Object objWithContext = BuildersKt.withContext(absMediaClient.getSeedlingBgDispatcher(), new AbsMediaClient$handleBgMediaDataLoaded$2(absMediaClient, token, j, mediaData, null), this);
                    if (objWithContext != IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        objWithContext = Unit.INSTANCE;
                    }
                    if (objWithContext == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                obj2 = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                obj2 = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj2);
            if (th2 != null) {
                OplusNotificationLogger.logMediaSeedlingDebug(AbsMediaClient.TAG, "handleBgMediaDataLoaded failed: " + th2.getMessage());
                th2.printStackTrace();
            }
            return Unit.INSTANCE;
        }
    }

    public AbsMediaClient(Context context, SeedlingMediaData seedlingMediaData, ActivityStarter activityStarter, MediaOutputDialogManager mediaOutputDialogManager, OplusSeedlingMediaDataCombineLatest oplusSeedlingMediaDataCombineLatest, IAppIconLoader iAppIconLoader, IAlbumLoader iAlbumLoader, Handler handler, Handler handler2, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope) {
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
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(2);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.playbackStateChangedTask = anonymousClass1;
        AnonymousClass1 anonymousClass2 = new AnonymousClass1(1);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.loadArtworkAndUpdateTask = anonymousClass2;
    }

    public static final void access$bindMediaController(AbsMediaClient absMediaClient, MediaSession.Token token) {
        if (absMediaClient.getMediaData().isSpotifyWhichSupportsOplusMediaController() || Intrinsics.areEqual(absMediaClient.getCacheToken(), token)) {
            return;
        }
        OplusNotificationLogger.logMediaSeedling(TAG, "bindMediaController cacheToken: " + absMediaClient.getCacheToken() + " token: " + token);
        absMediaClient.setCacheToken(token);
        MediaController mediaController = absMediaClient.mediaController;
        if (mediaController != null) {
            mediaController.unregisterCallback(absMediaClient.mediaControllerCallback);
        }
        absMediaClient.mediaController = token != null ? new MediaController(absMediaClient.getContext(), token) : null;
        MediaController mediaController2 = absMediaClient.mediaController;
        if (mediaController2 != null) {
            mediaController2.registerCallback(absMediaClient.mediaControllerCallback);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v7 */
    public static final Object access$bindSeedlingMediaActionButtons(AbsMediaClient absMediaClient, MediaData mediaData, ContinuationImpl continuationImpl) {
        AbsMediaClient$bindSeedlingMediaActionButtons$1 absMediaClient$bindSeedlingMediaActionButtons$1;
        OplusMediaActions oplusMediaActions;
        boolean z;
        absMediaClient.getClass();
        if (continuationImpl instanceof AbsMediaClient$bindSeedlingMediaActionButtons$1) {
            absMediaClient$bindSeedlingMediaActionButtons$1 = (AbsMediaClient$bindSeedlingMediaActionButtons$1) continuationImpl;
            int i = absMediaClient$bindSeedlingMediaActionButtons$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                absMediaClient$bindSeedlingMediaActionButtons$1.label = i - Integer.MIN_VALUE;
            } else {
                absMediaClient$bindSeedlingMediaActionButtons$1 = new AbsMediaClient$bindSeedlingMediaActionButtons$1(absMediaClient, continuationImpl);
            }
        } else {
            absMediaClient$bindSeedlingMediaActionButtons$1 = new AbsMediaClient$bindSeedlingMediaActionButtons$1(absMediaClient, continuationImpl);
        }
        Object objGenerateIconMaps = absMediaClient$bindSeedlingMediaActionButtons$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = absMediaClient$bindSeedlingMediaActionButtons$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objGenerateIconMaps);
            ?? r1 = OsBinderCacheUtils.getCurrentUserId() == 0 ? 1 : 0;
            OplusMediaActions oplusMediaActionsCreateOplusMediaActions = MediaActionBinderKt.createOplusMediaActions(absMediaClient.getContext(), mediaData, r1, absMediaClient.getActivityStarter(), absMediaClient.getOutputDialogFactory());
            if (oplusMediaActionsCreateOplusMediaActions.equalWithContent(absMediaClient.cachedMediaActions)) {
                absMediaClient.getMediaData().setMediaButtonUriChanged(false);
            } else {
                OplusNotificationLogger.logMediaSeedling(TAG, "bindSeedlingMediaActionButtons mediaAction=" + oplusMediaActionsCreateOplusMediaActions);
                absMediaClient.cachedMediaActions = oplusMediaActionsCreateOplusMediaActions;
                absMediaClient.getMediaData().setMediaButtonUriChanged(true);
                boolean zIsPlayState = SeedlingMediaData.Companion.isPlayState(absMediaClient.getMediaData().getState());
                FeatureOption.INSTANCE.getClass();
                if (FeatureOption.isMediaUMSEnabled) {
                    absMediaClient$bindSeedlingMediaActionButtons$1.L$0 = SpillingKt.nullOutSpilledVariable(mediaData);
                    absMediaClient$bindSeedlingMediaActionButtons$1.L$1 = oplusMediaActionsCreateOplusMediaActions;
                    absMediaClient$bindSeedlingMediaActionButtons$1.I$0 = r1;
                    absMediaClient$bindSeedlingMediaActionButtons$1.Z$0 = zIsPlayState;
                    absMediaClient$bindSeedlingMediaActionButtons$1.label = 1;
                    objGenerateIconMaps = absMediaClient.generateIconMaps(oplusMediaActionsCreateOplusMediaActions, absMediaClient$bindSeedlingMediaActionButtons$1);
                    if (objGenerateIconMaps == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    oplusMediaActions = oplusMediaActionsCreateOplusMediaActions;
                    z = zIsPlayState;
                } else {
                    absMediaClient.getMediaData().setMediaButton(new MediaButton(absMediaClient.getMediaData().getPlayerId(), absMediaClient.createPlayPauseAction(absMediaClient.getSimpleIcon(oplusMediaActionsCreateOplusMediaActions.getPlayPauseOrCustom()), oplusMediaActionsCreateOplusMediaActions, new HashMap(), zIsPlayState), absMediaClient.createNextAction(new HashMap(), oplusMediaActionsCreateOplusMediaActions, absMediaClient.getSimpleIcon(oplusMediaActionsCreateOplusMediaActions.getNextOrCustom())), absMediaClient.createPrevAction(new HashMap(), oplusMediaActionsCreateOplusMediaActions, absMediaClient.getSimpleIcon(oplusMediaActionsCreateOplusMediaActions.getPreOrCustom())), absMediaClient.createCustom0Action(absMediaClient.getSimpleIcon(oplusMediaActionsCreateOplusMediaActions.getAction0()), oplusMediaActionsCreateOplusMediaActions, new HashMap(), false), absMediaClient.createCustom1Action(absMediaClient.getSimpleIcon(oplusMediaActionsCreateOplusMediaActions.getAction1()), oplusMediaActionsCreateOplusMediaActions, new HashMap(), false)));
                }
            }
            return Unit.INSTANCE;
        }
        if (i2 != 1) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        z = absMediaClient$bindSeedlingMediaActionButtons$1.Z$0;
        oplusMediaActions = (OplusMediaActions) absMediaClient$bindSeedlingMediaActionButtons$1.L$1;
        ResultKt.throwOnFailure(objGenerateIconMaps);
        IconMapsTuple iconMapsTuple = (IconMapsTuple) objGenerateIconMaps;
        absMediaClient.getMediaData().setMediaButton(new MediaButton(absMediaClient.getMediaData().getPlayerId(), absMediaClient.createPlayPauseAction(null, oplusMediaActions, iconMapsTuple.getPlayPause(), z), absMediaClient.createNextAction(iconMapsTuple.getNext(), oplusMediaActions, null), absMediaClient.createPrevAction(iconMapsTuple.getPre(), oplusMediaActions, null), absMediaClient.createCustom0Action(null, oplusMediaActions, iconMapsTuple.getCustom0(), true), absMediaClient.createCustom1Action(null, oplusMediaActions, iconMapsTuple.getCustom1(), true)));
        return Unit.INSTANCE;
    }

    public static HashMap createMapFromUri(Pair pair) {
        return MapsKt__MapsKt.hashMapOf(new Pair[]{TuplesKt.to(MediaAction.ICON_DEFAULT, new Pair((Object) null, pair.getFirst())), TuplesKt.to(MediaAction.ICON_SECOND_HOME, new Pair((Object) null, pair.getSecond()))});
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [com.oplus.systemui.media.seedling.client.AbsMediaClient$createMediaActionRunnable$1] */
    public static AbsMediaClient$createMediaActionRunnable$1 createMediaActionRunnable$default(final AbsMediaClient absMediaClient, final String str, final Runnable runnable, final Integer num, String str2, int i) {
        final boolean z = (i & 8) == 0;
        final boolean z2 = (i & 16) == 0;
        if ((i & 32) != 0) {
            str2 = "";
        }
        final String str3 = str2;
        return new MediaActionRunnable() { // from class: com.oplus.systemui.media.seedling.client.AbsMediaClient$createMediaActionRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                OplusNotificationLogger.logMediaSeedling("AbsMediaClient", "click " + str + ", runnable=" + runnable);
                runnable.run();
                if (z) {
                    absMediaClient.getMediaDataCombineLatest().tempSuppressMediaListSort();
                }
                if (z || z2) {
                    absMediaClient.tempSuppressSeekBarUpdate();
                }
                absMediaClient.getMediaData().setAlbumRotationDirection(str3);
                Integer num2 = num;
                if (num2 != null) {
                    StatisticsManager.Companion.getInstance().doUpload(num2.intValue(), getCardEntrance(), StatisticsManager.UPLOAD_VALUE_DEFAULT, StatisticsManager.EVENT_ID_MEDIA_ACTION);
                }
            }
        };
    }

    public static boolean isUriNullInMap(Map map) {
        Pair pair = (Pair) map.get(MediaAction.ICON_DEFAULT);
        return (pair != null ? (Uri) pair.getSecond() : null) == null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    public final Object setMediaDeviceIcon(Continuation continuation) {
        setMediaDeviceIcon.1 r0;
        Drawable icon;
        SeedlingMediaData seedlingMediaData;
        if (continuation instanceof setMediaDeviceIcon.1) {
            r0 = (setMediaDeviceIcon.1) continuation;
            int i = r0.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                r0.label = i - Integer.MIN_VALUE;
            } else {
                r0 = new setMediaDeviceIcon.1(this, continuation);
            }
        } else {
            r0 = new setMediaDeviceIcon.1(this, continuation);
        }
        setMediaDeviceIcon.1 r6 = r0;
        Object objConvertDrawableToUri = r6.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = r6.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objConvertDrawableToUri);
            MediaDeviceData mediaDeviceData = this.cachedDevice;
            if (mediaDeviceData == null || (drawable = mediaDeviceData.getIconWithoutTheme()) == null) {
                icon = mediaDeviceData != null ? mediaDeviceData.getIcon() : null;
                if (icon == null) {
                    Drawable drawable = getContext().getDrawable(2131237113);
                    icon = drawable;
                }
                return Unit.INSTANCE;
            }
            icon = drawable;
            if (icon != null) {
                SeedlingMediaData mediaData = getMediaData();
                Context context = getContext();
                int i3 = this.deviceCount;
                this.deviceCount = i3 + 1;
                CoroutineDispatcher seedlingBgDispatcher = getSeedlingBgDispatcher();
                r6.L$0 = SpillingKt.nullOutSpilledVariable(mediaDeviceData);
                r6.L$1 = SpillingKt.nullOutSpilledVariable(icon);
                r6.L$2 = SpillingKt.nullOutSpilledVariable(icon);
                r6.L$3 = mediaData;
                r6.I$0 = 0;
                r6.label = 1;
                objConvertDrawableToUri = FileUtil.convertDrawableToUri(context, icon, "device", i3, seedlingBgDispatcher, r6);
                if (objConvertDrawableToUri == coroutine_suspended) {
                    return coroutine_suspended;
                }
                seedlingMediaData = mediaData;
            }
            return Unit.INSTANCE;
        }
        if (i2 != 1) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        seedlingMediaData = (SeedlingMediaData) r6.L$3;
        ResultKt.throwOnFailure(objConvertDrawableToUri);
        seedlingMediaData.setDeviceIconUri((Uri) ((Pair) objConvertDrawableToUri).getFirst());
        return Unit.INSTANCE;
    }

    public MediaButton createActionsFromState(PlaybackState playbackState) {
        AbsMediaClient absMediaClient;
        MediaAction standardAction = SeedlingMediaData.Companion.isPlayState(playbackState.getState()) ? getStandardAction(playbackState.getActions(), 2L, MediaButton.BTN_NAME_PLAY_OR_PAUSE) : getStandardAction(playbackState.getActions(), 4L, MediaButton.BTN_NAME_PLAY_OR_PAUSE);
        MediaAction standardAction2 = getStandardAction(playbackState.getActions(), 32L, MediaButton.BTN_NAME_NEXT_OR_CUSTOM);
        MediaAction standardAction3 = getStandardAction(playbackState.getActions(), 16L, MediaButton.BTN_NAME_PREV_OR_CUSTOM);
        Uri deviceIconUri = getMediaData().getDeviceIconUri();
        MediaAction mediaAction = null;
        if (OsBinderCacheUtils.getCurrentUserId() == UserHandle.USER_SYSTEM) {
            HashMap mapHashMapOf = MapsKt__MapsKt.hashMapOf(new Pair[]{TuplesKt.to(MediaAction.ICON_DEFAULT, new Pair((Object) null, deviceIconUri))});
            String deviceNameLabel = getMediaData().getDeviceNameLabel();
            if (deviceNameLabel == null) {
                deviceNameLabel = "";
            }
            MediaAction mediaAction2 = new MediaAction(MediaButton.BTN_NAME_CUSTOM1, mapHashMapOf, null, deviceNameLabel, false, null, 52, null);
            absMediaClient = this;
            mediaAction2.setAction(new createPlayAction.1.1(absMediaClient, 1));
            mediaAction = mediaAction2;
        } else {
            absMediaClient = this;
        }
        return new MediaButton(absMediaClient.getMediaData().getPlayerId(), standardAction, standardAction2, standardAction3, null, mediaAction);
    }

    public final MediaAction createCustom0Action(Icon icon, OplusMediaActions oplusMediaActions, HashMap map, boolean z) {
        if (z && isUriNullInMap(map)) {
            return null;
        }
        com.android.systemui.media.controls.shared.model.MediaAction action0 = oplusMediaActions.getAction0();
        Runnable action = action0 != null ? action0.getAction() : null;
        if (action == null) {
            OplusNotificationLogger.logMediaSeedling(TAG, "createCustom0Action runnable is null");
            return null;
        }
        CharSequence contentDescription = oplusMediaActions.getAction0().getContentDescription();
        String string = contentDescription != null ? contentDescription.toString() : null;
        if (string == null) {
            string = "";
        }
        MediaAction mediaAction = new MediaAction(MediaButton.BTN_NAME_CUSTOM0, map, null, string, false, icon, 20, null);
        mediaAction.setAction(createMediaActionRunnable$default(this, mediaAction.getButtonName(), action, null, null, 56));
        return mediaAction;
    }

    public final MediaAction createCustom1Action(Icon icon, OplusMediaActions oplusMediaActions, HashMap map, boolean z) {
        if (z && isUriNullInMap(map)) {
            return null;
        }
        com.android.systemui.media.controls.shared.model.MediaAction action1 = oplusMediaActions.getAction1();
        Runnable action = action1 != null ? action1.getAction() : null;
        if (action == null) {
            OplusNotificationLogger.logMediaSeedling(TAG, "createCustom1Action runnable is null");
            return null;
        }
        CharSequence contentDescription = oplusMediaActions.getAction1().getContentDescription();
        String string = contentDescription != null ? contentDescription.toString() : null;
        if (string == null) {
            string = "";
        }
        MediaAction mediaAction = new MediaAction(MediaButton.BTN_NAME_CUSTOM1, map, null, string, false, icon, 20, null);
        mediaAction.setAction(createMediaActionRunnable$default(this, mediaAction.getButtonName(), action, 5, null, 56));
        return mediaAction;
    }

    public final MediaAction createNextAction(HashMap map, OplusMediaActions oplusMediaActions, Icon icon) {
        com.android.systemui.media.controls.shared.model.MediaAction nextOrCustom = oplusMediaActions.getNextOrCustom();
        Runnable action = nextOrCustom != null ? nextOrCustom.getAction() : null;
        if (action == null) {
            OplusNotificationLogger.logMediaSeedling(TAG, "createNextAction runnable is null");
            return null;
        }
        if (isUriNullInMap(map)) {
            map = MapsKt__MapsKt.hashMapOf(new Pair[]{TuplesKt.to(MediaAction.ICON_DEFAULT, new Pair(getContext().getString(R$string.nextBtnIconRes), (Object) null)), TuplesKt.to(MediaAction.ICON_SECOND_HOME, new Pair(getContext().getString(R$string.nextBtnIconRes), (Object) null))});
        }
        HashMap map2 = map;
        CharSequence contentDescription = oplusMediaActions.getNextOrCustom().getContentDescription();
        String string = contentDescription != null ? contentDescription.toString() : null;
        MediaAction mediaAction = new MediaAction(MediaButton.BTN_NAME_NEXT_OR_CUSTOM, map2, null, string == null ? "" : string, oplusMediaActions.getNextOrCustom().getMediaActionEx().isStandardAction, icon, 4, null);
        mediaAction.setAction(createMediaActionRunnable$default(this, mediaAction.getButtonName(), action, 2, oplusMediaActions.getNextOrCustom().getMediaActionEx().isStandardAction ? ROTATE_RIGHT : "", 16));
        if (mediaAction.isStandardAction()) {
            mediaAction.setAnimRes(getContext().getString(R$string.nextBtnAnimRes));
        }
        return mediaAction;
    }

    public final MediaAction createPlayPauseAction(Icon icon, OplusMediaActions oplusMediaActions, HashMap map, boolean z) {
        HashMap mapHashMapOf;
        com.android.systemui.media.controls.shared.model.MediaAction playPauseOrCustom = oplusMediaActions.getPlayPauseOrCustom();
        Runnable action = playPauseOrCustom != null ? playPauseOrCustom.getAction() : null;
        if (action == null) {
            OplusNotificationLogger.logMediaSeedling(TAG, "createPlayPauseAction runnable is null");
            return null;
        }
        if (isUriNullInMap(map)) {
            String string = getContext().getString(R$string.pauseBtnIconRes);
            String string2 = getContext().getString(R$string.playBtnIconRes);
            mapHashMapOf = z ? MapsKt__MapsKt.hashMapOf(new Pair[]{TuplesKt.to(MediaAction.ICON_DEFAULT, new Pair(string, (Object) null)), TuplesKt.to(MediaAction.ICON_SECOND_HOME, new Pair(string, (Object) null))}) : MapsKt__MapsKt.hashMapOf(new Pair[]{TuplesKt.to(MediaAction.ICON_DEFAULT, new Pair(string2, (Object) null)), TuplesKt.to(MediaAction.ICON_SECOND_HOME, new Pair(string2, (Object) null))});
        } else {
            mapHashMapOf = map;
        }
        CharSequence contentDescription = oplusMediaActions.getPlayPauseOrCustom().getContentDescription();
        String string3 = contentDescription != null ? contentDescription.toString() : null;
        if (string3 == null) {
            string3 = "";
        }
        MediaAction mediaAction = new MediaAction(MediaButton.BTN_NAME_PLAY_OR_PAUSE, mapHashMapOf, null, string3, oplusMediaActions.getPlayPauseOrCustom().getMediaActionEx().isStandardAction, icon, 4, null);
        mediaAction.setAction(createMediaActionRunnable$default(this, mediaAction.getButtonName(), action, Integer.valueOf(z ? 4 : 3), null, 40));
        return mediaAction;
    }

    public final MediaAction createPrevAction(HashMap map, OplusMediaActions oplusMediaActions, Icon icon) {
        com.android.systemui.media.controls.shared.model.MediaAction preOrCustom = oplusMediaActions.getPreOrCustom();
        Runnable action = preOrCustom != null ? preOrCustom.getAction() : null;
        if (action == null) {
            OplusNotificationLogger.logMediaSeedling(TAG, "createPrevAction runnable is null");
            return null;
        }
        if (isUriNullInMap(map)) {
            map = MapsKt__MapsKt.hashMapOf(new Pair[]{TuplesKt.to(MediaAction.ICON_DEFAULT, new Pair(getContext().getString(R$string.preBtnIconRes), (Object) null)), TuplesKt.to(MediaAction.ICON_SECOND_HOME, new Pair(getContext().getString(R$string.preBtnIconRes), (Object) null))});
        }
        HashMap map2 = map;
        CharSequence contentDescription = oplusMediaActions.getPreOrCustom().getContentDescription();
        String string = contentDescription != null ? contentDescription.toString() : null;
        MediaAction mediaAction = new MediaAction(MediaButton.BTN_NAME_PREV_OR_CUSTOM, map2, null, string == null ? "" : string, oplusMediaActions.getPreOrCustom().getMediaActionEx().isStandardAction, icon, 4, null);
        mediaAction.setAction(createMediaActionRunnable$default(this, mediaAction.getButtonName(), action, 1, oplusMediaActions.getPreOrCustom().getMediaActionEx().isStandardAction ? ROTATE_LEFT : "", 16));
        if (mediaAction.isStandardAction()) {
            mediaAction.setAnimRes(getContext().getString(R$string.preBtnAnimRes));
        }
        return mediaAction;
    }

    public final void dispatchMediaDataLoaded(int i, String str) {
        boolean z = this.isDestroyed;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m(i, "dispatchMediaDataLoaded needUpdate=", " reason=", str, " isDestroyed=");
        sbM.append(z);
        OplusNotificationLogger.logMediaSeedling(TAG, sbM.toString());
        if (this.isDestroyed) {
            return;
        }
        getMediaDataCombineLatest().onMediaDataLoaded(getMediaData().getPlayerId(), getMediaData(), i);
    }

    public Long executePlaybackStateChangedTaskTimeThreshold() {
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x00e1 A[PHI: r2 r12 r13
      0x00e1: PHI (r2v6 com.oplus.systemui.media.controls.ui.OplusMediaActions) = 
      (r2v5 com.oplus.systemui.media.controls.ui.OplusMediaActions)
      (r2v16 com.oplus.systemui.media.controls.ui.OplusMediaActions)
     binds: [B:30:0x00de, B:19:0x0052] A[DONT_GENERATE, DONT_INLINE]
      0x00e1: PHI (r12v6 com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData) = 
      (r12v5 com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData)
      (r12v32 com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData)
     binds: [B:30:0x00de, B:19:0x0052] A[DONT_GENERATE, DONT_INLINE]
      0x00e1: PHI (r13v12 java.lang.Object) = (r13v11 java.lang.Object), (r13v1 java.lang.Object) binds: [B:30:0x00de, B:19:0x0052] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:35:0x0104 A[PHI: r2 r12 r13
      0x0104: PHI (r2v7 com.oplus.systemui.media.controls.ui.OplusMediaActions) = 
      (r2v6 com.oplus.systemui.media.controls.ui.OplusMediaActions)
      (r2v18 com.oplus.systemui.media.controls.ui.OplusMediaActions)
     binds: [B:33:0x0101, B:18:0x0045] A[DONT_GENERATE, DONT_INLINE]
      0x0104: PHI (r12v8 com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData) = 
      (r12v7 com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData)
      (r12v34 com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData)
     binds: [B:33:0x0101, B:18:0x0045] A[DONT_GENERATE, DONT_INLINE]
      0x0104: PHI (r13v16 java.lang.Object) = (r13v15 java.lang.Object), (r13v1 java.lang.Object) binds: [B:33:0x0101, B:18:0x0045] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0128, code lost:
    
        if (r13 == r1) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object generateIconMaps(com.oplus.systemui.media.controls.ui.OplusMediaActions r12, kotlin.coroutines.jvm.internal.ContinuationImpl r13) {
        /*
            Method dump skipped, instruction units count: 417
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.media.seedling.client.AbsMediaClient.generateIconMaps(com.oplus.systemui.media.controls.ui.OplusMediaActions, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public ActivityStarter getActivityStarter() {
        return this.activityStarter;
    }

    public IAlbumLoader getAlbumLoader() {
        return this.albumLoader;
    }

    public IAppIconLoader getAppIconLoader() {
        return this.appIconLoader;
    }

    public Handler getBgHandler() {
        return this.bgHandler;
    }

    public MediaSession.Token getCacheToken() {
        return this.cacheToken;
    }

    public Context getContext() {
        return this.context;
    }

    public final MediaAction getCustomAction(PlaybackState.CustomAction customAction, String str, String str2, String str3, String str4, String str5, int i) {
        if (customAction == null) {
            return null;
        }
        String action = customAction.getAction();
        boolean z = action == null || StringsKt__StringsKt.isBlank(action);
        boolean z2 = !z;
        if (z) {
            str = str2;
        }
        MediaAction mediaAction = new MediaAction(str5, MapsKt__MapsKt.hashMapOf(new Pair[]{TuplesKt.to(MediaAction.ICON_DEFAULT, new Pair(str, UriExtKt.toUri(i)))}), str3, str4, false, null, 48, null);
        mediaAction.setAction(new getCustomAction.1.1(customAction, z2, this));
        return mediaAction;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00d9, code lost:
    
        if (r12 == r0) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x011a, code lost:
    
        if (r12 == r0) goto L45;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getIconUri(com.android.systemui.media.controls.shared.model.MediaAction r9, java.lang.String r10, int r11, kotlin.coroutines.jvm.internal.ContinuationImpl r12) {
        /*
            Method dump skipped, instruction units count: 294
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.media.seedling.client.AbsMediaClient.getIconUri(com.android.systemui.media.controls.shared.model.MediaAction, java.lang.String, int, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public Handler getMainHandler() {
        return this.mainHandler;
    }

    public final MediaController getMediaController() {
        return this.mediaController;
    }

    public final MediaController.Callback getMediaControllerCallback() {
        return this.mediaControllerCallback;
    }

    public SeedlingMediaData getMediaData() {
        return this.mediaData;
    }

    public OplusSeedlingMediaDataCombineLatest getMediaDataCombineLatest() {
        return this.mediaDataCombineLatest;
    }

    public MediaOutputDialogManager getOutputDialogFactory() {
        return this.outputDialogFactory;
    }

    public final Runnable getPlaybackStateChangedTask() {
        return this.playbackStateChangedTask;
    }

    public CoroutineDispatcher getSeedlingBgDispatcher() {
        return this.seedlingBgDispatcher;
    }

    public CoroutineScope getSeedlingBgScope() {
        return this.seedlingBgScope;
    }

    public final Icon getSimpleIcon(com.android.systemui.media.controls.shared.model.MediaAction mediaAction) {
        Drawable icon;
        Bitmap bitmapConvertDrawableToBitmap;
        OplusMediaActionEx mediaActionEx;
        Icon icon2;
        if (mediaAction == null || (mediaActionEx = mediaAction.getMediaActionEx()) == null || (icon2 = mediaActionEx.getIcon()) == null || (icon = icon2.loadDrawableAsUser(getContext(), OsBinderCacheUtils.getCurrentUserId())) == null) {
            icon = mediaAction != null ? mediaAction.getIcon() : null;
        }
        if (icon == null || (bitmapConvertDrawableToBitmap = FileUtil.convertDrawableToBitmap(getContext(), icon)) == null) {
            return null;
        }
        return Icon.createWithBitmap(bitmapConvertDrawableToBitmap);
    }

    public final MediaAction getStandardAction(long j, long j2, String str) {
        if (((j2 != 4 && j2 != 2) || (j & 512) <= 0) && (j & j2) == 0) {
            OplusNotificationLogger.logMediaSeedling(TAG, "getStandardAction action return null is：" + j2);
            return null;
        }
        if (j2 == 4) {
            MediaAction mediaAction = new MediaAction(str, MapsKt__MapsKt.hashMapOf(new Pair[]{TuplesKt.to(MediaAction.ICON_DEFAULT, new Pair(getContext().getString(R$string.playBtnIconRes), (Object) null)), TuplesKt.to(MediaAction.ICON_SECOND_HOME, new Pair(getContext().getString(R$string.playBtnIconResSecondHome), (Object) null))}), null, getContext().getString(R$string.controls_media_button_pause), true, null, 36, null);
            mediaAction.setAction(new createPlayAction.1.1(this, 0));
            return mediaAction;
        }
        if (j2 == 2) {
            MediaAction mediaAction2 = new MediaAction(str, MapsKt__MapsKt.hashMapOf(new Pair[]{TuplesKt.to(MediaAction.ICON_DEFAULT, new Pair(getContext().getString(R$string.pauseBtnIconRes), (Object) null)), TuplesKt.to(MediaAction.ICON_SECOND_HOME, new Pair(getContext().getString(R$string.pauseBtnIconResSecondHome), (Object) null))}), null, getContext().getString(R$string.controls_media_button_play), true, null, 36, null);
            mediaAction2.setAction(new createPlayAction.1.1(this, 2));
            return mediaAction2;
        }
        if (j2 == 16) {
            MediaAction mediaAction3 = new MediaAction(str, MapsKt__MapsKt.hashMapOf(new Pair[]{TuplesKt.to(MediaAction.ICON_DEFAULT, new Pair(getContext().getString(R$string.preBtnIconRes), (Object) null)), TuplesKt.to(MediaAction.ICON_SECOND_HOME, new Pair(getContext().getString(R$string.preBtnIconResSecondHome), (Object) null))}), getContext().getString(R$string.preBtnAnimRes), getContext().getString(R$string.controls_media_button_prev), true, null, 32, null);
            mediaAction3.setAction(new createPlayAction.1.1(this, 4));
            return mediaAction3;
        }
        if (j2 != 32) {
            return null;
        }
        MediaAction mediaAction4 = new MediaAction(str, MapsKt__MapsKt.hashMapOf(new Pair[]{TuplesKt.to(MediaAction.ICON_DEFAULT, new Pair(getContext().getString(R$string.nextBtnIconRes), (Object) null)), TuplesKt.to(MediaAction.ICON_SECOND_HOME, new Pair(getContext().getString(R$string.nextBtnIconResSecondHome), (Object) null))}), getContext().getString(R$string.nextBtnAnimRes), getContext().getString(R$string.controls_media_button_next), true, null, 32, null);
        mediaAction4.setAction(new createPlayAction.1.1(this, 3));
        return mediaAction4;
    }

    public abstract void onCreate();

    public final void onDestroy() {
        OplusNotificationLogger.logMediaSeedling(TAG, "onDestroy");
        MediaActionBinderKt.releaseCache();
        setDestroyed(true);
        getBgHandler().removeCallbacks(this.loadArtworkAndUpdateTask);
        getAlbumLoader().onDestroy();
        Handler bgHandler = getBgHandler();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        bgHandler.post(anonymousClass1);
        this.mediaController = null;
    }

    /* JADX WARN: Code duplicated, block: B:50:0x0281  */
    /* JADX WARN: Code duplicated, block: B:51:0x028d  */
    /* JADX WARN: Code duplicated, block: B:53:0x0299  */
    /* JADX WARN: Code duplicated, block: B:54:0x02a9  */
    public final void onMediaDataLoaded(MediaData mediaData) {
        CharSequence charSequence;
        String str;
        AbsMediaClient absMediaClient;
        OplusNotificationLogger.logMediaSeedling(TAG, "onMediaDataLoaded: data=" + MediaDataExtKt.printInfo(mediaData, true) + " isDestroyed=" + this.isDestroyed);
        if (this.isDestroyed) {
            return;
        }
        getMediaData().setArtworkIcon(mediaData.getArtwork());
        CharSequence song = mediaData.getSong();
        CharSequence artist = mediaData.getArtist();
        Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = mediaData.getMediaDataEx().getDuration();
        int userId = mediaData.getUserId();
        MediaSession.Token token = mediaData.getToken();
        String app = mediaData.getApp();
        String artworkColor = mediaData.getMediaDataEx().getArtworkColor();
        String string = getContext().getString(R$string.quick_settings_media_device_label);
        int playbackLocation = mediaData.getPlaybackLocation();
        int preState = mediaData.getMediaDataEx().getPreState();
        String packageName = mediaData.getPackageName();
        String string2 = getContext().getString(R$string.controls_media_playing_item_description, song, artist, app);
        boolean active = mediaData.getActive();
        boolean lyricSupport = mediaData.getMediaDataEx().getLyricSupport();
        LyricData lyricData = new LyricData(mediaData.getMediaDataEx().getLyricData().getSong(), mediaData.getMediaDataEx().getLyricData().getArtist(), mediaData.getMediaDataEx().getLyricData().getLines(), mediaData.getMediaDataEx().getLyricData().getOriLyric(), mediaData.getMediaDataEx().getLyricData().isEnabled(4), mediaData.getMediaDataEx().getLyricData().isEnabled(16), mediaData.getMediaDataEx().getLyricData().getDisplayPolicy());
        if (Intrinsics.areEqual(getMediaData().getSong(), song) && Intrinsics.areEqual(getMediaData().getArtist(), artist)) {
            charSequence = artist;
            if (getMediaData().getDuration() == longRef.element && getMediaData().getUserId() == userId && Intrinsics.areEqual(getMediaData().getToken(), token) && Intrinsics.areEqual(getMediaData().getAppLabel(), app) && Intrinsics.areEqual(getMediaData().getArtworkColor(), artworkColor) && Intrinsics.areEqual(getMediaData().getDeviceNameLabel(), string) && getMediaData().getPlaybackLocation() == playbackLocation && getMediaData().getOldState() == preState && Intrinsics.areEqual(getMediaData().getPackageName(), packageName) && Intrinsics.areEqual(getMediaData().getVoiceLabel(), string2) && Intrinsics.areEqual(getMediaData().getLyricData(), lyricData) && getMediaData().getLyricSupport() == lyricSupport && getMediaData().getActive() == active && longRef.element != 0) {
                absMediaClient = this;
                str = TAG;
            }
            if (mediaData.getArtwork() == null) {
                HandlerExtKt.runTaskDelayed(absMediaClient.getBgHandler(), absMediaClient.loadArtworkAndUpdateTask, 500L);
            } else if (Intrinsics.areEqual(absMediaClient.oldArtwork, mediaData.getArtwork())) {
                OplusNotificationLogger.logMediaSeedling(str, "oldArtwork == newArtwork, dont update");
            } else {
                absMediaClient.oldArtwork = mediaData.getArtwork();
                HandlerExtKt.runTaskImmediately(absMediaClient.getBgHandler(), absMediaClient.loadArtworkAndUpdateTask);
            }
            BuildersKt.launch$default(absMediaClient.getSeedlingBgScope(), (CoroutineContext) null, (CoroutineStart) null, absMediaClient.new C01801(mediaData, token, longRef, null), 3);
        }
        charSequence = artist;
        if (!Intrinsics.areEqual(getMediaData().getSong(), song)) {
            tempSuppressSeekBarUpdate();
        }
        getMediaData().setSong(song);
        getMediaData().setArtist(charSequence);
        getMediaData().setUserId(userId);
        getMediaData().setToken(token);
        getMediaData().setDuration(longRef.element);
        getMediaData().setAppLabel(app);
        getMediaData().setArtworkColor(artworkColor);
        getMediaData().setDeviceNameLabel(string);
        getMediaData().setPlaybackLocation(playbackLocation);
        getMediaData().setOldState(preState);
        getMediaData().setPackageName(packageName);
        getMediaData().setVoiceLabel(string2);
        if (Intrinsics.areEqual(getMediaData().getLyricData(), lyricData)) {
            str = TAG;
        } else {
            String str2 = "seedling lyric: " + mediaData.getMediaDataEx().getLyricData();
            str = TAG;
            OplusNotificationLogger.logMediaSeedlingDebug(str, str2);
        }
        getMediaData().setLyricData(lyricData);
        getMediaData().setLyricSupport(lyricSupport);
        getMediaData().setActive(active);
        absMediaClient = this;
        absMediaClient.dispatchMediaDataLoaded(0, "onMediaDataLoaded");
        if (mediaData.getArtwork() == null) {
            HandlerExtKt.runTaskDelayed(absMediaClient.getBgHandler(), absMediaClient.loadArtworkAndUpdateTask, 500L);
        } else if (Intrinsics.areEqual(absMediaClient.oldArtwork, mediaData.getArtwork())) {
            absMediaClient.oldArtwork = mediaData.getArtwork();
            HandlerExtKt.runTaskImmediately(absMediaClient.getBgHandler(), absMediaClient.loadArtworkAndUpdateTask);
        } else {
            OplusNotificationLogger.logMediaSeedling(str, "oldArtwork == newArtwork, dont update");
        }
        BuildersKt.launch$default(absMediaClient.getSeedlingBgScope(), (CoroutineContext) null, (CoroutineStart) null, absMediaClient.new C01801(mediaData, token, longRef, null), 3);
    }

    public final void onMediaDataRemoved(String str) {
        OplusNotificationLogger.logMediaSeedling(TAG, "onMediaDataRemoved: " + str);
        getMediaDataCombineLatest().onMediaDataRemoved(getMediaData().getPlayerId());
    }

    public void setCacheToken(MediaSession.Token token) {
        this.cacheToken = token;
    }

    public void setDestroyed(boolean z) {
        this.isDestroyed = z;
    }

    public final void setMediaController(MediaController mediaController) {
        this.mediaController = mediaController;
    }

    public void setMediaData(SeedlingMediaData seedlingMediaData) {
        this.mediaData = seedlingMediaData;
    }

    public final void showMediaOutputDialog(String str) {
        OplusNotificationLogger.logMediaSeedling(TAG, "showMediaOutputDialog: pkg=" + str);
        if (Intrinsics.areEqual(Looper.getMainLooper(), Looper.myLooper())) {
            MediaOutputDialogManager.create$default(getOutputDialogFactory(), str, true, (View) null, false, false, (MediaSession.Token) null, false, 248);
            return;
        }
        Handler mainHandler = getMainHandler();
        AbsMediaClient$loadAppIconAndUpdate$1 absMediaClient$loadAppIconAndUpdate$1 = new AbsMediaClient$loadAppIconAndUpdate$1(1);
        absMediaClient$loadAppIconAndUpdate$1.this$0 = this;
        absMediaClient$loadAppIconAndUpdate$1.$appIcon = str;
        VarHandle.storeStoreFence();
        mainHandler.post(absMediaClient$loadAppIconAndUpdate$1);
    }

    public final void tempSuppressSeekBarUpdate() {
        getMediaDataCombineLatest().tempSuppressSeekBarUpdate();
    }

    public boolean updateMediaButton(PlaybackState playbackState) {
        MediaButton mediaButtonCreateActionsFromState = createActionsFromState(playbackState);
        OplusNotificationLogger.logMediaSeedling(TAG, "currentMediaButton " + getMediaData().getMediaButton() + " newMediaButton " + mediaButtonCreateActionsFromState);
        if (Intrinsics.areEqual(mediaButtonCreateActionsFromState, getMediaData().getMediaButton())) {
            return false;
        }
        getMediaData().setMediaButton(mediaButtonCreateActionsFromState);
        return true;
    }

    public final boolean updatePositionByPlaybackState(PlaybackState playbackState, boolean z) {
        if (playbackState != null) {
            int state = playbackState.getState();
            long position = playbackState.getPosition();
            long lastPositionUpdateTime = playbackState.getLastPositionUpdateTime();
            float playbackSpeed = playbackState.getPlaybackSpeed();
            boolean suppressSeekBarUpdate = getMediaDataCombineLatest().getSuppressSeekBarUpdate();
            boolean seekEnable = getMediaData().getSeekEnable();
            boolean seekAvailable = getMediaData().getSeekAvailable();
            if (state != getMediaData().getState() || position != getMediaData().getPosition() || lastPositionUpdateTime != getMediaData().getLastPositionUpdateTime() || playbackSpeed != getMediaData().getPlaybackSpeed()) {
                getMediaData().setState(state);
                getMediaData().setPosition(playbackState.getPosition());
                getMediaData().setLastPositionUpdateTime(playbackState.getLastPositionUpdateTime());
                getMediaData().setPlaybackSpeed(playbackState.getPlaybackSpeed());
                if (suppressSeekBarUpdate) {
                    getMediaData().setSeekEnable(seekEnable);
                    getMediaData().setSeekAvailable(seekAvailable);
                } else {
                    getMediaData().setSeekEnable(playbackState.getState() != 0 && getMediaData().getDuration() > 0);
                    getMediaData().setSeekAvailable((playbackState.getActions() & 256) != 0);
                }
                long position2 = getMediaData().getPosition();
                boolean seekEnable2 = getMediaData().getSeekEnable();
                boolean seekAvailable2 = getMediaData().getSeekAvailable();
                String packageName = getMediaData().getPackageName();
                StringBuilder sb = new StringBuilder("updateMediaDataByPlaybackState newState=");
                sb.append(state);
                sb.append(", newPosition=");
                sb.append(position2);
                OplusLockIconView$.ExternalSyntheticOutline0.m(", seekEnable=", ", seekAvailable=", sb, seekEnable2, seekAvailable2);
                sb.append(", seekUpdateSuppressed=");
                sb.append(suppressSeekBarUpdate);
                sb.append(", packageName=");
                sb.append(packageName);
                OplusNotificationLogger.logMediaSeedling(TAG, sb.toString());
                if (z) {
                    dispatchMediaDataLoaded(0, "updatePositionByPlaybackState");
                }
                return true;
            }
        }
        return false;
    }
}
