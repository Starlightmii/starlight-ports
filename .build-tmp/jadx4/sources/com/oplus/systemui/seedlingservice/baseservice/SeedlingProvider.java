package com.oplus.systemui.seedlingservice.baseservice;

import android.content.Context;
import android.os.Bundle;
import androidx.exifinterface.media.ExifInterface$;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.pantanal.seedling.SeedlingCardWidgetProvider;
import com.oplus.pantanal.seedling.bean.SeedlingCard;
import com.oplus.pantanal.seedling.bean.SeedlingHostEnum;
import com.oplus.pantanal.seedling.util.SeedlingTool;
import com.oplus.systemui.seedlingservice.statemanager.CardLifeCycleState;
import com.oplus.systemui.seedlingservice.statemanager.CardStateManager;
import com.oplus.systemui.seedlingservice.utils.LogUtil;
import com.oplus.systemui.seedlingservice.utils.ThreadUtil;
import java.util.HashSet;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingProvider extends SeedlingCardWidgetProvider {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "SeedlingProvider";
    private final Lazy isFluidEntrySupported$delegate = LazyKt__LazyJVMKt.lazy(new SeedlingProvider$$ExternalSyntheticLambda0());
    private final CardStateManager cardStateManager = CardStateManager.Companion.getInstance();

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isFluidEntrySupported_delegate$lambda$0() {
        return OplusFeatureConfigManager.getInstance().hasFeature("oplus.software.support_fluid_entry");
    }

    public Bundle call(final String str, String str2, final Bundle bundle) {
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("call: method=", str, " arg=", str2, " extras=");
        sbM.append(bundle);
        LogUtil.i(TAG, sbM.toString());
        ThreadUtil.INSTANCE.getWorkHandler().post(new Runnable() { // from class: com.oplus.systemui.seedlingservice.baseservice.SeedlingProvider.call.1
            @Override // java.lang.Runnable
            public final void run() {
                SeedlingProvider.this.cardStateManager.onClick(str, bundle);
            }
        });
        return super.call(str, str2, bundle);
    }

    /* JADX INFO: renamed from: getSuperChannelHost, reason: merged with bridge method [inline-methods] */
    public HashSet<SeedlingHostEnum> m360getSuperChannelHost() {
        HashSet<SeedlingHostEnum> hashSet = new HashSet<>();
        hashSet.add(SeedlingHostEnum.AOD);
        hashSet.add(SeedlingHostEnum.StatusBar);
        hashSet.add(SeedlingHostEnum.Notification);
        return hashSet;
    }

    public final boolean isFluidEntrySupported() {
        return ((Boolean) this.isFluidEntrySupported$delegate.getValue()).booleanValue();
    }

    public void onCardCreate(Context context, final SeedlingCard seedlingCard) {
        LogUtil.i(TAG, "onCardCreate: card=" + seedlingCard);
        ThreadUtil.INSTANCE.getWorkHandler().post(new Runnable() { // from class: com.oplus.systemui.seedlingservice.baseservice.SeedlingProvider.onCardCreate.1
            @Override // java.lang.Runnable
            public final void run() {
                CardStateManager.onCardLifeStateChanged$default(SeedlingProvider.this.cardStateManager, seedlingCard, CardLifeCycleState.CREATED, null, 4, null);
            }
        });
    }

    public void onCardObserve(Context context, String str, List<SeedlingCard> list) {
        LogUtil.i(TAG, "onCardObserve=" + list);
    }

    public boolean onCreate() {
        LogUtil.i(TAG, "onCreate ");
        SeedlingTool.setInitSdkOnCreate(isFluidEntrySupported());
        return super.onCreate();
    }

    public void onDestroy(Context context, final SeedlingCard seedlingCard) {
        LogUtil.i(TAG, "onDestroy=" + seedlingCard);
        ThreadUtil.INSTANCE.getWorkHandler().post(new Runnable() { // from class: com.oplus.systemui.seedlingservice.baseservice.SeedlingProvider.onDestroy.1
            @Override // java.lang.Runnable
            public final void run() {
                CardStateManager.onCardLifeStateChanged$default(SeedlingProvider.this.cardStateManager, seedlingCard, CardLifeCycleState.DESTROY, null, 4, null);
            }
        });
    }

    public void onHide(Context context, final SeedlingCard seedlingCard) {
        LogUtil.i(TAG, "onHide: card=" + seedlingCard);
        ThreadUtil.INSTANCE.getWorkHandler().post(new Runnable() { // from class: com.oplus.systemui.seedlingservice.baseservice.SeedlingProvider.onHide.1
            @Override // java.lang.Runnable
            public final void run() {
                CardStateManager.onCardLifeStateChanged$default(SeedlingProvider.this.cardStateManager, seedlingCard, CardLifeCycleState.HIDE, null, 4, null);
            }
        });
    }

    public void onShow(Context context, final SeedlingCard seedlingCard) {
        LogUtil.i(TAG, "onShow=" + seedlingCard);
        ThreadUtil.INSTANCE.getWorkHandler().post(new Runnable() { // from class: com.oplus.systemui.seedlingservice.baseservice.SeedlingProvider.onShow.1
            @Override // java.lang.Runnable
            public final void run() {
                CardStateManager.onCardLifeStateChanged$default(SeedlingProvider.this.cardStateManager, seedlingCard, CardLifeCycleState.SHOW, null, 4, null);
            }
        });
    }

    public void onSizeChanged(Context context, final SeedlingCard seedlingCard, final int i, final int i2) {
        super/*com.oplus.pantanal.seedling.lifecycle.ISeedlingCardLifecycle*/.onSizeChanged(context, seedlingCard, i, i2);
        ThreadUtil.INSTANCE.getWorkHandler().post(new Runnable() { // from class: com.oplus.systemui.seedlingservice.baseservice.SeedlingProvider.onSizeChanged.1
            @Override // java.lang.Runnable
            public final void run() {
                SeedlingProvider.this.cardStateManager.onSizeChanged(seedlingCard, i, i2);
            }
        });
        LogUtil.i(TAG, "onSizeChanged: card.host=" + seedlingCard.getHost() + " oldSize=" + i + " newSize=" + i2);
    }

    public void onSubscribed(Context context, SeedlingCard seedlingCard) {
        LogUtil.i(TAG, "onSubscribed: card=" + seedlingCard);
    }

    public void onUnSubscribed(Context context, SeedlingCard seedlingCard) {
        LogUtil.i(TAG, "onUnSubscribed: card=" + seedlingCard);
    }

    public void onUpdateData(Context context, final SeedlingCard seedlingCard, final Bundle bundle) {
        LogUtil.i(TAG, "onUpdateData: card=" + seedlingCard + "， data=" + bundle);
        ThreadUtil.INSTANCE.getWorkHandler().post(new Runnable() { // from class: com.oplus.systemui.seedlingservice.baseservice.SeedlingProvider.onUpdateData.1
            @Override // java.lang.Runnable
            public final void run() {
                SeedlingProvider.this.cardStateManager.onCardLifeStateChanged(seedlingCard, CardLifeCycleState.UPDATED, bundle);
            }
        });
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
