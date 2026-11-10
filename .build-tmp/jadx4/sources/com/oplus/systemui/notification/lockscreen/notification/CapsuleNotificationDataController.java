package com.oplus.systemui.notification.lockscreen.notification;

import android.app.Notification;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Handler;
import android.os.LocaleList;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatDelegateImpl$;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.core.MonoSpline$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.compose.foundation.gestures.DefaultDraggableAnchors$;
import androidx.constraintlayout.widget.ConstraintSet$;
import androidx.exifinterface.media.ExifInterface$;
import com.android.app.displaylib.PerDisplayInstanceRepositoryImpl$;
import com.android.internal.util.ContrastColorUtil;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.NotificationActivityStarter;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.NotificationResolverEx;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.notification.helper.PrivacyHelper;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.shared.MetricKt;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplus.keyguard.folder.OplusBouncerLocationController$clickListener$1$;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.base.LockScreenNotificationKotlinExKt;
import com.oplus.systemui.notification.lockscreen.capsule.NotificationCapsuleContainerControllerImpl;
import com.oplus.systemui.notification.row.oplusgroup.GroupIconManager;
import com.oplus.systemui.statusbar.notification.collection.OplusNotificationEntryExImpl;
import com.oplus.systemui.statusbar.util.MCSUtil;
import com.oplus.systemui.utils.OplusContrastColorUtil;
import com.oplusos.systemui.common.blurability.DebugTool;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.AppInfoUtil;
import com.oplusos.systemui.common.utils.OplusMultiAppUtils;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.Regex$;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleNotificationDataController implements Dumpable, NotifCollectionListener {
    private static final String METRIC_CONTENT_SEPARATOR = " · ";
    private static final String METRIC_LABEL_VALUE_SEPARATOR = ":";
    private static final String TAG = "CapsuleNotificationDataController";
    private final Lazy aggregateIcon$delegate;
    private final dagger.Lazy capsuleContainerController;
    private final Lazy colorUtil$delegate;
    private final CapsuleNotificationDataController$configurationCallback$1 configurationCallback;
    private final dagger.Lazy configurationController;
    private final Context context;
    private CapsuleNotificationContainerView currentContainer;
    private final dagger.Lazy dumpManager;
    public boolean hasEnterKeyguard;
    public boolean hasExitKeyguard;
    private final Map<String, Integer> iconInfoCache = new LinkedHashMap();
    private final dagger.Lazy iconManager;
    public boolean isNewNotificationPinned;
    public boolean isSystemStateListenersRegistered;
    private String key;
    private final dagger.Lazy keyguardStateController;
    private CapsuleNotificationInnerData lastAggregateData;
    private List<? extends PipelineEntry> lastEntries;
    private Boolean lastKeyguardShowing;
    private final dagger.Lazy lockScreenExtStateController;
    private final dagger.Lazy mPrivacyHelper;
    private final dagger.Lazy mainHandler;
    private final CoroutineScope mainScope;
    private final dagger.Lazy mediaPlayerDataHelper;
    private final dagger.Lazy notificationActivityStarter;
    private final dagger.Lazy notificationResolverEx;
    private NotificationEntry pendingNewNotification;
    private final dagger.Lazy pipeline;
    public long preLatestPostTime;
    private final Mutex refreshMutex;
    public int textColor;
    public static final Companion Companion = new Companion();
    private static final Set<String> PACKAGES_ALLOW_NOTIF_UPDATE = SetsKt__SetsKt.setOf(new String[]{"com.tencent.mm", "com.tencent.mobileqq", "com.ss.android.ugc.aweme", "com.xingin.xhs", "com.sina.weibo", "cn.soulapp.android", "com.realmecomm.app", "com.tencent.gamehelper.smoba", "com.meta.box", "com.tencent.gamehelper.pg", "com.huanyou.haituan", "com.immomo.momo", "com.baidu.tieba", "com.oneplus.bbs", "com.whatsapp", "com.facebook.katana", "com.instagram.android", "com.facebook.orca", "com.snapchat.android", "com.ss.android.ugc.trill", "org.telegram.messenger", "com.facebook.lite", "com.whatsapp.w4b", "com.pinterest", "com.zhiliaoapp.musically.go", "us.zoom.videomeetings", "com.imo.android.imoim", "com.twitter.android", "in.mohalla.sharechat", "jp.naver.line.android", "com.zing.zalo", "com.cardfeed.video_public", "com.viber.voip", "com.discord"});
    private static final Set<String> PACKAGES_USE_APP_ICON = SetsKt__SetsJVMKt.setOf("com.microsoft.appmanager");
    private static final Set<String> PACKAGES_SELF_PLATED_SMALL_ICON = SetsKt__SetsJVMKt.setOf("com.android.connectivity.resources");

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleNotificationReportData {
        public final int count;
        public final boolean isIconShow;
        private final String pkg;

        public CapsuleNotificationReportData(String str, int i, boolean z) {
            this.pkg = str;
            this.count = i;
            this.isIconShow = z;
        }

        public final String component1() {
            return this.pkg;
        }

        public final CapsuleNotificationReportData copy(String str, int i, boolean z) {
            return new CapsuleNotificationReportData(str, i, z);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CapsuleNotificationReportData)) {
                return false;
            }
            CapsuleNotificationReportData capsuleNotificationReportData = (CapsuleNotificationReportData) obj;
            return Intrinsics.areEqual(this.pkg, capsuleNotificationReportData.pkg) && this.count == capsuleNotificationReportData.count && this.isIconShow == capsuleNotificationReportData.isIconShow;
        }

        public final int getCount() {
            return this.count;
        }

        public final String getPkg() {
            return this.pkg;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isIconShow) + MonoSpline$.ExternalSyntheticOutline0.m(this.count, this.pkg.hashCode() * 31, 31);
        }

        public String toString() {
            return AppCompatDelegateImpl$.ExternalSyntheticOutline0.m(")", ConstraintSet$.ExternalSyntheticOutline0.m(this.count, "CapsuleNotificationReportData(pkg=", this.pkg, ", count=", ", isIconShow="), this.isIconShow);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CardType {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ CardType[] $VALUES;
        public static final CardType AGGREGATE;
        public static final CardType GROUP;
        public static final CardType SINGLE;
        public static final CardType SINGLE_MESSAGE;

        static {
            CardType cardType = new CardType("SINGLE", 0);
            SINGLE = cardType;
            CardType cardType2 = new CardType("SINGLE_MESSAGE", 1);
            SINGLE_MESSAGE = cardType2;
            CardType cardType3 = new CardType("GROUP", 2);
            GROUP = cardType3;
            CardType cardType4 = new CardType("AGGREGATE", 3);
            AGGREGATE = cardType4;
            CardType[] cardTypeArr = {cardType, cardType2, cardType3, cardType4};
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

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RefreshReason {
        public static final /* synthetic */ EnumEntries $ENTRIES;
        public static final /* synthetic */ RefreshReason[] $VALUES;
        public static final RefreshReason MEDIA_CHANGE;
        public static final RefreshReason NOTIFICATION_CHANGE;

        static {
            RefreshReason refreshReason = new RefreshReason("NOTIFICATION_CHANGE", 0);
            NOTIFICATION_CHANGE = refreshReason;
            RefreshReason refreshReason2 = new RefreshReason("MEDIA_CHANGE", 1);
            MEDIA_CHANGE = refreshReason2;
            RefreshReason[] refreshReasonArr = {refreshReason, refreshReason2};
            $VALUES = refreshReasonArr;
            $ENTRIES = EnumEntriesKt.enumEntries(refreshReasonArr);
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static RefreshReason valueOf(String str) {
            return (RefreshReason) Enum.valueOf(RefreshReason.class, str);
        }

        public static RefreshReason[] values() {
            return (RefreshReason[]) $VALUES.clone();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController$buildIconData$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            CapsuleNotificationDataController capsuleNotificationDataController = CapsuleNotificationDataController.this;
            Companion companion = CapsuleNotificationDataController.Companion;
            return capsuleNotificationDataController.buildIconData(null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController$buildSingleNotificationData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C01881 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C01881(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            CapsuleNotificationDataController capsuleNotificationDataController = CapsuleNotificationDataController.this;
            Companion companion = CapsuleNotificationDataController.Companion;
            return capsuleNotificationDataController.buildSingleNotificationData(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController$getIconData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C01891 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        boolean Z$1;
        int label;
        /* synthetic */ Object result;

        public C01891(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            CapsuleNotificationDataController capsuleNotificationDataController = CapsuleNotificationDataController.this;
            Companion companion = CapsuleNotificationDataController.Companion;
            return capsuleNotificationDataController.getIconData(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController$handleNewNotification$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C01901 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C01901(ContinuationImpl continuationImpl) {
            super(continuationImpl);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            CapsuleNotificationDataController capsuleNotificationDataController = CapsuleNotificationDataController.this;
            Companion companion = CapsuleNotificationDataController.Companion;
            return capsuleNotificationDataController.handleNewNotification(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController$refreshCapsuleData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C01911 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        int I$6;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        public C01911(Continuation continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CapsuleNotificationDataController.this.refreshCapsuleData(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController$registerSystemStateListeners$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C01921 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController$registerSystemStateListeners$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C00241 implements FlowCollector {
            public /* synthetic */ CapsuleNotificationDataController this$0;

            public final Object emit(Object obj, Continuation continuation) {
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                CapsuleNotificationDataController capsuleNotificationDataController = this.this$0;
                capsuleNotificationDataController.textColor = zBooleanValue ? -16777216 : -1;
                CapsuleNotificationContainerView capsuleNotificationContainerView = capsuleNotificationDataController.currentContainer;
                if (capsuleNotificationContainerView != null) {
                    int textColor = capsuleNotificationDataController.getTextColor();
                    int childCount = capsuleNotificationContainerView.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View childAt = capsuleNotificationContainerView.getChildAt(i);
                        CapsuleNotificationCardView capsuleNotificationCardView = childAt instanceof CapsuleNotificationCardView ? (CapsuleNotificationCardView) childAt : null;
                        if (capsuleNotificationCardView != null) {
                            capsuleNotificationCardView.applyTextColor(textColor);
                        }
                    }
                }
                return Unit.INSTANCE;
            }
        }

        public C01921(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return CapsuleNotificationDataController.this.new C01921(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlowIsKeyguardLightColor = ((NotificationCapsuleContainerControllerImpl) CapsuleNotificationDataController.this.capsuleContainerController.get()).isKeyguardLightColor();
                CapsuleNotificationDataController capsuleNotificationDataController = CapsuleNotificationDataController.this;
                C00241 c00241 = new C00241();
                c00241.this$0 = capsuleNotificationDataController;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (stateFlowIsKeyguardLightColor.collect(c00241, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController$registerSystemStateListeners$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements KeyguardStateController.Callback {
        public /* synthetic */ CapsuleNotificationDataController this$0;

        public final void onKeyguardShowingChanged() {
            CapsuleNotificationDataController capsuleNotificationDataController = this.this$0;
            KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateController) capsuleNotificationDataController.keyguardStateController.get();
            if (keyguardStateControllerImpl != null) {
                boolean z = keyguardStateControllerImpl.mShowing;
                Boolean bool = capsuleNotificationDataController.lastKeyguardShowing;
                capsuleNotificationDataController.lastKeyguardShowing = Boolean.valueOf(z);
                if (Intrinsics.areEqual(bool, Boolean.FALSE) && z) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "CapsuleNotificationDataController-->enter keyguard: mark hasEnterKeyguard = true, should suppress first show anim");
                    }
                    capsuleNotificationDataController.hasEnterKeyguard = true;
                    Handler handler = (Handler) capsuleNotificationDataController.mainHandler.get();
                    if (handler != null) {
                        CapsuleNotificationDataController$registerSystemStateListeners$2$onKeyguardShowingChanged$2 capsuleNotificationDataController$registerSystemStateListeners$2$onKeyguardShowingChanged$2 = new CapsuleNotificationDataController$registerSystemStateListeners$2$onKeyguardShowingChanged$2();
                        capsuleNotificationDataController$registerSystemStateListeners$2$onKeyguardShowingChanged$2.this$0 = capsuleNotificationDataController;
                        VarHandle.storeStoreFence();
                        handler.postDelayed(capsuleNotificationDataController$registerSystemStateListeners$2$onKeyguardShowingChanged$2, 3000L);
                    }
                }
                if (!Intrinsics.areEqual(bool, Boolean.TRUE) || z) {
                    return;
                }
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "CapsuleNotificationDataController-->exit keyguard: mark hasExitKeyguard = true");
                }
                capsuleNotificationDataController.hasExitKeyguard = true;
            }
        }
    }

    public CapsuleNotificationDataController(Context context, dagger.Lazy lazy, dagger.Lazy lazy2, dagger.Lazy lazy3, dagger.Lazy lazy4, dagger.Lazy lazy5, dagger.Lazy lazy6, dagger.Lazy lazy7, dagger.Lazy lazy8, dagger.Lazy lazy9, dagger.Lazy lazy10, dagger.Lazy lazy11, CoroutineScope coroutineScope, dagger.Lazy lazy12) {
        this.context = context;
        this.notificationResolverEx = lazy;
        this.lockScreenExtStateController = lazy2;
        this.mPrivacyHelper = lazy3;
        this.capsuleContainerController = lazy4;
        this.configurationController = lazy5;
        this.iconManager = lazy6;
        this.notificationActivityStarter = lazy7;
        this.dumpManager = lazy8;
        this.mediaPlayerDataHelper = lazy9;
        this.pipeline = lazy10;
        this.keyguardStateController = lazy11;
        this.mainScope = coroutineScope;
        this.mainHandler = lazy12;
        CapsuleNotificationDataController$$ExternalSyntheticLambda0 capsuleNotificationDataController$$ExternalSyntheticLambda0 = new CapsuleNotificationDataController$$ExternalSyntheticLambda0(0);
        capsuleNotificationDataController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.aggregateIcon$delegate = LazyKt__LazyJVMKt.lazy(capsuleNotificationDataController$$ExternalSyntheticLambda0);
        CapsuleNotificationDataController$$ExternalSyntheticLambda0 capsuleNotificationDataController$$ExternalSyntheticLambda1 = new CapsuleNotificationDataController$$ExternalSyntheticLambda0(1);
        capsuleNotificationDataController$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.colorUtil$delegate = LazyKt__LazyJVMKt.lazy(capsuleNotificationDataController$$ExternalSyntheticLambda1);
        this.key = CapsuleNotificationData.CAPSULE_NOTIF_KEY_PREFIX;
        this.refreshMutex = MutexKt.Mutex(false);
        this.lastEntries = CollectionsKt__CollectionsKt.emptyList();
        this.textColor = -1;
        CapsuleNotificationDataController$configurationCallback$1 capsuleNotificationDataController$configurationCallback$1 = new CapsuleNotificationDataController$configurationCallback$1();
        capsuleNotificationDataController$configurationCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.configurationCallback = capsuleNotificationDataController$configurationCallback$1;
    }

    public static List generateCapsuleNotificationReportData(List list, NotificationIconData notificationIconData) {
        StatusBarNotification sbn;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            NotificationEntry notificationEntry = (PipelineEntry) it.next();
            if (notificationEntry instanceof GroupEntry) {
                GroupEntry groupEntry = (GroupEntry) notificationEntry;
                NotificationEntry summary = groupEntry.getSummary();
                String packageName = (summary == null || (sbn = summary.getSbn()) == null) ? null : sbn.getPackageName();
                if (packageName != null) {
                    linkedHashMap.put(packageName, Integer.valueOf(((Number) linkedHashMap.getOrDefault(packageName, 0)).intValue() + groupEntry.getUntruncatedChildCount()));
                }
            } else if (notificationEntry instanceof NotificationEntry) {
                String packageName2 = notificationEntry.getSbn().getPackageName();
                packageName2.getClass();
                linkedHashMap.put(packageName2, Integer.valueOf(((Number) linkedHashMap.getOrDefault(packageName2, 0)).intValue() + 1));
            }
        }
        String packageName3 = notificationIconData.getPackageName();
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            String str = (String) entry.getKey();
            arrayList.add(new CapsuleNotificationReportData(str, ((Number) entry.getValue()).intValue(), Intrinsics.areEqual(str, packageName3)));
        }
        return arrayList;
    }

    public static String generateKeyForContainer(CapsuleNotificationContainerView capsuleNotificationContainerView) {
        return ListPopupWindow$.ExternalSyntheticOutline0.m(System.identityHashCode(capsuleNotificationContainerView), CapsuleNotificationData.CAPSULE_NOTIF_KEY_PREFIX);
    }

    private final Drawable getAggregateIcon() {
        return (Drawable) this.aggregateIcon$delegate.getValue();
    }

    private final ContrastColorUtil getColorUtil() {
        return (ContrastColorUtil) this.colorUtil$delegate.getValue();
    }

    private final CapsuleNotificationContainerView getOrCreateCurrentContainer() {
        CapsuleNotificationContainerView capsuleNotificationContainerView = this.currentContainer;
        if (capsuleNotificationContainerView != null) {
            return capsuleNotificationContainerView;
        }
        CapsuleNotificationContainerView capsuleNotificationContainerViewCreateNewContainer = createNewContainer();
        setCurrentContainer(capsuleNotificationContainerViewCreateNewContainer);
        return capsuleNotificationContainerViewCreateNewContainer;
    }

    private final void setCurrentContainer(CapsuleNotificationContainerView capsuleNotificationContainerView) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusBouncerLocationController$clickListener$1$.ExternalSyntheticOutline0.m("set currentContainer -> hash:", capsuleNotificationContainerView != null ? Integer.valueOf(System.identityHashCode(capsuleNotificationContainerView)) : "null", "CapsuleNotificationDataController-->", "SystemUi--Notification");
        }
        this.currentContainer = capsuleNotificationContainerView;
    }

    /* JADX WARN: Code duplicated, block: B:119:0x0251 A[EDGE_INSN: B:119:0x0251->B:93:0x01df BREAK  A[LOOP:3: B:108:0x021b->B:205:0x021b]] */
    /* JADX WARN: Code duplicated, block: B:124:0x0264  */
    /* JADX WARN: Code duplicated, block: B:126:0x0267  */
    /* JADX WARN: Code duplicated, block: B:128:0x0272  */
    /* JADX WARN: Code duplicated, block: B:130:0x0286  */
    /* JADX WARN: Code duplicated, block: B:132:0x02a6  */
    /* JADX WARN: Code duplicated, block: B:133:0x02a8  */
    /* JADX WARN: Code duplicated, block: B:136:0x02b3  */
    /* JADX WARN: Code duplicated, block: B:138:0x02b6  */
    /* JADX WARN: Code duplicated, block: B:143:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:146:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:149:0x02d1  */
    /* JADX WARN: Code duplicated, block: B:150:0x02d6  */
    /* JADX WARN: Code duplicated, block: B:153:0x02dd  */
    /* JADX WARN: Code duplicated, block: B:156:0x02e6  */
    /* JADX WARN: Code duplicated, block: B:157:0x02e8  */
    /* JADX WARN: Code duplicated, block: B:158:0x02ea  */
    /* JADX WARN: Code duplicated, block: B:163:0x02fb  */
    /* JADX WARN: Code duplicated, block: B:165:0x02fe  */
    /* JADX WARN: Code duplicated, block: B:168:0x0305  */
    /* JADX WARN: Code duplicated, block: B:169:0x0307  */
    /* JADX WARN: Code duplicated, block: B:176:0x031a  */
    /* JADX WARN: Code duplicated, block: B:177:0x031c  */
    /* JADX WARN: Code duplicated, block: B:180:0x0327  */
    /* JADX WARN: Code duplicated, block: B:181:0x032e  */
    /* JADX WARN: Code duplicated, block: B:184:0x0335  */
    /* JADX WARN: Code duplicated, block: B:185:0x0349  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r18v0, types: [java.lang.Integer, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v22, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v43, types: [com.oplus.systemui.notification.lockscreen.notification.CapsuleMediaPlayerDataHelper] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r9v5 */
    public final Object buildAggregateNotificationData(List list, int i, NotificationIconData notificationIconData, int i2, List list2, boolean z, Continuation continuation) {
        List list3;
        int i3;
        NotificationEntry latestEntry;
        NotificationEntry notificationEntry;
        String packageName;
        Notification notification;
        String string;
        ?? r9;
        Class notificationStyle;
        String string2;
        Bundle bundle;
        CharSequence charSequence;
        String string3;
        Bundle bundle2;
        CharSequence charSequence2;
        Integer numBoxInt;
        String appNameByPkgName;
        ?? quantityString;
        String strBuildMetricCapsuleContent;
        Bundle bundle3;
        CharSequence charSequence3;
        StatusBarNotification sbn;
        ?? r18;
        PrivacyHelper privacyHelper;
        StatusBarNotification sbn2;
        List attachedNotifChildren;
        StatusBarNotification sbn3;
        NotificationEntry notificationEntry2;
        NotificationEntry summary;
        List attachedNotifChildren2;
        ?? r1;
        StatusBarNotification sbn4;
        String packageName2;
        List<String> mediaPackageNames;
        int i4 = 0;
        if (z) {
            String quantityString2 = this.context.getResources().getQuantityString(2131820563, i, Boxing.boxInt(i));
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            CapsuleMediaPlayerDataHelper capsuleMediaPlayerDataHelper = (CapsuleMediaPlayerDataHelper) this.mediaPlayerDataHelper.get();
            if (capsuleMediaPlayerDataHelper != null && (mediaPackageNames = capsuleMediaPlayerDataHelper.getMediaPackageNames()) != null) {
                linkedHashSet.addAll(mediaPackageNames);
            }
            if (linkedHashSet.size() < 4) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    GroupEntry groupEntry = (PipelineEntry) it.next();
                    if (groupEntry instanceof GroupEntry) {
                        NotificationEntry summary2 = groupEntry.getSummary();
                        if (summary2 != null && (sbn4 = summary2.getSbn()) != null && (packageName2 = sbn4.getPackageName()) != null) {
                            linkedHashSet.add(packageName2);
                        }
                    } else if (groupEntry instanceof NotificationEntry) {
                        linkedHashSet.add(((NotificationEntry) groupEntry).getSbn().getPackageName());
                    }
                    if (linkedHashSet.size() >= 4) {
                        break;
                    }
                }
            }
            LocaleList locales = this.context.getResources().getConfiguration().getLocales();
            String str = Intrinsics.areEqual((locales.size() > 0 ? locales.get(0) : Locale.getDefault()).getLanguage(), Locale.CHINESE.getLanguage()) ? "、" : ", ";
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(linkedHashSet, 10));
            Iterator it2 = linkedHashSet.iterator();
            while (it2.hasNext()) {
                arrayList.add(AppInfoUtil.getAppNameByPkgName(this.context, (String) it2.next()));
            }
            ArrayList arrayList2 = new ArrayList();
            int size = arrayList.size();
            while (i4 < size) {
                Object obj = arrayList.get(i4);
                i4++;
                String str2 = (String) obj;
                str2.getClass();
                if (!StringsKt__StringsKt.isBlank(str2)) {
                    arrayList2.add(obj);
                }
            }
            return new CapsuleNotificationInnerData(notificationIconData, quantityString2, CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt___CollectionsKt.take(CollectionsKt___CollectionsKt.distinct(arrayList2), 4), str, (CharSequence) null, (CharSequence) null, (Function1) null, 62), CardType.AGGREGATE, null, null, null, i, 0, list2, 368);
        }
        if (i2 <= 0 || (r1 = (CapsuleMediaPlayerDataHelper) this.mediaPlayerDataHelper.get()) == 0) {
            list3 = list;
            i3 = i;
        } else {
            list3 = list;
            CapsuleNotificationInnerData capsuleNotificationInnerDataBuildMediaSingleIfNeed = r1.buildMediaSingleIfNeed(this.context, list3, notificationIconData, i2, i, list2);
            i3 = i;
            if (capsuleNotificationInnerDataBuildMediaSingleIfNeed != null) {
                return capsuleNotificationInnerDataBuildMediaSingleIfNeed;
            }
        }
        Object obj2 = null;
        if (i3 == 1 && list3.size() == 1) {
            GroupEntry groupEntry2 = (PipelineEntry) CollectionsKt___CollectionsKt.first(list3);
            if (groupEntry2 instanceof NotificationEntry) {
                notificationEntry2 = (NotificationEntry) groupEntry2;
            } else {
                notificationEntry2 = (!(groupEntry2 instanceof GroupEntry) || (summary = groupEntry2.getSummary()) == null || (attachedNotifChildren2 = summary.getAttachedNotifChildren()) == null) ? null : (NotificationEntry) CollectionsKt___CollectionsKt.firstOrNull(attachedNotifChildren2);
            }
            if (notificationEntry2 != null) {
                return buildSingleNotificationData(this.context, notificationEntry2, notificationIconData, list2, (ContinuationImpl) continuation);
            }
        }
        if (list3.size() != 1 || !(CollectionsKt___CollectionsKt.first(list3) instanceof GroupEntry)) {
            latestEntry = this.pendingNewNotification;
            if (latestEntry == null) {
                latestEntry = CapsuleNotificationUtils.getLatestEntry(list3);
                break;
            }
            String packageName3 = latestEntry.getSbn().getPackageName();
            List list4 = list3;
            if (!(list4 instanceof Collection) || !list4.isEmpty()) {
                Iterator it3 = list4.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        latestEntry = CapsuleNotificationUtils.getLatestEntry(list3);
                        break;
                    }
                    NotificationEntry notificationEntry3 = (PipelineEntry) it3.next();
                    if ((notificationEntry3 instanceof NotificationEntry) && Intrinsics.areEqual(notificationEntry3.getSbn().getPackageName(), packageName3)) {
                        if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            break;
                            break;
                        }
                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("UnGroup: use pendingNewNotification for group notification: ", latestEntry.getKey(), "CapsuleNotificationDataController-->", "SystemUi--Notification");
                        break;
                    }
                }
            } else {
                latestEntry = CapsuleNotificationUtils.getLatestEntry(list3);
                break;
            }
        } else {
            GroupEntry groupEntry3 = (GroupEntry) CollectionsKt___CollectionsKt.first(list3);
            NotificationEntry summary3 = groupEntry3.getSummary();
            String packageName4 = (summary3 == null || (sbn3 = summary3.getSbn()) == null) ? null : sbn3.getPackageName();
            latestEntry = this.pendingNewNotification;
            if (latestEntry == null || !Intrinsics.areEqual(latestEntry.getSbn().getPackageName(), packageName4)) {
                NotificationEntry summary4 = groupEntry3.getSummary();
                if (summary4 == null || (attachedNotifChildren = summary4.getAttachedNotifChildren()) == null) {
                    notificationEntry = null;
                } else {
                    latestEntry = (NotificationEntry) CollectionsKt___CollectionsKt.firstOrNull(attachedNotifChildren);
                }
                if (notificationEntry != null || (sbn2 = notificationEntry.getSbn()) == null) {
                    packageName = null;
                } else {
                    packageName = sbn2.getPackageName();
                }
                if (packageName == null) {
                    if (notificationEntry != null || (sbn = notificationEntry.getSbn()) == null) {
                        notification = null;
                    } else {
                        notification = sbn.getNotification();
                    }
                    if (notification != null || (bundle3 = notification.extras) == null || (charSequence3 = bundle3.getCharSequence("android.title")) == null) {
                        string = null;
                    } else {
                        string = charSequence3.toString();
                    }
                    r9 = "";
                    if (string == null) {
                        string = "";
                    }
                    if (notification != null) {
                        notificationStyle = notification.getNotificationStyle();
                    } else {
                        notificationStyle = null;
                    }
                    if (Notification.MetricStyle.class.equals(notificationStyle)) {
                        strBuildMetricCapsuleContent = buildMetricCapsuleContent(this.context, notification);
                        if (strBuildMetricCapsuleContent != null) {
                            r9 = strBuildMetricCapsuleContent;
                        }
                    } else {
                        if (notification != null || (bundle2 = notification.extras) == null || (charSequence2 = bundle2.getCharSequence("android.text")) == null) {
                            string2 = null;
                        } else {
                            string2 = charSequence2.toString();
                        }
                        if (string2 == null) {
                            string2 = "";
                        }
                        if (StringsKt__StringsKt.isBlank(string2)) {
                            if (notification != null && (bundle = notification.extras) != null && (charSequence = bundle.getCharSequence("android.bigText")) != null) {
                                string3 = charSequence.toString();
                            }
                            if (obj2 != null) {
                                obj2 = string3;
                                r9 = obj2;
                            }
                        } else {
                            r9 = string2;
                        }
                    }
                    obj2 = string3;
                    numBoxInt = Boxing.boxInt(i3);
                    if (StringsKt__StringsKt.isBlank(string)) {
                        appNameByPkgName = AppInfoUtil.getAppNameByPkgName(this.context, packageName);
                    } else {
                        appNameByPkgName = string;
                    }
                    if (StringsKt__StringsKt.isBlank((CharSequence) r9)) {
                        quantityString = this.context.getResources().getQuantityString(2131820563, i3, Boxing.boxInt(i3));
                        obj2 = numBoxInt;
                    } else {
                        quantityString = r9;
                        obj2 = numBoxInt;
                    }
                } else {
                    privacyHelper = (PrivacyHelper) this.mPrivacyHelper.get();
                    if (privacyHelper != null ? privacyHelper.needEncrypted(packageName, notificationEntry.getSbn().getUid(), notificationEntry.getKey(), notificationEntry, false) : false) {
                        appNameByPkgName = AppInfoUtil.getAppNameByPkgName(this.context, packageName);
                        quantityString = this.context.getResources().getQuantityString(2131820563, i3, Boxing.boxInt(i3));
                    } else {
                        if (notificationEntry != null) {
                            notification = null;
                        } else {
                            notification = null;
                        }
                        if (notification != null) {
                            string = null;
                        } else {
                            string = null;
                        }
                        r9 = "";
                        if (string == null) {
                            string = "";
                        }
                        if (notification != null) {
                            notificationStyle = notification.getNotificationStyle();
                        } else {
                            notificationStyle = null;
                        }
                        if (Notification.MetricStyle.class.equals(notificationStyle)) {
                            strBuildMetricCapsuleContent = buildMetricCapsuleContent(this.context, notification);
                            if (strBuildMetricCapsuleContent != null) {
                                r9 = strBuildMetricCapsuleContent;
                            }
                        } else {
                            if (notification != null) {
                                string2 = null;
                            } else {
                                string2 = null;
                            }
                            if (string2 == null) {
                                string2 = "";
                            }
                            if (StringsKt__StringsKt.isBlank(string2)) {
                                if (notification != null) {
                                    string3 = charSequence.toString();
                                }
                                if (obj2 != null) {
                                    obj2 = string3;
                                    r9 = obj2;
                                }
                            } else {
                                r9 = string2;
                            }
                        }
                        obj2 = string3;
                        numBoxInt = Boxing.boxInt(i3);
                        if (StringsKt__StringsKt.isBlank(string)) {
                            appNameByPkgName = AppInfoUtil.getAppNameByPkgName(this.context, packageName);
                        } else {
                            appNameByPkgName = string;
                        }
                        if (StringsKt__StringsKt.isBlank((CharSequence) r9)) {
                            quantityString = this.context.getResources().getQuantityString(2131820563, i3, Boxing.boxInt(i3));
                            obj2 = numBoxInt;
                        } else {
                            quantityString = r9;
                            obj2 = numBoxInt;
                        }
                    }
                }
                r18 = obj2;
                String str3 = appNameByPkgName;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    ?? sb = new StringBuilder("build group entries: ");
                    sb.append(list3);
                    sb.append(", badgeCount: ");
                    sb.append(r18);
                    sb.append(", allCount: ");
                    MobileInputLogger$.ExternalSyntheticOutline0.m(i3, "CapsuleNotificationDataController-->", "SystemUi--Notification", (StringBuilder) sb);
                }
                return new CapsuleNotificationInnerData(notificationIconData, str3, quantityString, CardType.GROUP, r18, null, notificationIconData.getIconColor(), i3, i2, list2, 32);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("Group: use pendingNewNotification for group notification: ", latestEntry.getKey(), "CapsuleNotificationDataController-->", "SystemUi--Notification");
            }
        }
        notificationEntry = latestEntry;
        if (notificationEntry != null) {
            packageName = null;
        } else {
            packageName = null;
        }
        if (packageName == null) {
            if (notificationEntry != null) {
                notification = null;
            } else {
                notification = null;
            }
            if (notification != null) {
                string = null;
            } else {
                string = null;
            }
            r9 = "";
            if (string == null) {
                string = "";
            }
            if (notification != null) {
                notificationStyle = notification.getNotificationStyle();
            } else {
                notificationStyle = null;
            }
            if (Notification.MetricStyle.class.equals(notificationStyle)) {
                strBuildMetricCapsuleContent = buildMetricCapsuleContent(this.context, notification);
                if (strBuildMetricCapsuleContent != null) {
                    r9 = strBuildMetricCapsuleContent;
                }
            } else {
                if (notification != null) {
                    string2 = null;
                } else {
                    string2 = null;
                }
                if (string2 == null) {
                    string2 = "";
                }
                if (StringsKt__StringsKt.isBlank(string2)) {
                    if (notification != null) {
                        string3 = charSequence.toString();
                    }
                    if (obj2 != null) {
                        obj2 = string3;
                        r9 = obj2;
                    }
                } else {
                    r9 = string2;
                }
            }
            obj2 = string3;
            numBoxInt = Boxing.boxInt(i3);
            if (StringsKt__StringsKt.isBlank(string)) {
                appNameByPkgName = AppInfoUtil.getAppNameByPkgName(this.context, packageName);
            } else {
                appNameByPkgName = string;
            }
            if (StringsKt__StringsKt.isBlank((CharSequence) r9)) {
                quantityString = this.context.getResources().getQuantityString(2131820563, i3, Boxing.boxInt(i3));
                obj2 = numBoxInt;
            } else {
                quantityString = r9;
                obj2 = numBoxInt;
            }
        } else {
            privacyHelper = (PrivacyHelper) this.mPrivacyHelper.get();
            if (privacyHelper != null ? privacyHelper.needEncrypted(packageName, notificationEntry.getSbn().getUid(), notificationEntry.getKey(), notificationEntry, false) : false) {
                appNameByPkgName = AppInfoUtil.getAppNameByPkgName(this.context, packageName);
                quantityString = this.context.getResources().getQuantityString(2131820563, i3, Boxing.boxInt(i3));
            } else {
                if (notificationEntry != null) {
                    notification = null;
                } else {
                    notification = null;
                }
                if (notification != null) {
                    string = null;
                } else {
                    string = null;
                }
                r9 = "";
                if (string == null) {
                    string = "";
                }
                if (notification != null) {
                    notificationStyle = notification.getNotificationStyle();
                } else {
                    notificationStyle = null;
                }
                if (Notification.MetricStyle.class.equals(notificationStyle)) {
                    strBuildMetricCapsuleContent = buildMetricCapsuleContent(this.context, notification);
                    if (strBuildMetricCapsuleContent != null) {
                        r9 = strBuildMetricCapsuleContent;
                    }
                } else {
                    if (notification != null) {
                        string2 = null;
                    } else {
                        string2 = null;
                    }
                    if (string2 == null) {
                        string2 = "";
                    }
                    if (StringsKt__StringsKt.isBlank(string2)) {
                        if (notification != null) {
                            string3 = charSequence.toString();
                        }
                        if (obj2 != null) {
                            obj2 = string3;
                            r9 = obj2;
                        }
                    } else {
                        r9 = string2;
                    }
                }
                obj2 = string3;
                numBoxInt = Boxing.boxInt(i3);
                if (StringsKt__StringsKt.isBlank(string)) {
                    appNameByPkgName = AppInfoUtil.getAppNameByPkgName(this.context, packageName);
                } else {
                    appNameByPkgName = string;
                }
                if (StringsKt__StringsKt.isBlank((CharSequence) r9)) {
                    quantityString = this.context.getResources().getQuantityString(2131820563, i3, Boxing.boxInt(i3));
                    obj2 = numBoxInt;
                } else {
                    quantityString = r9;
                    obj2 = numBoxInt;
                }
            }
        }
        r18 = obj2;
        String str4 = appNameByPkgName;
        if (LogUtil.getNormalDebug()) {
            ?? sb2 = new StringBuilder("build group entries: ");
            sb2.append(list3);
            sb2.append(", badgeCount: ");
            sb2.append(r18);
            sb2.append(", allCount: ");
            MobileInputLogger$.ExternalSyntheticOutline0.m(i3, "CapsuleNotificationDataController-->", "SystemUi--Notification", (StringBuilder) sb2);
        }
        return new CapsuleNotificationInnerData(notificationIconData, str4, quantityString, CardType.GROUP, r18, null, notificationIconData.getIconColor(), i3, i2, list2, 32);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0072  */
    /* JADX WARN: Code duplicated, block: B:33:0x0096 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005e, code lost:
    
        if (r9 == r1) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x008e, code lost:
    
        if (r9 == r1) goto L29;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x008e -> B:30:0x0091). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object buildIconData(java.util.List r8, kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController$buildIconData$1 r0 = (com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController$buildIconData$1 r0 = new com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController$buildIconData$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L47
            if (r2 == r5) goto L3f
            if (r2 != r4) goto L39
            java.lang.Object r8 = r0.L$2
            com.android.systemui.statusbar.notification.collection.PipelineEntry r8 = (com.android.systemui.statusbar.notification.collection.PipelineEntry) r8
            java.lang.Object r8 = r0.L$1
            java.util.Iterator r8 = (java.util.Iterator) r8
            java.lang.Object r2 = r0.L$0
            java.util.List r2 = (java.util.List) r2
            kotlin.ResultKt.throwOnFailure(r9)
            goto L91
        L39:
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            okio.Path$.ExternalSyntheticBUOutline0.m$1(r7)
            return r3
        L3f:
            java.lang.Object r8 = r0.L$0
            java.util.List r8 = (java.util.List) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L61
        L47:
            kotlin.ResultKt.throwOnFailure(r9)
            dagger.Lazy r9 = r7.mediaPlayerDataHelper
            java.lang.Object r9 = r9.get()
            com.oplus.systemui.notification.lockscreen.notification.CapsuleMediaPlayerDataHelper r9 = (com.oplus.systemui.notification.lockscreen.notification.CapsuleMediaPlayerDataHelper) r9
            if (r9 == 0) goto L66
            android.content.Context r2 = r7.context
            r0.L$0 = r8
            r0.label = r5
            java.lang.Object r9 = r9.getMediaNotificationIcon(r2, r0)
            if (r9 != r1) goto L61
            goto L90
        L61:
            com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController$NotificationIconData r9 = (com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController.NotificationIconData) r9
            if (r9 == 0) goto L66
            return r9
        L66:
            java.util.Iterator r9 = r8.iterator()
            r2 = r8
            r8 = r9
        L6c:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L96
            java.lang.Object r9 = r8.next()
            com.android.systemui.statusbar.notification.collection.PipelineEntry r9 = (com.android.systemui.statusbar.notification.collection.PipelineEntry) r9
            android.content.Context r5 = r7.context
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r0.L$0 = r6
            r0.L$1 = r8
            java.lang.Object r6 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r0.L$2 = r6
            r0.label = r4
            java.lang.Object r9 = r7.getIconData(r9, r5, r0)
            if (r9 != r1) goto L91
        L90:
            return r1
        L91:
            com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController$NotificationIconData r9 = (com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController.NotificationIconData) r9
            if (r9 == 0) goto L6c
            return r9
        L96:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController.buildIconData(java.util.List, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final String buildMetricCapsuleContent(Context context, Notification notification) {
        Object obj;
        if (notification != null) {
            try {
                Notification.MetricStyle style = Notification.Builder.recoverBuilder(context, notification).getStyle();
                obj = Result.constructor-impl(style instanceof Notification.MetricStyle ? style : null);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            if (obj instanceof Result.Failure) {
                obj = null;
            }
            Notification.MetricStyle metricStyle = (Notification.MetricStyle) obj;
            if (metricStyle != null) {
                Sequence sequenceExtractMetrics = MetricKt.extractMetrics(metricStyle, context);
                CapsuleNotificationDataController$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new CapsuleNotificationDataController$.ExternalSyntheticLambda2();
                externalSyntheticLambda2.f$0 = this;
                VarHandle.storeStoreFence();
                String strJoinToString$default = SequencesKt___SequencesKt.joinToString$default(SequencesKt___SequencesKt.filter(SequencesKt___SequencesKt.mapNotNull(sequenceExtractMetrics, externalSyntheticLambda2), new CapsuleNotificationDataController$.ExternalSyntheticLambda3()), METRIC_CONTENT_SEPARATOR, (CharSequence) null, 62);
                if (StringsKt__StringsKt.isBlank(strJoinToString$default)) {
                    return null;
                }
                return strJoinToString$default;
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:44:0x0126  */
    /* JADX WARN: Code duplicated, block: B:47:0x0131  */
    /* JADX WARN: Code duplicated, block: B:58:0x0158  */
    /* JADX WARN: Code duplicated, block: B:60:0x0160 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:66:0x016e  */
    /* JADX WARN: Code duplicated, block: B:72:0x0182  */
    /* JADX WARN: Code duplicated, block: B:74:0x019f  */
    /* JADX WARN: Code duplicated, block: B:76:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:79:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:80:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:8:0x001a  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r28v0, types: [com.oplus.systemui.notification.lockscreen.notification.CapsuleNotificationDataController] */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v9 */
    public final Object buildSingleNotificationData(Context context, NotificationEntry notificationEntry, NotificationIconData notificationIconData, List list, ContinuationImpl continuationImpl) {
        C01881 c01881;
        String packageName;
        Class notificationStyle;
        NotificationEntry notificationEntry2;
        boolean zNeedEncrypted;
        boolean zIsOplusMultiAppUser;
        String string;
        Ref.ObjectRef objectRef;
        String string2;
        List list2;
        NotificationIconData notificationIconData2;
        Notification notification;
        NotificationEntry notificationEntry3;
        String str;
        int i;
        Class cls;
        CharSequence charSequence;
        CharSequence charSequence2;
        ?? r2;
        String strBuildMetricCapsuleContent;
        String string3;
        Drawable drawableTryLoadMessagingAvatar;
        CardType cardType;
        Integer iconColor;
        Context context2 = context;
        if (continuationImpl instanceof C01881) {
            c01881 = (C01881) continuationImpl;
            int i2 = c01881.label;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c01881.label = i2 - Integer.MIN_VALUE;
            } else {
                c01881 = new C01881(continuationImpl);
            }
        } else {
            c01881 = new C01881(continuationImpl);
        }
        C01881 c01882 = c01881;
        Object obj = c01882.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c01882.label;
        String str2 = "";
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            StatusBarNotification sbn = notificationEntry.getSbn();
            packageName = sbn.getPackageName();
            Notification notification2 = sbn.getNotification();
            notificationStyle = notification2.getNotificationStyle();
            PrivacyHelper privacyHelper = (PrivacyHelper) this.mPrivacyHelper.get();
            if (privacyHelper != null) {
                packageName.getClass();
                notificationEntry2 = notificationEntry;
                zNeedEncrypted = privacyHelper.needEncrypted(packageName, sbn.getUid(), notificationEntry.getKey(), notificationEntry2, false);
            } else {
                notificationEntry2 = notificationEntry;
                zNeedEncrypted = false;
            }
            zIsOplusMultiAppUser = OplusMultiAppUtils.isOplusMultiAppUser(Boxing.boxInt(sbn.getUserId()));
            Bundle bundle = notification2.extras;
            if (bundle == null || (charSequence2 = bundle.getCharSequence("android.title")) == null || (string = charSequence2.toString()) == null) {
                string = "";
            }
            objectRef = new Ref.ObjectRef();
            Bundle bundle2 = notification2.extras;
            if (bundle2 == null || (charSequence = bundle2.getCharSequence("android.text")) == null || (string2 = charSequence.toString()) == null) {
                string2 = "";
            }
            objectRef.element = string2;
            if (notificationIconData == null) {
                c01882.L$0 = context2;
                c01882.L$1 = notificationEntry2;
                c01882.L$2 = SpillingKt.nullOutSpilledVariable(notificationIconData);
                list2 = list;
                c01882.L$3 = list2;
                c01882.L$4 = SpillingKt.nullOutSpilledVariable(sbn);
                c01882.L$5 = packageName;
                c01882.L$6 = notification2;
                c01882.L$7 = notificationStyle;
                c01882.L$8 = string;
                c01882.L$9 = objectRef;
                c01882.I$0 = zNeedEncrypted ? 1 : 0;
                c01882.Z$0 = zIsOplusMultiAppUser;
                c01882.label = 1;
                Object iconData = getIconData(notificationEntry2, context2, c01882);
                if (iconData == coroutine_suspended) {
                    return coroutine_suspended;
                }
                str = packageName;
                i = zNeedEncrypted ? 1 : 0;
                obj = iconData;
                notification = notification2;
                notificationEntry3 = notificationEntry2;
                cls = notificationStyle;
            } else {
                list2 = list;
                notificationIconData2 = notificationIconData;
                notification = notification2;
                notificationEntry3 = notificationEntry2;
                r2 = zNeedEncrypted;
            }
            List list3 = list2;
            if (Notification.MessagingStyle.class.equals(notificationStyle)) {
                if (r2 != 0 && !zIsOplusMultiAppUser) {
                    drawableTryLoadMessagingAvatar = CapsuleNotificationUtils.tryLoadMessagingAvatar(context2, notificationEntry3);
                }
                if (r2 == 0 || (StringsKt__StringsKt.isBlank(string) && StringsKt__StringsKt.isBlank((CharSequence) objectRef.element))) {
                    string = AppInfoUtil.getAppNameByPkgName(context2, packageName);
                    objectRef.element = context2.getResources().getQuantityString(2131820563, 1, Boxing.boxInt(1));
                }
                String str3 = string;
                if (drawableTryLoadMessagingAvatar != null) {
                    cardType = CardType.SINGLE_MESSAGE;
                } else {
                    cardType = CardType.SINGLE;
                }
                CardType cardType2 = cardType;
                String str4 = (String) objectRef.element;
                if (notificationIconData2 != null) {
                    iconColor = notificationIconData2.getIconColor();
                } else {
                    iconColor = null;
                }
                return new CapsuleNotificationInnerData(notificationIconData2, str3, str4, cardType2, null, drawableTryLoadMessagingAvatar, iconColor, 0, 0, list3, 400);
            }
            if (!Notification.BigTextStyle.class.equals(notificationStyle) && StringsKt__StringsKt.isBlank((CharSequence) objectRef.element)) {
                CharSequence charSequence3 = notification.extras.getCharSequence("android.bigText");
                if (charSequence3 != null && (string3 = charSequence3.toString()) != null) {
                    str2 = string3;
                }
                objectRef.element = str2;
            } else if (Notification.MetricStyle.class.equals(notificationStyle) && r2 == 0 && (strBuildMetricCapsuleContent = buildMetricCapsuleContent(context2, notification)) != null) {
                objectRef.element = strBuildMetricCapsuleContent;
            }
            drawableTryLoadMessagingAvatar = null;
            if (r2 == 0) {
                string = AppInfoUtil.getAppNameByPkgName(context2, packageName);
                objectRef.element = context2.getResources().getQuantityString(2131820563, 1, Boxing.boxInt(1));
            } else {
                string = AppInfoUtil.getAppNameByPkgName(context2, packageName);
                objectRef.element = context2.getResources().getQuantityString(2131820563, 1, Boxing.boxInt(1));
            }
            String str5 = string;
            if (drawableTryLoadMessagingAvatar != null) {
                cardType = CardType.SINGLE_MESSAGE;
            } else {
                cardType = CardType.SINGLE;
            }
            CardType cardType3 = cardType;
            String str6 = (String) objectRef.element;
            if (notificationIconData2 != null) {
                iconColor = notificationIconData2.getIconColor();
            } else {
                iconColor = null;
            }
            return new CapsuleNotificationInnerData(notificationIconData2, str5, str6, cardType3, null, drawableTryLoadMessagingAvatar, iconColor, 0, 0, list3, 400);
        }
        if (i3 != 1) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        boolean z = c01882.Z$0;
        i = c01882.I$0;
        Ref.ObjectRef objectRef2 = (Ref.ObjectRef) c01882.L$9;
        string = (String) c01882.L$8;
        cls = (Class) c01882.L$7;
        Notification notification3 = (Notification) c01882.L$6;
        str = (String) c01882.L$5;
        List list4 = (List) c01882.L$3;
        notificationEntry3 = (NotificationEntry) c01882.L$1;
        Context context3 = (Context) c01882.L$0;
        ResultKt.throwOnFailure(obj);
        zIsOplusMultiAppUser = z;
        context2 = context3;
        notification = notification3;
        objectRef = objectRef2;
        list2 = list4;
        notificationIconData2 = (NotificationIconData) obj;
        r2 = i;
        notificationStyle = cls;
        packageName = str;
        List list5 = list2;
        if (Notification.MessagingStyle.class.equals(notificationStyle)) {
            if (r2 != 0) {
            }
            if (r2 == 0) {
                string = AppInfoUtil.getAppNameByPkgName(context2, packageName);
                objectRef.element = context2.getResources().getQuantityString(2131820563, 1, Boxing.boxInt(1));
            } else {
                string = AppInfoUtil.getAppNameByPkgName(context2, packageName);
                objectRef.element = context2.getResources().getQuantityString(2131820563, 1, Boxing.boxInt(1));
            }
            String str7 = string;
            if (drawableTryLoadMessagingAvatar != null) {
                cardType = CardType.SINGLE_MESSAGE;
            } else {
                cardType = CardType.SINGLE;
            }
            CardType cardType4 = cardType;
            String str8 = (String) objectRef.element;
            if (notificationIconData2 != null) {
                iconColor = notificationIconData2.getIconColor();
            } else {
                iconColor = null;
            }
            return new CapsuleNotificationInnerData(notificationIconData2, str7, str8, cardType4, null, drawableTryLoadMessagingAvatar, iconColor, 0, 0, list5, 400);
        }
        if (!Notification.BigTextStyle.class.equals(notificationStyle)) {
            if (Notification.MetricStyle.class.equals(notificationStyle)) {
                objectRef.element = strBuildMetricCapsuleContent;
            }
        } else if (Notification.MetricStyle.class.equals(notificationStyle)) {
            objectRef.element = strBuildMetricCapsuleContent;
        }
        drawableTryLoadMessagingAvatar = null;
        if (r2 == 0) {
            string = AppInfoUtil.getAppNameByPkgName(context2, packageName);
            objectRef.element = context2.getResources().getQuantityString(2131820563, 1, Boxing.boxInt(1));
        } else {
            string = AppInfoUtil.getAppNameByPkgName(context2, packageName);
            objectRef.element = context2.getResources().getQuantityString(2131820563, 1, Boxing.boxInt(1));
        }
        String str9 = string;
        if (drawableTryLoadMessagingAvatar != null) {
            cardType = CardType.SINGLE_MESSAGE;
        } else {
            cardType = CardType.SINGLE;
        }
        CardType cardType5 = cardType;
        String str10 = (String) objectRef.element;
        if (notificationIconData2 != null) {
            iconColor = notificationIconData2.getIconColor();
        } else {
            iconColor = null;
        }
        return new CapsuleNotificationInnerData(notificationIconData2, str9, str10, cardType5, null, drawableTryLoadMessagingAvatar, iconColor, 0, 0, list5, 400);
    }

    public final int countDistinctSources(int i, List list) {
        StatusBarNotification sbn;
        String packageName;
        OplusNotificationEntryEx ex;
        CapsuleMediaPlayerDataHelper capsuleMediaPlayerDataHelper;
        List<String> mediaSourceKeys;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (i > 0 && (capsuleMediaPlayerDataHelper = (CapsuleMediaPlayerDataHelper) this.mediaPlayerDataHelper.get()) != null && (mediaSourceKeys = capsuleMediaPlayerDataHelper.getMediaSourceKeys()) != null) {
            Iterator<T> it = mediaSourceKeys.iterator();
            while (it.hasNext()) {
                linkedHashSet.add((String) it.next());
            }
        }
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            GroupEntry groupEntry = (PipelineEntry) it2.next();
            boolean z = true;
            String strM = null;
            if (groupEntry instanceof GroupEntry) {
                GroupEntry groupEntry2 = groupEntry;
                NotificationEntry summary = groupEntry2.getSummary();
                if (summary != null && (sbn = summary.getSbn()) != null && (packageName = sbn.getPackageName()) != null) {
                    NotificationEntry summary2 = groupEntry2.getSummary();
                    if (!((summary2 == null || (ex = summary2.getEx()) == null) ? false : ((OplusNotificationEntryExImpl) ex).useAppIconForSmallIcon()) && AppInfoUtil.isSystemApk(this.context, packageName)) {
                        z = false;
                    }
                    boolean zIsMCSPkg = MCSUtil.isMCSPkg(sbn.getPackageName(), sbn.getOpPkg());
                    if (z || zIsMCSPkg || "com.oplus.olc".equals(packageName)) {
                        strM = PerDisplayInstanceRepositoryImpl$.ExternalSyntheticOutline0.m(sbn.getUserId(), packageName, "-");
                    } else {
                        strM = packageName + sbn.getUid() + sbn.getId();
                    }
                }
            } else if (groupEntry instanceof NotificationEntry) {
                NotificationEntry notificationEntry = (NotificationEntry) groupEntry;
                String packageName2 = notificationEntry.getSbn().getPackageName();
                OplusNotificationEntryEx ex2 = notificationEntry.getEx();
                if (!(ex2 != null ? ((OplusNotificationEntryExImpl) ex2).useAppIconForSmallIcon() : false) && AppInfoUtil.isSystemApk(this.context, packageName2)) {
                    z = false;
                }
                boolean zIsMCSPkg2 = MCSUtil.isMCSPkg(notificationEntry.getSbn());
                if (z || zIsMCSPkg2 || "com.oplus.olc".equals(packageName2)) {
                    strM = PerDisplayInstanceRepositoryImpl$.ExternalSyntheticOutline0.m(notificationEntry.getSbn().getUserId(), packageName2, "-");
                } else {
                    strM = packageName2 + notificationEntry.getSbn().getUid() + notificationEntry.getSbn().getId();
                }
            }
            if (strM != null) {
                linkedHashSet.add(strM);
            }
        }
        return linkedHashSet.size();
    }

    public final CapsuleNotificationContainerView createNewContainer() {
        Context contextUpdateDisplayConfiguration$default = KeyguardUtils.Companion.updateDisplayConfiguration$default(KeyguardUtils.Companion, this.context, true, 4);
        contextUpdateDisplayConfiguration$default.getClass();
        return new CapsuleNotificationContainerView(contextUpdateDisplayConfiguration$default, this);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("=== Capsule Refresh state ===");
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "isCapsuleState = ", isCapsuleState());
        CapsuleMediaPlayerDataHelper capsuleMediaPlayerDataHelper = (CapsuleMediaPlayerDataHelper) this.mediaPlayerDataHelper.get();
        printWriter.println("isShowMediaNotification()= " + (capsuleMediaPlayerDataHelper != null ? Boolean.valueOf(capsuleMediaPlayerDataHelper.isShowMediaNotification()) : null));
        printWriter.println("lastEntries.size= " + this.lastEntries.size());
        printWriter.println("=== Capsule data ===");
        CapsuleNotificationInnerData capsuleNotificationInnerData = this.lastAggregateData;
        if (capsuleNotificationInnerData == null) {
            printWriter.println("lastAggregateData = null");
            return;
        }
        printWriter.println("lastAggregateData.cardType = " + capsuleNotificationInnerData.getCardType());
        printWriter.println("lastAggregateData.notifCount = " + capsuleNotificationInnerData.getNotifCount());
        printWriter.println("lastAggregateData.mediaCount = " + capsuleNotificationInnerData.getMediaCount());
        printWriter.println("lastAggregateData.badgeCount = " + capsuleNotificationInnerData.getBadgeCount());
        NotificationIconData iconData = capsuleNotificationInnerData.getIconData();
        printWriter.println("lastAggregateData.iconKey = " + (iconData != null ? iconData.getKey() : null));
        NotificationIconData iconData2 = capsuleNotificationInnerData.getIconData();
        printWriter.println("lastAggregateData.iconPkg = " + (iconData2 != null ? iconData2.getPackageName() : null));
        NotificationIconData iconData3 = capsuleNotificationInnerData.getIconData();
        printWriter.println("lastAggregateData.isCloned = " + (iconData3 != null ? Boolean.valueOf(iconData3.isCloned) : null));
        NotificationIconData iconData4 = capsuleNotificationInnerData.getIconData();
        printWriter.println("lastAggregateData.isMediaPlayer = " + (iconData4 != null ? Boolean.valueOf(iconData4.isMediaPlayer) : null));
    }

    public final GroupIconManager getGroupIconManager() {
        return (GroupIconManager) this.iconManager.get();
    }

    /* JADX WARN: Code duplicated, block: B:122:0x0318  */
    /* JADX WARN: Code duplicated, block: B:143:0x03d1  */
    /* JADX WARN: Code duplicated, block: B:184:0x047d  */
    /* JADX WARN: Code duplicated, block: B:185:0x0486  */
    /* JADX WARN: Code duplicated, block: B:187:0x048e  */
    /* JADX WARN: Code duplicated, block: B:191:0x049a  */
    /* JADX WARN: Code duplicated, block: B:194:0x04ae  */
    /* JADX WARN: Code duplicated, block: B:197:0x0514  */
    /* JADX WARN: Code duplicated, block: B:200:0x0523  */
    /* JADX WARN: Code duplicated, block: B:203:0x0532  */
    /* JADX WARN: Code duplicated, block: B:205:0x053c  */
    /* JADX WARN: Code duplicated, block: B:206:0x053f  */
    /* JADX WARN: Code duplicated, block: B:208:0x0542  */
    /* JADX WARN: Code duplicated, block: B:209:0x0544  */
    /* JADX WARN: Code duplicated, block: B:212:0x0548  */
    /* JADX WARN: Code duplicated, block: B:219:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:8:0x001c  */
    /* JADX WARN: Instruction removed from duplicated block: B:122:0x0318, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2, types: [int] */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r21v2 */
    /* JADX WARN: Type inference failed for: r4v14, types: [int] */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v51 */
    /* JADX WARN: Type inference failed for: r4v53 */
    /* JADX WARN: Type inference failed for: r4v64 */
    /* JADX WARN: Type inference failed for: r4v65 */
    /* JADX WARN: Type inference failed for: r4v66 */
    /* JADX WARN: Type inference failed for: r4v67 */
    /* JADX WARN: Type inference failed for: r4v70 */
    public final Object getIconData(PipelineEntry pipelineEntry, Context context, ContinuationImpl continuationImpl) {
        C01891 c01891;
        Drawable drawable;
        Ref.ObjectRef objectRefM;
        Ref.ObjectRef objectRef;
        Ref.IntRef intRef;
        Ref.BooleanRef booleanRef;
        Ref.ObjectRef objectRef2;
        String str;
        boolean zIsMCSPkg;
        NotificationEntry notificationEntry;
        String str2;
        boolean z;
        Integer num;
        boolean zIsOplusMultiAppUser;
        String str3;
        Integer num2;
        Drawable badgedAppIcon;
        Object obj;
        Drawable drawableMutate;
        boolean z2;
        boolean z3;
        Ref.IntRef intRef2;
        Ref.ObjectRef objectRef3;
        Drawable drawable2;
        NotificationEntry notificationEntry2;
        Ref.BooleanRef booleanRef2;
        PipelineEntry pipelineEntry2;
        Ref.ObjectRef objectRef4;
        ?? r10;
        String str4;
        Ref.ObjectRef objectRef5;
        NotificationEntry notificationEntry3;
        Drawable drawable3;
        String str5;
        PipelineEntry pipelineEntry3;
        Object packageIcon;
        Object obj2;
        String str6;
        boolean zIsMCSPkg2;
        StatusBarNotification sbn;
        StatusBarNotification sbn2;
        StatusBarNotification sbn3;
        StatusBarNotification sbn4;
        Notification notification;
        ExpandableNotificationRow row;
        OplusNotificationEntryEx ex;
        StatusBarNotification sbn5;
        Notification notification2;
        StatusBarNotification sbn6;
        StatusBarNotification sbn7;
        String str7;
        boolean z4;
        boolean z5;
        String str8;
        Icon icon;
        String string;
        String strValueOf;
        Integer num3;
        String str9;
        Ref.ObjectRef objectRef6;
        Drawable drawable4;
        Object objWithContext;
        Object obj3;
        Drawable drawable5;
        NotificationEntry notificationEntry4;
        Ref.ObjectRef objectRef7;
        Ref.ObjectRef objectRef8;
        Icon icon2;
        Drawable drawableLoadDrawable;
        Object obj4;
        boolean zBooleanValue;
        ?? r4;
        ?? r5;
        Ref.ObjectRef objectRef9;
        NotificationEntry notificationEntry5;
        boolean z6;
        Drawable drawable6;
        Context context2 = context;
        if (continuationImpl instanceof C01891) {
            c01891 = (C01891) continuationImpl;
            int i = c01891.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01891.label = i - Integer.MIN_VALUE;
            } else {
                c01891 = new C01891(continuationImpl);
            }
        } else {
            c01891 = new C01891(continuationImpl);
        }
        C01891 c01892 = c01891;
        Object obj5 = c01892.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01892.label;
        String str10 = TAG;
        if (i2 != 0) {
            if (i2 == 1) {
                boolean z7 = c01892.Z$1;
                boolean z8 = c01892.Z$0;
                int i3 = c01892.I$0;
                Drawable drawable7 = (Drawable) c01892.L$10;
                NotificationEntry notificationEntry6 = (NotificationEntry) c01892.L$9;
                Integer num4 = (Integer) c01892.L$8;
                Ref.ObjectRef objectRef10 = (Ref.ObjectRef) c01892.L$7;
                drawable = null;
                booleanRef = (Ref.BooleanRef) c01892.L$6;
                intRef = (Ref.IntRef) c01892.L$5;
                objectRef = (Ref.ObjectRef) c01892.L$4;
                objectRefM = (Ref.ObjectRef) c01892.L$3;
                String str11 = (String) c01892.L$2;
                Context context3 = (Context) c01892.L$1;
                pipelineEntry3 = (PipelineEntry) c01892.L$0;
                ResultKt.throwOnFailure(obj5);
                packageIcon = obj5;
                objectRef5 = objectRef10;
                str5 = str11;
                notificationEntry3 = notificationEntry6;
                zIsOplusMultiAppUser = z8;
                context2 = context3;
                num2 = num4;
                obj = coroutine_suspended;
                drawable3 = drawable7;
                zIsMCSPkg = z7;
                r5 = i3;
            } else {
                if (i2 != 2) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                objectRef8 = (Ref.ObjectRef) c01892.L$14;
                Drawable drawable8 = (Drawable) c01892.L$11;
                notificationEntry4 = (NotificationEntry) c01892.L$10;
                drawable5 = (Drawable) c01892.L$8;
                objectRef6 = (Ref.ObjectRef) c01892.L$7;
                objectRef7 = (Ref.ObjectRef) c01892.L$4;
                Ref.ObjectRef objectRef11 = (Ref.ObjectRef) c01892.L$3;
                String str12 = (String) c01892.L$2;
                ResultKt.throwOnFailure(obj5);
                z5 = true;
                drawable = null;
                str9 = str12;
                objectRef3 = objectRef11;
                drawable2 = drawable8;
                z4 = false;
            }
            objectRef2 = objectRef8;
            num3 = (Integer) obj5;
            notificationEntry2 = notificationEntry4;
            drawableMutate = drawable5;
            objectRef4 = objectRef7;
            String str13 = str9;
            objectRef2.element = num3;
            objectRef2 = objectRef6;
            str8 = str13;
            objectRef9 = objectRef4;
            notificationEntry5 = notificationEntry2;
            if (drawable2 != null) {
                z6 = z5;
            } else {
                z6 = z4;
            }
            if (drawable2 == null) {
                drawable6 = drawableMutate;
            } else {
                drawable6 = drawable2;
            }
            if (drawable6 != null) {
                return new NotificationIconData(str8, (String) objectRef9.element, drawable6, (Icon) objectRef3.element, z6, (Integer) objectRef2.element, notificationEntry5, 128);
            }
            return drawable;
        }
        drawable = null;
        objectRefM = DefaultDraggableAnchors$.ExternalSyntheticOutline0.m(obj5);
        objectRef = new Ref.ObjectRef();
        intRef = new Ref.IntRef();
        booleanRef = new Ref.BooleanRef();
        objectRef2 = new Ref.ObjectRef();
        if (!(pipelineEntry instanceof GroupEntry)) {
            coroutine_suspended = coroutine_suspended;
            if (pipelineEntry instanceof NotificationEntry) {
                NotificationEntry notificationEntry7 = (NotificationEntry) pipelineEntry;
                objectRef.element = notificationEntry7.getSbn().getPackageName();
                StatusBarNotification sbn8 = notificationEntry7.getSbn();
                if (sbn8 != null) {
                    str = sbn8.getPackageName() + sbn8.getUid() + sbn8.getId();
                } else {
                    str = null;
                }
                String strValueOf2 = String.valueOf(str);
                objectRefM.element = notificationEntry7.getSbn().getNotification().getSmallIcon();
                OplusNotificationEntryEx ex2 = notificationEntry7.getEx();
                boolean zUseAppIconForSmallIcon = ex2 != null ? ((OplusNotificationEntryExImpl) ex2).useAppIconForSmallIcon() : false;
                ExpandableNotificationRow row2 = notificationEntry7.getRow();
                intRef.element = row2 != null ? row2.getOriginalIconColor() : 0;
                Notification notification3 = notificationEntry7.getSbn().getNotification();
                booleanRef.element = notification3 != null ? notification3.isColorized() : false;
                zIsMCSPkg = MCSUtil.isMCSPkg(notificationEntry7.getSbn());
                Integer numBoxInt = Boxing.boxInt(notificationEntry7.getSbn().getUserId());
                boolean zIsOplusMultiAppUser2 = OplusMultiAppUtils.isOplusMultiAppUser(Boxing.boxInt(notificationEntry7.getSbn().getUserId()));
                boolean z9 = zUseAppIconForSmallIcon;
                notificationEntry = notificationEntry7;
                str2 = strValueOf2;
                z = z9;
                num = numBoxInt;
                zIsOplusMultiAppUser = zIsOplusMultiAppUser2;
            }
            return drawable;
        }
        GroupEntry groupEntry = (GroupEntry) pipelineEntry;
        if (groupEntry.getSummary() == null) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("GroupEntry summary is null, entryKey=", pipelineEntry.getKey(), ", children=", groupEntry.getChildren().size()));
            return null;
        }
        NotificationEntry summary = groupEntry.getSummary();
        objectRef.element = (summary == null || (sbn7 = summary.getSbn()) == null) ? null : sbn7.getPackageName();
        NotificationEntry summary2 = groupEntry.getSummary();
        if (summary2 == null || (sbn6 = summary2.getSbn()) == null) {
            str6 = null;
        } else {
            str6 = sbn6.getPackageName() + sbn6.getUid() + sbn6.getId();
        }
        String strValueOf3 = String.valueOf(str6);
        NotificationEntry summary3 = groupEntry.getSummary();
        objectRefM.element = (summary3 == null || (sbn5 = summary3.getSbn()) == null || (notification2 = sbn5.getNotification()) == null) ? null : notification2.getSmallIcon();
        NotificationEntry summary4 = groupEntry.getSummary();
        boolean zUseAppIconForSmallIcon2 = (summary4 == null || (ex = summary4.getEx()) == null) ? false : ((OplusNotificationEntryExImpl) ex).useAppIconForSmallIcon();
        NotificationEntry summary5 = groupEntry.getSummary();
        intRef.element = (summary5 == null || (row = summary5.getRow()) == null) ? 0 : row.getOriginalIconColor();
        NotificationEntry summary6 = groupEntry.getSummary();
        booleanRef.element = (summary6 == null || (sbn4 = summary6.getSbn()) == null || (notification = sbn4.getNotification()) == null) ? false : notification.isColorized();
        NotificationEntry summary7 = groupEntry.getSummary();
        if (summary7 == null || (sbn3 = summary7.getSbn()) == null) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("GroupEntry summary or sbn is null, entryKey=", pipelineEntry.getKey(), ", children=", groupEntry.getChildren().size()));
            zIsMCSPkg2 = false;
        } else {
            zIsMCSPkg2 = MCSUtil.isMCSPkg(sbn3.getPackageName(), sbn3.getOpPkg());
        }
        NotificationEntry summary8 = groupEntry.getSummary();
        NotificationEntry representativeEntry = summary8 != null ? summary8.getRepresentativeEntry() : null;
        NotificationEntry summary9 = groupEntry.getSummary();
        Integer numBoxInt2 = (summary9 == null || (sbn2 = summary9.getSbn()) == null) ? null : Boxing.boxInt(sbn2.getUserId());
        NotificationEntry summary10 = groupEntry.getSummary();
        zIsOplusMultiAppUser = OplusMultiAppUtils.isOplusMultiAppUser((summary10 == null || (sbn = summary10.getSbn()) == null) ? null : Boxing.boxInt(sbn.getUserId()));
        num = numBoxInt2;
        zIsMCSPkg = zIsMCSPkg2;
        notificationEntry = representativeEntry;
        str2 = strValueOf3;
        z = zUseAppIconForSmallIcon2;
        Object obj6 = objectRef.element;
        ?? r6 = z;
        if (obj6 != null) {
            r6 = z || PACKAGES_USE_APP_ICON.contains(obj6) || !AppInfoUtil.isSystemApk(context2, (String) objectRef.element);
        }
        if (r6 != 0 || zIsMCSPkg) {
            str3 = objectRef.element + "-" + num;
        } else {
            String str14 = str2;
            if ("com.oplus.olc".equals(objectRef.element)) {
                str3 = objectRef.element + "-" + num;
            } else {
                str3 = str14;
            }
        }
        if (!zIsOplusMultiAppUser || (obj2 = objectRef.element) == null || num == null) {
            num2 = num;
            badgedAppIcon = null;
        } else {
            num2 = num;
            badgedAppIcon = CapsuleNotificationUtils.getBadgedAppIcon(context2, (String) obj2, num2.intValue());
        }
        if (r6 == 0 || objectRef.element == null) {
            str10 = TAG;
            obj = coroutine_suspended;
            drawableMutate = null;
            z2 = zIsMCSPkg;
            z3 = zIsOplusMultiAppUser;
            intRef2 = intRef;
            objectRef3 = objectRefM;
            drawable2 = badgedAppIcon;
            notificationEntry2 = notificationEntry;
            booleanRef2 = booleanRef;
            pipelineEntry2 = pipelineEntry;
            objectRef4 = objectRef;
            r10 = r6;
            str4 = str3;
        } else {
            NotificationResolverEx notificationResolverEx = (NotificationResolverEx) this.notificationResolverEx.get();
            if (notificationResolverEx != null) {
                String str15 = (String) objectRef.element;
                c01892.L$0 = SpillingKt.nullOutSpilledVariable(pipelineEntry);
                c01892.L$1 = context2;
                c01892.L$2 = str3;
                c01892.L$3 = objectRefM;
                c01892.L$4 = objectRef;
                c01892.L$5 = intRef;
                c01892.L$6 = booleanRef;
                c01892.L$7 = objectRef2;
                c01892.L$8 = SpillingKt.nullOutSpilledVariable(num2);
                c01892.L$9 = notificationEntry;
                c01892.L$10 = badgedAppIcon;
                c01892.I$0 = r6;
                c01892.Z$0 = zIsOplusMultiAppUser;
                c01892.Z$1 = zIsMCSPkg;
                c01892.label = 1;
                packageIcon = notificationResolverEx.getPackageIcon(str15, c01892);
                obj = coroutine_suspended;
                if (packageIcon == obj) {
                    return obj;
                }
                objectRef5 = objectRef2;
                notificationEntry3 = notificationEntry;
                drawable3 = badgedAppIcon;
                str5 = str3;
                pipelineEntry3 = pipelineEntry;
                r5 = r6;
            } else {
                str10 = TAG;
                obj = coroutine_suspended;
                drawableMutate = null;
                objectRef5 = objectRef2;
                notificationEntry3 = notificationEntry;
                drawable3 = badgedAppIcon;
                str5 = str3;
                pipelineEntry3 = pipelineEntry;
                r4 = r6;
            }
            ?? r21 = r4;
            str4 = str5;
            booleanRef2 = booleanRef;
            objectRef4 = objectRef;
            r10 = r21 == true ? 1 : 0;
            z2 = zIsMCSPkg;
            z3 = zIsOplusMultiAppUser;
            intRef2 = intRef;
            objectRef3 = objectRefM;
            notificationEntry2 = notificationEntry3;
            objectRef2 = objectRef5;
            drawable2 = drawable3;
            pipelineEntry2 = pipelineEntry3;
        }
        if (drawableMutate == null) {
            icon2 = (Icon) objectRef3.element;
            if (icon2 != null || (drawableLoadDrawable = icon2.loadDrawable(context2)) == null || (drawableMutate = drawableLoadDrawable.mutate()) == null) {
                str7 = str4;
                pipelineEntry2 = pipelineEntry2;
                obj = obj;
                drawableMutate = drawable;
            } else {
                Icon icon3 = (Icon) objectRef3.element;
                if (icon3 == null) {
                    str7 = str4;
                    obj = obj;
                    zBooleanValue = false;
                } else {
                    try {
                        str7 = str4;
                        try {
                            obj4 = Result.constructor-impl(Boolean.valueOf(PACKAGES_SELF_PLATED_SMALL_ICON.contains(icon3.getResPackage())));
                        } catch (Throwable th) {
                            th = th;
                            obj4 = Result.constructor-impl(ResultKt.createFailure(th));
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        str7 = str4;
                    }
                    Throwable th3 = Result.exceptionOrNull-impl(obj4);
                    if (th3 != null) {
                        LockScreenNotificationKotlinExKt.logCapsule(str10, "isSelfPlatedIcon failed: " + icon3, th3);
                    }
                    Boolean bool = Boolean.FALSE;
                    if (obj4 instanceof Result.Failure) {
                        obj4 = bool;
                    }
                    zBooleanValue = ((Boolean) obj4).booleanValue();
                }
                if (!zBooleanValue && getColorUtil().isGrayscaleIcon(drawableMutate)) {
                    OplusContrastColorUtil oplusContrastColorUtil = OplusContrastColorUtil.getInstance(context2);
                    int color = (booleanRef2.element || oplusContrastColorUtil.isVectorDrawable(drawableMutate)) ? intRef2.element : context2.getColor(2131102881);
                    if (color != 0) {
                        if (oplusContrastColorUtil.isVectorDrawable(drawableMutate)) {
                            drawableMutate.setTint(color);
                        } else {
                            drawableMutate.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                        }
                    }
                    drawableMutate = CapsuleNotificationUtils.wrapWithWhiteBg(drawableMutate, context2, 4.0f);
                }
            }
        } else {
            str7 = str4;
            pipelineEntry2 = pipelineEntry2;
            obj = obj;
        }
        if (drawableMutate != null) {
            icon = (Icon) objectRef3.element;
            if (icon != null || (string = icon.toString()) == null) {
                string = (String) objectRef4.element;
            }
            strValueOf = String.valueOf(string);
            num3 = this.iconInfoCache.get(strValueOf);
            if (num3 == null) {
                CoroutineDispatcher io = Dispatchers.getIO();
                z5 = true;
                str9 = str7;
                Ref.BooleanRef booleanRef3 = booleanRef2;
                drawable4 = drawableMutate;
                CapsuleNotificationDataController$getIconData$7$1 capsuleNotificationDataController$getIconData$7$1 = new CapsuleNotificationDataController$getIconData$7$1(context2, drawable4, this, strValueOf, null);
                c01892.L$0 = SpillingKt.nullOutSpilledVariable(pipelineEntry2);
                c01892.L$1 = SpillingKt.nullOutSpilledVariable(context2);
                c01892.L$2 = str9;
                c01892.L$3 = objectRef3;
                c01892.L$4 = objectRef4;
                c01892.L$5 = SpillingKt.nullOutSpilledVariable(intRef2);
                c01892.L$6 = SpillingKt.nullOutSpilledVariable(booleanRef3);
                c01892.L$7 = objectRef2;
                c01892.L$8 = drawable4;
                c01892.L$9 = SpillingKt.nullOutSpilledVariable(num2);
                c01892.L$10 = notificationEntry2;
                c01892.L$11 = drawable2;
                c01892.L$12 = SpillingKt.nullOutSpilledVariable(drawable4);
                c01892.L$13 = SpillingKt.nullOutSpilledVariable(strValueOf);
                c01892.L$14 = objectRef2;
                c01892.I$0 = r10;
                c01892.Z$0 = z3;
                c01892.Z$1 = z2;
                z4 = false;
                c01892.I$1 = 0;
                c01892.label = 2;
                objWithContext = BuildersKt.withContext(io, capsuleNotificationDataController$getIconData$7$1, c01892);
                obj3 = obj;
                if (objWithContext == obj3) {
                    return obj3;
                }
                drawable5 = drawable4;
                notificationEntry4 = notificationEntry2;
                objectRef7 = objectRef4;
                objectRef6 = objectRef2;
                obj5 = objWithContext;
                objectRef8 = objectRef6;
                objectRef2 = objectRef8;
                num3 = (Integer) obj5;
                notificationEntry2 = notificationEntry4;
                drawableMutate = drawable5;
                objectRef4 = objectRef7;
            } else {
                str9 = str7;
                z4 = false;
                z5 = true;
                objectRef6 = objectRef2;
            }
            String str16 = str9;
            objectRef2.element = num3;
            objectRef2 = objectRef6;
            str8 = str16;
        } else {
            z4 = false;
            z5 = true;
            str8 = str7;
        }
        objectRef9 = objectRef4;
        notificationEntry5 = notificationEntry2;
        if (drawable2 != null) {
            z6 = z5;
        } else {
            z6 = z4;
        }
        if (drawable2 == null) {
            drawable6 = drawableMutate;
        } else {
            drawable6 = drawable2;
        }
        if (drawable6 != null) {
            return new NotificationIconData(str8, (String) objectRef9.element, drawable6, (Icon) objectRef3.element, z6, (Integer) objectRef2.element, notificationEntry5, 128);
        }
        return drawable;
        drawableMutate = (Drawable) packageIcon;
        r4 = r5;
        ?? r22 = r4;
        str4 = str5;
        booleanRef2 = booleanRef;
        objectRef4 = objectRef;
        r10 = r22 == true ? 1 : 0;
        z2 = zIsMCSPkg;
        z3 = zIsOplusMultiAppUser;
        intRef2 = intRef;
        objectRef3 = objectRefM;
        notificationEntry2 = notificationEntry3;
        objectRef2 = objectRef5;
        drawable2 = drawable3;
        pipelineEntry2 = pipelineEntry3;
        if (drawableMutate == null) {
            icon2 = (Icon) objectRef3.element;
            if (icon2 != null) {
                str7 = str4;
                pipelineEntry2 = pipelineEntry2;
                obj = obj;
                drawableMutate = drawable;
            } else {
                str7 = str4;
                pipelineEntry2 = pipelineEntry2;
                obj = obj;
                drawableMutate = drawable;
            }
        } else {
            str7 = str4;
            pipelineEntry2 = pipelineEntry2;
            obj = obj;
        }
        if (drawableMutate != null) {
            icon = (Icon) objectRef3.element;
            if (icon != null) {
                string = (String) objectRef4.element;
            } else {
                string = (String) objectRef4.element;
            }
            strValueOf = String.valueOf(string);
            num3 = this.iconInfoCache.get(strValueOf);
            if (num3 == null) {
                CoroutineDispatcher io2 = Dispatchers.getIO();
                z5 = true;
                str9 = str7;
                Ref.BooleanRef booleanRef4 = booleanRef2;
                drawable4 = drawableMutate;
                CapsuleNotificationDataController$getIconData$7$1 capsuleNotificationDataController$getIconData$7$2 = new CapsuleNotificationDataController$getIconData$7$1(context2, drawable4, this, strValueOf, null);
                c01892.L$0 = SpillingKt.nullOutSpilledVariable(pipelineEntry2);
                c01892.L$1 = SpillingKt.nullOutSpilledVariable(context2);
                c01892.L$2 = str9;
                c01892.L$3 = objectRef3;
                c01892.L$4 = objectRef4;
                c01892.L$5 = SpillingKt.nullOutSpilledVariable(intRef2);
                c01892.L$6 = SpillingKt.nullOutSpilledVariable(booleanRef4);
                c01892.L$7 = objectRef2;
                c01892.L$8 = drawable4;
                c01892.L$9 = SpillingKt.nullOutSpilledVariable(num2);
                c01892.L$10 = notificationEntry2;
                c01892.L$11 = drawable2;
                c01892.L$12 = SpillingKt.nullOutSpilledVariable(drawable4);
                c01892.L$13 = SpillingKt.nullOutSpilledVariable(strValueOf);
                c01892.L$14 = objectRef2;
                c01892.I$0 = r10;
                c01892.Z$0 = z3;
                c01892.Z$1 = z2;
                z4 = false;
                c01892.I$1 = 0;
                c01892.label = 2;
                objWithContext = BuildersKt.withContext(io2, capsuleNotificationDataController$getIconData$7$2, c01892);
                obj3 = obj;
                if (objWithContext == obj3) {
                    return obj3;
                }
                drawable5 = drawable4;
                notificationEntry4 = notificationEntry2;
                objectRef7 = objectRef4;
                objectRef6 = objectRef2;
                obj5 = objWithContext;
                objectRef8 = objectRef6;
                objectRef2 = objectRef8;
                num3 = (Integer) obj5;
                notificationEntry2 = notificationEntry4;
                drawableMutate = drawable5;
                objectRef4 = objectRef7;
            } else {
                str9 = str7;
                z4 = false;
                z5 = true;
                objectRef6 = objectRef2;
            }
            String str17 = str9;
            objectRef2.element = num3;
            objectRef2 = objectRef6;
            str8 = str17;
        } else {
            z4 = false;
            z5 = true;
            str8 = str7;
        }
        objectRef9 = objectRef4;
        notificationEntry5 = notificationEntry2;
        if (drawable2 != null) {
            z6 = z5;
        } else {
            z6 = z4;
        }
        if (drawable2 == null) {
            drawable6 = drawableMutate;
        } else {
            drawable6 = drawable2;
        }
        if (drawable6 != null) {
            return new NotificationIconData(str8, (String) objectRef9.element, drawable6, (Icon) objectRef3.element, z6, (Integer) objectRef2.element, notificationEntry5, 128);
        }
        return drawable;
    }

    public final NotificationActivityStarter getNotificationActivityStarter() {
        return (NotificationActivityStarter) this.notificationActivityStarter.get();
    }

    public final int getTextColor() {
        return this.textColor;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0085  */
    public final NotificationEntry getUpdatedNotificationEntry(List list) {
        Object next;
        List listEmptyList;
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            GroupEntry groupEntry = (PipelineEntry) it.next();
            if (groupEntry instanceof NotificationEntry) {
                listEmptyList = CollectionsKt__CollectionsJVMKt.listOf(groupEntry);
            } else if (groupEntry instanceof GroupEntry) {
                NotificationEntry summary = groupEntry.getSummary();
                if (summary == null || (listEmptyList = summary.getAttachedNotifChildren()) == null) {
                    listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                }
            } else {
                listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, listEmptyList);
        }
        Iterator it2 = CollectionsKt___CollectionsKt.filterNotNull(arrayList).iterator();
        if (it2.hasNext()) {
            next = it2.next();
            if (it2.hasNext()) {
                long updateTime = ((NotificationEntry) next).mNotificationEntryEx.getUpdateTime();
                do {
                    Object next2 = it2.next();
                    long updateTime2 = ((NotificationEntry) next2).mNotificationEntryEx.getUpdateTime();
                    if (updateTime < updateTime2) {
                        next = next2;
                        updateTime = updateTime2;
                    }
                } while (it2.hasNext());
            }
        } else {
            next = null;
        }
        NotificationEntry notificationEntry = (NotificationEntry) next;
        if (notificationEntry == null) {
            notificationEntry = null;
        } else {
            long updateTime3 = notificationEntry.mNotificationEntryEx.getUpdateTime();
            Notification notification = notificationEntry.getSbn().getNotification();
            if (notification != null) {
                boolean z = (notification.flags & 2) != 0;
                boolean zEquals = Notification.MessagingStyle.class.equals(notification.getNotificationStyle());
                boolean zContains = PACKAGES_ALLOW_NOTIF_UPDATE.contains(notificationEntry.getSbn().getPackageName());
                if (z || ((!zEquals && !zContains) || updateTime3 <= this.preLatestPostTime)) {
                    notificationEntry = null;
                }
            } else {
                notificationEntry = null;
            }
        }
        if (notificationEntry == null) {
            return null;
        }
        this.preLatestPostTime = notificationEntry.mNotificationEntryEx.getUpdateTime();
        return notificationEntry;
    }

    public final void handleAggregateNotification(CapsuleNotificationInnerData capsuleNotificationInnerData, boolean z, Function1 function1, boolean z2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("show aggregate card , animate:", "CapsuleNotificationDataController-->", "SystemUi--Notification", z);
        }
        this.isNewNotificationPinned = false;
        CapsuleNotificationContainerView orCreateCurrentContainer = getOrCreateCurrentContainer();
        orCreateCurrentContainer.showAggregate(capsuleNotificationInnerData, z);
        String strGenerateKeyForContainer = generateKeyForContainer(orCreateCurrentContainer);
        this.key = strGenerateKeyForContainer;
        function1.invoke(new CapsuleNotificationData(strGenerateKeyForContainer, orCreateCurrentContainer, null, z2, 4));
    }

    public final boolean handleKeyguardSessionBoundary(Function1 function1) {
        if (this.hasExitKeyguard) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleNotificationDataController-->exit keyguard, clear notification capsule hasExitKeyguard = false");
            }
            this.lastAggregateData = null;
            this.isNewNotificationPinned = false;
            setCurrentContainer(null);
            this.lastEntries = CollectionsKt__CollectionsKt.emptyList();
            this.pendingNewNotification = null;
            function1.invoke((Object) null);
            this.hasExitKeyguard = false;
            return true;
        }
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateController) this.keyguardStateController.get();
        if (keyguardStateControllerImpl == null || !keyguardStateControllerImpl.mKeyguardGoingAway) {
            KeyguardStateControllerImpl keyguardStateControllerImpl2 = (KeyguardStateController) this.keyguardStateController.get();
            if (keyguardStateControllerImpl2 != null && keyguardStateControllerImpl2.mShowing) {
                return false;
            }
            LogBuffer logBuffer = DebugTool.INSTANCE.getLogBuffer();
            if (logBuffer != null) {
                logBuffer.log(TAG, LogLevel.DEBUG, "noNeedRefreshCapsuleData: not_keyguard", (Throwable) null);
            }
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleNotificationDataController-->keyguard going away, skip refresh to keep encrypted state");
            return true;
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    public final Object handleNewNotification(NotificationEntry notificationEntry, CapsuleNotificationInnerData capsuleNotificationInnerData, Function1 function1, ContinuationImpl continuationImpl) {
        C01901 c01901;
        CapsuleNotificationDataController capsuleNotificationDataController;
        if (continuationImpl instanceof C01901) {
            c01901 = (C01901) continuationImpl;
            int i = c01901.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01901.label = i - Integer.MIN_VALUE;
            } else {
                c01901 = new C01901(continuationImpl);
            }
        } else {
            c01901 = new C01901(continuationImpl);
        }
        C01901 c01902 = c01901;
        Object objBuildSingleNotificationData = c01902.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01902.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objBuildSingleNotificationData);
            int i3 = (capsuleNotificationInnerData.getCardType() == CardType.AGGREGATE || capsuleNotificationInnerData.getIconData() == null || this.lastAggregateData == null) ? 0 : 1;
            if (i3 != 0) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    OplusLockIconView$.ExternalSyntheticOutline0.m("show single app Notification with new animation style, isGroup=", "CapsuleNotificationDataController-->", "SystemUi--Notification", capsuleNotificationInnerData.getBadgeCount() != null);
                }
                Integer iconColor = capsuleNotificationInnerData.getIconColor();
                CapsuleNotificationContainerView capsuleNotificationContainerViewCreateNewContainer = createNewContainer();
                capsuleNotificationContainerViewCreateNewContainer.setHasNewNotificationForCurrentApp(true);
                capsuleNotificationContainerViewCreateNewContainer.createAndAddNewCard(capsuleNotificationInnerData);
                setCurrentContainer(capsuleNotificationContainerViewCreateNewContainer);
                String strGenerateKeyForContainer = generateKeyForContainer(capsuleNotificationContainerViewCreateNewContainer);
                this.key = strGenerateKeyForContainer;
                function1.invoke(new CapsuleNotificationData(strGenerateKeyForContainer, capsuleNotificationContainerViewCreateNewContainer, iconColor, false, 8));
            } else {
                if (this.isNewNotificationPinned) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", "CapsuleNotificationDataController-->isNewNotificationPinned skip");
                    }
                    return Unit.INSTANCE;
                }
                Context context = this.context;
                c01902.L$0 = SpillingKt.nullOutSpilledVariable(notificationEntry);
                c01902.L$1 = SpillingKt.nullOutSpilledVariable(capsuleNotificationInnerData);
                c01902.L$2 = function1;
                c01902.I$0 = i3;
                c01902.label = 1;
                capsuleNotificationDataController = this;
                objBuildSingleNotificationData = capsuleNotificationDataController.buildSingleNotificationData(context, notificationEntry, null, null, c01902);
                if (objBuildSingleNotificationData == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
        if (i2 != 1) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        function1 = (Function1) c01902.L$2;
        ResultKt.throwOnFailure(objBuildSingleNotificationData);
        capsuleNotificationDataController = this;
        CapsuleNotificationInnerData capsuleNotificationInnerData2 = (CapsuleNotificationInnerData) objBuildSingleNotificationData;
        Integer iconColor2 = capsuleNotificationInnerData2.getIconColor();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "CapsuleNotificationDataController-->new notification, show single card for 3s, isNewNotificationPinned = true");
        }
        CapsuleNotificationContainerView capsuleNotificationContainerViewCreateNewContainer2 = capsuleNotificationDataController.createNewContainer();
        capsuleNotificationContainerViewCreateNewContainer2.showSingle(capsuleNotificationInnerData2, false);
        capsuleNotificationDataController.setCurrentContainer(capsuleNotificationContainerViewCreateNewContainer2);
        capsuleNotificationDataController.isNewNotificationPinned = true;
        CapsuleNotificationDataController capsuleNotificationDataController2 = capsuleNotificationDataController;
        String strGenerateKeyForContainer2 = generateKeyForContainer(capsuleNotificationContainerViewCreateNewContainer2);
        capsuleNotificationDataController2.key = strGenerateKeyForContainer2;
        function1.invoke(new CapsuleNotificationData(strGenerateKeyForContainer2, capsuleNotificationContainerViewCreateNewContainer2, iconColor2, false, 8));
        return Unit.INSTANCE;
    }

    public final boolean isCapsuleState() {
        return !((ILockScreenExtStateController) this.lockScreenExtStateController.get()).isStackState();
    }

    public final boolean noNeedRefreshCapsuleData(CapsuleNotificationInnerData capsuleNotificationInnerData, NotificationEntry notificationEntry) {
        CapsuleNotificationInnerData capsuleNotificationInnerData2 = this.lastAggregateData;
        if (capsuleNotificationInnerData2 != null) {
            if (capsuleNotificationInnerData2.getMediaCount() != capsuleNotificationInnerData.getMediaCount()) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("mediaCount changed ", capsuleNotificationInnerData2.getMediaCount(), capsuleNotificationInnerData.getMediaCount(), " -> ", "CapsuleNotificationDataController-->"));
                    return false;
                }
            } else if (notificationEntry == null && capsuleNotificationInnerData2.hasSameContentAs(capsuleNotificationInnerData)) {
                if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    return true;
                }
                Log.d("SystemUi--Notification", "CapsuleNotificationDataController-->has same content, no need fresh");
                return true;
            }
        }
        return false;
    }

    public final void onConfigChanged() {
        CapsuleNotificationContainerView capsuleNotificationContainerView = this.currentContainer;
        if (capsuleNotificationContainerView != null) {
            capsuleNotificationContainerView.onConfigChanged();
        }
    }

    public void onEntryAdded(NotificationEntry notificationEntry) {
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateController) this.keyguardStateController.get();
        if (keyguardStateControllerImpl == null || !keyguardStateControllerImpl.mShowing) {
            return;
        }
        if (CapsuleNotificationUtils.isAutoSummary(notificationEntry)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onEntryAdded: skip auto group summary notification: ", notificationEntry.getKey(), "CapsuleNotificationDataController-->", "SystemUi--Notification");
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("onEntryAdded: ", notificationEntry.getKey(), "CapsuleNotificationDataController-->", "SystemUi--Notification");
        }
        this.pendingNewNotification = notificationEntry;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x028c  */
    /* JADX WARN: Code duplicated, block: B:105:0x0294  */
    /* JADX WARN: Code duplicated, block: B:106:0x0297  */
    /* JADX WARN: Code duplicated, block: B:113:0x02d9  */
    /* JADX WARN: Code duplicated, block: B:116:0x02f5 A[Catch: all -> 0x02fc, TryCatch #5 {all -> 0x02fc, blocks: (B:114:0x02eb, B:116:0x02f5, B:121:0x0302, B:141:0x034f, B:143:0x0355, B:147:0x035e, B:149:0x0362, B:155:0x036f, B:157:0x0375, B:163:0x03be, B:172:0x0407, B:175:0x040f, B:179:0x0415, B:181:0x041b, B:183:0x0421, B:184:0x046d, B:186:0x0476, B:188:0x047c, B:190:0x0482, B:191:0x04a8, B:126:0x0310, B:128:0x0314, B:130:0x031b, B:132:0x0321, B:136:0x0333, B:139:0x0346, B:140:0x034b), top: B:210:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:120:0x0300  */
    /* JADX WARN: Code duplicated, block: B:123:0x0306  */
    /* JADX WARN: Code duplicated, block: B:125:0x030e  */
    /* JADX WARN: Code duplicated, block: B:126:0x0310 A[Catch: all -> 0x02fc, TryCatch #5 {all -> 0x02fc, blocks: (B:114:0x02eb, B:116:0x02f5, B:121:0x0302, B:141:0x034f, B:143:0x0355, B:147:0x035e, B:149:0x0362, B:155:0x036f, B:157:0x0375, B:163:0x03be, B:172:0x0407, B:175:0x040f, B:179:0x0415, B:181:0x041b, B:183:0x0421, B:184:0x046d, B:186:0x0476, B:188:0x047c, B:190:0x0482, B:191:0x04a8, B:126:0x0310, B:128:0x0314, B:130:0x031b, B:132:0x0321, B:136:0x0333, B:139:0x0346, B:140:0x034b), top: B:210:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:137:0x033e  */
    /* JADX WARN: Code duplicated, block: B:139:0x0346 A[Catch: all -> 0x02fc, TryCatch #5 {all -> 0x02fc, blocks: (B:114:0x02eb, B:116:0x02f5, B:121:0x0302, B:141:0x034f, B:143:0x0355, B:147:0x035e, B:149:0x0362, B:155:0x036f, B:157:0x0375, B:163:0x03be, B:172:0x0407, B:175:0x040f, B:179:0x0415, B:181:0x041b, B:183:0x0421, B:184:0x046d, B:186:0x0476, B:188:0x047c, B:190:0x0482, B:191:0x04a8, B:126:0x0310, B:128:0x0314, B:130:0x031b, B:132:0x0321, B:136:0x0333, B:139:0x0346, B:140:0x034b), top: B:210:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:140:0x034b A[Catch: all -> 0x02fc, TryCatch #5 {all -> 0x02fc, blocks: (B:114:0x02eb, B:116:0x02f5, B:121:0x0302, B:141:0x034f, B:143:0x0355, B:147:0x035e, B:149:0x0362, B:155:0x036f, B:157:0x0375, B:163:0x03be, B:172:0x0407, B:175:0x040f, B:179:0x0415, B:181:0x041b, B:183:0x0421, B:184:0x046d, B:186:0x0476, B:188:0x047c, B:190:0x0482, B:191:0x04a8, B:126:0x0310, B:128:0x0314, B:130:0x031b, B:132:0x0321, B:136:0x0333, B:139:0x0346, B:140:0x034b), top: B:210:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:143:0x0355 A[Catch: all -> 0x02fc, TRY_LEAVE, TryCatch #5 {all -> 0x02fc, blocks: (B:114:0x02eb, B:116:0x02f5, B:121:0x0302, B:141:0x034f, B:143:0x0355, B:147:0x035e, B:149:0x0362, B:155:0x036f, B:157:0x0375, B:163:0x03be, B:172:0x0407, B:175:0x040f, B:179:0x0415, B:181:0x041b, B:183:0x0421, B:184:0x046d, B:186:0x0476, B:188:0x047c, B:190:0x0482, B:191:0x04a8, B:126:0x0310, B:128:0x0314, B:130:0x031b, B:132:0x0321, B:136:0x0333, B:139:0x0346, B:140:0x034b), top: B:210:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:146:0x035c  */
    /* JADX WARN: Code duplicated, block: B:147:0x035e A[Catch: all -> 0x02fc, TRY_ENTER, TryCatch #5 {all -> 0x02fc, blocks: (B:114:0x02eb, B:116:0x02f5, B:121:0x0302, B:141:0x034f, B:143:0x0355, B:147:0x035e, B:149:0x0362, B:155:0x036f, B:157:0x0375, B:163:0x03be, B:172:0x0407, B:175:0x040f, B:179:0x0415, B:181:0x041b, B:183:0x0421, B:184:0x046d, B:186:0x0476, B:188:0x047c, B:190:0x0482, B:191:0x04a8, B:126:0x0310, B:128:0x0314, B:130:0x031b, B:132:0x0321, B:136:0x0333, B:139:0x0346, B:140:0x034b), top: B:210:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:152:0x036b  */
    /* JADX WARN: Code duplicated, block: B:155:0x036f A[Catch: all -> 0x02fc, TryCatch #5 {all -> 0x02fc, blocks: (B:114:0x02eb, B:116:0x02f5, B:121:0x0302, B:141:0x034f, B:143:0x0355, B:147:0x035e, B:149:0x0362, B:155:0x036f, B:157:0x0375, B:163:0x03be, B:172:0x0407, B:175:0x040f, B:179:0x0415, B:181:0x041b, B:183:0x0421, B:184:0x046d, B:186:0x0476, B:188:0x047c, B:190:0x0482, B:191:0x04a8, B:126:0x0310, B:128:0x0314, B:130:0x031b, B:132:0x0321, B:136:0x0333, B:139:0x0346, B:140:0x034b), top: B:210:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:157:0x0375 A[Catch: all -> 0x02fc, TryCatch #5 {all -> 0x02fc, blocks: (B:114:0x02eb, B:116:0x02f5, B:121:0x0302, B:141:0x034f, B:143:0x0355, B:147:0x035e, B:149:0x0362, B:155:0x036f, B:157:0x0375, B:163:0x03be, B:172:0x0407, B:175:0x040f, B:179:0x0415, B:181:0x041b, B:183:0x0421, B:184:0x046d, B:186:0x0476, B:188:0x047c, B:190:0x0482, B:191:0x04a8, B:126:0x0310, B:128:0x0314, B:130:0x031b, B:132:0x0321, B:136:0x0333, B:139:0x0346, B:140:0x034b), top: B:210:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:160:0x03b9  */
    /* JADX WARN: Code duplicated, block: B:163:0x03be A[Catch: all -> 0x02fc, TRY_LEAVE, TryCatch #5 {all -> 0x02fc, blocks: (B:114:0x02eb, B:116:0x02f5, B:121:0x0302, B:141:0x034f, B:143:0x0355, B:147:0x035e, B:149:0x0362, B:155:0x036f, B:157:0x0375, B:163:0x03be, B:172:0x0407, B:175:0x040f, B:179:0x0415, B:181:0x041b, B:183:0x0421, B:184:0x046d, B:186:0x0476, B:188:0x047c, B:190:0x0482, B:191:0x04a8, B:126:0x0310, B:128:0x0314, B:130:0x031b, B:132:0x0321, B:136:0x0333, B:139:0x0346, B:140:0x034b), top: B:210:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:167:0x03cc A[Catch: all -> 0x0282, TryCatch #1 {all -> 0x0282, blocks: (B:165:0x03c4, B:167:0x03cc, B:169:0x03d2, B:193:0x04ac, B:90:0x0234, B:92:0x0243, B:94:0x0255, B:96:0x025b, B:99:0x0285), top: B:203:0x0234 }] */
    /* JADX WARN: Code duplicated, block: B:171:0x0401  */
    /* JADX WARN: Code duplicated, block: B:174:0x040d A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:178:0x0414  */
    /* JADX WARN: Code duplicated, block: B:181:0x041b A[Catch: all -> 0x02fc, TryCatch #5 {all -> 0x02fc, blocks: (B:114:0x02eb, B:116:0x02f5, B:121:0x0302, B:141:0x034f, B:143:0x0355, B:147:0x035e, B:149:0x0362, B:155:0x036f, B:157:0x0375, B:163:0x03be, B:172:0x0407, B:175:0x040f, B:179:0x0415, B:181:0x041b, B:183:0x0421, B:184:0x046d, B:186:0x0476, B:188:0x047c, B:190:0x0482, B:191:0x04a8, B:126:0x0310, B:128:0x0314, B:130:0x031b, B:132:0x0321, B:136:0x0333, B:139:0x0346, B:140:0x034b), top: B:210:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:186:0x0476 A[Catch: all -> 0x02fc, TryCatch #5 {all -> 0x02fc, blocks: (B:114:0x02eb, B:116:0x02f5, B:121:0x0302, B:141:0x034f, B:143:0x0355, B:147:0x035e, B:149:0x0362, B:155:0x036f, B:157:0x0375, B:163:0x03be, B:172:0x0407, B:175:0x040f, B:179:0x0415, B:181:0x041b, B:183:0x0421, B:184:0x046d, B:186:0x0476, B:188:0x047c, B:190:0x0482, B:191:0x04a8, B:126:0x0310, B:128:0x0314, B:130:0x031b, B:132:0x0321, B:136:0x0333, B:139:0x0346, B:140:0x034b), top: B:210:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:188:0x047c A[Catch: all -> 0x02fc, TryCatch #5 {all -> 0x02fc, blocks: (B:114:0x02eb, B:116:0x02f5, B:121:0x0302, B:141:0x034f, B:143:0x0355, B:147:0x035e, B:149:0x0362, B:155:0x036f, B:157:0x0375, B:163:0x03be, B:172:0x0407, B:175:0x040f, B:179:0x0415, B:181:0x041b, B:183:0x0421, B:184:0x046d, B:186:0x0476, B:188:0x047c, B:190:0x0482, B:191:0x04a8, B:126:0x0310, B:128:0x0314, B:130:0x031b, B:132:0x0321, B:136:0x0333, B:139:0x0346, B:140:0x034b), top: B:210:0x02eb }] */
    /* JADX WARN: Code duplicated, block: B:218:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:219:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:8:0x001d  */
    /* JADX WARN: Code duplicated, block: B:92:0x0243 A[Catch: all -> 0x0282, TryCatch #1 {all -> 0x0282, blocks: (B:165:0x03c4, B:167:0x03cc, B:169:0x03d2, B:193:0x04ac, B:90:0x0234, B:92:0x0243, B:94:0x0255, B:96:0x025b, B:99:0x0285), top: B:203:0x0234 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v14 */
    public final Object refreshCapsuleData(List<? extends PipelineEntry> list, RefreshReason refreshReason, Function1 function1, Continuation continuation) throws Throwable {
        C01911 c01911;
        List<? extends PipelineEntry> list2;
        RefreshReason refreshReason2;
        Function1 function2;
        Object obj;
        Mutex mutex;
        int i;
        Mutex mutex2;
        int i2;
        int i3;
        Object obj2;
        RefreshReason refreshReason3;
        int i4;
        int i5;
        Function1 function3;
        List<? extends PipelineEntry> list3;
        int i6;
        NotificationIconData notificationIconData;
        int i7;
        int i8;
        RefreshReason refreshReason4;
        List<? extends PipelineEntry> list4;
        int i9;
        int i10;
        NotificationIconData notificationIconData2;
        int i11;
        List listGenerateCapsuleNotificationReportData;
        boolean z;
        List<? extends PipelineEntry> list5;
        int i12;
        Mutex mutex3;
        int i13;
        Object objBuildAggregateNotificationData;
        Object obj3;
        int i14;
        Mutex mutex4;
        List list6;
        Object obj4;
        int i15;
        NotificationIconData notificationIconData3;
        int i16;
        List<? extends PipelineEntry> list7;
        Object obj5;
        String str;
        CapsuleNotificationInnerData capsuleNotificationInnerData;
        String str2;
        CapsuleNotificationInnerData capsuleNotificationInnerData2;
        int notifCount;
        String str3;
        int i17;
        int i18;
        RefreshReason refreshReason5;
        NotificationEntry updatedNotificationEntry;
        boolean z2;
        ?? r9;
        CapsuleNotificationInnerData capsuleNotificationInnerData3;
        CapsuleNotificationInnerData capsuleNotificationInnerData4;
        CapsuleNotificationDataController capsuleNotificationDataController = this;
        if (continuation instanceof C01911) {
            c01911 = (C01911) continuation;
            int i19 = c01911.label;
            if ((i19 & Integer.MIN_VALUE) != 0) {
                c01911.label = i19 - Integer.MIN_VALUE;
            } else {
                c01911 = capsuleNotificationDataController.new C01911(continuation);
            }
        } else {
            c01911 = capsuleNotificationDataController.new C01911(continuation);
        }
        C01911 c01912 = c01911;
        Object obj6 = c01912.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i20 = c01912.label;
        try {
            if (i20 == 0) {
                ResultKt.throwOnFailure(obj6);
                Mutex mutex5 = capsuleNotificationDataController.refreshMutex;
                list2 = list;
                c01912.L$0 = list2;
                refreshReason2 = refreshReason;
                c01912.L$1 = refreshReason2;
                function2 = function1;
                c01912.L$2 = function2;
                c01912.L$3 = mutex5;
                c01912.I$0 = 0;
                c01912.label = 1;
                obj = null;
                if (mutex5.lock((Object) null, c01912) != coroutine_suspended) {
                    mutex = mutex5;
                    i = 0;
                }
                return coroutine_suspended;
            }
            if (i20 != 1) {
                if (i20 != 2) {
                    if (i20 == 3) {
                        int i21 = c01912.I$4;
                        i16 = c01912.I$3;
                        int i22 = c01912.I$2;
                        int i23 = c01912.I$1;
                        i15 = c01912.I$0;
                        List list8 = (List) c01912.L$5;
                        NotificationIconData notificationIconData4 = (NotificationIconData) c01912.L$4;
                        Mutex mutex6 = (Mutex) c01912.L$3;
                        obj4 = obj6;
                        Function1 function4 = (Function1) c01912.L$2;
                        RefreshReason refreshReason6 = (RefreshReason) c01912.L$1;
                        List<? extends PipelineEntry> list9 = (List) c01912.L$0;
                        try {
                            ResultKt.throwOnFailure(obj4);
                            list7 = list9;
                            obj3 = coroutine_suspended;
                            i13 = i21;
                            list6 = list8;
                            notificationIconData3 = notificationIconData4;
                            function3 = function4;
                            i10 = i23;
                            i14 = i22;
                            mutex4 = mutex6;
                            refreshReason4 = refreshReason6;
                            str = LogUtil.SEPARATOR;
                            try {
                                capsuleNotificationInnerData = (CapsuleNotificationInnerData) obj4;
                                str2 = TAG;
                                capsuleNotificationInnerData2 = capsuleNotificationDataController.lastAggregateData;
                                if (capsuleNotificationInnerData2 != null) {
                                    notifCount = capsuleNotificationInnerData2.getNotifCount();
                                } else {
                                    notifCount = 0;
                                }
                                str3 = LogUtil.TAG_NOTIFICATION;
                                if (refreshReason4 != RefreshReason.MEDIA_CHANGE) {
                                    if (i16 > notifCount || capsuleNotificationDataController.pendingNewNotification == null) {
                                        i17 = i13;
                                        i18 = i10;
                                        refreshReason5 = refreshReason4;
                                        if (i16 == notifCount) {
                                            updatedNotificationEntry = capsuleNotificationDataController.getUpdatedNotificationEntry(list7);
                                        } else {
                                            capsuleNotificationDataController.getUpdatedNotificationEntry(list7);
                                        }
                                    } else {
                                        capsuleNotificationDataController.getUpdatedNotificationEntry(list7);
                                        NotificationEntry notificationEntry = capsuleNotificationDataController.pendingNewNotification;
                                        if (notificationEntry != null) {
                                            refreshReason5 = refreshReason4;
                                            OplusNotificationEntryEx oplusNotificationEntryEx = notificationEntry.mNotificationEntryEx;
                                            long updateTime = oplusNotificationEntryEx != null ? oplusNotificationEntryEx.getUpdateTime() : 0L;
                                            long j = updateTime;
                                            i17 = i13;
                                            i18 = i10;
                                            capsuleNotificationDataController.preLatestPostTime = Math.max(j, capsuleNotificationDataController.preLatestPostTime);
                                            updatedNotificationEntry = notificationEntry;
                                        } else {
                                            refreshReason5 = refreshReason4;
                                        }
                                        long j2 = updateTime;
                                        i17 = i13;
                                        i18 = i10;
                                        capsuleNotificationDataController.preLatestPostTime = Math.max(j2, capsuleNotificationDataController.preLatestPostTime);
                                        updatedNotificationEntry = notificationEntry;
                                    }
                                    if (capsuleNotificationDataController.noNeedRefreshCapsuleData(capsuleNotificationInnerData, updatedNotificationEntry)) {
                                        Unit unit = Unit.INSTANCE;
                                        mutex4.unlock((Object) null);
                                        return unit;
                                    }
                                    if (updatedNotificationEntry != null && (capsuleNotificationInnerData4 = capsuleNotificationDataController.lastAggregateData) != null) {
                                        z2 = true;
                                        r9 = capsuleNotificationInnerData.hasSameStructAs(capsuleNotificationInnerData4) ? 1 : 0;
                                        if (updatedNotificationEntry != null) {
                                            if (capsuleNotificationDataController.isCapsuleState()) {
                                                c01912.L$0 = SpillingKt.nullOutSpilledVariable(list7);
                                                c01912.L$1 = SpillingKt.nullOutSpilledVariable(refreshReason5);
                                                c01912.L$2 = SpillingKt.nullOutSpilledVariable(function3);
                                                c01912.L$3 = mutex4;
                                                c01912.L$4 = SpillingKt.nullOutSpilledVariable(notificationIconData3);
                                                c01912.L$5 = SpillingKt.nullOutSpilledVariable(list6);
                                                c01912.L$6 = capsuleNotificationInnerData;
                                                c01912.L$7 = SpillingKt.nullOutSpilledVariable(updatedNotificationEntry);
                                                c01912.I$0 = i15;
                                                c01912.I$1 = i18;
                                                c01912.I$2 = i14;
                                                c01912.I$3 = i16;
                                                c01912.I$4 = i17;
                                                c01912.I$5 = notifCount;
                                                c01912.I$6 = r9;
                                                c01912.label = 4;
                                                if (capsuleNotificationDataController.handleNewNotification(updatedNotificationEntry, capsuleNotificationInnerData, function3, c01912) == obj3) {
                                                    return obj3;
                                                }
                                                mutex2 = mutex4;
                                                capsuleNotificationInnerData3 = capsuleNotificationInnerData;
                                            } else {
                                                capsuleNotificationDataController.handleAggregateNotification(capsuleNotificationInnerData, false, function3, false);
                                                mutex2 = mutex4;
                                            }
                                            capsuleNotificationDataController.pendingNewNotification = null;
                                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str3)) {
                                                Log.d(LogUtil.TAG + str3, str2 + str + "pendingNewNotification == null ");
                                            }
                                        } else {
                                            if (capsuleNotificationDataController.isCapsuleState() || r9 != 0 || capsuleNotificationDataController.hasEnterKeyguard) {
                                                z2 = false;
                                            }
                                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str3)) {
                                                String str4 = LogUtil.TAG + str3;
                                                StringBuilder sb = new StringBuilder();
                                                sb.append(str2);
                                                sb.append(str);
                                                sb.append("show aggregate card, isCapsuleState:" + capsuleNotificationDataController.isCapsuleState() + ", isContentUpdateOnly:" + ((boolean) r9) + ", !hasEnterKeyguard: " + capsuleNotificationDataController.hasEnterKeyguard);
                                                Log.d(str4, sb.toString());
                                            }
                                            capsuleNotificationDataController.handleAggregateNotification(capsuleNotificationInnerData, z2, function3, capsuleNotificationDataController.hasEnterKeyguard);
                                            if (capsuleNotificationDataController.hasEnterKeyguard) {
                                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str3)) {
                                                    Log.d(LogUtil.TAG + str3, str2 + str + "enter keyguard: first show card finished, hasEnterKeyguard = false");
                                                }
                                                capsuleNotificationDataController.hasEnterKeyguard = false;
                                            }
                                            mutex2 = mutex4;
                                        }
                                        capsuleNotificationDataController.lastAggregateData = capsuleNotificationInnerData;
                                        Unit unit2 = Unit.INSTANCE;
                                        mutex2.unlock((Object) null);
                                        return unit2;
                                    }
                                    z2 = true;
                                    if (updatedNotificationEntry != null) {
                                        if (capsuleNotificationDataController.isCapsuleState()) {
                                            c01912.L$0 = SpillingKt.nullOutSpilledVariable(list7);
                                            c01912.L$1 = SpillingKt.nullOutSpilledVariable(refreshReason5);
                                            c01912.L$2 = SpillingKt.nullOutSpilledVariable(function3);
                                            c01912.L$3 = mutex4;
                                            c01912.L$4 = SpillingKt.nullOutSpilledVariable(notificationIconData3);
                                            c01912.L$5 = SpillingKt.nullOutSpilledVariable(list6);
                                            c01912.L$6 = capsuleNotificationInnerData;
                                            c01912.L$7 = SpillingKt.nullOutSpilledVariable(updatedNotificationEntry);
                                            c01912.I$0 = i15;
                                            c01912.I$1 = i18;
                                            c01912.I$2 = i14;
                                            c01912.I$3 = i16;
                                            c01912.I$4 = i17;
                                            c01912.I$5 = notifCount;
                                            c01912.I$6 = r9;
                                            c01912.label = 4;
                                            if (capsuleNotificationDataController.handleNewNotification(updatedNotificationEntry, capsuleNotificationInnerData, function3, c01912) == obj3) {
                                                return obj3;
                                            }
                                            mutex2 = mutex4;
                                            capsuleNotificationInnerData3 = capsuleNotificationInnerData;
                                        } else {
                                            capsuleNotificationDataController.handleAggregateNotification(capsuleNotificationInnerData, false, function3, false);
                                            mutex2 = mutex4;
                                        }
                                        capsuleNotificationDataController.pendingNewNotification = null;
                                        if (LogUtil.getNormalDebug()) {
                                            Log.d(LogUtil.TAG + str3, str2 + str + "pendingNewNotification == null ");
                                        }
                                    } else {
                                        if (capsuleNotificationDataController.isCapsuleState()) {
                                            z2 = false;
                                        } else {
                                            z2 = false;
                                        }
                                        if (LogUtil.getNormalDebug()) {
                                            String str5 = LogUtil.TAG + str3;
                                            StringBuilder sb2 = new StringBuilder();
                                            sb2.append(str2);
                                            sb2.append(str);
                                            sb2.append("show aggregate card, isCapsuleState:" + capsuleNotificationDataController.isCapsuleState() + ", isContentUpdateOnly:" + ((boolean) r9) + ", !hasEnterKeyguard: " + capsuleNotificationDataController.hasEnterKeyguard);
                                            Log.d(str5, sb2.toString());
                                        }
                                        capsuleNotificationDataController.handleAggregateNotification(capsuleNotificationInnerData, z2, function3, capsuleNotificationDataController.hasEnterKeyguard);
                                        if (capsuleNotificationDataController.hasEnterKeyguard) {
                                            if (LogUtil.getNormalDebug()) {
                                                Log.d(LogUtil.TAG + str3, str2 + str + "enter keyguard: first show card finished, hasEnterKeyguard = false");
                                            }
                                            capsuleNotificationDataController.hasEnterKeyguard = false;
                                        }
                                        mutex2 = mutex4;
                                    }
                                    capsuleNotificationDataController.lastAggregateData = capsuleNotificationInnerData;
                                    Unit unit3 = Unit.INSTANCE;
                                    mutex2.unlock((Object) null);
                                    return unit3;
                                }
                                i17 = i13;
                                i18 = i10;
                                refreshReason5 = refreshReason4;
                                updatedNotificationEntry = null;
                                if (capsuleNotificationDataController.noNeedRefreshCapsuleData(capsuleNotificationInnerData, updatedNotificationEntry)) {
                                    Unit unit4 = Unit.INSTANCE;
                                    mutex4.unlock((Object) null);
                                    return unit4;
                                }
                                if (updatedNotificationEntry != null) {
                                    z2 = true;
                                } else {
                                    z2 = true;
                                }
                                if (updatedNotificationEntry != null) {
                                    if (capsuleNotificationDataController.isCapsuleState()) {
                                        c01912.L$0 = SpillingKt.nullOutSpilledVariable(list7);
                                        c01912.L$1 = SpillingKt.nullOutSpilledVariable(refreshReason5);
                                        c01912.L$2 = SpillingKt.nullOutSpilledVariable(function3);
                                        c01912.L$3 = mutex4;
                                        c01912.L$4 = SpillingKt.nullOutSpilledVariable(notificationIconData3);
                                        c01912.L$5 = SpillingKt.nullOutSpilledVariable(list6);
                                        c01912.L$6 = capsuleNotificationInnerData;
                                        c01912.L$7 = SpillingKt.nullOutSpilledVariable(updatedNotificationEntry);
                                        c01912.I$0 = i15;
                                        c01912.I$1 = i18;
                                        c01912.I$2 = i14;
                                        c01912.I$3 = i16;
                                        c01912.I$4 = i17;
                                        c01912.I$5 = notifCount;
                                        c01912.I$6 = r9;
                                        c01912.label = 4;
                                        if (capsuleNotificationDataController.handleNewNotification(updatedNotificationEntry, capsuleNotificationInnerData, function3, c01912) == obj3) {
                                            return obj3;
                                        }
                                        mutex2 = mutex4;
                                        capsuleNotificationInnerData3 = capsuleNotificationInnerData;
                                    } else {
                                        capsuleNotificationDataController.handleAggregateNotification(capsuleNotificationInnerData, false, function3, false);
                                        mutex2 = mutex4;
                                    }
                                    capsuleNotificationDataController.pendingNewNotification = null;
                                    if (LogUtil.getNormalDebug()) {
                                        Log.d(LogUtil.TAG + str3, str2 + str + "pendingNewNotification == null ");
                                    }
                                } else {
                                    if (capsuleNotificationDataController.isCapsuleState()) {
                                        z2 = false;
                                    } else {
                                        z2 = false;
                                    }
                                    if (LogUtil.getNormalDebug()) {
                                        String str6 = LogUtil.TAG + str3;
                                        StringBuilder sb3 = new StringBuilder();
                                        sb3.append(str2);
                                        sb3.append(str);
                                        sb3.append("show aggregate card, isCapsuleState:" + capsuleNotificationDataController.isCapsuleState() + ", isContentUpdateOnly:" + ((boolean) r9) + ", !hasEnterKeyguard: " + capsuleNotificationDataController.hasEnterKeyguard);
                                        Log.d(str6, sb3.toString());
                                    }
                                    capsuleNotificationDataController.handleAggregateNotification(capsuleNotificationInnerData, z2, function3, capsuleNotificationDataController.hasEnterKeyguard);
                                    if (capsuleNotificationDataController.hasEnterKeyguard) {
                                        if (LogUtil.getNormalDebug()) {
                                            Log.d(LogUtil.TAG + str3, str2 + str + "enter keyguard: first show card finished, hasEnterKeyguard = false");
                                        }
                                        capsuleNotificationDataController.hasEnterKeyguard = false;
                                    }
                                    mutex2 = mutex4;
                                }
                                capsuleNotificationDataController.lastAggregateData = capsuleNotificationInnerData;
                                Unit unit5 = Unit.INSTANCE;
                                mutex2.unlock((Object) null);
                                return unit5;
                            } catch (Throwable th) {
                                th = th;
                                mutex2 = mutex4;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            mutex2 = mutex6;
                        }
                    } else {
                        if (i20 != 4) {
                            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        capsuleNotificationInnerData3 = (CapsuleNotificationInnerData) c01912.L$6;
                        mutex2 = (Mutex) c01912.L$3;
                        try {
                            ResultKt.throwOnFailure(obj6);
                            str = LogUtil.SEPARATOR;
                            str2 = TAG;
                            str3 = LogUtil.TAG_NOTIFICATION;
                        } catch (Throwable th3) {
                            th = th3;
                            obj5 = null;
                        }
                    }
                    capsuleNotificationInnerData = capsuleNotificationInnerData3;
                    capsuleNotificationDataController.pendingNewNotification = null;
                    if (LogUtil.getNormalDebug()) {
                        Log.d(LogUtil.TAG + str3, str2 + str + "pendingNewNotification == null ");
                    }
                    capsuleNotificationDataController.lastAggregateData = capsuleNotificationInnerData;
                    Unit unit6 = Unit.INSTANCE;
                    mutex2.unlock((Object) null);
                    return unit6;
                }
                int i24 = c01912.I$4;
                i5 = c01912.I$3;
                int i25 = c01912.I$2;
                i6 = c01912.I$1;
                int i26 = c01912.I$0;
                Mutex mutex7 = (Mutex) c01912.L$3;
                Function1 function5 = (Function1) c01912.L$2;
                RefreshReason refreshReason7 = (RefreshReason) c01912.L$1;
                List<? extends PipelineEntry> list10 = (List) c01912.L$0;
                try {
                    ResultKt.throwOnFailure(obj6);
                    i2 = i24;
                    obj2 = obj6;
                    refreshReason3 = refreshReason7;
                    function3 = function5;
                    i3 = i26;
                    i4 = i25;
                    mutex2 = mutex7;
                    list3 = list10;
                    try {
                        notificationIconData = (NotificationIconData) obj2;
                        List<? extends PipelineEntry> list11 = list3;
                        i10 = i6;
                        i9 = i4;
                        list4 = list11;
                        i8 = i2;
                        i7 = i3;
                        refreshReason4 = refreshReason3;
                        notificationIconData2 = notificationIconData;
                        if (notificationIconData2 == null) {
                            capsuleNotificationDataController.lastAggregateData = null;
                            capsuleNotificationDataController.isNewNotificationPinned = false;
                            capsuleNotificationDataController.setCurrentContainer(null);
                            function3.invoke((Object) null);
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                Log.d(LogUtil.TAG + LogUtil.TAG_NOTIFICATION, TAG + LogUtil.SEPARATOR + "iconData is null");
                            }
                            Unit unit7 = Unit.INSTANCE;
                            mutex2.unlock((Object) null);
                            return unit7;
                        }
                        i11 = i8;
                        try {
                            listGenerateCapsuleNotificationReportData = generateCapsuleNotificationReportData(list4, notificationIconData2);
                            if (i11 != 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            c01912.L$0 = list4;
                            c01912.L$1 = refreshReason4;
                            c01912.L$2 = function3;
                            c01912.L$3 = mutex2;
                            c01912.L$4 = SpillingKt.nullOutSpilledVariable(notificationIconData2);
                            c01912.L$5 = SpillingKt.nullOutSpilledVariable(listGenerateCapsuleNotificationReportData);
                            c01912.I$0 = i7;
                            c01912.I$1 = i10;
                            c01912.I$2 = i9;
                            c01912.I$3 = i5;
                            c01912.I$4 = i11;
                            c01912.label = 3;
                            list5 = list4;
                            boolean z3 = z;
                            i12 = i7;
                            capsuleNotificationDataController = this;
                            mutex3 = mutex2;
                            i13 = i11;
                            try {
                                objBuildAggregateNotificationData = capsuleNotificationDataController.buildAggregateNotificationData(list5, i5, notificationIconData2, i9, listGenerateCapsuleNotificationReportData, z3, c01912);
                                obj3 = coroutine_suspended;
                                if (objBuildAggregateNotificationData == obj3) {
                                    return obj3;
                                }
                                i14 = i9;
                                mutex4 = mutex3;
                                list6 = listGenerateCapsuleNotificationReportData;
                                obj4 = objBuildAggregateNotificationData;
                                i15 = i12;
                                notificationIconData3 = notificationIconData2;
                                i16 = i5;
                                list7 = list5;
                                str = LogUtil.SEPARATOR;
                                capsuleNotificationInnerData = (CapsuleNotificationInnerData) obj4;
                                str2 = TAG;
                                capsuleNotificationInnerData2 = capsuleNotificationDataController.lastAggregateData;
                                if (capsuleNotificationInnerData2 != null) {
                                    notifCount = capsuleNotificationInnerData2.getNotifCount();
                                } else {
                                    notifCount = 0;
                                }
                                str3 = LogUtil.TAG_NOTIFICATION;
                                if (refreshReason4 != RefreshReason.MEDIA_CHANGE) {
                                    if (i16 > notifCount) {
                                        i17 = i13;
                                        i18 = i10;
                                        refreshReason5 = refreshReason4;
                                        if (i16 == notifCount) {
                                            updatedNotificationEntry = capsuleNotificationDataController.getUpdatedNotificationEntry(list7);
                                        } else {
                                            capsuleNotificationDataController.getUpdatedNotificationEntry(list7);
                                        }
                                    } else {
                                        i17 = i13;
                                        i18 = i10;
                                        refreshReason5 = refreshReason4;
                                        if (i16 == notifCount) {
                                            updatedNotificationEntry = capsuleNotificationDataController.getUpdatedNotificationEntry(list7);
                                        } else {
                                            capsuleNotificationDataController.getUpdatedNotificationEntry(list7);
                                        }
                                    }
                                    if (capsuleNotificationDataController.noNeedRefreshCapsuleData(capsuleNotificationInnerData, updatedNotificationEntry)) {
                                        Unit unit8 = Unit.INSTANCE;
                                        mutex4.unlock((Object) null);
                                        return unit8;
                                    }
                                    if (updatedNotificationEntry != null) {
                                        z2 = true;
                                    } else {
                                        z2 = true;
                                    }
                                    if (updatedNotificationEntry != null) {
                                        if (capsuleNotificationDataController.isCapsuleState()) {
                                            c01912.L$0 = SpillingKt.nullOutSpilledVariable(list7);
                                            c01912.L$1 = SpillingKt.nullOutSpilledVariable(refreshReason5);
                                            c01912.L$2 = SpillingKt.nullOutSpilledVariable(function3);
                                            c01912.L$3 = mutex4;
                                            c01912.L$4 = SpillingKt.nullOutSpilledVariable(notificationIconData3);
                                            c01912.L$5 = SpillingKt.nullOutSpilledVariable(list6);
                                            c01912.L$6 = capsuleNotificationInnerData;
                                            c01912.L$7 = SpillingKt.nullOutSpilledVariable(updatedNotificationEntry);
                                            c01912.I$0 = i15;
                                            c01912.I$1 = i18;
                                            c01912.I$2 = i14;
                                            c01912.I$3 = i16;
                                            c01912.I$4 = i17;
                                            c01912.I$5 = notifCount;
                                            c01912.I$6 = r9;
                                            c01912.label = 4;
                                            if (capsuleNotificationDataController.handleNewNotification(updatedNotificationEntry, capsuleNotificationInnerData, function3, c01912) == obj3) {
                                                return obj3;
                                            }
                                            mutex2 = mutex4;
                                            capsuleNotificationInnerData3 = capsuleNotificationInnerData;
                                            capsuleNotificationInnerData = capsuleNotificationInnerData3;
                                        } else {
                                            capsuleNotificationDataController.handleAggregateNotification(capsuleNotificationInnerData, false, function3, false);
                                            mutex2 = mutex4;
                                        }
                                        capsuleNotificationDataController.pendingNewNotification = null;
                                        if (LogUtil.getNormalDebug()) {
                                            Log.d(LogUtil.TAG + str3, str2 + str + "pendingNewNotification == null ");
                                        }
                                    } else {
                                        if (capsuleNotificationDataController.isCapsuleState()) {
                                            z2 = false;
                                        } else {
                                            z2 = false;
                                        }
                                        if (LogUtil.getNormalDebug()) {
                                            String str7 = LogUtil.TAG + str3;
                                            StringBuilder sb4 = new StringBuilder();
                                            sb4.append(str2);
                                            sb4.append(str);
                                            sb4.append("show aggregate card, isCapsuleState:" + capsuleNotificationDataController.isCapsuleState() + ", isContentUpdateOnly:" + ((boolean) r9) + ", !hasEnterKeyguard: " + capsuleNotificationDataController.hasEnterKeyguard);
                                            Log.d(str7, sb4.toString());
                                        }
                                        capsuleNotificationDataController.handleAggregateNotification(capsuleNotificationInnerData, z2, function3, capsuleNotificationDataController.hasEnterKeyguard);
                                        if (capsuleNotificationDataController.hasEnterKeyguard) {
                                            if (LogUtil.getNormalDebug()) {
                                                Log.d(LogUtil.TAG + str3, str2 + str + "enter keyguard: first show card finished, hasEnterKeyguard = false");
                                            }
                                            capsuleNotificationDataController.hasEnterKeyguard = false;
                                        }
                                        mutex2 = mutex4;
                                    }
                                    capsuleNotificationDataController.lastAggregateData = capsuleNotificationInnerData;
                                    Unit unit9 = Unit.INSTANCE;
                                    mutex2.unlock((Object) null);
                                    return unit9;
                                }
                                i17 = i13;
                                i18 = i10;
                                refreshReason5 = refreshReason4;
                                updatedNotificationEntry = null;
                                if (capsuleNotificationDataController.noNeedRefreshCapsuleData(capsuleNotificationInnerData, updatedNotificationEntry)) {
                                    Unit unit10 = Unit.INSTANCE;
                                    mutex4.unlock((Object) null);
                                    return unit10;
                                }
                                if (updatedNotificationEntry != null) {
                                    z2 = true;
                                } else {
                                    z2 = true;
                                }
                                if (updatedNotificationEntry != null) {
                                    if (capsuleNotificationDataController.isCapsuleState()) {
                                        c01912.L$0 = SpillingKt.nullOutSpilledVariable(list7);
                                        c01912.L$1 = SpillingKt.nullOutSpilledVariable(refreshReason5);
                                        c01912.L$2 = SpillingKt.nullOutSpilledVariable(function3);
                                        c01912.L$3 = mutex4;
                                        c01912.L$4 = SpillingKt.nullOutSpilledVariable(notificationIconData3);
                                        c01912.L$5 = SpillingKt.nullOutSpilledVariable(list6);
                                        c01912.L$6 = capsuleNotificationInnerData;
                                        c01912.L$7 = SpillingKt.nullOutSpilledVariable(updatedNotificationEntry);
                                        c01912.I$0 = i15;
                                        c01912.I$1 = i18;
                                        c01912.I$2 = i14;
                                        c01912.I$3 = i16;
                                        c01912.I$4 = i17;
                                        c01912.I$5 = notifCount;
                                        c01912.I$6 = r9;
                                        c01912.label = 4;
                                        if (capsuleNotificationDataController.handleNewNotification(updatedNotificationEntry, capsuleNotificationInnerData, function3, c01912) == obj3) {
                                            return obj3;
                                        }
                                        mutex2 = mutex4;
                                        capsuleNotificationInnerData3 = capsuleNotificationInnerData;
                                        capsuleNotificationInnerData = capsuleNotificationInnerData3;
                                    } else {
                                        capsuleNotificationDataController.handleAggregateNotification(capsuleNotificationInnerData, false, function3, false);
                                        mutex2 = mutex4;
                                    }
                                    capsuleNotificationDataController.pendingNewNotification = null;
                                    if (LogUtil.getNormalDebug()) {
                                        Log.d(LogUtil.TAG + str3, str2 + str + "pendingNewNotification == null ");
                                    }
                                } else {
                                    if (capsuleNotificationDataController.isCapsuleState()) {
                                        z2 = false;
                                    } else {
                                        z2 = false;
                                    }
                                    if (LogUtil.getNormalDebug()) {
                                        String str8 = LogUtil.TAG + str3;
                                        StringBuilder sb5 = new StringBuilder();
                                        sb5.append(str2);
                                        sb5.append(str);
                                        sb5.append("show aggregate card, isCapsuleState:" + capsuleNotificationDataController.isCapsuleState() + ", isContentUpdateOnly:" + ((boolean) r9) + ", !hasEnterKeyguard: " + capsuleNotificationDataController.hasEnterKeyguard);
                                        Log.d(str8, sb5.toString());
                                    }
                                    capsuleNotificationDataController.handleAggregateNotification(capsuleNotificationInnerData, z2, function3, capsuleNotificationDataController.hasEnterKeyguard);
                                    if (capsuleNotificationDataController.hasEnterKeyguard) {
                                        if (LogUtil.getNormalDebug()) {
                                            Log.d(LogUtil.TAG + str3, str2 + str + "enter keyguard: first show card finished, hasEnterKeyguard = false");
                                        }
                                        capsuleNotificationDataController.hasEnterKeyguard = false;
                                    }
                                    mutex2 = mutex4;
                                }
                                capsuleNotificationDataController.lastAggregateData = capsuleNotificationInnerData;
                                Unit unit11 = Unit.INSTANCE;
                                mutex2.unlock((Object) null);
                                return unit11;
                            } catch (Throwable th4) {
                                th = th4;
                                mutex2 = mutex3;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    mutex2 = mutex7;
                }
                obj5 = null;
                mutex2.unlock(obj5);
                throw th;
            }
            i = c01912.I$0;
            Mutex mutex8 = (Mutex) c01912.L$3;
            Function1 function6 = (Function1) c01912.L$2;
            RefreshReason refreshReason8 = (RefreshReason) c01912.L$1;
            List<? extends PipelineEntry> list12 = (List) c01912.L$0;
            ResultKt.throwOnFailure(obj6);
            function2 = function6;
            refreshReason2 = refreshReason8;
            mutex = mutex8;
            list2 = list12;
            obj = null;
            if (capsuleNotificationDataController.handleKeyguardSessionBoundary(function2)) {
                Unit unit12 = Unit.INSTANCE;
                mutex.unlock(obj);
                return unit12;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "CapsuleNotificationDataController-->".concat("refreshCapsuleData: " + refreshReason2));
            }
            capsuleNotificationDataController.lastEntries = CollectionsKt___CollectionsKt.toList(list2);
            CapsuleMediaPlayerDataHelper capsuleMediaPlayerDataHelper = (CapsuleMediaPlayerDataHelper) capsuleNotificationDataController.mediaPlayerDataHelper.get();
            int mediaPlayerCount = capsuleMediaPlayerDataHelper != null ? capsuleMediaPlayerDataHelper.getMediaPlayerCount() : 0;
            if (list2.isEmpty() && mediaPlayerCount == 0) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    Log.d("SystemUi--Notification", "CapsuleNotificationDataController-->notif list is empty");
                }
                capsuleNotificationDataController.lastAggregateData = null;
                capsuleNotificationDataController.isNewNotificationPinned = false;
                capsuleNotificationDataController.setCurrentContainer(null);
                function2.invoke((Object) null);
                Unit unit13 = Unit.INSTANCE;
                mutex.unlock((Object) null);
                return unit13;
            }
            Iterator it = list2.iterator();
            int untruncatedChildCount = 0;
            while (it.hasNext()) {
                GroupEntry groupEntry = (PipelineEntry) it.next();
                Iterator it2 = it;
                untruncatedChildCount += groupEntry instanceof GroupEntry ? groupEntry.getUntruncatedChildCount() : 1;
                it = it2;
            }
            int i27 = untruncatedChildCount + mediaPlayerCount;
            int i28 = 999;
            if (i27 <= 999) {
                i28 = i27;
            }
            i2 = capsuleNotificationDataController.countDistinctSources(mediaPlayerCount, list2) > 1 ? 1 : 0;
            if (i2 == 0) {
                c01912.L$0 = list2;
                c01912.L$1 = refreshReason2;
                c01912.L$2 = function2;
                c01912.L$3 = mutex;
                c01912.I$0 = i;
                c01912.I$1 = 0;
                c01912.I$2 = mediaPlayerCount;
                c01912.I$3 = i28;
                c01912.I$4 = i2;
                c01912.label = 2;
                Object objBuildIconData = capsuleNotificationDataController.buildIconData(list2, c01912);
                if (objBuildIconData != coroutine_suspended) {
                    i3 = i;
                    obj2 = objBuildIconData;
                    refreshReason3 = refreshReason2;
                    i4 = mediaPlayerCount;
                    i5 = i28;
                    mutex2 = mutex;
                    function3 = function2;
                    list3 = list2;
                    i6 = 0;
                    notificationIconData = (NotificationIconData) obj2;
                    List<? extends PipelineEntry> list13 = list3;
                    i10 = i6;
                    i9 = i4;
                    list4 = list13;
                    i8 = i2;
                    i7 = i3;
                    refreshReason4 = refreshReason3;
                }
                return coroutine_suspended;
            }
            notificationIconData = new NotificationIconData("aggregate", (String) null, capsuleNotificationDataController.getAggregateIcon(), (Icon) null, false, (Integer) null, (NotificationEntry) null, 176);
            int i29 = i2;
            i7 = i;
            i8 = i29;
            refreshReason4 = refreshReason2;
            list4 = list2;
            i9 = mediaPlayerCount;
            i5 = i28;
            mutex2 = mutex;
            function3 = function2;
            i10 = 0;
            notificationIconData2 = notificationIconData;
            if (notificationIconData2 == null) {
                capsuleNotificationDataController.lastAggregateData = null;
                capsuleNotificationDataController.isNewNotificationPinned = false;
                capsuleNotificationDataController.setCurrentContainer(null);
                function3.invoke((Object) null);
                if (LogUtil.getNormalDebug()) {
                    Log.d(LogUtil.TAG + LogUtil.TAG_NOTIFICATION, TAG + LogUtil.SEPARATOR + "iconData is null");
                }
                Unit unit14 = Unit.INSTANCE;
                mutex2.unlock((Object) null);
                return unit14;
            }
            i11 = i8;
            listGenerateCapsuleNotificationReportData = generateCapsuleNotificationReportData(list4, notificationIconData2);
            if (i11 != 0) {
                z = true;
            } else {
                z = false;
            }
            c01912.L$0 = list4;
            c01912.L$1 = refreshReason4;
            c01912.L$2 = function3;
            c01912.L$3 = mutex2;
            c01912.L$4 = SpillingKt.nullOutSpilledVariable(notificationIconData2);
            c01912.L$5 = SpillingKt.nullOutSpilledVariable(listGenerateCapsuleNotificationReportData);
            c01912.I$0 = i7;
            c01912.I$1 = i10;
            c01912.I$2 = i9;
            c01912.I$3 = i5;
            c01912.I$4 = i11;
            c01912.label = 3;
            list5 = list4;
            boolean z4 = z;
            i12 = i7;
            capsuleNotificationDataController = this;
            mutex3 = mutex2;
            i13 = i11;
            objBuildAggregateNotificationData = capsuleNotificationDataController.buildAggregateNotificationData(list5, i5, notificationIconData2, i9, listGenerateCapsuleNotificationReportData, z4, c01912);
            obj3 = coroutine_suspended;
            if (objBuildAggregateNotificationData == obj3) {
                return obj3;
            }
            i14 = i9;
            mutex4 = mutex3;
            list6 = listGenerateCapsuleNotificationReportData;
            obj4 = objBuildAggregateNotificationData;
            i15 = i12;
            notificationIconData3 = notificationIconData2;
            i16 = i5;
            list7 = list5;
            str = LogUtil.SEPARATOR;
            capsuleNotificationInnerData = (CapsuleNotificationInnerData) obj4;
            str2 = TAG;
            capsuleNotificationInnerData2 = capsuleNotificationDataController.lastAggregateData;
            if (capsuleNotificationInnerData2 != null) {
                notifCount = capsuleNotificationInnerData2.getNotifCount();
            } else {
                notifCount = 0;
            }
            str3 = LogUtil.TAG_NOTIFICATION;
            if (refreshReason4 != RefreshReason.MEDIA_CHANGE) {
                if (i16 > notifCount) {
                    i17 = i13;
                    i18 = i10;
                    refreshReason5 = refreshReason4;
                    if (i16 == notifCount) {
                        updatedNotificationEntry = capsuleNotificationDataController.getUpdatedNotificationEntry(list7);
                    } else {
                        capsuleNotificationDataController.getUpdatedNotificationEntry(list7);
                    }
                } else {
                    i17 = i13;
                    i18 = i10;
                    refreshReason5 = refreshReason4;
                    if (i16 == notifCount) {
                        updatedNotificationEntry = capsuleNotificationDataController.getUpdatedNotificationEntry(list7);
                    } else {
                        capsuleNotificationDataController.getUpdatedNotificationEntry(list7);
                    }
                }
                if (capsuleNotificationDataController.noNeedRefreshCapsuleData(capsuleNotificationInnerData, updatedNotificationEntry)) {
                    Unit unit15 = Unit.INSTANCE;
                    mutex4.unlock((Object) null);
                    return unit15;
                }
                if (updatedNotificationEntry != null) {
                    z2 = true;
                } else {
                    z2 = true;
                }
                if (updatedNotificationEntry != null) {
                    if (capsuleNotificationDataController.isCapsuleState()) {
                        c01912.L$0 = SpillingKt.nullOutSpilledVariable(list7);
                        c01912.L$1 = SpillingKt.nullOutSpilledVariable(refreshReason5);
                        c01912.L$2 = SpillingKt.nullOutSpilledVariable(function3);
                        c01912.L$3 = mutex4;
                        c01912.L$4 = SpillingKt.nullOutSpilledVariable(notificationIconData3);
                        c01912.L$5 = SpillingKt.nullOutSpilledVariable(list6);
                        c01912.L$6 = capsuleNotificationInnerData;
                        c01912.L$7 = SpillingKt.nullOutSpilledVariable(updatedNotificationEntry);
                        c01912.I$0 = i15;
                        c01912.I$1 = i18;
                        c01912.I$2 = i14;
                        c01912.I$3 = i16;
                        c01912.I$4 = i17;
                        c01912.I$5 = notifCount;
                        c01912.I$6 = r9;
                        c01912.label = 4;
                        if (capsuleNotificationDataController.handleNewNotification(updatedNotificationEntry, capsuleNotificationInnerData, function3, c01912) == obj3) {
                            return obj3;
                        }
                        mutex2 = mutex4;
                        capsuleNotificationInnerData3 = capsuleNotificationInnerData;
                        capsuleNotificationInnerData = capsuleNotificationInnerData3;
                    } else {
                        capsuleNotificationDataController.handleAggregateNotification(capsuleNotificationInnerData, false, function3, false);
                        mutex2 = mutex4;
                    }
                    capsuleNotificationDataController.pendingNewNotification = null;
                    if (LogUtil.getNormalDebug()) {
                        Log.d(LogUtil.TAG + str3, str2 + str + "pendingNewNotification == null ");
                    }
                } else {
                    if (capsuleNotificationDataController.isCapsuleState()) {
                        z2 = false;
                    } else {
                        z2 = false;
                    }
                    if (LogUtil.getNormalDebug()) {
                        String str9 = LogUtil.TAG + str3;
                        StringBuilder sb6 = new StringBuilder();
                        sb6.append(str2);
                        sb6.append(str);
                        sb6.append("show aggregate card, isCapsuleState:" + capsuleNotificationDataController.isCapsuleState() + ", isContentUpdateOnly:" + ((boolean) r9) + ", !hasEnterKeyguard: " + capsuleNotificationDataController.hasEnterKeyguard);
                        Log.d(str9, sb6.toString());
                    }
                    capsuleNotificationDataController.handleAggregateNotification(capsuleNotificationInnerData, z2, function3, capsuleNotificationDataController.hasEnterKeyguard);
                    if (capsuleNotificationDataController.hasEnterKeyguard) {
                        if (LogUtil.getNormalDebug()) {
                            Log.d(LogUtil.TAG + str3, str2 + str + "enter keyguard: first show card finished, hasEnterKeyguard = false");
                        }
                        capsuleNotificationDataController.hasEnterKeyguard = false;
                    }
                    mutex2 = mutex4;
                }
                capsuleNotificationDataController.lastAggregateData = capsuleNotificationInnerData;
                Unit unit16 = Unit.INSTANCE;
                mutex2.unlock((Object) null);
                return unit16;
            }
            i17 = i13;
            i18 = i10;
            refreshReason5 = refreshReason4;
            updatedNotificationEntry = null;
            if (capsuleNotificationDataController.noNeedRefreshCapsuleData(capsuleNotificationInnerData, updatedNotificationEntry)) {
                Unit unit17 = Unit.INSTANCE;
                mutex4.unlock((Object) null);
                return unit17;
            }
            if (updatedNotificationEntry != null) {
                z2 = true;
            } else {
                z2 = true;
            }
            if (updatedNotificationEntry != null) {
                if (capsuleNotificationDataController.isCapsuleState()) {
                    c01912.L$0 = SpillingKt.nullOutSpilledVariable(list7);
                    c01912.L$1 = SpillingKt.nullOutSpilledVariable(refreshReason5);
                    c01912.L$2 = SpillingKt.nullOutSpilledVariable(function3);
                    c01912.L$3 = mutex4;
                    c01912.L$4 = SpillingKt.nullOutSpilledVariable(notificationIconData3);
                    c01912.L$5 = SpillingKt.nullOutSpilledVariable(list6);
                    c01912.L$6 = capsuleNotificationInnerData;
                    c01912.L$7 = SpillingKt.nullOutSpilledVariable(updatedNotificationEntry);
                    c01912.I$0 = i15;
                    c01912.I$1 = i18;
                    c01912.I$2 = i14;
                    c01912.I$3 = i16;
                    c01912.I$4 = i17;
                    c01912.I$5 = notifCount;
                    c01912.I$6 = r9;
                    c01912.label = 4;
                    if (capsuleNotificationDataController.handleNewNotification(updatedNotificationEntry, capsuleNotificationInnerData, function3, c01912) == obj3) {
                        return obj3;
                    }
                    mutex2 = mutex4;
                    capsuleNotificationInnerData3 = capsuleNotificationInnerData;
                    capsuleNotificationInnerData = capsuleNotificationInnerData3;
                } else {
                    capsuleNotificationDataController.handleAggregateNotification(capsuleNotificationInnerData, false, function3, false);
                    mutex2 = mutex4;
                }
                capsuleNotificationDataController.pendingNewNotification = null;
                if (LogUtil.getNormalDebug()) {
                    Log.d(LogUtil.TAG + str3, str2 + str + "pendingNewNotification == null ");
                }
            } else {
                if (capsuleNotificationDataController.isCapsuleState()) {
                    z2 = false;
                } else {
                    z2 = false;
                }
                if (LogUtil.getNormalDebug()) {
                    String str10 = LogUtil.TAG + str3;
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append(str2);
                    sb7.append(str);
                    sb7.append("show aggregate card, isCapsuleState:" + capsuleNotificationDataController.isCapsuleState() + ", isContentUpdateOnly:" + ((boolean) r9) + ", !hasEnterKeyguard: " + capsuleNotificationDataController.hasEnterKeyguard);
                    Log.d(str10, sb7.toString());
                }
                capsuleNotificationDataController.handleAggregateNotification(capsuleNotificationInnerData, z2, function3, capsuleNotificationDataController.hasEnterKeyguard);
                if (capsuleNotificationDataController.hasEnterKeyguard) {
                    if (LogUtil.getNormalDebug()) {
                        Log.d(LogUtil.TAG + str3, str2 + str + "enter keyguard: first show card finished, hasEnterKeyguard = false");
                    }
                    capsuleNotificationDataController.hasEnterKeyguard = false;
                }
                mutex2 = mutex4;
            }
            capsuleNotificationDataController.lastAggregateData = capsuleNotificationInnerData;
            Unit unit18 = Unit.INSTANCE;
            mutex2.unlock((Object) null);
            return unit18;
        } catch (Throwable th8) {
            th = th8;
            mutex2 = mutex;
        }
    }

    public final Object refreshCapsuleDataByMedia(Function1 function1, Continuation continuation) throws Throwable {
        Object objRefreshCapsuleData = refreshCapsuleData(this.lastEntries, RefreshReason.MEDIA_CHANGE, function1, continuation);
        return objRefreshCapsuleData == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objRefreshCapsuleData : Unit.INSTANCE;
    }

    public final void registerSystemStateListeners() {
        if (this.isSystemStateListenersRegistered) {
            return;
        }
        this.isSystemStateListenersRegistered = true;
        BuildersKt.launch$default(this.mainScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new C01921(null), 2);
        ConfigurationController configurationController = (ConfigurationController) this.configurationController.get();
        if (configurationController != null) {
            configurationController.addCallback(this.configurationCallback);
        }
        DumpManager dumpManager = (DumpManager) this.dumpManager.get();
        if (dumpManager != null) {
            dumpManager.registerDumpable(this);
        }
        NotifPipeline notifPipeline = (NotifPipeline) this.pipeline.get();
        if (notifPipeline != null) {
            notifPipeline.addCollectionListener(this);
        }
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateController) this.keyguardStateController.get();
        if (keyguardStateControllerImpl != null) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2();
            anonymousClass2.this$0 = this;
            VarHandle.storeStoreFence();
            keyguardStateControllerImpl.addCallback(anonymousClass2);
        }
    }

    public final void showAggregate(boolean z) {
        this.isNewNotificationPinned = false;
        CapsuleNotificationInnerData capsuleNotificationInnerData = this.lastAggregateData;
        if (capsuleNotificationInnerData != null) {
            getOrCreateCurrentContainer().showAggregate(capsuleNotificationInnerData, z);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class NotificationIconData {
        private final NotificationEntry entry;
        private final Drawable icon;
        private final Integer iconColor;
        public final boolean isCloned;
        public final boolean isMediaPlayer;
        private final String key;
        private final String packageName;
        private final Icon smallIcon;

        public /* synthetic */ NotificationIconData(String str, String str2, Drawable drawable, Icon icon, boolean z, Integer num, NotificationEntry notificationEntry, int i) {
            this(str, str2, drawable, icon, (i & 16) != 0 ? false : z, (i & 32) != 0 ? null : num, notificationEntry, false);
        }

        public final String component1() {
            return this.key;
        }

        public final String component2() {
            return this.packageName;
        }

        public final Drawable component3() {
            return this.icon;
        }

        public final Icon component4() {
            return this.smallIcon;
        }

        public final Integer component6() {
            return this.iconColor;
        }

        public final NotificationEntry component7() {
            return this.entry;
        }

        public final NotificationIconData copy(String str, String str2, Drawable drawable, Icon icon, boolean z, Integer num, NotificationEntry notificationEntry, boolean z2) {
            return new NotificationIconData(str, str2, drawable, icon, z, num, notificationEntry, z2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof NotificationIconData)) {
                return false;
            }
            NotificationIconData notificationIconData = (NotificationIconData) obj;
            return Intrinsics.areEqual(this.key, notificationIconData.key) && Intrinsics.areEqual(this.packageName, notificationIconData.packageName) && Intrinsics.areEqual(this.icon, notificationIconData.icon) && Intrinsics.areEqual(this.smallIcon, notificationIconData.smallIcon) && this.isCloned == notificationIconData.isCloned && Intrinsics.areEqual(this.iconColor, notificationIconData.iconColor) && Intrinsics.areEqual(this.entry, notificationIconData.entry) && this.isMediaPlayer == notificationIconData.isMediaPlayer;
        }

        public final NotificationEntry getEntry() {
            return this.entry;
        }

        public final Drawable getIcon() {
            return this.icon;
        }

        public final Integer getIconColor() {
            return this.iconColor;
        }

        public final String getKey() {
            return this.key;
        }

        public final String getPackageName() {
            return this.packageName;
        }

        public final Icon getSmallIcon() {
            return this.smallIcon;
        }

        public final int hashCode() {
            int iHashCode = this.key.hashCode() * 31;
            String str = this.packageName;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            Drawable drawable = this.icon;
            int iHashCode3 = (iHashCode2 + (drawable == null ? 0 : drawable.hashCode())) * 31;
            Icon icon = this.smallIcon;
            int iM = ChangeSize$.ExternalSyntheticOutline0.m((iHashCode3 + (icon == null ? 0 : icon.hashCode())) * 31, 31, this.isCloned);
            Integer num = this.iconColor;
            int iHashCode4 = (iM + (num == null ? 0 : num.hashCode())) * 31;
            NotificationEntry notificationEntry = this.entry;
            return Boolean.hashCode(this.isMediaPlayer) + ((iHashCode4 + (notificationEntry != null ? notificationEntry.hashCode() : 0)) * 31);
        }

        public String toString() {
            String str = this.key;
            String str2 = this.packageName;
            Drawable drawable = this.icon;
            Icon icon = this.smallIcon;
            Integer num = this.iconColor;
            NotificationEntry notificationEntry = this.entry;
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("NotificationIconData(key=", str, ", packageName=", str2, ", icon=");
            sbM.append(drawable);
            sbM.append(", smallIcon=");
            sbM.append(icon);
            sbM.append(", isCloned=");
            sbM.append(this.isCloned);
            sbM.append(", iconColor=");
            sbM.append(num);
            sbM.append(", entry=");
            sbM.append(notificationEntry);
            sbM.append(", isMediaPlayer=");
            sbM.append(this.isMediaPlayer);
            sbM.append(")");
            return sbM.toString();
        }

        public NotificationIconData(String str, String str2, Drawable drawable, Icon icon, boolean z, Integer num, NotificationEntry notificationEntry, boolean z2) {
            this.key = str;
            this.packageName = str2;
            this.icon = drawable;
            this.smallIcon = icon;
            this.isCloned = z;
            this.iconColor = num;
            this.entry = notificationEntry;
            this.isMediaPlayer = z2;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CapsuleNotificationInnerData {
        private final Drawable avatarIcon;
        private final Integer badgeCount;
        private final CardType cardType;
        private final String content;
        private final Integer iconColor;
        private final NotificationIconData iconData;
        public final int mediaCount;
        public final int notifCount;
        private final List<CapsuleNotificationReportData> reportDataList;
        private final String title;

        public /* synthetic */ CapsuleNotificationInnerData(NotificationIconData notificationIconData, String str, String str2, CardType cardType, Integer num, Drawable drawable, Integer num2, int i, int i2, List list, int i3) {
            this(notificationIconData, str, str2, cardType, (i3 & 16) != 0 ? null : num, (i3 & 32) != 0 ? null : drawable, (i3 & 64) != 0 ? null : num2, (i3 & 128) != 0 ? 1 : i, (i3 & 256) != 0 ? 0 : i2, list);
        }

        public final NotificationIconData component1() {
            return this.iconData;
        }

        public final List<CapsuleNotificationReportData> component10() {
            return this.reportDataList;
        }

        public final String component2() {
            return this.title;
        }

        public final String component3() {
            return this.content;
        }

        public final CardType component4() {
            return this.cardType;
        }

        public final Integer component5() {
            return this.badgeCount;
        }

        public final Drawable component6() {
            return this.avatarIcon;
        }

        public final Integer component7() {
            return this.iconColor;
        }

        public final CapsuleNotificationInnerData copy(NotificationIconData notificationIconData, String str, String str2, CardType cardType, Integer num, Drawable drawable, Integer num2, int i, int i2, List<CapsuleNotificationReportData> list) {
            return new CapsuleNotificationInnerData(notificationIconData, str, str2, cardType, num, drawable, num2, i, i2, list);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CapsuleNotificationInnerData)) {
                return false;
            }
            CapsuleNotificationInnerData capsuleNotificationInnerData = (CapsuleNotificationInnerData) obj;
            return Intrinsics.areEqual(this.iconData, capsuleNotificationInnerData.iconData) && Intrinsics.areEqual(this.title, capsuleNotificationInnerData.title) && Intrinsics.areEqual(this.content, capsuleNotificationInnerData.content) && this.cardType == capsuleNotificationInnerData.cardType && Intrinsics.areEqual(this.badgeCount, capsuleNotificationInnerData.badgeCount) && Intrinsics.areEqual(this.avatarIcon, capsuleNotificationInnerData.avatarIcon) && Intrinsics.areEqual(this.iconColor, capsuleNotificationInnerData.iconColor) && this.notifCount == capsuleNotificationInnerData.notifCount && this.mediaCount == capsuleNotificationInnerData.mediaCount && Intrinsics.areEqual(this.reportDataList, capsuleNotificationInnerData.reportDataList);
        }

        public final Drawable getAvatarIcon() {
            return this.avatarIcon;
        }

        public final Integer getBadgeCount() {
            return this.badgeCount;
        }

        public final CardType getCardType() {
            return this.cardType;
        }

        public final String getContent() {
            return this.content;
        }

        public final Integer getIconColor() {
            return this.iconColor;
        }

        public final NotificationIconData getIconData() {
            return this.iconData;
        }

        public final int getMediaCount() {
            return this.mediaCount;
        }

        public final int getNotifCount() {
            return this.notifCount;
        }

        public final List<CapsuleNotificationReportData> getReportDataList() {
            return this.reportDataList;
        }

        public final String getTitle() {
            return this.title;
        }

        public final boolean hasSameContentAs(CapsuleNotificationInnerData capsuleNotificationInnerData) {
            return Intrinsics.areEqual(this.title, capsuleNotificationInnerData.title) && Intrinsics.areEqual(this.content, capsuleNotificationInnerData.content) && hasSameStructAs(capsuleNotificationInnerData);
        }

        public final boolean hasSameStructAs(CapsuleNotificationInnerData capsuleNotificationInnerData) {
            NotificationIconData notificationIconData = this.iconData;
            String key = notificationIconData != null ? notificationIconData.getKey() : null;
            NotificationIconData notificationIconData2 = capsuleNotificationInnerData.iconData;
            return Intrinsics.areEqual(key, notificationIconData2 != null ? notificationIconData2.getKey() : null) && this.notifCount == capsuleNotificationInnerData.notifCount && this.mediaCount == capsuleNotificationInnerData.mediaCount;
        }

        public final int hashCode() {
            NotificationIconData notificationIconData = this.iconData;
            int iHashCode = (this.cardType.hashCode() + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m((notificationIconData == null ? 0 : notificationIconData.hashCode()) * 31, 31, this.title), 31, this.content)) * 31;
            Integer num = this.badgeCount;
            int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
            Drawable drawable = this.avatarIcon;
            int iHashCode3 = (iHashCode2 + (drawable == null ? 0 : drawable.hashCode())) * 31;
            Integer num2 = this.iconColor;
            int iM = MonoSpline$.ExternalSyntheticOutline0.m(this.mediaCount, MonoSpline$.ExternalSyntheticOutline0.m(this.notifCount, (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31, 31), 31);
            List<CapsuleNotificationReportData> list = this.reportDataList;
            return iM + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "CapsuleNotificationInnerData(iconData=" + this.iconData + ", title=" + this.title + ", content=" + this.content + ", cardType=" + this.cardType + ", badgeCount=" + this.badgeCount + ", avatarIcon=" + this.avatarIcon + ", iconColor=" + this.iconColor + ", notifCount=" + this.notifCount + ", mediaCount=" + this.mediaCount + ", reportDataList=" + this.reportDataList + ")";
        }

        public CapsuleNotificationInnerData(NotificationIconData notificationIconData, String str, String str2, CardType cardType, Integer num, Drawable drawable, Integer num2, int i, int i2, List<CapsuleNotificationReportData> list) {
            this.iconData = notificationIconData;
            this.title = str;
            this.content = str2;
            this.cardType = cardType;
            this.badgeCount = num;
            this.avatarIcon = drawable;
            this.iconColor = num2;
            this.notifCount = i;
            this.mediaCount = i2;
            this.reportDataList = list;
        }
    }
}
