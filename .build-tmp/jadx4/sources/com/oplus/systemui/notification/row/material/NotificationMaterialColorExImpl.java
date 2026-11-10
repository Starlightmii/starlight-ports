package com.oplus.systemui.notification.row.material;

import android.content.Context;
import android.util.Log;
import androidx.tracing.Trace;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.OplusExpandableViewEx;
import com.android.systemui.statusbar.notification.stack.AmbientState;
import com.android.systemui.statusbar.notification.stack.OplusExpandableViewStateEx;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.notification.aod.OplusPanoramicAodCoreStartable;
import com.oplus.systemui.notification.aod.notification.NotificationAodTransition;
import com.oplus.systemui.notification.headsup.windowframe.HeadsUpLayoutController;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationMaterialColorExImpl extends NotificationMaterialColorEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationMaterialColorExImpl";
    private final MutableStateFlow _blurMode;
    private final MutableStateFlow _currentMaterialColor;
    private final MutableStateFlow _isCurrentMaterialColor;
    private final MutableStateFlow _isKeyguardReverseColor;
    private final StateFlow blurMode;
    private final Context context;
    private final CoroutineScope coroutineScope;
    private final StateFlow currentMaterialColor;
    private final Lazy headsUpLayoutController;
    private final StateFlow isKeyguardReverseColor;
    private final StateFlow isMaterialColor;
    private final MaterialColorApplier materialColorApplier;
    private final NotificationMaterialColorModeController materialColorModeController;
    private final Lazy materialUiModeController;
    private final Lazy notificationAodTransition;
    private final Lazy panoramicAodRepository;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.material.NotificationMaterialColorExImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.row.material.NotificationMaterialColorExImpl$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C00311 implements FlowCollector {
            public final /* synthetic */ int $r8$classId;
            public /* synthetic */ NotificationMaterialColorExImpl this$0;

            public /* synthetic */ C00311(int i) {
                this.$r8$classId = i;
            }

            public final Object emit(Object obj, Continuation continuation) {
                switch (this.$r8$classId) {
                    case 0:
                        MaterialColorMode materialColorMode = (MaterialColorMode) obj;
                        NotificationMaterialColorExImpl notificationMaterialColorExImpl = this.this$0;
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                            Log.d("SystemUi--Notification", "NotificationMaterialColorExImpl-->".concat("cur color mode=" + materialColorMode));
                        }
                        notificationMaterialColorExImpl._currentMaterialColor.setValue(materialColorMode);
                        notificationMaterialColorExImpl._isCurrentMaterialColor.setValue(Boxing.boxBoolean(materialColorMode == MaterialColorMode.MATERIAL));
                        break;
                    case 1:
                        boolean zBooleanValue = ((Boolean) obj).booleanValue();
                        NotificationMaterialColorExImpl notificationMaterialColorExImpl2 = this.this$0;
                        if (zBooleanValue != ((Boolean) notificationMaterialColorExImpl2._isKeyguardReverseColor.getValue()).booleanValue()) {
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                Log.d("SystemUi--Notification", "NotificationMaterialColorExImpl-->".concat("isKeyguardReverseColor=" + zBooleanValue + ", useReverseColor=" + notificationMaterialColorExImpl2.materialColorModeController.getUseReverseColor().getValue() + ", state=" + notificationMaterialColorExImpl2.materialColorModeController.getStatusBarState().getValue()));
                            }
                            notificationMaterialColorExImpl2._isKeyguardReverseColor.setValue(Boxing.boxBoolean(zBooleanValue));
                        }
                        break;
                    default:
                        NotificationBlurMode notificationBlurMode = (NotificationBlurMode) obj;
                        NotificationMaterialColorExImpl notificationMaterialColorExImpl3 = this.this$0;
                        if (notificationBlurMode != notificationMaterialColorExImpl3.getBlurMode().getValue()) {
                            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                                Log.d("SystemUi--Notification", "NotificationMaterialColorExImpl-->".concat("blurMode cur=" + notificationBlurMode + ", last=" + notificationMaterialColorExImpl3.getBlurMode().getValue()));
                            }
                            notificationMaterialColorExImpl3._blurMode.setValue(notificationBlurMode);
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return NotificationMaterialColorExImpl.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow currentMaterialColorMode = NotificationMaterialColorExImpl.this.materialColorModeController.getCurrentMaterialColorMode();
                NotificationMaterialColorExImpl notificationMaterialColorExImpl = NotificationMaterialColorExImpl.this;
                C00311 c00311 = new C00311(0);
                c00311.this$0 = notificationMaterialColorExImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (currentMaterialColorMode.collect(c00311, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.material.NotificationMaterialColorExImpl$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.row.material.NotificationMaterialColorExImpl$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function3 {
            /* synthetic */ int I$0;
            /* synthetic */ boolean Z$0;
            int label;

            public final Object invoke(Object obj, Object obj2, Object obj3) {
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                int iIntValue = ((Number) obj2).intValue();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(3, (Continuation) obj3);
                anonymousClass1.Z$0 = zBooleanValue;
                anonymousClass1.I$0 = iIntValue;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:11:0x0017  */
            public final Object invokeSuspend(Object obj) {
                boolean z;
                boolean z2 = this.Z$0;
                int i = this.I$0;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                if (z2) {
                    z = true;
                    if (i != 1 && i != 2) {
                        z = false;
                    }
                } else {
                    z = false;
                }
                return Boxing.boxBoolean(z);
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return NotificationMaterialColorExImpl.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            int i2 = 1;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.combine(NotificationMaterialColorExImpl.this.materialColorModeController.getUseReverseColor(), NotificationMaterialColorExImpl.this.materialColorModeController.getStatusBarState(), new AnonymousClass1(3, null)));
                NotificationMaterialColorExImpl notificationMaterialColorExImpl = NotificationMaterialColorExImpl.this;
                AnonymousClass1.C00311 c00311 = new AnonymousClass1.C00311(i2);
                c00311.this$0 = notificationMaterialColorExImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (flowDistinctUntilChanged.collect(c00311, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.notification.row.material.NotificationMaterialColorExImpl$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass3(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return NotificationMaterialColorExImpl.this.new AnonymousClass3(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow blurMode = NotificationMaterialColorExImpl.this.materialColorModeController.getBlurMode();
                NotificationMaterialColorExImpl notificationMaterialColorExImpl = NotificationMaterialColorExImpl.this;
                AnonymousClass1.C00311 c00311 = new AnonymousClass1.C00311(2);
                c00311.this$0 = notificationMaterialColorExImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (blurMode.collect(c00311, this) == coroutine_suspended) {
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

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final boolean isSupported(Context context) {
            if (GaussBlurUtils.isGaussBlurDisabled()) {
                return false;
            }
            ScrimControllerEx.Companion.getClass();
            return !ScrimControllerEx.isThirdTheme && ScrimUtil.isMiddleOrHighAnimLevel(context);
        }
    }

    public NotificationMaterialColorExImpl(Context context, NotificationMaterialColorModeController notificationMaterialColorModeController, MaterialColorApplier materialColorApplier, CoroutineScope coroutineScope, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4) {
        this.context = context;
        this.materialColorModeController = notificationMaterialColorModeController;
        this.materialColorApplier = materialColorApplier;
        this.coroutineScope = coroutineScope;
        this.panoramicAodRepository = lazy;
        this.notificationAodTransition = lazy2;
        this.materialUiModeController = lazy3;
        this.headsUpLayoutController = lazy4;
        Boolean bool = Boolean.FALSE;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._isCurrentMaterialColor = MutableStateFlow;
        this.isMaterialColor = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(MaterialColorMode.UNINITIATED);
        this._currentMaterialColor = MutableStateFlow2;
        this.currentMaterialColor = FlowKt.asStateFlow(MutableStateFlow2);
        MutableStateFlow MutableStateFlow3 = StateFlowKt.MutableStateFlow(bool);
        this._isKeyguardReverseColor = MutableStateFlow3;
        this.isKeyguardReverseColor = FlowKt.asStateFlow(MutableStateFlow3);
        MutableStateFlow MutableStateFlow4 = StateFlowKt.MutableStateFlow(NotificationBlurMode.UNINITIATED);
        this._blurMode = MutableStateFlow4;
        this.blurMode = FlowKt.asStateFlow(MutableStateFlow4);
        BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new AnonymousClass1(null), 2);
        BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new AnonymousClass2(null), 2);
        BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new AnonymousClass3(null), 2);
    }

    public static final boolean isSupported(Context context) {
        return Companion.isSupported(context);
    }

    @Override // com.oplus.systemui.notification.row.material.NotificationMaterialColorEx
    public void applyColorModeForHeadsUp(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            String key = expandableNotificationRow.getKey();
            OplusExpandableViewEx oplusExpandableViewEx = ((ExpandableView) expandableNotificationRow).mExpandableViewEx;
            MaterialColorMode materialColorMode = oplusExpandableViewEx != null ? oplusExpandableViewEx.getMaterialColorMode() : null;
            OplusExpandableViewEx oplusExpandableViewEx2 = ((ExpandableView) expandableNotificationRow).mExpandableViewEx;
            Log.d("SystemUi--Notification", "NotificationMaterialColorExImpl-->".concat("applyColorModeForHeadsUp key=" + key + ", color mode=" + materialColorMode + ", show=" + (oplusExpandableViewEx2 != null ? oplusExpandableViewEx2.getNotificationBlurMode() : null)));
        }
        ((HeadsUpLayoutController) this.headsUpLayoutController.get()).notifyMaterialColorChanged();
    }

    @Override // com.oplus.systemui.notification.row.material.NotificationMaterialColorEx
    public void applyColorModeForRow(ExpandableNotificationRow expandableNotificationRow, MaterialColorMode materialColorMode) {
        float panoramicAodProgress = ((OplusPanoramicAodCoreStartable) this.panoramicAodRepository.get()).getPanoramicAodProgress();
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "applyColorModeForRow key=" + expandableNotificationRow.getKey() + ", mode=" + materialColorMode + ",panoramicAodProgress=" + panoramicAodProgress, null, 8);
        Trace.beginSection("NotificationMaterialColorExImpl applyColorModeForRow");
        this.materialColorApplier.applyColorMode(this.context, expandableNotificationRow, materialColorMode);
        if (panoramicAodProgress != 0.0f) {
            ((NotificationAodTransition) this.notificationAodTransition.get()).notifyProgressChanged(expandableNotificationRow, panoramicAodProgress, "afterApplyColorMode");
        }
        android.os.Trace.endSection();
    }

    @Override // com.oplus.systemui.notification.row.material.NotificationMaterialColorEx
    public StateFlow getBlurMode() {
        return this.blurMode;
    }

    @Override // com.oplus.systemui.notification.row.material.NotificationMaterialColorEx
    public NotificationBlurMode getBlurModeForRow(ExpandableNotificationRow expandableNotificationRow, boolean z) {
        return this.materialColorModeController.getMaterialBlurModeForRow(expandableNotificationRow, z);
    }

    @Override // com.oplus.systemui.notification.row.material.NotificationMaterialColorEx
    public MaterialColorMode getColorModeForRow(ExpandableNotificationRow expandableNotificationRow) {
        return this.materialColorModeController.getMaterialColorModeForRow(expandableNotificationRow, false);
    }

    @Override // com.oplus.systemui.notification.row.material.NotificationMaterialColorEx
    public StateFlow getCurrentMaterialColor() {
        return this.currentMaterialColor;
    }

    @Override // com.oplus.systemui.notification.row.material.NotificationMaterialColorEx
    public StateFlow isKeyguardReverseColor() {
        return this.isKeyguardReverseColor;
    }

    @Override // com.oplus.systemui.notification.row.material.NotificationMaterialColorEx
    public StateFlow isMaterialColor() {
        return this.isMaterialColor;
    }

    @Override // com.oplus.systemui.notification.row.material.NotificationMaterialColorEx
    public void updateColorOnModeChanged(ExpandableNotificationRow expandableNotificationRow) {
        ((NotificationMaterialUiModeController) this.materialUiModeController.get()).updateColorOnModeChanged(this.context, expandableNotificationRow, expandableNotificationRow.mRowEx.getNotificationBlurMode());
    }

    @Override // com.oplus.systemui.notification.row.material.NotificationMaterialColorEx
    public void updateNotificationColorMode(List<? extends ExpandableView> list, AmbientState ambientState) {
        List<ExpandableNotificationRow> attachedChildren;
        MaterialColorMode materialColorMode = (MaterialColorMode) this.materialColorModeController.getCurrentMaterialColorMode().getValue();
        NotificationBlurMode notificationBlurMode = (NotificationBlurMode) getBlurMode().getValue();
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableView) it.next();
                if (expandableNotificationRow instanceof ExpandableNotificationRow) {
                    ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
                    OplusExpandableViewStateEx oplusExpandableViewStateEx = expandableNotificationRow2.getViewState().mExpandableViewStateEx;
                    if (oplusExpandableViewStateEx != null && (oplusExpandableViewStateEx.getNotificationBlurMode() != notificationBlurMode || oplusExpandableViewStateEx.getMaterialColorMode() == MaterialColorMode.UNINITIATED || (oplusExpandableViewStateEx.getMaterialColorMode() != materialColorMode && oplusExpandableViewStateEx.getMaterialColorMode() != MaterialColorMode.COLOR_NOTIFICATION))) {
                        MaterialColorMode materialColorModeForRow = this.materialColorModeController.getMaterialColorModeForRow(expandableNotificationRow2, false);
                        if (expandableNotificationRow2.mIsSummaryWithChildren && (attachedChildren = expandableNotificationRow2.getAttachedChildren()) != null) {
                            for (ExpandableNotificationRow expandableNotificationRow3 : attachedChildren) {
                                OplusExpandableViewStateEx oplusExpandableViewStateEx2 = expandableNotificationRow3.getViewState().mExpandableViewStateEx;
                                if (oplusExpandableViewStateEx2 != null) {
                                    oplusExpandableViewStateEx2.setMaterialColorMode(materialColorModeForRow);
                                }
                                OplusExpandableViewStateEx oplusExpandableViewStateEx3 = expandableNotificationRow3.getViewState().mExpandableViewStateEx;
                                if (oplusExpandableViewStateEx3 != null) {
                                    oplusExpandableViewStateEx3.setNotificationBlurMode(notificationBlurMode);
                                }
                            }
                        }
                        oplusExpandableViewStateEx.setMaterialColorMode(materialColorModeForRow);
                        oplusExpandableViewStateEx.setNotificationBlurMode(notificationBlurMode);
                    }
                }
            }
        }
    }

    @Override // com.oplus.systemui.notification.row.material.NotificationMaterialColorEx
    public void updateNotificationColorMode(ExpandableNotificationRow expandableNotificationRow) {
        OplusExpandableViewStateEx oplusExpandableViewStateEx = expandableNotificationRow.getViewState().mExpandableViewStateEx;
        if (oplusExpandableViewStateEx == null) {
            return;
        }
        MaterialColorMode materialColorModeForRow = this.materialColorModeController.getMaterialColorModeForRow(expandableNotificationRow, false);
        if (oplusExpandableViewStateEx.getMaterialColorMode() != materialColorModeForRow) {
            oplusExpandableViewStateEx.setMaterialColorMode(materialColorModeForRow);
        }
        NotificationBlurMode materialBlurModeForRow = this.materialColorModeController.getMaterialBlurModeForRow(expandableNotificationRow, false);
        if (oplusExpandableViewStateEx.getNotificationBlurMode() != materialBlurModeForRow) {
            oplusExpandableViewStateEx.setNotificationBlurMode(materialBlurModeForRow);
        }
    }
}
