package com.oplus.systemui.volume.repository;

import android.net.Uri;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.VolumeFeatureOption;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class SuperVolumeRepository$observeSettings$1 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SuperVolumeRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SuperVolumeRepository$observeSettings$1(SuperVolumeRepository superVolumeRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = superVolumeRepository;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        SuperVolumeRepository$observeSettings$1 superVolumeRepository$observeSettings$1 = new SuperVolumeRepository$observeSettings$1(this.this$0, continuation);
        superVolumeRepository$observeSettings$1.L$0 = obj;
        return superVolumeRepository$observeSettings$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return create((Uri) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Uri uri = (Uri) this.L$0;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        boolean zAreEqual = Intrinsics.areEqual(uri, this.this$0.mMusicSuperVolumeUri);
        SuperVolumeRepository superVolumeRepository = this.this$0;
        if (zAreEqual) {
            superVolumeRepository.handleStreamChange(3, 0);
        } else {
            boolean zAreEqual2 = Intrinsics.areEqual(uri, superVolumeRepository.mAlarmSuperVolumeUri);
            SuperVolumeRepository superVolumeRepository2 = this.this$0;
            if (zAreEqual2) {
                superVolumeRepository2.handleStreamChange(4, 0);
            } else {
                boolean zAreEqual3 = Intrinsics.areEqual(uri, superVolumeRepository2.mRingSuperVolumeUri);
                SuperVolumeRepository superVolumeRepository3 = this.this$0;
                if (zAreEqual3) {
                    superVolumeRepository3.handleStreamChange(2, 0);
                } else {
                    boolean zAreEqual4 = Intrinsics.areEqual(uri, superVolumeRepository3.mNotificationSuperVolumeUri);
                    SuperVolumeRepository superVolumeRepository4 = this.this$0;
                    if (zAreEqual4) {
                        superVolumeRepository4.handleStreamChange(5, 0);
                    } else {
                        boolean zAreEqual5 = Intrinsics.areEqual(uri, superVolumeRepository4.mCallSuperVolumeUri);
                        SuperVolumeRepository superVolumeRepository5 = this.this$0;
                        if (zAreEqual5) {
                            superVolumeRepository5.getClass();
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            FlavorOneFeatureOption.isFlavorOneDevice();
                            VolumeFeatureOption.INSTANCE.getClass();
                            if (VolumeFeatureOption.isOplusSuperVolumeCall()) {
                                superVolumeRepository5.handleStreamChange(0, 1);
                            }
                        } else if (Intrinsics.areEqual(uri, superVolumeRepository5.mCallSuperVolumeSpeakerUri)) {
                            SuperVolumeRepository superVolumeRepository6 = this.this$0;
                            superVolumeRepository6.getClass();
                            FlavorOneFeatureOption.INSTANCE.getClass();
                            if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                                VolumeFeatureOption.INSTANCE.getClass();
                                if (VolumeFeatureOption.isOplusSuperVolumeCall()) {
                                    superVolumeRepository6.handleStreamChange(0, 2);
                                }
                            }
                        }
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }
}
