package com.oplus.systemui.statusbar.pipeline.battery.ui.binder;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Trace;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.app.animation.Interpolators;
import com.android.systemui.DualToneHandler;
import com.android.systemui.FontSizeUtils;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.oplus.systemui.aod.display.AODDisplayUtil;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableBase;
import com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.BatteryContentDrawableFactory;
import com.oplus.systemui.statusbar.pipeline.battery.ui.drawable.HorizontalBatteryContentDrawable;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.BatteryIconStyle;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.ChargeIcon;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.OldHorizontal;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.PercentOutIcon;
import com.oplus.systemui.statusbar.pipeline.battery.ui.model.StatBatteryIcon;
import com.oplus.systemui.statusbar.pipeline.battery.ui.view.StatBatteryMeterView;
import com.oplus.systemui.statusbar.pipeline.battery.ui.viewmodel.LocationBasedBatteryViewModel;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.text.NumberFormat;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BatteryViewBinder {
    public static final BatteryViewBinder INSTANCE = new BatteryViewBinder();
    private static final String TAG = "BatteryViewBinder";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Binding {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function3 {
        final /* synthetic */ ImageView $batteryCharge;
        final /* synthetic */ ImageView $batteryChargeReverse;
        final /* synthetic */ ImageView $batteryIconView;
        final /* synthetic */ TextView $batteryPercentInView;
        final /* synthetic */ TextView $batteryPercentOutView;
        final /* synthetic */ ProgressBar $progressBar;
        final /* synthetic */ FrameLayout $statBatteryView;
        final /* synthetic */ StatBatteryMeterView $view;
        final /* synthetic */ LocationBasedBatteryViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C01491 extends SuspendLambda implements Function2 {
            final /* synthetic */ ImageView $batteryCharge;
            final /* synthetic */ ImageView $batteryChargeReverse;
            final /* synthetic */ ImageView $batteryIconView;
            final /* synthetic */ TextView $batteryPercentInView;
            final /* synthetic */ TextView $batteryPercentOutView;
            final /* synthetic */ ProgressBar $progressBar;
            final /* synthetic */ FrameLayout $statBatteryView;
            final /* synthetic */ StatBatteryMeterView $view;
            final /* synthetic */ LocationBasedBatteryViewModel $viewModel;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C01501 extends SuspendLambda implements Function2 {
                final /* synthetic */ StatBatteryMeterView $view;
                final /* synthetic */ LocationBasedBatteryViewModel $viewModel;
                int label;

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$1$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C01511 implements FlowCollector {
                    public final /* synthetic */ int $r8$classId;
                    public /* synthetic */ StatBatteryMeterView $view;
                    public /* synthetic */ LocationBasedBatteryViewModel $viewModel;

                    public /* synthetic */ C01511(int i) {
                        this.$r8$classId = i;
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
                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                ((Boolean) obj).booleanValue();
                                StatBatteryMeterView statBatteryMeterView = this.$view;
                                LocationBasedBatteryViewModel locationBasedBatteryViewModel = this.$viewModel;
                                if (statBatteryMeterView.getAllowLayoutTransition() != ((Boolean) locationBasedBatteryViewModel.getAllowLayoutTransition().getValue()).booleanValue()) {
                                    BatteryViewBinder batteryViewBinder = BatteryViewBinder.INSTANCE;
                                    boolean zBooleanValue = ((Boolean) locationBasedBatteryViewModel.getAllowLayoutTransition().getValue()).booleanValue();
                                    String moduleTag = locationBasedBatteryViewModel.getModuleTag();
                                    batteryViewBinder.getClass();
                                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                                        Log.d("SystemUi--Statusbar", "BatteryViewBinder-->".concat(moduleTag + " setupLayoutTransition view:" + statBatteryMeterView + " " + zBooleanValue));
                                    }
                                    if (zBooleanValue) {
                                        Object tag = statBatteryMeterView.getTag(2131365595);
                                        LayoutTransition layoutTransition = tag instanceof LayoutTransition ? (LayoutTransition) tag : null;
                                        if (layoutTransition == null) {
                                            layoutTransition = new LayoutTransition();
                                            layoutTransition.setDuration(200L);
                                            Property property = View.ALPHA;
                                            layoutTransition.setAnimator(2, ObjectAnimator.ofFloat((Object) null, (Property<Object, Float>) property, 0.0f, 1.0f));
                                            layoutTransition.setInterpolator(2, Interpolators.ALPHA_IN);
                                            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat((Object) null, (Property<Object, Float>) property, 1.0f, 0.0f);
                                            layoutTransition.setInterpolator(3, Interpolators.ALPHA_OUT);
                                            layoutTransition.setAnimator(3, objectAnimatorOfFloat);
                                            statBatteryMeterView.setTag(2131365595, layoutTransition);
                                            BatteryViewBinder$setupLayoutTransition$layoutTransition$1$1 batteryViewBinder$setupLayoutTransition$layoutTransition$1$1 = new BatteryViewBinder$setupLayoutTransition$layoutTransition$1$1();
                                            batteryViewBinder$setupLayoutTransition$layoutTransition$1$1.$view = statBatteryMeterView;
                                            batteryViewBinder$setupLayoutTransition$layoutTransition$1$1.$moduleTag = moduleTag;
                                            VarHandle.storeStoreFence();
                                            layoutTransition.addTransitionListener(batteryViewBinder$setupLayoutTransition$layoutTransition$1$1);
                                        }
                                        statBatteryMeterView.setLayoutTransition(layoutTransition);
                                    } else {
                                        if (statBatteryMeterView.getLayoutTransition() != null && statBatteryMeterView.getLayoutTransition().isRunning()) {
                                            statBatteryMeterView.getLayoutTransition().endChangingAnimations();
                                        }
                                        statBatteryMeterView.setLayoutTransition(null);
                                    }
                                    statBatteryMeterView.setAllowLayoutTransition(((Boolean) locationBasedBatteryViewModel.getAllowLayoutTransition().getValue()).booleanValue());
                                }
                                break;
                            case 1:
                                ((Boolean) obj).booleanValue();
                                StatBatteryMeterView statBatteryMeterView2 = this.$view;
                                if (statBatteryMeterView2.isShown() && AODDisplayUtil.getInstance(statBatteryMeterView2.getContext()).isPanoramicProcessType()) {
                                    LocationBasedBatteryViewModel locationBasedBatteryViewModel2 = this.$viewModel;
                                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                                        MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("aodBatteryUpdate moduleTag:", locationBasedBatteryViewModel2.getModuleTag(), "BatteryViewBinder-->", "SystemUi--Statusbar");
                                    }
                                    SmoothTransitionController.Companion.getInstance(statBatteryMeterView2.getContext()).requestScreenStateForSysUiComponent(BatteryViewBinder.TAG, 100);
                                }
                                break;
                            default:
                                StatBatteryIcon statBatteryIcon = (StatBatteryIcon) obj;
                                Trace.traceBegin(8L, this.$viewModel.getModuleTag() + "#BatteryViewBinder#updateBatteryContentView");
                                StatBatteryMeterView statBatteryMeterView3 = this.$view;
                                if (!Intrinsics.areEqual(statBatteryMeterView3.getBatteryIcon(), statBatteryIcon)) {
                                    statBatteryMeterView3.setBatteryIcon(statBatteryIcon);
                                    BatteryViewBinder.bind$updateBatteryContentView(statBatteryMeterView3, statBatteryIcon);
                                }
                                Trace.traceEnd(8L);
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01501(LocationBasedBatteryViewModel locationBasedBatteryViewModel, StatBatteryMeterView statBatteryMeterView, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedBatteryViewModel;
                    this.$view = statBatteryMeterView;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C01501(this.$viewModel, this.$view, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        MutableStateFlow allowLayoutTransition = this.$viewModel.getAllowLayoutTransition();
                        StatBatteryMeterView statBatteryMeterView = this.$view;
                        LocationBasedBatteryViewModel locationBasedBatteryViewModel = this.$viewModel;
                        C01511 c01511 = new C01511(0);
                        c01511.$view = statBatteryMeterView;
                        c01511.$viewModel = locationBasedBatteryViewModel;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (allowLayoutTransition.collect(c01511, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$1$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                final /* synthetic */ ImageView $batteryCharge;
                final /* synthetic */ ImageView $batteryChargeReverse;
                final /* synthetic */ ImageView $batteryIconView;
                final /* synthetic */ TextView $batteryPercentInView;
                final /* synthetic */ TextView $batteryPercentOutView;
                final /* synthetic */ FrameLayout $statBatteryView;
                final /* synthetic */ StatBatteryMeterView $view;
                final /* synthetic */ LocationBasedBatteryViewModel $viewModel;
                int label;

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$1$1$2$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C01521 implements FlowCollector {
                    public /* synthetic */ ImageView $batteryCharge;
                    public /* synthetic */ ImageView $batteryChargeReverse;
                    public /* synthetic */ ImageView $batteryIconView;
                    public /* synthetic */ TextView $batteryPercentInView;
                    public /* synthetic */ TextView $batteryPercentOutView;
                    public /* synthetic */ FrameLayout $statBatteryView;
                    public /* synthetic */ StatBatteryMeterView $view;
                    public /* synthetic */ LocationBasedBatteryViewModel $viewModel;

                    public final Object emit(Object obj, Continuation continuation) {
                        BatteryIconStyle batteryIconStyle = (BatteryIconStyle) obj;
                        LocationBasedBatteryViewModel locationBasedBatteryViewModel = this.$viewModel;
                        Trace.traceBegin(8L, locationBasedBatteryViewModel.getModuleTag() + "#BatteryViewBinder#updateBatteryIconStyle");
                        StatBatteryMeterView statBatteryMeterView = this.$view;
                        if (!Intrinsics.areEqual(statBatteryMeterView.getBatteryIconStyle(), batteryIconStyle)) {
                            BatteryIconStyle batteryIconStyle2 = statBatteryMeterView.getBatteryIconStyle();
                            if (!Intrinsics.areEqual(batteryIconStyle2 != null ? Boxing.boxInt(batteryIconStyle2.getDensityDpi()) : null, batteryIconStyle != null ? Boxing.boxInt(batteryIconStyle.getDensityDpi()) : null)) {
                                TextView textView = this.$batteryPercentOutView;
                                ImageView imageView = this.$batteryChargeReverse;
                                ImageView imageView2 = this.$batteryCharge;
                                FontSizeUtils.updateFontSize(textView, 2131165635);
                                if (imageView != null && imageView.getVisibility() == 0) {
                                    imageView.setImageDrawable(statBatteryMeterView.getContext().getDrawable(2131236729));
                                }
                                ChargeIcon chargeIcon = (ChargeIcon) locationBasedBatteryViewModel.getBatteryCharge().getValue();
                                if (chargeIcon != null && chargeIcon.getIconId() > 0 && imageView2 != null && imageView2.getVisibility() == 0) {
                                    imageView2.setImageDrawable(statBatteryMeterView.getContext().getDrawable(chargeIcon.getIconId()));
                                }
                            }
                            statBatteryMeterView.setBatteryIconStyle(batteryIconStyle);
                            BatteryViewBinder.bind$updateBatteryIconStyle(this.$batteryIconView, this.$view, this.$viewModel, this.$statBatteryView, this.$batteryPercentInView, batteryIconStyle);
                        }
                        Trace.traceEnd(8L);
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(LocationBasedBatteryViewModel locationBasedBatteryViewModel, StatBatteryMeterView statBatteryMeterView, TextView textView, ImageView imageView, ImageView imageView2, ImageView imageView3, FrameLayout frameLayout, TextView textView2, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedBatteryViewModel;
                    this.$view = statBatteryMeterView;
                    this.$batteryPercentOutView = textView;
                    this.$batteryChargeReverse = imageView;
                    this.$batteryCharge = imageView2;
                    this.$batteryIconView = imageView3;
                    this.$statBatteryView = frameLayout;
                    this.$batteryPercentInView = textView2;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass2(this.$viewModel, this.$view, this.$batteryPercentOutView, this.$batteryChargeReverse, this.$batteryCharge, this.$batteryIconView, this.$statBatteryView, this.$batteryPercentInView, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        MutableStateFlow batteryIconViewStyle = this.$viewModel.getBatteryIconViewStyle();
                        LocationBasedBatteryViewModel locationBasedBatteryViewModel = this.$viewModel;
                        StatBatteryMeterView statBatteryMeterView = this.$view;
                        TextView textView = this.$batteryPercentOutView;
                        ImageView imageView = this.$batteryChargeReverse;
                        ImageView imageView2 = this.$batteryCharge;
                        ImageView imageView3 = this.$batteryIconView;
                        FrameLayout frameLayout = this.$statBatteryView;
                        TextView textView2 = this.$batteryPercentInView;
                        C01521 c01521 = new C01521();
                        c01521.$viewModel = locationBasedBatteryViewModel;
                        c01521.$view = statBatteryMeterView;
                        c01521.$batteryPercentOutView = textView;
                        c01521.$batteryChargeReverse = imageView;
                        c01521.$batteryCharge = imageView2;
                        c01521.$batteryIconView = imageView3;
                        c01521.$statBatteryView = frameLayout;
                        c01521.$batteryPercentInView = textView2;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (batteryIconViewStyle.collect(c01521, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$1$1$3, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass3 extends SuspendLambda implements Function2 {
                final /* synthetic */ StatBatteryMeterView $view;
                final /* synthetic */ LocationBasedBatteryViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(LocationBasedBatteryViewModel locationBasedBatteryViewModel, StatBatteryMeterView statBatteryMeterView, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedBatteryViewModel;
                    this.$view = statBatteryMeterView;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass3(this.$viewModel, this.$view, continuation);
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
                        MutableSharedFlow mutableSharedFlowIsLevelOrChargeUpdate = this.$viewModel.isLevelOrChargeUpdate();
                        StatBatteryMeterView statBatteryMeterView = this.$view;
                        LocationBasedBatteryViewModel locationBasedBatteryViewModel = this.$viewModel;
                        C01501.C01511 c01511 = new C01501.C01511(i2);
                        c01511.$view = statBatteryMeterView;
                        c01511.$viewModel = locationBasedBatteryViewModel;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (mutableSharedFlowIsLevelOrChargeUpdate.collect(c01511, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$1$1$4, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass4 extends SuspendLambda implements Function2 {
                final /* synthetic */ TextView $batteryPercentOutView;
                final /* synthetic */ StatBatteryMeterView $view;
                final /* synthetic */ LocationBasedBatteryViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass4(LocationBasedBatteryViewModel locationBasedBatteryViewModel, StatBatteryMeterView statBatteryMeterView, TextView textView, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedBatteryViewModel;
                    this.$view = statBatteryMeterView;
                    this.$batteryPercentOutView = textView;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass4(this.$viewModel, this.$view, this.$batteryPercentOutView, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        MutableStateFlow batteryPercentOut = this.$viewModel.getBatteryPercentOut();
                        LocationBasedBatteryViewModel locationBasedBatteryViewModel = this.$viewModel;
                        StatBatteryMeterView statBatteryMeterView = this.$view;
                        TextView textView = this.$batteryPercentOutView;
                        AnonymousClass5.C01531 c01531 = new AnonymousClass5.C01531(2);
                        c01531.$viewModel = locationBasedBatteryViewModel;
                        c01531.$view = statBatteryMeterView;
                        c01531.$batteryCharge = textView;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (batteryPercentOut.collect(c01531, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$1$1$5, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass5 extends SuspendLambda implements Function2 {
                final /* synthetic */ ImageView $batteryCharge;
                final /* synthetic */ StatBatteryMeterView $view;
                final /* synthetic */ LocationBasedBatteryViewModel $viewModel;
                int label;

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$1$1$5$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C01531 implements FlowCollector {
                    public /* synthetic */ Object $batteryCharge;
                    public final /* synthetic */ int $r8$classId;
                    public /* synthetic */ StatBatteryMeterView $view;
                    public /* synthetic */ LocationBasedBatteryViewModel $viewModel;

                    public /* synthetic */ C01531(int i) {
                        this.$r8$classId = i;
                    }

                    public final Object emit(Object obj, Continuation continuation) {
                        switch (this.$r8$classId) {
                            case 0:
                                ChargeIcon chargeIcon = (ChargeIcon) obj;
                                Trace.traceBegin(8L, this.$viewModel.getModuleTag() + "#BatteryViewBinder#updateChargingView");
                                StatBatteryMeterView statBatteryMeterView = this.$view;
                                if (!Intrinsics.areEqual(statBatteryMeterView.getBatteryCharge(), chargeIcon)) {
                                    BatteryViewBinder.bind$updateChargingView((ImageView) this.$batteryCharge, statBatteryMeterView, chargeIcon);
                                    statBatteryMeterView.setBatteryCharge(chargeIcon);
                                }
                                Trace.traceEnd(8L);
                                break;
                            case 1:
                                ChargeIcon chargeIcon2 = (ChargeIcon) obj;
                                Trace.traceBegin(8L, this.$viewModel.getModuleTag() + "#BatteryViewBinder#updateReverseChargeView");
                                StatBatteryMeterView statBatteryMeterView2 = this.$view;
                                if (!Intrinsics.areEqual(statBatteryMeterView2.getBatteryReverse(), chargeIcon2)) {
                                    BatteryViewBinder.bind$updateReverseChargeView((ImageView) this.$batteryCharge, statBatteryMeterView2, chargeIcon2);
                                    statBatteryMeterView2.setBatteryReverse(chargeIcon2);
                                }
                                Trace.traceEnd(8L);
                                break;
                            default:
                                PercentOutIcon percentOutIcon = (PercentOutIcon) obj;
                                Trace.traceBegin(8L, this.$viewModel.getModuleTag() + "#BatteryViewBinder#updatePercentOutView");
                                StatBatteryMeterView statBatteryMeterView3 = this.$view;
                                if (!Intrinsics.areEqual(statBatteryMeterView3.getBatteryPercentOut(), percentOutIcon)) {
                                    statBatteryMeterView3.setBatteryPercentOut(percentOutIcon);
                                    BatteryViewBinder.bind$updatePercentOutView((TextView) this.$batteryCharge, statBatteryMeterView3, percentOutIcon);
                                }
                                Trace.traceEnd(8L);
                                break;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass5(LocationBasedBatteryViewModel locationBasedBatteryViewModel, StatBatteryMeterView statBatteryMeterView, ImageView imageView, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedBatteryViewModel;
                    this.$view = statBatteryMeterView;
                    this.$batteryCharge = imageView;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass5(this.$viewModel, this.$view, this.$batteryCharge, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        MutableStateFlow batteryCharge = this.$viewModel.getBatteryCharge();
                        LocationBasedBatteryViewModel locationBasedBatteryViewModel = this.$viewModel;
                        StatBatteryMeterView statBatteryMeterView = this.$view;
                        ImageView imageView = this.$batteryCharge;
                        C01531 c01531 = new C01531(0);
                        c01531.$viewModel = locationBasedBatteryViewModel;
                        c01531.$view = statBatteryMeterView;
                        c01531.$batteryCharge = imageView;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (batteryCharge.collect(c01531, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$1$1$6, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass6 extends SuspendLambda implements Function2 {
                final /* synthetic */ StatBatteryMeterView $view;
                final /* synthetic */ LocationBasedBatteryViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass6(LocationBasedBatteryViewModel locationBasedBatteryViewModel, StatBatteryMeterView statBatteryMeterView, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedBatteryViewModel;
                    this.$view = statBatteryMeterView;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass6(this.$viewModel, this.$view, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        MutableStateFlow batteryIcon = this.$viewModel.getBatteryIcon();
                        LocationBasedBatteryViewModel locationBasedBatteryViewModel = this.$viewModel;
                        StatBatteryMeterView statBatteryMeterView = this.$view;
                        C01501.C01511 c01511 = new C01501.C01511(2);
                        c01511.$viewModel = locationBasedBatteryViewModel;
                        c01511.$view = statBatteryMeterView;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (batteryIcon.collect(c01511, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$1$1$7, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass7 extends SuspendLambda implements Function2 {
                final /* synthetic */ ImageView $batteryChargeReverse;
                final /* synthetic */ StatBatteryMeterView $view;
                final /* synthetic */ LocationBasedBatteryViewModel $viewModel;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass7(LocationBasedBatteryViewModel locationBasedBatteryViewModel, StatBatteryMeterView statBatteryMeterView, ImageView imageView, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedBatteryViewModel;
                    this.$view = statBatteryMeterView;
                    this.$batteryChargeReverse = imageView;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass7(this.$viewModel, this.$view, this.$batteryChargeReverse, continuation);
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
                        MutableStateFlow reverseCharge = this.$viewModel.getReverseCharge();
                        LocationBasedBatteryViewModel locationBasedBatteryViewModel = this.$viewModel;
                        StatBatteryMeterView statBatteryMeterView = this.$view;
                        ImageView imageView = this.$batteryChargeReverse;
                        AnonymousClass5.C01531 c01531 = new AnonymousClass5.C01531(i2);
                        c01531.$viewModel = locationBasedBatteryViewModel;
                        c01531.$view = statBatteryMeterView;
                        c01531.$batteryCharge = imageView;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (reverseCharge.collect(c01531, this) == coroutine_suspended) {
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

            /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$1$1$8, reason: invalid class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class AnonymousClass8 extends SuspendLambda implements Function2 {
                final /* synthetic */ TextView $batteryPercentInView;
                final /* synthetic */ ProgressBar $progressBar;
                final /* synthetic */ StatBatteryMeterView $view;
                final /* synthetic */ LocationBasedBatteryViewModel $viewModel;
                int label;

                /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$1$1$8$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C01541 implements FlowCollector {
                    public /* synthetic */ TextView $batteryPercentInView;
                    public /* synthetic */ ProgressBar $progressBar;
                    public /* synthetic */ StatBatteryMeterView $view;
                    public /* synthetic */ LocationBasedBatteryViewModel $viewModel;

                    public final Object emit(Object obj, Continuation continuation) {
                        OldHorizontal oldHorizontal = (OldHorizontal) obj;
                        Trace.traceBegin(8L, "BatteryViewBinder#" + this.$viewModel.getModuleTag() + "#updateOldHorizontalViewContent");
                        StatBatteryMeterView statBatteryMeterView = this.$view;
                        if (!Intrinsics.areEqual(statBatteryMeterView.getOldHorizontal(), oldHorizontal)) {
                            statBatteryMeterView.setOldHorizontal(oldHorizontal);
                            BatteryViewBinder.bind$updateOldHorizontalViewContent(this.$progressBar, this.$batteryPercentInView, oldHorizontal);
                        }
                        Trace.traceEnd(8L);
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass8(LocationBasedBatteryViewModel locationBasedBatteryViewModel, StatBatteryMeterView statBatteryMeterView, ProgressBar progressBar, TextView textView, Continuation continuation) {
                    super(2, continuation);
                    this.$viewModel = locationBasedBatteryViewModel;
                    this.$view = statBatteryMeterView;
                    this.$progressBar = progressBar;
                    this.$batteryPercentInView = textView;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass8(this.$viewModel, this.$view, this.$progressBar, this.$batteryPercentInView, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        MutableStateFlow oldHorizontal = this.$viewModel.getOldHorizontal();
                        LocationBasedBatteryViewModel locationBasedBatteryViewModel = this.$viewModel;
                        StatBatteryMeterView statBatteryMeterView = this.$view;
                        ProgressBar progressBar = this.$progressBar;
                        TextView textView = this.$batteryPercentInView;
                        C01541 c01541 = new C01541();
                        c01541.$viewModel = locationBasedBatteryViewModel;
                        c01541.$view = statBatteryMeterView;
                        c01541.$progressBar = progressBar;
                        c01541.$batteryPercentInView = textView;
                        VarHandle.storeStoreFence();
                        this.label = 1;
                        if (oldHorizontal.collect(c01541, this) == coroutine_suspended) {
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
            public C01491(FrameLayout frameLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ProgressBar progressBar, TextView textView, TextView textView2, StatBatteryMeterView statBatteryMeterView, LocationBasedBatteryViewModel locationBasedBatteryViewModel, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = locationBasedBatteryViewModel;
                this.$view = statBatteryMeterView;
                this.$batteryPercentOutView = textView;
                this.$batteryChargeReverse = imageView;
                this.$batteryCharge = imageView2;
                this.$batteryIconView = imageView3;
                this.$statBatteryView = frameLayout;
                this.$batteryPercentInView = textView2;
                this.$progressBar = progressBar;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                LocationBasedBatteryViewModel locationBasedBatteryViewModel = this.$viewModel;
                StatBatteryMeterView statBatteryMeterView = this.$view;
                TextView textView = this.$batteryPercentOutView;
                ImageView imageView = this.$batteryChargeReverse;
                ImageView imageView2 = this.$batteryCharge;
                ImageView imageView3 = this.$batteryIconView;
                C01491 c01491 = new C01491(this.$statBatteryView, imageView, imageView2, imageView3, this.$progressBar, textView, this.$batteryPercentInView, statBatteryMeterView, locationBasedBatteryViewModel, continuation);
                c01491.L$0 = obj;
                return c01491;
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
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C01501(this.$viewModel, this.$view, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(this.$viewModel, this.$view, this.$batteryPercentOutView, this.$batteryChargeReverse, this.$batteryCharge, this.$batteryIconView, this.$statBatteryView, this.$batteryPercentInView, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(this.$viewModel, this.$view, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass4(this.$viewModel, this.$view, this.$batteryPercentOutView, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass5(this.$viewModel, this.$view, this.$batteryCharge, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass6(this.$viewModel, this.$view, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass7(this.$viewModel, this.$view, this.$batteryChargeReverse, null), 3);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass8(this.$viewModel, this.$view, this.$progressBar, this.$batteryPercentInView, null), 3);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(FrameLayout frameLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ProgressBar progressBar, TextView textView, TextView textView2, StatBatteryMeterView statBatteryMeterView, LocationBasedBatteryViewModel locationBasedBatteryViewModel, Continuation continuation) {
            super(3, continuation);
            this.$view = statBatteryMeterView;
            this.$viewModel = locationBasedBatteryViewModel;
            this.$batteryPercentOutView = textView;
            this.$batteryChargeReverse = imageView;
            this.$batteryCharge = imageView2;
            this.$batteryIconView = imageView3;
            this.$statBatteryView = frameLayout;
            this.$batteryPercentInView = textView2;
            this.$progressBar = progressBar;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            StatBatteryMeterView statBatteryMeterView = this.$view;
            LocationBasedBatteryViewModel locationBasedBatteryViewModel = this.$viewModel;
            TextView textView = this.$batteryPercentOutView;
            ImageView imageView = this.$batteryChargeReverse;
            ImageView imageView2 = this.$batteryCharge;
            ImageView imageView3 = this.$batteryIconView;
            FrameLayout frameLayout = this.$statBatteryView;
            TextView textView2 = this.$batteryPercentInView;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(frameLayout, imageView, imageView2, imageView3, this.$progressBar, textView, textView2, statBatteryMeterView, locationBasedBatteryViewModel, (Continuation) obj3);
            anonymousClass1.L$0 = (LifecycleOwner) obj;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$1, kotlin.coroutines.Continuation] */
        /* JADX WARN: Type inference failed for: r1v1, types: [com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$1] */
        /* JADX WARN: Type inference failed for: r1v3, types: [android.view.ViewGroup, com.oplus.systemui.statusbar.pipeline.battery.ui.view.StatBatteryMeterView] */
        public final Object invokeSuspend(Object obj) {
            ?? r1 = this;
            LifecycleOwner lifecycleOwner = (LifecycleOwner) r1.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = r1.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Lifecycle.State state = Lifecycle.State.STARTED;
                    LocationBasedBatteryViewModel locationBasedBatteryViewModel = r1.$viewModel;
                    StatBatteryMeterView statBatteryMeterView = r1.$view;
                    TextView textView = r1.$batteryPercentOutView;
                    ImageView imageView = r1.$batteryChargeReverse;
                    ImageView imageView2 = r1.$batteryCharge;
                    ImageView imageView3 = r1.$batteryIconView;
                    C01491 c01491 = new C01491(r1.$statBatteryView, imageView, imageView2, imageView3, r1.$progressBar, textView, r1.$batteryPercentInView, statBatteryMeterView, locationBasedBatteryViewModel, null);
                    r1.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                    r1.label = 1;
                    if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, c01491, (Continuation) r1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                BatteryViewBinder batteryViewBinder = BatteryViewBinder.INSTANCE;
                r1 = r1.$view;
                batteryViewBinder.getClass();
                r1.setLayoutTransition(null);
                r1.setTag(2131365595, null);
                return Unit.INSTANCE;
            } catch (Throwable th) {
                BatteryViewBinder batteryViewBinder2 = BatteryViewBinder.INSTANCE;
                StatBatteryMeterView statBatteryMeterView2 = r1.$view;
                batteryViewBinder2.getClass();
                statBatteryMeterView2.setLayoutTransition(null);
                statBatteryMeterView2.setTag(2131365595, null);
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.pipeline.battery.ui.binder.BatteryViewBinder$bind$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Binding {
        public /* synthetic */ DualToneHandler $dualToneHandler;
        public /* synthetic */ LocationBasedBatteryViewModel $viewModel;
    }

    public static final void access$updateChildrenPosition(BatteryViewBinder batteryViewBinder, ViewGroup viewGroup, View view, boolean z) {
        batteryViewBinder.getClass();
        int iIndexOfChild = viewGroup.indexOfChild(view);
        if (z) {
            int right = view.getRight();
            for (int i = iIndexOfChild - 1; -1 < i; i--) {
                View childAt = viewGroup.getChildAt(i);
                childAt.setLeft(right);
                childAt.setRight(childAt.getMeasuredWidth() + right);
                right = childAt.getRight();
            }
            return;
        }
        int left = view.getLeft();
        for (int i2 = iIndexOfChild - 1; -1 < i2; i2--) {
            View childAt2 = viewGroup.getChildAt(i2);
            childAt2.setRight(left);
            childAt2.setLeft(childAt2.getRight() - childAt2.getMeasuredWidth());
            left = childAt2.getLeft();
        }
    }

    public static final Binding bind(StatBatteryMeterView statBatteryMeterView, LocationBasedBatteryViewModel locationBasedBatteryViewModel) {
        ImageView imageView = (ImageView) statBatteryMeterView.findViewById(2131362241);
        TextView textView = (TextView) statBatteryMeterView.findViewById(2131362245);
        ImageView imageView2 = (ImageView) statBatteryMeterView.findViewById(2131362236);
        ImageView imageView3 = (ImageView) statBatteryMeterView.findViewById(2131362237);
        DualToneHandler dualToneHandler = new DualToneHandler(statBatteryMeterView.getContext());
        FrameLayout frameLayout = (FrameLayout) statBatteryMeterView.findViewById(2131362239);
        TextView textView2 = (TextView) statBatteryMeterView.findViewById(2131362247);
        ProgressBar progressBar = (ProgressBar) statBatteryMeterView.findViewById(2131365351);
        bind$updateBatteryIconStyle(imageView, statBatteryMeterView, locationBasedBatteryViewModel, frameLayout, textView2, (BatteryIconStyle) locationBasedBatteryViewModel.getBatteryIconViewStyle().getValue());
        bind$updatePercentOutView(textView, statBatteryMeterView, (PercentOutIcon) locationBasedBatteryViewModel.getBatteryPercentOut().getValue());
        bind$updateChargingView(imageView2, statBatteryMeterView, (ChargeIcon) locationBasedBatteryViewModel.getBatteryCharge().getValue());
        bind$updateReverseChargeView(imageView3, statBatteryMeterView, (ChargeIcon) locationBasedBatteryViewModel.getReverseCharge().getValue());
        bind$updateOldHorizontalViewContent(progressBar, textView2, (OldHorizontal) locationBasedBatteryViewModel.getOldHorizontal().getValue());
        RepeatWhenAttachedKt.repeatWhenAttached$default(statBatteryMeterView, Dispatchers.getMain().getImmediate(), new AnonymousClass1(frameLayout, imageView3, imageView2, imageView, progressBar, textView, textView2, statBatteryMeterView, locationBasedBatteryViewModel, null), 2);
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.$viewModel = locationBasedBatteryViewModel;
        anonymousClass2.$dualToneHandler = dualToneHandler;
        VarHandle.storeStoreFence();
        return anonymousClass2;
    }

    public static final void bind$hideBatteryIcon(ImageView imageView, StatBatteryMeterView statBatteryMeterView) {
        if (imageView != null && imageView.getVisibility() == 0) {
            imageView.setVisibility(8);
        }
        BatteryContentDrawableBase batteryStyleDrawable = statBatteryMeterView.getBatteryStyleDrawable();
        if (batteryStyleDrawable != null) {
            batteryStyleDrawable.onDestroy();
        }
        statBatteryMeterView.setBatteryStyleDrawable(null);
        if ((imageView != null ? imageView.getDrawable() : null) != null) {
            imageView.setImageDrawable(null);
        }
    }

    public static final void bind$updateBatteryContentView(StatBatteryMeterView statBatteryMeterView, StatBatteryIcon statBatteryIcon) {
        if (statBatteryIcon == null) {
            return;
        }
        statBatteryMeterView.setContentDescription(statBatteryIcon.getContentDescription());
        if ((statBatteryIcon instanceof StatBatteryIcon.Horizontal) && (statBatteryMeterView.getBatteryStyleDrawable() instanceof HorizontalBatteryContentDrawable)) {
            HorizontalBatteryContentDrawable horizontalBatteryContentDrawable = (HorizontalBatteryContentDrawable) statBatteryMeterView.getBatteryStyleDrawable();
            StatBatteryIcon.Horizontal horizontal = (StatBatteryIcon.Horizontal) statBatteryIcon;
            horizontalBatteryContentDrawable.setShowPercentIn(horizontal.getPercentIn());
            horizontalBatteryContentDrawable.setChargingBgIconId(horizontal.getChargeBgIconId());
        }
        BatteryContentDrawableBase batteryStyleDrawable = statBatteryMeterView.getBatteryStyleDrawable();
        if (batteryStyleDrawable != null) {
            batteryStyleDrawable.setChargeIconId(statBatteryIcon.getChargeIconId());
            batteryStyleDrawable.setBatteryLevel(statBatteryIcon.getPowerLevel());
            batteryStyleDrawable.setSpecialColor(statBatteryIcon.getSpecialProgressColor());
        }
        BatteryContentDrawableBase batteryStyleDrawable2 = statBatteryMeterView.getBatteryStyleDrawable();
        if (batteryStyleDrawable2 != null) {
            batteryStyleDrawable2.setColors(statBatteryIcon.getProgressColor(), statBatteryIcon.getBackgroundColor(), statBatteryIcon.getTintColor());
        }
        BatteryContentDrawableBase batteryStyleDrawable3 = statBatteryMeterView.getBatteryStyleDrawable();
        if (batteryStyleDrawable3 != null) {
            batteryStyleDrawable3.invalidateSelf();
        }
    }

    public static final void bind$updateBatteryIconStyle(ImageView imageView, StatBatteryMeterView statBatteryMeterView, LocationBasedBatteryViewModel locationBasedBatteryViewModel, FrameLayout frameLayout, TextView textView, BatteryIconStyle batteryIconStyle) {
        ViewGroup.LayoutParams layoutParams = imageView != null ? imageView.getLayoutParams() : null;
        LinearLayout.LayoutParams layoutParams2 = layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : null;
        float scaleFactor = batteryIconStyle != null ? batteryIconStyle.getScaleFactor() : 1.0f;
        Integer numValueOf = batteryIconStyle != null ? Integer.valueOf(batteryIconStyle.getIconStyle()) : null;
        if (numValueOf != null && numValueOf.intValue() == 1) {
            if (imageView != null) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            }
            if (layoutParams2 != null) {
                layoutParams2.width = (int) (statBatteryMeterView.getContext().getResources().getDimensionPixelSize(2131172998) * scaleFactor);
            }
            if (layoutParams2 != null) {
                layoutParams2.height = (int) (statBatteryMeterView.getContext().getResources().getDimensionPixelSize(2131172989) * scaleFactor);
            }
        } else if (numValueOf != null && numValueOf.intValue() == 3) {
            if (imageView != null) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            }
            if (layoutParams2 != null) {
                layoutParams2.width = (int) (statBatteryMeterView.getContext().getResources().getDimensionPixelSize(2131173012) * scaleFactor);
            }
            if (layoutParams2 != null) {
                layoutParams2.height = (int) (statBatteryMeterView.getContext().getResources().getDimensionPixelSize(2131173007) * scaleFactor);
            }
        } else {
            if (numValueOf == null || numValueOf.intValue() != 4) {
                if (numValueOf != null && numValueOf.intValue() == 5) {
                    bind$hideBatteryIcon(imageView, statBatteryMeterView);
                    bind$updateOldHorizontal(frameLayout, statBatteryMeterView, textView, scaleFactor, true, batteryIconStyle.getOldStylePercentIn());
                    return;
                } else {
                    bind$hideBatteryIcon(imageView, statBatteryMeterView);
                    bind$updateOldHorizontal(frameLayout, statBatteryMeterView, textView, scaleFactor, false, false);
                    return;
                }
            }
            if (imageView != null) {
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
            if (layoutParams2 != null) {
                layoutParams2.width = (int) (statBatteryMeterView.getContext().getResources().getDimensionPixelSize(2131172986) * scaleFactor);
            }
            if (layoutParams2 != null) {
                layoutParams2.height = (int) (statBatteryMeterView.getContext().getResources().getDimensionPixelSize(2131172980) * scaleFactor);
            }
        }
        bind$updateOldHorizontal(frameLayout, statBatteryMeterView, textView, scaleFactor, false, false);
        BatteryContentDrawableBase batteryStyleDrawable = statBatteryMeterView.getBatteryStyleDrawable();
        if (batteryStyleDrawable != null) {
            batteryStyleDrawable.onDestroy();
        }
        BatteryContentDrawableBase batteryContentDrawableBaseCreateContentDrawable = BatteryContentDrawableFactory.INSTANCE.createContentDrawable(statBatteryMeterView.getContext(), batteryIconStyle.getIconStyle(), 0);
        if (batteryIconStyle.getIconStyle() == 1 && (batteryContentDrawableBaseCreateContentDrawable instanceof HorizontalBatteryContentDrawable)) {
            HorizontalBatteryContentDrawable horizontalBatteryContentDrawable = (HorizontalBatteryContentDrawable) batteryContentDrawableBaseCreateContentDrawable;
            horizontalBatteryContentDrawable.setFrameColorMode(batteryIconStyle.getFrameColorMode());
            horizontalBatteryContentDrawable.setThemeModeColor(batteryIconStyle.isThemeModeColor);
            horizontalBatteryContentDrawable.setPercentOutBgMode(batteryIconStyle.isPercentOutBgMode);
            horizontalBatteryContentDrawable.setPercentOutPbMode(batteryIconStyle.isPercentOutPbMode);
            horizontalBatteryContentDrawable.setBatteryOutChargeBgMode(batteryIconStyle.getBatteryOutChargeBgMode());
            horizontalBatteryContentDrawable.setThemeId(batteryIconStyle.getThemeId());
        }
        statBatteryMeterView.setBatteryStyleDrawable(batteryContentDrawableBaseCreateContentDrawable);
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        if (imageView != null) {
            imageView.setImageDrawable(statBatteryMeterView.getBatteryStyleDrawable());
        }
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, locationBasedBatteryViewModel.getModuleTag() + " create drawable: " + statBatteryMeterView.getBatteryStyleDrawable() + " iconStyle:" + batteryIconStyle, null, 8);
        bind$updateBatteryContentView(statBatteryMeterView, (StatBatteryIcon) locationBasedBatteryViewModel.getBatteryIcon().getValue());
        if (imageView != null) {
            imageView.requestLayout();
        }
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0034  */
    /* JADX WARN: Code duplicated, block: B:20:0x0036 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:22:0x0039  */
    /* JADX WARN: Code duplicated, block: B:31:0x0061  */
    public static final void bind$updateChargingView(ImageView imageView, StatBatteryMeterView statBatteryMeterView, ChargeIcon chargeIcon) {
        Drawable drawable;
        ChargeIcon batteryCharge;
        ChargeIcon batteryCharge2;
        if (chargeIcon != null) {
            boolean z = chargeIcon.isVisible;
            if (imageView == null) {
                if (imageView != null) {
                    imageView.setVisibility(z ? 0 : 8);
                }
                if (imageView != null && imageView.getVisibility() == 0 && chargeIcon.getIconId() > 0) {
                    imageView.setImageDrawable(statBatteryMeterView.getContext().getDrawable(chargeIcon.getIconId()));
                    drawable = imageView.getDrawable();
                    if (drawable != null) {
                        drawable.setAutoMirrored(true);
                    }
                }
            } else {
                if ((imageView.getVisibility() == 0) != z || (batteryCharge = statBatteryMeterView.getBatteryCharge()) == null || batteryCharge.getIconId() != chargeIcon.getIconId() || (batteryCharge2 = statBatteryMeterView.getBatteryCharge()) == null || batteryCharge2.getThemeId() != chargeIcon.getThemeId()) {
                    if (imageView != null) {
                        imageView.setVisibility(z ? 0 : 8);
                    }
                    if (imageView != null) {
                        imageView.setImageDrawable(statBatteryMeterView.getContext().getDrawable(chargeIcon.getIconId()));
                        drawable = imageView.getDrawable();
                        if (drawable != null) {
                            drawable.setAutoMirrored(true);
                        }
                    }
                }
            }
            if (imageView == null || imageView.getVisibility() != 0) {
                return;
            }
            imageView.setImageTintList(ColorStateList.valueOf(chargeIcon.getTintColor()));
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0012  */
    /* JADX WARN: Code duplicated, block: B:11:0x0014 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:12:0x0016  */
    /* JADX WARN: Code duplicated, block: B:13:0x0018  */
    public static final void bind$updateOldHorizontal(FrameLayout frameLayout, StatBatteryMeterView statBatteryMeterView, TextView textView, float f, boolean z, boolean z2) {
        int i;
        if (frameLayout != null) {
            if ((frameLayout.getVisibility() == 0) != z) {
                if (frameLayout != null) {
                    if (z) {
                        i = 0;
                    } else {
                        i = 8;
                    }
                    frameLayout.setVisibility(i);
                }
            }
        } else if (frameLayout != null) {
            if (z) {
                i = 0;
            } else {
                i = 8;
            }
            frameLayout.setVisibility(i);
        }
        if (z) {
            Resources resources = statBatteryMeterView.getContext().getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(2131165607);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(2131165608);
            int dimensionPixelSize3 = resources.getDimensionPixelSize(2131165612);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (dimensionPixelSize2 * f), (int) (dimensionPixelSize * f));
            layoutParams.setMarginStart((int) (dimensionPixelSize3 * f));
            if (frameLayout != null) {
                frameLayout.setLayoutParams(layoutParams);
            }
            FontSizeUtils.updateFontSize(textView, 2131165619);
            if (textView != null) {
                if ((textView.getVisibility() == 0) == z2) {
                    return;
                }
            }
            if (textView != null) {
                textView.setVisibility(z2 ? 0 : 8);
            }
        }
    }

    public static final void bind$updateOldHorizontalViewContent(ProgressBar progressBar, TextView textView, OldHorizontal oldHorizontal) {
        Drawable foreground;
        if (oldHorizontal != null) {
            if (progressBar != null) {
                progressBar.setForeground(oldHorizontal.getForegroundDrawable());
            }
            if (progressBar != null) {
                progressBar.setProgressDrawable(oldHorizontal.getProgressDrawable());
            }
            if (progressBar != null) {
                progressBar.setProgress(oldHorizontal.getPowerLevel());
            }
            if (textView != null) {
                textView.setTextColor(oldHorizontal.getTintColor());
            }
            BatteryViewBinder batteryViewBinder = INSTANCE;
            String str = NumberFormat.getIntegerInstance().format(oldHorizontal.getPowerLevel());
            batteryViewBinder.getClass();
            if (!Intrinsics.areEqual(str, textView != null ? textView.getText() : null) && textView != null) {
                textView.setText(str);
            }
            if (progressBar == null || (foreground = progressBar.getForeground()) == null) {
                return;
            }
            foreground.setTint(oldHorizontal.getTintColor());
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0014  */
    /* JADX WARN: Code duplicated, block: B:12:0x0016 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:14:0x0019  */
    public static final void bind$updatePercentOutView(TextView textView, StatBatteryMeterView statBatteryMeterView, PercentOutIcon percentOutIcon) {
        if (percentOutIcon != null) {
            boolean z = percentOutIcon.isVisible;
            if (textView == null) {
                if (textView != null) {
                    textView.setVisibility(z ? 0 : 8);
                }
                statBatteryMeterView.requestLayout();
            } else {
                if ((textView.getVisibility() == 0) != z) {
                    if (textView != null) {
                        textView.setVisibility(z ? 0 : 8);
                    }
                    statBatteryMeterView.requestLayout();
                }
            }
            if (z) {
                BatteryViewBinder batteryViewBinder = INSTANCE;
                String str = NumberFormat.getPercentInstance().format(percentOutIcon.getBatteryLevel() / 100.0f);
                batteryViewBinder.getClass();
                if (!Intrinsics.areEqual(str, textView != null ? textView.getText() : null) && textView != null) {
                    textView.setText(str);
                }
                if (textView != null) {
                    textView.setTextColor(percentOutIcon.getTintColor());
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0024  */
    /* JADX WARN: Code duplicated, block: B:16:0x0026 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:18:0x0029  */
    /* JADX WARN: Code duplicated, block: B:27:0x0051  */
    public static final void bind$updateReverseChargeView(ImageView imageView, StatBatteryMeterView statBatteryMeterView, ChargeIcon chargeIcon) {
        Drawable drawable;
        ChargeIcon batteryReverse;
        if (chargeIcon != null) {
            boolean z = chargeIcon.isVisible;
            if (imageView == null) {
                if (imageView != null) {
                    imageView.setVisibility(z ? 0 : 8);
                }
                if (imageView != null && imageView.getVisibility() == 0 && chargeIcon.getIconId() > 0) {
                    imageView.setImageDrawable(statBatteryMeterView.getContext().getDrawable(chargeIcon.getIconId()));
                    drawable = imageView.getDrawable();
                    if (drawable != null) {
                        drawable.setAutoMirrored(true);
                    }
                }
            } else {
                if ((imageView.getVisibility() == 0) != z || (batteryReverse = statBatteryMeterView.getBatteryReverse()) == null || batteryReverse.getThemeId() != chargeIcon.getThemeId()) {
                    if (imageView != null) {
                        imageView.setVisibility(z ? 0 : 8);
                    }
                    if (imageView != null) {
                        imageView.setImageDrawable(statBatteryMeterView.getContext().getDrawable(chargeIcon.getIconId()));
                        drawable = imageView.getDrawable();
                        if (drawable != null) {
                            drawable.setAutoMirrored(true);
                        }
                    }
                }
            }
            if (imageView == null || imageView.getVisibility() != 0) {
                return;
            }
            imageView.setImageTintList(ColorStateList.valueOf(chargeIcon.getTintColor()));
        }
    }
}
