package com.oplus.systemui.scenario.host.impl.domain.binder;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimationModifierKt;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.SizeTransform;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.foundation.contextmenu.ContextMenuAreaKt$;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.ComposeView;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.res.PrimitiveResources_androidKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplus.systemui.qs.base.res.util.QSConstant;
import com.oplus.systemui.qs.base.scenario.PrivacyScenarioCapsuleViewModel;
import com.oplus.systemui.qs.base.spotlight.SharedSpotLightEffect;
import com.oplus.systemui.qs.base.spotlight.SpotLightFrameLayout;
import com.oplus.systemui.qs.base.tile.PressFeedbackHelper;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import com.oplus.systemui.qs.scenario.domain.viewmodel.PrivacyScenarioCapsuleViewModelImpl;
import com.oplus.systemui.scenario.host.impl.ui.composable.LocalViewsMapKt;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.outline.RoundRectOutlineProvider;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioCapsuleViewBinder {
    public static final ScenarioCapsuleViewBinder INSTANCE = new ScenarioCapsuleViewBinder();
    private static final String KEY_CAMERA_ICON = "CameraCapsuleIcon";
    private static final String KEY_CAPSULE_BG = "CapsuleBackground";
    private static final String KEY_LOCATION_ICON = "LocationCapsuleIcon";
    private static final String KEY_MIC_ICON = "MicrophoneCapsuleIcon";
    private static final String KEY_PRIVACY_TEXT = "PrivacyCapsuleText";
    private static final String KEY_SCENARIO_ICON = "ScenarioCapsuleIcon";
    private static final String KEY_SCENARIO_TEXT = "ScenarioCapsuleText";
    private static final ProvidableCompositionLocal<Boolean> LocalFirstLaunch;
    private static final ProvidableCompositionLocal<PrivacyScenarioCapsuleViewModel> LocalViewModel;
    private static final String PREFIX_PRIVACY = "Privacy";
    private static final String PREFIX_SCENARIO = "Scenario";
    private static final String TAG = "ScenarioCapsuleViewBinder";
    private static final ContentTransform capsuleContentTransform;
    private static final EnterTransition capsuleEnterTransition;
    private static final ExitTransition capsuleExitTransition;
    private static final ContentTransform capsuleNoneTransform;
    private static final ContentTransform capsuleVisibilityTransform;
    private static final SpringSpec floatSizeSpring;
    private static final SpringSpec floatVisibilitySpring;
    private static final EnterTransition imageEnterTransition;
    private static final ExitTransition imageExitTransition;
    private static final SpringSpec intSizeSpring;

    public static ContentTransform $r8$lambda$NsUwkNJEacQnMpupi3fhOE9vcag(boolean z, AnimatedContentTransitionScope animatedContentTransitionScope) {
        if (z) {
            return (animatedContentTransitionScope.getInitialState() == null || animatedContentTransitionScope.getTargetState() == null) ? capsuleVisibilityTransform : capsuleContentTransform;
        }
        return capsuleNoneTransform;
    }

    static {
        final int i = 0;
        LocalViewModel = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i) {
                    case 0:
                        throw new IllegalStateException("No ViewModel provided");
                    default:
                        ScenarioCapsuleViewBinder scenarioCapsuleViewBinder = ScenarioCapsuleViewBinder.INSTANCE;
                        return Boolean.TRUE;
                }
            }
        });
        final int i2 = 1;
        LocalFirstLaunch = CompositionLocalKt.compositionLocalOf$default(new Function0() { // from class: com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder$$ExternalSyntheticLambda0
            public final Object invoke() {
                switch (i2) {
                    case 0:
                        throw new IllegalStateException("No ViewModel provided");
                    default:
                        ScenarioCapsuleViewBinder scenarioCapsuleViewBinder = ScenarioCapsuleViewBinder.INSTANCE;
                        return Boolean.TRUE;
                }
            }
        });
        SpringSpec springSpecSpring$default = AnimationSpecKt.spring$default(1.0f, 438.65f, (Object) null, 4);
        floatVisibilitySpring = springSpecSpring$default;
        SpringSpec springSpecSpring$default2 = AnimationSpecKt.spring$default(0.8f, 438.65f, (Object) null, 4);
        floatSizeSpring = springSpecSpring$default2;
        SpringSpec springSpecSpring$default3 = AnimationSpecKt.spring$default(0.8f, 438.65f, (Object) null, 4);
        intSizeSpring = springSpecSpring$default3;
        EnterTransition enterTransitionPlus = EnterExitTransitionKt.scaleIn-L8ZKh-E$default(springSpecSpring$default2, 0.0f, 0L, 6).plus(EnterExitTransitionKt.fadeIn$default(springSpecSpring$default2, 2));
        Alignment.Companion companion = Alignment.Companion;
        imageEnterTransition = enterTransitionPlus.plus(EnterExitTransitionKt.expandHorizontally$default(springSpecSpring$default3, companion.getCenterHorizontally(), 12));
        imageExitTransition = EnterExitTransitionKt.scaleOut-L8ZKh-E$default(springSpecSpring$default2, 0.0f, 0L, 6).plus(EnterExitTransitionKt.fadeOut$default(springSpecSpring$default2, 2)).plus(EnterExitTransitionKt.shrinkHorizontally$default(springSpecSpring$default3, companion.getCenterHorizontally(), 12));
        EnterTransition enterTransitionFadeIn$default = EnterExitTransitionKt.fadeIn$default(springSpecSpring$default, 2);
        capsuleEnterTransition = enterTransitionFadeIn$default;
        ExitTransition exitTransitionFadeOut$default = EnterExitTransitionKt.fadeOut$default(springSpecSpring$default, 2);
        capsuleExitTransition = exitTransitionFadeOut$default;
        capsuleVisibilityTransform = new ContentTransform(enterTransitionFadeIn$default, exitTransitionFadeOut$default, (SizeTransform) null, 4);
        capsuleContentTransform = new ContentTransform(EnterExitTransitionKt.fadeIn$default(springSpecSpring$default2, 2).plus(EnterExitTransitionKt.scaleIn-L8ZKh-E$default(springSpecSpring$default2, 0.6f, 0L, 4)), EnterExitTransitionKt.fadeOut$default(springSpecSpring$default2, 2).plus(EnterExitTransitionKt.scaleOut-L8ZKh-E$default(springSpecSpring$default2, 0.6f, 0L, 4)), AnimatedContentKt.SizeTransform(false, new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda2()), 4);
        capsuleNoneTransform = new ContentTransform(EnterTransition.Companion.getNone(), ExitTransition.Companion.getNone(), (SizeTransform) null, 4);
    }

    public final void CapsuleAnimatedContent(Modifier modifier, Function3 function3, Composer composer, int i) {
        Object obj;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1787325807);
        int i2 = i | 6 | (composerStartRestartGroup.changedInstance(this) ? 256 : 128);
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            Modifier modifier2 = Modifier.Companion;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1787325807, i2, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.CapsuleAnimatedContent (ScenarioCapsuleViewBinder.kt:535)");
            }
            PrivacyScenarioCapsuleViewModel privacyScenarioCapsuleViewModel = (PrivacyScenarioCapsuleViewModel) composerStartRestartGroup.consume(LocalViewModel);
            State stateCollectAsState = SnapshotStateKt.collectAsState(privacyScenarioCapsuleViewModel.getCapsuleState(), (CoroutineContext) null, composerStartRestartGroup, 0, 1);
            boolean zIsAnimationEnable = ((PrivacyScenarioCapsuleViewModelImpl) privacyScenarioCapsuleViewModel).isAnimationEnable();
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("CapsuleAnimatedContent: capsuleState=", (String) stateCollectAsState.getValue(), ", animationEnable=", zIsAnimationEnable), null, 8);
            String str = (String) stateCollectAsState.getValue();
            Alignment center = Alignment.Companion.getCenter();
            boolean zChanged = composerStartRestartGroup.changed(zIsAnimationEnable);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                obj = objRememberedValue;
                ScenarioCapsuleViewBinder$.ExternalSyntheticLambda13 externalSyntheticLambda13 = new ScenarioCapsuleViewBinder$.ExternalSyntheticLambda13();
                externalSyntheticLambda13.f$0 = zIsAnimationEnable;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(externalSyntheticLambda13);
                obj = externalSyntheticLambda13;
            }
            obj = objRememberedValue;
            ScenarioCapsuleViewBinder$.ExternalSyntheticLambda14 externalSyntheticLambda14 = new ScenarioCapsuleViewBinder$.ExternalSyntheticLambda14();
            externalSyntheticLambda14.f$0 = function3;
            VarHandle.storeStoreFence();
            AnimatedContentKt.AnimatedContent(str, modifier2, (Function1) obj, center, (String) null, (Function1) null, ComposableLambdaKt.rememberComposableLambda(-1022976601, true, externalSyntheticLambda14, composerStartRestartGroup, 54), composerStartRestartGroup, 1575984, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier = modifier2;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda12 scenarioCapsuleViewBinder$$ExternalSyntheticLambda12 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda12(1);
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda12.f$0 = this;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda12.f$1 = modifier;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda12.f$2 = function3;
            VarHandle.storeStoreFence();
            scopeUpdateScopeEndRestartGroup.updateScope(scenarioCapsuleViewBinder$$ExternalSyntheticLambda12);
        }
    }

    public final void CapsuleAnimatedVisibility(Modifier modifier, Function2 function2, Composer composer, int i) {
        Modifier modifier2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1624138545);
        int i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i | (composerStartRestartGroup.changedInstance(this) ? 256 : 128);
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1624138545, i2, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.CapsuleAnimatedVisibility (ScenarioCapsuleViewBinder.kt:499)");
            }
            PrivacyScenarioCapsuleViewModel privacyScenarioCapsuleViewModel = (PrivacyScenarioCapsuleViewModel) composerStartRestartGroup.consume(LocalViewModel);
            State stateCollectAsState = SnapshotStateKt.collectAsState(privacyScenarioCapsuleViewModel.getShowCapsule(), (CoroutineContext) null, composerStartRestartGroup, 0, 1);
            boolean zIsAnimationEnable = ((PrivacyScenarioCapsuleViewModelImpl) privacyScenarioCapsuleViewModel).isAnimationEnable();
            EnterTransition none = zIsAnimationEnable ? capsuleEnterTransition : EnterTransition.Companion.getNone();
            ExitTransition none2 = zIsAnimationEnable ? capsuleExitTransition : ExitTransition.Companion.getNone();
            LogUtil.i$default(LogUtil.TAG_QS, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("CapsuleAnimatedVisibility: showCapsule=", ", animationEnable=", ((Boolean) stateCollectAsState.getValue()).booleanValue(), zIsAnimationEnable), null, 8);
            boolean zBooleanValue = ((Boolean) stateCollectAsState.getValue()).booleanValue();
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda11 scenarioCapsuleViewBinder$$ExternalSyntheticLambda11 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda11(0);
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda11.f$0 = function2;
            VarHandle.storeStoreFence();
            modifier2 = modifier;
            AnimatedVisibilityKt.AnimatedVisibility(zBooleanValue, modifier2, none, none2, (String) null, ComposableLambdaKt.rememberComposableLambda(1457150375, true, scenarioCapsuleViewBinder$$ExternalSyntheticLambda11, composerStartRestartGroup, 54), composerStartRestartGroup, ((i2 << 3) & 112) | 196608, 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            modifier2 = modifier;
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda12 scenarioCapsuleViewBinder$$ExternalSyntheticLambda12 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda12(0);
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda12.f$0 = this;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda12.f$1 = modifier2;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda12.f$2 = function2;
            VarHandle.storeStoreFence();
            scopeUpdateScopeEndRestartGroup.updateScope(scenarioCapsuleViewBinder$$ExternalSyntheticLambda12);
        }
    }

    public final void CapsuleBackground(Modifier modifier, Composer composer, int i) {
        Modifier modifier2;
        Object obj;
        Object obj2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1457728441);
        int i2 = (composerStartRestartGroup.changedInstance(this) ? 32 : 16) | i;
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1457728441, i2, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.CapsuleBackground (ScenarioCapsuleViewBinder.kt:275)");
            }
            PrivacyScenarioCapsuleViewModel privacyScenarioCapsuleViewModel = (PrivacyScenarioCapsuleViewModel) composerStartRestartGroup.consume(LocalViewModel);
            ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) composerStartRestartGroup.consume(LocalViewsMapKt.getLocalViewsMap());
            float dimension = ((Resources) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalResources())).getDimension(2131171127);
            boolean zChanged = composerStartRestartGroup.changed(dimension);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = QSConstant.getFullCornerProvider(dimension);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            RoundRectOutlineProvider roundRectOutlineProvider = (RoundRectOutlineProvider) objRememberedValue;
            State stateCollectAsState = SnapshotStateKt.collectAsState(privacyScenarioCapsuleViewModel.getScenarioCapsuleDrawable(), (CoroutineContext) null, composerStartRestartGroup, 0, 1);
            State stateCollectAsState2 = SnapshotStateKt.collectAsState(privacyScenarioCapsuleViewModel.getScenarioLightAction(), "noop", (CoroutineContext) null, composerStartRestartGroup, 48, 2);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(concurrentHashMap);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.Companion.getEmpty()) {
                obj = objRememberedValue2;
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda16 scenarioCapsuleViewBinder$$ExternalSyntheticLambda16 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda16(0);
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda16.f$0 = concurrentHashMap;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(scenarioCapsuleViewBinder$$ExternalSyntheticLambda16);
                obj = scenarioCapsuleViewBinder$$ExternalSyntheticLambda16;
            }
            obj = objRememberedValue2;
            Function1 function1 = (Function1) obj;
            boolean zChanged2 = composerStartRestartGroup.changed(stateCollectAsState) | composerStartRestartGroup.changedInstance(roundRectOutlineProvider) | composerStartRestartGroup.changed(stateCollectAsState2) | composerStartRestartGroup.changedInstance(privacyScenarioCapsuleViewModel);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue3 == Composer.Companion.getEmpty()) {
                obj2 = objRememberedValue3;
                ScenarioCapsuleViewBinder$.ExternalSyntheticLambda17 externalSyntheticLambda17 = new ScenarioCapsuleViewBinder$.ExternalSyntheticLambda17();
                externalSyntheticLambda17.f$0 = stateCollectAsState;
                externalSyntheticLambda17.f$1 = roundRectOutlineProvider;
                externalSyntheticLambda17.f$2 = stateCollectAsState2;
                externalSyntheticLambda17.f$3 = privacyScenarioCapsuleViewModel;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(externalSyntheticLambda17);
                obj2 = externalSyntheticLambda17;
            }
            obj2 = objRememberedValue3;
            Function1 function2 = (Function1) obj2;
            modifier2 = modifier;
            AndroidView_androidKt.AndroidView(function1, modifier2, function2, composerStartRestartGroup, 48, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            modifier2 = modifier;
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda3 scenarioCapsuleViewBinder$$ExternalSyntheticLambda3 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda3(2);
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda3.f$0 = this;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda3.f$1 = modifier2;
            VarHandle.storeStoreFence();
            scopeUpdateScopeEndRestartGroup.updateScope(scenarioCapsuleViewBinder$$ExternalSyntheticLambda3);
        }
    }

    public final void CapsuleContent(Composer composer, int i) {
        float fDimensionResource;
        Object obj;
        Composer composerStartRestartGroup = composer.startRestartGroup(245066891);
        int i2 = i | (composerStartRestartGroup.changedInstance(this) ? 4 : 2);
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(245066891, i2, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.CapsuleContent (ScenarioCapsuleViewBinder.kt:238)");
            }
            PrivacyScenarioCapsuleViewModel privacyScenarioCapsuleViewModel = (PrivacyScenarioCapsuleViewModel) composerStartRestartGroup.consume(LocalViewModel);
            HapticFeedback hapticFeedback = (HapticFeedback) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalHapticFeedback());
            if (((Configuration) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalConfiguration())).orientation == 2) {
                composerStartRestartGroup.startReplaceGroup(-713908182);
                fDimensionResource = PrimitiveResources_androidKt.dimensionResource(2131172358, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-713905930);
                composerStartRestartGroup.endReplaceGroup();
                fDimensionResource = Dp.Companion.getUnspecified-D9Ej5fM();
            }
            Alignment center = Alignment.Companion.getCenter();
            Modifier.Companion companion = Modifier.Companion;
            Modifier modifier = SizeKt.widthIn-VpY3zN4$default(SizeKt.wrapContentHeight$default(companion, (Alignment.Vertical) null, false, 3, (Object) null), 0.0f, fDimensionResource, 1);
            Unit unit = Unit.INSTANCE;
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(hapticFeedback) | composerStartRestartGroup.changedInstance(privacyScenarioCapsuleViewModel);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                obj = objRememberedValue;
                ScenarioCapsuleViewBinder$CapsuleContent$1$1 scenarioCapsuleViewBinder$CapsuleContent$1$1 = new ScenarioCapsuleViewBinder$CapsuleContent$1$1();
                scenarioCapsuleViewBinder$CapsuleContent$1$1.$haptic = hapticFeedback;
                scenarioCapsuleViewBinder$CapsuleContent$1$1.$viewModel = privacyScenarioCapsuleViewModel;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(scenarioCapsuleViewBinder$CapsuleContent$1$1);
                obj = scenarioCapsuleViewBinder$CapsuleContent$1$1;
            }
            obj = objRememberedValue;
            Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(modifier, unit, (PointerInputEventHandler) obj);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPointerInput);
            ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
            Function0 constructor = companion2.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
                throw null;
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer2 = Updater.constructor-impl(composerStartRestartGroup);
            ChangeSize$.ExternalSyntheticOutline0.m(companion2, composer2, measurePolicyMaybeCachedBoxMeasurePolicy, composer2, currentCompositionLocalMap);
            Updater.set-impl(composer2, modifierMaterializeModifier, ContextMenuAreaKt$.ExternalSyntheticOutline0.m(companion2, composer2, Integer.valueOf(iHashCode), composer2));
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ScenarioCapsuleViewBinder scenarioCapsuleViewBinder = INSTANCE;
            Modifier modifierMatchParentSize = boxScopeInstance.matchParentSize(companion);
            ComposableSingletons$ScenarioCapsuleViewBinderKt composableSingletons$ScenarioCapsuleViewBinderKt = ComposableSingletons$ScenarioCapsuleViewBinderKt.INSTANCE;
            scenarioCapsuleViewBinder.CapsuleAnimatedVisibility(modifierMatchParentSize, composableSingletons$ScenarioCapsuleViewBinderKt.getLambda$77693743$vendor__oplus__packages__apps__systemui__oplus_pods__scenario__host__android_common__com_oplus_systemui_scenario_host(), composerStartRestartGroup, 560);
            scenarioCapsuleViewBinder.CapsuleAnimatedContent(null, composableSingletons$ScenarioCapsuleViewBinderKt.m357x26f9cef1(), composerStartRestartGroup, 560);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda8 scenarioCapsuleViewBinder$$ExternalSyntheticLambda8 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda8(0);
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda8.f$0 = this;
            VarHandle.storeStoreFence();
            scopeUpdateScopeEndRestartGroup.updateScope(scenarioCapsuleViewBinder$$ExternalSyntheticLambda8);
        }
    }

    public final void CapsuleDecorProvider(PrivacyScenarioCapsuleViewModel privacyScenarioCapsuleViewModel, Function2 function2, Composer composer, int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1029981564);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(privacyScenarioCapsuleViewModel) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= (i & OplusAnimationController.FLAG_FLEXIBLE_TO_FULL_EXCHANGE_TRANSITION) == 0 ? composerStartRestartGroup.changed(this) : composerStartRestartGroup.changedInstance(this) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1029981564, i2, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.CapsuleDecorProvider (ScenarioCapsuleViewBinder.kt:192)");
            }
            Context context = (Context) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            boolean zChanged = composerStartRestartGroup.changed((Configuration) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalConfiguration())) | composerStartRestartGroup.changed(context);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = QsColorUtil.createDarkColorContext(context, QsColorUtil.isNeedDarkThemeColor(context, false));
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Context context2 = (Context) objRememberedValue;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                objRememberedValue2 = new ConcurrentHashMap();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            ProvidedValue[] providedValueArr = {AndroidCompositionLocals_androidKt.getLocalContext().provides(context2), LocalViewModel.provides(privacyScenarioCapsuleViewModel), LocalViewsMapKt.getLocalViewsMap().provides((ConcurrentHashMap) objRememberedValue2)};
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda4 scenarioCapsuleViewBinder$$ExternalSyntheticLambda4 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda4(0);
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda4.f$0 = function2;
            VarHandle.storeStoreFence();
            CompositionLocalKt.CompositionLocalProvider(providedValueArr, ComposableLambdaKt.rememberComposableLambda(-1761375684, true, scenarioCapsuleViewBinder$$ExternalSyntheticLambda4, composerStartRestartGroup, 54), composerStartRestartGroup, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda5 scenarioCapsuleViewBinder$$ExternalSyntheticLambda5 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda5();
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda5.f$0 = this;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda5.f$1 = privacyScenarioCapsuleViewModel;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda5.f$2 = function2;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda5.f$3 = i;
            VarHandle.storeStoreFence();
            scopeUpdateScopeEndRestartGroup.updateScope(scenarioCapsuleViewBinder$$ExternalSyntheticLambda5);
        }
    }

    public final void CapsuleImage(String str, int i, Modifier modifier, Function0 function0, Composer composer, int i2) {
        int i3;
        Modifier modifier2;
        Object obj;
        Object obj2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1375589430);
        if ((i2 & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        int i4 = i3 | (composerStartRestartGroup.changed(i) ? 32 : 16) | 384 | (composerStartRestartGroup.changedInstance(function0) ? 2048 : OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE);
        if (composerStartRestartGroup.shouldExecute((i4 & 1171) != 1170, i4 & 1)) {
            modifier2 = Modifier.Companion;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1375589430, i4, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.CapsuleImage (ScenarioCapsuleViewBinder.kt:478)");
            }
            ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) composerStartRestartGroup.consume(LocalViewsMapKt.getLocalViewsMap());
            Modifier modifier3 = SizeKt.requiredSize-3ABfNKs(modifier2, PrimitiveResources_androidKt.dimensionResource(2131172357, composerStartRestartGroup, 0));
            boolean zChangedInstance = ((i4 & 14) == 4) | composerStartRestartGroup.changedInstance(concurrentHashMap) | ((i4 & 112) == 32);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                obj = objRememberedValue;
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda31 scenarioCapsuleViewBinder$$ExternalSyntheticLambda31 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda31(1);
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda31.f$0 = concurrentHashMap;
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda31.f$1 = str;
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda31.f$2 = i;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(scenarioCapsuleViewBinder$$ExternalSyntheticLambda31);
                obj = scenarioCapsuleViewBinder$$ExternalSyntheticLambda31;
            }
            obj = objRememberedValue;
            Function1 function1 = (Function1) obj;
            boolean z = (i4 & 7168) == 2048;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue2 == Composer.Companion.getEmpty()) {
                obj2 = objRememberedValue2;
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda16 scenarioCapsuleViewBinder$$ExternalSyntheticLambda16 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda16(1);
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda16.f$0 = function0;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(scenarioCapsuleViewBinder$$ExternalSyntheticLambda16);
                obj2 = scenarioCapsuleViewBinder$$ExternalSyntheticLambda16;
            }
            obj2 = objRememberedValue2;
            AndroidView_androidKt.AndroidView(function1, modifier3, (Function1) obj2, composerStartRestartGroup, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier2 = modifier;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda36 scenarioCapsuleViewBinder$$ExternalSyntheticLambda36 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda36();
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda36.f$0 = this;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda36.f$1 = str;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda36.f$2 = i;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda36.f$3 = modifier2;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda36.f$4 = function0;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda36.f$5 = i2;
            VarHandle.storeStoreFence();
            scopeUpdateScopeEndRestartGroup.updateScope(scenarioCapsuleViewBinder$$ExternalSyntheticLambda36);
        }
    }

    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [boolean, int] */
    public final void CapsuleText(RowScope rowScope, String str, int i, Modifier modifier, Function0 function0, Composer composer, int i2) {
        Modifier modifier2;
        ?? r5;
        Object obj;
        Object obj2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1685794190);
        int i3 = i2 | (composerStartRestartGroup.changed(i) ? 256 : 128) | 3072 | (composerStartRestartGroup.changedInstance(function0) ? 16384 : OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_TO_TILING) | (composerStartRestartGroup.changedInstance(this) ? 131072 : OplusAnimationController.FLAG_ALLOW_LIGHTOS_CORNER_RADIUS);
        if (composerStartRestartGroup.shouldExecute((74899 & i3) != 74898, i3 & 1)) {
            Modifier modifier3 = Modifier.Companion;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1685794190, i3, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.CapsuleText (ScenarioCapsuleViewBinder.kt:442)");
            }
            Context context = (Context) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            PrivacyScenarioCapsuleViewModel privacyScenarioCapsuleViewModel = (PrivacyScenarioCapsuleViewModel) composerStartRestartGroup.consume(LocalViewModel);
            ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) composerStartRestartGroup.consume(LocalViewsMapKt.getLocalViewsMap());
            boolean zBooleanValue = ((Boolean) composerStartRestartGroup.consume(LocalFirstLaunch)).booleanValue();
            String str2 = (String) function0.invoke();
            if (str2 == null) {
                str2 = "";
            }
            boolean z = !zBooleanValue && ((PrivacyScenarioCapsuleViewModelImpl) privacyScenarioCapsuleViewModel).isAnimationEnable();
            State stateCollectAsState = SnapshotStateKt.collectAsState(privacyScenarioCapsuleViewModel.isTextMarqueeEnable(), (CoroutineContext) null, composerStartRestartGroup, 0, 1);
            boolean z2 = z;
            String str3 = str2;
            modifier2 = modifier3;
            State stateCollectAsState2 = SnapshotStateKt.collectAsState(privacyScenarioCapsuleViewModel.getTextColorRes(), 2131103292, (CoroutineContext) null, composerStartRestartGroup, 0, 2);
            boolean zChanged = composerStartRestartGroup.changed(((Number) stateCollectAsState2.getValue()).intValue()) | composerStartRestartGroup.changed(context);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = context.getColorStateList(((Number) stateCollectAsState2.getValue()).intValue());
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ColorStateList colorStateList = (ColorStateList) objRememberedValue;
            colorStateList.getClass();
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(concurrentHashMap) | ((i3 & 896) == 256);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.Companion.getEmpty()) {
                r5 = 0;
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda31 scenarioCapsuleViewBinder$$ExternalSyntheticLambda31 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda31(0);
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda31.f$0 = concurrentHashMap;
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda31.f$1 = str;
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda31.f$2 = i;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(scenarioCapsuleViewBinder$$ExternalSyntheticLambda31);
                obj = scenarioCapsuleViewBinder$$ExternalSyntheticLambda31;
            } else {
                r5 = 0;
                obj = objRememberedValue2;
            }
            Function1 function1 = (Function1) obj;
            Modifier modifier4 = PaddingKt.padding-VpY3zN4$default(AnimationModifierKt.animateContentSize$default(rowScope.weight(modifier2, 1.0f, (boolean) r5), intSizeSpring, 2), PrimitiveResources_androidKt.dimensionResource(2131172362, composerStartRestartGroup, (int) r5), 0.0f, 2, (Object) null);
            boolean zChanged2 = composerStartRestartGroup.changed(str3) | composerStartRestartGroup.changed(z2) | composerStartRestartGroup.changed(stateCollectAsState) | composerStartRestartGroup.changedInstance(colorStateList);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue3 == Composer.Companion.getEmpty()) {
                obj2 = objRememberedValue3;
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda32 scenarioCapsuleViewBinder$$ExternalSyntheticLambda32 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda32();
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda32.f$0 = str3;
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda32.f$1 = z2;
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda32.f$2 = colorStateList;
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda32.f$3 = stateCollectAsState;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(scenarioCapsuleViewBinder$$ExternalSyntheticLambda32);
                obj2 = scenarioCapsuleViewBinder$$ExternalSyntheticLambda32;
            }
            obj2 = objRememberedValue3;
            AndroidView_androidKt.AndroidView(function1, modifier4, (Function1) obj2, composerStartRestartGroup, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier2 = modifier;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda33 scenarioCapsuleViewBinder$$ExternalSyntheticLambda33 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda33();
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda33.f$0 = this;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda33.f$1 = rowScope;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda33.f$2 = str;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda33.f$3 = i;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda33.f$4 = modifier2;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda33.f$5 = function0;
            VarHandle.storeStoreFence();
            scopeUpdateScopeEndRestartGroup.updateScope(scenarioCapsuleViewBinder$$ExternalSyntheticLambda33);
        }
    }

    public final void FirstLaunchProvider(Function2 function2, Composer composer, int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-2091539408);
        int i2 = i | (composerStartRestartGroup.changedInstance(this) ? 32 : 16);
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2091539408, i2, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.FirstLaunchProvider (ScenarioCapsuleViewBinder.kt:519)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState = (MutableState) objRememberedValue;
            Unit unit = Unit.INSTANCE;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new ScenarioCapsuleViewBinder$FirstLaunchProvider$1$1(mutableState, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            EffectsKt.LaunchedEffect(unit, (Function2) objRememberedValue2, composerStartRestartGroup, 6);
            ProvidableCompositionLocal<Boolean> providableCompositionLocal = LocalFirstLaunch;
            Boolean bool = (Boolean) mutableState.getValue();
            bool.getClass();
            ProvidedValue providedValueProvides = providableCompositionLocal.provides(bool);
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda4 scenarioCapsuleViewBinder$$ExternalSyntheticLambda4 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda4(1);
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda4.f$0 = function2;
            VarHandle.storeStoreFence();
            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.rememberComposableLambda(498583408, true, scenarioCapsuleViewBinder$$ExternalSyntheticLambda4, composerStartRestartGroup, 54), composerStartRestartGroup, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda3 scenarioCapsuleViewBinder$$ExternalSyntheticLambda3 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda3(3);
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda3.f$0 = this;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda3.f$1 = function2;
            VarHandle.storeStoreFence();
            scopeUpdateScopeEndRestartGroup.updateScope(scenarioCapsuleViewBinder$$ExternalSyntheticLambda3);
        }
    }

    public final void ImageAnimatedVisibility(Function0 function0, Modifier modifier, Function2 function2, Composer composer, int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1626208009);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        int i3 = i2 | 48;
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= (i & OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1) == 0 ? composerStartRestartGroup.changed(this) : composerStartRestartGroup.changedInstance(this) ? OplusAnimationController.FLAG_TRANSITION_DISPLAY_CHANGE_ABORT : OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE;
        }
        boolean z = false;
        if (composerStartRestartGroup.shouldExecute((i3 & 1171) != 1170, i3 & 1)) {
            Modifier modifier2 = Modifier.Companion;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1626208009, i3, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.ImageAnimatedVisibility (ScenarioCapsuleViewBinder.kt:566)");
            }
            PrivacyScenarioCapsuleViewModel privacyScenarioCapsuleViewModel = (PrivacyScenarioCapsuleViewModel) composerStartRestartGroup.consume(LocalViewModel);
            boolean zBooleanValue = ((Boolean) composerStartRestartGroup.consume(LocalFirstLaunch)).booleanValue();
            boolean zBooleanValue2 = ((Boolean) function0.invoke()).booleanValue();
            if (!zBooleanValue && ((PrivacyScenarioCapsuleViewModelImpl) privacyScenarioCapsuleViewModel).isAnimationEnable()) {
                z = true;
            }
            EnterTransition none = z ? imageEnterTransition : EnterTransition.Companion.getNone();
            ExitTransition none2 = z ? imageExitTransition : ExitTransition.Companion.getNone();
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda11 scenarioCapsuleViewBinder$$ExternalSyntheticLambda11 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda11(1);
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda11.f$0 = function2;
            VarHandle.storeStoreFence();
            AnimatedVisibilityKt.AnimatedVisibility(zBooleanValue2, modifier2, none, none2, (String) null, ComposableLambdaKt.rememberComposableLambda(1272470497, true, scenarioCapsuleViewBinder$$ExternalSyntheticLambda11, composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 112) | 196608, 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier = modifier2;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda29 scenarioCapsuleViewBinder$$ExternalSyntheticLambda29 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda29();
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda29.f$0 = this;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda29.f$1 = function0;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda29.f$2 = modifier;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda29.f$3 = function2;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda29.f$4 = i;
            VarHandle.storeStoreFence();
            scopeUpdateScopeEndRestartGroup.updateScope(scenarioCapsuleViewBinder$$ExternalSyntheticLambda29);
        }
    }

    public final void PrivacyIcons(String str, State state, Composer composer, int i) {
        ScenarioCapsuleViewBinder scenarioCapsuleViewBinder = this;
        Composer composerStartRestartGroup = composer.startRestartGroup(1716556027);
        int i2 = i | (composerStartRestartGroup.changed(state) ? 32 : 16) | (composerStartRestartGroup.changedInstance(scenarioCapsuleViewBinder) ? 256 : 128);
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1716556027, i2, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.PrivacyIcons (ScenarioCapsuleViewBinder.kt:376)");
            }
            State stateCollectAsState = SnapshotStateKt.collectAsState(BaseQSResPool.INSTANCE.isGlobalThemeApplied(), (CoroutineContext) null, composerStartRestartGroup, 0, 1);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19 scenarioCapsuleViewBinder$$ExternalSyntheticLambda19 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19(4);
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda19.f$0 = state;
                VarHandle.storeStoreFence();
                objRememberedValue = SnapshotStateKt.derivedStateOf(scenarioCapsuleViewBinder$$ExternalSyntheticLambda19);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            State state2 = (State) objRememberedValue;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19 scenarioCapsuleViewBinder$$ExternalSyntheticLambda110 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19(6);
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda110.f$0 = state;
                VarHandle.storeStoreFence();
                objRememberedValue2 = SnapshotStateKt.derivedStateOf(scenarioCapsuleViewBinder$$ExternalSyntheticLambda110);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            State state3 = (State) objRememberedValue2;
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19 scenarioCapsuleViewBinder$$ExternalSyntheticLambda111 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19(7);
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda111.f$0 = state;
                VarHandle.storeStoreFence();
                objRememberedValue3 = SnapshotStateKt.derivedStateOf(scenarioCapsuleViewBinder$$ExternalSyntheticLambda111);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            State state4 = (State) objRememberedValue3;
            float fDimensionResource = PrimitiveResources_androidKt.dimensionResource(2131172356, composerStartRestartGroup, 0);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            Object obj = objRememberedValue4;
            if (objRememberedValue4 == companion.getEmpty()) {
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19 scenarioCapsuleViewBinder$$ExternalSyntheticLambda112 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19(8);
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda112.f$0 = state2;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(scenarioCapsuleViewBinder$$ExternalSyntheticLambda112);
                obj = scenarioCapsuleViewBinder$$ExternalSyntheticLambda112;
            }
            ScenarioCapsuleViewBinder$.ExternalSyntheticLambda40 externalSyntheticLambda40 = new ScenarioCapsuleViewBinder$.ExternalSyntheticLambda40(1);
            externalSyntheticLambda40.f$0 = str;
            externalSyntheticLambda40.f$1 = stateCollectAsState;
            VarHandle.storeStoreFence();
            int i3 = ((i2 << 3) & 7168) | 4486;
            scenarioCapsuleViewBinder.ImageAnimatedVisibility((Function0) obj, null, ComposableLambdaKt.rememberComposableLambda(-1324333576, true, externalSyntheticLambda40, composerStartRestartGroup, 54), composerStartRestartGroup, i3);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            Object obj2 = objRememberedValue5;
            if (objRememberedValue5 == companion.getEmpty()) {
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda46 scenarioCapsuleViewBinder$$ExternalSyntheticLambda46 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda46();
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda46.f$0 = state2;
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda46.f$1 = state3;
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda46.f$2 = state4;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(scenarioCapsuleViewBinder$$ExternalSyntheticLambda46);
                obj2 = scenarioCapsuleViewBinder$$ExternalSyntheticLambda46;
            }
            Function0 function0 = (Function0) obj2;
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda24 scenarioCapsuleViewBinder$$ExternalSyntheticLambda24 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda24(2);
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda24.f$0 = fDimensionResource;
            VarHandle.storeStoreFence();
            ImageAnimatedVisibility(function0, null, ComposableLambdaKt.rememberComposableLambda(940036769, true, scenarioCapsuleViewBinder$$ExternalSyntheticLambda24, composerStartRestartGroup, 54), composerStartRestartGroup, i3);
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            Object obj3 = objRememberedValue6;
            if (objRememberedValue6 == companion.getEmpty()) {
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19 scenarioCapsuleViewBinder$$ExternalSyntheticLambda113 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19(9);
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda113.f$0 = state3;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(scenarioCapsuleViewBinder$$ExternalSyntheticLambda113);
                obj3 = scenarioCapsuleViewBinder$$ExternalSyntheticLambda113;
            }
            Function0 function1 = (Function0) obj3;
            ScenarioCapsuleViewBinder$.ExternalSyntheticLambda40 externalSyntheticLambda41 = new ScenarioCapsuleViewBinder$.ExternalSyntheticLambda40(2);
            externalSyntheticLambda41.f$0 = str;
            externalSyntheticLambda41.f$1 = stateCollectAsState;
            VarHandle.storeStoreFence();
            ImageAnimatedVisibility(function1, null, ComposableLambdaKt.rememberComposableLambda(1989905984, true, externalSyntheticLambda41, composerStartRestartGroup, 54), composerStartRestartGroup, i3);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            Object obj4 = objRememberedValue7;
            if (objRememberedValue7 == companion.getEmpty()) {
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda50 scenarioCapsuleViewBinder$$ExternalSyntheticLambda50 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda50();
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda50.f$0 = state3;
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda50.f$1 = state4;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(scenarioCapsuleViewBinder$$ExternalSyntheticLambda50);
                obj4 = scenarioCapsuleViewBinder$$ExternalSyntheticLambda50;
            }
            Function0 function2 = (Function0) obj4;
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda24 scenarioCapsuleViewBinder$$ExternalSyntheticLambda25 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda24(1);
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda25.f$0 = fDimensionResource;
            VarHandle.storeStoreFence();
            ImageAnimatedVisibility(function2, null, ComposableLambdaKt.rememberComposableLambda(-1255192097, true, scenarioCapsuleViewBinder$$ExternalSyntheticLambda25, composerStartRestartGroup, 54), composerStartRestartGroup, i3);
            Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
            Object obj5 = objRememberedValue8;
            if (objRememberedValue8 == companion.getEmpty()) {
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19 scenarioCapsuleViewBinder$$ExternalSyntheticLambda114 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19(5);
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda114.f$0 = state4;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(scenarioCapsuleViewBinder$$ExternalSyntheticLambda114);
                obj5 = scenarioCapsuleViewBinder$$ExternalSyntheticLambda114;
            }
            Function0 function3 = (Function0) obj5;
            ScenarioCapsuleViewBinder$.ExternalSyntheticLambda40 externalSyntheticLambda42 = new ScenarioCapsuleViewBinder$.ExternalSyntheticLambda40(0);
            externalSyntheticLambda42.f$0 = str;
            externalSyntheticLambda42.f$1 = stateCollectAsState;
            VarHandle.storeStoreFence();
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-205322882, true, externalSyntheticLambda42, composerStartRestartGroup, 54);
            scenarioCapsuleViewBinder = this;
            scenarioCapsuleViewBinder.ImageAnimatedVisibility(function3, null, composableLambdaRememberComposableLambda, composerStartRestartGroup, i3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda12 scenarioCapsuleViewBinder$$ExternalSyntheticLambda12 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda12(2);
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda12.f$0 = scenarioCapsuleViewBinder;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda12.f$1 = str;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda12.f$2 = state;
            VarHandle.storeStoreFence();
            scopeUpdateScopeEndRestartGroup.updateScope(scenarioCapsuleViewBinder$$ExternalSyntheticLambda12);
        }
    }

    public final void PrivacySection(Composer composer, int i) {
        Object obj;
        Composer composerStartRestartGroup = composer.startRestartGroup(-623662788);
        int i2 = (composerStartRestartGroup.changedInstance(this) ? 4 : 2) | i;
        int i3 = 0;
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-623662788, i2, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.PrivacySection (ScenarioCapsuleViewBinder.kt:420)");
            }
            PrivacyScenarioCapsuleViewModel privacyScenarioCapsuleViewModel = (PrivacyScenarioCapsuleViewModel) composerStartRestartGroup.consume(LocalViewModel);
            State stateCollectAsState = SnapshotStateKt.collectAsState(privacyScenarioCapsuleViewModel.getPrivacyAppName(), (CoroutineContext) null, composerStartRestartGroup, 0, 1);
            State stateCollectAsState2 = SnapshotStateKt.collectAsState(privacyScenarioCapsuleViewModel.getPrivacyPermGroup(), (CoroutineContext) null, composerStartRestartGroup, 0, 1);
            float fDimensionResource = PrimitiveResources_androidKt.dimensionResource(2131172359, composerStartRestartGroup, 0);
            float fDimensionResource2 = PrimitiveResources_androidKt.dimensionResource(2131172360, composerStartRestartGroup, 0);
            float fDimensionResource3 = PrimitiveResources_androidKt.dimensionResource(2131172356, composerStartRestartGroup, 0);
            Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
            Modifier.Companion companion = Modifier.Companion;
            Modifier modifier = PaddingKt.padding-VpY3zN4(companion, fDimensionResource, fDimensionResource2);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composerStartRestartGroup, 48);
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
            ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
            Function0 constructor = companion2.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
                throw null;
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer2 = Updater.constructor-impl(composerStartRestartGroup);
            ChangeSize$.ExternalSyntheticOutline0.m(companion2, composer2, measurePolicyRowMeasurePolicy, composer2, currentCompositionLocalMap);
            Updater.set-impl(composer2, modifierMaterializeModifier, ContextMenuAreaKt$.ExternalSyntheticOutline0.m(companion2, composer2, Integer.valueOf(iHashCode), composer2));
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ScenarioCapsuleViewBinder scenarioCapsuleViewBinder = INSTANCE;
            scenarioCapsuleViewBinder.PrivacyIcons(PREFIX_PRIVACY, stateCollectAsState2, composerStartRestartGroup, 518);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion, fDimensionResource3), composerStartRestartGroup, 0);
            boolean zChanged = composerStartRestartGroup.changed(stateCollectAsState);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                obj = objRememberedValue;
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19 scenarioCapsuleViewBinder$$ExternalSyntheticLambda19 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19(i3);
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda19.f$0 = stateCollectAsState;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(scenarioCapsuleViewBinder$$ExternalSyntheticLambda19);
                obj = scenarioCapsuleViewBinder$$ExternalSyntheticLambda19;
            }
            obj = objRememberedValue;
            scenarioCapsuleViewBinder.CapsuleText(rowScope, KEY_PRIVACY_TEXT, 2131364643, null, (Function0) obj, composerStartRestartGroup, 262198);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda8 scenarioCapsuleViewBinder$$ExternalSyntheticLambda8 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda8(1);
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda8.f$0 = this;
            VarHandle.storeStoreFence();
            scopeUpdateScopeEndRestartGroup.updateScope(scenarioCapsuleViewBinder$$ExternalSyntheticLambda8);
        }
    }

    public final void ScenarioSection(Composer composer, int i) {
        Object obj;
        Object obj2;
        Object obj3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1107818088);
        int i2 = 2;
        int i3 = i | (composerStartRestartGroup.changedInstance(this) ? 4 : 2);
        int i4 = 1;
        if (composerStartRestartGroup.shouldExecute((i3 & 3) != 2, i3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1107818088, i3, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.ScenarioSection (ScenarioCapsuleViewBinder.kt:347)");
            }
            PrivacyScenarioCapsuleViewModel privacyScenarioCapsuleViewModel = (PrivacyScenarioCapsuleViewModel) composerStartRestartGroup.consume(LocalViewModel);
            State stateCollectAsState = SnapshotStateKt.collectAsState(privacyScenarioCapsuleViewModel.getScenarioName(), (CoroutineContext) null, composerStartRestartGroup, 0, 1);
            State stateCollectAsState2 = SnapshotStateKt.collectAsState(privacyScenarioCapsuleViewModel.getShowSpacer(), (CoroutineContext) null, composerStartRestartGroup, 0, 1);
            State stateCollectAsState3 = SnapshotStateKt.collectAsState(privacyScenarioCapsuleViewModel.getScenarioIconRes(), 2131236458, (CoroutineContext) null, composerStartRestartGroup, 0, 2);
            State stateCollectAsState4 = SnapshotStateKt.collectAsState(privacyScenarioCapsuleViewModel.getScenarioPermGroup(), (CoroutineContext) null, composerStartRestartGroup, 0, 1);
            float fDimensionResource = PrimitiveResources_androidKt.dimensionResource(2131172359, composerStartRestartGroup, 0);
            float fDimensionResource2 = PrimitiveResources_androidKt.dimensionResource(2131172360, composerStartRestartGroup, 0);
            float fDimensionResource3 = PrimitiveResources_androidKt.dimensionResource(2131172361, composerStartRestartGroup, 0);
            Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
            Modifier modifier = PaddingKt.padding-VpY3zN4(Modifier.Companion, fDimensionResource, fDimensionResource2);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composerStartRestartGroup, 48);
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
            ComposeUiNode.Companion companion = ComposeUiNode.Companion;
            Function0 constructor = companion.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
                throw null;
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer2 = Updater.constructor-impl(composerStartRestartGroup);
            ChangeSize$.ExternalSyntheticOutline0.m(companion, composer2, measurePolicyRowMeasurePolicy, composer2, currentCompositionLocalMap);
            Updater.set-impl(composer2, modifierMaterializeModifier, ContextMenuAreaKt$.ExternalSyntheticOutline0.m(companion, composer2, Integer.valueOf(iHashCode), composer2));
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ScenarioCapsuleViewBinder scenarioCapsuleViewBinder = INSTANCE;
            boolean zChanged = composerStartRestartGroup.changed(stateCollectAsState3);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                obj = objRememberedValue;
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19 scenarioCapsuleViewBinder$$ExternalSyntheticLambda19 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19(i4);
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda19.f$0 = stateCollectAsState3;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(scenarioCapsuleViewBinder$$ExternalSyntheticLambda19);
                obj = scenarioCapsuleViewBinder$$ExternalSyntheticLambda19;
            }
            obj = objRememberedValue;
            scenarioCapsuleViewBinder.CapsuleImage(KEY_SCENARIO_ICON, 2131364996, null, (Function0) obj, composerStartRestartGroup, 32774);
            boolean zChanged2 = composerStartRestartGroup.changed(stateCollectAsState);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                obj2 = objRememberedValue2;
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19 scenarioCapsuleViewBinder$$ExternalSyntheticLambda110 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19(i2);
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda110.f$0 = stateCollectAsState;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(scenarioCapsuleViewBinder$$ExternalSyntheticLambda110);
                obj2 = scenarioCapsuleViewBinder$$ExternalSyntheticLambda110;
            }
            obj2 = objRememberedValue2;
            scenarioCapsuleViewBinder.CapsuleText(rowScope, KEY_SCENARIO_TEXT, 2131365614, null, (Function0) obj2, composerStartRestartGroup, 262198);
            boolean zChanged3 = composerStartRestartGroup.changed(stateCollectAsState2);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged3 || objRememberedValue3 == Composer.Companion.getEmpty()) {
                obj3 = objRememberedValue3;
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19 scenarioCapsuleViewBinder$$ExternalSyntheticLambda111 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda19(3);
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda111.f$0 = stateCollectAsState2;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(scenarioCapsuleViewBinder$$ExternalSyntheticLambda111);
                obj3 = scenarioCapsuleViewBinder$$ExternalSyntheticLambda111;
            }
            obj3 = objRememberedValue3;
            Function0 function0 = (Function0) obj3;
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda24 scenarioCapsuleViewBinder$$ExternalSyntheticLambda24 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda24(0);
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda24.f$0 = fDimensionResource3;
            VarHandle.storeStoreFence();
            scenarioCapsuleViewBinder.ImageAnimatedVisibility(function0, null, ComposableLambdaKt.rememberComposableLambda(-1012817377, true, scenarioCapsuleViewBinder$$ExternalSyntheticLambda24, composerStartRestartGroup, 54), composerStartRestartGroup, 4480);
            composerStartRestartGroup = composerStartRestartGroup;
            scenarioCapsuleViewBinder.PrivacyIcons(PREFIX_SCENARIO, stateCollectAsState4, composerStartRestartGroup, 518);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda8 scenarioCapsuleViewBinder$$ExternalSyntheticLambda8 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda8(2);
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda8.f$0 = this;
            VarHandle.storeStoreFence();
            scopeUpdateScopeEndRestartGroup.updateScope(scenarioCapsuleViewBinder$$ExternalSyntheticLambda8);
        }
    }

    public final void SyncHostVisibility(View view, Composer composer, int i) {
        Object obj;
        Composer composerStartRestartGroup = composer.startRestartGroup(-795048593);
        int i2 = i | (composerStartRestartGroup.changedInstance(view) ? 4 : 2) | (composerStartRestartGroup.changedInstance(this) ? 32 : 16);
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-795048593, i2, -1, "com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder.SyncHostVisibility (ScenarioCapsuleViewBinder.kt:217)");
            }
            State stateCollectAsState = SnapshotStateKt.collectAsState(((PrivacyScenarioCapsuleViewModel) composerStartRestartGroup.consume(LocalViewModel)).getShowCapsule(), (CoroutineContext) null, composerStartRestartGroup, 0, 1);
            boolean z = ((Configuration) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalConfiguration())).orientation == 2;
            boolean zChanged = composerStartRestartGroup.changed(stateCollectAsState) | composerStartRestartGroup.changed(z) | composerStartRestartGroup.changedInstance(view);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                obj = objRememberedValue;
                ScenarioCapsuleViewBinder$$ExternalSyntheticLambda9 scenarioCapsuleViewBinder$$ExternalSyntheticLambda9 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda9();
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda9.f$0 = z;
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda9.f$1 = view;
                scenarioCapsuleViewBinder$$ExternalSyntheticLambda9.f$2 = stateCollectAsState;
                VarHandle.storeStoreFence();
                composerStartRestartGroup.updateRememberedValue(scenarioCapsuleViewBinder$$ExternalSyntheticLambda9);
                obj = scenarioCapsuleViewBinder$$ExternalSyntheticLambda9;
            }
            obj = objRememberedValue;
            EffectsKt.SideEffect((Function0) obj, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            ScenarioCapsuleViewBinder$$ExternalSyntheticLambda3 scenarioCapsuleViewBinder$$ExternalSyntheticLambda3 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda3(1);
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda3.f$0 = this;
            scenarioCapsuleViewBinder$$ExternalSyntheticLambda3.f$1 = view;
            VarHandle.storeStoreFence();
            scopeUpdateScopeEndRestartGroup.updateScope(scenarioCapsuleViewBinder$$ExternalSyntheticLambda3);
        }
    }

    public final View bind(ViewGroup viewGroup, PrivacyScenarioCapsuleViewModel privacyScenarioCapsuleViewModel) {
        final Context context = viewGroup.getContext();
        SpotLightFrameLayout spotLightFrameLayout = new SpotLightFrameLayout(context) { // from class: com.oplus.systemui.scenario.host.impl.domain.binder.ScenarioCapsuleViewBinder$bind$flContainer$1
            public final PressFeedbackHelper.QsTilePressFeedback pressFeedbackHelper;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(context, null, 0, 0, 14, null);
                context.getClass();
                this.pressFeedbackHelper = new PressFeedbackHelper.QsTilePressFeedback(this);
            }

            @Override // com.oplus.systemui.qs.base.spotlight.SpotLightFrameLayout, android.view.ViewGroup, android.view.View
            public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
                PressFeedbackHelper.onTouchPressEvent(motionEvent, this.pressFeedbackHelper);
                return super.dispatchTouchEvent(motionEvent);
            }

            public final PressFeedbackHelper.QsTilePressFeedback getPressFeedbackHelper() {
                return this.pressFeedbackHelper;
            }

            @Override // com.oplus.systemui.qs.base.spotlight.SpotLightFrameLayout
            public int getSpotLightType() {
                return 1;
            }

            @Override // android.view.View
            public final void onSizeChanged(int i, int i2, int i3, int i4) {
                super.onSizeChanged(i, i2, i3, i4);
                SharedSpotLightEffect.updateBounds(this, getWidth(), getHeight());
            }

            @Override // com.oplus.systemui.qs.base.spotlight.SpotLightFrameLayout
            public final void updateSpotLightPath(Path path, Object obj) {
                float dimension = getResources().getDimension(2131171089);
                path.addRoundRect(0.0f, 0.0f, getWidth(), getHeight(), dimension, dimension, Path.Direction.CW);
            }
        };
        spotLightFrameLayout.setClipToPadding(false);
        spotLightFrameLayout.setClipChildren(false);
        spotLightFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 17));
        ComposeView composeView = new ComposeView(viewGroup.getContext(), (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
        composeView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 17));
        ScenarioCapsuleViewBinder$$ExternalSyntheticLambda3 scenarioCapsuleViewBinder$$ExternalSyntheticLambda3 = new ScenarioCapsuleViewBinder$$ExternalSyntheticLambda3(0);
        scenarioCapsuleViewBinder$$ExternalSyntheticLambda3.f$0 = privacyScenarioCapsuleViewModel;
        scenarioCapsuleViewBinder$$ExternalSyntheticLambda3.f$1 = viewGroup;
        VarHandle.storeStoreFence();
        composeView.setContent(ComposableLambdaKt.composableLambdaInstance(1972221644, true, scenarioCapsuleViewBinder$$ExternalSyntheticLambda3));
        spotLightFrameLayout.addView(composeView);
        viewGroup.addView(spotLightFrameLayout);
        return spotLightFrameLayout;
    }
}
