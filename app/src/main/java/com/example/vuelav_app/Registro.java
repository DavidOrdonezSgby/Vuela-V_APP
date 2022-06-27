package com.example.vuelav_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Models.RegisterRequest;
import com.example.vuelav_app.Logico.Response.UsuarioResponse;
import com.example.vuelav_app.Logico.Service.UsuarioService;
import com.example.vuelav_app.actividades.Login;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registro extends AppCompatActivity implements View.OnClickListener{

    private UsuarioService usuarioService = Apis.getUsuarioService();

    private EditText nombre, apellido, cedula ,razonsocial, telefono, email, contra;
    private Button btnDates, completarRegistro;
    private DatePickerDialog.OnDateSetListener setListener;
    private String fecha, sexo;
    private Spinner sppais;
    private RadioButton rdMasculino,rdFemenino;
    private CheckBox boxAceptarterminos;
    private boolean terminos;
    private PendingIntent pendingIntent;
    private final static  String CHANNEL_ID="NOFTIFICACION";
    private final static int NOTIFICACION_ID =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        dtsfindVew();
        completarRegistro = (Button) findViewById(R.id.btnRegistrar);
        completarRegistro.setOnClickListener(this);
        CargarPaises();
        MostrarFecha();
        setPendingIntent();



    }

private void dtsfindVew(){
    btnDates = (Button) findViewById(R.id.btnDate);
    nombre = (EditText) findViewById(R.id.txtNombreRegistro);
    apellido = (EditText) findViewById(R.id.txtApellidoRegistro);
    cedula = (EditText) findViewById(R.id.txtCedulaRegistro);
    telefono = (EditText) findViewById(R.id.txtCelularRegistro);
    razonsocial = (EditText)findViewById(R.id.txtRazonSocialRegistro);
    email = (EditText) findViewById(R.id.txtUsernameRegistro);
    contra = (EditText) findViewById(R.id.txtPasswordregistro);
    boxAceptarterminos = findViewById(R.id.boxterminoscondiciones);
    sppais = findViewById(R.id.spPaisRegistro);
    rdMasculino = findViewById(R.id.rdregistromasculino);
    rdFemenino = findViewById(R.id.rdregistrofemenino);
    boxAceptarterminos = findViewById(R.id.boxterminoscondiciones);

}
private void MostrarFecha(){
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
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
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
            btnDates.setHint(selectDate);
            System.out.println(selectDate);

            SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            fecha = formats.format(mCalendar.getTime());

        }
    };
}

    private void crearusuario(){
        System.out.println( ":v entro");
        RegisterRequest usuario = new RegisterRequest();
        usuario.setDocIdentificacion(cedula.getText().toString());
        usuario.setNombres(nombre.getText().toString());
        usuario.setApellidos(apellido.getText().toString());
        usuario.setRazonSocial(razonsocial.getText().toString());
        usuario.setFechaNacimiento(fecha);
        usuario.setTelefono(telefono.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setClave(contra.getText().toString());
        usuario.setPais(sppais.getSelectedItem().toString());
        TipoSexo();
        usuario.setGenero(sexo);
        usuario.setTerminosCondiciones(terminos);

        Call<UsuarioResponse> call=usuarioService.signup(usuario);
        System.out.println("paso los datosd");
        call.enqueue(new Callback<UsuarioResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
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
    private void setPendingIntent(){
        Intent intent = new Intent(this, SesionIniciada.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(SesionIniciada.class);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);
    }
    private void EviarNotificacion() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_how_to_reg_24);
        builder.setContentTitle("Notificacion - Registro con éxito");
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA,1000,1000);
        builder.setVibrate(new long[]{1000,1000,1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICACION_ID,builder.build());
    }

    private void CargarPaises(){
        String[] paises = {"Seleccione el país de origen...", "Afganistán","Albania","Alemania","Andorra",
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

        ArrayAdapter<String> pais = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, paises);
        sppais.setAdapter(pais);
        sppais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                Toast.makeText(getApplicationContext(), "Ya puede iniciar sesión",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Login.class);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void DespuesTerminos(){
        if(terminos==true){
            showRegisterComplete();
            EviarNotificacion();
            createNotificationChanne();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChanne() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            CharSequence name = "Notificacion - Registro Exitoso";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name,NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private boolean validar() {
        boolean retorno = true;
        String c1 = cedula.getText().toString();
        String c2 = nombre.getText().toString();
        String c3 = apellido.getText().toString();
        String c4 = razonsocial.getText().toString();
        String c5 = telefono.getText().toString();
        String c6 = email.getText().toString();
        String c7 = contra.getText().toString();
        String c8 = btnDates.getHint().toString();

        if (c1.isEmpty()) {
            cedula.setError("Ingresar la cédula");
            retorno = false;
        }else if(c1.length()<10){
            cedula.setError("Cédula incorrecta");
            retorno = false;
        }
        if (c2.isEmpty()) {
            nombre.setError("Ingresar el nombre");
            retorno = false;
        }
        if (c3.isEmpty()) {
            apellido.setError("Ingresar el apellido");
            retorno = false;
        }
        if (c4.isEmpty()) {
            razonsocial.setError("Ingresar la razón social");
            retorno = false;
        }
        if (c5.isEmpty()) {
            telefono.setError("Ingresar el teléfono");
            retorno = false;
        }else if(c5.length()<10){
            telefono.setError("Celular incorrecto");
            retorno = false;
        }
        if (c6.isEmpty() ) {
            email.setError("Ingresar el email");
            retorno = false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(c6).matches()){
            email.setError("Email invalido");
            retorno = false;
        }
        if (c7.isEmpty()) {
            contra.setError("Ingresar una contraseña");
            retorno = false;
        }else if(c7.length()<8){
            contra.setError("Contraseña debe tener más de 8 caracteres");
            retorno = false;
        }
        if (("Seleccione la fecha").equals(c8)) {
            btnDates.setError("Seleccione una fecha");
            retorno = false;
        }else{
            btnDates.setError(null);
        }
        if(sppais.getSelectedItem().toString().equals("Seleccione el país de origen...")){
            System.out.println("Seleccione el pais" );
            Toast.makeText(Registro.this,"Seleccione su pais de origen",Toast.LENGTH_LONG).show();
            retorno = false;
        }
        if(boxAceptarterminos.isChecked()==false){
            Toast.makeText(Registro.this,"Debe Aceptar los Términos y Condiciones",Toast.LENGTH_LONG).show();
            terminos = false;
            retorno = false;
        }else{
            terminos = true;
        }

        return retorno;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnRegistrar:
                if(!validar()){
                    Toast.makeText(this, "Ingresar Datos", Toast.LENGTH_SHORT).show();
                }else{
                    crearusuario();
                }
                break;
        }
    }
}