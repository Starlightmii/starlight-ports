package com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.car.app.serialization.Bundler$;
import androidx.compose.animation.ChangeSize$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.keyguard.anim.transition.OplusTransitionExtendedInfoUtil$;
import com.google.gson.GsonBuilder;
import com.oplus.systemui.keyguard.domain.quickaffordance.AffordanceSqlHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialBrightness;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMessageViewInfo;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialSpaceBean;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialUtils;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperInfo;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperLoader;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper;
import com.oplus.zoom.common.ZoomDCSManager;
import com.oplusos.keyguard.utils.KgdLogUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import okio.Path$;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPictorialCursorHelper extends PictorialCursorHelper {
    public static final Companion Companion = new Companion();
    private static final String EXCEPTION_STR = "Exception = ";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static Bundle getBundle(int i, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt("p_scene", i);
        bundle.putInt("p_ver", 1);
        bundle.putLong("p_ts", System.currentTimeMillis());
        if (TextUtils.isEmpty(bundle.getString("imageId"))) {
            bundle.putString("imageId", "");
        }
        return bundle;
    }

    public static String getRowKey(int i, boolean z) {
        String str = "one";
        if (i != 0) {
            if (i == 1) {
                str = ZoomDCSManager.VALUE_SPLIT_COUNT_TWO;
            } else if (i == 2) {
                str = ZoomDCSManager.VALUE_SPLIT_COUNT_THREE;
            } else if (i == 3) {
                str = "four";
            } else if (i == 4) {
                str = "five";
            } else if (i == 5) {
                str = "six";
            }
        }
        return z ? Bundler$.ExternalSyntheticOutline0.m("row_", str, "_image_pfd") : "row_".concat(str);
    }

    public static void handleSupportPictorialTips(JSONObject jSONObject, Context context, Context context2, Resources resources, PictorialWallpaperInfo pictorialWallpaperInfo) {
        int color;
        int color2;
        int color3;
        String strOptString = jSONObject.optString("clickText");
        Drawable pictorialDrawable = PictorialCursorHelper.getPictorialDrawable(context, resources, jSONObject.optInt("viewButtonBackground"), 2131235180);
        Drawable pictorialDrawable2 = PictorialCursorHelper.getPictorialDrawable(context, resources, jSONObject.optInt("viewButtonPressedId"), 2131235180);
        int size = PictorialCursorHelper.getSize(jSONObject, context2, "viewButtonSize", 10);
        try {
            color = Color.parseColor(jSONObject.optString("viewButtonColor"));
            if (color == -1) {
                color = Color.parseColor("#ffffffff");
            }
        } catch (Exception unused) {
            color = Color.parseColor("#ffffffff");
        }
        String strOptString2 = jSONObject.optString("title");
        int size2 = PictorialCursorHelper.getSize(jSONObject, context2, "titleSize", 12);
        try {
            color2 = Color.parseColor(jSONObject.optString("titleColor"));
        } catch (Exception unused2) {
            color2 = -1;
        }
        int size3 = PictorialCursorHelper.getSize(jSONObject, context2, "contentSize", 12);
        try {
            color3 = Color.parseColor(jSONObject.optString("contentColor"));
            if (color3 == -1) {
                color3 = Color.parseColor("#e6ffffff");
            }
        } catch (Exception unused3) {
            color3 = Color.parseColor("#e6ffffff");
        }
        int size4 = PictorialCursorHelper.getSize(jSONObject, context2, "viewButtonMarginLeft", 0);
        String strOptString3 = jSONObject.optString("content");
        String strOptString4 = jSONObject.has("copyrightDesc") ? jSONObject.optString("copyrightDesc") : null;
        String strOptString5 = jSONObject.optString("webUrl");
        String strOptString6 = jSONObject.optString("deepLink");
        String strOptString7 = jSONObject.optString("instantApp");
        String strOptString8 = jSONObject.optString("packageNameLink");
        String strOptString9 = jSONObject.optString("wallpaperTitle");
        String strOptString10 = jSONObject.optString("wallpaperWebUrl");
        String strOptString11 = jSONObject.optString("wallpaperId");
        String strOptString12 = jSONObject.optString("txtGroupId");
        String strOptString13 = jSONObject.optString("wallpaperWebType");
        String strOptString14 = jSONObject.optString("transparent");
        pictorialWallpaperInfo.setClickText(strOptString);
        pictorialWallpaperInfo.setMoreButtonBg(pictorialDrawable);
        pictorialWallpaperInfo.setMoreButtonPressedBg(pictorialDrawable2);
        pictorialWallpaperInfo.setMoreButtonSize(size);
        pictorialWallpaperInfo.setMoreButtonColor(color);
        pictorialWallpaperInfo.setMoreButtonMarginLeft(size4);
        pictorialWallpaperInfo.setTitleSize(size2);
        pictorialWallpaperInfo.setTitleColor(color2);
        pictorialWallpaperInfo.setTextTitle(strOptString2);
        pictorialWallpaperInfo.setCopyrightDesc(strOptString4);
        pictorialWallpaperInfo.setContentColor(color3);
        pictorialWallpaperInfo.setContentSize(size3);
        pictorialWallpaperInfo.setTextContent(strOptString3);
        pictorialWallpaperInfo.setWebUrl(strOptString5);
        pictorialWallpaperInfo.setDeepLink(strOptString6);
        pictorialWallpaperInfo.setInstantAppLink(strOptString7);
        pictorialWallpaperInfo.setPackageNameLink(strOptString8);
        pictorialWallpaperInfo.setWallpaperTitle(strOptString9);
        pictorialWallpaperInfo.setWallpaperWebUrl(strOptString10);
        pictorialWallpaperInfo.setWallpaperId(strOptString11);
        pictorialWallpaperInfo.setTxtGroupId(strOptString12);
        pictorialWallpaperInfo.setWallpaperWebType(strOptString13);
        pictorialWallpaperInfo.setTransparent(strOptString14);
        PictorialUtils.fitPictorialDetailsIntent(context, pictorialWallpaperInfo);
    }

    /* JADX WARN: Code duplicated, block: B:42:0x013a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:43:0x013c  */
    /* JADX WARN: Code duplicated, block: B:45:0x0143 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:48:0x0152  */
    /* JADX WARN: Code duplicated, block: B:51:0x015d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:52:0x015f  */
    /* JADX WARN: Code duplicated, block: B:53:0x0164  */
    /* JADX WARN: Code duplicated, block: B:55:0x0167 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:59:0x0170  */
    /* JADX WARN: Code duplicated, block: B:61:0x0176  */
    /* JADX WARN: Code duplicated, block: B:63:0x017c A[ADDED_TO_REGION] */
    public static List parseBundle(Bundle bundle, Context context, Context context2, boolean z, Resources resources, boolean z2) {
        ArrayList arrayList;
        boolean z3;
        PictorialWallpaperInfo pictorialWallpaperInfo;
        PictorialWallpaperInfo pictorialWallpaperInfo2;
        PictorialWallpaperInfo pictorialWallpaperInfo3;
        ParcelFileDescriptor parcelFileDescriptor;
        int i;
        PictorialWallpaperInfo pictorialWallpaperInfo4;
        Drawable wallpaperDrawable;
        Drawable drawableLoadWallpaperSync;
        ArrayList arrayList2 = new ArrayList();
        if (bundle != null && bundle.getInt("p_code", -1) == 0) {
            boolean z4 = false;
            PictorialWallpaperLoader.getInstance().setForceUpdate(bundle.getBoolean("is_forced_replacement", false));
            int i2 = 0;
            PictorialWallpaperInfo pictorialWallpaperInfo5 = null;
            PictorialWallpaperInfo pictorialWallpaperInfo6 = null;
            while (i2 < 6) {
                String string = bundle.getString(getRowKey(i2, z4));
                if (string != null) {
                    KgdLogUtil.w("PictorialCursorHelper", "index = " + i2 + "rowData =" + string);
                    try {
                        JSONObject jSONObject = new JSONObject(string);
                        KgdLogUtil.i("PictorialCursorHelper", "parseRowData: " + jSONObject);
                        String strOptString = jSONObject.optString("imageId");
                        String strOptString2 = jSONObject.optString("groupId");
                        int iOptInt = jSONObject.optInt("groupType");
                        String strOptString3 = jSONObject.optString("fileUri");
                        String strOptString4 = jSONObject.optString("filePath");
                        String strOptString5 = jSONObject.optString("fileUnzipUri");
                        int iOptInt2 = jSONObject.optInt("imageType");
                        pictorialWallpaperInfo2 = pictorialWallpaperInfo5;
                        try {
                            String strOptString6 = jSONObject.optString("brightnessValue");
                            arrayList2 = arrayList2;
                            try {
                                OplusPictorialCursorHelper$parseRowData$instanceCreator$1 oplusPictorialCursorHelper$parseRowData$instanceCreator$1 = new OplusPictorialCursorHelper$parseRowData$instanceCreator$1();
                                pictorialWallpaperInfo3 = pictorialWallpaperInfo6;
                                try {
                                    GsonBuilder gsonBuilder = new GsonBuilder();
                                    gsonBuilder.registerTypeAdapter(oplusPictorialCursorHelper$parseRowData$instanceCreator$1, PictorialBrightness.class);
                                    PictorialBrightness pictorialBrightness = (PictorialBrightness) gsonBuilder.create().fromJson(PictorialBrightness.class, strOptString6);
                                    KgdLogUtil.i("PictorialCursorHelper", "parseRowData, pictorialBrightness: " + pictorialBrightness);
                                    pictorialWallpaperInfo5 = new PictorialWallpaperInfo();
                                    try {
                                        pictorialWallpaperInfo5.setImageId(strOptString);
                                        pictorialWallpaperInfo5.setGroupType(iOptInt);
                                        pictorialWallpaperInfo5.setGroupId(strOptString2);
                                        pictorialWallpaperInfo5.setFileUri(Uri.parse(strOptString3));
                                        pictorialWallpaperInfo5.setFilePath(strOptString4);
                                        pictorialWallpaperInfo5.setFileUnzipUri(strOptString5);
                                        pictorialWallpaperInfo5.setPictorialImageType(iOptInt2);
                                        pictorialWallpaperInfo5.setBrightnessValue(pictorialBrightness);
                                        if (z) {
                                            try {
                                                handleSupportPictorialTips(jSONObject, context, context2, resources, pictorialWallpaperInfo5);
                                            } catch (JSONException e) {
                                                e = e;
                                                OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e.getMessage(), "PictorialCursorHelper");
                                            }
                                        }
                                    } catch (JSONException e2) {
                                        e = e2;
                                    }
                                } catch (JSONException e3) {
                                    e = e3;
                                    pictorialWallpaperInfo5 = null;
                                    OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e.getMessage(), "PictorialCursorHelper");
                                    parcelFileDescriptor = (ParcelFileDescriptor) bundle.getParcelable(getRowKey(i2, true));
                                    i = i2 % 2;
                                    if (i == 0) {
                                        if (pictorialWallpaperInfo5 != null) {
                                            pictorialWallpaperInfo5.setWallpaperType(PictorialWallpaperInfo.WallpaperType.COVER);
                                        }
                                        if (!z2) {
                                            PictorialWallpaperLoader.getInstance().getClass();
                                            drawableLoadWallpaperSync = PictorialWallpaperLoader.loadWallpaperSync(context, pictorialWallpaperInfo5, parcelFileDescriptor, true);
                                            if (pictorialWallpaperInfo5 != null) {
                                                pictorialWallpaperInfo5.setWallpaperDrawable(drawableLoadWallpaperSync);
                                            }
                                        }
                                        pictorialWallpaperInfo4 = pictorialWallpaperInfo3;
                                        if (PictorialUtils.isSameImage(pictorialWallpaperInfo4, pictorialWallpaperInfo5)) {
                                            if (pictorialWallpaperInfo4 != null) {
                                                wallpaperDrawable = pictorialWallpaperInfo4.getWallpaperDrawable();
                                            } else {
                                                wallpaperDrawable = null;
                                            }
                                            if (wallpaperDrawable != null) {
                                                pictorialWallpaperInfo5.copyWallpaperDrawable(pictorialWallpaperInfo4);
                                            }
                                        }
                                        arrayList = arrayList2;
                                        if (pictorialWallpaperInfo5 != null) {
                                            arrayList.add(pictorialWallpaperInfo5);
                                        }
                                        pictorialWallpaperInfo6 = null;
                                        z3 = false;
                                    } else {
                                        arrayList = arrayList2;
                                        pictorialWallpaperInfo = pictorialWallpaperInfo3;
                                        if (i == 1) {
                                        }
                                        z3 = false;
                                        KgdLogUtil.w("PictorialCursorHelper", "coverInfo is null");
                                        pictorialWallpaperInfo5 = pictorialWallpaperInfo2;
                                        pictorialWallpaperInfo6 = pictorialWallpaperInfo;
                                    }
                                    i2++;
                                    arrayList2 = arrayList;
                                    z4 = z3;
                                }
                            } catch (JSONException e4) {
                                e = e4;
                                pictorialWallpaperInfo3 = pictorialWallpaperInfo6;
                            }
                        } catch (JSONException e5) {
                            e = e5;
                            pictorialWallpaperInfo3 = pictorialWallpaperInfo6;
                            pictorialWallpaperInfo5 = null;
                            OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e.getMessage(), "PictorialCursorHelper");
                            parcelFileDescriptor = (ParcelFileDescriptor) bundle.getParcelable(getRowKey(i2, true));
                            i = i2 % 2;
                            if (i == 0) {
                                if (pictorialWallpaperInfo5 != null) {
                                    pictorialWallpaperInfo5.setWallpaperType(PictorialWallpaperInfo.WallpaperType.COVER);
                                }
                                if (!z2) {
                                    PictorialWallpaperLoader.getInstance().getClass();
                                    drawableLoadWallpaperSync = PictorialWallpaperLoader.loadWallpaperSync(context, pictorialWallpaperInfo5, parcelFileDescriptor, true);
                                    if (pictorialWallpaperInfo5 != null) {
                                        pictorialWallpaperInfo5.setWallpaperDrawable(drawableLoadWallpaperSync);
                                    }
                                }
                                pictorialWallpaperInfo4 = pictorialWallpaperInfo3;
                                if (PictorialUtils.isSameImage(pictorialWallpaperInfo4, pictorialWallpaperInfo5)) {
                                    if (pictorialWallpaperInfo4 != null) {
                                        wallpaperDrawable = pictorialWallpaperInfo4.getWallpaperDrawable();
                                    } else {
                                        wallpaperDrawable = null;
                                    }
                                    if (wallpaperDrawable != null) {
                                        pictorialWallpaperInfo5.copyWallpaperDrawable(pictorialWallpaperInfo4);
                                    }
                                }
                                arrayList = arrayList2;
                                if (pictorialWallpaperInfo5 != null) {
                                    arrayList.add(pictorialWallpaperInfo5);
                                }
                                pictorialWallpaperInfo6 = null;
                                z3 = false;
                            } else {
                                arrayList = arrayList2;
                                pictorialWallpaperInfo = pictorialWallpaperInfo3;
                                if (i == 1) {
                                }
                                z3 = false;
                                KgdLogUtil.w("PictorialCursorHelper", "coverInfo is null");
                                pictorialWallpaperInfo5 = pictorialWallpaperInfo2;
                                pictorialWallpaperInfo6 = pictorialWallpaperInfo;
                            }
                            i2++;
                            arrayList2 = arrayList;
                            z4 = z3;
                        }
                    } catch (JSONException e6) {
                        e = e6;
                        pictorialWallpaperInfo2 = pictorialWallpaperInfo5;
                    }
                    parcelFileDescriptor = (ParcelFileDescriptor) bundle.getParcelable(getRowKey(i2, true));
                    i = i2 % 2;
                    if (i == 0) {
                        if (pictorialWallpaperInfo5 != null) {
                            pictorialWallpaperInfo5.setWallpaperType(PictorialWallpaperInfo.WallpaperType.COVER);
                        }
                        if (!z2 && i2 == 0) {
                            PictorialWallpaperLoader.getInstance().getClass();
                            drawableLoadWallpaperSync = PictorialWallpaperLoader.loadWallpaperSync(context, pictorialWallpaperInfo5, parcelFileDescriptor, true);
                            if (pictorialWallpaperInfo5 != null) {
                                pictorialWallpaperInfo5.setWallpaperDrawable(drawableLoadWallpaperSync);
                            }
                        }
                        pictorialWallpaperInfo4 = pictorialWallpaperInfo3;
                        if (PictorialUtils.isSameImage(pictorialWallpaperInfo4, pictorialWallpaperInfo5)) {
                            if (pictorialWallpaperInfo4 != null) {
                                wallpaperDrawable = pictorialWallpaperInfo4.getWallpaperDrawable();
                            } else {
                                wallpaperDrawable = null;
                            }
                            if (wallpaperDrawable != null && pictorialWallpaperInfo5 != null) {
                                pictorialWallpaperInfo5.copyWallpaperDrawable(pictorialWallpaperInfo4);
                            }
                        }
                        arrayList = arrayList2;
                        if (pictorialWallpaperInfo5 != null) {
                            arrayList.add(pictorialWallpaperInfo5);
                        }
                        pictorialWallpaperInfo6 = null;
                        z3 = false;
                    } else {
                        arrayList = arrayList2;
                        pictorialWallpaperInfo = pictorialWallpaperInfo3;
                        if (i == 1 || pictorialWallpaperInfo2 == null) {
                            z3 = false;
                            KgdLogUtil.w("PictorialCursorHelper", "coverInfo is null");
                            pictorialWallpaperInfo5 = pictorialWallpaperInfo2;
                        } else {
                            if (pictorialWallpaperInfo5 != null) {
                                pictorialWallpaperInfo5.setWallpaperType(PictorialWallpaperInfo.WallpaperType.NEXT);
                            }
                            pictorialWallpaperInfo2.setNextWallpaper(pictorialWallpaperInfo5);
                            if (z2 || i2 != 1) {
                                z3 = false;
                            } else {
                                PictorialWallpaperLoader.getInstance().getClass();
                                z3 = false;
                                Drawable drawableLoadWallpaperSync2 = PictorialWallpaperLoader.loadWallpaperSync(context, pictorialWallpaperInfo5, parcelFileDescriptor, false);
                                if (pictorialWallpaperInfo5 != null) {
                                    pictorialWallpaperInfo5.setWallpaperDrawable(drawableLoadWallpaperSync2);
                                }
                            }
                            pictorialWallpaperInfo6 = pictorialWallpaperInfo5;
                            pictorialWallpaperInfo5 = null;
                        }
                    }
                    i2++;
                    arrayList2 = arrayList;
                    z4 = z3;
                } else {
                    arrayList = arrayList2;
                    z3 = z4;
                    pictorialWallpaperInfo = pictorialWallpaperInfo6;
                }
                pictorialWallpaperInfo6 = pictorialWallpaperInfo;
                i2++;
                arrayList2 = arrayList;
                z4 = z3;
            }
        }
        return arrayList2;
    }

    public static PictorialSpaceBean parsePictorialSpace(Context context, Bundle bundle) {
        if (bundle == null || bundle.getInt("p_code", -1) != 0) {
            return null;
        }
        String string = bundle.getString("fileUri");
        String string2 = bundle.getString("pictorial_extra");
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) bundle.getParcelable("selection_image_pfd");
        PictorialSpaceBean pictorialSpaceBean = new PictorialSpaceBean(string, string2, parcelFileDescriptor, (Drawable) null);
        if (TextUtils.isEmpty(string) || !"pictorial".equals(AffordanceSqlHelper.Companion.getInstance().queryStartSelection(context))) {
            return pictorialSpaceBean;
        }
        PictorialWallpaperLoader pictorialWallpaperLoader = PictorialWallpaperLoader.getInstance();
        Uri uri = Uri.parse(string);
        pictorialWallpaperLoader.getClass();
        if (context != null) {
            pictorialSpaceBean.setPreviewDrawable(uri != null ? PictorialWallpaperLoader.processLoadWallpaperRequest(context, new PictorialWallpaperLoader.WallpaperLoadRequest().setGauss(false).setSync(true).setWallPFD(parcelFileDescriptor).setUri(uri)) : null);
            return pictorialSpaceBean;
        }
        Path$.ExternalSyntheticBUOutline0.m$1("why context is null? why? why? check it quickly!");
        return null;
    }

    public final Bundle callPictorialPage(ContentResolver contentResolver, Uri uri, int i, Bundle bundle) {
        if (contentResolver != null && uri != null) {
            try {
                Bundle bundle2 = getBundle(i, bundle);
                KgdLogUtil.i("PictorialCursorHelper", "callPictorialPage params = " + bundle2);
                return contentResolver.call(uri, "notifyPictorial", (String) null, bundle2);
            } catch (RemoteException e) {
                OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e.getMessage(), "PictorialCursorHelper");
            } catch (IllegalArgumentException e2) {
                OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e2.getMessage(), "PictorialCursorHelper");
            } catch (IllegalStateException e3) {
                OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e3.getMessage(), "PictorialCursorHelper");
            } catch (Exception e4) {
                OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e4.getMessage(), "PictorialCursorHelper");
            }
        }
        return null;
    }

    public boolean fitPictorialDetailsIntent(Context context, PictorialWallpaperInfo pictorialWallpaperInfo) {
        return false;
    }

    public final boolean getKgdPictorialRecommendationSetting(Context context) {
        boolean z = false;
        if (context != null && 1 == Settings.System.getIntForUser(context.getContentResolver(), "kgd_pictorial_recommendation", 0, -2)) {
            z = true;
        }
        KgdLogUtil.i("PictorialCursorHelper", "getKgdPictorialRecommendationSetting context: " + context + " state: " + z);
        return z;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper
    public int getPictorialActivateNotShow(ContentResolver contentResolver, Uri uri) {
        if (contentResolver == null) {
            KgdLogUtil.w("PictorialCursorHelper", "getPictorialActivateNotShow contentResolver is null");
            return -2;
        }
        if (uri == null) {
            KgdLogUtil.w("PictorialCursorHelper", "getPictorialActivateNotShow uri is null");
            return -2;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("pictorial_activate", 1);
            Bundle bundleCall = contentResolver.call(uri, "notifyPictorial", (String) null, getBundle(11, bundle));
            if (bundleCall == null) {
                KgdLogUtil.w("PictorialCursorHelper", "getPictorialActivateNotShow resultBundle is null");
                return -2;
            }
            if (bundleCall.getInt("p_code", -1) == 0) {
                int i = bundleCall.getInt("pictorial_activate_not_show", -2);
                if (i == 0) {
                    return 0;
                }
                if (i == 1) {
                    return 1;
                }
            }
            return -2;
        } catch (RemoteException e) {
            OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e.getMessage(), "PictorialCursorHelper");
        } catch (IllegalArgumentException e2) {
            OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e2.getMessage(), "PictorialCursorHelper");
        } catch (IllegalStateException e3) {
            OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e3.getMessage(), "PictorialCursorHelper");
        } catch (Exception e4) {
            OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e4.getMessage(), "PictorialCursorHelper");
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper
    public List<PictorialWallpaperInfo> getPictorialImageDataList(Params params) {
        Bundle bundle;
        if (params.getContentResolver() != null) {
            if (params.isGetBlur) {
                bundle = null;
            } else {
                if (params.isPAodMode) {
                    bundle = getBundle(1, null);
                    bundle.putString("isLightFetch", params.isLightFetch ? "1" : "0");
                    bundle.putBoolean("isPAodMode", true);
                    bundle.putBoolean("isDismissKeyguardGetImage", params.isDismissKeyguardGetImage);
                    if (!TextUtils.isEmpty(params.getPicParams())) {
                        bundle.putString("p_params", params.getPicParams());
                    }
                } else {
                    bundle = getBundle(1, null);
                    bundle.putString("isLightFetch", params.isLightFetch ? "1" : "0");
                    if (!TextUtils.isEmpty(params.getPicParams())) {
                        bundle.putString("p_params", params.getPicParams());
                    }
                }
            }
            try {
                if (params.getImageUri() != null) {
                    return parseBundle(params.getContentResolver().call(params.getImageUri(), "notifyPictorial", (String) null, bundle), params.getContext(), params.getDefaultDensityContext(), params.isSupportPictorialTips, params.getPictorialResources(), params.isLightFetch);
                }
                KgdLogUtil.w("PictorialCursorHelper", "imageUri = null");
                return null;
            } catch (RemoteException e) {
                OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e.getMessage(), "PictorialCursorHelper");
            } catch (IllegalArgumentException e2) {
                OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e2.getMessage(), "PictorialCursorHelper");
            } catch (IllegalStateException e3) {
                OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e3.getMessage(), "PictorialCursorHelper");
            } catch (Exception e4) {
                OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e4.getMessage(), "PictorialCursorHelper");
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:51:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:56:? A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r8v2 */
    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper
    public PictorialMessageViewInfo getPictorialMessageViewInfo(ContentResolver contentResolver, Uri uri, Context context, Resources resources) throws Throwable {
        Throwable th;
        Exception exc;
        Cursor cursorQuery;
        IllegalStateException illegalStateException;
        IllegalArgumentException illegalArgumentException;
        RemoteException remoteException;
        ?? r8 = 0;
        try {
            try {
                cursorQuery = contentResolver.query(uri, null, null, null, null);
                try {
                    if (cursorQuery == null) {
                        KgdLogUtil.w("PictorialCursorHelper", "getPictorialMessageViewInfo failed since cursor is null,uri" + uri);
                        return null;
                    }
                    if (cursorQuery.moveToFirst()) {
                        boolean z = true;
                        if (cursorQuery.getInt(cursorQuery.getColumnIndex("entryShow")) != 1) {
                            z = false;
                        }
                        int i = cursorQuery.getInt(cursorQuery.getColumnIndex("entryImageId"));
                        Drawable pictorialDrawable = PictorialCursorHelper.getPictorialDrawable(context, resources, i, 2131235181);
                        PictorialMessageViewInfo pictorialMessageViewInfo = new PictorialMessageViewInfo();
                        pictorialMessageViewInfo.setEntryShow(z);
                        pictorialMessageViewInfo.setEntryDrawable(pictorialDrawable);
                        pictorialMessageViewInfo.setEntryDrawableRes(i);
                        KgdLogUtil.i("PictorialCursorHelper", "getPictorialMessageViewInfo successfully");
                        cursorQuery.close();
                        return pictorialMessageViewInfo;
                    }
                    cursorQuery.close();
                    return null;
                } catch (RemoteException e) {
                    remoteException = e;
                    KgdLogUtil.i("PictorialCursorHelper", EXCEPTION_STR + remoteException.getMessage());
                    if (cursorQuery != null) {
                    }
                    return null;
                } catch (IllegalArgumentException e2) {
                    illegalArgumentException = e2;
                    KgdLogUtil.i("PictorialCursorHelper", EXCEPTION_STR + illegalArgumentException.getMessage());
                    if (cursorQuery != null) {
                    }
                    return null;
                } catch (IllegalStateException e3) {
                    illegalStateException = e3;
                    KgdLogUtil.i("PictorialCursorHelper", EXCEPTION_STR + illegalStateException.getMessage());
                    if (cursorQuery != null) {
                    }
                    return null;
                } catch (Exception e4) {
                    exc = e4;
                    KgdLogUtil.i("PictorialCursorHelper", EXCEPTION_STR + exc.getMessage());
                    if (cursorQuery != null) {
                    }
                    return null;
                }
            } catch (Throwable th2) {
                th = th2;
                r8 = contentResolver;
                if (r8 != 0) {
                    throw th;
                }
                r8.close();
                throw th;
            }
        } catch (RemoteException e5) {
            remoteException = e5;
            cursorQuery = null;
        } catch (IllegalArgumentException e6) {
            illegalArgumentException = e6;
            cursorQuery = null;
        } catch (IllegalStateException e7) {
            illegalStateException = e7;
            cursorQuery = null;
        } catch (Exception e8) {
            exc = e8;
            cursorQuery = null;
        } catch (Throwable th3) {
            th = th3;
            if (r8 != 0) {
                throw th;
            }
            r8.close();
            throw th;
        }
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper
    public PictorialSpaceBean getPictorialSpaceBean(Context context, ContentResolver contentResolver, Uri uri) {
        if (contentResolver != null) {
            try {
                if (uri != null) {
                    return parsePictorialSpace(context, contentResolver.call(uri, "notifyPictorial", (String) null, getBundle(1, null)));
                }
                KgdLogUtil.w("PictorialCursorHelper", "imageUri = " + uri);
                return null;
            } catch (RemoteException e) {
                OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e.getMessage(), "PictorialCursorHelper");
            } catch (IllegalArgumentException e2) {
                OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e2.getMessage(), "PictorialCursorHelper");
            } catch (IllegalStateException e3) {
                OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e3.getMessage(), "PictorialCursorHelper");
            } catch (Exception e4) {
                OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m(EXCEPTION_STR, e4.getMessage(), "PictorialCursorHelper");
            }
        }
        return null;
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper
    public void insert(ContentResolver contentResolver, Uri uri, ContentValues contentValues) {
        contentResolver.insert(uri, contentValues);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper
    public void reportStatisticData(ContentResolver contentResolver, Uri uri, String str, Bundle bundle) {
        getBundle(7, bundle);
        contentResolver.call(uri, "notifyPictorial", (String) null, bundle);
    }

    public final void setKgdPictorialRecommendationSetting(Context context, boolean z) {
        Settings.System.putIntForUser(context.getContentResolver(), "kgd_pictorial_recommendation", z ? 1 : 0, -2);
    }

    public final void setKgdPictorialRecommendationShow(Context context, boolean z) {
        Settings.System.putIntForUser(context.getContentResolver(), "kgd_pictorial_recommendation_show", z ? 1 : 0, -2);
    }

    @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper
    public void update(ContentResolver contentResolver, Uri uri, ContentValues contentValues) {
        contentResolver.update(uri, contentValues, null, null);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Params {
        private ContentResolver contentResolver;
        private Context context;
        private Context defaultDensityContext;
        private Uri imageUri;
        public boolean isDismissKeyguardGetImage;
        public boolean isGetBlur;
        public boolean isLightFetch;
        public boolean isPAodMode;
        public boolean isSupportPictorialTips;
        private String picParams;
        private Resources pictorialResources;
        private PictorialWallpaperLoader pictorialWallpaperLoader;

        public Params(ContentResolver contentResolver, Uri uri, PictorialWallpaperLoader pictorialWallpaperLoader, Context context, Context context2, boolean z, Resources resources, boolean z2, boolean z3, boolean z4, boolean z5, String str) {
            this.contentResolver = contentResolver;
            this.imageUri = uri;
            this.pictorialWallpaperLoader = pictorialWallpaperLoader;
            this.context = context;
            this.defaultDensityContext = context2;
            this.isSupportPictorialTips = z;
            this.pictorialResources = resources;
            this.isGetBlur = z2;
            this.isLightFetch = z3;
            this.isPAodMode = z4;
            this.isDismissKeyguardGetImage = z5;
            this.picParams = str;
        }

        public final ContentResolver component1() {
            return this.contentResolver;
        }

        public final String component12() {
            return this.picParams;
        }

        public final Uri component2() {
            return this.imageUri;
        }

        public final PictorialWallpaperLoader component3() {
            return this.pictorialWallpaperLoader;
        }

        public final Context component4() {
            return this.context;
        }

        public final Context component5() {
            return this.defaultDensityContext;
        }

        public final Resources component7() {
            return this.pictorialResources;
        }

        public final Params copy(ContentResolver contentResolver, Uri uri, PictorialWallpaperLoader pictorialWallpaperLoader, Context context, Context context2, boolean z, Resources resources, boolean z2, boolean z3, boolean z4, boolean z5, String str) {
            return new Params(contentResolver, uri, pictorialWallpaperLoader, context, context2, z, resources, z2, z3, z4, z5, str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Params)) {
                return false;
            }
            Params params = (Params) obj;
            return Intrinsics.areEqual(this.contentResolver, params.contentResolver) && Intrinsics.areEqual(this.imageUri, params.imageUri) && Intrinsics.areEqual(this.pictorialWallpaperLoader, params.pictorialWallpaperLoader) && Intrinsics.areEqual(this.context, params.context) && Intrinsics.areEqual(this.defaultDensityContext, params.defaultDensityContext) && this.isSupportPictorialTips == params.isSupportPictorialTips && Intrinsics.areEqual(this.pictorialResources, params.pictorialResources) && this.isGetBlur == params.isGetBlur && this.isLightFetch == params.isLightFetch && this.isPAodMode == params.isPAodMode && this.isDismissKeyguardGetImage == params.isDismissKeyguardGetImage && Intrinsics.areEqual(this.picParams, params.picParams);
        }

        public final ContentResolver getContentResolver() {
            return this.contentResolver;
        }

        public final Context getContext() {
            return this.context;
        }

        public final Context getDefaultDensityContext() {
            return this.defaultDensityContext;
        }

        public final Uri getImageUri() {
            return this.imageUri;
        }

        public final String getPicParams() {
            return this.picParams;
        }

        public final Resources getPictorialResources() {
            return this.pictorialResources;
        }

        public final PictorialWallpaperLoader getPictorialWallpaperLoader() {
            return this.pictorialWallpaperLoader;
        }

        public final int hashCode() {
            ContentResolver contentResolver = this.contentResolver;
            int iHashCode = (contentResolver == null ? 0 : contentResolver.hashCode()) * 31;
            Uri uri = this.imageUri;
            int iHashCode2 = (iHashCode + (uri == null ? 0 : uri.hashCode())) * 31;
            PictorialWallpaperLoader pictorialWallpaperLoader = this.pictorialWallpaperLoader;
            int iHashCode3 = (iHashCode2 + (pictorialWallpaperLoader == null ? 0 : pictorialWallpaperLoader.hashCode())) * 31;
            Context context = this.context;
            int iHashCode4 = (iHashCode3 + (context == null ? 0 : context.hashCode())) * 31;
            Context context2 = this.defaultDensityContext;
            int iM = ChangeSize$.ExternalSyntheticOutline0.m((iHashCode4 + (context2 == null ? 0 : context2.hashCode())) * 31, 31, this.isSupportPictorialTips);
            Resources resources = this.pictorialResources;
            int iM2 = ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m((iM + (resources == null ? 0 : resources.hashCode())) * 31, 31, this.isGetBlur), 31, this.isLightFetch), 31, this.isPAodMode), 31, this.isDismissKeyguardGetImage);
            String str = this.picParams;
            return iM2 + (str != null ? str.hashCode() : 0);
        }

        public final void setContentResolver(ContentResolver contentResolver) {
            this.contentResolver = contentResolver;
        }

        public final void setContext(Context context) {
            this.context = context;
        }

        public final void setDefaultDensityContext(Context context) {
            this.defaultDensityContext = context;
        }

        public final void setDismissKeyguardGetImage(boolean z) {
            this.isDismissKeyguardGetImage = z;
        }

        public final void setGetBlur(boolean z) {
            this.isGetBlur = z;
        }

        public final void setImageUri(Uri uri) {
            this.imageUri = uri;
        }

        public final void setLightFetch(boolean z) {
            this.isLightFetch = z;
        }

        public final void setPAodMode(boolean z) {
            this.isPAodMode = z;
        }

        public final void setPicParams(String str) {
            this.picParams = str;
        }

        public final void setPictorialResources(Resources resources) {
            this.pictorialResources = resources;
        }

        public final void setPictorialWallpaperLoader(PictorialWallpaperLoader pictorialWallpaperLoader) {
            this.pictorialWallpaperLoader = pictorialWallpaperLoader;
        }

        public final void setSupportPictorialTips(boolean z) {
            this.isSupportPictorialTips = z;
        }

        public String toString() {
            ContentResolver contentResolver = this.contentResolver;
            Uri uri = this.imageUri;
            PictorialWallpaperLoader pictorialWallpaperLoader = this.pictorialWallpaperLoader;
            Context context = this.context;
            Context context2 = this.defaultDensityContext;
            boolean z = this.isSupportPictorialTips;
            Resources resources = this.pictorialResources;
            boolean z2 = this.isGetBlur;
            boolean z3 = this.isLightFetch;
            boolean z4 = this.isPAodMode;
            boolean z5 = this.isDismissKeyguardGetImage;
            String str = this.picParams;
            StringBuilder sb = new StringBuilder("Params(contentResolver=");
            sb.append(contentResolver);
            sb.append(", imageUri=");
            sb.append(uri);
            sb.append(", pictorialWallpaperLoader=");
            sb.append(pictorialWallpaperLoader);
            sb.append(", context=");
            sb.append(context);
            sb.append(", defaultDensityContext=");
            sb.append(context2);
            sb.append(", isSupportPictorialTips=");
            sb.append(z);
            sb.append(", pictorialResources=");
            sb.append(resources);
            sb.append(", isGetBlur=");
            sb.append(z2);
            sb.append(", isLightFetch=");
            KeyguardLogger$.ExternalSyntheticOutline0.m(", isPAodMode=", ", isDismissKeyguardGetImage=", sb, z3, z4);
            sb.append(z5);
            sb.append(", picParams=");
            sb.append(str);
            sb.append(")");
            return sb.toString();
        }

        public Params() {
            this(null, null, null, null, null, false, null, false, false, false, false, null);
        }
    }

    public final Bundle callPictorialPage(ContentResolver contentResolver, Uri uri, int i) {
        return callPictorialPage(contentResolver, uri, i, null);
    }
}
