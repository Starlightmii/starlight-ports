package com.oplus.systemui.statusbar.policy;

import com.android.settingslib.SignalIcon;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class TelephonyIcons {
    public static final int[][] DATA_TYPE = {new int[]{2131236783, 2131236796}, new int[]{2131236784, 2131236796}, new int[]{2131236780, 2131236793}, new int[]{2131236768, 2131236785}, new int[]{2131236779, 2131236792}, new int[]{2131236781, 2131236794}, new int[]{2131236782, 2131236795}, new int[]{2131236769, 2131236786}, new int[]{2131236773, 2131236789}, new int[]{2131236774, 2131236790}, new int[]{2131236771, 2131236789}, new int[]{2131236772, 2131236789}, new int[]{2131236775, 2131236791}, new int[]{2131236775, 2131236791}, new int[]{2131236775, 2131236791}, new int[]{2131236775, 2131236791}, new int[]{2131236775, 2131236791}, new int[]{2131236952, 2131236791}, new int[]{2131234639, 2131234639}, new int[]{2131234640, 2131234640}};
    public static final SignalIcon.MobileIconGroup E;
    public static final SignalIcon.MobileIconGroup FIVE_DOT_FIVE_G;
    public static final SignalIcon.MobileIconGroup FIVE_G;
    public static final SignalIcon.MobileIconGroup FIVE_G_A;
    public static final SignalIcon.MobileIconGroup FIVE_G_BASIC;
    public static final SignalIcon.MobileIconGroup FIVE_G_E;
    public static final SignalIcon.MobileIconGroup FIVE_G_PLUS;
    public static final SignalIcon.MobileIconGroup FIVE_G_PLUS_PLUS;
    public static final SignalIcon.MobileIconGroup FIVE_G_SA;
    public static final SignalIcon.MobileIconGroup FIVE_G_UC;
    public static final SignalIcon.MobileIconGroup FIVE_G_UWB;
    public static final SignalIcon.MobileIconGroup FOUR_DOT_FIVE_G;
    public static final SignalIcon.MobileIconGroup FOUR_DOT_FIVE_G_PLUS;
    public static final SignalIcon.MobileIconGroup FOUR_G;
    public static final SignalIcon.MobileIconGroup FOUR_G_PLUS;
    public static final SignalIcon.MobileIconGroup G;
    public static final SignalIcon.MobileIconGroup H;
    public static final SignalIcon.MobileIconGroup H_PLUS;
    public static final SignalIcon.MobileIconGroup LTE;
    public static final SignalIcon.MobileIconGroup LTE_CA_5G_E;
    public static final SignalIcon.MobileIconGroup LTE_PLUS;
    public static final SignalIcon.MobileIconGroup NR_5G;
    public static final SignalIcon.MobileIconGroup NR_5G_PLUS;
    public static final SignalIcon.MobileIconGroup ONE_X;
    public static final SignalIcon.MobileIconGroup THREE_G;
    public static final SignalIcon.MobileIconGroup THREE_G_PLUS;
    public static final SignalIcon.MobileIconGroup TIGO_5G_CONNECTED;
    public static final SignalIcon.MobileIconGroup TIGO_5G_NOT_RESTRICTED;
    public static final SignalIcon.MobileIconGroup TWO_G;
    public static final SignalIcon.MobileIconGroup UNKNOWN;

    static {
        new SignalIcon.MobileIconGroup("CARRIER_NETWORK_CHANGE", 2131952574, 0);
        THREE_G = new SignalIcon.MobileIconGroup("3G", 2131952941, 2131236769);
        THREE_G_PLUS = new SignalIcon.MobileIconGroup("3G+", 2131952941, 2131236770);
        new SignalIcon.MobileIconGroup("WFC", 0, 0);
        UNKNOWN = new SignalIcon.MobileIconGroup("Unknown", 0, 0);
        E = new SignalIcon.MobileIconGroup("E", 2131952960, 2131236779);
        ONE_X = new SignalIcon.MobileIconGroup("1X", 2131952959, 2131236780);
        G = new SignalIcon.MobileIconGroup("G", 2131952961, 2131236780);
        TWO_G = new SignalIcon.MobileIconGroup("2G", 2131952961, 2131236768);
        H = new SignalIcon.MobileIconGroup("H", 2131952939, 2131236781);
        H_PLUS = new SignalIcon.MobileIconGroup("H+", 2131952940, 2131236782);
        FOUR_G = new SignalIcon.MobileIconGroup("4G", 2131952942, 2131236773);
        FOUR_G_PLUS = new SignalIcon.MobileIconGroup("4G+", 2131952945, 2131236774);
        FOUR_DOT_FIVE_G = new SignalIcon.MobileIconGroup("4.5G", 2131955768, 2131236771);
        FOUR_DOT_FIVE_G_PLUS = new SignalIcon.MobileIconGroup("4.5G+", 2131955769, 2131236772);
        LTE = new SignalIcon.MobileIconGroup("LTE", 2131952962, 2131236783);
        LTE_PLUS = new SignalIcon.MobileIconGroup("LTE+", 2131952963, 2131236784);
        LTE_CA_5G_E = new SignalIcon.MobileIconGroup("5Ge", 2131952957, 2131236775);
        NR_5G = new SignalIcon.MobileIconGroup("5G", 2131952947, 2131236775);
        NR_5G_PLUS = new SignalIcon.MobileIconGroup("5G_PLUS", 2131952947, 2131236775);
        FIVE_G_UC = new SignalIcon.MobileIconGroup("5GUC", 2131952955, 2131236951);
        FIVE_G_PLUS = new SignalIcon.MobileIconGroup("5G+", 2131952951, 2131236949);
        FIVE_G_PLUS_PLUS = new SignalIcon.MobileIconGroup("5G++", 2131952953, 2131236950);
        FIVE_G_E = new SignalIcon.MobileIconGroup("5GE", 2131952950, 2131236948);
        FIVE_G_A = new SignalIcon.MobileIconGroup("5GA", 2131952948, 2131236947);
        FIVE_DOT_FIVE_G = new SignalIcon.MobileIconGroup("5.5G", 2131952946, 2131236946);
        TIGO_5G_CONNECTED = new SignalIcon.MobileIconGroup("TIGO_5G_CONNECTED", 2131952947, 2131236733);
        TIGO_5G_NOT_RESTRICTED = new SignalIcon.MobileIconGroup("TIGO_5G_NOT_RESTRICTED", 2131952947, 2131236735);
        new SignalIcon.MobileIconGroup("DataDisabled", 2131952593, 0);
        new SignalIcon.MobileIconGroup("NotDefaultData", 2131954808, 0);
        FIVE_G = new SignalIcon.MobileIconGroup("5G", 2131952947, 2131236775);
        FIVE_G_BASIC = new SignalIcon.MobileIconGroup("5GBasic", 2131952949, 2131236775);
        FIVE_G_UWB = new SignalIcon.MobileIconGroup("5GUWB", 2131952956, 2131236952);
        FIVE_G_SA = new SignalIcon.MobileIconGroup("5GSA", 2131952954, 2131236775);
        new SignalIcon.MobileIconGroup("VoWIFI", 0, 2131234639);
        new SignalIcon.MobileIconGroup("VoWIFICall", 0, 2131234640);
    }
}
