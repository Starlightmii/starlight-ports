package com.oplus.systemui.notification.blur;

import com.oplus.systemui.notification.row.material.NotificationBlurMode;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.util.QSBlurConfigProvider;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
final class ViewBlurManager$updateCardPlatformMixConfig$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ViewBlurManager this$0;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ViewBlurManager.CardType.values().length];
            try {
                ViewBlurManager.CardType cardType = ViewBlurManager.CardType.NOTIFICATION;
                iArr[2] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewBlurManager$updateCardPlatformMixConfig$1(ViewBlurManager viewBlurManager, Continuation continuation) {
        super(2, continuation);
        this.this$0 = viewBlurManager;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new ViewBlurManager$updateCardPlatformMixConfig$1(this.this$0, continuation);
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
        List<ViewBlurProxy> list = CollectionsKt___CollectionsKt.toList(this.this$0.viewBlurProxyes.values());
        ViewBlurManager viewBlurManager = this.this$0;
        for (ViewBlurProxy viewBlurProxy : list) {
            BlurConfig blurConfig = viewBlurProxy.getBlurConfig();
            NotificationBlurMixColorParams.INSTANCE.getClass();
            blurConfig.setBlurRadius(QSBlurConfigProvider.getDefaultBlurRadius());
            ViewBlurManager.CardType cardType = (ViewBlurManager.CardType) ViewBlurManager.CardType.getEntries().get(viewBlurProxy.getBlurConfig().getCardType());
            if (WhenMappings.$EnumSwitchMapping$0[cardType.ordinal()] == 1) {
                viewBlurProxy.applyBlurConfig();
            } else {
                NotificationBlurMode notificationBlurModeResolveBlurMode = viewBlurManager.resolveBlurMode(cardType, viewBlurProxy.getView(), null);
                viewBlurProxy.getBlurConfig().setBlurColor(viewBlurManager.cardBlurColor);
                viewBlurProxy.getBlurConfig().setMotionBlurMixConfig(viewBlurManager.headsupCardMotionMixConfig(cardType, notificationBlurModeResolveBlurMode));
                if (ScrimUtil.isBPAnimLevel()) {
                    viewBlurProxy.getBlurConfig().setPlatformMixConfig(viewBlurManager.getMixConfig(cardType, notificationBlurModeResolveBlurMode));
                }
                int iOrdinal = cardType.ordinal();
                if (iOrdinal == 0 || iOrdinal == 1 || iOrdinal == 7) {
                    viewBlurProxy.getBlurConfig().setCornerRadius(viewBlurManager.blurCornerRadius);
                    viewBlurProxy.getBlurConfig().getGradientStrokeCornerParam().setRadius(viewBlurManager.blurCornerRadius);
                    ViewBlurProxy.updateMultiPlatformMixConfig$default(viewBlurProxy, viewBlurManager.blurCornerRadius, 6);
                }
                viewBlurProxy.applyBlurConfig();
            }
        }
        return Unit.INSTANCE;
    }
}
