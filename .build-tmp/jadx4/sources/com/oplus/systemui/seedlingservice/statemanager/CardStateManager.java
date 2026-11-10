package com.oplus.systemui.seedlingservice.statemanager;

import android.os.Bundle;
import android.os.Messenger;
import com.oplus.pantanal.seedling.bean.SeedlingCard;
import com.oplus.systemui.seedlingservice.datamanager.SeedlingDataManager;
import com.oplus.systemui.seedlingservice.eventmanager.Event;
import com.oplus.systemui.seedlingservice.eventmanager.EventParse;
import com.oplus.systemui.seedlingservice.flashlight.FlashlightTip;
import com.oplus.systemui.seedlingservice.mediaControl.MediaControlTip;
import com.oplus.systemui.seedlingservice.tip.NormalTip;
import com.oplus.systemui.seedlingservice.utils.LogUtil;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CardStateManager {
    private static final String FLASHLIGHT_METHOD_SWITCH_CLICK = "switchClick";
    private static final String TAG = "CardStateManager";
    private final ConcurrentHashMap<SeedlingCard, CardState> cardMap = new ConcurrentHashMap<>();
    private final HashMap<String, Messenger> messageMap = new HashMap<>();
    public static final Companion Companion = new Companion(null);
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new CardStateManager$$ExternalSyntheticLambda0());

    /* JADX INFO: Access modifiers changed from: private */
    public static final CardStateManager instance_delegate$lambda$0() {
        return new CardStateManager();
    }

    public static /* synthetic */ void onCardLifeStateChanged$default(CardStateManager cardStateManager, SeedlingCard seedlingCard, CardLifeCycleState cardLifeCycleState, Bundle bundle, int i, Object obj) {
        if ((i & 4) != 0) {
            bundle = null;
        }
        cardStateManager.onCardLifeStateChanged(seedlingCard, cardLifeCycleState, bundle);
    }

    public final void clearCard() {
        LogUtil.i(TAG, "clearCard");
        this.cardMap.clear();
        this.messageMap.clear();
    }

    public final void dump(PrintWriter printWriter) {
        printWriter.println(TAG);
        printWriter.println("\tcardMap: " + this.cardMap);
    }

    public final ConcurrentHashMap<SeedlingCard, CardState> getCardMap() {
        return this.cardMap;
    }

    public final Messenger getMessengerFromEventCode(String str) {
        return this.messageMap.get(str);
    }

    public final void initClientMessenger(String str, Messenger messenger) {
        if (str == null) {
            LogUtil.i(TAG, "set messenger error. eventCode = null");
            return;
        }
        Messenger messenger2 = this.messageMap.get(str);
        if (messenger2 != null) {
            if (messenger2.hashCode() == (messenger != null ? messenger.hashCode() : 0)) {
                return;
            }
        }
        LogUtil.i(TAG, "set messenger. eventCode=" + str + ", messenger=" + messenger);
        this.messageMap.put(str, messenger);
    }

    public final void onCardLifeStateChanged(SeedlingCard seedlingCard, CardLifeCycleState cardLifeCycleState, Bundle bundle) {
        LogUtil.i(TAG, "onCardLifeStateChanged: card=" + seedlingCard + " lifeState=" + cardLifeCycleState);
        if (cardLifeCycleState == CardLifeCycleState.DESTROY) {
            NormalTip tipFromServiceId = SeedlingDataManager.INSTANCE.getTipFromServiceId(seedlingCard.getServiceId());
            this.cardMap.remove(seedlingCard);
            if (tipFromServiceId != null) {
                tipFromServiceId.onDestroy(seedlingCard);
                return;
            }
            return;
        }
        if (cardLifeCycleState == CardLifeCycleState.HIDE && this.cardMap.get(seedlingCard) == null) {
            return;
        }
        CardState cardState = this.cardMap.get(seedlingCard);
        if (cardLifeCycleState == CardLifeCycleState.CREATED) {
            NormalTip tipFromServiceId2 = SeedlingDataManager.INSTANCE.getTipFromServiceId(seedlingCard.getServiceId());
            if (tipFromServiceId2 != null) {
                tipFromServiceId2.onCardCreate(seedlingCard);
            }
            this.cardMap.put(seedlingCard, new CardState(cardLifeCycleState, System.currentTimeMillis()));
        }
        CardLifeCycleState lifeCycleState = cardState != null ? cardState.getLifeCycleState() : null;
        CardLifeCycleState cardLifeCycleState2 = CardLifeCycleState.UPDATED;
        if (lifeCycleState == cardLifeCycleState2 || cardLifeCycleState != cardLifeCycleState2 || bundle == null) {
            return;
        }
        LogUtil.i(TAG, "onCardLifeStateChanged: updateData=" + seedlingCard + " data=" + bundle);
        EventParse eventParse = EventParse.INSTANCE;
        Event bundle2 = eventParse.parseBundle(bundle);
        LogUtil.i(TAG, "onCardLifeStateChanged: event=" + (bundle2 != null ? bundle2.getEvent() : null));
        SeedlingDataManager seedlingDataManager = SeedlingDataManager.INSTANCE;
        Event bundle3 = eventParse.parseBundle(bundle);
        String event = bundle3 != null ? bundle3.getEvent() : null;
        if (event == null) {
            event = "";
        }
        NormalTip tip = seedlingDataManager.getTip(event);
        LogUtil.i(TAG, "onCardLifeStateChanged: tip=" + tip);
        if (tip != null) {
            tip.cardUpdateData(seedlingCard, bundle);
        }
    }

    public final void onClick(String str, Bundle bundle) {
        if (Intrinsics.areEqual(str, FLASHLIGHT_METHOD_SWITCH_CLICK)) {
            FlashlightTip.INSTANCE.handleClick(bundle);
        } else {
            MediaControlTip.INSTANCE.handleClick(str, bundle);
        }
    }

    public final void onSizeChanged(SeedlingCard seedlingCard, int i, int i2) {
        String serviceId = seedlingCard.getServiceId();
        FlashlightTip flashlightTip = FlashlightTip.INSTANCE;
        if (Intrinsics.areEqual(serviceId, flashlightTip.getServiceId())) {
            flashlightTip.onSizeChanged(i, i2);
            return;
        }
        String serviceId2 = seedlingCard.getServiceId();
        MediaControlTip mediaControlTip = MediaControlTip.INSTANCE;
        if (Intrinsics.areEqual(serviceId2, mediaControlTip.getServiceId())) {
            mediaControlTip.onSizeChanged(seedlingCard, i2);
        }
    }

    public final void removeCard(SeedlingCard seedlingCard) {
        LogUtil.i(TAG, "removeCard " + seedlingCard);
        this.cardMap.remove(seedlingCard);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CardStateManager getInstance() {
            return (CardStateManager) CardStateManager.instance$delegate.getValue();
        }

        private Companion() {
        }
    }
}
