package com.example.vuelav_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Models.RegisterRequest;
import com.example.vuelav_app.Logico.Response.UsuarioResponse;
import com.example.vuelav_app.Logico.Service.UsuarioService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registro extends AppCompatActivity {


    private UsuarioService usuarioService = Apis.getUsuarioService();

    private EditText nombre, apellido, cedula ,razonsocial, telefono, email, contra;
    private Button btnDates;
    private DatePickerDialog.OnDateSetListener setListener;
    private String fecha, sexo;
    private Spinner sppais;
    private RadioButton rdMasculino,rdFemenino;
    private CheckBox boxAceptarterminos;
    private boolean terminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btnDates = (Button) findViewById(R.id.btnDate);
        nombre = (EditText) findViewById(R.id.txtNombreRegistro);
        apellido = (EditText) findViewById(R.id.txtApellidoRegistro);
       cedula = (EditText) findViewById(R.id.txtCedulaRegistro);
        telefono = (EditText) findViewById(R.id.txtCelularRegistro);
        razonsocial = (EditText)findViewById(R.id.txtRazonSocialRegistro);
        email = (EditText) findViewById(R.id.txtUsernameRegistro);
        contra = (EditText) findViewById(R.id.txtPasswordregistro);
        sppais = findViewById(R.id.spPaisRegistro);
        rdMasculino = findViewById(R.id.rdregistromasculino);
        rdFemenino = findViewById(R.id.rdregistrofemenino);
        boxAceptarterminos = findViewById(R.id.boxterminoscondiciones);
        CargarPaises();
        IniciarFecha();

    }

    private void IniciarFecha(){
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        btnDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Registro.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayofMoth) {
                Calendar mCalendar = Calendar.getInstance();
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayofMoth);
                String selectDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
                btnDates.setText(selectDate);
                System.out.println(selectDate);

                SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                fecha = formats.format(mCalendar.getTime());

            }
        };
    }
    private void TipoSexo(){
        if(rdMasculino.isChecked()==true){
            sexo="Masculino";
        }else{
            if(rdFemenino.isChecked()==true){
                sexo="Femenino";
            }
        }
        System.out.println(sexo);
    }

    private boolean AceptacionTerminos(){
        if(boxAceptarterminos.isChecked()==true){
            terminos = true;
            return true;
        }
        terminos = false;
        return false;
    }


    private void DespuesTerminos(){
        if(AceptacionTerminos()==true){
            showRegisterComplete();
        }else{
            Toast.makeText(Registro.this,"Debe Aceptar los Términos y Condiciones",Toast.LENGTH_LONG).show();
        }
    }
    public void crearusuario(View view){
        System.out.println( ":v entro");
        RegisterRequest usuario = new RegisterRequest();
        usuario.setDocIdentificacion(cedula.getText().toString());
        usuario.setNombres(nombre.getText().toString());
        usuario.setApellidos(apellido.getText().toString());
        usuario.setRazonSocial(razonsocial.getText().toString());
        usuario.setPais(sppais.getSelectedItem().toString());
        usuario.setFechaNacimiento(fecha);
        usuario.setTelefono(telefono.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setClave(contra.getText().toString());
        TipoSexo();
        usuario.setGenero(sexo);
        AceptacionTerminos();
        usuario.setTerminosCondiciones(terminos);


        Call<UsuarioResponse> call=usuarioService.signup(usuario);
        System.out.println("paso los datosd");
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                if(response.isSuccessful()){
                    DespuesTerminos();
                    System.out.println("entro y si valio \n Creadouski");

                }else{
                    System.out.println("Esta cedula ya esta creada");
                    showRegisterError();
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                System.out.println(t.toString());
                showRegisterError();

            }
        });
    }

    private void showRegisterComplete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_confirmacion_registro, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnComplete = view.findViewById(R.id.btnOk);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SesionIniciada.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }

    private void showRegisterError(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_error_registro, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnComplete = view.findViewById(R.id.btnOkError);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void CargarPaises(){
        String[] profesion = {"Seleccione el país de origen...", "Afganistán","Albania","Alemania","Andorra",
                "Angola","Antigua y Barbuda","Arabia Saudita","Argelia","Argentina","Armenia","Australia",
                "Austria","Azerbaiyán","Bahamas","Bangladés","Barbados","Baréin","Bélgica","Belice","Benín",
                "Bielorrusia","Birmania","Bolivia","Bosnia y Herzegovina","Botsuana","Brasil","Brunéi",
                "Bulgaria","Burkina Faso","Burundi","Bután","Cabo Verde","Camboya","Camerún","Canadá","Catar",
                "Chad","Chile","China","Chipre","Ciudad del Vaticano","Colombia","Comoras","Corea del Norte",
                "Corea del Sur","Costa de Marfil","Costa Rica","Croacia","Cuba","Dinamarca","Dominica","Ecuador",
                "Egipto","El Salvador","Emiratos Árabes Unidos","Eritrea","Eslovaquia","Eslovenia","España",
                "Estados Unidos","Estonia","Etiopía","Filipinas","Finlandia","Fiyi","Francia","Gabón","Gambia",
                "Georgia","Ghana","Granada","Grecia","Guatemala","Guyana","Guinea","Guinea ecuatorial","Guinea-Bisáu",
                "Haití","Honduras","Hungría","India","Indonesia","Irak","Irán","Irlanda","Islandia","Islas Marshall",
                "Islas Salomón","Israel","Italia","Jamaica","Japón","Jordania","Kazajistán","Kenia","Kirguistán",
                "Kiribati","Kuwait","Laos","Lesoto","Letonia","Líbano","Liberia","Libia","Liechtenstein","Lituania",
                "Luxemburgo","Madagascar","Malasia","Malaui","Maldivas","Malí","Malta","Marruecos","Mauricio",
                "Mauritania","México","Micronesia","Moldavia","Mónaco","Mongolia","Montenegro","Mozambique","Namibia",
                "Nauru","Nepal","Nicaragua","Níger","Nigeria","Noruega","Nueva Zelanda","Omán","Países Bajos","Pakistán",
                "Palaos","Palestina","Panamá","Papúa Nueva Guinea","Paraguay","Perú","Polonia","Portugal","Reino Unido",
                "República Centroafricana","República Checa","República de Macedonia","República del Congo",
                "República Democrática del Congo","República Dominicana","República Sudafricana","Ruanda","Rumanía",
                "Rusia","Samoa","San Cristóbal y Nieves","San Marino","San Vicente y las Granadinas","Santa Lucía",
                "Santo Tomé y Príncipe","Senegal","Serbia","Seychelles","Sierra Leona","Singapur","Siria","Somalia","Sri Lanka",
                "Suazilandia","Sudán","Sudán del Sur","Suecia","Suiza","Surinam","Tailandia","Tanzania","Tayikistán","Timor Oriental",
                "Togo","Tonga","Trinidad y Tobago","Túnez","Turkmenistán","Turquía","Tuvalu","Ucrania","Uganda","Uruguay","Uzbekistán",
                "Vanuatu","Venezuela","Vietnam","Yemen","Yibuti","Zambia","Zimbabue", "OTRO..."};

        ArrayAdapter<String> pais = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, profesion);
        sppais.setAdapter(pais);

    }
}