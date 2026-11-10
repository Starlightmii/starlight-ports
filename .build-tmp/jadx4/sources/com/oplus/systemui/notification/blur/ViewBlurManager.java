package com.oplus.systemui.notification.blur;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dumpable;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.panelanimation.platformblur.PlatformBlurManager;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplus.media.MediaCarouselControllerExImp;
import com.oplus.posteffect.params.CornerParams;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.notification.base.radius.SmoothRoundEx;
import com.oplus.systemui.notification.color.MaterialColorManager;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpContainerWindow;
import com.oplus.systemui.notification.lockscreen.effect.NotificationEffectRepository;
import com.oplus.systemui.notification.row.material.NotificationBlurMode;
import com.oplus.systemui.notification.row.material.NotificationMaterialColorEx;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.base.res.util.StrokeCornerUtils;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplus.systemui.statusbar.phone.ScrimControllerExImp;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.BlurMixConfig;
import com.oplusos.systemui.common.blurability.DebugTool;
import com.oplusos.systemui.common.blurability.DebugToolKt;
import com.oplusos.systemui.common.blurability.MixColor;
import com.oplusos.systemui.common.blurability.MixColorWithShader;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.platformblur.BlurConfigsProvider;
import com.oplusos.systemui.common.blurability.screenshot.StaticBlurManager;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.NotifiAndQsPlatformBlurExKt;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;
import com.oplusos.systemui.common.util.ScrimUtil;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
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
public final class ViewBlurManager implements ConfigurationController.ConfigurationListener, Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ViewBlurManager";
    private final CoroutineScope applicationScope;
    public float blurCornerRadius;
    private final HashSet<View> capsuleCardViews;
    public int cardBlurColor;
    private final ViewBlurManager$collectionListener$1 collectionListener;
    private final ConfigurationController configurationController;
    private final Context context;
    public final int customCardMixedBottomLayerColor;
    public final int customCardMixedTopLayerColor;
    private final HashSet<View> customCardViews;
    private final HashSet<View> decoratorsCardViews;
    private List<? extends PipelineEntry> entries;
    public float fullScreenBannerRadius;
    private final Lazy headsUpWindow;
    public volatile boolean isNightMode;
    private final KeyguardStateControllerImpl keyguardStateControllerImpl;
    public int lowGaussianNormalCardMixedBottomLayerColor;
    public int lowGaussianNormalCardMixedTopLayerColor;
    private final MaterialColorManager materialColorManager;
    private final MediaCarouselControllerExImp mediaCarouselControllerExImp;
    private final HashSet<View> menuCardViews;
    private final OnBeforeRenderListListener notifTracker;
    private final Lazy notificationEffectRepository;
    private final Lazy notificationKeyguardHelper;
    private final Lazy notificationMaterialColorEx;
    private View pinningBgView;
    private final Lazy platformBlurManager;
    private final SysuiStatusBarStateController statusBarStateController;
    private final HashMap<View, ViewBlurProxy> viewBlurProxyes = new HashMap<>();
    private Bitmap wallpaperBitmap;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.blur.ViewBlurManager$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements KeyguardStateController.Callback {
        public /* synthetic */ ViewBlurManager this$0;

        public final void onKeyguardShowingChanged() {
            ViewBlurManager viewBlurManager = this.this$0;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("[onKeyguardShowingChanged] isOnKeyGuard=", "ViewBlurManager-->", "SystemUi--Notification", ViewBlurManager.isOnKeyGuard$default(viewBlurManager));
            }
            if (ViewBlurManager.isOnKeyGuard$default(viewBlurManager)) {
                viewBlurManager.updateRowsBlur();
            }
            ViewBlurManager.access$updatePlatformBlurMode(viewBlurManager, viewBlurManager.keyguardStateControllerImpl.isVisible());
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.blur.ViewBlurManager$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.blur.ViewBlurManager$3$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 implements FlowCollector {
            public final /* synthetic */ int $r8$classId;
            public /* synthetic */ ViewBlurManager this$0;

            public /* synthetic */ AnonymousClass1(int i) {
                this.$r8$classId = i;
            }

            public final Object emit(Object obj, Continuation continuation) {
                int i = 1;
                switch (this.$r8$classId) {
                    case 0:
                        boolean zBooleanValue = ((Boolean) obj).booleanValue();
                        ViewBlurManager viewBlurManager = this.this$0;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            OplusLockIconView$.ExternalSyntheticOutline0.m("[isThirdThemeModeChanged] ", "ViewBlurManager-->", "SystemUi--Notification", zBooleanValue);
                        }
                        MaterialColorManager materialColorManager = viewBlurManager.materialColorManager;
                        materialColorManager.getClass();
                        MaterialColorManager.updateLowGaussianRowColor$default(materialColorManager, 3);
                        viewBlurManager.updateRowsBlur();
                        break;
                    case 1:
                        ((Boolean) obj).booleanValue();
                        ViewBlurManager viewBlurManager2 = this.this$0;
                        viewBlurManager2.getClass();
                        ViewBlurManager$$ExternalSyntheticLambda2 viewBlurManager$$ExternalSyntheticLambda2 = new ViewBlurManager$$ExternalSyntheticLambda2(i);
                        viewBlurManager$$ExternalSyntheticLambda2.f$0 = viewBlurManager2;
                        VarHandle.storeStoreFence();
                        viewBlurManager2.actionForAllCards(viewBlurManager$$ExternalSyntheticLambda2);
                        break;
                    case 2:
                        boolean noBlur = ((NotificationBlurMode) obj).getNoBlur();
                        ViewBlurManager viewBlurManager3 = this.this$0;
                        if (!noBlur) {
                            Iterator it = viewBlurManager3.menuCardViews.iterator();
                            while (it.hasNext()) {
                                viewBlurManager3.updatePlatformBlurParams((ViewBlurProxy) viewBlurManager3.viewBlurProxyes.get((View) it.next()), true);
                            }
                            Iterator it2 = viewBlurManager3.decoratorsCardViews.iterator();
                            while (it2.hasNext()) {
                                viewBlurManager3.updatePlatformBlurParams((ViewBlurProxy) viewBlurManager3.viewBlurProxyes.get((View) it2.next()), true);
                            }
                        } else {
                            viewBlurManager3.updateRowsBlur();
                        }
                        break;
                    default:
                        ((Boolean) obj).booleanValue();
                        ViewBlurManager viewBlurManager4 = this.this$0;
                        viewBlurManager4.getClass();
                        ViewBlurManager$$ExternalSyntheticLambda2 viewBlurManager$$ExternalSyntheticLambda3 = new ViewBlurManager$$ExternalSyntheticLambda2(0);
                        viewBlurManager$$ExternalSyntheticLambda3.f$0 = viewBlurManager4;
                        VarHandle.storeStoreFence();
                        viewBlurManager4.actionForAllCards(viewBlurManager$$ExternalSyntheticLambda3);
                        break;
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass3(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return ViewBlurManager.this.new AnonymousClass3(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlowIsThirdThemeMode = ScrimControllerEx.Companion.isThirdThemeMode();
                ViewBlurManager viewBlurManager = ViewBlurManager.this;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
                anonymousClass1.this$0 = viewBlurManager;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (stateFlowIsThirdThemeMode.collect(anonymousClass1, this) == coroutine_suspended) {
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

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CardType {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ CardType[] $VALUES;
        public static final CardType CAPSULE;
        public static final CardType CAPSULE_EAR;
        public static final CardType CLEAR_ALL;
        public static final CardType CLOSE_ALL;
        public static final CardType CUSTOM;
        public static final CardType EXPAND_GROUP_BUTTON;
        public static final CardType FULLSCREENBANNER;
        public static final CardType MENU;
        public static final CardType NOTIFICATION;
        public static final CardType SETTINGS;

        static {
            CardType cardType = new CardType("NOTIFICATION", 0);
            NOTIFICATION = cardType;
            CardType cardType2 = new CardType("CUSTOM", 1);
            CUSTOM = cardType2;
            CardType cardType3 = new CardType("CAPSULE", 2);
            CAPSULE = cardType3;
            CardType cardType4 = new CardType("CLOSE_ALL", 3);
            CLOSE_ALL = cardType4;
            CardType cardType5 = new CardType("FULLSCREENBANNER", 4);
            FULLSCREENBANNER = cardType5;
            CardType cardType6 = new CardType("SETTINGS", 5);
            SETTINGS = cardType6;
            CardType cardType7 = new CardType("CLEAR_ALL", 6);
            CLEAR_ALL = cardType7;
            CardType cardType8 = new CardType("MENU", 7);
            MENU = cardType8;
            CardType cardType9 = new CardType("CAPSULE_EAR", 8);
            CAPSULE_EAR = cardType9;
            CardType cardType10 = new CardType("EXPAND_GROUP_BUTTON", 9);
            EXPAND_GROUP_BUTTON = cardType10;
            CardType[] cardTypeArr = {cardType, cardType2, cardType3, cardType4, cardType5, cardType6, cardType7, cardType8, cardType9, cardType10};
            $VALUES = cardTypeArr;
            $ENTRIES = EnumEntriesKt.enumEntries(cardTypeArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static CardType valueOf(String str) {
            return (CardType) Enum.valueOf(CardType.class, str);
        }

        public static CardType[] values() {
            return (CardType[]) $VALUES.clone();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.blur.ViewBlurManager$setWallpaperBlurBitmap$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Bitmap $bitmap;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Bitmap bitmap, Continuation continuation) {
            super(2, continuation);
            this.$bitmap = bitmap;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return ViewBlurManager.this.new AnonymousClass1(this.$bitmap, continuation);
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
            ViewBlurManager.this.wallpaperBitmap = this.$bitmap;
            List<ViewBlurProxy> list = CollectionsKt___CollectionsKt.toList(ViewBlurManager.this.viewBlurProxyes.values());
            ViewBlurManager viewBlurManager = ViewBlurManager.this;
            for (ViewBlurProxy viewBlurProxy : list) {
                viewBlurProxy.setCurrentWallpaperBlurBitmap(viewBlurManager.wallpaperBitmap);
                viewBlurProxy.applyBlurConfig();
            }
            return Unit.INSTANCE;
        }
    }

    public static Unit $r8$lambda$mzaqBMzk4aZBtxqO2eoH6J_ViSY(ViewBlurManager viewBlurManager) {
        Iterator<Map.Entry<View, ViewBlurProxy>> it = viewBlurManager.viewBlurProxyes.entrySet().iterator();
        while (it.hasNext()) {
            NotificationBackgroundView notificationBackgroundView = (View) it.next().getKey();
            if (notificationBackgroundView instanceof NotificationBackgroundView) {
                NotificationBackgroundView notificationBackgroundView2 = notificationBackgroundView;
                if (notificationBackgroundView2.isAttachedToWindow()) {
                    notificationBackgroundView2.getExt().notifyMaterialColorChanged();
                }
            }
        }
        return Unit.INSTANCE;
    }

    public ViewBlurManager(Context context, NotifPipeline notifPipeline, SysuiStatusBarStateController sysuiStatusBarStateController, KeyguardStateControllerImpl keyguardStateControllerImpl, MediaCarouselControllerExImp mediaCarouselControllerExImp, ConfigurationController configurationController, Lazy lazy, Lazy lazy2, MaterialColorManager materialColorManager, CoroutineScope coroutineScope, Lazy lazy3, Lazy lazy4, Lazy lazy5) {
        this.context = context;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.keyguardStateControllerImpl = keyguardStateControllerImpl;
        this.mediaCarouselControllerExImp = mediaCarouselControllerExImp;
        this.configurationController = configurationController;
        this.platformBlurManager = lazy;
        this.headsUpWindow = lazy2;
        this.materialColorManager = materialColorManager;
        this.applicationScope = coroutineScope;
        this.notificationEffectRepository = lazy3;
        this.notificationMaterialColorEx = lazy4;
        this.notificationKeyguardHelper = lazy5;
        this.cardBlurColor = context.getColor(2131102893);
        this.lowGaussianNormalCardMixedTopLayerColor = context.getColor(2131102841);
        this.lowGaussianNormalCardMixedBottomLayerColor = context.getColor(2131102840);
        context.getColor(2131101652);
        context.getColor(2131101651);
        this.customCardMixedTopLayerColor = context.getColor(2131101472);
        this.customCardMixedBottomLayerColor = context.getColor(2131101471);
        this.blurCornerRadius = context.getResources().getDimensionPixelSize(2131170626);
        this.fullScreenBannerRadius = context.getResources().getDimensionPixelSize(2131168360);
        this.customCardViews = new HashSet<>();
        this.decoratorsCardViews = new HashSet<>();
        this.menuCardViews = new HashSet<>();
        this.capsuleCardViews = new HashSet<>();
        this.isNightMode = context.getResources().getConfiguration().isNightModeActive();
        ViewBlurManager$notifTracker$1 viewBlurManager$notifTracker$1 = new ViewBlurManager$notifTracker$1();
        viewBlurManager$notifTracker$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.notifTracker = viewBlurManager$notifTracker$1;
        ViewBlurManager$collectionListener$1 viewBlurManager$collectionListener$1 = new ViewBlurManager$collectionListener$1();
        viewBlurManager$collectionListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.collectionListener = viewBlurManager$collectionListener$1;
        ViewBlurManager$$ExternalSyntheticLambda0 viewBlurManager$$ExternalSyntheticLambda0 = new ViewBlurManager$$ExternalSyntheticLambda0(0);
        viewBlurManager$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        materialColorManager.setOnCurrentMaterialColorChanged(viewBlurManager$$ExternalSyntheticLambda0);
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        keyguardStateControllerImpl.addCallback(anonymousClass2);
        BuildersKt.launch$default(coroutineScope, Dispatchers.getMain(), (CoroutineStart) null, new AnonymousClass3(null), 2);
        notifPipeline.addOnBeforeRenderListListener(viewBlurManager$notifTracker$1);
        notifPipeline.addCollectionListener(viewBlurManager$collectionListener$1);
        configurationController.addCallback(this);
        NotificationPlatFormBlurParamsManager.INSTANCE.initOlive(context);
        if (ScrimUtil.isMiddleOrHighAnimLevel(context)) {
            BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new ViewBlurManager$4$1(this, null), 2);
            BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new ViewBlurManager$4$2(this, null), 2);
            BuildersKt.launch$default(coroutineScope, Dispatchers.getMain(), (CoroutineStart) null, new ViewBlurManager$4$3(this, null), 2);
        }
    }

    public static final void access$delaySetStaticBlur(ViewBlurManager viewBlurManager) {
        StaticBlurManager notifiAndQsBlurManager = ScrimControllerExImp.Companion.getNotifiAndQsBlurManager(viewBlurManager.context);
        if (notifiAndQsBlurManager.getScreenBlurredBitmap() != null) {
            viewBlurManager.updateRowsBlur();
            return;
        }
        delaySetStaticBlur.1 r1 = new delaySetStaticBlur.1();
        r1.this$0 = viewBlurManager;
        r1.$staticBlurManager = notifiAndQsBlurManager;
        VarHandle.storeStoreFence();
        notifiAndQsBlurManager.addBlurredBitmapObserver(r1);
    }

    public static final void access$updatePlatformBlurMode(ViewBlurManager viewBlurManager, boolean z) {
        ((PlatformBlurManager) viewBlurManager.platformBlurManager.get()).setPlatformBlurMode((!z || ScrimUtil.isLowGaussianLevel(viewBlurManager.context)) ? PlatformBlurManager.PlatformBlurMode.AUTO : PlatformBlurManager.PlatformBlurMode.MOTION);
    }

    public static boolean isOnKeyGuard$default(ViewBlurManager viewBlurManager) {
        int currentOrUpcomingState = viewBlurManager.statusBarStateController.getCurrentOrUpcomingState();
        return currentOrUpcomingState == 1 || currentOrUpcomingState == 2;
    }

    public static void logViewBlurInfo(ViewBlurProxy viewBlurProxy, String str) {
        LogBuffer logBuffer = DebugTool.INSTANCE.getLogBuffer();
        if (logBuffer != null) {
            logBuffer.log(TAG, LogLevel.DEBUG, "updateBlurType-from-" + str + " " + viewBlurProxy.getBlurType() + " for " + DebugToolKt.getName(viewBlurProxy.getView(), false), (Throwable) null);
        }
    }

    public static /* synthetic */ ViewBlurProxy requireBlurProxyForView$default(ViewBlurManager viewBlurManager, View view, CardType cardType, float f, NotificationBlurMode notificationBlurMode, int i) {
        if ((i & 4) != 0) {
            f = SmoothRoundEx.INSTANCE.getDefaultWeight();
        }
        if ((i & 8) != 0) {
            notificationBlurMode = null;
        }
        return viewBlurManager.requireBlurProxyForView(view, cardType, f, notificationBlurMode);
    }

    public final void actionForAllCards(Function2 function2) {
        ExpandableNotificationRow row;
        List<? extends PipelineEntry> list = this.entries;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                GroupEntry groupEntry = (PipelineEntry) it.next();
                if (groupEntry instanceof NotificationEntry) {
                    NotificationEntry notificationEntry = (NotificationEntry) groupEntry;
                    function2.invoke(((ActivatableNotificationView) notificationEntry.getRow()).mBackgroundNormal, this.viewBlurProxyes.get(((ActivatableNotificationView) notificationEntry.getRow()).mBackgroundNormal));
                } else if (groupEntry instanceof GroupEntry) {
                    GroupEntry groupEntry2 = groupEntry;
                    NotificationEntry summary = groupEntry2.getSummary();
                    if (summary != null && (row = summary.getRow()) != null) {
                        NotificationBackgroundView notificationBackgroundView = ((ActivatableNotificationView) row).mBackgroundNormal;
                        function2.invoke(notificationBackgroundView, this.viewBlurProxyes.get(notificationBackgroundView));
                    }
                    for (NotificationEntry notificationEntry2 : groupEntry2.getChildren()) {
                        function2.invoke(((ActivatableNotificationView) notificationEntry2.getRow()).mBackgroundNormal, this.viewBlurProxyes.get(((ActivatableNotificationView) notificationEntry2.getRow()).mBackgroundNormal));
                    }
                }
            }
        }
        for (View view : this.customCardViews) {
            function2.invoke(view, this.viewBlurProxyes.get(view));
        }
        for (View view2 : this.decoratorsCardViews) {
            function2.invoke(view2, this.viewBlurProxyes.get(view2));
        }
        for (View view3 : this.menuCardViews) {
            function2.invoke(view3, this.viewBlurProxyes.get(view3));
        }
        for (View view4 : this.capsuleCardViews) {
            function2.invoke(view4, this.viewBlurProxyes.get(view4));
        }
    }

    public final void blurForHeadsUp(View view, boolean z) {
        boolean z2 = ScrimUtil.isHighGaussianLevel(this.context) || ScrimUtil.isMiddleGaussianLevel(this.context);
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[blurForHeadsUp] blurHeadsUpEnabled=", z2), null, 8);
        ViewBlurProxy viewBlurProxy = this.viewBlurProxyes.get(view);
        if (viewBlurProxy != null) {
            if (z) {
                this.pinningBgView = view;
            }
            ScrimControllerEx.Companion.getClass();
            viewBlurProxy.setBlurType((ScrimControllerEx.isThirdTheme || !z2) ? ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE : ViewBlurProxy.BlurType.BlurTypeMotion.INSTANCE);
            logViewBlurInfo(viewBlurProxy, "[blurForHeadsUp]");
        }
    }

    public final void cancelBlurForHeadsUp(View view, boolean z) {
        ViewBlurProxy viewBlurProxy = this.viewBlurProxyes.get(view);
        if (viewBlurProxy != null) {
            if (z) {
                this.pinningBgView = null;
            }
            refreshBlurType(view);
            logViewBlurInfo(viewBlurProxy, "[cancelBlurHeadsUp]");
        }
    }

    public final BlurMixConfig customCardPlatformMixConfig() {
        return NotifiAndQsPlatformBlurExKt.panelElementPlatformMixConfig(new MixColor(4, this.customCardMixedTopLayerColor, this.customCardMixedBottomLayerColor));
    }

    public final ViewBlurProxy.BlurType decideBlurType() {
        if (!SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState && !ScrimUtil.isBAnimLevel() && (!isSinkFeatureNonShadeState() || !ScrimUtil.isBPAnimLevel())) {
            ScrimControllerEx.Companion.getClass();
            if (!ScrimControllerEx.isThirdTheme) {
                if (ScrimUtil.isBPAnimLevel()) {
                    return ViewBlurProxy.BlurType.BlurTypeBlendWallpaper.INSTANCE;
                }
                return GaussBlurUtils.isGaussBlurDisabled() ? ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE : ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE;
            }
        }
        return ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE;
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println();
        printWriter.println("ViewBlurManager:");
        printWriter.println();
        for (Map.Entry<View, ViewBlurProxy> entry : this.viewBlurProxyes.entrySet()) {
            printWriter.print(entry.getKey());
            printWriter.println(":");
            printWriter.println(entry.getValue());
        }
        printWriter.print("cardBlurColor=" + this.cardBlurColor + ", blurCornerRadius=" + this.blurCornerRadius + OplusPreventModeData.SEPARATOR);
        List<? extends PipelineEntry> list = this.entries;
        StringBuilder sb = new StringBuilder("entries=");
        sb.append(list);
        printWriter.println(sb.toString());
        printWriter.println("customCardViews=" + this.customCardViews);
        this.materialColorManager.dump(printWriter, strArr);
    }

    public final ViewBlurProxy getBlurProxyForView(View view) {
        return this.viewBlurProxyes.get(view);
    }

    public final BlurMixConfig getMixConfig(CardType cardType, NotificationBlurMode notificationBlurMode) {
        if (ScrimUtil.isBPAnimLevel()) {
            boolean z = this.isNightMode;
            int i = this.lowGaussianNormalCardMixedTopLayerColor;
            int i2 = this.lowGaussianNormalCardMixedBottomLayerColor;
            return z ? NotifiAndQsPlatformBlurExKt.panelElementPlatformMixConfig(new MixColor(4, i, i2)) : new BlurMixConfig.BlurMixSingle(new MixColor(2, i, i2));
        }
        int iOrdinal = cardType.ordinal();
        if (iOrdinal == 1) {
            return customCardPlatformMixConfig();
        }
        if (iOrdinal != 2) {
            if (iOrdinal == 3) {
                return NotificationPlatFormBlurParamsManager.INSTANCE.closeAllPlatformMixConfig(notificationBlurMode);
            }
            if (iOrdinal == 5) {
                return QSBlurConfigProvider.getInactiveBlurConfig(this.isNightMode, StrokeCornerUtils.isSupportStroke(), 1.0f);
            }
            if (iOrdinal == 6) {
                Context context = this.context;
                NotificationBlurMixColorParams notificationBlurMixColorParams = NotificationBlurMixColorParams.INSTANCE;
                boolean z2 = this.isNightMode;
                NotificationEffectRepository.Companion.getClass();
                boolean z3 = NotificationEffectRepository.isSupportBlurStroke;
                notificationBlurMixColorParams.getClass();
                return NotifiAndQsPlatformBlurExKt.panelElementPlatformMixWithShaderConfig(context, new MixColorWithShader(5, NotificationBlurMixColorParams.resolveDecoratorsMixedTopLayer(z2, z3), 3, NotificationBlurMixColorParams.resolveDecoratorsMixedBottomLayer(this.isNightMode, NotificationEffectRepository.isSupportBlurStroke)), true);
            }
            if (iOrdinal != 8) {
                return iOrdinal != 9 ? NotificationPlatFormBlurParamsManager.INSTANCE.normalCardPlatformMixConfig(notificationBlurMode, useMotionBlur(), "init") : NotificationPlatFormBlurParamsManager.INSTANCE.expandGroupHeaderBtnPlatformMixConfig(notificationBlurMode, "init", useMotionBlur());
            }
        }
        NotificationBlurMixColorParams.INSTANCE.getClass();
        return new BlurMixConfig.BlurMixSingleWithShader(new MixColorWithShader(5, NotificationBlurMixColorParams.resolveCapsuleMixedTopLayer(), 3, NotificationBlurMixColorParams.resolveCapsuleMixedBottomLayer()));
    }

    public final NotificationBlurMode globalBlurMode() {
        return (NotificationBlurMode) ((NotificationMaterialColorEx) this.notificationMaterialColorEx.get()).getBlurMode().getValue();
    }

    public final BlurMixConfig.BlurMixSingle headsupCardMotionMixConfig(CardType cardType, NotificationBlurMode notificationBlurMode) {
        ScrimControllerEx.Companion.getClass();
        if (ScrimControllerEx.isThirdTheme) {
            return null;
        }
        int iOrdinal = cardType.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                return new BlurMixConfig.BlurMixSingle(new MixColor(4, this.customCardMixedTopLayerColor, this.customCardMixedBottomLayerColor));
            }
            if (iOrdinal != 4) {
                return null;
            }
        }
        NotificationBlurMixColorParams notificationBlurMixColorParams = NotificationBlurMixColorParams.INSTANCE;
        return new BlurMixConfig.BlurMixSingle(new MixColor(notificationBlurMixColorParams.resolveHeadsUpMotionMixMode(notificationBlurMode), notificationBlurMixColorParams.resolveHeadsUpTopLayerColor(notificationBlurMode), notificationBlurMixColorParams.resolveHeadsUpBottomLayerColor(notificationBlurMode)));
    }

    public final boolean isKeyguardMaterialColorState() {
        return this.statusBarStateController.getState() != 0;
    }

    public final boolean isSinkFeatureNonShadeState() {
        return this.statusBarStateController.getState() != 0;
    }

    public final void onDensityOrFontScaleChanged() {
        updateResource();
        BuildersKt.launch$default(this.applicationScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new ViewBlurManager$updateCardPlatformMixConfig$1(this, null), 2);
        NotificationPlatFormBlurParamsManager.INSTANCE.initCache(this.context);
    }

    public final void onUiModeChanged() {
        this.isNightMode = this.context.getResources().getConfiguration().isNightModeActive();
        updateResource();
        BuildersKt.launch$default(this.applicationScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new ViewBlurManager$updateCardPlatformMixConfig$1(this, null), 2);
        this.materialColorManager.updateLowGaussianRowColor(true, true);
    }

    public final void refreshBlurType(View view) {
        ViewBlurProxy viewBlurProxy = this.viewBlurProxyes.get(view);
        if (viewBlurProxy != null) {
            refreshCardBlurType(viewBlurProxy);
            logViewBlurInfo(viewBlurProxy, "[refreshBlurType]");
        }
    }

    public final void refreshCardBlurType(ViewBlurProxy viewBlurProxy) {
        if (((HeadsUpContainerWindow) this.headsUpWindow.get()).isHeadsUpView(viewBlurProxy.getView())) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "ViewBlurManager-->".concat("[refreshCardBlurType] viewBlurProxy:" + viewBlurProxy + ",isHeadsView"));
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(viewBlurProxy.getView(), this.pinningBgView)) {
            return;
        }
        View view = viewBlurProxy.getView();
        View view2 = this.pinningBgView;
        Object parent = view2 != null ? view2.getParent() : null;
        View view3 = parent instanceof View ? (View) parent : null;
        for (ViewParent parent2 = view != null ? view.getParent() : null; parent2 != null; parent2 = parent2.getParent()) {
            if (parent2.equals(view3)) {
                return;
            }
        }
        viewBlurProxy.setBlurType(decideBlurType());
    }

    public final void refreshMotionMixConfig(ViewBlurProxy viewBlurProxy, NotificationBlurMode notificationBlurMode) {
        BlurMixConfig.BlurMixSingle motionBlurMixConfig = viewBlurProxy.getBlurConfig().getMotionBlurMixConfig();
        CardType cardType = (CardType) CardType.getEntries().get(viewBlurProxy.getBlurConfig().getCardType());
        if (notificationBlurMode == null) {
            notificationBlurMode = resolveBlurMode(cardType, viewBlurProxy.getView(), null);
        }
        BlurMixConfig.BlurMixSingle blurMixSingleHeadsupCardMotionMixConfig = headsupCardMotionMixConfig(cardType, notificationBlurMode);
        viewBlurProxy.getBlurConfig().setMotionBlurMixConfig(blurMixSingleHeadsupCardMotionMixConfig);
        if (Intrinsics.areEqual(motionBlurMixConfig, blurMixSingleHeadsupCardMotionMixConfig)) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "ViewBlurManager-->".concat("[refreshMotionMixConfig] viewBlurProxy:" + viewBlurProxy + ",motionBlurMixConfig=(" + motionBlurMixConfig + " -> " + blurMixSingleHeadsupCardMotionMixConfig + ")"));
        }
        viewBlurProxy.applyBlurConfig();
    }

    public final void releaseBlurProxyForView(View view, boolean z, String str) {
        ViewBlurProxy viewBlurProxyRemove = this.viewBlurProxyes.remove(view);
        if (viewBlurProxyRemove != null) {
            if (!Intrinsics.areEqual(viewBlurProxyRemove.getBlurType(), ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE) && !Intrinsics.areEqual(viewBlurProxyRemove.getBlurType(), ViewBlurProxy.BlurType.BlurTypeBlendWallpaper.INSTANCE) && z) {
                viewBlurProxyRemove.setBlurType(ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE);
            }
            logViewBlurInfo(viewBlurProxyRemove, "[unBindBlurController] reason: " + str);
        }
        this.customCardViews.remove(view);
        this.decoratorsCardViews.remove(view);
        this.menuCardViews.remove(view);
        this.capsuleCardViews.remove(view);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final ViewBlurProxy requireBlurProxyForView(View view, CardType cardType, float f, NotificationBlurMode notificationBlurMode) {
        int iOrdinal;
        ViewBlurProxy viewBlurProxy = this.viewBlurProxyes.get(view);
        if (viewBlurProxy != null) {
            return viewBlurProxy;
        }
        LogBuffer logBuffer = DebugTool.INSTANCE.getLogBuffer();
        BlurConfigsProvider blurConfigsProviderCreateKeyguardStackingBlurConfig = null;
        boolean z = false;
        if (logBuffer != null) {
            logBuffer.log(TAG, LogLevel.DEBUG, "[requireBlurProxyForView] for " + DebugToolKt.getName(view, false) + " cardType " + cardType, (Throwable) null);
        }
        NotificationBlurMode notificationBlurModeResolveBlurMode = resolveBlurMode(cardType, view, notificationBlurMode);
        NotificationBlurMixColorParams.INSTANCE.getClass();
        int defaultBlurRadius = QSBlurConfigProvider.getDefaultBlurRadius();
        int i = this.cardBlurColor;
        BlurMixConfig mixConfig = getMixConfig(cardType, notificationBlurModeResolveBlurMode);
        BlurMixConfig.BlurMixSingle blurMixSingleHeadsupCardMotionMixConfig = headsupCardMotionMixConfig(cardType, notificationBlurMode);
        int iOrdinal2 = cardType.ordinal();
        int iIntValue = (iOrdinal2 == 0 || iOrdinal2 == 4) ? NotificationBlurMixColorParams.HeadsUpCardBlurParams.INSTANCE.getHeadsUpBlurRadius().get().intValue() : 0;
        NotificationPlatFormBlurParamsManager notificationPlatFormBlurParamsManager = NotificationPlatFormBlurParamsManager.INSTANCE;
        BlurConfig blurConfig = new BlurConfig(defaultBlurRadius, i, Float.valueOf(f), notificationPlatFormBlurParamsManager.getGradientStrokeLineParams(cardType, notificationBlurModeResolveBlurMode), null, notificationPlatFormBlurParamsManager.getInnerShadowParams(cardType, notificationBlurModeResolveBlurMode), false, mixConfig, blurMixSingleHeadsupCardMotionMixConfig, iIntValue, null, 31914684);
        CardType cardType2 = CardType.FULLSCREENBANNER;
        blurConfig.setCornerRadius(cardType == cardType2 ? this.fullScreenBannerRadius : this.blurCornerRadius);
        blurConfig.setCardType(cardType.ordinal());
        FeatureOption.INSTANCE.getClass();
        int i2 = 1;
        if (!FeatureOption.isExpRegion() && ((iOrdinal = cardType.ordinal()) == 0 || iOrdinal == 1 || iOrdinal == 7)) {
            z = true;
        }
        blurConfig.setSupportMetaBall(z);
        int iOrdinal3 = cardType.ordinal();
        if (iOrdinal3 == 0 || iOrdinal3 == 1 || iOrdinal3 == 7) {
            blurConfig.setGradientStrokeCornerParam(SmoothRoundEx.INSTANCE.createCornerParams(blurConfig.getCornerRadius(), true));
        }
        notificationPlatFormBlurParamsManager.updateMirrorScale(cardType, notificationBlurModeResolveBlurMode, blurConfig.getPlatformMixConfig(), useMotionBlur());
        if (cardType == CardType.NOTIFICATION) {
            KeyguardStackingBlurConfig.Companion companion = KeyguardStackingBlurConfig.Companion;
            int i3 = this.cardBlurColor;
            float f2 = cardType == cardType2 ? this.fullScreenBannerRadius : this.blurCornerRadius;
            int blurRadius = blurConfig.getBlurRadius();
            CornerParams cornerParamsCopy$default = CornerParams.copy$default(blurConfig.getGradientStrokeCornerParam());
            ViewBlurManager$$ExternalSyntheticLambda4 viewBlurManager$$ExternalSyntheticLambda4 = new ViewBlurManager$$ExternalSyntheticLambda4();
            viewBlurManager$$ExternalSyntheticLambda4.f$0 = this;
            VarHandle.storeStoreFence();
            blurConfigsProviderCreateKeyguardStackingBlurConfig = companion.createKeyguardStackingBlurConfig(i3, f2, f, blurRadius, cornerParamsCopy$default, viewBlurManager$$ExternalSyntheticLambda4);
        }
        BlurConfigsProvider blurConfigsProvider = blurConfigsProviderCreateKeyguardStackingBlurConfig;
        StaticBlurManager notifiAndQsBlurManager = ScrimControllerExImp.Companion.getNotifiAndQsBlurManager(this.context);
        ViewBlurManager$$ExternalSyntheticLambda0 viewBlurManager$$ExternalSyntheticLambda0 = new ViewBlurManager$$ExternalSyntheticLambda0(i2);
        viewBlurManager$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        ViewBlurProxy viewBlurProxy2 = new ViewBlurProxy(view, blurConfig, notifiAndQsBlurManager, viewBlurManager$$ExternalSyntheticLambda0, blurConfigsProvider);
        this.viewBlurProxyes.put(view, viewBlurProxy2);
        switch (cardType.ordinal()) {
            case 1:
                this.customCardViews.add(view);
                break;
            case 2:
            case 8:
                this.capsuleCardViews.add(view);
                break;
            case 3:
            case 5:
            case 6:
            case 9:
                this.decoratorsCardViews.add(view);
                break;
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                this.menuCardViews.add(view);
                break;
        }
        viewBlurProxy2.setCurrentWallpaperBlurBitmap(this.wallpaperBitmap);
        return viewBlurProxy2;
    }

    public final void resetCornerRadiusForView(View view) {
        BlurConfig blurConfig;
        ViewBlurProxy viewBlurProxy = this.viewBlurProxyes.get(view);
        if (viewBlurProxy == null || (blurConfig = viewBlurProxy.getBlurConfig()) == null) {
            return;
        }
        blurConfig.setCornerRadius(this.blurCornerRadius);
    }

    public final NotificationBlurMode resolveBlurMode(CardType cardType, View view, NotificationBlurMode notificationBlurMode) {
        ExpandableNotificationRow expandableNotificationRow;
        NotificationBlurMode notificationBlurMode2;
        if (cardType == CardType.NOTIFICATION) {
            if (notificationBlurMode != null && notificationBlurMode != NotificationBlurMode.UNINITIATED) {
                return notificationBlurMode;
            }
            if (view != null) {
                ViewParent parent = view.getParent();
                while (true) {
                    if (parent == null) {
                        expandableNotificationRow = null;
                        break;
                    }
                    if (parent instanceof ExpandableNotificationRow) {
                        expandableNotificationRow = (ExpandableNotificationRow) parent;
                        break;
                    }
                    parent = parent.getParent();
                }
                if (expandableNotificationRow != null && (notificationBlurMode2 = expandableNotificationRow.mRowEx.getNotificationBlurMode()) != NotificationBlurMode.UNINITIATED && !notificationBlurMode2.getNoBlur()) {
                    return notificationBlurMode2;
                }
            }
        }
        return globalBlurMode();
    }

    public final void setWallpaperBlurBitmap(Bitmap bitmap) {
        this.materialColorManager.onWallpaperBitmapChanged(bitmap);
        if (ScrimUtil.isBPAnimLevel()) {
            BuildersKt.launch$default(this.applicationScope, Dispatchers.getMain(), (CoroutineStart) null, new AnonymousClass1(bitmap, null), 2);
        }
    }

    public final void updatePlatformBlurParams(ViewBlurProxy viewBlurProxy, boolean z) {
        if (viewBlurProxy != null) {
            CardType cardType = (CardType) CardType.getEntries().get(viewBlurProxy.getBlurConfig().getCardType());
            NotificationBlurMode notificationBlurModeResolveBlurMode = resolveBlurMode(cardType, viewBlurProxy.getView(), null);
            BlurConfig blurConfig = viewBlurProxy.getBlurConfig();
            if (z) {
                blurConfig.setPlatformMixConfig(getMixConfig(cardType, notificationBlurModeResolveBlurMode));
            }
            if (cardType != CardType.SETTINGS && cardType != CardType.EXPAND_GROUP_BUTTON) {
                NotificationPlatFormBlurParamsManager notificationPlatFormBlurParamsManager = NotificationPlatFormBlurParamsManager.INSTANCE;
                blurConfig.setGradientStrokeLineParam(notificationPlatFormBlurParamsManager.getGradientStrokeLineParams(cardType, notificationBlurModeResolveBlurMode));
                blurConfig.setInnerShadowParams(notificationPlatFormBlurParamsManager.getInnerShadowParams(cardType, notificationBlurModeResolveBlurMode));
            }
            NotificationPlatFormBlurParamsManager.INSTANCE.updateMirrorScale(cardType, notificationBlurModeResolveBlurMode, blurConfig.getPlatformMixConfig(), useMotionBlur());
            BuildersKt.launch$default(this.applicationScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new ViewBlurManager$updatePlatformBlurParams$1$2(viewBlurProxy, null), 2);
        }
    }

    public final void updateResource() {
        this.blurCornerRadius = this.context.getResources().getDimensionPixelSize(2131170626);
        this.fullScreenBannerRadius = this.context.getResources().getDimensionPixelSize(2131168360);
        this.cardBlurColor = this.context.getColor(2131102893);
        this.context.getColor(2131101652);
        this.context.getColor(2131101651);
        this.lowGaussianNormalCardMixedTopLayerColor = this.context.getColor(2131102841);
        this.lowGaussianNormalCardMixedBottomLayerColor = this.context.getColor(2131102840);
    }

    public final void updateRowsBlur() {
        boolean zIsOnKeyGuard$default = isOnKeyGuard$default(this);
        LogBuffer logBuffer = DebugTool.INSTANCE.getLogBuffer();
        if (logBuffer != null) {
            logBuffer.log(TAG, LogLevel.DEBUG, KeyguardLogger$.ExternalSyntheticOutline0.m("[enableKeyguardRowBlur] isOnKeyGuard=", zIsOnKeyGuard$default), (Throwable) null);
        }
        ViewBlurManager$$ExternalSyntheticLambda2 viewBlurManager$$ExternalSyntheticLambda2 = new ViewBlurManager$$ExternalSyntheticLambda2(2);
        viewBlurManager$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        actionForAllCards(viewBlurManager$$ExternalSyntheticLambda2);
        ViewBlurProxy viewBlurProxy = this.mediaCarouselControllerExImp.getViewBlurProxy();
        if (viewBlurProxy != null) {
            if (zIsOnKeyGuard$default) {
                refreshCardBlurType(viewBlurProxy);
            } else {
                viewBlurProxy.setBlurType(ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE);
            }
        }
    }

    public final boolean useMotionBlur() {
        return ((Boolean) ((PlatformBlurManager) this.platformBlurManager.get()).getUseMotionBlur().getValue()).booleanValue();
    }

    public final ViewBlurProxy requireBlurProxyForView(View view, CardType cardType) {
        return requireBlurProxyForView(view, cardType, SmoothRoundEx.INSTANCE.getDefaultWeight(), null);
    }

    @Deprecated
    public final ViewBlurProxy requireBlurProxyForView(View view, boolean z, float f) {
        return requireBlurProxyForView$default(this, view, z ? CardType.CUSTOM : CardType.NOTIFICATION, f, null, 8);
    }
}
