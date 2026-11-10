package com.oplus.systemui.statusbar;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.android.app.displaylib.PerDisplayRepository;
import com.android.systemui.display.dagger.ReferenceSysUIDisplaySubcomponent;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.plugins.statusbar.SysUiStateController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.qs.observer.SimpleModeObserver;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.scene.OplusSceneModeObserver;
import com.oplus.systemui.seamless.FakeBinder;
import com.oplus.systemui.statusbar.observer.GameModeObserver;
import com.oplus.systemui.statusbar.seeding.OneHandedObserver;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SysUiStateControllerImpl implements SysUiStateController {
    public static final Companion Companion = new Companion();
    private static final String TAG = "SysUiStateControllerImpl";
    private final MutableStateFlow _isKeyguardShowing;
    private final MutableStateFlow _isPanelExpanded;
    private final Context context;
    private final PerDisplayRepository displaySubComponentRepository;
    private final Lazy fakeBinder;
    private final SysUiStateControllerImpl$gameModeObserver$1 gameModeObserver;
    public boolean isBrickMode;
    public boolean isChildrenMode;
    public boolean isDeepBrickMode;
    public boolean isFullscreen;
    public boolean isGameMode;
    private final StateFlow isKeyguardShowing;
    public boolean isLightBrickMode;
    public boolean isOneHandedMode;
    private final StateFlow isPanelExpanded;
    public boolean isSimpleMode;
    public boolean isSuperPowerMode;
    private final KeyguardStateController.Callback keyguardStateCallback;
    private final KeyguardStateController keyguardStateController;
    private final List<SysUiStateController.ISysUiStateListener> listeners = new CopyOnWriteArrayList();
    public boolean notifiedStatusBarOnly;
    public float panelExpandFraction;
    public boolean qsExpanded;
    private final CoroutineScope scope;
    public boolean separateNtExpand;
    public float separateNtExpandFraction;
    public boolean separateQSExpand;
    public float separateQSExpandFraction;
    private final SysUiStateControllerImpl$simpleModeObserver$1 simpleModeObserver;
    private final SysuiStatusBarStateController statusBarStateController;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.SysUiStateControllerImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements StatusBarStateController.StateListener {
        public /* synthetic */ SysUiStateControllerImpl this$0;

        public final void onStateChanged(int i) {
            Object obj;
            for (SysUiStateController.ISysUiStateListener iSysUiStateListener : this.this$0.listeners) {
                try {
                    iSysUiStateListener.onStatusBarStateChanged(i);
                    obj = Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                Throwable th2 = Result.exceptionOrNull-impl(obj);
                if (th2 != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "SysUiStateControllerImpl-->".concat("onStatusBarStateChanged failed, listener=" + iSysUiStateListener + " reason=" + th2.getMessage()));
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.SysUiStateControllerImpl$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements IObserverListener {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ SysUiStateControllerImpl this$0;

        public /* synthetic */ AnonymousClass2(int i) {
            this.$r8$classId = i;
        }

        @Override // com.oplusos.systemui.common.observer.IObserverListener
        public final void onChange(boolean z) {
            switch (this.$r8$classId) {
                case 0:
                    SysUiStateControllerImpl sysUiStateControllerImpl = this.this$0;
                    sysUiStateControllerImpl.setChildrenMode(ChildrenModeObserver.getInstance().mIsChildrenMode);
                    Iterator it = sysUiStateControllerImpl.listeners.iterator();
                    while (it.hasNext()) {
                        ((SysUiStateController.ISysUiStateListener) it.next()).onChildrenModeChanged(sysUiStateControllerImpl.isChildrenMode);
                    }
                    break;
                case 1:
                    this.this$0.setSuperPowerMode(SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState);
                    List list = this.this$0.listeners;
                    SysUiStateControllerImpl sysUiStateControllerImpl2 = this.this$0;
                    Iterator it2 = list.iterator();
                    while (it2.hasNext()) {
                        ((SysUiStateController.ISysUiStateListener) it2.next()).onSuperPowerModeChanged(sysUiStateControllerImpl2.isSuperPowerMode);
                    }
                    break;
                default:
                    SysUiStateControllerImpl sysUiStateControllerImpl3 = this.this$0;
                    sysUiStateControllerImpl3.setOneHandedMode(OneHandedObserver.Companion.getInstance().getOneHandedEnable());
                    Iterator it3 = sysUiStateControllerImpl3.listeners.iterator();
                    while (it3.hasNext()) {
                        ((SysUiStateController.ISysUiStateListener) it3.next()).onOneHandedModeChanged(sysUiStateControllerImpl3.isOneHandedMode);
                    }
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.SysUiStateControllerImpl$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass4 implements OplusSceneModeObserver.Callback {
        public /* synthetic */ SysUiStateControllerImpl this$0;

        @Override // com.oplus.systemui.scene.OplusSceneModeObserver.Callback
        public final void onBrickModeChanged() {
            SysUiStateControllerImpl sysUiStateControllerImpl = this.this$0;
            sysUiStateControllerImpl.setBrickMode(OplusSceneModeObserver.Companion.getInstance(sysUiStateControllerImpl.context).isInBrickMode);
            Iterator it = sysUiStateControllerImpl.listeners.iterator();
            while (it.hasNext()) {
                ((SysUiStateController.ISysUiStateListener) it.next()).onBrickModeChanged(sysUiStateControllerImpl.isBrickMode);
            }
        }

        @Override // com.oplus.systemui.scene.OplusSceneModeObserver.Callback
        public final void onDeepBrickModeChanged() {
            SysUiStateControllerImpl sysUiStateControllerImpl = this.this$0;
            sysUiStateControllerImpl.setDeepBrickMode(OplusSceneModeObserver.Companion.getInstance(sysUiStateControllerImpl.context).isInDeepBrickMode);
            Iterator it = sysUiStateControllerImpl.listeners.iterator();
            while (it.hasNext()) {
                ((SysUiStateController.ISysUiStateListener) it.next()).onDeepBrickModeChanged(sysUiStateControllerImpl.isDeepBrickMode);
            }
        }

        @Override // com.oplus.systemui.scene.OplusSceneModeObserver.Callback
        public final void onLightBrickModeChanged() {
            SysUiStateControllerImpl sysUiStateControllerImpl = this.this$0;
            sysUiStateControllerImpl.setLightBrickMode(OplusSceneModeObserver.Companion.getInstance(sysUiStateControllerImpl.context).isInLightBrickMode);
            Iterator it = sysUiStateControllerImpl.listeners.iterator();
            while (it.hasNext()) {
                ((SysUiStateController.ISysUiStateListener) it.next()).onLightBrickModeChanged(sysUiStateControllerImpl.isLightBrickMode);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.SysUiStateControllerImpl$6, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass6 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.SysUiStateControllerImpl$6$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 implements FlowCollector {
            public /* synthetic */ SysUiStateControllerImpl this$0;

            public final Object emit(Object obj, Continuation continuation) {
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                SysUiStateControllerImpl sysUiStateControllerImpl = this.this$0;
                sysUiStateControllerImpl.setFullscreen(zBooleanValue);
                Iterator it = sysUiStateControllerImpl.listeners.iterator();
                while (it.hasNext()) {
                    ((SysUiStateController.ISysUiStateListener) it.next()).onFullscreenStateChanged(sysUiStateControllerImpl.isFullscreen);
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass6(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return SysUiStateControllerImpl.this.new AnonymousClass6(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlowIsInFullscreenMode = ((ReferenceSysUIDisplaySubcomponent) SysUiStateControllerImpl.this.displaySubComponentRepository.getOrDefault(0)).getStatusBarModeRepo().isInFullscreenMode();
                SysUiStateControllerImpl sysUiStateControllerImpl = SysUiStateControllerImpl.this;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1();
                anonymousClass1.this$0 = sysUiStateControllerImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (stateFlowIsInFullscreenMode.collect(anonymousClass1, this) == coroutine_suspended) {
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
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oplus.systemui.statusbar.SysUiStateControllerImpl$gameModeObserver$1, com.oplusos.systemui.common.observer.AbstractObserver] */
    /* JADX WARN: Type inference failed for: r7v5, types: [com.oplus.systemui.statusbar.SysUiStateControllerImpl$simpleModeObserver$1, com.oplusos.systemui.common.observer.AbstractObserver] */
    public SysUiStateControllerImpl(CoroutineScope coroutineScope, Context context, PerDisplayRepository perDisplayRepository, KeyguardStateController keyguardStateController, Lazy lazy, SysuiStatusBarStateController sysuiStatusBarStateController) {
        this.scope = coroutineScope;
        this.context = context;
        this.displaySubComponentRepository = perDisplayRepository;
        this.keyguardStateController = keyguardStateController;
        this.fakeBinder = lazy;
        this.statusBarStateController = sysuiStatusBarStateController;
        Boolean bool = Boolean.FALSE;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._isKeyguardShowing = MutableStateFlow;
        this.isKeyguardShowing = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(bool);
        this._isPanelExpanded = MutableStateFlow2;
        this.isPanelExpanded = FlowKt.asStateFlow(MutableStateFlow2);
        ?? r7 = new SimpleModeObserver() { // from class: com.oplus.systemui.statusbar.SysUiStateControllerImpl$simpleModeObserver$1
            @Override // com.oplusos.systemui.common.observer.AbstractObserver
            public final void onChange(boolean z) {
                KeyguardUtils.Companion companion = KeyguardUtils.Companion;
                SysUiStateControllerImpl sysUiStateControllerImpl = this.this$0;
                sysUiStateControllerImpl.setSimpleMode(companion.isSimpleMode(sysUiStateControllerImpl.context));
                Iterator it = sysUiStateControllerImpl.listeners.iterator();
                while (it.hasNext()) {
                    ((SysUiStateController.ISysUiStateListener) it.next()).onSimpleModeChanged(sysUiStateControllerImpl.isSimpleMode);
                }
            }
        };
        this.simpleModeObserver = r7;
        SysUiStateControllerImpl$keyguardStateCallback$1 sysUiStateControllerImpl$keyguardStateCallback$1 = new SysUiStateControllerImpl$keyguardStateCallback$1();
        sysUiStateControllerImpl$keyguardStateCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.keyguardStateCallback = sysUiStateControllerImpl$keyguardStateCallback$1;
        ?? r0 = new GameModeObserver() { // from class: com.oplus.systemui.statusbar.SysUiStateControllerImpl$gameModeObserver$1
            @Override // com.oplus.systemui.statusbar.observer.GameModeObserver, com.oplusos.systemui.common.observer.AbstractObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                boolean z2 = this.isInGameMode;
                SysUiStateControllerImpl sysUiStateControllerImpl = this.this$0;
                sysUiStateControllerImpl.setGameMode(z2);
                Iterator it = sysUiStateControllerImpl.listeners.iterator();
                while (it.hasNext()) {
                    ((SysUiStateController.ISysUiStateListener) it.next()).onGameModeChanged(sysUiStateControllerImpl.isGameMode);
                }
            }
        };
        this.gameModeObserver = r0;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        sysuiStatusBarStateController.addCallback(anonymousClass1);
        ChildrenModeObserver childrenModeObserver = ChildrenModeObserver.getInstance();
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(0);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        childrenModeObserver.addListener(anonymousClass2);
        SuperPowerSaveSettingsObserver companion = SuperPowerSaveSettingsObserver.Companion.getInstance();
        AnonymousClass2 anonymousClass3 = new AnonymousClass2(1);
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        companion.addListener(anonymousClass3);
        r7.unregister(context);
        r7.registerForUserSwitch(context, -1, false);
        OplusSceneModeObserver companion2 = OplusSceneModeObserver.Companion.getInstance(context);
        AnonymousClass4 anonymousClass4 = new AnonymousClass4();
        anonymousClass4.this$0 = this;
        VarHandle.storeStoreFence();
        companion2.addCallback(anonymousClass4);
        OneHandedObserver.Companion companion3 = OneHandedObserver.Companion;
        companion3.getInstance().registerForUserSwitch(context, -1, false);
        OneHandedObserver companion4 = companion3.getInstance();
        AnonymousClass2 anonymousClass5 = new AnonymousClass2(2);
        anonymousClass5.this$0 = this;
        VarHandle.storeStoreFence();
        companion4.addListener(anonymousClass5);
        r0.register(context, false);
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) keyguardStateController;
        keyguardStateControllerImpl.getClass();
        keyguardStateControllerImpl.addCallback(sysUiStateControllerImpl$keyguardStateCallback$1);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass6(null), 3);
    }

    public void addListener(SysUiStateController.ISysUiStateListener iSysUiStateListener) {
        if (this.listeners.contains(iSysUiStateListener)) {
            return;
        }
        this.listeners.add(iSysUiStateListener);
    }

    public final boolean getNotifiedStatusBarOnly() {
        return this.notifiedStatusBarOnly;
    }

    public final float getPanelExpandFraction() {
        return this.panelExpandFraction;
    }

    public boolean getQsExpanded() {
        return this.qsExpanded;
    }

    public final boolean getSeparateNtExpand() {
        return this.separateNtExpand;
    }

    public final float getSeparateNtExpandFraction() {
        return this.separateNtExpandFraction;
    }

    public final boolean getSeparateQSExpand() {
        return this.separateQSExpand;
    }

    public final float getSeparateQSExpandFraction() {
        return this.separateQSExpandFraction;
    }

    public int getStatusBarState() {
        return this.statusBarStateController.getState();
    }

    public final boolean isBrickMode() {
        return this.isBrickMode;
    }

    public final boolean isChildrenMode() {
        return this.isChildrenMode;
    }

    public final boolean isDeepBrickMode() {
        return this.isDeepBrickMode;
    }

    public final boolean isFullscreen() {
        return this.isFullscreen;
    }

    public final boolean isGameMode() {
        return this.isGameMode;
    }

    /* JADX INFO: renamed from: isKeyguardShowing, reason: collision with other method in class */
    public final boolean m383isKeyguardShowing() {
        KeyguardStateControllerImpl keyguardStateControllerImpl = this.keyguardStateController;
        return keyguardStateControllerImpl.mShowing && !keyguardStateControllerImpl.mOccluded;
    }

    public final boolean isLightBrickMode() {
        return this.isLightBrickMode;
    }

    public final boolean isOneHandedMode() {
        return this.isOneHandedMode;
    }

    public final StateFlow isPanelExpanded() {
        return this.isPanelExpanded;
    }

    public final boolean isSimpleMode() {
        return this.isSimpleMode;
    }

    public final boolean isSuperPowerMode() {
        return this.isSuperPowerMode;
    }

    public final void notifyExpandFractionChange(float f, int i) {
        Object obj;
        for (SysUiStateController.ISysUiStateListener iSysUiStateListener : this.listeners) {
            try {
                iSysUiStateListener.onPanelExpandFractionChange(f, i);
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "SysUiStateControllerImpl-->".concat("onPanelExpandFractionChange failed, listener=" + iSysUiStateListener + " reason=" + th2.getMessage()));
            }
        }
    }

    public final void notifyExpandedChanged(View view, boolean z) {
        Object obj;
        for (SysUiStateController.ISysUiStateListener iSysUiStateListener : this.listeners) {
            iSysUiStateListener.onQSExpandedChanged(z);
            try {
                iSysUiStateListener.onPanelExpandedChanged(z, view);
                this._isPanelExpanded.setValue(Boolean.valueOf(z));
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "SysUiStateControllerImpl-->".concat("onPanelExpandedChanged failed, listener=" + iSysUiStateListener + " reason=" + th2.getMessage()));
            }
        }
    }

    public final void notifyStatusbarState(boolean z, View view) {
        Object obj;
        this.separateQSExpand = z;
        boolean qsExpanded = getQsExpanded();
        boolean z2 = this.separateNtExpand;
        if (qsExpanded != (z2 || this.separateQSExpand)) {
            setQsExpanded(z2 || this.separateQSExpand);
            boolean z3 = this.separateQSExpand;
            for (SysUiStateController.ISysUiStateListener iSysUiStateListener : this.listeners) {
                try {
                    iSysUiStateListener.onPanelExpandedChanged(z3, view);
                    this._isPanelExpanded.setValue(Boolean.valueOf(z3));
                    obj = Result.constructor-impl(Unit.INSTANCE);
                } catch (Throwable th) {
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                Throwable th2 = Result.exceptionOrNull-impl(obj);
                if (th2 != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "SysUiStateControllerImpl-->".concat("onPanelExpandedChanged failed, listener=" + iSysUiStateListener + " reason=" + th2.getMessage()));
                }
            }
            this.notifiedStatusBarOnly = true;
        }
    }

    public final void onExpandFractionNoAnimAfter() {
        Object obj;
        for (SysUiStateController.ISysUiStateListener iSysUiStateListener : this.listeners) {
            try {
                iSysUiStateListener.onExpandFractionNoAnimAfter();
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "SysUiStateControllerImpl-->".concat("onExpandFractionNoAnimAfter failed, listener=" + iSysUiStateListener + " reason=" + th2.getMessage()));
            }
        }
    }

    public final void onExpandFractionNoAnimBefore() {
        Object obj;
        for (SysUiStateController.ISysUiStateListener iSysUiStateListener : this.listeners) {
            try {
                iSysUiStateListener.onExpandFractionNoAnimBefore();
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "SysUiStateControllerImpl-->".concat("onExpandFractionNoAnimBefore failed, listener=" + iSysUiStateListener + " reason=" + th2.getMessage()));
            }
        }
    }

    public final void onExpandedChanged(boolean z, View view) {
        if (getQsExpanded() != z) {
            Lazy lazy = this.fakeBinder;
            if (z) {
                ((FakeBinder) lazy.get()).bindQsFakeView(m383isKeyguardShowing());
            } else {
                ((FakeBinder) lazy.get()).unbindQsFakeView(m383isKeyguardShowing());
            }
            setQsExpanded(z);
            notifyExpandedChanged(view, getQsExpanded());
        }
    }

    public final void onSeparateNtExpand(boolean z, View view) {
        Lazy lazy = this.fakeBinder;
        if (z) {
            ((FakeBinder) lazy.get()).bindSimpleFakeView();
        } else {
            ((FakeBinder) lazy.get()).unbindSimpleFakeView();
        }
        this.separateNtExpand = z;
        boolean qsExpanded = getQsExpanded();
        boolean z2 = this.separateNtExpand;
        boolean z3 = true;
        if (qsExpanded != (z2 || this.separateQSExpand)) {
            if (!z2 && !this.separateQSExpand) {
                z3 = false;
            }
            setQsExpanded(z3);
            notifyExpandedChanged(view, getQsExpanded());
        }
    }

    public final void onSeparateQSExpand(boolean z, View view) {
        this.separateQSExpand = z;
        Lazy lazy = this.fakeBinder;
        if (z) {
            ((FakeBinder) lazy.get()).bindQsFakeView(m383isKeyguardShowing());
        } else {
            ((FakeBinder) lazy.get()).unbindQsFakeView(m383isKeyguardShowing());
        }
        boolean qsExpanded = getQsExpanded();
        boolean z2 = this.separateNtExpand;
        boolean z3 = true;
        if (qsExpanded != (z2 || this.separateQSExpand)) {
            if (!z2 && !this.separateQSExpand) {
                z3 = false;
            }
            setQsExpanded(z3);
            notifyExpandedChanged(view, getQsExpanded());
            return;
        }
        if (this.notifiedStatusBarOnly) {
            boolean qsExpanded2 = getQsExpanded();
            Iterator<T> it = this.listeners.iterator();
            while (it.hasNext()) {
                ((SysUiStateController.ISysUiStateListener) it.next()).onQSExpandedChanged(qsExpanded2);
            }
            this.notifiedStatusBarOnly = false;
        }
    }

    public void removeListener(SysUiStateController.ISysUiStateListener iSysUiStateListener) {
        this.listeners.remove(iSysUiStateListener);
    }

    public void setBrickMode(boolean z) {
        this.isBrickMode = z;
    }

    public void setChildrenMode(boolean z) {
        this.isChildrenMode = z;
    }

    public void setDeepBrickMode(boolean z) {
        this.isDeepBrickMode = z;
    }

    public void setFullscreen(boolean z) {
        this.isFullscreen = z;
    }

    public void setGameMode(boolean z) {
        this.isGameMode = z;
    }

    public void setLightBrickMode(boolean z) {
        this.isLightBrickMode = z;
    }

    public final void setNotifiedStatusBarOnly(boolean z) {
        this.notifiedStatusBarOnly = z;
    }

    public void setOneHandedMode(boolean z) {
        this.isOneHandedMode = z;
    }

    public final void setPanelExpandFraction(float f) {
        this.panelExpandFraction = f;
    }

    public void setQsExpanded(boolean z) {
        this.qsExpanded = z;
    }

    public final void setSeparateNtExpand(boolean z) {
        this.separateNtExpand = z;
    }

    public final void setSeparateNtExpandFraction(float f) {
        this.separateNtExpandFraction = f;
    }

    public final void setSeparateQSExpand(boolean z) {
        this.separateQSExpand = z;
    }

    public final void setSeparateQSExpandFraction(float f) {
        this.separateQSExpandFraction = f;
    }

    public void setSimpleMode(boolean z) {
        this.isSimpleMode = z;
    }

    public void setSuperPowerMode(boolean z) {
        this.isSuperPowerMode = z;
    }

    public final StateFlow isKeyguardShowing() {
        return this.isKeyguardShowing;
    }
}
