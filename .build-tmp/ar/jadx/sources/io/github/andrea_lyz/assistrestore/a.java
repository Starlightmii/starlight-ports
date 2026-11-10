package io.github.andrea_lyz.assistrestore;

import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements XposedInterface.Hooker {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2806a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AssistRestoreModule f2807b;

    public /* synthetic */ a(int i5, AssistRestoreModule assistRestoreModule) {
        this.f2806a = i5;
        this.f2807b = assistRestoreModule;
    }

    public final Object intercept(XposedInterface.Chain chain) {
        switch (this.f2806a) {
            case 0:
                return CtsHooks.lambda$installContextualSearchAvailability$2(this.f2807b, chain);
            case 1:
                return CtsHooks.lambda$installPermissionBypass$3(this.f2807b, chain);
            case 2:
                return CtsHooks.lambda$hookStartContextualSearch$4(this.f2807b, chain);
            case 3:
                return LauncherHooks.lambda$install$0(this.f2807b, chain);
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                return SystemUiHooks.lambda$installHandleTouchRegion$9(this.f2807b, chain);
            case 5:
                return SystemUiHooks.lambda$installHiddenGestureBarHandleTouch$8(this.f2807b, chain);
            case 6:
                return SystemUiHooks.lambda$installOcrScreenHandleLongPress$3(this.f2807b, chain);
            case 7:
                return SystemUiHooks.lambda$installOcrScreenHandleLongPress$5(this.f2807b, chain);
            case 8:
                return SystemUiHooks.lambda$installHiddenBarWindowHooks$10(this.f2807b, chain);
            case 9:
                return SystemUiHooks.lambda$installHiddenBarWindowHooks$12(this.f2807b, chain);
            case 10:
                return SystemUiHooks.lambda$installHandlePressAvailability$6(this.f2807b, chain);
            default:
                return SystemUiHooks.lambda$installHandlePressAvailability$7(this.f2807b, chain);
        }
    }
}
