package com.oplus.systemui.plugins.tileservice.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.util.Log;
import com.oplus.systemui.plugins.tileservice.plugin.TileStateController;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusTileXMLParser {
    public static final Companion Companion = new Companion();
    private static final String META_DATA_ACTION = "oplus.quicksettings.qs_tile";
    private static final String QS_TILE_ACTION = "android.service.quicksettings.action.QS_TILE";
    private static final String TAG = "OplusTileXMLParser";
    private static final String TAG_CONTENT_PROVIDER = "ContentProvider";
    private static final String TAG_CONTENT_PROVIDER_ACTIVE_VALUE = "activeValue";
    private static final String TAG_CONTENT_PROVIDER_AUTHORITIES = "contentProviderAuthorities";
    private static final String TAG_CONTENT_PROVIDER_INACTIVE_VALUE = "inactiveValue";
    private static final String TAG_CONTENT_PROVIDER_UNAVAILABLE_VALUE = "unavailableValue";
    private static final String TAG_CONTENT_PROVIDER_VALUE_COLUMN_NAME = "contentProviderValueColumnName";
    private static final String TAG_STATE_WHEN_LOCK = "stateWhenLock";
    private static final String TAG_TILE_SERVICE = "TileService";
    private static final String TAG_VALUE_AVAILABLE = "1";
    private static volatile OplusTileXMLParser instance;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusTileXMLParser getInstance() {
            OplusTileXMLParser oplusTileXMLParser;
            OplusTileXMLParser oplusTileXMLParser2 = OplusTileXMLParser.instance;
            if (oplusTileXMLParser2 != null) {
                return oplusTileXMLParser2;
            }
            synchronized (this) {
                oplusTileXMLParser = OplusTileXMLParser.instance;
                if (oplusTileXMLParser == null) {
                    oplusTileXMLParser = new OplusTileXMLParser();
                    OplusTileXMLParser.instance = oplusTileXMLParser;
                }
            }
            return oplusTileXMLParser;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final TileStateController.OplusCustomTileServiceInfo parse(ComponentName componentName, Context context) {
        Object obj;
        XmlResourceParser xmlResourceParserLoadXmlMetaData;
        String attributeName;
        Intent intent = new Intent(QS_TILE_ACTION);
        intent.setComponent(componentName);
        synchronized (this) {
            PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 128);
            try {
                if (listQueryIntentServices.size() > 0) {
                    ServiceInfo serviceInfo = packageManager.getServiceInfo(listQueryIntentServices.get(0).getComponentInfo().getComponentName(), 128);
                    xmlResourceParserLoadXmlMetaData = serviceInfo != null ? serviceInfo.loadXmlMetaData(packageManager, META_DATA_ACTION) : null;
                } else {
                    obj = Result.constructor-impl(Unit.INSTANCE);
                    Throwable th = Result.exceptionOrNull-impl(obj);
                    if (th != null) {
                        Log.e(TAG, "load xml data error", th);
                    }
                    xmlResourceParserLoadXmlMetaData = null;
                }
            } catch (Throwable th2) {
                obj = Result.constructor-impl(ResultKt.createFailure(th2));
            }
        }
        if (xmlResourceParserLoadXmlMetaData == null) {
            return null;
        }
        TileStateController.OplusCustomTileServiceInfo oplusCustomTileServiceInfo = null;
        for (int eventType = xmlResourceParserLoadXmlMetaData.getEventType(); eventType != 1; eventType = xmlResourceParserLoadXmlMetaData.next()) {
            if (eventType == 2 && Intrinsics.areEqual(xmlResourceParserLoadXmlMetaData.getName(), TAG_TILE_SERVICE)) {
                oplusCustomTileServiceInfo = new TileStateController.OplusCustomTileServiceInfo();
                int attributeCount = xmlResourceParserLoadXmlMetaData.getAttributeCount();
                for (int i = 0; i < attributeCount; i++) {
                    String attributeName2 = xmlResourceParserLoadXmlMetaData.getAttributeName(i);
                    if (attributeName2 != null) {
                        boolean zEquals = "1".equals(xmlResourceParserLoadXmlMetaData.getAttributeValue(i));
                        if (attributeName2.equals(TAG_STATE_WHEN_LOCK)) {
                            oplusCustomTileServiceInfo.setAvailableWhenLock(zEquals);
                        }
                    }
                }
            } else if (eventType == 2 && Intrinsics.areEqual(xmlResourceParserLoadXmlMetaData.getName(), TAG_CONTENT_PROVIDER)) {
                int attributeCount2 = xmlResourceParserLoadXmlMetaData.getAttributeCount();
                Uri uri = null;
                String str = null;
                String str2 = null;
                String str3 = null;
                String str4 = null;
                for (int i2 = 0; i2 < attributeCount2; i2++) {
                    String attributeValue = xmlResourceParserLoadXmlMetaData.getAttributeValue(i2);
                    if (attributeValue != null && attributeValue.length() != 0 && (attributeName = xmlResourceParserLoadXmlMetaData.getAttributeName(i2)) != null) {
                        switch (attributeName.hashCode()) {
                            case -1296312216:
                                if (attributeName.equals(TAG_CONTENT_PROVIDER_VALUE_COLUMN_NAME)) {
                                    str = attributeValue;
                                }
                                break;
                            case -1060202997:
                                if (attributeName.equals(TAG_CONTENT_PROVIDER_ACTIVE_VALUE)) {
                                    str2 = attributeValue;
                                }
                                break;
                            case -488550458:
                                if (attributeName.equals(TAG_CONTENT_PROVIDER_INACTIVE_VALUE)) {
                                    str3 = attributeValue;
                                }
                                break;
                            case 956201729:
                                if (attributeName.equals(TAG_CONTENT_PROVIDER_UNAVAILABLE_VALUE)) {
                                    str4 = attributeValue;
                                }
                                break;
                            case 1290971959:
                                if (attributeName.equals(TAG_CONTENT_PROVIDER_AUTHORITIES)) {
                                    uri = Uri.parse(attributeValue);
                                }
                                break;
                        }
                    }
                }
                TileStateController.CustomTileContentProviderInfo customTileContentProviderInfo = (uri == null || str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? null : new TileStateController.CustomTileContentProviderInfo(uri, str, str2, str3, str4);
                if (customTileContentProviderInfo != null) {
                    if (oplusCustomTileServiceInfo == null) {
                        oplusCustomTileServiceInfo = new TileStateController.OplusCustomTileServiceInfo();
                    }
                    oplusCustomTileServiceInfo.setCustomTileContentProviderInfo(customTileContentProviderInfo);
                }
            }
        }
        Log.d(TAG, "parse " + componentName.flattenToShortString() + " done. info: " + (oplusCustomTileServiceInfo != null ? oplusCustomTileServiceInfo.getCustomTileContentProviderInfo() : null));
        return oplusCustomTileServiceInfo;
    }
}
