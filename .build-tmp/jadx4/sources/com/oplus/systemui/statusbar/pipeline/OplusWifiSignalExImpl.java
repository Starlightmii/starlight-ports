package com.oplus.systemui.statusbar.pipeline;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.phone.worklife.OPWLBHelper;
import com.android.systemui.statusbar.pipeline.OplusWifiSignalEx;
import com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel;
import com.android.systemui.statusbar.pipeline.wifi.ui.view.ModernStatusBarWifiView;
import com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.LocationBasedWifiViewModel;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.statusbar.phone.signal.widget.OplusModernStatusBarWifiView;
import com.oplus.systemui.statusbar.phone.workfile.OPWLBHelperImpl;
import com.oplus.systemui.statusbar.pipeline.rus.OplusStatusBarRomUpdateManager;
import com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiIconSets;
import com.oplus.systemui.statusbar.pipeline.wifi.data.OplusWifiRepository;
import com.oplus.systemui.statusbar.pipeline.wifi.data.WifiThemeAdapterState;
import com.oplus.systemui.statusbar.pipeline.wifi.ui.viewmodel.OplusWifiViewModel;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import java.lang.invoke.VarHandle;
import kotlin.KotlinNothingValueException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusWifiSignalExImpl extends OplusWifiSignalEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusWifiSignalExImpl";
    private final CoroutineDispatcher bgDispatcher;
    private final Context context;
    private final OplusWifiRepository oplusWifiRepository;
    private final OplusWifiViewModel oplusWifiViewModel;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.OplusWifiSignalExImpl$bindEx$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.OplusWifiSignalExImpl$bindEx$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C01421 implements FlowCollector {
            public final /* synthetic */ int $r8$classId;
            public /* synthetic */ Object this$0;

            public /* synthetic */ C01421(int i) {
                this.$r8$classId = i;
            }

            public final Object emit(Object obj, Continuation continuation) {
                switch (this.$r8$classId) {
                    case 0:
                        boolean zBooleanValue = ((Boolean) obj).booleanValue();
                        OPWLBHelper companion = OPWLBHelperImpl.Companion.getInstance(((OplusWifiSignalExImpl) this.this$0).context);
                        if (companion != null) {
                            companion.processWifiConnectivity(zBooleanValue);
                        }
                        break;
                    case 1:
                        Pair pair = (Pair) obj;
                        Typeface typeface = (Typeface) pair.component1();
                        ((Number) pair.component2()).intValue();
                        ((TextView) this.this$0).setTypeface(typeface);
                        break;
                    default:
                        int iIntValue = ((Number) obj).intValue();
                        ImageView imageView = (ImageView) this.this$0;
                        if (iIntValue > 0) {
                            imageView.setImageResource(iIntValue);
                            imageView.setVisibility(0);
                        } else {
                            imageView.setVisibility(8);
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
            return OplusWifiSignalExImpl.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow wifiConnected = OplusWifiSignalExImpl.this.oplusWifiViewModel.getWifiConnected();
                OplusWifiSignalExImpl oplusWifiSignalExImpl = OplusWifiSignalExImpl.this;
                C01421 c01421 = new C01421(0);
                c01421.this$0 = oplusWifiSignalExImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (wifiConnected.collect(c01421, this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.OplusWifiSignalExImpl$bindEx$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function3 {
        final /* synthetic */ MutableStateFlow $iconTint;
        final /* synthetic */ ImageView $iconView;
        final /* synthetic */ Ref.BooleanRef $isCollecting;
        final /* synthetic */ Flow $typefaceCallback;
        final /* synthetic */ ImageView $wifiActivity;
        final /* synthetic */ ImageView $wifiLeft;
        final /* synthetic */ TextView $wifiLeftTv;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ OplusWifiSignalExImpl this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.OplusWifiSignalExImpl$bindEx$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ MutableStateFlow $iconTint;
            final /* synthetic */ ImageView $iconView;
            final /* synthetic */ Ref.BooleanRef $isCollecting;
            final /* synthetic */ Flow $typefaceCallback;
            final /* synthetic */ ImageView $wifiActivity;
            final /* synthetic */ ImageView $wifiLeft;
            final /* synthetic */ TextView $wifiLeftTv;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ OplusWifiSignalExImpl this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.OplusWifiSignalExImpl$bindEx$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01431 extends SuspendLambda implements Function2 {
                final /* synthetic */ Flow $typefaceCallback;
                final /* synthetic */ TextView $wifiLeftTv;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01431(Flow flow, TextView textView, Continuation continuation) {
                    super(2, continuation);
                    this.$typefaceCallback = flow;
                    this.$wifiLeftTv = textView;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01431(this.$typefaceCallback, this.$wifiLeftTv, continuation);
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
                        Flow flow = this.$typefaceCallback;
                        TextView textView = this.$wifiLeftTv;
                        AnonymousClass1.C01421 c01421 = new AnonymousClass1.C01421(i2);
                        c01421.this$0 = textView;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (flow.collect(c01421, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.OplusWifiSignalExImpl$bindEx$2$1$2, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01442 extends SuspendLambda implements Function2 {
                final /* synthetic */ ImageView $wifiLeft;
                final /* synthetic */ TextView $wifiLeftTv;
                int label;
                final /* synthetic */ OplusWifiSignalExImpl this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.OplusWifiSignalExImpl$bindEx$2$1$2$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                final class C01451 extends SuspendLambda implements Function3 {
                    final /* synthetic */ ImageView $wifiLeft;
                    final /* synthetic */ TextView $wifiLeftTv;
                    /* synthetic */ int I$0;
                    /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C01451(ImageView imageView, TextView textView, Continuation continuation) {
                        super(3, continuation);
                        this.$wifiLeft = imageView;
                        this.$wifiLeftTv = textView;
                    }

                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        int iIntValue = ((Number) obj).intValue();
                        C01451 c01451 = new C01451(this.$wifiLeft, this.$wifiLeftTv, (Continuation) obj3);
                        c01451.I$0 = iIntValue;
                        c01451.L$0 = (String) obj2;
                        return c01451.invokeSuspend(Unit.INSTANCE);
                    }

                    public final Object invokeSuspend(Object obj) {
                        int i = this.I$0;
                        String str = (String) this.L$0;
                        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                        if (i > 0) {
                            ImageView imageView = this.$wifiLeft;
                            TextView textView = this.$wifiLeftTv;
                            if (i > 0) {
                                imageView.setImageResource(i);
                                imageView.setVisibility(0);
                                textView.setVisibility(8);
                            } else {
                                imageView.setVisibility(8);
                            }
                        } else if (str != null) {
                            TextView textView2 = this.$wifiLeftTv;
                            ImageView imageView2 = this.$wifiLeft;
                            textView2.setText(str);
                            textView2.setVisibility(0);
                            imageView2.setVisibility(8);
                        } else {
                            this.$wifiLeft.setVisibility(8);
                            this.$wifiLeftTv.setVisibility(8);
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.OplusWifiSignalExImpl$bindEx$2$1$2$2, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C01462 implements FlowCollector {
                    public static final C01462 INSTANCE = new C01462();

                    public final Object emit(Object obj, Continuation continuation) {
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01442(OplusWifiSignalExImpl oplusWifiSignalExImpl, ImageView imageView, TextView textView, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusWifiSignalExImpl;
                    this.$wifiLeft = imageView;
                    this.$wifiLeftTv = textView;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01442(this.this$0, this.$wifiLeft, this.$wifiLeftTv, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow flowCombine = FlowKt.combine(this.this$0.oplusWifiViewModel.getWifiLeftResId(), this.this$0.oplusWifiViewModel.getWifiLeftText(), new C01451(this.$wifiLeft, this.$wifiLeftTv, null));
                        this.label = 1;
                        if (flowCombine.collect(C01462.INSTANCE, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.OplusWifiSignalExImpl$bindEx$2$1$3, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                final /* synthetic */ ImageView $wifiActivity;
                int label;
                final /* synthetic */ OplusWifiSignalExImpl this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(OplusWifiSignalExImpl oplusWifiSignalExImpl, ImageView imageView, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = oplusWifiSignalExImpl;
                    this.$wifiActivity = imageView;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass3(this.this$0, this.$wifiActivity, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow wifiActivityResId = this.this$0.oplusWifiViewModel.getWifiActivityResId();
                        ImageView imageView = this.$wifiActivity;
                        AnonymousClass1.C01421 c01421 = new AnonymousClass1.C01421(2);
                        c01421.this$0 = imageView;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (wifiActivityResId.collect(c01421, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.OplusWifiSignalExImpl$bindEx$2$1$4, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass4 extends SuspendLambda implements Function2 {
                final /* synthetic */ MutableStateFlow $iconTint;
                final /* synthetic */ ImageView $iconView;
                final /* synthetic */ ImageView $wifiActivity;
                final /* synthetic */ ImageView $wifiLeft;
                final /* synthetic */ TextView $wifiLeftTv;
                int label;

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.OplusWifiSignalExImpl$bindEx$2$1$4$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C01471 implements FlowCollector {
                    public /* synthetic */ ImageView $iconView;
                    public /* synthetic */ ImageView $wifiActivity;
                    public /* synthetic */ ImageView $wifiLeft;
                    public /* synthetic */ TextView $wifiLeftTv;

                    public final Object emit(Object obj, Continuation continuation) {
                        int iIntValue = ((Number) obj).intValue();
                        ImageView imageView = this.$iconView;
                        ImageView imageView2 = this.$wifiActivity;
                        ImageView imageView3 = this.$wifiLeft;
                        TextView textView = this.$wifiLeftTv;
                        ColorStateList colorStateListValueOf = ColorStateList.valueOf(iIntValue);
                        imageView.setImageTintList(colorStateListValueOf);
                        imageView2.setImageTintList(colorStateListValueOf);
                        imageView3.setImageTintList(colorStateListValueOf);
                        textView.setTextColor(iIntValue);
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass4(MutableStateFlow mutableStateFlow, ImageView imageView, ImageView imageView2, ImageView imageView3, TextView textView, Continuation continuation) {
                    super(2, continuation);
                    this.$iconTint = mutableStateFlow;
                    this.$iconView = imageView;
                    this.$wifiActivity = imageView2;
                    this.$wifiLeft = imageView3;
                    this.$wifiLeftTv = textView;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass4(this.$iconTint, this.$iconView, this.$wifiActivity, this.$wifiLeft, this.$wifiLeftTv, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        MutableStateFlow mutableStateFlow = this.$iconTint;
                        ImageView imageView = this.$iconView;
                        ImageView imageView2 = this.$wifiActivity;
                        ImageView imageView3 = this.$wifiLeft;
                        TextView textView = this.$wifiLeftTv;
                        C01471 c01471 = new C01471();
                        c01471.$iconView = imageView;
                        c01471.$wifiActivity = imageView2;
                        c01471.$wifiLeft = imageView3;
                        c01471.$wifiLeftTv = textView;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (mutableStateFlow.collect(c01471, this) == coroutine_suspended) {
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
            public AnonymousClass1(Ref.BooleanRef booleanRef, Flow flow, TextView textView, OplusWifiSignalExImpl oplusWifiSignalExImpl, ImageView imageView, ImageView imageView2, MutableStateFlow mutableStateFlow, ImageView imageView3, Continuation continuation) {
                super(2, continuation);
                this.$isCollecting = booleanRef;
                this.$typefaceCallback = flow;
                this.$wifiLeftTv = textView;
                this.this$0 = oplusWifiSignalExImpl;
                this.$wifiLeft = imageView;
                this.$wifiActivity = imageView2;
                this.$iconTint = mutableStateFlow;
                this.$iconView = imageView3;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$isCollecting, this.$typefaceCallback, this.$wifiLeftTv, this.this$0, this.$wifiLeft, this.$wifiActivity, this.$iconTint, this.$iconView, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                try {
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.$isCollecting.element = true;
                        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C01431(this.$typefaceCallback, this.$wifiLeftTv, null), 3);
                        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C01442(this.this$0, this.$wifiLeft, this.$wifiLeftTv, null), 3);
                        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(this.this$0, this.$wifiActivity, null), 3);
                        BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new AnonymousClass4(this.$iconTint, this.$iconView, this.$wifiActivity, this.$wifiLeft, this.$wifiLeftTv, null), 2);
                        this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                        this.label = 1;
                        if (DelayKt.awaitCancellation(this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    throw new KotlinNothingValueException();
                } catch (Throwable th) {
                    this.$isCollecting.element = false;
                    throw th;
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Ref.BooleanRef booleanRef, Flow flow, TextView textView, OplusWifiSignalExImpl oplusWifiSignalExImpl, ImageView imageView, ImageView imageView2, MutableStateFlow mutableStateFlow, ImageView imageView3, Continuation continuation) {
            super(3, continuation);
            this.$isCollecting = booleanRef;
            this.$typefaceCallback = flow;
            this.$wifiLeftTv = textView;
            this.this$0 = oplusWifiSignalExImpl;
            this.$wifiLeft = imageView;
            this.$wifiActivity = imageView2;
            this.$iconTint = mutableStateFlow;
            this.$iconView = imageView3;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$isCollecting, this.$typefaceCallback, this.$wifiLeftTv, this.this$0, this.$wifiLeft, this.$wifiActivity, this.$iconTint, this.$iconView, (Continuation) obj3);
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
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$isCollecting, this.$typefaceCallback, this.$wifiLeftTv, this.this$0, this.$wifiLeft, this.$wifiActivity, this.$iconTint, this.$iconView, null);
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

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.OplusWifiSignalExImpl$bindEx$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements OplusWifiSignalEx.OplusWifiViewBinding {
        public /* synthetic */ Ref.BooleanRef $isCollecting;

        public final boolean isCollecting() {
            return this.$isCollecting.element;
        }

        public final boolean oplusBinder() {
            return true;
        }
    }

    public OplusWifiSignalExImpl(Context context, OplusWifiViewModel oplusWifiViewModel, OplusWifiRepository oplusWifiRepository, CoroutineDispatcher coroutineDispatcher) {
        super(context);
        this.context = context;
        this.oplusWifiViewModel = oplusWifiViewModel;
        this.oplusWifiRepository = oplusWifiRepository;
        this.bgDispatcher = coroutineDispatcher;
    }

    public OplusWifiSignalEx.OplusWifiViewBinding bindEx(ViewGroup viewGroup, MutableStateFlow mutableStateFlow, LocationBasedWifiViewModel locationBasedWifiViewModel) {
        ImageView imageView = (ImageView) viewGroup.requireViewById(2131366082);
        ImageView imageView2 = (ImageView) viewGroup.requireViewById(2131366083);
        TextView textView = (TextView) viewGroup.requireViewById(2131366084);
        ImageView imageView3 = (ImageView) viewGroup.requireViewById(2131366093);
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(this.bgDispatcher), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        int iIntValue = ((Number) this.oplusWifiViewModel.getWifiLeftResId().getValue()).intValue();
        if (iIntValue > 0) {
            imageView2.setImageResource(iIntValue);
            imageView2.setVisibility(0);
            textView.setVisibility(8);
        } else {
            imageView2.setVisibility(8);
        }
        String str = (String) this.oplusWifiViewModel.getWifiLeftText().getValue();
        if (str != null) {
            textView.setText(str);
            textView.setVisibility(0);
            imageView2.setVisibility(8);
        } else {
            textView.setVisibility(8);
        }
        int iIntValue2 = ((Number) this.oplusWifiViewModel.getWifiActivityResId().getValue()).intValue();
        if (iIntValue2 > 0) {
            imageView.setImageResource(iIntValue2);
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
        int iIntValue3 = ((Number) mutableStateFlow.getValue()).intValue();
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(iIntValue3);
        imageView3.setImageTintList(colorStateListValueOf);
        imageView.setImageTintList(colorStateListValueOf);
        imageView2.setImageTintList(colorStateListValueOf);
        textView.setTextColor(iIntValue3);
        RepeatWhenAttachedKt.repeatWhenAttached$default(viewGroup, (CoroutineContext) null, new AnonymousClass2(booleanRef, FlowConflatedKt.conflatedCallbackFlow(new OplusWifiSignalExImpl$bindEx$typefaceCallback$1(this, null)), textView, this, imageView2, imageView, mutableStateFlow, imageView3, null), 3);
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.$isCollecting = booleanRef;
        VarHandle.storeStoreFence();
        return anonymousClass3;
    }

    public ModernStatusBarWifiView inflateWifiView(Context context, StatusBarLocation statusBarLocation) {
        return (OplusModernStatusBarWifiView) LayoutInflater.from(context).inflate(2131559189, (ViewGroup) null);
    }

    public final boolean isShowWifiIndicatorWhenEnabled() {
        if (!CustomizeFeatureOption.sWlanIconAlwaysOn) {
            return false;
        }
        OplusStatusBarRomUpdateManager companion = OplusStatusBarRomUpdateManager.Companion.getInstance(this.context);
        return companion != null ? companion.getShowWifiIndicatorWhenEnabled() : true;
    }

    public Flow isUseWifiStyle17Flow() {
        StateFlow wifiThemeAdapterState = this.oplusWifiRepository.getWifiThemeAdapterState();
        OplusWifiSignalExImpl$isUseWifiStyle17Flow$$inlined$map$1 oplusWifiSignalExImpl$isUseWifiStyle17Flow$$inlined$map$1 = new OplusWifiSignalExImpl$isUseWifiStyle17Flow$$inlined$map$1();
        oplusWifiSignalExImpl$isUseWifiStyle17Flow$$inlined$map$1.$this_unsafeTransform$inlined = wifiThemeAdapterState;
        VarHandle.storeStoreFence();
        return oplusWifiSignalExImpl$isUseWifiStyle17Flow$$inlined$map$1;
    }

    public int wifiSignalResId(WifiNetworkModel.Active active) {
        WifiThemeAdapterState wifiThemeAdapterState = (WifiThemeAdapterState) this.oplusWifiRepository.getWifiThemeAdapterState().getValue();
        return (wifiThemeAdapterState.isAdaptOs17Independent || !wifiThemeAdapterState.isAdaptOs16Independent) ? OplusWifiIconSets.INSTANCE.getWIFI_SIGNAL_O17()[active.getLevel()] : OplusWifiIconSets.INSTANCE.getWIFI_SIGNAL_O16()[active.getLevel()];
    }
}
