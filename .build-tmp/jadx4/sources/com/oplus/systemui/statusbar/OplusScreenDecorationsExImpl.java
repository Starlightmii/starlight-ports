package com.oplus.systemui.statusbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.SystemProperties;
import android.support.v4.content.FileProvider$;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.DisplayInfo;
import android.view.ViewGroup;
import androidx.collection.FloatList$;
import com.android.systemui.decor.CutoutDecorProviderFactory;
import com.android.systemui.decor.DecorProvider;
import com.android.systemui.decor.DecorProviderFactory;
import com.android.systemui.decor.RoundedCornerResDelegate;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.OplusPrivacyDotViewController;
import com.android.systemui.statusbar.ScreenDecorationsEx;
import com.android.systemui.statusbar.data.repository.HomeStatusBarComponentsRepository;
import com.android.systemui.statusbar.events.PrivacyDotViewController;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.oplus.systemui.keyguard.clock.WeatherInfoParseHelper$;
import com.oplus.systemui.privacy.PrivacyDotLayoutState;
import com.oplus.systemui.statusbar.corner.OnePxViewController;
import com.oplus.systemui.statusbar.corner.OplusCutoutDecorProviderFactory;
import com.oplus.systemui.statusbar.corner.OplusOnePxDecorProviderFactory;
import com.oplus.systemui.statusbar.corner.OplusRoundedCornerDecorProviderFactory;
import com.oplus.zoom.common.ZoomParameterHelper;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.StatusBarFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusScreenDecorationsExImpl extends ScreenDecorationsEx {
    public static final Companion Companion = new Companion();
    private static final String SYS_OPLUS_ROUND_CORNERS = "sys.oplus.roundcorners";
    private static final String TAG = "ScreenDecorationsEx";
    private final CoroutineScope applicationScope;
    private final HomeStatusBarComponentsRepository homeStatusBarComponentsRepository;
    public OplusOnePxDecorProviderFactory onePxDecorProviderFactory;
    private final OnePxViewController onePxViewController;
    private final OplusPrivacyDotViewController oplusPrivacyDotViewController;
    private final PrivacyDotViewController privacyDotViewController;
    private final DelayableExecutor uiExecutor;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.OplusScreenDecorationsExImpl$startOnScreenDecorationsThread$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.OplusScreenDecorationsExImpl$startOnScreenDecorationsThread$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C01161 implements FlowCollector {
            public /* synthetic */ OplusScreenDecorationsExImpl this$0;

            public final Object emit(Object obj, Continuation continuation) {
                PrivacyDotLayoutState privacyDotLayoutState = (PrivacyDotLayoutState) obj;
                OplusScreenDecorationsExImpl oplusScreenDecorationsExImpl = this.this$0;
                Resources resources = oplusScreenDecorationsExImpl.getContext().getResources();
                boolean z = resources.getConfiguration().getLayoutDirection() == 1;
                int dimensionPixelSize = resources.getDimensionPixelSize(2131171476);
                int burnInTranslationX = (int) privacyDotLayoutState.getBurnInTranslationX();
                oplusScreenDecorationsExImpl.updateDotContentsPadding(privacyDotLayoutState.getStatusBarPaddingTop(), privacyDotLayoutState.getStatusBarPaddingBottom(), z ? ((privacyDotLayoutState.getStatusBarPaddingLeft() + privacyDotLayoutState.getLeftInset()) - dimensionPixelSize) + burnInTranslationX : ((privacyDotLayoutState.getStatusBarPaddingRight() + privacyDotLayoutState.getRightInset()) - dimensionPixelSize) - burnInTranslationX);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return OplusScreenDecorationsExImpl.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow defaultPrivacyDotLayoutStateFlow = OplusScreenDecorationsExImpl.this.getDefaultPrivacyDotLayoutStateFlow();
                OplusScreenDecorationsExImpl oplusScreenDecorationsExImpl = OplusScreenDecorationsExImpl.this;
                C01161 c01161 = new C01161();
                c01161.this$0 = oplusScreenDecorationsExImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (defaultPrivacyDotLayoutStateFlow.collect(c01161, this) == coroutine_suspended) {
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

    public OplusScreenDecorationsExImpl(Context context, PrivacyDotViewController privacyDotViewController, OplusPrivacyDotViewController oplusPrivacyDotViewController, OnePxViewController onePxViewController, HomeStatusBarComponentsRepository homeStatusBarComponentsRepository, CoroutineScope coroutineScope, DelayableExecutor delayableExecutor) {
        super(context);
        this.privacyDotViewController = privacyDotViewController;
        this.oplusPrivacyDotViewController = oplusPrivacyDotViewController;
        this.onePxViewController = onePxViewController;
        this.homeStatusBarComponentsRepository = homeStatusBarComponentsRepository;
        this.applicationScope = coroutineScope;
        this.uiExecutor = delayableExecutor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Flow getDefaultPrivacyDotLayoutStateFlow() {
        StateFlow componentsByDisplayId = this.homeStatusBarComponentsRepository.getComponentsByDisplayId();
        OplusScreenDecorationsExImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$map$1 oplusScreenDecorationsExImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$map$1 = new OplusScreenDecorationsExImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$map$1();
        oplusScreenDecorationsExImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$map$1.$this_unsafeTransform$inlined = componentsByDisplayId;
        VarHandle.storeStoreFence();
        return FlowKt.transformLatest(FlowKt.distinctUntilChanged(oplusScreenDecorationsExImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$map$1), new OplusScreenDecorationsExImpl$getDefaultPrivacyDotLayoutStateFlow$$inlined$flatMapLatest$1(3, null));
    }

    public CutoutDecorProviderFactory getCutoutDecorProviderFactory(Resources resources, Display display, RoundedCornerResDelegate roundedCornerResDelegate) {
        return new OplusCutoutDecorProviderFactory(getContext(), resources, display, roundedCornerResDelegate);
    }

    public Drawable getCutoutDrawable(int i) {
        StatusBarFeatureOption.INSTANCE.getClass();
        String strM = (!StatusBarFeatureOption.isScreenSelect() || SystemProperties.get(SYS_OPLUS_ROUND_CORNERS).length() <= 0) ? "" : FileProvider$.ExternalSyntheticOutline0.m("_", SystemProperties.get(SYS_OPLUS_ROUND_CORNERS));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("CutoutDrawable, Tag: ", strM, "ScreenDecorationsEx-->", "SystemUi--Statusbar");
        }
        return getDrawable(i, 2130903134, getContext().getResources().getIdentifier("oplus_rc_cutout" + ((Object) strM), "drawable", "oplus"), strM);
    }

    public final Drawable getDrawable(int i, int i2, int i3, String str) {
        TypedArray typedArrayObtainTypedArray = getContext().getResources().obtainTypedArray(i2);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "displayConfigIndex: ", "ScreenDecorationsEx-->", "SystemUi--Statusbar");
        }
        if (i >= 0 && i < typedArrayObtainTypedArray.length()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("getIdentifier: ", typedArrayObtainTypedArray.getString(i), "ScreenDecorationsEx-->", "SystemUi--Statusbar");
            }
            i3 = getContext().getResources().getIdentifier(typedArrayObtainTypedArray.getString(i) + str, "drawable", "oplus");
        }
        typedArrayObtainTypedArray.recycle();
        return getContext().getResources().getDrawableForDensity(i3, ZoomParameterHelper.SMALL_SCREEN_HEIGHT_THRESHOLD_DP, null);
    }

    public List<DecorProvider> getOplusProviders(RoundedCornerResDelegate roundedCornerResDelegate) {
        if (this.onePxDecorProviderFactory == null) {
            this.onePxDecorProviderFactory = new OplusOnePxDecorProviderFactory(roundedCornerResDelegate);
        }
        OplusOnePxDecorProviderFactory oplusOnePxDecorProviderFactory = this.onePxDecorProviderFactory;
        if (oplusOnePxDecorProviderFactory == null) {
            oplusOnePxDecorProviderFactory = null;
        }
        return oplusOnePxDecorProviderFactory.getProviders();
    }

    public DecorProviderFactory getRoundedCornerDecorProviderFactory(RoundedCornerResDelegate roundedCornerResDelegate) {
        return new OplusRoundedCornerDecorProviderFactory(roundedCornerResDelegate);
    }

    public Drawable getRoundedCornerDrawable(int i, int i2) {
        int identifier;
        int identifier2;
        int identifier3;
        int identifier4;
        StatusBarFeatureOption.INSTANCE.getClass();
        String strM = (!StatusBarFeatureOption.isScreenSelect() || SystemProperties.get(SYS_OPLUS_ROUND_CORNERS).length() <= 0) ? "" : FileProvider$.ExternalSyntheticOutline0.m("_", SystemProperties.get(SYS_OPLUS_ROUND_CORNERS));
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            WeatherInfoParseHelper$.ExternalSyntheticOutline0.m("Tag: ", strM, "ScreenDecorationsEx-->", "SystemUi--Statusbar");
        }
        if (i2 == 0) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isFoldRemapDisabled()) {
                identifier = getContext().getResources().getIdentifier("oplus_rc_fold_top_left", "drawable", "oplus");
            } else {
                identifier = getContext().getResources().getIdentifier("oplus_rc_top_left" + ((Object) strM), "drawable", "oplus");
            }
            return getDrawable(i, 2130903166, identifier, strM);
        }
        if (i2 == 1) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isFoldRemapDisabled()) {
                identifier2 = getContext().getResources().getIdentifier("oplus_rc_fold_top_right", "drawable", "oplus");
            } else {
                identifier2 = getContext().getResources().getIdentifier("oplus_rc_top_right" + ((Object) strM), "drawable", "oplus");
            }
            return getDrawable(i, 2130903167, identifier2, strM);
        }
        if (i2 == 2) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isFoldRemapDisabled()) {
                identifier3 = getContext().getResources().getIdentifier("oplus_rc_fold_bottom_left", "drawable", "oplus");
            } else {
                identifier3 = getContext().getResources().getIdentifier("oplus_rc_bottom_left" + ((Object) strM), "drawable", "oplus");
            }
            return getDrawable(i, 2130903162, identifier3, strM);
        }
        if (i2 != 3) {
            return super.getRoundedCornerDrawable(i, i2);
        }
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isFoldRemapDisabled()) {
            identifier4 = getContext().getResources().getIdentifier("oplus_rc_fold_bottom_right", "drawable", "oplus");
        } else {
            identifier4 = getContext().getResources().getIdentifier("oplus_rc_bottom_right" + ((Object) strM), "drawable", "oplus");
        }
        return getDrawable(i, 2130903163, identifier4, strM);
    }

    public final DelayableExecutor getUiExecutor() {
        return this.uiExecutor;
    }

    public boolean isCenterHole(DisplayInfo displayInfo) {
        DisplayCutout displayCutout = displayInfo.displayCutout;
        return (displayCutout == null || displayCutout.getBoundingRectTop().isEmpty() || Math.abs(((displayInfo.getNaturalWidth() - displayCutout.getBoundingRectTop().width()) / 2) - displayCutout.getBoundingRectTop().left) > 5) ? false : true;
    }

    public void setupDecorationsInner(int i) {
        this.onePxViewController.setupOnePx(i);
    }

    public final void startOnScreenDecorationsThread(boolean z) {
        if (z) {
            this.oplusPrivacyDotViewController.init();
        } else {
            BuildersKt.launch$default(this.applicationScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        }
    }

    public void temporarilyHide(ViewGroup viewGroup, boolean z) {
        StatusBarFeatureOption.INSTANCE.getClass();
        if (StatusBarFeatureOption.isScreenResolutionSwitchSupport() && z) {
            viewGroup.setAlpha(0.0f);
            temporarilyHide.1 r0 = new temporarilyHide.1();
            r0.$overlayView = viewGroup;
            VarHandle.storeStoreFence();
            viewGroup.addOnLayoutChangeListener(r0);
        }
    }

    public final void updateColorInversion(int i) {
        this.onePxViewController.colorsInvertedValue(i);
    }

    public final void updateDotContentsPadding(int i, int i2, int i3) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            MobileInputLogger$.ExternalSyntheticOutline0.m(i3, "ScreenDecorationsEx-->", "SystemUi--Statusbar", FloatList$.ExternalSyntheticOutline0.m("updateContentsPadding, privacyPaddingTop = ", i, i2, ", paddingBottom:", ", dotMargin: "));
        }
        this.privacyDotViewController.updateCornerMarginAndPadding(i3, i, i2);
    }

    public final void updateLayoutParams(int i) {
        this.onePxViewController.updateEdgesParams(i);
    }
}
