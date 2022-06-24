package com.example.vuelav_app.Fragments.NotificationFragmentModel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.vuelav_app.Logico.Models.Usuario.ReservaModel;
import com.example.vuelav_app.Logico.Response.ReservaResponse;
import com.example.vuelav_app.R;

import java.util.List;

public class NotificacionesAdaptador extends ArrayAdapter<ReservaResponse> {

    private Context context;
    private List<ReservaResponse> reserva;

    public NotificacionesAdaptador(@NonNull Context context, int resource, List<ReservaResponse>reserva) {
        super(context, resource, reserva);
        this.context = context;
        this.reserva = reserva;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_notificacion, parent, false);
        System.out.println("Error obtener item notifiacion");
        TextView Nomvuelo = (TextView) row.findViewById(R.id.textView_vuelo);
        TextView idVuelo = (TextView) row.findViewById(R.id.textView_id_vuelo);
        TextView detalle = (TextView) row.findViewById(R.id.textView_llegada_salida);

        Nomvuelo.setText(reserva.get(position).getDestino());
        idVuelo.setText(reserva.get(position).getObservacion());
        detalle.setText("Hora de Salida: "+reserva.get(position).getHoraSalida()+", Hora de Llegada:"+reserva.get(position).getHoraLlegada());

        /**row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetalleListarProductosAdministrador.class);
                intent.putExtra("ID", String.valueOf(productos.get(position).getId_product()));
                intent.putExtra("Nombre", String.valueOf(productos.get(position).getName()));
                intent.putExtra("Descripcion", String.valueOf(productos.get(position).getDescription()));
                intent.putExtra("Precio", String.valueOf(productos.get(position).getPrice()));
                intent.putExtra("IDCAT", String.valueOf(productos.get(position).getId_categoria()));
                intent.putExtra("URLFOTO", String.valueOf(productos.get(position).getUrlFoto()));

                System.out.println("ID PRODUCTO"+productos.get(position).getId_product());

                context.startActivity(intent);
            }
        });**/

        return row;
    }
}
