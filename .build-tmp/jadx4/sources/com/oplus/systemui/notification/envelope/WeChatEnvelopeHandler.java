package com.oplus.systemui.notification.envelope;

import _COROUTINE.CoroutineDebuggingKt$;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import androidx.car.app.serialization.Bundler$;
import androidx.remotecallback.CallbackHandlerRegistry$;
import com.oplusos.systemui.common.settingsvalue.AbsSettingsValueProxy;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.OplusMultiAppUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class WeChatEnvelopeHandler extends EnvelopeHandler {
    public static String extractContent(String str) {
        String envelopeInfo;
        int iIndexOf;
        return (str == null || str.isEmpty() || (iIndexOf = str.indexOf((envelopeInfo = WeChatEnvelopeFieldList.getInstance().getEnvelopeInfo("envelope_content_tag")))) == -1) ? str : str.substring(envelopeInfo.length() + iIndexOf);
    }

    public static String extractUserName(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        String envelopeInfo = WeChatEnvelopeFieldList.getInstance().getEnvelopeInfo("envelope_user_name_tag_first");
        String envelopeInfo2 = WeChatEnvelopeFieldList.getInstance().getEnvelopeInfo("envelope_user_name_tag_last");
        int iIndexOf = str.indexOf(envelopeInfo);
        int iIndexOf2 = str.indexOf(envelopeInfo2);
        if (iIndexOf == -1 && iIndexOf2 == -1) {
            return str;
        }
        if (iIndexOf > iIndexOf2) {
            iIndexOf = -1;
        }
        if (iIndexOf != -1) {
            return str.substring(envelopeInfo.length() + iIndexOf, iIndexOf2);
        }
        return iIndexOf2 != -1 ? str.substring(0, iIndexOf2) : str;
    }

    public static boolean isWechatEncrypted(String str) {
        return (str == null || str.isEmpty() || str.indexOf(WeChatEnvelopeFieldList.getInstance().getEnvelopeInfo("envelope_content_tag")) != -1) ? false : true;
    }

    @Override // com.oplus.systemui.notification.envelope.EnvelopeHandler
    public final void sendEnvelopeNotification(Context context, int i, int i2, Notification notification, int i3, String str, boolean z) {
        Uri uri;
        String string;
        Bundle bundle = new Bundle();
        Resources resources = context.getResources();
        String packageName = context.getPackageName();
        bundle.putString("android.substName", context.getString(2131955128));
        bundle.putString("notification_source", "source_from_wechat");
        bundle.putString(WeChatEnvelopeFieldList.getInstance().getEnvelopeInfo("envelope_user_field"), str);
        bundle.putInt("envelope_related_uid", i3);
        CharSequence charSequence = notification.extras.getCharSequence("android.title");
        String string2 = notification.extras.getCharSequence("android.text").toString();
        if (((EnvelopeHelper) this.mEnvelopeHelper.get()).getSoundStatus() == 1) {
            uri = Uri.parse("android.resource://" + context.getPackageName() + "/2131886162");
        } else {
            uri = null;
        }
        Uri uri2 = Uri.parse("android.resource://" + context.getPackageName() + "/2131886161");
        if (isWechatEncrypted(string2)) {
            string = context.getString(2131957253);
            charSequence = null;
        } else {
            String envelopeInfo = WeChatEnvelopeFieldList.getInstance().getEnvelopeInfo("envelope_user_name_tag_last");
            String envelopeInfo2 = WeChatEnvelopeFieldList.getInstance().getEnvelopeInfo("envelope_content_tag");
            if (z) {
                String strExtractUserName = extractUserName(string2);
                String strExtractContent = extractContent(string2);
                if (i2 == 1) {
                    string2 = CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(strExtractUserName, envelopeInfo, strExtractContent);
                } else {
                    string2 = CallbackHandlerRegistry$.ExternalSyntheticOutline0.m("[" + String.valueOf(i2) + "]", strExtractUserName, envelopeInfo, strExtractContent);
                }
            } else {
                int iIndexOf = string2.indexOf(envelopeInfo);
                if (iIndexOf != 0 && iIndexOf != -1) {
                    String strExtractContent2 = extractContent(string2);
                    if (i2 != 1) {
                        envelopeInfo2 = "[" + String.valueOf(i2) + "]" + envelopeInfo2;
                    }
                    string2 = Bundler$.ExternalSyntheticOutline0.m(envelopeInfo2, strExtractContent2);
                }
            }
            string = string2;
        }
        Notification.Builder sound = new Notification.Builder(context, "ENVELOPE").setSmallIcon(resources.getIdentifier("envelope_notification_icon", "drawable", packageName)).setContentTitle(charSequence).setContentText(string).setContentIntent(notification.contentIntent).setAutoCancel(true).setExtras(bundle).setWhen(notification.when).setShowWhen(true).setGroup(String.valueOf(i)).setVisibility(0).setTimeoutAfter(86400000L).setSound(uri);
        NotificationManager notificationManagerFrom = NotificationManager.from(context);
        UserHandle userHandleForUid = UserHandle.getUserHandleForUid(i3);
        boolean z2 = userHandleForUid.getIdentifier() != 0;
        if (OplusMultiAppUtils.isOplusMultiAppUser(Integer.valueOf(userHandleForUid.getIdentifier()))) {
            userHandleForUid = UserHandle.of(0);
        }
        if (AbsSettingsValueProxy.getSecureIntValue(context, "envelope_notice_sound_enable", 1) == 0) {
            if (z2) {
                sound.setSound(uri2);
            } else {
                sound.setSound(null);
            }
        }
        notificationManagerFrom.notifyAsUser(WeChatEnvelopeHandler.class.getName(), i, sound.build(), userHandleForUid);
        LogUtil.d(LogUtil.TAG_NOTIFICATION, "Envelope", "Envelope--sendEnvelopeNotification: title:" + ((Object) charSequence) + ",content:" + string);
    }
}
