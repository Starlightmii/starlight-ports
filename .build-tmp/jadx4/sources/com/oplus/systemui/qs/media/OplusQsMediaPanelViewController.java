package com.oplus.systemui.qs.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.provider.Settings;
import android.view.View;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.Dumpable;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.ui.viewmodel.SeekBarViewModel;
import com.android.systemui.media.dialog.MediaOutputDialogManager;
import com.android.systemui.plugins.qs.IEditableView;
import com.android.systemui.qs.QSUtils;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.media.output.data.OutputAudioSharingInteractor;
import com.oplus.systemui.qs.base.seek.OplusQsVolumeIconView;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplusos.systemui.common.model.MediaPanelData;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Executor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
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
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaPanelViewController extends ViewController implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String OPLUS_ATLAS_CONNECTED = "com.oplus.atlas.OplusMMAtlasService.CONNECTED";
    private static final String SUPER_POWER_SAVE_DESKTOP_APP_LIST = "super_power_save_desktop_app_list";
    private static final String TAG = "OplusQsMediaPanelViewController";
    private final OutputAudioSharingInteractor audioSharingInteractor;
    private Handler bgHandler;
    private final BroadcastDispatcher broadcastDispatcher;
    public final Throwable creationStackTrace;
    private final DialogTransitionAnimator dialogLaunchAnimator;
    private final DumpManager dumpManager;
    public boolean isBeingDragged;
    public boolean isEditMode;
    public boolean isMediaDataListenerRegistered;
    private final OplusQsMediaPanelViewController$mBroadcastReceiver$1 mBroadcastReceiver;
    private final KeyguardUpdateMonitorCallback mCallback;
    private final OplusQsMediaPanelViewController$mObserverListener$1 mObserverListener;
    private final OplusQsMediaPanelViewController$mPackageReceiver$1 mPackageReceiver;
    private final ContentObserver mSuperPowerListObserve;
    private Handler mainHandler;
    private final MediaDataManager mediaDataManager;
    private final OplusQsMediaPanelViewController$mediaDataUpdateListener$1 mediaDataUpdateListener;
    private final OplusQsMediaDialogController qsMediaDialogController;
    private final SeekBarViewModel.Factory seekBarViewModelFactory;
    private final ShadeInteractor shadeInteractor;
    private final View view;

    /* JADX INFO: renamed from: com.oplus.systemui.qs.media.OplusQsMediaPanelViewController$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.media.OplusQsMediaPanelViewController$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00761 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ OplusQsMediaPanelViewController this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.media.OplusQsMediaPanelViewController$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00771 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQsMediaPanelViewController this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.qs.media.OplusQsMediaPanelViewController$1$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                final class C00781 extends SuspendLambda implements Function3 {
                    /* synthetic */ float F$0;
                    /* synthetic */ float F$1;
                    int label;

                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        float fFloatValue = ((Number) obj).floatValue();
                        float fFloatValue2 = ((Number) obj2).floatValue();
                        C00781 c00781 = new C00781(3, (Continuation) obj3);
                        c00781.F$0 = fFloatValue;
                        c00781.F$1 = fFloatValue2;
                        return c00781.invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Code duplicated, block: B:12:0x0023  */
                    public final Object invokeSuspend(Object obj) {
                        float f = this.F$0;
                        float f2 = this.F$1;
                        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                        float f3 = 0.0f;
                        if ((f > 0.0f && f < 1.0f) || (f2 > 0.0f && f2 < 1.0f)) {
                            f3 = -1.0f;
                        } else if (f >= 1.0f || f2 >= 1.0f) {
                            f3 = 1.0f;
                        } else if (f != 0.0f || f2 != 0.0f) {
                            f3 = -1.0f;
                        }
                        return Boxing.boxFloat(f3);
                    }
                }

                /* JADX INFO: renamed from: com.oplus.systemui.qs.media.OplusQsMediaPanelViewController$1$1$1$2, reason: invalid class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ int $r8$classId;
                    public /* synthetic */ OplusQsMediaPanelViewController this$0;

                    public /* synthetic */ AnonymousClass2(int i) {
                        this.$r8$classId = i;
                    }

                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                ((OplusQsBaseMediaPanelView) ((ViewController) this.this$0).mView).onShadeExpanded(((Number) obj).floatValue());
                                break;
                            default:
                                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                                OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView = (OplusQsBaseMediaPanelView) ((ViewController) this.this$0).mView;
                                oplusQsBaseMediaPanelView.inAudioSharing = zBooleanValue;
                                oplusQsBaseMediaPanelView.updateMediaOutputBtn(((Number) OplusQsVolumeIconView.Companion.getMusicStreamRoute().get()).intValue());
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00771(OplusQsMediaPanelViewController oplusQsMediaPanelViewController, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQsMediaPanelViewController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00771(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow flowCombine = FlowKt.combine(this.this$0.shadeInteractor.getShadeExpansion(), this.this$0.shadeInteractor.getSeparateQsExpansion(), new C00781(3, null));
                        OplusQsMediaPanelViewController oplusQsMediaPanelViewController = this.this$0;
                        AnonymousClass2 anonymousClass2 = new AnonymousClass2(0);
                        anonymousClass2.this$0 = oplusQsMediaPanelViewController;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (flowCombine.collect(anonymousClass2, this) == coroutine_suspended) {
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

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00761(OplusQsMediaPanelViewController oplusQsMediaPanelViewController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusQsMediaPanelViewController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                C00761 c00761 = new C00761(this.this$0, continuation);
                c00761.L$0 = obj;
                return c00761;
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new C00771(this.this$0, null), 2);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass1 anonymousClass1 = OplusQsMediaPanelViewController.this.new AnonymousClass1((Continuation) obj3);
            anonymousClass1.L$0 = (LifecycleOwner) obj;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle.State state = Lifecycle.State.CREATED;
                C00761 c00761 = new C00761(OplusQsMediaPanelViewController.this, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c00761, this) == coroutine_suspended) {
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

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.media.OplusQsMediaPanelViewController$onViewAttached$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC02561 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusQsMediaPanelViewController this$0;

        public /* synthetic */ RunnableC02561(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i = 1;
            switch (this.$r8$classId) {
                case 0:
                    ((OplusQsBaseMediaPanelView) ((ViewController) this.this$0).mView).queryHistoryMediaData();
                    break;
                case 1:
                    OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView = (OplusQsBaseMediaPanelView) ((ViewController) this.this$0).mView;
                    OplusQsBaseMediaPanelView.Companion companion = OplusQsBaseMediaPanelView.Companion;
                    oplusQsBaseMediaPanelView.bindOrResetMediaData("OPLUS_ATLAS_CONNECTED", false);
                    break;
                case 2:
                    ((OplusQsBaseMediaPanelView) ((ViewController) this.this$0).mView).queryHistoryMediaData();
                    Handler handler = this.this$0.mainHandler;
                    OplusQsMediaPanelViewController oplusQsMediaPanelViewController = this.this$0;
                    RunnableC02561 runnableC02561 = new RunnableC02561(i);
                    runnableC02561.this$0 = oplusQsMediaPanelViewController;
                    VarHandle.storeStoreFence();
                    handler.post(runnableC02561);
                    break;
                case 3:
                    if (this.this$0.isAttachedToWindow()) {
                        ((OplusQsBaseMediaPanelView) ((ViewController) this.this$0).mView).resetButtonEnableState("onUserSwitchComplete");
                        OplusQsMediaPanelViewController oplusQsMediaPanelViewController2 = this.this$0;
                        RunnableC02561 runnableC02562 = new RunnableC02561(5);
                        runnableC02562.this$0 = oplusQsMediaPanelViewController2;
                        VarHandle.storeStoreFence();
                        SystemUIThreadUtil.runOnUiThread(runnableC02562);
                        break;
                    }
                    break;
                case 4:
                    ((OplusQsBaseMediaPanelView) ((ViewController) this.this$0).mView).queryHistoryMediaData();
                    Handler handler2 = this.this$0.mainHandler;
                    OplusQsMediaPanelViewController oplusQsMediaPanelViewController3 = this.this$0;
                    RunnableC02561 runnableC02563 = new RunnableC02561(3);
                    runnableC02563.this$0 = oplusQsMediaPanelViewController3;
                    VarHandle.storeStoreFence();
                    handler2.post(runnableC02563);
                    break;
                case 5:
                    OplusQsMediaPanelViewController oplusQsMediaPanelViewController4 = this.this$0;
                    if (!oplusQsMediaPanelViewController4.isMediaDataListenerRegistered && oplusQsMediaPanelViewController4.isAttachedToWindow()) {
                        this.this$0.mediaDataManager.addListener(this.this$0.mediaDataUpdateListener);
                        this.this$0.isMediaDataListenerRegistered = true;
                        break;
                    }
                    break;
                default:
                    OplusQsMediaPanelViewController oplusQsMediaPanelViewController5 = this.this$0;
                    if (oplusQsMediaPanelViewController5.isMediaDataListenerRegistered) {
                        oplusQsMediaPanelViewController5.mediaDataManager.removeListener(this.this$0.mediaDataUpdateListener);
                        this.this$0.isMediaDataListenerRegistered = false;
                    }
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.media.OplusQsMediaPanelViewController$onViewAttached$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.qs.media.OplusQsMediaPanelViewController$onViewAttached$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ OplusQsMediaPanelViewController this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.qs.media.OplusQsMediaPanelViewController$onViewAttached$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00791 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ OplusQsMediaPanelViewController this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00791(OplusQsMediaPanelViewController oplusQsMediaPanelViewController, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusQsMediaPanelViewController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00791(this.this$0, continuation);
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
                        StateFlow audioSharingState = this.this$0.audioSharingInteractor.getAudioSharingState();
                        OplusQsMediaPanelViewController oplusQsMediaPanelViewController = this.this$0;
                        AnonymousClass1.C00761.C00771.AnonymousClass2 anonymousClass2 = new AnonymousClass1.C00761.C00771.AnonymousClass2(i2);
                        anonymousClass2.this$0 = oplusQsMediaPanelViewController;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (audioSharingState.collect(anonymousClass2, this) == coroutine_suspended) {
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

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(OplusQsMediaPanelViewController oplusQsMediaPanelViewController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = oplusQsMediaPanelViewController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00791(this.this$0, null), 3);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass2 anonymousClass2 = OplusQsMediaPanelViewController.this.new AnonymousClass2((Continuation) obj3);
            anonymousClass2.L$0 = (LifecycleOwner) obj;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle.State state = Lifecycle.State.CREATED;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(OplusQsMediaPanelViewController.this, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutine_suspended) {
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v6, types: [com.oplus.systemui.qs.media.OplusQsMediaPanelViewController$mPackageReceiver$1] */
    /* JADX WARN: Type inference failed for: r2v7, types: [com.oplus.systemui.qs.media.OplusQsMediaPanelViewController$mBroadcastReceiver$1] */
    public OplusQsMediaPanelViewController(OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView, MediaDataManager mediaDataManager, OplusQsMediaDialogController oplusQsMediaDialogController, BroadcastDispatcher broadcastDispatcher, DialogTransitionAnimator dialogTransitionAnimator, SeekBarViewModel.Factory factory, OutputAudioSharingInteractor outputAudioSharingInteractor, ShadeInteractor shadeInteractor, DumpManager dumpManager) {
        super(oplusQsBaseMediaPanelView);
        this.mediaDataManager = mediaDataManager;
        this.qsMediaDialogController = oplusQsMediaDialogController;
        this.broadcastDispatcher = broadcastDispatcher;
        this.dialogLaunchAnimator = dialogTransitionAnimator;
        this.seekBarViewModelFactory = factory;
        this.audioSharingInteractor = outputAudioSharingInteractor;
        this.shadeInteractor = shadeInteractor;
        this.dumpManager = dumpManager;
        this.bgHandler = new Handler((Looper) Dependency.sDependency.getDependencyInner(Dependency.BG_LOOPER));
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.creationStackTrace = new Throwable(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date())).fillInStackTrace();
        OplusQsMediaPanelViewController$mCallback$1 oplusQsMediaPanelViewController$mCallback$1 = new OplusQsMediaPanelViewController$mCallback$1();
        oplusQsMediaPanelViewController$mCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mCallback = oplusQsMediaPanelViewController$mCallback$1;
        this.mPackageReceiver = new BroadcastReceiver() { // from class: com.oplus.systemui.qs.media.OplusQsMediaPanelViewController$mPackageReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action != null && action.hashCode() == 525384130 && action.equals("android.intent.action.PACKAGE_REMOVED")) {
                    OplusNotificationLogger.logMediaPanel("OplusQsMediaPanelViewController", "Received ACTION_PACKAGE_REMOVED");
                    ((OplusQsBaseMediaPanelView) ((ViewController) this.this$0).mView).onMediaPlayerRemoveByPackage();
                }
            }
        };
        this.mBroadcastReceiver = new BroadcastReceiver() { // from class: com.oplus.systemui.qs.media.OplusQsMediaPanelViewController$mBroadcastReceiver$1
            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action != null) {
                    switch (action.hashCode()) {
                        case -1676458352:
                            if (action.equals("android.intent.action.HEADSET_PLUG")) {
                                boolean z = intent.getIntExtra("state", 0) == 0;
                                if (!z) {
                                    QSUtils.setHeadsetConnected(false);
                                }
                                OplusNotificationLogger.logMediaPanel("OplusQsMediaPanelViewController", "Received ACTION_HEADSET_PLUG disConnect: " + z + " headsetConnected: " + QSUtils.getHeadsetConnected());
                                break;
                            }
                            break;
                        case -504372327:
                            if (action.equals("com.oplus.atlas.OplusMMAtlasService.CONNECTED")) {
                                Handler handler = this.this$0.bgHandler;
                                OplusQsMediaPanelViewController oplusQsMediaPanelViewController = this.this$0;
                                OplusQsMediaPanelViewController.RunnableC02561 runnableC02561 = new OplusQsMediaPanelViewController.RunnableC02561(2);
                                runnableC02561.this$0 = oplusQsMediaPanelViewController;
                                VarHandle.storeStoreFence();
                                handler.post(runnableC02561);
                                break;
                            }
                            break;
                        case -301431627:
                            if (action.equals("android.bluetooth.device.action.ACL_CONNECTED")) {
                                QSUtils.setHeadsetConnected(true);
                                OplusNotificationLogger.logMediaPanel("OplusQsMediaPanelViewController", "Received ACTION_ACL_CONNECTED headsetConnected: true");
                                break;
                            }
                            break;
                        case 1821585647:
                            if (action.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
                                QSUtils.setHeadsetConnected(false);
                                OplusNotificationLogger.logMediaPanel("OplusQsMediaPanelViewController", "Received ACTION_ACL_DISCONNECTED headsetConnected: false");
                                break;
                            }
                            break;
                    }
                }
            }
        };
        RepeatWhenAttachedKt.repeatWhenAttached$default(oplusQsBaseMediaPanelView, (CoroutineContext) null, new AnonymousClass1(null), 3);
        OplusQsMediaPanelViewController$mObserverListener$1 oplusQsMediaPanelViewController$mObserverListener$1 = new OplusQsMediaPanelViewController$mObserverListener$1();
        oplusQsMediaPanelViewController$mObserverListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mObserverListener = oplusQsMediaPanelViewController$mObserverListener$1;
        final Handler handler = new Handler();
        this.mSuperPowerListObserve = new ContentObserver(handler) { // from class: com.oplus.systemui.qs.media.OplusQsMediaPanelViewController$mSuperPowerListObserve$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                ((OplusQsBaseMediaPanelView) ((ViewController) this.this$0).mView).updateSuperPowerList();
            }
        };
        this.view = oplusQsBaseMediaPanelView;
        OplusQsMediaPanelViewController$mediaDataUpdateListener$1 oplusQsMediaPanelViewController$mediaDataUpdateListener$1 = new OplusQsMediaPanelViewController$mediaDataUpdateListener$1();
        oplusQsMediaPanelViewController$mediaDataUpdateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mediaDataUpdateListener = oplusQsMediaPanelViewController$mediaDataUpdateListener$1;
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println(this);
        printWriter.println("  mView=" + ((ViewController) this).mView);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "  isAttachedToWindow=", isAttachedToWindow());
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "  isMediaDataListenerRegistered=", this.isMediaDataListenerRegistered);
        printWriter.println("  instance create time: " + this.creationStackTrace.getMessage() + ", stack trace:");
        Iterator it = ArraysKt___ArraysKt.drop(this.creationStackTrace.getStackTrace(), 1).iterator();
        while (it.hasNext()) {
            printWriter.println("\tat " + ((StackTraceElement) it.next()));
        }
    }

    public final MediaPanelData getMediaData() {
        return ((OplusQsBaseMediaPanelView) ((ViewController) this).mView).getMediaData();
    }

    public final View getView() {
        return this.view;
    }

    /* JADX WARN: Type inference failed for: r1v22, types: [android.view.ViewGroup, com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView] */
    public final void onViewAttached() {
        OplusNotificationLogger.logMediaPanel(TAG, "onViewAttached");
        OplusQsBaseMediaPanelView oplusQsBaseMediaPanelView = (OplusQsBaseMediaPanelView) ((ViewController) this).mView;
        OplusQsBaseMediaPanelView.Companion companion = OplusQsBaseMediaPanelView.Companion;
        oplusQsBaseMediaPanelView.bindOrResetMediaData("onViewAttached", false);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, this.mPackageReceiver, intentFilter, (Executor) null, (UserHandle) null, 0, 60);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(OPLUS_ATLAS_CONNECTED);
        intentFilter2.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter2.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter2.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, this.mBroadcastReceiver, intentFilter2, (Executor) null, (UserHandle) null, 0, 60);
        ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).registerCallback(this.mCallback);
        ((OplusQsBaseMediaPanelView) ((ViewController) this).mView).initSeekBarParams(this.seekBarViewModelFactory);
        ((OplusQsBaseMediaPanelView) ((ViewController) this).mView).setMediaOutputDialogFactory((MediaOutputDialogManager) DependencyEx.sDependency.getDependency(MediaOutputDialogManager.class));
        IEditableView iEditableView = (OplusQsBaseMediaPanelView) ((ViewController) this).mView;
        OplusQsMediaPanelViewController$initClickListener$1 oplusQsMediaPanelViewController$initClickListener$1 = new OplusQsMediaPanelViewController$initClickListener$1();
        oplusQsMediaPanelViewController$initClickListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        iEditableView.setOnClickListener(oplusQsMediaPanelViewController$initClickListener$1);
        IEditableView iEditableView2 = (OplusQsBaseMediaPanelView) ((ViewController) this).mView;
        OplusQsMediaPanelViewController$initClickListener$2 oplusQsMediaPanelViewController$initClickListener$2 = new OplusQsMediaPanelViewController$initClickListener$2();
        oplusQsMediaPanelViewController$initClickListener$2.this$0 = this;
        VarHandle.storeStoreFence();
        iEditableView2.setOnLongClickListener(oplusQsMediaPanelViewController$initClickListener$2);
        ?? r1 = (OplusQsBaseMediaPanelView) ((ViewController) this).mView;
        r1.setClickable(!r1.isEditMode);
        r1.setLongClickable(!r1.isEditMode);
        RunnableC02561 runnableC02561 = new RunnableC02561(5);
        runnableC02561.this$0 = this;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(runnableC02561);
        SuperPowerSaveSettingsObserver.Companion.getInstance().addListener(this.mObserverListener);
        ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("super_power_save_desktop_app_list"), this.mSuperPowerListObserve);
        Handler handler = this.bgHandler;
        RunnableC02561 runnableC02562 = new RunnableC02561(0);
        runnableC02562.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(runnableC02562);
        RepeatWhenAttachedKt.repeatWhenAttached$default(((ViewController) this).mView, Dispatchers.getMain().getImmediate(), new AnonymousClass2(null), 2);
        this.dumpManager.registerNormalDumpable(toString(), this);
    }

    public final void onViewDetached() {
        OplusNotificationLogger.logMediaPanel(TAG, "onViewDetached");
        this.broadcastDispatcher.unregisterReceiver(this.mPackageReceiver);
        this.broadcastDispatcher.unregisterReceiver(this.mBroadcastReceiver);
        ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).removeCallback(this.mCallback);
        RunnableC02561 runnableC02561 = new RunnableC02561(6);
        runnableC02561.this$0 = this;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(runnableC02561);
        SuperPowerSaveSettingsObserver.Companion.getInstance().removeListener(this.mObserverListener);
        ObserverDispatcher.getInstance().unregisterObserver(this.mSuperPowerListObserve);
        ((OplusQsBaseMediaPanelView) ((ViewController) this).mView).removeObserver$1();
        this.dumpManager.unregisterDumpable(toString());
    }

    public final void setBeingDragged(boolean z) {
        if (this.isBeingDragged != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("[isBeingDragged] ", "OplusQsMediaPanelViewController-->", "SystemUi--QuickSettings", z);
            }
            this.isBeingDragged = z;
            ((OplusQsBaseMediaPanelView) ((ViewController) this).mView).setBeingDragged(z);
        }
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [android.view.ViewGroup, com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView] */
    public final void setEditMode(boolean z) {
        if (this.isEditMode != z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("[isEditMode] ", "OplusQsMediaPanelViewController-->", "SystemUi--QuickSettings", z);
            }
            this.isEditMode = z;
            ?? r3 = (OplusQsBaseMediaPanelView) ((ViewController) this).mView;
            r3.setClickable(!z);
            r3.setLongClickable(!z);
            r3.setEditMode(z);
        }
    }

    public final void setMediaPanelData(MediaPanelData mediaPanelData) {
        ((OplusQsBaseMediaPanelView) ((ViewController) this).mView).setMediaData(mediaPanelData);
    }
}
