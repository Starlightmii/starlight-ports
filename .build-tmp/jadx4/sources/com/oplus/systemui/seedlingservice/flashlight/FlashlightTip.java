package com.oplus.systemui.seedlingservice.flashlight;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.service.notification.StatusBarNotification;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.google.gson.Gson;
import com.heytap.accessory.bean.FlowControlConfig$;
import com.oplus.content.OplusFeatureConfigManager;
import com.oplus.pantanal.seedling.bean.CancelPanelActionConfigEnum;
import com.oplus.pantanal.seedling.bean.SeedlingCard;
import com.oplus.pantanal.seedling.bean.SeedlingHostEnum;
import com.oplus.pantanal.seedling.update.SeedlingCardOptions;
import com.oplus.systemui.seedlingservice.R$drawable;
import com.oplus.systemui.seedlingservice.R$string;
import com.oplus.systemui.seedlingservice.datamanager.UiData;
import com.oplus.systemui.seedlingservice.eventmanager.Event;
import com.oplus.systemui.seedlingservice.statemanager.CardState;
import com.oplus.systemui.seedlingservice.statemanager.CardStateManager;
import com.oplus.systemui.seedlingservice.statemanager.StateManager;
import com.oplus.systemui.seedlingservice.tip.NormalTip;
import com.oplus.systemui.seedlingservice.utils.LogUtil;
import com.oplus.systemui.seedlingservice.utils.SeedlingStatisticsUtil;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlashlightTip extends NormalTip implements StateManager.StateListener {
    private static final String EFFECT_SWITCH_BG_COLOR_CLOSE = "#26FFFFFF";
    private static final String EFFECT_SWITCH_BG_COLOR_OPEN = "#FF0066FF";
    private static final String FEATURE_LITE_LIVEALERT_DEBUG = "persist.debug.lite_livealert";
    private static final String FEATURE_LIVEALERT_LIGHT_EFFECT_DEBUG = "persist.debug.livealert_effect_light";
    private static final String FEATURE_SUPPORT_LITE_LIVEALERT = "oplus.software.support_lite_livealert";
    private static final long FLASHLIGHT_DISMISS_TIME = 5000;
    public static final String FLASHLIGHT_EVENT = "flashlightEvent";
    private static final String FLASHLIGHT_LIVE_ALERT_EVENT_TYPE = "flashlightEvent";
    private static final String FLASHLIGHT_LIVE_ALERT_PROXY_SERVICE = "com.oplus.systemui.seedling.livealert.LiveAlertPendingIntentService";
    private static final int FLASHLIGHT_LIVE_ALERT_SIZE_LARGE = 3;
    private static final int FLASHLIGHT_LIVE_ALERT_SIZE_SMALL = 1;
    private static final int FLASHLIGHT_OFF = 2;
    private static final int FLASHLIGHT_OFF_FORCE_MAGIC_KEY = 4;
    private static final int FLASHLIGHT_OFF_MAGIC_KEY = 3;
    private static final int FLASHLIGHT_ON = 1;
    private static final int FLASHLIGHT_ON_MAGIC_KEY = 5;
    private static final String FLASHLIGHT_PARAMS_SWITCH = "switch";
    private static final String FLASHLIGHT_PROVIDER = "com.oplus.systemui.seedlingservice.flashlight.FlashlightLiveAlertProvider";
    private static final int FLASHLIGHT_SEEDLING_CARD_DESTROY = 0;
    private static final int FLASHLIGHT_SEEDLING_CARD_EXIST = 1;
    private static final String FLASHLIGHT_SERVICE_ID = "268451919";
    private static final int FLASHLIGHT_STATE_NONE = -1;
    private static final int IMMERSIVE_CARD_TYPE_INTERACTIVE = 2;
    private static final String KEY_DESCRIPTION = "lightDes";
    private static final String KEY_DESCRIPTION_COLOR = "lightDesColor";
    private static final String KEY_EFFECT_SWITCH_BG_COLOR = "effectSwitchBgColor";
    private static final String KEY_EFFECT_SWITCH_ICON = "effectSwitchIcon";
    private static final String KEY_FLASHLIGHT_LIVE_ALERT_EVENT_TYPE = "EventType";
    private static final String KEY_FLASHLIGHT_OPTION_FROM = "flashlightOptionFrom";
    private static final String KEY_FLASHLIGHT_SEEDLING_CARD_STATE = "flashlightSeedlingCardState";
    private static final String KEY_FLASHLIGHT_SERVICE_FROM = "service_from";
    private static final String KEY_FLASHLIGHT_STATE = "flashlightState";
    private static final String KEY_FLASHLIGHT_TITLE = "flashlightTitle";
    private static final String KEY_IMM_BTN_IMAGE_PATH = "immSwitchBtnImg";
    private static final String KEY_IS_OPEN = "isOpen";
    private static final String KEY_LARGE_IMAGE_PATH = "lgLightSwitchImg";
    private static final String KEY_LIVE_ALERT_CAPSULE = "oplus.livealert.capsule";
    private static final String KEY_LIVE_ALERT_CARD = "oplus.livealert.card";
    private static final String KEY_LIVE_ALERT_OPTIONS = "oplusLiveAlertOptions";
    private static final String KEY_LIVE_ALERT_PENDING_INTENT = "flashlight_pending_intent";
    private static final String KEY_NAME = "name";
    private static final String KEY_OPTION_IMMERSE_CARD_TYPE = "immersiveCardType";
    private static final String KEY_OPTION_RECEIVER_URI = "receiverUri";
    private static final String KEY_SHOW_EFFECT = "showEffect";
    private static final String KEY_SHOW_NORMAL = "showNormal";
    private static final String KEY_SMALL_IMAGE_PATH = "smLightSwitchImg";
    private static final String KEY_SWITCH_DESC = "switchDes";
    private static final String LIVE_ALERT_PENDING_INTENT_PREFIX = "pi";
    private static final String METHOD_CANCEL = "cancelLiveAlert";
    private static final String METHOD_SEND = "sendLiveAlert";
    private static final String PATH_CLOSE_IMAGE_PATH = "assets/images/close.png";
    private static final String PATH_DRAWABLE_FLASHLIGHT_CLOSE = "drawable/flashlight_close";
    private static final String PATH_DRAWABLE_FLASHLIGHT_OPEN = "drawable/flashlight_open";
    private static final String PATH_DRAWABLE_IMM_FLASHLIGHT_CLOSE = "drawable/imm_flashlight_close";
    private static final String PATH_DRAWABLE_IMM_FLASHLIGHT_OPEN = "drawable/imm_flashlight_open";
    private static final String PATH_EFFECT_LIGHT_CLOSE_IMAGE_PATH = "assets/images/effect_light_close.png";
    private static final String PATH_EFFECT_LIGHT_IMM_ICON_CLOSE = "drawable/effect_light_flashlight_close";
    private static final String PATH_EFFECT_LIGHT_IMM_ICON_OPEN = "drawable/effect_light_flashlight_open";
    private static final String PATH_EFFECT_LIGHT_IMM_SWITCH_CLOSE = "drawable/effect_light_imm_flashlight_close";
    private static final String PATH_EFFECT_LIGHT_IMM_SWITCH_OPEN = "drawable/effect_light_imm_flashlight_open";
    private static final String PATH_EFFECT_LIGHT_LARGE_CARD_CLOSE_IMAGE_PATH = "assets/images/effect_light_large_card_close.json";
    private static final String PATH_EFFECT_LIGHT_LARGE_CARD_ICON_CLOSE = "drawable/effect_light_flashlight_large_card_close";
    private static final String PATH_EFFECT_LIGHT_LARGE_CARD_ICON_OPEN = "drawable/effect_light_flashlight_large_card_open";
    private static final String PATH_EFFECT_LIGHT_LARGE_CARD_OPEN_IMAGE_PATH = "assets/images/effect_light_large_card_open.json";
    private static final String PATH_EFFECT_LIGHT_OPEN_IMAGE_PATH = "assets/images/effect_light_open.png";
    private static final String PATH_EFFECT_LIGHT_SWITCH_CLOSE = "drawable/effect_light_flashlight_switch_close";
    private static final String PATH_EFFECT_LIGHT_SWITCH_OPEN = "drawable/effect_light_flashlight_switch_open";
    private static final String PATH_EFFECT_SWITCH_ICON_CLOSE = "assets/images/effect_light_switch_icon_close.svg";
    private static final String PATH_EFFECT_SWITCH_ICON_OPEN = "assets/images/effect_light_switch_icon_open.svg";
    private static final String PATH_IMM_CLOSE_IMAGE_PATH = "assets/images/close_imm.svg";
    private static final String PATH_IMM_OPEN_IMAGE_PATH = "assets/images/open_imm.svg";
    private static final String PATH_LARGE_CARD_CLOSE_IMAGE_PATH = "assets/images/large_card_close.png";
    private static final String PATH_LARGE_CARD_OPEN_IMAGE_PATH = "assets/images/large_card_open.png";
    private static final String PATH_OPEN_IMAGE_PATH = "assets/images/open.png";
    private static final String PROVIDER = "com.oplus.card.server.systemui.provider";
    private static final String SEEDLING_ACTION_SYSTEMUI_FLASHLIGHT = "pantanal.intent.business.app.system.FLASH_LIGHT";
    private static final String TAG = "FlashlightTip";
    private static final int VALUE_FLASHLIGHT_OPTION_FROM_MAGIC_KEY = 1;
    private static final int VALUE_FLASHLIGHT_OPTION_FROM_NOTIFICATION = 0;
    private static final int VALUE_FLASHLIGHT_SERVICE_FROM = 1;
    private static boolean flashlightFromMagicKey;
    private static boolean flashlightOn;
    private static final Lazy gson$delegate;
    private static boolean isClickCard;
    private static int isFlashlightCardExist;
    private static String lastTitle;
    private static final Lazy supportLiteLiveAlert$delegate;
    private static final Lazy supportLiveAlertEffectLight$delegate;
    private static boolean titleChanged;
    public static final FlashlightTip INSTANCE = new FlashlightTip();
    private static int lastFlashlightState = -1;
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final Runnable flashlightCancelIntent = new Runnable() { // from class: com.oplus.systemui.seedlingservice.flashlight.FlashlightTip$flashlightCancelIntent$1
        @Override // java.lang.Runnable
        public final void run() {
            FlashlightTip flashlightTip = FlashlightTip.INSTANCE;
            Context mContext = flashlightTip.getMContext();
            if (mContext != null) {
                NormalTip.cancelIntent$default(flashlightTip, mContext, null, 2, null);
            }
        }
    };
    private static final Runnable flashlightCancelLiveAlert = new Runnable() { // from class: com.oplus.systemui.seedlingservice.flashlight.FlashlightTip$flashlightCancelLiveAlert$1
        @Override // java.lang.Runnable
        public final void run() {
            FlashlightTip flashlightTip = FlashlightTip.INSTANCE;
            if (flashlightTip.getMContext() != null) {
                FlashlightTip.sendLiveAlert$default(flashlightTip, "cancelLiveAlert", false, 2, null);
            }
        }
    };

    static {
        final int i = 0;
        supportLiteLiveAlert$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.seedlingservice.flashlight.FlashlightTip$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean zSupportLiteLiveAlert_delegate$lambda$0;
                switch (i) {
                    case 0:
                        zSupportLiteLiveAlert_delegate$lambda$0 = FlashlightTip.supportLiteLiveAlert_delegate$lambda$0();
                        break;
                    case 1:
                        zSupportLiteLiveAlert_delegate$lambda$0 = FlashlightTip.supportLiveAlertEffectLight_delegate$lambda$1();
                        break;
                    default:
                        return FlashlightTip.gson_delegate$lambda$2();
                }
                return Boolean.valueOf(zSupportLiteLiveAlert_delegate$lambda$0);
            }
        });
        final int i2 = 1;
        supportLiveAlertEffectLight$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.seedlingservice.flashlight.FlashlightTip$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean zSupportLiteLiveAlert_delegate$lambda$0;
                switch (i2) {
                    case 0:
                        zSupportLiteLiveAlert_delegate$lambda$0 = FlashlightTip.supportLiteLiveAlert_delegate$lambda$0();
                        break;
                    case 1:
                        zSupportLiteLiveAlert_delegate$lambda$0 = FlashlightTip.supportLiveAlertEffectLight_delegate$lambda$1();
                        break;
                    default:
                        return FlashlightTip.gson_delegate$lambda$2();
                }
                return Boolean.valueOf(zSupportLiteLiveAlert_delegate$lambda$0);
            }
        });
        final int i3 = 2;
        gson$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplus.systemui.seedlingservice.flashlight.FlashlightTip$$ExternalSyntheticLambda0
            public final Object invoke() {
                boolean zSupportLiteLiveAlert_delegate$lambda$0;
                switch (i3) {
                    case 0:
                        zSupportLiteLiveAlert_delegate$lambda$0 = FlashlightTip.supportLiteLiveAlert_delegate$lambda$0();
                        break;
                    case 1:
                        zSupportLiteLiveAlert_delegate$lambda$0 = FlashlightTip.supportLiveAlertEffectLight_delegate$lambda$1();
                        break;
                    default:
                        return FlashlightTip.gson_delegate$lambda$2();
                }
                return Boolean.valueOf(zSupportLiteLiveAlert_delegate$lambda$0);
            }
        });
    }

    private FlashlightTip() {
    }

    private final void checkLastFlashlightState() {
        if (lastFlashlightState == -1) {
            lastFlashlightState = flashlightOn ? 1 : 2;
        }
    }

    private final String createLiveAlertCapsuleData(Context context, int i) {
        String str;
        String string;
        if (i == 1) {
            str = getSupportLiveAlertEffectLight() ? PATH_EFFECT_LIGHT_IMM_ICON_OPEN : PATH_DRAWABLE_FLASHLIGHT_OPEN;
            string = context.getString(R$string.notification_flashlight_hasopen1);
        } else {
            str = getSupportLiveAlertEffectLight() ? PATH_EFFECT_LIGHT_IMM_ICON_CLOSE : PATH_DRAWABLE_FLASHLIGHT_CLOSE;
            string = context.getString(R$string.notification_flashlight_hasclose1);
        }
        String str2 = string;
        return getGson().toJson(new FlashlightLiveAlertCapsule(new CapsuleData((String) null, new Icon(new IconData(str, FlashlightLiveAlertCard.SIZE_SMALL, FlashlightLiveAlertCard.SHAPE_CIRCLE)), new CapsuleTitle(str2, str2, 0, 4, (DefaultConstructorMarker) null), 1, (DefaultConstructorMarker) null)));
    }

    private final String createLiveAlertCardData(Context context, int i) {
        String str;
        String str2;
        String str3;
        String string;
        String string2;
        String str4;
        String str5;
        String str6;
        String str7;
        if (i == 1) {
            if (getSupportLiveAlertEffectLight()) {
                str = PATH_EFFECT_LIGHT_LARGE_CARD_ICON_OPEN;
                str3 = PATH_EFFECT_LIGHT_IMM_ICON_OPEN;
                str2 = PATH_EFFECT_LIGHT_IMM_SWITCH_OPEN;
            } else {
                str = PATH_DRAWABLE_FLASHLIGHT_OPEN;
                str2 = PATH_DRAWABLE_IMM_FLASHLIGHT_OPEN;
                str3 = PATH_DRAWABLE_FLASHLIGHT_OPEN;
            }
            string = context.getString(R$string.notification_flashlight_hasopen1);
            string2 = context.getString(R$string.notification_flashlight_close);
            str4 = FlashlightLiveAlertCard.STATE_ON;
            str5 = PATH_EFFECT_LIGHT_SWITCH_OPEN;
        } else {
            if (getSupportLiveAlertEffectLight()) {
                str = PATH_EFFECT_LIGHT_LARGE_CARD_ICON_CLOSE;
                str3 = PATH_EFFECT_LIGHT_IMM_ICON_CLOSE;
                str2 = PATH_EFFECT_LIGHT_IMM_SWITCH_CLOSE;
            } else {
                str = PATH_DRAWABLE_FLASHLIGHT_CLOSE;
                str2 = PATH_DRAWABLE_IMM_FLASHLIGHT_CLOSE;
                str3 = PATH_DRAWABLE_FLASHLIGHT_CLOSE;
            }
            string = context.getString(R$string.notification_flashlight_hasclose1);
            string2 = context.getString(R$string.notification_flashlight_open);
            str4 = FlashlightLiveAlertCard.STATE_OFF;
            str5 = PATH_EFFECT_LIGHT_SWITCH_CLOSE;
        }
        String str8 = str2;
        String str9 = string;
        String str10 = string2;
        String str11 = str4;
        if (getSupportLiveAlertEffectLight()) {
            str7 = FlashlightLiveAlertCard.SIZE_REGULAR;
            str6 = FlashlightLiveAlertCard.SHAPE_RECTANGLE;
        } else {
            str6 = FlashlightLiveAlertCard.SHAPE_CIRCLE;
            str7 = FlashlightLiveAlertCard.SIZE_SMALL;
        }
        String str12 = getSupportLiveAlertEffectLight() ? FlashlightLiveAlertCard.COLOR_E6FFFFFF : FlashlightLiveAlertCard.COLOR_FFE7D38C;
        Icon icon = new Icon(new IconData(str, str7, str6));
        LabelText labelText = new LabelText(str9, null, null, null, str9, str12, 20, 14, null);
        String string3 = context.getString(R$string.notification_flashlight);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new LabelText(string3, null, null, null, string3, FlashlightLiveAlertCard.COLOR_8AFFFFFF, 14, 14, null));
        arrayList.add(arrayList2);
        StandardLayout standardLayout = new StandardLayout(null, icon, labelText, arrayList, 1, null);
        ActionsLayout actionsLayout = new ActionsLayout(null, getSupportLiveAlertEffectLight() ? CollectionsKt__CollectionsKt.arrayListOf(new LabelButton[]{new LabelButton(str10, FlashlightLiveAlertCard.ICON_LABEL, null, "", str11, "pi/flashlight_pending_intent", str5, FlashlightLiveAlertCard.COLOR_00000000, 12, 4, null)}) : CollectionsKt__CollectionsKt.arrayListOf(new LabelButton[]{new LabelButton(str10, null, null, str10, str11, "pi/flashlight_pending_intent", FlashlightLiveAlertCard.COLOR_26FFFFFF, FlashlightLiveAlertCard.COLOR_FFFFFFFF, 14, 6, null)}), 1, null);
        Icon icon2 = new Icon(new IconData(str3, FlashlightLiveAlertCard.SIZE_SMALL, FlashlightLiveAlertCard.SHAPE_CIRCLE));
        LabelText labelText2 = new LabelText(str9, null, null, null, str9, FlashlightLiveAlertCard.COLOR_FFFFFFFF, 12, 14, null);
        LabelText labelText3 = new LabelText(string3, null, null, null, r10, FlashlightLiveAlertCard.COLOR_FFFFFFFF, 12, 14, null);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new LabelButton(str10, FlashlightLiveAlertCard.ICON_LABEL, null, "", str11, "pi/flashlight_pending_intent", str8, FlashlightLiveAlertCard.COLOR_00000000, 12, 4, null));
        return getGson().toJson(new FlashlightLiveAlertCard(standardLayout, actionsLayout, new SymActionLayout(null, icon2, labelText2, labelText3, arrayList3, 1, null)));
    }

    private final String createLiveAlertOptions(Context context) {
        String packageName = context.getPackageName();
        packageName.getClass();
        ArrayList arrayListArrayListOf = CollectionsKt__CollectionsKt.arrayListOf(new Integer[]{8, 16});
        ArrayList arrayListArrayListOf2 = CollectionsKt__CollectionsKt.arrayListOf(new Integer[]{16});
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("immersiveCardType", 2);
        linkedHashMap.put(KEY_OPTION_RECEIVER_URI, FLASHLIGHT_PROVIDER);
        return getGson().toJson(new FlashlightLiveAlertOptions(true, packageName, 0, arrayListArrayListOf, arrayListArrayListOf2, null, 2, 0, 0, linkedHashMap, 420, null));
    }

    private final void delayCancelFlashlight() {
        Handler handler = mainHandler;
        Runnable runnable = flashlightCancelIntent;
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, FLASHLIGHT_DISMISS_TIME);
    }

    private final void delayCancelLiveAlert() {
        Handler handler = mainHandler;
        Runnable runnable = flashlightCancelLiveAlert;
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, FLASHLIGHT_DISMISS_TIME);
    }

    private final void forceCancelFlashlight() {
        Handler handler = mainHandler;
        Runnable runnable = flashlightCancelIntent;
        if (handler.hasCallbacks(runnable)) {
            handler.removeCallbacks(runnable);
        }
        Context mContext = getMContext();
        if (mContext != null) {
            NormalTip.cancelIntent$default(INSTANCE, mContext, null, 2, null);
        }
    }

    private final void forceCancelLiveAlert() {
        Handler handler = mainHandler;
        Runnable runnable = flashlightCancelLiveAlert;
        if (handler.hasCallbacks(runnable)) {
            handler.removeCallbacks(runnable);
        }
        sendLiveAlert$default(this, METHOD_CANCEL, false, 2, null);
    }

    private final Gson getGson() {
        return (Gson) gson$delegate.getValue();
    }

    private final boolean getSupportLiveAlertEffectLight() {
        return ((Boolean) supportLiveAlertEffectLight$delegate.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Gson gson_delegate$lambda$2() {
        return new Gson();
    }

    private final void handleDestroy() {
        if (isFlashlightCardExist == 0) {
            return;
        }
        isFlashlightCardExist = 0;
        flashlightFromMagicKey = false;
        lastFlashlightState = -1;
        titleChanged = false;
        lastTitle = null;
        isClickCard = false;
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_FLASHLIGHT_SEEDLING_CARD_STATE, isFlashlightCardExist);
        sendMessage(bundle);
    }

    private final void handleOptionFromMagicKey(Integer num) throws JSONException {
        LogUtil.d(TAG, "handleOptionFromMagicKey. flashlightState: " + num + ", lastFlashlightState: " + lastFlashlightState);
        flashlightFromMagicKey = true;
        if (num != null && num.intValue() == 4) {
            forceCancelFlashlight();
            return;
        }
        if (num != null && num.intValue() == 5) {
            updateCardData(1);
        } else if (num != null && num.intValue() == 3) {
            updateCardData(2);
            delayCancelFlashlight();
        }
    }

    private final void handleOptionFromNormal(Integer num) throws JSONException {
        LogUtil.d(TAG, "handleOptionFromNormal. flashlightState: " + num + ", isClickCard: " + isClickCard + ", lastFlashlightState: " + lastFlashlightState);
        int i = lastFlashlightState;
        if (num != null && num.intValue() == i && !titleChanged) {
            LogUtil.d(TAG, "onEvent flashlight state no change. flashlightState: " + num);
            return;
        }
        flashlightFromMagicKey = false;
        if (num != null && num.intValue() == 1) {
            updateCardData(1);
            return;
        }
        if (num != null && num.intValue() == 2) {
            updateCardData(2);
            if (!isClickCard) {
                forceCancelFlashlight();
            } else {
                isClickCard = false;
                delayCancelFlashlight();
            }
        }
    }

    private final boolean isLiveAlertShowingFlashlightOff() {
        return isFlashlightCardExist == 1 && !flashlightOn;
    }

    private final void onEventForLiveAlert(Event event) {
        LogUtil.i(TAG, "onEventForLiveAlert " + event);
        Bundle info = event.getInfo();
        if (info == null) {
            return;
        }
        int i = info.getInt("flashlightState");
        int i2 = info.getInt("flashlightOptionFrom", 0);
        LogUtil.i(TAG, "onEventForLiveAlert, flashlightState: " + i + ", flashlightFrom: " + i2);
        if (i2 == 0) {
            if (i == 1) {
                Handler handler = mainHandler;
                Runnable runnable = flashlightCancelLiveAlert;
                if (handler.hasCallbacks(runnable)) {
                    handler.removeCallbacks(runnable);
                }
                sendLiveAlert(METHOD_SEND, true);
                return;
            }
            if (i != 2) {
                return;
            }
            if (!isClickCard) {
                forceCancelLiveAlert();
                return;
            }
            isClickCard = false;
            sendLiveAlert(METHOD_SEND, false);
            delayCancelLiveAlert();
            return;
        }
        if (i2 != 1) {
            return;
        }
        if (i == 3) {
            sendLiveAlert(METHOD_SEND, false);
            delayCancelLiveAlert();
        } else {
            if (i == 4) {
                forceCancelLiveAlert();
                return;
            }
            if (i != 5) {
                return;
            }
            Handler handler2 = mainHandler;
            Runnable runnable2 = flashlightCancelLiveAlert;
            if (handler2.hasCallbacks(runnable2)) {
                handler2.removeCallbacks(runnable2);
            }
            sendLiveAlert(METHOD_SEND, true);
        }
    }

    private final void onEventForSeeding(Event event) throws JSONException {
        String string;
        LogUtil.i(TAG, "onEventForSeeding " + event);
        Bundle info = event.getInfo();
        Integer numValueOf = info != null ? Integer.valueOf(info.getInt("flashlightState")) : null;
        Integer numValueOf2 = info != null ? Integer.valueOf(info.getInt("flashlightOptionFrom", 0)) : null;
        if (info != null && (string = info.getString("flashlightTitle")) != null) {
            titleChanged = !string.equals(lastTitle);
            lastTitle = string;
        }
        LogUtil.i(TAG, "flashlightState=" + numValueOf + ", flashlightfrom=" + numValueOf2 + " titleChanged:" + titleChanged);
        if (numValueOf2 != null && numValueOf2.intValue() == 0) {
            handleOptionFromNormal(numValueOf);
        } else if (numValueOf2 != null && numValueOf2.intValue() == 1) {
            handleOptionFromMagicKey(numValueOf);
        }
    }

    private final void sendLiveAlert(String str, boolean z) {
        StatusBarNotification statusBarNotification;
        Object obj;
        LogUtil.i(TAG, "sendLiveAlert: method = " + str + ", switch = " + z);
        Context mContext = getMContext();
        if (mContext != null) {
            String packageName = mContext.getPackageName();
            if (Intrinsics.areEqual(str, METHOD_SEND)) {
                FlashlightTip flashlightTip = INSTANCE;
                String strCreateLiveAlertOptions = flashlightTip.createLiveAlertOptions(mContext);
                String strCreateLiveAlertCardData = flashlightTip.createLiveAlertCardData(mContext, z ? 1 : 2);
                String strCreateLiveAlertCapsuleData = flashlightTip.createLiveAlertCapsuleData(mContext, z ? 1 : 2);
                Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("EventType", "flashlightEvent");
                bundleM.putString(FLASHLIGHT_PARAMS_SWITCH, String.valueOf(z));
                Intent intent = new Intent();
                intent.setAction(FLASHLIGHT_LIVE_ALERT_PROXY_SERVICE);
                intent.setPackage(packageName);
                intent.putExtras(bundleM);
                PendingIntent service = PendingIntent.getService(mContext, bundleM.hashCode(), intent, 201326592);
                int i = Integer.parseInt(FLASHLIGHT_SERVICE_ID);
                Notification.Builder builder = new Notification.Builder(mContext, FLASHLIGHT_SERVICE_ID);
                builder.setSmallIcon(R$drawable.app_icon_dark);
                builder.setAutoCancel(false);
                Bundle bundle = new Bundle();
                bundle.putString(KEY_LIVE_ALERT_OPTIONS, strCreateLiveAlertOptions);
                bundle.putString(KEY_LIVE_ALERT_CARD, strCreateLiveAlertCardData);
                bundle.putString(KEY_LIVE_ALERT_CAPSULE, strCreateLiveAlertCapsuleData);
                bundle.putParcelable(KEY_LIVE_ALERT_PENDING_INTENT, service);
                builder.addExtras(bundle);
                statusBarNotification = new StatusBarNotification(packageName, packageName, i, null, 0, 0, 0, builder.build(), UserHandle.of(mContext.getUserId()), 0L);
            } else {
                int i2 = Integer.parseInt(FLASHLIGHT_SERVICE_ID);
                Notification.Builder builder2 = new Notification.Builder(mContext, FLASHLIGHT_SERVICE_ID);
                builder2.setSmallIcon(R$drawable.app_icon_dark);
                builder2.setAutoCancel(false);
                statusBarNotification = new StatusBarNotification(packageName, packageName, i2, null, 0, 0, 0, builder2.build(), UserHandle.of(mContext.getUserId()), 0L);
            }
            try {
                ContentResolver contentResolver = mContext.getContentResolver();
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("content", statusBarNotification);
                obj = Result.constructor-impl(contentResolver.call(PROVIDER, str, (String) null, bundle2));
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                LogUtil.i(TAG, "provider call " + str + " failed: " + th2);
            }
        }
    }

    public static /* synthetic */ void sendLiveAlert$default(FlashlightTip flashlightTip, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        flashlightTip.sendLiveAlert(str, z);
    }

    private final void sendMessage(Bundle bundle) {
        Message messageObtain = Message.obtain();
        Messenger messengerFromEventCode = CardStateManager.Companion.getInstance().getMessengerFromEventCode("flashlightEvent");
        bundle.putString("flashlightEvent", "flashlightEvent");
        messageObtain.what = 2;
        messageObtain.setData(bundle);
        try {
            LogUtil.i(TAG, "sendMessage: messengerInner=" + messengerFromEventCode);
            if (messengerFromEventCode != null) {
                messengerFromEventCode.send(messageObtain);
            }
        } catch (RemoteException e) {
            LogUtil.e(TAG, "sendMessage failed. " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean supportLiteLiveAlert_delegate$lambda$0() {
        return OplusFeatureConfigManager.getInstance().hasFeature(FEATURE_SUPPORT_LITE_LIVEALERT) || SystemProperties.getBoolean(FEATURE_LITE_LIVEALERT_DEBUG, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean supportLiveAlertEffectLight_delegate$lambda$1() {
        return !INSTANCE.getSupportLiteLiveAlert();
    }

    private final void updateCardData(int i) throws JSONException {
        boolean z;
        Bundle bundle = new Bundle();
        Context mContext = getMContext();
        bundle.putString("name", mContext != null ? mContext.getString(R$string.notification_flashlight) : null);
        String str = lastTitle;
        if (str != null && titleChanged) {
            LogUtil.d(TAG, "title changed: " + str);
            bundle.putString("name", str);
            titleChanged = false;
        }
        if (i == 1) {
            Handler handler = mainHandler;
            Runnable runnable = flashlightCancelIntent;
            if (handler.hasCallbacks(runnable)) {
                handler.removeCallbacks(runnable);
            }
            lastFlashlightState = i;
            Context mContext2 = getMContext();
            bundle.putString(KEY_DESCRIPTION, mContext2 != null ? mContext2.getString(R$string.notification_flashlight_hasopen1) : null);
            Context mContext3 = getMContext();
            bundle.putString(KEY_SWITCH_DESC, mContext3 != null ? mContext3.getString(R$string.notification_flashlight_close) : null);
            if (getSupportLiveAlertEffectLight()) {
                bundle.putString(KEY_LARGE_IMAGE_PATH, PATH_EFFECT_LIGHT_LARGE_CARD_OPEN_IMAGE_PATH);
                bundle.putString(KEY_SMALL_IMAGE_PATH, PATH_EFFECT_LIGHT_OPEN_IMAGE_PATH);
                bundle.putString(KEY_EFFECT_SWITCH_ICON, PATH_EFFECT_SWITCH_ICON_CLOSE);
                bundle.putString(KEY_EFFECT_SWITCH_BG_COLOR, EFFECT_SWITCH_BG_COLOR_OPEN);
            } else {
                bundle.putString(KEY_LARGE_IMAGE_PATH, PATH_LARGE_CARD_OPEN_IMAGE_PATH);
                bundle.putString(KEY_SMALL_IMAGE_PATH, PATH_OPEN_IMAGE_PATH);
                bundle.putString(KEY_EFFECT_SWITCH_ICON, "");
                bundle.putString(KEY_EFFECT_SWITCH_BG_COLOR, "");
            }
            bundle.putString(KEY_IMM_BTN_IMAGE_PATH, PATH_IMM_OPEN_IMAGE_PATH);
            z = true;
            bundle.putBoolean("isOpen", true);
        } else if (i != 2) {
            lastFlashlightState = -1;
            z = true;
        } else {
            lastFlashlightState = i;
            Context mContext4 = getMContext();
            bundle.putString(KEY_DESCRIPTION, mContext4 != null ? mContext4.getString(R$string.notification_flashlight_hasclose1) : null);
            Context mContext5 = getMContext();
            bundle.putString(KEY_SWITCH_DESC, mContext5 != null ? mContext5.getString(R$string.notification_flashlight_open) : null);
            if (getSupportLiveAlertEffectLight()) {
                bundle.putString(KEY_LARGE_IMAGE_PATH, PATH_EFFECT_LIGHT_LARGE_CARD_CLOSE_IMAGE_PATH);
                bundle.putString(KEY_SMALL_IMAGE_PATH, PATH_EFFECT_LIGHT_CLOSE_IMAGE_PATH);
                bundle.putString(KEY_EFFECT_SWITCH_ICON, PATH_EFFECT_SWITCH_ICON_CLOSE);
                bundle.putString(KEY_EFFECT_SWITCH_BG_COLOR, EFFECT_SWITCH_BG_COLOR_CLOSE);
            } else {
                bundle.putString(KEY_LARGE_IMAGE_PATH, PATH_LARGE_CARD_CLOSE_IMAGE_PATH);
                bundle.putString(KEY_SMALL_IMAGE_PATH, PATH_CLOSE_IMAGE_PATH);
                bundle.putString(KEY_EFFECT_SWITCH_ICON, "");
                bundle.putString(KEY_EFFECT_SWITCH_BG_COLOR, "");
            }
            bundle.putString(KEY_IMM_BTN_IMAGE_PATH, PATH_IMM_CLOSE_IMAGE_PATH);
            bundle.putBoolean("isOpen", false);
            z = true;
        }
        if (i == z || i == 2) {
            if (getSupportLiveAlertEffectLight()) {
                bundle.putString(KEY_DESCRIPTION_COLOR, "#E6FFFFFF");
                bundle.putBoolean(KEY_SHOW_EFFECT, z);
                bundle.putBoolean(KEY_SHOW_NORMAL, false);
            } else {
                bundle.putString(KEY_DESCRIPTION_COLOR, "#FFE7D38C");
                bundle.putBoolean(KEY_SHOW_NORMAL, z);
                bundle.putBoolean(KEY_SHOW_EFFECT, false);
            }
        }
        UiData uiData = new UiData();
        uiData.put(KEY_DESCRIPTION, bundle.getString(KEY_DESCRIPTION));
        uiData.put("name", bundle.getString("name"));
        uiData.put(KEY_SWITCH_DESC, bundle.getString(KEY_SWITCH_DESC));
        uiData.put(KEY_LARGE_IMAGE_PATH, bundle.getString(KEY_LARGE_IMAGE_PATH));
        uiData.put(KEY_SMALL_IMAGE_PATH, bundle.getString(KEY_SMALL_IMAGE_PATH));
        uiData.put(KEY_IMM_BTN_IMAGE_PATH, bundle.getString(KEY_IMM_BTN_IMAGE_PATH));
        uiData.put("isOpen", Boolean.valueOf(bundle.getBoolean("isOpen")));
        uiData.put(KEY_DESCRIPTION_COLOR, bundle.getString(KEY_DESCRIPTION_COLOR));
        uiData.put(KEY_SHOW_EFFECT, Boolean.valueOf(bundle.getBoolean(KEY_SHOW_EFFECT)));
        uiData.put(KEY_SHOW_NORMAL, Boolean.valueOf(bundle.getBoolean(KEY_SHOW_NORMAL)));
        uiData.put(KEY_EFFECT_SWITCH_ICON, bundle.getString(KEY_EFFECT_SWITCH_ICON));
        uiData.put(KEY_EFFECT_SWITCH_BG_COLOR, bundle.getString(KEY_EFFECT_SWITCH_BG_COLOR));
        createOrUpdateCard(uiData, bundle);
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public boolean checkCardInvalid(CardState cardState) {
        return false;
    }

    public final void dump(PrintWriter printWriter) {
        printWriter.println(TAG);
        printWriter.println("\tcacheUiData: " + getCacheUiData());
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\twaitingCreateCard: ", getWaitingCreateCard());
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("\tisFlashlightCardExist: ", isFlashlightCardExist, printWriter);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("\tlastFlashlightState: ", lastFlashlightState, printWriter);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\tflashlightOn: ", flashlightOn);
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public String getEventCode() {
        return "flashlightEvent";
    }

    public final boolean getFlashlightOn() {
        return flashlightOn;
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public String getSeedlingAction() {
        return SEEDLING_ACTION_SYSTEMUI_FLASHLIGHT;
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public SeedlingCardOptions getSeedlingCardOptions() {
        StateManager stateManager = StateManager.INSTANCE;
        boolean zIsKeyguardOccluded = stateManager.isKeyguardOccluded();
        boolean zIsKeyguardShowing = stateManager.isKeyguardShowing();
        boolean z = zIsKeyguardOccluded || lastFlashlightState == 1 || !zIsKeyguardShowing;
        LogUtil.i(TAG, "getSeedlingCardOptions: statusBarShow=" + z + ", flashlightFromMagicKey=" + flashlightFromMagicKey);
        SeedlingHostEnum seedlingHostEnum = SeedlingHostEnum.StatusBar;
        SeedlingCardOptions seedlingCardOptions = new SeedlingCardOptions((String) null, (String) null, true, (Boolean) null, false, 4, (List) null, MapsKt__MapsJVMKt.mapOf(TuplesKt.to(seedlingHostEnum, Boolean.valueOf(zIsKeyguardOccluded || lastFlashlightState == 1 || !zIsKeyguardShowing))), MapsKt__MapsJVMKt.mapOf(TuplesKt.to(seedlingHostEnum, Boolean.TRUE)), (CancelPanelActionConfigEnum) null, (Map) null, (Integer) null, 0, false, (Long) null, (Map) null, 61019, (DefaultConstructorMarker) null);
        if (flashlightFromMagicKey) {
            HashMap map = new HashMap();
            map.put(KEY_FLASHLIGHT_SERVICE_FROM, 1);
            seedlingCardOptions.setExtensibleActionMap(map);
        }
        return seedlingCardOptions;
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public String getServiceId() {
        return FLASHLIGHT_SERVICE_ID;
    }

    public final boolean getSupportLiteLiveAlert() {
        return ((Boolean) supportLiteLiveAlert$delegate.getValue()).booleanValue();
    }

    public final void handleClick(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        String string = bundle != null ? bundle.getString(FLASHLIGHT_PARAMS_SWITCH) : null;
        if (string != null) {
            int iHashCode = string.hashCode();
            if (iHashCode != 3569038) {
                if (iHashCode == 97196323 && string.equals("false")) {
                    bundle2.putInt("flashlightState", 1);
                    sendMessage(bundle2);
                    SeedlingStatisticsUtil.trackSeedlingFlashlight(getMContext(), "0");
                    return;
                }
            } else if (string.equals("true")) {
                isClickCard = true;
                bundle2.putInt("flashlightState", 2);
                sendMessage(bundle2);
                SeedlingStatisticsUtil.trackSeedlingFlashlight(getMContext(), "1");
                return;
            }
        }
        LogUtil.w(TAG, "illegal click param. " + bundle);
    }

    public final void liveAlertCardCreate() {
        LogUtil.i(TAG, "liveAlertCardCreate");
        isFlashlightCardExist = 1;
    }

    public final void liveAlertCardDestroy() {
        LogUtil.i(TAG, "liveAlertCardDestroy");
        isFlashlightCardExist = 0;
        isClickCard = false;
    }

    @Override // com.oplus.systemui.seedlingservice.statemanager.StateManager.StateListener
    public void onCancelIntent() {
        LogUtil.i(TAG, "onCancelIntent");
        if (getSupportLiteLiveAlert()) {
            forceCancelLiveAlert();
        } else {
            forceCancelFlashlight();
        }
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public void onCardCreate(SeedlingCard seedlingCard) {
        isFlashlightCardExist = 1;
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_FLASHLIGHT_SEEDLING_CARD_STATE, isFlashlightCardExist);
        sendMessage(bundle);
        LogUtil.i(TAG, "onCardCreate, isFlashlightCardExist=" + isFlashlightCardExist);
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public void onDestroy(SeedlingCard seedlingCard) {
        handleDestroy();
        LogUtil.i(TAG, "onDestroy, isFlashlightCardExist=" + isFlashlightCardExist);
    }

    @Override // com.oplus.systemui.seedlingservice.eventmanager.EventListener
    public void onEvent(Event event) throws JSONException {
        if (getSupportLiteLiveAlert()) {
            onEventForLiveAlert(event);
        } else {
            onEventForSeeding(event);
        }
    }

    public final void onLocaleChanged() throws JSONException {
        int i = isFlashlightCardExist;
        boolean z = flashlightOn;
        int i2 = lastFlashlightState;
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("onLocaleChanged: isFlashlightCardExist = ", i, ", flashlightOn = ", ", lastFlashlightState = ", z);
        sbM.append(i2);
        LogUtil.i(TAG, sbM.toString());
        if (isFlashlightCardExist != 1) {
            return;
        }
        if (getSupportLiteLiveAlert()) {
            sendLiveAlert(METHOD_SEND, flashlightOn);
        } else {
            checkLastFlashlightState();
            updateCardData(lastFlashlightState);
        }
    }

    @Override // com.oplus.systemui.seedlingservice.statemanager.StateManager.StateListener
    public void onLocked() {
        boolean zIsKeyguardShowing = StateManager.INSTANCE.isKeyguardShowing();
        int i = isFlashlightCardExist;
        boolean z = flashlightOn;
        int i2 = lastFlashlightState;
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("onLocked. locked: ", i, ", isFlashlightCardExit: ", ", flashlightOn: ", zIsKeyguardShowing);
        sbM.append(z);
        sbM.append(", lastFlashlightState: ");
        sbM.append(i2);
        LogUtil.d(TAG, sbM.toString());
        if (getSupportLiteLiveAlert()) {
            if (zIsKeyguardShowing || !isLiveAlertShowingFlashlightOff()) {
                return;
            }
            forceCancelLiveAlert();
            return;
        }
        if (isFlashlightCardExist == 1) {
            checkLastFlashlightState();
            if (zIsKeyguardShowing || lastFlashlightState != 2) {
                return;
            }
            forceCancelFlashlight();
        }
    }

    public final void onSizeChanged(int i, int i2) {
        if (getSupportLiteLiveAlert()) {
            if (i == 3 && i2 == 1 && isLiveAlertShowingFlashlightOff()) {
                delayCancelLiveAlert();
            }
            if (i == 1 && i2 == 3) {
                Handler handler = mainHandler;
                Runnable runnable = flashlightCancelLiveAlert;
                if (handler.hasCallbacks(runnable)) {
                    handler.removeCallbacks(runnable);
                    return;
                }
                return;
            }
            return;
        }
        checkLastFlashlightState();
        if (i == 9 && i2 == 7 && lastFlashlightState == 2) {
            delayCancelFlashlight();
        }
        if (i == 7 && i2 == 9) {
            Handler handler2 = mainHandler;
            Runnable runnable2 = flashlightCancelIntent;
            if (handler2.hasCallbacks(runnable2)) {
                handler2.removeCallbacks(runnable2);
            }
        }
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public void prepareCreateData(UiData uiData, Bundle bundle) {
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public void prepareUpdateData(UiData uiData, Bundle bundle) {
    }

    public final void setFlashlightOn(boolean z) {
        flashlightOn = z;
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip, com.oplus.systemui.seedlingservice.tip.CoreModule
    public void start(Context context) {
        super.start(context);
        StateManager.INSTANCE.addListener("flashlightEvent", this);
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip, com.oplus.systemui.seedlingservice.tip.CoreModule
    public void stop(Context context) {
        LogUtil.i(TAG, "stop");
        super.stop(context);
        StateManager.INSTANCE.removeListener("flashlightEvent");
        handleDestroy();
    }
}
