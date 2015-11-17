package br.senac.pi.carros;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import br.senac.pi.carros.domain.Carros;
import br.senac.pi.carros.domain.CarrosDB;

public class ListCarro extends AppCompatActivity {
    private CursorAdapter dataSource;
    private SQLiteDatabase database;
    private static final String campos[] = {"modelo", "fabricante", "ano", "cor", "motor", "_id"};
    ListView listView;
    CarrosDB carrosDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_carro);
        listView = (ListView) findViewById(R.id.listView);
        carrosDB = new CarrosDB(this);
        database = carrosDB.getWritableDatabase();
        findViewById(R.id.btnexibe).setOnClickListener(listarCarros());
        // Chama Listener de Delete.
        listView.setOnItemClickListener(deletarItem());

    }

    private View.OnClickListener listarCarros() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor carros = database.query("carro", campos, null, null, null, null, null, null);
                if (carros.getCount() > 0) {
                    dataSource = new SimpleCursorAdapter(ListCarro.this, R.layout.row, carros, campos, new int[]{R.id.txtmodelo_carros, R.id.txtfabricante_carro, R.id.txtAno_carro, R.id.txtcor, R.id.txtmotor});
                    listView.setAdapter(dataSource);
                } else {
                    Toast.makeText(ListCarro.this, getString(R.string.zero_registros), Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    //Reponsável por recuperar o item do banco ID e fazer o delete.
    private AdapterView.OnItemClickListener deletarItem() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final long itemSelecionado = id;
                AlertDialog.Builder builder = new AlertDialog.Builder(ListCarro.this);
                builder.setTitle("Pergunta");
                builder.setMessage("O que deseja fazer");
                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ListCarro.this, "Clicou em Editou", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Deletar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("carro", "ID do item Selecionado: " + itemSelecionado);
                        Carros carro = new Carros();
                        carro.setId(itemSelecionado);
                        carrosDB.delete(carro);
                        listView.invalidate();
                    }

                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };
    }
}