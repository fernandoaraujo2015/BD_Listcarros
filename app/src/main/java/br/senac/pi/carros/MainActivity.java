package br.senac.pi.carros;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.senac.pi.carros.domain.Carros;
import br.senac.pi.carros.domain.CarrosDB;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btncadastrarcarro);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtModeloCarro = (EditText) findViewById(R.id.modelo);
                EditText edtFabricanteCarro = (EditText) findViewById(R.id.fabricante);
                EditText edtAnoCarro = (EditText) findViewById(R.id.ano);
                EditText edtCorCarro = (EditText) findViewById(R.id.edtCor);
                EditText edtMotorCarro = (EditText) findViewById(R.id.edtmotor);

                String modelo = edtModeloCarro.getText().toString();
                String fabricante = edtFabricanteCarro.getText().toString();
                String ano = edtAnoCarro.getText().toString();
                String cor = edtCorCarro.getText().toString();
                String motor = edtMotorCarro.getText().toString();

                Carros carro = new Carros();
                carro.setModelo(modelo);
                carro.setFabricante(fabricante);
                carro.setAno(ano);
                carro.setCor(cor);
                carro.setMotor(motor);

                CarrosDB carrosDB = new CarrosDB(MainActivity.this);
                if(carrosDB.save(carro) != -1) {
                    Toast.makeText(MainActivity.this, getString(R.string.sucesso), Toast.LENGTH_LONG).show();
                    edtModeloCarro.setText("");
                    edtFabricanteCarro.setText("");
                    edtAnoCarro.setText("");
                    edtCorCarro.setText("");
                    edtMotorCarro.setText("");
                    edtModeloCarro.requestFocus();
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.error), Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.btnexibircarros).setOnClickListener(ListCarro());

    }
    private  View.OnClickListener ListCarro() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, ListCarro.class);
                startActivity(intent);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
