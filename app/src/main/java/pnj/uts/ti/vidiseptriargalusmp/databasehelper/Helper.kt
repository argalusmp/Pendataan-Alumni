package pnj.uts.ti.vidiseptriargalusmp.databasehelper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import pnj.uts.ti.vidiseptriargalusmp.model.AlumniModel
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Helper(contex: Context): SQLiteOpenHelper(contex, DB_NAME,null, DB_VERSION) {

    companion object{
        private val DB_NAME = "data_alumni"
        private val DB_VERSION = 1
        private val TABLE_NAME = "alumni"
        private val ID = "id"
        private val NAMA_ALUMNI = "nama_alumni"
        private val TEMPAT_LAHIR = "tempat_lahir"
        private val TANGGAL_LAHIR = "tanggal_lahir"
        private val ALAMAT = "alamt"
        private val NIK = "nik"
        private val NO_HP = "nohp"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME(" +
                "$ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$NIK TEXT," +
                "$NAMA_ALUMNI TEXT," +
                "$TEMPAT_LAHIR TEXT," +
                "$TANGGAL_LAHIR DATE," +
                "$ALAMAT TEXT," +
                "$NO_HP TEXT)"

        db?.execSQL(CREATE_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    @SuppressLint("Range")
    fun getAllAlumni(): List<AlumniModel>{
        val listAlumni = ArrayList<AlumniModel>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val cursor: Cursor = db.rawQuery(selectQuery,null)
        if (cursor != null){
            if (cursor.moveToFirst()){
                do{
                    val alumni = AlumniModel()
                    alumni.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    alumni.nik = cursor.getString(cursor.getColumnIndex(NIK))
                    alumni.nama_alumni = cursor.getString(cursor.getColumnIndex(NAMA_ALUMNI))
                    alumni.tempat_lahir = cursor.getString(cursor.getColumnIndex(TEMPAT_LAHIR))
                    alumni.alamat = cursor.getString(cursor.getColumnIndex(ALAMAT))
                    alumni.nohp = cursor.getString(cursor.getColumnIndex(NO_HP))
                    val tanggalLahirColumnIndex = cursor.getColumnIndex(TANGGAL_LAHIR)
                    if (tanggalLahirColumnIndex != -1) {
                        val tanggalLahirInMillis = cursor.getLong(tanggalLahirColumnIndex)
                        val tanggalLahir = Date(tanggalLahirInMillis)
                        alumni.tanggal_lahir = tanggalLahir
                    }

                    listAlumni.add(alumni)
                }while (cursor.moveToNext())
            }
        }
        cursor.close()
        return listAlumni

    }

    //insert
    fun addAlumni(alumni: AlumniModel): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NIK,alumni.nik)
        values.put(NAMA_ALUMNI,alumni.nama_alumni)
        values.put(ALAMAT,alumni.alamat)
        values.put(TEMPAT_LAHIR,alumni.tempat_lahir)
        values.put(NO_HP,alumni.nohp)
        values.put(TANGGAL_LAHIR,alumni.tanggal_lahir?.time)

        val _success = db.insert(TABLE_NAME,null,values)
        db.close()
        return (Integer.parseInt("$_success")!= -1)
    }

    //getAlumni
    fun getAlumni(_id: Int): AlumniModel{
        val alumni = AlumniModel()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery,null)

        cursor?.moveToFirst()

        alumni.id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(ID)))
        alumni.nik = cursor.getString(cursor.getColumnIndexOrThrow(NIK))
        alumni.nama_alumni = cursor.getString(cursor.getColumnIndexOrThrow(NAMA_ALUMNI))
        alumni.tempat_lahir = cursor.getString(cursor.getColumnIndexOrThrow(TEMPAT_LAHIR))
        alumni.alamat = cursor.getString(cursor.getColumnIndexOrThrow(ALAMAT))
        alumni.nohp = cursor.getString(cursor.getColumnIndexOrThrow(NO_HP))
        val tanggalLahirColumnIndex = cursor.getColumnIndex(TANGGAL_LAHIR)
        if (tanggalLahirColumnIndex != -1) {
            val tanggalLahirInMillis = cursor.getLong(tanggalLahirColumnIndex)
            val tanggalLahir = Date(tanggalLahirInMillis)
            alumni.tanggal_lahir = tanggalLahir
        }
        cursor.close()
        return alumni
    }

    fun deleteAlumni(alumni: AlumniModel): Boolean{
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, ID + "=?", arrayOf(alumni.id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success")!= -1
    }

    //update
    fun updateAlumni(alumni :AlumniModel): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NIK,alumni.nik)
        values.put(NAMA_ALUMNI,alumni.nama_alumni)
        values.put(ALAMAT,alumni.alamat)
        values.put(TEMPAT_LAHIR,alumni.tempat_lahir)
        values.put(NO_HP,alumni.nohp)
        values.put(TANGGAL_LAHIR,alumni.tanggal_lahir?.time)

        val _success = db.update(TABLE_NAME,values,ID + "=?", arrayOf(alumni.id.toString()))
        db.close()
        return Integer.parseInt("$_success")!= -1
    }
}