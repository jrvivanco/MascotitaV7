package org.jrvivanco.mascotita.menu_opciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import javax.mail.*;
import javax.mail.internet.*;
import org.jrvivanco.mascotita.R;
import org.jrvivanco.mascotita.mail.SendMail;
import java.util.Date;
import java.util.Properties;

public class ContactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_contacto);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void enviaEmail(View v){
        EditText etEmail = (EditText) findViewById(R.id.etEmail);
        EditText etNombre = (EditText) findViewById(R.id.etNombre);
        EditText etMensaje = (EditText) findViewById(R.id.etMensaje);

        //Creating SendMail object
        SendMail sm = new SendMail(this, etEmail.getText().toString().trim(),
                etNombre.getText().toString().trim(), etMensaje.getText().toString().trim());
        //Executing sendmail to send email
        sm.execute();
    }

    public void enviarComentarios(View v) throws AddressException {

        EditText etEmail = (EditText) findViewById(R.id.etEmail);
        EditText etNombre = (EditText) findViewById(R.id.etNombre);
        EditText etMensaje = (EditText) findViewById(R.id.etMensaje);
        String email;
        String nombre;
        String mensaje;
        email = etEmail.getText().toString();
        nombre = etNombre.getText().toString();
        mensaje = etMensaje.getText().toString();

        //envío email con JavaMail, pero hay que cambiar configuración de envío (host, usuario, contraseña, etc)
        //envío con Gmail
        String host="smtp.gmail.com";
        String to= "account@gmail.com"; //destinatario de los emails
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.stmp.user", to); //usuario email
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        String username = "user";
                        String password = "pass"; //cambiar contraseña para que funcione
                        return new PasswordAuthentication(username, password);
                    }
                });
        // create a message
        try {
            MimeMessage msg = new MimeMessage(session);
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("Mensaje de " + nombre);
            msg.setSentDate(new Date());
            msg.setFrom(new InternetAddress(email));
            msg.setText(mensaje);
            Transport transport = session.getTransport("smtp");
            transport.send(msg);
        }catch (MessagingException mex) {
            Toast.makeText(getBaseContext(),  "Error enviando email de "+nombre + " con mensaje " + mensaje + " a " + to, Toast.LENGTH_LONG).show();
        }
    }
}