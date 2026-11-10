package com.oplus.systemui.notification.row.material;

import android.R;
import android.app.Notification;
import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.ViewGroup;
import com.android.internal.widget.CallLayout;
import com.android.internal.widget.ConversationLayout;
import com.android.systemui.InitController;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.collection.EntryAdapter;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainerExt;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.oplus.systemui.notification.headsup.windowframe.OplusHeadsUpNotificationInteractor;
import com.oplus.systemui.utils.OplusContrastColorUtil;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMaterialUiModeController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationMaterialUiModeController";
    private final CoroutineScope coroutineScope;
    private final InitController initController;
    private final Lazy materialColorModeController;
    private final Lazy notificationMaterialColorEx;
    private final Lazy nssl;
    private final Lazy oplusHeadsUpInteractor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.material.NotificationMaterialUiModeController$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ NotificationMaterialUiModeController this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.row.material.NotificationMaterialUiModeController$start$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00341 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ NotificationMaterialUiModeController this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.notification.row.material.NotificationMaterialUiModeController$start$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00351 extends SuspendLambda implements Function3 {
                /* synthetic */ boolean Z$0;
                /* synthetic */ boolean Z$1;
                int label;
                final /* synthetic */ NotificationMaterialUiModeController this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00351(NotificationMaterialUiModeController notificationMaterialUiModeController, Continuation continuation) {
                    super(3, continuation);
                    this.this$0 = notificationMaterialUiModeController;
                }

                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    boolean zBooleanValue = ((Boolean) obj).booleanValue();
                    boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
                    C00351 c00351 = new C00351(this.this$0, (Continuation) obj3);
                    c00351.Z$0 = zBooleanValue;
                    c00351.Z$1 = zBooleanValue2;
                    return c00351.invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    boolean z = this.Z$0;
                    boolean z2 = this.Z$1;
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                        Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("darkMode=", ", isKeyguardReverseColor=", "NotificationMaterialUiModeController-->", z, z2));
                    }
                    if (((NotificationStackScrollLayout) this.this$0.nssl.get()).isAttachedToWindow()) {
                        NotificationMaterialUiModeController.access$notifyUiModeChanged(this.this$0);
                    }
                    NotificationMaterialUiModeController.access$updateHeadsUpUiMode(this.this$0);
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00341(NotificationMaterialUiModeController notificationMaterialUiModeController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = notificationMaterialUiModeController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00341(this.this$0, continuation);
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow flowCombine = FlowKt.combine(((NotificationMaterialColorModeController) this.this$0.materialColorModeController.get()).getDarkMode(), ((NotificationMaterialColorEx) this.this$0.notificationMaterialColorEx.get()).isKeyguardReverseColor(), new C00351(this.this$0, null));
                    this.label = 1;
                    if (FlowKt.collect(flowCombine, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            BuildersKt.launch$default(this.this$0.coroutineScope, Dispatchers.getMain(), (CoroutineStart) null, new C00341(this.this$0, null), 2);
        }
    }

    public NotificationMaterialUiModeController(Lazy lazy, Lazy lazy2, CoroutineScope coroutineScope, InitController initController, Lazy lazy3, Lazy lazy4) {
        this.materialColorModeController = lazy;
        this.nssl = lazy2;
        this.coroutineScope = coroutineScope;
        this.initController = initController;
        this.oplusHeadsUpInteractor = lazy3;
        this.notificationMaterialColorEx = lazy4;
    }

    public static final void access$notifyUiModeChanged(NotificationMaterialUiModeController notificationMaterialUiModeController) {
        List<ExpandableNotificationRow> attachedChildren;
        for (ExpandableNotificationRow expandableNotificationRow : ConvenienceExtensionsKt.getChildren((ViewGroup) notificationMaterialUiModeController.nssl.get())) {
            if (expandableNotificationRow instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
                if (expandableNotificationRow2.mIsSummaryWithChildren) {
                    NotificationChildrenContainer childrenContainer = expandableNotificationRow2.getChildrenContainer();
                    if (childrenContainer != null && (attachedChildren = childrenContainer.getAttachedChildren()) != null) {
                        for (ExpandableNotificationRow expandableNotificationRow3 : attachedChildren) {
                            NotificationEntry entry = expandableNotificationRow3.getEntry();
                            if (entry != null && entry.containsCustomViews()) {
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("dispatchUiModeChanged: key=", expandableNotificationRow3.getKey(), "NotificationMaterialUiModeController-->", "SystemUi--Notification");
                                }
                                expandableNotificationRow3.onUiModeChanged();
                            }
                        }
                    }
                } else {
                    NotificationEntry entry2 = expandableNotificationRow2.getEntry();
                    if (entry2 != null && entry2.containsCustomViews()) {
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("dispatchUiModeChanged: key=", expandableNotificationRow2.getKey(), "NotificationMaterialUiModeController-->", "SystemUi--Notification");
                        }
                        expandableNotificationRow2.onUiModeChanged();
                    }
                }
            }
        }
    }

    public static final void access$updateHeadsUpUiMode(NotificationMaterialUiModeController notificationMaterialUiModeController) {
        ExpandableNotificationRow row;
        NotificationEntry notificationEntryHeadsUpEntry = ((OplusHeadsUpNotificationInteractor) notificationMaterialUiModeController.oplusHeadsUpInteractor.get()).headsUpEntry();
        if (notificationEntryHeadsUpEntry == null || (row = notificationEntryHeadsUpEntry.getRow()) == null) {
            return;
        }
        NotificationEntry entry = row.getEntry();
        if (entry == null || !entry.containsCustomViews()) {
            ((NotificationMaterialColorEx) notificationMaterialUiModeController.notificationMaterialColorEx.get()).applyColorModeForHeadsUp(row, true);
        } else {
            row.onUiModeChanged();
        }
    }

    public final void start() {
        InitController initController = this.initController;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        initController.addPostInitTask(anonymousClass1);
    }

    public final void updateColorOnModeChanged(Context context, ExpandableNotificationRow expandableNotificationRow, NotificationBlurMode notificationBlurMode) {
        int notificationIconBgColor;
        StatusBarNotification sbn;
        NotificationChildrenContainer childrenContainer;
        NotificationChildrenContainerExt ext;
        if (expandableNotificationRow.mIsSummaryWithChildren && (childrenContainer = expandableNotificationRow.getChildrenContainer()) != null && (ext = childrenContainer.getExt()) != null) {
            ext.updatePillBgAndNumberColor();
        }
        notificationBlurMode.getClass();
        if (notificationBlurMode == NotificationBlurMode.HEADS_UP_MATERIAL_LIGHT || notificationBlurMode == NotificationBlurMode.HEADS_UP_MATERIAL_DARK) {
            notificationIconBgColor = NotificationMaterialColorConfig.INSTANCE.getNotificationIconBgColor();
        } else {
            OplusContrastColorUtil oplusContrastColorUtil = OplusContrastColorUtil.getInstance(context);
            EntryAdapter entryAdapter = expandableNotificationRow.getEntryAdapter();
            Notification notification = (entryAdapter == null || (sbn = entryAdapter.getSbn()) == null) ? null : sbn.getNotification();
            oplusContrastColorUtil.getClass();
            if (context == null) {
                notificationIconBgColor = 0;
            } else if (notification == null || !notification.isColorized()) {
                notificationIconBgColor = context.getColor(R.color.material_deep_teal_500);
            } else {
                notificationIconBgColor = notification.color;
                if (notificationIconBgColor == 0) {
                    notificationIconBgColor = context.getColor(R.color.materialColorTextSecondaryAndTertiaryInverseDisabled);
                }
            }
        }
        NotificationContentView[] layouts = expandableNotificationRow.getLayouts();
        if (layouts != null) {
            for (NotificationContentView notificationContentView : layouts) {
                for (ConversationLayout conversationLayout : notificationContentView.getAllViews()) {
                    if (conversationLayout instanceof ConversationLayout) {
                        conversationLayout.setNotificationBackgroundColor(notificationIconBgColor);
                    } else if (conversationLayout instanceof CallLayout) {
                        ((CallLayout) conversationLayout).setNotificationBackgroundColor(notificationIconBgColor);
                    }
                }
            }
        }
    }
}
