package com.oplus.systemui.notification.color;

import android.animation.ValueAnimator;
import android.app.WallpaperColors;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import androidx.collection.FloatList$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dumpable;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.keyguard.helper.ColorChangeCallback;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor$getTopActivity$2$;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.oplus.materialsdk.uxcolor.UxWallpaperColors;
import com.oplus.materialsdk.uxcolor.color.Material3ColorManager;
import com.oplus.materialsdk.uxcolor.color.MaterialStyle;
import com.oplus.systemui.keyguard.helper.OplusKeyguardTopAreaColorHelper;
import com.oplus.systemui.notification.blur.ViewBlurManager;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MaterialColorManager implements ColorChangeCallback, IObserverListener, Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MaterialColorManager";
    private final CoroutineScope applicationScope;
    private ValueAnimator colorTransitionAnimator;
    private final Context context;
    public int currentButtonMaterialColor;
    public int currentCapsuleMaterialColor;
    public int currentMaterialColor;
    private final Lazy isLowGaussianLevel$delegate;
    private Integer keyguardButtonMaterialColor;
    private Integer keyguardCapsuleMaterialColor;
    private Integer keyguardMaterialColor;
    private final dagger.Lazy keyguardTopAreaColorHelper;
    private WallpaperColors keyguardWallpaperColors;
    private final List<MaterialColorChangeCallback> materialColorChangeCallbacks;
    private Integer notificationCenterButtonMaterialColor;
    private Integer notificationCenterMaterialColor;
    private Function1 onCurrentMaterialColorChanged;
    private final SysuiStatusBarStateController statusBarStateController;
    private final dagger.Lazy viewBlurManager;
    private WallpaperColors wallpaperColors;

    /* JADX INFO: renamed from: com.oplus.systemui.notification.color.MaterialColorManager$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements StatusBarStateController.StateListener {
        public /* synthetic */ MaterialColorManager this$0;

        public final void onStateChanged(int i) {
            MaterialColorManager.updateLowGaussianRowColor$default(this.this$0, 3);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface MaterialColorChangeCallback {
        void onMaterialColorChanged();
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.color.MaterialColorManager$onColorBitmapChanged$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C01831 extends SuspendLambda implements Function2 {
        final /* synthetic */ Bitmap $bitmap;
        int label;
        final /* synthetic */ MaterialColorManager this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.color.MaterialColorManager$onColorBitmapChanged$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00121 extends SuspendLambda implements Function2 {
            final /* synthetic */ Bitmap $bitmap;
            int label;
            final /* synthetic */ MaterialColorManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00121(Bitmap bitmap, MaterialColorManager materialColorManager, Continuation continuation) {
                super(2, continuation);
                this.this$0 = materialColorManager;
                this.$bitmap = bitmap;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00121(this.$bitmap, this.this$0, continuation);
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
                MaterialColorManager materialColorManager = this.this$0;
                Bitmap bitmap = this.$bitmap;
                materialColorManager.getClass();
                Bitmap bitmapCreateScaledBitmap = bitmap != null ? Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 20, bitmap.getHeight() / 20, true) : null;
                if (bitmapCreateScaledBitmap == null) {
                    return null;
                }
                this.this$0.keyguardWallpaperColors = WallpaperColors.fromBitmap(bitmapCreateScaledBitmap);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01831(Bitmap bitmap, MaterialColorManager materialColorManager, Continuation continuation) {
            super(2, continuation);
            this.this$0 = materialColorManager;
            this.$bitmap = bitmap;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new C01831(this.$bitmap, this.this$0, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io = Dispatchers.getIO();
                C00121 c00121 = new C00121(this.$bitmap, this.this$0, null);
                this.label = 1;
                if (BuildersKt.withContext(io, c00121, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            this.this$0.updateLowGaussianRowColor(true, true);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.color.MaterialColorManager$onWallpaperBitmapChanged$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C01841 extends SuspendLambda implements Function2 {
        final /* synthetic */ Bitmap $bitmap;
        int label;
        final /* synthetic */ MaterialColorManager this$0;

        /* JADX INFO: renamed from: com.oplus.systemui.notification.color.MaterialColorManager$onWallpaperBitmapChanged$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class C00131 extends SuspendLambda implements Function2 {
            final /* synthetic */ Bitmap $bitmap;
            int label;
            final /* synthetic */ MaterialColorManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00131(Bitmap bitmap, MaterialColorManager materialColorManager, Continuation continuation) {
                super(2, continuation);
                this.$bitmap = bitmap;
                this.this$0 = materialColorManager;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                return new C00131(this.$bitmap, this.this$0, continuation);
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
                Bitmap bitmap = this.$bitmap;
                if (bitmap == null) {
                    return null;
                }
                this.this$0.wallpaperColors = WallpaperColors.fromBitmap(bitmap);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01841(Bitmap bitmap, MaterialColorManager materialColorManager, Continuation continuation) {
            super(2, continuation);
            this.this$0 = materialColorManager;
            this.$bitmap = bitmap;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return new C01841(this.$bitmap, this.this$0, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineDispatcher io = Dispatchers.getIO();
                C00131 c00131 = new C00131(this.$bitmap, this.this$0, null);
                this.label = 1;
                if (BuildersKt.withContext(io, c00131, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            MaterialColorManager.updateLowGaussianRowColor$default(this.this$0, 2);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.color.MaterialColorManager$updateLowGaussianRowColor$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class C01851 extends SuspendLambda implements Function2 {
        final /* synthetic */ boolean $useTransition;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01851(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$useTransition = z;
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return MaterialColorManager.this.new C01851(this.$useTransition, continuation);
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
            MaterialColorManager materialColorManager = MaterialColorManager.this;
            MaterialColorManager.access$updateColorValues(materialColorManager, OplusDarkModeUtil.isNightMode(materialColorManager.context));
            MaterialColorManager.this.setCurrentColorsByStateOnUiThread(this.$useTransition);
            return Unit.INSTANCE;
        }
    }

    public MaterialColorManager(Context context, SysuiStatusBarStateController sysuiStatusBarStateController, dagger.Lazy lazy, CoroutineScope coroutineScope, dagger.Lazy lazy2) {
        this.context = context;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.keyguardTopAreaColorHelper = lazy;
        this.applicationScope = coroutineScope;
        this.viewBlurManager = lazy2;
        MaterialColorManager$$ExternalSyntheticLambda0 materialColorManager$$ExternalSyntheticLambda0 = new MaterialColorManager$$ExternalSyntheticLambda0();
        materialColorManager$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.isLowGaussianLevel$delegate = LazyKt__LazyJVMKt.lazy(materialColorManager$$ExternalSyntheticLambda0);
        this.notificationCenterButtonMaterialColor = 0;
        this.materialColorChangeCallbacks = new ArrayList();
        if (isLowGaussianLevel$1()) {
            SuperPowerSaveSettingsObserver.Companion.getInstance().addListener(this);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            sysuiStatusBarStateController.addCallback(anonymousClass1);
        }
        if (!isLowGaussianLevel$1() || ScrimUtil.isWallpaperBlurDisable()) {
            return;
        }
        ((OplusKeyguardTopAreaColorHelper) lazy.get()).addCallback(this);
    }

    public static final boolean access$shouldSetTransparentColors(MaterialColorManager materialColorManager) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            int animLevel = ScrimUtil.getAnimLevel();
            ScrimControllerEx.Companion.getClass();
            boolean z = ScrimControllerEx.isThirdTheme;
            boolean z2 = SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState;
            int state = materialColorManager.statusBarStateController.getState();
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("shouldSetTransparentColors anim_level = ", animLevel, ", isThirdTheme = ", ",  isSuperPowerSaveState = ", z);
            sbM.append(z2);
            sbM.append(", statusBarState = ");
            sbM.append(state);
            Log.d("SystemUi--Notification", "MaterialColorManager-->".concat(sbM.toString()));
        }
        if (ScrimUtil.isMiddleOrHighAnimLevel(materialColorManager.context)) {
            return true;
        }
        ScrimControllerEx.Companion.getClass();
        if (ScrimControllerEx.isThirdTheme || SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState) {
            return true;
        }
        return ScrimUtil.isBPAnimLevel() && !((ViewBlurManager) materialColorManager.viewBlurManager.get()).isKeyguardMaterialColorState();
    }

    public static final void access$updateColorValues(MaterialColorManager materialColorManager, boolean z) {
        materialColorManager.getClass();
        double d = z ? 30.0d : 95.0d;
        double d2 = z ? 40.0d : 70.0d;
        WallpaperColors wallpaperColors = materialColorManager.keyguardWallpaperColors;
        if (wallpaperColors != null) {
            Integer numValueOf = Integer.valueOf(materialColorManager.resolveUxSnColor(wallpaperColors, z, d));
            materialColorManager.keyguardMaterialColor = numValueOf;
            if (!z) {
                numValueOf = Integer.valueOf(materialColorManager.resolveUxSnColor(wallpaperColors, false, 90.0d));
            }
            materialColorManager.keyguardCapsuleMaterialColor = numValueOf;
            materialColorManager.keyguardButtonMaterialColor = Integer.valueOf(materialColorManager.resolveUxSnColor(wallpaperColors, z, d2));
        }
        WallpaperColors wallpaperColors2 = materialColorManager.wallpaperColors;
        if (wallpaperColors2 != null) {
            materialColorManager.notificationCenterMaterialColor = Integer.valueOf(materialColorManager.resolveUxSnColor(wallpaperColors2, z, d));
            materialColorManager.notificationCenterButtonMaterialColor = Integer.valueOf(materialColorManager.resolveUxSnColor(wallpaperColors2, z, d2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrentColorsByState(boolean z) {
        if (((ViewBlurManager) this.viewBlurManager.get()).isKeyguardMaterialColorState()) {
            Integer num = this.keyguardMaterialColor;
            int iIntValue = num != null ? num.intValue() : 0;
            Integer num2 = this.keyguardCapsuleMaterialColor;
            setCurrentColorWithTransition(iIntValue, z, Integer.valueOf(num2 != null ? num2.intValue() : 0));
            Integer num3 = this.keyguardButtonMaterialColor;
            this.currentButtonMaterialColor = num3 != null ? num3.intValue() : 0;
        } else if (ScrimUtil.isBAnimLevel()) {
            Integer num4 = this.notificationCenterMaterialColor;
            setCurrentColorWithTransition(num4 != null ? num4.intValue() : 0, z, null);
            Integer num5 = this.notificationCenterButtonMaterialColor;
            this.currentButtonMaterialColor = num5 != null ? num5.intValue() : 0;
        }
        notifyMaterialColorChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrentColorsByStateOnUiThread(boolean z) {
        setCurrentColorsByStateOnUiThread.1 r0 = new setCurrentColorsByStateOnUiThread.1();
        r0.this$0 = this;
        r0.$useTransition = z;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnUiThread(r0);
    }

    public static /* synthetic */ void updateLowGaussianRowColor$default(MaterialColorManager materialColorManager, int i) {
        materialColorManager.updateLowGaussianRowColor((i & 1) == 0, false);
    }

    public final void addMaterialColorCallback(MaterialColorChangeCallback materialColorChangeCallback) {
        if (this.materialColorChangeCallbacks.contains(materialColorChangeCallback)) {
            return;
        }
        this.materialColorChangeCallbacks.add(materialColorChangeCallback);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println();
        printWriter.println("MaterialColorManager:");
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("currentMaterialColor=", this.currentMaterialColor, printWriter);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("currentCapsuleMaterialColor=", this.currentCapsuleMaterialColor, printWriter);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("currentButtonMaterialColor=", this.currentButtonMaterialColor, printWriter);
        printWriter.println("keyguardMaterialColor=" + this.keyguardMaterialColor);
        printWriter.println("notificationCenterMaterialColor=" + this.notificationCenterMaterialColor);
    }

    public final int getCurrentButtonMaterialColor() {
        return this.currentButtonMaterialColor;
    }

    public final int getCurrentCapsuleMaterialColor() {
        return this.currentCapsuleMaterialColor;
    }

    public final int getCurrentMaterialColor() {
        return this.currentMaterialColor;
    }

    public final Integer getKeyguardMaterialColor() {
        return this.keyguardMaterialColor;
    }

    public final Integer getNotificationCenterButtonMaterialColor() {
        return this.notificationCenterButtonMaterialColor;
    }

    public final Integer getNotificationCenterMaterialColor() {
        return this.notificationCenterMaterialColor;
    }

    public final Function1 getOnCurrentMaterialColorChanged() {
        return this.onCurrentMaterialColorChanged;
    }

    public final boolean isLowGaussianLevel$1() {
        return ((Boolean) this.isLowGaussianLevel$delegate.getValue()).booleanValue();
    }

    public final void notifyMaterialColorChanged() {
        Object obj;
        Iterator it = CollectionsKt___CollectionsKt.toList(this.materialColorChangeCallbacks).iterator();
        while (it.hasNext()) {
            try {
                ((MaterialColorChangeCallback) it.next()).onMaterialColorChanged();
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                GestureInteractor$getTopActivity$2$.ExternalSyntheticOutline0.m(8, "Error in material color change callback, error: ", th2.getMessage(), LogUtil.TAG_NOTIFICATION, TAG);
            }
        }
    }

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        updateLowGaussianRowColor$default(this, 3);
    }

    public void onColorBitmapChanged(boolean z, Bitmap bitmap) {
        if (isLowGaussianLevel$1()) {
            BuildersKt.launch$default(this.applicationScope, Dispatchers.getMain(), (CoroutineStart) null, new C01831(bitmap, this, null), 2);
        }
    }

    public final void onColorChanged(int i, boolean z) {
    }

    public final void onWallpaperBitmapChanged(Bitmap bitmap) {
        if (isLowGaussianLevel$1()) {
            BuildersKt.launch$default(this.applicationScope, Dispatchers.getMain(), (CoroutineStart) null, new C01841(bitmap, this, null), 2);
        }
    }

    public final void removeMaterialColorCallback(MaterialColorChangeCallback materialColorChangeCallback) {
        this.materialColorChangeCallbacks.remove(materialColorChangeCallback);
    }

    public final int resolveUxSnColor(WallpaperColors wallpaperColors, boolean z, double d) {
        Context context = this.context;
        int argb = wallpaperColors.getPrimaryColor().toArgb();
        int i = UxWallpaperColors.COLOR_MIDDLE_GRAY;
        return new Material3ColorManager().getUxSn(context, argb, z, d, MaterialStyle.CONTENT).intValue();
    }

    public final void setCurrentButtonMaterialColor(int i) {
        this.currentButtonMaterialColor = i;
    }

    public final void setCurrentCapsuleMaterialColor(int i) {
        this.currentCapsuleMaterialColor = i;
    }

    public final void setCurrentColorWithTransition(int i, boolean z, Integer num) {
        int i2 = this.currentMaterialColor;
        int i3 = this.currentCapsuleMaterialColor;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("setCurrentColorWithTransition material=", i2, i, "->", ", capsule=");
        sbM.append(i3);
        sbM.append("->");
        sbM.append(num);
        sbM.append(", useTransition=");
        sbM.append(z);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
        if (!z || this.statusBarStateController.getState() != 1) {
            setCurrentMaterialColor(i);
            if (num != null) {
                this.currentCapsuleMaterialColor = num.intValue();
                return;
            }
            return;
        }
        ValueAnimator valueAnimator = this.colorTransitionAnimator;
        if (valueAnimator != null) {
            ValueAnimator valueAnimator2 = valueAnimator.isRunning() ? valueAnimator : null;
            if (valueAnimator2 != null) {
                valueAnimator2.cancel();
            }
        }
        int i4 = this.currentMaterialColor;
        int i5 = this.currentCapsuleMaterialColor;
        ArgbEvaluatorCompat argbEvaluatorCompat = ArgbEvaluatorCompat.getInstance();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(300L);
        MaterialColorManager$startColorTransition$2$1 materialColorManager$startColorTransition$2$1 = new MaterialColorManager$startColorTransition$2$1();
        materialColorManager$startColorTransition$2$1.this$0 = this;
        materialColorManager$startColorTransition$2$1.$evaluator = argbEvaluatorCompat;
        materialColorManager$startColorTransition$2$1.$materialStart = i4;
        materialColorManager$startColorTransition$2$1.$targetMaterialColor = i;
        materialColorManager$startColorTransition$2$1.$capsuleTargetColor = num;
        materialColorManager$startColorTransition$2$1.$capsuleStart = i5;
        VarHandle.storeStoreFence();
        valueAnimatorOfFloat.addUpdateListener(materialColorManager$startColorTransition$2$1);
        valueAnimatorOfFloat.start();
        this.colorTransitionAnimator = valueAnimatorOfFloat;
    }

    public final void setCurrentMaterialColor(int i) {
        if (this.currentMaterialColor != i) {
            this.currentMaterialColor = i;
            Function1 function1 = this.onCurrentMaterialColorChanged;
            if (function1 != null) {
                function1.invoke(Integer.valueOf(i));
            }
        }
    }

    public final void setKeyguardMaterialColor(Integer num) {
        this.keyguardMaterialColor = num;
    }

    public final void setNotificationCenterButtonMaterialColor(Integer num) {
        this.notificationCenterButtonMaterialColor = num;
    }

    public final void setNotificationCenterMaterialColor(Integer num) {
        this.notificationCenterMaterialColor = num;
    }

    public final void setOnCurrentMaterialColorChanged(Function1 function1) {
        this.onCurrentMaterialColorChanged = function1;
    }

    public final void updateLowGaussianRowColor(boolean z, boolean z2) {
        if (isLowGaussianLevel$1()) {
            LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("updateLowGaussianRowColor: needUpdate = ", ", useTransition = ", z, z2), null, 8);
            if (z) {
                BuildersKt.launch$default(this.applicationScope, Dispatchers.getIO(), (CoroutineStart) null, new C01851(z2, null), 2);
            } else {
                setCurrentColorsByStateOnUiThread(z2);
            }
        }
    }
}
