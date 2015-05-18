package se.lu.bos.misgen.model;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Erik, KpgQuop
 * Date: 2015-01-19
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */
public enum StaticObjectType {

    STATIC_OPEL(201, "LuaScripts\\WorldObjects\\Blocks\\static_opel.txt", "graphics\\blocks\\static_opel.mgm", StaticObjectCategory.VEHICLE, "static_opel.jpg"),
    STATIC_BF109_OPEN(201, "LuaScripts\\WorldObjects\\Blocks\\static_bf109_open.txt", "graphics\\blocks\\static_bf109_open.mgm", StaticObjectCategory.VEHICLE, "static_bf109_open.jpg"),
    STATIC_OPEN_FUEL(201, "LuaScripts\\WorldObjects\\Blocks\\static_opel_fuel.txt", "graphics\\blocks\\static_opel_fuel.mgm", StaticObjectCategory.VEHICLE, "static_opel_fuel.jpg"),
    STATIC_HE111(201, "LuaScripts\\WorldObjects\\Blocks\\static_he111.txt", "graphics\\blocks\\static_he111.mgm", StaticObjectCategory.VEHICLE, "static_he111.jpg"),
    STATIC_JU87(201, "LuaScripts\\WorldObjects\\Blocks\\static_ju87.txt", "graphics\\blocks\\static_ju87.mgm", StaticObjectCategory.VEHICLE, "static_ju87.jpg"),

    ART_POSITION_SMALL(0, "LuaScripts\\WorldObjects\\Blocks\\art_position_small.txt", "graphics\\blocks\\art_position_small.mgm", StaticObjectCategory.EMPLACEMENT, "art_position_small.jpg"),
    ART_POSITION_BIG(0, "LuaScripts\\WorldObjects\\Blocks\\art_position_big.txt", "graphics\\blocks\\art_position_big.mgm", StaticObjectCategory.EMPLACEMENT, "art_position_big.jpg"),
    ART_POSITION_AT(0, "LuaScripts\\WorldObjects\\Blocks\\art_position_at.txt", "graphics\\blocks\\art_position_at.mgm", StaticObjectCategory.EMPLACEMENT, "art_position_at.jpg"),
    ART_POSITION_AT2(0, "LuaScripts\\WorldObjects\\Blocks\\art_position_at2.txt", "graphics\\blocks\\art_position_at2.mgm", StaticObjectCategory.EMPLACEMENT, "art_position_at2.jpg"),
    ART_POSITION_AMB1(0, "LuaScripts\\WorldObjects\\Blocks\\art_position_amb1.txt", "graphics\\blocks\\art_position_amb1.mgm", StaticObjectCategory.EMPLACEMENT, "art_position_amb1.jpg"),
    ART_POSITION_AMB2(0, "LuaScripts\\WorldObjects\\Blocks\\art_position_amb2.txt", "graphics\\blocks\\art_position_amb2.mgm", StaticObjectCategory.EMPLACEMENT, "art_position_amb2.jpg"),
    ART_POSITION_MEDIUM(0, "LuaScripts\\WorldObjects\\Blocks\\art_position_medium.txt", "graphics\\blocks\\art_position_medium.mgm", StaticObjectCategory.EMPLACEMENT, "art_position_medium.jpg"),
    DUGOUT(0, "LuaScripts\\WorldObjects\\Blocks\\dugout.txt", "graphics\\blocks\\dugout.mgm", StaticObjectCategory.EMPLACEMENT, "dugout.jpg"),
    TANK_POSITION(0, "LuaScripts\\WorldObjects\\Blocks\\tank_position.txt", "graphics\\blocks\\tank_position.mgm", StaticObjectCategory.EMPLACEMENT, "tank_position.jpg"),
    MG_POSITION(0, "LuaScripts\\WorldObjects\\Blocks\\mg_position.txt", "graphics\\blocks\\mg_position.mgm", StaticObjectCategory.EMPLACEMENT, "mg_position.jpg"),
    MEH_01(0, "LuaScripts\\WorldObjects\\Blocks\\meh_01.txt", "graphics\\blocks\\meh_01.mgm", StaticObjectCategory.BUILDING, "meh_01.jpg"),
    RWSTATION_B(0, "LuaScripts\\WorldObjects\\Blocks\\rwstation_b.txt", "graphics\\blocks\\rwstation_b.mgm", StaticObjectCategory.BUILDING, "rwstation_b.jpg"),
    RWSTATION_S1(0, "LuaScripts\\WorldObjects\\Blocks\\rwstation_s1.txt", "graphics\\blocks\\rwstation_s1.mgm", StaticObjectCategory.BUILDING, "rwstation_s1.jpg"),
    RWSTATION_S2(0, "LuaScripts\\WorldObjects\\Blocks\\rwstation_s2.txt", "graphics\\blocks\\rwstation_s2.mgm", StaticObjectCategory.BUILDING, "rwstation_s2.jpg"),
    SCOT_01(0, "LuaScripts\\WorldObjects\\Blocks\\scot_01.txt", "graphics\\Blocks\\scot_01.mgm", StaticObjectCategory.BUILDING, "scot_01.jpg"),
    SKLAD_01(0, "LuaScripts\\WorldObjects\\Blocks\\sklad_01.txt", "graphics\\Blocks\\sklad_01.mgm", StaticObjectCategory.BUILDING, "sklad_01.jpg"),
    WATERTOWER(0, "LuaScripts\\WorldObjects\\Blocks\\watertower.txt", "graphics\\Blocks\\watertower.mgm", StaticObjectCategory.BUILDING, "watertower.jpg"),
    ARF_AMMO_1(0, "LuaScripts\\WorldObjects\\Blocks\\arf_ammo_1.txt", "graphics\\Blocks\\arf_ammo_1.mgm", StaticObjectCategory.BUILDING, "arf_ammo_1.jpg"),
    ARF_AMMO_2(0, "LuaScripts\\WorldObjects\\Blocks\\arf_ammo_2.txt", "graphics\\Blocks\\arf_ammo_2.mgm", StaticObjectCategory.BUILDING, "arf_ammo_2.jpg"),
    ARF_AMMO_3(0, "LuaScripts\\WorldObjects\\Blocks\\arf_ammo_3.txt", "graphics\\Blocks\\arf_ammo_3.mgm", StaticObjectCategory.BUILDING, "arf_ammo_3.jpg"),
    ARF_AMMO_4(0, "LuaScripts\\WorldObjects\\Blocks\\arf_ammo_4.txt", "graphics\\Blocks\\arf_ammo_4.mgm", StaticObjectCategory.BUILDING, "arf_ammo_4.jpg"),
    ARF_CAPONIERS_10_H_3(0, "LuaScripts\\WorldObjects\\Blocks\\arf_caponiers_10_h_3.txt", "graphics\\Blocks\\arf_caponiers_10_h_3.mgm", StaticObjectCategory.EMPLACEMENT, "arf_caponiers_10_h_3.jpg"),
    ARF_CAPONIERS_11_H_5(0, "LuaScripts\\WorldObjects\\Blocks\\arf_caponiers_11_h_5.txt", "graphics\\Blocks\\arf_caponiers_11_h_5.mgm", StaticObjectCategory.EMPLACEMENT, "arf_caponiers_11_h_5.jpg"),
    ARF_CAPONIERS_12_H_5(0, "LuaScripts\\WorldObjects\\Blocks\\arf_caponiers_12_h_5.txt", "graphics\\Blocks\\arf_caponiers_12_h_5.mgm", StaticObjectCategory.EMPLACEMENT, "arf_caponiers_12_h_5.jpg"),
    ARF_CAPONIERS_1_V_5(0, "LuaScripts\\WorldObjects\\Blocks\\arf_caponiers_1_v_5.txt", "graphics\\Blocks\\arf_caponiers_1_v_5.mgm", StaticObjectCategory.EMPLACEMENT, "arf_caponiers_1_v_5.jpg"),
    ARF_CAPONIERS_2_V_5(0, "LuaScripts\\WorldObjects\\Blocks\\arf_caponiers_2_v_5.txt", "graphics\\Blocks\\arf_caponiers_2_v_5.mgm", StaticObjectCategory.EMPLACEMENT, "arf_caponiers_2_v_5.jpg"),
    ARF_CAPONIERS_3_V_5(0, "LuaScripts\\WorldObjects\\Blocks\\arf_caponiers_3_v_5.txt", "graphics\\Blocks\\arf_caponiers_3_v_5.mgm", StaticObjectCategory.EMPLACEMENT, "arf_caponiers_3_v_5.jpg"),
    ARF_CAPONIERS_4_R_6(0, "LuaScripts\\WorldObjects\\Blocks\\arf_caponiers_4_r_6.txt", "graphics\\Blocks\\arf_caponiers_4_r_6.mgm", StaticObjectCategory.EMPLACEMENT, "arf_caponiers_4_r_6.jpg"),
    ARF_CAPONIERS_5_R_7(0, "LuaScripts\\WorldObjects\\Blocks\\arf_caponiers_5_r_7.txt", "graphics\\Blocks\\arf_caponiers_5_r_7.mgm", StaticObjectCategory.EMPLACEMENT, "arf_caponiers_5_r_7.jpg"),
    ARF_CAPONIERS_6_H_4(0, "LuaScripts\\WorldObjects\\Blocks\\arf_caponiers_6_h_4.txt", "graphics\\Blocks\\arf_caponiers_6_h_4.mgm", StaticObjectCategory.EMPLACEMENT, "arf_caponiers_6_h_4.jpg"),
    ARF_CAPONIERS_7_H_4(0, "LuaScripts\\WorldObjects\\Blocks\\arf_caponiers_7_h_4.txt", "graphics\\Blocks\\arf_caponiers_7_h_4.mgm", StaticObjectCategory.EMPLACEMENT, "arf_caponiers_7_h_4.jpg"),
    ARF_CAPONIERS_8_H_4(0, "LuaScripts\\WorldObjects\\Blocks\\arf_caponiers_8_h_4.txt", "graphics\\Blocks\\arf_caponiers_8_h_4.mgm", StaticObjectCategory.EMPLACEMENT, "arf_caponiers_8_h_4.jpg"),
    ARF_CAPONIERS_9_H_3(0, "LuaScripts\\WorldObjects\\Blocks\\arf_caponiers_9_h_3.txt", "graphics\\Blocks\\arf_caponiers_9_h_3.mgm", StaticObjectCategory.EMPLACEMENT, "arf_caponiers_9_h_3.jpg"),
    ARF_DUGOUTS_1(0, "LuaScripts\\WorldObjects\\Blocks\\arf_dugouts_1.txt", "graphics\\Blocks\\arf_dugouts_1.mgm", StaticObjectCategory.EMPLACEMENT, "arf_dugouts_1.jpg"),
    ARF_DUGOUTS_2(0, "LuaScripts\\WorldObjects\\Blocks\\arf_dugouts_2.txt", "graphics\\Blocks\\arf_dugouts_2.mgm", StaticObjectCategory.EMPLACEMENT, "arf_dugouts_2.jpg"),
    ARF_DUGOUTS_3(0, "LuaScripts\\WorldObjects\\Blocks\\arf_dugouts_3.txt", "graphics\\Blocks\\arf_dugouts_3.mgm", StaticObjectCategory.EMPLACEMENT, "arf_dugouts_3.jpg"),
    ARF_GSM_1(0, "LuaScripts\\WorldObjects\\Blocks\\arf_gsm_1.txt", "graphics\\Blocks\\arf_gsm_1.mgm", StaticObjectCategory.EMPLACEMENT, "arf_gsm_1.jpg"),
    ARF_GSM_2(0, "LuaScripts\\WorldObjects\\Blocks\\arf_gsm_2.txt", "graphics\\Blocks\\arf_gsm_2.mgm", StaticObjectCategory.EMPLACEMENT, "arf_gsm_2.jpg"),
    ARF_HANGARS_1(0, "LuaScripts\\WorldObjects\\Blocks\\arf_hangars_1.txt", "graphics\\Blocks\\arf_hangars_1.mgm", StaticObjectCategory.BUILDING, "arf_hangars_1.jpg"),
    ARF_HANGARS_2(0, "LuaScripts\\WorldObjects\\Blocks\\arf_hangars_2.txt", "graphics\\Blocks\\arf_hangars_2.mgm", StaticObjectCategory.BUILDING, "arf_hangars_2.jpg"),
    ARF_HANGARS_3(0, "LuaScripts\\WorldObjects\\Blocks\\arf_hangars_3.txt", "graphics\\Blocks\\arf_hangars_3.mgm", StaticObjectCategory.BUILDING, "arf_hangars_3.jpg"),
    ARF_NETS_1_4(0, "LuaScripts\\WorldObjects\\Blocks\\arf_nets_1_4.txt", "graphics\\Blocks\\arf_nets_1_4.mgm", StaticObjectCategory.EMPLACEMENT, "arf_nets_1_4.jpg"),
    ARF_NETS_2_5(0, "LuaScripts\\WorldObjects\\Blocks\\arf_nets_2_5.txt", "graphics\\Blocks\\arf_nets_2_5.mgm", StaticObjectCategory.EMPLACEMENT, "arf_nets_2_5.jpg"),
    ARF_NETS_3_3(0, "LuaScripts\\WorldObjects\\Blocks\\arf_nets_3_3.txt", "graphics\\Blocks\\arf_nets_3_3.mgm", StaticObjectCategory.EMPLACEMENT, "arf_nets_3_3.jpg"),
    ARF_TOWER_1(0, "LuaScripts\\WorldObjects\\Blocks\\arf_tower_1.txt", "graphics\\Blocks\\arf_tower_1.mgm", StaticObjectCategory.BUILDING, "arf_tower_1.jpg"),
    ARF_TOWER_2(0, "LuaScripts\\WorldObjects\\Blocks\\arf_tower_2.txt", "graphics\\Blocks\\arf_tower_2.mgm", StaticObjectCategory.BUILDING, "arf_tower_2.jpg"),
    FAKE_BLOCK(0, "LuaScripts\\WorldObjects\\Blocks\\fake_block.txt", "graphics\\Blocks\\fake_block.mgm", StaticObjectCategory.EMPLACEMENT, "fake_block.jpg"),
    GUMRAK(0, "LuaScripts\\WorldObjects\\Blocks\\gumrak.txt", "graphics\\Blocks\\gumrak.mgm", StaticObjectCategory.EMPLACEMENT, "gumrak.jpg"),
    STATIC_YAK1(101, "LuaScripts\\WorldObjects\\Blocks\\static_yak1.txt", "graphics\\Blocks\\static_yak1.mgm", StaticObjectCategory.VEHICLE, "static_yak1.jpg"),
    STATIC_YAK1_NET(0, "LuaScripts\\WorldObjects\\Blocks\\static_yak1_net.txt", "graphics\\Blocks\\static_yak1_net.mgm", StaticObjectCategory.EMPLACEMENT, "static_yak1_net.jpg"),
    STATIC_YAK1_OPEN(0, "LuaScripts\\WorldObjects\\Blocks\\static_yak1_open.txt", "graphics\\Blocks\\static_yak1_open.mgm", StaticObjectCategory.VEHICLE, "static_yak1_open.jpg"),
    STATIC_BF109(201, "LuaScripts\\WorldObjects\\Blocks\\static_bf109.txt", "graphics\\Blocks\\static_bf109.mgm", StaticObjectCategory.VEHICLE, "static_bf109.jpg"),
    STATIC_BF109_NET(0, "LuaScripts\\WorldObjects\\Blocks\\static_bf109_net.txt", "graphics\\Blocks\\static_bf109_net.mgm", StaticObjectCategory.EMPLACEMENT, "static_bf109_net.jpg"),
    STATIC_HE111_OPEN(201, "LuaScripts\\WorldObjects\\Blocks\\static_he111_open.txt", "graphics\\Blocks\\static_he111_open.mgm", StaticObjectCategory.VEHICLE, "static_he111_open.jpg"),
    STATIC_IL2(101, "LuaScripts\\WorldObjects\\Blocks\\static_il2.txt2", "graphics\\Blocks\\static_il2.mgm", StaticObjectCategory.VEHICLE, "static_il2.jpg"),
    STATIC_IL2_NET(0, "LuaScripts\\WorldObjects\\Blocks\\static_il2_net.txt", "graphics\\Blocks\\static_il2_net.mgm", StaticObjectCategory.EMPLACEMENT, "static_il2_net.jpg"),
    STATIC_JU87_NET(0, "LuaScripts\\WorldObjects\\Blocks\\static_ju87_net.txt", "graphics\\Blocks\\static_ju87_net.mgm", StaticObjectCategory.EMPLACEMENT, "static_ju87_net.jpg"),
    STATIC_JU87_OPEN(201, "LuaScripts\\WorldObjects\\Blocks\\static_ju87_open.txt", "graphics\\Blocks\\static_ju87_open.mgm", StaticObjectCategory.VEHICLE, "static_ju87_open.jpg"),
    STATIC_LAGG3(101, "LuaScripts\\WorldObjects\\Blocks\\static_lagg3.txt", "graphics\\Blocks\\static_lagg3.mgm", StaticObjectCategory.VEHICLE, "static_lagg3.jpg"),
    STATIC_LAGG3_NET(0, "LuaScripts\\WorldObjects\\Blocks\\static_lagg3_net.txt", "graphics\\Blocks\\static_lagg3_net.mgm", StaticObjectCategory.EMPLACEMENT, "static_lagg3_net.jpg"),
    STATIC_LAGG3_W1(101, "LuaScripts\\WorldObjects\\Blocks\\static_lagg3_w1.txt", "graphics\\Blocks\\static_lagg3_w1.mgm", StaticObjectCategory.VEHICLE, "static_lagg3_w1.jpg"),
    STATIC_LAGG3_W2(101, "LuaScripts\\WorldObjects\\Blocks\\static_lagg3_w2.txt", "graphics\\Blocks\\static_lagg3_w2.mgm", StaticObjectCategory.VEHICLE, "static_lagg3_w2.jpg"),
    STATIC_OPEL_FUEL(201, "LuaScripts\\WorldObjects\\Blocks\\static_opel_fuel.txt", "graphics\\Blocks\\static_opel_fuel.mgm", StaticObjectCategory.VEHICLE, "static_opel_fuel.jpg"),
    STATIC_PE2(101, "LuaScripts\\WorldObjects\\Blocks\\static_pe2.txt", "graphics\\Blocks\\static_pe2.mgm", StaticObjectCategory.VEHICLE, "static_pe2.jpg"),
    STATIC_PE2_OPEN(101, "LuaScripts\\WorldObjects\\Blocks\\static_pe2_open.txt", "graphics\\Blocks\\static_pe2_open.mgm", StaticObjectCategory.VEHICLE, "static_pe2_open.jpg"),
    STATIC_ZIS(101, "LuaScripts\\WorldObjects\\Blocks\\static_zis.txt", "graphics\\Blocks\\static_zis.mgm", StaticObjectCategory.VEHICLE, "static_zis.jpg"),
    STATIC_ZIS_FUEL(101, "LuaScripts\\WorldObjects\\Blocks\\static_zis_fuel.txt", "graphics\\Blocks\\static_zis_fuel.mgm", StaticObjectCategory.VEHICLE, "static_zis_fuel.jpg"),
    TACINSKAYA_1(0, "LuaScripts\\WorldObjects\\Blocks\\tacinskaya_1.txt", "graphics\\Blocks\\tacinskaya_1.mgm", StaticObjectCategory.EMPLACEMENT, "tacinsckaya_1.jpg"),
    TACINSKAYA_2(0, "LuaScripts\\WorldObjects\\Blocks\\tacinskaya_2.txt", "graphics\\Blocks\\tacinskaya_2.mgm", StaticObjectCategory.EMPLACEMENT, "tacinsckaya_2.jpg"),
    TACINSKAYA_3(0, "LuaScripts\\WorldObjects\\Blocks\\tacinskaya_3.txt", "graphics\\Blocks\\tacinskaya_3.mgm", StaticObjectCategory.EMPLACEMENT, "tacinsckaya_3.jpg"),
    VL_001(0, "LuaScripts\\WorldObjects\\Blocks\\vl_001.txt", "graphics\\Blocks\\vl_001.mgm", StaticObjectCategory.BUILDING, "vl_001.jpg"),
    VL_002(0, "LuaScripts\\WorldObjects\\Blocks\\vl_002.txt", "graphics\\Blocks\\vl_002.mgm", StaticObjectCategory.BUILDING, "vl_002.jpg"),
    VL_003(0, "LuaScripts\\WorldObjects\\Blocks\\vl_003.txt", "graphics\\Blocks\\vl_003.mgm", StaticObjectCategory.BUILDING, "vl_003.jpg"),
    VL_004(0, "LuaScripts\\WorldObjects\\Blocks\\vl_004.txt", "graphics\\Blocks\\vl_004.mgm", StaticObjectCategory.BUILDING, "vl_004.jpg"),
    VL_005(0, "LuaScripts\\WorldObjects\\Blocks\\vl_005.txt", "graphics\\Blocks\\vl_005.mgm", StaticObjectCategory.BUILDING, "vl_005.jpg"),
    VL_1_06(0, "LuaScripts\\WorldObjects\\Blocks\\vl_1-06.txt", "graphics\\Blocks\\vl_1-06.mgm", StaticObjectCategory.BUILDING, "vl_1_06.jpg"),
    VL_1_07(0, "LuaScripts\\WorldObjects\\Blocks\\vl_1-07.txt", "graphics\\Blocks\\vl_1-07.mgm", StaticObjectCategory.BUILDING, "vl_1_07.jpg"),
    VL_1_08(0, "LuaScripts\\WorldObjects\\Blocks\\vl_1-08.txt", "graphics\\Blocks\\vl_1-08.mgm", StaticObjectCategory.BUILDING, "vl_1_08.jpg"),
    VL_1_09(0, "LuaScripts\\WorldObjects\\Blocks\\vl_1-09.txt", "graphics\\Blocks\\vl_1-09.mgm", StaticObjectCategory.BUILDING, "vl_1_09.jpg"),
    VL_2WAGONS(0, "LuaScripts\\WorldObjects\\Blocks\\vl_2wagons.txt", "graphics\\Blocks\\vl_2wagons.mgm", StaticObjectCategory.VEHICLE, "vl_2wagons.jpg"),
    VL_3WAGONS(0, "LuaScripts\\WorldObjects\\Blocks\\vl_3wagons.txt", "graphics\\Blocks\\vl_3wagons.mgm", StaticObjectCategory.VEHICLE, "vl_3wagons.jpg"),
    VL_BANYA(0, "LuaScripts\\WorldObjects\\Blocks\\vl_banya.txt", "graphics\\Blocks\\vl_banya.mgm", StaticObjectCategory.BUILDING, "vl_banya.jpg"),
    VL_CERKVOZNES(0, "LuaScripts\\WorldObjects\\Blocks\\vl_cerkvoznes.txt", "graphics\\Blocks\\vl_cerkvoznes.mgm", StaticObjectCategory.BUILDING, "vl_cerkvoznes.jpg"),
    VL_CHAPEL(0, "LuaScripts\\WorldObjects\\Blocks\\vl_chapel.txt", "graphics\\Blocks\\vl_chapel.mgm", StaticObjectCategory.BUILDING, "vl_chapel.jpg"),
    VL_DEPO(0, "LuaScripts\\WorldObjects\\Blocks\\vl_depo.txt", "graphics\\Blocks\\vl_depo.mgm", StaticObjectCategory.BUILDING, "vl_depo.jpg"),
    VL_DK(0, "LuaScripts\\WorldObjects\\Blocks\\vl_dk.txt", "graphics\\Blocks\\vl_dk.mgm", StaticObjectCategory.BUILDING, "vl_dk.jpg"),
    VL_FACTORY(0, "LuaScripts\\WorldObjects\\Blocks\\vl_factory.txt", "graphics\\Blocks\\vl_factory.mgm", StaticObjectCategory.BUILDING, "vl_factory.jpg"),
    VL_KLAD(0, "LuaScripts\\WorldObjects\\Blocks\\vl_klad.txt", "graphics\\Blocks\\vl_klad.mgm", StaticObjectCategory.BUILDING, "vl_klad.jpg"),
    VL_N1STATION(0, "LuaScripts\\WorldObjects\\Blocks\\vl_n1station.txt", "graphics\\Blocks\\vl_n1station.mgm", StaticObjectCategory.BUILDING, "vl_n1station.jpg"),
    VL_N2STATION(0, "LuaScripts\\WorldObjects\\Blocks\\vl_n2station.txt", "graphics\\Blocks\\vl_n2station.mgm", StaticObjectCategory.BUILDING, "vl_n2station.jpg"),
    VL_NSSTATION(0, "LuaScripts\\WorldObjects\\Blocks\\vl_nsstation.txt", "graphics\\Blocks\\vl_nsstation.mgm", StaticObjectCategory.BUILDING, "vl_nsstation.jpg"),
    VL_NSSTATION_W(0, "LuaScripts\\WorldObjects\\Blocks\\vl_nsstation_w.txt", "graphics\\Blocks\\vl_nsstation_w.mgm", StaticObjectCategory.BUILDING, "vl_nsstation_w.jpg"),
    VL_PIONER(0, "LuaScripts\\WorldObjects\\Blocks\\vl_pioner.txt", "graphics\\Blocks\\vl_pioner.mgm", StaticObjectCategory.BUILDING, "vl_pioner.jpg"),
    VL_PVRZ(0, "LuaScripts\\WorldObjects\\Blocks\\vl_pvrz.txt", "graphics\\Blocks\\vl_pvrz.mgm", StaticObjectCategory.BUILDING, "vl_pvrz.jpg"),
    VL_ROUNDDEPOT(0, "LuaScripts\\WorldObjects\\Blocks\\vl_rounddepot.txt", "graphics\\Blocks\\vl_rounddepot.mgm", StaticObjectCategory.BUILDING, "vl_rounddepot.jpg"),
    VL_ROUNDDEPOT_RIGHT(0, "LuaScripts\\WorldObjects\\Blocks\\vl_rounddepot_right.txt", "graphics\\Blocks\\vl_rounddepot_right.mgm", StaticObjectCategory.BUILDING, "vl_rounddepot_right.jpg"),
    VL_SCHOOL(0, "LuaScripts\\WorldObjects\\Blocks\\vl_school.txt", "graphics\\Blocks\\vl_school.mgm", StaticObjectCategory.BUILDING, "vl_school.jpg"),
    VL_SCHOOL02(0, "LuaScripts\\WorldObjects\\Blocks\\vl_school02.txt", "graphics\\Blocks\\vl_school02.mgm", StaticObjectCategory.BUILDING, "vl_school02.jpg"),
    VL_SELMAG(0, "LuaScripts\\WorldObjects\\Blocks\\vl_selmag.txt", "graphics\\Blocks\\vl_selmag.mgm", StaticObjectCategory.BUILDING, "vl_selmag.jpg"),
    VL_SELPO(0, "LuaScripts\\WorldObjects\\Blocks\\vl_selpo.txt", "graphics\\Blocks\\vl_selpo.mgm", StaticObjectCategory.BUILDING, "vl_selpo.jpg"),
    VL_SELSOVET(0, "LuaScripts\\WorldObjects\\Blocks\\vl_selsovet.txt", "graphics\\Blocks\\vl_selsovet.mgm", StaticObjectCategory.BUILDING, "vl_selsovet.jpg"),
    VL_STADIUM(0, "LuaScripts\\WorldObjects\\Blocks\\vl_stadium.txt", "graphics\\Blocks\\vl_stadium.mgm", StaticObjectCategory.BUILDING, "vl_stadium.jpg"),
    VL_TORGRYAD(0, "LuaScripts\\WorldObjects\\Blocks\\vl_torgryad.txt", "graphics\\Blocks\\vl_torgryad.mgm", StaticObjectCategory.BUILDING, "vl_torgryad.jpg"),
    VL_TUPIK(0, "LuaScripts\\WorldObjects\\Blocks\\vl_tupik.txt", "graphics\\Blocks\\vl_tupik.mgm", StaticObjectCategory.BUILDING, "vl_tupik.jpg"),
    VL_VLVOKZAL(0, "LuaScripts\\WorldObjects\\Blocks\\vl_vlvokzal.txt", "graphics\\Blocks\\vl_vlvokzal.mgm", StaticObjectCategory.BUILDING, "vl_vlvokzal.jpg"),
    VL_VODONAPOR(0, "LuaScripts\\WorldObjects\\Blocks\\vl_vodonapor.txt", "graphics\\Blocks\\vl_vodonapor.mgm", StaticObjectCategory.BUILDING, "vl_vodonapor.jpg"),
    VL_WAGON_PASS(0, "LuaScripts\\WorldObjects\\Blocks\\vl_wagon_pass.txt", "graphics\\Blocks\\vl_wagon_pass.mgm", StaticObjectCategory.BUILDING, "vl_wagon_pass.jpg"),
    VL_WAGONS(0, "LuaScripts\\WorldObjects\\Blocks\\vl_wagons.txt", "graphics\\Blocks\\vl_wagons.mgm", StaticObjectCategory.BUILDING, "vl_wagons.jpg");

    private final int country;
    private final String script;
    private final String model;
    private final StaticObjectCategory category;
    private final String iconImage;

    StaticObjectType(int country, String script, String model, StaticObjectCategory category, String iconImage) {
        this.country = country;
        this.script = script;
        this.model = model;
        this.category = category;
        this.iconImage = iconImage;
    }

    public String getScript() {
        return script;
    }

    public String getModel() {
        return model;
    }

    public StaticObjectCategory getCategory() {
        return category;
    }

    public int getCountry() {
        return country;
    }

    public String getIconImage() {
        return iconImage;
    }

    public Set<StaticObjectType> values(int country) {
        return Arrays.asList(StaticObjectType.values()).stream().filter(sot -> sot.getCountry() == country).collect(Collectors.toSet());
    }
}
