package com.oplus.systemui.keyguard.wallpaper.pictorial.app;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import androidx.activity.result.ActivityResultRegistry$;
import androidx.car.app.serialization.Bundler$;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class PictorialAppData {
    public static final Uri URI_WALLPAPER_ENTRANCE_SHOW = Uri.parse("content://com.heytap.pictorial.wallpaper.provider");
    public static final List GLANCE_INVALID_REGION_MARK = new ArrayList(Arrays.asList("LATAM", "BR", "MX"));

    public String getActionSeePictorialDetails() {
        return getPictorialAppPackage() + ".intent.action.SEE_PICTORIAL_DETAILS";
    }

    public Uri getBaseUri() {
        return Uri.parse("content://" + getPictorialAppPackage() + ".data.provider");
    }

    public Uri getCallPictorialUri() {
        return Uri.parse("content://" + getPictorialAppPackage() + ".data.provider/keyguard_image_list");
    }

    public Uri getChangeWallPaperAddRotaPopupStatusUri() {
        return Uri.parse("content://" + getPictorialAppPackage() + ".data.provider/keyguard_set_wallpaper_popup_state_uri");
    }

    public String getDecoupLingAction() {
        return getPictorialAppPackage() + ".action.START_DECOUPLING_SERVICE";
    }

    public abstract PictorialAppInfo getDefPictorialAppInfo();

    public Uri getKeyguardEncryptedWallpaperUri() {
        return Uri.parse("content://" + getPictorialAppPackage() + ".data.provider/keyguard_encrypted_wallpaper_uri");
    }

    public Uri getKeyguardNotifyFinishUri() {
        return Uri.parse("content://" + getPictorialAppPackage() + ".data.provider/keyguard_notify_finish");
    }

    public Uri getKeyguardPopbackAnimationUri() {
        return Uri.parse("content://" + getPictorialAppPackage() + ".data.provider/keyguard_popback_animation_show");
    }

    public Uri getMessageViewShowUrl() {
        return Uri.parse("content://" + getPictorialAppPackage() + ".data.provider/keyguard_message_view_show");
    }

    public abstract PictorialAppInfo getOplusGLPictorialAppInfo();

    public String getPictorialActivateAction() {
        return getPictorialAppPackage() + ".action.START_PICTURE_ACTIVATE";
    }

    public String getPictorialActivateClassName() {
        return getPictorialAppPackage() + ".ui.slide.PictorialActivateActivity";
    }

    public abstract String getPictorialAppPackage();

    public String getPictorialGuideAction() {
        return "android.intent.action.PICTORIAL_GUIDE";
    }

    public String getPictorialGuideClassName() {
        return getPictorialAppPackage() + ".ui.PictorialGuideActivity";
    }

    public Uri getPictorialImageBlurUri() {
        return Uri.parse("content://" + getPictorialAppPackage() + ".data.provider/keyguard_unlock_image");
    }

    public Uri getPictorialImageUri() {
        return Uri.parse("content://" + getPictorialAppPackage() + ".data.provider/keyguard_image_list");
    }

    public Uri getPictorialNudgeUri() {
        return Uri.parse("content://" + getPictorialAppPackage() + ".data.provider/keyguard_app_nudge_analytics_uri");
    }

    public Uri getPictorialRecallTipsAfterClose() {
        return getPictorialAppPackage().equals("com.heytap.pictorial") ? Uri.parse("content://com.heytap.pictorial.data.provider/keyguard_message_pictorial_activate") : getUsePictorialViewShowUrl();
    }

    public String getPictorialSettingsActivity() {
        return getPictorialAppPackage() + ".ui.SettingActivity";
    }

    public String getPictorialSlideAction() {
        return getPictorialAppPackage() + ".action.START_PICTURE_MODE";
    }

    public String getPictorialSlideClassName() {
        return getPictorialAppPackage() + ".ui.slide.PictorialSlideActivity";
    }

    public Uri getPictorialSysFlagUri() {
        return Uri.parse("content://" + getPictorialAppPackage() + ".data.provider/keyguard_app_system_flags_uri");
    }

    public String getSettingsTitle(Context context, String str) {
        if (context == null) {
            return null;
        }
        String string = context.getString(2131954128);
        if ("com.heytap.pictorial".equals(str)) {
            return string;
        }
        if ("com.taboola.scoop".equals(str)) {
            return context.getString(2131956778);
        }
        try {
            Context contextCreatePackageContext = context.createPackageContext(str, 0);
            return contextCreatePackageContext.getResources().getString(contextCreatePackageContext.getResources().getIdentifier("pictorial_name", "string", str));
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
            String string2 = context.getString(2131954410);
            StringBuilder sbM = ActivityResultRegistry$.ExternalSyntheticOutline0.m("getSettingsTitle : picPkgName = ", str, "cause error:");
            sbM.append(e.getMessage());
            KgdLogUtil.i("PictorialAppData", sbM.toString());
            return string2;
        } catch (Exception e2) {
            String string3 = context.getString(2131954410);
            StringBuilder sbM2 = ActivityResultRegistry$.ExternalSyntheticOutline0.m("getSettingsTitle : picPkgName = ", str, "cause error:");
            sbM2.append(e2.getMessage());
            KgdLogUtil.i("PictorialAppData", sbM2.toString());
            return string3;
        }
    }

    public Uri getSignOfUserWallpaperUri() {
        return Uri.parse("content://com.android.systemui/sign_of_wallpaper");
    }

    public String getStatisticMethod() {
        return "postPictorialBuriedPoint";
    }

    public Uri getStatisticUri() {
        return Uri.parse("content://" + getPictorialAppPackage() + ".data.provider/keyguard_buried_point");
    }

    public Uri getSupportTaglineUri() {
        return Uri.parse("content://" + getPictorialAppPackage() + ".data.provider/keyguard_get_pictorial_cp_support_tagline_status");
    }

    public Uri getUsePictorialViewShowUrl() {
        return Uri.parse("content://" + getPictorialAppPackage() + ".data.provider/keyguard_use_pictorial");
    }

    public Uri getWallPaperAddRotaPopupStatusUri() {
        return Uri.parse("content://" + getPictorialAppPackage() + ".data.provider/keyguard_get_wallpaper_popup_state_uri");
    }

    public String getPictorialSettingsActivity(String str) {
        return Bundler$.ExternalSyntheticOutline0.m(str, ".ui.SettingActivity");
    }

    public String getSettingsTitle(Context context) {
        return getSettingsTitle(context, getPictorialAppPackage());
    }
}
