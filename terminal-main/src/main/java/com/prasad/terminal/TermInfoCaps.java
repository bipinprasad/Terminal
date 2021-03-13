
//Title:        Terminal Emulator
//Version:      
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator


package com.prasad.terminal;

/**
 * Terminal Info Capability
 */

public class TermInfoCaps {
    public static final int CAPABILITY_TYPE_UNKNOWN = 7;
    public static final int CAPABILITY_TYPE_BOOLEAN = 1;
    public static final int CAPABILITY_TYPE_NUMERIC = 2;
    public static final int CAPABILITY_TYPE_STRING = 4;

    /**
     * TermInfo Capability Id
     */
    public static final int CAPABILITY_ID_UNKNOWN = -1;
    public static final int CAPABILITY_ID_BW = 1;
    public static final int CAPABILITY_ID_AM = 2;
    public static final int CAPABILITY_ID_BCE = 3;
    public static final int CAPABILITY_ID_XSB = 4;
    public static final int CAPABILITY_ID_CCC = 5;
    public static final int CAPABILITY_ID_XHP = 6;
    public static final int CAPABILITY_ID_XHPA = 7;
    public static final int CAPABILITY_ID_CPIX = 8;
    public static final int CAPABILITY_ID_CRXM = 9;
    public static final int CAPABILITY_ID_XT = 10;
    public static final int CAPABILITY_ID_XENL = 11;
    public static final int CAPABILITY_ID_EO = 12;
    public static final int CAPABILITY_ID_GN = 13;
    public static final int CAPABILITY_ID_HC = 14;
    public static final int CAPABILITY_ID_CHTS = 15;
    public static final int CAPABILITY_ID_KM = 16;
    public static final int CAPABILITY_ID_HS = 17;
    public static final int CAPABILITY_ID_DAISY = 18;
    public static final int CAPABILITY_ID_HLS = 19;
    public static final int CAPABILITY_ID_IN = 20;
    public static final int CAPABILITY_ID_LPIX = 21;
    public static final int CAPABILITY_ID_DA = 22;
    public static final int CAPABILITY_ID_DB = 23;
    public static final int CAPABILITY_ID_MIR = 24;
    public static final int CAPABILITY_ID_MSGR = 25;
    public static final int CAPABILITY_ID_NXON = 26;
    public static final int CAPABILITY_ID_NPC = 28;
    public static final int CAPABILITY_ID_NDSCR = 29;
    public static final int CAPABILITY_ID_NRRMC = 30;
    public static final int CAPABILITY_ID_OS = 31;
    public static final int CAPABILITY_ID_MC5I = 32;
    public static final int CAPABILITY_ID_XVPA = 33;
    public static final int CAPABILITY_ID_SAM = 34;
    public static final int CAPABILITY_ID_ESLOK = 35;
    public static final int CAPABILITY_ID_HZ = 37;
    public static final int CAPABILITY_ID_UL = 38;
    public static final int CAPABILITY_ID_XON = 39;
    // numeric capabilities
    public static final int CAPABILITY_ID_BITWIN = 50;
    public static final int CAPABILITY_ID_BITYPE = 51;
    public static final int CAPABILITY_ID_BUFSZ = 52;
    public static final int CAPABILITY_ID_BTNS = 53;
    public static final int CAPABILITY_ID_COLS = 54;
    public static final int CAPABILITY_ID_SPINH = 55;
    public static final int CAPABILITY_ID_SPINV = 56;
    public static final int CAPABILITY_ID_IT = 57;
    public static final int CAPABILITY_ID_LH = 58;
    public static final int CAPABILITY_ID_LW = 59;
    public static final int CAPABILITY_ID_LINES = 60;
    public static final int CAPABILITY_ID_LM = 61;
    public static final int CAPABILITY_ID_MA = 62;
    public static final int CAPABILITY_ID_XMC = 63;
    public static final int CAPABILITY_ID_COLORS = 64;
    public static final int CAPABILITY_ID_MADDR = 65;
    public static final int CAPABILITY_ID_MJUMP = 66;
    public static final int CAPABILITY_ID_PAIRS = 67;
    public static final int CAPABILITY_ID_WNUM = 68;
    public static final int CAPABILITY_ID_MCS = 69;
    public static final int CAPABILITY_ID_MLS = 70;
    public static final int CAPABILITY_ID_NCV = 71;
    public static final int CAPABILITY_ID_NLAB = 72;
    public static final int CAPABILITY_ID_NPINS = 73;
    public static final int CAPABILITY_ID_ORC = 74;
    public static final int CAPABILITY_ID_ORL = 75;
    public static final int CAPABILITY_ID_ORHI = 76;
    public static final int CAPABILITY_ID_ORVI = 77;
    public static final int CAPABILITY_ID_PB = 78;
    public static final int CAPABILITY_ID_CPS = 79;
    public static final int CAPABILITY_ID_VT = 80;
    public static final int CAPABILITY_ID_WIDCS = 81;
    public static final int CAPABILITY_ID_WSL = 82;
    // string CAPABILITIES
    public static final int CAPABILITY_ID_ACSC = 91;
    public static final int CAPABILITY_ID_SCESA = 92;
    public static final int CAPABILITY_ID_CBT = 93;
    public static final int CAPABILITY_ID_BEL = 94;
    public static final int CAPABILITY_ID_BICR = 95;
    public static final int CAPABILITY_ID_BINEL = 96;
    public static final int CAPABILITY_ID_BIREP = 97;
    public static final int CAPABILITY_ID_CR = 98;
    public static final int CAPABILITY_ID_CPI = 99;
    public static final int CAPABILITY_ID_LPI = 100;
    public static final int CAPABILITY_ID_CHR = 101;
    public static final int CAPABILITY_ID_CVR = 102;
    public static final int CAPABILITY_ID_CSR = 103;
    public static final int CAPABILITY_ID_RMP = 104;
    public static final int CAPABILITY_ID_CSNM = 105;
    public static final int CAPABILITY_ID_TBC = 106;
    public static final int CAPABILITY_ID_MGC = 107;
    public static final int CAPABILITY_ID_CLEAR = 108;
    public static final int CAPABILITY_ID_EL1 = 109;
    public static final int CAPABILITY_ID_EL = 500;
    public static final int CAPABILITY_ID_ED = 501;
    public static final int CAPABILITY_ID_CSIN = 502;
    public static final int CAPABILITY_ID_COLORNM = 503;
    public static final int CAPABILITY_ID_HPA = 504;
    public static final int CAPABILITY_ID_CMDCH = 505;
    public static final int CAPABILITY_ID_CWIN = 506;
    public static final int CAPABILITY_ID_CUP = 507;
    public static final int CAPABILITY_ID_CUD1 = 508;
    public static final int CAPABILITY_ID_HOME = 509;
    public static final int CAPABILITY_ID_CIVIS = 110;
    public static final int CAPABILITY_ID_CUB1 = 111;
    public static final int CAPABILITY_ID_MRCUP = 112;
    public static final int CAPABILITY_ID_CNORM = 113;
    public static final int CAPABILITY_ID_CUF1 = 114;
    public static final int CAPABILITY_ID_LL = 115;
    public static final int CAPABILITY_ID_CUU1 = 116;
    public static final int CAPABILITY_ID_CVVIS = 117;
    public static final int CAPABILITY_ID_DEFBI = 118;
    public static final int CAPABILITY_ID_DEFC = 119;
    public static final int CAPABILITY_ID_DCH1 = 120;
    public static final int CAPABILITY_ID_DL1 = 121;
    public static final int CAPABILITY_ID_DEVT = 122;
    public static final int CAPABILITY_ID_DIAL = 123;
    public static final int CAPABILITY_ID_DSL = 124;
    public static final int CAPABILITY_ID_DCLK = 125;
    public static final int CAPABILITY_ID_DISPC = 126;
    public static final int CAPABILITY_ID_HD = 127;
    public static final int CAPABILITY_ID_ENACS = 128;
    public static final int CAPABILITY_ID_ENDBI = 129;
    public static final int CAPABILITY_ID_SMACS = 130;
    public static final int CAPABILITY_ID_SMAM = 131;
    public static final int CAPABILITY_ID_BLINK = 132;
    public static final int CAPABILITY_ID_BOLD = 133;
    public static final int CAPABILITY_ID_SMCUP = 134;
    public static final int CAPABILITY_ID_SMDC = 135;
    public static final int CAPABILITY_ID_DIM = 136;
    public static final int CAPABILITY_ID_SWIDM = 137;
    public static final int CAPABILITY_ID_SDRFQ = 138;
    public static final int CAPABILITY_ID_SMIR = 139;
    public static final int CAPABILITY_ID_SITM = 140;
    public static final int CAPABILITY_ID_SLM = 141;
    public static final int CAPABILITY_ID_SMICM = 142;
    public static final int CAPABILITY_ID_SNLQ = 143;
    public static final int CAPABILITY_ID_SNRMQ = 144;
    public static final int CAPABILITY_ID_SMPCH = 145;
    public static final int CAPABILITY_ID_PROT = 146;
    public static final int CAPABILITY_ID_REV = 147;
    public static final int CAPABILITY_ID_SMSC = 148;
    public static final int CAPABILITY_ID_INVIS = 149;
    public static final int CAPABILITY_ID_SSHM = 150;
    public static final int CAPABILITY_ID_SMSO = 151;
    public static final int CAPABILITY_ID_SSUBM = 152;
    public static final int CAPABILITY_ID_SSUPM = 153;
    public static final int CAPABILITY_ID_SMUL = 154;
    public static final int CAPABILITY_ID_SUM = 155;
    public static final int CAPABILITY_ID_SMXON = 156;
    public static final int CAPABILITY_ID_ECH = 157;
    public static final int CAPABILITY_ID_RMACS = 158;
    public static final int CAPABILITY_ID_RMAM = 159;
    public static final int CAPABILITY_ID_SGR0 = 160;
    public static final int CAPABILITY_ID_RMCUP = 161;
    public static final int CAPABILITY_ID_RMDC = 162;
    public static final int CAPABILITY_ID_RWIDM = 163;
    public static final int CAPABILITY_ID_RMIR = 164;
    public static final int CAPABILITY_ID_RITM = 165;
    public static final int CAPABILITY_ID_RLM = 166;
    public static final int CAPABILITY_ID_RMICM = 167;
    public static final int CAPABILITY_ID_RMPCH = 168;
    public static final int CAPABILITY_ID_RMSC = 169;
    public static final int CAPABILITY_ID_RSHM = 170;
    public static final int CAPABILITY_ID_RMSO = 171;
    public static final int CAPABILITY_ID_RSUBM = 172;
    public static final int CAPABILITY_ID_RSUPM = 173;
    public static final int CAPABILITY_ID_RMUL = 174;
    public static final int CAPABILITY_ID_RUM = 175;
    public static final int CAPABILITY_ID_RMXON = 176;
    public static final int CAPABILITY_ID_PAUSE = 177;
    public static final int CAPABILITY_ID_HOOK = 178;
    public static final int CAPABILITY_ID_FLASH = 179;
    public static final int CAPABILITY_ID_FF = 180;
    public static final int CAPABILITY_ID_FSL = 181;
    public static final int CAPABILITY_ID_GETM = 182;
    public static final int CAPABILITY_ID_WINGO = 183;
    public static final int CAPABILITY_ID_HUP = 184;
    public static final int CAPABILITY_ID_IS1 = 185;
    public static final int CAPABILITY_ID_IS2 = 186;
    public static final int CAPABILITY_ID_IS3 = 187;
    public static final int CAPABILITY_ID_IF = 188;
    public static final int CAPABILITY_ID_IPROG = 189;
    public static final int CAPABILITY_ID_INITC = 190;
    public static final int CAPABILITY_ID_INITP = 191;
    public static final int CAPABILITY_ID_ICH1 = 192;
    public static final int CAPABILITY_ID_IL1 = 193;
    public static final int CAPABILITY_ID_IP = 194;
    public static final int CAPABILITY_ID_KA1 = 195;
    public static final int CAPABILITY_ID_KA3 = 196;
    public static final int CAPABILITY_ID_KB2 = 197;
    public static final int CAPABILITY_ID_KBS = 198;
    public static final int CAPABILITY_ID_KBEG = 199;
    public static final int CAPABILITY_ID_KCBT = 200;
    public static final int CAPABILITY_ID_KC1 = 201;
    public static final int CAPABILITY_ID_KC3 = 202;
    public static final int CAPABILITY_ID_KCAN = 203;
    public static final int CAPABILITY_ID_KTBC = 204;
    public static final int CAPABILITY_ID_KCLR = 205;
    public static final int CAPABILITY_ID_KCLO = 206;
    public static final int CAPABILITY_ID_KCMD = 207;
    public static final int CAPABILITY_ID_KCPY = 208;
    public static final int CAPABILITY_ID_KCRT = 209;
    public static final int CAPABILITY_ID_KCTAB = 210;
    public static final int CAPABILITY_ID_KDCH1 = 211;
    public static final int CAPABILITY_ID_KDL1 = 212;
    public static final int CAPABILITY_ID_KCUD1 = 213;
    public static final int CAPABILITY_ID_KRMIR = 214;
    public static final int CAPABILITY_ID_KEND = 215;
    public static final int CAPABILITY_ID_KENT = 216;
    public static final int CAPABILITY_ID_KEL = 217;
    public static final int CAPABILITY_ID_KED = 218;
    public static final int CAPABILITY_ID_KEXT = 219;
    public static final int CAPABILITY_ID_KF0 = 220;
    public static final int CAPABILITY_ID_KF1 = 221;
    public static final int CAPABILITY_ID_KF2 = 222;
    public static final int CAPABILITY_ID_KF3 = 223;
    public static final int CAPABILITY_ID_KF4 = 224;
    public static final int CAPABILITY_ID_KF5 = 225;
    public static final int CAPABILITY_ID_KF6 = 226;
    public static final int CAPABILITY_ID_KF7 = 227;
    public static final int CAPABILITY_ID_KF8 = 228;
    public static final int CAPABILITY_ID_KF9 = 229;
    public static final int CAPABILITY_ID_KF10 = 230;
    public static final int CAPABILITY_ID_KF11 = 231;
    public static final int CAPABILITY_ID_KF12 = 232;
    public static final int CAPABILITY_ID_KF13 = 233;
    public static final int CAPABILITY_ID_KF14 = 234;
    public static final int CAPABILITY_ID_KF15 = 235;
    public static final int CAPABILITY_ID_KF16 = 236;
    public static final int CAPABILITY_ID_KF17 = 237;
    public static final int CAPABILITY_ID_KF18 = 238;
    public static final int CAPABILITY_ID_KF19 = 239;
    public static final int CAPABILITY_ID_KF20 = 240;
    public static final int CAPABILITY_ID_KF21 = 241;
    public static final int CAPABILITY_ID_KF22 = 242;
    public static final int CAPABILITY_ID_KF23 = 243;
    public static final int CAPABILITY_ID_KF24 = 244;
    public static final int CAPABILITY_ID_KF25 = 245;
    public static final int CAPABILITY_ID_KF26 = 246;
    public static final int CAPABILITY_ID_KF27 = 247;
    public static final int CAPABILITY_ID_KF28 = 248;
    public static final int CAPABILITY_ID_KF29 = 249;
    public static final int CAPABILITY_ID_KF30 = 250;
    public static final int CAPABILITY_ID_KF31 = 251;
    public static final int CAPABILITY_ID_KF32 = 252;
    public static final int CAPABILITY_ID_KF33 = 253;
    public static final int CAPABILITY_ID_KF34 = 254;
    public static final int CAPABILITY_ID_KF35 = 255;
    public static final int CAPABILITY_ID_KF36 = 256;
    public static final int CAPABILITY_ID_KF37 = 257;
    public static final int CAPABILITY_ID_KF38 = 258;
    public static final int CAPABILITY_ID_KF39 = 259;
    public static final int CAPABILITY_ID_KF40 = 260;
    public static final int CAPABILITY_ID_KF41 = 261;
    public static final int CAPABILITY_ID_KF42 = 262;
    public static final int CAPABILITY_ID_KF43 = 263;
    public static final int CAPABILITY_ID_KF44 = 264;
    public static final int CAPABILITY_ID_KF45 = 265;
    public static final int CAPABILITY_ID_KF46 = 266;
    public static final int CAPABILITY_ID_KF47 = 267;
    public static final int CAPABILITY_ID_KF48 = 268;
    public static final int CAPABILITY_ID_KF49 = 269;
    public static final int CAPABILITY_ID_KF50 = 270;
    public static final int CAPABILITY_ID_KF51 = 271;
    public static final int CAPABILITY_ID_KF52 = 272;
    public static final int CAPABILITY_ID_KF53 = 273;
    public static final int CAPABILITY_ID_KF54 = 274;
    public static final int CAPABILITY_ID_KF55 = 275;
    public static final int CAPABILITY_ID_KF56 = 276;
    public static final int CAPABILITY_ID_KF57 = 277;
    public static final int CAPABILITY_ID_KF58 = 278;
    public static final int CAPABILITY_ID_KF59 = 279;
    public static final int CAPABILITY_ID_KF60 = 280;
    public static final int CAPABILITY_ID_KF61 = 281;
    public static final int CAPABILITY_ID_KF62 = 282;
    public static final int CAPABILITY_ID_KF63 = 283;
    public static final int CAPABILITY_ID_KFND = 284;
    public static final int CAPABILITY_ID_KHLP = 285;
    public static final int CAPABILITY_ID_KHOME = 286;
    public static final int CAPABILITY_ID_KICH1 = 287;
    public static final int CAPABILITY_ID_KIL1 = 288;
    public static final int CAPABILITY_ID_KCUB1 = 289;
    public static final int CAPABILITY_ID_KLL = 290;
    public static final int CAPABILITY_ID_KMRK = 291;
    public static final int CAPABILITY_ID_KMSG = 292;
    public static final int CAPABILITY_ID_KMOUS = 293;
    public static final int CAPABILITY_ID_KMOV = 294;
    public static final int CAPABILITY_ID_KNXT = 295;
    public static final int CAPABILITY_ID_KNP = 296;
    public static final int CAPABILITY_ID_KOPN = 297;
    public static final int CAPABILITY_ID_KOPT = 298;
    public static final int CAPABILITY_ID_KPP = 299;
    public static final int CAPABILITY_ID_KPRV = 300;
    public static final int CAPABILITY_ID_KPRT = 301;
    public static final int CAPABILITY_ID_KRDO = 302;
    public static final int CAPABILITY_ID_KREF = 303;
    public static final int CAPABILITY_ID_KRFR = 304;
    public static final int CAPABILITY_ID_KRPL = 305;
    public static final int CAPABILITY_ID_KRST = 306;
    public static final int CAPABILITY_ID_KRES = 307;
    public static final int CAPABILITY_ID_KCUF1 = 308;
    public static final int CAPABILITY_ID_KSAV = 309;
    public static final int CAPABILITY_ID_SKBEG = 310;
    public static final int CAPABILITY_ID_SKCAN = 311;
    public static final int CAPABILITY_ID_SKCMD = 312;
    public static final int CAPABILITY_ID_SKCPY = 313;
    public static final int CAPABILITY_ID_SKCRT = 314;
    public static final int CAPABILITY_ID_SKDC = 315;
    public static final int CAPABILITY_ID_SKDL = 316;
    public static final int CAPABILITY_ID_KSLT = 317;
    public static final int CAPABILITY_ID_SKEND = 318;
    public static final int CAPABILITY_ID_SKEOL = 319;
    public static final int CAPABILITY_ID_SKEXT = 320;
    public static final int CAPABILITY_ID_KIND = 321;
    public static final int CAPABILITY_ID_SKFND = 322;
    public static final int CAPABILITY_ID_SKHLP = 323;
    public static final int CAPABILITY_ID_SKHOM = 324;
    public static final int CAPABILITY_ID_SKIC = 325;
    public static final int CAPABILITY_ID_SKLFT = 326;
    public static final int CAPABILITY_ID_SKMSG = 327;
    public static final int CAPABILITY_ID_SKMOV = 328;
    public static final int CAPABILITY_ID_SKNXT = 329;
    public static final int CAPABILITY_ID_SKOPT = 330;
    public static final int CAPABILITY_ID_SKPRV = 331;
    public static final int CAPABILITY_ID_SKPRT = 332;
    public static final int CAPABILITY_ID_KRI = 333;
    public static final int CAPABILITY_ID_SKRDO = 334;
    public static final int CAPABILITY_ID_SKRPL = 335;
    public static final int CAPABILITY_ID_SKRIT = 336;
    public static final int CAPABILITY_ID_SKRES = 337;
    public static final int CAPABILITY_ID_SKSAV = 338;
    public static final int CAPABILITY_ID_SKSPD = 339;
    public static final int CAPABILITY_ID_KHTS = 340;
    public static final int CAPABILITY_ID_SKUND = 341;
    public static final int CAPABILITY_ID_KSPD = 342;
    public static final int CAPABILITY_ID_KUND = 343;
    public static final int CAPABILITY_ID_KCUU1 = 344;
    public static final int CAPABILITY_ID_RMKX = 345;
    public static final int CAPABILITY_ID_SMKX = 346;
    public static final int CAPABILITY_ID_LF0 = 347;
    public static final int CAPABILITY_ID_LF1 = 348;
    public static final int CAPABILITY_ID_LF2 = 349;
    public static final int CAPABILITY_ID_LF3 = 350;
    public static final int CAPABILITY_ID_LF4 = 351;
    public static final int CAPABILITY_ID_LF5 = 352;
    public static final int CAPABILITY_ID_LF6 = 353;
    public static final int CAPABILITY_ID_LF7 = 354;
    public static final int CAPABILITY_ID_LF8 = 355;
    public static final int CAPABILITY_ID_LF9 = 356;
    public static final int CAPABILITY_ID_LF10 = 357;
    public static final int CAPABILITY_ID_FLN = 358;
    public static final int CAPABILITY_ID_RMLN = 359;
    public static final int CAPABILITY_ID_SMLN = 360;
    public static final int CAPABILITY_ID_MEML = 361;
    public static final int CAPABILITY_ID_MEMU = 362;
    public static final int CAPABILITY_ID_SMM = 363;
    public static final int CAPABILITY_ID_MHPA = 364;
    public static final int CAPABILITY_ID_MCUD1 = 365;
    public static final int CAPABILITY_ID_MCUB1 = 366;
    public static final int CAPABILITY_ID_MCUF1 = 367;
    public static final int CAPABILITY_ID_MVPA = 368;
    public static final int CAPABILITY_ID_MCUU1 = 369;
    public static final int CAPABILITY_ID_MINFO = 370;
    public static final int CAPABILITY_ID_RMM = 371;
    public static final int CAPABILITY_ID_NEL = 372;
    public static final int CAPABILITY_ID_PORDER = 373;
    public static final int CAPABILITY_ID_OC = 374;
    public static final int CAPABILITY_ID_OP = 375;
    public static final int CAPABILITY_ID_PAD = 376;
    public static final int CAPABILITY_ID_DCH = 377;
    public static final int CAPABILITY_ID_DL = 378;
    public static final int CAPABILITY_ID_CUD = 379;
    public static final int CAPABILITY_ID_MCUD = 380;
    public static final int CAPABILITY_ID_ICH = 381;
    public static final int CAPABILITY_ID_INDN = 382;
    public static final int CAPABILITY_ID_IL = 383;
    public static final int CAPABILITY_ID_CUB = 384;
    public static final int CAPABILITY_ID_MCUB = 385;
    public static final int CAPABILITY_ID_CUF = 386;
    public static final int CAPABILITY_ID_MCUF = 387;
    public static final int CAPABILITY_ID_RIN = 388;
    public static final int CAPABILITY_ID_CUU = 389;
    public static final int CAPABILITY_ID_MCUU = 390;
    public static final int CAPABILITY_ID_PCTRM = 391;
    public static final int CAPABILITY_ID_PFKEY = 392;
    public static final int CAPABILITY_ID_PFLOC = 393;
    public static final int CAPABILITY_ID_PFXL = 394;
    public static final int CAPABILITY_ID_PFX = 395;
    public static final int CAPABILITY_ID_PLN = 396;
    public static final int CAPABILITY_ID_MC0 = 397;
    public static final int CAPABILITY_ID_MC5P = 398;
    public static final int CAPABILITY_ID_MC4 = 399;
    public static final int CAPABILITY_ID_MC5 = 400;
    public static final int CAPABILITY_ID_PULSE = 401;
    public static final int CAPABILITY_ID_QDIAL = 402;
    public static final int CAPABILITY_ID_RMCLK = 403;
    public static final int CAPABILITY_ID_REP = 404;
    public static final int CAPABILITY_ID_RFI = 405;
    public static final int CAPABILITY_ID_REQMP = 406;
    public static final int CAPABILITY_ID_RS1 = 407;
    public static final int CAPABILITY_ID_RS2 = 408;
    public static final int CAPABILITY_ID_RS3 = 409;
    public static final int CAPABILITY_ID_RF = 410;
    public static final int CAPABILITY_ID_RC = 411;
    public static final int CAPABILITY_ID_VPA = 412;
    public static final int CAPABILITY_ID_SC = 413;
    public static final int CAPABILITY_ID_SCESC = 414;
    public static final int CAPABILITY_ID_IND = 415;
    public static final int CAPABILITY_ID_RI = 416;
    public static final int CAPABILITY_ID_SCS = 417;
    public static final int CAPABILITY_ID_S0DS = 418;
    public static final int CAPABILITY_ID_S1DS = 419;
    public static final int CAPABILITY_ID_S2DS = 420;
    public static final int CAPABILITY_ID_S3DS = 421;
    public static final int CAPABILITY_ID_SETAB = 422;
    public static final int CAPABILITY_ID_SETAF = 423;
    public static final int CAPABILITY_ID_SGR = 424;
    public static final int CAPABILITY_ID_SETB = 425;
    public static final int CAPABILITY_ID_SMGB = 426;
    public static final int CAPABILITY_ID_SMGBP = 427;
    public static final int CAPABILITY_ID_SCLK = 428;
    public static final int CAPABILITY_ID_SETCOLOR = 429;
    public static final int CAPABILITY_ID_SCP = 430;
    public static final int CAPABILITY_ID_SETF = 431;
    public static final int CAPABILITY_ID_SMGL = 432;
    public static final int CAPABILITY_ID_SMGLP = 433;
    public static final int CAPABILITY_ID_SMGLR = 434;
    public static final int CAPABILITY_ID_SLINES = 435;
    public static final int CAPABILITY_ID_SMGR = 436;
    public static final int CAPABILITY_ID_SMGRP = 437;
    public static final int CAPABILITY_ID_HTS = 438;
    public static final int CAPABILITY_ID_SMGTB = 439;
    public static final int CAPABILITY_ID_SMGT = 440;
    public static final int CAPABILITY_ID_SMGTP = 441;
    public static final int CAPABILITY_ID_WIND = 442;
    public static final int CAPABILITY_ID_SBIM = 443;
    public static final int CAPABILITY_ID_SCSD = 444;
    public static final int CAPABILITY_ID_RBIM = 445;
    public static final int CAPABILITY_ID_RCSD = 446;
    public static final int CAPABILITY_ID_SUBCS = 447;
    public static final int CAPABILITY_ID_SUPCS = 448;
    public static final int CAPABILITY_ID_HT = 449;
    public static final int CAPABILITY_ID_DOCR = 450;
    public static final int CAPABILITY_ID_TSL = 451;
    public static final int CAPABILITY_ID_TONE = 452;
    public static final int CAPABILITY_ID_U0 = 453;
    public static final int CAPABILITY_ID_U1 = 454;
    public static final int CAPABILITY_ID_U2 = 455;
    public static final int CAPABILITY_ID_U3 = 456;
    public static final int CAPABILITY_ID_U4 = 457;
    public static final int CAPABILITY_ID_U5 = 458;
    public static final int CAPABILITY_ID_U6 = 459;
    public static final int CAPABILITY_ID_U7 = 460;
    public static final int CAPABILITY_ID_U8 = 461;
    public static final int CAPABILITY_ID_U9 = 462;
    public static final int CAPABILITY_ID_UC = 463;
    public static final int CAPABILITY_ID_HU = 464;
    public static final int CAPABILITY_ID_WAIT = 465;
    public static final int CAPABILITY_ID_XOFFC = 466;
    public static final int CAPABILITY_ID_XONC = 467;
    public static final int CAPABILITY_ID_ZEROM = 468;

  /*
  static final CapIdRow[] capIdRows =
  {
						// boolean capabilities
	new CapIdRow("bw", CAPABILITY_ID_BW, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("am", CAPABILITY_ID_AM, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("bce", CAPABILITY_ID_BCE, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("xsb", CAPABILITY_ID_XSB, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("ccc", CAPABILITY_ID_CCC, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("xhp", CAPABILITY_ID_XHP, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("xhpa", CAPABILITY_ID_XHPA, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("cpix", CAPABILITY_ID_CPIX, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("crxm", CAPABILITY_ID_CRXM, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("xt", CAPABILITY_ID_XT, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("xenl", CAPABILITY_ID_XENL, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("eo", CAPABILITY_ID_EO, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("gn", CAPABILITY_ID_GN, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("hc", CAPABILITY_ID_HC, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("chts", CAPABILITY_ID_CHTS, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("km", CAPABILITY_ID_KM, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("hs", CAPABILITY_ID_HS, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("daisy", CAPABILITY_ID_DAISY, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("hls", CAPABILITY_ID_HLS, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("in", CAPABILITY_ID_IN, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("lpix", CAPABILITY_ID_LPIX, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("da", CAPABILITY_ID_DA, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("db", CAPABILITY_ID_DB, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("mir", CAPABILITY_ID_MIR, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("msgr", CAPABILITY_ID_MSGR, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("nxon", CAPABILITY_ID_NXON, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("npc", CAPABILITY_ID_NPC, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("ndscr", CAPABILITY_ID_NDSCR, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("nrrmc", CAPABILITY_ID_NRRMC, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("os", CAPABILITY_ID_OS, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("mc5i", CAPABILITY_ID_MC5I, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("xvpa", CAPABILITY_ID_XVPA, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("sam", CAPABILITY_ID_SAM, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("eslok", CAPABILITY_ID_ESLOK, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("hz", CAPABILITY_ID_HZ, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("ul", CAPABILITY_ID_UL, CAPABILITY_TYPE_BOOLEAN ),
	new CapIdRow("xon", CAPABILITY_ID_XON, CAPABILITY_TYPE_BOOLEAN ),
		// numeric capabilities
	new CapIdRow("bitwin", CAPABILITY_ID_BITWIN, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("bitype", CAPABILITY_ID_BITYPE, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("bufsz", CAPABILITY_ID_BUFSZ, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("btns", CAPABILITY_ID_BTNS, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("cols", CAPABILITY_ID_COLS, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("spinh", CAPABILITY_ID_SPINH, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("spinv", CAPABILITY_ID_SPINV, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("it", CAPABILITY_ID_IT, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("lh", CAPABILITY_ID_LH, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("lw", CAPABILITY_ID_LW, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("lines", CAPABILITY_ID_LINES, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("lm", CAPABILITY_ID_LM, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("ma", CAPABILITY_ID_MA, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("xmc", CAPABILITY_ID_XMC, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("colors", CAPABILITY_ID_COLORS, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("maddr", CAPABILITY_ID_MADDR, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("mjump", CAPABILITY_ID_MJUMP, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("pairs", CAPABILITY_ID_PAIRS, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("Wnum", CAPABILITY_ID_WNUM, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("mcs", CAPABILITY_ID_MCS, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("mls", CAPABILITY_ID_MLS, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("ncv", CAPABILITY_ID_NCV, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("nlab", CAPABILITY_ID_NLAB, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("npins", CAPABILITY_ID_NPINS, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("orc", CAPABILITY_ID_ORC, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("orl", CAPABILITY_ID_ORL, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("orhi", CAPABILITY_ID_ORHI, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("orvi", CAPABILITY_ID_ORVI, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("pb", CAPABILITY_ID_PB, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("cps", CAPABILITY_ID_CPS, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("vt", CAPABILITY_ID_VT, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("widcs", CAPABILITY_ID_WIDCS, CAPABILITY_TYPE_NUMERIC ),
	new CapIdRow("wsl", CAPABILITY_ID_WSL, CAPABILITY_TYPE_NUMERIC ),
		// string CAPABILITIES
	new CapIdRow("acsc", CAPABILITY_ID_ACSC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("scesa", CAPABILITY_ID_SCESA, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cbt", CAPABILITY_ID_CBT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("bel", CAPABILITY_ID_BEL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("bicr", CAPABILITY_ID_BICR, CAPABILITY_TYPE_STRING ),
	new CapIdRow("binel", CAPABILITY_ID_BINEL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("birep", CAPABILITY_ID_BIREP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cr", CAPABILITY_ID_CR, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cpi", CAPABILITY_ID_CPI, CAPABILITY_TYPE_STRING ),
	new CapIdRow("lpi", CAPABILITY_ID_LPI, CAPABILITY_TYPE_STRING ),
	new CapIdRow("chr", CAPABILITY_ID_CHR, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cvr", CAPABILITY_ID_CVR, CAPABILITY_TYPE_STRING ),
	new CapIdRow("csr", CAPABILITY_ID_CSR, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rmp", CAPABILITY_ID_RMP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("csnm", CAPABILITY_ID_CSNM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("tbc", CAPABILITY_ID_TBC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("mgc", CAPABILITY_ID_MGC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("clear", CAPABILITY_ID_CLEAR, CAPABILITY_TYPE_STRING ),
	new CapIdRow("el1", CAPABILITY_ID_EL1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("el", CAPABILITY_ID_EL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ed", CAPABILITY_ID_ED, CAPABILITY_TYPE_STRING ),
	new CapIdRow("csin", CAPABILITY_ID_CSIN, CAPABILITY_TYPE_STRING ),
	new CapIdRow("colornm", CAPABILITY_ID_COLORNM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("hpa", CAPABILITY_ID_HPA, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cmdch", CAPABILITY_ID_CMDCH, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cwin", CAPABILITY_ID_CWIN, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cup", CAPABILITY_ID_CUP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cud1", CAPABILITY_ID_CUD1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("home", CAPABILITY_ID_HOME, CAPABILITY_TYPE_STRING ),
	new CapIdRow("civis", CAPABILITY_ID_CIVIS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cub1", CAPABILITY_ID_CUB1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("mrcup", CAPABILITY_ID_MRCUP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cnorm", CAPABILITY_ID_CNORM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cuf1", CAPABILITY_ID_CUF1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ll", CAPABILITY_ID_LL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cuu1", CAPABILITY_ID_CUU1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cvvis", CAPABILITY_ID_CVVIS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("defbi", CAPABILITY_ID_DEFBI, CAPABILITY_TYPE_STRING ),
	new CapIdRow("defc", CAPABILITY_ID_DEFC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("dch1", CAPABILITY_ID_DCH1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("dl1", CAPABILITY_ID_DL1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("devt", CAPABILITY_ID_DEVT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("dial", CAPABILITY_ID_DIAL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("dsl", CAPABILITY_ID_DSL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("dclk", CAPABILITY_ID_DCLK, CAPABILITY_TYPE_STRING ),
	new CapIdRow("dispc", CAPABILITY_ID_DISPC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("hd", CAPABILITY_ID_HD, CAPABILITY_TYPE_STRING ),
	new CapIdRow("enacs", CAPABILITY_ID_ENACS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("endbi", CAPABILITY_ID_ENDBI, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smacs", CAPABILITY_ID_SMACS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smam", CAPABILITY_ID_SMAM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("blink", CAPABILITY_ID_BLINK, CAPABILITY_TYPE_STRING ),
	new CapIdRow("bold", CAPABILITY_ID_BOLD, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smcup", CAPABILITY_ID_SMCUP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smdc", CAPABILITY_ID_SMDC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("dim", CAPABILITY_ID_DIM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("swidm", CAPABILITY_ID_SWIDM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("sdrfq", CAPABILITY_ID_SDRFQ, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smir", CAPABILITY_ID_SMIR, CAPABILITY_TYPE_STRING ),
	new CapIdRow("sitm", CAPABILITY_ID_SITM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("slm", CAPABILITY_ID_SLM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smicm", CAPABILITY_ID_SMICM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("snlq", CAPABILITY_ID_SNLQ, CAPABILITY_TYPE_STRING ),
	new CapIdRow("snrmq", CAPABILITY_ID_SNRMQ, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smpch", CAPABILITY_ID_SMPCH, CAPABILITY_TYPE_STRING ),
	new CapIdRow("prot", CAPABILITY_ID_PROT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rev", CAPABILITY_ID_REV, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smsc", CAPABILITY_ID_SMSC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("invis", CAPABILITY_ID_INVIS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("sshm", CAPABILITY_ID_SSHM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smso", CAPABILITY_ID_SMSO, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ssubm", CAPABILITY_ID_SSUBM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ssupm", CAPABILITY_ID_SSUPM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smul", CAPABILITY_ID_SMUL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("sum", CAPABILITY_ID_SUM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smxon", CAPABILITY_ID_SMXON, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ech", CAPABILITY_ID_ECH, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rmacs", CAPABILITY_ID_RMACS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rmam", CAPABILITY_ID_RMAM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("sgr0", CAPABILITY_ID_SGR0, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rmcup", CAPABILITY_ID_RMCUP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rmdc", CAPABILITY_ID_RMDC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rwidm", CAPABILITY_ID_RWIDM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rmir", CAPABILITY_ID_RMIR, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ritm", CAPABILITY_ID_RITM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rlm", CAPABILITY_ID_RLM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rmicm", CAPABILITY_ID_RMICM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rmpch", CAPABILITY_ID_RMPCH, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rmsc", CAPABILITY_ID_RMSC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rshm", CAPABILITY_ID_RSHM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rmso", CAPABILITY_ID_RMSO, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rsubm", CAPABILITY_ID_RSUBM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rsupm", CAPABILITY_ID_RSUPM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rmul", CAPABILITY_ID_RMUL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rum", CAPABILITY_ID_RUM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rmxon", CAPABILITY_ID_RMXON, CAPABILITY_TYPE_STRING ),
	new CapIdRow("pause", CAPABILITY_ID_PAUSE, CAPABILITY_TYPE_STRING ),
	new CapIdRow("hook", CAPABILITY_ID_HOOK, CAPABILITY_TYPE_STRING ),
	new CapIdRow("flash", CAPABILITY_ID_FLASH, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ff", CAPABILITY_ID_FF, CAPABILITY_TYPE_STRING ),
	new CapIdRow("fsl", CAPABILITY_ID_FSL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("getm", CAPABILITY_ID_GETM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("wingo", CAPABILITY_ID_WINGO, CAPABILITY_TYPE_STRING ),
	new CapIdRow("hup", CAPABILITY_ID_HUP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("is1", CAPABILITY_ID_IS1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("is2", CAPABILITY_ID_IS2, CAPABILITY_TYPE_STRING ),
	new CapIdRow("is3", CAPABILITY_ID_IS3, CAPABILITY_TYPE_STRING ),
	new CapIdRow("if", CAPABILITY_ID_IF, CAPABILITY_TYPE_STRING ),
	new CapIdRow("iprog", CAPABILITY_ID_IPROG, CAPABILITY_TYPE_STRING ),
	new CapIdRow("initc", CAPABILITY_ID_INITC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("initp", CAPABILITY_ID_INITP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ich1", CAPABILITY_ID_ICH1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("il1", CAPABILITY_ID_IL1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ip", CAPABILITY_ID_IP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ka1", CAPABILITY_ID_KA1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ka3", CAPABILITY_ID_KA3, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kb2", CAPABILITY_ID_KB2, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kbs", CAPABILITY_ID_KBS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kbeg", CAPABILITY_ID_KBEG, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kcbt", CAPABILITY_ID_KCBT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kc1", CAPABILITY_ID_KC1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kc3", CAPABILITY_ID_KC3, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kcan", CAPABILITY_ID_KCAN, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ktbc", CAPABILITY_ID_KTBC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kclr", CAPABILITY_ID_KCLR, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kclo", CAPABILITY_ID_KCLO, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kcmd", CAPABILITY_ID_KCMD, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kcpy", CAPABILITY_ID_KCPY, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kcrt", CAPABILITY_ID_KCRT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kctab", CAPABILITY_ID_KCTAB, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kdch1", CAPABILITY_ID_KDCH1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kdl1", CAPABILITY_ID_KDL1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kcud1", CAPABILITY_ID_KCUD1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("krmir", CAPABILITY_ID_KRMIR, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kend", CAPABILITY_ID_KEND, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kent", CAPABILITY_ID_KENT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kel", CAPABILITY_ID_KEL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ked", CAPABILITY_ID_KED, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kext", CAPABILITY_ID_KEXT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf0", CAPABILITY_ID_KF0, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf1", CAPABILITY_ID_KF1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf2", CAPABILITY_ID_KF2, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf3", CAPABILITY_ID_KF3, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf4", CAPABILITY_ID_KF4, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf5", CAPABILITY_ID_KF5, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf6", CAPABILITY_ID_KF6, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf7", CAPABILITY_ID_KF7, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf8", CAPABILITY_ID_KF8, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf9", CAPABILITY_ID_KF9, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf10", CAPABILITY_ID_KF10, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf11", CAPABILITY_ID_KF11, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf12", CAPABILITY_ID_KF12, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf13", CAPABILITY_ID_KF13, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf14", CAPABILITY_ID_KF14, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf15", CAPABILITY_ID_KF15, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf16", CAPABILITY_ID_KF16, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf17", CAPABILITY_ID_KF17, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf18", CAPABILITY_ID_KF18, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf19", CAPABILITY_ID_KF19, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf20", CAPABILITY_ID_KF20, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf21", CAPABILITY_ID_KF21, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf22", CAPABILITY_ID_KF22, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf23", CAPABILITY_ID_KF23, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf24", CAPABILITY_ID_KF24, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf25", CAPABILITY_ID_KF25, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf26", CAPABILITY_ID_KF26, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf27", CAPABILITY_ID_KF27, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf28", CAPABILITY_ID_KF28, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf29", CAPABILITY_ID_KF29, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf30", CAPABILITY_ID_KF30, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf31", CAPABILITY_ID_KF31, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf32", CAPABILITY_ID_KF32, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf33", CAPABILITY_ID_KF33, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf34", CAPABILITY_ID_KF34, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf35", CAPABILITY_ID_KF35, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf36", CAPABILITY_ID_KF36, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf37", CAPABILITY_ID_KF37, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf38", CAPABILITY_ID_KF38, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf39", CAPABILITY_ID_KF39, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf40", CAPABILITY_ID_KF40, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf41", CAPABILITY_ID_KF41, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf42", CAPABILITY_ID_KF42, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf43", CAPABILITY_ID_KF43, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf44", CAPABILITY_ID_KF44, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf45", CAPABILITY_ID_KF45, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf46", CAPABILITY_ID_KF46, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf47", CAPABILITY_ID_KF47, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf48", CAPABILITY_ID_KF48, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf49", CAPABILITY_ID_KF49, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf50", CAPABILITY_ID_KF50, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf51", CAPABILITY_ID_KF51, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf52", CAPABILITY_ID_KF52, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf53", CAPABILITY_ID_KF53, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf54", CAPABILITY_ID_KF54, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf55", CAPABILITY_ID_KF55, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf56", CAPABILITY_ID_KF56, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf57", CAPABILITY_ID_KF57, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf58", CAPABILITY_ID_KF58, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf59", CAPABILITY_ID_KF59, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf60", CAPABILITY_ID_KF60, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf61", CAPABILITY_ID_KF61, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf62", CAPABILITY_ID_KF62, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kf63", CAPABILITY_ID_KF63, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kfnd", CAPABILITY_ID_KFND, CAPABILITY_TYPE_STRING ),
	new CapIdRow("khlp", CAPABILITY_ID_KHLP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("khome", CAPABILITY_ID_KHOME, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kich1", CAPABILITY_ID_KICH1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kil1", CAPABILITY_ID_KIL1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kcub1", CAPABILITY_ID_KCUB1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kll", CAPABILITY_ID_KLL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kmrk", CAPABILITY_ID_KMRK, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kmsg", CAPABILITY_ID_KMSG, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kmous", CAPABILITY_ID_KMOUS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kmov", CAPABILITY_ID_KMOV, CAPABILITY_TYPE_STRING ),
	new CapIdRow("knxt", CAPABILITY_ID_KNXT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("knp", CAPABILITY_ID_KNP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kopn", CAPABILITY_ID_KOPN, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kopt", CAPABILITY_ID_KOPT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kpp", CAPABILITY_ID_KPP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kprv", CAPABILITY_ID_KPRV, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kprt", CAPABILITY_ID_KPRT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("krdo", CAPABILITY_ID_KRDO, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kref", CAPABILITY_ID_KREF, CAPABILITY_TYPE_STRING ),
	new CapIdRow("krfr", CAPABILITY_ID_KRFR, CAPABILITY_TYPE_STRING ),
	new CapIdRow("krpl", CAPABILITY_ID_KRPL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("krst", CAPABILITY_ID_KRST, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kres", CAPABILITY_ID_KRES, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kcuf1", CAPABILITY_ID_KCUF1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ksav", CAPABILITY_ID_KSAV, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kBEG", CAPABILITY_ID_SKBEG, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kCAN", CAPABILITY_ID_SKCAN, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kCMD", CAPABILITY_ID_SKCMD, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kCPY", CAPABILITY_ID_SKCPY, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kCRT", CAPABILITY_ID_SKCRT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kDC", CAPABILITY_ID_SKDC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kDL", CAPABILITY_ID_SKDL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kslt", CAPABILITY_ID_KSLT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kEND", CAPABILITY_ID_SKEND, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kEOL", CAPABILITY_ID_SKEOL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kEXT", CAPABILITY_ID_SKEXT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kind", CAPABILITY_ID_KIND, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kFND", CAPABILITY_ID_SKFND, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kHLP", CAPABILITY_ID_SKHLP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kHOM", CAPABILITY_ID_SKHOM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kIC", CAPABILITY_ID_SKIC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kLFT", CAPABILITY_ID_SKLFT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kMSG", CAPABILITY_ID_SKMSG, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kMOV", CAPABILITY_ID_SKMOV, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kNXT", CAPABILITY_ID_SKNXT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kOPT", CAPABILITY_ID_SKOPT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kPRV", CAPABILITY_ID_SKPRV, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kPRT", CAPABILITY_ID_SKPRT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kri", CAPABILITY_ID_KRI, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kRDO", CAPABILITY_ID_SKRDO, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kRPL", CAPABILITY_ID_SKRPL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kRIT", CAPABILITY_ID_SKRIT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kRES", CAPABILITY_ID_SKRES, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kSAV", CAPABILITY_ID_SKSAV, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kSPD", CAPABILITY_ID_SKSPD, CAPABILITY_TYPE_STRING ),
	new CapIdRow("khts", CAPABILITY_ID_KHTS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kUND", CAPABILITY_ID_SKUND, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kspd", CAPABILITY_ID_KSPD, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kund", CAPABILITY_ID_KUND, CAPABILITY_TYPE_STRING ),
	new CapIdRow("kcuu1", CAPABILITY_ID_KCUU1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rmkx", CAPABILITY_ID_RMKX, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smkx", CAPABILITY_ID_SMKX, CAPABILITY_TYPE_STRING ),
	new CapIdRow("lf0", CAPABILITY_ID_LF0, CAPABILITY_TYPE_STRING ),
	new CapIdRow("lf1", CAPABILITY_ID_LF1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("lf2", CAPABILITY_ID_LF2, CAPABILITY_TYPE_STRING ),
	new CapIdRow("lf3", CAPABILITY_ID_LF3, CAPABILITY_TYPE_STRING ),
	new CapIdRow("lf4", CAPABILITY_ID_LF4, CAPABILITY_TYPE_STRING ),
	new CapIdRow("lf5", CAPABILITY_ID_LF5, CAPABILITY_TYPE_STRING ),
	new CapIdRow("lf6", CAPABILITY_ID_LF6, CAPABILITY_TYPE_STRING ),
	new CapIdRow("lf7", CAPABILITY_ID_LF7, CAPABILITY_TYPE_STRING ),
	new CapIdRow("lf8", CAPABILITY_ID_LF8, CAPABILITY_TYPE_STRING ),
	new CapIdRow("lf9", CAPABILITY_ID_LF9, CAPABILITY_TYPE_STRING ),
	new CapIdRow("lf10", CAPABILITY_ID_LF10, CAPABILITY_TYPE_STRING ),
	new CapIdRow("fln", CAPABILITY_ID_FLN, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rmln", CAPABILITY_ID_RMLN, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smln", CAPABILITY_ID_SMLN, CAPABILITY_TYPE_STRING ),
	new CapIdRow("meml", CAPABILITY_ID_MEML, CAPABILITY_TYPE_STRING ),
	new CapIdRow("memu", CAPABILITY_ID_MEMU, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smm", CAPABILITY_ID_SMM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("mhpa", CAPABILITY_ID_MHPA, CAPABILITY_TYPE_STRING ),
	new CapIdRow("mcud1", CAPABILITY_ID_MCUD1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("mcub1", CAPABILITY_ID_MCUB1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("mcuf1", CAPABILITY_ID_MCUF1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("mvpa", CAPABILITY_ID_MVPA, CAPABILITY_TYPE_STRING ),
	new CapIdRow("mcuu1", CAPABILITY_ID_MCUU1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("minfo", CAPABILITY_ID_MINFO, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rmm", CAPABILITY_ID_RMM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("nel", CAPABILITY_ID_NEL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("porder", CAPABILITY_ID_PORDER, CAPABILITY_TYPE_STRING ),
	new CapIdRow("oc", CAPABILITY_ID_OC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("op", CAPABILITY_ID_OP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("pad", CAPABILITY_ID_PAD, CAPABILITY_TYPE_STRING ),
	new CapIdRow("dch", CAPABILITY_ID_DCH, CAPABILITY_TYPE_STRING ),
	new CapIdRow("dl", CAPABILITY_ID_DL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cud", CAPABILITY_ID_CUD, CAPABILITY_TYPE_STRING ),
	new CapIdRow("mcud", CAPABILITY_ID_MCUD, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ich", CAPABILITY_ID_ICH, CAPABILITY_TYPE_STRING ),
	new CapIdRow("indn", CAPABILITY_ID_INDN, CAPABILITY_TYPE_STRING ),
	new CapIdRow("il", CAPABILITY_ID_IL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cub", CAPABILITY_ID_CUB, CAPABILITY_TYPE_STRING ),
	new CapIdRow("mcub", CAPABILITY_ID_MCUB, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cuf", CAPABILITY_ID_CUF, CAPABILITY_TYPE_STRING ),
	new CapIdRow("mcuf", CAPABILITY_ID_MCUF, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rin", CAPABILITY_ID_RIN, CAPABILITY_TYPE_STRING ),
	new CapIdRow("cuu", CAPABILITY_ID_CUU, CAPABILITY_TYPE_STRING ),
	new CapIdRow("mcuu", CAPABILITY_ID_MCUU, CAPABILITY_TYPE_STRING ),
	new CapIdRow("pctrm", CAPABILITY_ID_PCTRM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("pfkey", CAPABILITY_ID_PFKEY, CAPABILITY_TYPE_STRING ),
	new CapIdRow("pfloc", CAPABILITY_ID_PFLOC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("pfxl", CAPABILITY_ID_PFXL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("pfx", CAPABILITY_ID_PFX, CAPABILITY_TYPE_STRING ),
	new CapIdRow("pln", CAPABILITY_ID_PLN, CAPABILITY_TYPE_STRING ),
	new CapIdRow("mc0", CAPABILITY_ID_MC0, CAPABILITY_TYPE_STRING ),
	new CapIdRow("mc5p", CAPABILITY_ID_MC5P, CAPABILITY_TYPE_STRING ),
	new CapIdRow("mc4", CAPABILITY_ID_MC4, CAPABILITY_TYPE_STRING ),
	new CapIdRow("mc5", CAPABILITY_ID_MC5, CAPABILITY_TYPE_STRING ),
	new CapIdRow("pulse", CAPABILITY_ID_PULSE, CAPABILITY_TYPE_STRING ),
	new CapIdRow("qdial", CAPABILITY_ID_QDIAL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rmclk", CAPABILITY_ID_RMCLK, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rep", CAPABILITY_ID_REP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rfi", CAPABILITY_ID_RFI, CAPABILITY_TYPE_STRING ),
	new CapIdRow("reqmp", CAPABILITY_ID_REQMP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rs1", CAPABILITY_ID_RS1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rs2", CAPABILITY_ID_RS2, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rs3", CAPABILITY_ID_RS3, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rf", CAPABILITY_ID_RF, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rc", CAPABILITY_ID_RC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("vpa", CAPABILITY_ID_VPA, CAPABILITY_TYPE_STRING ),
	new CapIdRow("sc", CAPABILITY_ID_SC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("scesc", CAPABILITY_ID_SCESC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ind", CAPABILITY_ID_IND, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ri", CAPABILITY_ID_RI, CAPABILITY_TYPE_STRING ),
	new CapIdRow("scs", CAPABILITY_ID_SCS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("s0ds", CAPABILITY_ID_S0DS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("s1ds", CAPABILITY_ID_S1DS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("s2ds", CAPABILITY_ID_S2DS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("s3ds", CAPABILITY_ID_S3DS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("setab", CAPABILITY_ID_SETAB, CAPABILITY_TYPE_STRING ),
	new CapIdRow("setaf", CAPABILITY_ID_SETAF, CAPABILITY_TYPE_STRING ),
	new CapIdRow("sgr", CAPABILITY_ID_SGR, CAPABILITY_TYPE_STRING ),
	new CapIdRow("setb", CAPABILITY_ID_SETB, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smgb", CAPABILITY_ID_SMGB, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smgbp", CAPABILITY_ID_SMGBP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("sclk", CAPABILITY_ID_SCLK, CAPABILITY_TYPE_STRING ),
	new CapIdRow("setcolor", CAPABILITY_ID_SETCOLOR, CAPABILITY_TYPE_STRING ),
	new CapIdRow("scp", CAPABILITY_ID_SCP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("setf", CAPABILITY_ID_SETF, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smgl", CAPABILITY_ID_SMGL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smglp", CAPABILITY_ID_SMGLP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smglr", CAPABILITY_ID_SMGLR, CAPABILITY_TYPE_STRING ),
	new CapIdRow("slines", CAPABILITY_ID_SLINES, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smgr", CAPABILITY_ID_SMGR, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smgrp", CAPABILITY_ID_SMGRP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("hts", CAPABILITY_ID_HTS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smgtb", CAPABILITY_ID_SMGTB, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smgt", CAPABILITY_ID_SMGT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("smgtp", CAPABILITY_ID_SMGTP, CAPABILITY_TYPE_STRING ),
	new CapIdRow("wind", CAPABILITY_ID_WIND, CAPABILITY_TYPE_STRING ),
	new CapIdRow("sbim", CAPABILITY_ID_SBIM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("scsd", CAPABILITY_ID_SCSD, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rbim", CAPABILITY_ID_RBIM, CAPABILITY_TYPE_STRING ),
	new CapIdRow("rcsd", CAPABILITY_ID_RCSD, CAPABILITY_TYPE_STRING ),
	new CapIdRow("subcs", CAPABILITY_ID_SUBCS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("supcs", CAPABILITY_ID_SUPCS, CAPABILITY_TYPE_STRING ),
	new CapIdRow("ht", CAPABILITY_ID_HT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("docr", CAPABILITY_ID_DOCR, CAPABILITY_TYPE_STRING ),
	new CapIdRow("tsl", CAPABILITY_ID_TSL, CAPABILITY_TYPE_STRING ),
	new CapIdRow("tone", CAPABILITY_ID_TONE, CAPABILITY_TYPE_STRING ),
	new CapIdRow("u0", CAPABILITY_ID_U0, CAPABILITY_TYPE_STRING ),
	new CapIdRow("u1", CAPABILITY_ID_U1, CAPABILITY_TYPE_STRING ),
	new CapIdRow("u2", CAPABILITY_ID_U2, CAPABILITY_TYPE_STRING ),
	new CapIdRow("u3", CAPABILITY_ID_U3, CAPABILITY_TYPE_STRING ),
	new CapIdRow("u4", CAPABILITY_ID_U4, CAPABILITY_TYPE_STRING ),
	new CapIdRow("u5", CAPABILITY_ID_U5, CAPABILITY_TYPE_STRING ),
	new CapIdRow("u6", CAPABILITY_ID_U6, CAPABILITY_TYPE_STRING ),
	new CapIdRow("u7", CAPABILITY_ID_U7, CAPABILITY_TYPE_STRING ),
	new CapIdRow("u8", CAPABILITY_ID_U8, CAPABILITY_TYPE_STRING ),
	new CapIdRow("u9", CAPABILITY_ID_U9, CAPABILITY_TYPE_STRING ),
	new CapIdRow("uc", CAPABILITY_ID_UC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("hu", CAPABILITY_ID_HU, CAPABILITY_TYPE_STRING ),
	new CapIdRow("wait", CAPABILITY_ID_WAIT, CAPABILITY_TYPE_STRING ),
	new CapIdRow("xoffc", CAPABILITY_ID_XOFFC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("xonc", CAPABILITY_ID_XONC, CAPABILITY_TYPE_STRING ),
	new CapIdRow("zerom", CAPABILITY_ID_ZEROM, CAPABILITY_TYPE_STRING ),
  };
  */

    public TermInfoCaps() {
    }

    /**
     * Return the capability Id for a string
     */
    public static int getCapabilityIdForString(String cap) {
        return getCapabilityIdForString(cap, CAPABILITY_TYPE_UNKNOWN);
    }

    /**
     * Return the capability Id for a string
     */
    public static int getCapabilityIdForString(String cap, int type) {
	/*
	int	iMax = capIdRows.length;
	for (int i = 0 ; i < iMax; i++)
	{
		if ( ( type == CAPABILITY_TYPE_UNKNOWN || type == capIdRows[i].capType )
		&&	capIdRows[i].capStr.equals(cap))
			return capIdRows[i].capId;
	}
	return CAPABILITY_ID_UNKNOWN;
	*/
        // boolean capabilities
        if (type == CAPABILITY_TYPE_UNKNOWN
            || type == CAPABILITY_TYPE_BOOLEAN) {
            if (cap.equals("bw"))
                return CAPABILITY_ID_BW;
            else if (cap.equals("am"))
                return CAPABILITY_ID_AM;
            else if (cap.equals("bce"))
                return CAPABILITY_ID_BCE;
            else if (cap.equals("xsb"))
                return CAPABILITY_ID_XSB;
            else if (cap.equals("ccc"))
                return CAPABILITY_ID_CCC;
            else if (cap.equals("xhp"))
                return CAPABILITY_ID_XHP;
            else if (cap.equals("xhpa"))
                return CAPABILITY_ID_XHPA;
            else if (cap.equals("cpix"))
                return CAPABILITY_ID_CPIX;
            else if (cap.equals("crxm"))
                return CAPABILITY_ID_CRXM;
            else if (cap.equals("xt"))
                return CAPABILITY_ID_XT;
            else if (cap.equals("xenl"))
                return CAPABILITY_ID_XENL;
            else if (cap.equals("eo"))
                return CAPABILITY_ID_EO;
            else if (cap.equals("gn"))
                return CAPABILITY_ID_GN;
            else if (cap.equals("hc"))
                return CAPABILITY_ID_HC;
            else if (cap.equals("chts"))
                return CAPABILITY_ID_CHTS;
            else if (cap.equals("km"))
                return CAPABILITY_ID_KM;
            else if (cap.equals("hs"))
                return CAPABILITY_ID_HS;
            else if (cap.equals("daisy"))
                return CAPABILITY_ID_DAISY;
            else if (cap.equals("hls"))
                return CAPABILITY_ID_HLS;
            else if (cap.equals("in"))
                return CAPABILITY_ID_IN;
            else if (cap.equals("lpix"))
                return CAPABILITY_ID_LPIX;
            else if (cap.equals("da"))
                return CAPABILITY_ID_DA;
            else if (cap.equals("db"))
                return CAPABILITY_ID_DB;
            else if (cap.equals("mir"))
                return CAPABILITY_ID_MIR;
            else if (cap.equals("msgr"))
                return CAPABILITY_ID_MSGR;
            else if (cap.equals("nxon"))
                return CAPABILITY_ID_NXON;
            else if (cap.equals("npc"))
                return CAPABILITY_ID_NPC;
            else if (cap.equals("ndscr"))
                return CAPABILITY_ID_NDSCR;
            else if (cap.equals("nrrmc"))
                return CAPABILITY_ID_NRRMC;
            else if (cap.equals("os"))
                return CAPABILITY_ID_OS;
            else if (cap.equals("mc5i"))
                return CAPABILITY_ID_MC5I;
            else if (cap.equals("xvpa"))
                return CAPABILITY_ID_XVPA;
            else if (cap.equals("sam"))
                return CAPABILITY_ID_SAM;
            else if (cap.equals("eslok"))
                return CAPABILITY_ID_ESLOK;
            else if (cap.equals("hz"))
                return CAPABILITY_ID_HZ;
            else if (cap.equals("ul"))
                return CAPABILITY_ID_UL;
            else if (cap.equals("xon"))
                return CAPABILITY_ID_XON;
            else
                return CAPABILITY_ID_UNKNOWN;
        }
        // numeric capabilities
        if (type == CAPABILITY_TYPE_UNKNOWN
            || type == CAPABILITY_TYPE_NUMERIC) {
            if (cap.equals("bitwin"))
                return CAPABILITY_ID_BITWIN;
            else if (cap.equals("bitype"))
                return CAPABILITY_ID_BITYPE;
            else if (cap.equals("bufsz"))
                return CAPABILITY_ID_BUFSZ;
            else if (cap.equals("btns"))
                return CAPABILITY_ID_BTNS;
            else if (cap.equals("cols"))
                return CAPABILITY_ID_COLS;
            else if (cap.equals("spinh"))
                return CAPABILITY_ID_SPINH;
            else if (cap.equals("spinv"))
                return CAPABILITY_ID_SPINV;
            else if (cap.equals("it"))
                return CAPABILITY_ID_IT;
            else if (cap.equals("lh"))
                return CAPABILITY_ID_LH;
            else if (cap.equals("lw"))
                return CAPABILITY_ID_LW;
            else if (cap.equals("lines"))
                return CAPABILITY_ID_LINES;
            else if (cap.equals("lm"))
                return CAPABILITY_ID_LM;
            else if (cap.equals("ma"))
                return CAPABILITY_ID_MA;
            else if (cap.equals("xmc"))
                return CAPABILITY_ID_XMC;
            else if (cap.equals("colors"))
                return CAPABILITY_ID_COLORS;
            else if (cap.equals("maddr"))
                return CAPABILITY_ID_MADDR;
            else if (cap.equals("mjump"))
                return CAPABILITY_ID_MJUMP;
            else if (cap.equals("pairs"))
                return CAPABILITY_ID_PAIRS;
            else if (cap.equals("Wnum"))
                return CAPABILITY_ID_WNUM;
            else if (cap.equals("mcs"))
                return CAPABILITY_ID_MCS;
            else if (cap.equals("mls"))
                return CAPABILITY_ID_MLS;
            else if (cap.equals("ncv"))
                return CAPABILITY_ID_NCV;
            else if (cap.equals("nlab"))
                return CAPABILITY_ID_NLAB;
            else if (cap.equals("npins"))
                return CAPABILITY_ID_NPINS;
            else if (cap.equals("orc"))
                return CAPABILITY_ID_ORC;
            else if (cap.equals("orl"))
                return CAPABILITY_ID_ORL;
            else if (cap.equals("orhi"))
                return CAPABILITY_ID_ORHI;
            else if (cap.equals("orvi"))
                return CAPABILITY_ID_ORVI;
            else if (cap.equals("pb"))
                return CAPABILITY_ID_PB;
            else if (cap.equals("cps"))
                return CAPABILITY_ID_CPS;
            else if (cap.equals("vt"))
                return CAPABILITY_ID_VT;
            else if (cap.equals("widcs"))
                return CAPABILITY_ID_WIDCS;
            else if (cap.equals("wsl"))
                return CAPABILITY_ID_WSL;
            else
                return CAPABILITY_ID_UNKNOWN;
        }
        // string CAPABILITIES
        if (type == CAPABILITY_TYPE_UNKNOWN
            || type == CAPABILITY_TYPE_STRING) {
            if (cap.equals("acsc"))
                return CAPABILITY_ID_ACSC;
            else if (cap.equals("scesa"))
                return CAPABILITY_ID_SCESA;
            else if (cap.equals("cbt"))
                return CAPABILITY_ID_CBT;
            else if (cap.equals("bel"))
                return CAPABILITY_ID_BEL;
            else if (cap.equals("bicr"))
                return CAPABILITY_ID_BICR;
            else if (cap.equals("binel"))
                return CAPABILITY_ID_BINEL;
            else if (cap.equals("birep"))
                return CAPABILITY_ID_BIREP;
            else if (cap.equals("cr"))
                return CAPABILITY_ID_CR;
            else if (cap.equals("cpi"))
                return CAPABILITY_ID_CPI;
            else if (cap.equals("lpi"))
                return CAPABILITY_ID_LPI;
            else if (cap.equals("chr"))
                return CAPABILITY_ID_CHR;
            else if (cap.equals("cvr"))
                return CAPABILITY_ID_CVR;
            else if (cap.equals("csr"))
                return CAPABILITY_ID_CSR;
            else if (cap.equals("rmp"))
                return CAPABILITY_ID_RMP;
            else if (cap.equals("csnm"))
                return CAPABILITY_ID_CSNM;
            else if (cap.equals("tbc"))
                return CAPABILITY_ID_TBC;
            else if (cap.equals("mgc"))
                return CAPABILITY_ID_MGC;
            else if (cap.equals("clear"))
                return CAPABILITY_ID_CLEAR;
            else if (cap.equals("el1"))
                return CAPABILITY_ID_EL1;
            else if (cap.equals("el"))
                return CAPABILITY_ID_EL;
            else if (cap.equals("ed"))
                return CAPABILITY_ID_ED;
            else if (cap.equals("csin"))
                return CAPABILITY_ID_CSIN;
            else if (cap.equals("colornm"))
                return CAPABILITY_ID_COLORNM;
            else if (cap.equals("hpa"))
                return CAPABILITY_ID_HPA;
            else if (cap.equals("cmdch"))
                return CAPABILITY_ID_CMDCH;
            else if (cap.equals("cwin"))
                return CAPABILITY_ID_CWIN;
            else if (cap.equals("cup"))
                return CAPABILITY_ID_CUP;
            else if (cap.equals("cud1"))
                return CAPABILITY_ID_CUD1;
            else if (cap.equals("home"))
                return CAPABILITY_ID_HOME;
            else if (cap.equals("civis"))
                return CAPABILITY_ID_CIVIS;
            else if (cap.equals("cub1"))
                return CAPABILITY_ID_CUB1;
            else if (cap.equals("mrcup"))
                return CAPABILITY_ID_MRCUP;
            else if (cap.equals("cnorm"))
                return CAPABILITY_ID_CNORM;
            else if (cap.equals("cuf1"))
                return CAPABILITY_ID_CUF1;
            else if (cap.equals("ll"))
                return CAPABILITY_ID_LL;
            else if (cap.equals("cuu1"))
                return CAPABILITY_ID_CUU1;
            else if (cap.equals("cvvis"))
                return CAPABILITY_ID_CVVIS;
            else if (cap.equals("defbi"))
                return CAPABILITY_ID_DEFBI;
            else if (cap.equals("defc"))
                return CAPABILITY_ID_DEFC;
            else if (cap.equals("dch1"))
                return CAPABILITY_ID_DCH1;
            else if (cap.equals("dl1"))
                return CAPABILITY_ID_DL1;
            else if (cap.equals("devt"))
                return CAPABILITY_ID_DEVT;
            else if (cap.equals("dial"))
                return CAPABILITY_ID_DIAL;
            else if (cap.equals("dsl"))
                return CAPABILITY_ID_DSL;
            else if (cap.equals("dclk"))
                return CAPABILITY_ID_DCLK;
            else if (cap.equals("dispc"))
                return CAPABILITY_ID_DISPC;
            else if (cap.equals("hd"))
                return CAPABILITY_ID_HD;
            else if (cap.equals("enacs"))
                return CAPABILITY_ID_ENACS;
            else if (cap.equals("endbi"))
                return CAPABILITY_ID_ENDBI;
            else if (cap.equals("smacs"))
                return CAPABILITY_ID_SMACS;
            else if (cap.equals("smam"))
                return CAPABILITY_ID_SMAM;
            else if (cap.equals("blink"))
                return CAPABILITY_ID_BLINK;
            else if (cap.equals("bold"))
                return CAPABILITY_ID_BOLD;
            else if (cap.equals("smcup"))
                return CAPABILITY_ID_SMCUP;
            else if (cap.equals("smdc"))
                return CAPABILITY_ID_SMDC;
            else if (cap.equals("dim"))
                return CAPABILITY_ID_DIM;
            else if (cap.equals("swidm"))
                return CAPABILITY_ID_SWIDM;
            else if (cap.equals("sdrfq"))
                return CAPABILITY_ID_SDRFQ;
            else if (cap.equals("smir"))
                return CAPABILITY_ID_SMIR;
            else if (cap.equals("sitm"))
                return CAPABILITY_ID_SITM;
            else if (cap.equals("slm"))
                return CAPABILITY_ID_SLM;
            else if (cap.equals("smicm"))
                return CAPABILITY_ID_SMICM;
            else if (cap.equals("snlq"))
                return CAPABILITY_ID_SNLQ;
            else if (cap.equals("snrmq"))
                return CAPABILITY_ID_SNRMQ;
            else if (cap.equals("smpch"))
                return CAPABILITY_ID_SMPCH;
            else if (cap.equals("prot"))
                return CAPABILITY_ID_PROT;
            else if (cap.equals("rev"))
                return CAPABILITY_ID_REV;
            else if (cap.equals("smsc"))
                return CAPABILITY_ID_SMSC;
            else if (cap.equals("invis"))
                return CAPABILITY_ID_INVIS;
            else if (cap.equals("sshm"))
                return CAPABILITY_ID_SSHM;
            else if (cap.equals("smso"))
                return CAPABILITY_ID_SMSO;
            else if (cap.equals("ssubm"))
                return CAPABILITY_ID_SSUBM;
            else if (cap.equals("ssupm"))
                return CAPABILITY_ID_SSUPM;
            else if (cap.equals("smul"))
                return CAPABILITY_ID_SMUL;
            else if (cap.equals("sum"))
                return CAPABILITY_ID_SUM;
            else if (cap.equals("smxon"))
                return CAPABILITY_ID_SMXON;
            else if (cap.equals("ech"))
                return CAPABILITY_ID_ECH;
            else if (cap.equals("rmacs"))
                return CAPABILITY_ID_RMACS;
            else if (cap.equals("rmam"))
                return CAPABILITY_ID_RMAM;
            else if (cap.equals("sgr0"))
                return CAPABILITY_ID_SGR0;
            else if (cap.equals("rmcup"))
                return CAPABILITY_ID_RMCUP;
            else if (cap.equals("rmdc"))
                return CAPABILITY_ID_RMDC;
            else if (cap.equals("rwidm"))
                return CAPABILITY_ID_RWIDM;
            else if (cap.equals("rmir"))
                return CAPABILITY_ID_RMIR;
            else if (cap.equals("ritm"))
                return CAPABILITY_ID_RITM;
            else if (cap.equals("rlm"))
                return CAPABILITY_ID_RLM;
            else if (cap.equals("rmicm"))
                return CAPABILITY_ID_RMICM;
            else if (cap.equals("rmpch"))
                return CAPABILITY_ID_RMPCH;
            else if (cap.equals("rmsc"))
                return CAPABILITY_ID_RMSC;
            else if (cap.equals("rshm"))
                return CAPABILITY_ID_RSHM;
            else if (cap.equals("rmso"))
                return CAPABILITY_ID_RMSO;
            else if (cap.equals("rsubm"))
                return CAPABILITY_ID_RSUBM;
            else if (cap.equals("rsupm"))
                return CAPABILITY_ID_RSUPM;
            else if (cap.equals("rmul"))
                return CAPABILITY_ID_RMUL;
            else if (cap.equals("rum"))
                return CAPABILITY_ID_RUM;
            else if (cap.equals("rmxon"))
                return CAPABILITY_ID_RMXON;
            else if (cap.equals("pause"))
                return CAPABILITY_ID_PAUSE;
            else if (cap.equals("hook"))
                return CAPABILITY_ID_HOOK;
            else if (cap.equals("flash"))
                return CAPABILITY_ID_FLASH;
            else if (cap.equals("ff"))
                return CAPABILITY_ID_FF;
            else if (cap.equals("fsl"))
                return CAPABILITY_ID_FSL;
            else if (cap.equals("getm"))
                return CAPABILITY_ID_GETM;
            else if (cap.equals("wingo"))
                return CAPABILITY_ID_WINGO;
            else if (cap.equals("hup"))
                return CAPABILITY_ID_HUP;
            else if (cap.equals("is1"))
                return CAPABILITY_ID_IS1;
            else if (cap.equals("is2"))
                return CAPABILITY_ID_IS2;
            else if (cap.equals("is3"))
                return CAPABILITY_ID_IS3;
            else if (cap.equals("if"))
                return CAPABILITY_ID_IF;
            else if (cap.equals("iprog"))
                return CAPABILITY_ID_IPROG;
            else if (cap.equals("initc"))
                return CAPABILITY_ID_INITC;
            else if (cap.equals("initp"))
                return CAPABILITY_ID_INITP;
            else if (cap.equals("ich1"))
                return CAPABILITY_ID_ICH1;
            else if (cap.equals("il1"))
                return CAPABILITY_ID_IL1;
            else if (cap.equals("ip"))
                return CAPABILITY_ID_IP;
            else if (cap.equals("ka1"))
                return CAPABILITY_ID_KA1;
            else if (cap.equals("ka3"))
                return CAPABILITY_ID_KA3;
            else if (cap.equals("kb2"))
                return CAPABILITY_ID_KB2;
            else if (cap.equals("kbs"))
                return CAPABILITY_ID_KBS;
            else if (cap.equals("kbeg"))
                return CAPABILITY_ID_KBEG;
            else if (cap.equals("kcbt"))
                return CAPABILITY_ID_KCBT;
            else if (cap.equals("kc1"))
                return CAPABILITY_ID_KC1;
            else if (cap.equals("kc3"))
                return CAPABILITY_ID_KC3;
            else if (cap.equals("kcan"))
                return CAPABILITY_ID_KCAN;
            else if (cap.equals("ktbc"))
                return CAPABILITY_ID_KTBC;
            else if (cap.equals("kclr"))
                return CAPABILITY_ID_KCLR;
            else if (cap.equals("kclo"))
                return CAPABILITY_ID_KCLO;
            else if (cap.equals("kcmd"))
                return CAPABILITY_ID_KCMD;
            else if (cap.equals("kcpy"))
                return CAPABILITY_ID_KCPY;
            else if (cap.equals("kcrt"))
                return CAPABILITY_ID_KCRT;
            else if (cap.equals("kctab"))
                return CAPABILITY_ID_KCTAB;
            else if (cap.equals("kdch1"))
                return CAPABILITY_ID_KDCH1;
            else if (cap.equals("kdl1"))
                return CAPABILITY_ID_KDL1;
            else if (cap.equals("kcud1"))
                return CAPABILITY_ID_KCUD1;
            else if (cap.equals("krmir"))
                return CAPABILITY_ID_KRMIR;
            else if (cap.equals("kend"))
                return CAPABILITY_ID_KEND;
            else if (cap.equals("kent"))
                return CAPABILITY_ID_KENT;
            else if (cap.equals("kel"))
                return CAPABILITY_ID_KEL;
            else if (cap.equals("ked"))
                return CAPABILITY_ID_KED;
            else if (cap.equals("kext"))
                return CAPABILITY_ID_KEXT;
            else if (cap.equals("kf0"))
                return CAPABILITY_ID_KF0;
            else if (cap.equals("kf1"))
                return CAPABILITY_ID_KF1;
            else if (cap.equals("kf2"))
                return CAPABILITY_ID_KF2;
            else if (cap.equals("kf3"))
                return CAPABILITY_ID_KF3;
            else if (cap.equals("kf4"))
                return CAPABILITY_ID_KF4;
            else if (cap.equals("kf5"))
                return CAPABILITY_ID_KF5;
            else if (cap.equals("kf6"))
                return CAPABILITY_ID_KF6;
            else if (cap.equals("kf7"))
                return CAPABILITY_ID_KF7;
            else if (cap.equals("kf8"))
                return CAPABILITY_ID_KF8;
            else if (cap.equals("kf9"))
                return CAPABILITY_ID_KF9;
            else if (cap.equals("kf10"))
                return CAPABILITY_ID_KF10;
            else if (cap.equals("kf11"))
                return CAPABILITY_ID_KF11;
            else if (cap.equals("kf12"))
                return CAPABILITY_ID_KF12;
            else if (cap.equals("kf13"))
                return CAPABILITY_ID_KF13;
            else if (cap.equals("kf14"))
                return CAPABILITY_ID_KF14;
            else if (cap.equals("kf15"))
                return CAPABILITY_ID_KF15;
            else if (cap.equals("kf16"))
                return CAPABILITY_ID_KF16;
            else if (cap.equals("kf17"))
                return CAPABILITY_ID_KF17;
            else if (cap.equals("kf18"))
                return CAPABILITY_ID_KF18;
            else if (cap.equals("kf19"))
                return CAPABILITY_ID_KF19;
            else if (cap.equals("kf20"))
                return CAPABILITY_ID_KF20;
            else if (cap.equals("kf21"))
                return CAPABILITY_ID_KF21;
            else if (cap.equals("kf22"))
                return CAPABILITY_ID_KF22;
            else if (cap.equals("kf23"))
                return CAPABILITY_ID_KF23;
            else if (cap.equals("kf24"))
                return CAPABILITY_ID_KF24;
            else if (cap.equals("kf25"))
                return CAPABILITY_ID_KF25;
            else if (cap.equals("kf26"))
                return CAPABILITY_ID_KF26;
            else if (cap.equals("kf27"))
                return CAPABILITY_ID_KF27;
            else if (cap.equals("kf28"))
                return CAPABILITY_ID_KF28;
            else if (cap.equals("kf29"))
                return CAPABILITY_ID_KF29;
            else if (cap.equals("kf30"))
                return CAPABILITY_ID_KF30;
            else if (cap.equals("kf31"))
                return CAPABILITY_ID_KF31;
            else if (cap.equals("kf32"))
                return CAPABILITY_ID_KF32;
            else if (cap.equals("kf33"))
                return CAPABILITY_ID_KF33;
            else if (cap.equals("kf34"))
                return CAPABILITY_ID_KF34;
            else if (cap.equals("kf35"))
                return CAPABILITY_ID_KF35;
            else if (cap.equals("kf36"))
                return CAPABILITY_ID_KF36;
            else if (cap.equals("kf37"))
                return CAPABILITY_ID_KF37;
            else if (cap.equals("kf38"))
                return CAPABILITY_ID_KF38;
            else if (cap.equals("kf39"))
                return CAPABILITY_ID_KF39;
            else if (cap.equals("kf40"))
                return CAPABILITY_ID_KF40;
            else if (cap.equals("kf41"))
                return CAPABILITY_ID_KF41;
            else if (cap.equals("kf42"))
                return CAPABILITY_ID_KF42;
            else if (cap.equals("kf43"))
                return CAPABILITY_ID_KF43;
            else if (cap.equals("kf44"))
                return CAPABILITY_ID_KF44;
            else if (cap.equals("kf45"))
                return CAPABILITY_ID_KF45;
            else if (cap.equals("kf46"))
                return CAPABILITY_ID_KF46;
            else if (cap.equals("kf47"))
                return CAPABILITY_ID_KF47;
            else if (cap.equals("kf48"))
                return CAPABILITY_ID_KF48;
            else if (cap.equals("kf49"))
                return CAPABILITY_ID_KF49;
            else if (cap.equals("kf50"))
                return CAPABILITY_ID_KF50;
            else if (cap.equals("kf51"))
                return CAPABILITY_ID_KF51;
            else if (cap.equals("kf52"))
                return CAPABILITY_ID_KF52;
            else if (cap.equals("kf53"))
                return CAPABILITY_ID_KF53;
            else if (cap.equals("kf54"))
                return CAPABILITY_ID_KF54;
            else if (cap.equals("kf55"))
                return CAPABILITY_ID_KF55;
            else if (cap.equals("kf56"))
                return CAPABILITY_ID_KF56;
            else if (cap.equals("kf57"))
                return CAPABILITY_ID_KF57;
            else if (cap.equals("kf58"))
                return CAPABILITY_ID_KF58;
            else if (cap.equals("kf59"))
                return CAPABILITY_ID_KF59;
            else if (cap.equals("kf60"))
                return CAPABILITY_ID_KF60;
            else if (cap.equals("kf61"))
                return CAPABILITY_ID_KF61;
            else if (cap.equals("kf62"))
                return CAPABILITY_ID_KF62;
            else if (cap.equals("kf63"))
                return CAPABILITY_ID_KF63;
            else if (cap.equals("kfnd"))
                return CAPABILITY_ID_KFND;
            else if (cap.equals("khlp"))
                return CAPABILITY_ID_KHLP;
            else if (cap.equals("khome"))
                return CAPABILITY_ID_KHOME;
            else if (cap.equals("kich1"))
                return CAPABILITY_ID_KICH1;
            else if (cap.equals("kil1"))
                return CAPABILITY_ID_KIL1;
            else if (cap.equals("kcub1"))
                return CAPABILITY_ID_KCUB1;
            else if (cap.equals("kll"))
                return CAPABILITY_ID_KLL;
            else if (cap.equals("kmrk"))
                return CAPABILITY_ID_KMRK;
            else if (cap.equals("kmsg"))
                return CAPABILITY_ID_KMSG;
            else if (cap.equals("kmous"))
                return CAPABILITY_ID_KMOUS;
            else if (cap.equals("kmov"))
                return CAPABILITY_ID_KMOV;
            else if (cap.equals("knxt"))
                return CAPABILITY_ID_KNXT;
            else if (cap.equals("knp"))
                return CAPABILITY_ID_KNP;
            else if (cap.equals("kopn"))
                return CAPABILITY_ID_KOPN;
            else if (cap.equals("kopt"))
                return CAPABILITY_ID_KOPT;
            else if (cap.equals("kpp"))
                return CAPABILITY_ID_KPP;
            else if (cap.equals("kprv"))
                return CAPABILITY_ID_KPRV;
            else if (cap.equals("kprt"))
                return CAPABILITY_ID_KPRT;
            else if (cap.equals("krdo"))
                return CAPABILITY_ID_KRDO;
            else if (cap.equals("kref"))
                return CAPABILITY_ID_KREF;
            else if (cap.equals("krfr"))
                return CAPABILITY_ID_KRFR;
            else if (cap.equals("krpl"))
                return CAPABILITY_ID_KRPL;
            else if (cap.equals("krst"))
                return CAPABILITY_ID_KRST;
            else if (cap.equals("kres"))
                return CAPABILITY_ID_KRES;
            else if (cap.equals("kcuf1"))
                return CAPABILITY_ID_KCUF1;
            else if (cap.equals("ksav"))
                return CAPABILITY_ID_KSAV;
            else if (cap.equals("kBEG"))
                return CAPABILITY_ID_SKBEG;
            else if (cap.equals("kCAN"))
                return CAPABILITY_ID_SKCAN;
            else if (cap.equals("kCMD"))
                return CAPABILITY_ID_SKCMD;
            else if (cap.equals("kCPY"))
                return CAPABILITY_ID_SKCPY;
            else if (cap.equals("kCRT"))
                return CAPABILITY_ID_SKCRT;
            else if (cap.equals("kDC"))
                return CAPABILITY_ID_SKDC;
            else if (cap.equals("kDL"))
                return CAPABILITY_ID_SKDL;
            else if (cap.equals("kslt"))
                return CAPABILITY_ID_KSLT;
            else if (cap.equals("kEND"))
                return CAPABILITY_ID_SKEND;
            else if (cap.equals("kEOL"))
                return CAPABILITY_ID_SKEOL;
            else if (cap.equals("kEXT"))
                return CAPABILITY_ID_SKEXT;
            else if (cap.equals("kind"))
                return CAPABILITY_ID_KIND;
            else if (cap.equals("kFND"))
                return CAPABILITY_ID_SKFND;
            else if (cap.equals("kHLP"))
                return CAPABILITY_ID_SKHLP;
            else if (cap.equals("kHOM"))
                return CAPABILITY_ID_SKHOM;
            else if (cap.equals("kIC"))
                return CAPABILITY_ID_SKIC;
            else if (cap.equals("kLFT"))
                return CAPABILITY_ID_SKLFT;
            else if (cap.equals("kMSG"))
                return CAPABILITY_ID_SKMSG;
            else if (cap.equals("kMOV"))
                return CAPABILITY_ID_SKMOV;
            else if (cap.equals("kNXT"))
                return CAPABILITY_ID_SKNXT;
            else if (cap.equals("kOPT"))
                return CAPABILITY_ID_SKOPT;
            else if (cap.equals("kPRV"))
                return CAPABILITY_ID_SKPRV;
            else if (cap.equals("kPRT"))
                return CAPABILITY_ID_SKPRT;
            else if (cap.equals("kri"))
                return CAPABILITY_ID_KRI;
            else if (cap.equals("kRDO"))
                return CAPABILITY_ID_SKRDO;
            else if (cap.equals("kRPL"))
                return CAPABILITY_ID_SKRPL;
            else if (cap.equals("kRIT"))
                return CAPABILITY_ID_SKRIT;
            else if (cap.equals("kRES"))
                return CAPABILITY_ID_SKRES;
            else if (cap.equals("kSAV"))
                return CAPABILITY_ID_SKSAV;
            else if (cap.equals("kSPD"))
                return CAPABILITY_ID_SKSPD;
            else if (cap.equals("khts"))
                return CAPABILITY_ID_KHTS;
            else if (cap.equals("kUND"))
                return CAPABILITY_ID_SKUND;
            else if (cap.equals("kspd"))
                return CAPABILITY_ID_KSPD;
            else if (cap.equals("kund"))
                return CAPABILITY_ID_KUND;
            else if (cap.equals("kcuu1"))
                return CAPABILITY_ID_KCUU1;
            else if (cap.equals("rmkx"))
                return CAPABILITY_ID_RMKX;
            else if (cap.equals("smkx"))
                return CAPABILITY_ID_SMKX;
            else if (cap.equals("lf0"))
                return CAPABILITY_ID_LF0;
            else if (cap.equals("lf1"))
                return CAPABILITY_ID_LF1;
            else if (cap.equals("lf2"))
                return CAPABILITY_ID_LF2;
            else if (cap.equals("lf3"))
                return CAPABILITY_ID_LF3;
            else if (cap.equals("lf4"))
                return CAPABILITY_ID_LF4;
            else if (cap.equals("lf5"))
                return CAPABILITY_ID_LF5;
            else if (cap.equals("lf6"))
                return CAPABILITY_ID_LF6;
            else if (cap.equals("lf7"))
                return CAPABILITY_ID_LF7;
            else if (cap.equals("lf8"))
                return CAPABILITY_ID_LF8;
            else if (cap.equals("lf9"))
                return CAPABILITY_ID_LF9;
            else if (cap.equals("lf10"))
                return CAPABILITY_ID_LF10;
            else if (cap.equals("fln"))
                return CAPABILITY_ID_FLN;
            else if (cap.equals("rmln"))
                return CAPABILITY_ID_RMLN;
            else if (cap.equals("smln"))
                return CAPABILITY_ID_SMLN;
            else if (cap.equals("meml"))
                return CAPABILITY_ID_MEML;
            else if (cap.equals("memu"))
                return CAPABILITY_ID_MEMU;
            else if (cap.equals("smm"))
                return CAPABILITY_ID_SMM;
            else if (cap.equals("mhpa"))
                return CAPABILITY_ID_MHPA;
            else if (cap.equals("mcud1"))
                return CAPABILITY_ID_MCUD1;
            else if (cap.equals("mcub1"))
                return CAPABILITY_ID_MCUB1;
            else if (cap.equals("mcuf1"))
                return CAPABILITY_ID_MCUF1;
            else if (cap.equals("mvpa"))
                return CAPABILITY_ID_MVPA;
            else if (cap.equals("mcuu1"))
                return CAPABILITY_ID_MCUU1;
            else if (cap.equals("minfo"))
                return CAPABILITY_ID_MINFO;
            else if (cap.equals("rmm"))
                return CAPABILITY_ID_RMM;
            else if (cap.equals("nel"))
                return CAPABILITY_ID_NEL;
            else if (cap.equals("porder"))
                return CAPABILITY_ID_PORDER;
            else if (cap.equals("oc"))
                return CAPABILITY_ID_OC;
            else if (cap.equals("op"))
                return CAPABILITY_ID_OP;
            else if (cap.equals("pad"))
                return CAPABILITY_ID_PAD;
            else if (cap.equals("dch"))
                return CAPABILITY_ID_DCH;
            else if (cap.equals("dl"))
                return CAPABILITY_ID_DL;
            else if (cap.equals("cud"))
                return CAPABILITY_ID_CUD;
            else if (cap.equals("mcud"))
                return CAPABILITY_ID_MCUD;
            else if (cap.equals("ich"))
                return CAPABILITY_ID_ICH;
            else if (cap.equals("indn"))
                return CAPABILITY_ID_INDN;
            else if (cap.equals("il"))
                return CAPABILITY_ID_IL;
            else if (cap.equals("cub"))
                return CAPABILITY_ID_CUB;
            else if (cap.equals("mcub"))
                return CAPABILITY_ID_MCUB;
            else if (cap.equals("cuf"))
                return CAPABILITY_ID_CUF;
            else if (cap.equals("mcuf"))
                return CAPABILITY_ID_MCUF;
            else if (cap.equals("rin"))
                return CAPABILITY_ID_RIN;
            else if (cap.equals("cuu"))
                return CAPABILITY_ID_CUU;
            else if (cap.equals("mcuu"))
                return CAPABILITY_ID_MCUU;
            else if (cap.equals("pctrm"))
                return CAPABILITY_ID_PCTRM;
            else if (cap.equals("pfkey"))
                return CAPABILITY_ID_PFKEY;
            else if (cap.equals("pfloc"))
                return CAPABILITY_ID_PFLOC;
            else if (cap.equals("pfxl"))
                return CAPABILITY_ID_PFXL;
            else if (cap.equals("pfx"))
                return CAPABILITY_ID_PFX;
            else if (cap.equals("pln"))
                return CAPABILITY_ID_PLN;
            else if (cap.equals("mc0"))
                return CAPABILITY_ID_MC0;
            else if (cap.equals("mc5p"))
                return CAPABILITY_ID_MC5P;
            else if (cap.equals("mc4"))
                return CAPABILITY_ID_MC4;
            else if (cap.equals("mc5"))
                return CAPABILITY_ID_MC5;
            else if (cap.equals("pulse"))
                return CAPABILITY_ID_PULSE;
            else if (cap.equals("qdial"))
                return CAPABILITY_ID_QDIAL;
            else if (cap.equals("rmclk"))
                return CAPABILITY_ID_RMCLK;
            else if (cap.equals("rep"))
                return CAPABILITY_ID_REP;
            else if (cap.equals("rfi"))
                return CAPABILITY_ID_RFI;
            else if (cap.equals("reqmp"))
                return CAPABILITY_ID_REQMP;
            else if (cap.equals("rs1"))
                return CAPABILITY_ID_RS1;
            else if (cap.equals("rs2"))
                return CAPABILITY_ID_RS2;
            else if (cap.equals("rs3"))
                return CAPABILITY_ID_RS3;
            else if (cap.equals("rf"))
                return CAPABILITY_ID_RF;
            else if (cap.equals("rc"))
                return CAPABILITY_ID_RC;
            else if (cap.equals("vpa"))
                return CAPABILITY_ID_VPA;
            else if (cap.equals("sc"))
                return CAPABILITY_ID_SC;
            else if (cap.equals("scesc"))
                return CAPABILITY_ID_SCESC;
            else if (cap.equals("ind"))
                return CAPABILITY_ID_IND;
            else if (cap.equals("ri"))
                return CAPABILITY_ID_RI;
            else if (cap.equals("scs"))
                return CAPABILITY_ID_SCS;
            else if (cap.equals("s0ds"))
                return CAPABILITY_ID_S0DS;
            else if (cap.equals("s1ds"))
                return CAPABILITY_ID_S1DS;
            else if (cap.equals("s2ds"))
                return CAPABILITY_ID_S2DS;
            else if (cap.equals("s3ds"))
                return CAPABILITY_ID_S3DS;
            else if (cap.equals("setab"))
                return CAPABILITY_ID_SETAB;
            else if (cap.equals("setaf"))
                return CAPABILITY_ID_SETAF;
            else if (cap.equals("sgr"))
                return CAPABILITY_ID_SGR;
            else if (cap.equals("setb"))
                return CAPABILITY_ID_SETB;
            else if (cap.equals("smgb"))
                return CAPABILITY_ID_SMGB;
            else if (cap.equals("smgbp"))
                return CAPABILITY_ID_SMGBP;
            else if (cap.equals("sclk"))
                return CAPABILITY_ID_SCLK;
            else if (cap.equals("setcolor"))
                return CAPABILITY_ID_SETCOLOR;
            else if (cap.equals("scp"))
                return CAPABILITY_ID_SCP;
            else if (cap.equals("setf"))
                return CAPABILITY_ID_SETF;
            else if (cap.equals("smgl"))
                return CAPABILITY_ID_SMGL;
            else if (cap.equals("smglp"))
                return CAPABILITY_ID_SMGLP;
            else if (cap.equals("smglr"))
                return CAPABILITY_ID_SMGLR;
            else if (cap.equals("slines"))
                return CAPABILITY_ID_SLINES;
            else if (cap.equals("smgr"))
                return CAPABILITY_ID_SMGR;
            else if (cap.equals("smgrp"))
                return CAPABILITY_ID_SMGRP;
            else if (cap.equals("hts"))
                return CAPABILITY_ID_HTS;
            else if (cap.equals("smgtb"))
                return CAPABILITY_ID_SMGTB;
            else if (cap.equals("smgt"))
                return CAPABILITY_ID_SMGT;
            else if (cap.equals("smgtp"))
                return CAPABILITY_ID_SMGTP;
            else if (cap.equals("wind"))
                return CAPABILITY_ID_WIND;
            else if (cap.equals("sbim"))
                return CAPABILITY_ID_SBIM;
            else if (cap.equals("scsd"))
                return CAPABILITY_ID_SCSD;
            else if (cap.equals("rbim"))
                return CAPABILITY_ID_RBIM;
            else if (cap.equals("rcsd"))
                return CAPABILITY_ID_RCSD;
            else if (cap.equals("subcs"))
                return CAPABILITY_ID_SUBCS;
            else if (cap.equals("supcs"))
                return CAPABILITY_ID_SUPCS;
            else if (cap.equals("ht"))
                return CAPABILITY_ID_HT;
            else if (cap.equals("docr"))
                return CAPABILITY_ID_DOCR;
            else if (cap.equals("tsl"))
                return CAPABILITY_ID_TSL;
            else if (cap.equals("tone"))
                return CAPABILITY_ID_TONE;
            else if (cap.equals("u0"))
                return CAPABILITY_ID_U0;
            else if (cap.equals("u1"))
                return CAPABILITY_ID_U1;
            else if (cap.equals("u2"))
                return CAPABILITY_ID_U2;
            else if (cap.equals("u3"))
                return CAPABILITY_ID_U3;
            else if (cap.equals("u4"))
                return CAPABILITY_ID_U4;
            else if (cap.equals("u5"))
                return CAPABILITY_ID_U5;
            else if (cap.equals("u6"))
                return CAPABILITY_ID_U6;
            else if (cap.equals("u7"))
                return CAPABILITY_ID_U7;
            else if (cap.equals("u8"))
                return CAPABILITY_ID_U8;
            else if (cap.equals("u9"))
                return CAPABILITY_ID_U9;
            else if (cap.equals("uc"))
                return CAPABILITY_ID_UC;
            else if (cap.equals("hu"))
                return CAPABILITY_ID_HU;
            else if (cap.equals("wait"))
                return CAPABILITY_ID_WAIT;
            else if (cap.equals("xoffc"))
                return CAPABILITY_ID_XOFFC;
            else if (cap.equals("xonc"))
                return CAPABILITY_ID_XONC;
            else if (cap.equals("zerom"))
                return CAPABILITY_ID_ZEROM;
            else
                return CAPABILITY_ID_UNKNOWN;
        }
        return CAPABILITY_ID_UNKNOWN;
    }

    public static String getCapabilityStringForId(int id) {
	/*
	int	iMax = capIdRows.length;
	for (int i = 0 ; i < iMax; i++)
	{
		if ( id == capIdRows[i].capId )
			return capIdRows[i].capStr;
	}
	*/
        switch (id) {
            case CAPABILITY_ID_BW:
                return "bw";
            case CAPABILITY_ID_AM:
                return "am";
            case CAPABILITY_ID_BCE:
                return "bce";
            case CAPABILITY_ID_XSB:
                return "xsb";
            case CAPABILITY_ID_CCC:
                return "ccc";
            case CAPABILITY_ID_XHP:
                return "xhp";
            case CAPABILITY_ID_XHPA:
                return "xhpa";
            case CAPABILITY_ID_CPIX:
                return "cpix";
            case CAPABILITY_ID_CRXM:
                return "crxm";
            case CAPABILITY_ID_XT:
                return "xt";
            case CAPABILITY_ID_XENL:
                return "xenl";
            case CAPABILITY_ID_EO:
                return "eo";
            case CAPABILITY_ID_GN:
                return "gn";
            case CAPABILITY_ID_HC:
                return "hc";
            case CAPABILITY_ID_CHTS:
                return "chts";
            case CAPABILITY_ID_KM:
                return "km";
            case CAPABILITY_ID_HS:
                return "hs";
            case CAPABILITY_ID_DAISY:
                return "daisy";
            case CAPABILITY_ID_HLS:
                return "hls";
            case CAPABILITY_ID_IN:
                return "in";
            case CAPABILITY_ID_LPIX:
                return "lpix";
            case CAPABILITY_ID_DA:
                return "da";
            case CAPABILITY_ID_DB:
                return "db";
            case CAPABILITY_ID_MIR:
                return "mir";
            case CAPABILITY_ID_MSGR:
                return "msgr";
            case CAPABILITY_ID_NXON:
                return "nxon";
            case CAPABILITY_ID_NPC:
                return "npc";
            case CAPABILITY_ID_NDSCR:
                return "ndscr";
            case CAPABILITY_ID_NRRMC:
                return "nrrmc";
            case CAPABILITY_ID_OS:
                return "os";
            case CAPABILITY_ID_MC5I:
                return "mc5i";
            case CAPABILITY_ID_XVPA:
                return "xvpa";
            case CAPABILITY_ID_SAM:
                return "sam";
            case CAPABILITY_ID_ESLOK:
                return "eslok";
            case CAPABILITY_ID_HZ:
                return "hz";
            case CAPABILITY_ID_UL:
                return "ul";
            case CAPABILITY_ID_XON:
                return "xon";

            // numeric capabilities
            case CAPABILITY_ID_BITWIN:
                return "bitwin";
            case CAPABILITY_ID_BITYPE:
                return "bitype";
            case CAPABILITY_ID_BUFSZ:
                return "bufsz";
            case CAPABILITY_ID_BTNS:
                return "btns";
            case CAPABILITY_ID_COLS:
                return "cols";
            case CAPABILITY_ID_SPINH:
                return "spinh";
            case CAPABILITY_ID_SPINV:
                return "spinv";
            case CAPABILITY_ID_IT:
                return "it";
            case CAPABILITY_ID_LH:
                return "lh";
            case CAPABILITY_ID_LW:
                return "lw";
            case CAPABILITY_ID_LINES:
                return "lines";
            case CAPABILITY_ID_LM:
                return "lm";
            case CAPABILITY_ID_MA:
                return "ma";
            case CAPABILITY_ID_XMC:
                return "xmc";
            case CAPABILITY_ID_COLORS:
                return "colors";
            case CAPABILITY_ID_MADDR:
                return "maddr";
            case CAPABILITY_ID_MJUMP:
                return "mjump";
            case CAPABILITY_ID_PAIRS:
                return "pairs";
            case CAPABILITY_ID_WNUM:
                return "Wnum";
            case CAPABILITY_ID_MCS:
                return "mcs";
            case CAPABILITY_ID_MLS:
                return "mls";
            case CAPABILITY_ID_NCV:
                return "ncv";
            case CAPABILITY_ID_NLAB:
                return "nlab";
            case CAPABILITY_ID_NPINS:
                return "npins";
            case CAPABILITY_ID_ORC:
                return "orc";
            case CAPABILITY_ID_ORL:
                return "orl";
            case CAPABILITY_ID_ORHI:
                return "orhi";
            case CAPABILITY_ID_ORVI:
                return "orvi";
            case CAPABILITY_ID_PB:
                return "pb";
            case CAPABILITY_ID_CPS:
                return "cps";
            case CAPABILITY_ID_VT:
                return "vt";
            case CAPABILITY_ID_WIDCS:
                return "widcs";
            case CAPABILITY_ID_WSL:
                return "wsl";

            // string CAPABILITIES
            case CAPABILITY_ID_ACSC:
                return "acsc";
            case CAPABILITY_ID_SCESA:
                return "scesa";
            case CAPABILITY_ID_CBT:
                return "cbt";
            case CAPABILITY_ID_BEL:
                return "bel";
            case CAPABILITY_ID_BICR:
                return "bicr";
            case CAPABILITY_ID_BINEL:
                return "binel";
            case CAPABILITY_ID_BIREP:
                return "birep";
            case CAPABILITY_ID_CR:
                return "cr";
            case CAPABILITY_ID_CPI:
                return "cpi";
            case CAPABILITY_ID_LPI:
                return "lpi";
            case CAPABILITY_ID_CHR:
                return "chr";
            case CAPABILITY_ID_CVR:
                return "cvr";
            case CAPABILITY_ID_CSR:
                return "csr";
            case CAPABILITY_ID_RMP:
                return "rmp";
            case CAPABILITY_ID_CSNM:
                return "csnm";
            case CAPABILITY_ID_TBC:
                return "tbc";
            case CAPABILITY_ID_MGC:
                return "mgc";
            case CAPABILITY_ID_CLEAR:
                return "clear";
            case CAPABILITY_ID_EL1:
                return "el1";
            case CAPABILITY_ID_EL:
                return "el";
            case CAPABILITY_ID_ED:
                return "ed";
            case CAPABILITY_ID_CSIN:
                return "csin";
            case CAPABILITY_ID_COLORNM:
                return "colornm";
            case CAPABILITY_ID_HPA:
                return "hpa";
            case CAPABILITY_ID_CMDCH:
                return "cmdch";
            case CAPABILITY_ID_CWIN:
                return "cwin";
            case CAPABILITY_ID_CUP:
                return "cup";
            case CAPABILITY_ID_CUD1:
                return "cud1";
            case CAPABILITY_ID_HOME:
                return "home";
            case CAPABILITY_ID_CIVIS:
                return "civis";
            case CAPABILITY_ID_CUB1:
                return "cub1";
            case CAPABILITY_ID_MRCUP:
                return "mrcup";
            case CAPABILITY_ID_CNORM:
                return "cnorm";
            case CAPABILITY_ID_CUF1:
                return "cuf1";
            case CAPABILITY_ID_LL:
                return "ll";
            case CAPABILITY_ID_CUU1:
                return "cuu1";
            case CAPABILITY_ID_CVVIS:
                return "cvvis";
            case CAPABILITY_ID_DEFBI:
                return "defbi";
            case CAPABILITY_ID_DEFC:
                return "defc";
            case CAPABILITY_ID_DCH1:
                return "dch1";
            case CAPABILITY_ID_DL1:
                return "dl1";
            case CAPABILITY_ID_DEVT:
                return "devt";
            case CAPABILITY_ID_DIAL:
                return "dial";
            case CAPABILITY_ID_DSL:
                return "dsl";
            case CAPABILITY_ID_DCLK:
                return "dclk";
            case CAPABILITY_ID_DISPC:
                return "dispc";
            case CAPABILITY_ID_HD:
                return "hd";
            case CAPABILITY_ID_ENACS:
                return "enacs";
            case CAPABILITY_ID_ENDBI:
                return "endbi";
            case CAPABILITY_ID_SMACS:
                return "smacs";
            case CAPABILITY_ID_SMAM:
                return "smam";
            case CAPABILITY_ID_BLINK:
                return "blink";
            case CAPABILITY_ID_BOLD:
                return "bold";
            case CAPABILITY_ID_SMCUP:
                return "smcup";
            case CAPABILITY_ID_SMDC:
                return "smdc";
            case CAPABILITY_ID_DIM:
                return "dim";
            case CAPABILITY_ID_SWIDM:
                return "swidm";
            case CAPABILITY_ID_SDRFQ:
                return "sdrfq";
            case CAPABILITY_ID_SMIR:
                return "smir";
            case CAPABILITY_ID_SITM:
                return "sitm";
            case CAPABILITY_ID_SLM:
                return "slm";
            case CAPABILITY_ID_SMICM:
                return "smicm";
            case CAPABILITY_ID_SNLQ:
                return "snlq";
            case CAPABILITY_ID_SNRMQ:
                return "snrmq";
            case CAPABILITY_ID_SMPCH:
                return "smpch";
            case CAPABILITY_ID_PROT:
                return "prot";
            case CAPABILITY_ID_REV:
                return "rev";
            case CAPABILITY_ID_SMSC:
                return "smsc";
            case CAPABILITY_ID_INVIS:
                return "invis";
            case CAPABILITY_ID_SSHM:
                return "sshm";
            case CAPABILITY_ID_SMSO:
                return "smso";
            case CAPABILITY_ID_SSUBM:
                return "ssubm";
            case CAPABILITY_ID_SSUPM:
                return "ssupm";
            case CAPABILITY_ID_SMUL:
                return "smul";
            case CAPABILITY_ID_SUM:
                return "sum";
            case CAPABILITY_ID_SMXON:
                return "smxon";
            case CAPABILITY_ID_ECH:
                return "ech";
            case CAPABILITY_ID_RMACS:
                return "rmacs";
            case CAPABILITY_ID_RMAM:
                return "rmam";
            case CAPABILITY_ID_SGR0:
                return "sgr0";
            case CAPABILITY_ID_RMCUP:
                return "rmcup";
            case CAPABILITY_ID_RMDC:
                return "rmdc";
            case CAPABILITY_ID_RWIDM:
                return "rwidm";
            case CAPABILITY_ID_RMIR:
                return "rmir";
            case CAPABILITY_ID_RITM:
                return "ritm";
            case CAPABILITY_ID_RLM:
                return "rlm";
            case CAPABILITY_ID_RMICM:
                return "rmicm";
            case CAPABILITY_ID_RMPCH:
                return "rmpch";
            case CAPABILITY_ID_RMSC:
                return "rmsc";
            case CAPABILITY_ID_RSHM:
                return "rshm";
            case CAPABILITY_ID_RMSO:
                return "rmso";
            case CAPABILITY_ID_RSUBM:
                return "rsubm";
            case CAPABILITY_ID_RSUPM:
                return "rsupm";
            case CAPABILITY_ID_RMUL:
                return "rmul";
            case CAPABILITY_ID_RUM:
                return "rum";
            case CAPABILITY_ID_RMXON:
                return "rmxon";
            case CAPABILITY_ID_PAUSE:
                return "pause";
            case CAPABILITY_ID_HOOK:
                return "hook";
            case CAPABILITY_ID_FLASH:
                return "flash";
            case CAPABILITY_ID_FF:
                return "ff";
            case CAPABILITY_ID_FSL:
                return "fsl";
            case CAPABILITY_ID_GETM:
                return "getm";
            case CAPABILITY_ID_WINGO:
                return "wingo";
            case CAPABILITY_ID_HUP:
                return "hup";
            case CAPABILITY_ID_IS1:
                return "is1";
            case CAPABILITY_ID_IS2:
                return "is2";
            case CAPABILITY_ID_IS3:
                return "is3";
            case CAPABILITY_ID_IF:
                return "if";
            case CAPABILITY_ID_IPROG:
                return "iprog";
            case CAPABILITY_ID_INITC:
                return "initc";
            case CAPABILITY_ID_INITP:
                return "initp";
            case CAPABILITY_ID_ICH1:
                return "ich1";
            case CAPABILITY_ID_IL1:
                return "il1";
            case CAPABILITY_ID_IP:
                return "ip";
            case CAPABILITY_ID_KA1:
                return "ka1";
            case CAPABILITY_ID_KA3:
                return "ka3";
            case CAPABILITY_ID_KB2:
                return "kb2";
            case CAPABILITY_ID_KBS:
                return "kbs";
            case CAPABILITY_ID_KBEG:
                return "kbeg";
            case CAPABILITY_ID_KCBT:
                return "kcbt";
            case CAPABILITY_ID_KC1:
                return "kc1";
            case CAPABILITY_ID_KC3:
                return "kc3";
            case CAPABILITY_ID_KCAN:
                return "kcan";
            case CAPABILITY_ID_KTBC:
                return "ktbc";
            case CAPABILITY_ID_KCLR:
                return "kclr";
            case CAPABILITY_ID_KCLO:
                return "kclo";
            case CAPABILITY_ID_KCMD:
                return "kcmd";
            case CAPABILITY_ID_KCPY:
                return "kcpy";
            case CAPABILITY_ID_KCRT:
                return "kcrt";
            case CAPABILITY_ID_KCTAB:
                return "kctab";
            case CAPABILITY_ID_KDCH1:
                return "kdch1";
            case CAPABILITY_ID_KDL1:
                return "kdl1";
            case CAPABILITY_ID_KCUD1:
                return "kcud1";
            case CAPABILITY_ID_KRMIR:
                return "krmir";
            case CAPABILITY_ID_KEND:
                return "kend";
            case CAPABILITY_ID_KENT:
                return "kent";
            case CAPABILITY_ID_KEL:
                return "kel";
            case CAPABILITY_ID_KED:
                return "ked";
            case CAPABILITY_ID_KEXT:
                return "kext";
            case CAPABILITY_ID_KF0:
                return "kf0";
            case CAPABILITY_ID_KF1:
                return "kf1";
            case CAPABILITY_ID_KF2:
                return "kf2";
            case CAPABILITY_ID_KF3:
                return "kf3";
            case CAPABILITY_ID_KF4:
                return "kf4";
            case CAPABILITY_ID_KF5:
                return "kf5";
            case CAPABILITY_ID_KF6:
                return "kf6";
            case CAPABILITY_ID_KF7:
                return "kf7";
            case CAPABILITY_ID_KF8:
                return "kf8";
            case CAPABILITY_ID_KF9:
                return "kf9";
            case CAPABILITY_ID_KF10:
                return "kf10";
            case CAPABILITY_ID_KF11:
                return "kf11";
            case CAPABILITY_ID_KF12:
                return "kf12";
            case CAPABILITY_ID_KF13:
                return "kf13";
            case CAPABILITY_ID_KF14:
                return "kf14";
            case CAPABILITY_ID_KF15:
                return "kf15";
            case CAPABILITY_ID_KF16:
                return "kf16";
            case CAPABILITY_ID_KF17:
                return "kf17";
            case CAPABILITY_ID_KF18:
                return "kf18";
            case CAPABILITY_ID_KF19:
                return "kf19";
            case CAPABILITY_ID_KF20:
                return "kf20";
            case CAPABILITY_ID_KF21:
                return "kf21";
            case CAPABILITY_ID_KF22:
                return "kf22";
            case CAPABILITY_ID_KF23:
                return "kf23";
            case CAPABILITY_ID_KF24:
                return "kf24";
            case CAPABILITY_ID_KF25:
                return "kf25";
            case CAPABILITY_ID_KF26:
                return "kf26";
            case CAPABILITY_ID_KF27:
                return "kf27";
            case CAPABILITY_ID_KF28:
                return "kf28";
            case CAPABILITY_ID_KF29:
                return "kf29";
            case CAPABILITY_ID_KF30:
                return "kf30";
            case CAPABILITY_ID_KF31:
                return "kf31";
            case CAPABILITY_ID_KF32:
                return "kf32";
            case CAPABILITY_ID_KF33:
                return "kf33";
            case CAPABILITY_ID_KF34:
                return "kf34";
            case CAPABILITY_ID_KF35:
                return "kf35";
            case CAPABILITY_ID_KF36:
                return "kf36";
            case CAPABILITY_ID_KF37:
                return "kf37";
            case CAPABILITY_ID_KF38:
                return "kf38";
            case CAPABILITY_ID_KF39:
                return "kf39";
            case CAPABILITY_ID_KF40:
                return "kf40";
            case CAPABILITY_ID_KF41:
                return "kf41";
            case CAPABILITY_ID_KF42:
                return "kf42";
            case CAPABILITY_ID_KF43:
                return "kf43";
            case CAPABILITY_ID_KF44:
                return "kf44";
            case CAPABILITY_ID_KF45:
                return "kf45";
            case CAPABILITY_ID_KF46:
                return "kf46";
            case CAPABILITY_ID_KF47:
                return "kf47";
            case CAPABILITY_ID_KF48:
                return "kf48";
            case CAPABILITY_ID_KF49:
                return "kf49";
            case CAPABILITY_ID_KF50:
                return "kf50";
            case CAPABILITY_ID_KF51:
                return "kf51";
            case CAPABILITY_ID_KF52:
                return "kf52";
            case CAPABILITY_ID_KF53:
                return "kf53";
            case CAPABILITY_ID_KF54:
                return "kf54";
            case CAPABILITY_ID_KF55:
                return "kf55";
            case CAPABILITY_ID_KF56:
                return "kf56";
            case CAPABILITY_ID_KF57:
                return "kf57";
            case CAPABILITY_ID_KF58:
                return "kf58";
            case CAPABILITY_ID_KF59:
                return "kf59";
            case CAPABILITY_ID_KF60:
                return "kf60";
            case CAPABILITY_ID_KF61:
                return "kf61";
            case CAPABILITY_ID_KF62:
                return "kf62";
            case CAPABILITY_ID_KF63:
                return "kf63";
            case CAPABILITY_ID_KFND:
                return "kfnd";
            case CAPABILITY_ID_KHLP:
                return "khlp";
            case CAPABILITY_ID_KHOME:
                return "khome";
            case CAPABILITY_ID_KICH1:
                return "kich1";
            case CAPABILITY_ID_KIL1:
                return "kil1";
            case CAPABILITY_ID_KCUB1:
                return "kcub1";
            case CAPABILITY_ID_KLL:
                return "kll";
            case CAPABILITY_ID_KMRK:
                return "kmrk";
            case CAPABILITY_ID_KMSG:
                return "kmsg";
            case CAPABILITY_ID_KMOUS:
                return "kmous";
            case CAPABILITY_ID_KMOV:
                return "kmov";
            case CAPABILITY_ID_KNXT:
                return "knxt";
            case CAPABILITY_ID_KNP:
                return "knp";
            case CAPABILITY_ID_KOPN:
                return "kopn";
            case CAPABILITY_ID_KOPT:
                return "kopt";
            case CAPABILITY_ID_KPP:
                return "kpp";
            case CAPABILITY_ID_KPRV:
                return "kprv";
            case CAPABILITY_ID_KPRT:
                return "kprt";
            case CAPABILITY_ID_KRDO:
                return "krdo";
            case CAPABILITY_ID_KREF:
                return "kref";
            case CAPABILITY_ID_KRFR:
                return "krfr";
            case CAPABILITY_ID_KRPL:
                return "krpl";
            case CAPABILITY_ID_KRST:
                return "krst";
            case CAPABILITY_ID_KRES:
                return "kres";
            case CAPABILITY_ID_KCUF1:
                return "kcuf1";
            case CAPABILITY_ID_KSAV:
                return "ksav";
            case CAPABILITY_ID_SKBEG:
                return "kBEG";
            case CAPABILITY_ID_SKCAN:
                return "kCAN";
            case CAPABILITY_ID_SKCMD:
                return "kCMD";
            case CAPABILITY_ID_SKCPY:
                return "kCPY";
            case CAPABILITY_ID_SKCRT:
                return "kCRT";
            case CAPABILITY_ID_SKDC:
                return "kDC";
            case CAPABILITY_ID_SKDL:
                return "kDL";
            case CAPABILITY_ID_KSLT:
                return "kslt";
            case CAPABILITY_ID_SKEND:
                return "kEND";
            case CAPABILITY_ID_SKEOL:
                return "kEOL";
            case CAPABILITY_ID_SKEXT:
                return "kEXT";
            case CAPABILITY_ID_KIND:
                return "kind";
            case CAPABILITY_ID_SKFND:
                return "kFND";
            case CAPABILITY_ID_SKHLP:
                return "kHLP";
            case CAPABILITY_ID_SKHOM:
                return "kHOM";
            case CAPABILITY_ID_SKIC:
                return "kIC";
            case CAPABILITY_ID_SKLFT:
                return "kLFT";
            case CAPABILITY_ID_SKMSG:
                return "kMSG";
            case CAPABILITY_ID_SKMOV:
                return "kMOV";
            case CAPABILITY_ID_SKNXT:
                return "kNXT";
            case CAPABILITY_ID_SKOPT:
                return "kOPT";
            case CAPABILITY_ID_SKPRV:
                return "kPRV";
            case CAPABILITY_ID_SKPRT:
                return "kPRT";
            case CAPABILITY_ID_KRI:
                return "kri";
            case CAPABILITY_ID_SKRDO:
                return "kRDO";
            case CAPABILITY_ID_SKRPL:
                return "kRPL";
            case CAPABILITY_ID_SKRIT:
                return "kRIT";
            case CAPABILITY_ID_SKRES:
                return "kRES";
            case CAPABILITY_ID_SKSAV:
                return "kSAV";
            case CAPABILITY_ID_SKSPD:
                return "kSPD";
            case CAPABILITY_ID_KHTS:
                return "khts";
            case CAPABILITY_ID_SKUND:
                return "kUND";
            case CAPABILITY_ID_KSPD:
                return "kspd";
            case CAPABILITY_ID_KUND:
                return "kund";
            case CAPABILITY_ID_KCUU1:
                return "kcuu1";
            case CAPABILITY_ID_RMKX:
                return "rmkx";
            case CAPABILITY_ID_SMKX:
                return "smkx";
            case CAPABILITY_ID_LF0:
                return "lf0";
            case CAPABILITY_ID_LF1:
                return "lf1";
            case CAPABILITY_ID_LF2:
                return "lf2";
            case CAPABILITY_ID_LF3:
                return "lf3";
            case CAPABILITY_ID_LF4:
                return "lf4";
            case CAPABILITY_ID_LF5:
                return "lf5";
            case CAPABILITY_ID_LF6:
                return "lf6";
            case CAPABILITY_ID_LF7:
                return "lf7";
            case CAPABILITY_ID_LF8:
                return "lf8";
            case CAPABILITY_ID_LF9:
                return "lf9";
            case CAPABILITY_ID_LF10:
                return "lf10";
            case CAPABILITY_ID_FLN:
                return "fln";
            case CAPABILITY_ID_RMLN:
                return "rmln";
            case CAPABILITY_ID_SMLN:
                return "smln";
            case CAPABILITY_ID_MEML:
                return "meml";
            case CAPABILITY_ID_MEMU:
                return "memu";
            case CAPABILITY_ID_SMM:
                return "smm";
            case CAPABILITY_ID_MHPA:
                return "mhpa";
            case CAPABILITY_ID_MCUD1:
                return "mcud1";
            case CAPABILITY_ID_MCUB1:
                return "mcub1";
            case CAPABILITY_ID_MCUF1:
                return "mcuf1";
            case CAPABILITY_ID_MVPA:
                return "mvpa";
            case CAPABILITY_ID_MCUU1:
                return "mcuu1";
            case CAPABILITY_ID_MINFO:
                return "minfo";
            case CAPABILITY_ID_RMM:
                return "rmm";
            case CAPABILITY_ID_NEL:
                return "nel";
            case CAPABILITY_ID_PORDER:
                return "porder";
            case CAPABILITY_ID_OC:
                return "oc";
            case CAPABILITY_ID_OP:
                return "op";
            case CAPABILITY_ID_PAD:
                return "pad";
            case CAPABILITY_ID_DCH:
                return "dch";
            case CAPABILITY_ID_DL:
                return "dl";
            case CAPABILITY_ID_CUD:
                return "cud";
            case CAPABILITY_ID_MCUD:
                return "mcud";
            case CAPABILITY_ID_ICH:
                return "ich";
            case CAPABILITY_ID_INDN:
                return "indn";
            case CAPABILITY_ID_IL:
                return "il";
            case CAPABILITY_ID_CUB:
                return "cub";
            case CAPABILITY_ID_MCUB:
                return "mcub";
            case CAPABILITY_ID_CUF:
                return "cuf";
            case CAPABILITY_ID_MCUF:
                return "mcuf";
            case CAPABILITY_ID_RIN:
                return "rin";
            case CAPABILITY_ID_CUU:
                return "cuu";
            case CAPABILITY_ID_MCUU:
                return "mcuu";
            case CAPABILITY_ID_PCTRM:
                return "pctrm";
            case CAPABILITY_ID_PFKEY:
                return "pfkey";
            case CAPABILITY_ID_PFLOC:
                return "pfloc";
            case CAPABILITY_ID_PFXL:
                return "pfxl";
            case CAPABILITY_ID_PFX:
                return "pfx";
            case CAPABILITY_ID_PLN:
                return "pln";
            case CAPABILITY_ID_MC0:
                return "mc0";
            case CAPABILITY_ID_MC5P:
                return "mc5p";
            case CAPABILITY_ID_MC4:
                return "mc4";
            case CAPABILITY_ID_MC5:
                return "mc5";
            case CAPABILITY_ID_PULSE:
                return "pulse";
            case CAPABILITY_ID_QDIAL:
                return "qdial";
            case CAPABILITY_ID_RMCLK:
                return "rmclk";
            case CAPABILITY_ID_REP:
                return "rep";
            case CAPABILITY_ID_RFI:
                return "rfi";
            case CAPABILITY_ID_REQMP:
                return "reqmp";
            case CAPABILITY_ID_RS1:
                return "rs1";
            case CAPABILITY_ID_RS2:
                return "rs2";
            case CAPABILITY_ID_RS3:
                return "rs3";
            case CAPABILITY_ID_RF:
                return "rf";
            case CAPABILITY_ID_RC:
                return "rc";
            case CAPABILITY_ID_VPA:
                return "vpa";
            case CAPABILITY_ID_SC:
                return "sc";
            case CAPABILITY_ID_SCESC:
                return "scesc";
            case CAPABILITY_ID_IND:
                return "ind";
            case CAPABILITY_ID_RI:
                return "ri";
            case CAPABILITY_ID_SCS:
                return "scs";
            case CAPABILITY_ID_S0DS:
                return "s0ds";
            case CAPABILITY_ID_S1DS:
                return "s1ds";
            case CAPABILITY_ID_S2DS:
                return "s2ds";
            case CAPABILITY_ID_S3DS:
                return "s3ds";
            case CAPABILITY_ID_SETAB:
                return "setab";
            case CAPABILITY_ID_SETAF:
                return "setaf";
            case CAPABILITY_ID_SGR:
                return "sgr";
            case CAPABILITY_ID_SETB:
                return "setb";
            case CAPABILITY_ID_SMGB:
                return "smgb";
            case CAPABILITY_ID_SMGBP:
                return "smgbp";
            case CAPABILITY_ID_SCLK:
                return "sclk";
            case CAPABILITY_ID_SETCOLOR:
                return "setcolor";
            case CAPABILITY_ID_SCP:
                return "scp";
            case CAPABILITY_ID_SETF:
                return "setf";
            case CAPABILITY_ID_SMGL:
                return "smgl";
            case CAPABILITY_ID_SMGLP:
                return "smglp";
            case CAPABILITY_ID_SMGLR:
                return "smglr";
            case CAPABILITY_ID_SLINES:
                return "slines";
            case CAPABILITY_ID_SMGR:
                return "smgr";
            case CAPABILITY_ID_SMGRP:
                return "smgrp";
            case CAPABILITY_ID_HTS:
                return "hts";
            case CAPABILITY_ID_SMGTB:
                return "smgtb";
            case CAPABILITY_ID_SMGT:
                return "smgt";
            case CAPABILITY_ID_SMGTP:
                return "smgtp";
            case CAPABILITY_ID_WIND:
                return "wind";
            case CAPABILITY_ID_SBIM:
                return "sbim";
            case CAPABILITY_ID_SCSD:
                return "scsd";
            case CAPABILITY_ID_RBIM:
                return "rbim";
            case CAPABILITY_ID_RCSD:
                return "rcsd";
            case CAPABILITY_ID_SUBCS:
                return "subcs";
            case CAPABILITY_ID_SUPCS:
                return "supcs";
            case CAPABILITY_ID_HT:
                return "ht";
            case CAPABILITY_ID_DOCR:
                return "docr";
            case CAPABILITY_ID_TSL:
                return "tsl";
            case CAPABILITY_ID_TONE:
                return "tone";
            case CAPABILITY_ID_U0:
                return "u0";
            case CAPABILITY_ID_U1:
                return "u1";
            case CAPABILITY_ID_U2:
                return "u2";
            case CAPABILITY_ID_U3:
                return "u3";
            case CAPABILITY_ID_U4:
                return "u4";
            case CAPABILITY_ID_U5:
                return "u5";
            case CAPABILITY_ID_U6:
                return "u6";
            case CAPABILITY_ID_U7:
                return "u7";
            case CAPABILITY_ID_U8:
                return "u8";
            case CAPABILITY_ID_U9:
                return "u9";
            case CAPABILITY_ID_UC:
                return "uc";
            case CAPABILITY_ID_HU:
                return "hu";
            case CAPABILITY_ID_WAIT:
                return "wait";
            case CAPABILITY_ID_XOFFC:
                return "xoffc";
            case CAPABILITY_ID_XONC:
                return "xonc";
            case CAPABILITY_ID_ZEROM:
                return "zerom";
        }
        return null;
    }

}
/*
  class CapIdRow
  {
	String	capStr;
	int		capId;
	int		capType;

	CapIdRow(String str, int id, int type)
	{
		capStr	= str;
		capId	= id;
		capType	= type;
	}
  }
*/
