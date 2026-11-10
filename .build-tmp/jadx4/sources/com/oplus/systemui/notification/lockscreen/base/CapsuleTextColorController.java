package com.oplus.systemui.notification.lockscreen.base;

import android.content.Context;
import android.util.Log;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.CoreStartable;
import com.android.systemui.InitController;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplus.systemui.keyguard.helper.OplusKeyguardBottomAreaColorHelper;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CapsuleTextColorController implements OplusPanoramicAodRepository.PanoramicAodProgressChangeListener, ConfigurationController.ConfigurationListener, CoreStartable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CapsuleTextColorController";
    private final CoroutineScope applicationScope;
    private final CapsuleTextColorController$colorChangeCallback$1 colorChangeCallback;
    private final Lazy configurationController;
    private final Context context;
    private final InitController initController;
    public boolean isImmersiveState;
    public boolean isPanoramicAod;
    private final Lazy keyguardBottomAreaColorHelper;
    public boolean keyguardLightColor = true;
    private final Lazy lockScreenExtStateController;
    private final OplusPanoramicAodRepository oplusPanoramicAodRepository;
    public float progress;
    private Function1 textLightColorChanged;
    public boolean uiLightModel;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.lockscreen.base.CapsuleTextColorController$start$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ CapsuleTextColorController this$0;

        public /* synthetic */ AnonymousClass2(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    CoroutineScope coroutineScope = this.this$0.applicationScope;
                    CapsuleTextColorController capsuleTextColorController = this.this$0;
                    BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new CapsuleTextColorController$start$2$1$1(capsuleTextColorController, null), 2);
                    BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new CapsuleTextColorController$start$2$1$2(capsuleTextColorController, null), 2);
                    break;
                default:
                    this.this$0.oplusPanoramicAodRepository.addCallback(this.this$0);
                    break;
            }
        }
    }

    public CapsuleTextColorController(Context context, InitController initController, OplusPanoramicAodRepository oplusPanoramicAodRepository, Lazy lazy, Lazy lazy2, Lazy lazy3, CoroutineScope coroutineScope) {
        this.context = context;
        this.initController = initController;
        this.oplusPanoramicAodRepository = oplusPanoramicAodRepository;
        this.configurationController = lazy;
        this.keyguardBottomAreaColorHelper = lazy2;
        this.lockScreenExtStateController = lazy3;
        this.applicationScope = coroutineScope;
        this.uiLightModel = !OplusDarkModeUtil.isNightMode(context);
        CapsuleTextColorController$colorChangeCallback$1 capsuleTextColorController$colorChangeCallback$1 = new CapsuleTextColorController$colorChangeCallback$1();
        capsuleTextColorController$colorChangeCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.colorChangeCallback = capsuleTextColorController$colorChangeCallback$1;
    }

    public final void applyNewTextColorModel(String str) {
        boolean z = false;
        if (!this.isPanoramicAod) {
            ScrimControllerEx.Companion.getClass();
            if (ScrimControllerEx.isThirdTheme) {
                z = true;
            } else if (!ScrimUtil.isMiddleOrHighAnimLevel(this.context)) {
                z = this.uiLightModel;
            } else if (!this.isImmersiveState) {
                z = this.keyguardLightColor;
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            boolean z2 = this.isPanoramicAod;
            boolean z3 = this.isImmersiveState;
            boolean z4 = this.uiLightModel;
            boolean z5 = this.keyguardLightColor;
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("applyNewTextColorModel:reason:", str, ", finalLightModel:", z, ", isPanoramicAod:");
            KeyguardLogger$.ExternalSyntheticOutline0.m(",  isImmersiveState:", ", uiLightModel:", sbM, z2, z3);
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(", keyguardLightColor:", "CapsuleTextColorController-->", sbM, z4, z5));
        }
        Function1 function1 = this.textLightColorChanged;
        if (function1 != null) {
            function1.invoke(Boolean.valueOf(z));
        }
    }

    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository.PanoramicAodProgressChangeListener
    public final void onPanoramicAodStateChanged(boolean z, boolean z2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("onPanoramicAodStateChange, isPanoramicAod: ", ", needUpdateUi: ", "CapsuleTextColorController-->", z, z2));
        }
    }

    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository.PanoramicAodProgressChangeListener
    public final void onProgressChanged(float f) {
        if (this.progress != 1.0f && f == 1.0f) {
            this.isPanoramicAod = true;
            applyNewTextColorModel("EnterAodEnd");
        }
        if (this.progress == 1.0f && f != 1.0f) {
            this.isPanoramicAod = false;
            applyNewTextColorModel("ExitAodStart");
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("onProgressChanged:", this.progress, LogUtil.SEPARATOR, f, "CapsuleTextColorController-->"));
        }
        this.progress = f;
    }

    public final void onUiModeChanged() {
        this.uiLightModel = !OplusDarkModeUtil.isNightMode(this.context);
        applyNewTextColorModel("onUiModeChanged");
    }

    public final void setTextLightColorChangeListener(Function1 function1) {
        this.textLightColorChanged = function1;
    }

    public final void start() {
        InitController initController = this.initController;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(1);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        initController.addPostInitTask(anonymousClass2);
        ((OplusKeyguardBottomAreaColorHelper) this.keyguardBottomAreaColorHelper.get()).addCallback(this.colorChangeCallback);
        ((ConfigurationController) this.configurationController.get()).addCallback(this);
        InitController initController2 = this.initController;
        AnonymousClass2 anonymousClass3 = new AnonymousClass2(0);
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        initController2.addPostInitTask(anonymousClass3);
    }
}
