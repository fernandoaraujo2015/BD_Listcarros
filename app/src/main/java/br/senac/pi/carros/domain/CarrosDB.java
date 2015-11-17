package br.senac.pi.carros.domain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aluno on 13/11/2015.
 */
public class CarrosDB extends SQLiteOpenHelper {
    private static final String TAG = "sqt";
    //Nome do banco
    private static final String NOME_BANCO = "cursoandroid.sqlite";
    private static final int VERSAO_BANCO = 1;

    public CarrosDB(Context context) {
        //context, nome do banco, factory, verso
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando a Tabela carro...");
        db.execSQL("CREATE TABLE IF NOT EXISTS carro (_id integer primary key autoincrement," + "modelo text, fabricante text, ano text, cor text, motor text);");
        Log.d(TAG, "Tabela carro criada com sucesso");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long save(Carros carro) {
        long id = carro.getId();
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("modelo", carro.getModelo());
            values.put("fabricante", carro.getFabricante());
            values.put("ano", carro.getAno());
            values.put("cor", carro.getCor());
            values.put("motor", carro.getMotor());
            if (id != 0) {
                String _id = String.valueOf(carro.getId());
                String[] whereArgs = new String[]{_id};
                //Updadte carro set values = ... where _id = ?
                //SELECT * FROM  carro WHERE id = 1;
                //SELECT carro SET nome, marca, WHERE _id 1;
                int count = db.update("carro", values, "_id=?", whereArgs);
                return count;
            } else {
                //insert into carro values (...)
                // INSERT INTO carro (nome, marca) values (
                id = db.insert("carro","",values);
                return id;
            }
        } finally {
            db.close();
        }
    }

    //Delete o carro
    public int delete(Carros carros) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            //delete from carro where _id = ?
            int count = db.delete("carro", "_id=?", new String[]{String.valueOf(carros.getId())});
            Log.i(TAG, "Delete [" + count + "] registro.");
            return count;
        } finally {
            db.close();
        }
    }

    //Consulta a Lista com todos os carros
    public List<Carros> findAll() {
        SQLiteDatabase db = getWritableDatabase();
        try {
            //SELECT *FROM carro;
            Cursor cursor = db.query("carro", null, null, null, null, null, null, null);
            return toList(cursor);

        } finally {
            db.close();
        }
    }

    public List<Carros> toList(Cursor cursor) {
        List<Carros> carros = new ArrayList<Carros>();
        if (cursor.moveToFirst()) {
            do {
                Carros carro = new Carros();
                carros.add(carro);
                //
                carro.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                carro.setModelo(cursor.getString(cursor.getColumnIndex("modelo")));
                carro.setFabricante(cursor.getString(cursor.getColumnIndex("fabricante")));
                carro.setAno(cursor.getString(cursor.getColumnIndex("ano")));
                carro.setCor(cursor.getString(cursor.getColumnIndex("cor")));
                carro.setMotor(cursor.getString(cursor.getColumnIndex("motor")));

            } while (cursor.moveToNext());
        }

    return carros;
}


}