
//Title:        Terminal Emulator
//Version:
//Copyright:    Copyright (c) 1997
//Author:       Bipin Prasad
//Company:      Prasad & Associates Ltd.
//Description:  Terminal Emulator


package com.prasad.terminal;

import java.awt.*;
import java.util.*;
import java.io.*;

/**
 * This class contains all the information necessary to describe a terminal's
 * capabilities. This corresponds to the "terminfo" on unix.
 */
public class TermInfo {
    String[] shortNames;    // multiple names. These names should be in lowercase and not contain blanks.
    String longName;    // this name can contain blanks and upper case for readability

    // Capabilities in terminfo are of three types:
    // Boolean capabilities indicating that the terminal has some particular
    // feature, numeric capabilities giving the size of the terminal or the
    // size of particular delays, and string capabilities that identify a
    // sequence which can be used to perform particular terminal operations.

    // boolean capabilities
    boolean auto_left_margin;        // bw      bw	 cub1 wraps from column 0 to last column
    boolean auto_right_margin;        // am      am     Terminal has automatic margins
    boolean back_color_erase;       //  bce    be     Screen erased with background color
    boolean can_change;              //  ccc    cc       Terminal can re-define existing color
    boolean ceol_standout_glitch;   // xhp     xs     Standout not erased by overwriting (hp)
    boolean col_addr_glitch;         //  xhpa   YA       Only positive motion for hpa/mhpa caps
    boolean cpi_changes_res;         //  cpix   YF       Changing character pitch changes resolution
    boolean cr_cancels_micro_mode;   //  crxm   YB       Using cr turns off micro mode
    boolean dest_tabs_magic_smso;    //  xt     xt       Destructive tabs, magic smso char (t1061)
    //boolean	teleray_glitch;         // xt      xt     Tabs ruin, magic so char (Teleray 1061)
    boolean eat_newline_glitch;     // xenl    xn     newline ignored after 80 cols (Concept)
    boolean erase_overstrike;       // eo      eo     Can erase overstrikes with a blank
    boolean generic_type;           // gn      gn     Generic line type (e.g.,, dialup, switch).
    boolean hard_copy;              // hc      hc     Hardcopy terminal
    boolean hard_cursor;             //  chts   HC       Cursor is hard to see
    boolean has_meta_key;           // km      km     Has a meta key (shift, sets parity bit)
    boolean has_status_line;        // hs      hs     Has extra "status line"
    boolean has_print_wheel;         //  daisy  YC       Printer needs operator to change character set
    boolean hue_lightness_saturation; // hls    hl       Terminal uses only HLS color notation (Tektronix)
    boolean insert_null_glitch;     // in      in     Insert mode distinguishes nulls
    boolean lpi_changes_res;         //  lpix   YG       Changing line pitch changes resolution
    boolean memory_above;           // da      da     Display may be retained above the screen
    boolean memory_below;           // db      db     Display may be retained below the screen
    boolean move_insert_mode;       // mir     mi     Safe to move while in insert mode
    boolean move_standout_mode;     // msgr    ms     Safe to move in standout modes
    boolean needs_xon_xoff;          //  nxon   nx       Padding won't work, xon/xoff required
    boolean no_esc_ctlc;             //  xsb    xb       Beehive (f1=escape, f2=ctrl C)
    boolean no_pad_char;             //  npc    NP       Pad character doesn't exist
    boolean non_dest_scroll_region;  //  ndscr  ND       Scrolling region is nondestructive
    boolean non_rev_rmcup;           //  nrrmc  NR       smcup does not reverse rmcup
    boolean over_strike;             //  os     os       Terminal overstrikes on hard-copy terminal
    boolean prtr_silent;             //  mc5i   5i       Printer won't echo on screen
    boolean row_addr_glitch;         //  xvpa   YD       Only positive motion for vpa/mvpa caps
    boolean semi_auto_right_margin;  //  sam    YE       Printing in last column causes cr
    boolean status_line_esc_ok;      //  eslok  es       Escape can be used on the status line
    boolean tilde_glitch;           // hz      hz     Hazeltine; cannot print ~
    boolean transparent_underline;  // ul      ul     underline character overstrikes
    boolean xon_xoff;                // xon     xo     Terminal uses xon/xoff handshaking

    // numeric capabilites
    int bit_image_entwining;    // bitwin  Yo       Number of passes for each bit-map row
    int bit_image_type;    // bitype  Yp       Type of bit image device
    int buffer_capacity;    // bufsz   Ya       Number of bytes buffered before printing
    int buttons;    // btns    BT       Number of buttons on the mouse
    int columns;                // cols    co     Number of columns in a line
    int dot_horz_spacing;    // spinh   Yc       Spacing of dots horizontally in dots per inch
    int dot_vert_spacing;    // spinv   Yb       Spacing of pins vertically in pins per inch
    int init_tabs;              // it      it     Tabs initially every # spaces
    int label_height;    // lh      lh       Number of rows in each label
    int label_width;    // lw      lw       Number of columns in each label
    int lines;                  // lines   li     Number of lines on screen or page
    int lines_of_memory;        // lm      lm     Lines of memory if > lines.  0 means varies
    int max_attributes;    // ma      ma       Maximum combined video attributes terminal can display
    int magic_cookie_glitch;    // xmc     sg     Number of blank chars left by smso or rmso
    int max_colors;    // colors  Co       Maximum number of colors on the screen
    int max_micro_address;    // maddr   Yd       Maximum value in micro_..._address
    int max_micro_jump;    // mjump   Ye       Maximum value in parm_..._micro
    int max_pairs;    // pairs   pa       Maximum number of color-pairs on the screen
    int maximum_windows;    // Wnum    MW       Maximum number of definable windows
    int micro_char_size;    // mcs     Yf       Character step size when in micro mode
    int micro_line_size;    // mls     Yg       Line step size when in micro mode
    int no_color_video;    // ncv     NC       Video attributes that can't be used with colors
    int num_labels;             // nlab    Nl     Number of labels on screen (start at 1)
    int number_of_pins;    // npins   Yh       Number of pins in print-head
    int output_res_char;    // orc     Yi       Horizontal resolution in units per character
    int output_res_line;    // orl     Yj       Vertical resolution in units per line
    int output_res_horz_inch;    // orhi    Yk       Horizontal resolution in units per inch
    int output_res_vert_inch;    // orvi    Yl       Vertical resolution in units per inch
    int padding_baud_rate;      // pb      pb     Lowest baud where cr/nl padding is needed
    int print_rate;    // cps     Ym       Print rate in characters per second where padding needed
    int virtual_terminal;       // vt      vt     Virtual terminal number (HP-UX system)
    int wide_char_size;    // widcs   Yn       Character step size when in double wide mode
    int width_status_line;      // wsl     ws     No. columns in status line

    // string capabilties
    String acs_chars;// acsc   ac       Graphic charset pairs aAbBcC
    String alt_scancode_esc;// scesa  S8       Alternate escape for scancode emulation (default is for vt100)
    String back_tab;               // cbt     bt     Back tab (P)
    String bell;                   // bel     bl     Audible signal (bell) (P)
    String bit_image_carriage_return;// bicr   Yv       Move to beginning of same row (use tparm)
    String bit_image_newline;// binel  Zz       Move to next row of the bit image (use tparm)
    String bit_image_repeat;// birep  Zy       Repeat bit-image cell #1 #2 times (use tparm)
    String carriage_return;        // cr      cr     Carriage return (P*)
    String change_char_pitch;// cpi    ZA       Change number of characters per inch
    String change_line_pitch;// lpi    ZB       Change number of lines per inch
    String change_res_horz;// chr    ZC       Change horizontal resolution
    String change_res_vert;// cvr    ZD       Change vertical resolution
    String change_scroll_region;   // csr     cs     change to lines #1 through #2 (vt100)(PG)
    String char_padding;// rmp    rP       Like ip but when in replace mode
    String char_set_names;// csnm   Zy       List of character set names
    String clear_all_tabs;         // tbc     ct     Clear all tab stops (P)
    String clear_margins;// mgc    MC       Clear all margins (top, bottom, and sides)
    String clear_screen;           // clear   cl     Clear screen and home cursor (P*)
    String clr_bol;// el1    cb       Clear to beginning of line, inclusive
    String clr_eol;                // el      ce     Clear to end of line (P)
    String clr_eos;                // ed      cd     Clear to end of display (P*)
    String code_set_init;// csin   ci       Init sequence for multiple codesets
    String color_names;// colornm  Yw		Give name for color #1
    String column_address;         // hpa     ch     Set cursor column (PG)
    String command_character;      // cmdch   CC     Term. settable cmd char in prototype
    String create_window;// cwin   CW       Define win #1 to go from #2,#3to #4,#5
    String cursor_address;         // cup     cm     Screen rel. cursor motion row #1 col #2 (PG)
    String cursor_down;            // cud1    do     Down one line
    String cursor_home;            // home    ho     Home cursor (if no cup)
    String cursor_invisible;       // civis   vi     Make cursor invisible
    String cursor_left;            // cub1    le     Move cursor left one space
    String cursor_mem_address;     // mrcup   CM     Memory relative cursor addressing
    String cursor_normal;          // cnorm   ve     Make cursor appear normal (undo vs/vi)
    String cursor_right;           // cuf1    nd     Non-destructive space (cursor right)
    String cursor_to_ll;           // ll      ll     Last line, first column (if no cup)
    String cursor_up;              // cuu1    up     Upline (cursor up)
    String cursor_visible;         // cvvis   vs     Make cursor very visible
    String define_bit_image_region;// defbi  Yx       Define rectangular bit-image region (use tparm)
    String define_char;// defc   ZE       Define a character in a character set-
    String delete_character;       // dch1    dc     Delete character (P*)
    String delete_line;            // dl1     dl     Delete line (P*)
    String device_type;// devt   dv       Indicate language/ codeset support
    String dial_phone;// dial   DI       Dial phone number #1
    String dis_status_line;        // dsl     ds     Disable status line
    String display_clock;// dclk   DK       Display time-of-day clock
    String display_pc_char;// dispc  S1       Display PC character
    String down_half_line;         // hd      hd     Half-line down (forward 1/2 linefeed)
    String ena_acs;// enacs  eA       Enable alternate character set
    String end_bit_image_region;// endbi  Yy       End a bit-image region (use tparm)
    String enter_alt_charset_mode; // smacs   as     Start alternate character set (P)
    String enter_am_mode;// smam   SA       Turn on automatic margins
    String enter_blink_mode;       // blink   mb     Turn on blinking
    String enter_bold_mode;        // bold    md     Turn on bold (extra bright) mode
    String enter_ca_mode;          // smcup   ti     String to begin programs that use cup
    String enter_delete_mode;      // smdc    dm     Delete mode (enter)
    String enter_dim_mode;         // dim     mh     Turn on half-bright mode
    String enter_doublewide_mode;// swidm  ZF       Enable double wide printing
    String enter_draft_quality;// sdrfq  ZG       Set draft quality print mode
    String enter_insert_mode;      // smir    im     Insert mode (enter);
    String enter_italics_mode;// sitm   ZH       Enable italics
    String enter_leftward_mode;// slm    ZI       Enable leftward carriage motion
    String enter_micro_mode;// smicm  ZJ       Enable micro motion capabilities
    String enter_near_letter_quality;// snlq   ZK       Set near-letter quality print
    String enter_normal_quality;// snrmq  ZL       Set normal quality print
    String enter_pc_charset_mode;// smpch  S2       Enter PC character display mode
    String enter_protected_mode;   // prot    mp     Turn on protected mode
    String enter_reverse_mode;     // rev     mr     Turn on reverse video mode
    String enter_scancode_mode;// smsc   S4       Enter PC scancode mode
    String enter_secure_mode;      // invis   mk     Turn on blank mode (chars invisible)
    String enter_shadow_mode;// sshm   ZM       Enable shadow printing
    String enter_standout_mode;    // smso    so     Begin stand out mode
    String enter_subscript_mode;// ssubm  ZN       Enable subscript printing
    String enter_superscript_mode;// ssupm  ZO       Enable superscript printing
    String enter_underline_mode;   // smul    us     Start underscore mode
    String enter_upward_mode;// sum    ZP       Enable upward carriage motion mode
    String enter_xon_mode;// smxon  SX       Turn on xon/xoff handshaking
    String erase_chars;            // ech     ec     Erase #1 characters (PG)
    String exit_alt_charset_mode;  // rmacs   ae     End alternate character set (P)
    String exit_am_mode;// rmam   RA       Turn off automatic margins
    String exit_attribute_mode;    // sgr0    me     Turn off all attributes
    String exit_ca_mode;           // rmcup   te     String to end programs that use cup
    String exit_delete_mode;       // rmdc    ed     End delete mode
    String exit_doublewide_mode;// rwidm  ZQ       Disable double wide printing
    String exit_insert_mode;       // rmir    ei     End insert mode
    String exit_italics_mode;// ritm   ZR       Disable italics
    String exit_leftward_mode;// rlm    ZS       Enable rightward (normal) carriage motion
    String exit_micro_mode;// rmicm  ZT       Disable micro motion capabilities
    String exit_pc_charset_mode;// rmpch  S3       Disable PC character display mode
    String exit_scancode_mode;// rmsc   S5       Disable PC scancode mode
    String exit_shadow_mode;// rshm   ZU       Disable shadow printing
    String exit_standout_mode;     // rmso    se     End stand out mode
    String exit_subscript_mode;// rsubm  ZV       Disable subscript printing
    String exit_superscript_mode;// rsupm  ZW       Disable superscript printing
    String exit_underline_mode;    // rmul    ue     End underscore mode
    String exit_upward_mode;// rum    ZX       Enable downward (normal) carriage motion
    String exit_xon_mode;// rmxon  RX       Turn off xon/xoff handshaking
    String fixed_pause;// pause  PA       Pause for 2-3 seconds
    String flash_hook;// hook   fh       Flash the switch hook
    String flash_screen;           // flash   vb     Visible bell (may not move cursor)
    String form_feed;              // ff      ff     Hardcopy terminal page eject (P*)
    String from_status_line;       // fsl     fs     Return from status line
    String get_mouse;// getm   Gm       Curses should get button events
    String goto_window;// wingo  WG       Go to window #1
    String hangup;// hup    HU       Hang-up phone
    String init_1string;           // is1     i1     Terminal initialization string
    String init_2string;           // is2     i2     Terminal initialization string
    String init_3string;           // is3     i3     Terminal initialization string
    String init_file;              // if      if     Name of file containing is
    String init_prog;// iprog  iP       Path name of program for initialization
    String initialize_color;// initc  Ic       Initialize the definition of color
    String initialize_pair;// initp  Ip       Initialize color-pair
    String insert_character;       // ich1    ic     Insert character (P)
    String insert_line;            // il1     al     Add new blank line (P*)
    String insert_padding;         // ip      ip     Insert pad after character inserted (p*)
    String key_a1;// ka1     K1       KEY_A1, upper left of keypad
    String key_a3;// ka3     K3       KEY_A3, upper right of keypad
    String key_b2;// kb2     K2       KEY_B2, center of keypad
    String key_backspace;          // kbs     kb     Sent by backspace key
    String key_beg;// kbeg    @1       KEY_BEG, sent by beg(inning) key
    String key_btab;// kcbt    kB       KEY_BTAB, sent by back-tab key
    String key_c1;// kc1     K4       KEY_C1, lower left of keypad
    String key_c3;// kc3     K5       KEY_C3, lower right of keypad
    String key_cancel;// kcan    @2       KEY_CANCEL, sent by cancel key
    String key_catab;              // ktbc    ka     Sent by clear-all-tabs key
    String key_clear;              // kclr    kC     Sent by clear screen or erase key
    String key_close;// kclo    @3       KEY_CLOSE, sent by close key
    String key_command;// kcmd    @4       KEY_COMMAND, sent by cmd (command) key
    String key_copy;// kcpy    @5       KEY_COPY, sent by copy key
    String key_create;// kcrt    @6       KEY_CREATE, sent by create key
    String key_ctab;               // kctab   kt     Sent by clear-tab key
    String key_dc;                 // kdch1   kD     Sent by delete character key
    String key_dl;                 // kdl1    kL     Sent by delete line key
    String key_down;               // kcud1   kd     Sent by terminal down arrow key
    String key_eic;                // krmir   kM     Sent by rmir or smir in insert mode
    String key_end;// kend    @7       KEY_END, sent by end key
    String key_enter;// kent    @8       KEY_ENTER, sent by enter/send key
    String key_eol;                // kel     kE     Sent by clear-to-end-of-line key
    String key_eos;                // ked     kS     Sent by clear-to-end-of-screen key
    String key_exit;// kext    @9       KEY_EXIT, sent by exit key
    String key_f0;                 // kf0     k0     Sent by function key f0
    String key_f1;                 // kf1     k1     Sent by function key f1
    String key_f2;                 // kf2     k2     Sent by function key f2
    String key_f3;                 // kf3     k3     Sent by function key f3
    String key_f4;                 // kf4     k4     Sent by function key f4
    String key_f5;                 // kf5     k5     Sent by function key f5
    String key_f6;                 // kf6     k6     Sent by function key f6
    String key_f7;                 // kf7     k7     Sent by function key f7
    String key_f8;                 // kf8     k8     Sent by function key f8
    String key_f9;                 // kf9     k9     Sent by function key f9
    String key_f10;                // kf10    ka     Sent by function key f10
    String key_f11;                // kf11    F1     Sent by function key f11
    String key_f12;                // kf12    F2     Sent by function key f12
    String key_f13;                // kf13    F3     Sent by function key f13
    String key_f14;                // kf14    F4     Sent by function key f14
    String key_f15;                // kf15    F5     Sent by function key f15
    String key_f16;                // kf16    F6     Sent by function key f16
    String key_f17;                // kf17    F7     Sent by function key f17
    String key_f18;                // kf18    F8     Sent by function key f18
    String key_f19;                // kf19    F9     Sent by function key f19
    String key_f20;                // kf20    FA     Sent by function key f20
    String key_f21;                // kf21    FB     Sent by function key f21
    String key_f22;                // kf22    FC     Sent by function key f22
    String key_f23;                // kf23    FD     Sent by function key f23
    String key_f24;                // kf24    FE     Sent by function key f24
    String key_f25;                // kf25    FF     Sent by function key f25
    String key_f26;                // kf26    FG     Sent by function key f26
    String key_f27;                // kf27    FH     Sent by function key f27
    String key_f28;                // kf28    FI     Sent by function key f28
    String key_f29;                // kf29    FJ     Sent by function key f29
    String key_f30;                // kf30    FK     Sent by function key f30
    String key_f31;                // kf31    FL     Sent by function key f31
    String key_f32;                // kf32    FM     Sent by function key f32
    String key_f33;                // kf33    FN     Sent by function key f33
    String key_f34;                // kf34    FO     Sent by function key f34
    String key_f35;                // kf35    FP     Sent by function key f35
    String key_f36;                // kf36    FQ     Sent by function key f36
    String key_f37;                // kf37    FR     Sent by function key f37
    String key_f38;                // kf38    FS     Sent by function key f38
    String key_f39;                // kf39    FT     Sent by function key f39
    String key_f40;                // kf40    FU     Sent by function key f40
    String key_f41;                // kf41    FV     Sent by function key f41
    String key_f42;                // kf42    FW     Sent by function key f42
    String key_f43;                // kf43    FX     Sent by function key f43
    String key_f44;                // kf44    FY     Sent by function key f44
    String key_f45;                // kf45    FZ     Sent by function key f45
    String key_f46;                // kf46    Fa     Sent by function key f46
    String key_f47;                // kf47    Fb     Sent by function key f47
    String key_f48;                // kf48    Fc     Sent by function key f48
    String key_f49;                // kf49    Fd     Sent by function key f49
    String key_f50;                // kf50    Fe     Sent by function key f50
    String key_f51;                // kf51    Ff     Sent by function key f51
    String key_f52;                // kf52    Fg     Sent by function key f52
    String key_f53;                // kf53    Fh     Sent by function key f53
    String key_f54;                // kf54    Fi     Sent by function key f54
    String key_f55;                // kf55    Fj     Sent by function key f55
    String key_f56;                // kf56    Fk     Sent by function key f56
    String key_f57;                // kf57    Fl     Sent by function key f57
    String key_f58;                // kf58    Fm     Sent by function key f58
    String key_f59;                // kf59    Fn     Sent by function key f59
    String key_f60;                // kf60    Fo     Sent by function key f60
    String key_f61;                // kf61    Fp     Sent by function key f61
    String key_f62;                // kf62    Fq     Sent by function key f62
    String key_f63;                // kf63    Fr     Sent by function key f63
    String key_find;// kfnd    @0       KEY_FIND, sent by find key
    String key_help;// khlp    %1       KEY_HELP, sent by help key
    String key_home;               // khome   kh     Sent by home key
    String key_ic;                 // kich1   kI     Sent by ins char/enter ins mode key
    String key_il;                 // kil1    kA     Sent by insert line
    String key_left;               // kcub1   kl     Sent by terminal left arrow key
    String key_ll;                 // kll     kH     Sent by home-down key
    String key_mark;// kmrk    %2       KEY_MARK, sent by mark key
    String key_message;// kmsg    %3       KEY_MESSAGE, sent by message key
    String key_mouse;// kmous   Km       0631, Mouse event has occured
    String key_move;// kmov    %4       KEY_MOVE, sent by move key
    String key_next;// knxt    %5       KEY_NEXT, sent by next-object key
    String key_npage;              // knp     kN     Sent by next-page key
    String key_open;// kopn    %6       KEY_OPEN, sent by open key
    String key_options;// kopt    %7       KEY_OPTIONS, sent by options key
    String key_ppage;              // kpp     kP     Sent by previous-page key
    String key_previous;// kprv    %8       KEY_PREVIOUS, sent by previous-object key
    String key_print;// kprt    %9       KEY_PRINT, sent by print or copy key
    String key_redo;// krdo    %0       KEY_REDO, sent by redo key
    String key_reference;// kref    &1       KEY_REFERENCE, sent by reference key
    String key_refresh;// krfr    &2       KEY_REFRESH, sent by refresh key
    String key_replace;// krpl    &3       KEY_REPLACE, sent by replace key
    String key_restart;// krst    &4       KEY_RESTART, sent by restart key
    String key_resume;// kres    &5       KEY_RESUME, sent by resume key
    String key_right;              // kcuf1   kr     Sent by terminal right arrow key
    String key_save;// ksav    &6       KEY_SAVE, sent by save key
    String key_sbeg;// kBEG    &9       KEY_SBEG, sent by shifted beginning key
    String key_scancel;// kCAN    &0       KEY_SCANCEL, sent by shifted cancel key
    String key_scommand;// kCMD    *1       KEY_SCOMMAND, sent by shifted command key
    String key_scopy;// kCPY    *2       KEY_SCOPY, sent by shifted copy key
    String key_screate;// kCRT    *3       KEY_SCREATE, sent by shifted create key
    String key_sdc;// kDC     *4       KEY_SDC, sent by shifted delete-char key
    String key_sdl;// kDL     *5       KEY_SDL, sent by shifted delete-line key
    String key_select;// kslt    *6       KEY_SELECT, sent by select key
    String key_send;// kEND    *7       KEY_SEND, sent by shifted end key
    String key_seol;// kEOL    *8       KEY_SEOL, sent by shifted clear-line key
    String key_sexit;// kEXT    *9       KEY_SEXIT, sent by shifted exit key
    String key_sf;                 // kind    kF     Sent by scroll-forward/down key
    String key_sfind;// kFND    *0       KEY_SFIND, sent by shifted find key
    String key_shelp;// kHLP    #1       KEY_SHELP, sent by shifted help key
    String key_shome;// kHOM    #2       KEY_SHOME, sent by shifted home key
    String key_sic;// kIC     #3       KEY_SIC, sent by shifted input key
    String key_sleft;// kLFT    #4       KEY_SLEFT, sent by shifted left-arrow key
    String key_smessage;// kMSG    %a       KEY_SMESSAGE, sent by shifted message key
    String key_smove;// kMOV    %b       KEY_SMOVE, sent by shifted move key
    String key_snext;// kNXT    %c       KEY_SNEXT, sent by shifted next key
    String key_soptions;// kOPT    %d       KEY_SOPTIONS, sent by shifted options key
    String key_sprevious;// kPRV    %e       KEY_SPREVIOUS, sent by shifted prev key
    String key_sprint;// kPRT    %f       KEY_SPRINT, sent by shifted print key
    String key_sr;                 // kri     kR     Sent by scroll-backward/up key
    String key_sredo;// kRDO    %g       KEY_SREDO, sent by shifted redo key
    String key_sreplace;// kRPL    %h       KEY_SREPLACE, sent by shifted replace key
    String key_sright;// kRIT    %i       KEY_SRIGHT, sent by shifted right-arrow key
    String key_srsume;// kRES    %j       KEY_SRSUME, sent by shifted resume key
    String key_ssave;// kSAV    !1       KEY_SSAVE, sent by shifted save key
    String key_ssuspend;// kSPD    !2       KEY_SSUSPEND, sent by shifted suspend key
    String key_stab;               // khts    kT     Sent by set-tab key
    String key_sundo;// kUND    !3       KEY_SUNDO, sent by shifted undo key
    String key_suspend;// kspd    &7       KEY_SUSPEND, sent by suspend key
    String key_undo;// kund    &8       KEY_UNDO, sent by undo key
    String key_up;                 // kcuu1   ku     Sent by terminal up arrow key
    String keypad_local;           // rmkx    ke     Out of "keypad transmit" mode
    String keypad_xmit;            // smkx    ks     Put terminal in "keypad transmit" mode
    String lab_f0;                 // lf0     l0     Labels on function key f0 if not f0
    String lab_f1;                 // lf1     l1     Labels on function key f1 if not f1
    String lab_f2;                 // lf2     l2     Labels on function key f2 if not f2
    String lab_f3;                 // lf3     l3     Labels on function key f3 if not f3
    String lab_f4;                 // lf4     l4     Labels on function key f4 if not f4
    String lab_f5;                 // lf5     l5     Labels on function key f5 if not f5
    String lab_f6;                 // lf6     l6     Labels on function key f6 if not f6
    String lab_f7;                 // lf7     l7     Labels on function key f7 if not f7
    String lab_f8;                 // lf8     l8     Labels on function key f8 if not f8
    String lab_f9;                 // lf9     l9     Labels on function key f9 if not f9
    String lab_f10;                // lf10    la     Labels on function key f10 if not f10
    String label_format;// fln     Lf       Label format
    String label_off;              // rmln    LF     Turn off soft labels
    String label_on;               // smln    LO     Turn on soft labels
    String memory_lock;            // meml    ml     Lock memory above cursor
    String memory_unlock;          // memu    mu     Turn memory lock off
    String meta_off;               // rmm     mo     Turn off "meta mode"
    String meta_on;                // smm     mm     Turn on "meta mode" (8th bit)
    String micro_column_address;// mhpa    ZY       Like column_address for micro adjustment
    String micro_down;// mcud1   ZZ       Like cursor_down for micro adjustment
    String micro_left;// mcub1   Za       Like cursor_left for micro adjustment
    String micro_right;// mcuf1   Zb       Like cursor_right for micro adjustment
    String micro_row_address;// mvpa    Zc       Like row_address for micro adjustment
    String micro_up;// mcuu1   Zd       Like cursor_up for micro adjustment
    String mouse_info;// minfo   Mi       Mouse status information
    String newline;                // nel     nw     Newline (behaves like cr followed by lf)
    String order_of_pins;// porder  Ze       Matches software bits to print-head pins
    String orig_colors;// oc      oc       Set all color(-pair)s to the original ones
    String orig_pair;// op      op       Set default color-pair to the original one
    String pad_char;               // pad     pc     Pad character (rather than null)
    String parm_dch;               // dch     DC     Delete #1 chars (PG*)
    String parm_delete_line;       // dl      DL     Delete #1 lines (PG*)
    String parm_down_cursor;       // cud     DO     Move cursor down #1 lines (PG*)
    String parm_down_micro;// mcud    Zf       Like parm_down_cursor for micro adjust
    String parm_ich;               // ich     IC     Insert #1 blank chars (PG*)
    String parm_index;             // indn    SF     Scroll forward #1 lines (PG)
    String parm_insert_line;       // il      AL     Add #1 new blank lines (PG*)
    String parm_left_cursor;       // cub     LE     Move cursor left #1 spaces (PG)
    String parm_left_micro;// mcub    Zg       Like parm_left_cursor for micro adjust
    String parm_right_cursor;      // cuf     RI     Move cursor right #1 spaces (PG*)
    String parm_right_micro;// mcuf    Zh       Like parm_right_cursor for micro adjust
    String parm_rindex;            // rin     SR     Scroll backward #1 lines (PG)
    String parm_up_cursor;         // cuu     UP     Move cursor up #1 lines (PG*)
    String parm_up_micro;// mcuu    Zi       Like parm_up_cursor for micro adjust
    String pc_term_options;// pctrm   S6       PC terminal options
    String pkey_key;               // pfkey   pk     Prog funct key #1 to type string #2
    String pkey_local;             // pfloc   pl     Prog funct key #1 to execute string #2
    String pkey_plab;// pfxl    xl       Prog key #1 to xmit string #2 and show string #3
    String pkey_xmit;              // pfx     px     Prog funct key #1 to xmit string #2
    String plab_norm;              // pln     pn     Prog label #1 to show string #2
    String print_screen;           // mc0     ps     Print contents of the screen
    String prtr_non;               // mc5p    pO     Turn on the printer for #1 bytes
    String prtr_off;               // mc4     pf     Turn off the printer
    String prtr_on;                // mc5     po     Turn on the printer
    String pulse;// pulse   PU       Select pulse dialing
    String quick_dial;// qdial   QD       Dial phone number #1, without progress detection
    String remove_clock;// rmclk   RC       Remove time-of-day clock
    String repeat_char;            // rep     rp     Repeat char #1 #2 times.  (PG*)
    String req_for_input;// rfi     RF       Send next input char (for ptys)
    String req_mouse_pos;// reqmp   RQ       Request mouse position report
    String reset_1string;          // rs1     r1     Reset terminal completely to sane modes.
    String reset_2string;          // rs2     r2     Reset terminal completely to sane modes.
    String reset_3string;          // rs3     r3     Reset terminal completely to sane modes.
    String reset_file;             // rf      rf     Name of file containing reset string
    String restore_cursor;         // rc      rc     Restore cursor to position of last sc
    String row_address;            // vpa     cv     Vertical position absolute (set row) (PG)
    String save_cursor;            // sc      sc     Save cursor position (P)
    String scancode_escape;// scesc   S7       Escape for scancode emulation
    String scroll_forward;         // ind     sf     Scroll text up (P)
    String scroll_reverse;         // ri      sr     Scroll text down (P)
    String select_char_set;// scs     Zj       Select character set
    String set0_des_seq;// s0ds    s0       Shift into codeset 0 EUC set 0, ASCII)
    String set1_des_seq;// s1ds    s1       Shift into codeset 1
    String set2_des_seq;// s2ds    s2       Shift into codeset 2
    String set3_des_seq;// s3ds    s3       Shift into codeset 3 attributes #1-#6
    String set_a_background;// setab   AB       Set background color using ANSI escape
    String set_a_foreground;// setaf   AF       Set foreground color using ANSI escape
    String set_attributes;         // sgr     sa     Define the video attributes (PG9)
    String set_background;// setb    Sb       Set current background color
    String set_bottom_margin;// smgb    Zk       Set bottom margin at current line
    String set_bottom_margin_parm;// smgbp   Zl       Set bottom margin at line #1 or #2 lines from bottom
    String set_clock;// sclk    SC       Set time-of-day clock
    String set_color_band;// setcolor         YzChange to ribbon color #1
    String set_color_pair;// scp     sp       Set current color-pair
    String set_foreground;// setf    Sf       Set current foreground color1
    String set_left_margin;// smgl    ML       Set left margin at current line
    String set_left_margin_parm;// smglp   Zm       Set left (right) margin at column #1 (#2)
    String set_lr_margin;// smglr   ML       Sets both left and right margins
    String set_page_length;// slines  YZ       Set page length to 1 lines (use tparm) of an inch
    String set_right_margin;// smgr    MR       Set right margin at current column
    String set_right_margin_parm;// smgrp   Zn       Set right margin at column #1
    String set_tab;                // hts     st     Set a tab in all rows; current column
    String set_tb_margin;// smgtb   MT       Sets both top and bottom margins
    String set_top_margin;// smgt    Zo       Set top margin at current line
    String set_top_margin_parm;// smgtp   Zp       Set top (bottom) margin at line #1 (#2)
    String set_window;             // wind    wi     Current window is lines #1-#2 cols #3-#4
    String start_bit_image;// sbim    Zq       Start printing bit image graphics
    String start_char_set_def;// scsd    Zr       Start definition of a character set
    String stop_bit_image;// rbim    Zs       End printing bit image graphics
    String stop_char_set_def;// rcsd    Zt       End definition of a character set
    String subscript_characters;// subcs   Zu       List of subscript-able'' characters
    String superscript_characters;// supcs   Zv       List of superscript-able'' characters
    String tab;                    // ht      ta     Tab to next 8 space hardware tab stop
    String these_cause_cr;// docr    Zw       Printing any of these chars causes cr
    String to_status_line;         // tsl     ts     Go to status line; column #1
    String tone;// tone    TO       Select touch tone dialing
    String user0;// u0      u0       User string 0
    String user1;// u1      u1       User string 1
    String user2;// u2      u2       User string 2
    String user3;// u3      u3       User string 3
    String user4;// u4      u4       User string 4
    String user5;// u5      u5       User string 5
    String user6;// u6      u6       User string 6
    String user7;// u7      u7       User string 7
    String user8;// u8      u8       User string 8
    String user9;// u9      u9       User string 9
    String underline_char;         // uc      uc     Underscore one char and move past it
    String up_half_line;           // hu      hu     Half-line up (reverse 1/2 linefeed)
    String wait_tone;// wait    WA       Wait for dial tone
    String xoff_character;// xoffc   XF       X-off character
    String xon_character;// xonc    XN       X-on character
    String zero_motion;// zerom   Zx       No motion for the subsequent character

    /**
     * Current parsing state, or the first state that matches
     */
    int parseState;
    /**
     * An array of characters that can begin a String capability
     */
    int[][] parseStateStartChars;
    /**
     * Master parse table for processing host characters
     */
    int[][] parseTable;
    /**
     * length of the parsing table - this may be less than parseTable.length
     */
    int parseTableLength;

    public TermInfo() {
    }

    public TermInfo(String termcaps) {
        super();
        parseFromTermcaps(termcaps);
        buildParseTable();
    }

    /**
     * Set values of the TermInfo, based upon values in termcap string
     */
    public void parseFromTermcaps(String info) {
        int infoLength = 0;

        if (info == null
            || (infoLength = info.length()) == 0)
            return;

        TermInfoTokenizer termInfoTokenizer = new TermInfoTokenizer(info);

        // get names - NB we are not ignoring commented out lines that begin with #
        //				Assuming that there are no commented out lines.
        String nameToken = termInfoTokenizer.nextToken();
        if (nameToken == null)
            return;

        StringTokenizer nameTokenizer = new StringTokenizer(nameToken, "|");
        int nameCount = nameTokenizer.countTokens();
        if (nameCount < 1) {
            System.out.println("ERROR: No terminal names found in string [" + nameToken + "]");
            return;
        }

        if (nameCount > 1) {
            this.shortNames = new String[nameCount - 1];
            for (int i = 0; i < nameCount - 1; i++)
                this.shortNames[i] = nameTokenizer.nextToken();
            this.longName = nameTokenizer.nextToken();
        } else {
            this.shortNames = new String[1];
            this.shortNames[0] = nameTokenizer.nextToken();
            this.longName = this.shortNames[0];
        }

        // get and process the other tokens
        while (termInfoTokenizer.hasMoreTokens()) {
            String token = termInfoTokenizer.nextToken();
            if (token == null)
                break;
            if (token.length() == 0)
                continue;

            // Sometimes individual capabilities are commented out
            // with a period before the capability name.
            if (token.charAt(0) == '.'
                || token.charAt(0) == '#')
                continue;

            // now process the token
            int idx = token.indexOf('=');
            if (idx < 0) {
                int idx2 = token.indexOf('#');
                if (idx2 < 0) {
                    // boolean capabilities
                    int capId = TermInfoCaps.getCapabilityIdForString(token, TermInfoCaps.CAPABILITY_TYPE_BOOLEAN);

                    switch (capId) {
                        case TermInfoCaps.CAPABILITY_ID_BW:
                            this.auto_left_margin = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_AM:
                            this.auto_right_margin = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_BCE:
                            this.back_color_erase = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_CCC:
                            this.can_change = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_XHP:
                            this.ceol_standout_glitch = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_XHPA:
                            this.col_addr_glitch = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_CPIX:
                            this.cpi_changes_res = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_CRXM:
                            this.cr_cancels_micro_mode = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_XT:
                            this.dest_tabs_magic_smso = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_XENL:
                            this.eat_newline_glitch = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_EO:
                            this.erase_overstrike = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_GN:
                            this.generic_type = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_HC:
                            this.hard_copy = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_CHTS:
                            this.hard_cursor = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_KM:
                            this.has_meta_key = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_HS:
                            this.has_status_line = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_DAISY:
                            this.has_print_wheel = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_HLS:
                            this.hue_lightness_saturation = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_IN:
                            this.insert_null_glitch = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_LPIX:
                            this.lpi_changes_res = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_DA:
                            this.memory_above = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_DB:
                            this.memory_below = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_MIR:
                            this.move_insert_mode = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_MSGR:
                            this.move_standout_mode = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_NXON:
                            this.needs_xon_xoff = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_XSB:
                            this.no_esc_ctlc = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_NPC:
                            this.no_pad_char = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_NDSCR:
                            this.non_dest_scroll_region = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_NRRMC:
                            this.non_rev_rmcup = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_OS:
                            this.over_strike = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_MC5I:
                            this.prtr_silent = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_XVPA:
                            this.row_addr_glitch = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_SAM:
                            this.semi_auto_right_margin = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_ESLOK:
                            this.status_line_esc_ok = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_HZ:
                            this.tilde_glitch = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_UL:
                            this.transparent_underline = true;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_XON:
                            this.xon_xoff = true;
                            break;
                        default:
                            System.out.println("ERROR: Unknown boolean token [" + token + "] encountered.");
                    }
                } else {
                    // numeric capabilties
                    int num;

                    try {
                        num = Integer.parseInt(token.substring(idx2 + 1));
                    } catch (Exception e) {
                        System.out.println("ERROR: invalid number in token [" + token + "]");
                        continue;
                    }
                    String capability = token.substring(0, idx2);
                    int capId = TermInfoCaps.getCapabilityIdForString(capability, TermInfoCaps.CAPABILITY_TYPE_NUMERIC);

                    switch (capId) {
                        case TermInfoCaps.CAPABILITY_ID_BITWIN:
                            this.bit_image_entwining = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_BITYPE:
                            this.bit_image_type = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_BUFSZ:
                            this.buffer_capacity = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_BTNS:
                            this.buttons = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_COLS:
                            this.columns = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_SPINH:
                            this.dot_horz_spacing = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_SPINV:
                            this.dot_vert_spacing = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_IT:
                            this.init_tabs = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_LH:
                            this.label_height = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_LW:
                            this.label_width = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_LINES:
                            this.lines = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_LM:
                            this.lines_of_memory = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_MA:
                            this.max_attributes = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_XMC:
                            this.magic_cookie_glitch = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_COLORS:
                            this.max_colors = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_MADDR:
                            this.max_micro_address = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_MJUMP:
                            this.max_micro_jump = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_PAIRS:
                            this.max_pairs = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_WNUM:
                            this.maximum_windows = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_MCS:
                            this.micro_char_size = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_MLS:
                            this.micro_line_size = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_NCV:
                            this.no_color_video = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_NLAB:
                            this.num_labels = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_NPINS:
                            this.number_of_pins = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_ORC:
                            this.output_res_char = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_ORL:
                            this.output_res_line = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_ORHI:
                            this.output_res_horz_inch = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_ORVI:
                            this.output_res_vert_inch = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_PB:
                            this.padding_baud_rate = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_CPS:
                            this.print_rate = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_VT:
                            this.virtual_terminal = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_WIDCS:
                            this.wide_char_size = num;
                            break;
                        case TermInfoCaps.CAPABILITY_ID_WSL:
                            this.width_status_line = num;
                            break;
                        default:
                            System.out.println("ERROR: Unknown integer token [" + token + "] encountered.");
                    }
                }
            } else {
                // String capabilities
                String capability = token.substring(0, idx);
                String val = token.substring(idx + 1);


                // print capabilties that contain % (for parameterrized strings
                if (val.indexOf('%') >= 0) {
                    System.out.println("Capability [" + capability + "] for TERM [" + this.shortNames[0] + "] has parameters in value [" + val + "]");
                }
                val = processEscapeChars(val);
                //
                int capId = TermInfoCaps.getCapabilityIdForString(capability, TermInfoCaps.CAPABILITY_TYPE_STRING);

                // set the capability values
                switch (capId) {
                    case TermInfoCaps.CAPABILITY_ID_ACSC:
                        this.acs_chars = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SCESA:
                        this.alt_scancode_esc = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CBT:
                        this.back_tab = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_BEL:
                        this.bell = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_BICR:
                        this.bit_image_carriage_return = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_BINEL:
                        this.bit_image_newline = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_BIREP:
                        this.bit_image_repeat = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CR:
                        this.carriage_return = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CPI:
                        this.change_char_pitch = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_LPI:
                        this.change_line_pitch = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CHR:
                        this.change_res_horz = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CVR:
                        this.change_res_vert = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CSR:
                        this.change_scroll_region = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RMP:
                        this.char_padding = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CSNM:
                        this.char_set_names = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_TBC:
                        this.clear_all_tabs = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MGC:
                        this.clear_margins = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CLEAR:
                        this.clear_screen = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_EL1:
                        this.clr_bol = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_EL:
                        this.clr_eol = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_ED:
                        this.clr_eos = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CSIN:
                        this.code_set_init = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_COLORNM:
                        this.color_names = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_HPA:
                        this.column_address = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CMDCH:
                        this.command_character = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CWIN:
                        this.create_window = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CUP:
                        this.cursor_address = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CUD1:
                        this.cursor_down = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_HOME:
                        this.cursor_home = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CIVIS:
                        this.cursor_invisible = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CUB1:
                        this.cursor_left = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MRCUP:
                        this.cursor_mem_address = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CNORM:
                        this.cursor_normal = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CUF1:
                        this.cursor_right = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_LL:
                        this.cursor_to_ll = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CUU1:
                        this.cursor_up = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CVVIS:
                        this.cursor_visible = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_DEFBI:
                        this.define_bit_image_region = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_DEFC:
                        this.define_char = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_DCH1:
                        this.delete_character = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_DL1:
                        this.delete_line = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_DEVT:
                        this.device_type = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_DIAL:
                        this.dial_phone = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_DSL:
                        this.dis_status_line = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_DCLK:
                        this.display_clock = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_DISPC:
                        this.display_pc_char = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_HD:
                        this.down_half_line = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_ENACS:
                        this.ena_acs = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_ENDBI:
                        this.end_bit_image_region = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMACS:
                        this.enter_alt_charset_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMAM:
                        this.enter_am_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_BLINK:
                        this.enter_blink_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_BOLD:
                        this.enter_bold_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMCUP:
                        this.enter_ca_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMDC:
                        this.enter_delete_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_DIM:
                        this.enter_dim_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SWIDM:
                        this.enter_doublewide_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SDRFQ:
                        this.enter_draft_quality = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMIR:
                        this.enter_insert_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SITM:
                        this.enter_italics_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SLM:
                        this.enter_leftward_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMICM:
                        this.enter_micro_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SNLQ:
                        this.enter_near_letter_quality = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SNRMQ:
                        this.enter_normal_quality = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMPCH:
                        this.enter_pc_charset_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_PROT:
                        this.enter_protected_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_REV:
                        this.enter_reverse_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMSC:
                        this.enter_scancode_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_INVIS:
                        this.enter_secure_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SSHM:
                        this.enter_shadow_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMSO:
                        this.enter_standout_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SSUBM:
                        this.enter_subscript_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SSUPM:
                        this.enter_superscript_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMUL:
                        this.enter_underline_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SUM:
                        this.enter_upward_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMXON:
                        this.enter_xon_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_ECH:
                        this.erase_chars = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RMACS:
                        this.exit_alt_charset_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RMAM:
                        this.exit_am_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SGR0:
                        this.exit_attribute_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RMCUP:
                        this.exit_ca_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RMDC:
                        this.exit_delete_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RWIDM:
                        this.exit_doublewide_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RMIR:
                        this.exit_insert_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RITM:
                        this.exit_italics_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RLM:
                        this.exit_leftward_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RMICM:
                        this.exit_micro_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RMPCH:
                        this.exit_pc_charset_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RMSC:
                        this.exit_scancode_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RSHM:
                        this.exit_shadow_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RMSO:
                        this.exit_standout_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RSUBM:
                        this.exit_subscript_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RSUPM:
                        this.exit_superscript_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RMUL:
                        this.exit_underline_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RUM:
                        this.exit_upward_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RMXON:
                        this.exit_xon_mode = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_PAUSE:
                        this.fixed_pause = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_HOOK:
                        this.flash_hook = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_FLASH:
                        this.flash_screen = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_FF:
                        this.form_feed = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_FSL:
                        this.from_status_line = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_GETM:
                        this.get_mouse = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_WINGO:
                        this.goto_window = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_HUP:
                        this.hangup = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_IS1:
                        this.init_1string = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_IS2:
                        this.init_2string = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_IS3:
                        this.init_3string = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_IF:
                        this.init_file = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_IPROG:
                        this.init_prog = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_INITC:
                        this.initialize_color = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_INITP:
                        this.initialize_pair = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_ICH1:
                        this.insert_character = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_IL1:
                        this.insert_line = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_IP:
                        this.insert_padding = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KA1:
                        this.key_a1 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KA3:
                        this.key_a3 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KB2:
                        this.key_b2 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KBS:
                        this.key_backspace = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KBEG:
                        this.key_beg = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KCBT:
                        this.key_btab = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KC1:
                        this.key_c1 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KC3:
                        this.key_c3 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KCAN:
                        this.key_cancel = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KTBC:
                        this.key_catab = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KCLR:
                        this.key_clear = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KCLO:
                        this.key_close = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KCMD:
                        this.key_command = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KCPY:
                        this.key_copy = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KCRT:
                        this.key_create = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KCTAB:
                        this.key_ctab = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KDCH1:
                        this.key_dc = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KDL1:
                        this.key_dl = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KCUD1:
                        this.key_down = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KRMIR:
                        this.key_eic = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KEND:
                        this.key_end = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KENT:
                        this.key_enter = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KEL:
                        this.key_eol = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KED:
                        this.key_eos = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KEXT:
                        this.key_exit = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF0:
                        this.key_f0 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF1:
                        this.key_f1 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF2:
                        this.key_f2 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF3:
                        this.key_f3 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF4:
                        this.key_f4 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF5:
                        this.key_f5 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF6:
                        this.key_f6 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF7:
                        this.key_f7 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF8:
                        this.key_f8 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF9:
                        this.key_f9 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF10:
                        this.key_f10 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF11:
                        this.key_f11 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF12:
                        this.key_f12 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF13:
                        this.key_f13 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF14:
                        this.key_f14 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF15:
                        this.key_f15 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF16:
                        this.key_f16 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF17:
                        this.key_f17 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF18:
                        this.key_f18 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF19:
                        this.key_f19 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF20:
                        this.key_f20 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF21:
                        this.key_f21 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF22:
                        this.key_f22 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF23:
                        this.key_f23 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF24:
                        this.key_f24 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF25:
                        this.key_f25 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF26:
                        this.key_f26 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF27:
                        this.key_f27 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF28:
                        this.key_f28 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF29:
                        this.key_f29 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF30:
                        this.key_f30 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF31:
                        this.key_f31 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF32:
                        this.key_f32 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF33:
                        this.key_f33 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF34:
                        this.key_f34 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF35:
                        this.key_f35 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF36:
                        this.key_f36 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF37:
                        this.key_f37 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF38:
                        this.key_f38 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF39:
                        this.key_f39 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF40:
                        this.key_f40 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF41:
                        this.key_f41 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF42:
                        this.key_f42 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF43:
                        this.key_f43 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF44:
                        this.key_f44 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF45:
                        this.key_f45 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF46:
                        this.key_f46 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF47:
                        this.key_f47 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF48:
                        this.key_f48 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF49:
                        this.key_f49 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF50:
                        this.key_f50 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF51:
                        this.key_f51 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF52:
                        this.key_f52 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF53:
                        this.key_f53 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF54:
                        this.key_f54 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF55:
                        this.key_f55 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF56:
                        this.key_f56 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF57:
                        this.key_f57 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF58:
                        this.key_f58 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF59:
                        this.key_f59 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF60:
                        this.key_f60 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF61:
                        this.key_f61 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF62:
                        this.key_f62 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KF63:
                        this.key_f63 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KFND:
                        this.key_find = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KHLP:
                        this.key_help = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KHOME:
                        this.key_home = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KICH1:
                        this.key_ic = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KIL1:
                        this.key_il = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KCUB1:
                        this.key_left = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KLL:
                        this.key_ll = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KMRK:
                        this.key_mark = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KMSG:
                        this.key_message = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KMOUS:
                        this.key_mouse = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KMOV:
                        this.key_move = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KNXT:
                        this.key_next = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KNP:
                        this.key_npage = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KOPN:
                        this.key_open = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KOPT:
                        this.key_options = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KPP:
                        this.key_ppage = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KPRV:
                        this.key_previous = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KPRT:
                        this.key_print = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KRDO:
                        this.key_redo = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KREF:
                        this.key_reference = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KRFR:
                        this.key_refresh = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KRPL:
                        this.key_replace = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KRST:
                        this.key_restart = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KRES:
                        this.key_resume = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KCUF1:
                        this.key_right = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KSAV:
                        this.key_save = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKBEG:
                        this.key_sbeg = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKCAN:
                        this.key_scancel = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKCMD:
                        this.key_scommand = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKCPY:
                        this.key_scopy = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKCRT:
                        this.key_screate = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKDC:
                        this.key_sdc = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKDL:
                        this.key_sdl = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KSLT:
                        this.key_select = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKEND:
                        this.key_send = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKEOL:
                        this.key_seol = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKEXT:
                        this.key_sexit = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KIND:
                        this.key_sf = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKFND:
                        this.key_sfind = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKHLP:
                        this.key_shelp = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKHOM:
                        this.key_shome = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKIC:
                        this.key_sic = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKLFT:
                        this.key_sleft = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKMSG:
                        this.key_smessage = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKMOV:
                        this.key_smove = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKNXT:
                        this.key_snext = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKOPT:
                        this.key_soptions = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKPRV:
                        this.key_sprevious = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKPRT:
                        this.key_sprint = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KRI:
                        this.key_sr = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKRDO:
                        this.key_sredo = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKRPL:
                        this.key_sreplace = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKRIT:
                        this.key_sright = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKRES:
                        this.key_srsume = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKSAV:
                        this.key_ssave = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKSPD:
                        this.key_ssuspend = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KHTS:
                        this.key_stab = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SKUND:
                        this.key_sundo = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KSPD:
                        this.key_suspend = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KUND:
                        this.key_undo = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_KCUU1:
                        this.key_up = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RMKX:
                        this.keypad_local = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMKX:
                        this.keypad_xmit = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_LF0:
                        this.lab_f0 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_LF1:
                        this.lab_f1 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_LF2:
                        this.lab_f2 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_LF3:
                        this.lab_f3 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_LF4:
                        this.lab_f4 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_LF5:
                        this.lab_f5 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_LF6:
                        this.lab_f6 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_LF7:
                        this.lab_f7 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_LF8:
                        this.lab_f8 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_LF9:
                        this.lab_f9 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_LF10:
                        this.lab_f10 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_FLN:
                        this.label_format = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RMLN:
                        this.label_off = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMLN:
                        this.label_on = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MEML:
                        this.memory_lock = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MEMU:
                        this.memory_unlock = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMM:
                        this.meta_on = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MHPA:
                        this.micro_column_address = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MCUD1:
                        this.micro_down = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MCUB1:
                        this.micro_left = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MCUF1:
                        this.micro_right = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MVPA:
                        this.micro_row_address = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MCUU1:
                        this.micro_up = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MINFO:
                        this.mouse_info = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RMM:
                        this.meta_off = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_NEL:
                        this.newline = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_PORDER:
                        this.order_of_pins = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_OC:
                        this.orig_colors = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_OP:
                        this.orig_pair = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_PAD:
                        this.pad_char = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_DCH:
                        this.parm_dch = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_DL:
                        this.parm_delete_line = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CUD:
                        this.parm_down_cursor = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MCUD:
                        this.parm_down_micro = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_ICH:
                        this.parm_ich = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_INDN:
                        this.parm_index = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_IL:
                        this.parm_insert_line = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CUB:
                        this.parm_left_cursor = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MCUB:
                        this.parm_left_micro = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CUF:
                        this.parm_right_cursor = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MCUF:
                        this.parm_right_micro = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RIN:
                        this.parm_rindex = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_CUU:
                        this.parm_up_cursor = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MCUU:
                        this.parm_up_micro = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_PCTRM:
                        this.pc_term_options = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_PFKEY:
                        this.pkey_key = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_PFLOC:
                        this.pkey_local = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_PFXL:
                        this.pkey_plab = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_PFX:
                        this.pkey_xmit = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_PLN:
                        this.plab_norm = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MC0:
                        this.print_screen = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MC5P:
                        this.prtr_non = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MC4:
                        this.prtr_off = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_MC5:
                        this.prtr_on = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_PULSE:
                        this.pulse = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_QDIAL:
                        this.quick_dial = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RMCLK:
                        this.remove_clock = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_REP:
                        this.repeat_char = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RFI:
                        this.req_for_input = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_REQMP:
                        this.req_mouse_pos = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RS1:
                        this.reset_1string = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RS2:
                        this.reset_2string = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RS3:
                        this.reset_3string = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RF:
                        this.reset_file = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RC:
                        this.restore_cursor = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_VPA:
                        this.row_address = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SC:
                        this.save_cursor = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SCESC:
                        this.scancode_escape = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_IND:
                        this.scroll_forward = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RI:
                        this.scroll_reverse = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SCS:
                        this.select_char_set = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_S0DS:
                        this.set0_des_seq = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_S1DS:
                        this.set1_des_seq = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_S2DS:
                        this.set2_des_seq = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_S3DS:
                        this.set3_des_seq = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SETAB:
                        this.set_a_background = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SETAF:
                        this.set_a_foreground = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SGR:
                        this.set_attributes = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SETB:
                        this.set_background = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMGB:
                        this.set_bottom_margin = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMGBP:
                        this.set_bottom_margin_parm = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SCLK:
                        this.set_clock = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SETCOLOR:
                        this.set_color_band = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SCP:
                        this.set_color_pair = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SETF:
                        this.set_foreground = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMGL:
                        this.set_left_margin = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMGLP:
                        this.set_left_margin_parm = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMGLR:
                        this.set_lr_margin = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SLINES:
                        this.set_page_length = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMGR:
                        this.set_right_margin = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMGRP:
                        this.set_right_margin_parm = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_HTS:
                        this.set_tab = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMGTB:
                        this.set_tb_margin = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMGT:
                        this.set_top_margin = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SMGTP:
                        this.set_top_margin_parm = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_WIND:
                        this.set_window = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SBIM:
                        this.start_bit_image = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SCSD:
                        this.start_char_set_def = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RBIM:
                        this.stop_bit_image = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_RCSD:
                        this.stop_char_set_def = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SUBCS:
                        this.subscript_characters = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_SUPCS:
                        this.superscript_characters = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_HT:
                        this.tab = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_DOCR:
                        this.these_cause_cr = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_TSL:
                        this.to_status_line = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_TONE:
                        this.tone = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_U0:
                        this.user0 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_U1:
                        this.user1 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_U2:
                        this.user2 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_U3:
                        this.user3 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_U4:
                        this.user4 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_U5:
                        this.user5 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_U6:
                        this.user6 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_U7:
                        this.user7 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_U8:
                        this.user8 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_U9:
                        this.user9 = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_UC:
                        this.underline_char = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_HU:
                        this.up_half_line = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_WAIT:
                        this.wait_tone = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_XOFFC:
                        this.xoff_character = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_XONC:
                        this.xon_character = val;
                        break;
                    case TermInfoCaps.CAPABILITY_ID_ZEROM:
                        this.zero_motion = val;
                        break;
                    default:
                        System.out.println("ERROR: String token [" + token + "] encountered.");
                }
            }
        }
    }

    private char[] valChars = new char[256];

    private synchronized String processEscapeChars(String val) {
        //
        //  A number of escape sequences are provided in the string valued
        //  capabilities for easy encoding of characters there.  Both \E and \e
        //  map to an ESCAPE character, ^x maps to Ctrl-x for any appropriate x,
        //  and the sequences \n, \l, \r, \t, \b, \f, and \s give a newline,
        //  linefeed, return, tab, backspace, formfeed, and space.  Other escapes
        //  include \^for ^, \\ for \, \, for comma, \: for :, and \0 for null.
        //  (\0 produces \200, which does not terminate a string, but behaves as a
        //  null character on most terminals.) Finally, characters may be
        //  specified as three octal digits after a \.
        //  Delay is specified as $<nnn>, where nnn is the delay in milliseconds

        //unescape and decode the string "val"

        // remove all $<...> sequences
        int valLength;
        for (int i = 0; i < 100; i++) {
            valLength = val.length();
            int delayIdxStart = val.indexOf("$<");
            if (delayIdxStart >= 0) {
                int delayIdxEnd = val.indexOf(">", delayIdxStart);
                if (delayIdxEnd > 0) {
                    String s1 = "";
                    String s2 = "";
                    if (delayIdxStart > 0)
                        s1 = val.substring(0, delayIdxStart);
                    if (delayIdxEnd < valLength - 1)
                        s2 = val.substring(delayIdxEnd + 1);
                    val = s1 + s2;
                } else
                    break;
            } else
                break;
        }
        // replace all \ escaped characters with the real representation
        //         and all ^x control characters with the real representation
        valLength = val.length();
        if (valLength > valChars.length)
            valChars = new char[valLength];
        val.getChars(0, valLength, valChars, 0);
        StringBuffer sb = new StringBuffer(valLength);
        boolean inBackSlash = false;
        boolean inCtrl = false;
        for (int i = 0; i < valLength; i++) {
            char ch = valChars[i];
            if (inBackSlash) {
                if (ch >= '0' && ch <= '9'
                    && i < valLength - 3
                    && valChars[i + 1] >= '0' && valChars[i + 1] <= '9'
                    && valChars[i + 2] >= '0' && valChars[i + 2] <= '9') {    // octal sequence
                    ch = (char) ((ch - '0') * 64 + (valChars[i + 1] - '0') * 8 + (valChars[i + 2] - '0'));
                    i += 2;
                } else {
                    switch (ch) {
                        case 'E':
                        case 'e':
                            ch = 0x1B;
                            break;
                        case 'N':
                        case 'n':
                            ch = '\n';
                            break;
                        case 'L':
                        case 'l':
                            System.out.println("\\l found for capability in val [" + val + "]");
                            System.out.println("Do not know what to to with \\l. Assuming \\n");
                            ch = '\n';
                            break;
                        case 'R':
                        case 'r':
                            ch = '\r';
                            break;
                        case 'T':
                        case 't':
                            ch = '\t';
                            break;
                        case 'B':
                        case 'b':
                            ch = '\b';
                            break;
                        case 'F':
                        case 'f':
                            ch = '\f';
                            break;
                        case 'S':
                        case 's':
                            ch = ' ';
                            break;
                        case '^':
                            ch = '^';
                            break;
                        case '\\':
                            ch = '\\';
                            break;
                        case ',':
                            ch = ',';
                            break;
                        case ':':
                            ch = ':';
                            break;
                        case '0':
                            ch = '\0';
                            break;
                        default:
                            sb.append('\\');
                    }
                }
                sb.append(ch);
                inBackSlash = false;
            } else if (inCtrl) {
                if (ch >= 'a'
                    && ch <= 'z')
                    ch = (char) (ch - 'a' + 1);
                else if (ch >= 'A'
                    && ch <= 'Z')
                    ch = (char) (ch - 'A' + 1);
                else
                    sb.append('^');
                sb.append(ch);
                inCtrl = false;
            } else {
                if (ch == '\\') {
                    inBackSlash = true;
                    continue;
                } else if (ch == '^') {
                    inCtrl = true;
                    continue;
                } else {
                    sb.append(ch);
                }
            }
        }
        return sb.toString();
    }

    private void addToParseTable(String s, int state) {
        int len;

        if (s != null
            && (len = s.length()) > 0) {
            parseTable[parseTableLength] = new int[len + 1];
            parseTable[parseTableLength][0] = state;
            for (int i = 0; i < len; i++)
                parseTable[parseTableLength][i + 1] = s.charAt(i);
            parseTableLength++;
        }
    }

    /**
     * Capability start characters are those characters that may be sent by the
     * host to this terminal emulator.
     */
    private void buildParseTable() {
        parseTable = new int[400][];
        parseTableLength = 0;

        //addToParseTable(this.acs_chars, CMD_STATE_ACS_CHARS );
        //addToParseTable(this.alt_scancode_esc, CMD_STATE_ALT_SCANCODE_ESC );
        addToParseTable(this.back_tab, CMD_STATE_BACK_TAB);
        addToParseTable(this.bell, CMD_STATE_BELL);
        addToParseTable(this.bit_image_carriage_return, CMD_STATE_BIT_IMAGE_CARRIAGE_RETURN);
        addToParseTable(this.bit_image_newline, CMD_STATE_BIT_IMAGE_NEWLINE);
        addToParseTable(this.bit_image_repeat, CMD_STATE_BIT_IMAGE_REPEAT);
        addToParseTable(this.carriage_return, CMD_STATE_CARRIAGE_RETURN);
        addToParseTable(this.change_char_pitch, CMD_STATE_CHANGE_CHAR_PITCH);
        addToParseTable(this.change_line_pitch, CMD_STATE_CHANGE_LINE_PITCH);
        addToParseTable(this.change_res_horz, CMD_STATE_CHANGE_RES_HORZ);
        addToParseTable(this.change_res_vert, CMD_STATE_CHANGE_RES_VERT);
        addToParseTable(this.change_scroll_region, CMD_STATE_CHANGE_SCROLL_REGION);
        addToParseTable(this.char_padding, CMD_STATE_CHAR_PADDING);
        //addToParseTable(this.char_set_names, CMD_STATE_CHAR_SET_NAMES );
        addToParseTable(this.clear_all_tabs, CMD_STATE_CLEAR_ALL_TABS);
        addToParseTable(this.clear_margins, CMD_STATE_CLEAR_MARGINS);
        addToParseTable(this.clear_screen, CMD_STATE_CLEAR_SCREEN);
        addToParseTable(this.clr_bol, CMD_STATE_CLR_BOL);
        addToParseTable(this.clr_eol, CMD_STATE_CLR_EOL);
        addToParseTable(this.clr_eos, CMD_STATE_CLR_EOS);
        addToParseTable(this.code_set_init, CMD_STATE_CODE_SET_INIT);
        addToParseTable(this.color_names, CMD_STATE_COLOR_NAMES);
        addToParseTable(this.column_address, CMD_STATE_COLUMN_ADDRESS);
        addToParseTable(this.command_character, CMD_STATE_COMMAND_CHARACTER);
        addToParseTable(this.create_window, CMD_STATE_CREATE_WINDOW);
        addToParseTable(this.cursor_address, CMD_STATE_CURSOR_ADDRESS);
        addToParseTable(this.cursor_down, CMD_STATE_CURSOR_DOWN);
        addToParseTable(this.cursor_home, CMD_STATE_CURSOR_HOME);
        addToParseTable(this.cursor_invisible, CMD_STATE_CURSOR_INVISIBLE);
        addToParseTable(this.cursor_left, CMD_STATE_CURSOR_LEFT);
        addToParseTable(this.cursor_mem_address, CMD_STATE_CURSOR_MEM_ADDRESS);
        addToParseTable(this.cursor_normal, CMD_STATE_CURSOR_NORMAL);
        addToParseTable(this.cursor_right, CMD_STATE_CURSOR_RIGHT);
        addToParseTable(this.cursor_to_ll, CMD_STATE_CURSOR_TO_LL);
        addToParseTable(this.cursor_up, CMD_STATE_CURSOR_UP);
        addToParseTable(this.cursor_visible, CMD_STATE_CURSOR_VISIBLE);
        addToParseTable(this.define_bit_image_region, CMD_STATE_DEFINE_BIT_IMAGE_REGION);
        addToParseTable(this.define_char, CMD_STATE_DEFINE_CHAR);
        addToParseTable(this.delete_character, CMD_STATE_DELETE_CHARACTER);
        addToParseTable(this.delete_line, CMD_STATE_DELETE_LINE);
        addToParseTable(this.device_type, CMD_STATE_DEVICE_TYPE);
        addToParseTable(this.dial_phone, CMD_STATE_DIAL_PHONE);
        addToParseTable(this.dis_status_line, CMD_STATE_DIS_STATUS_LINE);
        addToParseTable(this.display_clock, CMD_STATE_DISPLAY_CLOCK);
        addToParseTable(this.display_pc_char, CMD_STATE_DISPLAY_PC_CHAR);
        addToParseTable(this.down_half_line, CMD_STATE_DOWN_HALF_LINE);
        addToParseTable(this.ena_acs, CMD_STATE_ENA_ACS);
        addToParseTable(this.end_bit_image_region, CMD_STATE_END_BIT_IMAGE_REGION);
        addToParseTable(this.enter_alt_charset_mode, CMD_STATE_ENTER_ALT_CHARSET_MODE);
        addToParseTable(this.enter_am_mode, CMD_STATE_ENTER_AM_MODE);
        addToParseTable(this.enter_blink_mode, CMD_STATE_ENTER_BLINK_MODE);
        addToParseTable(this.enter_bold_mode, CMD_STATE_ENTER_BOLD_MODE);
        addToParseTable(this.enter_ca_mode, CMD_STATE_ENTER_CA_MODE);
        addToParseTable(this.enter_delete_mode, CMD_STATE_ENTER_DELETE_MODE);
        addToParseTable(this.enter_dim_mode, CMD_STATE_ENTER_DIM_MODE);
        addToParseTable(this.enter_doublewide_mode, CMD_STATE_ENTER_DOUBLEWIDE_MODE);
        addToParseTable(this.enter_draft_quality, CMD_STATE_ENTER_DRAFT_QUALITY);
        addToParseTable(this.enter_insert_mode, CMD_STATE_ENTER_INSERT_MODE);
        addToParseTable(this.enter_italics_mode, CMD_STATE_ENTER_ITALICS_MODE);
        addToParseTable(this.enter_leftward_mode, CMD_STATE_ENTER_LEFTWARD_MODE);
        addToParseTable(this.enter_micro_mode, CMD_STATE_ENTER_MICRO_MODE);
        addToParseTable(this.enter_near_letter_quality, CMD_STATE_ENTER_NEAR_LETTER_QUALITY);
        addToParseTable(this.enter_normal_quality, CMD_STATE_ENTER_NORMAL_QUALITY);
        addToParseTable(this.enter_pc_charset_mode, CMD_STATE_ENTER_PC_CHARSET_MODE);
        addToParseTable(this.enter_protected_mode, CMD_STATE_ENTER_PROTECTED_MODE);
        addToParseTable(this.enter_reverse_mode, CMD_STATE_ENTER_REVERSE_MODE);
        addToParseTable(this.enter_scancode_mode, CMD_STATE_ENTER_SCANCODE_MODE);
        addToParseTable(this.enter_secure_mode, CMD_STATE_ENTER_SECURE_MODE);
        addToParseTable(this.enter_shadow_mode, CMD_STATE_ENTER_SHADOW_MODE);
        addToParseTable(this.enter_standout_mode, CMD_STATE_ENTER_STANDOUT_MODE);
        addToParseTable(this.enter_subscript_mode, CMD_STATE_ENTER_SUBSCRIPT_MODE);
        addToParseTable(this.enter_superscript_mode, CMD_STATE_ENTER_SUPERSCRIPT_MODE);
        addToParseTable(this.enter_underline_mode, CMD_STATE_ENTER_UNDERLINE_MODE);
        addToParseTable(this.enter_upward_mode, CMD_STATE_ENTER_UPWARD_MODE);
        addToParseTable(this.enter_xon_mode, CMD_STATE_ENTER_XON_MODE);
        addToParseTable(this.erase_chars, CMD_STATE_ERASE_CHARS);
        addToParseTable(this.exit_alt_charset_mode, CMD_STATE_EXIT_ALT_CHARSET_MODE);
        addToParseTable(this.exit_am_mode, CMD_STATE_EXIT_AM_MODE);
        addToParseTable(this.exit_attribute_mode, CMD_STATE_EXIT_ATTRIBUTE_MODE);
        addToParseTable(this.exit_ca_mode, CMD_STATE_EXIT_CA_MODE);
        addToParseTable(this.exit_delete_mode, CMD_STATE_EXIT_DELETE_MODE);
        addToParseTable(this.exit_doublewide_mode, CMD_STATE_EXIT_DOUBLEWIDE_MODE);
        addToParseTable(this.exit_insert_mode, CMD_STATE_EXIT_INSERT_MODE);
        addToParseTable(this.exit_italics_mode, CMD_STATE_EXIT_ITALICS_MODE);
        addToParseTable(this.exit_leftward_mode, CMD_STATE_EXIT_LEFTWARD_MODE);
        addToParseTable(this.exit_micro_mode, CMD_STATE_EXIT_MICRO_MODE);
        addToParseTable(this.exit_pc_charset_mode, CMD_STATE_EXIT_PC_CHARSET_MODE);
        addToParseTable(this.exit_scancode_mode, CMD_STATE_EXIT_SCANCODE_MODE);
        addToParseTable(this.exit_shadow_mode, CMD_STATE_EXIT_SHADOW_MODE);
        addToParseTable(this.exit_subscript_mode, CMD_STATE_EXIT_SUBSCRIPT_MODE);
        addToParseTable(this.exit_superscript_mode, CMD_STATE_EXIT_SUPERSCRIPT_MODE);
        addToParseTable(this.exit_standout_mode, CMD_STATE_EXIT_STANDOUT_MODE);
        addToParseTable(this.exit_underline_mode, CMD_STATE_EXIT_UNDERLINE_MODE);
        addToParseTable(this.exit_upward_mode, CMD_STATE_EXIT_UPWARD_MODE);
        addToParseTable(this.exit_xon_mode, CMD_STATE_EXIT_XON_MODE);
        addToParseTable(this.fixed_pause, CMD_STATE_FIXED_PAUSE);
        addToParseTable(this.flash_hook, CMD_STATE_FLASH_HOOK);
        addToParseTable(this.flash_screen, CMD_STATE_FLASH_SCREEN);
        addToParseTable(this.form_feed, CMD_STATE_FORM_FEED);
        addToParseTable(this.from_status_line, CMD_STATE_FROM_STATUS_LINE);
        addToParseTable(this.get_mouse, CMD_STATE_GET_MOUSE);
        addToParseTable(this.goto_window, CMD_STATE_GOTO_WINDOW);
        addToParseTable(this.hangup, CMD_STATE_HANGUP);
        addToParseTable(this.init_1string, CMD_STATE_INIT_1STRING);
        addToParseTable(this.init_2string, CMD_STATE_INIT_2STRING);
        addToParseTable(this.init_3string, CMD_STATE_INIT_3STRING);
        addToParseTable(this.init_file, CMD_STATE_INIT_FILE);
        addToParseTable(this.init_prog, CMD_STATE_INIT_PROG);
        addToParseTable(this.initialize_color, CMD_STATE_INITIALIZE_COLOR);
        addToParseTable(this.initialize_pair, CMD_STATE_INITIALIZE_PAIR);
        addToParseTable(this.insert_character, CMD_STATE_INSERT_CHARACTER);
        addToParseTable(this.insert_line, CMD_STATE_INSERT_LINE);
        addToParseTable(this.insert_padding, CMD_STATE_INSERT_PADDING);
        //addToParseTable(this.key_a1, CMD_STATE_KEY_A1 );
        //addToParseTable(this.key_a3, CMD_STATE_KEY_A3 );
        //addToParseTable(this.key_b2, CMD_STATE_KEY_B2 );
        //addToParseTable(this.key_backspace, CMD_STATE_KEY_BACKSPACE );
        //addToParseTable(this.key_beg, CMD_STATE_KEY_BEG );
        //addToParseTable(this.key_btab, CMD_STATE_KEY_BTAB );
        //addToParseTable(this.key_c1, CMD_STATE_KEY_C1 );
        //addToParseTable(this.key_c3, CMD_STATE_KEY_C3 );
        //addToParseTable(this.key_cancel, CMD_STATE_KEY_CANCEL );
        //addToParseTable(this.key_catab, CMD_STATE_KEY_CATAB );
        //addToParseTable(this.key_clear, CMD_STATE_KEY_CLEAR );
        //addToParseTable(this.key_close, CMD_STATE_KEY_CLOSE );
        //addToParseTable(this.key_command, CMD_STATE_KEY_COMMAND );
        //addToParseTable(this.key_copy, CMD_STATE_KEY_COPY );
        //addToParseTable(this.key_create, CMD_STATE_KEY_CREATE );
        //addToParseTable(this.key_ctab, CMD_STATE_KEY_CTAB );
        //addToParseTable(this.key_dc, CMD_STATE_KEY_DC );
        //addToParseTable(this.key_dl, CMD_STATE_KEY_DL );
        //addToParseTable(this.key_down, CMD_STATE_KEY_DOWN );
        //addToParseTable(this.key_eic, CMD_STATE_KEY_EIC );
        //addToParseTable(this.key_end, CMD_STATE_KEY_END );
        //addToParseTable(this.key_enter, CMD_STATE_KEY_ENTER );
        //addToParseTable(this.key_eol, CMD_STATE_KEY_EOL );
        //addToParseTable(this.key_eos, CMD_STATE_KEY_EOS );
        //addToParseTable(this.key_exit, CMD_STATE_KEY_EXIT );
        //addToParseTable(this.key_f0, CMD_STATE_KEY_F0 );
        //addToParseTable(this.key_f1, CMD_STATE_KEY_F1 );
        //addToParseTable(this.key_f2, CMD_STATE_KEY_F2 );
        //addToParseTable(this.key_f3, CMD_STATE_KEY_F3 );
        //addToParseTable(this.key_f4, CMD_STATE_KEY_F4 );
        //addToParseTable(this.key_f5, CMD_STATE_KEY_F5 );
        //addToParseTable(this.key_f6, CMD_STATE_KEY_F6 );
        //addToParseTable(this.key_f7, CMD_STATE_KEY_F7 );
        //addToParseTable(this.key_f8, CMD_STATE_KEY_F8 );
        //addToParseTable(this.key_f9, CMD_STATE_KEY_F9 );
        //addToParseTable(this.key_f10, CMD_STATE_KEY_F10 );
        //addToParseTable(this.key_f11, CMD_STATE_KEY_F11 );
        //addToParseTable(this.key_f12, CMD_STATE_KEY_F12 );
        //addToParseTable(this.key_f13, CMD_STATE_KEY_F13 );
        //addToParseTable(this.key_f14, CMD_STATE_KEY_F14 );
        //addToParseTable(this.key_f15, CMD_STATE_KEY_F15 );
        //addToParseTable(this.key_f16, CMD_STATE_KEY_F16 );
        //addToParseTable(this.key_f17, CMD_STATE_KEY_F17 );
        //addToParseTable(this.key_f18, CMD_STATE_KEY_F18 );
        //addToParseTable(this.key_f19, CMD_STATE_KEY_F19 );
        //addToParseTable(this.key_f20, CMD_STATE_KEY_F20 );
        //addToParseTable(this.key_f21, CMD_STATE_KEY_F21 );
        //addToParseTable(this.key_f22, CMD_STATE_KEY_F22 );
        //addToParseTable(this.key_f23, CMD_STATE_KEY_F23 );
        //addToParseTable(this.key_f24, CMD_STATE_KEY_F24 );
        //addToParseTable(this.key_f25, CMD_STATE_KEY_F25 );
        //addToParseTable(this.key_f26, CMD_STATE_KEY_F26 );
        //addToParseTable(this.key_f27, CMD_STATE_KEY_F27 );
        //addToParseTable(this.key_f28, CMD_STATE_KEY_F28 );
        //addToParseTable(this.key_f29, CMD_STATE_KEY_F29 );
        //addToParseTable(this.key_f30, CMD_STATE_KEY_F30 );
        //addToParseTable(this.key_f31, CMD_STATE_KEY_F31 );
        //addToParseTable(this.key_f32, CMD_STATE_KEY_F32 );
        //addToParseTable(this.key_f33, CMD_STATE_KEY_F33 );
        //addToParseTable(this.key_f34, CMD_STATE_KEY_F34 );
        //addToParseTable(this.key_f35, CMD_STATE_KEY_F35 );
        //addToParseTable(this.key_f36, CMD_STATE_KEY_F36 );
        //addToParseTable(this.key_f37, CMD_STATE_KEY_F37 );
        //addToParseTable(this.key_f38, CMD_STATE_KEY_F38 );
        //addToParseTable(this.key_f39, CMD_STATE_KEY_F39 );
        //addToParseTable(this.key_f40, CMD_STATE_KEY_F40 );
        //addToParseTable(this.key_f41, CMD_STATE_KEY_F41 );
        //addToParseTable(this.key_f42, CMD_STATE_KEY_F42 );
        //addToParseTable(this.key_f43, CMD_STATE_KEY_F43 );
        //addToParseTable(this.key_f44, CMD_STATE_KEY_F44 );
        //addToParseTable(this.key_f45, CMD_STATE_KEY_F45 );
        //addToParseTable(this.key_f46, CMD_STATE_KEY_F46 );
        //addToParseTable(this.key_f47, CMD_STATE_KEY_F47 );
        //addToParseTable(this.key_f48, CMD_STATE_KEY_F48 );
        //addToParseTable(this.key_f49, CMD_STATE_KEY_F49 );
        //addToParseTable(this.key_f50, CMD_STATE_KEY_F50 );
        //addToParseTable(this.key_f51, CMD_STATE_KEY_F51 );
        //addToParseTable(this.key_f52, CMD_STATE_KEY_F52 );
        //addToParseTable(this.key_f53, CMD_STATE_KEY_F53 );
        //addToParseTable(this.key_f54, CMD_STATE_KEY_F54 );
        //addToParseTable(this.key_f55, CMD_STATE_KEY_F55 );
        //addToParseTable(this.key_f56, CMD_STATE_KEY_F56 );
        //addToParseTable(this.key_f57, CMD_STATE_KEY_F57 );
        //addToParseTable(this.key_f58, CMD_STATE_KEY_F58 );
        //addToParseTable(this.key_f59, CMD_STATE_KEY_F59 );
        //addToParseTable(this.key_f60, CMD_STATE_KEY_F60 );
        //addToParseTable(this.key_f61, CMD_STATE_KEY_F61 );
        //addToParseTable(this.key_f62, CMD_STATE_KEY_F62 );
        //addToParseTable(this.key_f63, CMD_STATE_KEY_F63 );
        //addToParseTable(this.key_find, CMD_STATE_KEY_FIND );
        //addToParseTable(this.key_help, CMD_STATE_KEY_HELP );
        //addToParseTable(this.key_home, CMD_STATE_KEY_HOME );
        //addToParseTable(this.key_ic, CMD_STATE_KEY_IC );
        //addToParseTable(this.key_il, CMD_STATE_KEY_IL );
        //addToParseTable(this.key_left, CMD_STATE_KEY_LEFT );
        //addToParseTable(this.key_ll, CMD_STATE_KEY_LL );
        //addToParseTable(this.key_mark, CMD_STATE_KEY_MARK );
        //addToParseTable(this.key_message, CMD_STATE_KEY_MESSAGE );
        //addToParseTable(this.key_mouse, CMD_STATE_KEY_MOUSE );
        //addToParseTable(this.key_move, CMD_STATE_KEY_MOVE );
        //addToParseTable(this.key_next, CMD_STATE_KEY_NEXT );
        //addToParseTable(this.key_npage, CMD_STATE_KEY_NPAGE );
        //addToParseTable(this.key_open, CMD_STATE_KEY_OPEN );
        //addToParseTable(this.key_options, CMD_STATE_KEY_OPTIONS );
        //addToParseTable(this.key_ppage, CMD_STATE_KEY_PPAGE );
        //addToParseTable(this.key_previous, CMD_STATE_KEY_PREVIOUS );
        //addToParseTable(this.key_print, CMD_STATE_KEY_PRINT );
        //addToParseTable(this.key_redo, CMD_STATE_KEY_REDO );
        //addToParseTable(this.key_reference, CMD_STATE_KEY_REFERENCE );
        //addToParseTable(this.key_refresh, CMD_STATE_KEY_REFRESH );
        //addToParseTable(this.key_replace, CMD_STATE_KEY_REPLACE );
        //addToParseTable(this.key_restart, CMD_STATE_KEY_RESTART );
        //addToParseTable(this.key_resume, CMD_STATE_KEY_RESUME );
        //addToParseTable(this.key_right, CMD_STATE_KEY_RIGHT );
        //addToParseTable(this.key_save, CMD_STATE_KEY_SAVE );
        //addToParseTable(this.key_sbeg, CMD_STATE_KEY_SBEG );
        //addToParseTable(this.key_scancel, CMD_STATE_KEY_SCANCEL );
        //addToParseTable(this.key_scommand, CMD_STATE_KEY_SCOMMAND );
        //addToParseTable(this.key_scopy, CMD_STATE_KEY_SCOPY );
        //addToParseTable(this.key_screate, CMD_STATE_KEY_SCREATE );
        //addToParseTable(this.key_sdc, CMD_STATE_KEY_SDC );
        //addToParseTable(this.key_sdl, CMD_STATE_KEY_SDL );
        //addToParseTable(this.key_select, CMD_STATE_KEY_SELECT );
        //addToParseTable(this.key_send, CMD_STATE_KEY_SEND );
        //addToParseTable(this.key_seol, CMD_STATE_KEY_SEOL );
        //addToParseTable(this.key_sexit, CMD_STATE_KEY_SEXIT );
        //addToParseTable(this.key_sf, CMD_STATE_KEY_SF );
        //addToParseTable(this.key_sfind, CMD_STATE_KEY_SFIND );
        //addToParseTable(this.key_shelp, CMD_STATE_KEY_SHELP );
        //addToParseTable(this.key_shome, CMD_STATE_KEY_SHOME );
        //addToParseTable(this.key_sic, CMD_STATE_KEY_SIC );
        //addToParseTable(this.key_sleft, CMD_STATE_KEY_SLEFT );
        //addToParseTable(this.key_smessage, CMD_STATE_KEY_SMESSAGE );
        //addToParseTable(this.key_smove, CMD_STATE_KEY_SMOVE );
        //addToParseTable(this.key_snext, CMD_STATE_KEY_SNEXT );
        //addToParseTable(this.key_soptions, CMD_STATE_KEY_SOPTIONS );
        //addToParseTable(this.key_sprevious, CMD_STATE_KEY_SPREVIOUS );
        //addToParseTable(this.key_sprint, CMD_STATE_KEY_SPRINT );
        //addToParseTable(this.key_sr, CMD_STATE_KEY_SR );
        //addToParseTable(this.key_sredo, CMD_STATE_KEY_SREDO );
        //addToParseTable(this.key_sreplace, CMD_STATE_KEY_SREPLACE );
        //addToParseTable(this.key_sright, CMD_STATE_KEY_SRIGHT );
        //addToParseTable(this.key_srsume, CMD_STATE_KEY_SRSUME );
        //addToParseTable(this.key_ssave, CMD_STATE_KEY_SSAVE );
        //addToParseTable(this.key_ssuspend, CMD_STATE_KEY_SSUSPEND );
        //addToParseTable(this.key_stab, CMD_STATE_KEY_STAB );
        //addToParseTable(this.key_sundo, CMD_STATE_KEY_SUNDO );
        //addToParseTable(this.key_suspend, CMD_STATE_KEY_SUSPEND );
        //addToParseTable(this.key_undo, CMD_STATE_KEY_UNDO );
        //addToParseTable(this.key_up, CMD_STATE_KEY_UP );
        //addToParseTable(this.keypad_local, CMD_STATE_KEYPAD_LOCAL );
        //addToParseTable(this.keypad_xmit, CMD_STATE_KEYPAD_XMIT );
        //addToParseTable(this.lab_f0, CMD_STATE_LAB_F0 );
        //addToParseTable(this.lab_f1, CMD_STATE_LAB_F1 );
        //addToParseTable(this.lab_f10, CMD_STATE_LAB_F10 );
        //addToParseTable(this.lab_f2, CMD_STATE_LAB_F2 );
        //addToParseTable(this.lab_f3, CMD_STATE_LAB_F3 );
        //addToParseTable(this.lab_f4, CMD_STATE_LAB_F4 );
        //addToParseTable(this.lab_f5, CMD_STATE_LAB_F5 );
        //addToParseTable(this.lab_f6, CMD_STATE_LAB_F6 );
        //addToParseTable(this.lab_f7, CMD_STATE_LAB_F7 );
        //addToParseTable(this.lab_f8, CMD_STATE_LAB_F8 );
        //addToParseTable(this.lab_f9, CMD_STATE_LAB_F9 );
        //addToParseTable(this.label_format, CMD_STATE_LABEL_FORMAT );
        addToParseTable(this.label_off, CMD_STATE_LABEL_OFF);
        addToParseTable(this.label_on, CMD_STATE_LABEL_ON);
        addToParseTable(this.memory_lock, CMD_STATE_MEMORY_LOCK);
        addToParseTable(this.memory_unlock, CMD_STATE_MEMORY_UNLOCK);
        addToParseTable(this.meta_off, CMD_STATE_META_OFF);
        addToParseTable(this.meta_on, CMD_STATE_META_ON);
        addToParseTable(this.micro_column_address, CMD_STATE_MICRO_COLUMN_ADDRESS);
        addToParseTable(this.micro_down, CMD_STATE_MICRO_DOWN);
        addToParseTable(this.micro_left, CMD_STATE_MICRO_LEFT);
        addToParseTable(this.micro_right, CMD_STATE_MICRO_RIGHT);
        addToParseTable(this.micro_row_address, CMD_STATE_MICRO_ROW_ADDRESS);
        addToParseTable(this.micro_up, CMD_STATE_MICRO_UP);
        addToParseTable(this.mouse_info, CMD_STATE_MOUSE_INFO);
        addToParseTable(this.newline, CMD_STATE_NEWLINE);
        addToParseTable(this.order_of_pins, CMD_STATE_ORDER_OF_PINS);
        addToParseTable(this.orig_colors, CMD_STATE_ORIG_COLORS);
        addToParseTable(this.orig_pair, CMD_STATE_ORIG_PAIR);
        addToParseTable(this.pad_char, CMD_STATE_PAD_CHAR);
        addToParseTable(this.parm_dch, CMD_STATE_PARM_DCH);
        addToParseTable(this.parm_delete_line, CMD_STATE_PARM_DELETE_LINE);
        addToParseTable(this.parm_down_cursor, CMD_STATE_PARM_DOWN_CURSOR);
        addToParseTable(this.parm_down_micro, CMD_STATE_PARM_DOWN_MICRO);
        addToParseTable(this.parm_ich, CMD_STATE_PARM_ICH);
        addToParseTable(this.parm_index, CMD_STATE_PARM_INDEX);
        addToParseTable(this.parm_insert_line, CMD_STATE_PARM_INSERT_LINE);
        addToParseTable(this.parm_left_cursor, CMD_STATE_PARM_LEFT_CURSOR);
        addToParseTable(this.parm_left_micro, CMD_STATE_PARM_LEFT_MICRO);
        addToParseTable(this.parm_right_cursor, CMD_STATE_PARM_RIGHT_CURSOR);
        addToParseTable(this.parm_right_micro, CMD_STATE_PARM_RIGHT_MICRO);
        addToParseTable(this.parm_rindex, CMD_STATE_PARM_RINDEX);
        addToParseTable(this.parm_up_cursor, CMD_STATE_PARM_UP_CURSOR);
        addToParseTable(this.parm_up_micro, CMD_STATE_PARM_UP_MICRO);
        addToParseTable(this.pc_term_options, CMD_STATE_PC_TERM_OPTIONS);
        addToParseTable(this.pkey_key, CMD_STATE_PKEY_KEY);
        addToParseTable(this.pkey_local, CMD_STATE_PKEY_LOCAL);
        addToParseTable(this.pkey_plab, CMD_STATE_PKEY_PLAB);
        addToParseTable(this.pkey_xmit, CMD_STATE_PKEY_XMIT);
        addToParseTable(this.plab_norm, CMD_STATE_PLAB_NORM);
        addToParseTable(this.print_screen, CMD_STATE_PRINT_SCREEN);
        addToParseTable(this.prtr_non, CMD_STATE_PRTR_NON);
        addToParseTable(this.prtr_off, CMD_STATE_PRTR_OFF);
        addToParseTable(this.prtr_on, CMD_STATE_PRTR_ON);
        addToParseTable(this.pulse, CMD_STATE_PULSE);
        addToParseTable(this.quick_dial, CMD_STATE_QUICK_DIAL);
        addToParseTable(this.remove_clock, CMD_STATE_REMOVE_CLOCK);
        addToParseTable(this.repeat_char, CMD_STATE_REPEAT_CHAR);
        addToParseTable(this.req_for_input, CMD_STATE_REQ_FOR_INPUT);
        addToParseTable(this.req_mouse_pos, CMD_STATE_REQ_MOUSE_POS);
        addToParseTable(this.reset_1string, CMD_STATE_RESET_1STRING);
        addToParseTable(this.reset_2string, CMD_STATE_RESET_2STRING);
        addToParseTable(this.reset_3string, CMD_STATE_RESET_3STRING);
        addToParseTable(this.reset_file, CMD_STATE_RESET_FILE);
        addToParseTable(this.restore_cursor, CMD_STATE_RESTORE_CURSOR);
        addToParseTable(this.row_address, CMD_STATE_ROW_ADDRESS);
        addToParseTable(this.save_cursor, CMD_STATE_SAVE_CURSOR);
        addToParseTable(this.scancode_escape, CMD_STATE_SCANCODE_ESCAPE);
        addToParseTable(this.scroll_forward, CMD_STATE_SCROLL_FORWARD);
        addToParseTable(this.scroll_reverse, CMD_STATE_SCROLL_REVERSE);
        addToParseTable(this.select_char_set, CMD_STATE_SELECT_CHAR_SET);
        addToParseTable(this.set0_des_seq, CMD_STATE_SET0_DES_SEQ);
        addToParseTable(this.set1_des_seq, CMD_STATE_SET1_DES_SEQ);
        addToParseTable(this.set2_des_seq, CMD_STATE_SET2_DES_SEQ);
        addToParseTable(this.set3_des_seq, CMD_STATE_SET3_DES_SEQ);
        addToParseTable(this.set_a_background, CMD_STATE_SET_A_BACKGROUND);
        addToParseTable(this.set_a_foreground, CMD_STATE_SET_A_FOREGROUND);
        addToParseTable(this.set_attributes, CMD_STATE_SET_ATTRIBUTES);
        addToParseTable(this.set_background, CMD_STATE_SET_BACKGROUND);
        addToParseTable(this.set_bottom_margin, CMD_STATE_SET_BOTTOM_MARGIN);
        addToParseTable(this.set_bottom_margin_parm, CMD_STATE_SET_BOTTOM_MARGIN_PARM);
        addToParseTable(this.set_clock, CMD_STATE_SET_CLOCK);
        addToParseTable(this.set_color_band, CMD_STATE_SET_COLOR_BAND);
        addToParseTable(this.set_color_pair, CMD_STATE_SET_COLOR_PAIR);
        addToParseTable(this.set_foreground, CMD_STATE_SET_FOREGROUND);
        addToParseTable(this.set_left_margin, CMD_STATE_SET_LEFT_MARGIN);
        addToParseTable(this.set_left_margin_parm, CMD_STATE_SET_LEFT_MARGIN_PARM);
        addToParseTable(this.set_lr_margin, CMD_STATE_SET_LR_MARGIN);
        addToParseTable(this.set_page_length, CMD_STATE_SET_PAGE_LENGTH);
        addToParseTable(this.set_right_margin, CMD_STATE_SET_RIGHT_MARGIN);
        addToParseTable(this.set_right_margin_parm, CMD_STATE_SET_RIGHT_MARGIN_PARM);
        addToParseTable(this.set_tab, CMD_STATE_SET_TAB);
        addToParseTable(this.set_tb_margin, CMD_STATE_SET_TB_MARGIN);
        addToParseTable(this.set_top_margin, CMD_STATE_SET_TOP_MARGIN);
        addToParseTable(this.set_top_margin_parm, CMD_STATE_SET_TOP_MARGIN_PARM);
        addToParseTable(this.set_window, CMD_STATE_SET_WINDOW);
        addToParseTable(this.start_bit_image, CMD_STATE_START_BIT_IMAGE);
        addToParseTable(this.start_char_set_def, CMD_STATE_START_CHAR_SET_DEF);
        addToParseTable(this.stop_bit_image, CMD_STATE_STOP_BIT_IMAGE);
        addToParseTable(this.stop_char_set_def, CMD_STATE_STOP_CHAR_SET_DEF);
        addToParseTable(this.subscript_characters, CMD_STATE_SUBSCRIPT_CHARACTERS);
        addToParseTable(this.superscript_characters, CMD_STATE_SUPERSCRIPT_CHARACTERS);
        addToParseTable(this.tab, CMD_STATE_TAB);
        addToParseTable(this.these_cause_cr, CMD_STATE_THESE_CAUSE_CR);
        addToParseTable(this.to_status_line, CMD_STATE_TO_STATUS_LINE);
        addToParseTable(this.tone, CMD_STATE_TONE);
        addToParseTable(this.user0, CMD_STATE_USER0);
        addToParseTable(this.user1, CMD_STATE_USER1);
        addToParseTable(this.user2, CMD_STATE_USER2);
        addToParseTable(this.user3, CMD_STATE_USER3);
        addToParseTable(this.user4, CMD_STATE_USER4);
        addToParseTable(this.user5, CMD_STATE_USER5);
        addToParseTable(this.user6, CMD_STATE_USER6);
        addToParseTable(this.user7, CMD_STATE_USER7);
        addToParseTable(this.user8, CMD_STATE_USER8);
        addToParseTable(this.user9, CMD_STATE_USER9);
        addToParseTable(this.wait_tone, CMD_STATE_WAIT_TONE);
        addToParseTable(this.xoff_character, CMD_STATE_XOFF_CHARACTER);
        addToParseTable(this.xon_character, CMD_STATE_XON_CHARACTER);
        addToParseTable(this.zero_motion, CMD_STATE_ZERO_MOTION);
        addToParseTable(this.underline_char, CMD_STATE_UNDERLINE_CHAR);
        addToParseTable(this.up_half_line, CMD_STATE_UP_HALF_LINE);

        // squeeze parseTable to be only as big as the number of rows in it.
        if (parseTableLength > 0) {
            int[][] newParseTable = new int[parseTableLength][];
            for (int i = 0; i < parseTableLength; i++)
                newParseTable[i] = parseTable[i];
            parseTable = newParseTable;
        } else
            parseTable = null;

        // now build the list of characters that can start a command
        // first is a character, followed by the row in parseTable for the
        // first command that begins with this character.
        int[][] startChars = new int[128][2];
        int charCnt = 0;

        for (int i = 0; i < parseTableLength; i++) {
            char ch = (char) parseTable[i][1];
            boolean found = false;
            for (int j = 0; j < charCnt; j++) {
                if (startChars[j][0] == ch) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                startChars[charCnt][0] = ch;
                startChars[charCnt][1] = i;    // index into parseTable
                charCnt++;
            }
        }
        if (charCnt > 0) {
            parseStateStartChars = new int[charCnt][2];
            for (int i = 0; i < charCnt; i++)
                parseStateStartChars[i] = startChars[i];
        } else
            parseStateStartChars = null;
    }

    public static void main(String[] args) {
        char[] charBuf = new char[2048];
        TermInfo termInfo = new TermInfo();

        if (args.length != 2) {
            System.out.println("Please supply two arguments: <terminalName> <unticFilePath>");
            System.exit(1);
        }
        //for (int i = 0 ; i < args.length ; i++)
        {
            //String termName = args[i];
            //if (termName.lastIndexOf('/') >= 0)
            //	termName = termName.substring(termName.lastIndexOf('/') + 1);
            //// create the untic file
            //try {
            //	String execString = "untic " + termName + " >/tmp/del.1";
            //	System.out.println("Executing [" + execString + "]");
            //	Runtime.getRuntime().exec(execString);
            //} catch (Exception e)
            //{
            //	e.printStackTrace();
            //	System.out.println("=======================================");
            //	System.out.println(" ERROR: untic Execution failed terminal [" + termName + "]");
            //	System.out.println("=======================================");
            //	continue;
            //}
            //File	tmpFile = new File("/tmp/del.1");
            File termName = new File(args[0]);
            File tmpFile = new File(args[1]);
            int fileSize = (int) tmpFile.length();
            if (fileSize < 1) {
                System.out.println("=======================================");
                System.out.println(" ERROR: File " + tmpFile.getPath() + " is zero length for termName [" + termName + "]");
                System.out.println("=======================================");
            } else {
                if (fileSize > charBuf.length)
                    charBuf = new char[fileSize];

                FileReader fileReader = null;
                try {
                    fileReader = new FileReader(tmpFile);
                    int charsRead = fileReader.read(charBuf, 0, fileSize);
                    if (charsRead > 0) {
                        String info = new String(charBuf, 0, charsRead);
                        termInfo.parseFromTermcaps(info);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("=======================================");
                    System.out.println(" ERROR: Reading file failed for termName [" + termName + "]");
                    System.out.println("=======================================");
                    //continue;
                } finally {
                    try {
                        if (fileReader != null)
                            fileReader.close();
                    } catch (Exception e) {
                    }
                    ;
                }
            }
        }
    }

    private char[] inputLine;
    private int inputLineCharCnt;    // characters received so far in inputLine;
    private int[] matchingStates1;
    private int[] matchingStates2;
    private int[] currentMatchingStates;
    private int[] nextMatchingStates;
    private int currentMatchingStatesCnt;
    private int nextMatchingStatesCnt;

    private int expectedParamCharCnt;    // number of parameter characters expected <1 means accept all
    // numeric characters
    // 1 == expect one character
    // >= 2 == expect numeric characters upto and including this many
    public static final int VPUT_STATE_NONE = 0;
    public static final int VPUT_STATE_PROCESSING_CMD = 1;    // possible command in process
    public static final int VPUT_STATE_PARAM_CHARS = 2;    // expecting one character parameter

    private int vputState;

    public void vput(Graphics g, int ch) {
        if (inputLine == null) {
            inputLine = new char[1024];
            inputLineCharCnt = 0;
            matchingStates1 = new int[400];
            matchingStates2 = new int[400];
            currentMatchingStates = matchingStates1;
            nextMatchingStates = matchingStates2;
            currentMatchingStatesCnt = 0;
            nextMatchingStatesCnt = 0;
        }

        switch (vputState) {
            case VPUT_STATE_NONE:
                // first character - determine if a state has begun
                int iMax = (parseStateStartChars == null) ? 0 : parseStateStartChars.length;
                for (int i = 0; i < iMax; i++) {
                    if (ch == parseStateStartChars[i][0]) {
                        int startStateIdx = parseStateStartChars[i][1];
                        //set the arrays for next matches
                        currentMatchingStatesCnt = 0;
                        for (int j = startStateIdx; j < parseTable.length; j++) {
                            if (ch == parseTable[j][1]) {
                                currentMatchingStates[currentMatchingStatesCnt] = j;
                                currentMatchingStatesCnt++;
                            }
                        }
                        if (currentMatchingStatesCnt == 1
                            && parseTable[startStateIdx].length == 2) {
                            // single character command has been matched
                            procCmd(g, startStateIdx, null);
                        } else {
                            vputState = VPUT_STATE_PROCESSING_CMD;
                            int[] tmpStates;
                            int tmpCnt;
                            tmpStates = nextMatchingStates;
                            tmpCnt = nextMatchingStatesCnt;
                            nextMatchingStates = currentMatchingStates;
                            nextMatchingStatesCnt = currentMatchingStatesCnt;
                            currentMatchingStates = tmpStates;
                            currentMatchingStatesCnt = 0;
                            inputLine[0] = (char) ch;
                            inputLineCharCnt = 1;
                        }
                        return;
                    }
                }
                procChar(g, ch);
                return;

            case VPUT_STATE_PROCESSING_CMD:
                break;

            case VPUT_STATE_PARAM_CHARS:
                break;
        }

    }

    private void procChar(Graphics g, int ch) {
	/*
	else
	{
		process the character; in the appropriate graphics/regular character set.
			if the character is at the end of the line, then do wrapping as
			necessary. Also set the flag for ignoring the next newLine if
			appropriate. If character is newline then ignore if a flag is set.
		move cursor  (unless a flag was set - in that case unset the flag)
	}
	*/
    }

    private void procCmd(Graphics g, int startStateIdx, String[] formulas) {
        int matchedCmd = currentMatchingStates[startStateIdx];
	/*
	switch (macthedCmd)
	{
	}
	*/
    }

    class TermInfoTokenizer {
        char[] buf;
        int tokenStart;    // inclusive
        int tokenEnd;    // exclusive
        int bufLen;

        public TermInfoTokenizer(String s) {
            if (s != null
                && s.trim().length() > 0)
                buf = s.trim().toCharArray();
            tokenStart = 0;
            tokenEnd = 0;
            bufLen = buf.length;
        }

        boolean hasMoreTokens() {
            if (buf != null
                && tokenStart < bufLen)
                return true;
            else
                return false;
        }

        String nextToken() {
            if (!hasMoreTokens())
                return null;

            tokenEnd = -1;
            boolean inEscape = false;
            for (int i = tokenStart; tokenEnd < 0 && i < bufLen; i++) {
                char ch = buf[i];
                if (inEscape) {
                    inEscape = false;
                    continue;
                } else {
                    switch (ch) {
                        case '\\':
                        case '^':
                            inEscape = true;
                            continue;
                        case ',':
                            tokenEnd = i;
                            break;
                    }
                }
            }
            String retVal;
            if (tokenEnd < 0) {
                retVal = new String(buf, tokenStart, bufLen - tokenStart);
                tokenStart = bufLen;
            } else {
                retVal = new String(buf, tokenStart, tokenEnd - tokenStart);
                tokenStart = tokenEnd + 1;
            }
            return retVal.trim();
        }
    }

    //public static final int CMD_STATE_NONE					= 0;
    public static final int CMD_STATE_START = 0;
    //public static final int CMD_STATE_ACS_CHARS 				= CMD_STATE_START + 1	;
    //public static final int CMD_STATE_ALT_SCANCODE_ESC 			= CMD_STATE_START + 2	;
    public static final int CMD_STATE_BACK_TAB = CMD_STATE_START + 3;
    public static final int CMD_STATE_BELL = CMD_STATE_START + 4;
    public static final int CMD_STATE_BIT_IMAGE_CARRIAGE_RETURN = CMD_STATE_START + 5;
    public static final int CMD_STATE_BIT_IMAGE_NEWLINE = CMD_STATE_START + 6;
    public static final int CMD_STATE_BIT_IMAGE_REPEAT = CMD_STATE_START + 7;
    public static final int CMD_STATE_CARRIAGE_RETURN = CMD_STATE_START + 8;
    public static final int CMD_STATE_CHANGE_CHAR_PITCH = CMD_STATE_START + 9;
    public static final int CMD_STATE_CHANGE_LINE_PITCH = CMD_STATE_START + 10;
    public static final int CMD_STATE_CHANGE_RES_HORZ = CMD_STATE_START + 11;
    public static final int CMD_STATE_CHANGE_RES_VERT = CMD_STATE_START + 12;
    public static final int CMD_STATE_CHANGE_SCROLL_REGION = CMD_STATE_START + 13;
    public static final int CMD_STATE_CHAR_PADDING = CMD_STATE_START + 14;
    //public static final int CMD_STATE_CHAR_SET_NAMES 			= CMD_STATE_START + 15	;
    public static final int CMD_STATE_CLEAR_ALL_TABS = CMD_STATE_START + 16;
    public static final int CMD_STATE_CLEAR_MARGINS = CMD_STATE_START + 17;
    public static final int CMD_STATE_CLEAR_SCREEN = CMD_STATE_START + 18;
    public static final int CMD_STATE_CLR_BOL = CMD_STATE_START + 19;
    public static final int CMD_STATE_CLR_EOL = CMD_STATE_START + 20;
    public static final int CMD_STATE_CLR_EOS = CMD_STATE_START + 21;
    public static final int CMD_STATE_CODE_SET_INIT = CMD_STATE_START + 22;
    public static final int CMD_STATE_COLOR_NAMES = CMD_STATE_START + 23;
    public static final int CMD_STATE_COLUMN_ADDRESS = CMD_STATE_START + 24;
    public static final int CMD_STATE_COMMAND_CHARACTER = CMD_STATE_START + 25;
    public static final int CMD_STATE_CREATE_WINDOW = CMD_STATE_START + 26;
    public static final int CMD_STATE_CURSOR_ADDRESS = CMD_STATE_START + 27;
    public static final int CMD_STATE_CURSOR_DOWN = CMD_STATE_START + 28;
    public static final int CMD_STATE_CURSOR_HOME = CMD_STATE_START + 29;
    public static final int CMD_STATE_CURSOR_INVISIBLE = CMD_STATE_START + 30;
    public static final int CMD_STATE_CURSOR_LEFT = CMD_STATE_START + 31;
    public static final int CMD_STATE_CURSOR_MEM_ADDRESS = CMD_STATE_START + 32;
    public static final int CMD_STATE_CURSOR_NORMAL = CMD_STATE_START + 33;
    public static final int CMD_STATE_CURSOR_RIGHT = CMD_STATE_START + 34;
    public static final int CMD_STATE_CURSOR_TO_LL = CMD_STATE_START + 35;
    public static final int CMD_STATE_CURSOR_UP = CMD_STATE_START + 36;
    public static final int CMD_STATE_CURSOR_VISIBLE = CMD_STATE_START + 37;
    public static final int CMD_STATE_DEFINE_BIT_IMAGE_REGION = CMD_STATE_START + 38;
    public static final int CMD_STATE_DEFINE_CHAR = CMD_STATE_START + 39;
    public static final int CMD_STATE_DELETE_CHARACTER = CMD_STATE_START + 40;
    public static final int CMD_STATE_DELETE_LINE = CMD_STATE_START + 41;
    public static final int CMD_STATE_DEVICE_TYPE = CMD_STATE_START + 42;
    public static final int CMD_STATE_DIAL_PHONE = CMD_STATE_START + 43;
    public static final int CMD_STATE_DIS_STATUS_LINE = CMD_STATE_START + 44;
    public static final int CMD_STATE_DISPLAY_CLOCK = CMD_STATE_START + 45;
    public static final int CMD_STATE_DISPLAY_PC_CHAR = CMD_STATE_START + 46;
    public static final int CMD_STATE_DOWN_HALF_LINE = CMD_STATE_START + 47;
    public static final int CMD_STATE_ENA_ACS = CMD_STATE_START + 48;
    public static final int CMD_STATE_END_BIT_IMAGE_REGION = CMD_STATE_START + 49;
    public static final int CMD_STATE_ENTER_ALT_CHARSET_MODE = CMD_STATE_START + 50;
    public static final int CMD_STATE_ENTER_AM_MODE = CMD_STATE_START + 51;
    public static final int CMD_STATE_ENTER_BLINK_MODE = CMD_STATE_START + 52;
    public static final int CMD_STATE_ENTER_BOLD_MODE = CMD_STATE_START + 53;
    public static final int CMD_STATE_ENTER_CA_MODE = CMD_STATE_START + 54;
    public static final int CMD_STATE_ENTER_DELETE_MODE = CMD_STATE_START + 55;
    public static final int CMD_STATE_ENTER_DIM_MODE = CMD_STATE_START + 56;
    public static final int CMD_STATE_ENTER_DOUBLEWIDE_MODE = CMD_STATE_START + 57;
    public static final int CMD_STATE_ENTER_DRAFT_QUALITY = CMD_STATE_START + 58;
    public static final int CMD_STATE_ENTER_INSERT_MODE = CMD_STATE_START + 59;
    public static final int CMD_STATE_ENTER_ITALICS_MODE = CMD_STATE_START + 60;
    public static final int CMD_STATE_ENTER_LEFTWARD_MODE = CMD_STATE_START + 61;
    public static final int CMD_STATE_ENTER_MICRO_MODE = CMD_STATE_START + 62;
    public static final int CMD_STATE_ENTER_NEAR_LETTER_QUALITY = CMD_STATE_START + 63;
    public static final int CMD_STATE_ENTER_NORMAL_QUALITY = CMD_STATE_START + 64;
    public static final int CMD_STATE_ENTER_PC_CHARSET_MODE = CMD_STATE_START + 65;
    public static final int CMD_STATE_ENTER_PROTECTED_MODE = CMD_STATE_START + 66;
    public static final int CMD_STATE_ENTER_REVERSE_MODE = CMD_STATE_START + 67;
    public static final int CMD_STATE_ENTER_SCANCODE_MODE = CMD_STATE_START + 68;
    public static final int CMD_STATE_ENTER_SECURE_MODE = CMD_STATE_START + 69;
    public static final int CMD_STATE_ENTER_SHADOW_MODE = CMD_STATE_START + 70;
    public static final int CMD_STATE_ENTER_STANDOUT_MODE = CMD_STATE_START + 71;
    public static final int CMD_STATE_ENTER_SUBSCRIPT_MODE = CMD_STATE_START + 72;
    public static final int CMD_STATE_ENTER_SUPERSCRIPT_MODE = CMD_STATE_START + 73;
    public static final int CMD_STATE_ENTER_UNDERLINE_MODE = CMD_STATE_START + 74;
    public static final int CMD_STATE_ENTER_UPWARD_MODE = CMD_STATE_START + 75;
    public static final int CMD_STATE_ENTER_XON_MODE = CMD_STATE_START + 76;
    public static final int CMD_STATE_ERASE_CHARS = CMD_STATE_START + 77;
    public static final int CMD_STATE_EXIT_ALT_CHARSET_MODE = CMD_STATE_START + 78;
    public static final int CMD_STATE_EXIT_AM_MODE = CMD_STATE_START + 79;
    public static final int CMD_STATE_EXIT_ATTRIBUTE_MODE = CMD_STATE_START + 80;
    public static final int CMD_STATE_EXIT_CA_MODE = CMD_STATE_START + 81;
    public static final int CMD_STATE_EXIT_DELETE_MODE = CMD_STATE_START + 82;
    public static final int CMD_STATE_EXIT_DOUBLEWIDE_MODE = CMD_STATE_START + 83;
    public static final int CMD_STATE_EXIT_INSERT_MODE = CMD_STATE_START + 84;
    public static final int CMD_STATE_EXIT_ITALICS_MODE = CMD_STATE_START + 85;
    public static final int CMD_STATE_EXIT_LEFTWARD_MODE = CMD_STATE_START + 86;
    public static final int CMD_STATE_EXIT_MICRO_MODE = CMD_STATE_START + 87;
    public static final int CMD_STATE_EXIT_PC_CHARSET_MODE = CMD_STATE_START + 88;
    public static final int CMD_STATE_EXIT_SCANCODE_MODE = CMD_STATE_START + 89;
    public static final int CMD_STATE_EXIT_SHADOW_MODE = CMD_STATE_START + 90;
    public static final int CMD_STATE_EXIT_SUBSCRIPT_MODE = CMD_STATE_START + 91;
    public static final int CMD_STATE_EXIT_SUPERSCRIPT_MODE = CMD_STATE_START + 92;
    public static final int CMD_STATE_EXIT_STANDOUT_MODE = CMD_STATE_START + 93;
    public static final int CMD_STATE_EXIT_UNDERLINE_MODE = CMD_STATE_START + 94;
    public static final int CMD_STATE_EXIT_UPWARD_MODE = CMD_STATE_START + 95;
    public static final int CMD_STATE_EXIT_XON_MODE = CMD_STATE_START + 96;
    public static final int CMD_STATE_FIXED_PAUSE = CMD_STATE_START + 97;
    public static final int CMD_STATE_FLASH_HOOK = CMD_STATE_START + 98;
    public static final int CMD_STATE_FLASH_SCREEN = CMD_STATE_START + 99;
    public static final int CMD_STATE_FORM_FEED = CMD_STATE_START + 100;
    public static final int CMD_STATE_FROM_STATUS_LINE = CMD_STATE_START + 101;
    public static final int CMD_STATE_GET_MOUSE = CMD_STATE_START + 102;
    public static final int CMD_STATE_GOTO_WINDOW = CMD_STATE_START + 103;
    public static final int CMD_STATE_HANGUP = CMD_STATE_START + 104;
    public static final int CMD_STATE_INIT_1STRING = CMD_STATE_START + 105;
    public static final int CMD_STATE_INIT_2STRING = CMD_STATE_START + 106;
    public static final int CMD_STATE_INIT_3STRING = CMD_STATE_START + 107;
    public static final int CMD_STATE_INIT_FILE = CMD_STATE_START + 108;
    public static final int CMD_STATE_INIT_PROG = CMD_STATE_START + 109;
    public static final int CMD_STATE_INITIALIZE_COLOR = CMD_STATE_START + 110;
    public static final int CMD_STATE_INITIALIZE_PAIR = CMD_STATE_START + 111;
    public static final int CMD_STATE_INSERT_CHARACTER = CMD_STATE_START + 112;
    public static final int CMD_STATE_INSERT_LINE = CMD_STATE_START + 113;
    public static final int CMD_STATE_INSERT_PADDING = CMD_STATE_START + 114;
    //public static final int CMD_STATE_KEY_A1 					= CMD_STATE_START + 115	;
    //public static final int CMD_STATE_KEY_A3 					= CMD_STATE_START + 116	;
    //public static final int CMD_STATE_KEY_B2 					= CMD_STATE_START + 117	;
    //public static final int CMD_STATE_KEY_BACKSPACE 			= CMD_STATE_START + 118	;
    //public static final int CMD_STATE_KEY_BEG 					= CMD_STATE_START + 119	;
    //public static final int CMD_STATE_KEY_BTAB 					= CMD_STATE_START + 120	;
    //public static final int CMD_STATE_KEY_C1 					= CMD_STATE_START + 121	;
    //public static final int CMD_STATE_KEY_C3 					= CMD_STATE_START + 122	;
    //public static final int CMD_STATE_KEY_CANCEL 				= CMD_STATE_START + 123	;
    //public static final int CMD_STATE_KEY_CATAB 				= CMD_STATE_START + 124	;
    //public static final int CMD_STATE_KEY_CLEAR 				= CMD_STATE_START + 125	;
    //public static final int CMD_STATE_KEY_CLOSE 				= CMD_STATE_START + 126	;
    //public static final int CMD_STATE_KEY_COMMAND 				= CMD_STATE_START + 127	;
    //public static final int CMD_STATE_KEY_COPY 					= CMD_STATE_START + 128	;
    //public static final int CMD_STATE_KEY_CREATE 				= CMD_STATE_START + 129	;
    //public static final int CMD_STATE_KEY_CTAB 					= CMD_STATE_START + 130	;
    //public static final int CMD_STATE_KEY_DC 					= CMD_STATE_START + 131	;
    //public static final int CMD_STATE_KEY_DL 					= CMD_STATE_START + 132	;
    //public static final int CMD_STATE_KEY_DOWN 					= CMD_STATE_START + 133	;
    //public static final int CMD_STATE_KEY_EIC 					= CMD_STATE_START + 134	;
    //public static final int CMD_STATE_KEY_END 					= CMD_STATE_START + 135	;
    //public static final int CMD_STATE_KEY_ENTER 				= CMD_STATE_START + 136	;
    //public static final int CMD_STATE_KEY_EOL 					= CMD_STATE_START + 137	;
    //public static final int CMD_STATE_KEY_EOS 					= CMD_STATE_START + 138	;
    //public static final int CMD_STATE_KEY_EXIT 					= CMD_STATE_START + 139	;
    //public static final int CMD_STATE_KEY_F0 					= CMD_STATE_START + 140	;
    //public static final int CMD_STATE_KEY_F1 					= CMD_STATE_START + 141	;
    //public static final int CMD_STATE_KEY_F2 					= CMD_STATE_START + 142	;
    //public static final int CMD_STATE_KEY_F3 					= CMD_STATE_START + 143	;
    //public static final int CMD_STATE_KEY_F4 					= CMD_STATE_START + 144	;
    //public static final int CMD_STATE_KEY_F5 					= CMD_STATE_START + 145	;
    //public static final int CMD_STATE_KEY_F6 					= CMD_STATE_START + 146	;
    //public static final int CMD_STATE_KEY_F7 					= CMD_STATE_START + 147	;
    //public static final int CMD_STATE_KEY_F8 					= CMD_STATE_START + 148	;
    //public static final int CMD_STATE_KEY_F9 					= CMD_STATE_START + 149	;
    //public static final int CMD_STATE_KEY_F10 					= CMD_STATE_START + 150	;
    //public static final int CMD_STATE_KEY_F11 					= CMD_STATE_START + 151	;
    //public static final int CMD_STATE_KEY_F12 					= CMD_STATE_START + 152	;
    //public static final int CMD_STATE_KEY_F13 					= CMD_STATE_START + 153	;
    //public static final int CMD_STATE_KEY_F14 					= CMD_STATE_START + 154	;
    //public static final int CMD_STATE_KEY_F15 					= CMD_STATE_START + 155	;
    //public static final int CMD_STATE_KEY_F16 					= CMD_STATE_START + 156	;
    //public static final int CMD_STATE_KEY_F17 					= CMD_STATE_START + 157	;
    //public static final int CMD_STATE_KEY_F18 					= CMD_STATE_START + 158	;
    //public static final int CMD_STATE_KEY_F19 					= CMD_STATE_START + 159	;
    //public static final int CMD_STATE_KEY_F20 					= CMD_STATE_START + 160	;
    //public static final int CMD_STATE_KEY_F21 					= CMD_STATE_START + 161	;
    //public static final int CMD_STATE_KEY_F22 					= CMD_STATE_START + 162	;
    //public static final int CMD_STATE_KEY_F23 					= CMD_STATE_START + 163	;
    //public static final int CMD_STATE_KEY_F24 					= CMD_STATE_START + 164	;
    //public static final int CMD_STATE_KEY_F25 					= CMD_STATE_START + 165	;
    //public static final int CMD_STATE_KEY_F26 					= CMD_STATE_START + 166	;
    //public static final int CMD_STATE_KEY_F27 					= CMD_STATE_START + 167	;
    //public static final int CMD_STATE_KEY_F28 					= CMD_STATE_START + 168	;
    //public static final int CMD_STATE_KEY_F29 					= CMD_STATE_START + 169	;
    //public static final int CMD_STATE_KEY_F30 					= CMD_STATE_START + 170	;
    //public static final int CMD_STATE_KEY_F31 					= CMD_STATE_START + 171	;
    //public static final int CMD_STATE_KEY_F32 					= CMD_STATE_START + 172	;
    //public static final int CMD_STATE_KEY_F33 					= CMD_STATE_START + 173	;
    //public static final int CMD_STATE_KEY_F34 					= CMD_STATE_START + 174	;
    //public static final int CMD_STATE_KEY_F35 					= CMD_STATE_START + 175	;
    //public static final int CMD_STATE_KEY_F36 					= CMD_STATE_START + 176	;
    //public static final int CMD_STATE_KEY_F37 					= CMD_STATE_START + 177	;
    //public static final int CMD_STATE_KEY_F38 					= CMD_STATE_START + 178	;
    //public static final int CMD_STATE_KEY_F39 					= CMD_STATE_START + 179	;
    //public static final int CMD_STATE_KEY_F40 					= CMD_STATE_START + 180	;
    //public static final int CMD_STATE_KEY_F41 					= CMD_STATE_START + 181	;
    //public static final int CMD_STATE_KEY_F42 					= CMD_STATE_START + 182	;
    //public static final int CMD_STATE_KEY_F43 					= CMD_STATE_START + 183	;
    //public static final int CMD_STATE_KEY_F44 					= CMD_STATE_START + 184	;
    //public static final int CMD_STATE_KEY_F45 					= CMD_STATE_START + 185	;
    //public static final int CMD_STATE_KEY_F46 					= CMD_STATE_START + 186	;
    //public static final int CMD_STATE_KEY_F47 					= CMD_STATE_START + 187	;
    //public static final int CMD_STATE_KEY_F48 					= CMD_STATE_START + 188	;
    //public static final int CMD_STATE_KEY_F49 					= CMD_STATE_START + 189	;
    //public static final int CMD_STATE_KEY_F50 					= CMD_STATE_START + 190	;
    //public static final int CMD_STATE_KEY_F51 					= CMD_STATE_START + 191	;
    //public static final int CMD_STATE_KEY_F52 					= CMD_STATE_START + 192	;
    //public static final int CMD_STATE_KEY_F53 					= CMD_STATE_START + 193	;
    //public static final int CMD_STATE_KEY_F54 					= CMD_STATE_START + 194	;
    //public static final int CMD_STATE_KEY_F55 					= CMD_STATE_START + 195	;
    //public static final int CMD_STATE_KEY_F56 					= CMD_STATE_START + 196	;
    //public static final int CMD_STATE_KEY_F57 					= CMD_STATE_START + 197	;
    //public static final int CMD_STATE_KEY_F58 					= CMD_STATE_START + 198	;
    //public static final int CMD_STATE_KEY_F59 					= CMD_STATE_START + 199	;
    //public static final int CMD_STATE_KEY_F60 					= CMD_STATE_START + 200	;
    //public static final int CMD_STATE_KEY_F61 					= CMD_STATE_START + 201	;
    //public static final int CMD_STATE_KEY_F62 					= CMD_STATE_START + 202	;
    //public static final int CMD_STATE_KEY_F63 					= CMD_STATE_START + 203	;
    //public static final int CMD_STATE_KEY_FIND 					= CMD_STATE_START + 204	;
    //public static final int CMD_STATE_KEY_HELP 					= CMD_STATE_START + 205	;
    //public static final int CMD_STATE_KEY_HOME 					= CMD_STATE_START + 206	;
    //public static final int CMD_STATE_KEY_IC 					= CMD_STATE_START + 207	;
    //public static final int CMD_STATE_KEY_IL 					= CMD_STATE_START + 208	;
    //public static final int CMD_STATE_KEY_LEFT 					= CMD_STATE_START + 209	;
    //public static final int CMD_STATE_KEY_LL 					= CMD_STATE_START + 210	;
    //public static final int CMD_STATE_KEY_MARK 					= CMD_STATE_START + 211	;
    //public static final int CMD_STATE_KEY_MESSAGE 				= CMD_STATE_START + 212	;
    //public static final int CMD_STATE_KEY_MOUSE 				= CMD_STATE_START + 213	;
    //public static final int CMD_STATE_KEY_MOVE 					= CMD_STATE_START + 214	;
    //public static final int CMD_STATE_KEY_NEXT 					= CMD_STATE_START + 215	;
    //public static final int CMD_STATE_KEY_NPAGE   				= CMD_STATE_START + 216	;
    //public static final int CMD_STATE_KEY_OPEN 					= CMD_STATE_START + 217	;
    //public static final int CMD_STATE_KEY_OPTIONS 				= CMD_STATE_START + 218	;
    //public static final int CMD_STATE_KEY_PPAGE 				= CMD_STATE_START + 219	;
    //public static final int CMD_STATE_KEY_PREVIOUS 				= CMD_STATE_START + 220	;
    //public static final int CMD_STATE_KEY_PRINT 				= CMD_STATE_START + 221	;
    //public static final int CMD_STATE_KEY_REDO 					= CMD_STATE_START + 222	;
    //public static final int CMD_STATE_KEY_REFERENCE 			= CMD_STATE_START + 223	;
    //public static final int CMD_STATE_KEY_REFRESH 				= CMD_STATE_START + 224	;
    //public static final int CMD_STATE_KEY_REPLACE 				= CMD_STATE_START + 225	;
    //public static final int CMD_STATE_KEY_RESTART 				= CMD_STATE_START + 226	;
    //public static final int CMD_STATE_KEY_RESUME 				= CMD_STATE_START + 227	;
    //public static final int CMD_STATE_KEY_RIGHT 				= CMD_STATE_START + 228	;
    //public static final int CMD_STATE_KEY_SAVE 					= CMD_STATE_START + 229	;
    //public static final int CMD_STATE_KEY_SBEG 					= CMD_STATE_START + 230	;
    //public static final int CMD_STATE_KEY_SCANCEL 				= CMD_STATE_START + 231	;
    //public static final int CMD_STATE_KEY_SCOMMAND 				= CMD_STATE_START + 232	;
    //public static final int CMD_STATE_KEY_SCOPY 				= CMD_STATE_START + 233	;
    //public static final int CMD_STATE_KEY_SCREATE 				= CMD_STATE_START + 234	;
    //public static final int CMD_STATE_KEY_SDC 					= CMD_STATE_START + 235	;
    //public static final int CMD_STATE_KEY_SDL 					= CMD_STATE_START + 236	;
    //public static final int CMD_STATE_KEY_SELECT 				= CMD_STATE_START + 237	;
    //public static final int CMD_STATE_KEY_SEND 					= CMD_STATE_START + 238	;
    //public static final int CMD_STATE_KEY_SEOL 					= CMD_STATE_START + 239	;
    //public static final int CMD_STATE_KEY_SEXIT 				= CMD_STATE_START + 240	;
    //public static final int CMD_STATE_KEY_SF 					= CMD_STATE_START + 241	;
    //public static final int CMD_STATE_KEY_SFIND 				= CMD_STATE_START + 242	;
    //public static final int CMD_STATE_KEY_SHELP 				= CMD_STATE_START + 243	;
    //public static final int CMD_STATE_KEY_SHOME 				= CMD_STATE_START + 244	;
    //public static final int CMD_STATE_KEY_SIC 					= CMD_STATE_START + 245	;
    //public static final int CMD_STATE_KEY_SLEFT 				= CMD_STATE_START + 246	;
    //public static final int CMD_STATE_KEY_SMESSAGE 				= CMD_STATE_START + 247	;
    //public static final int CMD_STATE_KEY_SMOVE 				= CMD_STATE_START + 248	;
    //public static final int CMD_STATE_KEY_SNEXT 				= CMD_STATE_START + 249	;
    //public static final int CMD_STATE_KEY_SOPTIONS 				= CMD_STATE_START + 250	;
    //public static final int CMD_STATE_KEY_SPREVIOUS 			= CMD_STATE_START + 251	;
    //public static final int CMD_STATE_KEY_SPRINT 				= CMD_STATE_START + 252	;
    //public static final int CMD_STATE_KEY_SR 					= CMD_STATE_START + 253	;
    //public static final int CMD_STATE_KEY_SREDO 				= CMD_STATE_START + 254	;
    //public static final int CMD_STATE_KEY_SREPLACE 				= CMD_STATE_START + 255	;
    //public static final int CMD_STATE_KEY_SRIGHT 				= CMD_STATE_START + 256	;
    //public static final int CMD_STATE_KEY_SRSUME 				= CMD_STATE_START + 257	;
    //public static final int CMD_STATE_KEY_SSAVE 				= CMD_STATE_START + 258	;
    //public static final int CMD_STATE_KEY_SSUSPEND 				= CMD_STATE_START + 259	;
    //public static final int CMD_STATE_KEY_STAB 					= CMD_STATE_START + 260	;
    //public static final int CMD_STATE_KEY_SUNDO 				= CMD_STATE_START + 261	;
    //public static final int CMD_STATE_KEY_SUSPEND 				= CMD_STATE_START + 262	;
    //public static final int CMD_STATE_KEY_UNDO 					= CMD_STATE_START + 263	;
    //public static final int CMD_STATE_KEY_UP 					= CMD_STATE_START + 264	;
    //public static final int CMD_STATE_KEYPAD_LOCAL 				= CMD_STATE_START + 265	;
    //public static final int CMD_STATE_KEYPAD_XMIT 				= CMD_STATE_START + 266	;
    //public static final int CMD_STATE_LAB_F0 					= CMD_STATE_START + 267	;
    //public static final int CMD_STATE_LAB_F1 					= CMD_STATE_START + 268	;
    //public static final int CMD_STATE_LAB_F10 					= CMD_STATE_START + 269	;
    //public static final int CMD_STATE_LAB_F2 					= CMD_STATE_START + 270	;
    //public static final int CMD_STATE_LAB_F3 					= CMD_STATE_START + 271	;
    //public static final int CMD_STATE_LAB_F4 					= CMD_STATE_START + 272	;
    //public static final int CMD_STATE_LAB_F5 					= CMD_STATE_START + 273	;
    //public static final int CMD_STATE_LAB_F6 					= CMD_STATE_START + 274	;
    //public static final int CMD_STATE_LAB_F7 					= CMD_STATE_START + 275	;
    //public static final int CMD_STATE_LAB_F8 					= CMD_STATE_START + 276	;
    //public static final int CMD_STATE_LAB_F9 					= CMD_STATE_START + 277	;
    //public static final int CMD_STATE_LABEL_FORMAT 				= CMD_STATE_START + 278	;
    public static final int CMD_STATE_LABEL_OFF = CMD_STATE_START + 279;
    public static final int CMD_STATE_LABEL_ON = CMD_STATE_START + 280;
    public static final int CMD_STATE_MEMORY_LOCK = CMD_STATE_START + 281;
    public static final int CMD_STATE_MEMORY_UNLOCK = CMD_STATE_START + 282;
    public static final int CMD_STATE_META_OFF = CMD_STATE_START + 283;
    public static final int CMD_STATE_META_ON = CMD_STATE_START + 284;
    public static final int CMD_STATE_MICRO_COLUMN_ADDRESS = CMD_STATE_START + 285;
    public static final int CMD_STATE_MICRO_DOWN = CMD_STATE_START + 286;
    public static final int CMD_STATE_MICRO_LEFT = CMD_STATE_START + 287;
    public static final int CMD_STATE_MICRO_RIGHT = CMD_STATE_START + 288;
    public static final int CMD_STATE_MICRO_ROW_ADDRESS = CMD_STATE_START + 289;
    public static final int CMD_STATE_MICRO_UP = CMD_STATE_START + 290;
    public static final int CMD_STATE_MOUSE_INFO = CMD_STATE_START + 291;
    public static final int CMD_STATE_NEWLINE = CMD_STATE_START + 292;
    public static final int CMD_STATE_ORDER_OF_PINS = CMD_STATE_START + 293;
    public static final int CMD_STATE_ORIG_COLORS = CMD_STATE_START + 294;
    public static final int CMD_STATE_ORIG_PAIR = CMD_STATE_START + 295;
    public static final int CMD_STATE_PAD_CHAR = CMD_STATE_START + 296;
    public static final int CMD_STATE_PARM_DCH = CMD_STATE_START + 297;
    public static final int CMD_STATE_PARM_DELETE_LINE = CMD_STATE_START + 298;
    public static final int CMD_STATE_PARM_DOWN_CURSOR = CMD_STATE_START + 299;
    public static final int CMD_STATE_PARM_DOWN_MICRO = CMD_STATE_START + 300;
    public static final int CMD_STATE_PARM_ICH = CMD_STATE_START + 301;
    public static final int CMD_STATE_PARM_INDEX = CMD_STATE_START + 302;
    public static final int CMD_STATE_PARM_INSERT_LINE = CMD_STATE_START + 303;
    public static final int CMD_STATE_PARM_LEFT_CURSOR = CMD_STATE_START + 304;
    public static final int CMD_STATE_PARM_LEFT_MICRO = CMD_STATE_START + 305;
    public static final int CMD_STATE_PARM_RIGHT_CURSOR = CMD_STATE_START + 306;
    public static final int CMD_STATE_PARM_RIGHT_MICRO = CMD_STATE_START + 307;
    public static final int CMD_STATE_PARM_RINDEX = CMD_STATE_START + 308;
    public static final int CMD_STATE_PARM_UP_CURSOR = CMD_STATE_START + 309;
    public static final int CMD_STATE_PARM_UP_MICRO = CMD_STATE_START + 310;
    public static final int CMD_STATE_PC_TERM_OPTIONS = CMD_STATE_START + 311;
    public static final int CMD_STATE_PKEY_KEY = CMD_STATE_START + 312;
    public static final int CMD_STATE_PKEY_LOCAL = CMD_STATE_START + 313;
    public static final int CMD_STATE_PKEY_PLAB = CMD_STATE_START + 314;
    public static final int CMD_STATE_PKEY_XMIT = CMD_STATE_START + 315;
    public static final int CMD_STATE_PLAB_NORM = CMD_STATE_START + 316;
    public static final int CMD_STATE_PRINT_SCREEN = CMD_STATE_START + 317;
    public static final int CMD_STATE_PRTR_NON = CMD_STATE_START + 318;
    public static final int CMD_STATE_PRTR_OFF = CMD_STATE_START + 319;
    public static final int CMD_STATE_PRTR_ON = CMD_STATE_START + 320;
    public static final int CMD_STATE_PULSE = CMD_STATE_START + 321;
    public static final int CMD_STATE_QUICK_DIAL = CMD_STATE_START + 322;
    public static final int CMD_STATE_REMOVE_CLOCK = CMD_STATE_START + 323;
    public static final int CMD_STATE_REPEAT_CHAR = CMD_STATE_START + 324;
    public static final int CMD_STATE_REQ_FOR_INPUT = CMD_STATE_START + 325;
    public static final int CMD_STATE_REQ_MOUSE_POS = CMD_STATE_START + 326;
    public static final int CMD_STATE_RESET_1STRING = CMD_STATE_START + 327;
    public static final int CMD_STATE_RESET_2STRING = CMD_STATE_START + 328;
    public static final int CMD_STATE_RESET_3STRING = CMD_STATE_START + 329;
    public static final int CMD_STATE_RESET_FILE = CMD_STATE_START + 330;
    public static final int CMD_STATE_RESTORE_CURSOR = CMD_STATE_START + 331;
    public static final int CMD_STATE_ROW_ADDRESS = CMD_STATE_START + 332;
    public static final int CMD_STATE_SAVE_CURSOR = CMD_STATE_START + 333;
    public static final int CMD_STATE_SCANCODE_ESCAPE = CMD_STATE_START + 334;
    public static final int CMD_STATE_SCROLL_FORWARD = CMD_STATE_START + 335;
    public static final int CMD_STATE_SCROLL_REVERSE = CMD_STATE_START + 336;
    public static final int CMD_STATE_SELECT_CHAR_SET = CMD_STATE_START + 337;
    public static final int CMD_STATE_SET0_DES_SEQ = CMD_STATE_START + 338;
    public static final int CMD_STATE_SET1_DES_SEQ = CMD_STATE_START + 339;
    public static final int CMD_STATE_SET2_DES_SEQ = CMD_STATE_START + 340;
    public static final int CMD_STATE_SET3_DES_SEQ = CMD_STATE_START + 341;
    public static final int CMD_STATE_SET_A_BACKGROUND = CMD_STATE_START + 342;
    public static final int CMD_STATE_SET_A_FOREGROUND = CMD_STATE_START + 343;
    public static final int CMD_STATE_SET_ATTRIBUTES = CMD_STATE_START + 344;
    public static final int CMD_STATE_SET_BACKGROUND = CMD_STATE_START + 345;
    public static final int CMD_STATE_SET_BOTTOM_MARGIN = CMD_STATE_START + 346;
    public static final int CMD_STATE_SET_BOTTOM_MARGIN_PARM = CMD_STATE_START + 347;
    public static final int CMD_STATE_SET_CLOCK = CMD_STATE_START + 348;
    public static final int CMD_STATE_SET_COLOR_BAND = CMD_STATE_START + 349;
    public static final int CMD_STATE_SET_COLOR_PAIR = CMD_STATE_START + 350;
    public static final int CMD_STATE_SET_FOREGROUND = CMD_STATE_START + 351;
    public static final int CMD_STATE_SET_LEFT_MARGIN = CMD_STATE_START + 352;
    public static final int CMD_STATE_SET_LEFT_MARGIN_PARM = CMD_STATE_START + 353;
    public static final int CMD_STATE_SET_LR_MARGIN = CMD_STATE_START + 354;
    public static final int CMD_STATE_SET_PAGE_LENGTH = CMD_STATE_START + 355;
    public static final int CMD_STATE_SET_RIGHT_MARGIN = CMD_STATE_START + 356;
    public static final int CMD_STATE_SET_RIGHT_MARGIN_PARM = CMD_STATE_START + 357;
    public static final int CMD_STATE_SET_TAB = CMD_STATE_START + 358;
    public static final int CMD_STATE_SET_TB_MARGIN = CMD_STATE_START + 359;
    public static final int CMD_STATE_SET_TOP_MARGIN = CMD_STATE_START + 360;
    public static final int CMD_STATE_SET_TOP_MARGIN_PARM = CMD_STATE_START + 361;
    public static final int CMD_STATE_SET_WINDOW = CMD_STATE_START + 362;
    public static final int CMD_STATE_START_BIT_IMAGE = CMD_STATE_START + 363;
    public static final int CMD_STATE_START_CHAR_SET_DEF = CMD_STATE_START + 364;
    public static final int CMD_STATE_STOP_BIT_IMAGE = CMD_STATE_START + 365;
    public static final int CMD_STATE_STOP_CHAR_SET_DEF = CMD_STATE_START + 366;
    public static final int CMD_STATE_SUBSCRIPT_CHARACTERS = CMD_STATE_START + 367;
    public static final int CMD_STATE_SUPERSCRIPT_CHARACTERS = CMD_STATE_START + 368;
    public static final int CMD_STATE_TAB = CMD_STATE_START + 369;
    public static final int CMD_STATE_THESE_CAUSE_CR = CMD_STATE_START + 370;
    public static final int CMD_STATE_TO_STATUS_LINE = CMD_STATE_START + 371;
    public static final int CMD_STATE_TONE = CMD_STATE_START + 372;
    public static final int CMD_STATE_USER0 = CMD_STATE_START + 373;
    public static final int CMD_STATE_USER1 = CMD_STATE_START + 374;
    public static final int CMD_STATE_USER2 = CMD_STATE_START + 375;
    public static final int CMD_STATE_USER3 = CMD_STATE_START + 376;
    public static final int CMD_STATE_USER4 = CMD_STATE_START + 377;
    public static final int CMD_STATE_USER5 = CMD_STATE_START + 378;
    public static final int CMD_STATE_USER6 = CMD_STATE_START + 379;
    public static final int CMD_STATE_USER7 = CMD_STATE_START + 380;
    public static final int CMD_STATE_USER8 = CMD_STATE_START + 381;
    public static final int CMD_STATE_USER9 = CMD_STATE_START + 382;
    public static final int CMD_STATE_WAIT_TONE = CMD_STATE_START + 383;
    public static final int CMD_STATE_XOFF_CHARACTER = CMD_STATE_START + 384;
    public static final int CMD_STATE_XON_CHARACTER = CMD_STATE_START + 385;
    public static final int CMD_STATE_ZERO_MOTION = CMD_STATE_START + 386;
    public static final int CMD_STATE_UNDERLINE_CHAR = CMD_STATE_START + 387;
    public static final int CMD_STATE_UP_HALF_LINE = CMD_STATE_START + 388;

    /**
     * This method prints a default parse file. The parse file is stuctured to
     * have at least four fields.
     * 1. TermInfo Capability String
     * 2. TermInfo Translation String
     * 3. Tokens expected by parser.
     * 4. Values of parameters expressed in terms of the tokens.
     * %v1 thru %v9 refer to characters/numbers extracted from the token string
     * %s1  refers to the a string extracted from the token string.
     * Fields 3 and 4 may be repeated in pairs, if the terminfo capability
     * requires the generation of multiple token sequences and formulas.
     * <p>
     * Tokens expected by the parser consists of characters (%c), numbers (%d)
     * and String (%s) in addition to other characters. The tokens are
     * encoded using the same syntax as the terminfo fields with the addition
     * of following special symbols:
     * <p>
     * %c variations - %c Any character (including ^A ^B etc)
     * %S Space or greater character
     * %R 24  or higher character
     * %S 80  or higher character
     * %T '@' or higher character
     * %U '`' or higher character
     * %X '?' or higher character
     * %Y '/' or higher character
     * %s variations	%s Any string of characters
     * %J String of characters of length determined by last param encountered (p2)
     * %K String of printable characters not including single quote "'"
     * %L String of numbers and semicolons
     * %M String of printable characters not including carriage return "\r"
     * %d no variations
     * <p>
     * In the formula field, %x means that the token is contained in a %L
     * (string of semicolon separated numbers). Other % sequences have the same meaning
     * as in Terminfo. To quote from Unix terminfo help:
     * The argument mechanism uses a stack and special "%" codes to
     * manipulate the stack in the manner of Reverse Polish Notation
     * (postfix).  Typically a sequence pushes one of the arguments onto the
     * stack and then prints it in some format.  Often more complex
     * operations are necessary.  Operations are in postfix form with the
     * operands in the usual order.  That is, to subtract 5 from the first
     * argument, one would use %p1%{5}%-.
     * <p>
     * The "%" encodings have the following meanings:
     * <p>
     * %%             Outputs "%".
     * %[[:]flags][width[.precision]][doxXs]
     * As in printf(); flags are [-+#] and space.
     * <p>
     * %c             Print pop() gives %c.
     * <p>
     * %p[1-9]        Push the ith argument.
     * <p>
     * %P[a-z]        Set dynamic variable [a-z] to pop().
     * <p>
     * %g[a-z]        Get dynamic variable [a-z] and push it.
     * <p>
     * %P[A-Z]        Set static variable [a-z] to pop().
     * <p>
     * %g[A-Z]        Get static variable [a-z] and push it.
     * <p>
     * %'c'           Push char constant c.
     * <p>
     * %{nn}          Push decimal constant nn.
     * <p>
     * %l             Push strlen(pop()).
     * <p>
     * %+ %- %* %/ %m Arithmetic (%m is mod):  push(pop integer2 op pop
     * integer1) where integer1 represents the top of the
     * stack
     * %& %| %^       Bit operations:  push(pop integer2 op pop
     * integer1)
     * <p>
     * %= %> %<       Logical operations:  push(pop integer2 op pop
     * integer1)
     * <p>
     * %A %O          Logical operations:  and, or
     * <p>
     * %! %~          Unary operations:  push(op pop())
     * <p>
     * %i             (For ANSI terminals) add 1 to the first argument
     * (if one argument present), or first two arguments
     * (if more than one argument present).
     * <p>
     * %? expr %t thenpart %e elsepart %;
     * If-then-else; %e elsepart is optional; else-if's
     * are possible as in Algol 68:
     * <p>
     * %? c
     * %? c1 %t b1 %e c2 %t b2 %e c3 %t b3 %e c4 %t b4 %e
     * b5 %;
     * <p>
     * ci are conditions; bi are bodies.
     * <p>
     * If the "-" flag is used with "%[doxXs]", then a colon must be placed
     * between the "%" and the "-" to differentiate the flag from the binary
     * "%-" operator.  For example: "%:-16.16s".
     * <p>
     * Consider the Hewlett-Packard 2645, which, to get to row 3 and column
     * 12, needs to be sent \E&a12c03Y padded for 6 milliseconds.  Note that
     * the order of the rows and columns is inverted here, and that the row
     * and column are zero-padded as two digits.  Thus, its cup capability
     * is:
     * <p>
     * cup=\E&a%p2%2.2dc%p1%2.2dY$<6>
     * <p>
     * The Micro-Term ACT-IV needs the current row and column sent preceded
     * by a ^T, with the row and column simply encoded in binary:
     * <p>
     * cup=^T%p1%c%p2%c
     * <p>
     * Devices that use "%c" need to be able to backspace the cursor (cub1),
     * and to move the cursor up one line on the screen (cuu1).  This is
     * and to move the cursor up one line on the screen (cuu1).  This is
     * necessary because it is not always safe to transmit \n, ^D, and \r, as
     * the system may change or discard them.  (The library functions dealing
     * with terminfo set tty modes so that tabs are never expanded, so \t is
     * safe to send.  This turns out to be essential for the Ann Arbor 4080.)
     * cup=\E=%p1%'\s'%+%c%p2%'\s'%+%c
     * <p>
     * After sending "\E=", this pushes the first argument, pushes the ASCII
     * decimal value for a space (32), adds them (pushing the sum on the
     * stack in place of the two previous values), and outputs that value as
     * a character.  Then the same is done for the second argument.  More
     * complex arithmetic is possible using the stack.
     * <p>
     * Both \E and \e map to an ESCAPE character, ^x maps to a control x
     * for any appropriate x, and the sequences \n, \l,  \r, \t,  \b,  \f,
     * and \s give a newline, linefeed, return, tab,
     * backspace, formfeed, and space, respectively.  Other escapes
     * include:  \^  for  caret  (^);  \\ for backslash (\); \, for
     * comma (,); \: for colon (:); and  \0  for  null.   (\0  will
     * actually produce \200, which does not terminate a string but
     * behaves as a null character on most devices,  providing  CS7
     * is  specified.   (See  stty(1)).  Finally, characters may be
     * given as three octal digits after a backslash (for  example,
     * \123).
     */
    private void writeDefaultParseFile() {
        String[][] defaultParamParse = {

            // command, terminfo_orig_string, expected_string, formulas
            // The following attributes capabilities may have parameterized strings:
            //  String	change_scroll_region;   // csr     cs     change to lines #1 through #2 (vt100)(PG)
            // [^[[%i%p1%d;%p2%dr]   same for all terminals
            {"csr", "^[[%i%p1%d;%p2%dr", "^[[%d;%dr", "p1=%v1%{1}%-, p2=%v2%{1}%-",},
            //  String	parm_left_cursor;       // cub     LE     Move cursor left #1 spaces (PG)
            // [^[[%p1%dD]
            {"cub", "^[[%p1%dD", "^[[%dD", "p1=%v1",},
            // [^[[%p2%dD]
            {"cub", "^[[%p2%dD", "^[[%dD", "p1=%v1",},
            // [^_lef %p1%d\r] for terminals 4025-17, 4025-17ws, 4025, 4025ex
            {"cub", "^_lef %p1%d\\r", "^_lef %d\\r", "p1=%v1",},
            //  String	parm_down_cursor;       // cud     DO     Move cursor down #1 lines (PG*)
            // [^[[%p1%dB] for most terminals
            {"cud", "^[[%p1%dB", "^[[%dB", "p1=%v1",},
            // [^_dow %p1%d\r] for terminals 4025-17, 4025-17ws, 4025, 4025ex
            {"cud", "^_dow %p1%d\\r", "^_dow %d\\r", "p1=%v1",},
            //  String	parm_right_cursor;      // cuf     RI     Move cursor right #1 spaces (PG*)
            // [\E[%p1%dC] for most terminals
            {"cuf", "\\E[%p1%dC", "\\E[%dC", "p1=%v1",},
            // [^_rig %p1%d\r] for terminals 4025-17, 4025-17ws, 4025, 4025ex
            {"cuf", "^_rig %p1%d\\r", "^_rig %d\\r", "p1=%v1",},
            //  String	cursor_address;         // cup     cm     Screen rel. cursor motion row #1 col #2 (PG)
            //			[cup]  [%i\E[%p1%d;%p2%dH]	- term bg, bitgraph-ni, bitgraph-nv, obitgraph-ni, obitgraph-rv, obitgraph
            {"cup", "%i\\E[%p1%d;%p2%dH", "\\E[%d;%dH", "p1=%v1%{1}%-, p2=%v2%{1}%-",},
            //			[cup]  [%i\E[%p1%d;%p2%df] - term ya
            {"cup", "%i\\E[%p1%d;%p2%df", "\\E[%d;%df", "p1=%v1%{1}%-, p2=%v2%{1}%-",},
            //			[cup]  [\E&a%p1%dy%p2%dC] - term 110 150 2382 2392 2393 2394 2397 2621-ba 2621-nl 2621-nt 2621-wl
            //											2621 2621k45 2621p 2622 2623 2624 2625 2627 2628 262x 2644
            //											2645 2647 2703 300h 300l 70092-w 70092 70094-w 70094 9020
            //											9816te 9816teb 98204b 9826 9836 9837 98541 98543 98544
            //											98546 98548 98549 98550 98700 98705 98720 98730 98736 D1182A a1096a
            //											a1416a hk hp2621nl hp2621nt hp2621wl hpY hp hpterm plus
            {"cup", "\\E&a%p1%dy%p2%dC", "\\E&a%dy%dC", "p1=%v1, p2=%v2",},
            //			[cup]  [\E&a%p1%dy%p2%dX] - term 2626-12-s 2626-12 2626-12x40 2626-ns 2626-s 2626-x40 2626
            {"cup", "\\E&a%p1%dy%p2%dX", "\\E&a%dy%dX", "p1=%v1, p2=%v2",},
            //			[cup]  [\E&a%p2%dc%p1%dR] - term 2621-48
            {"cup", "\\E&a%p2%dc%p1%dR", "\\E&a%dc%dR", "p1=%v2, p2=%v1",},
            //			[cup]  [\E&a%p2%dc%p1%dY] - term 2648 9835
            {"cup", "\\E&a%p2%dc%p1%dY", "\\E&a%dc%dY", "p1=%v2, p2=%v1",},
            //			[cup]  [\E1%p1%' '%+%c%p2%' '%+%c] cdc456 cdc456tst
            {"cup", "\\E1%p1%' '%+%c%p2%' '%+%c", "\\E1%S%S", "p1=%v1%' '%-, p2=%v2%' '%-",},
            //			[cup]  [\E=%' '%p1%+%c%' '%p2%+%c] dt4315Y
            {"cup", "\\E=%' '%p1%+%c%' '%p2%+%c", "\\E=%S%S", "p1=%v1%' '%-, p2=%v2%' '%-",},
            //			[cup]  [\E=%p1%' '%+%c%p2%' '%+%c\EF \t] adm42-nl
            {"cup", "\\E=%p1%' '%+%c%p2%' '%+%c\\EF \\t", "\\E=%S%S\\EF \\t", "p1=%v1%' '%-, p2=%v2%' '%-",},
            //			[cup]  [\E=%p1%' '%+%c%p2%' '%+%c] 120 30 50 60 9122p 912b 912cc 9202p 920b 9502p 9504p 950rv2p 950rv4p
            //											950rv adm1a adm21 adm2 adm31 adm3a+ adm3a adm42 adm5 ampex apple
            //											bc beacon c1003 carlock f100-rv f100 falco-p falco iq140 mime-3a mime-3ax
            //											mime2a-s mime2as oadm31 qvt101 smarterm smartvid soroc synertek
            //											tec500 tvi2p tvi905 tvi910+ tvi912-2p tvi9122p tvi912 tvi9202p
            //											tvi925E tvi925 tvi950-2p tvi950-4p tvi950-ap tvi950-b tvi950-ns tvi950-rv-2p
            //											tvi950-rv-4p tvi950-rv tvi9502p tvi9504p tvi950 tvi950b tvi950ns tvi950rv2p
            //											tvi950rv4p tvi950rv ubell wy100 wy120-25 wy120-vb wy30-vb wy50-vb
            //											wy60-25 wy60-2p wy60-42 wy60-43 wy60-vb wy60old zen30
            {"cup", "\\E=%p1%' '%+%c%p2%' '%+%c", "\\E=%S%S", "p1=%v1%' '%-, p2=%v2%' '%-",},
            //			[cup]  [\E=%p1%'@'%+%c%p2%'@'%+%c]  netx xitex
            {"cup", "\\E=%p1%'@'%+%c%p2%'@'%+%c", "\\E=%T%T", "p1=%v1%'@'%-, p2=%v2%'@'%-",},
            //			[cup]  [\E=%p1%p1%' '%+%c%p2%' '%+%c] t500
            {"cup", "\\E=%p1%p1%' '%+%c%p2%' '%+%c", "\\E=%S%S", "p1=%v1%' '%-, p2=%v2%' '%-",},
            //			[cup]  [\EF%p1%' '%+%c%p2%' '%+%c] microb
            {"cup", "\\EF%p1%' '%+%c%p2%' '%+%c", "\\EF%S%S", "p1=%v1%' '%-, p2=%v2%' '%-",},
            //			[cup]  [\EF%p2%3d%p1%3d] sb1 sb2 sbi
            {"cup", "\\EF%p2%3d%p1%3d", "\\EF%3d%3d", "p1=%v2, p2=%v1",},
            //			[cup]  [\EG%p1%' '%+%c%p2%' '%+%c] 1linepty pty
            {"cup", "\\EG%p1%' '%+%c%p2%' '%+%c", "\\EG%S%S", "p1=%v1%' '%-, p2=%v2%' '%-",},
            //			[cup]  [\EG%p2%c%p1%c] virtual
            {"cup", "\\EG%p2%c%p1%c", "\\EG%c%c", "p2=%v1, p1=%v2",},
            //			[cup]  [\EX%p1%' '%+%c\EY%p2%' '%+%c] bantam fox owl
            {"cup", "\\EX%p1%' '%+%c\\EY%p2%' '%+%c", "\\EX%S\\EY%S", "p1=%v1%' '%-, p2=%v2%' '%-",},
            //			[cup]  [\EY%p1%' '%+%c%p2%' '%+%c] altoh19 h1552-rv h1552 h19-bs h19-pb h19-smul h19-u h19
            //						h19b h19bs h19u it mime2a mime2av reach screwpoint sol
            //						superbrain t1061 t1061f t3800 vi200-f vi200-ic
            //						vi200-rv-ic vi200-rv vi200 viewpoint vt50h vt52
            {"cup", "\\EY%p1%' '%+%c%p2%' '%+%c", "\\EY%S%S", "p1=%v1%' '%-, p2=%v2%' '%-",},
            //
            //			[cup]  [\EY%p2%' '%+%c%p1%' '%+%c] 3045 blit-pb blit blitlayer cbblit dm3025 oblit
            {"cup", "\\EY%p2%' '%+%c%p1%' '%+%c", "\\EY%S%S", "p1=%v2%' '%-, p2=%v1%' '%-",},
            //
            //			[cup]  [\E[%i%2;%2H] 4424-el-2 f1720 ovi300 vi300-aw vi300-rv vi300-ss vi300 vi550
            {"cup", "\\E[%i%2;%2H", "\\E[%2H;%2H", "p1=%v1%{1}%-, p2=%v2%{1}%-",},
            //
            //			[cup]  [\E[%i%p1%02d;%p2%02dH] AT386-MY
            {"cup", "\\E[%i%p1%02d;%p2%02dH", "\\E[%02d;%02dH", "p1=%v1%{1}%-, p2=%v2%{1}%-",},
            //			[cup]  [\E[%i%p1%03d;%p2%03dH] i400
            {"cup", "\\E[%i%p1%03d;%p2%03dH", "\\E[%03d;%03dH", "p1=%v1%{1}%-, p2=%v2%{1}%-",},
            //
            //			[cup]  [\E[%i%p1%d;%p2%dH] 36 4424-2 4424 75 85 aaa-18-rv aaa-18 aaa-20 aaa-22 aaa-24-rv aaa-24]
            //						aaa-26 aaa-28 aaa-29-ctxt aaa-29-np aaa-29-rv aaa-29 aaa-30-ctxt aaa-30-rv-ctxt]
            //						aaa-30-rv aaa-30-s-ctxt aaa-30-s-rv-ctxt aaa-30-s-rv aaa-30-s aaa-36-rv aaa-36
            //						aaa-40-rv aaa-40 aaa-48-rv aaa-48 aaa-59 aaa-60-rv aaa-60-s-rv aaa-60-s aaa-60
            //						aaa-db aaa-unk aaa18 aaa20 aaa22 aaa24 aaa26 aaa28 aaa29 aaa30 aaa36 aaa40
            //						aaa48 aaa59 aaa60 aaa aaadb aixtermY ansi-25Y ansiY ansi d800 dt80-w dt80
            //						dtterm env230 gigi h19-a h19a intext2 minansi pt-22 pt-23 pt-24 pt-25 pt-26
            //						pt-e pt-z pt132-e pt ref2dosY reflect2Y sqnt220Y st340Y sunY sun sym220Y
            //						tab132-rv tab132-w-rv tab132-w tab132 to300-24 uvt1220Y uvt1224Y vt100-am
            //						vt100-nav-w vt100-nav vt100-np vt100-s-bot vt100-w-nam vt100-w vt100Y vt100
            //						vt100am vt100nam vt100w vt125 vt132 vt220-am vt220Y vt220 vt300Y vt320-am
            //						vt320 wy60vt1 wy75-vb wy75-w wy75-wvb wy85-nx wy85-vb wy85-w wy85-wvb
            //						xtermY xterm xterms zterm
            {"cup", "\\E[%i%p1%d;%p2%dH", "\\E[%d;%dH", "p1=%v1%{1}%-, p2=%v2%{1}%-",},
            //
            //			[cup]  [\E[%i%p1%{1}%+%d;%p2%dH]  vt100-s vt100s
            {"cup", "\\E[%i%p1%{1}%+%d;%p2%dH", "\\E[%d;%dH", "p1=%v1%{1}%-%{1}%-, p2=%v2%{1}%-",},
            //
            //			[cup]  [\E^Q%p2%c%p1%' '%+%c] h1420
            {"cup", "\\E^Q%p2%c%p1%' '%+%c", "\\E^Q%c%S", "p2=%v1, p1=%v2%' '%-",},
            //
            //			[cup]  [\E^Q%p2%c%p1%c]  esprit h1510
            {"cup", "\\E^Q%p2%c%p1%c", "\\E^Q%c%c", "p2=%v1, p1=%v2",},
            //
            //			[cup]  [\Ea%i%p1%dR%p2%dC] wy120-25-w wy120-w-vb wy120-w wy50-w wy50-wvb wy60-25-w wy60-25Y
            //						wy60-42-w wy60-43-w wy60-w-vb wy60-w wy60Y
            {"cup", "\\Ea%i%p1%dR%p2%dC", "\\Ea%dR%dC", "p1=%v1%{1}%-1, p2=%v2%{1}%-",},
            //
            //			[cup]  [\Ea%p1%' '%+%c%p2%' '%+%c] c100-rv-na c100-rv-pp c100-rv c1004p c100
            //						c100rv4p c100rv4pna c100rv4ppp c100rv c100rvna c100rvpp c100rvs c100s oc100
            {"cup", "\\Ea%p1%' '%+%c%p2%' '%+%c", "\\Ea%S%S", "p1=%v1%' '%-, p2=%v2%' '%-",},
            //
            //			[cup]  [\Ea%p1%?%p1%{95}%>%t^A%{96}%-%;%' '%+%c%p2%?%p2%{95}%>%t^A%{96}%-%;%' '%+%c]
            //					c108-4 c108-4p c108-8 c108-na c108-rv-4p c108-rv-na c108-rv c108-w c108
            {"cup", "\\Ea%p1%?%p1%{95}%>%t^A%{96}%-%;%' '%+%c%p2%?%p2%{95}%>%t^A%{96}%-%;%' '%+%c",
                "\\Ea%S%S", "p1=%v1%' '%-			, p2=%v2%' '%-",
                "\\Ea^A%S%S", "p1=%v1%' '%-%{96}%+	, p2=%v2%' '%-",
                "\\Ea%S^A%S", "p1=%v1%' '%-			, p2=%v2%' '%-%{96}%+",
                "\\Ea^A%S^A%S", "p1=%v1%' '%-%{96}%+	, p2=%v2%' '%-%{96}%+",
            },
            //
            //			[cup]  [\Ef%p2%' '%+%c%p1%' '%+%c] i100
            {"cup", "\\Ef%p2%' '%+%c%p1%' '%+%c", "\\Ef%S%S", "p1=%v2%' '%-, p2=%v1%' '%-",},
            //			[cup]  [\Ey%p1%' '%+%c%p2%' '%+%c] microkit
            {"cup", "\\Ey%p1%' '%+%c%p2%' '%+%c", "\\Ey%S%S", "p1=%v1%' '%-, p2=%v2%' '%-",},
            //			[cup]  [\Ey%p1%{32}%/%' '%+%c%p1%{31}%&%' '%+%c%p2%{32}%/%' '%+%c%p2%{31}%&%'@'%+%c] ibm3151Y ibm3164Y
            {"cup", "\\Ey%p1%{32}%/%' '%+%c%p1%{31}%&%' '%+%c%p2%{32}%/%' '%+%c%p2%{31}%&%'@'%+%c",
                "\\Ey%S%S%S%T", "p1=%v1%' '%-%{32}%*%v2%' '%-%+, p2=%v3%' '%-%{32}%*%v4%'@'%-%+",},
            //
            //			[cup]  [\E|%p1%' '%+%c%p2%' '%+%c] ct8500
            {"cup", "\\E|%p1%' '%+%c%p2%' '%+%c", "\\E|%S%S", "p1=%v1%' '%-, p2=%v2%' '%-",},
            //			[cup]  [\f%p2%'`'%\^%c%p1%'`'%\^%c] dm2500
            //	ERROR -				treat %\^ as %^
            {"cup", "\\f%p2%'`'%\\^%c%p1%'`'%\\^%c", "\\f%U%U", "p1=%v2%'`'%^, p2=%v1%'`'%^",},
            //			[cup]  [\f%p2%'`'%^Ec%p1%'`'%^Ec] exidy
            //	ERROR -				treat %^Ec as %^E%c
            {"cup", "\\f%p2%'`'%^Ec%p1%'`'%^Ec", "\\f%U%U", "p1=%v2%'`'%^, p2=%v1%'`'%^",},
            //			[cup]  [^AM%p2%d\,%p1%d\,] cg7900
            {"cup", "^AM%p2%d\\,%p1%d\\,", "^AM%d\\,%d\\,", "p1=%v2, p2=%v1",},
            //			[cup]  [^B%i%p1%c%p2%c] ca
            {"cup", "^B%i%p1%c%p2%c", "^B%c%c", "p1=%v1%{1}%-, p2=%v2%{1}%-",},
            //			[cup]  [^C%p2%c%p1%c] compucolor2
            {"cup", "^C%p2%c%p1%c", "^C%c%c", "p1=%v2, p2=%v1",},
            //			[cup]  [^E%p1%' '%+%c%p2%' '%+%c] ibm
            {"cup", "^E%p1%' '%+%c%p2%' '%+%c", "^E%S%S", "p1=%v1%' '%-, p2=%v2%' '%-",},
            //			[cup]  [^K%p1%' '%+%c^P%p1%{10}%/%{16}%*%p1%{10}%m%+%c] regent100 regent20 regent25 regent40-s regent40 regent60-na regent60
            //	ERROR - 		treat second %p1 as %p2
            {"cup", "^K%p1%' '%+%c^P%p1%{10}%/%{16}%*%p1%{10}%m%+%c",
                "^K%S^P%c", "p1=%v1%' '%-, p2=%v2%{16}%/%{10}%*%v2%{16}%m%+",},
            //
            //			[cup]  [^K%p1%'@'%+%c\E^E%p1%02d] a980
            //	ERROR - 		treat second %p1 as %p2
            {"cup", "^K%p1%'@'%+%c\\E^E%p1%02d", "^K%T\\E^E%02d", "p1=%v1%'@'%-, p2=%v2",},
            //			[cup]  [^K%p2%c%p1%c] swtp
            {"cup", "^K%p2%c%p1%c", "^K%c%c", "p1=%v2, p2=%v1",},
            //			[cup]  [^N%p1%c^P%p1%{10}%/%{16}%*%p1%{10}%m%+%c] it2
            //	ERROR - 		treat second %p1 as %p2
            {"cup", "^N%p1%c^P%p1%{10}%/%{16}%*%p1%{10}%m%+%c",
                "^N%c^P%c", "p1=%v1, p2=%v2%{16}%/%{10}%*%v2%{16}%m%+",},
            //			[cup]  [^O%p1%' '%+%c%p2%' '%+%c] intext
            {"cup", "^O%p1%' '%+%c%p2%' '%+%c", "^O%S%S", "p1=%v1%' '%-, p2=%v2%' '%-",},
            //			[cup]  [^O%p1%p1%{16}%m%{2}%*%-%'9'%+%c%p2%p2%{16}%m%{2}%*%-%'9'%+%c] delta
            {"cup", "^O%p1%p1%{16}%m%{2}%*%-%'9'%+%c%p2%p2%{16}%m%{2}%*%-%'9'%+%c",
                "^O%c%c", "p1=%{16}%v1%'9'%-%{16}%m%-%v1%'9'%-%+, p2=%{16}%v2%'9'%-%{16}%m%-%v2%'9'%-%+",},
            //			[cup]  [^O%p2%{10}%/%{16}%*%p2%{10}%m%+%c%p1%?%p1%{19}%>%t%{12}%+%;%'@'%+%c] aa
            {"cup", "^O%p2%{10}%/%{16}%*%p2%{10}%m%+%c%p1%?%p1%{19}%>%t%{12}%+%;%'@'%+%c",
                "^O%c%T", "p1=%v2%'@'%-%?%v2%'@'%-%{19}%>%t%{12}%-, p2=%v1%{16}%/%{10}%*%v1%{16}%m%+",},
            //			[cup]  [^P%p1%' '%+%c%p2%' '%+%c] mdl110 vc404-na vc404-s-na vc404-s vc404 vc415
            {"cup", "^P%p1%' '%+%c%p2%' '%+%c", "^P%S%S", "p1=%v1%' '%-, p2=%v2%' '%-",},
            //			[cup]  [^P%p2%c%p1%c] d200 dg
            {"cup", "^P%p2%c%p1%c", "^P%c%c", "p1=%v2, p2=%v1",},
            //			[cup]  [^P^Q%p2%c%p1%c] dtc
            {"cup", "^P^Q%p2%c%p1%c", "^P^Q%c%c", "p1=%v2, p2=%v1",},
            //			[cup]  [^T%p1%c%p2%c] act5s microterm5 microterm
            {"cup", "^T%p1%c%p2%c", "^T%c%c", "p1=%v1, p2=%v2",},
            //			[cup]  [^T%p1%{24}%+%c%p2%?%p2%{32}%>%t%{48}%+%;%{80}%+%c] mime-fb mime-hb mime mimefb mimehb
            {"cup", "^T%p1%{24}%+%c%p2%?%p2%{32}%>%t%{48}%+%;%{80}%+%c",
                "^T%R%S", "p1=%v1%{24}%-, p2=%v2%{80}%-%?%v2%{80}%-%{32}%>%t%{48}%-",},
            //			[cup]  [^W%p1%' '%+%c%p2%' '%+%c] xl83
            {"cup", "^W%p1%' '%+%c%p2%' '%+%c", "^W%S%S", "p1=%v1%' '%-, p2=%v2%' '%-",},
            //			[cup]  [^\%p2%' '%+%c%p1%' '%+%c] tek4023
            {"cup", "^\\%p2%' '%+%c%p1%' '%+%c", "^\\%S%S", "p1=%v2%' '%-, p2=%v1%' '%-",},
            //			[cup]  [^_%i%p1%{1}%-%c%p2%{1}%-%c] addrinfo
            {"cup", "^_%i%p1%{1}%-%c%p2%{1}%-%c", "^_%c%c", "p1=%v1, p2=%v2",},
            //			[cup]  [^~%p2%' '%+%c%p1%' '%+%c] dm1520 terak
            {"cup", "^~%p2%' '%+%c%p1%' '%+%c", "^~%S%S", "p1=%v2%' '%-, p2=%v1%' '%-",},
            //			[cup]  [l%p2%~%c%p1%~%c] tec400
            {"cup", "l%p2%~%c%p1%~%c", "l%c%c", "p1=%v2%~, p2=%v1%~",},
            //			[cup]  [~^Q%p2%c%p1%c] h1500 h1520 h2000 s1500
            {"cup", "~^Q%p2%c%p1%c", "~^Q%c%c", "p1=%v2, p2=%v1",},
            //
            //  String	parm_up_cursor;         // cuu     UP     Move cursor up #1 lines (PG*)
            //			[cuu]  [\E[%p1%dA] 4424-2 4424 75 85 AT386-MY aixtermY dt80-w dt80 dtterm env230 gigi pt-22 pt-23
            //						pt-24 pt-25 pt-26 pt-e pt-z pt132-e pt ref2dosY reflect2Y sqnt220Y st340Y sym220Y
            //						tab132-rv tab132-w-rv tab132-w tab132 to300-24 uvt1220Y uvt1224Y vt100-am vt100-nav-w
            //						vt100-nav vt100-np vt100-s-bot vt100-s vt100-w-nam vt100-w vt100Y vt100
            //						vt100am vt100nam vt100s vt100w vt125 vt132 vt220-am vt220Y vt220 vt300Y
            //						vt320-am vt320 wy60vt1 wy75-vb wy75-w wy75-wvb wy85-nx wy85-vb wy85-w wy85-wvb
            //						xtermY xterm xterms zterm
            {"cuu", "\\E[%p1%dA", "\\E[%dA", "p1=%v1",},
            //
            //			[cuu]  [^_up %p1%d\r] 4025-17 4025-17ws 4025 4025ex
            {"cuu", "^_up %p1%d\\r", "^_up %d\\r", "p1=%v1",},
            //				//  String	parm_dch;               // dch     DC     Delete #1 chars (PG*)
            //			[dch]  [\E[%p1%dP] 4424-2 4424 75 85 AT386-MY aixtermY dt80-w dt80 dtterm env230 pt-22
            //						pt-23 pt-24 pt-25 pt-26 pt-e pt-z pt132-e pt ref2dosY reflect2Y
            //						sqnt220Y st340Y sunY sym220Y tab132-rv tab132-w-rv tab132-w tab132 to300-24
            //						vt100-am vt100-nav-w vt100-nav vt100-np vt100-s-bot vt100-s vt100-w-nam vt100-w
            //						vt100 vt100am vt100nam vt100s vt100w vt125 vt132 vt220-am vt220Y vt220
            //						vt300Y vt320-am vt320 wy75-vb wy75-w wy75-wvb wy85-vb wy85-w wy85-wvb
            //						xtermY xterm xterms zterm
            {"dch", "\\E[%p1%dP", "\\E[%dP", "p1=%v1",},
            //
            //			[dch]  [\Ee%p1%' '%+%c] blit-pb blit cbblit
            {"dch", "\\Ee%p1%' '%+%c", "\\Ee%S", "p1=%v1%' '%-",},
            //
            //  String	parm_delete_line;       // dl      DL     Delete #1 lines (PG*)
            //			[dl]  [\EE%p1%' '%+%c] blit-pb blit cbblit
            {"dl", "\\EE%p1%' '%+%c", "\\EE%S", "p1=%v1%' '%-",},
            //			[dl]  [\E[%p1%dM] 4424-2 4424 75 85 AT386-MY aixtermY ansi-25Y dt80-w dt80 dtterm env230 pt-22
            //						pt-23 pt-24 pt-25 pt-26 pt-e pt-z pt132-e pt ref2dosY reflect2Y st340Y
            //						sunY sym220Y tab132-rv tab132-w-rv tab132-w tab132 to300-24 vt100-am
            //						vt100-nav-w vt100-nav vt100-np vt100-s-bot vt100-s vt100-w-nam vt100-w
            //						vt100 vt100am vt100nam vt100s vt100w vt125 vt132 vt220-am vt220Y
            //						vt220 vt300Y vt320-am vt320 wy75-vb wy75-w wy75-wvb wy85-nx wy85-vb wy85-w
            //						wy85-wvb xtermY xterm xterms zterm
            {"dl", "\\E[%p1%dM", "\\E[%dM", "p1=%v1",},
            //
            //			[dl]  [\Ee%p1%' '%+%c] oblit
            {"dl", "\\Ee%p1%' '%+%c", "\\Ee%S", "p1=%v1%' '%-",},
            //
            //  String	erase_chars;            // ech     ec     Erase #1 characters (PG)
            //			[ech]  [\E[%p1%dX]  75 85 aixtermY dtterm sym220Y vt220-am vt220 vt320-am vt320 wy75-vb
            //						wy75-w wy75-wvb wy85-nx wy85-vb wy85-w wy85-wvb
            {"ech", "\\E[%p1%dX", "\\E[%dX", "p1=%v1",},
            //
            //  String	column_address;         // hpa     ch     Set cursor column (PG)
            //			[hpa]  [\E&a%p1%dC] 110 150 2382 2392 2393 2394 2397 2621-48 2621-ba 2621-nl 2621-nt 2621-wl 2621
            //						2621k45 2621p 2622 2623 2624 2625 2626-12-s 2626-12 2626-12x40 2626-ns
            //						2626-s 2626-x40 2626 2627 2628 262x 2640 2640b 2644 2645 2647 2648 2703 300h
            //						300l 70092-w 70092 70094-w 70094 9020 9816te 9816teb 98204b 9826 9835 9836
            //						9837 98541 98543 98544 98546 98548 98549 98550 98700 98705 98720
            //						98730 98736 D1182A a1096a a1416a hk hp2621nl hp2621nt hp2621wl
            //						hpY hp hpsub hpterm plus
            {"hpa", "\\E&a%p1%dC", "\\E&a%dC", "p1=%v1",},
            //
            //			[hpa]  [\E[%i%p1%dG] 75 aixtermY intext2 wy75-vb wy75-w wy75-wvb
            {"hpa", "\\E[%i%p1%dG", "\\E[%dG", "p1=%v1%{1}%-",},
            //			[hpa]  [\E[%i%p1%d`] ansi-25Y pt-22 pt-23 pt-24 pt-25 pt-26 pt-e pt-z pt132-e pt
            {"hpa", "\\E[%i%p1%d`", "\\E[%d`", "p1=%v1%{1}%-",},
            //			[hpa]  [\E\t%p1%{1}%+%c] 1620-m8 diablo
            {"hpa", "\\E\\t%p1%{1}%+%c", "\\E\\t%c", "p1=%v1%{1}%-",},
            //			[hpa]  [\E]%p1%' '%+%c] f100-rv f100
            {"hpa", "\\E%p1%' '%+%c", "\\E%S", "p1=%v1%' '%-",},
            //			[hpa]  [^P%p1%{10}%/%{16}%*%p1%{10}%m%+%c] it2
            {"hpa", "^P%p1%{10}%/%{16}%*%p1%{10}%m%+%c", "^P%c", "p1=%v1%{16}%/%{10}%*%v1%{16}%m%+",},
            //
            //  String	parm_ich;               // ich     IC     Insert #1 blank chars (PG*)
            //			[ich]  [\E[%p1%d@] 4424-2 4424 75 85 AT386-MY aaa-18-rv aaa-18 aaa-20 aaa-22 aaa-24-rv aaa-24
            //						aaa-26 aaa-28 aaa-29-ctxt aaa-29-np aaa-29-rv aaa-29 aaa-30-ctxt
            //						aaa-30-rv-ctxt aaa-30-rv aaa-30-s-ctxt aaa-30-s-rv-ctxt aaa-30-s-rv
            //						aaa-30-s aaa-36-rv aaa-36 aaa-40-rv aaa-40 aaa-48-rv aaa-48 aaa-59
            //						aaa-60-rv aaa-60-s-rv aaa-60-s aaa-60 aaa-db aaa-unk aaa18 aaa20
            //						aaa22 aaa24 aaa26 aaa28 aaa29 aaa30 aaa36 aaa40 aaa48 aaa59 aaa60
            //						aaa aaadb ansi dtterm pt-22 pt-23 pt-24 pt-25 pt-26 pt-e pt-z
            //						pt132-e pt sunY sym220Y wy75-vb wy75-w wy75-wvb wy85-vb wy85-w
            //						wy85-wvb xtermY xterm xterms
            {"ich", "\\E[%p1%d@", "\\E[%d@", "p1=%v1",},
            //
            //			[ich]  [\Ef%p1%' '%+%c] blit-pb blit cbblit
            {"ich", "\\Ef%p1%' '%+%c", "\\Ef%S", "p1=%v1%' '%-",},
            //
            //  String	parm_insert_line;       // il      AL     Add #1 new blank lines (PG*)
            //			[il]  [\EF%p1%' '%+%c] blit-pb blit cbblit
            {"il", "\\EF%p1%' '%+%c", "\\EF%S", "p1=%v1%' '%-",},
            //			[il]  [\E[%p1%dL] 4424-2 4424 75 85 AT386-MY aixtermY ansi-25Y dt80-w dt80 dtterm env230
            //						pt-22 pt-23 pt-24 pt-25 pt-26 pt-e pt-z pt132-e pt ref2dosY reflect2Y
            //						st340Y sunY sym220Y tab132-rv tab132-w-rv tab132-w tab132 to300-24
            //						vt100-am vt100-nav-w vt100-nav vt100-np vt100-s-bot vt100-s vt100-w-nam
            //						vt100-w vt100 vt100am vt100nam vt100s vt100w vt125 vt132 vt220-am
            //						vt220Y vt220 vt300Y vt320-am vt320 wy75-vb wy75-w wy75-wvb wy85-nx
            //						wy85-vb wy85-w wy85-wvb xtermY xterm xterms zterm
            {"il", "\\E[%p1%dL", "\\E[%dL", "p1=%v1",},
            //
            //			[il]  [\Ef%p1%' '%+%c] oblit
            {"il", "\\Ef%p1%' '%+%c", "\\Ef%S", "p1=%v1%' '%-",},
            //			[il]  [^_up\r^_ili %p1%d\r] 4025-17 4025-17ws 4025 4025ex
            {"il", "^_up\\r^_ili %p1%d\\r", "^_up\\r^_ili %d\\r", "p1=%v1",},
            //
            //  String	parm_index;             // indn    SF     Scroll forward #1 lines (PG)
            //			[indn]  [\E&r%dU] 2393 2397
            //	ERROR - treat %d as %p1%d
            {"indn", "\\E&r%dU", "\\E&r%dU", "p1=%v1",},
            //			[indn]  [\E[%p1%dS] AT386-MY aixtermY pt-22 pt-23 pt-24 pt-25 pt-26 pt-e pt-z pt132-e pt
            {"indn", "\\E[%p1%dS", "\\E[%dS", "p1=%v1",},
            //
            //  String	prtr_non;               // mc5p    pO     Turn on the printer for #1 bytes
            //			[mc5p]  [\E&p%p1%dW] 150 2392 2393 2394 2397 70092-w 70092 70094-w 70094 plus
            {"mc5p", "\\E&p%p1%dW", "\\E&p%dW", "p1=%v1",},
            //			[mc5p]  [\EP%p1%03d] cbblit
            {"mc5p", "\\EP%p1%03d", "\\EP%03d", "p1=%v1",},
            //			[mc5p]  [\E[%p1%dv] aaa-18-rv aaa-18 aaa-20 aaa-22 aaa-24-rv aaa-24 aaa-26 aaa-28 aaa-29-ctxt
            //						aaa-29-np aaa-29-rv aaa-29 aaa-30-ctxt aaa-30-rv-ctxt aaa-30-rv
            //						aaa-30-s-ctxt aaa-30-s-rv-ctxt aaa-30-s-rv aaa-30-s aaa-36-rv aaa-36
            //						aaa-40-rv aaa-40 aaa-48-rv aaa-48 aaa-59 aaa-60-rv aaa-60-s-rv
            //						aaa-60-s aaa-60 aaa-db aaa-unk aaa18 aaa20 aaa22 aaa24 aaa26
            //						aaa28 aaa29 aaa30 aaa36 aaa40 aaa48 aaa59 aaa60 aaa aaadb
            {"mc5p", "\\E[%p1%dv", "\\E[%dv", "p1=%v1",},
            //
            //  String	pkey_key;               // pfkey   pk     Prog funct key #1 to type string #2
            //			[pfkey]  [\E&f%p1%dk%p2%l%dL%p2%s] 110 150 2382 2392 2393 2394 2397 2621-48 2621-ba 2621-nl 2621-nt 2621-wl
            //						2621 2621k45 2621p 2622 2623 2624 2625 2626-12-s 2626-12 2626-12x40
            //						2626-ns 2626-s 2626-x40 2626 2627 2628 262x 2640 2640b 2644 2645
            //						2647 2648 2703 300h 300l 70092-w 70092 70094-w 70094 9020 9816te
            //						9816teb 98204b 9826 9836 9837 98541 98543 98544 98546 98548 98549 98550
            //						98700 98705 98720 98730 98736 D1182A a1096a a1416a hk hp2621nl
            //						hp2621nt hp2621wl hp hpterm plus]
            {"pfkey", "\\E&f%p1%dk%p2%l%dL%p2%s", "\\E&f%dk%dL%J", "p1=%v1, p2=%v2, s1=%s1",},
            //
            //			[pfkey]  [\EZ0%p1%'?'%+%c%p2%s\177] 60
            {"pfkey", "\\EZ0%p1%'?'%+%c%p2%s\\177", "\\EZ0%X%p2%s\\177", "p1=%v1%'?'%-, s1=%s1",},
            //
            //  String	pkey_local;             // pfloc   pl     Prog funct key #1 to execute string #2
            //			[pfloc]  [\E&f1a%p1%dk%p2%l%dL%p2%s] 110 150 2382 2392 2393 2394 2397 2621-48 2621-ba 2621-nl 2621-nt 2621-wl
            //						2621 2621k45 2621p 2622 2623 2624 2625 2626-12-s 2626-12 2626-12x40
            //						2626-ns 2626-s 2626-x40 2626 2627 2628 262x 2640 2640b 2644 2645
            //						2647 2648 2703 300h 300l 70092-w 70092 70094-w 70094 9020 9816te
            //						9816teb 98204b 9826 9836 9837 98541 98543 98544 98546 98548 98549
            //						98550 98700 98705 98720 98730 98736 D1182A a1096a a1416a hk
            //						hp2621nl hp2621nt hp2621wl hp hpterm plus
            {"pfloc", "\\E&f1a%p1%dk%p2%l%dL%p2%s", "\\E&f1a%dk%dL%J", "p1=%v1, p2=%v2, s1=%s1",},
            //
            //			[pfloc]  [\EZ2%p1%'?'%+%c%p2%s\177] 120 60 wy120-25-w wy120-25 wy120-vb wy120-w-vb wy120-w wy60-25-w wy60-25
            {"pfloc", "\\EZ2%p1%'?'%+%c%p2%s\\177", "\\EZ2%X%p2%s\\177", "p1=%v1%'?'%-, s1=%s1",},
            //						wy60-2p wy60-42-w wy60-42 wy60-43-w wy60-43 wy60-vb wy60-w-vb wy60-w wy60old
            //
            //  String	pkey_xmit;              // pfx     px     Prog funct key #1 to xmit string #2
            //			[pfx]  [\E&f2a%p1%dk%p2%l%dL%p2%s] 110 150 2382 2392 2393 2394 2397 2621-48 2621-ba 2621-nl 2621-nt 2621-wl
            //						2621 2621k45 2621p 2622 2623 2624 2625 2626-12-s 2626-12 2626-12x40 2626-ns
            //						2626-s 2626-x40 2626 2627 2628 262x 2640 2640b 2644 2645 2647 2648
            //						2703 300h 300l 70092-w 70092 70094-w 70094 9020 9816te 9816teb 98204b
            //						9826 9836 9837 98541 98543 98544 98546 98548 98549 98550 98700 98705
            //						98720 98730 98736 D1182A a1096a a1416a hk hp2621nl hp2621nt hp2621wl
            //						hp hpterm plus
            {"pfx", "\\E&f2a%p1%dk%p2%l%dL%p2%s", "\\E&f2a%dk%dL%p2%J", "p1=%v1, p2=%v2, s1=%s1",},
            //
            //			[pfx]  [\EQ%p1%{1}%-%d'%p2%s'] AT386-MY
            {"pfx", "\\EQ%p1%{1}%-%d'%p2%s'", "\\EQ%d'%K'", "p1=%v1%{1}%+, s1=%s1",},
            //			[pfx]  [\EZ1%p1%'?'%+%c%p2%s\177] 120 60 wy120-25-w wy120-25 wy120-vb wy120-w-vb wy120-w wy60-25-w
            {"pfx", "\\EZ1%p1%'?'%+%c%p2%s\\177", "\\EZ1%X%s\\177", "p1=%v1%'?'%-, s1=%s1",},
            //						wy60-25 wy60-2p wy60-42-w wy60-42 wy60-43-w wy60-43 wy60-vb wy60-w-vb wy60-w wy60old
            //
            //			[pfx]  [\Ez%p1%'?'%+%c%p2%s\177] 30 50 wy30-vb wy50-vb wy50-w wy50-wvb
            {"pfx", "\\Ez%p1%'?'%+%c%p2%s\\177", "\\Ez%X%s\\177", "p1=%v1%'?'%-, s1=%s1",},
            //			[pfx]  [\Ez%p1%'?'%+%c%p2%s] c1003
            {"pfx", "\\Ez%p1%'?'%+%c%p2%s", "\\Ez%X%s", "p1=%v1%'?'%-, s1=%s1",},
            //
            //  String	plab_norm;              // pln     pn     Prog label #1 to show string #2
            //			[pln]  [\E&f%p1%dk%p2%l%dd0L%p2%s] 110 150 2382 2392 2393 2394 2397 2621-48 2621-ba 2621-nl 2621-nt 2621-wl
            {"pln", "\\E&f%p1%dk%p2%l%dd0L%p2%s", "\\E&f%dk%dd0L%J", "p1=%v1, p2=%v2, s1=%s1",},
            //						2621 2621k45 2621p 2622 2623 2624 2625 2626-12-s 2626-12 2626-12x40
            //						2626-ns 2626-s 2626-x40 2626 2627 2628 262x 2640 2640b 2644 2645
            //						2647 2648 2703 300h 300l 70092-w 70092 70094-w 70094 9020 9816te
            //						9816teb 98204b 9826 9836 9837 98541 98543 98544 98546 98548 98549
            //						98550 98700 98705 98720 98730 98736 D1182A a1096a a1416a hk
            //						hp2621nl hp2621nt hp2621wl hp hpterm plus
            //
            //			[pln]  [\Ez%p1%'/'%+%c%p2%s\r] 120 30 50 60 wy120-vb wy120-w-vb wy120-w wy30-vb wy50-vb wy50-w
            {"pln", "\\Ez%p1%'/'%+%c%p2%s\\r", "\\Ez%Y%p2%M\\r", "p1=%v1%'/'%-, s1=%s1",},
            //						wy50-wvb wy60-2p wy60-42-w wy60-42 wy60-vb wy60-w-vb wy60-w wy60old
            //
            //  String	repeat_char;            // rep     rp     Repeat char #1 #2 times.  (PG*)
//need rep2 - repeat last char also
//rep3 - backspace and then repeat character before last n times
//that way, character output wont block till the next characters are received
//for parser to recognize the sequence
            //			[rep]  [%p1%c\E[%p2%{1}%-%db] aaa-18-rv aaa-18 aaa-20 aaa-22 aaa-24-rv aaa-24 aaa-26 aaa-28
            //						aaa-29-ctxt aaa-29-np aaa-29-rv aaa-29 aaa-30-ctxt aaa-30-rv-ctxt aaa-30-rv
            //						aaa-30-s-ctxt aaa-30-s-rv-ctxt aaa-30-s-rv aaa-30-s aaa-36-rv aaa-36
            //						aaa-40-rv aaa-40 aaa-48-rv aaa-48 aaa-59 aaa-60-rv aaa-60-s-rv
            //						aaa-60-s aaa-60 aaa-db aaa-unk aaa18 aaa20 aaa22 aaa24 aaa26
            //						aaa28 aaa29 aaa30 aaa36 aaa40 aaa48 aaa59 aaa60 aaa aaadb ansi
            {"rep", "%p1%c\\E[%p2%{1}%-%db", "%c\\E[%db", "p1=%v1, p2=%v2%{1}%+",},
            //
            //			[rep]  [%p1%c\b\E[=1;%p2%dp\E[%p2%dC] pt-22 pt-23 pt-24 pt-25 pt-26 pt-e pt-z pt132-e pt
            {"rep", "%p1%c\\b\\E[=1;%p2%dp\\E[%p2%dC", "%c\\b\\E[=1;%dp\\E[%dC", "p1=%v1, p2=%v2",},
            //			[rep]  [\ER%p2%3d%p1%c] citoh-6lpi citoh-8lpi citoh-comp citoh-elite citoh-pica citoh-prop citoh
            {"rep", "\\ER%p2%3d%p1%c", "\\ER%3d%c", "p1=%v2, p2=%v1",},
            //			[rep]  [\Er%p1%c%p2%' '%+%c] c100-rv-na c100-rv-pp c100-rv c1004p c100 c100rv4p c100rv4pna
            //						c100rv4ppp c100rv c100rvna c100rvpp c100rvs c100s c108-4
            //						c108-4p c108-8 c108-na c108-rv-4p c108-rv-na c108-rv c108-w c108 oc100
            {"rep", "\\Er%p1%c%p2%' '%+%c", "\\Er%c%S", "p1=%v1, p2=%v2%' '%-",},
            //
            //  String	parm_rindex;            // rin     SR     Scroll backward #1 lines (PG)
            //			[rin]  [\E&r%dD] 2393 2397
            {"rin", "\\E&r%dD", "\\E&r%dD", "p1=%v1",},
            //			[rin]  [\E[%p1%dT] pt-22 pt-23 pt-24 pt-25 pt-26 pt-e pt-z pt132-e pt
            {"rin", "\\E[%p1%dT", "\\E[%dT", "p1=%v1",},
            //										// setb
            //			[setb]  [\E[4%p1%dm] ansi-25Y ansiY
            {"setb", "\\E[4%p1%dm", "\\E[4%dm", "p1=%v1",},
            //										// setf
            //			[setf]  [\E[3%p1%dm] ansi-25Y ansiY
            {"setf", "\\E[3%p1%dm", "\\E[3%dm", "p1=%v1",},
            //
            //  String	set_attributes;         // sgr     sa     Define the video attributes (PG9)
            // p1         standout		1
            // p2         underline     2
            // p3         reverse       4
            // p4         blink         8
            // p5         dim          16
            // p6         bold         32
            // p7         invis       256
            // p8         protect     512
            // p9         altcharset   64
            //			[sgr]  [%?%p8%t\E)%e\E(%;%?%p9%t\EH^B%e\EH^C%;\EG%'0'%?%p2%p6%|%t%{8}%|%;%?%p1%p3%|%p6%|%t%{4}%|%;%?%p4%t%{2}%|%;%?%p1%p5%|%t%{64}%|%;%?%p7%t%{1}%|%;%c]
            //					60 wy60-25-w wy60-25 wy60-2p wy60-42-w wy60-42 wy60-43-w wy60-43
            //					wy60-vb wy60-w-vb wy60-w wy60old

//for sgr strings with %s, split %s using ";" as separator
//the split numbers can then be matched to obtain the parameters

            {"sgr", "%?%p8%t\\E)%e\\E(%;%?%p9%t\\EH^B%e\\EH^C%;\\EG%'0'%?%p2%p6%|%t%{8}%|%;%?%p1%p3%|%p6%|%t%{4}%|%;%?%p4%t%{2}%|%;%?%p1%p5%|%t%{64}%|%;%?%p7%t%{1}%|%;%c",
                "\\E)", "p8=1",
                "\\E(", "p8=0",
                "\\EH^B", "p9=1",
                "\\EH^C", "p9=0",
                "\\EG%c", "p7=%?%v1%{1}%&%t1%e0%;, p4=%?%v1%{2}%&%t1%e0%;, p1|p3|p6=%?%v1%{4}%&%t1%e0%;, p2|p6=%?%v1%{8}%&%t1%e0%;, p1|p5=%?%v1%{64}%&%t1%e0%;",
            },
            //
            //			[sgr]  [%?%p8%t\E)%e\E(%;%?%p9%t\EcE%e\EcD%;\EG%'0'%?%p2%t%{8}%|%;%?%p1%p3%|%p6%|%t%{4}%|%;%?%p4%t%{2}%|%;%?%p1%p5%|%t%{64}%|%;%?%p7%t%{1}%|%;%c]
            //					120 wy120-25-w wy120-25 wy120-vb wy120-w-vb wy120-w
            {"sgr", "%?%p8%t\\E)%e\\E(%;%?%p9%t\\EcE%e\\EcD%;\\EG%'0'%?%p2%t%{8}%|%;%?%p1%p3%|%p6%|%t%{4}%|%;%?%p4%t%{2}%|%;%?%p1%p5%|%t%{64}%|%;%?%p7%t%{1}%|%;%c",
                "\\E)", "p8=1",
                "\\E(", "p8=0",
                "\\EcE", "p9=1",
                "\\EcD", "p9=0",
                "\\EG%c", "p7=%?%v1%{1}%&%t1%e0%;, p4=%?%v1%{2}%&%t1%e0%;, p1|p3|p6=%?%v1%{4}%&%t1%e0%;, p2=%?%v1%{8}%&%t1%e0%;, p|p5=%?%v1%{64}%&%t1%e0%;",
            },
            //
            //			[sgr]  [\E&d%?%p7%t%'s'%c%;%p1%p3%|%p6%|%{2}%*%p2%{4}%*%+%p4%+%p5%{8}%*%+%'@'%+%c%?%p9%t%'^N'%c%e%'^O'%c%;]
            //					110 150 2382 2392 2393 2394 2397 2621-48 2621-ba 2621-nl 2621-nt
            //					2621-wl 2621 2621k45 2621p 2622 2623 2624 2625 2626-12-s
            //					2626-12 2626-12x40 2626-ns 2626-s 2626-x40 2626 2627 2628
            //					262x 2640 2640b 2644 2645 2647 2648 2703 300h 300l 70092-w
            //					70092 70094-w 70094 9020 9816te 9816teb 98204b 9826 9836
            //					9837 98541 98543 98544 98546 98548 98549 98550 98700 98705
            //					98720 98730 98736 D1182A a1096a a1416a hk hp2621nl hp2621nt
            //					hp2621wl hpY hp hpsub hpterm plus
            {"sgr", "\\E&d%?%p7%t%'s'%c%;%p1%p3%|%p6%|%{2}%*%p2%{4}%*%+%p4%+%p5%{8}%*%+%'@'%+%c%?%p9%t%'^N'%c%e%'^O'%c%;",
                "\\E&ds%c", "p7=1, , p4=%v1%'@'%-%{1}%&, p1|p3|p6=%v1%'@'%-%{2}%&, p2=%v1%'@'%-%{4}%&, p5=%v1%'@'%-%{8}%&, ",
                "\\E&d%c", "p7=0, p4=%v1%'@'%-%{1}%&, p1|p3|p6=%v1%'@'%-%{2}%&, p2=%v1%'@'%-%{4}%&, p5=%v1%'@'%-%{8}%&, ",
            },
            //
            //			[sgr]  [\E4%'@'%?%p1%t%'A'%|%;%?%p2%t%'B'%|%;%?%p3%t%'A'%|%;%?%p4%t%'D'%|%;%?%p5%t%'@'%|%;%?%p6%t%'H'%|%;%?%p7%t%'P'%|%;%c%?%p9%t\E<A%e\E<@%;]
            //					ibm3151Y ibm3164Y
            {"sgr", "\\E4%'@'%?%p1%t%'A'%|%;%?%p2%t%'B'%|%;%?%p3%t%'A'%|%;%?%p4%t%'D'%|%;%?%p5%t%'@'%|%;%?%p6%t%'H'%|%;%?%p7%t%'P'%|%;%c%?%p9%t\\E<A%e\\E<@%;",
                "\\E4%c", "p1=%v1%'A'%&, p2=%v1%'B'%&, p3=%v1%'A'%&, p4=%v1%'D'%&, p5=%v1%'@'%&, p6=%v1%'H'%&, p7=%v1%'P'%&,",
                "\\E<A", "p9=1",
                "\\E<@", "p9=0",
            },
            //
            //			[sgr]  [\EG%'0'%?%p2%p6%|%t%{8}%|%;%?%p1%p3%|%p6%|%t%{4}%|%;%?%p4%t%{2}%|%;%?%p1%p5%|%t%{64}%|%;%?%p7%t%{1}%|%;%c%?%p8%t\E)%e\E(%;%?%p9%t\EH^B%e\EH^C%;]
            {"sgr", "\\EG%'0'%?%p2%p6%|%t%{8}%|%;%?%p1%p3%|%p6%|%t%{4}%|%;%?%p4%t%{2}%|%;%?%p1%p5%|%t%{64}%|%;%?%p7%t%{1}%|%;%c%?%p8%t\\E)%e\\E(%;%?%p9%t\\EH^B%e\\EH^C%;",
                "\\EG%c", "p7=%v1%{1}%&, p4=%v1%{2}%&, p1|p3|p6=%v1%{4}%&, p2|p6=%v1%{8}%&, p1|p5=%v1%{64}%&,",
                "\\E)", "p8=1",
                "\\E(", "p8=0",
                "\\EH^B", "p9=1",
                "\\EH^C", "p9=0",
            },
            //					50 wy50-vb wy50-w wy50-wvb
            //
            //			[sgr]  [\EG%'0'%?%p2%p6%|%t%{8}%|%;%?%p1%p3%|%p6%|%t%{4}%|%;%?%p4%t%{2}%|%;%?%p5%t%{64}%|%;%?%p7%t%{1}%|%;%c%?%p8%t\E)%e\E(%;%?%p9%t\EH^B%e\EH^C%;]
            {"sgr", "\\EG%'0'%?%p2%p6%|%t%{8}%|%;%?%p1%p3%|%p6%|%t%{4}%|%;%?%p4%t%{2}%|%;%?%p5%t%{64}%|%;%?%p7%t%{1}%|%;%c%?%p8%t\\E)%e\\E(%;%?%p9%t\\EH^B%e\\EH^C%;",
                "\\EG%c", "p7=%v1%{1}%&, p4=%v1%{2}%&, p1|p3|p6=%v1%{4}%&, p2|p6=%v1%{8}%&, p5=%v1%{64}%&,",
                "\\E)", "p8=1",
                "\\E(", "p8=0",
                "\\EH^B", "p9=1",
                "\\EH^C", "p9=0",
            },
            //					30 wy30-vb
            //
            //			[sgr]  [\E[%?%p1%!%t7;%;%?%p2%t4;%;%?%p3%t7;%;%?%p4%t5;%;%?%p6%t1;%;%?%p7%t8;%;m]
//%x == means contains
            {"sgr", "\\E[%?%p1%!%t7;%;%?%p2%t4;%;%?%p3%t7;%;%?%p4%t5;%;%?%p6%t1;%;%?%p7%t8;%;m",
                "\\E[%Lm", "p1=%?%s1%'7'%x%t0%e1%;, p2=%?%s1%'4'%x%t1%e0%;, p3=%?%s1%'7'%x%t1%e0%;, p4=%?%s1%'5'%x%t1%e0%;, p6=%?%s1%'1'%x%t1%e0%;, p7=%?%s1%'8'%x%t1%e0%;,",
            },
            //					aaa-18-rv aaa-24-rv aaa-29-rv aaa-30-rv-ctxt aaa-30-rv
            //					aaa-30-s-rv-ctxt aaa-30-s-rv aaa-36-rv aaa-40-rv aaa-48-rv
            //					aaa-60-rv aaa-60-s-rv aaa-rv-unk
            //
            //			[sgr]  [\E[%?%p1%t7;%;%?%p2%t4;%;%?%p3%t7;%;%?%p4%t5;%;%?%p6%t1;%;%?%p7%t8;%;m]
            //					aaa-18 aaa-20 aaa-22 aaa-24 aaa-26 aaa-28 aaa-29-ctxt aaa-29-np
            //					aaa-29 aaa-30-ctxt aaa-30-s-ctxt aaa-30-s aaa-36 aaa-40
            //					aaa-48 aaa-59 aaa-60-s aaa-60 aaa-db aaa-unk aaa18 aaa20
            //					aaa22 aaa24 aaa26 aaa28 aaa29 aaa30 aaa36 aaa40 aaa48
            //					aaa59 aaa60 aaa aaadb
            {"sgr", "\\E[%?%p1%t7;%;%?%p2%t4;%;%?%p3%t7;%;%?%p4%t5;%;%?%p6%t1;%;%?%p7%t8;%;m",
                "\\E[%Lm", "p1=%?%s1%'7'%x%t1%e0%;, p2=%?%s1%'4'%x%t1%e0%;, p3=%?%s1%'7'%x%t1%e0%;, p4=%?%s1%'5'%x%t1%e0%;, p6=%?%s1%'1'%x%t1%e0%;, p7=%?%s1%'8'%x%t1%e0%;,"
            },
            //
            //			[sgr]  [\E[%?%p1%t7;%;%?%p2%t4;%;%?%p3%t7;%;%?%p4%t5;%;%?%p6%t1;%;m]
            //					4424
            {"sgr", "\\E[%?%p1%t7;%;%?%p2%t4;%;%?%p3%t7;%;%?%p4%t5;%;%?%p6%t1;%;m",
                "\\E[%Lm", "p1=%?%s1%'7'%x%t1%e0%;, p2=%?%s1%'4'%x%t1%e0%;, p3=%?%s1%'7'%x%t1%e0%;, p4=%?%s1%'5'%x%t1%e0%;, p6=%?%s1%'1'%x%t1%e0%;,",
            },
            //
            //			[sgr]  [\E[%?%p1%t;7%;%?%p2%t;4%;%?%p3%t;7%;%?%p4%t;5%;%?%p6%t;1%;m%?%p9%t\E(0%e\E(B%;]
            //					vt220-am vt220 vt320-am vt320
            {"sgr", "\\E[%?%p1%t;7%;%?%p2%t;4%;%?%p3%t;7%;%?%p4%t;5%;%?%p6%t;1%;m%?%p9%t\\E(0%e\\E(B%;",
                "\\E[%Lm", "p1=%?%s1%'7'%x%t1%e0%;, p2=%?%s1%'4'%x%t1%e0%;, p3=%?%s1%'7'%x%t1%e0%;, p4=%?%s1%'5'%x%t1%e0%;, p6=%?%s1%'1'%x%t1%e0%;,",
                "\\E(0", "p9=1",
                "\\E(B", "p9=0",
            },
            //
            //			[sgr]  [\E[%?%p1%t;7%;%?%p2%t;4%;%?%p3%t;7%;%?%p4%t;5%;%?%p6%t;1%;m]
            //					dt80-w dt80 env230 tab132-rv tab132-w-rv tab132-w tab132
            //					vt100-am vt100-np vt100-s-bot vt100-s vt100-w-nam vt100-w
            //					vt100Y vt100 vt100am vt100nam vt100s vt100w vt125 vt132
            //					wy60vt1 xtermY xterm xterms zterm
            {"sgr", "\\E[%?%p1%t;7%;%?%p2%t;4%;%?%p3%t;7%;%?%p4%t;5%;%?%p6%t;1%;m",
                "\\E[%Lm", "p1=%?%s1%'7'%x%t1%e0%;, p2=%?%s1%'4'%x%t1%e0%;, p3=%?%s1%'7'%x%t1%e0%;, p4=%?%s1%'5'%x%t1%e0%;, p6=%?%s1%'1'%x%t1%e0%;,",
            },
            //
            //			[sgr]  [\E[%?%p1%t;7%;%?%p2%t;4%;%?%p3%t;7%;%?%p4%t;5%;%?%p6%t;12%;m]
            //					aixtermY
            {"sgr", "\\E[%?%p1%t;7%;%?%p2%t;4%;%?%p3%t;7%;%?%p4%t;5%;%?%p6%t;12%;m",
                "\\E[%Lm", "p1=%?%s1%'7'%x%t1%e0%;, p2=%?%s1%'4'%x%t1%e0%;, p3=%?%s1%'7'%x%t1%e0%;, p4=%?%s1%'5'%x%t1%e0%;, p6=%?%s1%'12'%x%t1%e0%;,",
            },
            //
            //			[sgr]  [\E[%{0}%?%p2%p6%|%t%{8}%|%;%?%p1%p3%|%p6%|%t%{16}%|%;%?%p4%t%{2}%|%;%?%p1%p5%|%t%{1}%|%;%?%p7%t%{4}%|%;%dp%?%p9%t^N%e^O%;]
            //					75 wy75-vb wy75-w wy75-wvb
            {"sgr", "\\E[%{0}%?%p2%p6%|%t%{8}%|%;%?%p1%p3%|%p6%|%t%{16}%|%;%?%p4%t%{2}%|%;%?%p1%p5%|%t%{1}%|%;%?%p7%t%{4}%|%;%dp%?%p9%t^N%e^O%;",
                "\\E[%d^N", "p9=1, p1|p5=%d%{1}%&, p4=%d%{2}%&, p7=%d%{4}%&, p2|p6=%d%{8}%&, p1|p3|p6=%d%{16}%&, ",
                "\\E[%d^O", "p9=0, p1|p5=%d%{1}%&, p4=%d%{2}%&, p7=%d%{4}%&, p2|p6=%d%{8}%&, p1|p3|p6=%d%{16}%&, ",
            },
            //
            //			[sgr]  [\E[0%?%p1%t;1%;%?%p2%t;4%;%?%p3%t;7%;%?%p4%t;5%;%?%p6%t;1%;%?%p7%t;3%;%?%p8%t%;m%?%p9%t\E)0^N%e^O%;]
            //				uvt1220Y uvt1224Y
            {"sgr", "\\E[0%?%p1%t;1%;%?%p2%t;4%;%?%p3%t;7%;%?%p4%t;5%;%?%p6%t;1%;%?%p7%t;3%;%?%p8%t%;m%?%p9%t\\E)0^N%e^O%;",
                "\\E[0%Lm\\E)0^N", "p9=1, p1=%?%s1%'1'%x%t1%e0%;, p2=%?%s1%'4'%x%t1%e0%;, p3=%?%s1%'7'%x%t1%e0%;, p4=%?%s1%'5'%x%t1%e0%;, p6=%?%s1%'1'%x%t1%e0%;, p7=%?%s1%'3'%x%t1%e0%;, ",
                "\\E[0%Lm^O", "p9=0, p1=%?%s1%'1'%x%t1%e0%;, p2=%?%s1%'4'%x%t1%e0%;, p3=%?%s1%'7'%x%t1%e0%;, p4=%?%s1%'5'%x%t1%e0%;, p6=%?%s1%'1'%x%t1%e0%;, p7=%?%s1%'3'%x%t1%e0%;, ",
            },
            //
            //			[sgr]  [\E[0%?%p1%t;2;7%;%?%p2%t;4%;%?%p3%t;7%;%?%p4%t;5%;%?%p5%t;2%;%?%p6%t;1%;%?%p7%t;8%;m%?%p9%t^N%e^O%;]
            //				dtterm
            // fudge: use 2 to switch on p1 and 7 to switch on p3
            {"sgr", "\\E[0%?%p1%t;2;7%;%?%p2%t;4%;%?%p3%t;7%;%?%p4%t;5%;%?%p5%t;2%;%?%p6%t;1%;%?%p7%t;8%;m%?%p9%t^N%e^O%;",
                "\\E[0%Lm^N", "p9=1, p1=%?%s1%'2'%x%t1%e0%;, p2=%?%s1%'4'%x%t1%e0%;, p3=%?%s1%'7'%x%t1%e0%;, p4=%?%s1%'5'%x%t1%e0%;, p5=%?%s1%'2'%x%t1%e0%;, p6=%?%s1%'1'%x%t1%e0%;, p7=%?%s1%'8'%x%t1%e0%;, ",
                "\\E[0%Lm^O", "p9=0, p1=%?%s1%'2'%x%t1%e0%;, p2=%?%s1%'4'%x%t1%e0%;, p3=%?%s1%'7'%x%t1%e0%;, p4=%?%s1%'5'%x%t1%e0%;, p5=%?%s1%'2'%x%t1%e0%;, p6=%?%s1%'1'%x%t1%e0%;, p7=%?%s1%'8'%x%t1%e0%;, ",
            },
            //
            //			[sgr]  [\E[0%?%p1%t;7%;%?%p2%t;4%;%?%p3%t;7%;%?%p4%t;5%;%?%p6%t;1%;m]
            //				ansi
            {"sgr", "\\E[0%?%p1%t;7%;%?%p2%t;4%;%?%p3%t;7%;%?%p4%t;5%;%?%p6%t;1%;m",
                "\\E[0%Lm", "p1=%?%s1%'7'%x%t1%e0%;, p2=%?%s1%'4'%x%t1%e0%;, p3=%?%s1%'7'%x%t1%e0%;, p4=%?%s1%'5'%x%t1%e0%;, p6=%?%s1%'1'%x%t1%e0%;, "
            },
            //
            //			[sgr]  [\E[0%?%p2%t;4%;%?%p3%t;7%;%?%p4%t;5%;%?%p5%t;2%;%?%p6%p1%|%t;1%;%?%p7%t;8%;m%?%p9%t^N%e^O%;]
            {"sgr", "\\E[0%?%p2%t;4%;%?%p3%t;7%;%?%p4%t;5%;%?%p5%t;2%;%?%p6%p1%|%t;1%;%?%p7%t;8%;m%?%p9%t^N%e^O%;",
                "\\E[0%Lm^N", "p9=1, p2=%?%s1%'4'%x%t1%e0%;, p3=%?%s1%'7'%x%t1%e0%;, p4=%?%s1%'5'%x%t1%e0%;, p5=%?%s1%'2'%x%t1%e0%;, p6|p1=%?%s1%'1'%x%t1%e0%;, p7=%?%s1%'8'%x%t1%e0%;, ",
                "\\E[0%Lm^O", "p9=0, p2=%?%s1%'4'%x%t1%e0%;, p3=%?%s1%'7'%x%t1%e0%;, p4=%?%s1%'5'%x%t1%e0%;, p5=%?%s1%'2'%x%t1%e0%;, p6|p1=%?%s1%'1'%x%t1%e0%;, p7=%?%s1%'8'%x%t1%e0%;, ",
            },
            //				85 wy85-nx wy85-vb wy85-w wy85-wvb
            //
            //			[sgr]  [\E[0%?%p6%t;1%;%?%p2%t;4%;%?%p1%p3%|%t;7%;m]
            //				sunY
            {"sgr", "\\E[0%?%p6%t;1%;%?%p2%t;4%;%?%p1%p3%|%t;7%;m",
                "\\E[0%Lm", "p6=%?%s1%'1'%x%t1%e0%;, p2=%?%s1%'4'%x%t1%e0%;, p1|p3=%?%s1%'7'%x%t1%e0%;,",
            },
            //
            //			[sgr]  [\E[0;%?%p1%p3%|%t7;%;%?%p2%t4;%;%?%p4%t5;%;%?%p5%t2;%;%?%p7%t11%e10%;m%?%p9%t^N%e^O%;]
            {"sgr", "\\E[0;%?%p1%p3%|%t7;%;%?%p2%t4;%;%?%p4%t5;%;%?%p5%t2;%;%?%p7%t11%e10%;m%?%p9%t^N%e^O%;",
                "\\E[0;%Lm^N", "p9=1, p2=%?%s1%'4'%x%t1%e0%;, p1|p3=%?%s1%'7'%x%t1%e0%;, p4=%?%s1%'5'%x%t1%e0%;, p5=%?%s1%'2'%x%t1%e0%;, p7=%?%s1%'11'%x%t1%e0%;, ",
                "\\E[0;%Lm^O", "p9=0, p2=%?%s1%'4'%x%t1%e0%;, p1|p3=%?%s1%'7'%x%t1%e0%;, p4=%?%s1%'5'%x%t1%e0%;, p5=%?%s1%'2'%x%t1%e0%;, p7=%?%s1%'11'%x%t1%e0%;, ",
            },
            //				pt-22 pt-23 pt-24 pt-25 pt-26 pt-e pt-z pt132-e pt
            //
            //			[sgr]  [\E[10m\E[0%?%p1%p3%|%t;7%;%?%p2%t;4%;%?%p4%t;5%;%?%p6%t;1%;%?%p9%t;12%;%?%p7%t;9%;m]
            {"sgr", "\\E[10m\\E[0%?%p1%p3%|%t;7%;%?%p2%t;4%;%?%p4%t;5%;%?%p6%t;1%;%?%p9%t;12%;%?%p7%t;9%;m",
                "\\E[10m\\E[0%Lm", "p2=%?%s1%'4'%x%t1%e0%;, p1|p3=%?%s1%'7'%x%t1%e0%;, p4=%?%s1%'5'%x%t1%e0%;, p6=%?%s1%'1'%x%t1%e0%;, p7=%?%s1%'9'%x%t1%e0%;, p9=%?%s1%'12'%x%t1%e0%;, ",
            },
            //				AT386-MY
            //
            // ???  String	to_status_line;         // tsl     ts     Go to status line; column #1
            //			[tsl]  [\E z"\E?\E^E\EE\Ea %+]
            //				c108-4 c108-4p c108-8 c108-na c108-rv-4p c108-rv-na c108-rv c108-w c108
            {"tsl", "\\E z\"\\E?\\E^E\\EE\\Ea %+", "\\E z\"\\E?\\E^E\\EE\\Ea ", "p1=0",},
            //
            //			[tsl]  [\E&w7f2p2I\E&w4f2I\r\EK\E&a%p1%dC]
            //				2626-12-s 2626-s
            {"tsl", "\\E&w7f2p2I\\E&w4f2I\\r\\EK\\E&a%p1%dC", "\\E&w7f2p2I\\E&w4f2I\\r\\EK\\E&a%dC", "p1=%v1",},
            //
            //			[tsl]  [\E7\E[1;%p1%dH\E[1K]
            //					vt100-s vt100s
            {"tsl", "\\E7\\E[1;%p1%dH\\E[1K", "\\E7\\E[1;%dH\\E[1K", "p1=%v1",},
            //
            //			[tsl]  [\E7\E[24;%p1%dH\E[1K]
            //					vt100-s-bot
            {"tsl", "\\E7\\E[24;%p1%dH\\E[1K", "\\E7\\E[24;%dH\\E[1K", "p1=%v1",},
            //
            //			[tsl]  [\E[1$}\E[1;%i%p1%dH]
            //					st340Y
            {"tsl", "\\E[1$}\\E[1;%i%p1%dH", "\\E[1$}\\E[1;%dH", "p1=%v1%{1}%-",},
            //
            //			[tsl]  [\E[1$}\E[;H%?%p1%{0}%=%!%t\E[%p1%dC%;]
            //					reflect2Y sqnt220Y vt300Y
            {"tsl", "\\E[1$}\\E[;H%?%p1%{0}%=%!%t\\E[%p1%dC%;",
                "\\E[1$}\\E[;H\\E[%dC", "p1=%v1",
                "\\E[1$}\\E[;H", "p1=0",
            },
            //
            //			[tsl]  [\E[40h\E7\E[25;%i%p1%dH]
            //					85 wy85-nx wy85-vb wy85-w wy85-wvb
            {"tsl", "\\E[40h\\E7\\E[25;%i%p1%dH", "\\E[40h\\E7\\E[25;%dH", "p1=%v1%{1}%-",},
            //
            //			[tsl]  [\E[<0i%?%p1%{0}%=%!%t\E[%p1%dC%;]
            //					uvt1224Y
            {"tsl", "\\E[<0i%?%p1%{0}%=%!%t\\E[%p1%dC%;",
                "\\E[<0i\\E[%dC", "p1=%v1",
                "\\E[<0i", "p1=0",
            },
            //
            //			[tsl]  [\E[=2;%i%p1%d@]
            //					pt-22 pt-23 pt-24 pt-25 pt-26 pt-e pt132-e pt
            {"tsl", "\\E[=2;%i%p1%d@", "\\E[=2;%d@", "p1=%v1%{1}%-",},
            //
            //			[tsl]  [\E[>51h\E[1;%p1%dH\E[2K]
            //					aaa-29-ctxt aaa-29-np aaa-29-rv aaa-29 aaa-30-s-ctxt
            //					aaa-30-s-rv-ctxt aaa-30-s-rv aaa-30-s aaa-59 aaa-60-s-rv
            //					aaa-60-s aaa29 aaa59
            {"tsl", "\\E[>51h\\E[1;%p1%dH\\E[2K", "\\E[>51h\\E[1;%dH\\E[2K", "p1=%v1",},
            //
            //			[tsl]  [\E[?%p1%dT]
            //					aixtermY
            {"tsl", "\\E[?%p1%dT", "\\E[?%dT", "p1=%v1",},
            //
            //			[tsl]  [\Ej\Ex5\EY8%p1%' '%+%c\Eo\Eo]
            //					altoh19 h19-bs h19-pb h19-smul h19-u h19 h19b h19bs h19u reach
            {"tsl", "\\Ej\\Ex5\\EY8%p1%' '%+%c\\Eo\\Eo", "\\Ej\\Ex5\\EY8%S\\Eo\\Eo", "p1=%v1%' '%-",},
            //
            //			[tsl]  [\r\EK\EG %p2%' '%+%c]
            //					1linepty
            {"tsl", "\\r\\EK\\EG %p2%' '%+%c", "\\r\\EK\\EG %S", "p1=%v1%' '%-",},
            //
            //  String	row_address;            // vpa     cv     Vertical position absolute (set row) (PG)
            //			[vpa]  [\E&a%p1%dR]
            {"vpa", "\\E&a%p1%dR", "\\E&a%dR", "p1=%v1",},
            //					2621-48
            //
            //			[vpa]  [\E&a%p1%dY]
            //					110 150 2382 2392 2393 2394 2397 2621-ba 2621-nl 2621-nt 2621-wl 2621
            //					2621k45 2621p 2622 2623 2624 2625 2626-12-s 2626-12 2626-12x40 2626-ns
            //					2626-s 2626-x40 2626 2627 2628 262x 2644 2645 2647 2648 2703 300h
            //					300l 70092-w 70092 70094-w 70094 9020 9816te 9816teb 98204b 9826
            //					9835 9836 9837 98541 98543 98544 98546 98548 98549 98550 98700
            //					98705 98720 98730 98736 D1182A a1096a a1416a hk hp2621nl hp2621nt
            //					hp2621wl hpY hp hpterm plus
            {"vpa", "\\E&a%p1%dY", "\\E&a%dY", "p1=%v1",},
            //
            //			[vpa]  [\E[%i%p1%de]  pt-22 pt-23 pt-24 pt-25 pt-26 pt-e pt-z pt132-e pt
            {"vpa", "\\E[%i%p1%de", "\\E[%de", "p1=%v1%{1}%-",},
            //
            //			[vpa]  [\E[%p1%' '%+%c]	f100-rv f100
            {"vpa", "\\E[%p1%' '%+%c", "\\E[%S", "p1=%v1%' '%-",},
            //
            //			[vpa]  [^K%p1%c]	it2
            {"vpa", "^K%p1%c", "^K%c", "p1=%v1",},
        };

        String defaultParseFile = "dfltparse";
        FileOutputStream f = null;
        PrintWriter out = null;

        try {
            // Create new URL to prefsave program with file name appended
            f = new FileOutputStream(defaultParseFile);
            out = new PrintWriter(f);

            int iMax = defaultParamParse.length;
            for (int i = 0; i < iMax; i++) {
                int jMax = defaultParamParse[i].length;
                out.print("{ " + defaultParamParse[i][0] + ", ");
                for (int j = 1; j < jMax; j++) {
                    out.print(defaultParamParse[i][j] + ", ");
                }
                out.println("}, ");
            }
        } catch (IOException e) {
            System.out.println("I/O error writing options to file [" + defaultParseFile + "]");
        } finally {
            if (out != null)
                out.close();
            out = null;
        }
    }

    /**
     * Read the parse file and create an array of array of strings.
     * <p>
     * Each row in the array has the following strings:
     * 1. Terminal Capability
     * 2. Original Terminal String
     * <p>
     * ZERO OR MORE PAIRS OF THE FOLLOWING TWO STRINGS:
     * <p>
     * 3. String specifying the characters/tokens expected to be received by the
     * terminal emulator.
     * 4. String specifying the formulas to be used to determine variable
     * values using the received characters/tokens.
     * <p>
     * NOTE: All these strings above have \ and ^ sequences converted to characters.
     */
    private String[][] readDefaultParseFile(StringBuffer sb) {
        String[][] retVal = null;
        TermInfoTokenizer tokenizer = new TermInfoTokenizer(sb.toString());
        Vector vector = new Vector(30);
        String[] arr = new String[100];

        // get and process the other tokens

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token == null)
                break;
            if (token.length() == 0)
                continue;

            if (token.equals("{")) {
                int arrayCnt2 = 0;
                while (tokenizer.hasMoreTokens()) {
                    String subToken = tokenizer.nextToken();
                    if (subToken == null)
                        break;
                    if (subToken.length() == 0)
                        continue;
                    if (subToken.equals("}"))
                        break;
                    if (arrayCnt2 < 100) {
                        arr[arrayCnt2] = subToken;
                        arrayCnt2++;
                    }
                }
                if (arrayCnt2 >= 3) {
                    String[] tmp = new String[arrayCnt2];
                    for (int i = 0; i < arrayCnt2; i++)
                        tmp[i] = arr[i];
                    vector.addElement(tmp);
                }
            } else {
                System.out.println("Ignoring token [" + token + "]");
            }
        }
        int iMax = vector.size();
        if (iMax > 0) {
            retVal = new String[iMax][];
            for (int i = 0; i < iMax; i++) {
                retVal[i] = (String[]) vector.elementAt(i);
            }
        }
        return retVal;
    }

}
