package jakobev.ytksoundboard;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Marv & Jutta on 16.11.2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String LOG_TAG = "DATABASEHANDLER";
    private static final String DATABASE_NAME = "soundboard.db";
    private static final int DATABASE_VERSION = 11;

    //Tabelle 1 = Alle Sounds
    private static final String MAIN_TABLE = "main_table";
    private static final String MAIN_ID = "_id";
    private static final String MAIN_NAME = "soundName";
    private static final String MAIN_ITEM_ID = "soundID";

    //Tabelle 2 = Favoriten
    private static final String FAVORITES_TABLE = "favorites_table";
    private static final String FAVORITES_ID = "_id";
    private static final String FAVORITES_NAME = "favoName";
    private static final String FAVORITES_ITEM_ID = "favoID";

    private static final String SQL_CREATE_MAIN_TABLE = "CREATE TABLE IF NOT EXISTS " + MAIN_TABLE + "(" + MAIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MAIN_NAME + " TEXT, " + MAIN_ITEM_ID + " INTEGER unique);";
    private static final String SQL_CREATE_FAVORITES_TABLE = "CREATE TABLE IF NOT EXISTS " + FAVORITES_TABLE + "(" + FAVORITES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FAVORITES_NAME + " TEXT, " + FAVORITES_ITEM_ID + " INTEGER);";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL(SQL_CREATE_MAIN_TABLE);
            db.execSQL(SQL_CREATE_FAVORITES_TABLE);

        }catch (Exception e){
            Log.e(LOG_TAG, "Fehler beim erstellen der Datenbank"+ e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + MAIN_TABLE);
        onCreate(db);
    }
    private boolean verification(SQLiteDatabase database, String tableName,String idColumn,Integer soundID){
        int count = -1;
        Cursor cursor = null;

        try {

            String query = "SELECT * FROM " + tableName + " WHERE " + idColumn + " = " + soundID;
            cursor = database.rawQuery(query,null);

            if (cursor.moveToFirst())
                count = cursor.getInt(0);

            return (count > 0);

        }finally {

            if (cursor != null)
                cursor.close();
        }
    }

    public void createSoundCollection(Context context){

        List<String> nameList = Arrays.asList(context.getResources().getStringArray(R.array.soundNames));
        SoundObject[] soundItems = {
                new SoundObject(nameList.get(0), R.raw.feuerteufel),
                new SoundObject(nameList.get(1), R.raw.naa_herrlustig),
                new SoundObject(nameList.get(2), R.raw.naa_herrlustig_full),
                new SoundObject(nameList.get(3), R.raw.paschulke_mystisch),
                new SoundObject(nameList.get(4), R.raw.paschulke_siehnach),
                new SoundObject(nameList.get(5), R.raw.peter_apocalypse),
                new SoundObject(nameList.get(6), R.raw.peter_brauch_pilze),
                new SoundObject(nameList.get(7), R.raw.peter_herrnachbar),
                new SoundObject(nameList.get(8), R.raw.peter_kein_pilz),
                new SoundObject(nameList.get(9), R.raw.peter_pflueckt_pilz),
                new SoundObject(nameList.get(10), R.raw.peter_pilze_meinstdu),
                new SoundObject(nameList.get(11), R.raw.peter_pilzesprechen),
                new SoundObject(nameList.get(12), R.raw.peter_psychointro),
                new SoundObject(nameList.get(13), R.raw.peter_scheisshaus),
                new SoundObject(nameList.get(14), R.raw.peter_sicher_pilze),
                new SoundObject(nameList.get(15), R.raw.schrei),
                new SoundObject(nameList.get(16),R.raw.meister_hart_wie),
                new SoundObject(nameList.get(17),R.raw.meister_nackig),
                new SoundObject(nameList.get(18),R.raw.meister_penis_acid),
                new SoundObject(nameList.get(19),R.raw.meister_probier_mal),
                new SoundObject(nameList.get(20),R.raw.meister_rightround),
                new SoundObject(nameList.get(21),R.raw.meister_ruelps),
                new SoundObject(nameList.get(22),R.raw.meister_saas),
                new SoundObject(nameList.get(23),R.raw.meister_warum_heissen_deutschlander),
                new SoundObject(nameList.get(24),R.raw.pilaf_5000watt),
                new SoundObject(nameList.get(25),R.raw.pilaf_furz_lache),
                new SoundObject(nameList.get(26),R.raw.pilaf_haarfarbe_hehe),
                new SoundObject(nameList.get(27),R.raw.pilaf_ja_bitte),
                new SoundObject(nameList.get(28),R.raw.pilaf_limbo),
                new SoundObject(nameList.get(29),R.raw.pilaf_robotnik_lache),
                new SoundObject(nameList.get(30),R.raw.pilaf_ruegenwalder_alle),
                new SoundObject(nameList.get(31),R.raw.pilaf_scooter_hehe),
                new SoundObject(nameList.get(32),R.raw.pilaf_songohan_ist_aus_deutschland),
                new SoundObject(nameList.get(33),R.raw.pilaf_steigt_aus_ihr_ganoven),
                new SoundObject(nameList.get(34),R.raw.pilaf_teewurst_aepfel_genau),
                new SoundObject(nameList.get(35),R.raw.pilaf_teewurst_halts_maul),
                new SoundObject(nameList.get(36),R.raw.pilaf_wer_ist_das),
                new SoundObject(nameList.get(37),R.raw.pyradonis_ahhhhh),
                new SoundObject(nameList.get(38),R.raw.pyradonis_ein_zwei_tropfen_jauchensaft),
                new SoundObject(nameList.get(39),R.raw.pyradonis_kiffen_spinnenbein),
                new SoundObject(nameList.get(40),R.raw.pyradonis_neeein),
                new SoundObject(nameList.get(41),R.raw.pyradonis_rezept),
                new SoundObject(nameList.get(42),R.raw.pyradonis_scheiss_doch_was_gut_schmeckt),
                new SoundObject(nameList.get(43),R.raw.pyradonis_warumnicht),
                new SoundObject(nameList.get(44),R.raw.lesch_allaella),
                new SoundObject(nameList.get(45),R.raw.lesch_ausreichendkacken),
                new SoundObject(nameList.get(46),R.raw.lesch_flussiges_eis),
                new SoundObject(nameList.get(47),R.raw.lesch_grosswiegehirn),
                new SoundObject(nameList.get(48),R.raw.lesch_kackealsobinich),
                new SoundObject(nameList.get(49),R.raw.lesch_kameramann),
                new SoundObject(nameList.get(50),R.raw.lesch_lache),
                new SoundObject(nameList.get(51),R.raw.lesch_mondfliegen),
                new SoundObject(nameList.get(52),R.raw.lesch_nasseralswasser),
                new SoundObject(nameList.get(53),R.raw.lesch_rationalismus),
                new SoundObject(nameList.get(54),R.raw.lesch_riesenbrocken),
                new SoundObject(nameList.get(55),R.raw.lesch_techno),
                new SoundObject(nameList.get(56),R.raw.lesch_wasseristnass),
                new SoundObject(nameList.get(57),R.raw.lesch_weltschopfer),
                new SoundObject(nameList.get(58),R.raw.spuksucher_siebenzwerge),
                new SoundObject(nameList.get(59),R.raw.spuksucher_abneigung),
                new SoundObject(nameList.get(60),R.raw.spuksucher_ahnenscheisse),
                new SoundObject(nameList.get(61),R.raw.spuksucher_altehaus_dicketitten),
                new SoundObject(nameList.get(62),R.raw.spuksucher_aufjedenfall_maennertitten),
                new SoundObject(nameList.get(63),R.raw.spuksucher_bahnschranken),
                new SoundObject(nameList.get(64),R.raw.spuksucher_boese_mama),
                new SoundObject(nameList.get(65),R.raw.spuksucher_danke_tschuss),
                new SoundObject(nameList.get(66),R.raw.spuksucher_der_naechste_morgen),
                new SoundObject(nameList.get(67),R.raw.spuksucher_erklarungfuralles),
                new SoundObject(nameList.get(68),R.raw.spuksucher_esel_reiten),
                new SoundObject(nameList.get(69),R.raw.spuksucher_fuckbach_sophie),
                new SoundObject(nameList.get(70),R.raw.spuksucher_gefuhlt),
                new SoundObject(nameList.get(71),R.raw.spuksucher_geheimnissvolleorte),
                new SoundObject(nameList.get(72),R.raw.spuksucher_graefinkackball),
                new SoundObject(nameList.get(73),R.raw.spuksucher_graf_scheisse_wird_fundig),
                new SoundObject(nameList.get(74),R.raw.spuksucher_guten_tag),
                new SoundObject(nameList.get(75),R.raw.spuksucher_henningisthenning),
                new SoundObject(nameList.get(76),R.raw.spuksucher_hier_bin_ich_uberfordert),
                new SoundObject(nameList.get(77),R.raw.spuksucher_i_need_your_help),
                new SoundObject(nameList.get(78),R.raw.spuksucher_ich_komme),
                new SoundObject(nameList.get(79),R.raw.spuksucher_ineed_your_help),
                new SoundObject(nameList.get(80),R.raw.spuksucher_intro),
                new SoundObject(nameList.get(81),R.raw.spuksucher_intro2),
                new SoundObject(nameList.get(82),R.raw.spuksucher_jadanke_tschuss),
                new SoundObject(nameList.get(83),R.raw.spuksucher_jungfer),
                new SoundObject(nameList.get(84),R.raw.spuksucher_jungfrau_fuck),
                new SoundObject(nameList.get(85),R.raw.spuksucher_kackbrett),
                new SoundObject(nameList.get(86),R.raw.spuksucher_keine_titten_mehr_gehoert),
                new SoundObject(nameList.get(87),R.raw.spuksucher_kinderspinnen),
                new SoundObject(nameList.get(88),R.raw.spuksucher_maennertitten),
                new SoundObject(nameList.get(89),R.raw.spuksucher_maennertitten_flur),
                new SoundObject(nameList.get(90),R.raw.spuksucher_mehr_erfahren),
                new SoundObject(nameList.get(91),R.raw.spuksucher_meinefrau),
                new SoundObject(nameList.get(92),R.raw.spuksucher_moor_versunken),
                new SoundObject(nameList.get(93),R.raw.spuksucher_moorgeister),
                new SoundObject(nameList.get(94),R.raw.spuksucher_nacht_burg),
                new SoundObject(nameList.get(95),R.raw.spuksucher_puzzleteile),
                new SoundObject(nameList.get(96),R.raw.spuksucher_raetsel),
                new SoundObject(nameList.get(97),R.raw.spuksucher_richtig_schwere_titten),
                new SoundObject(nameList.get(98),R.raw.spuksucher_ritual),
                new SoundObject(nameList.get(99),R.raw.spuksucher_satzfall_spukt),
                new SoundObject(nameList.get(100),R.raw.spuksucher_schaun_wa_mal),
                new SoundObject(nameList.get(101),R.raw.spuksucher_schwaaanz),
                new SoundObject(nameList.get(102),R.raw.spuksucher_schwanz_frueher_da),
                new SoundObject(nameList.get(103),R.raw.spuksucher_sperma_schoepfen),
                new SoundObject(nameList.get(104),R.raw.spuksucher_spuk_treiben),
                new SoundObject(nameList.get(105),R.raw.spuksucher_spukthier_nein),
                new SoundObject(nameList.get(106),R.raw.spuksucher_tuerhallo),
                new SoundObject(nameList.get(107),R.raw.spuksucher_voodoo_gelaber),
                new SoundObject(nameList.get(108),R.raw.spuksucher_voodoopriester),
                new SoundObject(nameList.get(109),R.raw.spuksucher_warum_klopfen_sie),
                new SoundObject(nameList.get(110),R.raw.spuksucher_wetterfahne),
                new SoundObject(nameList.get(111),R.raw.spuksucher_wirdoftgefragt),
                new SoundObject(nameList.get(112),R.raw.spuksucher_zehnuhr),
                new SoundObject(nameList.get(113),R.raw.spuksucher_zugestossen),




        };
        for (SoundObject i: soundItems)
            putIntoMain(i);
    }
    private void putIntoMain(SoundObject soundObject){

        SQLiteDatabase database = this.getWritableDatabase();

        if (!verification(database,MAIN_TABLE,MAIN_ITEM_ID,soundObject.getItemID())){

            try {

                ContentValues contentValues = new ContentValues();
                contentValues.put(MAIN_NAME,soundObject.getItemName());
                contentValues.put(MAIN_ITEM_ID,soundObject.getItemID());

                database.insert(MAIN_TABLE,null,contentValues);

            }catch (Exception e){
                Log.e(LOG_TAG,"(MAIN) Fehler beim Einfügen des Sounds" + e.getMessage());
            }finally {
                database.close();
            }
        }
    }

    public Cursor getSoundCollection(){

        SQLiteDatabase database = this.getReadableDatabase();

        return database.rawQuery("SELECT * FROM " + MAIN_TABLE + " ORDER BY " + MAIN_NAME, null);
    }

    public void addFavorites (SoundObject soundObject){

        SQLiteDatabase database = this.getWritableDatabase();

        if (!verification(database, FAVORITES_TABLE,FAVORITES_ITEM_ID,soundObject.getItemID())){

            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(FAVORITES_NAME,soundObject.getItemName());
                contentValues.put(FAVORITES_ITEM_ID,soundObject.getItemID());

                database.insert(FAVORITES_TABLE,null,contentValues);

            }catch (Exception e){
                Log.e(LOG_TAG,"(FAVORITES) Fehler beim Einfügen des Sounds" + e.getMessage());
            }finally {
                database.close();

            }
        }

    }

    public void removeFavorite(Context context, SoundObject soundObject){

        SQLiteDatabase database = this.getWritableDatabase();

        if (verification(database,FAVORITES_TABLE,FAVORITES_ITEM_ID,soundObject.getItemID())){

            try {

                database.delete(FAVORITES_TABLE,FAVORITES_ITEM_ID + " = " + soundObject.getItemID(),null);

                Activity activity = (Activity) context;
                Intent delete = activity.getIntent();
                activity.overridePendingTransition(0,0);
                delete.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                activity.finish();
                activity.overridePendingTransition(0,0);
                context.startActivity(delete);

            }catch (Exception e){
                Log.e(LOG_TAG,"(FAVORITES) Fehler beim löschen des Sounds" + e.getMessage());
            }finally {
                database.close();
            }
        }
    }

    public Cursor getFavorites(){

        SQLiteDatabase database = this.getReadableDatabase();

        return database.rawQuery("SELECT * FROM " + FAVORITES_TABLE + " ORDER BY " + FAVORITES_NAME , null);
    }

    public void updateFavorites(){

        SQLiteDatabase database = this.getWritableDatabase();

        try {

            Cursor favorite_content = database.rawQuery("SELECT * FROM " + FAVORITES_TABLE, null);

            if (favorite_content.getCount()== 0){

                Log.d(LOG_TAG,"Cursor ist leer");
                favorite_content.close();
            }
            while (favorite_content.moveToNext()){

                String entryName = favorite_content.getString(favorite_content.getColumnIndex(FAVORITES_NAME));

                Cursor updateEntry = database.rawQuery("SELECT * FROM " + MAIN_TABLE + " WHERE " + MAIN_NAME + " = '" + entryName + "'",null );

                if (updateEntry.getCount()==0){

                    Log.d(LOG_TAG,"Cursor ist leer");
                    updateEntry.close();
                }

                updateEntry.moveToFirst();

                if (favorite_content.getInt(favorite_content.getColumnIndex(FAVORITES_ITEM_ID)) != updateEntry.getInt(updateEntry.getColumnIndex(MAIN_ITEM_ID))){

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(FAVORITES_ITEM_ID,updateEntry.getInt(updateEntry.getColumnIndex(MAIN_ITEM_ID)));
                    database.update(FAVORITES_TABLE,contentValues,FAVORITES_NAME + " = '" + entryName + "'",null);
                }
            }

        }catch (Exception e){
            Log.e(LOG_TAG,"Fehler beim Aktualiesieren der Favoriten" + e.getMessage());
        }
    }

    public void update (){

        try {

            SQLiteDatabase database = this.getWritableDatabase();

            database.execSQL("DROP TABLE IF EXISTS " + MAIN_TABLE);

            database.execSQL(SQL_CREATE_MAIN_TABLE);

            database.close();

        }catch (Exception e){
            Log.e(LOG_TAG,"Fehler beim Aktualisieren der Main Tabelle" + e.getMessage());
        }
    }
}